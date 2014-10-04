import java.util.ListIterator;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/*
 * 
 */
public class JSoupParser {
	
	public static void main(String [] args) {
	
		try {
		    
		    Document doc = Jsoup.connect("http://eavesdrop.openstack.org/irclogs/%23heat/").get();
		    Elements links = doc.select("body a");
		    
		    ListIterator<Element> iter = links.listIterator();
		    while(iter.hasNext()) {
		    	Element e = (Element) iter.next();
		    	System.out.println("Element name:" + e.nodeName() + " Element:" + e.toString());
		    }
		    
		} catch (Exception exp) {
			exp.printStackTrace();
		}
	}
}