package fr.tsadeo.app.gwt.playwithcss.client.view;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.DisclosurePanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.StackPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import fr.tsadeo.app.gwt.playwithcss.client.cssproperty.AbstractPanelProperties;
import fr.tsadeo.app.gwt.playwithcss.client.cssproperty.PanelTableCellsProperties;
import fr.tsadeo.app.gwt.playwithcss.client.cssproperty.PanelWidgetProperties;
import fr.tsadeo.app.gwt.playwithcss.client.cssproperty.PanelWorkingSetProperties;
import fr.tsadeo.app.gwt.playwithcss.client.util.ElementToControl;
import fr.tsadeo.app.gwt.playwithcss.client.util.Types;
import fr.tsadeo.app.gwt.playwithcss.client.util.Types.ToControl;
import fr.tsadeo.app.gwt.playwithcss.client.util.Types.TypeElement;
import fr.tsadeo.app.gwt.playwithcss.client.widget.AbstractWorkingSet;
import fr.tsadeo.app.gwt.playwithcss.client.widget.SimpleWorkingSet;
import fr.tsadeo.app.gwt.playwithcss.client.widget.TableWorkingSet;

public class PropertiesView extends SimplePanel {

	
	private final HorizontalPanel _main = new HorizontalPanel();
	
	private final StackPanel _stackPanel = new StackPanel();
	private  AbstractWorkingSet _workingSet;	
	private final Panel _panelWordingSet = new VerticalPanel();
	private final DisclosurePanel _panelTableCells = new DisclosurePanel("table cells properties");
	
	private PanelWorkingSetProperties _panelWorkingSetProperties;
	private PanelTableCellsProperties _panelTableCellsProperties;
	private final List<PanelWidgetProperties> _listPanelWidgetProperties = new ArrayList<PanelWidgetProperties>();
	

	public PropertiesView () {
		
		this._main.add(this._stackPanel);
		this._main.add(this.buildWorkingSet());
		
		this.setWidget(this._main);
		this.setStylePrimaryName("divParameterView");
		
		this.init();

	}
	
	private void createPanelWidgetProperties(final String title, final TypeElement typeElement) {
		
		final PanelWidgetProperties panelProperties = new PanelWidgetProperties(title, this);
		this._listPanelWidgetProperties.add(panelProperties);
		this._stackPanel.add(panelProperties, this.buildLabelStackPanel(title, typeElement));
		
		this.createWidgetElementToControl(panelProperties, typeElement);
	}
	
	private String buildLabelStackPanel (final String title, final TypeElement typeElement) {
		return title + " - " + typeElement;
	}
	
	private void createWidgetElementToControl (final PanelWidgetProperties panelProperties, final TypeElement typeElement) {
		
		final Element element = this.createElement(typeElement);
		this._workingSet.add(panelProperties.getTitle(),element);
		
		panelProperties.setElementToControl(new ElementToControl(element, typeElement), true);
	}
	public void changeElementToControl (final AbstractPanelProperties panelProperties, final TypeElement typeElement) {

		GWT.log("changeElementToControl() " + typeElement);

		if (panelProperties.getToControl() == ToControl.WIDGET) {

			// create new element and add to working set
			final Element newElement = this.createElement(typeElement);
			if (newElement != null) {

				// replace element from Working set
				GWT.log("this._workingSet.replace... " + newElement);
				this._workingSet
						.replace(panelProperties.getTitle(), newElement);

				// add new Element to PanelProperties
				panelProperties.setElementToControl(new ElementToControl(
					newElement, typeElement),
						false);
			}

			final String stackText = this.buildLabelStackPanel(
					panelProperties.getTitle(), typeElement);
			this._stackPanel.setStackText(this._stackPanel.getSelectedIndex(),
					stackText);
		}
		else {
			this.changeWorkingSet(typeElement, false);
		}
		
	}
	
	private Element createElement(final Types.TypeElement typeElement) {
		
		switch (typeElement) {
		  case SPAN: return DOM.createSpan();
		  case DIV: return DOM.createDiv();
		  default:   return DOM.createElement(typeElement.getElement());
		}
	}

	private void init() {
		
		this._stackPanel.addStyleName("stackPanel");
		this.createPanelWidgetProperties("box1", Types.TypeElement.DIV);
		this.createPanelWidgetProperties("box2", TypeElement.DIV);
		this.createPanelWidgetProperties("box3", TypeElement.DIV);
	}

	private void changeWorkingSet(final TypeElement typeElement, final boolean reinit) {

		this.displayPanelTableCellsProperties(typeElement);
		if (this._workingSet != null) {
			if (this._workingSet.getTypeElement() == typeElement)
				return;

			this._panelWordingSet.remove(this._workingSet);
		}
		
		final AbstractWorkingSet oldWorkingSet = this._workingSet;

		switch (typeElement) {
		  case DIV:
			this._workingSet = new SimpleWorkingSet();
			break;
		  case TABLE:
			this._workingSet = new TableWorkingSet();
		}

		if (oldWorkingSet != null) {
		  oldWorkingSet.transferElements(this._workingSet);
		}
		this._panelWordingSet.add(this._workingSet);
		
		final ElementToControl elementToControl = new ElementToControl( this._workingSet.getWorkingSetElement(), typeElement);
		this._panelWorkingSetProperties.setElementToControl(elementToControl, reinit);
		if (this._panelTableCellsProperties != null && typeElement == TypeElement.TABLE){
			this._panelTableCellsProperties.setElementToControl(elementToControl, reinit);
		}
	
	}
	private Panel buildWorkingSet() {

		// container properties
		final DisclosurePanel dPanel = new DisclosurePanel("container properties");
		this._panelWorkingSetProperties = new PanelWorkingSetProperties("workingset", this);
		dPanel.add(this._panelWorkingSetProperties);
		this._panelWordingSet.add(dPanel);
		
		// table cells properties
		this._panelWordingSet.add(this._panelTableCells);
		
		this.changeWorkingSet(TypeElement.DIV, true);
		
		return this._panelWordingSet;
	}
	
	private void displayPanelTableCellsProperties (final TypeElement typeElement) {
		if (typeElement != TypeElement.TABLE) {
			this._panelTableCells.setVisible(false);
			return;
		}
		if (this._panelTableCellsProperties == null) {
			this._panelTableCellsProperties = new PanelTableCellsProperties("workingsetTableCelld", this);
			this._panelTableCells.add(this._panelTableCellsProperties);
		}
		this._panelTableCells.setVisible(true);
	}

}
