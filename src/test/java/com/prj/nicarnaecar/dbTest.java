package com.prj.nicarnaecar;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.beans.factory.annotation.Autowired;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class dbTest {

	private static Logger Logger = LoggerFactory.getLogger(dbTest.class);
	
	@Autowired 
	private DataSource ds;
	
	@Test
	// DataSource 접속여부 확인 테스트
	public void testConnection() {
		try {
			Connection conn = ds.getConnection();
			Logger.info("성공");
			System.out.println("성공");
		} catch (SQLException e) {
			Logger.info("실패");
			System.out.println("실패");
			e.printStackTrace();
		}
	}
	
	@Autowired
	private SqlSessionFactory sqlFactory;
	@Test
	//SqlSessionFactory 접속여부 테스트
	public void testSqlSessionFactory() {
		Logger.info(sqlFactory.toString() + " : sessionFactory 접속성공");
	}
	
	/*@Test
	public void testSqlSessionTemplate() {
		try(SqlSession session = sqlFactory.openSession()) {
			Logger.info(session.toString() + " : session 가져오기 성공");
		} catch(Exception e) {
			Logger.info("session 가져오기 실패");
			e.printStackTrace();
		}
	}*/
}
