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

import dk.bitmovers.timeregistration.client.view.TimeRegistrationSession;
//import dk.bitmovers.timeregistration.client.view.TimeRegistrationSession;
import dk.bitmovers.timeregistration.client.view.ViewTokens;
import dk.bitmovers.timeregistration.data.provider.UserProvider;
import dk.bitmovers.timeregistration.model.User;

@Controller
@SessionAttributes({ "timeregistrationSession" })
public class LoginController {
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private UserProvider userProvider;

	@RequestMapping(value = "/j_spring_security_check-dur", method = RequestMethod.POST)
	public String handleEvent(Model model, @RequestParam(value = "username", required = true) String username) {
		User user = userProvider.retrieveUserByName(username);
		logger.debug("retrieved clientId=" + username + ",");
		TimeRegistrationSession ses= new TimeRegistrationSession(user);
	
		model.addAttribute("timeregistrationSession", ses);
		return "redirect:welcome.do";
	}
}
