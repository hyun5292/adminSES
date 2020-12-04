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

	// 유료서비스 이용자 수 구하기
	public int GetServiceUserCnt();

	// 이메일용 회원 목록 불러오기
	public List<MemberDTO> GetMEmailList(Map<String, Object> map);

	// 이메일용 회원 수 구하기
	public int GetMEmailCnt();

	// 이메일 일반 회원 목록 가져오기
	public List<MemberDTO> GetsSchEmGeneral(Map<String, Object> map);

	// 이메일 검색된 일반 회원 수 구하기
	public int GetsSchEmGCnt(Map<String, Object> map);

	// PayLog 회원 목록 불러오기
	public List<MemberDTO> GetPlMList(Map<String, Object> map);

	// 유료서비스 미가입/가입 처리
	boolean MakePLJoin(Map<String, Object> map);

	// 유료서비스 회원 조건 검색 수 구하기
	public int GetsSchPayMCnt(Map<String, Object> map);

	// 유료서비스 회원 조건 검색 목록 구하기
	public List<MemberDTO> GetsSchPayM(Map<String, Object> mmap);
}
