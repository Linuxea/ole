package com.old.core;

/**
 * Created by Linuxea on 2017/8/21.
 */
public abstract class ITable {

	abstract int save();

	abstract int update();

	abstract int delete();

	abstract ITable findById();
}
