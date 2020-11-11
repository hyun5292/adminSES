package com.company.dto;

public class SnsDTO {
	private String S_KIND = "";
	private String S_NAME = "";
	private int S_TEL1 = 0;
	private int S_TEL2 = 0;
	private int S_TEL3 = 0;
	private String S_DEPT = "";
	private String S_START_DT = "";
	private String S_END_DT = "";
	
	public SnsDTO() {}

	public SnsDTO(String s_KIND, String s_NAME, int s_TEL1, int s_TEL2, int s_TEL3, String s_DEPT, String s_START_DT,
			String s_END_DT) {
		super();
		S_KIND = s_KIND;
		S_NAME = s_NAME;
		S_TEL1 = s_TEL1;
		S_TEL2 = s_TEL2;
		S_TEL3 = s_TEL3;
		S_DEPT = s_DEPT;
		S_START_DT = s_START_DT;
		S_END_DT = s_END_DT;
	}

	public String getS_KIND() {
		return S_KIND;
	}

	public void setS_KIND(String s_KIND) {
		S_KIND = s_KIND;
	}

	public String getS_NAME() {
		return S_NAME;
	}

	public void setS_NAME(String s_NAME) {
		S_NAME = s_NAME;
	}

	public int getS_TEL1() {
		return S_TEL1;
	}

	public void setS_TEL1(int s_TEL1) {
		S_TEL1 = s_TEL1;
	}

	public int getS_TEL2() {
		return S_TEL2;
	}

	public void setS_TEL2(int s_TEL2) {
		S_TEL2 = s_TEL2;
	}

	public int getS_TEL3() {
		return S_TEL3;
	}

	public void setS_TEL3(int s_TEL3) {
		S_TEL3 = s_TEL3;
	}

	public String getS_DEPT() {
		return S_DEPT;
	}

	public void setS_DEPT(String s_DEPT) {
		S_DEPT = s_DEPT;
	}

	public String getS_START_DT() {
		return S_START_DT;
	}

	public void setS_START_DT(String s_START_DT) {
		S_START_DT = s_START_DT;
	}

	public String getS_END_DT() {
		return S_END_DT;
	}

	public void setS_END_DT(String s_END_DT) {
		S_END_DT = s_END_DT;
	}
	
	
}
/*
CREATE TABLE `sns` (
`S_KIND` VARCHAR(20) NOT NULL COLLATE 'latin1_swedish_ci',
`S_NAME` VARCHAR(20) NOT NULL COLLATE 'latin1_swedish_ci',
`S_TEL1` INT(11) NOT NULL,
`S_TEL2` INT(11) NOT NULL,
`S_TEL3` INT(11) NOT NULL,
`S_DEPT` VARCHAR(30) NOT NULL COLLATE 'latin1_swedish_ci',
`S_START_DT` VARCHAR(50) NOT NULL COLLATE 'latin1_swedish_ci',
`S_END_DT` VARCHAR(50) NOT NULL COLLATE 'latin1_swedish_ci',
PRIMARY KEY (`S_KIND`) USING BTREE
)
COLLATE='latin1_swedish_ci'
ENGINE=InnoDB
;
*/