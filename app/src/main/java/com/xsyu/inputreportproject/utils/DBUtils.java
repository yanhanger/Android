package com.xsyu.inputreportproject.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import oracle.jdbc.OracleDriver;

/**
 * 数据库连接工具类
 */
public class DBUtils {
    /**
     * 测试jdbc连接本地mysql数据库
     */
    //定义连接数据库所用的变量
    public static final String driverName = "oracle.jdbc.driver.OracleDriver";
    public static final String url = "jdbc:oracle:thin:@47.97.251.227:1521:ORCL";
    public static final String name = "xxcs";
    public static final String password = "xxcs";
    public static Connection con = null;
    public static PreparedStatement ps = null;
    public PreparedStatement GetConDatabase(String sql){
        try{
            Class.forName(driverName);
            con = DriverManager.getConnection(url,name,password);//获取连接
            System.out.println("这块的con"+con);
            System.out.println("数据库连接成功！");
            ps = con.prepareStatement(sql);
        }catch(Exception ex){
            ex.printStackTrace();
            System.out.println(ex.getMessage());
        }
        System.out.println("输出con"+ps);
        return ps;
    }
    //数据库关闭连接
    public void close(){
        try{
           this.con.close();
           this.ps.close();
        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }

}
