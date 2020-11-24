package com.company.service;

import java.util.List;
import java.util.Map;

import com.company.dto.SnsDTO;

public interface SService {

	// ���� �˻� SNS�� ����� ��� �ҷ�����
	List<SnsDTO> GetSchSList(Map<String, Object> map);

	// ���� �˻� SNS�� ����� ������ ���� ���ϱ�
	int GetSchPageCnt(Map<String, Object> map);

	// ���� �˻� SNS�� ����� ���� �ҷ�����
	SnsDTO GetSInfo(Map<String, Object> map);

	// ���ο� SNS ����� ���
	boolean NewSnsMember(Map<String, Object> map);

	//  SNS ����� ���� ����
	boolean ModifySnsMember(Map<String, Object> map);

	// SNS�� ����� ����
	boolean DeleteSNS(Map<String, Object> map);

	// ���� ����� ������ ����
	boolean ChgNowSns(Map<String, Object> map);

	// ���� ��ϵ� �ٸ� SNS ����ڰ� �ִ��� Ȯ��
	SnsDTO ChkTodaySNS(Map<String, Object> map);

}
