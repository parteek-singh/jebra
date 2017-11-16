package com.parteek.jebra.methods;

import java.util.ArrayList;
import java.util.List;

import com.parteek.jebra.JebraParameters;
import com.parteek.jebra.MainClassStructure;
import com.parteek.jebra.statements.JebraStmts;

import japa.parser.ast.body.MethodDeclaration;
import japa.parser.ast.stmt.Statement;

public abstract class MethodParent {
	
	private List<Statement> bodyStmts;
	protected ArrayList<JebraStmts> jebraStmts;
	protected List<JebraParameters> jebraParameters;
	private ArrayList<Integer> lineNumbers;
	private List<String> ifBodyStmtsString;
	private String name;
	private String returnType;
	private String className;
	private MainClassStructure myClass;
	
	private MethodDeclaration myMethod;
	private String methodDeclationStmt;
	
	
	
	public MethodParent() {
		bodyStmts = new ArrayList<Statement>();
		jebraStmts = new ArrayList<JebraStmts>();
		jebraParameters = new ArrayList<JebraParameters>();
		lineNumbers = new ArrayList<Integer>();
		ifBodyStmtsString = new ArrayList<String>();
	}
	
	public List<JebraStmts> getJebraStmts() {
		return jebraStmts;
	}

	public void setJebraStmts(ArrayList<JebraStmts> jebraStmts) {
		this.jebraStmts = jebraStmts;
	}

	public void addStatement(JebraStmts obj) {
		jebraStmts.add(obj);
	}

	public void addParameters(JebraParameters obj) {
		jebraParameters.add(obj);
	}

	public List<JebraParameters> getJebraParameters() {
		return jebraParameters;
	}

	public void setJebraParameters(List<JebraParameters> jebraParameters) {
		this.jebraParameters = jebraParameters;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getReturnType() {
		return returnType;
	}

	public void setReturnType(String returnType) {
		this.returnType = returnType;
	}
	
	public void setClassName(String className) {
		this.className = className;
	}

	public String getClassName() {
		return className;
	}

	public void setMyClass(MainClassStructure myClass) {
		this.myClass = myClass;
	}

	public MainClassStructure getMyClass() {
		return myClass;
	}

	public MethodDeclaration getMyMethod() {
		return myMethod;
	}

	public void setMyMethod(MethodDeclaration myMethod) {
		this.myMethod = myMethod;
	}
	
	public void setMethodDeclationStmt(String methodDeclationStmt) {
		this.methodDeclationStmt = methodDeclationStmt;
	}

	public String getMethodDeclationStmt() {
		return methodDeclationStmt;
	}
	
	public String toString() {
        return methodDeclationStmt;
    }
	
	public void setIfBodyStmtsString(List<String> ifBodyStmtsString) {
		this.ifBodyStmtsString = ifBodyStmtsString;
	}

	public List<String> getIfBodyStmtsString() {
		return ifBodyStmtsString;
	}
	
	public List<Statement> getBodyStmts() {
		return bodyStmts;
	}

	public void setBodyStmts(List<Statement> bodyStmts) {
		this.bodyStmts = bodyStmts;
	}

	public ArrayList<Integer> getLineNumbers() {
		lineNumbers.clear();
		for (JebraStmts statement : this.jebraStmts) {
			if(statement!=null) {
				lineNumbers.add(statement.getIndex());
			}
		}
		return lineNumbers;
	}

	public void setLineNumbers(ArrayList<Integer> lineNumbers) {
		this.lineNumbers = lineNumbers;
	}
	
	public void addLineNumbers(Integer lineNumbers) {
		this.lineNumbers.add(lineNumbers);
	}


	
//	public void createTryCatchStmts(Statement statement ,Integer ifElse, boolean isTestMethodCall) {
//		if (statement!=null && statement instanceof TryStmt) {
//			TryStmt tryStmt = (TryStmt) statement;
//			JebraTryStmt jebraTryStmt = new JebraTryStmt();
//			jebraTryStmt.setTryOrignalStmt(tryStmt);
//			jebraTryStmt.setTryBody(tryStmt.getTryBlock());
//			jebraTryStmt.setFinallyBody(tryStmt.getFinallyBlock());
//			jebraTryStmt.setIfElseIndex(ifElse);
//			for (CatchClause clause : tryStmt.getCatchs()) {
//				if(clause != null) {
//					jebraTryStmt.addCatchBody(clause);
//				}
//			}
//			
//			jebraTryStmt.setLineStart(tryStmt.getBeginLine());
//			jebraTryStmt.setLineEnd(tryStmt.getEndLine());
//			
//			addStatement(jebraTryStmt);
//			
//			// start generating try block
//			if(jebraTryStmt.getTryBody() != null && jebraTryStmt.getTryBody() instanceof BlockStmt) {
//				BlockStmt blockStmt = (BlockStmt) jebraTryStmt.getTryBody();
//				genrateStatementsFromLoop(blockStmt, ifElse, isTestMethodCall,Constants.TRY);
//			}
//			
//			
//			// start generating catch block
//			if(jebraTryStmt.getCatchBody() != null) {
//				for (CatchClause catchClause : jebraTryStmt.getCatchBody()) {
//					if(catchClause != null &&  catchClause.getCatchBlock() != null && catchClause.getCatchBlock() instanceof BlockStmt) {
//						BlockStmt blockStmt = (BlockStmt) catchClause.getCatchBlock();
//						genrateStatementsFromLoop(blockStmt, ifElse, isTestMethodCall,Constants.CATCH);
//					}
//				}
//			}
//	
//			// start generating try block
//			if(jebraTryStmt.getFinallyBody()!= null && jebraTryStmt.getFinallyBody() instanceof BlockStmt) {
//				BlockStmt blockStmt = (BlockStmt) jebraTryStmt.getFinallyBody();
//				genrateStatementsFromLoop(blockStmt, ifElse, isTestMethodCall,Constants.FINALLY);
//			}
//		}
//	}
//	public void createWhileLoopStmts(Statement statement ,Integer ifElse, boolean isTestMethodCall) {
//		if (statement!=null && statement instanceof WhileStmt) {
//			WhileStmt whileStmt = (WhileStmt) statement;
//			JebraLoopStmts jebraLoopStmts = new JebraLoopStmts();
//			jebraLoopStmts.setWhileStmt(whileStmt);
//
//			if(whileStmt.getBody()!=null && whileStmt.getBody() instanceof BlockStmt) {
//				BlockStmt blockStmt = (BlockStmt) whileStmt.getBody();
//				if (blockStmt != null && blockStmt.getStmts() != null) {
//					for (Statement statement1 : blockStmt.getStmts()) {
//						jebraLoopStmts.getBodyStmts().add(statement1.toString());
//					}
//				}
//			}
//			
//			int beginLine = statement.getBeginLine();
//			int endLine = statement.getEndLine();
//			for (int i = beginLine; i <= endLine; i++) {
//				jebraLoopStmts.addLineNumber(i);
//			}
//			addStatement(jebraLoopStmts);
//			
//			if(whileStmt.getBody()!=null && whileStmt.getBody() instanceof BlockStmt) {
//				BlockStmt blockStmt = (BlockStmt) whileStmt.getBody();
//				genrateStatementsFromLoop(blockStmt, ifElse, isTestMethodCall,null);
//			}
//		}
//	}
//	public void createDoWhileLoopStmts(Statement statement ,Integer ifElse, boolean isTestMethodCall) {
//		if (statement!=null && statement instanceof DoStmt) {
//			DoStmt doWhileStmt = (DoStmt) statement;
//			JebraLoopStmts jebraLoopStmts = new JebraLoopStmts();
//			jebraLoopStmts.setDoWhileStmt(doWhileStmt);
//
//			if(doWhileStmt.getBody()!=null && doWhileStmt.getBody() instanceof BlockStmt) {
//				BlockStmt blockStmt = (BlockStmt) doWhileStmt.getBody();
//				if (blockStmt != null && blockStmt.getStmts() != null) {
//					for (Statement statement1 : blockStmt.getStmts()) {
//						jebraLoopStmts.getBodyStmts().add(statement1.toString());
//					}
//				}
//			}
//			
//			int beginLine = statement.getBeginLine();
//			int endLine = statement.getEndLine();
//			for (int i = beginLine; i <= endLine; i++) {
//				jebraLoopStmts.addLineNumber(i);
//			}
//			addStatement(jebraLoopStmts);
//			
//			if(doWhileStmt.getBody()!=null && doWhileStmt.getBody() instanceof BlockStmt) {
//				BlockStmt blockStmt = (BlockStmt) doWhileStmt.getBody();
//				genrateStatementsFromLoop(blockStmt, ifElse, isTestMethodCall,null);
//			}
//		}
//	}
//	public void createForLoopStmts(Statement statement ,Integer ifElse, boolean isTestMethodCall) {
//		if (statement!=null && statement instanceof ForStmt) {
//			ForStmt forStmt = (ForStmt) statement;
//			JebraLoopStmts jebraLoopStmts = new JebraLoopStmts();
//			jebraLoopStmts.setForStmt(forStmt);
//
//			if(forStmt.getBody()!=null && forStmt.getBody() instanceof BlockStmt) {
//				BlockStmt blockStmt = (BlockStmt) forStmt.getBody();
//				if (blockStmt != null && blockStmt.getStmts() != null) {
//					for (Statement statement1 : blockStmt.getStmts()) {
//						jebraLoopStmts.getBodyStmts().add(statement1.toString());
//					}
//				}
//			}
//			
//			int beginLine = statement.getBeginLine();
//			int endLine = statement.getEndLine();
//			for (int i = beginLine; i <= endLine; i++) {
//				jebraLoopStmts.addLineNumber(i);
//			}
//			addStatement(jebraLoopStmts);
//			
//			if(forStmt.getBody()!=null && forStmt.getBody() instanceof BlockStmt) {
//				BlockStmt blockStmt = (BlockStmt) forStmt.getBody();
//				genrateStatementsFromLoop(blockStmt, ifElse, isTestMethodCall , null);
//			}
//		}
//	}
//	public void createForEachStmts(Statement statement ,Integer ifElse, boolean isTestMethodCall) {
//		
//		if (statement!=null && statement instanceof ForeachStmt) {
//			ForeachStmt foreachStmt = (ForeachStmt) statement;
//			JebraLoopStmts jebraLoopStmts = new JebraLoopStmts();
//			jebraLoopStmts.setForeachStmt(foreachStmt);
//
//			if (foreachStmt.getVariable() instanceof VariableDeclarationExpr) {
//				VariableDeclarationExpr declarationExpr = (VariableDeclarationExpr) foreachStmt.getVariable();
//				if(declarationExpr!=null) {
//					jebraLoopStmts.setReturnType(declarationExpr.getType().toString());
//					for (VariableDeclarator var : declarationExpr.getVars()) {
//						jebraLoopStmts.setReturnObject(var.getId().toString());
//					}
//				}
//			}
//			
//			
//			if(foreachStmt.getBody()!=null && foreachStmt.getBody() instanceof BlockStmt) {
//				BlockStmt blockStmt = (BlockStmt) foreachStmt.getBody();
//				if (blockStmt != null) {
//					for (Statement statement1 : blockStmt.getStmts()) {
//						jebraLoopStmts.getBodyStmts().add(statement1.toString());
//					}
//				}
//			}
//			
//			int beginLine = statement.getBeginLine();
//			int endLine = statement.getEndLine();
//			for (int i = beginLine; i <= endLine; i++) {
//				jebraLoopStmts.addLineNumber(i);
//			}
//			addStatement(jebraLoopStmts);
//			
//			if(foreachStmt.getBody()!=null && foreachStmt.getBody() instanceof BlockStmt) {
//				BlockStmt blockStmt = (BlockStmt) foreachStmt.getBody();
//				genrateStatementsFromLoop(blockStmt, ifElse, isTestMethodCall,null);
//			}
//			
//		}
//	}
//	public void genrateStatementsFromLoop(BlockStmt blockStmt ,Integer ifElse, boolean isTestMethodCall, String ifTryThenWriteType) {
//		
//		for (Statement stmsObj : blockStmt.getStmts()) {
//			if (stmsObj instanceof ExpressionStmt) {
//				createExpressionStmt(stmsObj,ifElse , isTestMethodCall,ifTryThenWriteType);
//			}
//			if (stmsObj instanceof ReturnStmt) {
//				createExpressionStmt(stmsObj,ifElse, isTestMethodCall,ifTryThenWriteType);
//			}
//			if (stmsObj instanceof TryStmt) {
//				createTryCatchStmts(stmsObj, ++ifElse, isTestMethodCall);
//			}
//			if (stmsObj instanceof IfStmt) {
//				IfStmt obj = (IfStmt) stmsObj;
//				//createIfStmt(obj,"if",ifElse,null ,isTestMethodCall,ifTryThenWriteType);
//			}
//		}
//	}
//	public void createExpressionStmt(Statement statement,int ifElseIndex, boolean isTestMethodCall,String ifTryThenWriteType){
//		
//		if (statement instanceof ExpressionStmt) {
//			Expression expr = ((ExpressionStmt) statement).getExpression();
//			JebraStmts jebraStmts = new JebraStmts();
//			jebraStmts.setExpr(expr);
//			jebraStmts.setIfElseIndex(ifElseIndex);
//			jebraStmts.setTryCatchType(ifTryThenWriteType);
//			if (expr instanceof VariableDeclarationExpr) {
//				VariableDeclarationExpr declarationExpr = (VariableDeclarationExpr) expr;
//				jebraStmts.setReturnType(declarationExpr.getType().toString());
//				
//				checkForCollectionTypeStatements(declarationExpr, jebraStmts);
//				if(isTestMethodCall) {
//					System.out.println("Parteek");
//				}
//				
//				for (VariableDeclarator var : declarationExpr.getVars()) {
//					jebraStmts.setStmt(declarationExpr.toString());
//					jebraStmts.setLeft(var.getId().toString());
//					
//					if (var.getInit() instanceof IntegerLiteralExpr) {
//						IntegerLiteralExpr rightObject = (IntegerLiteralExpr) var.getInit();
//						jebraStmts.setRight(rightObject.getValue());
//					}
//					if (var.getInit() instanceof MethodCallExpr) {
//						MethodCallExpr rightObject = (MethodCallExpr) var.getInit();
//						jebraStmts.setRight(rightObject.toString());
//					}
//					if (var.getInit() instanceof ObjectCreationExpr) {
//						ObjectCreationExpr rightObject = (ObjectCreationExpr) var.getInit();
//						jebraStmts.setRight(rightObject.toString());
//					}
//					if (var.getInit() instanceof NullLiteralExpr) {
//						NullLiteralExpr rightObject = (NullLiteralExpr) var.getInit();
//						jebraStmts.setRight("null");
//					}
//				}
//			}
//			else if (expr instanceof MethodCallExpr) {
//				MethodCallExpr rightObject = (MethodCallExpr) expr;
//				jebraStmts.setRight(rightObject.toString());
//				jebraStmts.setStmt(expr.toString());
//			}
//			else if(expr instanceof AssignExpr){
//				AssignExpr assignExpr = (AssignExpr) expr;
//				jebraStmts.setLeft(assignExpr.getTarget().toString());
//				jebraStmts.setRight(assignExpr.getValue().toString());
//				jebraStmts.setOperator((assignExpr.getOperator().toString() == "assign") ? "equals" : "");
//				jebraStmts.setStmt(jebraStmts.getLeft() + " "+jebraStmts.getOperator() + " "+ jebraStmts.getRight());
//				
//			}
//			//System.out.println(jebraStmts.getStmt());
//			jebraStmts.setIndex(statement.getBeginLine());
//			addStatement(jebraStmts);
//		}
//		else if(statement instanceof ReturnStmt){
//			ReturnStmt returnStmt= (ReturnStmt)statement;
//			JebraStmts jebraStmts = new JebraStmts();
//			jebraStmts.setStmt(returnStmt.toString());
//			jebraStmts.setIndex(statement.getBeginLine());
//			jebraStmts.setIfElseIndex(ifElseIndex);
//			addStatement(jebraStmts);
//		}
//		
//	}
	
//	public void createIfStmt(IfStmt obj,String type,int ifElseIndex, Statement statement, boolean isTestMethodCall , String ifTryThenWriteType){
//		JebraIfStmts jebraStmts = new JebraIfStmts();
//		jebraStmts.setIfStmts(true);
//		jebraStmts.setIfStmtType(type);
//		jebraStmts.setIfElseIndex(ifElseIndex);
//		jebraStmts.setTryCatchType(ifTryThenWriteType);
//		if(obj!=null){
//			jebraStmts.setIndex(obj.getBeginLine());
//			jebraStmts.setIfStmts(obj);
//
//		}else if(statement !=null){
//			jebraStmts.setIndex(statement.getBeginLine());
//		}
//		if(jebraStmts.getIfStmts() !=null && jebraStmts.getIfStmts().getThenStmt()!=null){
//			int beginLine = jebraStmts.getIfStmts().getThenStmt().getBeginLine();
//			int endLine = jebraStmts.getIfStmts().getThenStmt().getEndLine();
//			for (int i = beginLine; i <= endLine; i++) {
//				jebraStmts.addLineNumber(i);
//			}
//			if (jebraStmts.getIfStmts().getThenStmt() instanceof BlockStmt) {
//				
//				BlockStmt blockStmt = (BlockStmt) jebraStmts.getIfStmts().getThenStmt();
//				if (blockStmt != null) {
//					for (Statement statement1 : blockStmt.getStmts()) {
//						jebraStmts.getBodyStmts().add(statement1.toString());
//					}
//				}
//			}
//		}
//		else if(statement!=null){
//			int beginLine = statement.getBeginLine();
//			int endLine = statement.getEndLine();
//			for (int i = beginLine; i <= endLine; i++) {
//				jebraStmts.addLineNumber(i);
//			}
//			
//		}
//		
//		if(obj!=null){
//			if (obj.getCondition() != null) {
//				if (obj.getCondition() instanceof BinaryExpr) {
//					BinaryExpr expr = (BinaryExpr) obj.getCondition();
//					jebraStmts.setLeft(expr.getLeft().toString());
//					jebraStmts.setOperator(expr.getOperator().toString());
//					jebraStmts.setRight(expr.getRight().toString());
//					jebraStmts.setStmt(expr.toString());
//				
//				}
//				if(obj.getCondition() instanceof BooleanLiteralExpr){
//					BooleanLiteralExpr expr = (BooleanLiteralExpr) obj.getCondition();
//					jebraStmts.setLeft("boolean");
//					jebraStmts.setOperator("equals");
//					jebraStmts.setRight(((Boolean)expr.getValue()).toString());
//					jebraStmts.setStmt("if_"+((Boolean)expr.getValue()).toString());
//				}
//			}
//		}
//		if(type=="else"){
//			jebraStmts.setStmt("else");
//		}
//		
//		addStatement(jebraStmts);
//		if(obj!=null){
//			if (obj.getThenStmt() instanceof BlockStmt) {
//				
//				BlockStmt blockStmt = (BlockStmt) obj.getThenStmt();
//				if (blockStmt != null) {
//					for (Statement statement1 : blockStmt.getStmts()) {
//						if (statement1 instanceof ExpressionStmt) {
//							createExpressionStmt(statement1 ,ifElseIndex , isTestMethodCall , null);
//						}
//						if (statement1 instanceof ReturnStmt) {
//							createExpressionStmt(statement1 ,ifElseIndex , isTestMethodCall , null);
//						}
//					}
//				}
//			}
//		}
		//System.out.println("}");
	//	checkForElseIfStmt(obj , ifElseIndex , isTestMethodCall);
//	}
//	public void checkForElseIfStmt(IfStmt obj,int ifElseIndex , boolean isTestMethodCall){
//		
//		if (obj!=null && obj.getThenStmt() != null) {
//			if(obj.getElseStmt()!=null){
//				if(obj.getElseStmt() instanceof IfStmt){
//					createIfStmt((IfStmt)obj.getElseStmt(),"elseif",ifElseIndex,null, isTestMethodCall , null);
//				}
//				else if(obj.getElseStmt() instanceof BlockStmt){
//					BlockStmt stmt = (BlockStmt)obj.getElseStmt();
//					if(stmt!=null && stmt.getStmts()!=null){
//						boolean elseFlag =false;
//						for (Statement statement : stmt.getStmts()) {
//							if(statement!=null){
//								if(statement instanceof IfStmt){
//									createIfStmt((IfStmt)statement,"else", ifElseIndex , null , isTestMethodCall ,null);
//								}
//								else if(statement instanceof ExpressionStmt){
//									if(!elseFlag)
//										createIfStmt(null , "else" , ifElseIndex , stmt , isTestMethodCall , null);
//									elseFlag=true;
//									createExpressionStmt(statement,ifElseIndex,isTestMethodCall , null);
//								}
//							}
//						}
//					}
//					//System.out.println();
//				}
//			}
//		}
//	}
	
//	private void checkForCollectionTypeStatements(Expression expr, JebraStmts jebraStmts) {
//		boolean isPresent = false;
//		if (expr instanceof VariableDeclarationExpr) {
//			VariableDeclarationExpr declarationExpr = (VariableDeclarationExpr) expr;
//
//			if (declarationExpr.getType() != null) {
//				JebraCollections jebraCollections = new JebraCollections();
//				if (declarationExpr.getType() instanceof ReferenceType) {
//					ReferenceType returnType = (ReferenceType) declarationExpr.getType();
//					if (returnType != null && returnType.getType() != null
//							&& returnType.getType() instanceof ClassOrInterfaceType) {
//						ClassOrInterfaceType interfaceType = (ClassOrInterfaceType) returnType.getType();
//						isPresent = Utils.isCollectionType(interfaceType.getName());
//						if (isPresent) {
//							jebraCollections.setType(interfaceType.getName());
//							for (Type type : interfaceType.getTypeArgs()) {
//								jebraCollections.setReturnType(type.toString());
//							}
//						}
//					}
//				}
//				if (isPresent) {
//					if (declarationExpr.getVars() != null) {
//						for (VariableDeclarator declarator : declarationExpr.getVars()) {
//							jebraCollections.setObjName(declarator.getId().toString());
//							
//							if(declarator.getInit()!=null &&  declarator.getInit() instanceof MethodCallExpr) {
//								MethodCallExpr callExpr = (MethodCallExpr) declarator.getInit();
//								jebraCollections.setMethodCallName(callExpr.getName());
//							}
//						}
//					}
//
//					jebraStmts.addAnyCollectionObject(jebraCollections);
//				}
//			}
//		}
//	}
	
	
	
	
//	public void genrateStatements(Statement statement,int ifElse , Boolean isTestMethodCall ) {
//
//		if (statement instanceof ExpressionStmt) {
//			this.createExpressionStmt(statement, ifElse , isTestMethodCall , null);
//		}
//		else if (statement instanceof ForeachStmt) {
//			this.createForEachStmts(statement,ifElse, isTestMethodCall);
//		}
//		else if (statement instanceof ForStmt) {
//			this.createForLoopStmts(statement,ifElse, isTestMethodCall);
//		}
//		else if (statement instanceof WhileStmt) {
//			this.createWhileLoopStmts(statement,ifElse, isTestMethodCall);
//		}
//		else if (statement instanceof DoStmt) {
//			this.createDoWhileLoopStmts(statement,ifElse, isTestMethodCall);
//		}
//		else if (statement instanceof TryStmt) {
//			this.createTryCatchStmts(statement,ifElse, isTestMethodCall);
//			ifElse++;
//		}
//		else if (statement instanceof IfStmt) {
//			IfStmt obj = (IfStmt) statement;
//			//this.createIfStmt(obj, "if", ifElse, null, isTestMethodCall,null);
//			//ifElse++;
//		}
	
//	}
	
}
