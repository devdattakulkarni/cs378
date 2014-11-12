package cs378.assignment6.service;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import cs378.assignment6.domain.Event;
import cs378.assignment6.etl.Loader;

public class MeetingDataMgrService implements Loader {
	private SessionFactory sessionFactory;
	
	public MeetingDataMgrService() {
		// A SessionFactory is set up once for an application
        sessionFactory = new Configuration()
                .configure() // configures settings from hibernate.cfg.xml
                .buildSessionFactory();
	}
	
	public void load(Object source) throws Exception {
		insertMeetingRecord(source);
	}
	
	private void insertMeetingRecord(Object obj) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save( new Event( "Meeting record 1", new Date() ) );
		session.save( new Event( "Meeting record 2", new Date() ) );
		session.getTransaction().commit();
		session.close();
	}
}
