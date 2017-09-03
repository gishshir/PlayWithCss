package fr.tsadeo.app.gwt.playwithcss.client.cssproperty;

import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.VerticalPanel;

import fr.tsadeo.app.gwt.playwithcss.client.util.ElementToControl;

public class CssPositionAndSizeProperties extends AbstractCssProperties {
	
	private static final String NAME = "PositionAndSize";
	private static final String DISPLAY_NAME = "Position & size";

	private final VerticalPanel _main = new VerticalPanel();
	
	private final CssPositionProperties _cssPositionProperties;
	private final CssSizeProperties _cssSizeProperties;
	private final CssFloatProperties _cssFloatProperties;
	

	
	//---------------------------------------- constructor
	public CssPositionAndSizeProperties(final String title, final PanelWidgetProperties panelProperties) {
		super(NAME + "." + title, panelProperties);
		
		this._cssPositionProperties = new CssPositionProperties(title, panelProperties);
		this._cssSizeProperties = new CssSizeProperties(title, panelProperties);
		this._cssFloatProperties = new CssFloatProperties(title, panelProperties);
		this.getContent().add(this.buildMainPanel());

	}
	//--------------------------------------- overriding AbstractCssProperties
	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public String getDisplayName() {
		return DISPLAY_NAME;
	}

	@Override
	protected void reinit() {
		this._cssPositionProperties.reinit();
		this._cssSizeProperties.reinit();
		this._cssFloatProperties.reinit();
	}
	@Override
	protected void applyCss() {
		this._cssPositionProperties.applyCss();
		this._cssSizeProperties.applyCss();
		this._cssFloatProperties.applyCss();
	}
	@Override
	public void setElementToControl( final ElementToControl elementToControl, final boolean reinit) {
		this._cssPositionProperties.setElementToControl(elementToControl, reinit);
		this._cssSizeProperties.setElementToControl(elementToControl, reinit);
		this._cssFloatProperties.setElementToControl(elementToControl, reinit);
	}
	@Override
	protected void getDisplayCssProperties(final StringBuilder sb) {
		this._cssPositionProperties.getDisplayCssProperties(sb);
		this._cssSizeProperties.getDisplayCssProperties(sb);
		this._cssFloatProperties.getDisplayCssProperties(sb);
	}
	//------------------------------------ private methods
	private Panel buildMainPanel() {
		
		this._main.setSpacing(5);
		this._main.add(this._cssPositionProperties);
		this._main.add(this._cssSizeProperties);
		this._main.add(this._cssFloatProperties);
		return this._main;
	}

}
