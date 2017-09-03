package fr.tsadeo.app.gwt.playwithcss.client.cssproperty;

import com.google.gwt.user.client.ui.StackPanel;

import fr.tsadeo.app.gwt.playwithcss.client.util.ElementToControl;

public class CssBorderProperties extends AbstractCssProperties {

	private static final String NAME = "Border";
	private static final String DISPLAY_NAME = NAME;
	
	private final StackPanel _stackPanel = new StackPanel();
	
	private final CssBorderWidthProperties _cssBorderWidthProperties;
	private final CssBorderColorProperties _cssBorderColorProperties;
	private final CssBorderStyleProperties _cssBorderStyleProperties;
	private final CssBorderRadiusProperties _cssBorderRadiusProperties;
	
	//---------------------------------- overriding AbstractCssProperties
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
		this._cssBorderColorProperties.reinit();
		this._cssBorderStyleProperties.reinit();
		this._cssBorderWidthProperties.reinit();
		this._cssBorderRadiusProperties.reinit();
	}
	@Override
	protected void applyCss() {
		this._cssBorderColorProperties.applyCss();
		this._cssBorderStyleProperties.applyCss();
		this._cssBorderWidthProperties.applyCss();
		this._cssBorderRadiusProperties.applyCss();
	}
	@Override
	protected void getDisplayCssProperties(final StringBuilder sb) {
		this._cssBorderColorProperties.getDisplayCssProperties(sb);
		this._cssBorderStyleProperties.getDisplayCssProperties(sb);
		this._cssBorderWidthProperties.getDisplayCssProperties(sb);
		this._cssBorderRadiusProperties.getDisplayCssProperties(sb);
	}
	
	@Override
	public void setElementToControl(final ElementToControl elementToControl,final boolean reinit) {
		super.setElementToControl(elementToControl, reinit);
		
		this._cssBorderColorProperties.setElementToControl(elementToControl, reinit);
		this._cssBorderStyleProperties.setElementToControl(elementToControl, reinit);
		this._cssBorderWidthProperties.setElementToControl(elementToControl, reinit);
		this._cssBorderRadiusProperties.setElementToControl(elementToControl, reinit);
	}
	//---------------------------------- constructor
	public CssBorderProperties(final String title, final AbstractPanelProperties panelProperties, final String defaultColor) {
		super(NAME + "." + title, panelProperties);
		
		this._cssBorderColorProperties = new CssBorderColorProperties(title, panelProperties, defaultColor);
		this._cssBorderStyleProperties = new CssBorderStyleProperties(title, panelProperties);
		this._cssBorderWidthProperties = new CssBorderWidthProperties(title, panelProperties);
		this._cssBorderRadiusProperties = new CssBorderRadiusProperties(title, panelProperties);
		
		this._stackPanel.addStyleName("innerStackPanel");
		this._stackPanel.add(this._cssBorderColorProperties, "color");
		this._stackPanel.add(this._cssBorderStyleProperties, "style");
		this._stackPanel.add(this._cssBorderWidthProperties, "width");
		this._stackPanel.add(this._cssBorderRadiusProperties, "radius");
		
		this.getContent().add(this._stackPanel);
	}

}
