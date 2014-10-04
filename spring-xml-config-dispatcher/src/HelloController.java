import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
	
    private GreetingService greetingService;
    private GreetingService gs;

    @ResponseBody
    @RequestMapping(value = "/")
    public String helloWorld()
    {
        return "Hello world!";
    }
    
    @ResponseBody
   //@RequestMapping({"/", "home"})
    public String helloAgain()
    {
    	return "Hello Again";
    }
    
    @ResponseBody
    @RequestMapping(value = "/", params = {"name"}, method=RequestMethod.GET)
    public String getGreeting(@RequestParam("name") String name)
    {
        return "Invoking greeting service: " + greetingService.getGreeting(name);
    }    
    
    @ResponseBody
    @RequestMapping(value = "/", params = {"course=cs378"}, method=RequestMethod.GET)
    public String getGreeting1(@RequestParam("course") String course)
    {
        return "Course is:" + course;
    }    
    
    @ResponseBody
    @RequestMapping(value = "/", params = {"name", "lastname"})
    public String getNameAndLastName(@RequestParam("name") String name,
    						 @RequestParam("lastname") String lastname)
    {
        return "Name is:" + name + " Lastname is:" + lastname;
    }    

    @ResponseBody
    @RequestMapping(value = "/", params = {"greeting1", "greeting2"})
    public String getGreetings(@RequestParam() Map<String, String> params)
    {
    	String response = "";
    	for(String k : params.keySet()) {
    		response += "key:" + k + " value:" + params.get(k) + "<br>";
    	}
    	return response;
    }
        
    public void setGreetingService(GreetingService greetingService)
    {
        this.greetingService = greetingService;
    }
    
    public void setGs(GreetingService gs)
    {
    	this.gs = gs;
    }
}