package com.company.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.company.dto.EmpLogDTO;
import com.company.dto.EmployeeDTO;
import com.company.dto.PageDTO;
import com.company.service.ELService;
import com.company.service.EService;

@Controller
@Repository
public class M_AdminController {
	@Autowired
	EService Ser_E;
	@Autowired
	ELService Ser_EL;

	@Inject
	HttpSession session;

	// �α���
	@RequestMapping(value = "/doLogin", method = RequestMethod.POST)
	public String MLogin(HttpServletResponse response, HttpServletRequest request, RedirectAttributes attr)
			throws Exception {
		EmployeeDTO dto = Ser_E.ELogin(request);

		if (dto == null) { // �ش� ������ �������� ���� ���
			session.setAttribute("eId", null);
			session.setAttribute("ePw", null);
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('�������� �ʴ� ȸ���Դϴ�!!'); history.go(-1); </script>");
			out.flush();
		} else if (!dto.getM_PW().equals(request.getParameter("ePw"))) { // ��й�ȣ�� ��ġ���� ���� ���
			session.setAttribute("eId", null);
			session.setAttribute("ePw", null);
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('��й�ȣ�� Ʋ�Ƚ��ϴ�!!'); history.go(-1); </script>");
			out.flush();
		} else { // ����
			session.setAttribute("eId", dto.getM_ID());
			session.setAttribute("ePw", dto.getM_PW());

			// parameter�� string���� �� �����ϱ� �������� �� �̽� map���� �����ߵȴ� �̽�
			Map<String, Object> session_map = new HashMap<String, Object>();
			SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			Calendar time = Calendar.getInstance();
			String now = format1.format(time.getTime());

			session_map.put("el_Id", session.getAttribute("eId"));
			session_map.put("el_Activity", "�α���");
			session_map.put("el_DT", now);

			boolean rslt = Ser_EL.WriteLog(session_map);

			if (!rslt) {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>alert('������ �߻��߽��ϴ�'); history.go(-1);</script>");
				out.flush();
			}

			return "redirect:/main";
		}

		return "/login";
	}

	// �α׾ƿ�
	@RequestMapping("/logout")
	public String MLogout(HttpServletResponse response, HttpServletRequest request, RedirectAttributes attr)
			throws Exception {

		// parameter�� string���� �� �����ϱ� �������� �� �̽� map���� �����ߵȴ� �̽�
		Map<String, Object> session_map = new HashMap<String, Object>();
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Calendar time = Calendar.getInstance();
		String now = format1.format(time.getTime());

		session_map.put("el_Id", session.getAttribute("eId"));
		session_map.put("el_Activity", "�α׾ƿ�");
		session_map.put("el_DT", now);

		boolean rslt = Ser_EL.WriteLog(session_map);

		if (!rslt) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('������ �߻��߽��ϴ�'); history.go(-1);</script>");
			out.flush();
		}

		session.removeAttribute("user");
		session.invalidate();

		return "redirect:/main";
	}

	// ��й�ȣ ����
	@RequestMapping(value = "/doChgPW", method = RequestMethod.POST)
	public String ChagePWD(HttpServletResponse response, HttpServletRequest request, Model model) throws IOException {
		// parameter�� string���� �� �����ϱ� �������� �� �̽� map���� �����ߵȴ� �̽�
		Map<String, Object> map = new HashMap<String, Object>();

		if (!request.getParameter("nowPW").equals(session.getAttribute("ePw").toString())) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('�� ��й�ȣ�� ��ġ���� �ʽ��ϴ�!!');</script>");
			out.flush();
			
			return "modifypwd";
		} else {
			map.put("eId", session.getAttribute("eId").toString());
			map.put("ePw", request.getParameter("newPW"));

			boolean result = Ser_E.ChagePWD(map);

			if (result) {
				session.removeAttribute("user");
				session.invalidate();

				// parameter�� string���� �� �����ϱ� �������� �� �̽� map���� �����ߵȴ� �̽�
				Map<String, Object> session_map = new HashMap<String, Object>();
				SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				Calendar time = Calendar.getInstance();
				String now = format1.format(time.getTime());

				session_map.put("el_Id", session.getAttribute("eId"));
				session_map.put("el_Activity", session.getAttribute("eId") + " ��й�ȣ ����");
				session_map.put("el_DT", now);

				boolean rslt = Ser_EL.WriteLog(session_map);

				if (!rslt) {
					response.setContentType("text/html; charset=UTF-8");
					PrintWriter out = response.getWriter();
					out.println("<script>alert('������ �߻��߽��ϴ�'); history.go(-1);</script>");
					out.flush();
				}
				
				return "redirect:/login";
			} else {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>alert('������ �߻��Ͽ����ϴ�!!'); history.go(-1);</script>");
				out.flush();

				session.removeAttribute("user");
				session.invalidate();
				return "redirect:/login";
			}
		}
	}

	// ���� Ȱ�� ���� ��¥ �˻�
	@RequestMapping("/el_sch")
	public String EmpLSearchedList(HttpServletRequest request, Model model) {
		// parameter�� string���� �� �����ϱ� �������� �� �̽� map���� �����ߵȴ� �̽�
		Map<String, Object> map = new HashMap<String, Object>();
		String mId = request.getParameter("mId");
		String pgNum = request.getParameter("pgnum");
		String StartDT = request.getParameter("StartDT");
		String EndDT = request.getParameter("EndDT").split(";")[0];
		PageDTO pgDTO = new PageDTO();
		if (pgNum == null) // null�̸� �� ó��
			pgNum = "1";
		// int������
		int pgnum = Integer.parseInt(pgNum);

		// ���� ���� ��������
		EmployeeDTO dto = Ser_E.GetEInfo(mId);

		if (dto == null) {
			dto = new EmployeeDTO();
		} else {
			dto.setE_ENTER_DT(dto.getE_ENTER_DT().split(" ")[0]);
			dto.setE_RESIGN_DT(dto.getE_RESIGN_DT().split(" ")[0]);
		}

		map.put("mId", mId);
		map.put("StartDT", StartDT);
		map.put("EndDT", EndDT);

		// ��ü �Խñ� ���� ����
		pgDTO.setTotalCnt(Ser_EL.GetSchELPageCnt(map));
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
		List<EmpLogDTO> dtos = Ser_EL.GetSchELList(map);

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
		model.addAttribute("elLink", "el_sch?mId=" + mId + "&StartDT=" + StartDT + "&EndDT=" + EndDT);
		model.addAttribute("StartDT", StartDT);
		model.addAttribute("EndDT", EndDT);
		model.addAttribute("dto", dto);
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

		return "/member/m_admin";
	}

	// ���� ������ ���� �ο�
	@RequestMapping("/doAuth")
	public String DoAuth(HttpServletResponse response, HttpServletRequest request, Model model) throws IOException {
		String mId = request.getParameter("mId");
		boolean result;

		result = Ser_E.MakeDoAuth(mId);

		if (!result) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('������ �߻��߽��ϴ�'); history.go(-1);</script>");
			out.flush();
		}

		// parameter�� string���� �� �����ϱ� �������� �� �̽� map���� �����ߵȴ� �̽�
		Map<String, Object> session_map = new HashMap<String, Object>();
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Calendar time = Calendar.getInstance();
		String now = format1.format(time.getTime());

		session_map.put("el_Id", session.getAttribute("eId"));
		session_map.put("el_Activity", mId + " ������ ���� �ο�");
		session_map.put("el_DT", now);

		boolean rslt = Ser_EL.WriteLog(session_map);

		if (!rslt) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('������ �߻��߽��ϴ�'); history.go(-1);</script>");
			out.flush();
		}

		return "redirect:/m_search";
	}

	// ���� ������ ���� ����
	@RequestMapping("/dontAuth")
	public String DontAuth(HttpServletResponse response, HttpServletRequest request, Model model) throws IOException {
		String mId = request.getParameter("mId");
		boolean result;

		result = Ser_E.MakeDontAuth(mId);

		if (!result) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('������ �߻��߽��ϴ�'); history.go(-1);</script>");
			out.flush();
		}

		// parameter�� string���� �� �����ϱ� �������� �� �̽� map���� �����ߵȴ� �̽�
		Map<String, Object> session_map = new HashMap<String, Object>();
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Calendar time = Calendar.getInstance();
		String now = format1.format(time.getTime());

		session_map.put("el_Id", session.getAttribute("eId"));
		session_map.put("el_Activity", mId + " ������ ���� ����");
		session_map.put("el_DT", now);

		boolean rslt = Ser_EL.WriteLog(session_map);

		if (!rslt) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('������ �߻��߽��ϴ�'); history.go(-1);</script>");
			out.flush();
		}

		return "redirect:/m_search";
	}
}
