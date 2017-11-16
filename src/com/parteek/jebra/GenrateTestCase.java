package com.parteek.jebra;

import japa.parser.ast.body.VariableDeclarator;
import japa.parser.ast.expr.AssignExpr;
import japa.parser.ast.expr.BinaryExpr;
import japa.parser.ast.expr.BooleanLiteralExpr;
import japa.parser.ast.expr.MethodCallExpr;
import japa.parser.ast.expr.NameExpr;
import japa.parser.ast.expr.ObjectCreationExpr;
import japa.parser.ast.expr.ThisExpr;
import japa.parser.ast.expr.VariableDeclarationExpr;
import japa.parser.ast.stmt.BlockStmt;
import japa.parser.ast.stmt.ExpressionStmt;
import japa.parser.ast.stmt.IfStmt;
import japa.parser.ast.stmt.Statement;
import japa.parser.ast.type.ClassOrInterfaceType;
import japa.parser.ast.type.ReferenceType;
import japa.parser.ast.type.Type;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;

import javax.swing.JOptionPane;

import com.parteek.jebra.methods.TestMethodStructure;
import com.parteek.jebra.statements.JebraIfElseStmts;
import com.parteek.jebra.statements.JebraLoopStmts;
import com.parteek.jebra.statements.JebraStmts;

import util.Constants;
import util.Utils;

public class GenrateTestCase {

	private static GenrateTestCase genrateTestCase =null;
	//private Set<TestMethodStructure> testMethods;
	private TestMethodStructure currentTestMethods;
//	private Map<String, String> mockedObjects = null;
//	private Map<String, ArrayList<String>> mockedRefernce = null;
	
	private GenrateTestCase(){
		
	}
	
	public static GenrateTestCase getInstance() {
		if(genrateTestCase==null){
			genrateTestCase = new GenrateTestCase();
		}
		return genrateTestCase;
	}

	
	private String getReturnType(JebraStmts stmts) {
		if(stmts != null) {
			String dummyObject = "'Please add you own data'";
			String returnObj = stmts.getReturnObject();
			String returnType = stmts.getReturnType();
			if("NULL_OBJECT".equals(returnType)) {
				if(stmts.getConditionType().equals(Constants.PASS)) {
					if(Utils.NeedToMatchOrNot(stmts.getOperator())) {
						return returnObj;
					}
					else {
						return dummyObject; // need to fix it
					}
				}
				else {
					return dummyObject; // need to fix it
				}
			}
			else if("INSTANCE_OF".equals(returnType)) {
				System.out.println("");
			}
			else if("int".equalsIgnoreCase(returnType) || "integer".equalsIgnoreCase(returnType)) {
				if(stmts.getConditionType().equals(Constants.PASS)) {
					if(Utils.NeedToMatchOrNot(stmts.getOperator())) {
						return returnObj;
					}
					else {
						
					}
				}
				else {
					Random random = new Random();
					Integer number = random.nextInt(50) + 1;
					return number.toString();
				}
			}
			else if("double".equalsIgnoreCase(returnType)) {
				if(stmts.getConditionType().equals(Constants.PASS)) {
					if(Utils.NeedToMatchOrNot(stmts.getOperator())) {
						return returnObj;
					}
					else {
						
					}
				}
				else {
					Random random = new Random();
					Double number = random.nextDouble();
					return number.toString();
				}
			}
			else if("long".equalsIgnoreCase(returnType)) {
				if(stmts.getConditionType().equals(Constants.PASS)) {
					if(Utils.NeedToMatchOrNot(stmts.getOperator())) {
						return returnObj;
					}
					else {
						
					}
				}
				else {
					Random random = new Random();
					Long number = random.nextLong();
					return number.toString();
				}
			}
			else if("float".equalsIgnoreCase(returnType)) {
				if(stmts.getConditionType().equals(Constants.PASS)) {
					if(Utils.NeedToMatchOrNot(stmts.getOperator())) {
						return returnObj;
					}
					else {
						
					}
				}
				else {
					Random random = new Random();
					Float number = random.nextFloat();
					return number.toString();
				}
			}
			else if("string".equalsIgnoreCase(returnType) ) {
				if(stmts.getConditionType().equals(Constants.UN_DEFIEND)
						&& stmts.getOperator() == null) {
					return dummyObject;
				}
				else if(stmts.getConditionType().equals(Constants.PASS)) {
					if(Utils.NeedToMatchOrNot(stmts.getOperator())) {
						return returnObj;
					}
					else {
						
					}
				}
				else {
					if(!Utils.NeedToMatchOrNot(stmts.getOperator())) {
						return returnObj;
					}
					else {
						
					}
					return dummyObject;
				}
			}
			else if("char".equalsIgnoreCase(returnType) || "character".equalsIgnoreCase(returnType)) {
				if(stmts.getConditionType().equals(Constants.PASS)) {
					if(Utils.NeedToMatchOrNot(stmts.getOperator())) {
						return returnObj;
					}
					else {
						
					}
				}
				else {
					if(!Utils.NeedToMatchOrNot(stmts.getOperator())) {
						return returnObj;
					}
					else {
						
					}
				}
			}
			else if("boolean".equalsIgnoreCase(returnType) || "bool".equalsIgnoreCase(returnType)) {
				if(stmts.getConditionType().equals(Constants.PASS)) {
					if(Utils.NeedToMatchOrNot(stmts.getOperator())) {
						return returnObj;
					}
					else {
						return returnObj.equalsIgnoreCase("true") ? "false" : "true" ;
					}
				}
				else {
					if(!Utils.NeedToMatchOrNot(stmts.getOperator())) {
						return returnObj;
					}
					else {
						return returnObj.equalsIgnoreCase("true") ? "false" : "true" ;
					}
				}
			}
			else {
				if(returnType != null && returnObj != null) {
					JebraCollections anyNewObject = new JebraCollections();
					anyNewObject.setReturnType(returnType);
					anyNewObject.setObjName(returnObj);
					stmts.addAnyNewObject(anyNewObject);
				}
				return returnObj;
			}
		}
		return null;
		
	}
	public void GenrateTest(TestMethodStructure testMethods) {
		if(testMethods.getName().equals("Testmethod2_else_if_if_if")) {
			System.out.println("");
		}
		MainClassStructure classStructure = testMethods.getMyClass();
		if(classStructure ==null) 
			return;
		Set<String> testStatements = new LinkedHashSet<String>();
		//ArrayList<String> annotatedObject = new ArrayList<String>();
		//annotatedObject.add(Constants.EJECT_MOCK);
		//annotatedObject.add(Constants.SPY);
		String mockMainClass = Constants.APPEND_MOCK+testMethods.getClassName();
		//annotatedObject.add("public "+testMethods.getClassName()+" " +mockMainClass + ";");
		testMethods.addMockedRefernce(testMethods.getClassName(), mockMainClass ,true,true,false);
		
		if(Utils.isNotEmpty(testMethods.getJebraParameters())) {
			for (int i = 0; i < testMethods.getJebraParameters().size(); i++) {
				JebraParameters jebraParameters = testMethods.getJebraParameters().get(i);
				if(jebraParameters != null) {
					if(!Utils.isPrimitveType(jebraParameters.getReturnType())) {
						String refType = jebraParameters.getReturnType();
						String vairableName = Constants.APPEND_MOCK +jebraParameters.getObject();
						testMethods.addMockedRefernce( refType, vairableName, false, false, true);
					}
				}
			}
		}
		for (int i = 0; i < classStructure.getJebraFieldStmts().size(); i++) {
			JebraStmts jebraStmts = classStructure.getJebraFieldStmts().get(i);
			System.out.println();
			if(jebraStmts != null){
				String refType = jebraStmts.getReturnType();
				String vairableName = Constants.APPEND_MOCK +jebraStmts.getReturnObject();
				testMethods.addMockedRefernce( refType, vairableName, false, false, true);
			}
		}
		//this.addTestMethods(testMethods);
		
		testStatements.add(Constants.TEST);
		testStatements.add(testMethods.getMethodDeclationStmt() + " { ");
		List<JebraStmts>  jebraStmts= testMethods.getJebraStmts();
		for (int i = 0; i < jebraStmts.size(); i++) {
			JebraStmts stmt = jebraStmts.get(i);
			if(stmt != null) {
				String returnString  =  getReturnType(stmt);
				testStatements.addAll(genrateCollectionObject(stmt));
				testStatements.addAll(genrateNewObjectIfAny(stmt));
				
				String temp = null;
				if(stmt.toString().contains(".")) {
					temp = stmt.toString().substring(0, stmt.toString().indexOf("."));
				}
				if(testMethods.checkForCollectionObjectisPresentorNot(temp)) {
					continue;
				}
				if(stmt instanceof JebraIfElseStmts){
					JebraIfElseStmts ifStmts = (JebraIfElseStmts)stmt;
					IfStmt ifStmt = ifStmts.getIfOrigStmts();
					
					if(!(isHardCoded(ifStmts.getLeft()) && isHardCoded(ifStmts.getRight()))){
						System.out.println();
					}
					if(ifStmt!=null && ifStmt.getThenStmt()!=null ){
						if(ifStmt.getCondition()!=null && ifStmt.getCondition() instanceof BinaryExpr) {
							BinaryExpr ifStatement = (BinaryExpr) ifStmt.getCondition();
							
							if(ifStatement.getLeft() instanceof MethodCallExpr) {
								MethodCallExpr callExpr = (MethodCallExpr)ifStatement.getLeft();
								analyzeMethodCallExpr(callExpr, testMethods, testStatements,null);
							}
							if(ifStatement.getRight() instanceof MethodCallExpr) {
								MethodCallExpr callExpr = (MethodCallExpr)ifStatement.getRight();
								analyzeMethodCallExpr(callExpr, testMethods, testStatements,null);
							}
						}
						if(ifStmt.getThenStmt() instanceof BlockStmt){
							BlockStmt blockStmt = (BlockStmt)ifStmt.getThenStmt();
					 		List<Statement> statements=  blockStmt.getStmts();
					 		for (int j = 0; j < statements.size(); j++) {
					 			Statement statement= statements.get(j);
					 			if(statement!=null && statement instanceof ExpressionStmt){
					 				ExpressionStmt expressionStmt = (ExpressionStmt) statement;
					 				if(expressionStmt!=null && expressionStmt.getExpression()!=null){
					 					if(expressionStmt.getExpression() instanceof VariableDeclarationExpr){
					 						VariableDeclarationExpr declarationExpr = (VariableDeclarationExpr)expressionStmt.getExpression();
					 						analyzeVariableDeclarationExpr(declarationExpr, testMethods, testStatements,returnString);
					 					}
					 					else if(expressionStmt.getExpression() instanceof MethodCallExpr){
					 						MethodCallExpr callExpr = (MethodCallExpr)expressionStmt.getExpression();
					 						analyzeMethodCallExpr(callExpr, testMethods, testStatements,null);
					 					}
					 					else if(expressionStmt.getExpression() instanceof AssignExpr) {
					 						AssignExpr assignExpr = (AssignExpr)expressionStmt.getExpression();
					 						
					 						String rst = fetchAssignExpr(assignExpr);
											if(rst !=null)
												testStatements.add(rst);
					 					}
					 				}
					 			}
							}
						}
					}
				}
			
				else if(stmt instanceof JebraLoopStmts) {
	
					JebraLoopStmts jebraLoopStmts = (JebraLoopStmts) stmt;
					if(jebraLoopStmts!=null) {
						String mockStr = "mock" + jebraLoopStmts.getReturnObject();
						boolean present = testMethods.isMockedObjectsPresent(jebraLoopStmts.getReturnType(),mockStr);
						if(!present) {
							testMethods.addMockedRefernce( jebraLoopStmts.getReturnType(), mockStr ,true,true,false);
						}
						
						if(jebraLoopStmts.getForeachStmt()!=null && jebraLoopStmts.getForeachStmt().getIterable() !=null) {
							if(jebraLoopStmts.getForeachStmt().getIterable() instanceof MethodCallExpr) {
								MethodCallExpr callExpr = (MethodCallExpr) jebraLoopStmts.getForeachStmt().getIterable();
								analyzeMethodCallExpr(callExpr, testMethods, testStatements,returnString);
							}
						}
					}
				}
				// Not if statements
				else if(stmt.getExpr() instanceof VariableDeclarationExpr){
					if(isHardCoded(stmt.getRight())){
						continue;
					}
					VariableDeclarationExpr expr = (VariableDeclarationExpr) stmt.getExpr();
					analyzeVariableDeclarationExpr(expr, testMethods, testStatements,returnString);
				}
				else if(stmt.getExpr()!=null && stmt.getExpr() instanceof BinaryExpr) {
					BinaryExpr ifStatement = (BinaryExpr) stmt.getExpr();
					
					if(ifStatement.getLeft() instanceof MethodCallExpr) {
						MethodCallExpr callExpr = (MethodCallExpr)ifStatement.getLeft();
						analyzeMethodCallExpr(callExpr, testMethods, testStatements,returnString);
					}
					if(ifStatement.getRight() instanceof MethodCallExpr) {
						MethodCallExpr callExpr = (MethodCallExpr)ifStatement.getRight();
						analyzeMethodCallExpr(callExpr, testMethods, testStatements,returnString);
					}
				}
				else if(stmt.getExpr()!=null && stmt.getExpr() instanceof BooleanLiteralExpr) {
					JOptionPane.showMessageDialog(null, "Need to implement \n genratetestCase.java L-181");
				}
				else if(stmt.getExpr() instanceof MethodCallExpr){
					if(stmt.getReturnObject() != null && stmt.getReturnType() != null) {
						String mockStr = "mock"+stmt.getReturnObject();
						boolean present = testMethods.isMockedObjectsPresent(stmt.getReturnType(), mockStr);
						if(!present) {
							testMethods.addMockedRefernce( testMethods.getClassName(), mockStr ,true,true,false);
						}
					}
					MethodCallExpr callExpr = (MethodCallExpr)stmt.getExpr();
					analyzeMethodCallExpr(callExpr, testMethods, testStatements,returnString);
				}
				else if(stmt.getExpr() instanceof AssignExpr) {
						AssignExpr assignExpr = (AssignExpr)stmt.getExpr();
						String rst = fetchAssignExpr(assignExpr);
						if(rst !=null)
							testStatements.add(rst);
				}
				else if (stmt.getExpr() instanceof NameExpr) {
					NameExpr nameExpr = (NameExpr) stmt.getExpr();
					if (nameExpr != null) {
						fetchNameExpr(stmt, testMethods);
					}
				}
			}
		}
		//mockMainClass
		testStatements.add(writeStatement(Constants.FINAL_CALL,mockMainClass,testMethods.getOrignalMethodName(),testMethods.getReturnType(),testMethods));
		
		testStatements.add("}  //End of Test-case");
		testMethods.setTestBodyStatments(testStatements);
	}
	
	//parameter is left
	private String writeStatement(Integer type,String mockObj ,String methodName , String returnString, TestMethodStructure testMethods){

		if(returnString != null && returnString.contains("dummy")) {

		}
		if(type == Constants.FINAL_CALL) {
			String finalStr = testMethods.getReturnOutput() + "  response = "+ mockObj + "." + methodName + "();";
			finalStr +="\n";
			finalStr +="\tassertNotNull(response)";
			return finalStr;
		}
		else if(returnString == null) {
			return "Mockito.doNothing().when(" + mockObj + ")." + methodName + "();";
		}
		else if (type == Constants.DO_RETURN) {
			String returnObject = Constants.DEFAULT_RETURN_STATEMENT;
			if(Utils.isNotBlank(returnString)) {
				returnObject = returnString;
			}
			return "doReturn(" + returnObject + ").when(" + mockObj + ")." + methodName + "();";
		} else if (type == Constants.DO_NOTHING) {
			return "Mockito.doNothing().when(" + mockObj + ")." + methodName + "();";
		}
		
		return null;
	}

	private Set<String> genrateNewObjectIfAny(JebraStmts stmt) {
		Set<String> newObjectsSet = new LinkedHashSet<String>();
		ArrayList<JebraCollections>  jebraCollections = stmt.getAnyNewObject();
		for (JebraCollections newObj : jebraCollections) {
			if(newObj != null) {
				String collectionStatement = "";
				collectionStatement += newObj.getReturnType() + " ";
				collectionStatement += newObj.getObjName() + "  =  new ";
				collectionStatement += newObj.getReturnType() + "();";
				newObjectsSet.add(collectionStatement);
			}
		}
		
		return newObjectsSet;
	}
	private Set<String> genrateCollectionObject(JebraStmts stmt) {
		
		Set<String> collectionSet = new LinkedHashSet<String>();
		ArrayList<JebraCollections>  jebraCollections = stmt.getAnyCollectionObject();
		for (JebraCollections collections : jebraCollections) {
			if(collections != null) {
				String collectionStatement = "";
				String collectionName ="";
				if(!Utils.isBlank(collections.getType())) {
					collectionStatement += collections.getType() ;
					if(!Utils.isBlank(collections.getReturnType())) {
						collectionStatement += "<" + collections.getReturnType() + ">  ";
					}
					
					if(!Utils.isBlank(collections.getObjName())) {
						collectionName += collections.getObjName();
					}
					else {
						// this is the case where we don't have object name (it should not come here.if come then its an error in code manipulation)
						collectionName += collections.getType()+collections.getReturnType();
					}
					
					collectionStatement += collectionName;
					collectionStatement += " =  new ";
					collectionStatement += getSubColleactionType(collections);
					
					if(!Utils.isBlank(collections.getReturnType())) {
						collectionStatement += "<" + collections.getReturnType() + ">  ";
					}
					
					collectionStatement += "();";
				}
				collectionSet.add(collectionStatement);
				
				
				// create object to add in collection
				String newObject ="";
				newObject += collections.getReturnType();
				newObject += " ";
				newObject += collections.getReturnType().toLowerCase();
				newObject += "  =  new ";
				newObject +=  collections.getReturnType();
				newObject += "();";
				collectionSet.add(newObject);
				
				// add newly created object in collections
				String addObject ="";
				addObject += collectionName;
				addObject += ".add(" + collections.getReturnType().toLowerCase() + "); ";
				collectionSet.add(addObject);
			}
		}
		
		return collectionSet;
	}
	public void setCurrentTestMethods(TestMethodStructure currentTestMethods) {
		this.currentTestMethods = currentTestMethods;
	}

	public TestMethodStructure getCurrentTestMethods() {
		return currentTestMethods;
	}
	
	public static boolean isHardCoded(String str) {
	    
		if(isString(str) || isInteger(str))
			return true;
		else 
			return false;
	}
	public static boolean isString(String s) {
	    
		
		if( s !=null && (s.startsWith("\"") && s.endsWith("\"")))
			return true;
		else 
			return false;
	}
	
	public static boolean isInteger(String s) {
	    try { 
	        Integer.parseInt(s); 
	    } catch(NumberFormatException e) { 
	        return false; 
	    } catch(NullPointerException e) {
	        return false;
	    }
	    // only got here if we didn't return false
	    return true;
	}
	
	public String fetchAssignExpr(AssignExpr assignExpr) {
		if(assignExpr !=null){
			if (assignExpr.getValue() instanceof MethodCallExpr) {
				MethodCallExpr callExpr = (MethodCallExpr) assignExpr.getValue();
				if (callExpr != null) {
					if (callExpr.getScope() instanceof NameExpr) {
						NameExpr expr = (NameExpr) callExpr.getScope();
						if (expr != null && expr.getName() != null) {
							String returnString = null;
							return writeStatement(Constants.DO_RETURN, Constants.APPEND_MOCK + expr.getName(),
									callExpr.getName(), returnString,null);
						}
					}
				}
			}
			else if(assignExpr.getValue() instanceof ObjectCreationExpr) {
				
			}
		}
		return null;
	}
	
	public void fetchNameExpr(JebraStmts stmt, TestMethodStructure testMethods) {

		String mockStr = "mock" + stmt.getReturnObject();
		boolean present = testMethods.isMockedObjectsPresent(stmt.getReturnType(), mockStr);
		if (!present) {
			testMethods.addMockedRefernce(stmt.getReturnType(), mockStr, true, true, false);
		}
	}

	private boolean isNotPrimitiveType(String classType) {
		classType = classType.toLowerCase();
		if(classType.equalsIgnoreCase("string") ||
				classType.equalsIgnoreCase("integer") ||
				classType.equalsIgnoreCase("double")  ||
				classType.equalsIgnoreCase("character")) {
			return false;
		}
		if(classType.contains("arrayList") ||
				classType.contains("set") ||
				classType.contains("map")) {
			return false;
		}
		return true;
	}
	
	public void analyzeMethodCallExpr(MethodCallExpr callExpr , TestMethodStructure testMethods,Set<String> testStatements,String returnString) {

		if(callExpr !=null){
			if(callExpr.getScope() == null) {
				String mockStr = "mock"+testMethods.getClassName(); 
				testStatements.add(writeStatement(Constants.DO_RETURN,mockStr,callExpr.getName(),returnString ,null));
				
			}
			if(callExpr.getScope() instanceof ThisExpr) {
				ThisExpr thisExpr = (ThisExpr) callExpr.getScope();
				if(thisExpr!=null) {
					String mockStr = "mock"+testMethods.getClassName();
					boolean present = testMethods.isMockedObjectsPresent(testMethods.getClassName(), mockStr);
					if(!present) {
						testMethods.addMockedRefernce( testMethods.getClassName(), mockStr ,true,true,false);
					}
				
					testStatements.add(writeStatement(Constants.DO_RETURN,mockStr,callExpr.getName(),returnString , null));
				}
			}
			else if(callExpr.getScope() instanceof MethodCallExpr){
				MethodCallExpr methodCallExpr = (MethodCallExpr)callExpr.getScope();
				if(methodCallExpr != null) {
					if(methodCallExpr.getScope() instanceof NameExpr){
						analyzeNameExpr(methodCallExpr, testMethods, testStatements, returnString);
					}
				}
			}
			else if(callExpr.getScope() instanceof NameExpr){
				analyzeNameExpr(callExpr, testMethods, testStatements, returnString);
			}
		}
	}
	public void analyzeNameExpr(MethodCallExpr expr , TestMethodStructure testMethods,Set<String> testStatements,String returnString) {
		if(expr.getScope() instanceof NameExpr){
			NameExpr nameExpr= (NameExpr)expr.getScope();
			if(nameExpr!=null){
				if(nameExpr.getName()!=null) {
					if (!testMethods.checkForCollectionObjectisPresentorNot(nameExpr.getName()) &&
							!Utils.containedBasicFunction(expr.toString())) {

						String mockStr = "mock" + nameExpr.getName();
						boolean present = testMethods.isMockedObjectsPresent(mockStr, mockStr);
						if (!present) {
							// String refType = jebraStmts.getReturnType();
							// String vairableName = Constants.APPEND_MOCK +jebraStmts.getLeft();
							// addMockedRefernce(testMethods, refType, vairableName, false, false, true);
							JOptionPane.showMessageDialog(null,
									"Please inform dev.\nError Code<jebra-E100>\nMessage<Mock Object not found >\n GenrateTestCase.java [line-no:366]  testmethod name: "
											+ testMethods.getMethodDeclationStmt());
							System.out.println("");
						}
						testStatements.add(writeStatement(Constants.DO_RETURN, mockStr, expr.getName(), returnString , null));
					}
				}
			}
		}
	}
	public void analyzeVariableDeclarationExpr(VariableDeclarationExpr expr , TestMethodStructure testMethods,Set<String> testStatements,String returnString) {

		if(expr!=null) {
			for (int j = 0; j < expr.getVars().size(); j++) {
				if(expr.getVars().get(j) instanceof VariableDeclarator){
					VariableDeclarator declarator = (VariableDeclarator)expr.getVars().get(j);
					if(declarator!=null){
						if(expr.getType()!=null  && expr.getType() instanceof ReferenceType) {
							ReferenceType referenceType = (ReferenceType) expr.getType();
							if(referenceType.getType() != null && isNotPrimitiveType(referenceType.getType().toString())) {
								String mockStr = "mock"+declarator.getId();
//								if(referenceType.getType() instanceof ClassOrInterfaceType) {
//									ClassOrInterfaceType interfaceType = (ClassOrInterfaceType) referenceType.getType();
//									if(interfaceType != null) {
//										List<Type>  types = interfaceType.getTypeArgs();
//										if (Utils.isNotEmpty(types)) {
//											for (Type type : types) {
//												String mockTypeStr = "mock" + type;
//												boolean present = testMethods.isMockedObjectsPresent(type.toString(),
//														mockTypeStr);
//												if (!present) {
//													testMethods.addMockedRefernce(type.toString(), mockTypeStr, false,
//															false, true);
//												}
//											}
//										}
//									}
//								}
								{
									boolean present = testMethods.isMockedObjectsPresent(referenceType.getType().toString(),mockStr);
									if(!present) {
										testMethods.addMockedRefernce(referenceType.getType().toString(), mockStr ,false,false,true);
									}
								}
							}
							if(Utils.isCollectionType(expr.getType().toString()))
								returnString = declarator.getId().toString();
						}
						
						if(declarator.getInit() instanceof ObjectCreationExpr){
							ObjectCreationExpr  creationExpr= (ObjectCreationExpr)declarator.getInit();
							String mockedObject =Constants.APPEND_MOCK+declarator.getId();
							testMethods.addMockedRefernce(creationExpr.getType().toString(), mockedObject, false, false, true);
							continue;
						}
						
						if(declarator.getInit() instanceof MethodCallExpr){
							MethodCallExpr callExpr = (MethodCallExpr)declarator.getInit();
							analyzeMethodCallExpr(callExpr, testMethods, testStatements,returnString);
						}
					}
				}
			}
		}
	}
	
	private String getSubColleactionType(JebraCollections collections) {
		String typeLowerCase = collections.getType().toLowerCase();
		
		if(typeLowerCase.contains("collection")) {
			return "ArrayList" ;
		}
		else if(typeLowerCase.contains("list")) {
			return "ArrayList" ;
		}
		else if(typeLowerCase.contains("set")) {
			return "HashSet" ;	
		}
		else if(typeLowerCase.contains("map")) {
			return "HashMap" ;
		}
		else {
			return collections.getType() ;
		}
	}
}
