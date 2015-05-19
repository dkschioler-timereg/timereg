package dk.bitmovers.timeregistration.client.controller;

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
import dk.bitmovers.timeregistration.model.Client;

@Controller
@SessionAttributes({ "currentClient" })
public class ClientController {
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private ClientProvider clientProvider;

	@RequestMapping(value = "/setClient.do", method = RequestMethod.POST)
	public String handleEvent(Model model, @RequestParam(value = "client", required = true) String clientId) {
		Client retrieveClient = clientProvider.retrieveClient(Long.parseLong(clientId));
		logger.debug("retrieved clientId=" + clientId + "," + retrieveClient);
		model.addAttribute("currentClient", retrieveClient);
		return ViewTokens.VIEW_JSP_EVENT_INPUT;
	}
}
