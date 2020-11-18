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
	public int GetSnsQListCnt(String mId) {
		return temp.selectOne("qMap.GetSnsQListCnt", mId);
	}

	// 날짜별로 검색된 SNS사 담당자의 문의 내역 가져오기
	public List<QnaDTO> GetSchSnsQList(Map<String, Object> map) {
		return temp.selectList("qMap.GetSchSnsQList", map);
	}

	// 날짜별로 검색된 SNS사 담당자의 문의 내역 개수 가져오기
	public int GetSchSnsQListCnt(Map<String, Object> map) {
		return temp.selectOne("qMap.GetSchSnsQListCnt", map);
	}
}
