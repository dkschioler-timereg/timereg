package dk.bitmovers.timeregistration.client.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import dk.bitmovers.timeregistration.client.view.ViewTokens;
import dk.bitmovers.timeregistration.data.provider.ClientProvider;
import dk.bitmovers.timeregistration.data.provider.RegistrationProvider;
import dk.bitmovers.timeregistration.model.Client;
import dk.bitmovers.timeregistration.model.RegistrationItem;

@Controller
@SessionAttributes({ ViewTokens.SESSION_KEY_TIMEREGISTRATION_SESSION })
public class EventController {
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private ClientProvider clientProvider;

	@Autowired
	private RegistrationProvider registrationProvider;

	@RequestMapping(value = "/welcome.do", method = RequestMethod.GET)
	public String welcome(Model model) {
		Object object = model.asMap().get(ViewTokens.SESSION_KEY_TIMEREGISTRATION_SESSION);
		logger.debug("timeregsSess="+object);
		
		List<Client> queryClients = clientProvider.queryClients(null);
		model.addAttribute("clients", queryClients);
		
		model.addAttribute("message", "Hello Lisbeth fra Lars");
		List<RegistrationItem> registrationItems = registrationProvider.getRegistrationItems(null);
		model.addAttribute("registrationItems", registrationItems);

		return ViewTokens.VIEW_JSP_EVENT_INPUT;
	}

	@RequestMapping(value = "/post-registration-event.do", method = RequestMethod.POST)
	public String handleEvent(Model model, @RequestParam(value = "registration-item", required = false) String registrationItem,
			@RequestParam(value = "registration-event-timestamp", required = false) String timestamp,
			@RequestParam(value = "registration-event-comment", required = false) String comment) {
		logger.trace("handleEvent");
		List<RegistrationItem> registrationItems = registrationProvider.getRegistrationItems(null);
		model.addAttribute("registrationItems", registrationItems);
		logger.debug("timestamp=" + timestamp + ", item=" + registrationItem + ", comment=" + comment);
		model.addAttribute("message", "Event has been added: " + registrationItem + ", timestamp=" + timestamp + "comment=" + comment);
		return ViewTokens.VIEW_JSP_EVENT_INPUT;
	}
}
