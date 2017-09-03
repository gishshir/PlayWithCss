package fr.tsadeo.app.gwt.playwithcss.client.cssproperty;

import fr.tsadeo.app.gwt.playwithcss.client.util.Types.LevelElement;
import fr.tsadeo.app.gwt.playwithcss.client.util.Types.ToControl;
import fr.tsadeo.app.gwt.playwithcss.client.view.PropertiesView;

/**
 *  Panel contenant les propriétés des Cellules du WorkingSet pour une table
 * @author sylvie
 *
 */
public class PanelTableCellsProperties extends AbstractPanelProperties {
	
	public PanelTableCellsProperties(final String title, final PropertiesView propertiesView) {
		super(title, propertiesView, ToControl.WORKINGSET);
		this.setLevelElement(LevelElement.LEVEL_3);
		this.reinit();
	}

	@Override
	protected void buildListCssProperties(PropertiesView propertiesView) {
		this._listCssProperties.add(new CssPaddingMarginProperties(this._title, this));
		this._listCssProperties.add(new CssBorderProperties(this._title, this, "black"));
		this._listCssProperties.add(new CssBackgroundProperties(this._title, this));
	}

}
