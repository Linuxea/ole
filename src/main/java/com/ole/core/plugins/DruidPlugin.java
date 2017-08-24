package com.ole.core.plugins;

import com.alibaba.druid.pool.DruidDataSource;
import com.ole.core.interfaces.IAtom;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

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
		scanProperties();
		init();
	}

	public static void scanProperties(){
		Properties properties = new Properties();
		InputStream fileInputStream = null;
		try {
			fileInputStream =
					DruidPlugin.class.getClassLoader().getResourceAsStream("db.properties");
			properties.load(fileInputStream);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		url = properties.getProperty("dburl");
		userName = properties.getProperty("userName");
		password = properties.getProperty("password");

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
