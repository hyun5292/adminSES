package com.company.service;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.company.dao.MemberDAO;
import com.company.dto.MemberDTO;

@Service
public class MemberService implements MService {
	@Inject
	MemberDAO mDAO;

	// ȸ�� ��� �ҷ�����
	@Override
	public List<MemberDTO> GetMList(Map<String, Object> map) {
		return mDAO.GetMList(map);
	}

	// ��ü ������ ���� ���ϱ�
	@Override
	public int PageCnt() {
		return mDAO.ListCnt();
	}

	// ���� �˻� ȸ�� ��� �ҷ�����
	@Override
	public List<MemberDTO> GetSchMList(Map<String, Object> map) {
		return mDAO.GetSchMList(map);
	}

	// ���� �˻� ȸ�� ������ ���� ���ϱ�
	@Override
	public int GetSchPageCnt(Map<String, Object> map) {
		return mDAO.GetSchPageCnt(map);
	}
	
	// ȸ�� ���� ��������
	@Override
	public MemberDTO GetMInfo(String mId) {
		return mDAO.GetMInfo(mId);
	}

	// Ż���� �Ϲ� ȸ�� �� ���ϱ�
	@Override
	public int GeneralNotUseCnt() {
		return mDAO.GeneralNotUseCnt();
	}

	// �ش� ���� �̿� ���� ȸ�� �� ���ϱ�
	@Override
	public int GetM_NCHK() {
		return mDAO.GetM_NCHK();
	}

	// ���̽��� �̿� ���� ȸ�� �� ���ϱ�
	@Override
	public int GetM_FBCHK() {
		return mDAO.GetM_FBCHK();
	}

	// ���� �̿� ���� ȸ�� �� ���ϱ�
	@Override
	public int GetM_GCHK() {
		return mDAO.GetM_GCHK();
	}

	// ī�� �̿� ���� ȸ�� �� ���ϱ�
	@Override
	public int GetM_KTCHK() {
		return mDAO.GetM_KTCHK();
	}

	// ���Ἥ�� �̿��� �� ���ϱ�
	@Override
	public int GetServiceUserCnt() {
		return mDAO.GetServiceUserCnt();
	}

	// �̸��Ͽ� ȸ�� ��� �ҷ�����
	@Override
	public List<MemberDTO> GetMEmailList(Map<String, Object> map) {
		return mDAO.GetMEmailList(map);
	}

	// �̸��Ͽ� ȸ�� �� ���ϱ�
	@Override
	public int GetMEmailCnt() {
		return mDAO.GetMEmailCnt();
	}

	// �̸��� �Ϲ� ȸ�� ��� ��������
	@Override
	public List<MemberDTO> GetsSchEmGeneral(Map<String, Object> map) {
		return mDAO.GetsSchEmGeneral(map);
	}

	// �̸��� �˻��� �Ϲ� ȸ�� �� ���ϱ�
	@Override
	public int GetsSchEmGCnt(Map<String, Object> map) {
		return mDAO.GetsSchEmGCnt(map);
	}

	// PayLog ȸ�� ��� �ҷ�����
	@Override
	public List<MemberDTO> GetPlMList(Map<String, Object> map) {
		return mDAO.GetPlMList(map);
	}

	// ���Ἥ�� �̰���/���� ó��
	@Override
	public boolean MakePLJoin(Map<String, Object> map) {
		return mDAO.MakePLJoin(map);
	}

	// ���Ἥ�� ȸ�� ���� �˻� �� ���ϱ�
	@Override
	public int GetsSchPayMCnt(Map<String, Object> map) {
		return mDAO.GetsSchPayMCnt(map);
	}

	// ���Ἥ�� ȸ�� ���� �˻� ��� ���ϱ�
	@Override
	public List<MemberDTO> GetsSchPayM(Map<String, Object> mmap) {
		return mDAO.GetsSchPayM(mmap);
	}

	// ����ȭ�� ��� Name �˻�
	@Override
	public List<MemberDTO> GetSchMmain(Map<String, Object> map) {
		return mDAO.GetSchMmain(map);
	}
}
