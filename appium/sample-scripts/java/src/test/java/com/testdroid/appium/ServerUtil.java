package com.testdroid.appium;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import org.apache.commons.codec.binary.Base64;

public class ServerUtil {

	private String protocol;
	private String host;
	private int port;
	private String context;
	private String username;
	private String password;
	private String absoluteurl;
	private Properties properties;
	private String sampleuploadfile;
	private String mobcontext;
	private String chromedriverpath;
	private String firefoxdriverpath;
	private String appcenterclientapppath;
	private static ServerUtil instance = new ServerUtil();

	public ServerUtil() {
		properties = new Properties();
		if (System.getenv("skipDatafile")!=null && System.getenv("skipDatafile").equals("true")) {
                        System.out.println("........ Inside if .........");
			protocol = System.getenv("protocol"); //Passed as a part of the command line as -Doption
			host = System.getenv("hostname");
			port = Integer.valueOf(System.getenv("port"));
			context = System.getenv("context");
			username = System.getenv("username");
			password = System.getenv("password");
			absoluteurl = System.getenv("absoluteurl");
			sampleuploadfile = System.getenv("uploadfile");
			mobcontext = System.getenv("mobcontext");
			chromedriverpath = System.getenv("chromedriverpath");
			firefoxdriverpath = System.getenv("firefoxdriverpath");
			appcenterclientapppath = System.getenv("appcenterclientapppath");
		} else {
                        System.out.println("........ Inside else .........");
			InputStream is = ClassLoader.getSystemResourceAsStream("datafile.properties");
			try {
				properties.load(is);
				protocol = properties.getProperty("protocol");
				host = properties.getProperty("hostname");
				port = Integer.valueOf(properties.getProperty("port"));
				context = properties.getProperty("context");
				username = properties.getProperty("username");
				password = properties.getProperty("password");
				absoluteurl = properties.getProperty("absoluteurl");
				sampleuploadfile = properties.getProperty("uploadfile");
				mobcontext = properties.getProperty("mobcontext");
				chromedriverpath = properties.getProperty("chromedriverpath");
				firefoxdriverpath = properties.getProperty("firefoxdriverpath");
				appcenterclientapppath = properties.getProperty("appcenterclientapppath");
			} catch (IOException e) {
				e.printStackTrace();
			} finally {

			}
		}
	}

	public ServerUtil setProperties(String pUsername, String pPassword, String pAbsoluteurl, String pUploadfile, String pContext, String pMobcontext, String pPort, String pProtocol, String pHostname, String pChromedriverpath, String pFirefoxdriverpath, String pAppcenterclientapppath) {
		protocol = pProtocol;
		host = pHostname;
		port = Integer.valueOf(pPort);
		context = pContext;
		username = pUsername;
		password = pPassword;
		absoluteurl = pAbsoluteurl;
		sampleuploadfile = pUploadfile;
		mobcontext = pMobcontext;
		chromedriverpath = pChromedriverpath;
		firefoxdriverpath = pFirefoxdriverpath;
		appcenterclientapppath = pAppcenterclientapppath;
		return instance;
	}

	public static ServerUtil getInstance() {
		return instance;
	}

	public String getProtocol() {
		return protocol;
	}

	public String getHost() {
		return host;
	}

	public int getPort() {
		return port;
	}

	public String getContext() {
		return context;
	}

	public String getMobContext() {
		return mobcontext;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getAbsoluteurl() {
		return absoluteurl;
	}

	public String getSampleUploadFile() {
		return sampleuploadfile;
	}

	public String getFirefoxDriverPath() {
		return firefoxdriverpath;
	}

	public String getChoromeDriverPath() {
		return chromedriverpath;
	}

	public String getAppCenterClientPath() {
		return appcenterclientapppath;
	}

	public String getbase64EncodedCredentials() {
		return "Basic " + new String(Base64.encodeBase64(
                (username + ":" + password).getBytes()));
	}

	public URL getUrlWithContext() {
		try {
			return new URL(getUrlWithPort().toExternalForm() + "/"
					+ getContext());
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public URL getUrlWithPort() {
		try {
			URL url = new URL(getProtocol() + "://" + getHost() + ":"
					+ getPort());
			return url;
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
