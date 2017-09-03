package fr.tsadeo.app.gwt.playwithcss.client.cssproperty;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import fr.tsadeo.app.gwt.playwithcss.client.cssproperty.CssStyleBean.CssStyle;
import fr.tsadeo.app.gwt.playwithcss.client.util.WidgetUtils;

public class CssTextAlignAndControlProperties extends AbstractCssProperties {
	
	private static final String NAME = "TextAlignAndControl";
	private static final String DISPLAY_NAME = "Text align & control";
	
	private static final String GROUP_VERTICAL_ALIGN = "verticalAlign";
	private static final String GROUP_TEXT_ALIGN = "textAlign";
	private static final String GROUP_WHITE_SPACE = "whiteSpace";
	private static final String GROUP_TEXT_OVERFLOW = "textOverflow";
	
	private final VerticalPanel _main = new VerticalPanel();
	
	private final HorizontalPanel _panelDetailVerticalAlign = new HorizontalPanel();
	private final VerticalPanel _panelDetailTextAlign = new VerticalPanel();
	private final VerticalPanel _panelDetailWhiteSpace = new VerticalPanel();
	private final VerticalPanel _panelDetailTextOverflow = new VerticalPanel();
	
	private final TextBox _tbTextIndent = new TextBox();
	private final TextBox _tbLineHeight = new TextBox();
	
	private final CheckBox _cbVerticalAlign = new CheckBox("vertical-align");
	private final CheckBox _cbTextAlign = new CheckBox("text-align");
	private final CheckBox _cbWhiteSpace = new CheckBox("white-space");
	private final CheckBox _cbTextOverflow = new CheckBox("text-overflow");
	
	private RadioButton _rbVertAlignTop;
	private RadioButton _rbVertAlignMiddle;
	private RadioButton _rbVertAlignBottom;
	private RadioButton _rbVertAlignBaseLine;
	private RadioButton _rbVertAlignSub;
	private RadioButton _rbVertAlignSuper;
	private RadioButton _rbVertAlignTextTop;
	private RadioButton _rbVertAlignTextBottom;
	
	
	private RadioButton _rbTextAlignLeft;
	private RadioButton _rbTextAlignCenter;
	private RadioButton _rbTextAlignRight;
	private RadioButton _rbTextAlignJustify;
	
	private RadioButton _rbWhiteSpaceNormal;
	private RadioButton _rbWhiteSpacePre;
	private RadioButton _rbWhiteSpaceNowrap;
	
	private RadioButton _rbTextOverflowClip;
	private RadioButton _rbTextOverflowEllipsis;
	
	public CssTextAlignAndControlProperties(final String title, final PanelWidgetProperties panelProperties) {
		super(NAME + "." + title, panelProperties);
		
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

        this._tbLineHeight.setValue(null);
        this._tbTextIndent.setValue(null);
        
        this._cbTextAlign.setValue(false);
        this._cbVerticalAlign.setValue(false);
        this._cbWhiteSpace.setValue(false);
        this._cbTextOverflow.setValue(false);
        
        this._rbTextAlignLeft.setValue(true);
        this._rbVertAlignTop.setValue(true);
        this._rbWhiteSpaceNormal.setValue(true);
        this._rbTextOverflowEllipsis.setValue(true);

        this.displayPanels();
        this.applyCss();

	}
	@Override
	protected void applyCss() {
		this.applyAlignAndControl(null);
	}
	@Override
	protected void getDisplayCssProperties(final StringBuilder sb) {
		this.applyAlignAndControl(sb);
	}
	//------------------------------------- private methods
	private Panel buildMainPanel() {
		
		this._main.setSpacing(5);
		this._main.add(this.buildTextIdentPanel());
		this._main.add(this.buildLineHeightPanel());
		this._main.add(this.buildVerticalAlignPanel());
		this._main.add(this.buildTextAlignPanel());
		this._main.add(this.buildWhiteSpacePanel());
		this._main.add(this.buildTextOverflowPanel());
		return this._main;
	}
	
	private void initAll() {
		this.initRadioButton();

		this.initHandlers();
	}
	
	private void initRadioButton() {

		String group = this.buildGroupName(GROUP_VERTICAL_ALIGN);
		this._rbVertAlignBaseLine = new RadioButton(group, CssStyle.verticalAlignBaseLine.getStyleValue());
		this._rbVertAlignBottom = new RadioButton(group,CssStyle.verticalAlignBottom.getStyleValue());
		this._rbVertAlignMiddle= new RadioButton(group, CssStyle.verticalAlignMiddle.getStyleValue());
		this._rbVertAlignSub = new RadioButton(group, CssStyle.verticalAlignSub.getStyleValue());
		this._rbVertAlignSuper = new RadioButton(group,CssStyle.verticalAlignSuper.getStyleValue());
		this._rbVertAlignTextBottom = new RadioButton(group, CssStyle.verticalAlignTextBottom.getStyleValue());
		this._rbVertAlignTextTop = new RadioButton(group, CssStyle.verticalAlignTextTop.getStyleValue());
		this._rbVertAlignTop = new RadioButton(group, CssStyle.verticalAlignTop.getStyleValue());
		
		group = this.buildGroupName(GROUP_TEXT_ALIGN);
		this._rbTextAlignCenter = new RadioButton(group, CssStyle.textAlignCenter.getStyleValue());
		this._rbTextAlignJustify = new RadioButton(group,CssStyle.textAlignJustify.getStyleValue());
		this._rbTextAlignLeft = new RadioButton(group, CssStyle.textAlignLeft.getStyleValue());
		this._rbTextAlignRight = new RadioButton(group, CssStyle.textAlignRight.getStyleValue());
		
		group = this.buildGroupName(GROUP_WHITE_SPACE);
		this._rbWhiteSpaceNormal = new RadioButton(group, CssStyle.whiteSpaceNormal.getStyleValue());
		this._rbWhiteSpaceNowrap = new RadioButton(group, CssStyle.whiteSpaceNoWrap.getStyleValue());
		this._rbWhiteSpacePre = new RadioButton(group, CssStyle.whiteSpacePre.getStyleValue());
		
		group = this.buildGroupName(GROUP_TEXT_OVERFLOW);
		this._rbTextOverflowClip = new RadioButton(group, CssStyle.textOverflowClip.getStyleValue());
		this._rbTextOverflowEllipsis = new RadioButton(group, CssStyle.textOverflowEllipsis.getStyleValue());
		
	}
	
	 private Panel buildTextIdentPanel() {
	    	
	   final Panel panel = new SimplePanel();
	   panel.add(WidgetUtils.buildTextBox( CssStyle.textIndent.getStyleName(), this._tbTextIndent));
	   return panel;
	 }
	 private Panel buildLineHeightPanel() {
	    	
		 final Panel panel = new SimplePanel();
		 panel.add(WidgetUtils.buildTextBox( CssStyle.lineHeight.getStyleName(), this._tbLineHeight));
		 return panel;
	}
	 private Panel buildVerticalAlignPanel() {
	    	
	   final HorizontalPanel panel = new HorizontalPanel();
	   final SimplePanel cbPanel = new SimplePanel(this._cbVerticalAlign);
	   cbPanel.addStyleName("l120");
	   panel.add(cbPanel);
	   
	   final VerticalPanel vpanel1 = new VerticalPanel();
	   vpanel1.add(this._rbVertAlignBaseLine);
	   vpanel1.add(this._rbVertAlignBottom);
	   vpanel1.add(this._rbVertAlignMiddle);
	   vpanel1.add(this._rbVertAlignSub);
	      
	   final VerticalPanel vpanel2 = new VerticalPanel();
	   vpanel2.add(this._rbVertAlignSuper);
	   vpanel2.add(this._rbVertAlignTextBottom);
	   vpanel2.add(this._rbVertAlignTextTop);
	   vpanel2.add(this._rbVertAlignTop);
	   
	   this._panelDetailVerticalAlign.add(vpanel1);
	   this._panelDetailVerticalAlign.add(vpanel2);
	   
	   panel.add(this._panelDetailVerticalAlign);
		   
	   return panel;
	}

	 private Panel buildTextAlignPanel() {
	    	
		 final HorizontalPanel panel = new HorizontalPanel();
		 final SimplePanel cbPanel = new SimplePanel(this._cbTextAlign);
		   cbPanel.addStyleName("l120");
		  panel.add(cbPanel);
		   
		   this._panelDetailTextAlign.add(this._rbTextAlignLeft);
		   this._panelDetailTextAlign.add(this._rbTextAlignCenter);
		   this._panelDetailTextAlign.add(this._rbTextAlignRight);
		   this._panelDetailTextAlign.add(this._rbTextAlignJustify);
		   
		   panel.add(this._panelDetailTextAlign);
			   
		   return panel;
		 }
	 
	 private Panel buildTextOverflowPanel() {
		 
		 final HorizontalPanel panel = new HorizontalPanel();
		 final SimplePanel cbPanel = new SimplePanel(this._cbTextOverflow);
		   cbPanel.addStyleName("l120");
		   panel.add(cbPanel);
		   
		   this._panelDetailTextOverflow.add(this._rbTextOverflowClip);
		   this._panelDetailTextOverflow.add(this._rbTextOverflowEllipsis);
   
		   panel.add(this._panelDetailTextOverflow);
			   
		   return panel;
		 
	 }
	 
	 private Panel buildWhiteSpacePanel() {
	    	
		 final HorizontalPanel panel = new HorizontalPanel();
		 final SimplePanel cbPanel = new SimplePanel(this._cbWhiteSpace);
		   cbPanel.addStyleName("l120");
		   panel.add(cbPanel);
		   
		   this._panelDetailWhiteSpace.add(this._rbWhiteSpaceNormal);
		   this._panelDetailWhiteSpace.add(this._rbWhiteSpaceNowrap);
		   this._panelDetailWhiteSpace.add(this._rbWhiteSpacePre);
   
		   panel.add(this._panelDetailWhiteSpace);
			   
		   return panel;
	 }
	 
	private void removeAlignAndControl() {
		
		if (this._elementToControl == null) return;
		this.removeStyle(CssStyle.lineHeight);
		this.removeStyle(CssStyle.textIndent);
		this.removeStyle(CssStyle.verticalAlignBaseLine);
		this.removeStyle(CssStyle.textAlignCenter);
		this.removeStyle(CssStyle.whiteSpaceNormal);
		this.removeStyle(CssStyle.textOverflowClip);
	}
	 
	private void applyAlignAndControl(final StringBuilder sb) {
					
	    if (this._elementToControl == null) return;
	    if (sb == null) {
	     removeAlignAndControl();
	    }
	   
	   WidgetUtils.completeSuffixPx(this._tbLineHeight);
	   WidgetUtils.completeSuffixPx(this._tbTextIndent);
	   
	   // line-height
	   this.applyStyle(sb, CssStyle.lineHeight.updateStyleValue(this._tbLineHeight.getValue()));
	   // text-indent
	   this.applyStyle(sb, CssStyle.textIndent.updateStyleValue(this._tbTextIndent.getValue()));
	   
	   CssStyle cssStyle = null;
	    // vertical-align
       if (this._cbVerticalAlign.getValue()) {
    	   
    	   cssStyle = null;
    	   if (this._rbVertAlignBaseLine.getValue()) {
    		   cssStyle = CssStyle.verticalAlignBaseLine;
    	   }
    	   else if (this._rbVertAlignBottom.getValue()) {
    		   cssStyle = CssStyle.verticalAlignBottom;
    	   }
    	   else if (this._rbVertAlignMiddle.getValue()) {
    		   cssStyle = CssStyle.verticalAlignMiddle;
    	   }
    	   else if (this._rbVertAlignSub.getValue()) {
    		   cssStyle = CssStyle.verticalAlignSub;
    	   }
    	   else if (this._rbVertAlignSuper.getValue()) {
    		   cssStyle = CssStyle.verticalAlignSuper;  
    	   }
    	   else if (this._rbVertAlignTextBottom.getValue()) {
    		   cssStyle = CssStyle.verticalAlignTextBottom;
    	   }
    	   else if (this._rbVertAlignTextTop.getValue()) {
    		   cssStyle = CssStyle.verticalAlignTextTop;
    	   }
    	   else if (this._rbVertAlignTop.getValue()) {
    		   cssStyle = CssStyle.verticalAlignTop;
    	   }
    	   
    	   if (cssStyle != null) {
    		 this.applyStyle(sb, cssStyle);
           }
       }
       // text-value
       if (this._cbTextAlign.getValue()) {
    	   
    	   cssStyle = null;
    	   if (this._rbTextAlignCenter.getValue()) {
    		   cssStyle = CssStyle.textAlignCenter;
    	   }
    	   else if (this._rbTextAlignJustify.getValue()) {
    		   cssStyle = CssStyle.textAlignJustify;
    	   }
    	   else if(this._rbTextAlignLeft.getValue()) {
    		   cssStyle = CssStyle.textAlignLeft;
    	   }   
    	   else if (this._rbTextAlignRight.getValue()) {
    		   cssStyle = CssStyle.textAlignRight;
    	   }
    	   
    	   if (cssStyle != null) {
    		   this.applyStyle(sb, cssStyle);
             }
       }
       // white-space
       if (this._cbWhiteSpace.getValue()) {
    	   
    	   cssStyle = null;
  
    	   if (this._rbWhiteSpaceNormal.getValue()) {
    		   cssStyle = CssStyle.whiteSpaceNormal;
    	   }
    	   else if (this._rbWhiteSpaceNowrap.getValue()) {
    		   cssStyle = CssStyle.whiteSpaceNoWrap;
    	   }
    	   else if (this._rbWhiteSpacePre.getValue()) {
    		   cssStyle = CssStyle.whiteSpacePre;
    	   }
    	   
    	   if (cssStyle != null) {
        	     this.applyStyle(sb, cssStyle);
               }
       }
       
       // text overflow
       if (this._cbTextOverflow.getValue()) {
    	   cssStyle = null;

    	   if (this._rbTextOverflowClip.getValue()) {
    		   cssStyle = CssStyle.textOverflowClip;
    	   }
    	   else if (this._rbTextOverflowEllipsis.getValue()) {
    		   cssStyle = CssStyle.textOverflowEllipsis;
    	   }
    	   if (cssStyle != null) {
      	   this.applyStyle(sb, cssStyle);
             }
       }
	    
	}
	 private void initHandlers() {
		
		 final ClickHandler cbClickHandler = new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				displayPanels();
				applyCss();
			}
		};
		
		this._cbTextAlign.addClickHandler(cbClickHandler);
		this._cbVerticalAlign.addClickHandler(cbClickHandler);
		this._cbWhiteSpace.addClickHandler(cbClickHandler);
		this._cbTextOverflow.addClickHandler(cbClickHandler);
		
		 final ChangeHandler tbChangeHandler = new ChangeHandler() {
			
			@Override
			public void onChange(ChangeEvent event) {
				applyCss();
			}
		};
		this._tbLineHeight.addChangeHandler(tbChangeHandler);
		this._tbTextIndent.addChangeHandler(tbChangeHandler);
		
		
		 final ClickHandler rbClickHandler = new ClickHandler() {
				
				@Override
				public void onClick(ClickEvent event) {
					applyCss();
				}
			};
			
		this._rbTextAlignCenter.addClickHandler(rbClickHandler);
		this._rbTextAlignJustify.addClickHandler(rbClickHandler);
		this._rbTextAlignLeft.addClickHandler(rbClickHandler);
		this._rbTextAlignRight.addClickHandler(rbClickHandler);
		
		this._rbVertAlignBaseLine.addClickHandler(rbClickHandler);
		this._rbVertAlignBottom.addClickHandler(rbClickHandler);
		this._rbVertAlignMiddle.addClickHandler(rbClickHandler);
		this._rbVertAlignSub.addClickHandler(rbClickHandler);
		this._rbVertAlignSuper.addClickHandler(rbClickHandler);
		this._rbVertAlignTextBottom.addClickHandler(rbClickHandler);
		this._rbVertAlignTextTop.addClickHandler(rbClickHandler);
		this._rbVertAlignTop.addClickHandler(rbClickHandler);
		
		this._rbWhiteSpaceNormal.addClickHandler(rbClickHandler);
		this._rbWhiteSpaceNowrap.addClickHandler(rbClickHandler);
		this._rbWhiteSpacePre.addClickHandler(rbClickHandler);
		
		this._rbTextOverflowClip.addClickHandler(rbClickHandler);
		this._rbTextOverflowEllipsis.addClickHandler(rbClickHandler);
		
		
	 }
	 private void displayPanels () {
		 
		 // panel Vertical align
		 this._panelDetailVerticalAlign.setVisible(this._cbVerticalAlign.getValue());
	     // panel Text align
		 this._panelDetailTextAlign.setVisible(this._cbTextAlign.getValue());
		 // panel White space
		 this._panelDetailWhiteSpace.setVisible(this._cbWhiteSpace.getValue());
		 // panel Text Overflow
		 this._panelDetailTextOverflow.setVisible(this._cbTextOverflow.getValue());
	 }
}
