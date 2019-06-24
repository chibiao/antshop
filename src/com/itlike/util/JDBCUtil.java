package com.itlike.util;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.sql.DataSource;
//import org.apache.commons.dbcp.BasicDataSourceFactory;


import com.alibaba.druid.pool.DruidDataSourceFactory;

//jdbc连接工具类
public class JDBCUtil {
	public static DataSource ds = null;
	static {
		try {
			// 读取配置文件
			Properties p = new Properties();
			String path = JDBCUtil.class.getClassLoader().getResource("db.properties").getPath();
			// 加载
			FileInputStream in = new FileInputStream(path);
			p.load(in);
			//dbcp连接池
			//ds = BasicDataSourceFactory.createDataSource(p);
			ds = DruidDataSourceFactory.createDataSource(p);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//获取数据源
	public static DataSource getDataSource(){
		return ds;
	}
	//获取数据库连接
	/*public static Connection getConn() {
		try {
			// 连接数据库
			return ds.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}*/

	public static void close(Connection conn, Statement st, ResultSet res) {
		if (res != null) {
			try {
				res.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (st != null) {
			try {
				st.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
