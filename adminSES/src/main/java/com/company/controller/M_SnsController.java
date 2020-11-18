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
	// 서비스 인터페이스 갖고 와서 여기서 정의
	@Autowired
	MService Ser_M;
	@Autowired
	QService Ser_Q;
	@Autowired
	SService Ser_S;

	// SNS사 담당자 문의 내역 날짜 검색
	@RequestMapping("/sns_qsch")
	public String SnsSchQnaList(HttpServletRequest request, Model model) {
		// parameter로 string으로 걍 보내니까 오류난다 이 똬식 map으로 보내야된대 똬식
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
		if (pgNum == null) // null이면 맨 처음
			pgNum = "1";
		// int형으로
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

		// SNS사 담당자 정보 가져오기
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
		// 전체 게시글 개수 설정
		pgDTO.setTotalCnt(Ser_Q.GetSchSnsQListCnt(map));
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
		List<QnaDTO> dtos = Ser_Q.GetSchSnsQList(map);

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

	// 새로운 SNS 담당자 등록
	@RequestMapping(value = "/newSns", method = RequestMethod.POST)
	public String NewSnsMember(HttpServletResponse response, HttpServletRequest request, Model model)
			throws IOException {
		// parameter로 string으로 걍 보내니까 오류난다 이 똬식 map으로 보내야된대 똬식
		Map<String, Object> map = new HashMap<String, Object>();
		boolean result = false;
		SimpleDateFormat format1 = new SimpleDateFormat ( "yyyy-MM-dd");
				
		Calendar time = Calendar.getInstance();
		       
		String now = format1.format(time.getTime());

		map.put("exitDT", now);
		map.put("sKind", request.getParameter("sKind"));
		
		// 기존 담당자 마감일 설정
		result = Ser_S.ChgNowSns(map);
		
		map = new HashMap<String, Object>();

		if (!result) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('현재 담당 중인 담당자의 마감일을 먼저 설정해주세요!!'); history.go(-1);</script>");
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
			out.println("<script>alert('오류가 발생했습니다'); history.go(-1);</script>");
			out.flush();
		}

		return "redirect:/" + "m_search";
	}

	// SNS 담당자 정보 수정
	@RequestMapping(value = "/modifySns", method = RequestMethod.POST)
	public String ModifySnsMember(HttpServletResponse response, HttpServletRequest request, Model model)
			throws IOException {
		// parameter로 string으로 걍 보내니까 오류난다 이 똬식 map으로 보내야된대 똬식
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
			out.println("<script>alert('오류가 발생했습니다'); history.go(-1);</script>");
			out.flush();
		}

		return "redirect:/" + "m_search";
	}

	// SNS사 담당자 삭제
	@RequestMapping("/delete_sns")
	public String DeleteSns(HttpServletResponse response, HttpServletRequest request, Model model)
			throws IOException {
		// parameter로 string으로 걍 보내니까 오류난다 이 똬식 map으로 보내야된대 똬식
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
			out.println("<script>alert('오류가 발생했습니다'); history.go(-1);</script>");
			out.flush();
		}

		return "redirect:/" + "m_search";
	}
}
