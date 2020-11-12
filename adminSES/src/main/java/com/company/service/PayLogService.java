package com.company.service;

import java.util.List;

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
	public List<PayLogDTO> getMemberPLList(String mId) {
		return plDAO.getMemberPLList(mId);
	}

}
