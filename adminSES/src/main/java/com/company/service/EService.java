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
	boolean MakeDoAuth(String mId);

	// ���� ������ ���� ����
	boolean MakeDontAuth(String mId);

	// �̸��� �˻��� ���� �� ���ϱ�
	int GetsSchEmACnt(Map<String, Object> map);

	// �̸��� �˻��� ���� ��� ���ϱ�
	List<EmployeeDTO> GetsSchEmAdmin(Map<String, Object> map);

	// �α���
	EmployeeDTO ELogin(HttpServletRequest request);

	// ��й�ȣ ����
	boolean ChagePWD(Map<String, Object> map);
	
}
