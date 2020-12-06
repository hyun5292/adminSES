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
	// 서비스 인터페이스 갖고 와서 여기서 정의
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

	// 메일 관련 회원 검색
	@RequestMapping(value = "/sch_emailG", method = RequestMethod.POST)
	public String SchEmailGeneral(HttpServletRequest request, Model model) {
		// parameter로 string으로 걍 보내니까 오류난다 이 똬식 map으로 보내야된대 똬식
		Map<String, Object> map = new HashMap<String, Object>();
		PageDTO pgDTO = new PageDTO();
		String pgNum = request.getParameter("pgnum");
		String mKind = request.getParameter("mKind");
		String mId = request.getParameter("mId");
		String mName = request.getParameter("mName");
		if (pgNum == null) // null이면 맨 처음
			pgNum = "1";
		// int형으로
		int pgnum = Integer.parseInt(pgNum);

		map.put("mId", '%' + mId + '%');
		map.put("mName", '%' + mName + '%');

		// 전체 게시글 개수 설정
		pgDTO.setTotalCnt(Ser_M.GetsSchEmGCnt(map));
		// 현재 페이지 번호 설정
		pgDTO.setPageNum(pgnum);
		// 보여줄 게시물 수 설정
		pgDTO.setContentNum(5);
		// 현재 페이지 블록 설정
		pgDTO.setCurBlock(pgnum);
		// 마지막 블록 번호 설정
		pgDTO.setLastBlock(pgDTO.getTotalCnt());
		// 이전 화살표 표시 여부
		pgDTO.prevnext(pgnum);
		// 시작 페이지 설정
		pgDTO.setStartPage(pgDTO.getCurBlock());
		// 마지막 페이지 설정
		pgDTO.setEndPage(pgDTO.getLastBlock(), pgDTO.getCurBlock());

		map.put("startNum", (pgnum - 1) * pgDTO.getContentNum());
		map.put("ContentNum", pgDTO.getContentNum());

		// 회원 목록 불러오기
		List<MemberDTO> dtos = Ser_M.GetsSchEmGeneral(map);

		for (int i = 0; i < dtos.size(); i++) {
			dtos.get(i).setM_KIND("일반");
		}

		int first = (pgnum - 1) * pgDTO.getContentNum() + 1;
		int last = first + pgDTO.getContentNum();
		int j = 0;
		// 각 게시물 번호
		for (int i = first; i < last; i++) {
			if (i <= pgDTO.getTotalCnt()) {
				dtos.get(j).setNUM(i);
				j++;
			}
		}

		String prev = "", next = ""; // <, >

		if (pgDTO.isPrev()) { // 이전 블록이 존재하는가
			prev = "<";
		}
		if (pgDTO.isNext()) { // 다음 블록이 존재하는가
			next = ">";
		}

		// 넘어가서 출력될 페이지 번호들
		int[] pg;
		if (dtos.size() == 0) {
			pg = new int[1];
		} else {
			pg = new int[(pgDTO.getEndPage() - pgDTO.getStartPage()) + 1];
		}

		// 원래는 자바스크립트 써서 해줘야되는데 무슨 파일 또 가져와서 설치해야 된다길래
		// 그냥 여기서 값 계산해서 넘겨주기
		j = 0;
		for (int i = pgDTO.getStartPage(); i < pgDTO.getStartPage() + pgDTO.getContentNum(); i++) {
			if (pg.length > j)
				pg[j] = i;
			j++;
		}

		String mlink = "sch_emailG";
		String mlink2 = "&mKind=" + mKind + "&mId=" + mId + "&mName=" + mName;
		// 값 넘겨주기
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

	// 메일 관련 직원 검색
	@RequestMapping("/sch_emailA")
	public String SchEmailAdmin(HttpServletRequest request, Model model) {
		// parameter로 string으로 걍 보내니까 오류난다 이 똬식 map으로 보내야된대 똬식
		Map<String, Object> map = new HashMap<String, Object>();
		PageDTO pgDTO = new PageDTO();
		String pgNum = request.getParameter("pgnum");
		String mKind = request.getParameter("mKind");
		String mId = request.getParameter("mId");
		String mName = request.getParameter("mName");
		if (pgNum == null) // null이면 맨 처음
			pgNum = "1";
		// int형으로
		int pgnum = Integer.parseInt(pgNum);

		map.put("mId", '%' + mId + '%');
		map.put("mName", '%' + mName + '%');

		// 전체 게시글 개수 설정
		pgDTO.setTotalCnt(Ser_E.GetsSchEmACnt(map));
		// 현재 페이지 번호 설정
		pgDTO.setPageNum(pgnum);
		// 보여줄 게시물 수 설정
		pgDTO.setContentNum(5);
		// 현재 페이지 블록 설정
		pgDTO.setCurBlock(pgnum);
		// 마지막 블록 번호 설정
		pgDTO.setLastBlock(pgDTO.getTotalCnt());
		// 이전 화살표 표시 여부
		pgDTO.prevnext(pgnum);
		// 시작 페이지 설정
		pgDTO.setStartPage(pgDTO.getCurBlock());
		// 마지막 페이지 설정
		pgDTO.setEndPage(pgDTO.getLastBlock(), pgDTO.getCurBlock());

		map.put("startNum", (pgnum - 1) * pgDTO.getContentNum());
		map.put("ContentNum", pgDTO.getContentNum());

		// 회원 목록 불러오기
		List<EmployeeDTO> dtos = Ser_E.GetsSchEmAdmin(map);

		for (int i = 0; i < dtos.size(); i++) {
			dtos.get(i).setM_KIND("직원");
		}

		int first = (pgnum - 1) * pgDTO.getContentNum() + 1;
		int last = first + pgDTO.getContentNum();
		int j = 0;
		// 각 게시물 번호
		for (int i = first; i < last; i++) {
			if (i <= pgDTO.getTotalCnt()) {
				dtos.get(j).setNUM(i);
				j++;
			}
		}

		String prev = "", next = ""; // <, >

		if (pgDTO.isPrev()) { // 이전 블록이 존재하는가
			prev = "<";
		}
		if (pgDTO.isNext()) { // 다음 블록이 존재하는가
			next = ">";
		}

		// 넘어가서 출력될 페이지 번호들
		int[] pg;
		if (dtos.size() == 0) {
			pg = new int[1];
		} else {
			pg = new int[(pgDTO.getEndPage() - pgDTO.getStartPage()) + 1];
		}

		// 원래는 자바스크립트 써서 해줘야되는데 무슨 파일 또 가져와서 설치해야 된다길래
		// 그냥 여기서 값 계산해서 넘겨주기
		j = 0;
		for (int i = pgDTO.getStartPage(); i < pgDTO.getStartPage() + pgDTO.getContentNum(); i++) {
			if (pg.length > j)
				pg[j] = i;
			j++;
		}

		String mlink = "sch_emailA";
		String mlink2 = "&mKind=" + mKind + "&mId=" + mId + "&mName=" + mName;
		// 값 넘겨주기
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

	// 메일 전송
	@RequestMapping(value = "/doSend", method = RequestMethod.POST)
	public String mailSending(HttpServletResponse response, HttpServletRequest request, Model model) throws Exception {
		String setfrom = "tytyjacob5514@kyungmin.ac.kr";
		String tomail = request.getParameter("getMembers"); // 받는 사람 명단
		String title = request.getParameter("inputTitle"); // 제목
		String content = request.getParameter("message"); // 내용
		String mKind = request.getParameter("mkd"); // 종류
		String[] mails = tomail.split(",");
		for(int i = 0; i < mails.length; i++) {
			System.out.println(mails[i]);
		}
		
		EmployeeDTO edto = null;
		MemberDTO mdto = null;
		String mail = "";
		if (mKind.equals("직원")) {
			System.out.println(1);
			for (int i = 0; i < mails.length; i++) {
				edto = Ser_E.GetEInfo(mails[i]);
				if (edto != null) {
					mail = edto.getE_EMAIL1() + "@" + edto.getE_EMAIL2();
				}

				try {
					MimeMessage message = mailSender.createMimeMessage();
					MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");

					messageHelper.setFrom(setfrom); // 보내는사람 생략하면 정상작동을 안함
					messageHelper.setTo(mail); // 받는사람 이메일
					messageHelper.setSubject(title); // 메일제목은 생략이 가능하다
					messageHelper.setText(content); // 메일 내용

					mailSender.send(message);
				} catch (Exception e) {
					response.setContentType("text/html; charset=UTF-8");
					PrintWriter out = response.getWriter();
					out.println("<script>alert('오류가 발생했습니다'); history.go(-1);</script>");
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

					messageHelper.setFrom(setfrom); // 보내는사람 생략하면 정상작동을 안함
					messageHelper.setTo(mail); // 받는사람 이메일
					messageHelper.setSubject(title); // 메일제목은 생략이 가능하다
					messageHelper.setText(content); // 메일 내용

					mailSender.send(message);
				} catch (Exception e) {
					response.setContentType("text/html; charset=UTF-8");
					PrintWriter out = response.getWriter();
					out.println("<script>alert('오류가 발생했습니다'); history.go(-1);</script>");
					out.flush();
				}
			}
		}

		// parameter로 string으로 걍 보내니까 오류난다 이 똬식 map으로 보내야된대 똬식
		Map<String, Object> session_map = new HashMap<String, Object>();
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Calendar time = Calendar.getInstance();
		String now = format1.format(time.getTime());

		session_map.put("el_Id", session.getAttribute("eId"));
		session_map.put("el_Activity", tomail + " 메일 전송");
		session_map.put("el_DT", now);

		boolean rslt = Ser_EL.WriteLog(session_map);

		if (!rslt) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('오류가 발생했습니다'); history.go(-1);</script>");
			out.flush();
		}


		return "redirect:/m_mail";
	}
}
