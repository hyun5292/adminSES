package com.company.dto;

public class EmployeeDTO {
	private int NUM = 0;
	private String M_ID = "";
	private String M_PW = "";
	private String M_NAME = "";
	private String M_KIND = "";
	private String E_EMAIL1 = "";
	private String E_EMAIL2 = "";
	private int M_TEL1 = 0;
	private int M_TEL2 = 0;
	private int M_TEL3 = 0; 
	private String E_ENTER_DT = "";
	private String E_RESIGN_DT = "";
	private String E_RANK = "";
	private String E_AUTH = "";
	
	public EmployeeDTO() {}
	
	public EmployeeDTO(String e_ID, String e_PW, String e_NAME, String e_KIND, String e_EMAIL1, String e_EMAIL2, int e_TEL1,
			int e_TEL2, int e_TEL3, String e_ENTER_DT, String e_RESIGN_DT, String e_RANK, String e_AUTH) {
		super();
		M_ID = e_ID;
		M_PW = e_PW;
		M_NAME = e_NAME;
		M_KIND = e_KIND;
		E_EMAIL1 = e_EMAIL1;
		E_EMAIL2 = e_EMAIL2;
		M_TEL1 = e_TEL1;
		M_TEL2 = e_TEL2;
		M_TEL3 = e_TEL3;
		E_ENTER_DT = e_ENTER_DT;
		E_RESIGN_DT = e_RESIGN_DT;
		E_RANK = e_RANK;
		E_AUTH = e_AUTH;
	}

	public int getNUM() {
		return NUM;
	}

	public void setNUM(int nUM) {
		NUM = nUM;
	}
	public String getM_ID() {
		return M_ID;
	}
	public void setM_ID(String e_ID) {
		M_ID = e_ID;
	}
	public String getM_PW() {
		return M_PW;
	}
	public void setM_PW(String e_PW) {
		M_PW = e_PW;
	}
	public String getM_NAME() {
		return M_NAME;
	}
	public void setM_NAME(String e_NAME) {
		M_NAME = e_NAME;
	}
	public String getM_KIND() {
		return M_KIND;
	}
	public void setM_KIND(String m_KIND) {
		M_KIND = "Á÷¿ø";
	}
	public String getE_EMAIL1() {
		return E_EMAIL1;
	}
	public void setE_EMAIL1(String e_EMAIL1) {
		E_EMAIL1 = e_EMAIL1;
	}
	public String getE_EMAIL2() {
		return E_EMAIL2;
	}
	public void setE_EMAIL2(String e_EMAIL2) {
		E_EMAIL2 = e_EMAIL2;
	}
	public int getM_TEL1() {
		return M_TEL1;
	}
	public void setM_TEL1(int e_TEL1) {
		M_TEL1 = e_TEL1;
	}
	public int getM_TEL2() {
		return M_TEL2;
	}
	public void setM_TEL2(int e_TEL2) {
		M_TEL2 = e_TEL2;
	}
	public int getM_TEL3() {
		return M_TEL3;
	}
	public void setM_TEL3(int e_TEL3) {
		M_TEL3 = e_TEL3;
	}
	
	public String getE_ENTER_DT() {
		return E_ENTER_DT;
	}

	public void setE_ENTER_DT(String e_ENTER_DT) {
		E_ENTER_DT = e_ENTER_DT;
	}

	public String getE_RESIGN_DT() {
		return E_RESIGN_DT;
	}

	public void setE_RESIGN_DT(String e_RESIGN_DT) {
		E_RESIGN_DT = e_RESIGN_DT;
	}

	public String getE_RANK() {
		return E_RANK;
	}
	public void setE_RANK(String e_RANK) {
		E_RANK = e_RANK;
	}

	public String getE_AUTH() {
		return E_AUTH;
	}

	public void setE_AUTH(String e_AUTH) {
		E_AUTH = e_AUTH;
	}
	
}

/*
CREATE TABLE `employee` (
	`M_ID` VARCHAR(20) NOT NULL DEFAULT '' COLLATE 'utf8_general_ci',
	`M_PW` VARCHAR(20) NOT NULL DEFAULT '' COLLATE 'utf8_general_ci',
	`M_NAME` VARCHAR(20) NOT NULL DEFAULT '' COLLATE 'utf8_general_ci',
	`E_EMAIL1` VARCHAR(40) NOT NULL DEFAULT '' COLLATE 'utf8_general_ci',
	`E_EMAIL2` VARCHAR(40) NOT NULL DEFAULT '' COLLATE 'utf8_general_ci',
	`M_TEL1` INT(11) NOT NULL DEFAULT '0',
	`M_TEL2` INT(11) NOT NULL DEFAULT '0',
	`M_TEL3` INT(11) NOT NULL DEFAULT '0',
	`E_ENTER_DT` TIMESTAMP NOT NULL DEFAULT dayofmonth(0 - 0 - 0) ON UPDATE current_timestamp(),
	`E_RESIGN_DT` TIMESTAMP NOT NULL DEFAULT dayofmonth(0 - 0 - 0) ON UPDATE current_timestamp(),
	`E_RANK` VARCHAR(20) NOT NULL DEFAULT '' COLLATE 'utf8_general_ci',
	`E_AUTH` VARCHAR(20) NOT NULL DEFAULT 'N' COLLATE 'utf8_general_ci',
	PRIMARY KEY (`M_ID`) USING BTREE
)
COLLATE='latin1_swedish_ci'
ENGINE=InnoDB
;

*/