package fr.tsadeo.app.gwt.playwithcss.client.widget;

import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.ui.FlowPanel;

import fr.tsadeo.app.gwt.playwithcss.client.util.Types.TypeElement;

public class SimpleWorkingSet extends AbstractWorkingSet implements IWorkingSet {


	private final FlowPanel _main = new FlowPanel();
	
	public SimpleWorkingSet() {
		super(TypeElement.DIV);
		this._main.setStyleName("workingSet");
		this.setWidget(this._main);
	}

	
	//-------------------------------------------------------------overriding AbstractWorkingSet
	@Override
	protected void addProtected(final String name, final Element element) {
		this._main.getElement().appendChild(element);
	}

	@Override
	protected void replaceProtected(final String name, final Element newElement,
			Element oldElement) {
		this._main.getElement().replaceChild(newElement, oldElement);
	}

	@Override
	protected void removeProtected(final String name,  final Element element) {
		this._main .getElement().removeChild(element);
	}


	@Override
	protected void clearAllProtected() {
		this._main.clear();
	}


	@Override
	public Element getWorkingSetElement() {
		return this._main.getElement();
	}
	

}
