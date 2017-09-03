package fr.tsadeo.app.gwt.playwithcss.client.cssproperty;

public class CssStyleBean {
	
	public enum CssStyle {
		
		
		// className / styleName / styleValue
		displayNone(new CssStyleBean("cssDisplayNone", "display", "none")), 
		displayBlock(new CssStyleBean("cssDisplayBlock", "display", "block")),
		displayInline(new CssStyleBean("cssDisplayInline", "display", "inline")),
		displayListItem(new CssStyleBean("cssDisplayListItem", "display", "list-item")),
		
		visible(new CssStyleBean("cssVisible", "visibility", "visible")),
		hidden(new CssStyleBean("cssHidden", "visibility", "hidden")),
		
		positionAbsolute(new CssStyleBean("cssPositionAbsolute","position", "absolute")),
		positionRelative(new CssStyleBean("cssPositionRelative","position", "relative")),
		positionStatic(new CssStyleBean("cssPositionStatic","position", "static")),
		positionFixed(new CssStyleBean("cssPositionFixed","position", "fixed")),
		
		floatLeft(new CssStyleBean("cssFloatLeft","float", "left")),
		floatRight(new CssStyleBean("cssFloatRight","float", "right")),
		floatNone(new CssStyleBean("cssFloatNone","float", "none")),
		
		tableLayoutFixed(new CssStyleBean("cssTableLayoutFixed","table-layout", "fixed")),
		tableLayoutAuto(new CssStyleBean("cssTableLayoutAuto","table-layout", "auto")),
		
		borderCollapseCollapse(new CssStyleBean("cssBorderCollapseCollapse","border-collapse", "collapse")),
		borderCollapseSeparate(new CssStyleBean("cssBorderCollapseSeparate","border-collapse", "separate")),
		
		emptyCellsShow(new CssStyleBean("cssEmptyCellsShow","empty-cells", "show")),
		emptyCellsHide(new CssStyleBean("cssEmptyCellsHide","empty-cells", "hide")),
		
		clearLeft(new CssStyleBean("cssClearLeft","clear", "left")),
		clearRight(new CssStyleBean("cssClearRight","clear", "right")),
		clearNone(new CssStyleBean("cssClearNone","clear", "none")),
		
		overflowVisible(new CssStyleBean("cssOverflowVisible","overflow", "visible")),
		overflowAuto(new CssStyleBean("cssOverflowAuto","overflow", "auto")),
		overflowScroll(new CssStyleBean("cssOverflowScroll","overflow", "scroll")),
		overflowHidden(new CssStyleBean("cssOverflowHidden","overflow", "hidden")),
		
		dirLtr(new CssStyleBean("cssDirLtr","dir", "ltr")),
		dirRtl(new CssStyleBean("cssDirLtr","dir", "rTl")),
		
		backgroundAttachmentFixed(new CssStyleBean( "cssBackgroundAttachmentFixed", "background-attachment", "fixed")),
		backgroundAttachmentScroll(new CssStyleBean( "cssBackgroundAttachmentScroll", "background-attachment", "scroll")),
		backgroundRepeat(new CssStyleBean( "cssBackgroundRepeat", "background-repeat", "repeat")),
		backgroundRepeatX(new CssStyleBean( "cssBackgroundRepeatX", "background-repeat", "repeat-x")),
		backgroundRepeatY(new CssStyleBean( "cssBackgroundRepeatY", "background-repeat", "repeat-y")),
		backgroundNoRepeat(new CssStyleBean( "cssBackgroundNoRepeat", "background-repeat", "no-repeat")),
		
		// styleName / styleValue
		verticalAlignBaseLine(new CssStyleBean("vertical-align", "baseligne")),
		verticalAlignBottom(new CssStyleBean("vertical-align", "bottom")),
		verticalAlignMiddle(new CssStyleBean("vertical-align", "middle")),
		verticalAlignSub(new CssStyleBean("vertical-align", "sub")),
		verticalAlignSuper(new CssStyleBean("vertical-align", "super")),
		verticalAlignTextBottom(new CssStyleBean("vertical-align", "text-bottom")),
		verticalAlignTextTop(new CssStyleBean("vertical-align", "text-top")),
		verticalAlignTop(new CssStyleBean("vertical-align", "top")),
		
		textAlignCenter(new CssStyleBean("text-align", "center")),
		textAlignJustify(new CssStyleBean("text-align", "justify")),
		textAlignLeft(new CssStyleBean("text-align", "left")),
		textAlignRight(new CssStyleBean("text-align", "right")),
		
		whiteSpaceNormal(new CssStyleBean("white-space", "normal")),
		whiteSpaceNoWrap(new CssStyleBean("white-space", "nowrap")),
		whiteSpacePre(new CssStyleBean("white-space", "pre")),
		
		textOverflowClip(new CssStyleBean( "text-overflow", "clip")),
		textOverflowEllipsis(new CssStyleBean( "text-overflow", "ellipsis")),
		

		//  styleName
		clip(new CssStyleBean("clip")),
		
		sizeWidth(new CssStyleBean("width")),
		sizeHeight(new CssStyleBean("height")),
		sizeMinWidth(new CssStyleBean("min-width")),
		sizeMaxWidth(new CssStyleBean("max-width")),
		
		positionTop(new CssStyleBean("top")),
		positionBottom(new CssStyleBean("bottom")),
		positionRight(new CssStyleBean("right")),
		positionLeft(new CssStyleBean("left")),
		
		positionZIndex(new CssStyleBean("z-index")),
		
		borderColor(new CssStyleBean("border-color")),
		borderColorTop(new CssStyleBean("border-top-color")),
		borderColorLeft(new CssStyleBean("border-left-color")),
		borderColorRight(new CssStyleBean("border-right-color")),
		borderColorBottom(new CssStyleBean("border-bottom-color")),
		
		borderWidth(new CssStyleBean("border-width")),
		borderWidthTop(new CssStyleBean("border-top-width")),
		borderWidthLeft(new CssStyleBean("border-left-width")),
		borderWidthRight(new CssStyleBean("border-right-width")),
		borderWidthBottom(new CssStyleBean("border-bottom-width")),
		
		borderRadius(new CssStyleBean("border-radius")),
		
		borderStyle(new CssStyleBean( "border-style")),
		borderStyleTop(new CssStyleBean("border-top-style")),
		borderStyleLeft(new CssStyleBean( "border-left-style")),
		borderStyleRight(new CssStyleBean("border-right-style")),
		borderStyleBottom(new CssStyleBean( "border-bottom-style")),
		
		lineHeight(new CssStyleBean( "line-height")),
		textIndent(new CssStyleBean("text-indent")),
		
		margin(new CssStyleBean("margin")),
		marginTop(new CssStyleBean("margin-top")),
		marginBottom(new CssStyleBean("margin-bottom")),
		marginRight(new CssStyleBean("margin-right")),
		marginLeft(new CssStyleBean("margin-left")),
		
		padding(new CssStyleBean("padding")),
		paddingTop(new CssStyleBean("padding-top")),
		paddingBottom(new CssStyleBean("padding-bottom")),
		paddingRight(new CssStyleBean("padding-right")),
		paddingLeft(new CssStyleBean("padding-left")),
		
		backgroundColor(new CssStyleBean("background-color")),
		backgroundPosition(new CssStyleBean("background-position")),
		backgroundImage(new CssStyleBean("background-image")),
		
		borderSpacing(new CssStyleBean("border-spacing"));
		
		public final CssStyleBean getCssStyleBean() {
			return this._cssStyleBean;
		}
		public String getStyleName() {
			return this._cssStyleBean._styleName;
		}
		public String getStyleValue() {
			return this._cssStyleBean._styleValue;
		}
		public String getClassName() {
			return this._cssStyleBean._className;
		}
		public CssStyle updateStyleValue (final String styleValue){
			this._cssStyleBean._styleValue = styleValue;
			return this;
		}
		private final CssStyleBean _cssStyleBean;
		private CssStyle (final CssStyleBean cssStyleBean) {
			this._cssStyleBean = cssStyleBean;
		}
	}
	
	private final String _className;
	private final String _styleName;
	private String _styleValue;
	
	//--------------------------------------------- public methods
	public String getDisplayStyleAttribute () {
		return (this._styleValue != null && this._styleValue.length() > 0)? this._styleName + ": " + this._styleValue + ";":null;
	}
	
	//--------------------------------------------- accessor
	public String getClassName() {
		return _className;
	}
	public String getStyleName() {
		return _styleName;
	}
	public String getStyleValue() {
		return _styleValue;
	}
	//------------------------------------------- constructors
	public CssStyleBean(final String styleName) {
		this(styleName, null);
	}
	public CssStyleBean(final String styleName, String styleValue) {
		this(null, styleName, styleValue);
	}
	public CssStyleBean( final String className, final String styleName, String styleValue) {
		this._className = className;
		this._styleName = styleName;
		this._styleValue = styleValue;
	}

}
