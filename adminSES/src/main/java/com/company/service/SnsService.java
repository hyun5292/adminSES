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
	public SnsDTO GetSInfo(Map<String, Object> map) {
		return sDAO.GetSInfo(map);
	}

	// ���ο� SNS ����� ���
	@Override
	public boolean NewSnsMember(Map<String, Object> map) {
		return sDAO.NewSnsMember(map);
	}

	//  SNS ����� ���� ����
	@Override
	public boolean ModifySnsMember(Map<String, Object> map) {
		return sDAO.ModifySnsMember(map);
	}

	// SNS�� ����� ����
	@Override
	public boolean DeleteSNS(Map<String, Object> map) {
		return sDAO.DeleteSNS(map);
	}

	// ���� ����� ������ ����
	@Override
	public boolean ChgNowSns(Map<String, Object> map) {
		return sDAO.ChgNowSns(map);
	}

	// ���� ��ϵ� �ٸ� SNS ����ڰ� �ִ��� Ȯ��
	@Override
	public SnsDTO ChkTodaySNS(Map<String, Object> map) {
		return sDAO.ChkTodaySNS(map);
	}

}
