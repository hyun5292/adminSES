package com.company.service;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.company.dao.PayLogDAO;
import com.company.dto.PayLogDTO;
import com.company.dto.QnaDTO;

@Service
public class PayLogService implements PLService{
	@Inject
	PayLogDAO plDAO;

	// ȸ���� ���� ���� ��������
	@Override
	public List<PayLogDTO> getMemberPLList(Map<String, Object> plmap) {
		return plDAO.getMemberPLList(plmap);
	}

	// ��ü ������ ���� ���ϱ�
	@Override
	public int getMemberPLListCnt(String mId) {
		return plDAO.getMemberPLListCnt(mId);
	}

	// �˻��� ȸ���� ���� ���� ���� ��������
	@Override
	public int getSearchedMemberPLListCnt(Map<String, Object> pmap) {
		return plDAO.getSearchedMemberPLListCnt(pmap);
	}

	// �˻��� ȸ���� ���� ���� ��������
	@Override
	public List<PayLogDTO> getSearchedMemberPLList(Map<String, Object> pmap) {
		return plDAO.getSearchedMemberPLList(pmap);
	}

}
