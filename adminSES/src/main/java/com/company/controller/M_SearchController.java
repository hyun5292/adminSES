package com.company.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.company.dto.MemberDTO;
import com.company.dto.PageDTO;
import com.company.service.MService;

@Controller
@Repository
public class M_SearchController {
	// ���� �������̽� ���� �ͼ� ���⼭ ����
	@Autowired
	MService Ser_M;

	// ȸ�� ���� �˻�
	@RequestMapping("/search_m")
	public String SearchedList(HttpServletRequest request, Model model) {
		// parameter�� string���� �� �����ϱ� �������� �� �̽� map���� �����ߵȴ� �̽�
		Map<String, Object> map = new HashMap<String, Object>();
		PageDTO pgDTO = new PageDTO();
		String mKind = request.getParameter("mKind");
		String mId = request.getParameter("mId");
		String mName = request.getParameter("mName");
		String pgNum = request.getParameter("pgnum");
		if (pgNum == null) // null�̸� �� ó��
			pgNum = "1";
		// int������
		int pgnum = Integer.parseInt(pgNum);

		if ((mKind != null) || (mKind != "")) {
			map.put("mKind", mKind);
		}
		if ((mId != null) || (mId != "")) {
			map.put("mId", "%" + mId + "%");
		}
		if ((mName != null) || (mName != "")) {
			map.put("mName", "%" + mName + "%");
		}

		// ��ü �Խñ� ���� ����
		pgDTO.setTotalCnt(Ser_M.SearchedListCnt(map));
		// ���� ������ ��ȣ ����
		pgDTO.setPageNum(pgnum);
		// ������ �Խù� �� ����
		pgDTO.setContentNum(10);
		// ���� ������ ��� ����
		pgDTO.setCurBlock(pgnum);
		// ������ ��� ��ȣ ����
		pgDTO.setLastBlock(pgDTO.getTotalCnt());
		// ���� ȭ��ǥ ǥ�� ����
		pgDTO.prevnext(pgnum);
		// ���� ������ ����
		pgDTO.setStartPage(pgDTO.getCurBlock());
		// ������ ������ ����
		pgDTO.setEndPage(pgDTO.getLastBlock(), pgDTO.getCurBlock());

		map.put("startNum", (pgnum - 1) * pgDTO.getContentNum());
		map.put("ContentNum", pgDTO.getContentNum());

		List<MemberDTO> dtos = Ser_M.GetSearchedMList(map);

		int first = (pgnum - 1) * pgDTO.getContentNum() + 1;
		int last = first + pgDTO.getContentNum();
		int j = 0;
		// �� �Խù� ��ȣ
		for (int i = first; i < last; i++) {
			if (i <= pgDTO.getTotalCnt()) {
				dtos.get(j).setNUM(i);
				j++;
			}
		}

		String prev = "", next = ""; // <, >

		if (pgDTO.isPrev()) { // ���� ����� �����ϴ°�
			prev = "<";
		}
		if (pgDTO.isNext()) { // ���� ����� �����ϴ°�
			next = ">";
		}

		// �Ѿ�� ��µ� ������ ��ȣ��
		int[] pg;
		if (dtos.size() == 0) {
			pg = new int[1];
		} else {
			pg = new int[(pgDTO.getEndPage() - pgDTO.getStartPage()) + 1];
		}

		// ������ �ڹٽ�ũ��Ʈ �Ἥ ����ߵǴµ� ���� ���� �� �����ͼ� ��ġ�ؾ� �ȴٱ淡
		// �׳� ���⼭ �� ����ؼ� �Ѱ��ֱ�
		j = 0;
		for (int i = pgDTO.getStartPage(); i < pgDTO.getStartPage() + pgDTO.getContentNum(); i++) {
			if (pg.length > j)
				pg[j] = i;
			j++;
		}

		// �� �Ѱ��ֱ�
		model.addAttribute("dtos", dtos);
		model.addAttribute("mKind", mKind);
		model.addAttribute("mId", mId);
		model.addAttribute("mName", mName);
		model.addAttribute("before", pgDTO.getStartPage() - 1);
		model.addAttribute("after", pgDTO.getEndPage() + 1);
		model.addAttribute("prev", prev);
		model.addAttribute("pg", pg);
		model.addAttribute("next", next);
		if (pgDTO.getTotalCnt() % pgDTO.getContentNum() > 0)
			model.addAttribute("last", pgDTO.getTotalCnt() / pgDTO.getContentNum() + 1);
		else
			model.addAttribute("last", pgDTO.getTotalCnt() / pgDTO.getContentNum());

		return "/member/m_search";
	}
	
	// � �з��� �����ΰ�
	@RequestMapping("/chklistmkind")
	public String CheckListmKind(HttpServletRequest request, Model model) {
		String result = "m_search";
		String mId = request.getParameter("mId");
		String mKind = Ser_M.GetMKind(mId);
		
		if(mKind.equals("�Ϲ�")) {
			result = "m_general";
		} else if(mKind.equals("����")) {
			result = "m_admin";
		} else if(mKind.equals("SNS��")) {
			result = "m_sns";
		}
		
		model.addAttribute("mId", mId);
		
		return "redirect:/" + result;
	}
}
