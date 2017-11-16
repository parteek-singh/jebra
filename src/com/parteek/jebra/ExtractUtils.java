package com.parteek.jebra;
/*
 * It is a libiray class (Singleton)
 * */

import japa.parser.ast.body.VariableDeclarator;
import japa.parser.ast.expr.AssignExpr;
import japa.parser.ast.expr.Expression;
import japa.parser.ast.expr.IntegerLiteralExpr;
import japa.parser.ast.expr.MethodCallExpr;
import japa.parser.ast.expr.NullLiteralExpr;
import japa.parser.ast.expr.ObjectCreationExpr;
import japa.parser.ast.expr.VariableDeclarationExpr;
import japa.parser.ast.stmt.ExpressionStmt;
import japa.parser.ast.stmt.ReturnStmt;
import japa.parser.ast.stmt.Statement;

public class ExtractUtils {

	private static ExtractUtils extractUtils;
	
	public ExtractUtils() {
		// TODO Auto-generated constructor stub
	}
	
	public static ExtractUtils getInstance() {
		
		if(extractUtils == null) {
			extractUtils = new ExtractUtils();
			return extractUtils;
		}
		
		return extractUtils; 
	}
	
	
	
}
