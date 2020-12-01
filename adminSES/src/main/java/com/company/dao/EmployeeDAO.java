package com.company.dao;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.company.dto.EmployeeDTO;
import com.company.dto.MemberDTO;

@Repository
public class EmployeeDAO {
	@Autowired
	public SqlSessionTemplate temp;

	// 조건 검색 직원 목록 불러오기
	public List<EmployeeDTO> GetSchEList(Map<String, Object> map) {
		return temp.selectList("eMap.GetSchEList", map);
	}

	// 조건 검색 직원 페이지 개수 구하기
	public int GetSchPageCnt(Map<String, Object> map) {
		return temp.selectOne("eMap.GetSchPageCnt", map);
	}

	// 직원 정보 가져오기
	public EmployeeDTO GetEInfo(String mId) {
		return temp.selectOne("eMap.GetEInfo", mId);
	}

	// 직원 관리자 권한 부여
	public boolean MakeDoAuth(String mId) {
		int rs = temp.update("eMap.MakeDoAuth", mId);
		if (rs == 1)
			return true;
		else
			return false;
	}

	// 직원 관리자 권한 해지
	public boolean MakeDontAuth(String mId) {
		int rs = temp.update("eMap.MakeDontAuth", mId);
		if (rs == 1)
			return true;
		else
			return false;
	}

	// 이메일 검색된 직원 수 구하기
	public int GetsSchEmACnt(Map<String, Object> map) {
		return temp.selectOne("eMap.GetsSchEmACnt", map);
	}

	// 이메일 검색된 직원 목록 구하기
	public List<EmployeeDTO> GetsSchEmAdmin(Map<String, Object> map) {
		return temp.selectList("eMap.GetsSchEmAdmin", map);
	}
}
