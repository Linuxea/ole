package com.ole.core.interfaces;

/**
 * Created by Linuxea on 2017/8/21.
 */
public class TableImpl implements ITable {

	private Object[] ids;
	private Object[] columns;
	private String name;

	/**
	 * init
	 * 解析此table class
	 */
	private void init(){

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


	public Object[] getIds() {
		return ids;
	}

	public void setIds(Object[] ids) {
		this.ids = ids;
	}

	public Object[] getColumns() {
		return columns;
	}

	public void setColumns(Object[] columns) {
		this.columns = columns;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
