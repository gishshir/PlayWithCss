package fr.tsadeo.app.gwt.playwithcss.client.cssproperty;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.VerticalPanel;

import fr.tsadeo.app.gwt.playwithcss.client.util.ElementToControl;
import fr.tsadeo.app.gwt.playwithcss.client.util.Types;
import fr.tsadeo.app.gwt.playwithcss.client.util.Types.TypeElement;
import fr.tsadeo.app.gwt.playwithcss.client.view.PropertiesView;

public class CssTypeContainerProperties extends AbstractCssProperties {
	
	
	private static final String NAME = "TypeContainer";
	private static final String DISPLAY_NAME = "Element type";
	
	private static final String GROUP_PRINC = "principal";
	
	private final VerticalPanel _main = new VerticalPanel();
	private final PropertiesView _propertiesView;

	private final  Map<Types.TypeElement, RadioButton> _mapRadioButton = new HashMap<Types.TypeElement, RadioButton>();
	
	//--------------------------------------------- constructor
	public CssTypeContainerProperties(final String title, 
			final PropertiesView propertiesView, final AbstractPanelProperties panelProperties,
			final boolean showReinitButton) {
		super(NAME + "." + title, panelProperties, showReinitButton);

        this._propertiesView = propertiesView;
		this.initAll();
		this.getContent().add(this.buildMainPanel());
	}

	//---------------------------------------------  overriding AbstractCssProperties
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
		this._mapRadioButton.get(TypeElement.DIV).setValue(Boolean.TRUE);
		this.applyTypeContainer();
	}
	@Override
	protected void applyCss() {
		this.applyTypeContainer();
	}
	@Override
	public void setElementToControl(final ElementToControl elementToControl, final boolean reinit) {
		super.setElementToControl(elementToControl, false, false);
		
		if (this._mapRadioButton.containsKey(elementToControl.getTypeElement())) {
		this._mapRadioButton.get(elementToControl.getTypeElement()).setValue(Boolean.TRUE);
		}
	}
	@Override
	protected void getDisplayCssProperties(final StringBuilder sb) {
		final TypeElement typeElement = this.getTypeElement();
		if (typeElement == null) return;
		
		
		sb.append(typeElement.getBeginBalise() + "..." + typeElement.getEndBalise());
	}
	@Override
	protected boolean isHtmlCssProperties() {
		return false;
	}
	//--------------------------------- private methods
	private void initAll() {
		this.initRadioButton();
		this.initHandler();
	}
	private void initHandler() {
		
		final ClickHandler clickHandler = new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				applyTypeContainer();	
			}
		};
		final Iterator<Types.TypeElement> iter = this._mapRadioButton.keySet().iterator();
        while (iter.hasNext()) {
        	Types.TypeElement type = (Types.TypeElement) iter.next();
			this._mapRadioButton.get(type).addClickHandler(clickHandler);
		}
	}
	private void initRadioButton() {
		
		//radioButton
		String group = this.buildGroupName(GROUP_PRINC);
		
		final List<TypeElement> listTypeElement = TypeElement.getListTypeElement(this.getToControl());
		for (int i = 0; i < listTypeElement.size(); i++) {
			
			final TypeElement type = listTypeElement.get(i);
			this._mapRadioButton.put(type, new RadioButton(group, type.getBeginBalise()));
		}		
	}
	
	private Panel buildMainPanel() {
		
		this._main.setSpacing(5);
		
		final Iterator<Types.TypeElement> iter = this._mapRadioButton.keySet().iterator();
        while (iter.hasNext()) {
        	Types.TypeElement type = (Types.TypeElement) iter.next();
			this._main.add(this._mapRadioButton.get(type));
		}

		return this._main;
	}
	
	private TypeElement getTypeElement() {
		
		final Iterator<TypeElement> iter = this._mapRadioButton.keySet().iterator();
        while (iter.hasNext()) {
        	TypeElement type = (TypeElement) iter.next();
			if (this._mapRadioButton.get(type).getValue()) {
				return type;
			}
        }
        return null;
	}
	private void applyTypeContainer() {
		
		final TypeElement typeElement = this.getTypeElement();
        if (typeElement  != null) {
		  this._propertiesView.changeElementToControl(this._panelProperties, typeElement);
        }
	}

}
