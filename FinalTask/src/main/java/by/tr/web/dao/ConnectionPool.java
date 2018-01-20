package by.tr.web.dao;

import java.util.Locale;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.mysql.jdbc.Statement;
import by.tr.web.dao.PooledConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public final class ConnectionPool {
	
	private static final Logger log = Logger.getLogger(ConnectionPool.class);
	private BlockingQueue<Connection> connectionQueue;
	private BlockingQueue<Connection> givenAwayConQueue;
	private String driverName;
	private String url;
	private String user;
	private String password;
	private int poolSize;
	private static ConnectionPool instance;
	
	private ConnectionPool() throws ConnectionPoolException {
		
		DBResourceManager dbResourseManager = DBResourceManager.getInstance();
		this.driverName = dbResourseManager.getValue(DBParameter.DB_DRIVER);
		this.url = dbResourseManager.getValue(DBParameter.DB_URL);
		this.user = dbResourseManager.getValue(DBParameter.DB_USER);
		this.password = dbResourseManager.getValue(DBParameter.DB_PASSWORD);
		try {

			this.poolSize = Integer.parseInt(dbResourseManager.getValue(DBParameter.DB_POLL_SIZE));
		 
		} catch (NumberFormatException e) { 
			poolSize = 5;
		}
		initPoolData();
	}
	
	public static ConnectionPool getInstance() throws ConnectionPoolException {
		if(instance==null){
			instance = new ConnectionPool();
		}
        return instance;
    }
	
	public void initPoolData() throws ConnectionPoolException { 
		
		Locale.setDefault(Locale.ENGLISH);
		try {
			Class.forName(driverName); 
			givenAwayConQueue = new ArrayBlockingQueue<Connection>(poolSize);
			connectionQueue = new ArrayBlockingQueue<Connection>(poolSize);
			for (int i = 0; i < poolSize; i++) {
				Connection connection = DriverManager.getConnection(url, user, password);
				PooledConnection pooledConnection = new PooledConnection(connection);
				connectionQueue.add(pooledConnection);
			}
		} catch (SQLException e) {
			throw new ConnectionPoolException("SQLException in ConnectionPool",e);
		} catch (ClassNotFoundException e) {
			throw new ConnectionPoolException("Can't find database driver class", e);
		}
	}

	public void dispose() { 
		clearConnectionQueue();
	}

	private void clearConnectionQueue() {
		try {
			closeConnectionsQueue(givenAwayConQueue); closeConnectionsQueue(connectionQueue);
		} catch (SQLException e) {
			log.log(Level.ERROR, "Error closing the connection.", e);
		}
	}

	public Connection takeConnection() throws ConnectionPoolException { 
		Connection connection = null;
		try {
			connection = connectionQueue.take(); 
			givenAwayConQueue.add(connection);
		} catch (InterruptedException e) {
			throw new ConnectionPoolException("Error connecting to the data source.", e);
		}
		return connection;
	}
	 

	public void closeConnection(Connection con, Statement st, ResultSet rs) {
		try {
			con.close();
		} catch (SQLException e) {
		log.log(Level.ERROR, "Connection isn't return to the pool."); 
		}
		try {
			rs.close();
	 
		} catch (SQLException e) {
			log.log(Level.ERROR, "ResultSet isn't closed.");
		}

		try {
			st.close();
		} catch (SQLException e) {
			log.log(Level.ERROR, "Statement isn't closed.");
		}
	}

	public void closeConnection(Connection con, Statement st) {
		try {
			con.close();
		} catch (SQLException e) {
			log.log(Level.ERROR, "Connection isn't return to the pool.");
		}
		try {
			st.close();
		} catch (SQLException e) {
			log.log(Level.ERROR, "Statement isn't closed.");
		}
	}

	private void closeConnectionsQueue(BlockingQueue<Connection> queue) throws SQLException {
		Connection connection;
		while ((connection = queue.poll()) != null) {
			if (!connection.getAutoCommit()){ 
				connection.commit();
			}
			((PooledConnection) connection).reallyClose();
		}
	}




}
