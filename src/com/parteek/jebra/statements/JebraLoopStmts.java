package com.parteek.jebra.statements;

import java.util.ArrayList;

import com.parteek.jebra.JebraCollections;

import japa.parser.ast.body.VariableDeclarator;
import japa.parser.ast.expr.BinaryExpr;
import japa.parser.ast.expr.BooleanLiteralExpr;
import japa.parser.ast.expr.Expression;
import japa.parser.ast.expr.MethodCallExpr;
import japa.parser.ast.expr.NameExpr;
import japa.parser.ast.expr.VariableDeclarationExpr;
import japa.parser.ast.stmt.BlockStmt;
import japa.parser.ast.stmt.DoStmt;
import japa.parser.ast.stmt.ForStmt;
import japa.parser.ast.stmt.ForeachStmt;
import japa.parser.ast.stmt.IfStmt;
import japa.parser.ast.stmt.Statement;
import japa.parser.ast.stmt.WhileStmt;
import util.Constants;
import util.JebraStatementFactory;
import util.Utils;

public class JebraLoopStmts extends JebraStmts{

	private ForeachStmt foreachStmt;
	private ForStmt forStmt;
	private WhileStmt whileStmt;
	private DoStmt doWhileStmt;

	ArrayList<JebraStmts> containedStmts;
	private JebraStmts conditionalStmt;
	
	public JebraLoopStmts() {
		setContainedStmts(new ArrayList<JebraStmts>());
	}
	
	
	public ForeachStmt getForeachStmt() {
		return foreachStmt;
	}
	
	public void setForeachStmt(ForeachStmt foreachStmt) {
		this.foreachStmt = foreachStmt;
	}
	
	public ArrayList<JebraStmts> getContainedStmts() {
		return containedStmts;
	}

	public void setContainedStmts(ArrayList<JebraStmts> containedStmts) {
		this.containedStmts = containedStmts;
	}
	
	public void addContainedStmts(JebraStmts containedStmt) {
		this.containedStmts.add(containedStmt);
	}


	public ForStmt getForStmt() {
		return forStmt;
	}


	public void setForStmt(ForStmt forStmt) {
		this.forStmt = forStmt;
	}


	public WhileStmt getWhileStmt() {
		return whileStmt;
	}


	public void setWhileStmt(WhileStmt whileStmt) {
		this.whileStmt = whileStmt;
	}


	public DoStmt getDoWhileStmt() {
		return doWhileStmt;
	}


	public void setDoWhileStmt(DoStmt doWhileStmt) {
		this.doWhileStmt = doWhileStmt;
	}
	
	public void InitForEachLoopObject(Statement statement) {
		
		ForeachStmt foreachStmt = (ForeachStmt) statement;
		this.setForeachStmt(foreachStmt);
		this.setStmt(foreachStmt.toString());
		if (foreachStmt.getVariable() instanceof VariableDeclarationExpr) {
			VariableDeclarationExpr declarationExpr = (VariableDeclarationExpr) foreachStmt.getVariable();
			if (declarationExpr != null) {
				this.setReturnType(declarationExpr.getType().toString());
				for (VariableDeclarator var : declarationExpr.getVars()) {
					this.setReturnObject(var.getId().toString());
				}
			}
		}
		CalculateLineNumbers(statement);
		retriveConditionalStmt(foreachStmt.getIterable(), foreachStmt.getBeginLine());
		if (foreachStmt.getBody() != null && foreachStmt.getBody() instanceof BlockStmt) {
			BlockStmt blockStmt = (BlockStmt) foreachStmt.getBody();
			GenrateBlockStatement(blockStmt);
		}
	}
	
	public void InitForLoopObject(Statement statement) {

		ForStmt forStmt = (ForStmt) statement;
		this.setStmt(forStmt.toString());
		this.setForStmt(forStmt);
		
		CalculateLineNumbers(statement);
		retriveConditionalStmt(forStmt.getCompare(), forStmt.getBeginLine());
		if (forStmt.getBody() != null && forStmt.getBody() instanceof BlockStmt) {
			BlockStmt blockStmt = (BlockStmt) forStmt.getBody();
			GenrateBlockStatement(blockStmt);
		}
	}
	
	public void InitWhileLoopObject(Statement statement) {
	
		WhileStmt whileStmt = (WhileStmt) statement;
		this.setWhileStmt(whileStmt);
		this.setStmt(whileStmt.toString());

		CalculateLineNumbers(statement);
		retriveConditionalStmt(whileStmt.getCondition() , whileStmt.getBeginLine());
		if (whileStmt.getBody() != null && whileStmt.getBody() instanceof BlockStmt) {
			BlockStmt blockStmt = (BlockStmt) whileStmt.getBody();
			GenrateBlockStatement(blockStmt);
		}
	}
	
	public void InitDoWhileLoopObject(Statement statement) {
		
		DoStmt doWhileStmt = (DoStmt) statement;
		this.setDoWhileStmt(doWhileStmt);
		this.setStmt(doWhileStmt.toString());
		
		CalculateLineNumbers(statement);
		retriveConditionalStmt(doWhileStmt.getCondition() , doWhileStmt.getBeginLine());
		
		if (doWhileStmt.getBody() != null && doWhileStmt.getBody() instanceof BlockStmt) {
			BlockStmt blockStmt = (BlockStmt) doWhileStmt.getBody();
			GenrateBlockStatement(blockStmt);
		}
	}
	
	public void retriveConditionalStmt(Expression exprObj , int indexObj){
		
		conditionalStmt = new JebraStmts();

		JebraCollections jebraCollections = null;
		if (exprObj != null) {
			if (exprObj instanceof BinaryExpr) {
				BinaryExpr expr = (BinaryExpr) exprObj;				
				conditionalStmt.setExpr(expr);
				conditionalStmt.setLeft(expr.getLeft().toString());
				conditionalStmt.setRight(expr.getRight().toString());
				conditionalStmt.setIndex(indexObj);
				conditionalStmt.setStmt(expr.toString());
				conditionalStmt.setOperator(expr.getOperator().toString());
				
				if(!(expr.getLeft() instanceof MethodCallExpr)) {
					conditionalStmt.setReturnObject(expr.getLeft().toString());
					conditionalStmt.setReturnType(getObjectType(expr.getLeft()));
				}
				else if(!(expr.getRight() instanceof MethodCallExpr)) {
					conditionalStmt.setReturnObject(expr.getRight().toString());
					conditionalStmt.setReturnType(getObjectType(expr.getRight()));
				}
				
				if(expr.getRight() instanceof MethodCallExpr) {
					MethodCallExpr callExpr = (MethodCallExpr) expr.getRight();
					if(callExpr != null) {
						if(this.getReturnObject() != null && this.getReturnType() != null) {
							jebraCollections = new JebraCollections();
							jebraCollections.setMethodCallName(callExpr.getScope().toString());
							jebraCollections.setObjName(this.getReturnObject());
							jebraCollections.setReturnType(this.getReturnType());
							jebraCollections.setType("List");
							conditionalStmt.addAnyCollectionObject(jebraCollections);
						}
						
						conditionalStmt.setExpr(callExpr);
						conditionalStmt.setLeft("boolean");
						conditionalStmt.setRight(callExpr.getScope().toString());
						conditionalStmt.setIndex(callExpr.getBeginLine());
						conditionalStmt.setStmt(callExpr.toString());
						conditionalStmt.setOperator("equals");
						conditionalStmt.setConditionType(Constants.FAIL);
						conditionalStmt.setReturnType(Constants.NULL_OBJECT);
						conditionalStmt.setReturnObject(this.getReturnObject());
			
					}
				}
			}
			else if (exprObj instanceof BooleanLiteralExpr) {
				BooleanLiteralExpr expr = (BooleanLiteralExpr) exprObj;
				conditionalStmt.setExpr(expr);
				conditionalStmt.setLeft("boolean");
				conditionalStmt.setRight(((Boolean) expr.getValue()).toString());
				conditionalStmt.setIndex(indexObj);
				conditionalStmt.setStmt(expr.toString());
				conditionalStmt.setOperator("equals");
			}
			else if(exprObj instanceof NameExpr) {
				NameExpr expr = (NameExpr) exprObj;
				if(expr != null) {
					conditionalStmt.setExpr(expr);
					conditionalStmt.setLeft(this.getReturnType() +" "+this.getReturnObject());
					conditionalStmt.setRight(expr.toString());
					conditionalStmt.setIndex(indexObj);
					conditionalStmt.setStmt(expr.toString());
					conditionalStmt.setOperator(":");
					conditionalStmt.setReturnType(this.getReturnType());
					conditionalStmt.setReturnObject(this.getReturnObject());
				}
			}
			else if(exprObj instanceof MethodCallExpr) {
				MethodCallExpr callExpr = (MethodCallExpr) exprObj;
				if(callExpr != null) {
					
					if (Utils.isCollectionType(this.getReturnObject())) {
						jebraCollections = new JebraCollections();
						jebraCollections.setMethodCallName(callExpr.toString());
						jebraCollections.setObjName(this.getReturnObject());
						jebraCollections.setReturnType(this.getReturnType());

						jebraCollections.setType("List");
					}
					conditionalStmt.addAnyCollectionObject(jebraCollections);
					conditionalStmt.setExpr(callExpr);
					conditionalStmt.setLeft("boolean");
					conditionalStmt.setRight(callExpr.toString());
					conditionalStmt.setIndex(callExpr.getBeginLine());
					conditionalStmt.setStmt(callExpr.toString());
					conditionalStmt.setOperator("equals");
					conditionalStmt.setReturnType(this.getReturnType());
					conditionalStmt.setReturnObject(this.getReturnObject());
					
				}
			}
		}
	
		this.containedStmts.add(conditionalStmt);
	}
	private void CalculateLineNumbers(Statement statement) {
		int beginLine = statement.getBeginLine();
		int endLine = statement.getEndLine();
		for (int i = beginLine; i <= endLine; i++) {
			this.addLineNumber(i);
		}
	}
	
	private void GenrateBlockStatement(BlockStmt blockStmt) {
		if (blockStmt != null && blockStmt.getStmts() != null) {
			for (Statement stmt : blockStmt.getStmts()) {
				JebraStatementFactory.getInstance().genrateStatements(stmt, this.containedStmts);
				this.getBodyStmts().add(stmt.toString());
			}
		}
	}


	public JebraStmts getConditionalStmt() {
		return conditionalStmt;
	}


	public void setConditionalStmt(JebraStmts conditionalStmt) {
		this.conditionalStmt = conditionalStmt;
	}
}
