package com.amaropticals.model;

import javax.xml.bind.annotation.XmlElement;

public class LensModel {
	
	private String sph;
	private String cyl;
	private String axial;
	
	@XmlElement(name="sph")
	public String getSph() {
		return sph;
	}
	public void setSph(String sph) {
		this.sph = sph;
	}
	
	@XmlElement(name="cyl")
	public String getCyl() {
		return cyl;
	}
	public void setCyl(String cyl) {
		this.cyl = cyl;
	}
	
	@XmlElement(name="axial")
	public String getAxial() {
		return axial;
	}
	public void setAxial(String axial) {
		this.axial = axial;
	}

}
