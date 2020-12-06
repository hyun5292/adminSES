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

	// 로그인
	@RequestMapping(value = "/doLogin", method = RequestMethod.POST)
	public String MLogin(HttpServletResponse response, HttpServletRequest request, RedirectAttributes attr)
			throws Exception {
		EmployeeDTO dto = Ser_E.ELogin(request);

		if (dto == null) { // 해당 직원이 존재하지 않을 경우
			session.setAttribute("eId", null);
			session.setAttribute("ePw", null);
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('존재하지 않는 회원입니다!!'); history.go(-1); </script>");
			out.flush();
		} else if (!dto.getM_PW().equals(request.getParameter("ePw"))) { // 비밀번호가 일치하지 않을 경우
			session.setAttribute("eId", null);
			session.setAttribute("ePw", null);
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('비밀번호가 틀렸습니다!!'); history.go(-1); </script>");
			out.flush();
		} else { // 성공
			session.setAttribute("eId", dto.getM_ID());
			session.setAttribute("ePw", dto.getM_PW());

			// parameter로 string으로 걍 보내니까 오류난다 이 똬식 map으로 보내야된대 똬식
			Map<String, Object> session_map = new HashMap<String, Object>();
			SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			Calendar time = Calendar.getInstance();
			String now = format1.format(time.getTime());

			session_map.put("el_Id", session.getAttribute("eId"));
			session_map.put("el_Activity", "로그인");
			session_map.put("el_DT", now);

			boolean rslt = Ser_EL.WriteLog(session_map);

			if (!rslt) {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>alert('오류가 발생했습니다'); history.go(-1);</script>");
				out.flush();
			}

			return "redirect:/main";
		}

		return "/login";
	}

	// 로그아웃
	@RequestMapping("/logout")
	public String MLogout(HttpServletResponse response, HttpServletRequest request, RedirectAttributes attr)
			throws Exception {

		// parameter로 string으로 걍 보내니까 오류난다 이 똬식 map으로 보내야된대 똬식
		Map<String, Object> session_map = new HashMap<String, Object>();
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Calendar time = Calendar.getInstance();
		String now = format1.format(time.getTime());

		session_map.put("el_Id", session.getAttribute("eId"));
		session_map.put("el_Activity", "로그아웃");
		session_map.put("el_DT", now);

		boolean rslt = Ser_EL.WriteLog(session_map);

		if (!rslt) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('오류가 발생했습니다'); history.go(-1);</script>");
			out.flush();
		}

		session.removeAttribute("user");
		session.invalidate();

		return "redirect:/main";
	}

	// 비밀번호 변경
	@RequestMapping(value = "/doChgPW", method = RequestMethod.POST)
	public String ChagePWD(HttpServletResponse response, HttpServletRequest request, Model model) throws IOException {
		// parameter로 string으로 걍 보내니까 오류난다 이 똬식 map으로 보내야된대 똬식
		Map<String, Object> map = new HashMap<String, Object>();

		if (!request.getParameter("nowPW").equals(session.getAttribute("ePw").toString())) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('현 비밀번호가 일치하지 않습니다!!');</script>");
			out.flush();
			
			return "modifypwd";
		} else {
			map.put("eId", session.getAttribute("eId").toString());
			map.put("ePw", request.getParameter("newPW"));

			boolean result = Ser_E.ChagePWD(map);

			if (result) {
				session.removeAttribute("user");
				session.invalidate();

				// parameter로 string으로 걍 보내니까 오류난다 이 똬식 map으로 보내야된대 똬식
				Map<String, Object> session_map = new HashMap<String, Object>();
				SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				Calendar time = Calendar.getInstance();
				String now = format1.format(time.getTime());

				session_map.put("el_Id", session.getAttribute("eId"));
				session_map.put("el_Activity", session.getAttribute("eId") + " 비밀번호 변경");
				session_map.put("el_DT", now);

				boolean rslt = Ser_EL.WriteLog(session_map);

				if (!rslt) {
					response.setContentType("text/html; charset=UTF-8");
					PrintWriter out = response.getWriter();
					out.println("<script>alert('오류가 발생했습니다'); history.go(-1);</script>");
					out.flush();
				}
				
				return "redirect:/login";
			} else {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>alert('오류가 발생하였습니다!!'); history.go(-1);</script>");
				out.flush();

				session.removeAttribute("user");
				session.invalidate();
				return "redirect:/login";
			}
		}
	}

	// 직원 활동 내역 날짜 검색
	@RequestMapping("/el_sch")
	public String EmpLSearchedList(HttpServletRequest request, Model model) {
		// parameter로 string으로 걍 보내니까 오류난다 이 똬식 map으로 보내야된대 똬식
		Map<String, Object> map = new HashMap<String, Object>();
		String mId = request.getParameter("mId");
		String pgNum = request.getParameter("pgnum");
		String StartDT = request.getParameter("StartDT");
		String EndDT = request.getParameter("EndDT").split(";")[0];
		PageDTO pgDTO = new PageDTO();
		if (pgNum == null) // null이면 맨 처음
			pgNum = "1";
		// int형으로
		int pgnum = Integer.parseInt(pgNum);

		// 직원 정보 가져오기
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

		// 전체 게시글 개수 설정
		pgDTO.setTotalCnt(Ser_EL.GetSchELPageCnt(map));
		// 현재 페이지 번호 설정
		pgDTO.setPageNum(pgnum);
		// 보여줄 게시물 수 설정
		pgDTO.setContentNum(10);
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

		// 직원 활동 목록 불러오기
		List<EmpLogDTO> dtos = Ser_EL.GetSchELList(map);

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

		// 값 넘겨주기
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

	// 직원 관리자 권한 부여
	@RequestMapping("/doAuth")
	public String DoAuth(HttpServletResponse response, HttpServletRequest request, Model model) throws IOException {
		String mId = request.getParameter("mId");
		boolean result;

		result = Ser_E.MakeDoAuth(mId);

		if (!result) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('오류가 발생했습니다'); history.go(-1);</script>");
			out.flush();
		}

		// parameter로 string으로 걍 보내니까 오류난다 이 똬식 map으로 보내야된대 똬식
		Map<String, Object> session_map = new HashMap<String, Object>();
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Calendar time = Calendar.getInstance();
		String now = format1.format(time.getTime());

		session_map.put("el_Id", session.getAttribute("eId"));
		session_map.put("el_Activity", mId + " 관리자 권한 부여");
		session_map.put("el_DT", now);

		boolean rslt = Ser_EL.WriteLog(session_map);

		if (!rslt) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('오류가 발생했습니다'); history.go(-1);</script>");
			out.flush();
		}

		return "redirect:/m_search";
	}

	// 직원 관리자 권한 해지
	@RequestMapping("/dontAuth")
	public String DontAuth(HttpServletResponse response, HttpServletRequest request, Model model) throws IOException {
		String mId = request.getParameter("mId");
		boolean result;

		result = Ser_E.MakeDontAuth(mId);

		if (!result) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('오류가 발생했습니다'); history.go(-1);</script>");
			out.flush();
		}

		// parameter로 string으로 걍 보내니까 오류난다 이 똬식 map으로 보내야된대 똬식
		Map<String, Object> session_map = new HashMap<String, Object>();
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Calendar time = Calendar.getInstance();
		String now = format1.format(time.getTime());

		session_map.put("el_Id", session.getAttribute("eId"));
		session_map.put("el_Activity", mId + " 관리자 권한 해제");
		session_map.put("el_DT", now);

		boolean rslt = Ser_EL.WriteLog(session_map);

		if (!rslt) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('오류가 발생했습니다'); history.go(-1);</script>");
			out.flush();
		}

		return "redirect:/m_search";
	}
}
