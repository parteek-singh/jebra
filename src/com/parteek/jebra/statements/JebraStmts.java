package com.parteek.jebra.statements;

import java.util.ArrayList;
import java.util.List;

import com.parteek.jebra.JebraCollections;

import japa.parser.ast.expr.BooleanLiteralExpr;
import japa.parser.ast.expr.CharLiteralExpr;
import japa.parser.ast.expr.DoubleLiteralExpr;
import japa.parser.ast.expr.Expression;
import japa.parser.ast.expr.IntegerLiteralExpr;
import japa.parser.ast.expr.LongLiteralExpr;
import japa.parser.ast.expr.NullLiteralExpr;
import japa.parser.ast.expr.StringLiteralExpr;

public class JebraStmts implements Cloneable{

	private ArrayList<Integer> lineNumber = new ArrayList<Integer>();
	private List<String> bodyStmts = new ArrayList<String>();
	private ArrayList<JebraCollections> anyCollectionObject = new ArrayList<JebraCollections>(); // if this statement contains collection object.
	private ArrayList<JebraCollections> anyNewObject = new ArrayList<JebraCollections>(); // if this statement contains new Java Class object.
	private JebraStmts origStmt;
	private String operator;
	private int index = -1;
	private String returnType;
	private String returnObject;
	private String returnValue;
	private String ConditionType; //pass or fail
	
	private String stmt;
	private Expression expr;
	
	private String tryCatchType;
	private Boolean throwExcpetion;
	private String left;
	private String right;
	private int ifElseIndex = -1; // this is related to if else .but this need to be here to calculate this stmt
									// belongs to which if and else

	public JebraStmts() {
		// TODO Auto-generated constructor stub
		ConditionType = "undefined";
		
	}
	public void setLeft(String left) {
		this.left = left;
	}

	public String getLeft() {
		return left;
	}

	public void setRight(String right) {
		this.right = right;
	}

	public String getRight() {
		return right;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getOperator() {
		return operator;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public int getIndex() {
		return index;
	}

	public void setReturnType(String returnType) {
		this.returnType = returnType;
	}

	public String getReturnType() {
		return returnType;
	}

	public void setStmt(String stmt) {
		this.stmt = stmt;
	}

	public String getStmt() {
		return stmt;
	}

	public void setExpr(Expression expr) {
		this.expr = expr;
	}

	public Expression getExpr() {
		return expr;
	}

	public void setIfElseIndex(int ifElseIndex) {
		this.ifElseIndex = ifElseIndex;
	}

	public Integer getIfElseIndex() {
		return ifElseIndex;
	}

	public void setLineNumber(ArrayList<Integer> lineNumber) {
		this.lineNumber = lineNumber;
	}

	public void addLineNumber(Integer lineNumber) {
		this.lineNumber.add(lineNumber);
	}

	public ArrayList<Integer> getLineNumber() {
		return lineNumber;
	}

	public void setBodyStmts(ArrayList<String> bodyStmts) {
		this.bodyStmts = bodyStmts;
	}

	public List<String> getBodyStmts() {
		return bodyStmts;
	}

	public void setOrigStmt(JebraStmts origStmt) {
		this.origStmt = origStmt;
	}

	public JebraStmts getOrigStmt() {
		return origStmt;
	}

	public ArrayList<JebraCollections> getAnyNewObject() {
		return anyNewObject;
	}

	public void setAnyNewObject(ArrayList<JebraCollections> anyNewObject) {
		this.anyNewObject = anyNewObject;
	}
	
	public void addAnyNewObject(JebraCollections anyNewObject) {
		if(anyNewObject != null)
			this.anyNewObject.add(anyNewObject);
	}
	
	public ArrayList<JebraCollections> getAnyCollectionObject() {
		return anyCollectionObject;
	}

	public void setAnyCollectionObject(ArrayList<JebraCollections> anyCollectionObject) {
		this.anyCollectionObject = anyCollectionObject;
	}
	
	public void addAnyCollectionObject(JebraCollections anyCollectionObject) {
		if(anyCollectionObject != null)
			this.anyCollectionObject.add(anyCollectionObject);
	}
	
	public boolean isCollectionObject(String object) {
		boolean flag = false;
		if(this.anyCollectionObject != null) {
			for (JebraCollections jebraCollections : anyCollectionObject) {
				if(jebraCollections!=null) {
					flag = jebraCollections.getObjName().equals(object);
					if(flag) {
						return flag;
					}
				}
			}
		}
		return flag;
	}

	public String getTryCatchType() {
		return tryCatchType;
	}

	public void setTryCatchType(String tryCatchType) {
		this.tryCatchType = tryCatchType;
	}

	public String getReturnValue() {
		return returnValue;
	}

	public void setReturnValue(String returnValue) {
		this.returnValue = returnValue;
	}

	public String getConditionType() {
		return ConditionType;
	}

	public void setConditionType(String conditionType) {
		ConditionType = conditionType;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.stmt;
	}
	
	public boolean CheckIfEqual(String str) {
		boolean checkFlag = false;
		if(str!=null) {
			if(str.contains(this.stmt)) {
				checkFlag = true;
			}
		}
		return checkFlag;
	}

	public Boolean getThrowExcpetion() {
		return throwExcpetion;
	}

	public void setThrowExcpetion(Boolean throwExcpetion) {
		this.throwExcpetion = throwExcpetion;
	}

	public String getReturnObject() {
		return returnObject;
	}

	public void setReturnObject(String returnObject) {
		this.returnObject = returnObject;
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}
	
	public String getObjectType(Object obj) {
		
		if(obj instanceof IntegerLiteralExpr) {
			return "Integer";
		}
		else if(obj instanceof StringLiteralExpr) {
			return "String";
		}
		else if(obj instanceof DoubleLiteralExpr) {
			return "Double";
		}
		else if(obj instanceof LongLiteralExpr) {
			return "Long";
		}
		else if(obj instanceof CharLiteralExpr) {
			return "Char";
		}
		else if(obj instanceof BooleanLiteralExpr) {
			return "Boolean";
		}
		else if(obj instanceof NullLiteralExpr) {
			return "NULL_OBJECT";
		}
		else {
			return null;
		}
	}
}
// public class JebraStmts extends JebraStmtParent{
//
// private int index=-1;
// private String returnType;
//
// private String operator;
// private String stmt;
// private Expression expr;
// private IfStmt ifStmts;
// private String ifStmtType;
// private boolean isIfStmts;
// private JebraStmts origStmt;
// private int ifElseIndex = -1;
// private ArrayList<Integer> lineNumber =new ArrayList<Integer>();
// private List<String> bodyStmts =new ArrayList<String>();
//
// public void setIfStmts(IfStmt ifStmts) {
// this.ifStmts = ifStmts;
// }
// public IfStmt getIfStmts() {
// return ifStmts;
// }
// public void setIfStmts(boolean isIfStmts) {
// this.isIfStmts = isIfStmts;
// }
// public boolean isIfStmts() {
// return isIfStmts;
// }
// public void setIfStmtType(String ifStmtType) {
// this.ifStmtType = ifStmtType;
// }
// public String getIfStmtType() {
// return ifStmtType;
// }
// public void setIfElseIndex(int ifElseIndex) {
// this.ifElseIndex = ifElseIndex;
// }
// public int getIfElseIndex() {
// return ifElseIndex;
// }
//
// public void setLineNumber(ArrayList<Integer> lineNumber) {
// this.lineNumber = lineNumber;
// }
// public void addLineNumber(Integer lineNumber) {
// this.lineNumber.add(lineNumber);
// }
// public ArrayList<Integer> getLineNumber() {
// return lineNumber;
// }
// public void setBodyStmts(ArrayList<String> bodyStmts) {
// this.bodyStmts = bodyStmts;
// }
// public List<String> getBodyStmts() {
// return bodyStmts;
// }
// public void setOrigStmt(JebraStmts origStmt) {
// this.origStmt = origStmt;
// }
// public JebraStmts getOrigStmt() {
// return origStmt;
// }
//
//
// }
