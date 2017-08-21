package com.ole.core.interfaces;

import static com.google.common.base.Preconditions.*;
import com.ole.core.annotation.Column;
import com.ole.core.annotation.Id;
import com.ole.core.annotation.Table;

import java.lang.reflect.Field;
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
		this.name = table.name();
		Field[] fields = this.getClass().getFields();
		for(Field field: fields){
			field.setAccessible(true);
			Column column = field.getAnnotation(Column.class);
			if(null!=column){
				String columnName = column.name();
				Class columnType = column.type();
				columnsMap.put(columnName, columnType);
				continue; //不会既是column又是id注解
			}
			Id id = field.getAnnotation(Id.class);
			if(null != id){
				String idName = id.name();
				Class idType = id.type();
				idMap.put(idName, idType);
			}
		}
	}

	public Set<String> getIdName(){
		return idMap.keySet();
	}

	@Override
	public int save() {
		return 0;
	}

	@Override
	public int update() {
		return 0;
	}

	@Override
	public int delete() {
		return 0;
	}

	@Override
	public ITable findById() {
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
