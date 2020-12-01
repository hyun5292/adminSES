package com.company.service;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.company.dao.QnaDAO;
import com.company.dto.QnaDTO;

@Service
public class QnaService implements QService {
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

	// 검색된 SNS사 담당자의 문의 내역 개수 가져오기
	@Override
	public List<QnaDTO> GetSnsQList(Map<String, Object> map) {
		return qDAO.GetSnsQList(map);
	}

	// 검색된 SNS사 담당자의 문의 내역 개수 가져오기
	@Override
	public int GetSnsQListCnt(Map<String, Object> map) {
		return qDAO.GetSnsQListCnt(map);
	}

	// 날짜별로 검색된 SNS사 담당자의 문의 내역 가져오기
	@Override
	public List<QnaDTO> GetSchSnsQList(Map<String, Object> map) {
		return qDAO.GetSchSnsQList(map);
	}

	// 날짜별로 검색된 SNS사 담당자의 문의 내역 개수 가져오기
	@Override
	public int GetSchSnsQListCnt(Map<String, Object> map) {
		return qDAO.GetSchSnsQListCnt(map);
	}

	// 메인화면용 최신 문의 내역 가져오기
	@Override
	public List<QnaDTO> GetQRecentList() {
		return qDAO.GetQRecentList();
	}
}
