package com.company.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.company.dto.EmployeeDTO;
import com.company.dto.SnsDTO;

public interface EService {
	// 조건 검색 직원 목록 불러오기
	List<EmployeeDTO> GetSchEList(Map<String, Object> map);

	// 조건 검색 직원 페이지 개수 구하기
	int GetSchPageCnt(Map<String, Object> map);

	// 직원 정보 가져오기
	EmployeeDTO GetEInfo(String mId);

	// 직원 관리자 권한 부여
	boolean MakeDoAuth(String mId);

	// 직원 관리자 권한 해지
	boolean MakeDontAuth(String mId);

	// 이메일 검색된 직원 수 구하기
	int GetsSchEmACnt(Map<String, Object> map);

	// 이메일 검색된 직원 목록 구하기
	List<EmployeeDTO> GetsSchEmAdmin(Map<String, Object> map);

	// 로그인
	EmployeeDTO ELogin(HttpServletRequest request);

	// 비밀번호 변경
	boolean ChagePWD(Map<String, Object> map);
	
}
