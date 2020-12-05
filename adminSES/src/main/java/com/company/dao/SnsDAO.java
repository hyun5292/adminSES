package com.company.dao;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.company.dto.SnsDTO;

@Repository
public class SnsDAO {
	@Autowired
	public SqlSessionTemplate temp;

	// ���� �˻� SNS�� ����� ��� �ҷ�����
	public List<SnsDTO> GetSchSList(Map<String, Object> map) {
		return temp.selectList("snsMap.GetSchSList", map);
	}

	// ���� �˻� SNS�� ����� ������ ���� ���ϱ�
	public int GetSchPageCnt(Map<String, Object> map) {
		return temp.selectOne("snsMap.GetSchPageCnt", map);
	}

	// ���� �˻� SNS�� ����� ���� �ҷ�����
	public SnsDTO GetSInfo(Map<String, Object> map) {
		return temp.selectOne("snsMap.GetSInfo", map);
	}

	// ���ο� SNS ����� ���
	public boolean NewSnsMember(Map<String, Object> map) {
		int rs = temp.insert("snsMap.NewSnsMember", map);
		if (rs == 1)
			return true;
		else
			return false;
	}

	//  SNS ����� ���� ����
	public boolean ModifySnsMember(Map<String, Object> map) {
		int rs = temp.update("snsMap.ModifySnsMember", map);
		if (rs == 1)
			return true;
		else
			return false;
	}
	// SNS�� ����� ����
	public boolean DeleteSNS(Map<String, Object> map) {
		int rs = temp.delete("snsMap.DeleteSNS", map);
		System.out.println(rs);
		if (rs == 1)
			return true;
		else
			return false;
	}

	// ���� ����� ������ ����
	public boolean ChgNowSns(Map<String, Object> map) {
		int rs = temp.update("snsMap.ChgNowSns", map);
		if (rs == 1)
			return true;
		else
			return false;
	}

	// ���� ��ϵ� �ٸ� SNS ����ڰ� �ִ��� Ȯ��
	public int ChkTodaySNS(Map<String, Object> map) {
		return temp.selectOne("snsMap.ChkTodaySNS", map);
	}

	// sns�� ����� ��� �ҷ�����
	public List<SnsDTO> GetSnsList() {
		return temp.selectList("snsMap.GetSnsList");
	}
	
}
