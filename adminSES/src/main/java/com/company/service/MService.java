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

	// Ż���� �Ϲ� ȸ�� �� ���ϱ�
	public int GeneralNotUseCnt();

	// ���̹� �̿� ���� ȸ�� �� ���ϱ�
	public int GetM_NCHK();

	// ���̽��� �̿� ���� ȸ�� �� ���ϱ�
	public int GetM_FBCHK();

	// ���� �̿� ���� ȸ�� �� ���ϱ�
	public int GetM_GCHK();

	// ī�� �̿� ���� ȸ�� �� ���ϱ�
	public int GetM_KTCHK();

	// ���Ἥ�� �̿��� �� ���ϱ�
	public int GetServiceUserCnt();

	// �̸��Ͽ� ȸ�� ��� �ҷ�����
	public List<MemberDTO> GetMEmailList(Map<String, Object> map);

	// �̸��Ͽ� ȸ�� �� ���ϱ�
	public int GetMEmailCnt();

	// �̸��� �Ϲ� ȸ�� ��� ��������
	public List<MemberDTO> GetsSchEmGeneral(Map<String, Object> map);

	// �̸��� �˻��� �Ϲ� ȸ�� �� ���ϱ�
	public int GetsSchEmGCnt(Map<String, Object> map);

	// PayLog ȸ�� ��� �ҷ�����
	public List<MemberDTO> GetPlMList(Map<String, Object> map);

	// ���Ἥ�� �̰���/���� ó��
	boolean MakePLJoin(Map<String, Object> map);

	// ���Ἥ�� ȸ�� ���� �˻� �� ���ϱ�
	public int GetsSchPayMCnt(Map<String, Object> map);

	// ���Ἥ�� ȸ�� ���� �˻� ��� ���ϱ�
	public List<MemberDTO> GetsSchPayM(Map<String, Object> mmap);
}
