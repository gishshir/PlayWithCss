package fr.tsadeo.app.gwt.playwithcss.client.cssproperty;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.VerticalPanel;

import fr.tsadeo.app.gwt.playwithcss.client.cssproperty.CssStyleBean.CssStyle;


public class CssDisplayAndVisibility extends AbstractCssProperties {

	private static final String NAME = "DisplayAndVisibility";
	private static final String DISPLAY_NAME = "Display & visibility";
	
	private static final String GROUP_DISPLAY = "display";
	private static final String GROUP_VISIBILITY = "visibility";
	
	private final VerticalPanel _main = new VerticalPanel();
	private final VerticalPanel _panelDetailDisplay = new VerticalPanel();
	private final VerticalPanel _panelDetailVisibility = new VerticalPanel();
	
	private  final CheckBox _cbDisplay = new CheckBox("display");
	private  final CheckBox _cbVisibility = new CheckBox("visibility");
	
	private RadioButton _rbNone;
	private RadioButton _rbBlock;
	private RadioButton _rbInLine;
	private RadioButton _rbListItem;
	
	private RadioButton _rbHidden;
	private RadioButton _rbVisible;

	public CssDisplayAndVisibility(final String title, final PanelWidgetProperties panelProperties) {
		super(NAME + "." + title, panelProperties);
		
		this.initRadioButton();
		this.initHandler();
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
		this._cbDisplay.setValue(false);
		this._cbVisibility.setValue(false);
		this._rbBlock.setValue(true);
		this._rbVisible.setValue(true);
		this.showDetailPanel();
		this.applyCss();
	}
	@Override
	protected void applyCss() {
		this.applyDisplayAndVisibility(null);
	}
	@Override
	protected void getDisplayCssProperties(final StringBuilder sb) {
		this.applyDisplayAndVisibility(sb);
	}
	//------------------------------------------ private methods
	private Panel buildMainPanel() {
		
		this._main.setSpacing(5);

	   // display
	   final HorizontalPanel hPanelDisplay = new HorizontalPanel();
	   hPanelDisplay.setSpacing(5);
	   
	   hPanelDisplay.add(this._cbDisplay);
	   hPanelDisplay.add(this.buildDisplayPanelRadioButton());
	   this._main.add(hPanelDisplay);
	   
	   final HorizontalPanel hPanelVisibility = new HorizontalPanel();
	   hPanelVisibility.setSpacing(5);

	   hPanelVisibility.add(this._cbVisibility);
	   hPanelVisibility.add(this.buildVisibilityPanelRadioButton());
	   this._main.add(hPanelVisibility);
	     
	   return this._main;	
	}
	
	private Panel buildVisibilityPanelRadioButton() {

		this._panelDetailVisibility.add(this._rbHidden);
		this._panelDetailVisibility.add(this._rbVisible);
		return this._panelDetailVisibility;
		
	}
	private Panel buildDisplayPanelRadioButton() {
		
		this._panelDetailDisplay.add(this._rbNone);
		this._panelDetailDisplay.add(this._rbBlock);
		this._panelDetailDisplay.add(this._rbInLine);
		this._panelDetailDisplay.add(this._rbListItem);
		return this._panelDetailDisplay;
	}

	private void initRadioButton() {

		// display
		String group = this.buildGroupName(GROUP_DISPLAY);
		this._rbNone = new RadioButton(group, CssStyle.displayNone.getStyleValue());
		this._rbBlock = new RadioButton(group,  CssStyle.displayBlock.getStyleValue());
		this._rbInLine = new RadioButton(group,  CssStyle.displayInline.getStyleValue());
		this._rbListItem = new RadioButton(group, CssStyle.displayListItem.getStyleValue());
		
		// visibility
		group = this.buildGroupName(GROUP_VISIBILITY);
		this._rbHidden = new RadioButton(group,  CssStyle.hidden.getStyleValue());
		this._rbVisible = new RadioButton(group,  CssStyle.visible.getStyleValue());
		
		
		
	}

	private void initHandler() {
		// handler
		final ClickHandler rbClickHandler = new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				applyCss();	
			}
		};
		this._rbNone.addClickHandler(rbClickHandler);
		this._rbBlock.addClickHandler(rbClickHandler);
		this._rbInLine.addClickHandler(rbClickHandler);
		this._rbListItem.addClickHandler(rbClickHandler);
		
		this._rbHidden.addClickHandler(rbClickHandler);
		this._rbVisible.addClickHandler(rbClickHandler);
		
		// handler
		final ClickHandler cbClickHandler = new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				showDetailPanel();
				applyCss();	
			}
		};
		this._cbDisplay.addClickHandler(cbClickHandler);
		this._cbVisibility.addClickHandler(cbClickHandler);
	}


	private void removeAllStyleDisplayAndVisibility() {
		if (this._elementToControl == null) return;
		
		this.removeStyle(CssStyle.displayNone);
		this.removeStyle(CssStyle.displayBlock);
		this.removeStyle(CssStyle.displayInline);
		this.removeStyle(CssStyle.displayListItem);
		
		this.removeStyle(CssStyle.hidden);
		this.removeStyle(CssStyle.visible);
	}
	
	private void showDetailPanel() {
		
		this._panelDetailDisplay.setVisible(this._cbDisplay.getValue());
		this._panelDetailVisibility.setVisible(this._cbVisibility.getValue());
	}
    
    private void applyDisplayAndVisibility(final StringBuilder sb) {
    	   	
    	if (sb == null) {
    	    this.removeAllStyleDisplayAndVisibility();
    	}
    	
    	// display
		if (this._cbDisplay.getValue()) {
			if (this._rbNone.getValue()) {
				this.applyStyle(sb, CssStyle.displayNone);
			} else if (this._rbInLine.getValue()) {
				  this.applyStyle(sb,  CssStyle.displayInline);
			} else if (this._rbListItem.getValue()) {
				this.applyStyle(sb,  CssStyle.displayListItem);
			} else if (this._rbBlock.getValue()) {
				this.applyStyle(sb,  CssStyle.displayBlock);
			}
		}
    	
    	// visibility
		if (this._cbVisibility.getValue()) {
			
		  if (this._rbVisible.getValue()) {
		    this.applyStyle(sb,  CssStyle.visible);
		  }
		  else {
			  this.applyStyle(sb,  CssStyle.hidden);
		  }
		}
    }
    


}
