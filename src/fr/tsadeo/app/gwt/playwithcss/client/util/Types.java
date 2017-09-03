package fr.tsadeo.app.gwt.playwithcss.client.util;

import java.util.ArrayList;
import java.util.List;

public final class Types {
	
	public enum TypePosition {coins, cotes};
	public enum Positions {top(TypePosition.cotes), left(TypePosition.cotes), right(TypePosition.cotes), bottom(TypePosition.cotes),
		topL(TypePosition.coins), topR(TypePosition.coins), bottomR(TypePosition.coins), bottomL(TypePosition.coins);
	
	   private final TypePosition _type;
	   private Positions(final TypePosition type) {
		this._type = type;
	   }
	   
	   public static List<Positions> getValues(final TypePosition type) {
		   
		   final List<Positions> list = new ArrayList<Types.Positions>(4);
		   for (int i = 0; i < values().length; i++) {
			  final Positions position = values()[i];
			  if(position._type == type) {
				  list.add(position);
			  }		
		   }
		   return list;
	   }
	};

	public enum ToControl {WIDGET, WORKINGSET, BOTH};
	
	public enum LevelElement {LEVEL_1, LEVEL_2, LEVEL_3};
	
	public enum TypeElement {

		DIV("div", ToControl.BOTH),
		TABLE("table", ToControl.WORKINGSET),
	    SPAN("span", ToControl.WIDGET),
	    P("p"), 
	    PRE("pre"), BLOCKQUOTE("blockquote"), ADDRESS(
				"address"), NOWRAP("nowrap");

		private final String _element;
		private final ToControl _toControl;

		
		//------------------------------------------------- accessors
		public final String getElement() {
			return this._element;
		}
		private final ToControl getToControl() {
			return this._toControl;
		}

		
		//------------------------------------------------- constructeur
		TypeElement(final String element) {
			this(element, ToControl.WIDGET);
		}
		TypeElement(final String element, final ToControl toControl) {
			this._element = element;
			this._toControl = toControl;
		}
		
		//-------------------------------------------------- public methods

		public final String getBeginBalise() {
			return "<" + this._element + ">";
		}
		public final String getEndBalise() {
			return "<\\" + this._element + ">";
		}
		
		public static final List<TypeElement> getListTypeElement (final ToControl toControl) {
			final List<TypeElement> list = new ArrayList<Types.TypeElement>();
			final TypeElement[] values = TypeElement.values();
			for (int i = 0; i < values.length; i++) {
				final TypeElement typeElement = values[i];
				if (typeElement.getToControl() == toControl || typeElement.getToControl() == ToControl.BOTH) {
					list.add(typeElement);
				}
			}
			return list;
		}

	}
	
}
