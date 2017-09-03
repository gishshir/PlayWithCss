package fr.tsadeo.app.gwt.playwithcss.client.cssproperty;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.VerticalPanel;

import fr.tsadeo.app.gwt.playwithcss.client.cssproperty.CssStyleBean.CssStyle;
import fr.tsadeo.app.gwt.playwithcss.client.util.WidgetUtils;

public class CssFloatProperties extends AbstractCssProperties {
	
	private static final String NAME = "Float";
	private static final String DISPLAY_NAME = NAME;
	
	private static final String GROUP_FLOAT = NAME + "float";
	private static final String GROUP_CLEAR = NAME + "clear";
	
	private final VerticalPanel _main = new VerticalPanel();
	private final HorizontalPanel _panelDetailFloat = new HorizontalPanel();
	private final HorizontalPanel _panelDetailClear = new HorizontalPanel();
	
	private final CheckBox _cbFloat = new CheckBox("float");
	private final CheckBox _cbClear = new CheckBox("clear");
	
	private RadioButton _rbFloatLeft;
	private RadioButton _rbFloatRight;
	private RadioButton _rbFloatNone;
	
	private RadioButton _rbClearLeft;
	private RadioButton _rbClearRight;
	private RadioButton _rbClearNone;

	public CssFloatProperties(final String title, final PanelWidgetProperties panelProperties) {
		super(NAME, panelProperties, false);
		this.initRadioButton();
		this.initHandlers();
		this.getContent().add(this.buildMainPanel());
	}

	//------------------------------------------ overriding AbstractCssProperties
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
		this._cbFloat.setValue(false);
		this._rbFloatNone.setValue(true);
		
		this._cbClear.setValue(false);
		this._rbClearNone.setValue(true);
		
		this.displayPanelDetails();
		this.applyCss();
	}
	@Override
	protected void applyCss() {
       this.applyFloadAndClear(null);
	}
	@Override
	protected void getDisplayCssProperties(final StringBuilder sb) {
		this.applyFloadAndClear(sb);
	}
	//------------------------------------------ private methods
	private void removeAllStyleFloatAndClear() {
		if (this._elementToControl == null) return;
		
		this.removeStyle(CssStyle.floatLeft);
		this.removeStyle(CssStyle.floatRight);
		this.removeStyle(CssStyle.floatNone);
		
		this.removeStyle(CssStyle.clearLeft);
		this.removeStyle(CssStyle.clearRight);
		this.removeStyle(CssStyle.clearNone);
	}
	private void applyFloadAndClear(final StringBuilder sb) {

		if (this._elementToControl == null) return;
		if (sb == null) {
    	  this.removeAllStyleFloatAndClear();
		}
    	
    	// float
		if (this._cbFloat.getValue()) {
			if (this._rbFloatLeft.getValue()) {
				this.applyStyle(sb, CssStyle.floatLeft);
			} else if (this._rbFloatRight.getValue()) {
				this.applyStyle(sb, CssStyle.floatRight);
			} else if (this._rbFloatNone.getValue()) {
				this.applyStyle(sb, CssStyle.floatNone);
			}
		}
		
    	// clear
		if (this._cbClear.getValue()) {
			if (this._rbClearLeft.getValue()) {
				this.applyStyle(sb, CssStyle.clearLeft);
			} else if (this._rbClearRight.getValue()) {
				this.applyStyle(sb, CssStyle.clearRight);
			} else if (this._rbClearNone.getValue()) {
				this.applyStyle(sb, CssStyle.clearNone);
			}
		}
	}
	private void displayPanelDetails() {
		this._panelDetailFloat.setVisible(this._cbFloat.getValue());
		this._panelDetailClear.setVisible(this._cbClear.getValue());
		this.applyCss();
	}
	private void initHandlers() {
		
		final ClickHandler rbClickHandler = new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				displayPanelDetails();
				applyCss();
			}
		};

        this._rbFloatLeft.addClickHandler(rbClickHandler);
        this._rbFloatRight.addClickHandler(rbClickHandler);
        this._rbFloatNone.addClickHandler(rbClickHandler);
        
        this._rbClearLeft.addClickHandler(rbClickHandler);
        this._rbClearRight.addClickHandler(rbClickHandler);
        this._rbClearNone.addClickHandler(rbClickHandler);
        
        final ClickHandler cbClickHandler = new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				displayPanelDetails();
			}
		};
		
		this._cbFloat.addClickHandler(cbClickHandler);
		this._cbClear.addClickHandler(cbClickHandler);
		
	}
    private void initRadioButton() {
		
		
		//radioButton
		String group = this.buildGroupName(GROUP_FLOAT);
		this._rbFloatLeft = new RadioButton(group,  CssStyle.floatLeft.getStyleValue());
		this._rbFloatRight = new RadioButton(group, CssStyle.floatRight.getStyleValue());
		this._rbFloatNone = new RadioButton(group,  CssStyle.floatNone.getStyleValue());
		
		group = this.buildGroupName(GROUP_CLEAR);
		this._rbClearLeft = new RadioButton(group, CssStyle.clearLeft.getStyleValue());
		this._rbClearRight = new RadioButton(group,  CssStyle.clearRight.getStyleValue());
		this._rbClearNone = new RadioButton(group,  CssStyle.clearNone.getStyleValue());
				
	}
    private Panel buildMainPanel() {
    	
    	final String width = "100px";
    	
    	this._main.setSpacing(5);
    	
    	final HorizontalPanel hpFloat = new HorizontalPanel();
    	hpFloat.add(WidgetUtils.buildSimplePlanel(this._cbFloat, width) );
    	hpFloat.add(this.buildPanelDetailFloat());
    	this._main.add(hpFloat);
    	
    	final HorizontalPanel hpClear= new HorizontalPanel();
    	hpClear.add((WidgetUtils.buildSimplePlanel(this._cbClear, width)));
    	hpClear.add(this.buildPanelDetailClear());
    	this._main.add(hpClear);
    	
    	return this._main;
    }
    private Panel buildPanelDetailFloat () {
    	
    	this._panelDetailFloat.setWidth("100%");
    	this._panelDetailFloat.setSpacing(5);
    	this._panelDetailFloat.add(this._rbFloatLeft);
    	this._panelDetailFloat.add(this._rbFloatRight);
    	this._panelDetailFloat.add(this._rbFloatNone);
		
		return this._panelDetailFloat;
    }
    
    private Panel buildPanelDetailClear () {
    	
    	this._panelDetailClear.setWidth("100%");
    	this._panelDetailClear.setSpacing(5);
    	this._panelDetailClear.add(this._rbClearLeft);
    	this._panelDetailClear.add(this._rbClearRight);
    	this._panelDetailClear.add(this._rbClearNone);
		
		return this._panelDetailClear;
    }
}
