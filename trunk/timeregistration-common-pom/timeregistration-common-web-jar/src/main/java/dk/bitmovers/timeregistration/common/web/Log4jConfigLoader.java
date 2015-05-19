package dk.bitmovers.timeregistration.common.web;

import java.io.InputStream;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.PropertyConfigurator;

public class Log4jConfigLoader implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {

	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		String main = (String) arg0.getServletContext().getAttribute("log4j.properties.file");

		String log4JProps = buildLog4jFilename(main);
		System.out.println("Configuring log4j from: " + log4JProps);
		InputStream resourceAsStream = null;
		try {
			resourceAsStream = Log4jConfigLoader.class.getResourceAsStream(log4JProps);
			if (resourceAsStream != null) {
				PropertyConfigurator.configure(resourceAsStream);
			} else {
				System.err.println("Did not find resource on : " + log4JProps);
			}
		} finally {
			IOUtils.closeQuietly(resourceAsStream);
		}

	}

	public String buildLog4jFilename(String primaryName) {

		String main = StringUtils.isBlank(primaryName) ? "log4j" : primaryName;
		String environment = System.getProperty("timeregistration.environment");
		environment = StringUtils.isBlank(environment) ? "" : environment + ".";
		String log4JProps = "/" + main + "." + environment + "properties";
		return log4JProps;
	}
}
