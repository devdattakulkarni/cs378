package com.restfully.shop.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.restfully.shop.domain.Student;
import com.restfully.shop.services.DBService;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;


/**
 * @author <a href="mailto:bill@burkecentral.com">Bill Burke</a>
 * @version $Revision: 1 $
 */
public class CustomerResourceTest
{
	DBService dbService;
	
	@Before
	public void setUp() {
		dbService = new DBService();
	}	
	
   @Test
   public void testCustomerResource() throws Exception
   {
      Client client = ClientBuilder.newClient();
      try {
         System.out.println("*** Create a new Customer ***");

         String xml = "<customer>"
                 + "<first-name>Bill</first-name>"
                 + "<last-name>Burke</last-name>"
                 + "<street>256 Clarendon Street</street>"
                 + "<city>Boston</city>"
                 + "<state>MA</state>"
                 + "<zip>02115</zip>"
                 + "<country>USA</country>"
                 + "</customer>";

         Response response = client.target("http://localhost:8080/services/customers")
                 .request().post(Entity.xml(xml));
         
         assertEquals(201, response.getStatus());
         
         if (response.getStatus() != 201) throw new RuntimeException("Failed to create");
         String location = response.getLocation().toString();
         System.out.println("Location: " + location);
         response.close();

         System.out.println("*** GET Created Customer **");
         String customer = client.target(location).request().get(String.class);
         System.out.println(customer);

         String updateCustomer = "<customer>"
                 + "<first-name>William</first-name>"
                 + "<last-name>Burke</last-name>"
                 + "<street>256 Clarendon Street</street>"
                 + "<city>Boston</city>"
                 + "<state>MA</state>"
                 + "<zip>02115</zip>"
                 + "<country>USA</country>"
                 + "</customer>";
         response = client.target(location).request().put(Entity.xml(updateCustomer));
         if (response.getStatus() != 204) throw new RuntimeException("Failed to update");
         response.close();
         System.out.println("**** After Update ***");
         customer = client.target(location).request().get(String.class);
         System.out.println(customer);
      } finally {
         client.close();
      }
   }
   
   @Test
	public void testGetStudent() {
		String city = "Austin";		
		Student student = dbService.getStudentByCity(city);
		assertNotNull(student);
	}
   
   @Test
   public void testAddStudent() {
	   String name = "devdatta";
	   String city = "Minneapolis";
	   int uteid = 111;
	   dbService.addStudent(name, city, uteid);
	   
	   Student student = dbService.getStudentByCity("Minneapolis");
	   assertNotNull(student);
   }
   
   @Test
   public void testGetStudentByName()  throws Exception {
	   String name = "\"devdatta\"; drop table student_courses_xref;";
	   dbService.getStudentByName(name);	   
   }
   
   @Test
   public void testDeleteStudent() throws Exception {
	   String name = "devdatta1";
	   String city = "Minneapolis";
	   int uteid = 111;
	   dbService.addStudent(name, city, uteid);
	   
	   Student student = dbService.getStudentByName(name);
	   assertNotNull(student);
	   
	   dbService.deleteStudentByName(name);
	   
	   student = dbService.getStudentByName(name);
	   assertNull(student);
   }   
   
   @Test
   public void testUpdateStudent() throws Exception  {
	   
	   dbService.deleteStudentByName("devdatta2");
	   
	   String name = "devdatta2";
	   String city = "Minneapolis";
	   int uteid = 111;
	   dbService.addStudent(name, city, uteid);
	   
	   Student student = dbService.getStudentByName(name);
	   assertNotNull(student);
	   assertEquals(city, student.getCity());
	   
	   String newCity = "Austin";
	   dbService.updateStudentByName(name, newCity, uteid);
	   
	   student = dbService.getStudentByName(name);
	   assertNotNull(student);
	   assertEquals(newCity, student.getCity());
   }
   
   @Test
   public void findScrollType() {
	   dbService.findScrollType();
   }
   
   @Test
   public void findConcurrencyType() {
	   dbService.findConcurrencyType();
   }
   
   @Test
   public void findTransactionIsolationLevel() {
	   dbService.findTransactionIsolationLevel();;
   }
}