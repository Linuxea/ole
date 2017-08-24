package com.ole.core.utils;

import com.ole.core.interfaces.IAtom;
import com.ole.core.interfaces.ITable;
import com.ole.core.plugins.DruidPlugin;

import java.sql.Connection;
import java.sql.SQLException;

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

	/**
	 * 事务原子方法
	 * @param iAtom
	 */
	public static void atom(IAtom iAtom){
		Connection connection = DruidPlugin.getConnection();
		try{
			connection.setAutoCommit(false);
			iAtom.run();
            connection.commit();
		}catch (Exception e){
            e.printStackTrace();
		    try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
		}finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            DruidPlugin.releaseConnection(connection);
		}
	}

}
