package com.company.dao;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.company.dto.MemberDTO;

@Repository
public class MemberDAO {
	@Autowired
	public SqlSessionTemplate temp;

	// ȸ�� ��� �ҷ�����
	public List<MemberDTO> GetMList(Map<String, Object> map) {
		return temp.selectList("mMap.GetMList", map);
	}

	// ��ü ������ ���� ���ϱ�
	public int ListCnt() {
		return temp.selectOne("mMap.ListCnt");
	}

	// ���� �˻� ȸ�� ��� �ҷ�����
	public List<MemberDTO> GetSchMList(Map<String, Object> map) {
		return temp.selectList("mMap.GetSchMList", map);
	}

	// ���� �˻� ȸ�� ������ ���� ���ϱ�
	public int GetSchPageCnt(Map<String, Object> map) {
		return temp.selectOne("mMap.GetSchPageCnt", map);
	}

	// �˻��� ������ ���� ���ϱ�
	public int SearchedListCnt(Map<String, Object> map) {
		return temp.selectOne("mMap.SearchedListCnt", map);
	}

	// ȸ���� ȸ�� �з� ��������
	public String GetMKind(String mId) {
		return temp.selectOne("mMap.GetMKind", mId);
	}

	// ȸ�� ���� ��������
	public MemberDTO GetMInfo(String mId) {
		return temp.selectOne("mMap.GetMInfo", mId);
	}

	// �Ϲ� ȸ�� �� ���ϱ�
	public int GeneralCnt() {
		return temp.selectOne("mMap.GeneralCnt");
	}

	// Ż���� �Ϲ� ȸ�� �� ���ϱ�
	public int GeneralNotUseCnt() {
		return temp.selectOne("mMap.GeneralNotUseCnt");
	}
}