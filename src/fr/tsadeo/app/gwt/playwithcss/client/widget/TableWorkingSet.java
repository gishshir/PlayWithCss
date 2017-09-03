package fr.tsadeo.app.gwt.playwithcss.client.widget;

import java.util.HashMap;
import java.util.Map;

import com.google.gwt.dom.client.Node;
import com.google.gwt.dom.client.NodeList;
import com.google.gwt.dom.client.TableElement;
import com.google.gwt.dom.client.TableRowElement;
import com.google.gwt.user.client.DOM;
import com.google.gwt.dom.client.Element;

import fr.tsadeo.app.gwt.playwithcss.client.util.Types.TypeElement;
/**
 * 
 * les widgets a manipuler sont dans un container de type table
 * @author sylvie
 *
 */
public class TableWorkingSet extends AbstractWorkingSet implements IWorkingSet {
	
	private final TableElement _tableElement = TableElement.as(DOM.createElement(TypeElement.TABLE.getElement()));
	
	private final Map<String, Element> _mapCellByName = new HashMap<String, Element>();
	
	private final static int MAX_COL = 2;
	
	private int _rowCount = 0;
	
	public TableWorkingSet() {
		super(TypeElement.TABLE);
		this._tableElement.addClassName("workingSet");
		this.getElement().appendChild(this._tableElement);
	}

	
	//----------------------------------------- private methods
	private void addRowNode () {
		
		final TableRowElement tableRow = this._tableElement.insertRow(this._rowCount);
		for (int i = 0; i < MAX_COL; i++) {
			tableRow.insertCell(i);
		}
		this._rowCount++;
	}
	
	private Element getNextEmptyCell() {
		final int countElement = this.getCountElements();
		if (countElement > this._rowCount * MAX_COL) {
			this.addRowNode();
		}
		
		final int div = countElement / MAX_COL;
		final int mod = countElement % MAX_COL;
		
		final int row = (mod == 0)?div-1:div;
		final int col = (mod == 0)?MAX_COL-1:mod-1;
		
		final TableRowElement tableRow = this._tableElement.getRows().getItem(row);
		return tableRow.getCells().getItem(col);
	}

	
	//------------------------------------------ overrriding AbstractWorkingSet
	@Override
	protected void addProtected(String name, Element element) {
        final Element cell = this.getNextEmptyCell();
		this._mapCellByName.put(name, cell);
		cell.appendChild(element);
	}

	@Override
	protected void replaceProtected(String name, Element newElement,
			Element oldElement) {
		final Element cell = this._mapCellByName.get(name);
		if (cell == null) return;
		
		cell.replaceChild(newElement, oldElement);
	}

	@Override
	protected void removeProtected(String name, Element element) {
		final Element cell = this._mapCellByName.get(name);
		if (cell == null) return;
		
		this._mapCellByName.remove(name);
		cell.removeChild(element);
	}


	@Override
	protected void clearAllProtected() {
		this._mapCellByName.clear();
		if (!this._tableElement.hasChildNodes()) return;
		
		final NodeList<Node> nodes = this._tableElement.getChildNodes();
		for (int i = 0; i < nodes.getLength(); i++) {
			final Node node = nodes.getItem(i);
			node.removeFromParent();
		}
	}


	@Override
	public Element getWorkingSetElement() {
		return this._tableElement;
	}

}
