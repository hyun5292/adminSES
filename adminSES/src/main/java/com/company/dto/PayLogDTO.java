package com.company.dto;

public class PayLogDTO {
	private int NUM = 0;
	private String PL_NUM = "";
	private String M_ID = "";
	private String PL_TITLE = "";
	private int PL_PRICE = 0;
	private int PL_DATE1 = 0;
	private int PL_DATE2 = 0;
	private int PL_DATE3 = 0;
	
	public PayLogDTO () {}
	
	public PayLogDTO(String pL_NUM, String m_ID, String pL_TITLE, int pL_PRICE, int pL_DATE1, int pL_DATE2, int pL_DATE3) {
		super();
		PL_NUM = pL_NUM;
		M_ID = m_ID;
		PL_TITLE = pL_TITLE;
		PL_PRICE = pL_PRICE;
		PL_DATE1 = pL_DATE1;
		PL_DATE2 = pL_DATE2;
		PL_DATE3 = pL_DATE3;
	}

	
	public int getNUM() {
		return NUM;
	}

	public void setNUM(int nUM) {
		NUM = nUM;
	}

	public String getPL_NUM() {
		return PL_NUM;
	}

	public void setPL_NUM(String pL_NUM) {
		PL_NUM = pL_NUM;
	}

	public String getM_ID() {
		return M_ID;
	}

	public void setM_ID(String m_ID) {
		M_ID = m_ID;
	}

	public String getPL_TITLE() {
		return PL_TITLE;
	}

	public void setPL_TITLE(String pL_TITLE) {
		PL_TITLE = pL_TITLE;
	}

	public int getPL_PRICE() {
		return PL_PRICE;
	}

	public void setPL_PRICE(int pL_PRICE) {
		PL_PRICE = pL_PRICE;
	}

	public int getPL_DATE1() {
		return PL_DATE1;
	}

	public void setPL_DATE1(int pL_DATE1) {
		PL_DATE1 = pL_DATE1;
	}

	public int getPL_DATE2() {
		return PL_DATE2;
	}

	public void setPL_DATE2(int pL_DATE2) {
		PL_DATE2 = pL_DATE2;
	}

	public int getPL_DATE3() {
		return PL_DATE3;
	}

	public void setPL_DATE3(int pL_DATE3) {
		PL_DATE3 = pL_DATE3;
	}

	
	
	
}

/*
CREATE TABLE `pay_log` (
	`PL_NUM` INT(11) NOT NULL AUTO_INCREMENT,
	`M_ID` VARCHAR(20) NOT NULL COLLATE 'utf8_general_ci',
	`PL_TITLE` VARCHAR(100) NOT NULL COLLATE 'utf8_general_ci',
	`PL_PRICE` INT(11) NOT NULL,
	`PL_DATE1` INT(11) NOT NULL DEFAULT '0',
	`PL_DATE2` INT(11) NOT NULL DEFAULT '0',
	`PL_DATE3` INT(11) NOT NULL DEFAULT '0',
	PRIMARY KEY (`PL_NUM`) USING BTREE
)
COLLATE='latin1_swedish_ci'
ENGINE=InnoDB
;

*/