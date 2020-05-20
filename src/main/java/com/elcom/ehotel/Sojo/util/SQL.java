package com.elcom.ehotel.Sojo.util;

import com.elcom.ehotel.Sojo.dbi.IMBroker;

public class SQL {
	
	public static IMBroker broker = IMBroker.getInstance();
	public static final String GET_LIST_ROOMS = "BEGIN EAPI.getListRooms(?); END;";
	public static final String GET_LIST_DEVICES = "BEGIN EAPI.getListDevices(?,?); END;";
	public static final String REGISTER_TABLET = "BEGIN EAPI.registerTablet(?,?,?); END;";
	public static final String GET_DATA_WELCOME= "BEGIN EAPI.getDataWelcome(?,?); END;";
	public static final String SET_LANGUAGE_GET_MAIN= "BEGIN EAPI.setLanguageAndGetMain(?,?,?); END;";
	public static final String GET_LIST_CHANEL= "BEGIN EAPI.getListChannels(?,?); END;";
	public static final String GET_LIST_SUBS_CHANEL= "BEGIN EAPI.getListSubjectsChannels(?,?); END;";
	public static final String GET_LIST_CHANEL_BY_SUBS= "BEGIN EAPI.getListChannelsBySubjects(?,?,?); END;";
	public static final String GET_SUBS_VIDEO= "BEGIN EAPI.getListSubjectMovie(?,?); END;";
	public static final String GET_LIST_VIDEO_BY_SUBS= "BEGIN EAPI.getListMovie(?,?,?); END;";
	public static final String GET_SUBS_MUSIC= "BEGIN EAPI.getListSubjectMusic(?,?); END;";
	public static final String GET_LIST_MUSIC_BY_SUBS= "BEGIN EAPI.getListMusic(?,?,?); END;";
	
	public static final String GET_LIST_MAIN= "BEGIN EAPI.getListMain(?,?); END;";
	public static final String GET_LIST_SUBS_INFO= "BEGIN EAPI.getListSubjectInfo(?,?,?); END;";
	public static final String GET_LIST_ITEM_INFO= "BEGIN EAPI.getListItemInfo(?,?,?); END;";
	public static final String GET_LIST_AIR_PORT= "BEGIN EAPI.getListAirPort(?,?); END;";
	public static final String GET_FLIGHT_SCHEDULE= "BEGIN EAPI.getFlightSchedule(?,?,?); END;";
	public static final String GET_FEEDBACK= "BEGIN EAPI.getFeedback(?,?); END;";
	public static final String GET_LIST_SUBS_ORDER= "BEGIN EAPI.getListSubjectOrder(?,?,?); END;";
	public static final String GET_LIST_ITEM_ORDER= "BEGIN EAPI.getListItemOrder(?,?,?); END;";
	public static final String GET_WEATHER= "BEGIN EAPI.getWeathers(?,?); END;";
	public static final String GET_INFO_TV= "BEGIN EAPI.getInfoTV(?,?); END;";
	public static final String GET_LIST_MODE= "BEGIN EAPI.getListMode(?,?); END;";
	public static final String GET_MODE_INFO= "BEGIN EAPI.getModeInfo(?,?,?); END;";
	public static final String GET_CHANEL_MODE= "BEGIN EAPI.getChannelMode(?,?,?); END;";
		
}
