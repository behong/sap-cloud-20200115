package com.example.demo.xml;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "result")
@XmlType(name="Info", namespace="com.example.demo.xml.Address", propOrder={"zipNo", "lnmAdres", "rnAdres"})
public class Address {
	
	private String zipNo;
	private String lnmAdres;
	private String rnAdres;
	
	
	@Override
	public String toString() {
		return "Address [zipNo=" + zipNo + ", lnmAdres=" + lnmAdres + ", rnAdres=" + rnAdres + "]";
	}
	
	@Override
	public int hashCode() {
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}

	public String getZipNo() {
		return zipNo;
	}
	
	@XmlElement(name="zipNo")
	public void setZipNo(String zipNo) {
		this.zipNo = zipNo;
	}
	
	public String getLnmAdres() {
		return lnmAdres;
	}
	
	@XmlElement(name="lnmAdres")
	public void setLnmAdres(String lnmAdres) {
		this.lnmAdres = lnmAdres;
	}
	
	public String getRnAdres() {
		return rnAdres;
	}
	
	@XmlElement(name="rnAdres")
	public void setRnAdres(String rnAdres) {
		this.rnAdres = rnAdres;
	}
	
	
}
