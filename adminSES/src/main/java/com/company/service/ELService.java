package com.company.service;

import java.util.List;
import java.util.Map;

import com.company.dto.EmpLogDTO;

public interface ELService {
	// ȸ���� Ȱ�� �α� ��ü ���� ��������
	int GetELPageCnt(String mId);

	// ȸ���� Ȱ�� �α� ��ü ��������
	List<EmpLogDTO> GetELList(Map<String, Object> map);

	// ��¥���� �˻��� ȸ���� Ȱ�� �α� ��ü ���� ��������
	int GetSchELPageCnt(Map<String, Object> map);

	// ��¥���� �˻��� ȸ���� Ȱ�� �α� ��ü ��������
	List<EmpLogDTO> GetSchELList(Map<String, Object> map);

	// Ȱ�� ��� �����
	boolean WriteLog(Map<String, Object> map);

	
}
