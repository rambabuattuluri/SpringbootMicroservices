package com.springboot.microservice.CurrencyConversionFactor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="conversion_factor")
public class ConversionFactorBean {

	@Id
	@GeneratedValue
	@Column(name="id")
	private Long id;
	@Column(name="from_country_code")
	private String fromCountryCode;
	@Column(name="to_country_code")
	private String toCountryCode; 
	@Column(name="to_conversion_factor")
	private double conversionFactor;
	
	public ConversionFactorBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ConversionFactorBean(Long id, String fromCountryCode, String toCountryCode, double conversionFactor) {
		super();
		this.id = id;
		this.fromCountryCode = fromCountryCode;
		this.toCountryCode = toCountryCode;
		this.conversionFactor = conversionFactor;
	}
	
	@Override
	public String toString() {
		return "ConversionFactorBean [id=" + id + ", fromCountryCode=" + fromCountryCode + ", toCountryCode="
				+ toCountryCode + ", conversionFactor=" + conversionFactor + "]";
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

	public double getConversionFactor() {
		return conversionFactor;
	}

	public void setConversionFactor(double conversionFactor) {
		this.conversionFactor = conversionFactor;
	}

}
