package com.elcom.ehotel.Sojo.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Properties;

public class Config {

	private String dbiTVPort = "";
	private String dbiTVIp = "";
	private String dbiHost = "";
	private String dbiPort = "";
	private String dbiServicename = "";
	private String remoteViewport = "";

	public String getDbiHost() {
		return dbiHost;
	}

	public void setDbiHost(String dbiHost) {
		this.dbiHost = dbiHost;
	}

	public String getDbiPort() {
		return dbiPort;
	}

	public void setDbiPort(String dbiPort) {
		this.dbiPort = dbiPort;
	}

	public String getdbiServicename() {
		return dbiServicename;
	}

	public void setdbiServicename(String dbiServicename) {
		this.dbiServicename = dbiServicename;
	}

	public String getremoteViewport() {
		return remoteViewport;
	}

	public void setremoteViewport(String remoteViewport) {
		this.remoteViewport = remoteViewport;
	}
	
	public String getDbiTVPort() {
		return dbiTVPort;
	}

	public void setDbiTVPort(String dbiTVPort) {
		this.dbiTVPort = dbiTVPort;
	}
	public String getDbiTVIp() {
		return dbiTVIp;
	}

	public void setDbiTVIp(String dbiTVIp) {
		this.dbiTVIp = dbiTVIp;
	}

	public Config() {
		Properties prop = new Properties();
		String configFileName = "config.properties";
		InputStream input = getClass().getClassLoader().getResourceAsStream(configFileName);
		try {
			prop.load(input);
			dbiTVPort = prop.getProperty("ehoteldbi.TVPort");
			dbiTVIp = prop.getProperty("ehoteldbi.TVIp");
			dbiHost = prop.getProperty("ehoteldbi.dbihostname");
			dbiPort = prop.getProperty("ehoteldbi.dbiport");
			dbiServicename = prop.getProperty("ehoteldbi.dbiservicename");
			remoteViewport = prop.getProperty("ehoteldbi.remoteviewport");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				input.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
