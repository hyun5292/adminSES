package com.company.service;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.company.dao.EmpLogDAO;
import com.company.dto.EmpLogDTO;

@Service
public class EmpLogService implements ELService{
	@Inject
	EmpLogDAO elDAO;

	// ȸ���� Ȱ�� �α� ��ü ���� ��������
	@Override
	public int GetELPageCnt(String mId) {
		return elDAO.GetELPageCnt(mId);
	}

	// ȸ���� Ȱ�� �α� ��ü ��������
	@Override
	public List<EmpLogDTO> GetELList(Map<String, Object> map) {
		return elDAO.GetELList(map);
	}

	// ��¥���� �˻��� ȸ���� Ȱ�� �α� ��ü ���� ��������
	@Override
	public int GetSchELPageCnt(Map<String, Object> map) {
		return elDAO.GetSchELPageCnt(map);
	}

	// ��¥���� �˻��� ȸ���� Ȱ�� �α� ��ü ��������
	@Override
	public List<EmpLogDTO> GetSchELList(Map<String, Object> map) {
		return elDAO.GetSchELList(map);
	}

	// Ȱ�� ��� �����
	@Override
	public boolean WriteLog(Map<String, Object> map) {
		return elDAO.WriteLog(map);
	}

}
