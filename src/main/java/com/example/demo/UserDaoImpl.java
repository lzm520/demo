package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import net.bytebuddy.asm.Advice.Return;

@Repository
public class UserDaoImpl implements UserDao{
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public boolean lookForUser(String userId) {
		String sqlString="SELECT userId,birthday,gender,email FROM users WHERE userId=? ";
		try 
		{
			if(jdbcTemplate.queryForMap(sqlString, userId)!=null);
			{
				return true;
			}
		} 
		catch (Exception e) 
		{
		}
		return false;
	}

	@Override
	public List<User> lookForAllUser() {
		String sqlString="SELECT userId,birthday,gender,email FROM users ";
		return jdbcTemplate.query(sqlString, new BeanPropertyRowMapper(User.class));
	}

	@Override
	public void saveUser(User user) {	
		//String sqlString="insert into users(userId,birthday,gender,email) VALUES (?,?,?,?)";
		jdbcTemplate.update("insert into users(userId,birthday,gender,email) VALUES (?,?,?,?)",user.getUserId(),user.getBirthday(),user.getGender(),user.getEmail());
	}
	
	
}
