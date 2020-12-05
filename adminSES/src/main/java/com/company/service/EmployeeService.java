package com.company.service;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.company.dao.EmployeeDAO;
import com.company.dto.EmployeeDTO;

@Service
public class EmployeeService implements EService{
	@Inject
	EmployeeDAO eDAO;

	// ���� �˻� ���� ��� �ҷ�����
	@Override
	public List<EmployeeDTO> GetSchEList(Map<String, Object> map) {
		return eDAO.GetSchEList(map);
	}

	// ���� �˻� ���� ������ ���� ���ϱ�
	@Override
	public int GetSchPageCnt(Map<String, Object> map) {
		return eDAO.GetSchPageCnt(map);
	}

	// ���� ���� ��������
	@Override
	public EmployeeDTO GetEInfo(String mId) {
		return eDAO.GetEInfo(mId);
	}

	// ���� ������ ���� �ο�
	@Override
	public boolean MakeDoAuth(String mId) {
		return eDAO.MakeDoAuth(mId);
	}

	// ���� ������ ���� ����
	@Override
	public boolean MakeDontAuth(String mId) {
		return eDAO.MakeDontAuth(mId);
	}

	// �̸��� �˻��� ���� �� ���ϱ�
	@Override
	public int GetsSchEmACnt(Map<String, Object> map) {
		return eDAO.GetsSchEmACnt(map);
	}

	// �̸��� �˻��� ���� ��� ���ϱ�
	@Override
	public List<EmployeeDTO> GetsSchEmAdmin(Map<String, Object> map) {
		return eDAO.GetsSchEmAdmin(map);
	}

	// �α���
	@Override
	public EmployeeDTO ELogin(HttpServletRequest request) {
		return eDAO.ELogin(request.getParameter("eId"));
	}
	
}
