package com.company.service;

import java.util.List;
import java.util.Map;

import com.company.dto.SnsDTO;

public interface SService {

	// 조건 검색 SNS사 담당자 목록 불러오기
	List<SnsDTO> GetSchSList(Map<String, Object> map);

	// 조건 검색 SNS사 담당자 페이지 개수 구하기
	int GetSchPageCnt(Map<String, Object> map);

}
