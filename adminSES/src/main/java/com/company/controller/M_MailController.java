package com.company.controller;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.company.dto.EmployeeDTO;
import com.company.dto.MemberDTO;
import com.company.dto.PageDTO;
import com.company.service.ELService;
import com.company.service.EService;
import com.company.service.MService;

@Controller
@Repository
public class M_MailController {
	// ���� �������̽� ���� �ͼ� ���⼭ ����
	@Autowired
	MService Ser_M;
	@Autowired
	EService Ser_E;
	@Autowired
	ELService Ser_EL;

	@Autowired
	private JavaMailSender mailSender;
	@Inject
	HttpSession session;

	// ���� ���� ȸ�� �˻�
	@RequestMapping(value = "/sch_emailG", method = RequestMethod.POST)
	public String SchEmailGeneral(HttpServletRequest request, Model model) {
		// parameter�� string���� �� �����ϱ� �������� �� �̽� map���� �����ߵȴ� �̽�
		Map<String, Object> map = new HashMap<String, Object>();
		PageDTO pgDTO = new PageDTO();
		String pgNum = request.getParameter("pgnum");
		String mKind = request.getParameter("mKind");
		String mId = request.getParameter("mId");
		String mName = request.getParameter("mName");
		if (pgNum == null) // null�̸� �� ó��
			pgNum = "1";
		// int������
		int pgnum = Integer.parseInt(pgNum);

		map.put("mId", '%' + mId + '%');
		map.put("mName", '%' + mName + '%');

		// ��ü �Խñ� ���� ����
		pgDTO.setTotalCnt(Ser_M.GetsSchEmGCnt(map));
		// ���� ������ ��ȣ ����
		pgDTO.setPageNum(pgnum);
		// ������ �Խù� �� ����
		pgDTO.setContentNum(5);
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

		// ȸ�� ��� �ҷ�����
		List<MemberDTO> dtos = Ser_M.GetsSchEmGeneral(map);

		for (int i = 0; i < dtos.size(); i++) {
			dtos.get(i).setM_KIND("�Ϲ�");
		}

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

		String mlink = "sch_emailG";
		String mlink2 = "&mKind=" + mKind + "&mId=" + mId + "&mName=" + mName;
		// �� �Ѱ��ֱ�
		model.addAttribute("mlink", mlink);
		model.addAttribute("mlink2", mlink2);
		model.addAttribute("mKind", mKind);
		model.addAttribute("mId", mId);
		model.addAttribute("mName", mName);
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

		return "/member/m_mail";
	}

	// ���� ���� ���� �˻�
	@RequestMapping("/sch_emailA")
	public String SchEmailAdmin(HttpServletRequest request, Model model) {
		// parameter�� string���� �� �����ϱ� �������� �� �̽� map���� �����ߵȴ� �̽�
		Map<String, Object> map = new HashMap<String, Object>();
		PageDTO pgDTO = new PageDTO();
		String pgNum = request.getParameter("pgnum");
		String mKind = request.getParameter("mKind");
		String mId = request.getParameter("mId");
		String mName = request.getParameter("mName");
		if (pgNum == null) // null�̸� �� ó��
			pgNum = "1";
		// int������
		int pgnum = Integer.parseInt(pgNum);

		map.put("mId", '%' + mId + '%');
		map.put("mName", '%' + mName + '%');

		// ��ü �Խñ� ���� ����
		pgDTO.setTotalCnt(Ser_E.GetsSchEmACnt(map));
		// ���� ������ ��ȣ ����
		pgDTO.setPageNum(pgnum);
		// ������ �Խù� �� ����
		pgDTO.setContentNum(5);
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

		// ȸ�� ��� �ҷ�����
		List<EmployeeDTO> dtos = Ser_E.GetsSchEmAdmin(map);

		for (int i = 0; i < dtos.size(); i++) {
			dtos.get(i).setM_KIND("����");
		}

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

		String mlink = "sch_emailA";
		String mlink2 = "&mKind=" + mKind + "&mId=" + mId + "&mName=" + mName;
		// �� �Ѱ��ֱ�
		model.addAttribute("mlink", mlink);
		model.addAttribute("mlink2", mlink2);
		model.addAttribute("mKind", mKind);
		model.addAttribute("mId", mId);
		model.addAttribute("mName", mName);
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

		return "/member/m_mail";
	}

	// ���� ����
	@RequestMapping(value = "/doSend", method = RequestMethod.POST)
	public String mailSending(HttpServletResponse response, HttpServletRequest request, Model model) throws Exception {
		String setfrom = "tytyjacob5514@kyungmin.ac.kr";
		String tomail = request.getParameter("getMembers"); // �޴� ��� ���
		String title = request.getParameter("inputTitle"); // ����
		String content = request.getParameter("message"); // ����
		String mKind = request.getParameter("mkd"); // ����
		String[] mails = tomail.split(",");
		for(int i = 0; i < mails.length; i++) {
			System.out.println(mails[i]);
		}
		
		EmployeeDTO edto = null;
		MemberDTO mdto = null;
		String mail = "";
		if (mKind.equals("����")) {
			System.out.println(1);
			for (int i = 0; i < mails.length; i++) {
				edto = Ser_E.GetEInfo(mails[i]);
				if (edto != null) {
					mail = edto.getE_EMAIL1() + "@" + edto.getE_EMAIL2();
				}

				try {
					MimeMessage message = mailSender.createMimeMessage();
					MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");

					messageHelper.setFrom(setfrom); // �����»�� �����ϸ� �����۵��� ����
					messageHelper.setTo(mail); // �޴»�� �̸���
					messageHelper.setSubject(title); // ���������� ������ �����ϴ�
					messageHelper.setText(content); // ���� ����

					mailSender.send(message);
				} catch (Exception e) {
					response.setContentType("text/html; charset=UTF-8");
					PrintWriter out = response.getWriter();
					out.println("<script>alert('������ �߻��߽��ϴ�'); history.go(-1);</script>");
					out.flush();
				}
			}
		} else {
			System.out.println(2);
			for (int i = 0; i < mails.length; i++) {
				mdto = Ser_M.GetMInfo(mails[i]);
				if (mdto != null) {
					mail = mdto.getM_EMAIL1() + "@" + mdto.getM_EMAIL2();
				}

				try {
					MimeMessage message = mailSender.createMimeMessage();
					MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");

					messageHelper.setFrom(setfrom); // �����»�� �����ϸ� �����۵��� ����
					messageHelper.setTo(mail); // �޴»�� �̸���
					messageHelper.setSubject(title); // ���������� ������ �����ϴ�
					messageHelper.setText(content); // ���� ����

					mailSender.send(message);
				} catch (Exception e) {
					response.setContentType("text/html; charset=UTF-8");
					PrintWriter out = response.getWriter();
					out.println("<script>alert('������ �߻��߽��ϴ�'); history.go(-1);</script>");
					out.flush();
				}
			}
		}

		// parameter�� string���� �� �����ϱ� �������� �� �̽� map���� �����ߵȴ� �̽�
		Map<String, Object> session_map = new HashMap<String, Object>();
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Calendar time = Calendar.getInstance();
		String now = format1.format(time.getTime());

		session_map.put("el_Id", session.getAttribute("eId"));
		session_map.put("el_Activity", tomail + " ���� ����");
		session_map.put("el_DT", now);

		boolean rslt = Ser_EL.WriteLog(session_map);

		if (!rslt) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('������ �߻��߽��ϴ�'); history.go(-1);</script>");
			out.flush();
		}


		return "redirect:/m_mail";
	}
}
