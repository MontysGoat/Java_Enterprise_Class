package jndi;

import javax.naming.*;
import java.util.*;

public class App {

  public static void main(String[] args) {
    boolean isLookup = false;
    if (args.length > 1)
      isLookup = true;
    Context ctx = null;
    try {
      String url = (args.length == 1) ? args[0] : null;
      System.out.println(url);
      Hashtable env = new Hashtable();
      // This *required* property specifies the factory to be used
      // to create the context.
      env.put(Context.INITIAL_CONTEXT_FACTORY, "weblogic.jndi.WLInitialContextFactory");

      if (url != null) {
        // This property specifies the URL of the WebLogic Server that will
        // provide the naming service. Defaults to t3://localhost:7001
        env.put(Context.PROVIDER_URL, url);
      }

      ctx = new InitialContext(env);
      System.out.println("Initial context created");

      if (!isLookup) {
        try {
          ctx.createSubcontext("module2");
          System.out.println("Subcontext 'module2' created");
        } catch (NameAlreadyBoundException e) {
          System.out.println("Subcontext 'module2' already exists;" + " continuing with existing subcontext");
        }
      }

      List<StudentInfo> students;

      students = new ArrayList<StudentInfo>(
          Arrays.asList(new StudentInfo("first1", "last1", "111-111-1111", "1@1.com", "111 1st st"),
              new StudentInfo("first2", "last2", "222-222-2222", "2@2.com", "222 2nd st"),
              new StudentInfo("first3", "last3", "333-333-3333", "3@3.com", "333 3rd st")));

      int i = 1;
      for (StudentInfo student : students) {
        String bindName = "student" + i;
        if (isLookup) {
          if (((StudentInfo) ctx.lookup(bindName)).equals(student)) {
            System.out.println(bindName + " found");
          } else {
            System.out.println(bindName + " not found");
          }
        } else {
          ctx.rebind(bindName, student);
          System.out.println("Bound '" + student + "' to '" + bindName + "'");
        }
        i++;
      }
    } catch (NamingException e) {
      System.out.println(e.toString());
    } finally {
      if (ctx != null) {
        try {
          ctx.close();
        } catch (NamingException e) {
          System.out.println("Failed to close context due to: " + e);
        }
      }
    }

  }
}
