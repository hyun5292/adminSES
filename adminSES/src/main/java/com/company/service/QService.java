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

	// 가장 큰 문의 번호 가져오기
	public int GetLastQnum();

	// 문의 클릭 시 내용 불러오기
	public QnaDTO GetQData(String qnum);

	// 받은 문의 답변 입력
	public boolean AddReply(Map<String, Object> map);

	// 작성된 문의 입력
	public boolean AddMewQna(Map<String, Object> map);

	// 받은 문의 삭제
	public boolean DeleteQna(String qnum);

	// 받은 문의 키워드 검색 목록 수 구하기
	public int GetSchKWQListCnt(Map<String, Object> map);

	// 받은 문의 키워드 검색 목록 구하기
	public List<QnaDTO> GetSchKWQList(Map<String, Object> map);
}
