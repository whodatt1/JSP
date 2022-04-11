package repository;

import java.sql.Connection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DBConnector {
	private static Logger log = LoggerFactory.getLogger(DBConnector.class);
	
	public static Connection getConnection() {
		Connection cn = null;
		try {
			Context initContext = new InitialContext();
			Context envContext = (Context) initContext.lookup("java:/comp/env");
			DataSource ds = (DataSource) envContext.lookup("jdbc/mysql");
			
			try {
				cn = ds.getConnection();
			} catch (Exception e) {
				log.info(">>> DB 커넥션 오류");
				e.printStackTrace();
				return null;
			}
		} catch (Exception e) {
			log.info(">>> DBCP 설정 오류");
			e.printStackTrace();
			return null;
		}
		
		return cn;
	}

}
