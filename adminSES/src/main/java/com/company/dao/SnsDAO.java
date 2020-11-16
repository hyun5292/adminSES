package com.company.dao;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.company.dto.SnsDTO;

@Repository
public class SnsDAO {
	@Autowired
	public SqlSessionTemplate temp;

	// 조건 검색 SNS사 담당자 목록 불러오기
	public List<SnsDTO> GetSchSList(Map<String, Object> map) {
		return temp.selectList("snsMap.GetSchSList", map);
	}

	// 조건 검색 SNS사 담당자 페이지 개수 구하기
	public int GetSchPageCnt(Map<String, Object> map) {
		return temp.selectOne("snsMap.GetSchPageCnt", map);
	}
	
}
