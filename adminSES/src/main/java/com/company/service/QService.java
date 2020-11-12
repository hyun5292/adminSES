package com.company.service;

import java.util.List;
import java.util.Map;

import com.company.dto.QnaDTO;

public interface QService {
	// 회원의 문의 내역 가져오기
	public List<QnaDTO> getMemberQList(Map<String, Object> qmap);

	// 전체 페이지 개수 구하기
	public int getMemberQListCnt(String mId);
}
