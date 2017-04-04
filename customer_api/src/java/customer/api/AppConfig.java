package customer.api;

import javax.annotation.sql.DataSourceDefinition;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@DataSourceDefinition(
		name = "java:app/jdbc/customer",
		className = "com.mysql.jdbc.jdbc2.optional.MysqlConnectionPoolDataSource",
		serverName = "localhost",
		portNumber = 3306,
		databaseName = "derby_sample",
		user = "fred",
		password = "fred"
)
@ApplicationPath("/api")
public class AppConfig extends Application {
	
}
