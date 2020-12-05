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

import com.company.dto.MemberDTO;
import com.company.dto.PageDTO;
import com.company.dto.PayLogDTO;
import com.company.dto.QnaDTO;
import com.company.service.ELService;
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
	@Autowired
	ELService Ser_EL;

	@Inject
	HttpSession session;

	// ���Ἥ�� ���� �˻�
	@RequestMapping("/PaySch")
	public String SchMPayService(HttpServletResponse response, HttpServletRequest request, Model model)
			throws IOException {
		// parameter�� string���� �� �����ϱ� �������� �� �̽� map���� �����ߵȴ� �̽�
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> Pmap = new HashMap<String, Object>();
		Map<String, Object> Mmap = new HashMap<String, Object>();
		MemberDTO mdto = new MemberDTO();
		String mId = request.getParameter("mId");
		String join = request.getParameter("join");
		String schId = request.getParameter("schId");
		String schName = request.getParameter("schName");
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

		Mmap.put("join", join);
		Mmap.put("schId", "%" + schId + "%");
		Mmap.put("schName", "%" + schName + "%");

		// ��ü �Խñ� ���� ����
		PpgDTO.setTotalCnt(Ser_PL.getMemberPLListCnt(mId));
		MpgDTO.setTotalCnt(Ser_M.GetsSchPayMCnt(Mmap));
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
		List<MemberDTO> mdtos = Ser_M.GetsSchPayM(Mmap);

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

		// �� �Ѱ��ֱ�
		model.addAttribute("join", join);
		model.addAttribute("schId", schId);
		model.addAttribute("schName", schName);
		model.addAttribute("Usercnt", Usercnt);
		model.addAttribute("ThisMoney", (Usercnt * 19900));
		model.addAttribute("mdto", mdto);
		model.addAttribute("mgPLLink",
				"PaySch?mId=" + mId + "&join=" + join + "&schId=" + schId + "&schName=" + schName);

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

	// �Ϲ� ȸ�� ���� ���� ��¥ �˻�
	@RequestMapping("/service_Schpaylog")
	public String EPayLogSearchedList(HttpServletRequest request, Model model) {
		// parameter�� string���� �� �����ϱ� �������� �� �̽� map���� �����ߵȴ� �̽�
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

	// ���Ἥ�� ���� ó��
	@RequestMapping("/dojoinpay")
	public String DoJoinPay(HttpServletResponse response, HttpServletRequest request, Model model) throws IOException {
		// parameter�� string���� �� �����ϱ� �������� �� �̽� map���� �����ߵȴ� �̽�
		Map<String, Object> map = new HashMap<String, Object>();
		String mId = request.getParameter("mId");
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH) + 1;
		int day = cal.get(Calendar.DAY_OF_MONTH);

		map.put("join", "����");
		map.put("mId", mId);
		map.put("today_year", year);
		map.put("today_month", month);
		map.put("today_date", day);

		boolean chkJoin = Ser_M.MakePLJoin(map);

		if (!chkJoin) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('������ �߻��߽��ϴ�'); history.go(-1);</script>");
			out.flush();
		}

		// parameter�� string���� �� �����ϱ� �������� �� �̽� map���� �����ߵȴ� �̽�
		Map<String, Object> session_map = new HashMap<String, Object>();
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Calendar time = Calendar.getInstance();
		String now = format1.format(time.getTime());

		session_map.put("el_Id", session.getAttribute("eId"));
		session_map.put("el_Activity", mId + " ���Ἥ�� ���� ó��");
		session_map.put("el_DT", now);

		boolean rslt = Ser_EL.WriteLog(session_map);

		if (!rslt) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('������ �߻��߽��ϴ�'); history.go(-1);</script>");
			out.flush();
		}

		return "redirect:/pay_service?mId=" + mId;
	}

	// ���Ἥ�� ���� ���� ó��
	@RequestMapping("/dontjoinpay")
	public String DontJoinPay(HttpServletResponse response, HttpServletRequest request, Model model)
			throws IOException {
		// parameter�� string���� �� �����ϱ� �������� �� �̽� map���� �����ߵȴ� �̽�
		Map<String, Object> map = new HashMap<String, Object>();
		String mId = request.getParameter("mId");

		map.put("join", "�̰���");
		map.put("mId", mId);
		map.put("today_year", "0");
		map.put("today_month", "0");
		map.put("today_date", "0");

		boolean chkJoin = Ser_M.MakePLJoin(map);

		if (!chkJoin) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('������ �߻��߽��ϴ�'); history.go(-1);</script>");
			out.flush();
		}

		// parameter�� string���� �� �����ϱ� �������� �� �̽� map���� �����ߵȴ� �̽�
		Map<String, Object> session_map = new HashMap<String, Object>();
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Calendar time = Calendar.getInstance();
		String now = format1.format(time.getTime());

		session_map.put("el_Id", session.getAttribute("eId"));
		session_map.put("el_Activity", mId + " ���Ἥ�� ���� ���� ó��");
		session_map.put("el_DT", now);

		boolean rslt = Ser_EL.WriteLog(session_map);

		if (!rslt) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('������ �߻��߽��ϴ�'); history.go(-1);</script>");
			out.flush();
		}

		return "redirect:/pay_service?mId=" + mId;
	}

	// ���Ἥ�� ���� ó��
	@RequestMapping("/dopayed")
	public String DoPayed(HttpServletResponse response, HttpServletRequest request, Model model) throws IOException {
		// parameter�� string���� �� �����ϱ� �������� �� �̽� map���� �����ߵȴ� �̽�
		Map<String, Object> map = new HashMap<String, Object>();
		String mId = request.getParameter("mId");
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar time = Calendar.getInstance();
		String now = format1.format(time.getTime());

		map.put("mId", mId);
		map.put("plTitle", "�˸� ����");
		map.put("plPrice", "19900");
		map.put("today", now);

		boolean chkPay = Ser_PL.MakePay(map);

		if (!chkPay) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('������ �߻��߽��ϴ�'); history.go(-1);</script>");
			out.flush();
		}

		// parameter�� string���� �� �����ϱ� �������� �� �̽� map���� �����ߵȴ� �̽�
		Map<String, Object> session_map = new HashMap<String, Object>();
		session_map.put("el_Id", session.getAttribute("eId"));
		session_map.put("el_Activity", mId + " ���Ἥ�� ���� ó��");
		session_map.put("el_DT", now);

		boolean rslt = Ser_EL.WriteLog(session_map);

		if (!rslt) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('������ �߻��߽��ϴ�'); history.go(-1);</script>");
			out.flush();
		}

		return "redirect:/pay_service?mId=" + mId;
	}

	// ���Ἥ�� �̳� ó��
	@RequestMapping("/dontpayed")
	public String DontPayed(HttpServletResponse response, HttpServletRequest request, Model model) throws IOException {
		// parameter�� string���� �� �����ϱ� �������� �� �̽� map���� �����ߵȴ� �̽�
		Map<String, Object> map = new HashMap<String, Object>();
		String mId = request.getParameter("mId");
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM");
		Calendar time = Calendar.getInstance();
		String now = format1.format(time.getTime());

		map.put("mId", mId);
		map.put("payDT", "%" + now + "%");

		boolean chkNoPay = Ser_PL.MakeNoPay(map);

		if (!chkNoPay) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('������ �߻��߽��ϴ�'); history.go(-1);</script>");
			out.flush();
		}

		// parameter�� string���� �� �����ϱ� �������� �� �̽� map���� �����ߵȴ� �̽�
		Map<String, Object> session_map = new HashMap<String, Object>();
		session_map.put("el_Id", session.getAttribute("eId"));
		session_map.put("el_Activity", mId + " ���Ἥ�� �̳� ó��");
		session_map.put("el_DT", now);

		boolean rslt = Ser_EL.WriteLog(session_map);

		if (!rslt) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('������ �߻��߽��ϴ�'); history.go(-1);</script>");
			out.flush();
		}

		return "redirect:/pay_service?mId=" + mId;
	}
}
