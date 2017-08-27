package com.ole.core.interfaces;

import static com.google.common.base.Preconditions.*;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.ole.core.annotation.Column;
import com.ole.core.annotation.Id;
import com.ole.core.plugins.DruidPlugin;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by Linuxea on 2017/8/21.
 */
public class TableImpl implements ITable{

	private Map<String,Class> idMap;
	private Map<String,Class> columnsMap;
	private String tableName; //table name
    private Column column;

    /**
	 * init
	 * 解析此table class
	 */
	private void init(){
		com.ole.core.annotation.Table table = this.getClass().getAnnotation(com.ole.core.annotation.Table.class);
		checkNotNull(table, "this class isn't mapping to a table");
		idMap = Maps.newTreeMap();
		columnsMap = Maps.newTreeMap();
		this.tableName = table.name();
		if(StringUtils.isEmpty(tableName)){ //Table注解没有name值的时候默认为当前类的名称
		    this.tableName = this.getClass().getSimpleName().toLowerCase();
        }
		Field[] fields = this.getClass().getDeclaredFields();
		for(Field field: fields){
			field.setAccessible(true);
            column = field.getAnnotation(Column.class);
			if(null!= column){
				String columnName = column.name();
				if(StringUtils.isEmpty(columnName)){
                    columnName = field.getName();
                }
				Class columnType = column.getClass();
				columnsMap.put(columnName, columnType);
				continue; //不会既是column又是id注解
			}
			Id id = field.getAnnotation(Id.class);
			if(null != id){
				String idName = id.name();
                if(StringUtils.isEmpty(idName)){
                    idName = field.getName();
                }
				Class idType = id.getClass();
				idMap.put(idName, idType);
			}
		}
	}


	public Set<String> getColumnsName(){
		return columnsMap.keySet();
	}

	public Set<String> getIdsColumnsName(){
	    return idMap.keySet();
    }

	public Set<String> getIdWithOtherColumnsName(){
	    Set<String> setString = Sets.newHashSet();
        setString.addAll(getIdsColumnsName());
        setString.addAll(getColumnsName());
        return setString;
    }

	public TableImpl() {
		this.init();
	}

	@Override
	public int save(){
		Set<String> allColumnsSet = getIdWithOtherColumnsName();
		StringBuilder stringBuilder = new StringBuilder("(");
		StringBuilder questionMark = new StringBuilder("(");
		for(String string : allColumnsSet){
			stringBuilder.append(string + ",");
			questionMark.append("?,");
		}
		String columnsExceptDot = stringBuilder.substring(0, stringBuilder.length()-1);
		columnsExceptDot +=")";
		String questionExceptDot = questionMark.substring(0, questionMark.length()-1);
		questionExceptDot += ")";
		String sql = "insert into " + this.tableName + columnsExceptDot + " values " + questionExceptDot + ";";
		System.out.println("sql:" + sql);
		Connection connection = DruidPlugin.getConnection();
		PreparedStatement preparedStatement;
		int result = 0;
		try {
			preparedStatement = connection.prepareStatement(sql);
			Iterator<String> iterable = allColumnsSet.iterator();
			int i = 0;
			Class<? extends TableImpl> clazz = this.getClass();
			while (iterable.hasNext()){
			    String column = iterable.next();
                Method method = clazz.getMethod("get" + StringUtils.capitalize(column));
                Object returnVal = method.invoke(this);
                preparedStatement.setObject(++i, returnVal);
            }
			result = preparedStatement.executeUpdate();
		} catch ( ReflectiveOperationException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
			DruidPlugin.releaseConnection(connection);
		}
		return result;
	}

	@Override
	public int update() {
		System.out.println("update");
		return 0;
	}

	@Override
	public int delete() {
		StringBuilder stringBuilder = new StringBuilder(35);
		stringBuilder.append(" delete from " + this.getTableName() );
		stringBuilder.append(" where  1=1 ");
		Iterator<String> iterator = idMap.keySet().iterator();
        Class<? extends TableImpl> clazz = this.getClass();
		for(;iterator.hasNext();) {
            String idName = iterator.next();
            Method method ;
            Object returnVal = null;
           try{
               method = clazz.getMethod("get" + StringUtils.capitalize(idName));
               returnVal = method.invoke(this);
           }catch (ReflectiveOperationException e){
               e.printStackTrace();
           }
		    stringBuilder.append(" and " + idName + " = " + returnVal + " ");
        }
        System.out.println(stringBuilder.toString());
        Connection connection = DruidPlugin.getConnection();
        PreparedStatement preparedStatement;
        int result = 0;
        try {
            preparedStatement = connection.prepareStatement(stringBuilder.toString());
            result = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
	}

	@Override
	public ITable findById() {
		System.out.println("findById");
		return null;
	}

	public Map<String, Class> getIdMap() {
		return idMap;
	}

	public void setIdMap(Map<String, Class> idMap) {
		this.idMap = idMap;
	}

	public Map<String, Class> getColumnsMap() {
		return columnsMap;
	}

	public void setColumnsMap(Map<String, Class> columnsMap) {
		this.columnsMap = columnsMap;
	}

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
}
