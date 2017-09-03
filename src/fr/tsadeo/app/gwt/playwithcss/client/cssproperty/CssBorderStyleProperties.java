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
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.VerticalPanel;

import fr.tsadeo.app.gwt.playwithcss.client.cssproperty.CssStyleBean.CssStyle;
import fr.tsadeo.app.gwt.playwithcss.client.util.Types.Positions;
import fr.tsadeo.app.gwt.playwithcss.client.util.Types.TypePosition;


public class CssBorderStyleProperties extends AbstractCssProperties {
	
	private static final String NAME = "BorderStyle";
	private static final String DISPLAY_NAME = NAME;
	
	private static final String GROUP_PRINC = NAME + "princ";
		
	private final VerticalPanel _main = new VerticalPanel();
	
	private final VerticalPanel _panelDetailStyle = new VerticalPanel();
	private final VerticalPanel _panelDetailDetail = new VerticalPanel();
	
	private final CheckBox _cbBorderStyle = new CheckBox("border-style");
	
	private RadioButton _rbAll;
	private RadioButton _rbDetail;

	
	private BorderStyleDetailElement _detailElementAll;
	private final List<BorderStyleDetailElement> _listDetailElement = new ArrayList<CssBorderStyleProperties.BorderStyleDetailElement>(4);

	public CssBorderStyleProperties(final String title, final AbstractPanelProperties panelProperties) {
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
		this._cbBorderStyle.setValue(true);
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
		this.applyBorderStyle(null);
	}
	@Override
	protected void getDisplayCssProperties(final StringBuilder sb) {
		this.applyBorderStyle(sb);
	}
	//------------------------------------- private methods
	private Panel buildMainPanel() {
		
		this._main.setSpacing(5);
		
		
		//------------- panel style--------------------
		
		// all
		this._panelDetailStyle.add(this._rbAll);
		this._panelDetailStyle.add(this._detailElementAll);

		// detail
		this._panelDetailStyle.add(this._rbDetail);

		// items : _vPanelDetail
		for (int i = 0; i < this._listDetailElement.size(); i++) {
			this._panelDetailDetail.add(this._listDetailElement.get(i));
		}
		this._panelDetailStyle.add(this._panelDetailDetail);

		//----------- main -------------------------
		this._main.add(this._cbBorderStyle);
		this._main.add(this._panelDetailStyle);
		return this._main;
	}
	private void displayDetails() {
		
		final boolean showPanelStyle = this._cbBorderStyle.getValue();
		this._panelDetailStyle.setVisible(showPanelStyle);
		
		final boolean showDetail = this._rbDetail.getValue();
		this._panelDetailDetail.setVisible(showDetail);
		this._detailElementAll.setVisible(!showDetail);
		
	}
	private void removeAllBorderStyle() {
		
		this.removeStyle(CssStyle.borderStyle);
		this.removeStyle(CssStyle.borderStyleTop);
		this.removeStyle(CssStyle.borderStyleRight);
		this.removeStyle(CssStyle.borderStyleLeft);
		this.removeStyle(CssStyle.borderStyleBottom);

	}
	private void applyBorderStyle(final StringBuilder sb) {
		
		if (this._elementToControl == null) return;
		if (sb == null) {
		  this.removeAllBorderStyle();
		}
		
		if (!this._cbBorderStyle.getValue()) {
			return;
		}
		
		// all
		if (this._rbAll.getValue()) {
			this.applyStyle(sb, CssStyle.borderStyle.updateStyleValue( this._detailElementAll.getValue()));
		}
		else { //detail
			for (int i = 0; i < this._listDetailElement.size(); i++) {
				final BorderStyleDetailElement	detailElement = this._listDetailElement.get(i);
				
				CssStyle cssStyle = null;
				switch (detailElement.getCoin()) {
				  case bottom: cssStyle = CssStyle.borderStyleBottom;
					break;
				  case left: cssStyle = CssStyle.borderStyleLeft;
					break;
				  case right: cssStyle = CssStyle.borderStyleRight;
					break;
				  case top: cssStyle = CssStyle.borderStyleTop;
					break;
				}
				
				if (cssStyle != null) {
				  this.applyStyle(sb, cssStyle.updateStyleValue(detailElement.getValue()));
				}
			}
		}
	}
	private void initAll() {
		this.initBorderStyleRadioButton();
		this.initBorderStyleItems();
		this.initHandlers();
	}
	
	private void initHandlers() {
		
		// radio button
		final ClickHandler clickHandler = new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				displayDetails();	
				applyCss();
			}
		};
		this._rbAll.addClickHandler(clickHandler);
		this._rbDetail.addClickHandler(clickHandler);
		this._cbBorderStyle.addClickHandler(clickHandler);
	}
	
	
	private void initBorderStyleItems() {
		
		final ChangeHandler changeHandler = new ChangeHandler() {
			
			@Override
			public void onChange(ChangeEvent event) {
				applyCss();
			}
		};
		
		this. _detailElementAll = new BorderStyleDetailElement(changeHandler);
		
		// list of items
		final List<Positions> cotes = Positions.getValues(TypePosition.cotes);
		for (int i = 0; i < cotes.size(); i++) {
			this._listDetailElement.add(new BorderStyleDetailElement(cotes.get(i), changeHandler));
		}
			
		
	}
	private void initBorderStyleRadioButton() {
		
		//radioButton
		final String group = this.buildGroupName(GROUP_PRINC);
		this._rbAll = new RadioButton(group, "all");
		this._rbDetail = new RadioButton(group, "detail");
				
	
		
	}
	
	//========================================== inner class
	class BorderStyleDetailElement extends HorizontalPanel {
		
		private final String[] _listStyles = new String[] {
		  "none", "hidden", "dotted", "dashed", "solid",
		  "double", "groove", "ridge", "inset", "outset"
		};
		private final ListBox _lbStyles = new ListBox();
		private final Label _labelItem;
		private final Positions _coin;
		
		String getLabel()   {
			return (this._coin == null)?"":this._coin.name();
		}
		Positions getCoin() {
			return this._coin;
		}
		void reinit() {
			this._lbStyles.setSelectedIndex(4);
		}
		
		String getValue() {
			return this._lbStyles.getValue(this._lbStyles.getSelectedIndex());
		}
		
		BorderStyleDetailElement(final ChangeHandler changeHandler) {
			this(null, changeHandler);
		}
		BorderStyleDetailElement(final Positions coin, 
				final ChangeHandler changeHandler) {
			
			this.setSpacing(5);
			this._coin = coin;
			this._labelItem = new Label(getLabel());
			this._labelItem.setWidth("100px");
			this.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
			
			for (int i = 0; i < this._listStyles.length; i++) {
				this._lbStyles.addItem(this._listStyles[i]);		
			}
            this._lbStyles.addChangeHandler(changeHandler);
			
			this.add(this._labelItem);
			this.add(this._lbStyles);
		}
	}
	
}
