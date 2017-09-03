package fr.tsadeo.app.gwt.playwithcss.client.util;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.NodeList;
import com.google.gwt.dom.client.TableCellElement;
import com.google.gwt.dom.client.TableElement;
import com.google.gwt.dom.client.TableRowElement;

import fr.tsadeo.app.gwt.playwithcss.client.util.Types.LevelElement;
import fr.tsadeo.app.gwt.playwithcss.client.util.Types.TypeElement;

/**
 * encapsule l'element à controler et ses elements fils, constituant les sous niveaux
 * @author sylvie
 *
 */
public final class ElementToControl {
	
	private final static String STYLE = "style";

	private final TypeElement _typeElement;
	private final Element _element;
	
	
	public Element getElement () {
		return this._element;
	}
	public TypeElement getTypeElement () {
		return this._typeElement;
	}
	
	
	//--------------------------------------------------------- public methods
	
	public void setInnerText(final String text, final boolean html,  final LevelElement levelElement) {
		 
		 if (this._element == null) return;
			
			switch (levelElement) {
			case LEVEL_1: 
				if (html)
					{this._element.setInnerHTML(text);}
				else {this._element.setInnerText(text);}
				break;
				
			case LEVEL_2: 
			case LEVEL_3:
				final List<Element> list = this.getElements(levelElement);
				if (list == null || list.isEmpty()) return;
				for (int i = 0; i <list.size(); i++) {
					if (html)
					{list.get(i).setInnerHTML(text);}
				else {list.get(i).setInnerText(text);}
				}
				break;
			} 
	 }
	public void removeStyleAttributFromPrefix(String attributePrefix, final LevelElement levelElement) {
		 
		 if (this._element == null) return;
			
			switch (levelElement) {
			case LEVEL_1: 
				this._removeStyleAttributFromPrefix(this._element, attributePrefix);
				break;
				
			case LEVEL_2: 
			case LEVEL_3:
				final List<Element> list = this.getElements(levelElement);
				if (list == null || list.isEmpty()) return;
				for (int i = 0; i <list.size(); i++) {
					this._removeStyleAttributFromPrefix(list.get(i), attributePrefix);
				}
				break;
			} 
	 }
	public void addStyleAttributeWithPrefix (final String attributePrefix, final String attributeValue, final LevelElement levelElement) {
		 if (this._element == null) return;
			
			switch (levelElement) {
			case LEVEL_1: 
				this._addStyleAttributeWithPrefix(this._element, attributePrefix, attributeValue);
				break;
				
			case LEVEL_2: 
			case LEVEL_3:
				final List<Element> list = this.getElements(levelElement);
				if (list == null || list.isEmpty()) return;
				for (int i = 0; i <list.size(); i++) {
					this._addStyleAttributeWithPrefix(list.get(i), attributePrefix, attributeValue);
				}
				break;
			} 
	 }
	public void addClassName (final String className, final LevelElement levelElement) {
		if (this._element == null) return;
		
		switch (levelElement) {
		case LEVEL_1: 
			this._element.addClassName(className);
			break;
			
		case LEVEL_2: 
		case LEVEL_3:
			final List<Element> list = this.getElements(levelElement);
			if (list == null || list.isEmpty()) return;
			for (int i = 0; i <list.size(); i++) {
				list.get(i).addClassName(className);	
			}
			break;
		}
	}
	
	/**
	 * @param className
	 * @param levelElement
	 */
	public void removeClassName (final String className, final LevelElement levelElement) {
		if (this._element == null) return;
		
		switch (levelElement) {
		case LEVEL_1: 
			this._element.removeClassName(className);
			break;
			
		case LEVEL_2: 
		case LEVEL_3:
			final List<Element> list = this.getElements(levelElement);
			if (list == null || list.isEmpty()) return;
			for (int i = 0; i <list.size(); i++) {
				list.get(i).removeClassName(className);	
			}
			break;
		}
	}
	//------------------------------------------------------- private methods
	private List<Element> getElements (final LevelElement levelElement) {
		List<Element> list = null;
		
		switch (levelElement) {
		case LEVEL_1: 
			list = new ArrayList<Element>(1);
			list.add(this._element);
			break;
			
		case LEVEL_2: 
		case LEVEL_3:
			if (this._typeElement == TypeElement.TABLE) {
				list = this .extractElementsFromTable(levelElement);
			}
			break;
		}
		return list;
	}
	private List<Element> extractElementsFromTable (final LevelElement level) {
		
		if (!this._element.hasChildNodes()) return null;
		final TableElement tableElement = TableElement.as(this._element);
		
		final List<Element> listElements = new ArrayList<Element>();
		
		final NodeList<TableRowElement> rows = tableElement.getRows();
		for (int i = 0; i < rows.getLength(); i++) {
			final TableRowElement row = rows.getItem(i);
			if (level == LevelElement.LEVEL_2) {
				listElements.add(row);
			}
			else {
				
				final NodeList<TableCellElement> cells = row.getCells();
				for (int j = 0; j < cells.getLength(); j++) {
					listElements.add(cells.getItem(j));
				}
			}
			
		}
		
		return listElements;
	}
	
	 private void _removeStyleAttributFromPrefix(final Element element, String attributePrefix) {
	    	
	    	final String styleAttributes = element.getAttribute(STYLE);
	    	if (styleAttributes == null || styleAttributes.length() == 0) {
	    		return;
	    	}
	    	final StringBuilder sb = new StringBuilder();
	    	final String[] tokens = styleAttributes.split(";");
	    	for (int i = 0; i < tokens.length; i++) {
				String token = tokens[i].trim();
				if (token.equals(";") || token.equals("")) {
					continue;
				}
				if (!token.startsWith(attributePrefix)) {
					sb.append(token + "; ");
				}
			}
	    	element.setAttribute("style", sb.toString());
	    }
	
    private void _addStyleAttributeWithPrefix (final Element element, final String attributePrefix, final String attributeValue) {
		
		if (attributeValue == null || attributeValue.trim().length() == 0) {
			this._removeStyleAttributFromPrefix(element, attributePrefix);
			return;
		}
		
		final String styleAttributes = element.getAttribute(STYLE);

		if (styleAttributes == null || styleAttributes.length() == 0) {
			element.setAttribute(STYLE, attributePrefix + ": " + attributeValue + "; ");
			return;
		}
		
		boolean findAttribute = false;
		final StringBuilder sb = new StringBuilder();
    	final String[] tokens = styleAttributes.split(";");
    	for (int i = 0; i < tokens.length; i++) {
			String token = tokens[i].trim();

			if (token.equals(";") || token.equals("")) {
				continue;
			}
			if (token.startsWith(attributePrefix)) {
				// replace
				findAttribute = true;
				sb.append(attributePrefix + ": " + attributeValue + "; ");
			}
			else {
				sb.append(token + "; ");
			}	
		}
    	
    	// new attribute
    	if(!findAttribute) {
    		sb.append(attributePrefix + ": " + attributeValue + "; ");
    	}
    	
    	element.setAttribute(STYLE, sb.toString());
		
	}
    
 
	
	//----------------------------------------constructor
	public ElementToControl(final Element element, final TypeElement typeElement) {
		this._element = element;
		this._typeElement = typeElement;
	}
}
