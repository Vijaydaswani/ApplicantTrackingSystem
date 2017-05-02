/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CE;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

/**
 *
 * @author HP
 */
public class DbConnection {
    
    
    public static Connection getConnection()
    {

    Connection con=null;
    try
    {
    Class.forName("com.mysql.jdbc.Driver");
    con=DriverManager.getConnection("jdbc:mysql://25.91.104.236/cyberedge","Vijay","daswani111");
    
    }catch(Exception e)
            {
            System.out.println(e);
            }
   return con; 
    }
 
 }
