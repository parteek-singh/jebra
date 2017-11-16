package com.parteek.jebra;

import japa.parser.ast.body.BodyDeclaration;
import japa.parser.ast.body.ClassOrInterfaceDeclaration;
import japa.parser.ast.body.ConstructorDeclaration;
import japa.parser.ast.body.FieldDeclaration;
import japa.parser.ast.body.MethodDeclaration;
import japa.parser.ast.body.ModifierSet;
import japa.parser.ast.body.Parameter;
import japa.parser.ast.body.VariableDeclarator;
import japa.parser.ast.expr.IntegerLiteralExpr;
import japa.parser.ast.expr.MethodCallExpr;
import japa.parser.ast.expr.NullLiteralExpr;
import japa.parser.ast.expr.ObjectCreationExpr;
import japa.parser.ast.stmt.BlockStmt;
import japa.parser.ast.stmt.Statement;

import java.util.ArrayList;
import java.util.List;

import com.parteek.jebra.methods.MethodStructure;
import com.parteek.jebra.statements.JebraIfElseStmts;
import com.parteek.jebra.statements.JebraLoopStmts;
import com.parteek.jebra.statements.JebraStmts;
import com.parteek.jebra.statements.JebraTryStmt;
import com.sun.org.apache.bcel.internal.generic.SWITCH;

public class MainClassStructure {

	private ClassOrInterfaceDeclaration myClass;
	private ArrayList<MethodStructure> allMethods;
	private List<JebraStmts> jebraFieldStmts;
	private String className;
	private String modifier;
	private String classStmt;
	
	public MainClassStructure() {
		allMethods = new ArrayList<MethodStructure>();
		jebraFieldStmts = new ArrayList<JebraStmts>();
	}

	public void setAllMethods(ArrayList<MethodStructure> allMethods) {
		this.allMethods = allMethods;
	}

	public ArrayList<MethodStructure> getAllMethods() {
		return allMethods;
	}

	public void setMyClass(ClassOrInterfaceDeclaration myClass) {
		this.myClass = myClass;
	}

	public ClassOrInterfaceDeclaration getMyClass() {
		return myClass;
	}	
	
	public void setJebraFieldStmts(List<JebraStmts> jebraFieldStmts) {
		this.jebraFieldStmts = jebraFieldStmts;
	}

	public List<JebraStmts> getJebraFieldStmts() {
		return jebraFieldStmts;
	}
	
	public void addMethod(MethodStructure obj) {		
		allMethods.add(obj);
	}
	
	public void addFieldStmt(JebraStmts obj) {		
		jebraFieldStmts.add(obj);
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getClassName() {
		return className;
	}

	public void setModifier(String modifier) {
		this.modifier = modifier;
	}

	public String getModifier() {
		return modifier;
	}

	public void setClassStmt(String classStmt) {
		this.classStmt = classStmt;
	}

	public String getClassStmt() {
		return classStmt;
	}
	
	public String createModifier(int modifier) {
		 String type ="";
		switch (modifier) {
		case ModifierSet.ABSTRACT:
			type = "abstract";
			break;
		case ModifierSet.FINAL:
			type = "final";
			break;
		case ModifierSet.NATIVE:
			type = "native";
			break;
		case ModifierSet.PRIVATE:
			type = "private";
			break;
		case ModifierSet.PROTECTED:
			type = "protacted";
			break;
		case ModifierSet.PUBLIC:
			type = "public";
			break;
		case ModifierSet.STATIC:
			type = "ststic";
			break;
		case ModifierSet.STRICTFP:
			type = "strictfp";
			break;
		case ModifierSet.SYNCHRONIZED:
			type = "synchronized";
			break;
		case ModifierSet.TRANSIENT:
			type = "transient";
			break;
		case ModifierSet.VOLATILE:
			type = "volatile";
			break;
		default:
			type = "";
			break;
		}
		return type;
	}

	public void createFieldVaiables(BodyDeclaration member){

		  FieldDeclaration fieldDec = (FieldDeclaration) member;
		  JebraStmts jebraStmts = new JebraStmts();
		  jebraStmts.setStmt(member.toString());
		  
		  jebraStmts.setReturnType(fieldDec.getType().toString());
		  
		  if(fieldDec.getVariables()!=null && fieldDec.getVariables().size()>0){
			  for (VariableDeclarator bodyDeclaration : fieldDec.getVariables()) {
				  if (bodyDeclaration instanceof VariableDeclarator) {
					  VariableDeclarator declarator = (VariableDeclarator)bodyDeclaration;
					  if (declarator != null) {
						  jebraStmts.setLeft(declarator.getId().toString());
						  if (declarator.getInit() instanceof IntegerLiteralExpr) {
							  IntegerLiteralExpr rightObject = (IntegerLiteralExpr) declarator.getInit();
							  jebraStmts.setRight(rightObject.getValue());
						  }
						  if (declarator.getInit() instanceof MethodCallExpr) {
							  MethodCallExpr rightObject = (MethodCallExpr) declarator.getInit();
							  jebraStmts.setRight(rightObject.toString());
						  }
						  if (declarator.getInit() instanceof ObjectCreationExpr) {
							  ObjectCreationExpr rightObject = (ObjectCreationExpr) declarator.getInit();
							  jebraStmts.setRight(rightObject.toString());
						  }
						  if (declarator.getInit() instanceof NullLiteralExpr) {
							  jebraStmts.setRight("null");
						  }
					}
				 }
			  }
		  }
		  jebraStmts.setReturnObject(jebraStmts.getLeft());
		  this.addFieldStmt(jebraStmts);
		//  System.out.println(jebraStmts.getStmt());
	}
	
	public void createConstructor(BodyDeclaration member){

		ConstructorDeclaration methoDec = (ConstructorDeclaration) member;
	    BlockStmt s = (BlockStmt) methoDec.getBlock();
	    List<Statement> bodyStmts = s.getStmts();
	    MethodStructure methodStructure = new MethodStructure();
	    methodStructure.setName(methoDec.getName());
	    //methodStructure.setClassName(methoDec);
	    methodStructure.setReturnType(createModifier(methoDec.getModifiers()));
	    methodStructure.setMyConstDeclaration(methoDec);
	    methodStructure.setMethodDeclationStmt(methodStructure.getReturnType()+" "+methodStructure.getName()+ "()");
	 //   System.out.println(methodStructure.getMethodDeclationStmt()+" { ");
	    methodStructure.setBodyStmts(bodyStmts);
	    for (int i = s.getBeginLine(); i <= s.getEndLine(); i++) {
	    	methodStructure.addLineNumbers(i);
		}
	    addMethod(methodStructure);
	 //   System.out.println("}");
	}
	public void createMethod(BodyDeclaration member){

		MethodDeclaration methoDec = (MethodDeclaration) member;
        BlockStmt s = (BlockStmt) methoDec.getBody();
        List<Statement> bodyStmts = s.getStmts();
        MethodStructure methodStructure = new MethodStructure();
        methodStructure.setName(methoDec.getName());
        methodStructure.setMyClass(this);
       // methodStructure.setClassName(className);
        methodStructure.setReturnType(methoDec.getType().toString());
        methodStructure.setMyMethod(methoDec);
        for (int i = s.getBeginLine(); i <= s.getEndLine(); i++) {
	    	methodStructure.addLineNumbers(i);
		}
        if(methoDec.getParameters()!=null && methoDec.getParameters().size()>0){
            for(Parameter parameter : methoDec.getParameters()) {
          	  if(parameter!=null){
          		  if(parameter instanceof Parameter){
          			  JebraParameters jebraParameters = new JebraParameters();
          			  jebraParameters.setStmt(parameter.toString());
          			  jebraParameters.setReturnType(parameter.getType().toString());
          			  jebraParameters.setObject(parameter.getId().toString());
          			  methodStructure.addParameters(jebraParameters);
          		  }
          	  }
            }
        }
        String dummyMethodStmt = methodStructure.getReturnType()+" "+methodStructure.getName()+ "( ";
        
        for (JebraParameters parameters : methodStructure.getJebraParameters()) {
			if(parameters!=null){
				dummyMethodStmt +=parameters.getStmt() +" , ";
			}
		}
        dummyMethodStmt += ")";
        methodStructure.setMethodDeclationStmt(dummyMethodStmt);
     //   System.out.println(methodStructure.getMethodDeclationStmt()+" { ");
        try {
			methodStructure.initailiseMethodBody(bodyStmts);
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
     //   System.out.println("}");
        addMethod(methodStructure);
	}
	
	public String getClassObjectRefType(String vairableName){
		 
		for (JebraStmts stmts : this.getJebraFieldStmts()) {
			if(stmts!=null && stmts.getLeft().equals(vairableName)){
				return stmts.getReturnType();
			}
		}
		return null;
	}
}
