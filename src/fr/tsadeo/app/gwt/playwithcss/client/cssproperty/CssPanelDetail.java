package fr.tsadeo.app.gwt.playwithcss.client.cssproperty;

import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import fr.tsadeo.app.gwt.playwithcss.client.util.Types.Positions;
import fr.tsadeo.app.gwt.playwithcss.client.util.Types.TypePosition;
import fr.tsadeo.app.gwt.playwithcss.client.util.WidgetUtils;

/**
 * Panel de 4 textboxes présentées verticalement
 * @author NDMZ2720
 *
 */
public class CssPanelDetail extends VerticalPanel {
	
	
	private static final String DEFAULT_VALUE = "10px";
	
	private final TextBox _tbPos1 = new TextBox();
	private final TextBox _tbPos2 = new TextBox();
	private final TextBox _tbPos3 = new TextBox();
	private final TextBox _tbPos4 = new TextBox();
	
	private final TypePosition _typePosition;
	
	public CssPanelDetail () {
		this(TypePosition.cotes);
	}
	public CssPanelDetail (final TypePosition typePosition) {
		this._typePosition = typePosition;
		this.buildTextBoxes();
	}
	
	public void setChangeHandler(final ChangeHandler tbChangeHandler) {
		this.initTextBoxes(tbChangeHandler);
	}
	
	public void reinit(final String defaultTop, final String defaultBottom,
			final String defaultLeft, final String defaultRight) {
		
		this._tbPos3.setValue((defaultBottom!=null)?defaultBottom:DEFAULT_VALUE);
		this._tbPos2.setValue((defaultLeft!=null)?defaultLeft:DEFAULT_VALUE);
		this._tbPos4.setValue((defaultRight!=null)?defaultRight:DEFAULT_VALUE);
		this._tbPos1.setValue((defaultTop!=null)?defaultTop:DEFAULT_VALUE);
	}
	
	public void reinit() {
		this.reinit(null, null, null, null);
	}
	
	//--------------------------------------------- public methods
	
	public String getValue(Positions position) {
		return this.getValue(this.getTextBox(position));
	}
	
	public boolean isValueEmpty(Positions position) {
		return (WidgetUtils.getTextBoxValueLength(this.getTextBox(position)) == 0)?true:false;
	}
	//--------------------------------------------- private methods
	private boolean isTypePositionCote() {
		return this._typePosition != null && this._typePosition == TypePosition.cotes;
	}
	private TextBox getTextBox(Positions position) {
		
		if (this.isTypePositionCote()) {
			switch (position) {
			case top:return this._tbPos1;
			case left:return this._tbPos2;
			case bottom:return this._tbPos3;
			case right:return this._tbPos4;		
			}
		} else {
			switch (position) {
			case topL:return this._tbPos1;
			case topR:return this._tbPos2;
			case bottomR:return this._tbPos3;
			case bottomL:return this._tbPos4;
			}
		}
		return null;
	}
	private String getValue(final TextBox textBox) {
		WidgetUtils.completeSuffixPx(textBox);
		final String value = textBox.getValue();
		return (value.equals(""))?null:value;
	}
	private void buildTextBoxes () {
		
		this.add(WidgetUtils.buildTextBox(this.isTypePositionCote()?"top":"top left", this._tbPos1));
		this.add(WidgetUtils.buildTextBox(this.isTypePositionCote()?"bottom":"bottom right", this._tbPos3));
		this.add(WidgetUtils.buildTextBox(this.isTypePositionCote()?"left":"top right", this._tbPos2));
		this.add(WidgetUtils.buildTextBox(this.isTypePositionCote()?"right":"bottom left", this._tbPos4));
		this.setStyleName("panelTextBoxes");
	}
	private void initTextBoxes(final ChangeHandler tbChangeHandler) {
		
	    this._tbPos3.addChangeHandler(tbChangeHandler);
	    this._tbPos2.addChangeHandler(tbChangeHandler);
	    this._tbPos4.addChangeHandler(tbChangeHandler);
	    this._tbPos1.addChangeHandler(tbChangeHandler);
	}

}
