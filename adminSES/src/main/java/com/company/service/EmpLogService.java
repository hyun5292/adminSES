package com.company.service;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.company.dao.EmpLogDAO;
import com.company.dto.EmpLogDTO;

@Service
public class EmpLogService implements ELService{
	@Inject
	EmpLogDAO elDAO;

	// 회원의 활동 로그 전체 개수 가져오기
	@Override
	public int GetELPageCnt(String mId) {
		return elDAO.GetELPageCnt(mId);
	}

	// 회원의 활동 로그 전체 가져오기
	@Override
	public List<EmpLogDTO> GetELList(Map<String, Object> map) {
		return elDAO.GetELList(map);
	}

	// 날짜별로 검색된 회원의 활동 로그 전체 개수 가져오기
	@Override
	public int GetSchELPageCnt(Map<String, Object> map) {
		return elDAO.GetSchELPageCnt(map);
	}

	// 날짜별로 검색된 회원의 활동 로그 전체 가져오기
	@Override
	public List<EmpLogDTO> GetSchELList(Map<String, Object> map) {
		return elDAO.GetSchELList(map);
	}

	// 활동 기록 남기기
	@Override
	public boolean WriteLog(Map<String, Object> map) {
		return elDAO.WriteLog(map);
	}

}
