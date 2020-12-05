package com.company.dao;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.company.dto.QnaDTO;

@Repository
public class QnaDAO {
	@Autowired
	public SqlSessionTemplate temp;

	// 회원의 문의 내역 가져오기
	public List<QnaDTO> getMemberQList(Map<String, Object> qmap) {
		return temp.selectList("qMap.getMemberQList", qmap);
	}

	// 전체 페이지 개수 구하기
	public int getMemberQListCnt(String mId) {
		return temp.selectOne("qMap.getMemberQListCnt", mId);
	}

	// 검색된 회원의 문의 내역 개수 가져오기
	public int getSearchedMemberQListCnt(Map<String, Object> qmap) {
		return temp.selectOne("qMap.getSearchedMemberQListCnt", qmap);
	}

	// 검색된 회원의 문의 내역 가져오기
	public List<QnaDTO> getSearchedMemberQList(Map<String, Object> qmap) {
		return temp.selectList("qMap.getSearchedMemberQList", qmap);
	}

	// 검색된 SNS사 담당자의 문의 내역 가져오기
	public List<QnaDTO> GetSnsQList(Map<String, Object> map) {
		return temp.selectList("qMap.GetSnsQList", map);
	}

	// 검색된 SNS사 담당자의 문의 내역 개수 가져오기
	public int GetSnsQListCnt(Map<String, Object> map) {
		return temp.selectOne("qMap.GetSnsQListCnt", map);
	}

	// 날짜별로 검색된 SNS사 담당자의 문의 내역 가져오기
	public List<QnaDTO> GetSchSnsQList(Map<String, Object> map) {
		return temp.selectList("qMap.GetSchSnsQList", map);
	}

	// 날짜별로 검색된 SNS사 담당자의 문의 내역 개수 가져오기
	public int GetSchSnsQListCnt(Map<String, Object> map) {
		return temp.selectOne("qMap.GetSchSnsQListCnt", map);
	}

	// 메인화면용 최신 문의 내역 가져오기
	public List<QnaDTO> GetQRecentList() {
		return temp.selectList("qMap.GetQRecentList");
	}

	// 문의 목록 가져오기
	public List<QnaDTO> GetQList(Map<String, Object> map) {
		return temp.selectList("qMap.GetQList", map);
	}

	// 문의 목록 개수 가져오기
	public int GetQListCnt() {
		return temp.selectOne("qMap.GetQListCnt");
	}

	// 문의 날짜 검색 수 가져오기
	public int GetSchQListCnt(Map<String, Object> map) {
		return temp.selectOne("qMap.GetSchQListCnt", map);
	}

	// 문의 날짜 검색 목록 가져오기
	public List<QnaDTO> GetSchQList(Map<String, Object> map) {
		return temp.selectList("qMap.GetSchQList", map);
	}

	// 가장 큰 문의 번호 가져오기
	public int GetLastQnum() {
		return temp.selectOne("qMap.GetLastQnum");
	}

	// 문의 클릭 시 내용 불러오기
	public QnaDTO GetQData(String qnum) {
		return temp.selectOne("qMap.GetQData", qnum);
	}

	// 받은 문의 답변 입력
	public boolean AddReply(Map<String, Object> map) {
		int rs = temp.update("qMap.AddReply", map);
		if (rs == 1)
			return true;
		else
			return false;
	}

	// 작성된 문의 입력
	public boolean AddMewQna(Map<String, Object> map) {
		int rs = temp.insert("qMap.AddMewQna", map);
		if (rs == 1)
			return true;
		else
			return false;
	}

	// 받은 문의 삭제
	public boolean DeleteQna(String qnum) {
		int rs = temp.delete("qMap.DeleteQna", qnum);
		if (rs == 1)
			return true;
		else
			return false;
	}

	// 받은 문의 키워드 검색 목록 수 구하기
	public int GetSchKWQListCnt(Map<String, Object> map) {
		return temp.selectOne("qMap.GetSchKWQListCnt", map);
	}

	// 받은 문의 키워드 검색 목록 구하기
	public List<QnaDTO> GetSchKWQList(Map<String, Object> map) {
		return temp.selectList("qMap.GetSchKWQList", map);
	}
}
