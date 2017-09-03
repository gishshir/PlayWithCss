package fr.tsadeo.app.gwt.playwithcss.client.cssproperty;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import fr.tsadeo.app.gwt.playwithcss.client.cssproperty.CssStyleBean.CssStyle;
import fr.tsadeo.app.gwt.playwithcss.client.util.Types.Positions;
import fr.tsadeo.app.gwt.playwithcss.client.util.Types.TypePosition;
import fr.tsadeo.app.gwt.playwithcss.client.util.WidgetUtils;

public class CssBorderWidthProperties extends AbstractCssProperties {
	
	private static final String NAME = "BorderWidth";
	private static final String DISPLAY_NAME = NAME;
	
	private static final String GROUP_PRINC = ".princ";
	private static final String GROUP_DETAIL = ".detail";
	
	private final VerticalPanel _main = new VerticalPanel();
	
	private final VerticalPanel _panelDetailWidth = new VerticalPanel();
	private final VerticalPanel _panelDetailDetail = new VerticalPanel();
	
	private final CheckBox _cbBorderWidth = new CheckBox("border-width");
	
	private RadioButton _rbAll;
	private RadioButton _rbDetail;
	
	private  BorderWidthDetailElement _detailElementAll;
	private final List<BorderWidthDetailElement> _listDetailElement =
			new ArrayList<BorderWidthDetailElement>();

	public CssBorderWidthProperties(final String title, final AbstractPanelProperties panelProperties) {
		super(NAME + "." + title, panelProperties, false);
		
		this.initAll();
		this.getContent().add(this.buildMainPanel());

	}
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
		
		this._cbBorderWidth.setValue(true);
		this._rbAll.setValue(true);
		this._detailElementAll.reinit();
		
		for (int i = 0; i < this._listDetailElement.size(); i++) {
			this._listDetailElement.get(i).reinit();	
		}
		this.displayDetails();
		this.applyCss();
	}
	@Override
	protected void applyCss() {
		this.applyBorderWith(null);
	}
	@Override
	protected void getDisplayCssProperties(final StringBuilder sb) {
		this.applyBorderWith(sb);
	}
	//------------------------------------- private methods
	private void applyBorderWith(final StringBuilder sb) {
		if (this._elementToControl == null) return;
		if (sb == null) {
		  this.removeAllBorderWidth();
		}
		
		if (!this._cbBorderWidth.getValue()) {
			return;
		}
		
		if (this._rbAll.getValue()) {
			this.applyStyle(sb, CssStyle.borderWidth.updateStyleValue(this._detailElementAll.getValue()));
		}
		else { //detail
			for (int i = 0; i < this._listDetailElement.size(); i++) {
				final BorderWidthDetailElement	detailElement = this._listDetailElement.get(i);
				
				CssStyle cssStyle = null;
				switch (detailElement.getCoin()) {
				  case bottom: cssStyle = CssStyle.borderWidthBottom;
					break;
				  case left: cssStyle = CssStyle.borderWidthLeft;
					break;
				  case right: cssStyle = CssStyle.borderWidthRight;
					break;
				  case top: cssStyle = CssStyle.borderWidthTop;
					break;
				}
				
				if (cssStyle != null) {
				  this.applyStyle(sb, cssStyle.updateStyleValue(detailElement.getValue()));
				}
				
			}
		}
		
	}
	
	private void removeAllBorderWidth() {
		
		this.removeStyle(CssStyle.borderWidth);
		this.removeStyle(CssStyle.borderWidthTop);
		this.removeStyle(CssStyle.borderWidthBottom);
		this.removeStyle(CssStyle.borderWidthRight);
		this.removeStyle(CssStyle.borderWidthLeft);
	}
	
	private void displayDetails() {
			
		final boolean showPanelWidth = this._cbBorderWidth.getValue();
		this._panelDetailWidth.setVisible(showPanelWidth);
		
		final boolean showDetail = this._rbDetail.getValue();
		this._panelDetailDetail.setVisible(showDetail);
		this._detailElementAll.setVisible(!showDetail);
	}
	private void initAll() {
		this.initBorderWidthRadioButton();
		this.initBorderWidthItems();
		this.initHandlers();
	}
	
	private void initHandlers() {

	    final ClickHandler clickHandler = new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				displayDetails();	
				applyCss();
			}
		};
		
		this._rbAll.addClickHandler(clickHandler);
		this._rbDetail.addClickHandler(clickHandler);
		this._cbBorderWidth.addClickHandler(clickHandler);
		
	}
	
	private void initBorderWidthRadioButton() {
		
	
		
		//radioButton
		String group = this.buildGroupName(GROUP_PRINC);
		this._rbAll = new RadioButton(group, "all");
		this._rbDetail = new RadioButton(group, "detail");
		
	
	}
	private void initBorderWidthItems() {
		
	  final ClickHandler clickHandler = new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				applyCss();				
			}
		};
		
		final ChangeHandler changeHandler = new ChangeHandler() {
			
			@Override
			public void onChange(ChangeEvent event) {
				applyCss();				
			}
		};
		
		this._detailElementAll =  new BorderWidthDetailElement(
				null, clickHandler, changeHandler);
		
		BorderWidthDetailElement item;
		final List<Positions> cotes = Positions.getValues(TypePosition.cotes);
		for (int i = 0; i < cotes.size(); i++) {
			 item = new BorderWidthDetailElement(
					 cotes.get(i), clickHandler, changeHandler);
				this._listDetailElement.add(item);
		}	
		
	}
	
	private Panel buildMainPanel() {
		
		this._main.setSpacing(5);
		
		//------------ width -------------------------
		//all
		this._panelDetailWidth.add(this._rbAll);
		this._panelDetailWidth.add(this._detailElementAll);
		
		this._panelDetailWidth.add(this._rbDetail);
		for (int i = 0; i < this._listDetailElement.size(); i++) {
			this._panelDetailDetail.add(this._listDetailElement.get(i));		
		}
		this._panelDetailWidth.add(this._panelDetailDetail);
		
		
		//------------- main --------------------------
		this._main.add(this._cbBorderWidth);
		this._main.add(this._panelDetailWidth);
			
		return this._main;
	}
	
	//========================================= inner class

	class BorderWidthDetailElement extends HorizontalPanel {
		
		private final RadioButton _rbFreeValue;
		private final RadioButton _rbThin;
		private final RadioButton _rbMedium;
		private final RadioButton _rbThick;
		
		private final Positions _coin;
	    final TextBox _tbFreeValue = new TextBox();
	    
	    String getLabel() {
	    	return (this._coin == null)?"":this._coin.name();
	    }
	    Positions getCoin() {
	    	return this._coin;
	    }
	    String getValue() {
	    	
	    	String value;
	    	if (this._rbFreeValue.getValue() &&
	    			WidgetUtils.getTextBoxValueLength(this._tbFreeValue) > 0) {
	    		WidgetUtils.completeSuffixPx(this._tbFreeValue);
	    		value = this._tbFreeValue.getValue();
	    	}
	    	else {
	    		if (this._rbThin.getValue()) {
	    			value = "thin";
	    		}
	    		else if (this._rbMedium.getValue()) {
	    			value = "medium";
	    		}
	    		else {
	    			value = "thick";
	    		}
	    	}
	    	return value;
	    }
		
		BorderWidthDetailElement(
				final Positions coin,
				final ClickHandler clickHandler,
				final ChangeHandler changeHandler) {
			
			this._coin = coin;
			
			final String group = CssBorderWidthProperties.this.buildGroupName(GROUP_DETAIL + "." +  "." + getLabel());
			this._rbFreeValue = new RadioButton(group, "");
			this._rbThin = new RadioButton(group, "thin");
			this._rbMedium = new RadioButton(group, "medium");
			this._rbThick = new RadioButton(group, "thick");
			
			this._tbFreeValue.setWidth("30px");
			this._tbFreeValue.addClickHandler(new ClickHandler() {
				
				@Override
				public void onClick(ClickEvent event) {
					_rbFreeValue.setValue(true);		
				}
			});

			this._rbThin.addClickHandler(clickHandler);
			this._rbMedium.addClickHandler(clickHandler);
			this._rbThick.addClickHandler(clickHandler);
			
			this._tbFreeValue.addChangeHandler(changeHandler);
			
			final Label mlabel = new Label(getLabel());
			mlabel.setWidth("60px");
			this.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
			this.setSpacing(5);
			this.add(mlabel);
			this.add(this._rbFreeValue);
			this.add(this._tbFreeValue);
			this.add(this._rbThin);
			this.add(this._rbMedium);
			this.add(this._rbThick);
			
			this.reinit();
		}
		
		void reinit() {
			this._rbThin.setValue(true);
			this._tbFreeValue.setText("");
		}
	}
	
}
