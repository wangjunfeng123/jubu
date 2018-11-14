package com.ninep.jubu.test.jdbc;

import java.sql.*;

/**
 * @author wangjunfeng
 * @version 1.0
 * @desc jdbc 源码分析 service.
 * @since 2018/11/7
 */
public class JdbcTest {

    public static void PreparedStatement_Select(int id) {
        try {
            Connection conn = null;
            PreparedStatement pstmt = null;
            ResultSet rs = null;
            String sql = "SELECT * FROM user WHERE id = ?";

            // jdbc 6部曲
            // 1、注册驱动
            // 2、获取数据库连接
            // 3、创建prepareStatement对象
            // 4、执行sql
            // 5、遍历获取查询结果
            // 6、关闭数据库连接

          //1、加载数据库厂商的驱动程序
            // Driver implements java.sql.Driver 实现Driver 接口，
            // DriverManager 来管理Driver的注册，有个registerDriver方法注册driver. 把driver 封装到driverInfo 中
            // 执行过程：下面这句话，加载com.mysql,jdbc.Driver 驱动，DriverManager.registerDriver 注册驱动，转driverInfo，如果驱动没有在驱动列表中，
            // 把驱动装入驱动列表中，完成注册。可以通过时序图来更好的展示。
            Class.forName("com.mysql.jdbc.Driver");

            //2、提供数据库连接url
            // 和mysql建立TCP长连接，
            // 执行过程：把user，password 封装到info中， 获取当前线程的ClassLoader，
            // 遍历驱动，找到可供使用的驱动，连接数据库server  conn = aDriver.driver.connect(url, info);  尝试连接数据库
            // java.mysql.jdbc.Connection extend java.sql.Connection

            // 如何放回数据库连接的conn，MySQLConnection implements java.mysql.jdbc.Connection
            // ConnectionImpl  实现 MySQLConnection 接口
            // 构造方法： ConnectionImpl(String hostToConnectTo, int portToConnectTo, Properties info, String databaseToConnectTo, String url)

            // 构造方法很长  ---> 有个方法 createNewIO   主要做两件事情
            // 一、建立和MysqlServer的Socket连接，
            // 二、连接成功后，进行登录校验, 发送用户名、密码、当前数据库连接默认选择的数据库名。

            // 1、createNewIO --> coreConnection  - > new MysqlIO()  ->
            // 2、构造方法中：获取socket 连接：this.mysqlConnection = this.socketFactory.beforeHandshake();
            // 3、封装输入输出流

            // coreConnection -> this.io.doHandshake(this.user, this.password, this.database);
            // -> this.secureAuth411((Buffer)null, packLength, user, password, database, true, false); 登录校验
            // -> secureAuth411  -> this.send(packet, packet.getPosition()); 向Mysql服务器发送登录信息包(用户名、密码、此Socket连接默认选择的数据库)
            // Buffer reply = this.checkErrorPacket(); 读取Mysql服务器登录检验后发送的状态信息，如果成功就返回，如果登录失败则抛出异常
            // send(Buffer packet, int packetLen) ->
            //  -> this.mysqlOutput.write(packetToSend.getByteBuffer(), 0, packetLen);  //把登录信息的字节流发送给MySQL Server
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jubu?useUnicode=true&characterEncoding=UTF-8",
                    "root", "123456");


            // 3、执行sql预编译

            // 3.1、String nativeSql = this.getProcessEscapeCodesForPrepStmts()?this.nativeSQL(sql):sql; 需要编译的
            // pStmt = (com.mysql.jdbc.PreparedStatement)this.clientPrepareStatement(nativeSql, resultSetType, resultSetConcurrency, false);
            // -> pStmt = com.mysql.jdbc.PreparedStatement.getInstance(this.getMultiHostSafeProxy(), nativeSql, this.database);  创建preparedStatement实例 并返回
            pstmt = conn.prepareStatement(sql);

            // 3.2 、设置自定义参数，最终把参数保存到preparedStatement实例中
            // 对应的属性类型：parameterTypes，参数值：parameterValues
            // setInt(int parameterIndex, int x)
            // 3.2.1 配置参数类型：this.parameterTypes[parameterIndex - 1 + this.getParameterIndexOffset()] = 4;
            // 3.2.2 this.setInternal(parameterIndex, String.valueOf(x));  -> setInternal(int paramIndex, byte[] val)
            //   ->this.parameterValues[paramIndex - 1 + parameterIndexOffset] = val;  配置参数值

            pstmt.setInt(1, id);

            //4、执行sql
            rs = pstmt.executeQuery();

            //5、循环遍历结果
            while (rs.next()) {
                int i = 1;
                System.out.println(rs.getInt(i++));
                System.out.println(rs.getString(i++));
                System.out.println(rs.getString(i++));
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //入口方法
    public static void main(String[] args) {
        JdbcTest.PreparedStatement_Select(1);
    }

}