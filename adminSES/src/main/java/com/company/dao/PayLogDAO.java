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

	// 회원의 문의 내역 가져오기
	public List<PayLogDTO> getMemberPLList(String mId) {
		return temp.selectList("plMap.getMemberPLList", mId);
	}
	
}
