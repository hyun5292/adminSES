package com.company.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.company.dto.MemberDTO;
import com.company.dto.PageDTO;
import com.company.dto.PayLogDTO;
import com.company.dto.QnaDTO;
import com.company.service.MService;
import com.company.service.PLService;
import com.company.service.QService;

@Controller
@Repository
public class M_GeneralController {
	@Autowired
	MService Ser_M;
	@Autowired
	QService Ser_Q;
	@Autowired
	PLService Ser_PL;

	// 일반 회원 문의 내역 날짜 검색
	@RequestMapping("/emp_qna_search")
	public String EQnaSearchedList(HttpServletRequest request, Model model) {
		String mId = request.getParameter("mId");
		String qStartDT = request.getParameter("qStartDT");
		String qEndDT = request.getParameter("qEndDT").split(";")[0];
		Map<String, Object> Qmap = new HashMap<String, Object>();
		Map<String, Object> Pmap = new HashMap<String, Object>();
		MemberDTO mdto = new MemberDTO();
		String FB = "", KT = "", N = "", G = "";
		PageDTO QpgDTO = new PageDTO();
		PageDTO PpgDTO = new PageDTO();
		String QpgNum = request.getParameter("qpgnum");
		String PpgNum = request.getParameter("ppgnum");
		if (QpgNum == null || QpgNum.equals("")) // null이면 맨 처음
			QpgNum = "1";
		if (PpgNum == null || PpgNum.equals("")) // null이면 맨 처음
			PpgNum = "1";
		// int형으로
		int Qpgnum = Integer.parseInt(QpgNum);
		int Ppgnum = Integer.parseInt(PpgNum);

		if (mId == null || mId.equals("")) {
			mdto = new MemberDTO(0, "", "", "", "", "", "", 0, 0, 0, 0, 0, 0, "", "", "", "", "", "", "", 0, 0, 0, "");
		} else {
			mdto = Ser_M.GetMInfo(mId);
		}

		if (mdto.getM_FBCHK().equals("Y")) {
			FB = "Facebook";
		} else {
			FB = "";
		}

		if (mdto.getM_KTCHK().equals("Y")) {
			KT = "KakaoTalk";
		} else {
			KT = "";
		}

		if (mdto.getM_NCHK().equals("Y")) {
			N = "Naver";
		} else {
			N = "";
		}

		if (mdto.getM_GCHK().equals("Y")) {
			G = "Google";
		} else {
			G = "";
		}

		Qmap.put("mId", mId);
		Qmap.put("qSDate", qStartDT);
		Qmap.put("qEDate", qEndDT);

		// 전체 게시글 개수 설정
		QpgDTO.setTotalCnt(Ser_Q.getSearchedMemberQListCnt(Qmap));
		PpgDTO.setTotalCnt(Ser_PL.getMemberPLListCnt(mId));
		// 현재 페이지 번호 설정
		QpgDTO.setPageNum(Qpgnum);
		PpgDTO.setPageNum(Ppgnum);
		// 보여줄 게시물 수 설정
		QpgDTO.setContentNum(5);
		PpgDTO.setContentNum(5);
		// 현재 페이지 블록 설정
		QpgDTO.setCurBlock(Qpgnum);
		PpgDTO.setCurBlock(Ppgnum);
		// 마지막 블록 번호 설정
		QpgDTO.setLastBlock(QpgDTO.getTotalCnt());
		PpgDTO.setLastBlock(PpgDTO.getTotalCnt());
		// 이전 화살표 표시 여부
		QpgDTO.prevnext(Qpgnum);
		PpgDTO.prevnext(Ppgnum);
		// 시작 페이지 설정
		QpgDTO.setStartPage(QpgDTO.getCurBlock());
		PpgDTO.setStartPage(PpgDTO.getCurBlock());
		// 마지막 페이지 설정
		QpgDTO.setEndPage(QpgDTO.getLastBlock(), QpgDTO.getCurBlock());
		PpgDTO.setEndPage(PpgDTO.getLastBlock(), PpgDTO.getCurBlock());

		Qmap.put("startNum", (Qpgnum - 1) * QpgDTO.getContentNum());
		Qmap.put("ContentNum", QpgDTO.getContentNum());

		Pmap.put("mId", mId);
		Pmap.put("startNum", (Ppgnum - 1) * PpgDTO.getContentNum());
		Pmap.put("ContentNum", PpgDTO.getContentNum());

		List<QnaDTO> qdtos = Ser_Q.getSearchedMemberQList(Qmap);
		List<PayLogDTO> pdtos = Ser_PL.getMemberPLList(Pmap);

		int q_first = (Qpgnum - 1) * QpgDTO.getContentNum() + 1;
		int q_last = q_first + QpgDTO.getContentNum();
		int q_j = 0;
		// 각 게시물 번호
		for (int i = q_first; i < q_last; i++) {
			if (i <= QpgDTO.getTotalCnt()) {
				qdtos.get(q_j).setNUM(i);
				q_j++;
			}
		}

		int pl_first = (Ppgnum - 1) * PpgDTO.getContentNum() + 1;
		int pl_last = pl_first + PpgDTO.getContentNum();
		int pl_j = 0;
		// 각 게시물 번호
		for (int i = pl_first; i < pl_last; i++) {
			if (i <= PpgDTO.getTotalCnt()) {
				pdtos.get(pl_j).setNUM(i);
				pl_j++;
			}
		}

		String qprev = "", qnext = ""; // <, >

		if (QpgDTO.isPrev()) { // 이전 블록이 존재하는가
			qprev = "<";
		}
		if (QpgDTO.isNext()) { // 다음 블록이 존재하는가
			qnext = ">";
		}

		String plprev = "", plnext = ""; // <, >

		if (PpgDTO.isPrev()) { // 이전 블록이 존재하는가
			plprev = "<";
		}
		if (PpgDTO.isNext()) { // 다음 블록이 존재하는가
			plnext = ">";
		}

		// 넘어가서 출력될 페이지 번호들
		int[] qpg, plpg;
		if (qdtos.size() == 0) {
			qpg = new int[1];
		} else {
			qpg = new int[(QpgDTO.getEndPage() - QpgDTO.getStartPage()) + 1];
		}
		if (pdtos.size() == 0) {
			plpg = new int[1];
		} else {
			plpg = new int[(PpgDTO.getEndPage() - PpgDTO.getStartPage()) + 1];
		}

		// 원래는 자바스크립트 써서 해줘야되는데 무슨 파일 또 가져와서 설치해야 된다길래
		// 그냥 여기서 값 계산해서 넘겨주기
		q_j = 0;
		pl_j = 0;
		for (int i = QpgDTO.getStartPage(); i < QpgDTO.getStartPage() + QpgDTO.getContentNum(); i++) {
			if (qpg.length > q_j)
				qpg[q_j] = i;
			q_j++;
		}
		for (int i = PpgDTO.getStartPage(); i < PpgDTO.getStartPage() + PpgDTO.getContentNum(); i++) {
			if (plpg.length > pl_j)
				plpg[pl_j] = i;
			pl_j++;
		}

		for (int i = 0; i < qdtos.size(); i++) {
			if ((qdtos.get(i).getQ_REPLY() == null) || (qdtos.get(i).getQ_REPLY().equals(""))) {
				qdtos.get(i).setQ_chkREPLY("X");
			} else {
				qdtos.get(i).setQ_chkREPLY("O");
			}
		}
		model.addAttribute("qStartDT", qStartDT);
		model.addAttribute("qEndDT", qEndDT);

		model.addAttribute("mgQLink", "emp_qna_search?mId="+mId+"&qStartDT="+qStartDT+"&qEndDT="+qEndDT);
		model.addAttribute("mdto", mdto);
		model.addAttribute("FB", FB);
		model.addAttribute("KT", KT);
		model.addAttribute("N", N);
		model.addAttribute("G", G);
		model.addAttribute("qdtos", qdtos);
		model.addAttribute("qbefore", QpgDTO.getStartPage() - 1);
		model.addAttribute("qafter", QpgDTO.getEndPage() + 1);
		model.addAttribute("qprev", qprev);
		model.addAttribute("qpg", qpg);
		model.addAttribute("qnext", qnext);
		if (QpgDTO.getTotalCnt() % QpgDTO.getContentNum() > 0)
			model.addAttribute("qlast", QpgDTO.getTotalCnt() / QpgDTO.getContentNum() + 1);
		else
			model.addAttribute("qlast", QpgDTO.getTotalCnt() / QpgDTO.getContentNum());

		model.addAttribute("mgPLLink", "m_general?mId="+mId);
		model.addAttribute("pdtos", pdtos);
		model.addAttribute("pbefore", PpgDTO.getStartPage() - 1);
		model.addAttribute("pafter", PpgDTO.getEndPage() + 1);
		model.addAttribute("pprev", plprev);
		model.addAttribute("plpg", plpg);
		model.addAttribute("pnext", plnext);
		if (PpgDTO.getTotalCnt() % PpgDTO.getContentNum() > 0)
			model.addAttribute("plast", PpgDTO.getTotalCnt() / PpgDTO.getContentNum() + 1);
		else
			model.addAttribute("plast", PpgDTO.getTotalCnt() / PpgDTO.getContentNum());

		return "/member/m_general";
	}

	// 일반 회원 결제 내역 날짜 검색
	@RequestMapping("/emp_paylog_search")
	public String EPayLogSearchedList(HttpServletRequest request, Model model) {
		String mId = request.getParameter("mId");
		String pStartDT = request.getParameter("pStartDT");
		String pEndDT = request.getParameter("pEndDT").split(";")[0];
		Map<String, Object> Qmap = new HashMap<String, Object>();
		Map<String, Object> Pmap = new HashMap<String, Object>();
		MemberDTO mdto = new MemberDTO();
		String FB = "", KT = "", N = "", G = "";
		PageDTO QpgDTO = new PageDTO();
		PageDTO PpgDTO = new PageDTO();
		String QpgNum = request.getParameter("qpgnum");
		String PpgNum = request.getParameter("ppgnum");
		if (QpgNum == null || QpgNum.equals("")) // null이면 맨 처음
			QpgNum = "1";
		if (PpgNum == null || PpgNum.equals("")) // null이면 맨 처음
			PpgNum = "1";
		// int형으로
		int Qpgnum = Integer.parseInt(QpgNum);
		int Ppgnum = Integer.parseInt(PpgNum);

		if (mId == null || mId.equals("")) {
			mdto = new MemberDTO(0, "", "", "", "", "", "", 0, 0, 0, 0, 0, 0, "", "", "", "", "", "", "", 0, 0, 0, "");
		} else {
			mdto = Ser_M.GetMInfo(mId);
		}

		if (mdto.getM_FBCHK().equals("Y")) {
			FB = "Facebook";
		} else {
			FB = "";
		}

		if (mdto.getM_KTCHK().equals("Y")) {
			KT = "KakaoTalk";
		} else {
			KT = "";
		}

		if (mdto.getM_NCHK().equals("Y")) {
			N = "Naver";
		} else {
			N = "";
		}

		if (mdto.getM_GCHK().equals("Y")) {
			G = "Google";
		} else {
			G = "";
		}

		Pmap.put("mId", mId);
		Pmap.put("pSDate", pStartDT);
		Pmap.put("pEYear", pEndDT);

		// 전체 게시글 개수 설정
		QpgDTO.setTotalCnt(Ser_Q.getMemberQListCnt(mId));
		PpgDTO.setTotalCnt(Ser_PL.getSearchedMemberPLListCnt(Pmap));
		// 현재 페이지 번호 설정
		QpgDTO.setPageNum(Qpgnum);
		PpgDTO.setPageNum(Ppgnum);
		// 보여줄 게시물 수 설정
		QpgDTO.setContentNum(5);
		PpgDTO.setContentNum(5);
		// 현재 페이지 블록 설정
		QpgDTO.setCurBlock(Qpgnum);
		PpgDTO.setCurBlock(Ppgnum);
		// 마지막 블록 번호 설정
		QpgDTO.setLastBlock(QpgDTO.getTotalCnt());
		PpgDTO.setLastBlock(PpgDTO.getTotalCnt());
		// 이전 화살표 표시 여부
		QpgDTO.prevnext(Qpgnum);
		PpgDTO.prevnext(Ppgnum);
		// 시작 페이지 설정
		QpgDTO.setStartPage(QpgDTO.getCurBlock());
		PpgDTO.setStartPage(PpgDTO.getCurBlock());
		// 마지막 페이지 설정
		QpgDTO.setEndPage(QpgDTO.getLastBlock(), QpgDTO.getCurBlock());
		PpgDTO.setEndPage(PpgDTO.getLastBlock(), PpgDTO.getCurBlock());

		Qmap.put("mId", mId);
		Qmap.put("startNum", (Qpgnum - 1) * QpgDTO.getContentNum());
		Qmap.put("ContentNum", QpgDTO.getContentNum());

		Pmap.put("startNum", (Ppgnum - 1) * PpgDTO.getContentNum());
		Pmap.put("ContentNum", PpgDTO.getContentNum());

		List<QnaDTO> qdtos = Ser_Q.getMemberQList(Qmap);
		List<PayLogDTO> pdtos = Ser_PL.getSearchedMemberPLList(Pmap);

		int q_first = (Qpgnum - 1) * QpgDTO.getContentNum() + 1;
		int q_last = q_first + QpgDTO.getContentNum();
		int q_j = 0;
		// 각 게시물 번호
		for (int i = q_first; i < q_last; i++) {
			if (i <= QpgDTO.getTotalCnt()) {
				qdtos.get(q_j).setNUM(i);
				q_j++;
			}
		}

		int pl_first = (Ppgnum - 1) * PpgDTO.getContentNum() + 1;
		int pl_last = pl_first + PpgDTO.getContentNum();
		int pl_j = 0;
		// 각 게시물 번호
		for (int i = pl_first; i < pl_last; i++) {
			if (i <= PpgDTO.getTotalCnt()) {
				pdtos.get(pl_j).setNUM(i);
				pl_j++;
			}
		}

		String qprev = "", qnext = ""; // <, >

		if (QpgDTO.isPrev()) { // 이전 블록이 존재하는가
			qprev = "<";
		}
		if (QpgDTO.isNext()) { // 다음 블록이 존재하는가
			qnext = ">";
		}

		String plprev = "", plnext = ""; // <, >

		if (PpgDTO.isPrev()) { // 이전 블록이 존재하는가
			plprev = "<";
		}
		if (PpgDTO.isNext()) { // 다음 블록이 존재하는가
			plnext = ">";
		}

		// 넘어가서 출력될 페이지 번호들
		int[] qpg, plpg;
		if (qdtos.size() == 0) {
			qpg = new int[1];
		} else {
			qpg = new int[(QpgDTO.getEndPage() - QpgDTO.getStartPage()) + 1];
		}
		if (pdtos.size() == 0) {
			plpg = new int[1];
		} else {
			plpg = new int[(PpgDTO.getEndPage() - PpgDTO.getStartPage()) + 1];
		}

		// 원래는 자바스크립트 써서 해줘야되는데 무슨 파일 또 가져와서 설치해야 된다길래
		// 그냥 여기서 값 계산해서 넘겨주기
		q_j = 0;
		pl_j = 0;
		for (int i = QpgDTO.getStartPage(); i < QpgDTO.getStartPage() + QpgDTO.getContentNum(); i++) {
			if (qpg.length > q_j)
				qpg[q_j] = i;
			q_j++;
		}
		for (int i = PpgDTO.getStartPage(); i < PpgDTO.getStartPage() + PpgDTO.getContentNum(); i++) {
			if (plpg.length > pl_j)
				plpg[pl_j] = i;
			pl_j++;
		}

		for (int i = 0; i < qdtos.size(); i++) {
			if ((qdtos.get(i).getQ_REPLY() == null) || (qdtos.get(i).getQ_REPLY().equals(""))) {
				qdtos.get(i).setQ_chkREPLY("X");
			} else {
				qdtos.get(i).setQ_chkREPLY("O");
			}
		}
		model.addAttribute("pStartDT", pStartDT);
		model.addAttribute("pEndDT", pEndDT);

		model.addAttribute("mgQLink", "m_general?mId="+mId);
		model.addAttribute("mdto", mdto);
		model.addAttribute("FB", FB);
		model.addAttribute("KT", KT);
		model.addAttribute("N", N);
		model.addAttribute("G", G);
		model.addAttribute("qdtos", qdtos);
		model.addAttribute("qbefore", QpgDTO.getStartPage() - 1);
		model.addAttribute("qafter", QpgDTO.getEndPage() + 1);
		model.addAttribute("qprev", qprev);
		model.addAttribute("qpg", qpg);
		model.addAttribute("qnext", qnext);
		if (QpgDTO.getTotalCnt() % QpgDTO.getContentNum() > 0)
			model.addAttribute("qlast", QpgDTO.getTotalCnt() / QpgDTO.getContentNum() + 1);
		else
			model.addAttribute("qlast", QpgDTO.getTotalCnt() / QpgDTO.getContentNum());

		model.addAttribute("mgPLLink", "emp_paylog_search?mId="+mId+"&pStartDT="+pStartDT+"&pEndDT="+pEndDT);
		model.addAttribute("pdtos", pdtos);
		model.addAttribute("pbefore", PpgDTO.getStartPage() - 1);
		model.addAttribute("pafter", PpgDTO.getEndPage() + 1);
		model.addAttribute("pprev", plprev);
		model.addAttribute("plpg", plpg);
		model.addAttribute("pnext", plnext);
		if (PpgDTO.getTotalCnt() % PpgDTO.getContentNum() > 0)
			model.addAttribute("plast", PpgDTO.getTotalCnt() / PpgDTO.getContentNum() + 1);
		else
			model.addAttribute("plast", PpgDTO.getTotalCnt() / PpgDTO.getContentNum());

		return "/member/m_general";
	}
}
