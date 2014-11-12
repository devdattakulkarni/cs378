package cs378.assignment6.resources;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/myeavesdrop")
public class MeetingInfoApplication extends Application {
	
	   private Set<Object> singletons = new HashSet<Object>();
	   
	   public MeetingInfoApplication() {
		   singletons.add(new MeetingInfoResource());
	   }
	   
	   @Override
	   public Set<Object> getSingletons() {
	      return singletons;
	   }
}
