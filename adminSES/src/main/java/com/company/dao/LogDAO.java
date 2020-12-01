package com.company.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.company.dto.LogDTO;

@Repository
public class LogDAO {
	@Autowired
	public SqlSessionTemplate temp;

	// 해당 멤버 로그 목록 불러오기
	public List<LogDTO> getMemberLogList(Map<String, Object> map) {
		return temp.selectList("lMap.getMemberLogList", map);
	}

	// 해당 멤버 로그 목록 개수 불러오기
	public int getMemberLListCnt(String mId) {
		return temp.selectOne("lMap.getMemberLListCnt", mId);
	}

	// 날짜별로 검색된 직원의 활동 내역 개수 가져오기
	public int getSearchedMemberLListCnt(Map<String, Object> lmap) {
		return temp.selectOne("lMap.getSearchedMemberLListCnt", lmap);
	}

	// 날짜별로 검색된 직원의 활동 내역 가져오기
	public List<LogDTO> getSearchedMemberLList(Map<String, Object> lmap) {
		return temp.selectList("lMap.getSearchedMemberLList", lmap);
	}
	
	
}
