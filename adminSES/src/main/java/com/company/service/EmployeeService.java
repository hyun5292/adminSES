package com.company.service;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.company.dao.EmployeeDAO;
import com.company.dto.EmployeeDTO;

@Service
public class EmployeeService implements EService{
	@Inject
	EmployeeDAO eDAO;

	// 조건 검색 직원 목록 불러오기
	@Override
	public List<EmployeeDTO> GetSchEList(Map<String, Object> map) {
		return eDAO.GetSchEList(map);
	}

	// 조건 검색 직원 페이지 개수 구하기
	@Override
	public int GetSchPageCnt(Map<String, Object> map) {
		return eDAO.GetSchPageCnt(map);
	}

	// 직원 정보 가져오기
	@Override
	public EmployeeDTO GetEInfo(String mId) {
		return eDAO.GetEInfo(mId);
	}

	// 직원 관리자 권한 부여
	@Override
	public boolean MakeDoAuth(String mId) {
		return eDAO.MakeDoAuth(mId);
	}

	// 직원 관리자 권한 해지
	@Override
	public boolean MakeDontAuth(String mId) {
		return eDAO.MakeDontAuth(mId);
	}

	// 이메일 검색된 직원 수 구하기
	@Override
	public int GetsSchEmACnt(Map<String, Object> map) {
		return eDAO.GetsSchEmACnt(map);
	}

	// 이메일 검색된 직원 목록 구하기
	@Override
	public List<EmployeeDTO> GetsSchEmAdmin(Map<String, Object> map) {
		return eDAO.GetsSchEmAdmin(map);
	}

	// 로그인
	@Override
	public EmployeeDTO ELogin(HttpServletRequest request) {
		return eDAO.ELogin(request.getParameter("eId"));
	}
	
}
