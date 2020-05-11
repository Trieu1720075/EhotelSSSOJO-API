package com.elcom.ehotel.Sojo.controller;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.elcom.ehotel.Sojo.dao.SoJoDao;
import com.elcom.ehotel.Sojo.model.Params;

@Path("")
public class SoJoController {

	// TC_ApiGetListRoom
	@GET
	@Path("/TC_ApiGetListRoom")
	@Produces(MediaType.APPLICATION_JSON)
	public List<HashMap<String, String>> ListRoom() {
		SoJoDao dao = new SoJoDao();
		List<HashMap<String, String>> listRoom = dao.getListRoom();
		return listRoom;
	}

	// TC_ApiGetListRoomTV
	@GET
	@Path("/TC_ApiGetListRoomTV/{roomid}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<HashMap<String, String>> ListDevices(@PathParam("roomid") String roomId) {
		SoJoDao dao = new SoJoDao();
		List<HashMap<String, String>> listDevices = dao.getListDevice(roomId);
		return listDevices;
	}

	// TC_ApiRegTablet
	@GET
	@Path("/TC_ApiRegTablet/{serikeytablet}/{serikeytv}")
	@Produces(MediaType.APPLICATION_JSON)
	public String RegisTablet(@PathParam("serikeytablet") String serikeytablet,
			@PathParam("serikeytv") String serikeytv) {
		SoJoDao dao = new SoJoDao();
		String listRegisTablet = dao.registerTablet(serikeytablet, serikeytv);
		return listRegisTablet;
	}

	// TC_ApiWelcome
	@GET
	@Path("/TC_ApiWelcome/{keytablet}")
	@Produces(MediaType.APPLICATION_JSON)
	public Map<String, Object> WelCome(@PathParam("keytablet") String keytablet) {
		SoJoDao dao = new SoJoDao();
		Map<String, Object> dataWelcome = dao.getDataWelcome(keytablet);
		return dataWelcome;
	}

	// TC_ApiSetlang
	@GET
	@Path("/TC_ApiSetlang/{keytablet}/{langid}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<HashMap<String, String>> Language(@PathParam("keytablet") String keytablet,
			@PathParam("langid") String langid) {
		SoJoDao dao = new SoJoDao();
		List<HashMap<String, String>> setLang = dao.setLanguageAndGetMain(keytablet, langid);
		return setLang;
	}

	// TC_ApiGetSubjectTV
	@GET
	@Path("/TC_ApiGetSubjectTV/{keytablet}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<HashMap<String, String>> ListSubsChanel(@PathParam("keytablet") String keytablet) {
		SoJoDao dao = new SoJoDao();
		List<HashMap<String, String>> listChanel = dao.getListSubjectsChannels(keytablet);
		return listChanel;
	}

	// TC_ApiGetListTV
	@GET
	@Path("/TC_ApiGetListTV/{keytablet}/{subjectID}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<HashMap<String, String>> ListTVChanelBySub(@PathParam("keytablet") String keytablet,
			@PathParam("subjectID") String subjectId) {
		SoJoDao dao = new SoJoDao();
		List<HashMap<String, String>> listChanelBySubs = dao.getListChannelsBySubjects(keytablet, subjectId);
		return listChanelBySubs;
	}

	// TC_ApiPlayChannel
	@POST
	@Path("/TC_ApiPlayChannel")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response PlayChanel(Params para) {
		SoJoDao dao = new SoJoDao();
		Response result = dao.sendRequestPlayChanel(para);
		return result;
	}

	// TC_ApiUpDownChannel
	@POST
	@Path("/TC_ApiUpDownChannel")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response upDownChanel(Params para) {
		SoJoDao dao = new SoJoDao();
		Response result = dao.sendRequestUpDownChanel(para);
		return result;
	}

	// TC_ApiGetSubjectVideo
	@GET
	@Path("/TC_ApiGetSubjectVideo/{keytablet}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<HashMap<String, String>> ListSubsVideo(@PathParam("keytablet") String keytablet) {
		SoJoDao dao = new SoJoDao();
		List<HashMap<String, String>> listChanel = dao.getSubjectVideo(keytablet);
		return listChanel;
	}

	// TC_ApiGetListVideo
	@GET
	@Path("/TC_ApiGetListVideo/{keytablet}/{vsubjectid}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<HashMap<String, String>> ListVideoBySub(@PathParam("keytablet") String keytablet,
			@PathParam("vsubjectid") String subjectId) {
		SoJoDao dao = new SoJoDao();
		List<HashMap<String, String>> listChanelBySubs = dao.getListVideoBySubjects(keytablet, subjectId);
		return listChanelBySubs;
	}

	// TC_ApiPlayVideo
	@POST
	@Path("/TC_ApiPlayVideo")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response PlayVideo(Params para) {
		SoJoDao dao = new SoJoDao();
		Response result = dao.sendRequestPlayVideo(para);
		return result;
	}

	// TC_ApiGetSubjectMusic
	@GET
	@Path("/TC_ApiGetSubjectMusic/{keytablet}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<HashMap<String, String>> ListSubMusic(@PathParam("keytablet") String keytablet) {
		SoJoDao dao = new SoJoDao();
		List<HashMap<String, String>> listChanel = dao.getSubjectMusic(keytablet);
		return listChanel;
	}

	// TC_ApiGetListMusic
	@GET
	@Path("/TC_ApiGetListMusic/{keytablet}/{msubjectid }")
	@Produces(MediaType.APPLICATION_JSON)
	public List<HashMap<String, String>> ListMusicBySub(@PathParam("keytablet") String keytablet,
			@PathParam("msubjectid") String subjectId) {
		SoJoDao dao = new SoJoDao();
		List<HashMap<String, String>> listChanelBySubs = dao.getListMusicBySubjects(keytablet, subjectId);
		return listChanelBySubs;
	}

	// TC_ApiPlayMusic
	@POST
	@Path("/TC_ApiPlayMusic")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response PlayMusic(Params para) {
		SoJoDao dao = new SoJoDao();
		Response result = dao.sendRequestPlayMusic(para);
		return result;
	}

	// TC_ApiGetListMain
	@GET
	@Path("/TC_ApiGetListMain/{keytablet}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<HashMap<String, String>> ListMain(@PathParam("keytablet") String keytablet) {
		SoJoDao dao = new SoJoDao();
		List<HashMap<String, String>> list = dao.getListMain(keytablet);
		return list;
	}

	// TC_ApiGetWeather
	@GET
	@Path("/TC_ApiGetWeather/{keytablet}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<HashMap<String, String>> Weather(@PathParam("keytablet") String keytablet) {
		SoJoDao dao = new SoJoDao();
		List<HashMap<String, String>> list = dao.getWeather(keytablet);
		return list;
	}

	// TC_ApiGetFeedBack
	@GET
	@Path("/TC_ApiGetFeedBack/{keytablet}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<HashMap<String, String>> FeedBack(@PathParam("keytablet") String keytablet) {
		SoJoDao dao = new SoJoDao();
		List<HashMap<String, String>> list = dao.getFeedback(keytablet);
		return list;
	}

	// TC_ApiGetListAirPort
	@GET
	@Path("/TC_ApiGetListAirPort/{keytablet}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<HashMap<String, String>> ListAirPort(@PathParam("keytablet") String keytablet) {
		SoJoDao dao = new SoJoDao();
		List<HashMap<String, String>> list = dao.getListAirPort(keytablet);
		return list;
	}

	// TC_ApiGetListSubjectInfo
	@GET
	@Path("/TC_ApiGetListSubjectInfo/{keytablet}/{mainid}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<HashMap<String, String>> ListSubjectInfo(@PathParam("keytablet") String keytablet,
			@PathParam("mainid") String subjectId) {
		SoJoDao dao = new SoJoDao();
		List<HashMap<String, String>> list = dao.getListSubjectInfo(keytablet, subjectId);
		return list;
	}

	// TC_ApiGetListItemInfo
	@GET
	@Path("/TC_ApiGetListItemInfo/{keytablet}/{subsid}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<HashMap<String, String>> ListItemInfo(@PathParam("keytablet") String keytablet,
			@PathParam("subsid") String subjectId) {
		SoJoDao dao = new SoJoDao();
		List<HashMap<String, String>> list = dao.getListItemInfo(keytablet, subjectId);
		return list;
	}

	// TC_ApiGetListSubjectOrder
	@GET
	@Path("/TC_ApiGetListSubjectOrder/{keytablet}/{subsid}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<HashMap<String, String>> ListSubjectOrder(@PathParam("keytablet") String keytablet,
			@PathParam("subsid") String subjectId) {
		SoJoDao dao = new SoJoDao();
		List<HashMap<String, String>> list = dao.getListSubjectOrder(keytablet, subjectId);
		return list;
	}

	// TC_ApiGetListItemOrder
	@GET
	@Path("/TC_ApiGetListItemOrder/{keytablet}/{subsid}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<HashMap<String, String>> ListItemOrder(@PathParam("keytablet") String keytablet,
			@PathParam("subsid") String subjectId) {
		SoJoDao dao = new SoJoDao();
		List<HashMap<String, String>> list = dao.getListItemOrder(keytablet, subjectId);
		return list;
	}

	// TC_ApiGetFlightSchedule
	@GET
	@Path("/TC_ApiGetFlightSchedule/{keytablet}/{airportid}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<HashMap<String, String>> FlightSchedule(@PathParam("keytablet") String keytablet,
			@PathParam("airportid") String subjectId) {
		SoJoDao dao = new SoJoDao();
		List<HashMap<String, String>> list = dao.getFlightSchedule(keytablet, subjectId);
		return list;
	}

	// TC_ApiControlVolume
	@POST
	@Path("/TC_ApiControlVolume")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response ControlVolume(Params para) {
		SoJoDao dao = new SoJoDao();
		Response result = dao.sendRequestControlVolume(para);
		return result;
	}

	// TC_ApiControlSleep
	@POST
	@Path("/controlSleep")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response ControlSleep(@QueryParam("api") String api, @QueryParam("keytablet") String keytablet,
			@QueryParam("bedtimestory") String bedtimestory) {
		SoJoDao dao = new SoJoDao();
		Response result = dao.sendRequestControlSleep(api, keytablet, bedtimestory);
		return result;
	}

	// not done
	// TC_ApiControlWakeup
	@POST
	@Path("/controlWakeUp")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response ControlWakeUp(@QueryParam("api") String api, @QueryParam("keytablet") String keytablet,
			@QueryParam("date") String date, @QueryParam("time") String time, @QueryParam("loop") String loop) {
		SoJoDao dao = new SoJoDao();
		Response result = dao.sendRequestControlWakeUp(api, keytablet, date, time, loop);
		return result;
	}

	// not done
	// TC_ApiControlRelaxVideo
	@POST
	@Path("/controlRelaxVideo")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response ControlRelaxVideo(@QueryParam("api") String api, @QueryParam("keytablet") String keytablet) {
		SoJoDao dao = new SoJoDao();
		Response result = dao.sendRequestRelaxVideo(api, keytablet);
		return result;
	}

	// not done
	// TC_ApiControlWork
	@POST
	@Path("/controlWork")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response ControlWork(@QueryParam("api") String api, @QueryParam("keytablet") String keytablet,
			@QueryParam("chooseMusic") String chooseMusic) {
		SoJoDao dao = new SoJoDao();
		Response result = dao.sendRequestControlWork(api, keytablet, chooseMusic);
		return result;
	}

	// not done
	// TC_ApiControlRelaxLightly
	@POST
	@Path("/controlRelaxLightly")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response ControlRelaxLightly(@QueryParam("api") String api, @QueryParam("keytablet") String keytablet) {
		SoJoDao dao = new SoJoDao();
		Response result = dao.sendRequestRelaxLightly(api, keytablet);
		return result;
	}

	// not done
	// TC_ApiControlRelaxLightly
	@POST
	@Path("/controlRelaxExciting")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response ControlRelaxExciting(@QueryParam("api") String api, @QueryParam("keytablet") String keytablet) {
		SoJoDao dao = new SoJoDao();
		Response result = dao.sendRequestRelaxExciting(api, keytablet);
		return result;
	}

	// TC_ApiControlGetOutRoom
	@POST
	@Path("/TC_ApiControlGetOutRoom")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response ControlGetOutRoom() {
		SoJoDao dao = new SoJoDao();
		Response result = dao.sendRequestGetOutRoom();
		return result;
	}

	// TC_ApiBackWelcome
	@POST
	@Path("/TC_ApiBackWelcome")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response BackWelcome() {
		SoJoDao dao = new SoJoDao();
		Response result = dao.sendRequestBackWelcome();
		return result;
	}

	// TC_ApiReBootTV
	@POST
	@Path("/TC_ApiReBootTV")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response ReBootTV() {
		SoJoDao dao = new SoJoDao();
		Response result = dao.sendRequestReBootTV();
		return result;
	}

	// TC_ApiReBootTV
	@POST
	@Path("/TC_ApiTurnOnTV")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response TurnOnTV() {
		SoJoDao dao = new SoJoDao();
		Response result = dao.sendRequestTurnOnTV();
		return result;
	}

}