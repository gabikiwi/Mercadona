package com.mercadona.pos.dao;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Calendar;


public class MySQLAccess {
  private Connection connect = null;
  private Statement statement = null;
  private PreparedStatement preparedStatement = null;
  private ResultSet resultSet = null;

  public void readDataBase() throws Exception {
    try {
      // This will load the MySQL driver, each DB has its own driver
      Class.forName("com.mysql.jdbc.Driver");
      // Setup the connection with the DB
      connect = DriverManager
          .getConnection("jdbc:mysql://localhost/mercadona_saf?"
              + "user=mercadona&password=welcome1");

      // Statements allow to issue SQL queries to the database
      statement = connect.createStatement();
      // Result set get the result of the SQL query
      resultSet = statement
          .executeQuery("select * from kiwi_messaging_module_store LIMIT 0");
      writeResultSet(resultSet);

      // PreparedStatements can use variables and are more efficient
      preparedStatement = connect
          .prepareStatement("insert into  kiwi_messaging_module_store values (?, ?, ?, ?, ?, ? , ?, ?)");
      // "ID, MESSAGE_ID, MESSAGE_TYPE, TIME_STAMP, MESSAGE_DATA, PRIORITY, RETRY_COUNT, TTL from kiwi_messaging_module_store");
      // Parameters start with 1
      
      //System.out.println(resultSet.getInt("MESSAGE_DATA"));
      //create a sample of the Blob Message
      byte[] blobData = new byte[32];

		for (int i = 0; i < blobData.length; i++) {
			blobData[i] = 1;
		}
		
      Blob b1 = connect.createBlob();
      b1.setBytes(1, blobData);
      
      
      //ID
      preparedStatement.setInt(1, 10003);
      
    //Message_Id
      preparedStatement.setString(2, "1000002");
      
    //Message_Type
      preparedStatement.setString(3, "xml");
      
      //Time_Stamp
      java.util.Date utilDate = new java.util.Date();
      Calendar cal = Calendar.getInstance();
      cal.setTime(utilDate);    
      
      preparedStatement.setTimestamp(4, new java.sql.Timestamp(cal.getTimeInMillis()));
      
      //BLOB
      preparedStatement.setBlob(5, b1);
      
      
      
      
      
      
      
      //Priority
      preparedStatement.setInt(6, 2);
      
      //Retry_Count
      preparedStatement.setInt(7, 2);
     
      
   
      
      
      //Message_Type
      preparedStatement.setInt(8, 10);
  
      System.out.println("TEST");
      preparedStatement.executeUpdate();
      
      preparedStatement = connect
          .prepareStatement("SELECT * from kiwi_messaging_module_store");
      resultSet = preparedStatement.executeQuery();
      
      writeResultSet(resultSet);

      
      // Remove again the insert comment
    //  preparedStatement = connect
    //  .prepareStatement("delete from kiwi_messaging_module_store  where id= ? ; ");
    //  preparedStatement.setInt(1, 10001);
    //  preparedStatement.executeUpdate();
      
      resultSet = statement
      .executeQuery("select * from kiwi_messaging_module_store");
      writeMetaData(resultSet);
      
    } catch (Exception e) {
      throw e;
    } finally {
      close();
    }

  }

  private void writeMetaData(ResultSet resultSet) throws SQLException {
    //   Now get some metadata from the database
    // Result set get the result of the SQL query
    
    System.out.println("The columns in the table are: ");
    
    System.out.println("Table: " + resultSet.getMetaData().getTableName(1));
    for  (int i = 1; i<= resultSet.getMetaData().getColumnCount(); i++){
      System.out.println("Column " +i  + " "+ resultSet.getMetaData().getColumnName(i));
    }
  }

  private void writeResultSet(ResultSet resultSet) throws SQLException {
    // ResultSet is initially before the first data set
    while (resultSet.next()) {
      // It is possible to get the columns via name
      // also possible to get the columns via the column number
      // which starts at 1
      // e.g. resultSet.getSTring(2);
    	
      Integer id = resultSet.getInt("id");
      Blob message_data = resultSet.getBlob("message_data");
      String message_id = resultSet.getString("message_id");
      String message_type = resultSet.getString("message_type");
      Integer priority = resultSet.getInt("priority");
      Integer retry_count = resultSet.getInt("retry_count");
      Timestamp time_stamp = resultSet.getTimestamp("time_stamp");
      Integer ttl = resultSet.getInt("ttl");
      
          
      
      System.out.println("id: " + id);
      System.out.println("message_data: " + message_data);
      System.out.println("message_id: " + message_id);
      System.out.println("message_type: " + message_type);
      System.out.println("retry_count: " + retry_count);
      System.out.println("priority: " + priority);
      System.out.println("retry_count: " + retry_count);
      System.out.println("time_stamp: " + time_stamp);
      System.out.println("ttl: " + ttl);
      
    }
  }

  // You need to close the resultSet
  private void close() {
    try {
      if (resultSet != null) {
        resultSet.close();
      }

      if (statement != null) {
        statement.close();
      }

      if (connect != null) {
        connect.close();
      }
    } catch (Exception e) {

    }
  }

} 