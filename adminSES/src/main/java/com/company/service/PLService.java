package com.company.service;

import java.util.List;
import java.util.Map;

import com.company.dto.PayLogDTO;
import com.company.dto.QnaDTO;

public interface PLService {
	// ȸ���� ���� ���� ��������
	public List<PayLogDTO> getMemberPLList(String mId);
}
