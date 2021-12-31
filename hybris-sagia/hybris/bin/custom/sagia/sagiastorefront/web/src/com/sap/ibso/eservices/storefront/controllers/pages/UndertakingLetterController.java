package com.sap.ibso.eservices.storefront.controllers.pages;

import com.sap.ibso.eservices.facades.data.SagiaMediaData;
import com.sap.ibso.eservices.facades.sagia.SagiaMediaFacade;
import de.hybris.platform.acceleratorstorefrontcommons.annotations.RequireHardLogIn;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.pages.AbstractPageController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "/undertakingLetter")
public class UndertakingLetterController extends AbstractPageController {
	@Autowired
	private SagiaMediaFacade sagiaMediaFacade;

	@RequestMapping(method = RequestMethod.GET)
	@RequireHardLogIn
	public ResponseEntity<Map<String, String>> getUndertakingLetter(HttpServletRequest request, Model model) {
		SagiaMediaData pageMedia = sagiaMediaFacade.getSagiaMediaForPageName("undertakingLetter");
		String undertakingLetterUrl = pageMedia.getAttachments()
											  .stream()
											  .filter(file -> "undertakingLetterSample".equalsIgnoreCase(file.getCode()))
											  .findFirst()
											  .map(file -> file.getDownloadUrl())
											  .orElse("");
		Map<String, String> map = new HashMap<>();
		map.put("undertakingLetterUrl", undertakingLetterUrl);
 		return new ResponseEntity<>(map, HttpStatus.OK);
	}
}
