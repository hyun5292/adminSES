package com.company.service;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.company.dao.LogDAO;
import com.company.dto.LogDTO;

@Service
public class LogService implements LService {
	@Inject
	LogDAO lDAO;

	// 해당 멤버 로그 목록 불러오기
	@Override
	public List<LogDTO> getMemberLogList(Map<String, Object> map) {
		return lDAO.getMemberLogList(map);
	}

	// 해당 멤버 로그 목록 개수 불러오기
	@Override
	public int getMemberLListCnt(String mId) {
		return lDAO.getMemberLListCnt(mId);
	}

	// 날짜별로 검색된 직원의 활동 내역 개수 가져오기
	@Override
	public int getSearchedMemberLListCnt(Map<String, Object> lmap) {
		return lDAO.getSearchedMemberLListCnt(lmap);
	}

	// 날짜별로 검색된 직원의 활동 내역 가져오기
	@Override
	public List<LogDTO> getSearchedMemberLList(Map<String, Object> lmap) {
		return lDAO.getSearchedMemberLList(lmap);
	}

}
