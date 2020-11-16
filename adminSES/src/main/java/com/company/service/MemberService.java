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
