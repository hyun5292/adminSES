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
	
}
