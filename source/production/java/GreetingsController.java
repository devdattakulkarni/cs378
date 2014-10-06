import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class GreetingsController {
	
	@ResponseBody
    @RequestMapping(value = "/personalGreeting", params = {"user"}, method=RequestMethod.POST)
    public String getGreeting(@RequestParam("user") String userName)
    {
        return "Hi, " + userName; 
    }
	
	@ResponseBody
    @RequestMapping(value = "/cookie", params = {"user"}, method=RequestMethod.POST)
    public String getGreetingWithCookie(
    		@CookieValue(value = "friendOrStranger", defaultValue = "stranger") String cookieVal,
    		@RequestParam("user") String userName,
    		HttpServletResponse response)
    {
		// create cookie and set it in response
	    Cookie cookie = new Cookie("friendOrStranger", "friend " + userName);
	    //cookie.setMaxAge(10000);
	    response.addCookie(cookie);
		return "/greetingsCookie.jsp";
    }	

	@ResponseBody
    @RequestMapping(value = "/greeting", method=RequestMethod.GET)
    public String getGreeting()
    {
        return "Hello world";
    }   
	
}
