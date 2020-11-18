package com.company.service;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.company.dao.MemberDAO;
import com.company.dto.MemberDTO;

@Service
public class MemberService implements MService {
	@Inject
	MemberDAO mDAO;

	// 회원 목록 불러오기
	@Override
	public List<MemberDTO> GetMList(Map<String, Object> map) {
		return mDAO.GetMList(map);
	}

	// 전체 페이지 개수 구하기
	@Override
	public int PageCnt() {
		return mDAO.ListCnt();
	}

	// 조건 검색 회원 목록 불러오기
	@Override
	public List<MemberDTO> GetSchMList(Map<String, Object> map) {
		return mDAO.GetSchMList(map);
	}

	// 조건 검색 회원 페이지 개수 구하기
	@Override
	public int GetSchPageCnt(Map<String, Object> map) {
		return mDAO.GetSchPageCnt(map);
	}
	
	// 회원 정보 가져오기
	@Override
	public MemberDTO GetMInfo(String mId) {
		return mDAO.GetMInfo(mId);
	}

	// 탈퇴한 일반 회원 수 구하기
	@Override
	public int GeneralNotUseCnt() {
		return mDAO.GeneralNotUseCnt();
	}

	// 해당 서비스 이용 중인 회원 수 구하기
	@Override
	public int GetM_NCHK() {
		return mDAO.GetM_NCHK();
	}

	// 페이스북 이용 중인 회원 수 구하기
	@Override
	public int GetM_FBCHK() {
		return mDAO.GetM_FBCHK();
	}

	// 구글 이용 중인 회원 수 구하기
	@Override
	public int GetM_GCHK() {
		return mDAO.GetM_GCHK();
	}

	// 카톡 이용 중인 회원 수 구하기
	@Override
	public int GetM_KTCHK() {
		return mDAO.GetM_KTCHK();
	}
}
