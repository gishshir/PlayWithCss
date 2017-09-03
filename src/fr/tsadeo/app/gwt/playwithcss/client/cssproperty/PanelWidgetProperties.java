package fr.tsadeo.app.gwt.playwithcss.client.cssproperty;

import fr.tsadeo.app.gwt.playwithcss.client.util.Types.ToControl;
import fr.tsadeo.app.gwt.playwithcss.client.view.PropertiesView;

/**
 * Panel contenant sous forme d'onglet toutes les properties d'un element Widget à manipuler
 * @author sylvie
 *
 */
public class PanelWidgetProperties extends AbstractPanelProperties {

	@Override
	protected void buildListCssProperties(final PropertiesView propertiesView) {
		this._listCssProperties.add(new CssTypeContainerProperties(this._title, propertiesView, this, true));
		this._listCssProperties.add(new CssPositionAndSizeProperties(this._title, this));
		this._listCssProperties.add(new CssDisplayAndVisibility(this._title, this));

		this._listCssProperties.add(new CssContentProperties(this._title, this));
		this._listCssProperties.add(new CssTextAlignAndControlProperties(this._title, this));
		this._listCssProperties.add(new CssBackgroundProperties(this._title, this));
		this._listCssProperties.add(new CssBorderProperties(this._title, this, "blue"));
		
		this._listCssProperties.add(new CssPaddingMarginProperties(this._title, this));
	}
	
	public PanelWidgetProperties(final String title, final PropertiesView propertiesView) {
		super(title, propertiesView, ToControl.WIDGET);
	}


}
