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

	// ���� ��� ��������
	@Override
	public List<QnaDTO> GetQList(Map<String, Object> map) {
		return qDAO.GetQList(map);
	}

	// ���� ��� ���� ��������
	@Override
	public int GetQListCnt() {
		return qDAO.GetQListCnt();
	}

	// ���� ��¥ �˻� �� ��������
	@Override
	public int GetSchQListCnt(Map<String, Object> map) {
		return qDAO.GetSchQListCnt(map);
	}

	// ���� ��¥ �˻� ��� ��������
	@Override
	public List<QnaDTO> GetSchQList(Map<String, Object> map) {
		return qDAO.GetSchQList(map);
	}

	// ���� ū ���� ��ȣ ��������
	@Override
	public int GetLastQnum() {
		return qDAO.GetLastQnum();
	}

	// ���� Ŭ�� �� ���� �ҷ�����
	@Override
	public QnaDTO GetQData(String qnum) {
		return qDAO.GetQData(qnum);
	}

	// ���� ���� �亯 �Է�
	@Override
	public boolean AddReply(Map<String, Object> map) {
		return qDAO.AddReply(map);
	}

	// �ۼ��� ���� �Է�
	@Override
	public boolean AddMewQna(Map<String, Object> map) {
		return qDAO.AddMewQna(map);
	}

	// ���� ���� ����
	@Override
	public boolean DeleteQna(String qnum) {
		return qDAO.DeleteQna(qnum);
	}

	// ���� ���� Ű���� �˻� ��� �� ���ϱ�
	@Override
	public int GetSchKWQListCnt(Map<String, Object> map) {
		return qDAO.GetSchKWQListCnt(map);
	}

	// ���� ���� Ű���� �˻� ��� ���ϱ�
	@Override
	public List<QnaDTO> GetSchKWQList(Map<String, Object> map) {
		return qDAO.GetSchKWQList(map);
	}
}
