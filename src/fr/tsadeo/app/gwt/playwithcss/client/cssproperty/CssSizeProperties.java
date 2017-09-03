package fr.tsadeo.app.gwt.playwithcss.client.cssproperty;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import fr.tsadeo.app.gwt.playwithcss.client.cssproperty.CssStyleBean.CssStyle;
import fr.tsadeo.app.gwt.playwithcss.client.util.WidgetUtils;

public class CssSizeProperties extends AbstractCssProperties {
	
	private static final String NAME = "Size";
	private static final String DISPLAY_NAME = NAME;
	
	private final VerticalPanel _main = new VerticalPanel();
	private final VerticalPanel _panelDetailSize = new VerticalPanel();
	
	private final CheckBox _cbSize = new CheckBox("size");
	
	private final TextBox _tbWidth = new TextBox();
	private final TextBox _tbHeight = new TextBox();
	private final TextBox _tbMaxWith = new TextBox();
	private final TextBox _tbMinWidth = new TextBox();	

	public CssSizeProperties(final String title, final PanelWidgetProperties panelProperties) {
		super(NAME + "." + title, panelProperties, false);

        this.initHandler();
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
			this._cbSize.setValue(false);
			this.reinitTextBoxes();
			this.displayDetailPanelSize();
			this.applyCss();
		}
		@Override
		protected void applyCss() {
			this.applySizes(null);
		}
		@Override
		protected void getDisplayCssProperties(final StringBuilder sb) {
			this.applySizes(sb);
		}
		//------------------------------------------ private methods
		private Panel buildMain() {
			
			this._main.setSpacing(5);
			
			this._panelDetailSize.add(WidgetUtils.buildTextBox(CssStyle.sizeWidth.getStyleName(), this._tbWidth));
			this._panelDetailSize.add(WidgetUtils.buildTextBox(CssStyle.sizeHeight.getStyleName(), this._tbHeight));
			this._panelDetailSize.add(WidgetUtils.buildTextBox(CssStyle.sizeMaxWidth.getStyleName(), this._tbMaxWith));
			this._panelDetailSize.add(WidgetUtils.buildTextBox(CssStyle.sizeMinWidth.getStyleName(), this._tbMinWidth));

			
			this._main.add(this._cbSize);
			this._main.add(this._panelDetailSize);
			return this._main;
					
		}
		private void reinitTextBoxes() {
			
			final String defaultPos = "50px";
			this._tbWidth.setValue(defaultPos);
			this._tbHeight.setValue(defaultPos);
			this._tbMaxWith.setValue(null);
			this._tbMinWidth.setValue(null);
		}
		private void initHandler() {
			
			// check box
			this._cbSize.addClickHandler(new ClickHandler() {
				
				@Override
				public void onClick(ClickEvent event) {
					displayDetailPanelSize();
					applyCss();
				}
			});
			
			// text boxes
			 final ChangeHandler tbChangeHandler = new ChangeHandler() {
				
				@Override
				public void onChange(ChangeEvent event) {

					applyCss();
					
				}
			};
		    this._tbWidth.addChangeHandler(tbChangeHandler);
		    this._tbHeight.addChangeHandler(tbChangeHandler);
		    this._tbMaxWith.addChangeHandler(tbChangeHandler);
		    this._tbMinWidth.addChangeHandler(tbChangeHandler);
		}
		
		private void displayDetailPanelSize() {
			this._panelDetailSize.setVisible(this._cbSize.getValue());
		}
		
		private void removeAllSizes() {
			if (this._elementToControl == null) return;
			
			this.removeStyle(CssStyle.sizeWidth);
			this.removeStyle(CssStyle.sizeHeight);
			this.removeStyle(CssStyle.sizeMinWidth);
			this.removeStyle(CssStyle.sizeMaxWidth);
		}
		
		private void applySizes(final StringBuilder sb) {
			
			if (this._elementToControl == null) return;
			if (sb == null) {
			  this.removeAllSizes();
			}
			if (!this._cbSize.getValue()) return;
			
			WidgetUtils.completeSuffixPx(this._tbHeight);
			WidgetUtils.completeSuffixPx(this._tbMaxWith);
			WidgetUtils.completeSuffixPx(this._tbMinWidth);
			WidgetUtils.completeSuffixPx(this._tbWidth);
	
			this.applyStyle(sb, CssStyle.sizeWidth.updateStyleValue(this._tbWidth.getValue()));
			this.applyStyle(sb, CssStyle.sizeHeight.updateStyleValue( this._tbHeight.getValue()));
			this.applyStyle(sb,CssStyle.sizeMinWidth.updateStyleValue(this._tbMinWidth.getValue()));
			this.applyStyle(sb, CssStyle.sizeMaxWidth.updateStyleValue(this._tbMaxWith.getValue()));
						

		}
}
