package com.company.dao;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.company.dto.EmpLogDTO;

@Repository
public class EmpLogDAO {
	@Autowired
	public SqlSessionTemplate temp;

	// 회원의 활동 로그 전체 개수 가져오기
	public int GetELPageCnt(String mId) {
		return temp.selectOne("elMap.GetELPageCnt", mId);
	}

	// 회원의 활동 로그 전체 가져오기
	public List<EmpLogDTO> GetELList(Map<String, Object> map) {
		return temp.selectList("elMap.GetELList", map);
	}

	// 날짜별로 검색된 회원의 활동 로그 전체 개수 가져오기
	public int GetSchELPageCnt(Map<String, Object> map) {
		return temp.selectOne("elMap.GetSchELPageCnt", map);
	}

	// 날짜별로 검색된 회원의 활동 로그 전체 가져오기
	public List<EmpLogDTO> GetSchELList(Map<String, Object> map) {
		return temp.selectList("elMap.GetSchELList", map);
	}
	
}
