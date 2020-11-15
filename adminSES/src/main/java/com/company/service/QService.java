package com.company.service;

import java.util.List;
import java.util.Map;

import com.company.dto.QnaDTO;

public interface QService {
	// ȸ���� ���� ���� ��������
	public List<QnaDTO> getMemberQList(Map<String, Object> qmap);

	// ��ü ������ ���� ���ϱ�
	public int getMemberQListCnt(String mId);

	// �˻��� ȸ���� ���� ���� ���� ��������
	public int getSearchedMemberQListCnt(Map<String, Object> qmap);

	// �˻��� ȸ���� ���� ���� ��������
	public List<QnaDTO> getSearchedMemberQList(Map<String, Object> qmap);
}
