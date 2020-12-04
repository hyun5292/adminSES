package com.company.dao;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.company.dto.QnaDTO;

@Repository
public class QnaDAO {
	@Autowired
	public SqlSessionTemplate temp;

	// ȸ���� ���� ���� ��������
	public List<QnaDTO> getMemberQList(Map<String, Object> qmap) {
		return temp.selectList("qMap.getMemberQList", qmap);
	}

	// ��ü ������ ���� ���ϱ�
	public int getMemberQListCnt(String mId) {
		return temp.selectOne("qMap.getMemberQListCnt", mId);
	}

	// �˻��� ȸ���� ���� ���� ���� ��������
	public int getSearchedMemberQListCnt(Map<String, Object> qmap) {
		return temp.selectOne("qMap.getSearchedMemberQListCnt", qmap);
	}

	// �˻��� ȸ���� ���� ���� ��������
	public List<QnaDTO> getSearchedMemberQList(Map<String, Object> qmap) {
		return temp.selectList("qMap.getSearchedMemberQList", qmap);
	}

	// �˻��� SNS�� ������� ���� ���� ��������
	public List<QnaDTO> GetSnsQList(Map<String, Object> map) {
		return temp.selectList("qMap.GetSnsQList", map);
	}

	// �˻��� SNS�� ������� ���� ���� ���� ��������
	public int GetSnsQListCnt(Map<String, Object> map) {
		return temp.selectOne("qMap.GetSnsQListCnt", map);
	}

	// ��¥���� �˻��� SNS�� ������� ���� ���� ��������
	public List<QnaDTO> GetSchSnsQList(Map<String, Object> map) {
		return temp.selectList("qMap.GetSchSnsQList", map);
	}

	// ��¥���� �˻��� SNS�� ������� ���� ���� ���� ��������
	public int GetSchSnsQListCnt(Map<String, Object> map) {
		return temp.selectOne("qMap.GetSchSnsQListCnt", map);
	}

	// ����ȭ��� �ֽ� ���� ���� ��������
	public List<QnaDTO> GetQRecentList() {
		return temp.selectList("qMap.GetQRecentList");
	}

	// ���� ��� ��������
	public List<QnaDTO> GetQList(Map<String, Object> map) {
		return temp.selectList("qMap.GetQList", map);
	}

	// ���� ��� ���� ��������
	public int GetQListCnt() {
		return temp.selectOne("qMap.GetQListCnt");
	}

	// ���� ��¥ �˻� �� ��������
	public int GetSchQListCnt(Map<String, Object> map) {
		return temp.selectOne("qMap.GetSchQListCnt", map);
	}

	// ���� ��¥ �˻� ��� ��������
	public List<QnaDTO> GetSchQList(Map<String, Object> map) {
		return temp.selectList("qMap.GetSchQList", map);
	}
}
