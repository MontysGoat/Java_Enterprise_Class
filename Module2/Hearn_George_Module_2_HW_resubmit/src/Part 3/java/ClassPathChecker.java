

import java.net.URL;
import java.net.URLClassLoader;

public class ClassPathChecker{

   public static void CheckClassPath () {

        ClassLoader cl = ClassLoader.getSystemClassLoader();

        URL[] urls = ((URLClassLoader)cl).getURLs();

        for(URL url: urls){
        	System.out.println(url.getFile());
        }
         
   }
}