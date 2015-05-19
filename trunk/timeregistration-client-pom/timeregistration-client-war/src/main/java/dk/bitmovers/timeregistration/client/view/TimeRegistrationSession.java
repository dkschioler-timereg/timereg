package dk.bitmovers.timeregistration.client.view;

import java.io.Serializable;

import dk.bitmovers.timeregistration.model.Client;
import dk.bitmovers.timeregistration.model.Provider;
import dk.bitmovers.timeregistration.model.User;

public class TimeRegistrationSession implements Serializable {

	private static final long serialVersionUID = 1L;

	private Client currentClient;

	private Provider currentProvider;

	private String lastRequest;
	
	private final User ueer;

	public TimeRegistrationSession(User ueer) {
		super();
		this.ueer = ueer;
	}

	public Client getCurrentClient() {
		return currentClient;
	}

	public void setCurrentClient(Client currentClient) {
		this.currentClient = currentClient;
	}

	public Provider getCurrentProvider() {
		return currentProvider;
	}

	public void setCurrentProvider(Provider currentProvider) {
		this.currentProvider = currentProvider;
	}

	public String getLastRequest() {
		return lastRequest;
	}

	public void setLastRequest(String lastRequest) {
		this.lastRequest = lastRequest;
	}

	public User getUeer() {
		return ueer;
	}
	
	
	

}
