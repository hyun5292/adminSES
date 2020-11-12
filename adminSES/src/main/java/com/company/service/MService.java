package com.company.service;

import java.util.List;
import java.util.Map;

import com.company.dto.MemberDTO;

public interface MService {
	// ȸ�� ��� �ҷ�����
	public List<MemberDTO> GetMList(Map<String, Object> map);

	// ��ü ������ ���� ���ϱ�
	public int PageCnt();

	// �˻��� ȸ�� ��� �ҷ�����
	public List<MemberDTO> GetSearchedMList(Map<String, Object> map);

	// �˻��� ������ ���� ���ϱ�
	public int SearchedListCnt(Map<String, Object> map);

	// ȸ���� ȸ�� �з� ��������
	public String GetMKind(String mId);

	// ȸ�� ���� ��������
	public MemberDTO GetMInfo(String mId);

	// �Ϲ� ȸ�� �� ���ϱ�
	public int GeneralCnt();

	// Ż���� �Ϲ� ȸ�� �� ���ϱ�
	public int GeneralNotUseCnt();
}
