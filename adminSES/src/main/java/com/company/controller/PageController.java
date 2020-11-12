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
public class PageController {
	// ���� �������̽� ���� �ͼ� ���⼭ ����
	@Autowired
	MService Ser_M;
	@Autowired
	QService Ser_Q;
	@Autowired
	PLService Ser_PL;

	// �������� �̵�
	@RequestMapping("/main")
	public String GoMain(HttpServletRequest request, Model model) {
		int MCnt = Ser_M.GeneralCnt();
		int NoMCnt = Ser_M.GeneralNotUseCnt();

		// �� �Ѱ��ֱ�
		model.addAttribute("MCnt", MCnt);
		model.addAttribute("NoMCnt", NoMCnt);

		return "/main";
	}

	// ���� ���Ƿ� �̵�
	@RequestMapping("/qna")
	public String GoQna(HttpServletRequest request, Model model) {
		return "/qna/qna";
	}

	// ���� ���� �亯���� �̵�
	@RequestMapping("/qna_answer")
	public String GoQnaAnswer(HttpServletRequest request, Model model) {
		return "/qna/qna_answer";
	}

	// ȸ�� �˻����� �̵�
	@RequestMapping("/m_search")
	public String GoMSearch(HttpServletRequest request, Model model) {
		String pgNum = request.getParameter("pgnum");
		if (pgNum == null) // null�̸� �� ó��
			pgNum = "1";
		// int������
		int pgnum = Integer.parseInt(pgNum);
		Map<String, Object> map = new HashMap<String, Object>();
		PageDTO pgDTO = new PageDTO();

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

		List<MemberDTO> dtos = Ser_M.GetMList(map);

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
		Map<String, Object> Pmap = new HashMap<String, Object>();
		MemberDTO mdto = new MemberDTO();
		String mId = request.getParameter("mId");
		String FB = "", KT = "", N = "", G = "";
		PageDTO QpgDTO = new PageDTO();
		PageDTO PpgDTO = new PageDTO();
		String QpgNum = request.getParameter("qpgnum");
		String PpgNum = request.getParameter("ppgnum");
		if (QpgNum == null) // null�̸� �� ó��
			QpgNum = "1";
		if (PpgNum == null) // null�̸� �� ó��
			PpgNum = "1";
		// int������
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

		// ��ü �Խñ� ���� ����
		QpgDTO.setTotalCnt(Ser_Q.getMemberQListCnt(mId));
		// ���� ������ ��ȣ ����
		QpgDTO.setPageNum(Qpgnum);
		// ������ �Խù� �� ����
		QpgDTO.setContentNum(5);
		// ���� ������ ��� ����
		QpgDTO.setCurBlock(Qpgnum);
		// ������ ��� ��ȣ ����
		QpgDTO.setLastBlock(QpgDTO.getTotalCnt());
		// ���� ȭ��ǥ ǥ�� ����
		QpgDTO.prevnext(Qpgnum);
		// ���� ������ ����
		QpgDTO.setStartPage(QpgDTO.getCurBlock());
		// ������ ������ ����
		QpgDTO.setEndPage(QpgDTO.getLastBlock(), QpgDTO.getCurBlock());

		Qmap.put("mId", mId);
		Qmap.put("startNum", (Qpgnum - 1) * QpgDTO.getContentNum());
		Qmap.put("ContentNum", QpgDTO.getContentNum());

		List<QnaDTO> qdtos = Ser_Q.getMemberQList(Qmap);
		List<PayLogDTO> pdtos = Ser_PL.getMemberPLList(mId);
		
		int first = (Qpgnum - 1) * QpgDTO.getContentNum() + 1;
		int last = first + QpgDTO.getContentNum();
		int j = 0;
		// �� �Խù� ��ȣ
		for (int i = first; i < last; i++) {
			if (i <= QpgDTO.getTotalCnt()) {
				qdtos.get(j).setNUM(i);
				j++;
			}
		}

		String qprev = "", qnext = ""; // <, >

		if (QpgDTO.isPrev()) { // ���� ����� �����ϴ°�
			qprev = "<";
		}
		if (QpgDTO.isNext()) { // ���� ����� �����ϴ°�
			qnext = ">";
		}

		// �Ѿ�� ��µ� ������ ��ȣ��
		int[] qpg;
		if (qdtos.size() == 0) {
			qpg = new int[1];
		} else {
			qpg = new int[(QpgDTO.getEndPage() - QpgDTO.getStartPage()) + 1];
		}

		// ������ �ڹٽ�ũ��Ʈ �Ἥ ����ߵǴµ� ���� ���� �� �����ͼ� ��ġ�ؾ� �ȴٱ淡
		// �׳� ���⼭ �� ����ؼ� �Ѱ��ֱ�
		j = 0;
		for (int i = QpgDTO.getStartPage(); i < QpgDTO.getStartPage() + QpgDTO.getContentNum(); i++) {
			if (qpg.length > j)
				qpg[j] = i;
			j++;
		}
		for (int i = 0; i < qdtos.size(); i++) {
			if ((qdtos.get(i).getQ_REPLY() == null) || (qdtos.get(i).getQ_REPLY().equals(""))) {
				qdtos.get(i).setQ_chkREPLY("X");
			} else {
				qdtos.get(i).setQ_chkREPLY("O");
			}
		}

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

		model.addAttribute("pdtos", pdtos);

		return "/member/m_general";
	}

	// ���� ȸ������ �̵�
	@RequestMapping("/m_admin")
	public String GoMAdmin(HttpServletRequest request, Model model) {
		return "/member/m_admin";
	}

	// SNS��� �̵�
	@RequestMapping("/m_sns")
	public String GoMSns(HttpServletRequest request, Model model) {
		return "/member/m_sns";
	}

	// ȸ�� ���� �������� �̵�
	@RequestMapping("/m_mail")
	public String GoMMail(HttpServletRequest request, Model model) {
		return "/member/m_mail";
	}

	// ���Ἥ�񽺷� �̵�
	@RequestMapping("/pay_service")
	public String GoPayService(HttpServletRequest request, Model model) {
		return "/pay_service";
	}
}
