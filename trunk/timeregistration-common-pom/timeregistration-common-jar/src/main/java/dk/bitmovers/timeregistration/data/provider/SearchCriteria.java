package dk.bitmovers.timeregistration.data.provider;

import java.io.Serializable;
import java.util.Map;

import dk.bitmovers.timeregistration.model.Client;
import dk.bitmovers.timeregistration.model.ClientProject;
import dk.bitmovers.timeregistration.model.Provider;
import dk.bitmovers.timeregistration.model.User;

public interface SearchCriteria extends Serializable {
	
	public static String USER_ID = User.class.getName() + ".id";
	public static String USER_NAME = User.class.getName() + ".name";
	public static String CLIENT_ID = Client.class.getName() + ".id";
	public static String CLIENTPROJECT_ID = ClientProject.class.getName() + ".id";
	public static String PROVIDER_ID = Provider.class.getName() + ".id";
	
	public Map<String, String> getCriteria();

}
