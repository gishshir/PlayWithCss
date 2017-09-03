package fr.tsadeo.app.gwt.playwithcss.client.widget;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.ui.SimplePanel;

import fr.tsadeo.app.gwt.playwithcss.client.util.Types.TypeElement;

public abstract class AbstractWorkingSet extends SimplePanel implements IWorkingSet {
	
	private final Map<String, Element> _mapWidgetByName = new LinkedHashMap<String, Element>();
	private final TypeElement _typeElement;
	
	public final TypeElement getTypeElement() {
		return this._typeElement;
	}
	
	protected AbstractWorkingSet(final TypeElement typeElement) {
		this.setStylePrimaryName("panel");
		this._typeElement = typeElement;
	}
	
	//------------------------------------------------ abstract methods
	public abstract Element getWorkingSetElement();
	protected abstract void addProtected (final String name, final Element element);
	protected abstract void replaceProtected(final String name, final Element newElement, final Element oldElement);
	protected abstract void removeProtected(final String name, final Element element);
	protected abstract void clearAllProtected();

	
	//------------------------------------------------ public methods
	/**
	 * Transfer list elements from this to newWorkingSet
	 * @param newWorkingSet
	 */
	public void transferElements (AbstractWorkingSet newWorkingSet) {
		
		newWorkingSet.clearAll();
		
		final Iterator<String> iterElement = this._mapWidgetByName.keySet().iterator();
		while (iterElement.hasNext()) {
			final String name =  iterElement.next();
			newWorkingSet.add(name, this._mapWidgetByName.get(name));			
		}
		this.clearAll();
	}
	
	//------------------------------------------------ private methods
	private void clearAll() {
		this._mapWidgetByName.clear();
		this.clearAllProtected();
	}
	
	//----------------------------------------------- protected methods
	protected int getCountElements() {
		return this._mapWidgetByName.size();
	}
	//------------------------------------------------ overriding IWorkingSet
	@Override
	public void add(String name, Element element) {
		
		if (!this._mapWidgetByName.containsKey(name)){
			GWT.log("AbstractWorkingSet > add element for name: " + name);
			this._mapWidgetByName.put(name, element);
			this.addProtected(name, element);
		}
	}

	@Override
	public void replace(String name, Element newElement) {

		if (name == null || !this._mapWidgetByName.containsKey(name)) return;
    	final Element oldElement = this._mapWidgetByName.get(name);
    	if (oldElement != null) {
    		GWT.log("AbstractWorkingSet > replace element for name: " + name);
    		this._mapWidgetByName.put(name, newElement);
    		this.replaceProtected(name, newElement, oldElement);
    	}

	}

	@Override
	public void remove(String name) {
		
		if (name == null || !this._mapWidgetByName.containsKey(name)) return;
    	final Element element = this._mapWidgetByName.get(name);
    	if (element != null) {
    		GWT.log("AbstractWorkingSet > remove element for name: " + name);
    		this._mapWidgetByName.remove(name);
    		this.removeProtected(name, element);
    	}
	}

}
