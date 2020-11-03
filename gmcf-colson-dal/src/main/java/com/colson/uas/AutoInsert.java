package com.colson.uas;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class AutoInsert {
    public static void main(String[] args) throws Exception {

        //--------------------------------连接数据库----------------------
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://10.163.0.96:3306/?useUnicode=true&characterEncoding=utf8";
        String user = "root";
        String password = "123456";

        String username = "ceshi";
        String realname = "测试";

        //1、新建驱动
        Driver driverInstance = (Driver) Class.forName(driver).newInstance();
        //2、注册驱动
        DriverManager.registerDriver(driverInstance);
        //3、获取连接
        Connection conn = DriverManager.getConnection(url, user, password);

        //----------------------对数据库进行操作-------------------
        //记录开始时间
        Long begin = System.currentTimeMillis();
        //-----------插入数据----------
        //sql语句前缀

        //设置事务为非自动提交
        conn.setAutoCommit(false);
        //使用PrepareStatement更好
        PreparedStatement pstate = conn.prepareStatement("");

        String passwordSalt = PasswordUtils.genSalt();
        password = PasswordUtils.encryptPassword("123456", passwordSalt);

        String str = "','";
        String preStr = "',";
        String afterStr = ",'";
        String comma = ",";
        String nullStr = "null";

//        定义用户信息

        String email = "q111@qq.com";
        String phoneNo = "16211122112";
        String level = "0";
        String source = "0";
        String systemId = "-1";
        String lastLoginIp = "0";
        String lastLoginDatetime = "0000-00-00 00:00:00";
        String loginToken = "";
        String tokenExpireTime = "0000-00-00 00:00:00";
        String status = "1";
        String roleSummary = "测试";


//         初始化ceshi用户
        StringBuilder insertUserSql = new StringBuilder("INSERT INTO db_gmf_uas.t_uas_auth_user(f_id, f_version, f_username, f_realname, f_email, f_phone_no, f_password, f_password_salt, f_level, f_source, f_system_id, f_last_login_ip, f_last_login_datetime, f_login_token, f_token_expire_time, f_status, f_role_summary) VALUES (");
        insertUserSql.append(nullStr).append(comma)
                .append(0).append(afterStr)
                .append(username).append(str)
                .append(realname).append(str)
                .append(email).append(str)
                .append(phoneNo).append(str)
                .append(password).append(str)
                .append(passwordSalt).append(preStr)
                .append(level).append(comma)
                .append(source).append(comma)
                .append(systemId).append(comma)
                .append(lastLoginIp).append(afterStr)
                .append(lastLoginDatetime).append(str)
                .append(loginToken).append(str)
                .append(tokenExpireTime).append(preStr)
                .append(status).append(afterStr)
                .append(roleSummary).append("');");

        pstate.executeUpdate(insertUserSql.toString());


//        初始化一级二级角色
        String insertAuthRole = "INSERT INTO db_gmf_uas.t_uas_auth_role(f_id, f_version, f_parent_id, f_parent_name, f_role_name, f_description, f_status, f_system_id) VALUES (null, 0, -1, '', '一级角色', '一级角色', 1, 1);";
        pstate.executeUpdate(insertAuthRole);
        String queryAuthRole = "select f_id from db_gmf_uas.t_uas_auth_role where f_role_name = '一级角色' and f_system_id = 1 and f_parent_id = -1;";
        ResultSet resultSet = pstate.executeQuery(queryAuthRole);
        Integer parentRoleId = -1;
        if (resultSet.next()) {
            parentRoleId = resultSet.getInt("f_id");
        }

        String insertAuthRole2 = "INSERT INTO db_gmf_uas.t_uas_auth_role(f_id, f_version, f_parent_id, f_parent_name, f_role_name, f_description, f_status, f_system_id) VALUES (null, 0, "+parentRoleId+", '一级角色', '测试', '测试', 1, 1);";
        pstate.executeUpdate(insertAuthRole2);

        String queryUser = "select f_id from db_gmf_uas.t_uas_auth_user where f_username = '" + username + "';";
        resultSet = pstate.executeQuery(queryUser);
        Integer userId = -1;
        if (resultSet.next()) {
            userId = resultSet.getInt("f_id");
        }

        String queryRole = "select f_id from db_gmf_uas.t_uas_auth_role where f_parent_id = "+parentRoleId+" and f_role_name = '测试' and f_system_id = 1;";
        resultSet = pstate.executeQuery(queryRole);
        Integer roleId = -1;
        if (resultSet.next()) {
            roleId = resultSet.getInt("f_id");
        }

//        初始化用户角色关联关系
        String insertUserRole = "INSERT INTO db_gmf_uas.t_uas_auth_user_role(f_id, f_user_id, f_role_id, f_status) VALUES (null, " + userId + ", " + roleId + ", 1);";
        pstate.executeUpdate(insertUserRole);

        String queryPermission = "select f_id from db_gmf_uas.t_uas_auth_permission where f_system_id = 1;";

        resultSet = pstate.executeQuery(queryPermission);

        while (resultSet.next()) {
            String insertRolePermission = "INSERT INTO db_gmf_uas.t_uas_auth_role_permission(f_id, f_role_id, f_permission_id, f_status) VALUES (null," + roleId + "," + resultSet.getInt("f_id") + ",1);";
            pstate.addBatch(insertRolePermission);
        }

        //执行sql
        pstate.executeBatch();
        //提交事务
        conn.commit();

        //大循环完毕，关闭连接
        pstate.close();
        conn.close();
    }
}
