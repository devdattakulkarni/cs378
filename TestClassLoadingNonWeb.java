
public class TestClassLoadingNonWeb {
	
	public static void main(String [] args) {
		
		 ClassLoader classLoader = TestClassLoadingNonWeb.class.getClassLoader();
		 System.out.println(classLoader.getClass().getName());
		 
		 ClassLoader parent = classLoader.getParent();
		 while(parent != null) {
			 System.out.println(parent.getClass().getName());
			 parent = parent.getParent();
		 }
	}
}


