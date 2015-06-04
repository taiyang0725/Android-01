package com.anxiong.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.os.Handler;
import android.os.Message;

public class MyLocation {

	/**
	 * ����Google MAP ͨ���û���ǰ��γ�� ����û���ǰ����
	 */
	private static final String GOOGLE_MAPS_API_KEY = "abcdefg";

	private LocationManager locationManager;
	private Location location;
	private String city = "������ɽ";
	private Handler handler;

	public MyLocation(Context context,Handler handler) {
		
		this.handler=handler;

		this.locationManager = (LocationManager) context
				.getSystemService(Context.LOCATION_SERVICE);

		/*
		 * ֻ�Ǽ򵥵Ļ�ȡ���� ����Ҫʵʱ���� ����������ע��
		 * locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
		 * 0, 0, listener);
		 */

		location = locationManager
				.getLastKnownLocation(LocationManager.GPS_PROVIDER);

		if (location == null) {

			location = locationManager
					.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
		}
		
	}

	private void start() {

		if (location != null) {
			new Thread(new Runnable() {

				@Override
				public void run() {
					String temp = getCityByGoogleMapApi(location);

					if (temp != null && temp.length() >= 2) {
						city = temp;
						
					}
				}
			}).start();

		}

	}

	/**
	 * ��ó���
	 * 
	 * @return
	 */
	public void getCity() {
		Message message=new Message();
		message.obj=city;
		message.what=1;
		handler.sendMessage(message);
		
	}

	/**
	 * ͨ��Google map api ����������
	 * */

	private String getCityByGoogleMapApi(Location location) {

		// http://maps.google.com/maps/geo?q=40.714224,-73.961452&output=json&oe=utf8&sensor=true_or_false&key=your_api_key
		String localCityName = "";
		HttpURLConnection connection = null;
		URL serverAddress = null;
		InputStream is = null;
		InputStreamReader isr = null;

		try {

			serverAddress = new URL("http://maps.google.com/maps/geo?q="
					+ Double.toString(location.getLatitude()) + ","
					+ Double.toString(location.getLongitude())
					+ "&output=xml&oe=utf8&sensor=true&key="
					+ GOOGLE_MAPS_API_KEY);
            
			Tools.showLog( Double.toString(location.getLatitude())+"*****"+ Double.toString(location.getLongitude()));
			connection = (HttpURLConnection) serverAddress.openConnection();
			connection.setRequestMethod("GET");
			connection.setDoOutput(true);
			connection.setReadTimeout(10000);
			connection.connect();

			is = connection.getInputStream();
			isr = new InputStreamReader(is);

			InputSource inputSource = new InputSource(isr);

			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser saxParser = factory.newSAXParser();
			XMLReader xmlReader = saxParser.getXMLReader();

			GeocodeXmlHandler handler = new GeocodeXmlHandler();
			xmlReader.setContentHandler(handler);
			xmlReader.parse(inputSource);

			localCityName = handler.getLocalityName();

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {

			e.printStackTrace();
		} catch (SAXException e) {

			e.printStackTrace();
		} finally {
			try {
				if (is != null) {
					is.close();
				}
				if (isr != null) {
					isr.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		return localCityName;
	}

	class GeocodeXmlHandler extends DefaultHandler {

		private boolean inLocalityName = false;
		private boolean finished = false;
		private StringBuilder builder;
		private String localityName;

		@Override
		public void startDocument() throws SAXException {

			super.startDocument();
			builder = new StringBuilder();
		}

		@Override
		public void startElement(String uri, String localName, String qName,
				Attributes attributes) throws SAXException {

			super.startElement(uri, localName, qName, attributes);
			if (localName.equalsIgnoreCase(localityName)) {
				this.inLocalityName = true;

			}
		}

		@Override
		public void endElement(String uri, String localName, String qName)
				throws SAXException {

			super.endElement(uri, localName, qName);
			if (!this.finished) {
				if (localName.equalsIgnoreCase(localityName)) {
					this.localityName = builder.toString();
					this.finished = true;
				}
				if (builder == null) {
					builder.setLength(0);
				}
			}

		}

		@Override
		public void characters(char[] ch, int start, int length)
				throws SAXException {
			super.characters(ch, start, length);

			if (this.inLocalityName && !this.finished) {
				if ((ch[start] != '\n') && (ch[start] != ' ')) {
					builder.append(ch, start, length);
				}

			}

		}

		public String getLocalityName() {
			return this.localityName;
		}
	}

}
