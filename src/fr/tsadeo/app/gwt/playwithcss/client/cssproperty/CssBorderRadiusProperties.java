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
import fr.tsadeo.app.gwt.playwithcss.client.util.WidgetUtils;
import fr.tsadeo.app.gwt.playwithcss.client.util.Types.Positions;
import fr.tsadeo.app.gwt.playwithcss.client.util.Types.TypePosition;

public class CssBorderRadiusProperties extends AbstractCssProperties {

	private static final String NAME = "BorderRadius";
	private static final String DISPLAY_NAME = NAME;
	
	private static final String SEPARATOR_VALUE = " ";
	
	private static final String GROUP_BORDER_RADIUS = "borderRadius";
	
	private final VerticalPanel _main = new VerticalPanel();
	private final Panel _panelBorderRadius = new SimplePanel();
	private final CssPanelDetail _panelDetailBorderRadius = new CssPanelDetail(TypePosition.coins);
	
	private final CheckBox _cbBorderRadius = new CheckBox(CssStyle.borderRadius.getStyleName());
	private RadioButton _rbRadiusAll;
	private RadioButton _rbRadiusDetail;	
	
	private final TextBox _tbRadiusAll = new TextBox();

	
	
	//---------------------------- constructor
	public CssBorderRadiusProperties(final String title, final AbstractPanelProperties panelProperties) {
		super(NAME + "." + title, panelProperties, false);
		
		this.initAll();
		this.getContent().add(this.buildMainPanel());
		this.displayBorderRadiusPanel();
	}
	//------------------------------ overriding AbstractCssProperties
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
		
	   this._cbBorderRadius.setValue(false);
       this._rbRadiusAll.setValue(true);       
       this._panelDetailBorderRadius.reinit();      
       this.displayBorderRadiusPanel();
       this.applyCss();
	}

	@Override
	protected void applyCss() {
		this.applyBorderRadius(null);
	}
	@Override
	protected void getDisplayCssProperties(StringBuilder sb) {
		this.applyBorderRadius(sb);
	}
	//--------------------------------------- private methods
	private Panel buildMainPanel() {
			
		this._panelBorderRadius.add(this.builThemePanel(
				this._tbRadiusAll, this._rbRadiusAll, 
				this._rbRadiusDetail, this._panelDetailBorderRadius));
		this._panelBorderRadius.addStyleName("marge10");

		final SimplePanel cbPanelRadius = new SimplePanel(this._cbBorderRadius);
		cbPanelRadius.addStyleName("l120");

		this._main.add(cbPanelRadius);
        this._main.add(this._panelBorderRadius);		
		return this._main;
	}
	
	private void initAll() {
		
		this._tbRadiusAll.setStylePrimaryName("box");

		//radioButton
		String group = this.buildGroupName(GROUP_BORDER_RADIUS);
		this._rbRadiusAll = new RadioButton(group, "all");
		this._rbRadiusDetail = new RadioButton(group, "detail");

		this.initHandlers();
	}
	
	private void displayBorderRadiusPanel() {

		if (this._cbBorderRadius.getValue()) {
			
			this._panelBorderRadius.setVisible(true);
			final boolean showDetailRadius = this._rbRadiusDetail.getValue();
			this._panelDetailBorderRadius.setVisible(showDetailRadius);
			this._tbRadiusAll.setVisible(!showDetailRadius);
		}
		else {
			this._panelBorderRadius.setVisible(false);
		}
	}
	private void removeAllBorderRadius() {
		if (this._elementToControl == null) return;
		this.removeStyle( CssStyle.borderRadius);
	}
	private void applyBorderRadius(final StringBuilder sb) {
		
		if (this._elementToControl == null) return;
		if (sb == null) {
		  this.removeAllBorderRadius();
		}
		
		// padding
		if (this._cbBorderRadius.getValue()) {
			if (this._rbRadiusAll.getValue()) {
				WidgetUtils.completeSuffixPx(this._tbRadiusAll);
				final String value = this._tbRadiusAll.getValue();
				this.applyStyle(sb, CssStyle.borderRadius.updateStyleValue(this.buildValue(value, value, value, value)));
			} else {
				
				final String multiValue = this.buildValue(this._panelDetailBorderRadius.getValue(Positions.topL),
						this._panelDetailBorderRadius.getValue(Positions.topR),
						this._panelDetailBorderRadius.getValue(Positions.bottomR),
						this._panelDetailBorderRadius.getValue(Positions.bottomL));
				this.applyStyle(sb, CssStyle.borderRadius.updateStyleValue(multiValue));
								
			}
		}
	}
	private String buildValue (final String topLeft, final String topRight, final String bottomRight, final String bottomLeft) {
		return topLeft + SEPARATOR_VALUE + topRight + SEPARATOR_VALUE + bottomRight + SEPARATOR_VALUE + bottomLeft;
	}
	private void initHandlers() {
		
		final ClickHandler clickHandler = new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				displayBorderRadiusPanel();	
				applyCss();
			}
		};
		
		this._rbRadiusAll.addClickHandler(clickHandler);
		this._rbRadiusDetail.addClickHandler(clickHandler);
		
		this._cbBorderRadius.addClickHandler(clickHandler);
		
		final ChangeHandler tbChangeHandler = new ChangeHandler() {
			
			@Override
			public void onChange(ChangeEvent event) {
				applyCss();
			}
		};
		
		this._panelDetailBorderRadius.setChangeHandler(tbChangeHandler);		
		this._tbRadiusAll.addChangeHandler(tbChangeHandler);
	}
}
