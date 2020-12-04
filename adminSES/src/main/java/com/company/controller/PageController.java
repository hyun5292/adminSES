package com.company.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.company.dto.EmpLogDTO;
import com.company.dto.EmployeeDTO;
import com.company.dto.LogDTO;
import com.company.dto.MemberDTO;
import com.company.dto.PageDTO;
import com.company.dto.PayLogDTO;
import com.company.dto.QnaDTO;
import com.company.dto.SnsDTO;
import com.company.service.ELService;
import com.company.service.EService;
import com.company.service.LService;
import com.company.service.MService;
import com.company.service.PLService;
import com.company.service.QService;
import com.company.service.SService;

@Controller
@Repository
public class PageController {
	// ���� �������̽� ���� �ͼ� ���⼭ ����
	@Autowired
	MService Ser_M;
	@Autowired
	QService Ser_Q;
	@Autowired
	PLService Ser_PL;
	@Autowired
	EService Ser_E;
	@Autowired
	ELService Ser_EL;
	@Autowired
	SService Ser_S;
	@Autowired
	LService Ser_L;

	// �α��� �������� �̵�
	@RequestMapping("/login")
	public String GoLogin(HttpServletRequest request, Model model) {
		return "/login";
	}

	// �������� �̵�
	@RequestMapping("/main")
	public String GoMain(HttpServletRequest request, Model model) {
		int MCnt = Ser_M.PageCnt();
		int NoMCnt = Ser_M.GeneralNotUseCnt();
		int SUsercnt = Ser_M.GetServiceUserCnt();
		int SUserpct = 0;

		// ���Ἥ�� �̿�� ���
		SUserpct = Math.round(((float) SUsercnt / (float) MCnt) * 100);

		// ���� ��� �ҷ�����
		List<QnaDTO> dtos = Ser_Q.GetQRecentList();

		// �� �Ѱ��ֱ�
		model.addAttribute("dtos", dtos);
		model.addAttribute("MCnt", MCnt);
		model.addAttribute("NoMCnt", NoMCnt);
		model.addAttribute("SUsercnt", SUsercnt);
		model.addAttribute("SUserpct", SUserpct);

		return "/main";
	}

	// ���� ���Ƿ� �̵�
	@RequestMapping("/qna")
	public String GoQna(HttpServletRequest request, Model model) {
		// parameter�� string���� �� �����ϱ� �������� �� �̽� map���� �����ߵȴ� �̽�
		Map<String, Object> map = new HashMap<String, Object>();
		PageDTO pgDTO = new PageDTO();
		String pgNum = request.getParameter("pgnum");
		if (pgNum == null) // null�̸� �� ó��
			pgNum = "1";
		// int������
		int pgnum = Integer.parseInt(pgNum);

		// ��ü �Խñ� ���� ����
		pgDTO.setTotalCnt(Ser_Q.GetQListCnt());
		// ���� ������ ��ȣ ����
		pgDTO.setPageNum(pgnum);
		// ������ �Խù� �� ����
		pgDTO.setContentNum(7);
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

		// ���� ��� �ҷ�����
		List<QnaDTO> dtos = Ser_Q.GetQList(map);
		
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
		model.addAttribute("QLink", "qna?");
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

	// ȸ�� �˻����� �̵�
	@RequestMapping("/m_search")
	public String GoMSearch(HttpServletRequest request, Model model) {
		// parameter�� string���� �� �����ϱ� �������� �� �̽� map���� �����ߵȴ� �̽�
		Map<String, Object> map = new HashMap<String, Object>();
		PageDTO pgDTO = new PageDTO();

		String pgNum = request.getParameter("pgnum");
		if (pgNum == null) // null�̸� �� ó��
			pgNum = "1";
		// int������
		int pgnum = Integer.parseInt(pgNum);

		// ��ü �Խñ� ���� ����
		pgDTO.setTotalCnt(Ser_M.PageCnt());
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

		// ȸ�� ��� �ҷ�����
		List<MemberDTO> dtos = Ser_M.GetMList(map);

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

		// �� �Ѱ��ֱ�
		model.addAttribute("mlink", "m_search");
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

		return "/member/m_search";
	}

	// �Ϲ� ȸ������ �̵�
	@RequestMapping("/m_general")
	public String GoMGeneral(HttpServletRequest request, Model model) {
		Map<String, Object> Qmap = new HashMap<String, Object>();
		Map<String, Object> Lmap = new HashMap<String, Object>();
		MemberDTO mdto = new MemberDTO();
		String mId = request.getParameter("mId");
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

		// ��ü �Խñ� ���� ����
		QpgDTO.setTotalCnt(Ser_Q.getMemberQListCnt(mId));
		LpgDTO.setTotalCnt(Ser_L.getMemberLListCnt(mId));
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

		Qmap.put("mId", mId);
		Qmap.put("startNum", (Qpgnum - 1) * QpgDTO.getContentNum());
		Qmap.put("ContentNum", QpgDTO.getContentNum());
		List<QnaDTO> qdtos = Ser_Q.getMemberQList(Qmap);

		Lmap.put("mId", mId);
		Lmap.put("startNum", (Lpgnum - 1) * LpgDTO.getContentNum());
		Lmap.put("ContentNum", LpgDTO.getContentNum());
		List<LogDTO> ldtos = Ser_L.getMemberLogList(Lmap);

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

		int totalPay = 0;
		totalPay = 19900 * Ser_PL.getMPayList(mId);

		model.addAttribute("totalPay", totalPay);
		model.addAttribute("QLink", "m_general?mId=" + mId);
		model.addAttribute("LLink", "m_general?mId=" + mId);

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
		model.addAttribute("qpgnum", Qpgnum);
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

	// ���� ȸ������ �̵�
	@RequestMapping("/m_admin")
	public String GoMAdmin(HttpServletRequest request, Model model) {
		// parameter�� string���� �� �����ϱ� �������� �� �̽� map���� �����ߵȴ� �̽�
		Map<String, Object> map = new HashMap<String, Object>();
		PageDTO pgDTO = new PageDTO();
		String mId = request.getParameter("mId");
		String pgNum = request.getParameter("pgnum");
		String elLink = "";
		if (pgNum == null) // null�̸� �� ó��
			pgNum = "1";
		// int������
		int pgnum = Integer.parseInt(pgNum);

		// ���� ���� ��������
		EmployeeDTO dto = Ser_E.GetEInfo(mId);

		if (mId == null) {
			dto = new EmployeeDTO();
		} else {
			dto.setE_ENTER_DT(dto.getE_ENTER_DT().split(" ")[0]);
			dto.setE_RESIGN_DT(dto.getE_RESIGN_DT().split(" ")[0]);
		}

		// ��ü �Խñ� ���� ����
		pgDTO.setTotalCnt(Ser_EL.GetELPageCnt(mId));
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

		map.put("mId", mId);
		map.put("startNum", (pgnum - 1) * pgDTO.getContentNum());
		map.put("ContentNum", pgDTO.getContentNum());

		// ���� Ȱ�� ��� �ҷ�����
		List<EmpLogDTO> dtos = Ser_EL.GetELList(map);

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
		elLink = "m_admin?mId=" + mId;
		model.addAttribute("elLink", elLink);
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

	// SNS��� �̵�
	@RequestMapping("/m_sns")
	public String GoMSns(HttpServletRequest request, Model model) {
		// parameter�� string���� �� �����ϱ� �������� �� �̽� map���� �����ߵȴ� �̽�
		Map<String, Object> map = new HashMap<String, Object>();
		PageDTO pgDTO = new PageDTO();
		String pgNum = request.getParameter("pgnum");
		String mId = request.getParameter("mId");
		String mKind = request.getParameter("mKind");
		String snsLink = "";
		float gcnt = Ser_M.PageCnt();
		float serviceUsercnt = 0;
		float userAvg = 0;
		String mId2 = "", formAction = "";
		if (pgNum == null) // null�̸� �� ó��
			pgNum = "1";
		// int������
		int pgnum = Integer.parseInt(pgNum);
		if (mId != null) {
			if (mId.equals("Naver")) {
				serviceUsercnt = Ser_M.GetM_NCHK();
			} else if (mId.equals("Facebook")) {
				serviceUsercnt = Ser_M.GetM_FBCHK();
			} else if (mId.equals("Google")) {
				serviceUsercnt = Ser_M.GetM_GCHK();
			} else if (mId.equals("Kakaotalk")) {
				serviceUsercnt = Ser_M.GetM_KTCHK();
			}
		}

		userAvg = (float) Math.round(serviceUsercnt / gcnt);
		userAvg *= 100;
		userAvg = Math.round(userAvg);

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
			mId2 = "%" + dto.getM_ID() + "-" + dto.getS_START_DT() + "%";
			map.put("mId", mId2);
			map.put("mKind", "%" + mKind + "%");
			formAction = "modifySns";
		}

		// ��ü �Խñ� ���� ����
		pgDTO.setTotalCnt(Ser_Q.GetSnsQListCnt(map));
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

		map.put("mId", mId2);
		map.put("startNum", (pgnum - 1) * pgDTO.getContentNum());
		map.put("ContentNum", pgDTO.getContentNum());

		// ���� Ȱ�� ��� �ҷ�����
		List<QnaDTO> dtos = Ser_Q.GetSnsQList(map);

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
		snsLink = "m_sns?mId=" + mId + "&mKind=" + dto.getS_START_DT();
		model.addAttribute("snsLink", snsLink);
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

	// ȸ�� ���� �������� �̵�
	@RequestMapping("/m_mail")
	public String GoMMail(HttpServletRequest request, Model model) {
		// parameter�� string���� �� �����ϱ� �������� �� �̽� map���� �����ߵȴ� �̽�
		Map<String, Object> map = new HashMap<String, Object>();
		PageDTO pgDTO = new PageDTO();
		String pgNum = request.getParameter("pgnum");
		if (pgNum == null) // null�̸� �� ó��
			pgNum = "1";
		// int������
		int pgnum = Integer.parseInt(pgNum);

		// ��ü �Խñ� ���� ����
		pgDTO.setTotalCnt(Ser_M.GetMEmailCnt());
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
		List<MemberDTO> dtos = Ser_M.GetMEmailList(map);

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

		String mlink = "m_mail";
		// �� �Ѱ��ֱ�
		model.addAttribute("mlink", mlink);
		model.addAttribute("chkGVal", "");
		model.addAttribute("chkSVal", "");
		model.addAttribute("chkAdVal", "");
		model.addAttribute("mlink2", "");
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

	// ���Ἥ�񽺷� �̵�
	@RequestMapping("/pay_service")
	public String GoPayService(HttpServletRequest request, Model model) {
		// parameter�� string���� �� �����ϱ� �������� �� �̽� map���� �����ߵȴ� �̽�
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> Pmap = new HashMap<String, Object>();
		Map<String, Object> Mmap = new HashMap<String, Object>();
		MemberDTO mdto = new MemberDTO();
		String mId = request.getParameter("mId");
		PageDTO PpgDTO = new PageDTO();
		PageDTO MpgDTO = new PageDTO();
		String PpgNum = request.getParameter("ppgnum");
		String MpgNum = request.getParameter("mpgnum");
		// �̿��� ��
		int Usercnt = Ser_M.GetServiceUserCnt();
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM");
		Calendar time = Calendar.getInstance();
		String now = format1.format(time.getTime());

		if (PpgNum == null || PpgNum.equals("")) // null�̸� �� ó��
			PpgNum = "1";
		if (MpgNum == null || MpgNum.equals("")) // null�̸� �� ó��
			MpgNum = "1";
		// int������
		int Ppgnum = Integer.parseInt(PpgNum);
		int Mpgnum = Integer.parseInt(MpgNum);

		// ȸ�� ���� ��������
		if (mId == null || mId.equals("")) {
			mdto = new MemberDTO(0, "", "", "", "", "", "", 0, 0, 0, 0, 0, 0, "", "", "", "", "", "", "", 0, 0, 0, "");
		} else {
			mdto = Ser_M.GetMInfo(mId);
		}

		map.put("mId", mdto.getM_ID());
		map.put("plDT", "%" + now + "%");
		int pl_chk = Ser_PL.getChkPay(map);
		if (pl_chk > 0) { // ������
			mdto.setM_PAY_CHK("����");
		} else { // ������
			mdto.setM_PAY_CHK("�̳�");
		}

		// ��ü �Խñ� ���� ����
		PpgDTO.setTotalCnt(Ser_PL.getMemberPLListCnt(mId));
		MpgDTO.setTotalCnt(Ser_M.PageCnt());
		// ���� ������ ��ȣ ����
		PpgDTO.setPageNum(Ppgnum);
		MpgDTO.setPageNum(Mpgnum);
		// ������ �Խù� �� ����
		PpgDTO.setContentNum(5);
		MpgDTO.setContentNum(10);
		// ���� ������ ��� ����
		PpgDTO.setCurBlock(Ppgnum);
		MpgDTO.setCurBlock(Mpgnum);
		// ������ ��� ��ȣ ����
		PpgDTO.setLastBlock(PpgDTO.getTotalCnt());
		MpgDTO.setLastBlock(MpgDTO.getTotalCnt());
		// ���� ȭ��ǥ ǥ�� ����
		PpgDTO.prevnext(Ppgnum);
		MpgDTO.prevnext(Mpgnum);
		// ���� ������ ����
		PpgDTO.setStartPage(PpgDTO.getCurBlock());
		MpgDTO.setStartPage(MpgDTO.getCurBlock());
		// ������ ������ ����
		PpgDTO.setEndPage(PpgDTO.getLastBlock(), PpgDTO.getCurBlock());
		MpgDTO.setEndPage(MpgDTO.getLastBlock(), MpgDTO.getCurBlock());

		Pmap.put("mId", mId);
		Pmap.put("startNum", (Ppgnum - 1) * PpgDTO.getContentNum());
		Pmap.put("ContentNum", PpgDTO.getContentNum());

		List<PayLogDTO> pdtos = Ser_PL.getMemberPLList(Pmap);

		Mmap.put("startNum", (Mpgnum - 1) * MpgDTO.getContentNum());
		Mmap.put("ContentNum", MpgDTO.getContentNum());

		// ȸ�� ��� �ҷ�����
		List<MemberDTO> mdtos = Ser_M.GetPlMList(Mmap);

		for (int i = 0; i < mdtos.size(); i++) {
			map.put("mId", mdtos.get(i).getM_ID());
			map.put("plDT", "%" + now + "%");
			int chk_pl = Ser_PL.getChkPay(map);
			if (chk_pl > 0) { // ������
				mdtos.get(i).setM_PAY_CHK("����");
			} else { // ������
				mdtos.get(i).setM_PAY_CHK("�̳�");
			}
		}

		int pl_first = (Ppgnum - 1) * PpgDTO.getContentNum() + 1;
		int pl_last = pl_first + PpgDTO.getContentNum();
		int pl_j = 0;
		// �� �Խù� ��ȣ
		for (int i = pl_first; i < pl_last; i++) {
			if (i <= PpgDTO.getTotalCnt()) {
				pdtos.get(pl_j).setNUM(i);
				pl_j++;
			}
		}

		int m_first = (Mpgnum - 1) * MpgDTO.getContentNum() + 1;
		int m_last = m_first + MpgDTO.getContentNum();
		int m_j = 0;
		// �� �Խù� ��ȣ
		for (int i = m_first; i < m_last; i++) {
			if (i <= MpgDTO.getTotalCnt()) {
				mdtos.get(m_j).setNUM(i);
				m_j++;
			}
		}

		String plprev = "", plnext = ""; // <, >

		if (PpgDTO.isPrev()) { // ���� ����� �����ϴ°�
			plprev = "<";
		}
		if (PpgDTO.isNext()) { // ���� ����� �����ϴ°�
			plnext = ">";
		}

		String mprev = "", mnext = ""; // <, >

		if (MpgDTO.isPrev()) { // ���� ����� �����ϴ°�
			mprev = "<";
		}
		if (MpgDTO.isNext()) { // ���� ����� �����ϴ°�
			mnext = ">";
		}

		// �Ѿ�� ��µ� ������ ��ȣ��
		int[] plpg, mpg;
		if (pdtos.size() == 0) {
			plpg = new int[1];
		} else {
			plpg = new int[(PpgDTO.getEndPage() - PpgDTO.getStartPage()) + 1];
		}
		if (mdtos.size() == 0) {
			mpg = new int[1];
		} else {
			mpg = new int[(MpgDTO.getEndPage() - MpgDTO.getStartPage()) + 1];
		}

		// ������ �ڹٽ�ũ��Ʈ �Ἥ ����ߵǴµ� ���� ���� �� �����ͼ� ��ġ�ؾ� �ȴٱ淡
		// �׳� ���⼭ �� ����ؼ� �Ѱ��ֱ�
		pl_j = 0;
		for (int i = PpgDTO.getStartPage(); i < PpgDTO.getStartPage() + PpgDTO.getContentNum(); i++) {
			if (plpg.length > pl_j)
				plpg[pl_j] = i;
			pl_j++;
		}
		m_j = 0;
		for (int i = MpgDTO.getStartPage(); i < MpgDTO.getStartPage() + MpgDTO.getContentNum(); i++) {
			if (mpg.length > m_j)
				mpg[m_j] = i;
			m_j++;
		}

		model.addAttribute("Usercnt", Usercnt);
		model.addAttribute("ThisMoney", (Usercnt * 19900));
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

		model.addAttribute("mPLLink", "pay_service?mId=" + mId);
		model.addAttribute("mdtos", mdtos);
		model.addAttribute("mbefore", MpgDTO.getStartPage() - 1);
		model.addAttribute("mafter", MpgDTO.getEndPage() + 1);
		model.addAttribute("mprev", mprev);
		model.addAttribute("mpg", mpg);
		model.addAttribute("mnext", mnext);
		if (MpgDTO.getTotalCnt() % MpgDTO.getContentNum() > 0)
			model.addAttribute("mlast", MpgDTO.getTotalCnt() / MpgDTO.getContentNum() + 1);
		else
			model.addAttribute("mlast", MpgDTO.getTotalCnt() / MpgDTO.getContentNum());

		return "/pay_service";
	}
}
