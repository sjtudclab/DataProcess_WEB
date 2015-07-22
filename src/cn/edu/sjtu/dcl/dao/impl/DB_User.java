package cn.edu.sjtu.dcl.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DB_User {

	private DB_Conn db_conn = new DB_Conn();

	public boolean validateUser(String username, String password)
			throws Exception {
		db_conn.ConnectDB();
		ResultSet rs = null;
		try {
			String sql = "select * from DATA_PROCESS_USER_TABLE where name ='"
					+ username + "'";
			rs = db_conn.sm.executeQuery(sql);

			if (rs.next()) {
				String pwdDB = rs.getString("password");
				return pwdDB.equals(password);
			}
		} catch (SQLException SqlE) {
			SqlE.printStackTrace();
			throw SqlE;
		} catch (Exception E) {
			E.printStackTrace();
			throw E;
		} finally {
			db_conn.CloseDB();
		}
		
		return false;
	}
}
