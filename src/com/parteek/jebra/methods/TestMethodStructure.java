package com.parteek.jebra.methods;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.parteek.jebra.MainClassStructure;
import com.parteek.jebra.statements.JebraStmts;

import japa.parser.ast.stmt.Statement;
import util.Constants;
import util.JebraStatementFactory;
import util.Utils;

public class TestMethodStructure extends MethodParent{

	private String returnOutput;
	private ArrayList<String> testMethodCreationType;
	private Map<String, Set<String>> mockedObjects = null; // it contains the reference type and all associated mock objects 
	private Map<String, ArrayList<String>> mockedRefernce = null; // it contains the reference type and associated annotated objects
	
	private Set<String> testBodyStatments;
	private String orignalMethodName;
	public TestMethodStructure() {
		setMockedObjects(new HashMap<String, Set<String>>());
		setMockedRefernce(new HashMap<String, ArrayList<String>>());
	}

//	public void initialise(MethodStructure structure,String val, ArrayList<JebraStmts> jebraStmtsGroup, JebraStmts jebraStmts,
//			MainClassStructure myClass, Set<Integer> lineInMethod, ArrayList<String> bodyStmts) {
	public void initialise(MethodStructure structure,String val, List<JebraStmts> jebraStmtsGroup,
			MainClassStructure myClass, Set<Integer> lineInMethod) {
		this.setName("Test" + structure.getName() +"_"+ val);
		this.setReturnType("void");
		this.setReturnOutput(structure.getReturnType());
		this.setClassName(myClass.getClassName());
		this.setMyClass(myClass);
		this.setJebraParameters(structure.getJebraParameters());
		//this.setBodyStmts(structure.getBodyStmts());
		this.setOrignalMethodName(structure.getName());
		
		this.getJebraStmts().addAll(jebraStmtsGroup);
		if(lineInMethod != null)
			this.getLineNumbers().addAll(lineInMethod);
		//this.getIfBodyStmtsString().addAll(bodyStmts);
		
//		if(jebraStmts!=null) {
//			this.addStatement(jebraStmts);
//			this.getLineNumbers().addAll(jebraStmts.getLineNumber());
//			this.getIfBodyStmtsString().addAll(jebraStmts.getBodyStmts());
//		}
		this.setMethodDeclationStmt(
				"public " + this.getReturnType() + "  " + this.getName() + "() ");

//		this.setTestMethodCreationType(getTestMethodHierarchy(val));
		//CalulateJebraStamentsForTestMethods();

	}

	private ArrayList<String> getTestMethodHierarchy(String val) {
		if(val ==null) {
			return null;
		}
		ArrayList<String> arrayList = new ArrayList<String>();
		String[] aa = val.split("_");
		for (int i = 0; i < aa.length; i++) {
			String string = aa[i];
			if (string != null && !string.equals("")) {
				arrayList.add(string);
			}

		}
		return arrayList;
	}

	private void CalulateJebraStamentsForTestMethods() {
		List<Statement> rawData = getBodyStmts();
		this.getJebraStmts().clear();

		for (Statement statement : rawData) {
			if (statement != null) {
				JebraStatementFactory.getInstance().genrateStatements(statement, jebraStmts);
			}
		}
	}

	public Set<String> getTestBodyStatments() {
		return testBodyStatments;
	}

	public void setTestBodyStatments(Set<String> testBodyStatments) {
		this.testBodyStatments = testBodyStatments;
	}
	
	public void addTestBodyStatments(String testBodyStatment) {
		this.testBodyStatments.add(testBodyStatment);
	}

	public String getOrignalMethodName() {
		return orignalMethodName;
	}

	public void setOrignalMethodName(String orignalMethodName) {
		this.orignalMethodName = orignalMethodName;
	}
	
	public ArrayList<String> getTestMethodCreationType() {
		return testMethodCreationType;
	}

	public void setTestMethodCreationType(ArrayList<String> testMethodCreationType) {
		this.testMethodCreationType = testMethodCreationType;
	} // means if_else_if breaks in if else if array

	public Map<String, Set<String>> getMockedObjects() {
		return mockedObjects;
	}

	public void setMockedObjects(Map<String, Set<String>> mockedObjects) {
		this.mockedObjects = mockedObjects;
	}

	public void addMockedObjects(String refernceType,String mockedObjects) {
		if(!this.mockedObjects.containsKey(refernceType)) {
			HashSet<String> hashSet= new HashSet<String>();
			hashSet.add(mockedObjects);
			this.mockedObjects.put(refernceType, hashSet);
		}
		else {
			this.mockedObjects.get(refernceType).add(mockedObjects);
		}
	}
	
	public Map<String, ArrayList<String>> getMockedRefernce() {
		return mockedRefernce;
	}

	public void setMockedRefernce(Map<String, ArrayList<String>> mockedRefernce) {
		this.mockedRefernce = mockedRefernce;
	}
	
	public void addMockedRefernce(String key, ArrayList<String> mockedRefernce) {
		this.mockedRefernce.put(key, mockedRefernce);
	}
	
	
	public void addMockedRefernce(String refType,String vairableName,boolean ejectMock,boolean spy,boolean mock) {
		ArrayList<String> annotatedObject = new ArrayList<String>();
		if(refType!=null && vairableName!=null && !Utils.isCollectionType(refType)) {
		
			if(!this.mockedObjects.containsKey(refType)) {
					
				if(ejectMock)
					annotatedObject.add(Constants.EJECT_MOCK);
				if(spy)
					annotatedObject.add(Constants.SPY);
				if(mock)
					annotatedObject.add(Constants.MOCK);
				
				annotatedObject.add("public "+refType+" " +vairableName + " ;");
				addMockedRefernce(refType, annotatedObject);
			}
			addMockedObjects(refType , vairableName);
		}
	}
	
	public boolean isMockedObjectsPresent(String object, String mockObject) {
		
		for(Entry<String, Set<String>> entry : this.mockedObjects.entrySet()) {
			Set<String>  str = entry.getValue();
			if(str.contains(mockObject)) {
				return true;
			}
		}

		if(this.mockedObjects.containsKey(object)) {
			for(Entry<String, Set<String>> entry : this.mockedObjects.entrySet()) {
				Set<String>  str = entry.getValue();
				if(!str.contains(mockObject)) {
					addMockedObjects(object, mockObject);
				}
			}
			return true;
		}
		return false;
	}
	
	public boolean checkForCollectionObjectisPresentorNot(String object) {
		boolean flag =false;
		for (JebraStmts jebraStmt : this.getJebraStmts()) {
			if(jebraStmt!=null) {
				flag = jebraStmt.isCollectionObject(object);
				if(flag) {
					return true;
				}
			}
		}
		return flag;
	}
	
	
	public boolean CheckMethodContainsStatement(String str) {
		boolean checkFlag = false;
		for (JebraStmts jebraStmts : jebraStmts) {
			if(jebraStmts!=null) {
				checkFlag = jebraStmts.CheckIfEqual(str);
				if(checkFlag) {
					break;
				}
			}
		}
		return checkFlag;
	}
	
	public String getReturnOutput() {
		return returnOutput;
	}

	public void setReturnOutput(String returnOutput) {
		this.returnOutput = returnOutput;
	}
}
