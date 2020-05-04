package com.elcom.ehotel.Sojo.model;

public class Params {
	String keytablet;
	String url;
	String volume;
	Boolean plus;
	String number;
	String step;
	
	
	public void setKeytablet(String keytablet) {
		this.keytablet = keytablet;
	}
	
	public String getKeytablet() {
		return keytablet;
	}
	
	public String getUrl() {
		return url;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}

	public void setPlus(Boolean plus) {
		this.plus = plus;
	}
	
	public Boolean getPlus() {
		return plus;
	}
	
	public void setVolume(String volume) {
		this.volume = volume;
	}
	
	public String getVolume() {
		return volume;
	}
	
	public void setNumber(String number) {
		this.number = number;
	}
	
	public String getNumber() {
		return number;
	}
	
	public void setStep(String step) {
		this.step = step;
	}
	
	public String getStep() {
		return step;
	}

//	@Override
//	public String toString() {
//		return "Message: [api=" + api + "; url=\" + url + \"]";
//	}

}