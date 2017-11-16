package com.parteek.jebra.statements;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.parteek.jebra.JebraCollections;

import japa.parser.ast.body.VariableDeclarator;
import japa.parser.ast.expr.BinaryExpr;
import japa.parser.ast.expr.BooleanLiteralExpr;
import japa.parser.ast.expr.ConditionalExpr;
import japa.parser.ast.expr.MethodCallExpr;
import japa.parser.ast.expr.NameExpr;
import japa.parser.ast.expr.NullLiteralExpr;
import japa.parser.ast.expr.VariableDeclarationExpr;
import util.Utils;

public class JebraConditonalStmt extends JebraStmts {

	private String mainReturnType;
	private String mainReturnObjectName;

	private ArrayList<JebraStmts> conditionalStmt;
	private ArrayList<JebraStmts> thenStatement;
	private ArrayList<JebraStmts> elseStmt;

	public JebraConditonalStmt() {

		setConditionalStmt(new ArrayList<JebraStmts>());
		setThenStatement(new ArrayList<JebraStmts>());
		setElseStmt(new ArrayList<JebraStmts>());
	}

	public void InitObject(VariableDeclarationExpr declarationExpr,VariableDeclarator var) {
		this.setMainReturnType(declarationExpr.getType().toString());
		this.setMainReturnObjectName(var.getId().toString());
		this.setStmt(declarationExpr.toString());
		this.setExpr(declarationExpr);
		JebraCollections jebraCollections = null;
		if(Utils.isCollectionType(declarationExpr.getType().toString())) {
			jebraCollections = new JebraCollections();
			//jebraCollections.setMethodCallName(callExpr.getScope().toString());
			jebraCollections.setObjName(this.getMainReturnObjectName());
			jebraCollections.setReturnType(this.getMainReturnType());
			jebraCollections.setType("List");
		}
		ConditionalExpr rightObject = (ConditionalExpr) var.getInit();						
		if (rightObject.getCondition() != null) {
			if (rightObject.getCondition() instanceof BinaryExpr) {
				JebraStmts stmts = new JebraStmts();
				BinaryExpr binaryExpr = (BinaryExpr) rightObject.getCondition();

				stmts.setLeft(binaryExpr.getLeft().toString());
				stmts.setOperator(binaryExpr.getOperator().toString());
				stmts.setRight(binaryExpr.getRight().toString());
				stmts.setStmt(binaryExpr.toString());
				stmts.setExpr(binaryExpr);
				if ((binaryExpr.getLeft() instanceof NullLiteralExpr
						|| binaryExpr.getRight() instanceof NullLiteralExpr)
						&& ("null".equals(binaryExpr.getLeft().toString())
								|| "null".equals(binaryExpr.getRight().toString()))) {
					stmts.setReturnObject("null");
					stmts.setReturnType("NULL_OBJECT");
				} else if ((binaryExpr.getLeft() instanceof BooleanLiteralExpr
						|| binaryExpr.getRight() instanceof BooleanLiteralExpr)
						&& (Utils.convertStringToBoolean(binaryExpr.getLeft().toString())
								|| Utils.convertStringToBoolean(binaryExpr.getRight().toString()))) {
					if(binaryExpr.getLeft() instanceof BooleanLiteralExpr) {
						stmts.setReturnObject(binaryExpr.getLeft().toString());
					}
					else if(binaryExpr.getRight() instanceof BooleanLiteralExpr) {
						stmts.setReturnObject(binaryExpr.getRight().toString());
					}
					stmts.setReturnType("Boolean");
				}
				
				//stmts.addAnyCollectionObject(jebraCollections);
				stmts.addLineNumber(binaryExpr.getBeginLine());
				this.addConditionalStmt(stmts);

			}
			if (rightObject.getCondition() instanceof BooleanLiteralExpr) {
				JebraStmts stmts = new JebraStmts();
				BooleanLiteralExpr binaryExpr = (BooleanLiteralExpr) rightObject.getCondition();
				stmts.setLeft("boolean");
				stmts.setOperator("equals");
				stmts.setRight(((Boolean) binaryExpr.getValue()).toString());
				stmts.setStmt("if_" + ((Boolean) binaryExpr.getValue()).toString());
				stmts.setExpr(binaryExpr);
				stmts.addLineNumber(binaryExpr.getBeginLine());
				this.addConditionalStmt(stmts);
			}
		}
		if (rightObject.getThenExpr() != null) {
			this.thenStatement.addAll(conditionalStmt);
			
			if (rightObject.getThenExpr() instanceof NameExpr) {
			//	JOptionPane.showMessageDialog(null, "Need code here jebraCollectuinStmt.java L-66\n\nContact dev");
			}
			if(rightObject.getThenExpr() instanceof MethodCallExpr) {
				MethodCallExpr callExpr = (MethodCallExpr) rightObject.getThenExpr();
				JebraStmts thenStatement = new JebraStmts();
				thenStatement.setReturnType(declarationExpr.getType().toString());
				thenStatement.setLeft(var.getId().toString());
				thenStatement.setExpr(callExpr);
				thenStatement.setRight(callExpr.toString());
				thenStatement.setStmt(callExpr.toString());
				thenStatement.setReturnObject(this.mainReturnObjectName);
				thenStatement.setReturnType(this.mainReturnType);
				
				JebraCollections collections = null;
				try {
					collections = (JebraCollections) jebraCollections.clone();
				} catch (CloneNotSupportedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(collections != null) {
					collections.setMethodCallName(callExpr.toString());
				}
				thenStatement.addAnyCollectionObject(collections);
				this.addThenStatement(thenStatement);
				
			}
			if (rightObject.getThenExpr() instanceof NullLiteralExpr) {
				NullLiteralExpr nullObject = (NullLiteralExpr) rightObject.getThenExpr();
				JebraStmts thenStatement = new JebraStmts();
				thenStatement.setReturnType(declarationExpr.getType().toString());
				thenStatement.setLeft(var.getId().toString());
				thenStatement.setExpr(nullObject);
				thenStatement.setRight("null");
				thenStatement.setStmt(nullObject.toString());
				this.addThenStatement(thenStatement);
			}
		}
		
		if (rightObject.getElseExpr() != null) {
			
			this.elseStmt.addAll(conditionalStmt);
			
			if (rightObject.getElseExpr() instanceof NameExpr) {
			//	JOptionPane.showMessageDialog(null, "Need code here jebraCollectuinStmt.java L-93\n\nContact dev");
			}
			if(rightObject.getElseExpr() instanceof MethodCallExpr) {
				MethodCallExpr callExpr = (MethodCallExpr) rightObject.getElseExpr();
				JebraStmts elseStmt = new JebraStmts();
				elseStmt.setReturnType(declarationExpr.getType().toString());
				elseStmt.setLeft(var.getId().toString());
				elseStmt.setExpr(callExpr);
				elseStmt.setRight(callExpr.toString());
				elseStmt.setStmt(callExpr.toString());
				elseStmt.setReturnObject(this.mainReturnObjectName);
				elseStmt.setReturnType(this.mainReturnType);
				JebraCollections collections = null;
				try {
					collections = (JebraCollections) jebraCollections.clone();
				} catch (CloneNotSupportedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(collections != null) {
					collections.setMethodCallName(callExpr.toString());
				}
				elseStmt.addAnyCollectionObject(collections);
				this.addElseStmt(elseStmt);
				
			}
			if (rightObject.getElseExpr() instanceof NullLiteralExpr) {
				NullLiteralExpr nullObject = (NullLiteralExpr) rightObject.getElseExpr();
				JebraStmts elseStmt = new JebraStmts();
				elseStmt.setReturnType(declarationExpr.getType().toString());
				elseStmt.setLeft(var.getId().toString());
				elseStmt.setExpr(nullObject);
				elseStmt.setRight("null");
				elseStmt.setStmt(nullObject.toString());
				this.addElseStmt(elseStmt);
			}
		}
	}
	
	public String getMainReturnType() {
		return mainReturnType;
	}

	public void setMainReturnType(String mainReturnType) {
		this.mainReturnType = mainReturnType;
	}

	public String getMainReturnObjectName() {
		return mainReturnObjectName;
	}

	public void setMainReturnObjectName(String mainReturnObjectName) {
		this.mainReturnObjectName = mainReturnObjectName;
	}

	public ArrayList<JebraStmts> getConditionalStmt() {
		return conditionalStmt;
	}

	public void setConditionalStmt(ArrayList<JebraStmts> conditionalStmt) {
		this.conditionalStmt = conditionalStmt;
	}
	
	public ArrayList<JebraStmts> getThenStatement() {
		return thenStatement;
	}

	public void setThenStatement(ArrayList<JebraStmts> thenStatement) {
		this.thenStatement = thenStatement;
	}

	public ArrayList<JebraStmts> getElseStmt() {
		return elseStmt;
	}

	public void setElseStmt(ArrayList<JebraStmts> elseStmt) {
		this.elseStmt = elseStmt;
	}

	public void addConditionalStmt(JebraStmts conditionalStmt) {
		this.conditionalStmt.add(conditionalStmt);
	}

	public void addThenStatement(JebraStmts thenStatement) {
		this.thenStatement.add(thenStatement);
	}
	
	public void addElseStmt(JebraStmts elseStmt) {
		this.elseStmt.add(elseStmt);
	}
}
