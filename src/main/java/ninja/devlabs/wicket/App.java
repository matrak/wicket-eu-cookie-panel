package ninja.devlabs.wicket;

import ninja.devlabs.wicket.pages.IndexPage;
import ninja.devlabs.wicket.pages.LegalNoticePage;

import org.apache.wicket.Page;
import org.apache.wicket.protocol.http.WebApplication;

public class App extends WebApplication {
	
	@Override
	public Class<? extends Page> getHomePage() {
		return IndexPage.class;
	}
	
	@Override
	protected void init() {
		super.init();
		mountPage("/legal-notice", LegalNoticePage.class);
	}
	
}
