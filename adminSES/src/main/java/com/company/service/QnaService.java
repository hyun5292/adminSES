package com.company.service;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.company.dao.QnaDAO;
import com.company.dto.QnaDTO;

@Service
public class QnaService implements QService {
	@Inject
	QnaDAO qDAO;

	// ȸ���� ���� ���� ��������
	@Override
	public List<QnaDTO> getMemberQList(Map<String, Object> qmap) {
		return qDAO.getMemberQList(qmap);
	}

	// ��ü ������ ���� ���ϱ�
	@Override
	public int getMemberQListCnt(String mId) {
		return qDAO.getMemberQListCnt(mId);
	}

	// �˻��� ȸ���� ���� ���� ���� ��������
	@Override
	public int getSearchedMemberQListCnt(Map<String, Object> qmap) {
		return qDAO.getSearchedMemberQListCnt(qmap);
	}

	// �˻��� ȸ���� ���� ���� ��������
	@Override
	public List<QnaDTO> getSearchedMemberQList(Map<String, Object> qmap) {
		return qDAO.getSearchedMemberQList(qmap);
	}

	// �˻��� SNS�� ������� ���� ���� ���� ��������
	@Override
	public List<QnaDTO> GetSnsQList(Map<String, Object> map) {
		return qDAO.GetSnsQList(map);
	}

	// �˻��� SNS�� ������� ���� ���� ���� ��������
	@Override
	public int GetSnsQListCnt(Map<String, Object> map) {
		return qDAO.GetSnsQListCnt(map);
	}

	// ��¥���� �˻��� SNS�� ������� ���� ���� ��������
	@Override
	public List<QnaDTO> GetSchSnsQList(Map<String, Object> map) {
		return qDAO.GetSchSnsQList(map);
	}

	// ��¥���� �˻��� SNS�� ������� ���� ���� ���� ��������
	@Override
	public int GetSchSnsQListCnt(Map<String, Object> map) {
		return qDAO.GetSchSnsQListCnt(map);
	}

	// ����ȭ��� �ֽ� ���� ���� ��������
	@Override
	public List<QnaDTO> GetQRecentList() {
		return qDAO.GetQRecentList();
	}
}
