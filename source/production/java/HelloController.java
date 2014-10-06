
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
 
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
 
@Controller
public class HelloController {
 
    @RequestMapping(value = "/hitCounter")
    public String hitCounter(
            @CookieValue(value = "hitCounter", defaultValue = "0") Long hitCounter,
            HttpServletResponse response) {
 
        // increment hit counter
        hitCounter++;
 
        // create cookie and set it in response
        Cookie cookie = new Cookie("hitCounter", hitCounter.toString());
        response.addCookie(cookie);
 
        // render hello.jsp page
        return "hello";
    }
 
}