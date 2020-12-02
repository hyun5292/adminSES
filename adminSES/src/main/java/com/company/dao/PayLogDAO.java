package com.company.dao;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.company.dto.PayLogDTO;
import com.company.dto.QnaDTO;

@Repository
public class PayLogDAO {
	@Autowired
	public SqlSessionTemplate temp;

	// 회원의 문의 내역 가져오기
	public List<PayLogDTO> getMemberPLList(Map<String, Object> plmap) {
		return temp.selectList("plMap.getMemberPLList", plmap);
	}

	// 전체 페이지 개수 구하기
	public int getMemberPLListCnt(String mId) {
		return temp.selectOne("plMap.getMemberPLListCnt", mId);
	}

	// 검색된 회원의 문의 내역 가져오기
	public int getSearchedMemberPLListCnt(Map<String, Object> pmap) {
		return temp.selectOne("plMap.getSearchedMemberPLListCnt", pmap);
	}

	// 검색된 회원의 결제 내역 가져오기
	public List<PayLogDTO> getSearchedMemberPLList(Map<String, Object> pmap) {
		return temp.selectList("plMap.getSearchedMemberPLList", pmap);
	}

	// 해당 회원의 이번달 내역 가져오기
	public int getChkPay(Map<String, Object> map) {
		return temp.selectOne("plMap.getChkPay", map);
	}

	// 검색된 회원의 Pay 횟수 구하기
	public int getMPayList(String mId) {
		return temp.selectOne("plMap.getMPayList", mId);
	}
}
