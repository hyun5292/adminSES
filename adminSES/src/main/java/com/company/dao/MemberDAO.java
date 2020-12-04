package com.company.dao;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.company.dto.MemberDTO;

@Repository
public class MemberDAO {
	@Autowired
	public SqlSessionTemplate temp;

	// 회원 목록 불러오기
	public List<MemberDTO> GetMList(Map<String, Object> map) {
		return temp.selectList("mMap.GetMList", map);
	}

	// 전체 페이지 개수 구하기
	public int ListCnt() {
		return temp.selectOne("mMap.ListCnt");
	}

	// 조건 검색 회원 목록 불러오기
	public List<MemberDTO> GetSchMList(Map<String, Object> map) {
		return temp.selectList("mMap.GetSchMList", map);
	}

	// 조건 검색 회원 페이지 개수 구하기
	public int GetSchPageCnt(Map<String, Object> map) {
		return temp.selectOne("mMap.GetSchPageCnt", map);
	}

	// 검색된 페이지 개수 구하기
	public int SearchedListCnt(Map<String, Object> map) {
		return temp.selectOne("mMap.SearchedListCnt", map);
	}

	// 회원의 회원 분류 가져오기
	public String GetMKind(String mId) {
		return temp.selectOne("mMap.GetMKind", mId);
	}

	// 회원 정보 가져오기
	public MemberDTO GetMInfo(String mId) {
		return temp.selectOne("mMap.GetMInfo", mId);
	}

	// 탈퇴한 일반 회원 수 구하기
	public int GeneralNotUseCnt() {
		return temp.selectOne("mMap.GeneralNotUseCnt");
	}

	// 해당 서비스 이용 중인 회원 수 구하기
	public int GetM_NCHK() {
		return temp.selectOne("mMap.GetM_NCHK");
	}

	// 페이스북 이용 중인 회원 수 구하기
	public int GetM_FBCHK() {
		return temp.selectOne("mMap.GetM_FBCHK");
	}

	// 구글 이용 중인 회원 수 구하기
	public int GetM_GCHK() {
		return temp.selectOne("mMap.GetM_GCHK");
	}

	// 카톡 이용 중인 회원 수 구하기
	public int GetM_KTCHK() {
		return temp.selectOne("mMap.GetM_KTCHK");
	}

	// 유료서비스 이용자 수 구하기
	public int GetServiceUserCnt() {
		return temp.selectOne("mMap.GetServiceUserCnt");
	}

	// 이메일용 회원 목록 불러오기
	public List<MemberDTO> GetMEmailList(Map<String, Object> map) {
		return temp.selectList("mMap.GetMEmailList", map);
	}

	// 이메일용 회원 수 구하기
	public int GetMEmailCnt() {
		return temp.selectOne("mMap.GetMEmailCnt");
	}

	// 이메일 일반 회원 목록 가져오기
	public List<MemberDTO> GetsSchEmGeneral(Map<String, Object> map) {
		return temp.selectList("mMap.GetsSchEmGeneral", map);
	}

	// 이메일 검색된 일반 회원 수 구하기
	public int GetsSchEmGCnt(Map<String, Object> map) {
		return temp.selectOne("mMap.GetsSchEmGCnt", map);
	}

	// PayLog 회원 목록 불러오기
	public List<MemberDTO> GetPlMList(Map<String, Object> map) {
		return temp.selectList("mMap.GetPlMList", map);
	}

	// 유료서비스 미가입/가입 처리
	public boolean MakePLJoin(Map<String, Object> map) {
		int rs = temp.update("mMap.MakePLJoin", map);
		if (rs == 1)
			return true;
		else
			return false;
	}

	// 유료서비스 회원 조건 검색 수 구하기
	public int GetsSchPayMCnt(Map<String, Object> map) {
		return temp.selectOne("mMap.GetsSchPayMCnt", map);
	}

	// 유료서비스 회원 조건 검색 목록 구하기
	public List<MemberDTO> GetsSchPayM(Map<String, Object> mmap) {
		return temp.selectList("mMap.GetsSchPayM", mmap);
	}
}