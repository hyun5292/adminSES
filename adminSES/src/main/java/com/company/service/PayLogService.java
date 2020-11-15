package com.company.service;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.company.dao.PayLogDAO;
import com.company.dto.PayLogDTO;
import com.company.dto.QnaDTO;

@Service
public class PayLogService implements PLService{
	@Inject
	PayLogDAO plDAO;

	// 회원의 문의 내역 가져오기
	@Override
	public List<PayLogDTO> getMemberPLList(Map<String, Object> plmap) {
		return plDAO.getMemberPLList(plmap);
	}

	// 전체 페이지 개수 구하기
	@Override
	public int getMemberPLListCnt(String mId) {
		return plDAO.getMemberPLListCnt(mId);
	}

	// 검색된 회원의 결제 내역 개수 가져오기
	@Override
	public int getSearchedMemberPLListCnt(Map<String, Object> pmap) {
		return plDAO.getSearchedMemberPLListCnt(pmap);
	}

	// 검색된 회원의 결제 내역 가져오기
	@Override
	public List<PayLogDTO> getSearchedMemberPLList(Map<String, Object> pmap) {
		return plDAO.getSearchedMemberPLList(pmap);
	}

}
