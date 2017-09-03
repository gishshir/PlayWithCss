package fr.tsadeo.app.gwt.playwithcss.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;

import fr.tsadeo.app.gwt.playwithcss.client.view.PropertiesView;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class PlayWithCss implements EntryPoint {


	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {

		RootPanel.get("divView").add(new PropertiesView());
		

	}
}
