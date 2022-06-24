package com.qwerty1434.dart.dao;

import java.sql.SQLException;
import java.util.List;

import com.qwerty1434.dart.dto.UserDto;

public interface User {
	public int insert(UserDto data) throws SQLException;
	public int update(UserDto data) throws SQLException;
	public int delete(String id) throws SQLException;
	public UserDto select(String id, String pw) throws SQLException;
	public List<UserDto> selectAll() throws SQLException;
}
