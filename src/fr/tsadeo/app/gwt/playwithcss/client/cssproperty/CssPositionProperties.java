package fr.tsadeo.app.gwt.playwithcss.client.cssproperty;



import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import fr.tsadeo.app.gwt.playwithcss.client.cssproperty.CssStyleBean.CssStyle;
import fr.tsadeo.app.gwt.playwithcss.client.util.Types.Positions;
import fr.tsadeo.app.gwt.playwithcss.client.util.WidgetUtils;


public class CssPositionProperties extends AbstractCssProperties {
	private static final String NAME = "Position";
	private static final String DISPLAY_NAME = NAME;
	
	private static final String GROUP_POSITION = "position";
	
	private final VerticalPanel _main = new VerticalPanel();
	private final VerticalPanel _panelDetailPosition = new VerticalPanel();
	
	private RadioButton _rbStatic;
	private RadioButton _rbRelative;
	private RadioButton _rbAbsolute;
	private RadioButton _rbFixed;
	
	private final CheckBox _cbPosition = new CheckBox("position");
	private final TextBox _tbZIndex = new TextBox();
	
	private final CssPanelDetail _panelSize = new CssPanelDetail();

	public CssPositionProperties(final String title, final PanelWidgetProperties panelProperties) {
		super(NAME + "." + title, panelProperties, false);
		
		this.initRadioButton();
		this.initHandlers();
		this.getContent().add(this.buildMain());
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
			this._cbPosition.setValue(false);
			this._rbStatic.setValue(true);
			this.reinitTextBoxes();
			this.displayPanelDetailPosition();
			this.applyCss();
		}
		@Override
		protected void applyCss() {
			this.applyPosition(null);
		}
		@Override
		protected void getDisplayCssProperties(final StringBuilder sb) {
			this.applyPosition(sb);
		}
		//------------------------------------------ private methods
		private Panel buildMain() {
			
			this._main.setSpacing(5);
			
			final VerticalPanel panelRadioButton = new VerticalPanel();
			panelRadioButton.add(this._rbStatic);
			panelRadioButton.add(this._rbAbsolute);
			panelRadioButton.add(this._rbRelative);
			panelRadioButton.add(this._rbFixed);
			panelRadioButton.setStyleName("panelRadioButtons");
			
			final HorizontalPanel hPanel = new HorizontalPanel();
			hPanel.add(panelRadioButton);
			hPanel.add(this._panelSize);
			
			this._panelDetailPosition.add(hPanel);
			this._panelDetailPosition.add(WidgetUtils.buildTextBox( CssStyle.positionZIndex.getStyleName(), this._tbZIndex));
			
			this._main.add(this._cbPosition);
			this._main.add(this._panelDetailPosition);
			return this._main;
					
		}
		

		private void displayPanelDetailPosition() {
			this._panelDetailPosition.setVisible(this._cbPosition.getValue());
		}
		private void displayForStaticPosition() {
			this._panelSize.setVisible(!this._rbStatic.getValue());
		}
		
		private void reinitTextBoxes() {
			final String defaultPos = "10px";
			final String nonePos = "0px";

			this._panelSize.reinit(defaultPos, nonePos, defaultPos, nonePos);
			this._tbZIndex.setValue("-1");
		}
		private void initRadioButton() {
			
			String group = this.buildGroupName(GROUP_POSITION);
			this._rbStatic = new RadioButton(group, CssStyle.positionStatic.getStyleValue());
			this._rbRelative = new RadioButton(group, CssStyle.positionRelative.getStyleValue());
			this._rbAbsolute = new RadioButton(group,CssStyle.positionAbsolute.getStyleValue());
			this._rbFixed = new RadioButton(group, CssStyle.positionFixed.getStyleValue());
				
		}
		
		private void initHandlers() {
			
			// check box
			this._cbPosition.addClickHandler(new ClickHandler() {
				
				@Override
				public void onClick(ClickEvent event) {
					displayPanelDetailPosition();
					applyCss();
				}
			});
			
			// radio button
			final ClickHandler rbClickHandler = new ClickHandler() {
				
				@Override
				public void onClick(ClickEvent event) {

					applyCss();
					
				}
			};

			this._rbStatic.addClickHandler(rbClickHandler);
			this._rbAbsolute.addClickHandler(rbClickHandler);
			this._rbFixed.addClickHandler(rbClickHandler);
			this._rbRelative.addClickHandler(rbClickHandler);
			
			
			// text boxes
			final ChangeHandler tbChangeHandler = new ChangeHandler() {
					
				@Override
				public void onChange(ChangeEvent event) {

					applyCss();
					
				}
			};
		    this._panelSize.setChangeHandler(tbChangeHandler);
		    this._tbZIndex.addChangeHandler(tbChangeHandler);
		}

		
		
		private void removeAllPosition() {
			if (this._elementToControl == null) return;
			
			this.removeStyle(CssStyle.positionStatic);
			this.removeStyle(CssStyle.positionAbsolute);
			this.removeStyle(CssStyle.positionRelative);
			this.removeStyle(CssStyle.positionFixed);
			
			this.removeStyle(CssStyle.positionTop);
			this.removeStyle(CssStyle.positionBottom);
			this.removeStyle(CssStyle.positionLeft);
			this.removeStyle(CssStyle.positionRight);
			
			this.removeStyle(CssStyle.positionZIndex);
	
		}
		private void applyPosition(final StringBuilder sb) {
			
			this.displayForStaticPosition();
			
			if (this._elementToControl == null) return;
			if(sb == null) {
			  this.removeAllPosition();
			}
			if (!this._cbPosition.getValue()) return;
			
			if (this._rbAbsolute.getValue()) {
				this.applyStyle(sb, CssStyle.positionAbsolute);
			}
			else if (this._rbFixed.getValue()) {
				this.applyStyle(sb, CssStyle.positionFixed);
			}
			else if (this._rbRelative.getValue()) {
				this.applyStyle(sb, CssStyle.positionRelative);
			}
			if (this._rbStatic.getValue()) {
				this.applyStyle(sb, CssStyle.positionStatic);
			}
			
			//numeric position
			if (this._rbStatic.getValue()) return;
			
			this.applyStyle(sb, CssStyle.positionTop.updateStyleValue(this._panelSize.getValue(Positions.top)));
			this.applyStyle(sb, CssStyle.positionBottom.updateStyleValue(this._panelSize.getValue(Positions.bottom)));
			this.applyStyle(sb, CssStyle.positionRight.updateStyleValue(this._panelSize.getValue(Positions.right)));
			this.applyStyle(sb, CssStyle.positionLeft.updateStyleValue(this._panelSize.getValue(Positions.left)));
			// z-index
			this.applyStyle(sb, CssStyle.positionZIndex.updateStyleValue(this._tbZIndex.getValue()));
			
		}

}
