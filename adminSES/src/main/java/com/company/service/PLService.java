package com.company.service;

import java.util.List;
import java.util.Map;

import com.company.dto.PayLogDTO;
import com.company.dto.QnaDTO;

public interface PLService {
	// ȸ���� ���� ���� ��������
	public List<PayLogDTO> getMemberPLList(Map<String, Object> plmap);

	// ��ü ������ ���� ���ϱ�
	public int getMemberPLListCnt(String mId);

	// �˻��� ȸ���� ���� ���� ���� ��������
	public int getSearchedMemberPLListCnt(Map<String, Object> pmap);

	// �˻��� ȸ���� ���� ���� ��������
	public List<PayLogDTO> getSearchedMemberPLList(Map<String, Object> pmap);
}
