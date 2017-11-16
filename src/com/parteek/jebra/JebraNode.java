package com.parteek.jebra;

import com.parteek.jebra.statements.JebraStmts;

public class JebraNode {

	private Integer index;
	JebraStmts jebraStmts;

	public JebraNode(int index_ ,JebraStmts stmts) {
		this.index = index_;
		this.jebraStmts = stmts;
	}
	
	public Integer getIndex() {
		return index;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}

	public JebraStmts getJebraStmts() {
		return jebraStmts;
	}

	public void setJebraStmts(JebraStmts jebraStmts) {
		this.jebraStmts = jebraStmts;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Index : "+index + "  statment : " + this.jebraStmts.toString();
	}
}
