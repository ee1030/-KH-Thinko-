package com.kh.thinko.common;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class JDBCTemplate {
	private int age;
	private String name;
	// 1. 반복되는 Connection 객체의 생성을 간소화
	// 2. 트랜잭션 처리, close() 처리의 간소화

	private static Connection conn = null;

	private JDBCTemplate() {}
	
	/* 이전 JDBC 수업때는 요청 시 마다 Connection을 새로 만들고 반환하는 과정을 거쳤다.
	 * -> 요청 시간이 지연됨. (비효율적)
	 * 
	 * WAS(Web Application Server, Tomcat (== Servlet Container))가 
	 * 미리 DB에 접속 할 수 있는 객체(Connection)를 일정 개수를 만들어 두고
	 * 요청이 올 때 마다 만들어둔 객체를 전달하고 사용 완료 후 반환받는
	 * Connection Pool을 사용함.
	 * 
	 * */
	
	// DB 연결을 위한 Connection 객체를 간접적으로 얻어가는 메소드를 생성
	public static Connection getConnection() throws NamingException, SQLException {
		// JNDI(Java Naming and Directory Interface API)
		/*디렉터리 서비스에 접근하는데 사용하는 API
		어플리케이션은 JNDI를 사용하여 서버의 resource를 찾는다.
		특히 JDBC resource를 data source라고 부른다.
		
		Resource를 서버에 등록할 때 고유한 JNDI 이름을 붙이는데, JNDI 이름은 디렉터리 경로 형태를 가진다.
		예를 들어 data source의 JNDI 이름은 'jdbc/mydb' 형식으로 짓는다.
		
		 서버에서 'jdbc/oracle'라는 DataSource를 찾으려면 
		'java:comp/env/jdbc/oracle'라는 JNDI 이름으로 찾아야 한다. 
		즉 lookup() 메소드에 'java:comp/env/jdbc/oracle'를 인자값으로 넘긴다.
		
		*/
		
		// Servers에 존재하는 context.xml 파일을 찾는 작업
		Context initContext = new InitialContext();
		Context envContext  = (Context)initContext.lookup("java:/comp/env");  // java:comp/env	응용 프로그램 환경 항목
		
		// context.xml 파일에서 name이 "jdbc/oracle"인 DataSource를 얻어옴
		// DataSource : DriverManager를 대체하는 객체로 
		// Connection 생성, Connectoin pool을 구현하는 객체
		DataSource ds = (DataSource)envContext.lookup("jdbc/oracle");

		conn = ds.getConnection(); // DataSource에 의해 미리 만들어진 Connection 중 하나를 얻어옴.
		
		conn.setAutoCommit(false);
		
		return conn;
	}
	
	// 트랜잭션 처리(commit, rollback)도 공통적으로 사용함
	// static으로 미리 선언 코드길이 감소 효과 + 재사용성
	public static void commit(Connection conn) {
		try {
			if(conn != null && !conn.isClosed()) {
				// 참조하고 있는 커넥션이 닫혀있지 않은 경우
				conn.commit();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void rollback(Connection conn) {
		try {
			if(conn != null && !conn.isClosed()) {
				// 참조하고 있는 커넥션이 닫혀있지 않은 경우
				conn.rollback();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// DB 연결 자원 반환 구문도 static으로 작성
	public static void close(Connection conn) {
		try {
			if(conn != null && !conn.isClosed()) {
				// 참조하고 있는 커넥션이 닫혀있지 않은 경우
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void close(ResultSet rset) {
		try {
			if(rset != null && !rset.isClosed()) {
				// 참조하고 있는 ResultSet이 닫혀있지 않은 경우
				rset.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// Statement, PreparedStatement 두 객체를 반환하는 메소드
	// 어떻게? PreparedStatement는 Statement의 자식이므로
	// 매개변수 stmt에 다형성이 적용되어 자식 객체인 PreparedStatement를 참조 가능
	public static void close(Statement stmt) {
		try {
			if(stmt != null && !stmt.isClosed()) {
				// 참조하고 있는 Statement가 닫혀있지 않은 경우
				stmt.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
