package com.company.service;

import java.util.List;
import java.util.Map;

import com.company.dto.PayLogDTO;
import com.company.dto.QnaDTO;

public interface PLService {
	// 회원의 문의 내역 가져오기
	public List<PayLogDTO> getMemberPLList(Map<String, Object> plmap);

	// 전체 페이지 개수 구하기
	public int getMemberPLListCnt(String mId);

	// 검색된 회원의 결제 내역 개수 가져오기
	public int getSearchedMemberPLListCnt(Map<String, Object> pmap);

	// 검색된 회원의 결제 내역 가져오기
	public List<PayLogDTO> getSearchedMemberPLList(Map<String, Object> pmap);

	// 해당 회원의 이번달 내역 가져오기
	public int getChkPay(Map<String, Object> map);

	// 검색된 회원의 Pay 횟수 구하기
	public int getMPayList(String mId);
}
