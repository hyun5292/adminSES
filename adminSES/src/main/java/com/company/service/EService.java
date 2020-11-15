package com.company.service;

import javax.servlet.http.HttpServletRequest;

import com.company.dto.EmployeeDTO;

public interface EService {
	// ·Î±×ÀÎ
	public EmployeeDTO ELogin(HttpServletRequest request);
}
