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
import com.google.gwt.user.client.ui.HasAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import fr.tsadeo.app.gwt.playwithcss.client.cssproperty.CssStyleBean.CssStyle;
import fr.tsadeo.app.gwt.playwithcss.client.util.WidgetUtils;

public class CssContentProperties extends AbstractCssProperties{
	
	private static final String NAME = "ContentTextorImage";
	private static final String DISPLAY_NAME = "Content (text or image)";
	
	private static final String GROUP_PRINC = NAME + "princ";
	private static final String GROUP_DIRECTION = NAME + "direction";
	private static final String GROUP_OVERFLOW = NAME + "overflow";
	
	private static final String CLIP_DEFAULT = "0px";
	
	private static final String[] IMAGE_NAMES = new String[] {
		  "faq", "flower", "flowers", "harp", "rabbit", "house",
		  "chick", "spider", "dog", "orange"
		};

	private final VerticalPanel _main = new VerticalPanel();
	
	private final HorizontalPanel _textDetailPanel = new HorizontalPanel();
	private final VerticalPanel _overfowDetailPanel = new VerticalPanel();
	private final VerticalPanel _clipDetailPanel = new VerticalPanel();
	
	private RadioButton _rbText;
	private RadioButton _rbImage;
	
	private RadioButton _rbDirectionLtR;
	private RadioButton _rbDirectionRtL;
	
	private RadioButton _rbOverflowVisible;
	private RadioButton _rbOverflowHidden;
	private RadioButton _rbOverflowScroll;
	private RadioButton _rbOverflowAuto;
	
	private final CheckBox _cbOverflow = new CheckBox("overflow");
	private final CheckBox _cbClip = new CheckBox("clip");
	
	private final ListBox _lbImages = new ListBox();
	
	private final TextBox _tbClipTop = new TextBox();
	private final TextBox _tbClipWidth = new TextBox();
	private final TextBox _tbClipHeight = new TextBox();
	private final TextBox _tbClipLeft = new TextBox();
	
	
	private final TextArea _textArea = new TextArea();
	private final String _defaultText;
	
	public CssContentProperties(final String title, final PanelWidgetProperties panelProperties) {
		super(NAME + "." + title, panelProperties);
		this._defaultText = title;
		
		this.initAll();
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
		
		 // text
		 this._rbText.setValue(true);
		 this._textArea.setText(this._defaultText);
		 
		 // image
		 this._lbImages.setSelectedIndex(0);
		 
		 //overflow
		 this._cbOverflow.setValue(false);
		 this._rbOverflowVisible.setValue(true);
		 
		 // clip
		 this._cbClip.setValue(false);
		 this._tbClipHeight.setText("");
		 this._tbClipLeft.setText("");
		 this._tbClipTop.setText("");
		 this._tbClipWidth.setText("");
		 
		 //direction
		 this._rbDirectionLtR.setValue(true);
        
         displayPanels();
         applyCss();
	}
	
	@Override
	protected void applyCss() {
		this.applyContent(null);
	}
	
	@Override
	protected void getDisplayCssProperties(final StringBuilder sb) {

		this.applyContent(sb);
	}
	//------------------------------------------ private methods
	private Panel buildMainPanel() {
		
	   // text
	   final HorizontalPanel hPanelText = new HorizontalPanel();
	   hPanelText.setSpacing(5);
	   hPanelText.add(this._rbText);
	   hPanelText.add(this.buildTextDetailPanel());
	   this._main.add(hPanelText);
	   
	   // image
	   final HorizontalPanel hPanelImage = new HorizontalPanel();
	   hPanelImage.setSpacing(5);
	   hPanelImage.add(this._rbImage);
	   hPanelImage.add(this._lbImages);
	   this._main.add(hPanelImage);
	   
	   // overflow
	   final HorizontalPanel hPanelOverflow = new HorizontalPanel();
	   hPanelOverflow.setSpacing(5);
	   hPanelOverflow.add(this._cbOverflow);
	   hPanelOverflow.add(this.buildOverflowDetailPanel());
	   this._main.add(hPanelOverflow);
	   
	   // clip
	   final HorizontalPanel hPanelClip = new HorizontalPanel();
	   hPanelClip.setSpacing(5);
	   hPanelClip.add(this._cbClip);
	   hPanelClip.add(this.buildClipDetailPanel());
	   this._main.add(hPanelClip);
	   
       return this._main;
	}
	
	private Panel buildTextDetailPanel() {
		
		this._textArea.setStylePrimaryName("panelTextArea");
		this._textArea.addChangeHandler(new ChangeHandler() {
			
			@Override
			public void onChange(ChangeEvent event) {

				applyCss();
				
			}
		});
		
		this._textDetailPanel.add(this._textArea);
		
		// direction
		final VerticalPanel vPanelDirection = new VerticalPanel();
		vPanelDirection.add(new Label("direction: "));
		vPanelDirection.add(this._rbDirectionLtR);
		vPanelDirection.add(this._rbDirectionRtL);
		this._textDetailPanel.add(vPanelDirection);
		
		return this._textDetailPanel;
	}
	private Panel buildOverflowDetailPanel() {
		
		this._overfowDetailPanel.add(this._rbOverflowVisible);
		this._overfowDetailPanel.add(this._rbOverflowHidden);
		this._overfowDetailPanel.add(this._rbOverflowScroll);
		this._overfowDetailPanel.add(this._rbOverflowAuto);
		
		return this._overfowDetailPanel;
	}
	private Panel buildClipDetailPanel() {
		
	  this._clipDetailPanel.setHeight("120px");
	  this._clipDetailPanel.setVerticalAlignment(HasAlignment.ALIGN_BOTTOM);
	  this._clipDetailPanel.add(WidgetUtils.buildTextBox("hide top", this._tbClipTop));
	  this._clipDetailPanel.add(WidgetUtils.buildTextBox("trim width", this._tbClipWidth));
	  this._clipDetailPanel.add(WidgetUtils.buildTextBox("trim heigth", this._tbClipHeight));
	  this._clipDetailPanel.add(WidgetUtils.buildTextBox("hide left", this._tbClipLeft));
		
	  return this._clipDetailPanel;	
	}
	private void initAll() {
		this.initListImages();
		this.initComponents();
		this.initHandlers();
	}
	private void initListImages() {
		
		final List<String> listImages = Arrays.asList(IMAGE_NAMES);
		Collections.sort(listImages);
		for (int i = 0; i < listImages.size(); i++) {
			final String name = listImages.get(i);
			this._lbImages.addItem(name,name );	
		}
		
		this._lbImages.addChangeHandler(new ChangeHandler() {
			
			@Override
			public void onChange(ChangeEvent event) {
				applyCss();	
			}
		});
		
	}
	private void initComponents() {
			
		// radio button --------------------
		
		// text / image
	    String group = this.buildGroupName(GROUP_PRINC);
		this._rbText = new RadioButton(group, "text");
		this._rbImage = new RadioButton(group, "image");
		
		// direction
		group = this.buildGroupName(GROUP_DIRECTION);
		this._rbDirectionLtR = new RadioButton(group, CssStyle.dirLtr.getStyleValue());
		this._rbDirectionRtL = new RadioButton(group, CssStyle.dirRtl.getStyleValue());
		
		// overflow
		group = this.buildGroupName(GROUP_OVERFLOW);
		this._rbOverflowAuto = new RadioButton(GROUP_OVERFLOW, CssStyle.overflowAuto.getStyleValue());
		this._rbOverflowHidden = new RadioButton(GROUP_OVERFLOW, CssStyle.overflowHidden.getStyleValue());
		this._rbOverflowScroll = new RadioButton(GROUP_OVERFLOW, CssStyle.overflowScroll.getStyleValue());
		this._rbOverflowVisible = new RadioButton(GROUP_OVERFLOW, CssStyle.overflowVisible.getStyleValue() + " [default]");
		
	}
	private void initHandlers() {
		
		final ClickHandler clickHandlerPrinc = new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				displayPanels();
				applyCss();
			}
		};
		
		final ClickHandler clickHandlerApply = new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				applyCss();
			}
		};
		
		final ChangeHandler changeHandlerApply = new ChangeHandler() {
			
			@Override
			public void onChange(ChangeEvent event) {
				applyCss();
			}
		};
			
		// radio button --------------------
		
		// text / image		
		this._rbText.addClickHandler(clickHandlerPrinc);
		this._rbImage.addClickHandler(clickHandlerPrinc);
		
		// direction
		this._rbDirectionLtR.addClickHandler(clickHandlerApply);
		this._rbDirectionRtL.addClickHandler(clickHandlerApply);
		
		// overflow
		this._rbOverflowAuto.addClickHandler(clickHandlerApply);
		this._rbOverflowHidden.addClickHandler(clickHandlerApply);
		this._rbOverflowScroll.addClickHandler(clickHandlerApply);
		this._rbOverflowVisible.addClickHandler(clickHandlerApply);
		
		//check boxes
		this._cbOverflow.addClickHandler(clickHandlerPrinc);
		this._cbClip.addClickHandler(clickHandlerPrinc);
		
		// Text boxes
		this._tbClipHeight.addChangeHandler(changeHandlerApply);
		this._tbClipLeft.addChangeHandler(changeHandlerApply);
		this._tbClipTop.addChangeHandler(changeHandlerApply);
		this._tbClipWidth.addChangeHandler(changeHandlerApply);
	}

	
	private void displayPanels() {

          // panel detail text
		final boolean showText = this._rbText.getValue();
		this._textDetailPanel.setVisible(showText);
		this._lbImages.setVisible(!showText);
		
		// overflow
		this._overfowDetailPanel.setVisible(this._cbOverflow.getValue());
		
		// clip
		this._clipDetailPanel.setVisible(this._cbClip.getValue());
	}
	private void removeAllContent() {
		
		this._elementToControl.setInnerText("", false, this.getLevelElement());
		this._elementToControl.setInnerText("", true,  this.getLevelElement());
		
		this.removeStyle(CssStyle.overflowAuto);
		this.removeStyle(CssStyle.overflowVisible);
		this.removeStyle(CssStyle.overflowScroll);
		this.removeStyle(CssStyle.overflowHidden);
		
		this.removeStyle(CssStyle.clip);
		
		this.removeStyle(CssStyle.dirLtr);
		this.removeStyle(CssStyle.dirRtl);

	}
	private void applyContent(final StringBuilder sb) {
		if (this._elementToControl == null) return;
		if (sb == null) {
		  this.removeAllContent();
		}
		
		// text
		if (this._rbText.getValue()) {
		  String text = this._textArea.getValue();
		  text = (text == null || text.trim().length() == 0)?"":text.trim();
		  this._elementToControl.setInnerText(text, false, this.getLevelElement());
		  
		  //direction
		  if (this._rbDirectionLtR.getValue()) {
		    this.applyStyle(sb, CssStyle.dirLtr);
		  }
		  else {
			  this.applyStyle(sb, CssStyle.dirRtl);  
		  }
		}
		else {
			//image
			final String pathImage = GWT.getModuleName() +
					"/images/" + this._lbImages.getValue(this._lbImages.getSelectedIndex()) + ".gif";
			final String innerImg = "<img src=\"" + pathImage + "\" alt=\"my image\" >";
			this._elementToControl.setInnerText(innerImg, true, this.getLevelElement());
		}
		
		// overflow [auto, visible, hidden, scroll]
		if (this._cbOverflow.getValue()) {
			if (this._rbOverflowAuto.getValue()) {
				this.applyStyle(sb, CssStyle.overflowAuto);
			}
			else if (this._rbOverflowHidden.getValue()) {
				this.applyStyle(sb, CssStyle.overflowHidden);
			}
			else if (this._rbOverflowScroll.getValue()) {
				this.applyStyle(sb, CssStyle.overflowScroll);
			}
			else  {
				this.applyStyle(sb, CssStyle.overflowVisible);
			}
		}
		
		//clip:rect(0px 130px 130px 0px);
		if (this._cbClip.getValue()) {
			
			// 1-top
			String top = CLIP_DEFAULT;
			if (WidgetUtils.getTextBoxValueLength(this._tbClipTop) > 0) {
				WidgetUtils.completeSuffixPx(this._tbClipTop);
				top = this._tbClipTop.getText();
			}
			
			//2-width
			String width = CLIP_DEFAULT;
			if (WidgetUtils.getTextBoxValueLength(this._tbClipWidth) > 0) {
				WidgetUtils.completeSuffixPx(this._tbClipWidth);
				width = this._tbClipWidth.getText();
			}
			
			// 3-height
			String height = CLIP_DEFAULT;
			if (WidgetUtils.getTextBoxValueLength(this._tbClipHeight) > 0) {
				WidgetUtils.completeSuffixPx(this._tbClipHeight);
				height = this._tbClipHeight.getText();
			}
			
			// 4-left
			String left = CLIP_DEFAULT;
			if (WidgetUtils.getTextBoxValueLength(this._tbClipLeft) > 0) {
				WidgetUtils.completeSuffixPx(this._tbClipLeft);
				left = this._tbClipLeft.getText();
			}
			
			final String rect = "rect(" 
			        + top + " " + width + " " + height + " " + left+ ")";
			
			this.applyStyle(sb, CssStyle.clip.updateStyleValue(rect));
		}
	}
}
