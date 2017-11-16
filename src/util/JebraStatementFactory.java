package util;

import java.util.ArrayList;

import com.parteek.jebra.JebraCollections;
import com.parteek.jebra.statements.JebraConditonalStmt;
import com.parteek.jebra.statements.JebraIfElseStmts;
import com.parteek.jebra.statements.JebraLoopStmts;
import com.parteek.jebra.statements.JebraStmts;
import com.parteek.jebra.statements.JebraTryStmt;

import japa.parser.ast.body.VariableDeclarator;
import japa.parser.ast.expr.AssignExpr;
import japa.parser.ast.expr.BinaryExpr;
import japa.parser.ast.expr.BooleanLiteralExpr;
import japa.parser.ast.expr.ConditionalExpr;
import japa.parser.ast.expr.Expression;
import japa.parser.ast.expr.IntegerLiteralExpr;
import japa.parser.ast.expr.MethodCallExpr;
import japa.parser.ast.expr.NameExpr;
import japa.parser.ast.expr.NullLiteralExpr;
import japa.parser.ast.expr.ObjectCreationExpr;
import japa.parser.ast.expr.VariableDeclarationExpr;
import japa.parser.ast.stmt.DoStmt;
import japa.parser.ast.stmt.ExpressionStmt;
import japa.parser.ast.stmt.ForStmt;
import japa.parser.ast.stmt.ForeachStmt;
import japa.parser.ast.stmt.IfStmt;
import japa.parser.ast.stmt.ReturnStmt;
import japa.parser.ast.stmt.Statement;
import japa.parser.ast.stmt.TryStmt;
import japa.parser.ast.stmt.WhileStmt;
import japa.parser.ast.type.ClassOrInterfaceType;
import japa.parser.ast.type.ReferenceType;
import japa.parser.ast.type.Type;

public class JebraStatementFactory {

	private static JebraStatementFactory factory = null;

	private JebraStatementFactory() {

	}

	public static JebraStatementFactory getInstance() {

		if (factory == null) {
			factory = new JebraStatementFactory();
		}

		return factory;
	}

	public void genrateStatements(Statement statement , ArrayList<JebraStmts> jebraStmts) {

		if (statement instanceof ExpressionStmt) {
			 CreateExpressionStmt(statement , jebraStmts);
		}
		else if (statement instanceof ForeachStmt) {
			CreateForEachStmts(statement, jebraStmts);
		}
		else if (statement instanceof ForStmt) {
			CreateForLoopStmts(statement, jebraStmts);
		}
		else if (statement instanceof WhileStmt) {
			CreateWhileLoopStmts(statement, jebraStmts);
		}
		else if (statement instanceof DoStmt) {
			CreateDoWhileLoopStmts(statement, jebraStmts);
		}
		else if (statement instanceof TryStmt) {
			CreateTryCatchStmts(statement, jebraStmts);
		}
		else if (statement instanceof IfStmt) {
			//this.createIfStmt(obj, "if", ifElse, null, isTestMethodCall,null);
			CreateIfElseStmts(statement, jebraStmts);
			//ifElse++;
		}
	}
	
	public void CreateIfElseStmts(Statement statement, ArrayList<JebraStmts> jebraStmtsArray) {
		
		if (statement instanceof IfStmt) {
			IfStmt obj = (IfStmt) statement;
			JebraIfElseStmts jebraStmts = new JebraIfElseStmts();

			if (obj != null) {
				jebraStmts.initObject(obj);
			}
			jebraStmtsArray.add(jebraStmts);
		}
	}
	
	public void CreateExpressionStmt(Statement statement, ArrayList<JebraStmts> jebraStmtsArray) {

		if (statement instanceof ExpressionStmt) {
			Expression expr = ((ExpressionStmt) statement).getExpression();
			JebraStmts jebraStmts = new JebraStmts();
			jebraStmts.setExpr(expr);
			//jebraStmts.setIfElseIndex(ifElseIndex);
			//jebraStmts.setTryCatchType(ifTryThenWriteType);
			if (expr instanceof VariableDeclarationExpr) {
				VariableDeclarationExpr declarationExpr = (VariableDeclarationExpr) expr;
				jebraStmts.setReturnType(declarationExpr.getType().toString());
				jebraStmts.setStmt(declarationExpr.toString());
				CheckForCollectionTypeStatements(declarationExpr, jebraStmts);

				for (VariableDeclarator var : declarationExpr.getVars()) {
					
					jebraStmts.setLeft(var.getId().toString());
					jebraStmts.setReturnObject(jebraStmts.getLeft());
					if (var.getInit() instanceof IntegerLiteralExpr) {
						IntegerLiteralExpr rightObject = (IntegerLiteralExpr) var.getInit();
						jebraStmts.setRight(rightObject.getValue());
					}
					if (var.getInit() instanceof MethodCallExpr) {
						MethodCallExpr rightObject = (MethodCallExpr) var.getInit();
						jebraStmts.setRight(rightObject.toString());
					}
					if (var.getInit() instanceof ObjectCreationExpr) {
						ObjectCreationExpr rightObject = (ObjectCreationExpr) var.getInit();
						jebraStmts.setRight(rightObject.toString());
					}
					if (var.getInit() instanceof NullLiteralExpr) {
						NullLiteralExpr rightObject = (NullLiteralExpr) var.getInit();
						jebraStmts.setRight("null");
					}
					if (var.getInit() instanceof ConditionalExpr) {
						
						JebraConditonalStmt conditonalStmt = new JebraConditonalStmt();
						conditonalStmt.InitObject(declarationExpr, var);
						jebraStmtsArray.add(conditonalStmt);
						return ; // because it is a different type of stmt (constional statement)
					}
				}
			} else if (expr instanceof MethodCallExpr) {
				MethodCallExpr rightObject = (MethodCallExpr) expr;
				jebraStmts.setRight(rightObject.toString());
				jebraStmts.setStmt(expr.toString());
			} else if (expr instanceof AssignExpr) {
				AssignExpr assignExpr = (AssignExpr) expr;
				jebraStmts.setLeft(assignExpr.getTarget().toString());
				jebraStmts.setRight(assignExpr.getValue().toString());
				jebraStmts.setOperator((assignExpr.getOperator().toString() == "assign") ? "equals" : "");
				jebraStmts.setStmt(jebraStmts.getLeft() + " " + jebraStmts.getOperator() + " " + jebraStmts.getRight());

			}
			// System.out.println(jebraStmts.getStmt());
			jebraStmts.setIndex(statement.getBeginLine());
			jebraStmtsArray.add(jebraStmts);
		} else if (statement instanceof ReturnStmt) {
			ReturnStmt returnStmt = (ReturnStmt) statement;
			JebraStmts jebraStmts = new JebraStmts();
			jebraStmts.setStmt(returnStmt.toString());
			jebraStmts.setIndex(statement.getBeginLine());
			//jebraStmts.setIfElseIndex(ifElseIndex);
			jebraStmtsArray.add(jebraStmts);
		}
	}

	public void CreateTryCatchStmts(Statement statement, ArrayList<JebraStmts> jebraStmts) {
		if (statement != null && statement instanceof TryStmt) {
			TryStmt tryStmt = (TryStmt) statement;
			JebraTryStmt jebraTryStmt = new JebraTryStmt();
			jebraTryStmt.initObject(tryStmt);
			jebraStmts.add(jebraTryStmt);
		}
	}

	public void CreateForEachStmts(Statement statement, ArrayList<JebraStmts> jebraStmts) {

		if (statement != null && statement instanceof ForeachStmt) {
			JebraLoopStmts jebraLoopStmts = new JebraLoopStmts();
			jebraLoopStmts.InitForEachLoopObject(statement);
			jebraStmts.add(jebraLoopStmts);
		}
	}

	public void CreateForLoopStmts(Statement statement, ArrayList<JebraStmts> jebraStmts) {
		if (statement != null && statement instanceof ForStmt) {
			JebraLoopStmts jebraLoopStmts = new JebraLoopStmts();
			jebraLoopStmts.InitForLoopObject(statement);
			jebraStmts.add(jebraLoopStmts);
		}
	}

	public void CreateWhileLoopStmts(Statement statement, ArrayList<JebraStmts> jebraStmts) {
		if (statement != null && statement instanceof WhileStmt) {
			JebraLoopStmts jebraLoopStmts = new JebraLoopStmts();
			jebraLoopStmts.InitWhileLoopObject(statement);
			jebraStmts.add(jebraLoopStmts);
		}
	}

	public void CreateDoWhileLoopStmts(Statement statement, ArrayList<JebraStmts> jebraStmts) {
		if (statement != null && statement instanceof DoStmt) {
			JebraLoopStmts jebraLoopStmts = new JebraLoopStmts();
			jebraLoopStmts.InitDoWhileLoopObject(statement);			
			jebraStmts.add(jebraLoopStmts);
		}
	}

	private void CheckForCollectionTypeStatements(Expression expr, JebraStmts jebraStmts) {
		boolean isPresent = false;
		if (expr instanceof VariableDeclarationExpr) {
			VariableDeclarationExpr declarationExpr = (VariableDeclarationExpr) expr;

			if (declarationExpr.getType() != null) {
				JebraCollections jebraCollections = new JebraCollections();
				if (declarationExpr.getType() instanceof ReferenceType) {
					ReferenceType returnType = (ReferenceType) declarationExpr.getType();
					if (returnType != null && returnType.getType() != null
							&& returnType.getType() instanceof ClassOrInterfaceType) {
						ClassOrInterfaceType interfaceType = (ClassOrInterfaceType) returnType.getType();
						isPresent = Utils.isCollectionType(interfaceType.getName());
						if (isPresent) {
							jebraCollections.setType(interfaceType.getName());
							for (Type type : interfaceType.getTypeArgs()) {
								jebraCollections.setReturnType(type.toString());
							}
						}
					}
				}
				if (isPresent) {
					if (declarationExpr.getVars() != null) {
						for (VariableDeclarator declarator : declarationExpr.getVars()) {
							jebraCollections.setObjName(declarator.getId().toString());

							if (declarator.getInit() != null && declarator.getInit() instanceof MethodCallExpr) {
								MethodCallExpr callExpr = (MethodCallExpr) declarator.getInit();
								jebraCollections.setMethodCallName(callExpr.getName());
							}
						}
					}

					jebraStmts.addAnyCollectionObject(jebraCollections);
				}
			}
		}
	}
}
