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
}
