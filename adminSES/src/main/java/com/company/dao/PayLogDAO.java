package com.company.dao;

import java.util.List;
import java.util.Map;

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
	public List<PayLogDTO> getMemberPLList(Map<String, Object> plmap) {
		return temp.selectList("plMap.getMemberPLList", plmap);
	}

	// ��ü ������ ���� ���ϱ�
	public int getMemberPLListCnt(String mId) {
		return temp.selectOne("plMap.getMemberPLListCnt", mId);
	}

	// �˻��� ȸ���� ���� ���� ��������
	public int getSearchedMemberPLListCnt(Map<String, Object> pmap) {
		return temp.selectOne("plMap.getSearchedMemberPLListCnt", pmap);
	}

	// �˻��� ȸ���� ���� ���� ��������
	public List<PayLogDTO> getSearchedMemberPLList(Map<String, Object> pmap) {
		return temp.selectList("plMap.getSearchedMemberPLList", pmap);
	}

	// �ش� ȸ���� �̹��� ���� ��������
	public int getChkPay(Map<String, Object> map) {
		return temp.selectOne("plMap.getChkPay", map);
	}

	// �˻��� ȸ���� Pay Ƚ�� ���ϱ�
	public int getMPayList(String mId) {
		return temp.selectOne("plMap.getMPayList", mId);
	}

	// ���Ἥ�� ���� ó��
	public boolean MakePay(Map<String, Object> map) {
		int rs = temp.insert("plMap.MakePay", map);
		if (rs == 1)
			return true;
		else
			return false;
	}

	// ���Ἥ�� �̳� ó��
	public boolean MakeNoPay(Map<String, Object> map) {
		int rs = temp.delete("plMap.MakeNoPay", map);
		if (rs == 1)
			return true;
		else
			return false;
	}
}
