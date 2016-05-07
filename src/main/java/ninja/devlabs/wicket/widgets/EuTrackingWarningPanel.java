package ninja.devlabs.wicket.widgets;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import ninja.devlabs.wicket.pages.LegalNoticePage;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.ResourceModel;
import org.apache.wicket.request.cycle.RequestCycle;
import org.apache.wicket.request.http.WebRequest;

public class EuTrackingWarningPanel extends Panel {
	
    private static final long serialVersionUID = 1L;
    
    private static final String COOKIE_NAME = 
    	"EU_TRACKING_WARNING_ACCEPTED";

    private static final String SET_COOKIE = 
		"javascript:document.getElementById('euTrackingWarningPanel').style.display='none';" + 
		"document.cookie='%s=1;expires=%s'; " + 
		"return false;";
    
	public EuTrackingWarningPanel(String wicketId) {
		super(wicketId);

		add(new BookmarkablePageLink<Void>("legalNotice", LegalNoticePage.class)
				.setBody(new ResourceModel("eu_tracking_panel.legal_notice_link")));
		
		String onclick = String.format(SET_COOKIE, COOKIE_NAME, getExpirationDate());
		WebMarkupContainer closeLink = new WebMarkupContainer("closeLink");
		closeLink.add(AttributeModifier.replace("onclick", onclick));
		add(closeLink);
	}

	private static long ONE_YEAR = TimeUnit.DAYS.toMillis(360);
	
	private static String getExpirationDate() {
		Date expdate = new Date();
		expdate.setTime (expdate.getTime() + ONE_YEAR);
		DateFormat df = new SimpleDateFormat("EEE, dd MMM yyyy hh:mm:ss z", Locale.ENGLISH);
		df.setTimeZone(TimeZone.getTimeZone("GMT"));
		return df.format(expdate);
	}

	@Override
	protected void onConfigure() {
		WebRequest webRequest = (WebRequest) RequestCycle.get().getRequest();
		if (webRequest.getCookie(COOKIE_NAME) != null) {
			setVisibilityAllowed(false);
		}
		super.onConfigure();
	}
}
