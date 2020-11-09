package com.company.dao;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.company.dto.MemberDTO;

@Repository
public class MemberDAO {
	@Autowired
	public SqlSessionTemplate temp;

	// list 가져와
	public List<MemberDTO> GetMList(Map<String, Object> map) {
		return temp.selectList("mMap.MList", map);
	}

	// 전체 페이지 개수 구하기
	public int ListCnt() {
		return temp.selectOne("mMap.ListCnt");
	}

	// 검색된 회원 목록 불러오기
	public List<MemberDTO> GetSearchedMList(Map<String, Object> map) {
		return temp.selectList("mMap.MSearchedList", map);
	}

	// 검색된 페이지 개수 구하기
	public int SearchedListCnt(Map<String, Object> map) {
		return temp.selectOne("mMap.SearchedListCnt", map);
	}
}