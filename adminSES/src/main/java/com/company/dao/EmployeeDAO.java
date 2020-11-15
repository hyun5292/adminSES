package com.company.dao;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.company.dto.EmployeeDTO;

@Repository
public class EmployeeDAO {
	@Inject
	private SqlSession sqlSession;

	// ·Î±×ÀÎ
	public EmployeeDTO ELogin(String eId) {
		return sqlSession.selectOne("eMap.ELogin", eId);
	}
}
