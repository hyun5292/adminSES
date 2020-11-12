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

	// �˻��� ȸ�� ��� �ҷ�����
	@Override
	public List<MemberDTO> GetSearchedMList(Map<String, Object> map) {
		return mDAO.GetSearchedMList(map);
	}

	// �˻��� ������ ���� ���ϱ�
	@Override
	public int SearchedListCnt(Map<String, Object> map) {
		return mDAO.SearchedListCnt(map);
	}

	// ȸ���� ȸ�� �з� ��������
	@Override
	public String GetMKind(String mId) {
		return mDAO.GetMKind(mId);
	}

	// ȸ�� ���� ��������
	@Override
	public MemberDTO GetMInfo(String mId) {
		return mDAO.GetMInfo(mId);
	}

	// �Ϲ� ȸ�� �� ���ϱ�
	@Override
	public int GeneralCnt() {
		return mDAO.GeneralCnt();
	}

	// Ż���� �Ϲ� ȸ�� �� ���ϱ�
	@Override
	public int GeneralNotUseCnt() {
		return mDAO.GeneralNotUseCnt();
	}
}
