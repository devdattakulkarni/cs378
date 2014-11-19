/*
 * Hibernate, Relational Persistence for Idiomatic Java
 *
 * Copyright (c) 2010, Red Hat Inc. or third-party contributors as
 * indicated by the @author tags or express copyright attribution
 * statements applied by the authors.  All third-party contributions are
 * distributed under license by Red Hat Inc.
 *
 * This copyrighted material is made available to anyone wishing to use, modify,
 * copy, or redistribute it subject to the terms and conditions of the GNU
 * Lesser General Public License, as published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Lesser General Public License
 * for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this distribution; if not, write to:
 * Free Software Foundation, Inc.
 * 51 Franklin Street, Fifth Floor
 * Boston, MA  02110-1301  USA
 */
package cs378.assignment6;

import java.util.Date;
import java.util.List;

import junit.framework.TestCase;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.ConstraintViolationException;

import cs378.assignment6.domain.Event;
import cs378.assignment6.domain.Meeting;
import cs378.assignment6.domain.MeetingList;

/**
 * Illustrates the use of Hibernate native APIs.  The code here is unchanged from the {@code basic} example, the
 * only difference being the use of annotations to supply the metadata instead of Hibernate mapping files.
 *
 * @author Steve Ebersole
 */
public class AnnotationsIllustrationTest extends TestCase {
	private SessionFactory sessionFactory;

	@Override
	protected void setUp() throws Exception {
		// A SessionFactory is set up once for an application
        sessionFactory = new Configuration()
                .configure() // configures settings from hibernate.cfg.xml
                .buildSessionFactory();
	}

	@Override
	protected void tearDown() throws Exception {
		if ( sessionFactory != null ) {
			sessionFactory.close();
		}
	}

	@SuppressWarnings({ "unchecked" })
	public void testBasicUsage() {
		// create a couple of events...
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save( new Event( "Event which started the big bang", new Date() ) );
		session.save( new Event( "A follow up event", new Date() ) );
		session.getTransaction().commit();
		session.close();

		// now lets pull events from the database and list them
		session = sessionFactory.openSession();
        session.beginTransaction();
        List result = session.createQuery( "from Event" ).list();
		for ( Event event : (List<Event>) result ) {
			System.out.println( "Event (" + event.getDate() + ") : " + event.getTitle() );
		}
        session.getTransaction().commit();
        session.close();
	}

	@SuppressWarnings({ "unchecked" })
	public void testMeetingInsert() {
		// create a couple of events...
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save( new Meeting( "Meeting 1") );
		session.save( new Meeting( "Meeting 2") );
		session.getTransaction().commit();
		session.close();

		// now lets pull events from the database and list them
		session = sessionFactory.openSession();
        session.beginTransaction();
        List result = session.createQuery( "from Meeting" ).list();
		for ( Meeting meeting : (List<Meeting>) result ) {
			System.out.println( "Meeting (" + meeting.getName() + ") " );
		}
        session.getTransaction().commit();
        session.close();
	}
	
	@SuppressWarnings({ "unchecked" })
	public void testMeetingEventsInsert() {
		// create a couple of events...
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		Meeting m1 =  new Meeting( "Meeting_11");
		session.save(m1);
		
		Event e1 = new Event( "Meeting_11 event 1", new Date() );
		Event e2 = new Event( "Meeting_11 event 2", new Date() );
		
		e1.setMeeting(m1);
		e2.setMeeting(m1);
		
		//m1.addEvent(e1);
		//m1.addEvent(e2);
		
		session.save(e1);
		session.save(e2);

		Meeting m2 =  new Meeting( "Meeting_21");
		session.save(m2);
		
		Meeting m3 =  new Meeting( "Meeting_31");
		session.save(m3);
		
		Meeting m4 =  new Meeting( "Meeting_41");
		session.save(m4);
		
		Event e12 = new Event( "Meeting_21 event 1", new Date() );
		e12.setMeeting(m2);
		//m2.addEvent(e12);
		
		session.save(e12);		

		session.getTransaction().commit();
		session.close();

		// now lets pull events from the database and list them
		session = sessionFactory.openSession();
        session.beginTransaction();

        // Select criteria
        Criteria criteria = session.createCriteria(Event.class).
        		add(Restrictions.eq("title", "Meeting_11 event 1"));                
        List result = criteria.list();
        
		for ( Event event : (List<Event>) result ) {
			System.out.println( "Event (" + event.getTitle() + ") " );
		}
		
		// Selection criteria
		List<Event> events = session.createQuery("from Event where meeting_name='Meeting_11'").list();
		for ( Event event : events ) {
			System.out.println( "Event (" + event.getTitle() + ") " );
		}
		
		// Join criteria, querying from owning side
		List<Object[]> events1 = session.createQuery(
				"from Event e join e.meeting m where m.name ='Meeting_11'").list();
		MeetingList mList = new MeetingList();
		for ( int i = 0; i < events1.size(); i++ ) {
			Object [] arr = events1.get(i);
			Event e = (Event) arr[0];
			Meeting m = (Meeting) arr[1];
			
			mList.addMeeting(m);
			
			System.out.println( "Event (" + e.getTitle() + ") " );
			System.out.println( "Meeting (" + m.getName() + ") " );
		}
		
        session.getTransaction().commit();
        session.close();
        
        // Delete -- this will throw ConstraintViolationException
        try { 
        session = sessionFactory.openSession();
        session.beginTransaction();
        
        Meeting meetingToDelete = (Meeting)session.get(Meeting.class, "Meeting_21");
        session.delete(meetingToDelete);
        session.getTransaction().commit();
        } catch (ConstraintViolationException e) {        	
        	session.getTransaction().rollback();
        	session.close();
        	
        	session = sessionFactory.openSession();
            session.beginTransaction();
            
        	Meeting meetingToDelete = (Meeting)session.get(Meeting.class, "Meeting_31");
            session.delete(meetingToDelete);
            session.getTransaction().commit();
            session.close();
        }
        
        // Parameterized delete --
        session = sessionFactory.openSession();
        session.beginTransaction();
        
        Query q = session.createQuery("from Meeting where name = :name ");
        q.setParameter("name", "Meeting_41");
        Meeting m = (Meeting)q.list().get(0);
        session.delete(m);

        session.getTransaction().commit();
        session.close();
	}
}