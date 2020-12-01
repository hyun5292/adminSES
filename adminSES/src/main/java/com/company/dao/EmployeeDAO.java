package com.company.dao;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.company.dto.EmployeeDTO;
import com.company.dto.MemberDTO;

@Repository
public class EmployeeDAO {
	@Autowired
	public SqlSessionTemplate temp;

	// ���� �˻� ���� ��� �ҷ�����
	public List<EmployeeDTO> GetSchEList(Map<String, Object> map) {
		return temp.selectList("eMap.GetSchEList", map);
	}

	// ���� �˻� ���� ������ ���� ���ϱ�
	public int GetSchPageCnt(Map<String, Object> map) {
		return temp.selectOne("eMap.GetSchPageCnt", map);
	}

	// ���� ���� ��������
	public EmployeeDTO GetEInfo(String mId) {
		return temp.selectOne("eMap.GetEInfo", mId);
	}

	// ���� ������ ���� �ο�
	public boolean MakeDoAuth(String mId) {
		int rs = temp.update("eMap.MakeDoAuth", mId);
		if (rs == 1)
			return true;
		else
			return false;
	}

	// ���� ������ ���� ����
	public boolean MakeDontAuth(String mId) {
		int rs = temp.update("eMap.MakeDontAuth", mId);
		if (rs == 1)
			return true;
		else
			return false;
	}

	// �̸��� �˻��� ���� �� ���ϱ�
	public int GetsSchEmACnt(Map<String, Object> map) {
		return temp.selectOne("eMap.GetsSchEmACnt", map);
	}

	// �̸��� �˻��� ���� ��� ���ϱ�
	public List<EmployeeDTO> GetsSchEmAdmin(Map<String, Object> map) {
		return temp.selectList("eMap.GetsSchEmAdmin", map);
	}
}
