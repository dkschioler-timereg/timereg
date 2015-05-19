package dk.bitmovers.timeregistration.data.provider;

import java.util.Map;
import java.util.TreeMap;

public class SearchCriteriaImpl implements SearchCriteria {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final Map<String, String> criteria = new TreeMap<String, String>();

	public SearchCriteriaImpl() {

	}

	@Override
	public Map<String, String> getCriteria() {

		return criteria;
	}

	
}
