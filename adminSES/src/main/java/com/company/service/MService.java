package com.company.service;

import java.util.List;
import java.util.Map;

import com.company.dto.MemberDTO;

public interface MService {

	// ȸ�� ��� �ҷ�����
	List<MemberDTO> GetMList(Map<String, Object> map);

	// ��ü ������ ���� ���ϱ�
	public int PageCnt();

	// ���� �˻� ȸ�� ��� �ҷ�����
	List<MemberDTO> GetSchMList(Map<String, Object> map);

	// ���� �˻� ȸ�� ������ ���� ���ϱ�
	int GetSchPageCnt(Map<String, Object> map);

	// ȸ�� ���� ��������
	public MemberDTO GetMInfo(String mId);

	// �Ϲ� ȸ�� �� ���ϱ�
	public int GeneralCnt();

	// Ż���� �Ϲ� ȸ�� �� ���ϱ�
	public int GeneralNotUseCnt();
}
