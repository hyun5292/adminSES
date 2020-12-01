package com.company.service;

import java.util.List;
import java.util.Map;

import com.company.dto.LogDTO;

public interface LService {

	// 해당 멤버 로그 목록 불러오기
	public List<LogDTO> getMemberLogList(Map<String, Object> map);

	// 해당 멤버 로그 목록 개수 불러오기
	public int getMemberLListCnt(String mId);

	// 날짜별로 검색된 직원의 활동 내역 개수 가져오기
	public int getSearchedMemberLListCnt(Map<String, Object> lmap);

	// 날짜별로 검색된 직원의 활동 내역 가져오기
	public List<LogDTO> getSearchedMemberLList(Map<String, Object> lmap);

}
