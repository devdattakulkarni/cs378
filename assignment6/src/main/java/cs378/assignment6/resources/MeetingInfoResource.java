package cs378.assignment6.resources;

import java.io.IOException;
import java.io.OutputStream;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;

import cs378.assignment6.controller.ETLController;
import cs378.assignment6.domain.MeetingList;
import cs378.assignment6.etl.Reader;
import cs378.assignment6.service.MeetingDataMgrService;

@Path("/projects")
public class MeetingInfoResource {
	
	private ETLController etlController;
	private Reader meetingDataReader;
	
	public MeetingInfoResource() {
		etlController = new ETLController();
		meetingDataReader = new MeetingDataMgrService();
		
		// Start data load
		//(new Thread(etlController)).start();;
	}
	
	// Test method
	@GET
	@Path("/solum/getAll")
	public String getAll() {
		return "Hello, world";
	}
	
	@GET
	@Path("/solum/meetings")
	@Produces("application/xml")
	public Response getMeetingList() {
		
		MeetingList meetingList = null;
		try {
			meetingList = (MeetingList) meetingDataReader.read("");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Response rs = Response.ok(meetingList).build();
		
		return rs;
	}
}
