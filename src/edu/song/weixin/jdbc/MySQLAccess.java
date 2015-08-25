package edu.song.weixin.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MySQLAccess {

	public static Connection getConnection(){
		Connection conn = null;
		String url = null;
		String user = null;
		String password = null;
		try {
			Class.forName("com.mysql.jdbc.Driver"); // 加载mysq驱动
		} catch (ClassNotFoundException e) {
			System.out.println("驱动加载错误");
			e.printStackTrace();// 打印出错详细信息
		}
		try {
			url = "jdbc:mysql://121.40.159.7:3306/weixintest?useUnicode=true&characterEncoding=utf8";// 简单写法：url																													// user=root(用户)&password=yqs2602555(密码)";
			user = "root";
			password = "root";
			conn = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			System.out.println("数据库链接错误");
			e.printStackTrace();
		}
		return conn;
	}
	
	public static String getTextReply(String content){
		Connection conn = getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		String sql = null;
		String reply = "null";
		
		try {
			stmt = conn.createStatement();
			sql = "select * from test where content= '" + content + "'";
			System.out.println(sql);// dept这张表有deptno，deptname和age这三个字段
			rs = stmt.executeQuery(sql);// 执行sql语句
			System.out.println(rs.getFetchSize());
			
			while (rs.next()) {
				
				reply = rs.getString("reply");
				System.out.println(reply);
			}
		} catch (SQLException e) {
			System.out.println("数据操作错误");
			e.printStackTrace();
		}
		// 关闭数据库
		try {
			if (rs != null) {
				rs.close();
				rs = null;
			}
			if (stmt != null) {
				stmt.close();
				stmt = null;
			}
			if (conn != null) {
				conn.close();
				conn = null;
			}
		}catch (Exception e) {
			System.out.println("数据库关闭错误");
			e.printStackTrace();
		}
		return reply;
	}
}
