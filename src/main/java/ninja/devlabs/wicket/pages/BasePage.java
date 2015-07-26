package ninja.devlabs.wicket.pages;

import java.util.Locale;

import ninja.devlabs.wicket.widgets.EuTrackingWarningPanel;

import org.apache.wicket.markup.html.WebPage;

public class BasePage extends WebPage {

	private static final long serialVersionUID = 1L;
	
	public BasePage() {
		
		getSession().setLocale(Locale.ENGLISH);
		//getSession().setLocale(Locale.GERMANY);
		//getSession().setLocale(Locale.forLanguageTag("pl"));
		
		add(new EuTrackingWarningPanel("euTrackingWarningPanel"));
	}

}
