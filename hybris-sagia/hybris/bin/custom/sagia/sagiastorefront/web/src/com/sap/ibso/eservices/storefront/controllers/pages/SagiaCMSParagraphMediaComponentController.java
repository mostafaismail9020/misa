package com.sap.ibso.eservices.storefront.controllers.pages.portal;

import de.hybris.platform.commerceservices.i18n.CommerceCommonI18NService;
import de.hybris.platform.core.model.c2l.LanguageModel;
import de.hybris.platform.servicelayer.i18n.I18NService;
import org.apache.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.annotation.Resource;

import com.sap.ibso.eservices.facades.sagia.impl.DefautSagiaLicenseApplyFacade;
import org.apache.commons.collections.CollectionUtils;
import java.util.Collection;
import de.hybris.platform.core.model.media.MediaModel;
import de.hybris.platform.servicelayer.media.MediaService;
import de.hybris.platform.servicelayer.i18n.CommonI18NService;

import java.io.InputStream;
import java.io.IOException;
import com.sap.ibso.eservices.storefront.controllers.pages.abs.SagiaAbstractPageController;
import com.sap.ibso.eservices.core.model.SagiaCMSParagraphMediaComponentModel;
import java.util.Locale;


@Controller
@RequestMapping(value = "/licenseTermsAndConditions")
public class SagiaCMSParagraphMediaComponentController extends SagiaAbstractPageController
{
	 private static final Logger LOG = Logger.getLogger(SagiaCMSParagraphMediaComponentController.class);
	 
	 protected static final String DOWNLOAD_MEDIA_CODE = "/download/{uid}";
	  
	 @Resource(name = "sagiaLicenseApplyFacade")
	 private DefautSagiaLicenseApplyFacade sagiaLicenseApplyFacade;

	 @Resource
	 private CommonI18NService commonI18NService;

	 @Resource
	 private CommerceCommonI18NService commerceCommonI18NService;

	 @Resource
	 I18NService i18NService;
	 
	 @Resource
	private MediaService mediaService;
	    
	 @RequestMapping(value = DOWNLOAD_MEDIA_CODE, method = RequestMethod.GET)
	    public void getResourceFile(@PathVariable("uid") final String uid, final Model model,
	            final HttpServletRequest request, final HttpServletResponse response)
	    {
	    	LOG.info("Entered into download button controller");
	    	
	    	InputStream is = null;
	    	String fileName = null;
	    	 
	    	try {
	    		    SagiaCMSParagraphMediaComponentModel paragraphMediaModel = null; 
	    		    
	    			paragraphMediaModel  = sagiaLicenseApplyFacade.getParagraphLicenseMedia(uid);
	    			
	    			LOG.info("paragraphMediaModel details are " + paragraphMediaModel);

				    MediaModel mediamodel = paragraphMediaModel.getAttachment(getLocale());
					  
					LOG.info("mediamodel code: " + mediamodel);
					if (mediamodel != null)
					{
					   LOG.info("entered into media model code and Language");
					   
					   is = mediaService.getStreamFromMedia(mediamodel);
					  
					  LOG.info("media service details "+ is);
					  
					  fileName = mediamodel.getRealFileName(); 
					 }
	 
	    		// copy it to response's OutputStream
				
				  if(is != null) 
				  { 
				    LOG.info("Entered into response outstream");
				  
				  response.setContentType("application/pdf");
				  response.addHeader("Content-Disposition", "attachment; filename="+fileName);
				  
				  LOG.info("before downloading the file");
				  org.apache.commons.io.IOUtils.copy(is, response.getOutputStream());
				  response.getOutputStream().flush(); 
				  }
				 
	    		LOG.info("Exit from download controller");

				 } 
	    	catch (IOException ex) {
	    		LOG.info("Error writing file to output stream. Filename was '{}'"+ ex);
	    		throw new RuntimeException("IOError writing file to output stream");
	    	}
	    }

		protected Locale getLocale()
		{
			final LanguageModel currentLanguage = commonI18NService.getCurrentLanguage();
			Locale locale = commerceCommonI18NService.getLocaleForLanguage(currentLanguage);
			if(locale == null)	{
				locale = i18NService.getCurrentLocale();
			}
			return locale;
		}

}	

