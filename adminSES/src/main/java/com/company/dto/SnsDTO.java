package com.company.dto;

public class SnsDTO {
	private int NUM = 0;
	private String M_ID = "";
	private String M_NAME = "";
	private String M_KIND = "";
	private int S_TEL1 = 0;
	private int S_TEL2 = 0;
	private int S_TEL3 = 0;
	private String S_DEPT = "";
	private int S_START_DT1 = 0;
	private int S_START_DT2 = 0;
	private int S_START_DT3 = 0;
	private int S_END_DT1 = 0;
	private int S_END_DT2 = 0;
	private int S_END_DT3 = 0;

	public SnsDTO() {
	}

	public SnsDTO(String s_KIND, String s_NAME, int s_TEL1, int s_TEL2, int s_TEL3, String s_DEPT, int s_START_DT1,
			int s_START_DT2, int s_START_DT3, int s_END_DT1, int s_END_DT2, int s_END_DT3) {
		super();
		M_ID = s_KIND;
		M_NAME = s_NAME;
		S_TEL1 = s_TEL1;
		S_TEL2 = s_TEL2;
		S_TEL3 = s_TEL3;
		S_DEPT = s_DEPT;
		S_START_DT1 = s_START_DT1;
		S_START_DT2 = s_START_DT2;
		S_START_DT3 = s_START_DT3;
		S_END_DT1 = s_END_DT1;
		S_END_DT1 = s_END_DT2;
		S_END_DT1 = s_END_DT3;
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

	public void setM_ID(String m_ID) {
		M_ID = m_ID;
	}

	public String getM_NAME() {
		return M_NAME;
	}

	public void setM_NAME(String m_NAME) {
		M_NAME = m_NAME;
	}

	public String getM_KIND() {
		return M_KIND;
	}

	public void setM_KIND(String m_KIND) {
		M_KIND = "SNS»ç";
	}

	public int getM_TEL1() {
		return S_TEL1;
	}

	public void setM_TEL1(int s_TEL1) {
		S_TEL1 = s_TEL1;
	}

	public int getM_TEL2() {
		return S_TEL2;
	}

	public void setM_TEL2(int s_TEL2) {
		S_TEL2 = s_TEL2;
	}

	public int getM_TEL3() {
		return S_TEL3;
	}

	public void setM_TEL3(int s_TEL3) {
		S_TEL3 = s_TEL3;
	}

	public String getS_DEPT() {
		return S_DEPT;
	}

	public void setS_DEPT(String s_DEPT) {
		S_DEPT = s_DEPT;
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

	public int getS_START_DT1() {
		return S_START_DT1;
	}

	public void setS_START_DT1(int s_START_DT1) {
		S_START_DT1 = s_START_DT1;
	}

	public int getS_START_DT2() {
		return S_START_DT2;
	}

	public void setS_START_DT2(int s_START_DT2) {
		S_START_DT2 = s_START_DT2;
	}

	public int getS_START_DT3() {
		return S_START_DT3;
	}

	public void setS_START_DT3(int s_START_DT3) {
		S_START_DT3 = s_START_DT3;
	}

	public int getS_END_DT1() {
		return S_END_DT1;
	}

	public void setS_END_DT1(int s_END_DT1) {
		S_END_DT1 = s_END_DT1;
	}

	public int getS_END_DT2() {
		return S_END_DT2;
	}

	public void setS_END_DT2(int s_END_DT2) {
		S_END_DT2 = s_END_DT2;
	}

	public int getS_END_DT3() {
		return S_END_DT3;
	}

	public void setS_END_DT3(int s_END_DT3) {
		S_END_DT3 = s_END_DT3;
	}
	
	
}
/*
CREATE TABLE `sns` (
	`M_ID` VARCHAR(20) NOT NULL COLLATE 'utf8_general_ci',
	`M_NAME` VARCHAR(20) NOT NULL COLLATE 'utf8_general_ci',
	`M_TEL1` INT(11) NOT NULL,
	`M_TEL2` INT(11) NOT NULL,
	`M_TEL3` INT(11) NOT NULL,
	`S_DEPT` VARCHAR(20) NOT NULL COLLATE 'utf8_general_ci',
	`S_START_DT1` INT(11) NOT NULL DEFAULT '0',
	`S_START_DT2` INT(11) NOT NULL DEFAULT '0',
	`S_START_DT3` INT(11) NOT NULL DEFAULT '0',
	`S_END_DT1` INT(11) NOT NULL DEFAULT '0',
	`S_END_DT2` INT(11) NOT NULL DEFAULT '0',
	`S_END_DT3` INT(11) NOT NULL DEFAULT '0',
	PRIMARY KEY (`M_ID`) USING BTREE
)
COLLATE='latin1_swedish_ci'
ENGINE=InnoDB
;

 */