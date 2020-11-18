package com.company.service;

import java.util.List;
import java.util.Map;

import com.company.dto.MemberDTO;

public interface MService {

	// 회원 목록 불러오기
	List<MemberDTO> GetMList(Map<String, Object> map);

	// 전체 페이지 개수 구하기
	public int PageCnt();

	// 조건 검색 회원 목록 불러오기
	List<MemberDTO> GetSchMList(Map<String, Object> map);

	// 조건 검색 회원 페이지 개수 구하기
	int GetSchPageCnt(Map<String, Object> map);

	// 회원 정보 가져오기
	public MemberDTO GetMInfo(String mId);

	// 탈퇴한 일반 회원 수 구하기
	public int GeneralNotUseCnt();

	// 네이버 이용 중인 회원 수 구하기
	public int GetM_NCHK();

	// 페이스북 이용 중인 회원 수 구하기
	public int GetM_FBCHK();

	// 구글 이용 중인 회원 수 구하기
	public int GetM_GCHK();

	// 카톡 이용 중인 회원 수 구하기
	public int GetM_KTCHK();
}
