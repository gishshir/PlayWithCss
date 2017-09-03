package fr.tsadeo.app.gwt.playwithcss.client.cssproperty;

import java.util.ArrayList;
import java.util.List;

import org.gwt.advanced.client.ui.widget.AdvancedTabPanel;
import org.gwt.advanced.client.ui.widget.tab.TabPosition;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.SimplePanel;

import fr.tsadeo.app.gwt.playwithcss.client.util.ElementToControl;
import fr.tsadeo.app.gwt.playwithcss.client.util.Types;
import fr.tsadeo.app.gwt.playwithcss.client.util.Types.LevelElement;
import fr.tsadeo.app.gwt.playwithcss.client.util.Types.ToControl;
import fr.tsadeo.app.gwt.playwithcss.client.view.PropertiesView;

/**
 * Panel contenant sous forme d'onglet toutes les properties d'un element à manipuler
 * @author sylvie
 *
 */
public abstract class AbstractPanelProperties extends Composite {

	
	private final AdvancedTabPanel _advancedTabPanel = new AdvancedTabPanel(TabPosition.LEFT);
	protected final String _title;
	private final Types.ToControl _toControl;
	private Types.LevelElement _levelElement = LevelElement.LEVEL_1;
	
	protected final List<AbstractCssProperties> _listCssProperties = new ArrayList<AbstractCssProperties>();
	
	private ElementToControl _elementToControl;
	
	//----------------------------------------------------- accessors
	public void setLevelElement (final LevelElement levelElement) {
		this._levelElement = levelElement;
	}
	public LevelElement getLevelElement () {
		return this._levelElement;
	}
	public ElementToControl getElementToControl() {
		return this._elementToControl;
	}
	public String getTitle() {
		return this._title;
	}
	public Types.ToControl getToControl() {
		return this._toControl;
	}
	
	//------------------------------------------------- protected methods
	protected abstract void buildListCssProperties(final PropertiesView propertiesView);
	
	protected void enableCssProperties(final AbstractCssProperties cssProperties, final boolean enable){
		
		int index = -1;
		for (int i = 0; i < this._listCssProperties.size(); i++) {
			if (cssProperties == this._listCssProperties.get(i)) {
				index = i;
				break;
			}
		}
		if (index == -1) return;
		
		this._advancedTabPanel.setTabEnabled(index, enable);
	}
	//------------------------------------------------- public methods
	public AbstractPanelProperties(final String title, final PropertiesView propertiesView, final ToControl toControl) {
		super();
		this._title = title;	
		this._toControl = toControl;
		this.buildListCssProperties(propertiesView);	
		this.initWidget(this.buildTabPanel());
	}
	public void reinit() {
		for (int i = 0; i < this._listCssProperties.size(); i++) {
			this._listCssProperties.get(i).reinit();
		}
	}
	public void setElementToControl( final ElementToControl elementToControl, final boolean reinit) {
		this._elementToControl = elementToControl;
		for (int i = 0; i < this._listCssProperties.size(); i++) {
			
			final AbstractCssProperties cssProperty = this._listCssProperties.get(i);
			final boolean enabled = cssProperty.isEnabledForTypeElement(elementToControl.getTypeElement());

			cssProperty.setElementToControl((enabled)?elementToControl:null, reinit);
			this._advancedTabPanel.setTabEnabled(i, enabled);
		}
	}
	//------------------------------------------------- private methods
	private Panel buildTabPanel() {
		
		for (int i = 0; i < this._listCssProperties.size(); i++) {
			final AbstractCssProperties cssProperties = this._listCssProperties.get(i);
			this._advancedTabPanel.addTab(new Label(cssProperties.getDisplayName()),
			cssProperties);
		}
		final Panel panel = new SimplePanel(this._advancedTabPanel);

		panel.setStylePrimaryName("panel");
		return panel;
	}
	


}
