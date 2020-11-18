package com.company.dto;

public class PayLogDTO {
	private int NUM = 0;
	private String PL_NUM = "";
	private String M_ID = "";
	private String PL_TITLE = "";
	private int PL_PRICE = 0;
	private String PL_DATE = "";
	
	public PayLogDTO () {}
	
	public PayLogDTO(String pL_NUM, String m_ID, String pL_TITLE, int pL_PRICE, String pL_DATE) {
		super();
		PL_NUM = pL_NUM;
		M_ID = m_ID;
		PL_TITLE = pL_TITLE;
		PL_PRICE = pL_PRICE;
		PL_DATE = pL_DATE;
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

	public String getPL_DATE() {
		return PL_DATE;
	}

	public void setPL_DATE(String pL_DATE) {
		PL_DATE = pL_DATE;
	}
}

/*
CREATE TABLE `pay_log` (
	`PL_NUM` INT(11) NOT NULL AUTO_INCREMENT,
	`M_ID` VARCHAR(20) NOT NULL COLLATE 'utf8_general_ci',
	`PL_TITLE` VARCHAR(100) NOT NULL COLLATE 'utf8_general_ci',
	`PL_PRICE` INT(11) NOT NULL,
	`PL_DATE` TIMESTAMP NOT NULL DEFAULT dayofmonth(0 - 0 - 0) ON UPDATE current_timestamp(),
	PRIMARY KEY (`PL_NUM`) USING BTREE
)
COLLATE='latin1_swedish_ci'
ENGINE=InnoDB
AUTO_INCREMENT=28
;

*/