package com.company.dao;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.company.dto.SnsDTO;

@Repository
public class SnsDAO {
	@Autowired
	public SqlSessionTemplate temp;

	// 조건 검색 SNS사 담당자 목록 불러오기
	public List<SnsDTO> GetSchSList(Map<String, Object> map) {
		return temp.selectList("snsMap.GetSchSList", map);
	}

	// 조건 검색 SNS사 담당자 페이지 개수 구하기
	public int GetSchPageCnt(Map<String, Object> map) {
		return temp.selectOne("snsMap.GetSchPageCnt", map);
	}

	// 조건 검색 SNS사 담당자 정보 불러오기
	public SnsDTO GetSInfo(Map<String, Object> map) {
		return temp.selectOne("snsMap.GetSInfo", map);
	}

	// 새로운 SNS 담당자 등록
	public boolean NewSnsMember(Map<String, Object> map) {
		int rs = temp.insert("snsMap.NewSnsMember", map);
		if (rs == 1)
			return true;
		else
			return false;
	}

	//  SNS 담당자 정보 수정
	public boolean ModifySnsMember(Map<String, Object> map) {
		int rs = temp.update("snsMap.ModifySnsMember", map);
		if (rs == 1)
			return true;
		else
			return false;
	}
	// SNS사 담당자 삭제
	public boolean DeleteSNS(Map<String, Object> map) {
		int rs = temp.delete("snsMap.DeleteSNS", map);
		System.out.println(rs);
		if (rs == 1)
			return true;
		else
			return false;
	}

	// 기존 담당자 마감일 설정
	public boolean ChgNowSns(Map<String, Object> map) {
		int rs = temp.update("snsMap.ChgNowSns", map);
		if (rs == 1)
			return true;
		else
			return false;
	}

	// 오늘 등록된 다른 SNS 담당자가 있는지 확인
	public int ChkTodaySNS(Map<String, Object> map) {
		return temp.selectOne("snsMap.ChkTodaySNS", map);
	}

	// sns사 담당자 목록 불러오기
	public List<SnsDTO> GetSnsList() {
		return temp.selectList("snsMap.GetSnsList");
	}
	
}
