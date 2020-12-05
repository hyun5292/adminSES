package com.company.service;

import java.util.List;
import java.util.Map;

import com.company.dto.QnaDTO;

public interface QService {
	// ȸ���� ���� ���� ��������
	public List<QnaDTO> getMemberQList(Map<String, Object> qmap);

	// ��ü ������ ���� ���ϱ�
	public int getMemberQListCnt(String mId);

	// �˻��� ȸ���� ���� ���� ���� ��������
	public int getSearchedMemberQListCnt(Map<String, Object> qmap);

	// �˻��� ȸ���� ���� ���� ��������
	public List<QnaDTO> getSearchedMemberQList(Map<String, Object> qmap);

	// �˻��� SNS�� ������� ���� ���� ��������
	public List<QnaDTO> GetSnsQList(Map<String, Object> map);

	// �˻��� SNS�� ������� ���� ���� ���� ��������
	public int GetSnsQListCnt(Map<String, Object> map);

	// ��¥���� �˻��� SNS�� ������� ���� ���� ��������
	public List<QnaDTO> GetSchSnsQList(Map<String, Object> map);

	// ��¥���� �˻��� SNS�� ������� ���� ���� ���� ��������
	public int GetSchSnsQListCnt(Map<String, Object> map);

	// ����ȭ��� �ֽ� ���� ���� ��������
	public List<QnaDTO> GetQRecentList();

	// ���� ��� ��������
	public List<QnaDTO> GetQList(Map<String, Object> map);

	// ���� ��� ���� ��������
	public int GetQListCnt();

	// ���� ��¥ �˻� �� ��������
	public int GetSchQListCnt(Map<String, Object> map);

	// ���� ��¥ �˻� ��� ��������
	public List<QnaDTO> GetSchQList(Map<String, Object> map);

	// ���� ū ���� ��ȣ ��������
	public int GetLastQnum();

	// ���� Ŭ�� �� ���� �ҷ�����
	public QnaDTO GetQData(String qnum);

	// ���� ���� �亯 �Է�
	public boolean AddReply(Map<String, Object> map);

	// �ۼ��� ���� �Է�
	public boolean AddMewQna(Map<String, Object> map);

	// ���� ���� ����
	public boolean DeleteQna(String qnum);

	// ���� ���� Ű���� �˻� ��� �� ���ϱ�
	public int GetSchKWQListCnt(Map<String, Object> map);

	// ���� ���� Ű���� �˻� ��� ���ϱ�
	public List<QnaDTO> GetSchKWQList(Map<String, Object> map);
}
