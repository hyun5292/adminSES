package com.company.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Repository
public class PageController {
	// 메인으로 이동
	@RequestMapping("/main")
	public String GoMain(HttpServletRequest request, Model model) {
		return "/main";
	}

	// 받은 문의로 이동
	@RequestMapping("/qna")
	public String GoQna(HttpServletRequest request, Model model) {
		return "/qna/qna";
	}

	// 받은 문의 답변으로 이동
	@RequestMapping("/qna_answer")
	public String GoQnaAnswer(HttpServletRequest request, Model model) {
		return "/qna/qna_answer";
	}

	// 회원 검색으로 이동
	@RequestMapping("/m_search")
	public String GoMSearch(HttpServletRequest request, Model model) {
		return "/member/m_search";
	}

	// 일반 회원으로 이동
	@RequestMapping("/m_general")
	public String GoMGeneral(HttpServletRequest request, Model model) {
		return "/member/m_general";
	}

	// 직원 회원으로 이동
	@RequestMapping("/m_admin")
	public String GoMAdmin(HttpServletRequest request, Model model) {
		return "/member/m_admin";
	}

	// SNS사로 이동
	@RequestMapping("/m_sns")
	public String GoMSns(HttpServletRequest request, Model model) {
		return "/member/m_sns";
	}

	// 회원 메일 전송으로 이동
	@RequestMapping("/m_mail")
	public String GoMMail(HttpServletRequest request, Model model) {
		return "/member/m_mail";
	}

	// 유료서비스로 이동
	@RequestMapping("/pay_service")
	public String GoPayService(HttpServletRequest request, Model model) {
		return "/pay_service";
	}
}
