package com.qwerty1434.dart.dao;

import java.sql.SQLException;
import java.util.List;

import com.qwerty1434.dart.dto.CorpCodeDto;

public interface CorpCode {
	public List<CorpCodeDto> search(String corp_code) throws SQLException;
	public List<CorpCodeDto> searchAll() throws SQLException;
	
}
