package com.company.service;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.company.dao.LogDAO;
import com.company.dto.LogDTO;

@Service
public class LogService implements LService {
	@Inject
	LogDAO lDAO;

	// �ش� ��� �α� ��� �ҷ�����
	@Override
	public List<LogDTO> getMemberLogList(Map<String, Object> map) {
		return lDAO.getMemberLogList(map);
	}

	// �ش� ��� �α� ��� ���� �ҷ�����
	@Override
	public int getMemberLListCnt(String mId) {
		return lDAO.getMemberLListCnt(mId);
	}

	// ��¥���� �˻��� ������ Ȱ�� ���� ���� ��������
	@Override
	public int getSearchedMemberLListCnt(Map<String, Object> lmap) {
		return lDAO.getSearchedMemberLListCnt(lmap);
	}

	// ��¥���� �˻��� ������ Ȱ�� ���� ��������
	@Override
	public List<LogDTO> getSearchedMemberLList(Map<String, Object> lmap) {
		return lDAO.getSearchedMemberLList(lmap);
	}

}
