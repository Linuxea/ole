package com.ole.core.utils;

import com.ole.core.interfaces.ITable;

/**
 * Created by Linuxea on 2017/8/21.
 * 普通工具类
 */
public class Db {

	public static int save(ITable iTable){
		return iTable.save();
	}

	public static int update(ITable iTable){
		return iTable.update();
	}

	public static int delete(ITable iTable){
		return iTable.delete();
	}

	public static ITable findById(ITable iTable){
		return iTable.findById();
	}

}
