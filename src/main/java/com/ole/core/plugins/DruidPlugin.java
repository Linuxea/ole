package com.ole.core.plugins;

import com.alibaba.druid.pool.DruidDataSource;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Linuxea on 2017/8/21.
 */
public class DruidPlugin {

	private static String url;
	private static String userName;
	private static String password;

	private static DruidDataSource dataSource;

	private static void init(){
		dataSource = new DruidDataSource();
		dataSource.setUrl(url);
		dataSource.setUsername(userName);
		dataSource.setPassword(password);
		//TODO 更多配置敬请期待
	}

	static {
		init();
	}

	/**
	 * 获取数据库连接
	 * @return
	 * @throws SQLException
	 */
	public static Connection getConnection() {
		try {
			return dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 关闭数据库连接池
	 * @param connection
	 * @throws SQLException
	 */
	public static void releaseConnection(Connection connection) {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}




	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public DruidPlugin(String url, String userName, String password) {
		this.url = url;
		this.userName = userName;
		this.password = password;
	}
}
