package com.company.service;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.company.dao.QnaDAO;
import com.company.dto.QnaDTO;

@Service
public class QnaService implements QService{
	@Inject
	QnaDAO qDAO;

	// 회원의 문의 내역 가져오기
	@Override
	public List<QnaDTO> getMemberQList(Map<String, Object> qmap) {
		return qDAO.getMemberQList(qmap);
	}

	// 전체 페이지 개수 구하기
	@Override
	public int getMemberQListCnt(String mId) {
		return qDAO.getMemberQListCnt(mId);
	}

	// 검색된 회원의 문의 내역 개수 가져오기
	@Override
	public int getSearchedMemberQListCnt(Map<String, Object> qmap) {
		return qDAO.getSearchedMemberQListCnt(qmap);
	}

	// 검색된 회원의 문의 내역 가져오기
	@Override
	public List<QnaDTO> getSearchedMemberQList(Map<String, Object> qmap) {
		return qDAO.getSearchedMemberQList(qmap);
	}

}
