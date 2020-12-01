package com.company.dto;

public class LogDTO {
	private int NUM = 0;
	private String SL_NAME = "";
	private String SL_SITE = "";
	private String SU_KIND = "";
	private String L_ACTIVITY = "";
	private String L_DATE = "";
	
	public LogDTO() {}

	public LogDTO(int nUM, String sL_NAME, String sL_SITE, String sU_KIND, String l_ACTIVITY, String l_DATE) {
		super();
		NUM = nUM;
		SL_NAME = sL_NAME;
		SL_SITE = sL_SITE;
		SU_KIND = sU_KIND;
		L_ACTIVITY = l_ACTIVITY;
		L_DATE = l_DATE;
	}

	public int getNUM() {
		return NUM;
	}

	public void setNUM(int nUM) {
		NUM = nUM;
	}

	public String getSL_NAME() {
		return SL_NAME;
	}

	public void setSL_NAME(String sL_NAME) {
		SL_NAME = sL_NAME;
	}

	public String getSL_SITE() {
		return SL_SITE;
	}

	public void setSL_SITE(String sL_SITE) {
		SL_SITE = sL_SITE;
	}

	public String getSU_KIND() {
		return SU_KIND;
	}

	public void setSU_KIND(String sU_KIND) {
		SU_KIND = sU_KIND;
	}

	public String getL_ACTIVITY() {
		return L_ACTIVITY;
	}

	public void setL_ACTIVITY(String l_ACTIVITY) {
		L_ACTIVITY = l_ACTIVITY;
	}

	public String getL_DATE() {
		return L_DATE;
	}

	public void setL_DATE(String l_DATE) {
		L_DATE = l_DATE;
	}
	
	
}

/*
CREATE TABLE `log` (
	`M_ID` VARCHAR(20) NOT NULL COLLATE 'utf8_general_ci',
	`SL_NAME` VARCHAR(20) NULL DEFAULT NULL COLLATE 'utf8_general_ci',
	`SL_SITE` VARCHAR(200) NULL DEFAULT NULL COLLATE 'utf8_general_ci',
	`SU_KIND` VARCHAR(40) NULL DEFAULT NULL COLLATE 'utf8_general_ci',
	`L_ACTIVITY` VARCHAR(20) NOT NULL COLLATE 'utf8_general_ci',
	`L_DATE` TIMESTAMP NOT NULL DEFAULT dayofmonth(0 - 0 - 0)
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB
;

 */