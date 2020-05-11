package com.elcom.ehotel.Sojo.dao;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.ws.rs.core.Response;
import com.elcom.DBI.SubProParam;
import com.elcom.ehotel.Sojo.model.Params;
import com.elcom.ehotel.Sojo.util.Config;
import com.elcom.ehotel.Sojo.util.SQL;

public class SoJoDao {
	// Refers the DB broker object
	// To log application
	// private static final Logger logger =
	// LogManager.getLogger(BrowserProgDao.class);
	// Read configuration params
	Config config = new Config();

	// TC_ApiGetListRoom
	@SuppressWarnings("unchecked")
	public List<HashMap<String, String>> getListRoom() {
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		Vector<String> outParam = new Vector<String>();
		SubProParam subOut = new SubProParam(outParam, "STRING_ARR", 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.GET_LIST_ROOMS, params);
			if ((params != null) & (params.size() > 0)) {
				subOut = (SubProParam) params.get(params.size() - 1);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		for (int i = 0; i < outParam.size(); i += 1) {
			HashMap<String, String> room = new HashMap<String, String>();
			room.put("roomNumber", outParam.get(i));
			list.add(room);
		}
		return list;
	}

	// TC_ApiGetListRoomTV
	@SuppressWarnings("unchecked")
	public List<HashMap<String, String>> getListDevice(String roomID) {
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(roomID), 0);
		params.add(in);
		Vector<String> outParam = new Vector<String>();
		SubProParam subOut = new SubProParam(outParam, "STRING_ARR", 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.GET_LIST_DEVICES, params);
			if ((params != null) & (params.size() > 0)) {
				subOut = (SubProParam) params.get(params.size() - 1);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		for (int i = 0; i < outParam.size(); i += 2) {
			HashMap<String, String> devices = new HashMap<String, String>();
			devices.put("seritvkey", outParam.get(i));
			devices.put("ip", outParam.get(i + 1));
			list.add(devices);
		}
		return list;
	}

	// TC_ApiRegTablet
	@SuppressWarnings("unchecked")
	public String registerTablet(String key, String seri) {
		String result = "-1";
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(key), 0);
		params.add(in);
		in = new SubProParam(new String(seri), 0);
		params.add(in);
		SubProParam subOut = new SubProParam(new String(), 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.REGISTER_TABLET, params);
			if ((params != null) & (params.size() > 0)) {
				SubProParam paramOut = (SubProParam) params.get(params.size() - 1);
				result = paramOut.getString().trim();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}

	// TC_ApiWelcome
	@SuppressWarnings("unchecked")
	public Map<String, Object> getDataWelcome(String key) {
		Map<String, Object> dataWelcome = new HashMap<String, Object>();
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(key), 0);
		params.add(in);
		Vector<String> outParam = new Vector<String>();
		SubProParam subOut = new SubProParam(outParam, "STRING_ARR", 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.GET_DATA_WELCOME, params);
			if ((params != null) & (params.size() > 0)) {
				subOut = (SubProParam) params.get(params.size() - 1);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		dataWelcome.put("roomNumber", outParam.get(0));
		dataWelcome.put("guestName", outParam.get(1));
		dataWelcome.put("background", outParam.get(2));

		for (int i = 4; i < outParam.size(); i += 4) {
			HashMap<String, String> lang = new HashMap<String, String>();
			lang.put("langId", outParam.get(i));
			lang.put("langName", outParam.get(i + 1));
			lang.put("langImage", outParam.get(i + 2));
			lang.put("langCode", outParam.get(i + 3));
			list.add(lang);
		}
		dataWelcome.put("language", list);
		return dataWelcome;
	}

	// TC_ApiSetlang
	@SuppressWarnings("unchecked")
	public List<HashMap<String, String>> setLanguageAndGetMain(String key, String langId) {
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(key), 0);
		params.add(in);
		in = new SubProParam(new String(langId), 0);
		params.add(in);
		Vector<String> outParam = new Vector<String>();
		SubProParam subOut = new SubProParam(outParam, "STRING_ARR", 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.SET_LANGUAGE_GET_MAIN, params);
			if ((params != null) & (params.size() > 0)) {
				subOut = (SubProParam) params.get(params.size() - 1);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		for (int i = 0; i < outParam.size(); i += 4) {
			String id = "id";
			String name = "name";
			String pic = "pic";
			String cod = "cod";
			HashMap<String, String> dataMain = new HashMap<String, String>();
			dataMain.put(id, outParam.get(i));
			dataMain.put(name, outParam.get(i + 1));
			dataMain.put(pic, outParam.get(i + 2));
			dataMain.put(cod, outParam.get(i + 3));
			list.add(dataMain);
		}
		return list;
	}

	// TC_ApiGetFeedBack
	@SuppressWarnings("unchecked")
	public List<HashMap<String, String>> getFeedback(String key) {
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(key), 0);
		params.add(in);
		Vector<String> outParam = new Vector<String>();
		SubProParam subOut = new SubProParam(outParam, "STRING_ARR", 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.GET_FEEDBACK, params);
			if ((params != null) & (params.size() > 0)) {
				subOut = (SubProParam) params.get(params.size() - 1);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		for (int i = 0; i < outParam.size(); i += 2) {
			String id = "id";
			String name = "name";
			HashMap<String, String> data = new HashMap<String, String>();
			data.put(id, outParam.get(i));
			data.put(name, outParam.get(i + 1));
			list.add(data);
		}
		return list;
	}

	// TC_ApiGetWeather
	@SuppressWarnings("unchecked")
	public List<HashMap<String, String>> getWeather(String key) {
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(key), 0);
		params.add(in);
		Vector<String> outParam = new Vector<String>();
		SubProParam subOut = new SubProParam(outParam, "STRING_ARR", 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.GET_WEATHER, params);
			if ((params != null) & (params.size() > 0)) {
				subOut = (SubProParam) params.get(params.size() - 1);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		for (int i = 0; i < outParam.size(); i += 10) {
			HashMap<String, String> dataWeather = new HashMap<String, String>();
			dataWeather.put("id", outParam.get(i));
			dataWeather.put("date", outParam.get(i + 1));
			dataWeather.put("temp", outParam.get(i + 2));
			dataWeather.put("tempmin", outParam.get(i + 3));
			dataWeather.put("tempmax", outParam.get(i + 4));
			dataWeather.put("day", outParam.get(i + 5));
			dataWeather.put("image", outParam.get(i + 6));
			dataWeather.put("detail", outParam.get(i + 7));
			dataWeather.put("winspeed", outParam.get(i + 8));
			dataWeather.put("humidity", outParam.get(i + 9));
			list.add(dataWeather);
		}
		return list;
	}

	// TC_ApiGetSubjectTV
	@SuppressWarnings("unchecked")
	public List<HashMap<String, String>> getListSubjectsChannels(String key) {
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(key), 0);
		params.add(in);
		Vector<String> outParam = new Vector<String>();
		SubProParam subOut = new SubProParam(outParam, "STRING_ARR", 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.GET_LIST_SUBS_CHANEL, params);
			if ((params != null) & (params.size() > 0)) {
				subOut = (SubProParam) params.get(params.size() - 1);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		for (int i = 0; i < outParam.size(); i += 3) {
			HashMap<String, String> dataWelcome = new HashMap<String, String>();
			dataWelcome.put("idSubs", outParam.get(i));
			dataWelcome.put("nameSubs", outParam.get(i + 1));
			dataWelcome.put("imgSubs", outParam.get(i + 2));
			list.add(dataWelcome);
		}
		return list;
	}

	// TC_ApiGetListTV
	@SuppressWarnings("unchecked")
	public List<HashMap<String, String>> getListChannelsBySubjects(String key, String subjectId) {
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(key), 0);
		params.add(in);
		in = new SubProParam(new String(subjectId), 0);
		params.add(in);
		Vector<String> outParam = new Vector<String>();
		SubProParam subOut = new SubProParam(outParam, "STRING_ARR", 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.GET_LIST_CHANEL_BY_SUBS, params);
			if ((params != null) & (params.size() > 0)) {
				subOut = (SubProParam) params.get(params.size() - 1);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		for (int i = 0; i < outParam.size(); i += 5) {
			HashMap<String, String> dataWelcome = new HashMap<String, String>();
			dataWelcome.put("idChanel", outParam.get(i));
			dataWelcome.put("nameChanel", outParam.get(i + 1));
			dataWelcome.put("indexChanel", outParam.get(i + 2));
			dataWelcome.put("urlChanel", outParam.get(i + 3));
			dataWelcome.put("imgChanel", outParam.get(i + 4));
			list.add(dataWelcome);
		}
		return list;
	}

	// TC_ApiPlayChannel
	public Response sendRequestPlayChanel(Params para) {
		Response r = null;
		String message = "sucessfully";
		try {
			r = Response.status(200).entity(message).build();
			DatagramSocket socket;
			DatagramPacket packet;
			InetAddress address;
			socket = new DatagramSocket();
			String dip = config.getDbiTVIp();
			address = InetAddress.getByName(dip);
			int port = Integer.parseInt(config.getDbiTVPort());
			String mess = "TC_ApiPlayChannel" + "$" + para.getUrl();
			String messSendRequest = "<SDAP/1.0>SERVER 100 MESSAGE SHOW MESSAGE_TYPE=FORWARD MESSAGE=\"" + mess
					+ "\"</SDAP/1.0>";
			byte messageSend[] = messSendRequest.getBytes();
			packet = new DatagramPacket(messageSend, messageSend.length, address, port);
			socket.send(packet);
			socket.close();
		} catch (RuntimeException e) {
			message = "Exception in play chanel " + e;
			r = Response.status(409).entity(message).build();
			e.printStackTrace();
		} catch (Exception e) {
			message = "Exception in play chanel " + e;
			r = Response.status(409).entity(message).build();
			e.printStackTrace();
		}

		return r;
	}

	// TC_ApiUpDownChannel
	public Response sendRequestUpDownChanel(Params para) {
		Response r = null;
		String message = "sucessfully";
		try {
			r = Response.status(200).entity(message).build();
			DatagramSocket socket;
			DatagramPacket packet;
			InetAddress address;
			socket = new DatagramSocket();
			String dip = config.getDbiTVIp();
			address = InetAddress.getByName(dip);
			int port = Integer.parseInt(config.getDbiTVPort());
			String mess = "TC_ApiUpDownChannel" + "$" + para.getKeytablet() + "$" + para.getNumber() + "$"
					+ para.getStep();
			String messSendRequest = "<SDAP/1.0>SERVER 100 MESSAGE SHOW MESSAGE_TYPE=FORWARD MESSAGE=\"" + mess
					+ "\"</SDAP/1.0>";
			byte messageSend[] = messSendRequest.getBytes();
			packet = new DatagramPacket(messageSend, messageSend.length, address, port);
			socket.send(packet);
			socket.close();
		} catch (RuntimeException e) {
			message = "Exception " + e;
			r = Response.status(409).entity(message).build();
			e.printStackTrace();
		} catch (Exception e) {
			message = "Exception " + e;
			r = Response.status(409).entity(message).build();
			e.printStackTrace();
		}

		return r;
	}

	// TC_ApiGetSubjectVideo
	@SuppressWarnings("unchecked")
	public List<HashMap<String, String>> getSubjectVideo(String key) {
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(key), 0);
		params.add(in);
		Vector<String> outParam = new Vector<String>();
		SubProParam subOut = new SubProParam(outParam, "STRING_ARR", 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.GET_SUBS_VIDEO, params);
			if ((params != null) & (params.size() > 0)) {
				subOut = (SubProParam) params.get(params.size() - 1);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		for (int i = 0; i < outParam.size(); i += 3) {
			HashMap<String, String> data = new HashMap<String, String>();
			data.put("idSubs", outParam.get(i));
			data.put("nameSubs", outParam.get(i + 1));
			data.put("imgSubs", outParam.get(i + 2));
			list.add(data);
		}
		return list;
	}

	// TC_ApiGetListVideo
	@SuppressWarnings("unchecked")
	public List<HashMap<String, String>> getListVideoBySubjects(String key, String vdsubjectId) {
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(key), 0);
		params.add(in);
		in = new SubProParam(new String(vdsubjectId), 0);
		params.add(in);
		Vector<String> outParam = new Vector<String>();
		SubProParam subOut = new SubProParam(outParam, "STRING_ARR", 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.GET_LIST_VIDEO_BY_SUBS, params);
			if ((params != null) & (params.size() > 0)) {
				subOut = (SubProParam) params.get(params.size() - 1);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		for (int i = 0; i < outParam.size(); i += 4) {
			HashMap<String, String> data = new HashMap<String, String>();
			data.put("id", outParam.get(i));
			data.put("name", outParam.get(i + 1));
			data.put("url", outParam.get(i + 2));
			data.put("img", outParam.get(i + 3));
			list.add(data);
		}
		return list;
	}

	// TC_ApiPlayVideo
	public Response sendRequestPlayVideo(Params para) {
		Response r = null;
		String message = "sucessfully";
		try {
			r = Response.status(200).entity(message).build();
			DatagramSocket socket;
			DatagramPacket packet;
			InetAddress address;
			socket = new DatagramSocket();
			String dip = config.getDbiTVIp();
			address = InetAddress.getByName(dip);
			int port = Integer.parseInt(config.getDbiTVPort());
			String mess = "TC_ApiPlayVideo" + "$" + para.getUrl();
			String messSendRequest = "<SDAP/1.0>SERVER 100 MESSAGE SHOW MESSAGE_TYPE=FORWARD MESSAGE=\"" + mess
					+ "\"</SDAP/1.0>";
			byte messageSend[] = messSendRequest.getBytes();
			packet = new DatagramPacket(messageSend, messageSend.length, address, port);
			socket.send(packet);
			socket.close();
		} catch (RuntimeException e) {
			message = "Exception in play chanel " + e;
			r = Response.status(409).entity(message).build();
			e.printStackTrace();
		} catch (Exception e) {
			message = "Exception in play chanel " + e;
			r = Response.status(409).entity(message).build();
			e.printStackTrace();
		}

		return r;
	}

	// TC_ApiGetSubjectMusic
	@SuppressWarnings("unchecked")
	public List<HashMap<String, String>> getSubjectMusic(String key) {
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(key), 0);
		params.add(in);
		Vector<String> outParam = new Vector<String>();
		SubProParam subOut = new SubProParam(outParam, "STRING_ARR", 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.GET_SUBS_MUSIC, params);
			if ((params != null) & (params.size() > 0)) {
				subOut = (SubProParam) params.get(params.size() - 1);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		for (int i = 0; i < outParam.size(); i += 3) {
			HashMap<String, String> data = new HashMap<String, String>();
			data.put("idSubs", outParam.get(i));
			data.put("nameSubs", outParam.get(i + 1));
			data.put("imgSubs", outParam.get(i + 2));
			list.add(data);
		}
		return list;
	}

	// TC_ApiGetListMusic
	@SuppressWarnings("unchecked")
	public List<HashMap<String, String>> getListMusicBySubjects(String key, String msubjectId) {
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(key), 0);
		params.add(in);
		in = new SubProParam(new String(msubjectId), 0);
		params.add(in);
		Vector<String> outParam = new Vector<String>();
		SubProParam subOut = new SubProParam(outParam, "STRING_ARR", 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.GET_LIST_MUSIC_BY_SUBS, params);
			if ((params != null) & (params.size() > 0)) {
				subOut = (SubProParam) params.get(params.size() - 1);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		for (int i = 0; i < outParam.size(); i += 3) {
			HashMap<String, String> data = new HashMap<String, String>();
			data.put("id", outParam.get(i));
			data.put("name", outParam.get(i + 1));
			data.put("url", outParam.get(i + 2));
			list.add(data);
		}
		return list;
	}

	// TC_ApiPlayMusic
	public Response sendRequestPlayMusic(Params para) {
		Response r = null;
		String message = "sucessfully";
		try {
			r = Response.status(200).entity(message).build();
			DatagramSocket socket;
			DatagramPacket packet;
			InetAddress address;
			socket = new DatagramSocket();
			String dip = config.getDbiTVIp();
			address = InetAddress.getByName(dip);
			int port = Integer.parseInt(config.getDbiTVPort());
			String mess = "TC_ApiPlayMusic" + "$" + para.getUrl();
			String messSendRequest = "<SDAP/1.0>SERVER 100 MESSAGE SHOW MESSAGE_TYPE=FORWARD MESSAGE=\"" + mess
					+ "\"</SDAP/1.0>";
			byte messageSend[] = messSendRequest.getBytes();
			packet = new DatagramPacket(messageSend, messageSend.length, address, port);
			socket.send(packet);
			socket.close();
		} catch (RuntimeException e) {
			message = "Exception in play chanel " + e;
			r = Response.status(409).entity(message).build();
			e.printStackTrace();
		} catch (Exception e) {
			message = "Exception in play chanel " + e;
			r = Response.status(409).entity(message).build();
			e.printStackTrace();
		}

		return r;
	}

	// TC_ApiGetListMain
	@SuppressWarnings("unchecked")
	public List<HashMap<String, String>> getListMain(String key) {
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(key), 0);
		params.add(in);
		Vector<String> outParam = new Vector<String>();
		SubProParam subOut = new SubProParam(outParam, "STRING_ARR", 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.GET_LIST_MAIN, params);
			if ((params != null) & (params.size() > 0)) {
				subOut = (SubProParam) params.get(params.size() - 1);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		for (int i = 0; i < outParam.size(); i += 4) {
			HashMap<String, String> data = new HashMap<String, String>();
			data.put("id", outParam.get(i));
			data.put("name", outParam.get(i + 1));
			data.put("img", outParam.get(i + 2));
			data.put("code", outParam.get(i + 3));
			list.add(data);
		}
		return list;
	}

	// TC_ApiGetListAirPort
	@SuppressWarnings("unchecked")
	public List<HashMap<String, String>> getListAirPort(String key) {
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(key), 0);
		params.add(in);
		Vector<String> outParam = new Vector<String>();
		SubProParam subOut = new SubProParam(outParam, "STRING_ARR", 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.GET_LIST_AIR_PORT, params);
			if ((params != null) & (params.size() > 0)) {
				subOut = (SubProParam) params.get(params.size() - 1);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		for (int i = 0; i < outParam.size(); i += 4) {
			HashMap<String, String> data = new HashMap<String, String>();
			data.put("id", outParam.get(i));
			data.put("name", outParam.get(i + 1));
			data.put("img", outParam.get(i + 2));
			data.put("code", outParam.get(i + 3));
			list.add(data);
		}
		return list;
	}

	// TC_ApiGetListSubjectInfo
	@SuppressWarnings("unchecked")
	public List<HashMap<String, String>> getListSubjectInfo(String key, String mainId) {
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(key), 0);
		params.add(in);
		in = new SubProParam(new String(mainId), 0);
		params.add(in);
		Vector<String> outParam = new Vector<String>();
		SubProParam subOut = new SubProParam(outParam, "STRING_ARR", 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.GET_LIST_SUBS_INFO, params);
			if ((params != null) & (params.size() > 0)) {
				subOut = (SubProParam) params.get(params.size() - 1);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		for (int i = 0; i < outParam.size(); i += 3) {
			HashMap<String, String> data = new HashMap<String, String>();
			data.put("id", outParam.get(i));
			data.put("name", outParam.get(i + 1));
			data.put("img", outParam.get(i + 2));
			list.add(data);
		}
		return list;
	}

	// TC_ApiGetListItemInfo
	@SuppressWarnings("unchecked")
	public List<HashMap<String, String>> getListItemInfo(String key, String subsId) {
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(key), 0);
		params.add(in);
		in = new SubProParam(new String(subsId), 0);
		params.add(in);
		Vector<String> outParam = new Vector<String>();
		SubProParam subOut = new SubProParam(outParam, "STRING_ARR", 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.GET_LIST_ITEM_INFO, params);
			if ((params != null) & (params.size() > 0)) {
				subOut = (SubProParam) params.get(params.size() - 1);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		for (int i = 0; i < outParam.size(); i += 3) {
			HashMap<String, String> data = new HashMap<String, String>();
			data.put("id", outParam.get(i));
			data.put("name", outParam.get(i + 1));
			data.put("description", outParam.get(i + 2));
			list.add(data);
		}
		return list;
	}

	// TC_ApiGetListSubjectOrder
	@SuppressWarnings("unchecked")
	public List<HashMap<String, String>> getListSubjectOrder(String key, String subject) {
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(key), 0);
		params.add(in);
		in = new SubProParam(new String(subject), 0);
		params.add(in);
		Vector<String> outParam = new Vector<String>();
		SubProParam subOut = new SubProParam(outParam, "STRING_ARR", 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.GET_LIST_SUBS_ORDER, params);
			if ((params != null) & (params.size() > 0)) {
				subOut = (SubProParam) params.get(params.size() - 1);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		for (int i = 0; i < outParam.size(); i += 3) {
			HashMap<String, String> data = new HashMap<String, String>();
			data.put("id", outParam.get(i));
			data.put("name", outParam.get(i + 1));
			data.put("img", outParam.get(i + 2));
			list.add(data);
		}
		return list;
	}

	// TC_ApiGetListItemOrder
	@SuppressWarnings("unchecked")
	public List<HashMap<String, String>> getListItemOrder(String key, String subject) {
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(key), 0);
		params.add(in);
		in = new SubProParam(new String(subject), 0);
		params.add(in);
		Vector<String> outParam = new Vector<String>();
		SubProParam subOut = new SubProParam(outParam, "STRING_ARR", 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.GET_LIST_ITEM_INFO, params);
			if ((params != null) & (params.size() > 0)) {
				subOut = (SubProParam) params.get(params.size() - 1);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		for (int i = 0; i < outParam.size(); i += 6) {
			HashMap<String, String> data = new HashMap<String, String>();
			data.put("id", outParam.get(i));
			data.put("name", outParam.get(i + 1));
			data.put("detail", outParam.get(i + 2));
			data.put("price", outParam.get(i + 3));
			data.put("unit", outParam.get(i + 4));
			data.put("image", outParam.get(i + 5));
			list.add(data);
		}
		return list;
	}

	// TC_ApiGetFlightSchedule
	@SuppressWarnings("unchecked")
	public List<HashMap<String, String>> getFlightSchedule(String key, String airportId) {
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(key), 0);
		params.add(in);
		in = new SubProParam(new String(airportId), 0);
		params.add(in);
		Vector<String> outParam = new Vector<String>();
		SubProParam subOut = new SubProParam(outParam, "STRING_ARR", 1);
		params.add(subOut);
		try {
			params = SQL.broker.executeSubPro(SQL.GET_FLIGHT_SCHEDULE, params);
			if ((params != null) & (params.size() > 0)) {
				subOut = (SubProParam) params.get(params.size() - 1);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		for (int i = 0; i < outParam.size(); i += 11) {
			HashMap<String, String> data = new HashMap<String, String>();
			data.put("id", outParam.get(i));
			data.put("code", outParam.get(i + 1));
			data.put("airport_arrival", outParam.get(i + 2));
			data.put("airport_departure", outParam.get(i + 3));
			data.put("time_arrival", outParam.get(i + 4));
			data.put("time_departure", outParam.get(i + 5));
			data.put("airline", outParam.get(i + 6));
			data.put("plane_type", outParam.get(i + 7));
			data.put("flight_type", outParam.get(i + 8));
			data.put("status", outParam.get(i + 9));
			data.put("logo", outParam.get(i + 10));
			list.add(data);
		}
		return list;
	}

	// TC_ApiControlVolume
	public Response sendRequestControlVolume(Params para) {
		Response r = null;
		String message = "sucessfully";
		try {
			r = Response.status(200).entity(message).build();
			DatagramSocket socket;
			DatagramPacket packet;
			InetAddress address;
			socket = new DatagramSocket();
			String dip = config.getDbiTVIp();
			address = InetAddress.getByName(dip);
			int port = Integer.parseInt(config.getDbiTVPort());
			String mess = "TC_ApiControlVolume" + "$" + para.getKeytablet() + "$" + para.getVolume();
			// String mess = volume;
			String messSendRequest = "<SDAP/1.0>SERVER 100 SET_VOLUME LEVEL " + para.getVolume() + " </SDAP/1.0>";
//			String messSendRequest = "<SDAP/1.0>SERVER 100 MESSAGE SHOW MESSAGE_TYPE=FORWARD MESSAGE=\"" + mess
//					+ "\"</SDAP/1.0>";
			byte messageSend[] = messSendRequest.getBytes();
			packet = new DatagramPacket(messageSend, messageSend.length, address, port);
			socket.send(packet);
			socket.close();
		} catch (RuntimeException e) {
			message = "Exception " + e;
			r = Response.status(409).entity(message).build();
			e.printStackTrace();
		} catch (Exception e) {
			message = "Exception " + e;
			r = Response.status(409).entity(message).build();
			e.printStackTrace();
		}

		return r;
	}

	// TC_ApiReBootTV
	public Response sendRequestReBootTV() {
		Response r = null;
		String message = "sucessfully";
		try {
			r = Response.status(200).entity(message).build();
			DatagramSocket socket;
			DatagramPacket packet;
			InetAddress address;
			socket = new DatagramSocket();
			String dip = config.getDbiTVIp();
			address = InetAddress.getByName(dip);
			int port = Integer.parseInt(config.getDbiTVPort());
			// String mess = api + "," + keyTablet + "," + volume ;
			// String mess = volume;
			String messSendRequest = "<SDAP/1.0>SERVER 100 REBOOT INSTANT</SDAP/1.0>";
//				String messSendRequest = "<SDAP/1.0>SERVER 100 MESSAGE SHOW MESSAGE_TYPE=FORWARD MESSAGE=\"" + mess
//						+ "\"</SDAP/1.0>";
			byte messageSend[] = messSendRequest.getBytes();
			packet = new DatagramPacket(messageSend, messageSend.length, address, port);
			socket.send(packet);
			socket.close();
		} catch (RuntimeException e) {
			message = "Exception " + e;
			r = Response.status(409).entity(message).build();
			e.printStackTrace();
		} catch (Exception e) {
			message = "Exception " + e;
			r = Response.status(409).entity(message).build();
			e.printStackTrace();
		}

		return r;
	}

	// TC_ApiControlSleep
	public Response sendRequestControlSleep(String api, String keyTablet, String bedTimeStory) {
		Response r = null;
		String message = "sucessfully";
		try {
			r = Response.status(200).entity(message).build();
			DatagramSocket socket;
			DatagramPacket packet;
			InetAddress address;
			socket = new DatagramSocket();
			String dip = config.getDbiTVIp();
			address = InetAddress.getByName(dip);
			int port = Integer.parseInt(config.getDbiTVPort());
			String mess = api + "," + bedTimeStory;
			String messSendRequest = "<SDAP/1.0>SERVER 100 MESSAGE SHOW MESSAGE_TYPE=FORWARD MESSAGE=\"" + mess
					+ "\"</SDAP/1.0>";
			byte messageSend[] = messSendRequest.getBytes();
			packet = new DatagramPacket(messageSend, messageSend.length, address, port);
			socket.send(packet);
			socket.close();
		} catch (RuntimeException e) {
			message = "Exception " + e;
			r = Response.status(409).entity(message).build();
			e.printStackTrace();
		} catch (Exception e) {
			message = "Exception " + e;
			r = Response.status(409).entity(message).build();
			e.printStackTrace();
		}

		return r;
	}

	// TC_ApiControlWakeUp
	public Response sendRequestControlWakeUp(String api, String keyTablet, String date, String time, String loop) {
		Response r = null;
		String message = "sucessfully";
		try {
			r = Response.status(200).entity(message).build();
			DatagramSocket socket;
			DatagramPacket packet;
			InetAddress address;
			socket = new DatagramSocket();
			String dip = config.getDbiTVIp();
			address = InetAddress.getByName(dip);
			int port = Integer.parseInt(config.getDbiTVPort());
			String mess = api + "," + keyTablet + "," + date + "," + time + "," + loop;
			String messSendRequest = "<SDAP/1.0>SERVER 100 MESSAGE SHOW MESSAGE_TYPE=FORWARD MESSAGE=\"" + mess
					+ "\"</SDAP/1.0>";
			byte messageSend[] = messSendRequest.getBytes();
			packet = new DatagramPacket(messageSend, messageSend.length, address, port);
			socket.send(packet);
			socket.close();
		} catch (RuntimeException e) {
			message = "Exception " + e;
			r = Response.status(409).entity(message).build();
			e.printStackTrace();
		} catch (Exception e) {
			message = "Exception " + e;
			r = Response.status(409).entity(message).build();
			e.printStackTrace();
		}

		return r;
	}

	// TC_ApiControlWork
	public Response sendRequestControlWork(String api, String keyTablet, String chooseMusic) {
		Response r = null;
		String message = "sucessfully";
		try {
			r = Response.status(200).entity(message).build();
			DatagramSocket socket;
			DatagramPacket packet;
			InetAddress address;
			socket = new DatagramSocket();
			String dip = config.getDbiTVIp();
			address = InetAddress.getByName(dip);
			int port = Integer.parseInt(config.getDbiTVPort());
			String mess = api + "," + keyTablet + "," + chooseMusic;
			String messSendRequest = "<SDAP/1.0>SERVER 100 MESSAGE SHOW MESSAGE_TYPE=FORWARD MESSAGE=\"" + mess
					+ "\"</SDAP/1.0>";
			byte messageSend[] = messSendRequest.getBytes();
			packet = new DatagramPacket(messageSend, messageSend.length, address, port);
			socket.send(packet);
			socket.close();
		} catch (RuntimeException e) {
			message = "Exception " + e;
			r = Response.status(409).entity(message).build();
			e.printStackTrace();
		} catch (Exception e) {
			message = "Exception " + e;
			r = Response.status(409).entity(message).build();
			e.printStackTrace();
		}

		return r;
	}

	// TC_ApiControlRelaxVideo
	public Response sendRequestRelaxVideo(String api, String keyTablet) {
		Response r = null;
		String message = "sucessfully";
		try {
			r = Response.status(200).entity(message).build();
			DatagramSocket socket;
			DatagramPacket packet;
			InetAddress address;
			socket = new DatagramSocket();
			String dip = config.getDbiTVIp();
			address = InetAddress.getByName(dip);
			int port = Integer.parseInt(config.getDbiTVPort());
			String mess = api + "," + keyTablet;
			String messSendRequest = "<SDAP/1.0>SERVER 100 MESSAGE SHOW MESSAGE_TYPE=FORWARD MESSAGE=\"" + mess
					+ "\"</SDAP/1.0>";
			byte messageSend[] = messSendRequest.getBytes();
			packet = new DatagramPacket(messageSend, messageSend.length, address, port);
			socket.send(packet);
			socket.close();
		} catch (RuntimeException e) {
			message = "Exception " + e;
			r = Response.status(409).entity(message).build();
			e.printStackTrace();
		} catch (Exception e) {
			message = "Exception " + e;
			r = Response.status(409).entity(message).build();
			e.printStackTrace();
		}

		return r;
	}

	// TC_ApiControlRelaxLightly
	public Response sendRequestRelaxLightly(String api, String keyTablet) {
		Response r = null;
		String message = "sucessfully";
		try {
			r = Response.status(200).entity(message).build();
			DatagramSocket socket;
			DatagramPacket packet;
			InetAddress address;
			socket = new DatagramSocket();
			String dip = config.getDbiTVIp();
			address = InetAddress.getByName(dip);
			int port = Integer.parseInt(config.getDbiTVPort());
			String mess = api + "," + keyTablet;
			String messSendRequest = "<SDAP/1.0>SERVER 100 MESSAGE SHOW MESSAGE_TYPE=FORWARD MESSAGE=\"" + mess
					+ "\"</SDAP/1.0>";
			byte messageSend[] = messSendRequest.getBytes();
			packet = new DatagramPacket(messageSend, messageSend.length, address, port);
			socket.send(packet);
			socket.close();
		} catch (RuntimeException e) {
			message = "Exception " + e;
			r = Response.status(409).entity(message).build();
			e.printStackTrace();
		} catch (Exception e) {
			message = "Exception " + e;
			r = Response.status(409).entity(message).build();
			e.printStackTrace();
		}

		return r;
	}

	// TC_ApiControlRelaxExciting
	public Response sendRequestRelaxExciting(String api, String keyTablet) {
		Response r = null;
		String message = "sucessfully";
		try {
			r = Response.status(200).entity(message).build();
			DatagramSocket socket;
			DatagramPacket packet;
			InetAddress address;
			socket = new DatagramSocket();
			String dip = config.getDbiTVIp();
			address = InetAddress.getByName(dip);
			int port = Integer.parseInt(config.getDbiTVPort());
			String mess = api + "," + keyTablet;
			String messSendRequest = "<SDAP/1.0>SERVER 100 MESSAGE SHOW MESSAGE_TYPE=FORWARD MESSAGE=\"" + mess
					+ "\"</SDAP/1.0>";
			byte messageSend[] = messSendRequest.getBytes();
			packet = new DatagramPacket(messageSend, messageSend.length, address, port);
			socket.send(packet);
			socket.close();
		} catch (RuntimeException e) {
			message = "Exception " + e;
			r = Response.status(409).entity(message).build();
			e.printStackTrace();
		} catch (Exception e) {
			message = "Exception " + e;
			r = Response.status(409).entity(message).build();
			e.printStackTrace();
		}

		return r;
	}

	// TC_ApiControlGetOutRoom
	public Response sendRequestGetOutRoom() {
		Response r = null;
		String message = "sucessfully";
		try {
			r = Response.status(200).entity(message).build();
			DatagramSocket socket;
			DatagramPacket packet;
			InetAddress address;
			socket = new DatagramSocket();
			String dip = config.getDbiTVIp();
			address = InetAddress.getByName(dip);
			int port = Integer.parseInt(config.getDbiTVPort());
			// String mess = api + "," + keyTablet;
			String messSendRequest = "<SDAP/1.0>SERVER 100 SYSTEM FULL_POWEROFF </SDAP/1.0>";
			// String messSendRequest = "<SDAP/1.0>SERVER 100 SYSTEM POWERON </SDAP/1.0>";

			byte messageSend[] = messSendRequest.getBytes();
			packet = new DatagramPacket(messageSend, messageSend.length, address, port);
			socket.send(packet);
			socket.close();
		} catch (RuntimeException e) {
			message = "Exception " + e;
			r = Response.status(409).entity(message).build();
			e.printStackTrace();
		} catch (Exception e) {
			message = "Exception " + e;
			r = Response.status(409).entity(message).build();
			e.printStackTrace();
		}

		return r;
	}

	// TC_ApiTurnOnTV
	public Response sendRequestTurnOnTV() {
		Response r = null;
		String message = "sucessfully";
		try {
			String request = config.getWol() + "b8:bb:af:ed:e3:99";
			String response = getRequest(request);
			if (response.equals("OK")) // sleep 15s and call open TV
			{
				// Thread.sleep(Long.parseLong(config.getSleepwol()));
				Thread.sleep(15000);
				r = Response.status(200).entity(message).build();
				DatagramSocket socket;
				DatagramPacket packet;
				InetAddress address;
				socket = new DatagramSocket();
				String dip = config.getDbiTVIp();
				address = InetAddress.getByName(dip);
				int port = Integer.parseInt(config.getDbiTVPort());
				// String mess = api + "," + keyTablet;
				// String messSendRequest = "<SDAP/1.0>SERVER 100 SYSTEM FULL_POWEROFF
				// </SDAP/1.0>";
				String messSendRequest = "<SDAP/1.0>SERVER 100 SYSTEM POWERON </SDAP/1.0>";

				byte messageSend[] = messSendRequest.getBytes();
				packet = new DatagramPacket(messageSend, messageSend.length, address, port);
				socket.send(packet);
				socket.close();
			}

		} catch (RuntimeException e) {
			message = "Exception " + e;
			r = Response.status(409).entity(message).build();
			e.printStackTrace();
		} catch (Exception e) {
			message = "Exception " + e;
			r = Response.status(409).entity(message).build();
			e.printStackTrace();
		}

		return r;
	}

	// TC_ApiBackWelcome
	public Response sendRequestBackWelcome() {
		Response r = null;
		String message = "sucessfully";
		try {
			r = Response.status(200).entity(message).build();
			DatagramSocket socket;
			DatagramPacket packet;
			InetAddress address;
			socket = new DatagramSocket();
			String dip = config.getDbiTVIp();
			address = InetAddress.getByName(dip);
			int port = Integer.parseInt(config.getDbiTVPort());
			String mess = "TC_ApiBackWelcome";
			String messSendRequest = "<SDAP/1.0>SERVER 100 MESSAGE SHOW MESSAGE_TYPE=FORWARD MESSAGE=\"" + mess
					+ "\"</SDAP/1.0>";
			byte messageSend[] = messSendRequest.getBytes();
			packet = new DatagramPacket(messageSend, messageSend.length, address, port);
			socket.send(packet);
			socket.close();
		} catch (RuntimeException e) {
			message = "Exception " + e;
			r = Response.status(409).entity(message).build();
			e.printStackTrace();
		} catch (Exception e) {
			message = "Exception " + e;
			r = Response.status(409).entity(message).build();
			e.printStackTrace();
		}

		return r;
	}

	public String getRequest(String baseUrl) {
		String rs = "";
		try {
			// String url = "http://api.agrimedia.vn/WeatherServices.asmx";
			// http://dataservice.accuweather.com/forecasts/v1/daily/5day/01?apikey=RIxIfBd6UA7smSGyIsfAsMbFWm5QxGg4&unit=17
			String url = baseUrl;
			URL obj = new URL(url);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("Content-Type", "text/xml; charset=utf-8");
			con.setDoOutput(true);
			String responseStatus = con.getResponseMessage();
			// System.out.println(responseStatus);
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			// System.out.println("response:" + response.toString());
			rs = response.toString();
		} catch (Exception e) {
			System.out.println(e);
		}
		return rs;
	}

	public static void main(String[] args) {
		SoJoDao s = new SoJoDao();
		String x = "-1";
		String keyTablet = "1900";
		String mainId = "25";
		String susId = "549";
		String airportId = "504";
		String itemId = "856";
//		System.out.println(s.getListRoom());
//		System.out.println(s.getListDevice("305"));
//		System.out.println(s.registerTablet("1900", "XTCLFOP7KAJOE"));
		// System.out.println(s.getDataWelcome("1900"));
//		System.out.println(s.setLanguageAndGetMain("1900", "2"));
		// System.out.println(s.getListChannels("1900"));
		/// System.out.println(s.getListSubjectsChannels("1900"));
		// System.out.println(s.getListChannelsBySubjects("1900", "162"));
//		System.out.println(s.getSubjectVideo("1900"));
//		System.out.println(s.getListVideoBySubjects("1900", "527"));
		//System.out.println(s.getSubjectMusic("1900"));
//		System.out.println(s.getListMusicBySubjects("1900", "550"));
//		System.out.println(s.getListMain(keyTablet));
//		System.out.println(s.getListSubjectInfo(keyTablet, mainId));
//		System.out.println(s.getListItemInfo(keyTablet, susId));
//		System.out.println(s.getListAirPort(keyTablet));
//		System.out.println(s.getFlightSchedule(keyTablet, airportId));
		System.out.println(s.getListSubjectOrder(x,x));
		System.out.println(s.getListItemOrder(x, x));
		System.out.println(s.getWeather(x));
		System.out.println(s.getFeedback(x));
	}

}
