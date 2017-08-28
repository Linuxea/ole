package com.ole.core.interfaces;

/**
 * Created by Linuxea on 2017/8/21.
 */
public interface ITable {

    int save();

    int update();

    int deleteById();

    ITable findById();


}
