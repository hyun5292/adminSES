package com.company.service;

import javax.servlet.http.HttpServletRequest;

import com.company.dto.EmployeeDTO;

public interface EService {
	// �α���
	public EmployeeDTO ELogin(HttpServletRequest request);
}
