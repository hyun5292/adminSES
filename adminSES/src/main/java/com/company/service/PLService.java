package com.company.service;

import java.util.List;
import java.util.Map;

import com.company.dto.PayLogDTO;
import com.company.dto.QnaDTO;

public interface PLService {
	// 회원의 문의 내역 가져오기
	public List<PayLogDTO> getMemberPLList(String mId);
}
