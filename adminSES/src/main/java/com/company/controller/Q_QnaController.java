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

import com.company.dto.PageDTO;
import com.company.dto.QnaDTO;
import com.company.dto.SnsDTO;
import com.company.service.ELService;
import com.company.service.QService;
import com.company.service.SService;

@Controller
@Repository
public class Q_QnaController {
	@Inject
	HttpSession session;
	
	@Autowired
	QService Ser_Q;
	@Autowired
	SService Ser_S;
	@Autowired
	ELService Ser_EL;

	// 문의 작성 페이지로 이동
	@RequestMapping("/new_qna")
	public String GoNewQAnswer(HttpServletRequest request, Model model) {
		// 로그인 확인
		if (session.getAttribute("eId") == null) {
			return "redirect:/login";
		}
		
		int NewQnum = Ser_Q.GetLastQnum() + 1;
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Calendar time = Calendar.getInstance();
		String now = format1.format(time.getTime());

		// sns사 담당자 목록 불러오기
		List<SnsDTO> dtos = Ser_S.GetSnsList();
		String[] sKinds = new String[dtos.size()];

		for (int i = 0; i < dtos.size(); i++) {
			sKinds[i] = (dtos.get(i).getM_NAME() + " " + dtos.get(i).getM_ID() + "-"
					+ dtos.get(i).getS_START_DT().split(" ")[0]);
		}

		// 값 넘겨주기
		model.addAttribute("sKinds", sKinds);
		model.addAttribute("NewQnum", NewQnum);
		model.addAttribute("today", now);
		model.addAttribute("formAction", "addnew_qna");

		return "/qna/qna_answer";
	}

	// 작성된 문의 입력
	@RequestMapping(value = "/addnew_qna", method = RequestMethod.POST)
	public String AddNewQAnswer(HttpServletResponse response, HttpServletRequest request, Model model)
			throws IOException {
		// parameter로 string으로 걍 보내니까 오류난다 이 똬식 map으로 보내야된대 똬식
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("QTitle", request.getParameter("QTitle"));
		map.put("QWriter", request.getParameter("QWriter").split(" ")[1]);
		map.put("QPwd", "9944");
		map.put("QnaDT", request.getParameter("QnaDT"));
		map.put("QContent", request.getParameter("QContent"));
		map.put("QReply", request.getParameter("QReply"));

		boolean result = Ser_Q.AddMewQna(map);

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
		session_map.put("el_Activity", request.getParameter("QTitle") + " 문의 입력");
		session_map.put("el_DT", now);

		boolean rslt = Ser_EL.WriteLog(session_map);

		if (!rslt) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('오류가 발생했습니다'); history.go(-1);</script>");
			out.flush();
		}

		return "redirect:/" + "qna";
	}

	// 받은 문의 답변으로 이동
	@RequestMapping("/qna_answer")
	public String GoQnaAnswer(HttpServletRequest request, Model model) {
		// 로그인 확인
		if (session.getAttribute("eId") == null) {
			return "redirect:/login";
		}
		
		// parameter로 string으로 걍 보내니까 오류난다 이 똬식 map으로 보내야된대 똬식
		Map<String, Object> map = new HashMap<String, Object>();
		String Qnum = request.getParameter("Qnum");

		// 문의 데이터 불러오기
		QnaDTO dto = Ser_Q.GetQData(Qnum);

		// 값 넘겨주기
		model.addAttribute("dto", dto);
		model.addAttribute("formAction", "addqna_answer");

		return "/qna/qna_answer";
	}

	// 받은 문의 삭제
	@RequestMapping("/delete_qna")
	public String DeleteQna(HttpServletResponse response, HttpServletRequest request, Model model) throws IOException {
		String Qnum = request.getParameter("Qnum");

		boolean result = Ser_Q.DeleteQna(Qnum);

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
		session_map.put("el_Activity", Qnum + "번 문의 삭제");
		session_map.put("el_DT", now);

		boolean rslt = Ser_EL.WriteLog(session_map);

		if (!rslt) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('오류가 발생했습니다'); history.go(-1);</script>");
			out.flush();
		}
		
		return "redirect:/" + "qna";
	}

	// 받은 문의 답변 입력
	@RequestMapping(value = "/addqna_answer", method = RequestMethod.POST)
	public String AddQnaAnswer(HttpServletResponse response, HttpServletRequest request, Model model)
			throws IOException {
		// parameter로 string으로 걍 보내니까 오류난다 이 똬식 map으로 보내야된대 똬식
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("Qnum", request.getParameter("Qnum"));
		map.put("QReply", request.getParameter("QReply"));

		boolean result = Ser_Q.AddReply(map);

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
		session_map.put("el_Activity", request.getParameter("Qnum") + "번 문의 답변 입력");
		session_map.put("el_DT", now);

		boolean rslt = Ser_EL.WriteLog(session_map);

		if (!rslt) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('오류가 발생했습니다'); history.go(-1);</script>");
			out.flush();
		}

		return "redirect:/" + "qna_answer?Qnum=" + request.getParameter("Qnum");
	}

	// 받은 문의 날짜 검색
	@RequestMapping("/sch_qna")
	public String SchQnaDate(HttpServletRequest request, Model model) {
		// parameter로 string으로 걍 보내니까 오류난다 이 똬식 map으로 보내야된대 똬식
		Map<String, Object> map = new HashMap<String, Object>();
		PageDTO pgDTO = new PageDTO();
		String pgNum = request.getParameter("pgnum");
		String StartDT = request.getParameter("StartDT");
		String EndDT = request.getParameter("EndDT");
		if (pgNum == null) // null이면 맨 처음
			pgNum = "1";
		// int형으로
		int pgnum = Integer.parseInt(pgNum);

		map.put("StartDT", StartDT);
		map.put("EndDT", EndDT);

		// 전체 게시글 개수 설정
		pgDTO.setTotalCnt(Ser_Q.GetSchQListCnt(map));
		// 현재 페이지 번호 설정
		pgDTO.setPageNum(pgnum);
		// 보여줄 게시물 수 설정
		pgDTO.setContentNum(7);
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

		// 문의 목록 불러오기
		List<QnaDTO> dtos = Ser_Q.GetSchQList(map);

		for (int i = 0; i < dtos.size(); i++) {
			if (dtos.get(i).getQ_REPLY() == null || dtos.get(i).getQ_REPLY() == "") {
				dtos.get(i).setQ_chkREPLY("X");
			} else {
				dtos.get(i).setQ_chkREPLY("O");
			}
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

		// 값 넘겨주기
		model.addAttribute("QLink", "sch_qna?StartDT=" + StartDT + "&EndDT=" + EndDT + "&");
		model.addAttribute("StartDT", StartDT);
		model.addAttribute("EndDT", EndDT);
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

		return "/qna/qna";
	}

	// 받은 문의 키워드 검색
	@RequestMapping("/schKW_qna")
	public String SchQnaKeyWord(HttpServletRequest request, Model model) {
		// parameter로 string으로 걍 보내니까 오류난다 이 똬식 map으로 보내야된대 똬식
		Map<String, Object> map = new HashMap<String, Object>();
		PageDTO pgDTO = new PageDTO();
		String pgNum = request.getParameter("pgnum");
		String title = request.getParameter("title");
		String kind = request.getParameter("kind");
		String qId = request.getParameter("qId");
		if (pgNum == null) // null이면 맨 처음
			pgNum = "1";
		// int형으로
		int pgnum = Integer.parseInt(pgNum);

		map.put("title", "%"+title+"%");
		map.put("qId", "%"+qId+"%");

		// 전체 게시글 개수 설정
		pgDTO.setTotalCnt(Ser_Q.GetSchKWQListCnt(map));
		// 현재 페이지 번호 설정
		pgDTO.setPageNum(pgnum);
		// 보여줄 게시물 수 설정
		pgDTO.setContentNum(7);
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

		// 문의 목록 불러오기
		List<QnaDTO> dtos = Ser_Q.GetSchKWQList(map);

		for (int i = 0; i < dtos.size(); i++) {
			if (dtos.get(i).getQ_REPLY() == null || dtos.get(i).getQ_REPLY() == "") {
				dtos.get(i).setQ_chkREPLY("X");
			} else {
				dtos.get(i).setQ_chkREPLY("O");
			}
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

		// 값 넘겨주기
		model.addAttribute("QLink", "schKW_qna?kind="+kind+"&title=" + title + "&qId=" + qId + "&");
		model.addAttribute("kind", kind);
		model.addAttribute("title", title);
		model.addAttribute("qId", qId);
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

		return "/qna/qna";
	}
}
