package cs378.assignment6.controller;

import java.net.URL;

import cs378.assignment6.etl.Loader;
import cs378.assignment6.etl.Reader;
import cs378.assignment6.etl.Transformer;
import cs378.assignment6.etl.impl.BasicTransformerImpl;
import cs378.assignment6.service.EavesdropReaderService;
import cs378.assignment6.service.MeetingDataMgrService;

public class ETLController {

	private Reader eavesdropReader;
	private Transformer transformer;
	private Loader meetingDataMgr;
	
	public ETLController() {
		eavesdropReader = new EavesdropReaderService();
		transformer = new BasicTransformerImpl();
		meetingDataMgr = new MeetingDataMgrService();
	}
	
	private void performETLActions() {
		try {
			URL source = new URL("http://eavesdrop.openstack.org/meetings/solum_team_meeting/2014/");
			
			// 1) Read
			Object data = eavesdropReader.read(source);
			
			// 2) Transform
			Object transformedData = transformer.transform(data);
			
			// 3) Load
			meetingDataMgr.load(transformedData);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		ETLController etlController = new ETLController();
		etlController.performETLActions();
	}
}
