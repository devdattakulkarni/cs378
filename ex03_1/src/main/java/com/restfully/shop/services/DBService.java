package com.restfully.shop.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;

import com.mysql.jdbc.DatabaseMetaData;
import com.restfully.shop.domain.Student;

public class DBService {
	
	String dbURL = "";
	DataSource ds;

	public DBService() {
		dbURL = "jdbc:mysql://localhost:3306/student_courses";
		ds = setupDataSource(dbURL);
		
	}
	
	public static DataSource setupDataSource(String connectURI) {
        BasicDataSource ds = new BasicDataSource();
        //ds.setDriverClassName("oracle.jdbc.driver.OracleDriver");
        ds.setUsername("devdatta");
        ds.setPassword("");
        ds.setUrl(connectURI);
        return ds;
    }
	
	public void findScrollType() {
		Connection conn = null;		
		try {
			conn = DriverManager.getConnection(dbURL,"devdatta", "");			
			DatabaseMetaData meta = (DatabaseMetaData) conn.getMetaData();			
			if (meta.supportsResultSetType(ResultSet.TYPE_FORWARD_ONLY)) {
			      System.out.println("type name=TYPE_FORWARD_ONLY");
			    }
			    if (meta.supportsResultSetType(ResultSet.TYPE_SCROLL_INSENSITIVE)) {
			      System.out.println("type name=TYPE_SCROLL_INSENSITIVE");
			    }
			    if (meta.supportsResultSetType(ResultSet.TYPE_SCROLL_SENSITIVE)) {
			      System.out.println("type name=TYPE_SCROLL_SENSITIVE");
			    }			    
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void findTransactionIsolationLevel() {
		Connection conn = null;		
		try {
			conn = DriverManager.getConnection(dbURL,"devdatta", "");
			int isolationLevel = conn.getTransactionIsolation();
			if (isolationLevel == conn.TRANSACTION_NONE) {
				System.out.println("Transaction isolation level is NONE");
			}
			if (isolationLevel == conn.TRANSACTION_READ_COMMITTED) {
				System.out.println("Transaction isolation level is READ_COMMITTED");
			}
			if (isolationLevel == conn.TRANSACTION_READ_UNCOMMITTED) {
				System.out.println("Transaction isolation level is READ_UNCOMMITTED");
			}
			if (isolationLevel == conn.TRANSACTION_REPEATABLE_READ) {
				System.out.println("Transaction isolation level is REPEATABLE_READ");
			}
			if (isolationLevel == conn.TRANSACTION_SERIALIZABLE) {
				System.out.println("Transaction isolation level is SERIALIZABLE");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void findConcurrencyType() {
		Connection conn = null;		
		try {
			conn = DriverManager.getConnection(dbURL,"devdatta", "");			
			DatabaseMetaData meta = (DatabaseMetaData) conn.getMetaData();			
			if (meta.supportsResultSetType(ResultSet.CONCUR_READ_ONLY)) {
			      System.out.println("type name=CONCUR_READ_ONLY");
			    }
			else if (meta.supportsResultSetType(ResultSet.CONCUR_UPDATABLE)) {
			      System.out.println("type name=CONCUR_UPDATABLE");
			    }
			else {
				System.out.println("Cannot determine concurrency type.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private Connection getConnection() {
		Connection conn = null;		
		try {
			conn = DriverManager.getConnection(dbURL,"devdatta", "");
			
			DatabaseMetaData meta = (DatabaseMetaData) conn.getMetaData();
			
			if (meta.supportsResultSetType(ResultSet.TYPE_FORWARD_ONLY)) {
			      System.out.println("type name=TYPE_FORWARD_ONLY");
			    }
			    if (meta.supportsResultSetType(ResultSet.TYPE_SCROLL_INSENSITIVE)) {
			      System.out.println("type name=TYPE_SCROLL_INSENSITIVE");
			    }
			    if (meta.supportsResultSetType(ResultSet.TYPE_SCROLL_SENSITIVE)) {
			      System.out.println("type name=TYPE_SCROLL_SENSITIVE");
			    }
			    
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return conn;
	}
	
	public Student getStudentByCity(String city) {
		return null;
	}
	
	/*public Student getStudentByCity(String city) {		
		String query = "select * from student where city=?";		
		PreparedStatement ps = prepareQuery(query, city);		
		ResultSet results = executeQuery(ps);		
		Student student = compileResults(results);		
		return student;
	}*/
	
	public Student getStudentByName(String name) throws Exception {
		String query = "select * from student where name=" + name;		
		//PreparedStatement ps = prepareQuery(query, name);
		Connection conn = ds.getConnection();
		PreparedStatement ps = conn.prepareStatement(query);
		ResultSet results = executeQuery(ps);		
		//Student student = compileResults(results);		
		//return student;
		return null;
	}

	public void deleteStudentByName(String name) {
		String query = "delete from student where name=?";		
		PreparedStatement ps = prepareQuery(query, name);		
		executeUpdate(ps);
	}
	
	public void addStudent(String name, String city, int uteid) {
		Student student = new Student();
		student.setCity(city);
		student.setName(name);
		student.setUteid(uteid);
		
		String query = "insert into student(city, name, uteid) values(?,?,?)";
		Map<String, Object> studentInfo = new HashMap<String, Object>();
		studentInfo.put("city",city);
		studentInfo.put("name",name);
		studentInfo.put("uteid",uteid);
		PreparedStatement ps = prepareQuery(query, studentInfo);
		executeUpdate(ps);
	}
	
	public void updateStudentByName(String name, String city, int uteid) {
		String query = "update student set city=? where name=?";
		
		Map<String, Object> studentInfo = new HashMap<String, Object>();
		studentInfo.put("city",city);
		studentInfo.put("name",name);
		studentInfo.put("uteid", uteid);
		PreparedStatement ps = prepareQuery(query, studentInfo);
		executeUpdate(ps);
	}

	private Student compileResults(ResultSet r) {
		Student student = null;		
		try {
			if (!r.next()) {
				return null;
			}			
			student = new Student();
			student.setCity(r.getString("city"));
			student.setName(r.getString("name"));
			student.setUteid(r.getInt("uteid"));			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return student;
	}
	
	private void executeUpdate(PreparedStatement ps) {
		try {
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private ResultSet executeQuery(PreparedStatement ps) {
		ResultSet results = null;
		try {
			results = ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return results;
	}
	
	private PreparedStatement prepareQuery(String query, String value) {
		PreparedStatement statement = null;
		try {
			 Connection conn = ds.getConnection();
			 statement = conn.prepareStatement(query);
			 statement.setString(1, value);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return statement;
	}
	
	private PreparedStatement prepareQuery(String query, Map<String,Object>studentInfo) {
		PreparedStatement statement = null;
		try {
			 Connection conn = ds.getConnection();
			 statement = conn.prepareStatement(query);
			 statement.setString(1, (String)studentInfo.get("city"));			 
			 statement.setString(2, (String)studentInfo.get("name"));
			 statement.setInt(3, (Integer)studentInfo.get("uteid"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return statement;
	}
}