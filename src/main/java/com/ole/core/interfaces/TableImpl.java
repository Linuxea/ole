package com.ole.core.interfaces;

import static com.google.common.base.Preconditions.*;

import com.google.common.collect.Maps;
import com.ole.core.annotation.Column;
import com.ole.core.annotation.Id;
import com.ole.core.annotation.Table;
import com.ole.core.plugins.DruidPlugin;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;
import java.util.Set;

/**
 * Created by Linuxea on 2017/8/21.
 */
public class TableImpl implements ITable {

	private Map<String,Class> idMap;
	private Map<String,Class> columnsMap;
	private String name; //table name

	/**
	 * init
	 * 解析此table class
	 */
	private void init(){
		Table table = this.getClass().getAnnotation(Table.class);
		checkNotNull(table, "this class isn't mapping to a table");
		idMap = Maps.newTreeMap();
		columnsMap = Maps.newTreeMap();
		this.name = table.name();
		Field[] fields = this.getClass().getDeclaredFields();
		for(Field field: fields){
			field.setAccessible(true);
			Column column = field.getAnnotation(Column.class);
			if(null!=column){
				String columnName = column.name();
				Class columnType = column.getClass();
				columnsMap.put(columnName, columnType);
				continue; //不会既是column又是id注解
			}
			Id id = field.getAnnotation(Id.class);
			if(null != id){
				String idName = id.name();
				Class idType = id.getClass();
				idMap.put(idName, idType);
			}
		}
	}

	public Set<String> getIdName(){
		return idMap.keySet();
	}

	public Set<String> getColumnsName(){
		return columnsMap.keySet();
	}

	public TableImpl() {
		this.init();
	}

	@Override
	public int save() {
		System.out.println("save");
		Set<String> columnsSet = getColumnsName();
		StringBuilder stringBuilder = new StringBuilder("(");
		StringBuilder questionMark = new StringBuilder("(");
		for(String string : columnsSet){
			stringBuilder.append(string + ",");
			questionMark.append("?,");
		}
		String columnsExceptDot = stringBuilder.substring(0, stringBuilder.length()-1);
		columnsExceptDot +=")";
		String questionExceptDot = questionMark.substring(0, questionMark.length()-1);
		questionExceptDot += ")";
		String sql = "insert into " + this.name + columnsExceptDot + " values " + questionExceptDot + ";";
		System.out.println(sql);
		Connection connection = DruidPlugin.getConnection();
		PreparedStatement preparedStatement;
		int result = 0;
		try {
			preparedStatement = connection.prepareStatement(sql);
			for(int i = 0;i<columnsSet.size();i++){
				preparedStatement.setObject(i+1, "q");
			}
			result = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
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
		System.out.println("delete");
		return 0;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
