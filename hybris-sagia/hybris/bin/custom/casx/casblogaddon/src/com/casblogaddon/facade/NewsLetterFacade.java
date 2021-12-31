package com.casblogaddon.facade;

public interface NewsLetterFacade
{

	void saveTicket(final String subject, final String comment, String eventPage);

	void saveNewsLetter(String fname, String lname, String email);

	void saveSubscription(String eventDetail);
	
	void unSubscribeNewsletter(String eventDetail);
}
