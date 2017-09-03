package fr.tsadeo.app.gwt.playwithcss.client.cssproperty;

import java.util.Set;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import fr.tsadeo.app.gwt.playwithcss.client.cssproperty.CssStyleBean.CssStyle;
import fr.tsadeo.app.gwt.playwithcss.client.util.ElementToControl;
import fr.tsadeo.app.gwt.playwithcss.client.util.Types.LevelElement;
import fr.tsadeo.app.gwt.playwithcss.client.util.Types.ToControl;
import fr.tsadeo.app.gwt.playwithcss.client.util.Types.TypeElement;

public abstract class AbstractCssProperties extends Composite {
	
	public final static String SAUT = "<br/>";
	
	private final static String STYLE_CSS_PROPERTIES = "cssProperties";
	private final static String STYLE_PANEL_CONTENT = "panelContent";
	private final static String STYLE_BAR_BUTTON = "barButton";
	private final static String STYLE_BUTTON_REINIT = "reinit";
	private final static String STYLE_BUTTON_EDIT = "edit";
	
	protected final AbstractPanelProperties _panelProperties;

	protected String title;
	
	protected ElementToControl _elementToControl;
	private final DockPanel _main = new DockPanel();
	private final FlowPanel _content = new FlowPanel();
	
	
	
	public abstract String getName();
	public abstract String getDisplayName();
	
	protected AbstractCssProperties(final String title, final AbstractPanelProperties panelProperties) {

		this(title, panelProperties, true);	
	}
	protected AbstractCssProperties(final String title, 
			final AbstractPanelProperties panelProperties, final boolean showReinitButton) {

		this.title = title;
		this._panelProperties = panelProperties;
		this._main.add(this._content, DockPanel.CENTER);
		if (showReinitButton) {
		  final Widget widget = this.buildButtonPanel();
		  this._main.add(widget, DockPanel.SOUTH);
		  this._main.setCellHeight(widget, "50px");
		  this._main.setCellVerticalAlignment(widget, HasVerticalAlignment.ALIGN_BOTTOM);
		  this._main.setCellHorizontalAlignment(widget, HasHorizontalAlignment.ALIGN_RIGHT);
		}
		this._main.setStylePrimaryName(STYLE_CSS_PROPERTIES);
		this.initWidget(this._main);	
	}
	
	protected FlowPanel getContent() {
		this._content.addStyleName(STYLE_PANEL_CONTENT);
		return this._content;
	}
	protected ToControl getToControl() {
		return this._panelProperties.getToControl();
	}
	protected LevelElement getLevelElement() {
		return this._panelProperties.getLevelElement();
	}
	
	protected void removeStyle(final CssStyle cssStyle) {
		if(cssStyle.getClassName() != null) {
			this._elementToControl.removeClassName(cssStyle.getClassName(), this.getLevelElement());
		}
		else {
			this.removeStyleAttributFromPrefix( cssStyle.getStyleName());
		}
	}
    protected void applyStyle (final StringBuilder sb, final CssStyle cssStyle) {
    	if (this._elementToControl == null && sb == null) return;
    	final CssStyleBean cssStyleBean = cssStyle.getCssStyleBean();
    	if (sb == null) {
    		
    		if (cssStyleBean.getClassName() != null) {
			  this._elementToControl.addClassName(cssStyleBean.getClassName(), this.getLevelElement());
    		}
    		else {
    			this.addStyleAttributeWithPrefix( cssStyleBean.getStyleName(), cssStyleBean.getStyleValue());
    		}
		}
		else {
			final String attribute = cssStyleBean.getDisplayStyleAttribute();
			if(attribute != null) {
			  sb.append(attribute);
			  sb.append(SAUT);
			}
		}
    }
	protected abstract void getDisplayCssProperties (final StringBuilder sb);
	protected abstract void reinit();
	protected abstract void applyCss();
	
	protected boolean isHtmlCssProperties() {
		return true;
	}
	
	public void setElementToControl(final ElementToControl elementToControl, final boolean reinit) {
		this.setElementToControl(elementToControl, reinit, !reinit);
	}
	protected void setElementToControl(  final ElementToControl elementToControl,
			final boolean reinit, final boolean applyCss) {
		this._elementToControl = elementToControl;
		if (reinit) {
			this.reinit();
		} 
        if (applyCss) {
			this.applyCss();
		}
	}
	protected Panel builThemePanel(final TextBox textBoxAll, 
			final RadioButton rbAll, final RadioButton rbDetail,
			final CssPanelDetail panelDetail) {
	
        final HorizontalPanel hPanelAll = new HorizontalPanel();
        hPanelAll.add(rbAll);
        hPanelAll.add(textBoxAll);
        
        final HorizontalPanel hPanelDetail = new HorizontalPanel();
        hPanelDetail.add(rbDetail);
        hPanelDetail.add(panelDetail);
        
        final VerticalPanel vPanel = new VerticalPanel();
        vPanel.add(hPanelAll);
        vPanel.add(hPanelDetail);
		
		return vPanel;
	}
	
	// a surcharger si nécessaire. Par défaut les properties 
	// s'applique à tous les type d'élement
	protected Set<TypeElement> getTypeElementReserved() {
		return null;
	}
	protected boolean isEnabledForTypeElement(final TypeElement typeElement) {
		if (typeElement == null) return false;
		if (this.getTypeElementReserved() == null) return true;
		 return this.getTypeElementReserved().contains(typeElement);
	}
	//----------------------------------------------------------------- private methods
	private void showCssProperties () {
				
		// Create the popup dialog box
		final DialogBox dialogBox = new DialogBox();
		dialogBox.setText( this.getDisplayName());
		dialogBox.setAnimationEnabled(true);
		final Button closeButton = new Button("Close");
		// We can set the id of a widget by accessing its Element
		closeButton.getElement().setId("closeButton");
		final HTML serverResponseLabel = new HTML();
		
		final StringBuilder sb = new StringBuilder();
		this.getDisplayCssProperties(sb);
		if (this.isHtmlCssProperties())  {
		  serverResponseLabel.setHTML(sb.toString());
		}
		else {
			 serverResponseLabel.setText(sb.toString());
		}
		
		VerticalPanel dialogVPanel = new VerticalPanel();
		//dialogVPanel.addStyleName("dialogVPanel");
		dialogVPanel.add(serverResponseLabel);
		dialogVPanel.setHorizontalAlignment(VerticalPanel.ALIGN_RIGHT);
		dialogVPanel.add(closeButton);
		dialogBox.setWidget(dialogVPanel);
		
		// Add a handler to close the DialogBox
		closeButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				dialogBox.hide();
			}
		});
		
		dialogBox.setPopupPositionAndShow(new PopupPanel.PositionCallback() {
	          public void setPosition(int offsetWidth, int offsetHeight) {
	        	  
	        	  int topMain = _panelProperties.getAbsoluteTop();
	        	  int leftMain = _panelProperties.getAbsoluteLeft();
	        	  
	              int left = leftMain + _panelProperties.getOffsetWidth() - (offsetWidth / 2);
	              int top = topMain  - (offsetHeight / 3);
	              dialogBox.setPopupPosition(left, top);
	            }
	          });

	}
	
	
	private Panel buildButtonPanel() {
		
		final Button buttonEdit = new Button("", new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				showCssProperties();
			}
		});
		buttonEdit.setStyleName(STYLE_BUTTON_EDIT);
		
		final Button buttonReinit = new Button("", new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				reinit();	
			}
		});
		buttonReinit.setStyleName(STYLE_BUTTON_REINIT);
		
		final HorizontalPanel panel = new HorizontalPanel();
		panel.setSpacing(5);
		panel.add(buttonEdit);
		panel.add(buttonReinit);
		panel.setStylePrimaryName(STYLE_BAR_BUTTON);
		return panel;
	}
	

	protected String buildGroupName(final String group) {
		return title  + "." + group;
	}
	protected void addStyleAttributeWithPrefix (final String attributePrefix, final String attributeValue) {
		
	  if (this._elementToControl == null) return;
	  this._elementToControl.addStyleAttributeWithPrefix(attributePrefix, attributeValue, this.getLevelElement());
		
	}
	
    protected void removeStyleAttributFromPrefix(String attributePrefix) {
    	
    	 if (this._elementToControl == null) return;
   	  this._elementToControl.removeStyleAttributFromPrefix(attributePrefix, this.getLevelElement());
    }
	

}
