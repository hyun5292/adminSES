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
	// ���� �������̽� ���� �ͼ� ���⼭ ����
	@Autowired
	MService Ser_M;
	@Autowired
	PLService Ser_PL;

	// �Ϲ� ȸ�� ���� ���� ��¥ �˻�
	@RequestMapping("/service_Schpaylog")
	public String EPayLogSearchedList(HttpServletRequest request, Model model) {
		Map<String, Object> Pmap = new HashMap<String, Object>();
		MemberDTO mdto = new MemberDTO();
		String mId = request.getParameter("mId");
		String pStartDT = request.getParameter("pStartDT");
		String pEndDT = request.getParameter("pEndDT");
		PageDTO PpgDTO = new PageDTO();
		String PpgNum = request.getParameter("ppgnum");
		// �̿��� ��
		int Usercnt = Ser_M.GetServiceUserCnt();

		if (PpgNum == null || PpgNum.equals("")) // null�̸� �� ó��
			PpgNum = "1";
		// int������
		int Ppgnum = Integer.parseInt(PpgNum);

		// ȸ�� ���� ��������
		if (mId == null || mId.equals("")) {
			mdto = new MemberDTO(0, "", "", "", "", "", "", 0, 0, 0, 0, 0, 0, "", "", "", "", "", "", "", 0, 0, 0, "");
		} else {
			mdto = Ser_M.GetMInfo(mId);
		}

		Pmap.put("mId", mId);
		Pmap.put("pSDate", pStartDT);
		Pmap.put("pEDate", pEndDT);

		// ��ü �Խñ� ���� ����
		PpgDTO.setTotalCnt(Ser_PL.getSearchedMemberPLListCnt(Pmap));
		// ���� ������ ��ȣ ����
		PpgDTO.setPageNum(Ppgnum);
		// ������ �Խù� �� ����
		PpgDTO.setContentNum(5);
		// ���� ������ ��� ����
		PpgDTO.setCurBlock(Ppgnum);
		// ������ ��� ��ȣ ����
		PpgDTO.setLastBlock(PpgDTO.getTotalCnt());
		// ���� ȭ��ǥ ǥ�� ����
		PpgDTO.prevnext(Ppgnum);
		// ���� ������ ����
		PpgDTO.setStartPage(PpgDTO.getCurBlock());
		// ������ ������ ����
		PpgDTO.setEndPage(PpgDTO.getLastBlock(), PpgDTO.getCurBlock());

		Pmap.put("startNum", (Ppgnum - 1) * PpgDTO.getContentNum());
		Pmap.put("ContentNum", PpgDTO.getContentNum());

		List<PayLogDTO> pdtos = Ser_PL.getSearchedMemberPLList(Pmap);

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

		String plprev = "", plnext = ""; // <, >

		if (PpgDTO.isPrev()) { // ���� ����� �����ϴ°�
			plprev = "<";
		}
		if (PpgDTO.isNext()) { // ���� ����� �����ϴ°�
			plnext = ">";
		}

		// �Ѿ�� ��µ� ������ ��ȣ��
		int[] plpg;
		if (pdtos.size() == 0) {
			plpg = new int[1];
		} else {
			plpg = new int[(PpgDTO.getEndPage() - PpgDTO.getStartPage()) + 1];
		}

		// ������ �ڹٽ�ũ��Ʈ �Ἥ ����ߵǴµ� ���� ���� �� �����ͼ� ��ġ�ؾ� �ȴٱ淡
		// �׳� ���⼭ �� ����ؼ� �Ѱ��ֱ�
		pl_j = 0;
		for (int i = PpgDTO.getStartPage(); i < PpgDTO.getStartPage() + PpgDTO.getContentNum(); i++) {
			if (plpg.length > pl_j)
				plpg[pl_j] = i;
			pl_j++;
		}

		// �� �Ѱ��ֱ�
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

	// ȸ�� ���� �˻�
	@RequestMapping("/schMember")
	public String schMember(HttpServletRequest request, Model model) {

		return "/pay_service";
	}
}
