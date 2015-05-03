package dk.bitmovers.timeregistration.data.provider;

import java.io.Serializable;
import java.util.Map;

public interface SearchCriteria extends Serializable {
	public Map<String, String> getCriteria();
	
}
