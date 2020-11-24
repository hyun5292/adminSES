package com.company.service;

import java.util.List;
import java.util.Map;

import com.company.dto.SnsDTO;

public interface SService {

	// 조건 검색 SNS사 담당자 목록 불러오기
	List<SnsDTO> GetSchSList(Map<String, Object> map);

	// 조건 검색 SNS사 담당자 페이지 개수 구하기
	int GetSchPageCnt(Map<String, Object> map);

	// 조건 검색 SNS사 담당자 정보 불러오기
	SnsDTO GetSInfo(Map<String, Object> map);

	// 새로운 SNS 담당자 등록
	boolean NewSnsMember(Map<String, Object> map);

	//  SNS 담당자 정보 수정
	boolean ModifySnsMember(Map<String, Object> map);

	// SNS사 담당자 삭제
	boolean DeleteSNS(Map<String, Object> map);

	// 기존 담당자 마감일 설정
	boolean ChgNowSns(Map<String, Object> map);

	// 오늘 등록된 다른 SNS 담당자가 있는지 확인
	SnsDTO ChkTodaySNS(Map<String, Object> map);

}
