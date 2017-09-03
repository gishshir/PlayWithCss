package fr.tsadeo.app.gwt.playwithcss.client.util;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class WidgetUtils {
	
	private static final List<String> NUMERIC_MENTIONS = new ArrayList<String>();
	
	static {
		
		NUMERIC_MENTIONS.add("pt");
		NUMERIC_MENTIONS.add("pc");
		NUMERIC_MENTIONS.add("in");
		NUMERIC_MENTIONS.add("mm");
		NUMERIC_MENTIONS.add("cm");
		NUMERIC_MENTIONS.add("px");
		NUMERIC_MENTIONS.add("em");
	}
	
   public static Panel buildSimplePlanel (final Widget widget, String width) {
		
		final Panel panel = new SimplePanel();
		if (width != null) {
		  panel.setWidth(width);
		}
		panel.add(widget);
		return panel;
	}

	public static Panel buildTextBox(final String labelTxt, final TextBox textBox) {
		
		final Panel panelTextBox = new HorizontalPanel();
		final Label label = new Label(labelTxt);
		panelTextBox.add(label);
		panelTextBox.add(textBox);
		
		label.setStyleName("label");
		textBox.setStylePrimaryName("box");
		
		
		panelTextBox.setStyleName("panelTextBox");
		return panelTextBox;
	}
	
	public static void completeSuffixPx(final TextBox textBox) {
		if (getTextBoxValueLength(textBox)== 0) return;
		
		final String value = textBox.getText().trim();
		final String finalValue = value.substring(value.length() - 2);
		
		if (finalValue.endsWith("%") || NUMERIC_MENTIONS.contains(finalValue) || value.equals("auto")) {
			textBox.setText(value);
		}
		else {
		   textBox.setText(value+ "px");
		}
	}
	public static int getTextBoxValueLength (final TextBox textBox) {
		if (textBox == null || textBox.getText() == null) {
			return 0;
		}
		return textBox.getText().trim().length();
	}
}
