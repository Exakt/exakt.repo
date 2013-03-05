package com.gsis.dao;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.DriverPropertyInfo;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.Properties;
import java.util.logging.Logger;

/*
 * @author : Johne Altamera
 */

public class JDCConnectionDriver implements Driver {

    public static final String URL_PREFIX = "jdbc:jdc:";
    private static final int MAJOR_VERSION = 1;
    private static final int MINOR_VERSION = 0;
    private JDCConnectionPool pool;

    public JDCConnectionDriver(String driver, String url, 
                                 String user, String password, Properties sqlConfig) 
                            throws ClassNotFoundException, 
                               InstantiationException, IllegalAccessException,
                                SQLException
    {

        DriverManager.registerDriver(this);
        Class.forName(driver).newInstance();
        pool = new JDCConnectionPool(url, user, password, sqlConfig);
    }

    public Connection connect(String url, Properties props) 
                                       throws SQLException {
        if(!url.startsWith(URL_PREFIX)) {
             return null;
        }
        return pool.getConnection();
    }

    public boolean acceptsURL(String url) {
        return url.startsWith(URL_PREFIX);
    }

    public int getMajorVersion() {
        return MAJOR_VERSION;
    }

    public int getMinorVersion() {
        return MINOR_VERSION;
    }

    public DriverPropertyInfo[] getPropertyInfo(String str, Properties props) {
        return new DriverPropertyInfo[0];
    }

    public boolean jdbcCompliant() {
        return false;
    }

	public Logger getParentLogger() throws SQLFeatureNotSupportedException {
		return null;
	}
}
