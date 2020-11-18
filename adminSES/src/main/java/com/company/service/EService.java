package com.company.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.company.dto.EmployeeDTO;
import com.company.dto.SnsDTO;

public interface EService {
	// ���� �˻� ���� ��� �ҷ�����
	List<EmployeeDTO> GetSchEList(Map<String, Object> map);

	// ���� �˻� ���� ������ ���� ���ϱ�
	int GetSchPageCnt(Map<String, Object> map);

	// ���� ���� ��������
	EmployeeDTO GetEInfo(String mId);

	// ���� ������ ���� �ο�
	boolean MakeDoAuth(Map<String, Object> map);

	// ���� ������ ���� ����
	boolean MakeDontAuth(Map<String, Object> map);
	
}
