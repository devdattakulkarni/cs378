package cs378.assignment6.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table( name = "MEETINGS" )
public class Meeting {

	private String name;
	
	private Set<Event> events;
	
	public Meeting() {
		this.events = new HashSet<Event>();
	}
	
	public Meeting(String name) {
		this.name = name;
		this.events = new HashSet<Event>();
	}
	
	@Id	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void addEvent(Event event) {
		this.events.add(event);
		if (!event.getMeeting().equals(this)) {
			event.setMeeting(this);
		}
	}
	
	@OneToMany(mappedBy="meeting")
	//@Cascade({CascadeType.SAVE_UPDATE, CascadeType.DELETE})
	//@Cascade({CascadeType.DELETE})
	public Set<Event> getEventList() {
		return events;
	}
	
	public void setEventList(Set<Event> eventSet) {
		this.events = eventSet;
	}
}