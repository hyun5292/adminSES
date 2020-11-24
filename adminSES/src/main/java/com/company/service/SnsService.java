package com.company.service;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.company.dao.SnsDAO;
import com.company.dto.SnsDTO;

@Service
public class SnsService implements SService{
	@Inject
	SnsDAO sDAO;

	// 조건 검색 SNS사 담당자 목록 불러오기
	@Override
	public List<SnsDTO> GetSchSList(Map<String, Object> map) {
		return sDAO.GetSchSList(map);
	}

	// 조건 검색 SNS사 담당자 페이지 개수 구하기
	@Override
	public int GetSchPageCnt(Map<String, Object> map) {
		return sDAO.GetSchPageCnt(map);
	}

	// 조건 검색 SNS사 담당자 정보 불러오기
	@Override
	public SnsDTO GetSInfo(Map<String, Object> map) {
		return sDAO.GetSInfo(map);
	}

	// 새로운 SNS 담당자 등록
	@Override
	public boolean NewSnsMember(Map<String, Object> map) {
		return sDAO.NewSnsMember(map);
	}

	//  SNS 담당자 정보 수정
	@Override
	public boolean ModifySnsMember(Map<String, Object> map) {
		return sDAO.ModifySnsMember(map);
	}

	// SNS사 담당자 삭제
	@Override
	public boolean DeleteSNS(Map<String, Object> map) {
		return sDAO.DeleteSNS(map);
	}

	// 기존 담당자 마감일 설정
	@Override
	public boolean ChgNowSns(Map<String, Object> map) {
		return sDAO.ChgNowSns(map);
	}

	// 오늘 등록된 다른 SNS 담당자가 있는지 확인
	@Override
	public SnsDTO ChkTodaySNS(Map<String, Object> map) {
		return sDAO.ChkTodaySNS(map);
	}

}
