package com.company.service;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import com.company.dao.EmployeeDAO;
import com.company.dto.EmployeeDTO;

public class EmployeeService implements EService{
	@Inject
	EmployeeDAO eDAO;

	// ·Î±×ÀÎ
	@Override
	public EmployeeDTO ELogin(HttpServletRequest request) {
		return eDAO.ELogin(request.getParameter("eId"));
	}

}
