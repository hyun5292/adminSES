package com.company.service;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.company.dao.SnsDAO;
import com.company.dto.SnsDTO;

@Service
public class SnsService implements SService{
	@Inject
	SnsDAO sDAO;

	// 조건 검색 SNS사 담당자 목록 불러오기
	@Override
	public List<SnsDTO> GetSchSList(Map<String, Object> map) {
		return sDAO.GetSchSList(map);
	}

	// 조건 검색 SNS사 담당자 페이지 개수 구하기
	@Override
	public int GetSchPageCnt(Map<String, Object> map) {
		return sDAO.GetSchPageCnt(map);
	}

	// 조건 검색 SNS사 담당자 정보 불러오기
	@Override
	public SnsDTO GetSInfo(String mId) {
		return sDAO.GetSInfo(mId);
	}

}
