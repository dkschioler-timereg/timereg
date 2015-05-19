package dk.bitmovers.timeregistration.client.view;

import java.io.Serializable;
import java.util.List;

import dk.bitmovers.timeregistration.model.Client;
import dk.bitmovers.timeregistration.model.Provider;
import dk.bitmovers.timeregistration.model.User;
import dk.bitmovers.timeregistration.model.WorkClockEvent;
import dk.bitmovers.timeregistration.model.WorkClockEventType;

public class TimeRegistrationSession implements Serializable {

	private static final long serialVersionUID = 1L;

	private Client currentClient;

	private Provider currentProvider;
	
	private WorkClockEvent currentWorkClockEvent;

	private String lastRequest;

	private final User user;

	List<Client> clients;

	List<Provider> providers;
	
	List<WorkClockEventType> workClockEventTypes;

	public List<WorkClockEventType> getWorkClockEventTypes() {
		return workClockEventTypes;
	}

	public void setWorkClockEventTypes(List<WorkClockEventType> workCleckEventTypes) {
		this.workClockEventTypes = workCleckEventTypes;
	}

	public TimeRegistrationSession(User ueer) {
		super();
		this.user = ueer;
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

	public User getUser() {
		return user;
	}

	public List<Client> getClients() {
		return clients;
	}

	public void setClients(List<Client> clients) {
		this.clients = clients;
	}

	public List<Provider> getProviders() {
		return providers;
	}

	public void setProviders(List<Provider> providers) {
		this.providers = providers;
	}

	public WorkClockEvent getCurrentWorkClockEvent() {
		return currentWorkClockEvent;
	}

	public void setCurrentWorkClockEvent(WorkClockEvent currentWorkClockEvent) {
		this.currentWorkClockEvent = currentWorkClockEvent;
	}

	@Override
	public String toString() {
		return "TimeRegistrationSession [currentClient=" + currentClient + ", currentProvider=" + currentProvider
				+ ", currentWorkClockEvent=" + currentWorkClockEvent + ", lastRequest=" + lastRequest + ", user=" + user + ", clients="
				+ clients + ", providers=" + providers + ", workClockEventTypes=" + workClockEventTypes + "]";
	}
	
	

}
