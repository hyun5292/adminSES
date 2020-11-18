package com.company.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
import org.springframework.web.bind.annotation.RequestMethod;

import com.company.dto.PageDTO;
import com.company.dto.QnaDTO;
import com.company.dto.SnsDTO;
import com.company.service.MService;
import com.company.service.QService;
import com.company.service.SService;

@Controller
@Repository
public class M_SnsController {
	// ���� �������̽� ���� �ͼ� ���⼭ ����
	@Autowired
	MService Ser_M;
	@Autowired
	QService Ser_Q;
	@Autowired
	SService Ser_S;

	// SNS�� ����� ���� ���� ��¥ �˻�
	@RequestMapping("/sns_qsch")
	public String SnsSchQnaList(HttpServletRequest request, Model model) {
		// parameter�� string���� �� �����ϱ� �������� �� �̽� map���� �����ߵȴ� �̽�
		Map<String, Object> map = new HashMap<String, Object>();
		PageDTO pgDTO = new PageDTO();
		String pgNum = request.getParameter("pgnum");
		String mId = request.getParameter("mId");
		String mKind = request.getParameter("mKind");
		String StartDT = request.getParameter("StartDT");
		String EndDT = request.getParameter("EndDT").split(";")[0];
		String formAction = "";
		float gcnt = Ser_M.PageCnt();
		float serviceUsercnt = 0;
		float userAvg = 0;
		if (pgNum == null) // null�̸� �� ó��
			pgNum = "1";
		// int������
		int pgnum = Integer.parseInt(pgNum);

		if (mId.equals("Naver")) {
			serviceUsercnt = Ser_M.GetM_NCHK();
		} else if (mId.equals("Facebook")) {
			serviceUsercnt = Ser_M.GetM_FBCHK();
		} else if (mId.equals("Google")) {
			serviceUsercnt = Ser_M.GetM_GCHK();
		} else if (mId.equals("Kakaotalk")) {
			serviceUsercnt = Ser_M.GetM_KTCHK();
		}

		userAvg = (float) (Math.round((serviceUsercnt / gcnt) * 100) / 100.0);

		map.put("mId", mId);
		map.put("sStartDT", "%" + mKind + "%");

		// SNS�� ����� ���� ��������
		SnsDTO dto = Ser_S.GetSInfo(map);

		map = new HashMap<String, Object>();

		if (dto == null) {
			dto = new SnsDTO();
			formAction = "newSns";
		} else {
			dto.setS_START_DT(dto.getS_START_DT().split(" ")[0]);
			dto.setS_END_DT(dto.getS_END_DT().split(" ")[0]);
			formAction = "modifySns";
		}

		String mId2 = "%" + dto.getM_ID() + "-" + dto.getS_START_DT() + "%";
		map.put("mId", mId2);
		map.put("StartDT", StartDT);
		map.put("EndDT", EndDT);
		// ��ü �Խñ� ���� ����
		pgDTO.setTotalCnt(Ser_Q.GetSchSnsQListCnt(map));
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

		// ���� Ȱ�� ��� �ҷ�����
		List<QnaDTO> dtos = Ser_Q.GetSchSnsQList(map);

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

		model.addAttribute("snsLink", "m_sns?mId=" + mId + "&StartDT=" + StartDT + "&EndDT=" + EndDT);
		model.addAttribute("formAction", formAction);
		model.addAttribute("dto", dto);
		model.addAttribute("gcnt", (int) gcnt);
		model.addAttribute("svUsercnt", (int) serviceUsercnt);
		model.addAttribute("userAvg", userAvg);
		model.addAttribute("dtos", dtos);
		model.addAttribute("before", pgDTO.getStartPage() - 1);
		model.addAttribute("after", pgDTO.getEndPage() + 1);
		model.addAttribute("prev", prev);
		model.addAttribute("pg", pg);
		model.addAttribute("next", next);
		if (pgDTO.getTotalCnt() % pgDTO.getContentNum() > 0)
			model.addAttribute("last", pgDTO.getTotalCnt() / pgDTO.getContentNum() + 1);
		else
			model.addAttribute("last", pgDTO.getTotalCnt() / pgDTO.getContentNum());

		return "/member/m_sns";
	}

	// ���ο� SNS ����� ���
	@RequestMapping(value = "/newSns", method = RequestMethod.POST)
	public String NewSnsMember(HttpServletResponse response, HttpServletRequest request, Model model)
			throws IOException {
		// parameter�� string���� �� �����ϱ� �������� �� �̽� map���� �����ߵȴ� �̽�
		Map<String, Object> map = new HashMap<String, Object>();
		boolean result = false;
		SimpleDateFormat format1 = new SimpleDateFormat ( "yyyy-MM-dd");
				
		Calendar time = Calendar.getInstance();
		       
		String now = format1.format(time.getTime());

		map.put("exitDT", now);
		map.put("sKind", request.getParameter("sKind"));
		
		// ���� ����� ������ ����
		result = Ser_S.ChgNowSns(map);
		
		map = new HashMap<String, Object>();

		if (!result) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('���� ��� ���� ������� �������� ���� �������ּ���!!'); history.go(-1);</script>");
			out.flush();
		}

		map.put("sName", request.getParameter("sName"));
		map.put("sKind", request.getParameter("sKind"));
		map.put("sTel1", request.getParameter("sTel1"));
		map.put("sTel2", request.getParameter("sTel2"));
		map.put("sTel3", request.getParameter("sTel3"));
		map.put("sDept", request.getParameter("sDept"));
		map.put("inDT", request.getParameter("inDT"));
		if (request.getParameter("inDT").equals(request.getParameter("exitDT"))) {
			map.put("exitDT", "0000-00-00 00:00:00");
		} else {
			map.put("exitDT", request.getParameter("exitDT"));
		}

		result = Ser_S.NewSnsMember(map);

		if (!result) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('������ �߻��߽��ϴ�'); history.go(-1);</script>");
			out.flush();
		}

		return "redirect:/" + "m_search";
	}

	// SNS ����� ���� ����
	@RequestMapping(value = "/modifySns", method = RequestMethod.POST)
	public String ModifySnsMember(HttpServletResponse response, HttpServletRequest request, Model model)
			throws IOException {
		// parameter�� string���� �� �����ϱ� �������� �� �̽� map���� �����ߵȴ� �̽�
		Map<String, Object> map = new HashMap<String, Object>();
		boolean result = false;

		map.put("sName", request.getParameter("sName"));
		map.put("sKind", request.getParameter("sKind2"));
		map.put("sTel1", request.getParameter("sTel1"));
		map.put("sTel2", request.getParameter("sTel2"));
		map.put("sTel3", request.getParameter("sTel3"));
		map.put("sDept", request.getParameter("sDept"));
		map.put("inDT", request.getParameter("inDT2"));
		if (request.getParameter("inDT2").equals(request.getParameter("exitDT"))) {
			map.put("exitDT", "0000-00-00 00:00:00");
		} else {
			map.put("exitDT", request.getParameter("exitDT"));
		}
		map.put("sStartDT", "%"+request.getParameter("inDT2")+"%");

		result = Ser_S.ModifySnsMember(map);

		if (!result) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('������ �߻��߽��ϴ�'); history.go(-1);</script>");
			out.flush();
		}

		return "redirect:/" + "m_search";
	}

	// SNS�� ����� ����
	@RequestMapping("/delete_sns")
	public String DeleteSns(HttpServletResponse response, HttpServletRequest request, Model model)
			throws IOException {
		// parameter�� string���� �� �����ϱ� �������� �� �̽� map���� �����ߵȴ� �̽�
		Map<String, Object> map = new HashMap<String, Object>();
		String sKind = request.getParameter("sKind");
		String inDT = request.getParameter("inDT");
		boolean result = false;

		map.put("sKind", sKind);
		map.put("inDT", "%"+inDT+"%");
		
		result = Ser_S.DeleteSNS(map);
		
		if (!result) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('������ �߻��߽��ϴ�'); history.go(-1);</script>");
			out.flush();
		}

		return "redirect:/" + "m_search";
	}
}
