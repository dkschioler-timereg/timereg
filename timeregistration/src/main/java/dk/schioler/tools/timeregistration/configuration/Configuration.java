package dk.schioler.tools.timeregistration.configuration;

import java.util.Map;

import javax.xml.bind.JAXBException;

import dk.schioler.tools.timeregistration.model.Project;

public interface Configuration {

	public abstract Object loadUserConfigeredProjects(String file)
			throws JAXBException;

	public abstract Map<String, Object> buildProjectMap();

}