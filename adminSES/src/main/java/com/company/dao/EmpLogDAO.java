package com.company.dao;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.company.dto.EmpLogDTO;

@Repository
public class EmpLogDAO {
	@Autowired
	public SqlSessionTemplate temp;

	// ȸ���� Ȱ�� �α� ��ü ���� ��������
	public int GetELPageCnt(String mId) {
		return temp.selectOne("elMap.GetELPageCnt", mId);
	}

	// ȸ���� Ȱ�� �α� ��ü ��������
	public List<EmpLogDTO> GetELList(Map<String, Object> map) {
		return temp.selectList("elMap.GetELList", map);
	}

	// ��¥���� �˻��� ȸ���� Ȱ�� �α� ��ü ���� ��������
	public int GetSchELPageCnt(Map<String, Object> map) {
		return temp.selectOne("elMap.GetSchELPageCnt", map);
	}

	// ��¥���� �˻��� ȸ���� Ȱ�� �α� ��ü ��������
	public List<EmpLogDTO> GetSchELList(Map<String, Object> map) {
		return temp.selectList("elMap.GetSchELList", map);
	}
	
}
