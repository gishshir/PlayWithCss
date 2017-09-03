package fr.tsadeo.app.gwt.playwithcss.client.cssproperty;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import fr.tsadeo.app.gwt.playwithcss.client.cssproperty.CssStyleBean.CssStyle;
import fr.tsadeo.app.gwt.playwithcss.client.util.Types.Positions;
import fr.tsadeo.app.gwt.playwithcss.client.util.WidgetUtils;

public class CssPaddingMarginProperties extends AbstractCssProperties {
	
	private static final String NAME = "PaddingMargin";
	private static final String DISPLAY_NAME = "Padding & Margin";
	
	private static final String GROUP_PADDING = "padding";
	private static final String GROUP_MARGIN = "margin";
	
	private final VerticalPanel _main = new VerticalPanel();
	
	private final Panel _panelPadding = new SimplePanel();
	private final Panel _panelMargin = new SimplePanel();
	
	private final CssPanelDetail _panelDetailPadding = new CssPanelDetail();
	private final CssPanelDetail _panelDetailMargin = new CssPanelDetail();
	
	private final CheckBox _cbPadding = new CheckBox(CssStyle.padding.getStyleName());
	private final CheckBox _cbMargin = new CheckBox(CssStyle.margin.getStyleName());
	
	private RadioButton _rbPaddingAll;
	private RadioButton _rbPaddingDetail;
	
	private RadioButton _rbMarginAll;
	private RadioButton _rbMarginDetail;
	
	private final TextBox _tbPaddingAll = new TextBox();
	private final TextBox _tbMarginAll = new TextBox();
	
	//---------------------------- constructor
	public CssPaddingMarginProperties(final String title, final AbstractPanelProperties panelProperties) {
		super(NAME + "." + title, panelProperties);
		
		this.initAll();
		this.getContent().add(this.buildMainPanel());
		this.displayPaddingMarginPanel();
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
		
		this._cbMargin.setValue(false);
		this._cbPadding.setValue(false);

       this._rbMarginAll.setValue(true);
       this._rbPaddingAll.setValue(true);
       
       this._panelDetailMargin.reinit();
       this._panelDetailPadding.reinit();
       
       this.displayPaddingMarginPanel();
       this.applyCss();

	}
	@Override
	protected void applyCss() {
		  this.applyPaddingMargin(null);
	}
	@Override
	protected void getDisplayCssProperties(final StringBuilder sb) {
		 this.applyPaddingMargin(sb);
	}
	//--------------------------------------- private methods
	private Panel buildMainPanel() {
		
		this._main.setSpacing(5);
		
		// padding
		this._panelPadding.add(this.builThemePanel(
				this._tbPaddingAll, this._rbPaddingAll, 
				this._rbPaddingDetail, this._panelDetailPadding));
		this._panelPadding.addStyleName("marge10");

		final SimplePanel cbPanelPadding = new SimplePanel(this._cbPadding);
		cbPanelPadding.addStyleName("l120");
		this._main.add(cbPanelPadding);
		this._main.add(this._panelPadding);
			
		
		// margin
		this._panelMargin.add(this.builThemePanel(
				this._tbMarginAll, this._rbMarginAll, 
				this._rbMarginDetail, this._panelDetailMargin));
		this._panelMargin.addStyleName("marge10");
		
		final SimplePanel cbPanelMargin = new SimplePanel(this._cbMargin);
		cbPanelMargin.addStyleName("l120");
		this._main.add(cbPanelMargin);
		this._main.add(this._panelMargin);
		
		return this._main;
	}
	

	private void initAll() {
		this.initTextBoxes();
		this.initPaddingMarginRadioButton();
		this.initHandlers();
	}
	private void initTextBoxes() {
		this._tbMarginAll.setStylePrimaryName("box");
		this._tbPaddingAll.setStylePrimaryName("box");
	}
	private void initPaddingMarginRadioButton() {
		
		//radioButton
		String group = this.buildGroupName(GROUP_PADDING);
		this._rbPaddingAll = new RadioButton(group, "all");
		this._rbPaddingDetail = new RadioButton(group, "detail");
				
		group = this.buildGroupName(GROUP_MARGIN);
		this._rbMarginAll = new RadioButton(group, "all");
		this._rbMarginDetail = new RadioButton(group, "detail");
		
	}
	
	private void initHandlers() {
		
		final ClickHandler clickHandler = new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				displayPaddingMarginPanel();	
				applyCss();
			}
		};
		
		this._rbPaddingAll.addClickHandler(clickHandler);
		this._rbPaddingDetail.addClickHandler(clickHandler);
		this._rbMarginAll.addClickHandler(clickHandler);
		this._rbMarginDetail.addClickHandler(clickHandler);
		
		this._cbMargin.addClickHandler(clickHandler);
		this._cbPadding.addClickHandler(clickHandler);
		
		final ChangeHandler tbChangeHandler = new ChangeHandler() {
			
			@Override
			public void onChange(ChangeEvent event) {
				applyCss();
			}
		};
		
		this._panelDetailMargin.setChangeHandler(tbChangeHandler);
		this._panelDetailPadding.setChangeHandler(tbChangeHandler);
		
		this._tbMarginAll.addChangeHandler(tbChangeHandler);
		this._tbPaddingAll.addChangeHandler(tbChangeHandler);
	}

	private void displayPaddingMarginPanel() {
		
		// margin
		if (this._cbMargin.getValue()) {
			
			this._panelMargin.setVisible(true);
			final boolean showDetailMargin = this._rbMarginDetail.getValue();
			this._panelDetailMargin.setVisible(showDetailMargin);
			this._tbMarginAll.setVisible(!showDetailMargin);
		}
		else {
			this._panelMargin.setVisible(false);
		}
		
		//  padding
		if (this._cbPadding.getValue()) {
			
		  this._panelPadding.setVisible(true);
		  final boolean showDetailPadding = this._rbPaddingDetail.getValue();
		  this._panelDetailPadding.setVisible(showDetailPadding);
		  this._tbPaddingAll.setVisible(!showDetailPadding);
		}
		else {
			 this._panelPadding.setVisible(false);
		}
		

		
	}
	private void removeAllPaddingMargin() {
		if (this._elementToControl == null) return;
		this.removeStyle( CssStyle.margin);
		this.removeStyle( CssStyle.padding);
	}
	private void applyPaddingMargin(final StringBuilder sb) {
		
		if (this._elementToControl == null) return;
		if (sb == null) {
		  this.removeAllPaddingMargin();
		}
		
		// padding
		if (this._cbPadding.getValue()) {
			if (this._rbPaddingAll.getValue()) {
				WidgetUtils.completeSuffixPx(this._tbPaddingAll);
				this.applyStyle(sb, CssStyle.padding.updateStyleValue(this._tbPaddingAll.getValue()));
			} else {
				
				this.applyStyle(sb, CssStyle.paddingTop.updateStyleValue(this._panelDetailPadding.getValue(Positions.top)));
				this.applyStyle(sb, CssStyle.paddingBottom.updateStyleValue(this._panelDetailPadding.getValue(Positions.bottom)));
				this.applyStyle(sb, CssStyle.paddingLeft.updateStyleValue(this._panelDetailPadding.getValue(Positions.left)));
				this.applyStyle(sb, CssStyle.paddingRight.updateStyleValue(this._panelDetailPadding.getValue(Positions.right)));
				
			}
		}
		
		// margin
		if (this._cbMargin.getValue()) {
			if (this._rbMarginAll.getValue()) {
				WidgetUtils.completeSuffixPx(this._tbMarginAll);
				this.applyStyle(sb, CssStyle.margin.updateStyleValue(this._tbMarginAll.getValue()));
			} else {
				this.applyStyle(sb, CssStyle.marginTop.updateStyleValue(this._panelDetailMargin.getValue(Positions.top)));
				this.applyStyle(sb, CssStyle.marginBottom.updateStyleValue(this._panelDetailMargin.getValue(Positions.bottom)));
				this.applyStyle(sb, CssStyle.marginLeft.updateStyleValue(this._panelDetailMargin.getValue(Positions.left)));
				this.applyStyle(sb, CssStyle.marginRight.updateStyleValue(this._panelDetailMargin.getValue(Positions.right)));
			}
		}
	}
}
