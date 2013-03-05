package com.gsis.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Properties;
import java.util.Vector;

/*
 * @author : Johne Altamera
 */

public class JDCConnectionPool {

	private Vector connections;
	private String url, user, password;
	private long timeout=60000;
	private ConnectionReaper reaper;
	private int poolsize=1000;
		
	public JDCConnectionPool(String url, String user, String password, Properties sqlConfig) {
		this.url = url;
	    this.user = user;
	    this.password = password;
	    
	    try{
	    	poolsize = Integer.parseInt(sqlConfig.getProperty("poolsize"));
	    	timeout = Integer.parseInt(sqlConfig.getProperty("timeout"));
	    }catch(NullPointerException e){
	    	poolsize = 1000;
	    	timeout = 60000;
	    }catch(Exception e){
	    	e.printStackTrace();
	    }
	    
		connections = new Vector(poolsize);
		reaper = new ConnectionReaper(this);
		reaper.start();
	}

	public synchronized void reapConnections() {

		long stale = System.currentTimeMillis() - timeout;
		Enumeration connlist = connections.elements();

		while((connlist != null) && (connlist.hasMoreElements())) {
			JDCConnection conn = (JDCConnection)connlist.nextElement();

			if((conn.inUse()) && (stale >conn.getLastUse()) && 
					(!conn.validate())) {
				removeConnection(conn);
			}
		}
	}

	public synchronized void closeConnections() {

		Enumeration connlist = connections.elements();

		while((connlist != null) && (connlist.hasMoreElements())) {
			JDCConnection conn = (JDCConnection)connlist.nextElement();
			removeConnection(conn);
		}
	}

	private synchronized void removeConnection(JDCConnection conn) {
		connections.removeElement(conn);
	}


	public synchronized Connection getConnection() throws SQLException {

		JDCConnection c;
		for(int i = 0; i < connections.size(); i++) {
			c = (JDCConnection)connections.elementAt(i);
			
			if (c.lease()) {
				return c;
			}
		}

		Connection conn = DriverManager.getConnection(url, user, password); //DriverManager.getConnection(connectionString);//
		c = new JDCConnection(conn, this);
		c.lease();
		connections.addElement(c);
		return c;
	} 

	public synchronized void returnConnection(JDCConnection conn) {
		conn.expireLease();
	}
}