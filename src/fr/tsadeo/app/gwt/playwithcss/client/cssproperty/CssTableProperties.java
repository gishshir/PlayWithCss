package fr.tsadeo.app.gwt.playwithcss.client.cssproperty;

import java.util.HashSet;
import java.util.Set;

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
import fr.tsadeo.app.gwt.playwithcss.client.util.Types.TypeElement;

public class CssTableProperties extends AbstractCssProperties {

	private static final String NAME = "TableProperties";
	private static final String DISPLAY_NAME = "Table";
	
	private static final String GROUP_TABLE_LAYOUT = NAME + "tableLayout";
	private static final String GROUP_BORDER_COLLAPSE = NAME + "borderCollapse";
	private static final String GROUP_EMPTY_CELLS = NAME + "emptyCells";
	
	private static final Set<TypeElement> typeElementReserved = new HashSet<TypeElement>(0);
	static {
		typeElementReserved.add(TypeElement.TABLE);
	}
	
	private final CheckBox _cbTableLayout = new CheckBox("table-layout");
	private final CheckBox _cbBorderCollapse = new CheckBox("border-collapse");
	private final CheckBox _cbBorderSpacing = new CheckBox("border-spacing");
	private final CheckBox _cbEmptyCells = new CheckBox("empty-cells");
	
	private final VerticalPanel _main = new VerticalPanel();
	private final VerticalPanel _panelDetailTableLayout = new VerticalPanel();
	private final HorizontalPanel _panelDetailBorderCollapse = new HorizontalPanel();
    private final SimplePanel _panelDetailBorderSpacing = new SimplePanel();
    private final HorizontalPanel _panelDetailEmptyCells = new HorizontalPanel();
    
    private RadioButton _rbTableLayoutFixed;
    private RadioButton _rbTableLayoutAuto;
    private RadioButton _rbBorderCollapseSeparate;
    private RadioButton _rbBorderCollapseCollapse;
    private RadioButton _rbEmptyCellsShow;
    private RadioButton _rbEmptyCellsHide;
    
    private final TextBox _tbCol1Width = new TextBox();
    private final TextBox _tbCol2Width = new TextBox();
    private final TextBox _tbBorderSpacing = new TextBox();
    
	
	//---------------------------------------------- constructors
	protected CssTableProperties(String title,
			AbstractPanelProperties panelProperties) {
		super(NAME + "." + title, panelProperties, true);
		
		this.initAll();
		this.getContent().add(this.buildMainPanel());
	}
	
	//---------------------------------------------- overriding AbstractCssProperties

	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public String getDisplayName() {
		return DISPLAY_NAME;
	}

	@Override
	protected void getDisplayCssProperties(StringBuilder sb) {
		this.applyTableProperties(sb);
	}

	@Override
	protected void reinit() {
		
		this._cbBorderCollapse.setValue(false);
		this._cbBorderSpacing.setValue(false);
		this._cbEmptyCells.setValue(false);
		this._cbTableLayout.setValue(false);
		
		this._rbTableLayoutAuto.setValue(true);
		this._tbCol1Width.setValue("20px");
		this._tbCol2Width.setValue("20px");
		
		this._rbBorderCollapseCollapse.setValue(true);
		
		this._tbBorderSpacing.setValue("5px");
		
		this._rbEmptyCellsHide.setValue(true);
		
		this.displayDetails();
		this.applyCss();

	}

	@Override
	protected void applyCss() {
		this.applyTableProperties(null);
	}
	@Override
	protected Set<TypeElement> getTypeElementReserved() {
		return typeElementReserved;
	}
	//---------------------------------------------- private methods
     private Panel buildMainPanel() {
		
		this._main.setSpacing(5);
		
		this._main.add(this.buildTableLayoutPanel());
		this._main.add(this.buildBorderCollapsePanel());
		this._main.add(this.buildBorderSpacingPanel());
		this._main.add(this.buildEmptyCellsPanel());
		
		return this._main;
    }
     private void initAll() {
    	 this.initComposants();
 		this.initRadioButton();
 		this.initHandlers();
 	}
     private void initComposants() {
    	 //TODO tempo à terminer
    	 this._cbTableLayout.setEnabled(false);
     }
     private void initHandlers() {
    	 
    	 final ClickHandler clickHandler = new ClickHandler() {
 			
 			@Override
 			public void onClick(ClickEvent event) {
 				displayDetails();	
 				applyCss();
 			}
 		};
 		
 		this._cbTableLayout.addClickHandler(clickHandler);
 		this._cbBorderCollapse.addClickHandler(clickHandler);
 		this._cbBorderSpacing.addClickHandler(clickHandler);
 		this._cbEmptyCells.addClickHandler(clickHandler);
 		
 		this._rbTableLayoutAuto.addClickHandler(clickHandler);
 		this._rbTableLayoutFixed.addClickHandler(clickHandler);
 		this._rbBorderCollapseCollapse.addClickHandler(clickHandler);
 		this._rbBorderCollapseSeparate.addClickHandler(clickHandler);
 		this._rbEmptyCellsHide.addClickHandler(clickHandler);
 		this._rbEmptyCellsShow.addClickHandler(clickHandler);
 		
 		final ChangeHandler changeHandler = new ChangeHandler() {
			
			@Override
			public void onChange(ChangeEvent event) {
				displayDetails();	
 				applyCss();
			}
		};
		this._tbBorderSpacing.addChangeHandler(changeHandler);
		this._tbCol1Width.addChangeHandler(changeHandler);
		this._tbCol2Width.addChangeHandler(changeHandler);
     }
     private void removeAllTableProperties() {
 		
 		this.removeStyle(CssStyle.tableLayoutAuto);
 		this.removeStyle(CssStyle.tableLayoutFixed);
 		this.removeStyle(CssStyle.borderCollapseCollapse);
 		this.removeStyle(CssStyle.borderCollapseSeparate);
 		this.removeStyle(CssStyle.borderSpacing);
 		this.removeStyle(CssStyle.emptyCellsHide);
 		this.removeStyle(CssStyle.emptyCellsShow);

 	}
     private void applyTableProperties(final StringBuilder sb) {
    	 
    	 if (this._elementToControl == null) return;
 		if (sb == null) {
 		  this.removeAllTableProperties();
 		}
 		
 		// table-layout
 		if (this._cbTableLayout.getValue()) {
 			if(this._rbTableLayoutAuto.getValue()) {
 				this.applyStyle(sb, CssStyle.tableLayoutAuto);
 			}
 			else {
 				this.applyStyle(sb, CssStyle.tableLayoutFixed);
 			}
 			// TODO appliquer largeur de colonne
 			WidgetUtils.completeSuffixPx(this._tbCol1Width);		
 			WidgetUtils.completeSuffixPx(this._tbCol2Width);
 		}
 		
 		// border-collapse
 		if (this._cbBorderCollapse.getValue()) {
 			if (this._rbBorderCollapseCollapse.getValue()) {
 				this.applyStyle(sb, CssStyle.borderCollapseCollapse);
 			}
 			else {
 				this.applyStyle(sb, CssStyle.borderCollapseSeparate);
 			}
 		}
 		
 		// border-spacing
 		if (this._cbBorderSpacing.getValue()) {
 			WidgetUtils.completeSuffixPx(this._tbBorderSpacing);
 			this.applyStyle(sb, CssStyle.borderSpacing.updateStyleValue(this._tbBorderSpacing.getValue()));
 		}
 		
 		// empty-cells
 		if (this._cbEmptyCells.getValue()) {
 			if (this._rbEmptyCellsHide.getValue()) {
 				this.applyStyle(sb, CssStyle.emptyCellsHide);
 			} else {
 				this.applyStyle(sb, CssStyle.emptyCellsShow);
 			}
 		}
     }
     private void displayDetails() {
 		
 		final boolean showPanelTableLayout = this._cbTableLayout.getValue();
 		this._panelDetailTableLayout.setVisible(showPanelTableLayout);
 		
 		final boolean showPanelBorderCollapse = this._cbBorderCollapse.getValue();
 		this._panelDetailBorderCollapse.setVisible(showPanelBorderCollapse);
 		
 		final boolean showPanelBorderSpacing = this._cbBorderSpacing.getValue();
 		this._panelDetailBorderSpacing.setVisible(showPanelBorderSpacing);
 		
 		final boolean showPanelEmptyCells = this._cbEmptyCells.getValue();
 		this._panelDetailEmptyCells.setVisible(showPanelEmptyCells);
     }
     private void initRadioButton() {
 		
    	// table-layout
 		String group = this.buildGroupName(GROUP_TABLE_LAYOUT);
 		this._rbTableLayoutAuto = new RadioButton(group,  CssStyle.tableLayoutFixed.getStyleValue());
 		this._rbTableLayoutFixed = new RadioButton(group, CssStyle.tableLayoutAuto.getStyleValue());
 		
        // border-collapse
 		group = this.buildGroupName(GROUP_BORDER_COLLAPSE);
 		this._rbBorderCollapseCollapse = new RadioButton(group,  CssStyle.borderCollapseCollapse.getStyleValue());
 		this._rbBorderCollapseSeparate = new RadioButton(group,  CssStyle.borderCollapseSeparate.getStyleValue());
 		
 		// empty-cells
 		group = this.buildGroupName(GROUP_EMPTY_CELLS);
 		this._rbEmptyCellsShow = new RadioButton(group, CssStyle.emptyCellsShow.getStyleValue());
 		this._rbEmptyCellsHide = new RadioButton(group, CssStyle.emptyCellsHide.getStyleValue());
 				
 	}
	private Panel buildTableLayoutPanel() {
		
		final HorizontalPanel panel = new HorizontalPanel();
		panel.setSpacing(5);
		
		panel.add(WidgetUtils.buildSimplePlanel(this._cbTableLayout, "150px"));
		
		// ---- detail
		
		final HorizontalPanel hPanelRadioButton = new HorizontalPanel();
		hPanelRadioButton.setSpacing(5);
		hPanelRadioButton.add(this._rbTableLayoutAuto);
		hPanelRadioButton.add(this._rbTableLayoutFixed);
		this._panelDetailTableLayout.add(hPanelRadioButton);
		
		this._panelDetailTableLayout.add(WidgetUtils.buildTextBox("col1 width: ", this._tbCol1Width));
		this._panelDetailTableLayout.add(WidgetUtils.buildTextBox("col2 width: ", this._tbCol2Width));
		
		panel.add(this._panelDetailTableLayout);
		
		return panel;
	}
	private Panel buildBorderCollapsePanel() {
		final HorizontalPanel panel = new HorizontalPanel();
		panel.setSpacing(5);
		
		panel.add(WidgetUtils.buildSimplePlanel(this._cbBorderCollapse, "150px"));
		
		// ----- detail
		this._panelDetailBorderCollapse.add(this._rbBorderCollapseSeparate);
		this._panelDetailBorderCollapse.add(this._rbBorderCollapseCollapse);
		panel.add(this._panelDetailBorderCollapse);
		
		return panel;
	}
	private Panel buildBorderSpacingPanel() {
		final HorizontalPanel panel = new HorizontalPanel();
		panel.setSpacing(5);
		
		panel.add(WidgetUtils.buildSimplePlanel(this._cbBorderSpacing, "150px"));
		
		// --- detail
		this._tbBorderSpacing.setWidth("80px");
		this._panelDetailBorderSpacing.add(this._tbBorderSpacing);
		panel.add(this._panelDetailBorderSpacing);
		
		return panel;
	}
	private Panel buildEmptyCellsPanel() {
		final HorizontalPanel panel = new HorizontalPanel();
		panel.setSpacing(5);
		
		panel.add(WidgetUtils.buildSimplePlanel(this._cbEmptyCells, "150px"));
		
		// --- detail
		this._panelDetailEmptyCells.add(this._rbEmptyCellsHide);
		this._panelDetailEmptyCells.add(this._rbEmptyCellsShow);
		panel .add(this._panelDetailEmptyCells);
		return panel;
	}

}
