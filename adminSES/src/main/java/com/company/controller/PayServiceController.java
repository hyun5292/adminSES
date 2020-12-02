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
public class PayServiceController {
	// 서비스 인터페이스 갖고 와서 여기서 정의
	@Autowired
	MService Ser_M;
	@Autowired
	PLService Ser_PL;

	// 일반 회원 결제 내역 날짜 검색
	@RequestMapping("/service_Schpaylog")
	public String EPayLogSearchedList(HttpServletRequest request, Model model) {
		Map<String, Object> Pmap = new HashMap<String, Object>();
		MemberDTO mdto = new MemberDTO();
		String mId = request.getParameter("mId");
		String pStartDT = request.getParameter("pStartDT");
		String pEndDT = request.getParameter("pEndDT");
		PageDTO PpgDTO = new PageDTO();
		String PpgNum = request.getParameter("ppgnum");
		// 이용자 수
		int Usercnt = Ser_M.GetServiceUserCnt();

		if (PpgNum == null || PpgNum.equals("")) // null이면 맨 처음
			PpgNum = "1";
		// int형으로
		int Ppgnum = Integer.parseInt(PpgNum);

		// 회원 정보 가져오기
		if (mId == null || mId.equals("")) {
			mdto = new MemberDTO(0, "", "", "", "", "", "", 0, 0, 0, 0, 0, 0, "", "", "", "", "", "", "", 0, 0, 0, "");
		} else {
			mdto = Ser_M.GetMInfo(mId);
		}

		Pmap.put("mId", mId);
		Pmap.put("pSDate", pStartDT);
		Pmap.put("pEDate", pEndDT);

		// 전체 게시글 개수 설정
		PpgDTO.setTotalCnt(Ser_PL.getSearchedMemberPLListCnt(Pmap));
		// 현재 페이지 번호 설정
		PpgDTO.setPageNum(Ppgnum);
		// 보여줄 게시물 수 설정
		PpgDTO.setContentNum(5);
		// 현재 페이지 블록 설정
		PpgDTO.setCurBlock(Ppgnum);
		// 마지막 블록 번호 설정
		PpgDTO.setLastBlock(PpgDTO.getTotalCnt());
		// 이전 화살표 표시 여부
		PpgDTO.prevnext(Ppgnum);
		// 시작 페이지 설정
		PpgDTO.setStartPage(PpgDTO.getCurBlock());
		// 마지막 페이지 설정
		PpgDTO.setEndPage(PpgDTO.getLastBlock(), PpgDTO.getCurBlock());

		Pmap.put("startNum", (Ppgnum - 1) * PpgDTO.getContentNum());
		Pmap.put("ContentNum", PpgDTO.getContentNum());

		List<PayLogDTO> pdtos = Ser_PL.getSearchedMemberPLList(Pmap);

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

		String plprev = "", plnext = ""; // <, >

		if (PpgDTO.isPrev()) { // 이전 블록이 존재하는가
			plprev = "<";
		}
		if (PpgDTO.isNext()) { // 다음 블록이 존재하는가
			plnext = ">";
		}

		// 넘어가서 출력될 페이지 번호들
		int[] plpg;
		if (pdtos.size() == 0) {
			plpg = new int[1];
		} else {
			plpg = new int[(PpgDTO.getEndPage() - PpgDTO.getStartPage()) + 1];
		}

		// 원래는 자바스크립트 써서 해줘야되는데 무슨 파일 또 가져와서 설치해야 된다길래
		// 그냥 여기서 값 계산해서 넘겨주기
		pl_j = 0;
		for (int i = PpgDTO.getStartPage(); i < PpgDTO.getStartPage() + PpgDTO.getContentNum(); i++) {
			if (plpg.length > pl_j)
				plpg[pl_j] = i;
			pl_j++;
		}

		// 값 넘겨주기
		model.addAttribute("mgPLLink", "service_Schpaylog?mId=" + mId + "&pStartDT=" + pStartDT + "&pEndDT=" + pEndDT);
		model.addAttribute("pStartDT", pStartDT);
		model.addAttribute("pEndDT", pEndDT);
		model.addAttribute("Usercnt", Usercnt);
		model.addAttribute("mdto", mdto);
		model.addAttribute("mgPLLink", "pay_service?mId=" + mId);
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

		return "/pay_service";
	}

	// 회원 조건 검색
	@RequestMapping("/schMember")
	public String schMember(HttpServletRequest request, Model model) {

		return "/pay_service";
	}
}
