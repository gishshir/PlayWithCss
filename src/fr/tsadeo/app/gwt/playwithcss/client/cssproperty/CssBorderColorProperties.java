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

public class CssBorderColorProperties extends AbstractCssProperties {
	
	private static final String NAME = "BorderColor";
	private static final String DISPLAY_NAME = NAME;
	
	private static final String GROUP_PRINC = NAME + "princ";
	private static final String GROUP_DETAIL = NAME + "detail";
	
	private final VerticalPanel _main = new VerticalPanel();
	private final VerticalPanel _panelDetailColor = new VerticalPanel();
	private final VerticalPanel _panelDetailDetail = new VerticalPanel();
	
	private final CheckBox _cbBorderColor = new CheckBox("border-color");
	
	private RadioButton _rbAll;
	private RadioButton _rbDetail;
	
	private final String _defaultColor;
	
	private  BorderColorDetailElement _detailElementAll;
	private final List<BorderColorDetailElement> _listDetailElement =
			new ArrayList<BorderColorDetailElement>();

	public CssBorderColorProperties(final String title, final AbstractPanelProperties panelProperties,
			final String defaultColor) {
		super(NAME + "." + title, panelProperties, false);
		
		this._defaultColor = defaultColor;
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
		
		this._cbBorderColor.setValue(true);
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
		this.applyBorderColor(null);	
	}
	@Override
	protected void getDisplayCssProperties(final StringBuilder sb) {
		this.applyBorderColor(sb);
		}
	
	//------------------------------------- private methods
	private Panel buildMainPanel() {
		
		this._main.setSpacing(5);
		
        //------------ color ---------------		
		// all
		this._panelDetailColor.add(this._rbAll);
		this._panelDetailColor.add(this._detailElementAll);
		
		// detail
		this._panelDetailColor.add(this._rbDetail);
		
		//items : _vPanelDetail
		for (int i = 0; i < this._listDetailElement.size(); i++) {
			this._panelDetailDetail.add(this._listDetailElement.get(i));		
		}	
		this._panelDetailColor.add(this._panelDetailDetail);
		
		//----------- main -------------------------
		this._main.add(this._cbBorderColor);
		this._main.add(this._panelDetailColor);
		return this._main;
	}
	private void initAll() {
		this.initBorderColorRadioButton();
		this.initBorderColorItems();
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
		this._cbBorderColor.addClickHandler(clickHandler);
		
	}
	
	private void initBorderColorRadioButton() {
		
		
		//radioButton
		final String group = this.buildGroupName(GROUP_PRINC);
		this._rbAll = new RadioButton(group, "all");
		this._rbDetail = new RadioButton(group, "detail");

		
	}
	private void initBorderColorItems() {
		
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
			
			this._detailElementAll = new BorderColorDetailElement(
					null, clickHandler, changeHandler);
			
			BorderColorDetailElement item;
			final List<Positions> cotes = Positions.getValues(TypePosition.cotes);
			for (int i = 0; i < cotes.size(); i++) {
				 item = new BorderColorDetailElement(
							cotes.get(i), clickHandler, changeHandler);
					this._listDetailElement.add(item);
			}	
		
	}
	private void displayDetails() {
		
		final boolean showPanelColor = this._cbBorderColor.getValue();
		this._panelDetailColor.setVisible(showPanelColor);
				
		final boolean showDetail = this._rbDetail.getValue();
		this._panelDetailDetail.setVisible(showDetail);
		this._detailElementAll.setVisible(!showDetail);
		
	}
	
	private void removeAllBorderColor() {
		
		this.removeStyle(CssStyle.borderColor);
		this.removeStyle(CssStyle.borderColorTop);
		this.removeStyle(CssStyle.borderColorBottom);
		this.removeStyle(CssStyle.borderColorLeft);
		this.removeStyle(CssStyle.borderColorRight);
	}
	private void applyBorderColor(final StringBuilder sb) {
		
		if (this._elementToControl == null) return;
		if (sb == null) {
		  this.removeAllBorderColor();
		}
		
		if (!this._cbBorderColor.getValue()) {
			return;
		}
		
		// all
		if (this._rbAll.getValue()) {
			this.applyStyle(sb, CssStyle.borderColor.updateStyleValue( this._detailElementAll.getValue()));
		}
		else { //detail
			for (int i = 0; i < this._listDetailElement.size(); i++) {
				final BorderColorDetailElement	detailElement = this._listDetailElement.get(i);
				
				CssStyle cssStyle = null;
				switch (detailElement.getCoin()) {
				  case bottom: cssStyle = CssStyle.borderColorBottom;
					break;
				  case left: cssStyle = CssStyle.borderColorLeft;
					break;
				  case right: cssStyle = CssStyle.borderColorRight;
					break;
				  case top: cssStyle = CssStyle.borderColorTop;
					break;
				}
				
				if (cssStyle != null) {
				  this.applyStyle(sb, cssStyle.updateStyleValue(detailElement.getValue()));
				}
			}
		}
	}
	//============================================ inner class
	class BorderColorDetailElement  extends HorizontalPanel {
		
		private Positions _coin;
		private final TextBox _tbFreeValue = new TextBox();
		private final RadioButton _rbFreeValue;
		private final RadioButton _rbTransparent;
		
		String getLabel () {
			return (this._coin==null)?"":this._coin.name();
		}
		
		Positions getCoin() {
			return this._coin;
		}
		
		 String getValue() {
		    	
		    	String value;
		    	if (this._rbFreeValue.getValue() &&
		    			WidgetUtils.getTextBoxValueLength(this._tbFreeValue) > 0) {
		    		value = this._tbFreeValue.getValue();
		    	}
		    	else {
		    			value = "transparent";
		    	}
		    	return value;
		    }
		
		BorderColorDetailElement (final Positions coin,
				final ClickHandler clickHandler,
				final ChangeHandler changeHandler) {
			
			this._coin = coin;
			//radiobutton
			final String group =
			CssBorderColorProperties.this.buildGroupName(GROUP_DETAIL + "." + getLabel());
			this._rbFreeValue = new RadioButton(group, "");
			this._rbTransparent = new RadioButton(group, "transparent");
			
			this._tbFreeValue.setWidth("50px");
			this._tbFreeValue.addClickHandler(new ClickHandler() {
				
				@Override
				public void onClick(ClickEvent event) {
					_rbFreeValue.setValue(true);
					changeHandler.onChange(null);
				}
			});

			this._rbFreeValue.addClickHandler(clickHandler);
			this._rbTransparent.addClickHandler(clickHandler);
			
			this._tbFreeValue.addChangeHandler(changeHandler);
			
			//assemblage
			final Label mlabel = new Label(getLabel());
			mlabel.setWidth("60px");
			this.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
			this.setSpacing(5);
			this.add(mlabel);
			this.add(this._rbFreeValue);
			this.add(this._tbFreeValue);
			this.add(this._rbTransparent);
		}
		
		void reinit() {
			this._rbFreeValue.setValue(true);
			this._tbFreeValue.setText(CssBorderColorProperties.this._defaultColor);
		}
	}
	
}
