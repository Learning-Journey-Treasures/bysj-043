package com.db;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

import java.sql.Statement;

public class conn {

	private Connection conn = null;        
	private Statement stmt = null;   
	private ResultSet rs = null;

	// ****************建立连接**********************
		public conn() {
		try {
	            Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (Exception e) {
			
		}
		try {
			String path = "jdbc:mysql://localhost:3306/zonghefuwu?characterEncoding=UTF-8";

			String name = "root";
			String word = "root";
			conn = DriverManager.getConnection(path, name, word);
	} catch (Exception e) {
		
	}
	}



	// ********************查询*********************
	public ResultSet query(String sql) {
		try {
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			rs = stmt.executeQuery(sql);
			System.out.println("数据库查询成功  "+sql);
			return rs;
		}
		catch (Exception e) {
			
		}
		return rs;
	}
	// ******************添加/修改/删除*************************
	public int update(String sql){
		try {
			stmt = conn.createStatement();
			System.out.println("更新成功  "+sql);
			return stmt.executeUpdate(sql);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return 0;	
		
	}
	
		
	public boolean  exist(String sql) {
		try {
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			rs = stmt.executeQuery(sql);
		
			if(rs.next()){
				return true;
			}
			else
			{
				return false;
			}
			
		}
		catch (Exception e) {
			
		}
		return false;
	}

	
	
	public void close() throws Exception{
		if(rs   != null) rs.close();
		if(stmt != null) stmt.close();
		if(conn != null) conn.close();
	}
}