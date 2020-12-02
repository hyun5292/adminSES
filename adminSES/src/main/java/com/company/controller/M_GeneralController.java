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

import com.company.dto.LogDTO;
import com.company.dto.MemberDTO;
import com.company.dto.PageDTO;
import com.company.dto.PayLogDTO;
import com.company.dto.QnaDTO;
import com.company.service.LService;
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
	@Autowired
	LService Ser_L;

	// �Ϲ� ȸ�� ���� ���� & Ȱ�� ���� ��¥ �˻�
	@RequestMapping("/g_dt_search")
	public String EQnaSearchedList(HttpServletRequest request, Model model) {
		String mId = request.getParameter("mId");
		String qStartDT = request.getParameter("qStartDT");
		String qEndDT = request.getParameter("qEndDT");
		String lStartDT = request.getParameter("lStartDT");
		String lEndDT = request.getParameter("lEndDT");
		Map<String, Object> Qmap = new HashMap<String, Object>();
		Map<String, Object> Lmap = new HashMap<String, Object>();
		MemberDTO mdto = new MemberDTO();
		String FB = "", KT = "", N = "", G = "";
		PageDTO QpgDTO = new PageDTO();
		PageDTO LpgDTO = new PageDTO();
		String QpgNum = request.getParameter("qpgnum");
		String LpgNum = request.getParameter("lpgnum");
		if (QpgNum == null || QpgNum.equals("")) // null�̸� �� ó��
			QpgNum = "1";
		if (LpgNum == null || LpgNum.equals("")) // null�̸� �� ó��
			LpgNum = "1";
		// int������
		int Qpgnum = Integer.parseInt(QpgNum);
		int Lpgnum = Integer.parseInt(LpgNum);
		
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

		if (qStartDT != null && qEndDT != null) {
			if (qStartDT != "" && qEndDT != "") {
				Qmap.put("mId", mId);
				Qmap.put("qSDate", qStartDT);
				Qmap.put("qEDate", qEndDT);
				// ��ü �Խñ� ���� ����
				QpgDTO.setTotalCnt(Ser_Q.getSearchedMemberQListCnt(Qmap));
			}
		} else {
			// ��ü �Խñ� ���� ����
			QpgDTO.setTotalCnt(Ser_Q.getMemberQListCnt(mId));
		}

		if (lStartDT != null && lEndDT != null) {
			if (lStartDT != "" && lEndDT != "") {
				Lmap.put("mId", mId);
				Lmap.put("lStartDT", lStartDT);
				Lmap.put("lEndDT", lEndDT);
				// ��ü �Խñ� ���� ����
				LpgDTO.setTotalCnt(Ser_L.getSearchedMemberLListCnt(Lmap));
			}
		} else {
			// ��ü �Խñ� ���� ����
			LpgDTO.setTotalCnt(Ser_L.getMemberLListCnt(mId));
		}

		// ���� ������ ��ȣ ����
		QpgDTO.setPageNum(Qpgnum);
		LpgDTO.setPageNum(Lpgnum);
		// ������ �Խù� �� ����
		QpgDTO.setContentNum(5);
		LpgDTO.setContentNum(5);
		// ���� ������ ��� ����
		QpgDTO.setCurBlock(Qpgnum);
		LpgDTO.setCurBlock(Lpgnum);
		// ������ ��� ��ȣ ����
		QpgDTO.setLastBlock(QpgDTO.getTotalCnt());
		LpgDTO.setLastBlock(LpgDTO.getTotalCnt());
		// ���� ȭ��ǥ ǥ�� ����
		QpgDTO.prevnext(Qpgnum);
		LpgDTO.prevnext(Lpgnum);
		// ���� ������ ����
		QpgDTO.setStartPage(QpgDTO.getCurBlock());
		LpgDTO.setStartPage(LpgDTO.getCurBlock());
		// ������ ������ ����
		QpgDTO.setEndPage(QpgDTO.getLastBlock(), QpgDTO.getCurBlock());
		LpgDTO.setEndPage(LpgDTO.getLastBlock(), LpgDTO.getCurBlock());

		List<QnaDTO> qdtos;
		List<LogDTO> ldtos;
		
		if (qStartDT != null && qEndDT != null) {
			if (qStartDT != "" && qEndDT != "") {
				Qmap.put("mId", mId);
				Qmap.put("qSDate", qStartDT);
				Qmap.put("qEDate", qEndDT);
				Qmap.put("startNum", (Qpgnum - 1) * QpgDTO.getContentNum());
				Qmap.put("ContentNum", QpgDTO.getContentNum());
				qdtos = Ser_Q.getSearchedMemberQList(Qmap);
			} else {
				Qmap.put("mId", mId);
				Qmap.put("startNum", (Qpgnum - 1) * QpgDTO.getContentNum());
				Qmap.put("ContentNum", QpgDTO.getContentNum());
				qdtos = Ser_Q.getMemberQList(Qmap);
			}
		} else {
			Qmap.put("mId", mId);
			Qmap.put("startNum", (Qpgnum - 1) * QpgDTO.getContentNum());
			Qmap.put("ContentNum", QpgDTO.getContentNum());
			qdtos = Ser_Q.getMemberQList(Qmap);
		}

		if (lStartDT != null && lEndDT != null) {
			if (lStartDT != "" && lEndDT != "") {
				Lmap.put("mId", mId);
				Lmap.put("lStartDT", lStartDT);
				Lmap.put("lEndDT", lEndDT);
				Lmap.put("startNum", (Lpgnum - 1) * LpgDTO.getContentNum());
				Lmap.put("ContentNum", LpgDTO.getContentNum());
				ldtos = Ser_L.getSearchedMemberLList(Lmap);
			} else {
				Lmap.put("mId", mId);
				Lmap.put("startNum", (Lpgnum - 1) * LpgDTO.getContentNum());
				Lmap.put("ContentNum", LpgDTO.getContentNum());
				ldtos = Ser_L.getMemberLogList(Lmap);
			}
		} else {
			Lmap.put("mId", mId);
			Lmap.put("startNum", (Lpgnum - 1) * LpgDTO.getContentNum());
			Lmap.put("ContentNum", LpgDTO.getContentNum());
			ldtos = Ser_L.getMemberLogList(Lmap);
		}

		for (int i = 0; i < ldtos.size(); i++) {
			if (ldtos.get(i).getSL_NAME() != null && ldtos.get(i).getSL_NAME() != "") {
				if (ldtos.get(i).getSU_KIND() != null && ldtos.get(i).getSU_KIND() != "") {
					ldtos.get(i).setL_ACTIVITY(ldtos.get(i).getSU_KIND() + "-" + ldtos.get(i).getSL_NAME() + " "
							+ ldtos.get(i).getL_ACTIVITY());
				}
			}
		}

		int q_first = (Qpgnum - 1) * QpgDTO.getContentNum() + 1;
		int q_last = q_first + QpgDTO.getContentNum();
		int q_j = 0;
		// �� �Խù� ��ȣ
		for (int i = q_first; i < q_last; i++) {
			if (i <= QpgDTO.getTotalCnt()) {
				qdtos.get(q_j).setNUM(i);
				q_j++;
			}
		}

		int l_first = (Lpgnum - 1) * LpgDTO.getContentNum() + 1;
		int l_last = l_first + LpgDTO.getContentNum();
		int l_j = 0;
		// �� �Խù� ��ȣ
		for (int i = l_first; i < l_last; i++) {
			if (i <= LpgDTO.getTotalCnt()) {
				ldtos.get(l_j).setNUM(i);
				l_j++;
			}
		}

		String qprev = "", qnext = ""; // <, >

		if (QpgDTO.isPrev()) { // ���� ����� �����ϴ°�
			qprev = "<";
		}
		if (QpgDTO.isNext()) { // ���� ����� �����ϴ°�
			qnext = ">";
		}

		String lprev = "", lnext = ""; // <, >

		if (LpgDTO.isPrev()) { // ���� ����� �����ϴ°�
			lprev = "<";
		}
		if (LpgDTO.isNext()) { // ���� ����� �����ϴ°�
			lnext = ">";
		}

		// �Ѿ�� ��µ� ������ ��ȣ��
		int[] qpg, lpg;
		if (qdtos.size() == 0) {
			qpg = new int[1];
		} else {
			qpg = new int[(QpgDTO.getEndPage() - QpgDTO.getStartPage()) + 1];
		}
		if (ldtos.size() == 0) {
			lpg = new int[1];
		} else {
			lpg = new int[(LpgDTO.getEndPage() - LpgDTO.getStartPage()) + 1];
		}

		// ������ �ڹٽ�ũ��Ʈ �Ἥ ����ߵǴµ� ���� ���� �� �����ͼ� ��ġ�ؾ� �ȴٱ淡
		// �׳� ���⼭ �� ����ؼ� �Ѱ��ֱ�
		q_j = 0;
		l_j = 0;
		for (int i = QpgDTO.getStartPage(); i < QpgDTO.getStartPage() + QpgDTO.getContentNum(); i++) {
			if (qpg.length > q_j) {
				qpg[q_j] = i;
			}
			q_j++;
		}
		for (int i = LpgDTO.getStartPage(); i < LpgDTO.getStartPage() + LpgDTO.getContentNum(); i++) {
			if (lpg.length > l_j) {
				lpg[l_j] = i;
			}
			l_j++;
		}

		for (int i = 0; i < qdtos.size(); i++) {
			if ((qdtos.get(i).getQ_REPLY() == null) || (qdtos.get(i).getQ_REPLY().equals(""))) {
				qdtos.get(i).setQ_chkREPLY("X");
			} else {
				qdtos.get(i).setQ_chkREPLY("O");
			}
		}
		
		String Link = "";
		if (qStartDT != null && qEndDT != null) {
			if (qStartDT != "" && qEndDT != "") {
				Link = "&qStartDT="+qStartDT+"&qEndDT="+qEndDT;
			}
		}
		if (lStartDT != null && lEndDT != null) {
			if (lStartDT != "" && lEndDT != "") {
				Link = "&lStartDT="+lStartDT+"&lEndDT="+lEndDT;
			}
		}

		int totalPay = 0;
		totalPay = 19900 * Ser_PL.getMPayList(mId);
		
		model.addAttribute("totalPay", totalPay);
		model.addAttribute("qStartDT", qStartDT);
		model.addAttribute("qEndDT", qEndDT);
		model.addAttribute("lStartDT", lStartDT);
		model.addAttribute("lEndDT", lEndDT);
		model.addAttribute("QLink", "g_dt_search?mId=" + mId+Link);
		model.addAttribute("LLink", "g_dt_search?mId=" + mId+Link);

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

		model.addAttribute("ldtos", ldtos);
		model.addAttribute("lbefore", LpgDTO.getStartPage() - 1);
		model.addAttribute("lafter", LpgDTO.getEndPage() + 1);
		model.addAttribute("lprev", lprev);
		model.addAttribute("lpg", lpg);
		model.addAttribute("lpgnum", Lpgnum);
		model.addAttribute("lnext", lnext);
		if (LpgDTO.getTotalCnt() % LpgDTO.getContentNum() > 0)
			model.addAttribute("llast", LpgDTO.getTotalCnt() / LpgDTO.getContentNum() + 1);
		else
			model.addAttribute("llast", LpgDTO.getTotalCnt() / LpgDTO.getContentNum());
		
		return "/member/m_general";
	}

}
