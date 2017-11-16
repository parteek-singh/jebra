package com.parteek.jebra;

import japa.parser.JavaParser;
import japa.parser.ParseException;
import japa.parser.ast.CompilationUnit;
import japa.parser.ast.body.BodyDeclaration;
import japa.parser.ast.body.ClassOrInterfaceDeclaration;
import japa.parser.ast.body.ConstructorDeclaration;
import japa.parser.ast.body.FieldDeclaration;
import japa.parser.ast.body.MethodDeclaration;
import japa.parser.ast.body.TypeDeclaration;

import java.io.FileInputStream;
import java.util.List;

public class JebraJunitMaker {
	
	private static JebraJunitMaker jebraJunitMaker ;
	private MainClassStructure mainClassStructure;
	
	private JebraJunitMaker() {
		
	}

	public static JebraJunitMaker getInstance(){
		if(jebraJunitMaker==null){
			jebraJunitMaker = new JebraJunitMaker();
		}
		return jebraJunitMaker;
	}
	
	public void startProcessing(String filePath) throws Exception {
		
		CompilationUnit cu;
	    FileInputStream in = new FileInputStream(filePath);
	    
	    cu = JavaParser.parse(in);
		setMainClassStructure(createStructure(cu));

	}
	private  MainClassStructure createStructure(CompilationUnit cu) {
		  
		  List<TypeDeclaration> types = cu.getTypes();
	      for (TypeDeclaration type : types) {
	          if (type instanceof ClassOrInterfaceDeclaration) {
	              //We are either in a class or in an interface
	              ClassOrInterfaceDeclaration classDec = (ClassOrInterfaceDeclaration) type;
	              //if (classDec.getModifiers() == ModifierSet.PUBLIC) {
	                  
	            	  MainClassStructure mainClass = new MainClassStructure();
	            	  mainClass.setMyClass(classDec);
	            	  mainClass.setClassName(classDec.getName());
	            	  mainClass.setModifier(mainClass.createModifier(classDec.getModifiers()));
	            	  mainClass.setClassStmt(mainClass.getModifier() +"  class "+ mainClass.getClassName());
	            	 // System.out.println(mainClass.getClassStmt() + "{");
	            	  List<BodyDeclaration> members = type.getMembers();
	                  for (BodyDeclaration member : members) {
	                	  if(member!=null){
		                	  if(member instanceof FieldDeclaration){
		                		  mainClass.createFieldVaiables(member);
		                	  }
		                	  else if(member instanceof ConstructorDeclaration){
		                		  mainClass.createConstructor(member);
		                	  }
		                	  else if (member instanceof MethodDeclaration) {
		                    	  mainClass.createMethod(member);  
		                      }
	                	  }
	                  }
	                  return mainClass;
	              
	              
	          }
	      }
	      return null;
	}

	public void setMainClassStructure(MainClassStructure mainClassStructure) {
		this.mainClassStructure = mainClassStructure;
	}

	public MainClassStructure getMainClassStructure() {
		return mainClassStructure;
	}
}
