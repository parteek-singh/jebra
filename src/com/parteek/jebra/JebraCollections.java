package com.parteek.jebra;

public class JebraCollections implements Cloneable{

	private String type; /// can be ArrayList,Set Map etc
	private String returnType; // type of objects in Collection
	private String ObjName; // collection object name
	private String methodCallName; // the method which have created it
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getReturnType() {
		return returnType;
	}
	public void setReturnType(String returnType) {
		this.returnType = returnType;
	}
	public String getObjName() {
		return ObjName;
	}
	public void setObjName(String objName) {
		ObjName = objName;
	}
	public String getMethodCallName() {
		return methodCallName;
	}
	public void setMethodCallName(String methodCallName) {
		this.methodCallName = methodCallName;
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}
}
