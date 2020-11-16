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

	// 일반 회원 수 구하기
	@Override
	public int GeneralCnt() {
		return mDAO.GeneralCnt();
	}

	// 탈퇴한 일반 회원 수 구하기
	@Override
	public int GeneralNotUseCnt() {
		return mDAO.GeneralNotUseCnt();
	}
}
