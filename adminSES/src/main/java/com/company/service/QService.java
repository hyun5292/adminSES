package com.company.service;

import java.util.List;
import java.util.Map;

import com.company.dto.QnaDTO;

public interface QService {
	// 회원의 문의 내역 가져오기
	public List<QnaDTO> getMemberQList(Map<String, Object> qmap);

	// 전체 페이지 개수 구하기
	public int getMemberQListCnt(String mId);

	// 검색된 회원의 문의 내역 개수 가져오기
	public int getSearchedMemberQListCnt(Map<String, Object> qmap);

	// 검색된 회원의 문의 내역 가져오기
	public List<QnaDTO> getSearchedMemberQList(Map<String, Object> qmap);

	// 검색된 SNS사 담당자의 문의 내역 가져오기
	public List<QnaDTO> GetSnsQList(Map<String, Object> map);

	// 검색된 SNS사 담당자의 문의 내역 개수 가져오기
	public int GetSnsQListCnt(Map<String, Object> map);

	// 날짜별로 검색된 SNS사 담당자의 문의 내역 가져오기
	public List<QnaDTO> GetSchSnsQList(Map<String, Object> map);

	// 날짜별로 검색된 SNS사 담당자의 문의 내역 개수 가져오기
	public int GetSchSnsQListCnt(Map<String, Object> map);

	// 메인화면용 최신 문의 내역 가져오기
	public List<QnaDTO> GetQRecentList();

	// 문의 목록 가져오기
	public List<QnaDTO> GetQList(Map<String, Object> map);

	// 문의 목록 개수 가져오기
	public int GetQListCnt();

	// 문의 날짜 검색 수 가져오기
	public int GetSchQListCnt(Map<String, Object> map);

	// 문의 날짜 검색 목록 가져오기
	public List<QnaDTO> GetSchQList(Map<String, Object> map);
}
