package com.company.dao;

import java.util.List;

import javax.inject.Inject;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.company.dto.PayLogDTO;
import com.company.dto.QnaDTO;

@Repository
public class PayLogDAO {
	@Autowired
	public SqlSessionTemplate temp;

	// ȸ���� ���� ���� ��������
	public List<PayLogDTO> getMemberPLList(String mId) {
		return temp.selectList("plMap.getMemberPLList", mId);
	}
	
}
