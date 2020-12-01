package com.company.service;

import java.util.List;
import java.util.Map;

import com.company.dto.LogDTO;

public interface LService {

	// �ش� ��� �α� ��� �ҷ�����
	public List<LogDTO> getMemberLogList(Map<String, Object> map);

	// �ش� ��� �α� ��� ���� �ҷ�����
	public int getMemberLListCnt(String mId);

	// ��¥���� �˻��� ������ Ȱ�� ���� ���� ��������
	public int getSearchedMemberLListCnt(Map<String, Object> lmap);

	// ��¥���� �˻��� ������ Ȱ�� ���� ��������
	public List<LogDTO> getSearchedMemberLList(Map<String, Object> lmap);

}
