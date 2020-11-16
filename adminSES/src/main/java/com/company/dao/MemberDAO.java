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

	// 회원 목록 불러오기
	public List<MemberDTO> GetMList(Map<String, Object> map) {
		return temp.selectList("mMap.GetMList", map);
	}

	// 전체 페이지 개수 구하기
	public int ListCnt() {
		return temp.selectOne("mMap.ListCnt");
	}

	// 조건 검색 회원 목록 불러오기
	public List<MemberDTO> GetSchMList(Map<String, Object> map) {
		return temp.selectList("mMap.GetSchMList", map);
	}

	// 조건 검색 회원 페이지 개수 구하기
	public int GetSchPageCnt(Map<String, Object> map) {
		return temp.selectOne("mMap.GetSchPageCnt", map);
	}

	// 검색된 페이지 개수 구하기
	public int SearchedListCnt(Map<String, Object> map) {
		return temp.selectOne("mMap.SearchedListCnt", map);
	}

	// 회원의 회원 분류 가져오기
	public String GetMKind(String mId) {
		return temp.selectOne("mMap.GetMKind", mId);
	}

	// 회원 정보 가져오기
	public MemberDTO GetMInfo(String mId) {
		return temp.selectOne("mMap.GetMInfo", mId);
	}

	// 일반 회원 수 구하기
	public int GeneralCnt() {
		return temp.selectOne("mMap.GeneralCnt");
	}

	// 탈퇴한 일반 회원 수 구하기
	public int GeneralNotUseCnt() {
		return temp.selectOne("mMap.GeneralNotUseCnt");
	}
}