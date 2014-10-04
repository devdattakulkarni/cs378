
public class GreetingServiceImpl implements GreetingService {

    public String getGreeting(String name)
    {
        return "Hello, " + name + "!";
    }
}
