/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CE;

import java.sql.Connection;

/**
 *
 * @author HP
 */
public class TestConnection {
    public static void main(String gg[])
    {
       
    Connection connection;
    try
 {
connection=DbConnection.getConnection();
connection.close();
System.out.println("OK");
}catch(Exception exception)
{
System.out.println(exception);
}
}   
    }

