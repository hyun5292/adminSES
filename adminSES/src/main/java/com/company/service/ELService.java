package com.company.service;

import java.util.List;
import java.util.Map;

import com.company.dto.EmpLogDTO;

public interface ELService {
	// 회원의 활동 로그 전체 개수 가져오기
	int GetELPageCnt(String mId);

	// 회원의 활동 로그 전체 가져오기
	List<EmpLogDTO> GetELList(Map<String, Object> map);

	// 날짜별로 검색된 회원의 활동 로그 전체 개수 가져오기
	int GetSchELPageCnt(Map<String, Object> map);

	// 날짜별로 검색된 회원의 활동 로그 전체 가져오기
	List<EmpLogDTO> GetSchELList(Map<String, Object> map);

	// 활동 기록 남기기
	boolean WriteLog(Map<String, Object> map);

	
}
