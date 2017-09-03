package fr.tsadeo.app.gwt.playwithcss.client.cssproperty;

import fr.tsadeo.app.gwt.playwithcss.client.util.Types.ToControl;
import fr.tsadeo.app.gwt.playwithcss.client.view.PropertiesView;

/**
 * Panel contenant les propriétés du WorkingSet, container des widgets à manipuler
 * @author sylvie
 *
 */
public class PanelWorkingSetProperties extends AbstractPanelProperties {
	

	public PanelWorkingSetProperties(final String title, final PropertiesView propertiesView) {
		super(title, propertiesView, ToControl.WORKINGSET);
	}

	@Override
	protected void buildListCssProperties(PropertiesView propertiesView) {
		this._listCssProperties.add(new CssTypeContainerProperties(this._title, propertiesView, this, false));
		this._listCssProperties.add(new CssPaddingMarginProperties(this._title, this));
		this._listCssProperties.add(new CssBorderProperties(this._title, this, "yellow"));
		this._listCssProperties.add(new CssBackgroundProperties(this._title, this));
		
		this._listCssProperties.add(new CssTableProperties(this._title, this));
	}


}
