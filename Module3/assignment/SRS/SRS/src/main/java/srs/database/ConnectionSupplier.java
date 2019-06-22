package srs.database;

import java.util.*;
import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.*;
import javax.sql.DataSource;

public class ConnectionSupplier {
  public static Connection GetConnection(String source, String url) throws NamingException, SQLException {
    Connection conn = null;
    Context ctx = null;
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
    DataSource ds = (javax.sql.DataSource) ctx.lookup(source);
    java.sql.Connection connection = (java.sql.Connection) ds.getConnection();
    return connection;
  }
}