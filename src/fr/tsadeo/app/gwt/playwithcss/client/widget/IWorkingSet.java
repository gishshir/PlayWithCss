package fr.tsadeo.app.gwt.playwithcss.client.widget;

import com.google.gwt.dom.client.Element;

public interface IWorkingSet {

	public abstract void add(final String name, final Element element);

	public abstract void replace(final String name, final Element newElement);

	public abstract void remove(final String name);

}