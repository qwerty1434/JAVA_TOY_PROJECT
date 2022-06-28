package com.qwerty1434.dart.dto;

public class CorpCodeDto {
	private String corp_code;
	private String corp_name;
	private String stock_code;
	private String modify_date;
	
	public CorpCodeDto(String corp_code, String corp_name, String stock_code, String modify_date) {
		super();
		this.corp_code = corp_code;
		this.corp_name = corp_name;
		this.stock_code = stock_code;
		this.modify_date = modify_date;
	}

	public String getCorp_code() {
		return corp_code;
	}

	public void setCorp_code(String corp_code) {
		this.corp_code = corp_code;
	}

	public String getCorp_name() {
		return corp_name;
	}

	public void setCorp_name(String corp_name) {
		this.corp_name = corp_name;
	}

	public String getStock_code() {
		return stock_code;
	}

	public void setStock_code(String stock_code) {
		this.stock_code = stock_code;
	}

	public String getModify_date() {
		return modify_date;
	}

	public void setModify_date(String modify_date) {
		this.modify_date = modify_date;
	}

	@Override
	public String toString() {
		return "CorpCodeDto [corp_code=" + corp_code + ", corp_name=" + corp_name + ", stock_code=" + stock_code
				+ ", modify_date=" + modify_date + "]";
	}
	
	
	
}
