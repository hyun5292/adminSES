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

	// �Ϲ� ȸ�� ���� ���� ��¥ �˻�
	@RequestMapping("/emp_qna_search")
	public String EQnaSearchedList(HttpServletRequest request, Model model) {
		String mId = request.getParameter("mId");
		String qStartDT = request.getParameter("qStartDT");
		String qEndDT = request.getParameter("qEndDT").split(";")[0];
		Map<String, Object> Qmap = new HashMap<String, Object>();
		MemberDTO mdto = new MemberDTO();
		String FB = "", KT = "", N = "", G = "";
		PageDTO QpgDTO = new PageDTO();
		String QpgNum = request.getParameter("qpgnum");
		if (QpgNum == null || QpgNum.equals("")) // null�̸� �� ó��
			QpgNum = "1";
		// int������
		int Qpgnum = Integer.parseInt(QpgNum);

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

		// ��ü �Խñ� ���� ����
		QpgDTO.setTotalCnt(Ser_Q.getSearchedMemberQListCnt(Qmap));
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

		Qmap.put("startNum", (Qpgnum - 1) * QpgDTO.getContentNum());
		Qmap.put("ContentNum", QpgDTO.getContentNum());

		List<QnaDTO> qdtos = Ser_Q.getSearchedMemberQList(Qmap);

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
		q_j = 0;
		for (int i = QpgDTO.getStartPage(); i < QpgDTO.getStartPage() + QpgDTO.getContentNum(); i++) {
			if (qpg.length > q_j)
				qpg[q_j] = i;
			q_j++;
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

		return "/member/m_general";
	}

}
