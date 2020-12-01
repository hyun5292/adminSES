package com.company.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.company.dto.LogDTO;

@Repository
public class LogDAO {
	@Autowired
	public SqlSessionTemplate temp;

	// �ش� ��� �α� ��� �ҷ�����
	public List<LogDTO> getMemberLogList(Map<String, Object> map) {
		return temp.selectList("lMap.getMemberLogList", map);
	}

	// �ش� ��� �α� ��� ���� �ҷ�����
	public int getMemberLListCnt(String mId) {
		return temp.selectOne("lMap.getMemberLListCnt", mId);
	}

	// ��¥���� �˻��� ������ Ȱ�� ���� ���� ��������
	public int getSearchedMemberLListCnt(Map<String, Object> lmap) {
		return temp.selectOne("lMap.getSearchedMemberLListCnt", lmap);
	}

	// ��¥���� �˻��� ������ Ȱ�� ���� ��������
	public List<LogDTO> getSearchedMemberLList(Map<String, Object> lmap) {
		return temp.selectList("lMap.getSearchedMemberLList", lmap);
	}
	
	
}
