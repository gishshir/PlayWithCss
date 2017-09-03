package fr.tsadeo.app.gwt.playwithcss.client.cssproperty;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import fr.tsadeo.app.gwt.playwithcss.client.cssproperty.CssStyleBean.CssStyle;
import fr.tsadeo.app.gwt.playwithcss.client.util.WidgetUtils;

public class CssBackgroundProperties extends AbstractCssProperties {
	
	private enum Position {
		top, left, center,  bottom, right;
	}
	
	private static final String NAME = "Background";
	private static final String DISPLAY_NAME = NAME;
	private static final String LABEL_WIDTH = "160px";
	
	private static final String[] IMAGE_NAMES = new String[] {
	  "file", "image", "music", "video", "orange", "black", "dog", "blue"	
	};
	
	private static final String GROUP_COLOR = "color";
	private static final String GROUP_IMAGE = "image";
	private static final String GROUP_REPEAT = "repeat";
	
	private static final String GROUP_VPOSITION = "vposition";
	private static final String GROUP_HPOSITION = "hposition";
	
	private static final String GROUP_ATTACHMENT = "attachment";
	
	private final VerticalPanel _main = new VerticalPanel();
	private final HorizontalPanel _panelPosition = new HorizontalPanel();
	private final HorizontalPanel _panelDetailPosition = new HorizontalPanel();
	
	private final HorizontalPanel _panelAttachment = new HorizontalPanel();
	private final HorizontalPanel _panelDetailAttachment = new HorizontalPanel();
	
	private final HorizontalPanel _panelRepeat = new HorizontalPanel();
	private final HorizontalPanel _panelDetailRepeat = new HorizontalPanel();
	
	private final HorizontalPanel _panelDetailColor = new HorizontalPanel();
	private final HorizontalPanel _panelDetailImage = new HorizontalPanel();
	
	private CheckBox _cbBackgroundColor = new CheckBox(CssStyle.backgroundColor.getStyleName());
	private CheckBox _cbBackgroundImage = new CheckBox(CssStyle.backgroundImage.getStyleName());
	private CheckBox _cbBackgroundAttachment = new CheckBox(CssStyle.backgroundAttachmentFixed.getStyleName());
	private CheckBox _cbBackgroundRepeat = new CheckBox(CssStyle.backgroundRepeat.getStyleName());
	private CheckBox _cbBackgroundPosition = new CheckBox(CssStyle.backgroundPosition.getStyleName());
	
	private RadioButton _rbColorColor;
	private RadioButton _rbColorTransparent;
	private final TextBox _tbColor = new TextBox();
	
	private RadioButton _rbImageImage;
	private RadioButton _rbImageNone;
	private final ListBox _lbImages = new ListBox();
	
	private RadioButton _rbRepeat;
	private RadioButton _rbRepeatX;
	private RadioButton _rbRepeatY;
	private RadioButton _rbNoRepeat;
	
	private RadioButton _rbPositionTop;
	private RadioButton _rbPositionVCenter;
	private RadioButton _rbPositionBottom;
	
	private RadioButton _rbPositionLeft;
	private RadioButton _rbPositionHCenter;
	private RadioButton _rbPositionRight;
	private final TextBox _tbVPosition = new TextBox();
	private final TextBox _tbHPosition = new TextBox();
	
	private RadioButton _rbAttachmentScroll;
	private RadioButton _rbAttachmentFixed;
	
	
	public CssBackgroundProperties(final String title, final AbstractPanelProperties panelProperties) {
		super(NAME + "." + title, panelProperties);
		
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
		
		this._cbBackgroundAttachment.setValue(false);
		this._cbBackgroundColor.setValue(false);
		this._cbBackgroundImage.setValue(false);
		this._cbBackgroundPosition.setValue(false);
		this._cbBackgroundRepeat.setValue(false);
		
		this._rbColorTransparent.setValue(true);
		this._rbImageNone.setValue(true);
		this._lbImages.setSelectedIndex(0);
		this._rbRepeat.setValue(true);
		this._rbPositionTop.setValue(true);
		this._rbPositionHCenter.setValue(true);
		this._rbAttachmentScroll.setValue(true);
		
		this._tbColor.setText("");
	    this.reinitTextBoxesPosition();
		
		this.displayPanels();
		this.applyCss();
	}

	@Override
	protected void applyCss() {
		this.applyBackground(null);	
	}
	@Override
	protected void getDisplayCssProperties(final StringBuilder sb) {
		this.applyBackground(sb);	
	}
	//---------------------------------- private methods
	private void reinitTextBoxesPosition() {
		this._tbVPosition.setText("");
		this._tbHPosition.setText("");
	}
	
	private void removeAllBackground() {
		if (this._elementToControl == null) return;
		
		this.removeStyle(CssStyle.backgroundAttachmentFixed);
		this.removeStyle(CssStyle.backgroundAttachmentScroll);
		
		this.removeStyle(CssStyle.backgroundRepeat);
		this.removeStyle(CssStyle.backgroundRepeatX);
		this.removeStyle(CssStyle.backgroundRepeatY);
		this.removeStyle(CssStyle.backgroundNoRepeat);
		
		this.removeStyle(CssStyle.backgroundColor);
		this.removeStyle(CssStyle.backgroundPosition);
		this.removeStyle(CssStyle.backgroundImage);

	}
	private void applyBackground(final StringBuilder sb) {
		
		
		if (this._elementToControl == null) return;
		if (sb == null) {
		  this.removeAllBackground();
		}
		
		// Background-Attachment [scroll/fixed]
		if (this._cbBackgroundAttachment.getValue()) {
			if (this._rbAttachmentFixed.getValue()) {
				this.applyStyle(sb, CssStyle.backgroundAttachmentFixed);
			} else {
				this.applyStyle(sb, CssStyle.backgroundAttachmentScroll);
			}
		}
		
		
		// Background-color
		if (this._cbBackgroundColor.getValue()) {
			if (this._rbColorColor.getValue()
					&& WidgetUtils.getTextBoxValueLength(this._tbColor) > 0) {
				this.applyStyle(sb, CssStyle.backgroundColor.updateStyleValue(this._tbColor.getText()));
			} else {
				this.applyStyle(sb, CssStyle.backgroundColor.updateStyleValue("transparent"));
			}
		}
		

		// Background-image
		boolean withImage = false;
		final int indexImage = this._lbImages.getSelectedIndex();
		
		if (this._cbBackgroundImage.getValue()) {
			if (this._rbImageImage.getValue() && indexImage > 0) {
				withImage = true;
				final String url = "url(" + GWT.getModuleName() + "/images/"
						+ this._lbImages.getValue(indexImage) + ".jpg)";
				this.applyStyle(sb, CssStyle.backgroundImage.updateStyleValue(url));
			} else {
				this.removeStyle(CssStyle.backgroundImage);
			}
		}
				
		// Background-repeat
		if (this._cbBackgroundRepeat.getValue() && withImage) {
			if (this._rbRepeat.getValue()) {
				this.applyStyle(sb, CssStyle.backgroundRepeat);
			} else if (this._rbRepeatX.getValue()) {
				this.applyStyle(sb, CssStyle.backgroundRepeatX);
			} else if (this._rbRepeatY.getValue()) {
				this.applyStyle(sb, CssStyle.backgroundRepeatY);
			} else { // no-repeat
				this.applyStyle(sb, CssStyle.backgroundNoRepeat);
			}
		}
		
		// Background-position
		if (this._cbBackgroundPosition.getValue() 
				&& withImage && this._rbNoRepeat.getValue()) {
			
			String vposition = "";
			if ( WidgetUtils.getTextBoxValueLength(this._tbVPosition) > 0) {
				WidgetUtils.completeSuffixPx(this._tbVPosition);
				vposition = this._tbVPosition.getText();
			}
			else if (this._rbPositionTop.getValue()) {
				vposition = Position.top.name();	
			}
			else if (this._rbPositionVCenter.getValue()) {
				vposition = Position.center.name();	
			}
			else if (this._rbPositionBottom.getValue()) {
				vposition = Position.bottom.name();	
			}
			
			String hposition = "";
			if (WidgetUtils.getTextBoxValueLength(this._tbHPosition) > 0) {
				WidgetUtils.completeSuffixPx(this._tbHPosition);
				hposition = this._tbHPosition.getText();
			}
			else if (this._rbPositionLeft.getValue()) {
				hposition = Position.left.name();
			}
			else if (this._rbPositionHCenter.getValue()) {
				hposition = Position.center.name();
			}
			else if (this._rbPositionRight.getValue()) {
				hposition = Position.right.name();
			}
			
			this.applyStyle(sb, CssStyle.backgroundPosition.updateStyleValue(hposition + " " + vposition));
			
		} 
	}
	
	private void initAll() {
		this.initRadioButton();
		this.initListImages();
		this.initTextBoxes();
		this.initHandlers();
	}

	private void initListImages() {
		this._lbImages.addItem("-- none --", "0");
		
		final List<String> listImages = Arrays.asList(IMAGE_NAMES);
		Collections.sort(listImages);
		for (int i = 0; i < listImages.size(); i++) {
			final String name = listImages.get(i);
			this._lbImages.addItem(name,name );	
		}
	
		
	}
	private void initTextBoxes () {
		this._tbColor.setWidth("50px");
		this._tbColor.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				_rbColorColor.setValue(true);
				applyCss();
			}
		});
		
		this._tbVPosition.setWidth("40px");
		this._tbHPosition.setWidth("40px");
	}
	private void initRadioButton() {

		String group = this.buildGroupName(GROUP_COLOR);
		this._rbColorColor = new RadioButton(group, "");
		this._rbColorTransparent = new RadioButton(group, "transparent");
		
		group = this.buildGroupName(GROUP_IMAGE);
		this._rbImageImage = new RadioButton(group, "");
		this._rbImageNone = new RadioButton(group, "none");
		
		group = this.buildGroupName(GROUP_REPEAT);
		this._rbRepeat = new RadioButton(group, CssStyle.backgroundRepeat.getStyleValue() + " [default]");
		this._rbRepeatX = new RadioButton(group,CssStyle.backgroundRepeatX.getStyleValue());
		this._rbRepeatY = new RadioButton(group, CssStyle.backgroundRepeatY.getStyleValue());
		this._rbNoRepeat = new RadioButton(group, CssStyle.backgroundNoRepeat.getStyleValue());
		
		group = this.buildGroupName(GROUP_VPOSITION);
		this._rbPositionTop = new RadioButton(group, Position.top.name());
		this. _rbPositionVCenter = new RadioButton(group, Position.center.name());
		this. _rbPositionBottom = new RadioButton(group, Position.bottom.name());
		
		group = this.buildGroupName(GROUP_HPOSITION);
		this._rbPositionLeft = new RadioButton(group, Position.left.name());
		this. _rbPositionHCenter = new RadioButton(group,Position.center.name());
		this. _rbPositionRight = new RadioButton(group, Position.right.name());
		
		group = this.buildGroupName(GROUP_ATTACHMENT);
		this._rbAttachmentScroll = new RadioButton(group, CssStyle.backgroundAttachmentScroll.getStyleValue() + " [default]");
		this._rbAttachmentFixed = new RadioButton(group, CssStyle.backgroundAttachmentFixed.getStyleValue());
				
	}
	
	private void displayPanels () {
		
		this._panelDetailImage.setVisible(this._cbBackgroundImage.getValue());
		this._panelDetailColor.setVisible(this._cbBackgroundColor.getValue());
		
		if (this._rbImageImage.getValue() &&
				this._lbImages.getSelectedIndex() > 0 ) {
			
			// panel attachment
			this._panelAttachment.setVisible(true);
			this._panelDetailAttachment.setVisible(this._cbBackgroundAttachment.getValue());
			// panel repeat
			this._panelRepeat.setVisible(true);
			this._panelDetailRepeat.setVisible(this._cbBackgroundRepeat.getValue());
			
			// panel position
			this._panelPosition.setVisible( this._rbNoRepeat.getValue());
            this._panelDetailPosition.setVisible(this._cbBackgroundPosition.getValue());
			
		}
		else {
			// panel attachment
			this._panelAttachment.setVisible(false);
			// panel repeat
			this._panelRepeat.setVisible(false);
			// panel position
			this._panelPosition.setVisible(false);
		}
		

	}
	
	private void initHandlers() {

		
		final ClickHandler rbClickHandler = new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				displayPanels();
				applyCss();				
			}
		};
		this._rbAttachmentFixed.addClickHandler(rbClickHandler);
		this._rbAttachmentFixed.addClickHandler(rbClickHandler);
		this._rbColorColor.addClickHandler(rbClickHandler);
		this._rbColorTransparent.addClickHandler(rbClickHandler);
		this._rbImageImage.addClickHandler(rbClickHandler);
		this._rbImageNone.addClickHandler(rbClickHandler);
				
		this._rbNoRepeat.addClickHandler(rbClickHandler);
		this._rbRepeat.addClickHandler(rbClickHandler);
		this._rbRepeatX.addClickHandler(rbClickHandler);
		this._rbRepeatY.addClickHandler(rbClickHandler);
		
		this._cbBackgroundAttachment.addClickHandler(rbClickHandler);
		this._cbBackgroundColor.addClickHandler(rbClickHandler);
		this._cbBackgroundImage.addClickHandler(rbClickHandler);
		this._cbBackgroundPosition.addClickHandler(rbClickHandler);
		this._cbBackgroundRepeat.addClickHandler(rbClickHandler);
		
		final ClickHandler positionHandler = new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				reinitTextBoxesPosition();
				applyCss();	
			}
		};
		this._rbPositionBottom.addClickHandler(positionHandler);
		this._rbPositionVCenter.addClickHandler(positionHandler);
		this._rbPositionLeft.addClickHandler(positionHandler);
		this._rbPositionHCenter.addClickHandler(positionHandler);
		this._rbPositionRight.addClickHandler(positionHandler);
		this._rbPositionTop.addClickHandler(positionHandler);
		
		this._tbColor.addChangeHandler(new ChangeHandler() {
			
			@Override
			public void onChange(ChangeEvent event) {
				applyCss();			
			}
		});
		
		this._tbVPosition.addChangeHandler(new ChangeHandler() {
			
			@Override
			public void onChange(ChangeEvent event) {
				
				if (WidgetUtils.getTextBoxValueLength(_tbVPosition) > 0) {
				  _rbPositionTop.setValue(false);
				  _rbPositionVCenter.setValue(false);
				  _rbPositionBottom.setValue(false);	
				}
				applyCss();	
			}
		});
		this._tbHPosition.addChangeHandler(new ChangeHandler() {
			
			@Override
			public void onChange(ChangeEvent event) {
				if (WidgetUtils.getTextBoxValueLength(_tbHPosition) > 0) {
				  _rbPositionLeft.setValue(false);
				  _rbPositionHCenter.setValue(false);
				  _rbPositionRight.setValue(false);	
				}
				applyCss();
				
			}
		});
		
		this._lbImages.addChangeHandler(new ChangeHandler() {
			
			@Override
			public void onChange(ChangeEvent event) {
				final boolean none = _lbImages.getSelectedIndex() == 0;
				_rbImageNone.setValue(none);	
				_rbImageImage.setValue(!none);
				displayPanels();
				applyCss();	
			}
		});
	}
	
	private Panel buildMainPanel() {
		
		this._main.setSpacing(1);
		this._main.add(this.buildColorPanel());
		this._main.add(this.buildImagePanel());
		this._main.add(this.buildAttachmentPanel());
		this._main.add(this.buildRepeatPanel());
		this._main.add(this.buildPositionPanel());
			
		return this._main;
	}
	
	private Panel buildPanelCheckbox (final CheckBox checkbox) {
		final SimplePanel panel = new SimplePanel();
		panel.setWidth(LABEL_WIDTH);
		panel.add(checkbox);
		return panel;
	}
	
    private Panel buildColorPanel () {
    	
    	this._panelDetailColor.setSpacing(3);
    	this._panelDetailColor.add(this._rbColorColor);
    	this._panelDetailColor.add(this._tbColor);
    	this._panelDetailColor.add(this._rbColorTransparent);
    	
    	final HorizontalPanel panel = new HorizontalPanel();
    	panel.add(this.buildPanelCheckbox(this._cbBackgroundColor));
    	panel.add(this._panelDetailColor);
    		
    	return panel;
    }
    
    private Panel buildImagePanel() {
    	
    	this._panelDetailImage.setSpacing(3);
    	this._panelDetailImage.add(this._rbImageImage);
    	this._panelDetailImage.add(this._lbImages);
    	this._panelDetailImage.add(this._rbImageNone);
    	
    	final HorizontalPanel panel = new HorizontalPanel();
    	panel.add(this.buildPanelCheckbox(this._cbBackgroundImage));
    	panel.add(this._panelDetailImage);
    	
    	return panel;
    }
    
    private Panel buildAttachmentPanel() {

    	this._panelDetailAttachment.setSpacing(3);
    	this._panelDetailAttachment.add(this._rbAttachmentScroll);
    	this._panelDetailAttachment.add(this._rbAttachmentFixed);

    	this._panelAttachment.add(this.buildPanelCheckbox(this._cbBackgroundAttachment));
    	this._panelAttachment.add(this._panelDetailAttachment);
    	return this._panelAttachment;	
    }
    
    private Panel buildRepeatPanel () {
    	
    	final VerticalPanel rbPanel1 = new VerticalPanel();
    	rbPanel1.add(this._rbRepeat);
    	rbPanel1.add(this._rbRepeatX);
    	
    	final VerticalPanel rbPanel2 = new VerticalPanel();
    	rbPanel2.add(this._rbRepeatY);
    	rbPanel2.add(this._rbNoRepeat);
    	
    	this._panelDetailRepeat.setSpacing(3);
    	this._panelDetailRepeat.add(rbPanel1);
    	this._panelDetailRepeat.add(rbPanel2);
    	
    	this._panelRepeat.add(this.buildPanelCheckbox(this._cbBackgroundRepeat));
    	this._panelRepeat.add(this._panelDetailRepeat);
    	return this._panelRepeat;	
    }
    
    private Panel buildPositionPanel () {
   	
    	final VerticalPanel rbPanel1 = new VerticalPanel();
    	rbPanel1.add(this._rbPositionTop);
    	rbPanel1.add(this._rbPositionVCenter);
    	rbPanel1.add(this._rbPositionBottom);
    	rbPanel1.add(this._tbVPosition);
    	
    	final VerticalPanel rbPanel2 = new VerticalPanel();
    	rbPanel2.add(this._rbPositionLeft);
    	rbPanel2.add(this._rbPositionHCenter);
    	rbPanel2.add(this._rbPositionRight);
    	rbPanel2.add(this._tbHPosition);

    	this._panelDetailPosition.setSpacing(3);
    	this._panelDetailPosition.add(rbPanel2);
    	this._panelDetailPosition.add(rbPanel1);

    	this._panelPosition.add(this.buildPanelCheckbox(this._cbBackgroundPosition));
    	this._panelPosition.add(this._panelDetailPosition);
    	
    	return this._panelPosition;	
    }


    
}
