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

	// 회원의 Pay내역 가져오기
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

	// 해당 회원의 이번달 내역 가져오기
	@Override
	public int getChkPay(Map<String, Object> map) {
		return plDAO.getChkPay(map);
	}

	// 검색된 회원의 Pay 횟수 구하기
	@Override
	public int getMPayList(String mId) {
		return plDAO.getMPayList(mId);
	}

	// 유료서비스 납부 처리
	@Override
	public boolean MakePay(Map<String, Object> map) {
		return plDAO.MakePay(map);
	}

	// 유료서비스 미납 처리
	@Override
	public boolean MakeNoPay(Map<String, Object> map) {
		return plDAO.MakeNoPay(map);
	}
}
