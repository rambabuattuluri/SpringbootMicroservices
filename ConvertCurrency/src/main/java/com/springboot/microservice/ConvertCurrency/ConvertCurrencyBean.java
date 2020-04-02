package com.springboot.microservice.ConvertCurrency;

public class ConvertCurrencyBean {
	
	private Long id;
	private String fromCountryCode;
	private String toCountryCode;
	private double convertAmount;
	private double conversionFactor;
	private double convertedAmount;
	
	public ConvertCurrencyBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ConvertCurrencyBean(Long id, String fromCountryCode, String toCountryCode, double convertAmount,
			double conversionFactor, double convertedAmount) {
		super();
		this.id = id;
		this.fromCountryCode = fromCountryCode;
		this.toCountryCode = toCountryCode;
		this.convertAmount = convertAmount;
		this.conversionFactor = conversionFactor;
		this.convertedAmount = convertedAmount;
	}

	@Override
	public String toString() {
		return "ConvertCurrencyBean [id=" + id + ", fromCountryCode=" + fromCountryCode + ", toCountryCode="
				+ toCountryCode + ", convertAmount=" + convertAmount + ", conversionFactor=" + conversionFactor
				+ ", convertedAmount=" + convertedAmount + "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFromCountryCode() {
		return fromCountryCode;
	}

	public void setFromCountryCode(String fromCountryCode) {
		this.fromCountryCode = fromCountryCode;
	}

	public String getToCountryCode() {
		return toCountryCode;
	}

	public void setToCountryCode(String toCountryCode) {
		this.toCountryCode = toCountryCode;
	}

	public double getConvertAmount() {
		return convertAmount;
	}

	public void setConvertAmount(double convertAmount) {
		this.convertAmount = convertAmount;
	}

	public double getConversionFactor() {
		return conversionFactor;
	}

	public void setConversionFactor(double conversionFactor) {
		this.conversionFactor = conversionFactor;
	}

	public double getConvertedAmount() {
		return convertedAmount;
	}

	public void setConvertedAmount(double convertedAmount) {
		this.convertedAmount = convertedAmount;
	}
	
}
