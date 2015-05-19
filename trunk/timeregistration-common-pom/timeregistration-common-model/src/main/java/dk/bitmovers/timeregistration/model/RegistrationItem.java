package dk.bitmovers.timeregistration.model;

// Generated May 3, 2015 11:09:22 AM by Hibernate Tools 4.3.1

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * RegistrationItem generated by hbm2java
 */
public class RegistrationItem implements java.io.Serializable {

	private int id;
	private ClientProject clientProject;
	private ProviderAccount providerAccount;
	private User user;
	private Date created;
	private String isActive;
	private Date ended;
	private String name;
	private Set<RegistrationSubmitted> registrationSubmitteds = new HashSet<RegistrationSubmitted>(
			0);
	private Set<RegistrationEvent> registrationEvents = new HashSet<RegistrationEvent>(
			0);

	public RegistrationItem() {
	}

	public RegistrationItem(int id, ClientProject clientProject,
			ProviderAccount providerAccount, User user, Date created,
			String isActive, String name) {
		this.id = id;
		this.clientProject = clientProject;
		this.providerAccount = providerAccount;
		this.user = user;
		this.created = created;
		this.isActive = isActive;
		this.name = name;
	}

	public RegistrationItem(int id, ClientProject clientProject,
			ProviderAccount providerAccount, User user, Date created,
			String isActive, Date ended, String name,
			Set<RegistrationSubmitted> registrationSubmitteds,
			Set<RegistrationEvent> registrationEvents) {
		this.id = id;
		this.clientProject = clientProject;
		this.providerAccount = providerAccount;
		this.user = user;
		this.created = created;
		this.isActive = isActive;
		this.ended = ended;
		this.name = name;
		this.registrationSubmitteds = registrationSubmitteds;
		this.registrationEvents = registrationEvents;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ClientProject getClientProject() {
		return this.clientProject;
	}

	public void setClientProject(ClientProject clientProject) {
		this.clientProject = clientProject;
	}

	public ProviderAccount getProviderAccount() {
		return this.providerAccount;
	}

	public void setProviderAccount(ProviderAccount providerAccount) {
		this.providerAccount = providerAccount;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getCreated() {
		return this.created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public String getIsActive() {
		return this.isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	public Date getEnded() {
		return this.ended;
	}

	public void setEnded(Date ended) {
		this.ended = ended;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<RegistrationSubmitted> getRegistrationSubmitteds() {
		return this.registrationSubmitteds;
	}

	public void setRegistrationSubmitteds(
			Set<RegistrationSubmitted> registrationSubmitteds) {
		this.registrationSubmitteds = registrationSubmitteds;
	}

	public Set<RegistrationEvent> getRegistrationEvents() {
		return this.registrationEvents;
	}

	public void setRegistrationEvents(Set<RegistrationEvent> registrationEvents) {
		this.registrationEvents = registrationEvents;
	}

}
