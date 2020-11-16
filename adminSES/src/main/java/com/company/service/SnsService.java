package com.company.service;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.company.dao.SnsDAO;
import com.company.dto.SnsDTO;

@Service
public class SnsService implements SService{
	@Inject
	SnsDAO sDAO;

	// ���� �˻� SNS�� ����� ��� �ҷ�����
	@Override
	public List<SnsDTO> GetSchSList(Map<String, Object> map) {
		return sDAO.GetSchSList(map);
	}

	// ���� �˻� SNS�� ����� ������ ���� ���ϱ�
	@Override
	public int GetSchPageCnt(Map<String, Object> map) {
		return sDAO.GetSchPageCnt(map);
	}

	// ���� �˻� SNS�� ����� ���� �ҷ�����
	@Override
	public SnsDTO GetSInfo(String mId) {
		return sDAO.GetSInfo(mId);
	}

}
