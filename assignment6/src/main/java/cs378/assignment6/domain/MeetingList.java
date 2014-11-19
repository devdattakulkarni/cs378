package cs378.assignment6.domain;

import java.util.ArrayList;
import java.util.List;

public class MeetingList {
	
	List<Meeting> meetings;
	
	public MeetingList() {
		meetings = new ArrayList<Meeting>();
	}
	
	public void addMeeting(Meeting m) {
		meetings.add(m);
	}

}
