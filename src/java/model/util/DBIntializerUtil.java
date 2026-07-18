/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.util;

public interface DBIntializerUtil
{
    String DRIVER="oracle.jdbc.driver.OracleDriver";
    String CON_STRING="jdbc:oracle:thin:@db-host:1521/service-name";
    String USERNAME="app_user";
    String PASSWORD="change-me";
    //jdbc:oracle:thin:@localhost:1521:orcl [shadow on SHADOW]
}
