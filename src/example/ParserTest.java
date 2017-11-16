package example;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.parteek.jebra.JebraJunitMaker;
import com.parteek.jebra.JebraParameters;
import com.parteek.jebra.MainClassStructure;
import com.parteek.jebra.methods.MethodStructure;
import com.parteek.jebra.statements.JebraStmts;

import japa.parser.JavaParser;
import japa.parser.ast.CompilationUnit;
import japa.parser.ast.ImportDeclaration;
import japa.parser.ast.body.BodyDeclaration;
import japa.parser.ast.body.ClassOrInterfaceDeclaration;
import japa.parser.ast.body.ConstructorDeclaration;
import japa.parser.ast.body.FieldDeclaration;
import japa.parser.ast.body.MethodDeclaration;
import japa.parser.ast.body.ModifierSet;
import japa.parser.ast.body.Parameter;
import japa.parser.ast.body.TypeDeclaration;
import japa.parser.ast.body.VariableDeclarator;
import japa.parser.ast.expr.IntegerLiteralExpr;
import japa.parser.ast.expr.MethodCallExpr;
import japa.parser.ast.expr.NullLiteralExpr;
import japa.parser.ast.expr.ObjectCreationExpr;
import japa.parser.ast.stmt.BlockStmt;
import japa.parser.ast.stmt.ExpressionStmt;
import japa.parser.ast.stmt.Statement;

public class ParserTest {

  public static void main(String[] args) throws Exception {
//    CompilationUnit cu;
//    FileInputStream in = new FileInputStream("E:\\workspace\\jebra\\src\\parent.java");
//    
//    cu = JavaParser.parse(in);
//    
//    ParserTest parserTest = new ParserTest();
//    MainClassStructure mainClass =  parserTest.createStructure(cu);
//    String filePath = "E:\\workspace\\jebra\\src\\parent.java";
//    JebraJunitMaker jebraJunitMaker= JebraJunitMaker.getInstance();
//    jebraJunitMaker.startProcessing(filePath);
//    
//    List<TypeDeclaration> types = cu.getTypes();
//    ArrayList<String> parents = new ArrayList<String>();
//
//    for (TypeDeclaration typeDeclaration : types) {
//      printTypes(typeDeclaration, parents);
//    }
//	 MainClassStructure mainClass =  parserTest.createStructure(cu);
    
	  String filePath = "E:\\workspace\\jebra\\src\\parent.java";
	  JebraJunitMaker jebraJunitMaker= JebraJunitMaker.getInstance();
	  jebraJunitMaker.startProcessing(filePath);
	  MainClassStructure classStructure = jebraJunitMaker.getMainClassStructure();
	//  System.out.println("");
    
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
            	//  System.out.println(mainClass.getClassStmt() + "{");
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
  private static ArrayList test(CompilationUnit cu) {
	  ArrayList signatures = new ArrayList();
      Object[] signature = new Object[7];
      int methoCnt = 0;

      signature[0] = new Boolean(false);
      
	  HashMap hmPck = new HashMap();
      //Primite types
      hmPck.put("byte", "byte");
      hmPck.put("short", "short");
      hmPck.put("int", "int");
      hmPck.put("long", "long");
      hmPck.put("float", "float");
      hmPck.put("double", "double");
      hmPck.put("boolean", "boolean");
      hmPck.put("char", "char");
      hmPck.put("String", "java.lang.String");
      hmPck.put("Boolean", "java.lang.Boolean");
      hmPck.put("Integer", "java.lang.Integer");
      hmPck.put("Byte", "java.lang.Byte");
      hmPck.put("Short", "java.lang.Short");
      hmPck.put("Long", "java.lang.Long");
      hmPck.put("Float", "java.lang.Float");
      hmPck.put("Double", "java.lang.Double");
      hmPck.put("Character", "java.lang.Character");
      hmPck.put("byte[]", "byte[]");
      hmPck.put("short[]", "short[]");
      hmPck.put("int[]", "int[]");
      hmPck.put("long[]", "long[]");
      hmPck.put("float[]", "float[]");
      hmPck.put("double[]", "double[]");
      hmPck.put("boolean[]", "boolean[]");
      hmPck.put("char[]", "char[]");
      hmPck.put("String[]", "java.lang.String[]");
      hmPck.put("Boolean[]", "java.lang.Boolean[]");
      hmPck.put("Integer[]", "java.lang.Integer[]");
      hmPck.put("Byte[]", "java.lang.Byte[]");
      hmPck.put("Short[]", "java.lang.Short[]");
      hmPck.put("Long[]", "java.lang.Long[]");
      hmPck.put("Float[]", "java.lang.Float[]");
      hmPck.put("Double[]", "java.lang.Double[]");
      hmPck.put("Character[]", "java.lang.Character[]");
      //Declared imports        
      List<ImportDeclaration> imports = cu.getImports();
      if (imports != null && !imports.isEmpty()) {
          for (ImportDeclaration imp : imports) {
              hmPck.put(imp.getName().getName(), imp.getName().toString());
          }
      }

      List<TypeDeclaration> types = cu.getTypes();
      for (TypeDeclaration type : types) {
          if (type instanceof ClassOrInterfaceDeclaration) {
              //We are either in a class or in an interface
              ClassOrInterfaceDeclaration classDec = (ClassOrInterfaceDeclaration) type;
              if (classDec.getModifiers() == ModifierSet.PUBLIC) {
                  //The class or interface is public
                  if (classDec.isInterface()) {
                      signature[2] = "Interface"; //interface
                  } else {
                      signature[2] = "Class"; //class
                  }
                  //convert to javaCase
                  signature[3] = classDec.getName().substring(0, 1).toLowerCase() + classDec.getName().substring(1);
              }

              List<BodyDeclaration> members = type.getMembers();
              for (BodyDeclaration member : members) {
                  if (member instanceof MethodDeclaration) {
                      //We are in a method
                      MethodDeclaration methoDec = (MethodDeclaration) member;
                      BlockStmt s = (BlockStmt) methoDec.getBody();
                      List<Statement > ss = s.getStmts();
                      if (methoDec.getModifiers() == ModifierSet.PUBLIC || methoDec.getModifiers() == 0) {
                          //the method is public
                          signature[1] = new Integer(methoCnt);
                          methoCnt++; //increment the signature's ID
                          signature[4] = methoDec.getName();

                          //retrieve method's pars
                          List pars = methoDec.getParameters();
                          if (pars != null) {
                              StringBuilder parsStr = new StringBuilder();

                              for (int i = 0; i < pars.size(); i++) {
                                  if (i > 0) {
                                      parsStr.append(", ");
                                  }
                                  Parameter sPar = (Parameter) pars.get(i);

                                  String sParCleaned = sPar.getType().toString();
                                  //remove Java generics definition, if present
                                  if (sPar.getType().toString().contains("<")) {
                                      sParCleaned = sParCleaned.substring(0, sParCleaned.indexOf("<"));
                                  }
                                  //before appending the parameter type, retrieve the package-class name from our lookup table
                                  if (hmPck.containsKey(sParCleaned)) {
                                      parsStr.append(hmPck.get(sParCleaned));
                                  } else {
                                      parsStr.append("NotFound"); //this prevents null values
                                  }
                              }
                              signature[5] = parsStr.toString();
                          } else {
                              //method with no parameter
                              signature[5] = "";
                          }

                          //retrieve method's annotations
                          List anns = methoDec.getAnnotations();

                          if (anns != null) {
                              StringBuilder annStr = new StringBuilder();

                              for (int i = 0; i < anns.size(); i++) {
                                  if (i > 0) {
                                      annStr.append(", ");
                                  }
                                  annStr.append(anns.get(i));
                              }

                              signature[6] = annStr.toString();
                          } else {
                              //method with no annotations
                              signature[6] = "";
                          }

                          //add signature[] to master signatures[]
                          signatures.add(signature.clone());
                          signature[4] = "";
                          signature[5] = "";
                          signature[6] = "";
                      }
                  }
              }
          } else {
              //bogus signature, no classes found - this is just to prevent GUI crashes
              signature[1] = new Integer(methoCnt);
              signature[2] = "";
              signature[3] = "";
              signature[4] = "";
              signature[5] = "";
              signature[6] = "";
          }
      }
      return signatures;
  }

  private static void printTypes(BodyDeclaration item, List<String> parents) {
    if (item instanceof TypeDeclaration) {

      TypeDeclaration type = (TypeDeclaration) item;

  //    System.out.println("class name : "+getClassName(type.getName(), parents));
      List<BodyDeclaration> members = type.getMembers();

      List<String> cloneOfParents = new ArrayList<String>(parents);
      cloneOfParents.add(type.getName());

      for (BodyDeclaration bodyDeclaration : members) {
        printTypes(bodyDeclaration, cloneOfParents);
      }
    }
  }

  private String getClassName(String name, List<String> parents) {
    StringBuilder builder = new StringBuilder();
    for (String string : parents) {
      builder.append(string);
      builder.append(".");
    }

    return builder.append(name).toString();
  }
  
  public String helloTest() {
	    StringBuilder builder = new StringBuilder();
//	    for (String string : parents) {
//	      builder.append(string);
//	      builder.append(".");
//	    }
return "";
	  }
}
