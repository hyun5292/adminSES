package com.company.dto;

public class EmpLogDTO {
	private int EL_NUM = 0;
	private String EL_ID = "";
	private String EL_ACTIVITY = "";
	private String EL_DATE = "";
	
	public EmpLogDTO() {}

	public EmpLogDTO(int eL_NUM, String eL_ID, String eL_ACTIVITY, String eL_DATE) {
		super();
		EL_NUM = eL_NUM;
		EL_ID = eL_ID;
		EL_ACTIVITY = eL_ACTIVITY;
		EL_DATE = eL_DATE;
	}
	
	public int getEL_NUM() {
		return EL_NUM;
	}

	public void setEL_NUM(int eL_NUM) {
		EL_NUM = eL_NUM;
	}

	public String getEL_ID() {
		return EL_ID;
	}

	public void setEL_ID(String eL_ID) {
		EL_ID = eL_ID;
	}

	public String getEL_ACTIVITY() {
		return EL_ACTIVITY;
	}

	public void setEL_ACTIVITY(String eL_ACTIVITY) {
		EL_ACTIVITY = eL_ACTIVITY;
	}

	public String getEL_DATE() {
		return EL_DATE;
	}

	public void setEL_DATE(String eL_DATE) {
		EL_DATE = eL_DATE;
	}
	
}

/*
CREATE TABLE `emp_log` (
	`EL_NUM` INT(11) NOT NULL,
	`EL_ID` VARCHAR(20) NOT NULL DEFAULT '' COLLATE 'latin1_swedish_ci',
	`EL_ACTIVITY` VARCHAR(40) NOT NULL COLLATE 'latin1_swedish_ci',
	`EL_DATE` VARCHAR(50) NOT NULL COLLATE 'latin1_swedish_ci',
	PRIMARY KEY (`EL_NUM`) USING BTREE,
	INDEX `E_ID` (`EL_ID`) USING BTREE,
	CONSTRAINT `E_ID` FOREIGN KEY (`EL_ID`) REFERENCES `adminses`.`employee` (`E_ID`) ON UPDATE RESTRICT ON DELETE RESTRICT
)
COLLATE='latin1_swedish_ci'
ENGINE=InnoDB
;
*/