package com.air.security.sodb.support.authentication.handler;

import java.security.GeneralSecurityException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import javax.security.auth.login.AccountLockedException;
import javax.security.auth.login.FailedLoginException;

import org.apereo.cas.authentication.Credential;
import org.apereo.cas.authentication.HandlerResult;
import org.apereo.cas.authentication.PreventedException;
import org.apereo.cas.authentication.UsernamePasswordCredential;
import org.apereo.cas.authentication.exceptions.AccountDisabledException;
import org.apereo.cas.authentication.exceptions.InvalidLoginLocationException;
import org.apereo.cas.authentication.handler.support.AbstractPreAndPostProcessingAuthenticationHandler;
import org.apereo.cas.authentication.principal.PrincipalFactory;
import org.apereo.cas.services.ServicesManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

/**
 * @author Carl
 * @version 创建时间：2018/1/30
 * @since 1.0.0
 */
public class CustomerAuthenticationHandler extends AbstractPreAndPostProcessingAuthenticationHandler {
//	@Autowired 
//	private JdbcTemplate jdbcTemplate;
	
	public CustomerAuthenticationHandler(String name, ServicesManager servicesManager, PrincipalFactory principalFactory, Integer order) {
        super(name, servicesManager, principalFactory, order);
    }

    @Override
    protected HandlerResult doAuthentication(Credential credential) throws GeneralSecurityException, PreventedException {
        UsernamePasswordCredential usernamePasswordCredentia = (UsernamePasswordCredential) credential;
        String username = usernamePasswordCredentia.getUsername();
        String password = usernamePasswordCredentia.getPassword();
    	Connection conn = null;
        try {
			Class.forName("com.mysql.jdbc.Driver");
			
			String url = "jdbc:mysql://127.0.0.1:3306/yellowcong";
			String user = "root";
			String pass = "root";
			
			conn = DriverManager.getConnection(url,user, pass);
			
			String sql = "SELECT * FROM cas_user WHERE username =? AND PASSWORD = ?";
			
			PreparedStatement  ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, password);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
        	//允许登录，并且通过this.principalFactory.createPrincipal来返回用户属性
	            return createHandlerResult(credential, this.principalFactory.createPrincipal(username, Collections.emptyMap()), null);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
        //查询数据库
//        String [] args = {username,password};
//        List<User> user = jdbcTemplate.query(sql, args, new RowMapper<User>(){
//
//            @Override
//            public User mapRow(ResultSet rs, int rowNum) throws SQLException {
//				User user = new User();
//				user.setUsername(rs.getString("username"));
//				user.setPassword(rs.getString("password"));
//            	return user;
//            	/*Student stu = new Student();
//                stu.setId(rs.getInt("ID"));
//                stu.setAge(rs.getInt("AGE"));
//                stu.setName(rs.getString("NAME"));
//                stu.setSumScore(rs.getString("SCORE_SUM"));
//                stu.setAvgScore(rs.getString("SCORE_AVG"));
//                return stu;*/
//            }
//
//        });
//        if(user.size() >0) {
//        	//允许登录，并且通过this.principalFactory.createPrincipal来返回用户属性
//            return createHandlerResult(credential, this.principalFactory.createPrincipal(username, Collections.emptyMap()), null);
//     
//        }
        
        if (username.startsWith("lock")) {
            //用户锁定
            throw new AccountLockedException();
        } else if (username.startsWith("disable")) {
            //用户禁用
            throw new AccountDisabledException();
        } else if (username.startsWith("invali")) {
            //禁止登录该工作站登录
            throw new InvalidLoginLocationException();
        } else if (username.startsWith("passorwd")) {
            //密码错误
            throw new FailedLoginException();
        } else if (username.startsWith("account")) {
            //账号错误
            throw new AccountLockedException();
        } else if(username.startsWith("yellowlu")) {
        	throw new DoubiException();
        }

        //允许登录，并且通过this.principalFactory.createPrincipal来返回用户属性
        return createHandlerResult(credential, this.principalFactory.createPrincipal(username, Collections.emptyMap()), null);
    }

    @Override
    public boolean supports(Credential credential) {
        return credential instanceof UsernamePasswordCredential;
    }
    
    static class User{
    	private String username;
    	private String password;
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
    }
}
