package bank.controller;

import bank.model.Employee;
import com.mysql.jdbc.PreparedStatement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class MysqlManager {
    public static Connection getConn() {
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/bank?useUnicode=true&characterEncoding=utf-8&useSSL=false";
        String username = "root";
        String password = "1292969896";
        Connection con = null;

        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }
    public String Subquery(String tablename,String limit){
        int i = 0;//子查询插入
        String message= new String();
        Connection connection = getConn();
        String sql = "insert into templ select EMPNO,FIRSTNME,MIDINIT,LASTNAME FROM "+tablename+" where " +limit+"";
        Statement statement;

        try {
           statement  =connection.createStatement();
           i =  statement.executeUpdate(sql);
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            message = e.getMessage();
            return message;
        }
        return message;
    }

    public String insert(Employee employee,String tablename) {
        int i = 0;//单行
        String message= new String();
        Connection connection = getConn();
        String sql = "insert into "+tablename+" (EMPNO,FIRSTNME,MIDINIT,LASTNAME) values('" + employee.EEMPNO+"','"+employee.EFIRSTNME+"','"+employee.EDININIT+"','"+employee.ELASTNAME+"')";
        Statement statement;
        try {
            statement =  connection.createStatement();
            i =statement.executeUpdate(sql);
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            message = e.getMessage();
            return message;
        }
        return message;
    }

    public static String delete(String usernum) {
        int i = 0;
        String message= new String();
        Connection connection = getConn();
        String sql = "delete from templ where EMPNO='" + usernum + "'";
        PreparedStatement preparedStatement;

        try {
            preparedStatement = (PreparedStatement) connection.prepareStatement(sql);
            i = preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            message = e.getMessage();
            return message;
        }
        return message;
    }

    public static Employee search(String tablename,String limit) {
        Connection connection = getConn();
        String sql = "select * from "+tablename+" where " +limit+"";
        Statement statement;

        try {
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            if(rs==null){
                return null;
            }
            int col = rs.getMetaData().getColumnCount();
            Employee one = new Employee();
            while (rs.next()) {
                for (int i = 1; i <= col; i++) {
                    if (i == 1) {
                        one.setEMPNO(rs.getString(i));
                  //      System.out.print(one.getEMPNO());
                    }
                    if (i == 2) {
                        one.setFIRSTNME(rs.getString(i));
                    //    System.out.print(one.getFIRSTNME());
                    }
                    if (i == 3) {
                        one.setDININIT(rs.getString(i));
                      //  System.out.print(one.getEMPNO());
                    }
                    if (i == 4) {
                        one.setLASTNAME(rs.getString(i));
                        //System.out.print(one.getLASTNAME());
                    }
                }
                return one;
            }
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static ObservableList<Employee> getall() {
        Connection connection = getConn();
        String sql = "Select * from templ ";
        try {
            Statement state = connection.createStatement();
            ResultSet rs = state.executeQuery(sql);
            ResultSetMetaData rsmd = rs.getMetaData();
            int col = rs.getMetaData().getColumnCount();
            ObservableList<Employee> employees = FXCollections.observableArrayList();
            while (rs.next()) {
                Employee one = new Employee();
                for (int i = 1; i <= col; i++) {
                    if (i == 1) {
                        one.setEMPNO(rs.getString(i));
                        System.out.print(one.getEMPNO()+"\n");
                    }
                    if (i == 2) {
                        one.setFIRSTNME(rs.getString(i));
                        System.out.print(one.getFIRSTNME()+"\n");
                    }
                    if (i == 3) {
                        one.setDININIT(rs.getString(i));
                    }
                    if (i == 4) {
                        one.setLASTNAME(rs.getString(i));
                    }
                }
                employees.add(one);
            }
            rs.close();
            state.close();
            connection.close();
            return employees;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
