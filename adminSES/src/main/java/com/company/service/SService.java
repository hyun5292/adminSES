package com.company.service;

import java.util.List;
import java.util.Map;

import com.company.dto.SnsDTO;

public interface SService {

	// ���� �˻� SNS�� ����� ��� �ҷ�����
	List<SnsDTO> GetSchSList(Map<String, Object> map);

	// ���� �˻� SNS�� ����� ������ ���� ���ϱ�
	int GetSchPageCnt(Map<String, Object> map);

}
