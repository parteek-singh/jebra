package com.parteek.jebra.statements;

import java.util.ArrayList;

import org.mockito.internal.matchers.InstanceOf;

import japa.parser.ast.expr.BinaryExpr;
import japa.parser.ast.expr.BooleanLiteralExpr;
import japa.parser.ast.expr.Expression;
import japa.parser.ast.expr.InstanceOfExpr;
import japa.parser.ast.expr.IntegerLiteralExpr;
import japa.parser.ast.expr.MethodCallExpr;
import japa.parser.ast.expr.StringLiteralExpr;
import japa.parser.ast.stmt.BlockStmt;
import japa.parser.ast.stmt.IfStmt;
import japa.parser.ast.stmt.Statement;
import util.JebraStatementFactory;
import util.Utils;

public class JebraIfElseStmts extends JebraStmts {

	private IfStmt ifOrigStmts;

	private JebraStmts conditionalStmt;
	private JebraStmts elseIfConditionalStmt;
	private ArrayList<JebraStmts> thenStmts;
	private ArrayList<JebraStmts> elseIfStmts;
	private ArrayList<JebraStmts> elseStmts;

	public JebraIfElseStmts() {
		setThenStmts(new ArrayList<JebraStmts>());
		setElseIfStmts(new ArrayList<JebraStmts>());
		setElseStmts(new ArrayList<JebraStmts>());
	}

	public JebraStmts getConditionalStmt() {
		return conditionalStmt;
	}

	public void setConditionalStmt(JebraStmts conditionalStmt) {
		this.conditionalStmt = conditionalStmt;
	}
	
	public IfStmt getIfOrigStmts() {
		return ifOrigStmts;
	}

	public void setIfOrigStmts(IfStmt ifOrigStmts) {
		this.ifOrigStmts = ifOrigStmts;
	}

	public ArrayList<JebraStmts> getThenStmts() {
		return thenStmts;
	}

	public void setThenStmts(ArrayList<JebraStmts> thenStmts) {
		this.thenStmts = thenStmts;
	}

	public void addThenStmts(JebraStmts thenStmts) {
		this.thenStmts.add(thenStmts);
	}

	public ArrayList<JebraStmts> getElseIfStmts() {
		return elseIfStmts;
	}

	public void setElseIfStmts(ArrayList<JebraStmts> elseIfStmts) {
		this.elseIfStmts = elseIfStmts;
	}

	public void addElseIfStmts(JebraStmts elseIfStmts) {
		this.elseIfStmts.add(elseIfStmts);
	}

	public ArrayList<JebraStmts> getElseStmts() {
		return elseStmts;
	}

	public void setElseStmts(ArrayList<JebraStmts> elseStmts) {
		this.elseStmts = elseStmts;
	}

	public void addElseStmts(JebraStmts elseStmts) {
		this.elseStmts.add(elseStmts);
	}

	public void initObject(IfStmt obj){
		
		setIndex(obj.getBeginLine());
		setIfOrigStmts(obj);
		setStmt(obj.toString());
		InitCondtion(obj, this);
		retriveConditionalStmt();
		
		// if block
		thenStmts.add(conditionalStmt);
		retriveThenStmt(this.ifOrigStmts,thenStmts);
		
		if(getIfOrigStmts() != null && this.ifOrigStmts.getElseStmt() !=null) {
			
			if(this.ifOrigStmts.getElseStmt() instanceof IfStmt) {
				IfStmt elseIfObj = (IfStmt)this.ifOrigStmts.getElseStmt();
				//else if block

				retriveElseIfConditionalStmt(elseIfObj);
				elseIfStmts.add(conditionalStmt);
				elseIfStmts.add(elseIfConditionalStmt);
				retriveThenStmt(elseIfObj , elseIfStmts);
				
				if(elseIfObj.getElseStmt() instanceof BlockStmt) {
					elseStmts.add(conditionalStmt);
					if(elseIfConditionalStmt != null)
						elseStmts.add(elseIfConditionalStmt);
					BlockStmt blockStmt = (BlockStmt) elseIfObj.getElseStmt();
					for (Statement statement : blockStmt.getStmts()) {
						if(statement!=null) {
							JebraStatementFactory.getInstance().genrateStatements(statement, elseStmts);
						}
					}	
				}
			}
			
			// else block
			if(this.ifOrigStmts.getElseStmt() instanceof BlockStmt) {
				
				elseStmts.add(conditionalStmt);
				if(elseIfConditionalStmt != null)
					elseStmts.add(elseIfConditionalStmt);
				
				BlockStmt blockStmt = (BlockStmt) this.ifOrigStmts.getElseStmt();
				for (Statement statement : blockStmt.getStmts()) {
					if(statement!=null) {
						JebraStatementFactory.getInstance().genrateStatements(statement, elseStmts);
					}
				}	
			}
		}
	}
	
	public void retriveThenStmt(IfStmt ifStmts , ArrayList<JebraStmts> arrayList){
		// if block
		if(getIfOrigStmts() != null && ifStmts.getThenStmt() !=null && ifStmts.getThenStmt() instanceof BlockStmt) {
			BlockStmt blockStmt = (BlockStmt) ifStmts.getThenStmt();
			for (Statement statement : blockStmt.getStmts()) {
				if(statement!=null) {
					JebraStatementFactory.getInstance().genrateStatements(statement, arrayList);
				}
			}	
		}
		
	}

	private void InitCondtion(IfStmt obj , JebraStmts stmt) {
		if(obj != null) {
			stmt.setIndex(obj.getBeginLine());
			if (obj.getCondition() != null) {
				if (obj.getCondition() instanceof BinaryExpr) {
					BinaryExpr expr = (BinaryExpr) obj.getCondition();
					stmt.setLeft(expr.getLeft().toString());
					stmt.setOperator(expr.getOperator().toString());
					stmt.setRight(expr.getRight().toString());
					stmt.setStmt(expr.toString());
					stmt.setExpr(expr);
					
					if(!(expr.getLeft() instanceof MethodCallExpr)) {
						stmt.setReturnObject(expr.getLeft().toString());
						stmt.setReturnType(getObjectType(expr.getLeft()));
					}
					else if(!(expr.getRight() instanceof MethodCallExpr)) {
						stmt.setReturnObject(expr.getRight().toString());
						stmt.setReturnType(getObjectType(expr.getRight()));
					}
				}
				if (obj.getCondition() instanceof InstanceOfExpr) {
					InstanceOfExpr expr = (InstanceOfExpr) obj.getCondition();
					if(expr != null) {
						stmt.setReturnObject(expr.getExpr().toString());
						stmt.setReturnType(expr.getType().toString());
						stmt.setLeft(expr.getExpr().toString());
						stmt.setOperator("INSTANCE_OF");
						stmt.setRight(null);
						stmt.setStmt(expr.toString());
						stmt.setExpr(expr);
						System.out.println("");
					}
				}
				if (obj.getCondition() instanceof BooleanLiteralExpr) {
					BooleanLiteralExpr expr = (BooleanLiteralExpr) obj.getCondition();
					stmt.setLeft("boolean");
					stmt.setOperator("equals");
					stmt.setRight(((Boolean) expr.getValue()).toString());
					stmt.setStmt("if_" + ((Boolean) expr.getValue()).toString());
					stmt.setExpr(expr);
					
					stmt.setReturnObject(expr.toString());
					stmt.setReturnType(getObjectType(expr));
				}
				if (obj.getCondition() instanceof MethodCallExpr) {
					MethodCallExpr callExpr = (MethodCallExpr) obj.getCondition();
					if(callExpr != null) {
						stmt.setOperator(callExpr.getName());
						if(callExpr.getScope() != null ) {
							if(callExpr.getScope() instanceof  MethodCallExpr) {
								MethodCallExpr expr = (MethodCallExpr) obj.getCondition();
								if(expr!=null) {
									stmt.setExpr(expr);
									stmt.setStmt(callExpr.toString());
								}
							}
						}
						if(Utils.isNotEmpty(callExpr.getArgs())) {
							if( callExpr.getArgs().get(0) instanceof  StringLiteralExpr ) {
								stmt.setReturnObject(callExpr.getArgs().get(0).toString());
								stmt.setReturnType("String");
							}
							else {
								stmt.setReturnObject(callExpr.getArgs().get(0).toString());
							}
							
						}
					}
				}
			}
		}
	}
	
	public void retriveConditionalStmt(){
		
		conditionalStmt = new JebraStmts();
		InitCondtion(this.getIfOrigStmts() , conditionalStmt);
//		conditionalStmt.setLeft(this.getLeft());
//		conditionalStmt.setRight(this.getRight());
//		conditionalStmt.setIndex(this.getIndex());
//		conditionalStmt.setExpr(this.getIfOrigStmts().getCondition());
//		conditionalStmt.setStmt(this.getStmt());
		conditionalStmt.setReturnObject(this.getReturnObject());
		conditionalStmt.setReturnType(this.getReturnType());
	}

	public void retriveElseIfConditionalStmt(IfStmt obj){
		String left = "";
		String right = "";
		String stmt = "";
		String opertor = "";
		int index = obj.getBeginLine();
		
		elseIfConditionalStmt = new JebraStmts();
		//Expression exprObject = null;
		InitCondtion(obj , elseIfConditionalStmt);
//		elseIfConditionalStmt.setLeft(left);
//		elseIfConditionalStmt.setIndex(index);
//		elseIfConditionalStmt.setRight(right);
//		elseIfConditionalStmt.setIndex(obj.getBeginLine());
////		elseIfConditionalStmt.setStmt(stmt);
//		elseIfConditionalStmt.setOperator(opertor);
		
//		if (obj.getCondition() != null) {
//			if (obj.getCondition() instanceof BinaryExpr) {
//				BinaryExpr expr = (BinaryExpr) obj.getCondition();
//				left = expr.getLeft().toString();
//				opertor = expr.getOperator().toString();
//				right = expr.getRight().toString();
//				stmt = expr.toString();
//				exprObject = expr;
//
//			}
//			if (obj.getCondition() instanceof BooleanLiteralExpr) {
//				BooleanLiteralExpr expr = (BooleanLiteralExpr) obj.getCondition();
//				left = "boolean";
//				opertor = "equals";
//				right = ((Boolean) expr.getValue()).toString();
//				stmt = "if_" + ((Boolean) expr.getValue()).toString();
//				exprObject = expr;
//			}
//			if (obj.getCondition() instanceof MethodCallExpr) {
//				MethodCallExpr callExpr = (MethodCallExpr) obj.getCondition();
//				if(callExpr != null) {
//					this.setOperator(callExpr.getName());
//					if(callExpr.getScope() != null ) {
//						if(callExpr.getScope() instanceof  MethodCallExpr) {
//							MethodCallExpr expr = (MethodCallExpr) obj.getCondition();
//							if(expr!=null) {
//								this.setExpr(expr);
//								this.setStmt(callExpr.toString());
//							}
//						}
//					}
//					if(Utils.isNotEmpty(callExpr.getArgs())) {
//						if( callExpr.getArgs().get(0) instanceof  StringLiteralExpr ) {
//							this.setReturnObject(callExpr.getArgs().get(0).toString());
//							this.setReturnType("String");
//						}
//						else {
//							this.setReturnObject(callExpr.getArgs().get(0).toString());
//						}
//						
//					}
//					
//					System.out.println("");
//				}
//			}
//		}
		
		
		//elseIfConditionalStmt.setExpr(exprObject);
		
	}
	
	public JebraStmts getElseIfConditionalStmt() {
		return elseIfConditionalStmt;
	}

	public void setElseIfConditionalStmt(JebraStmts elseIfConditionalStmt) {
		this.elseIfConditionalStmt = elseIfConditionalStmt;
	}
}
