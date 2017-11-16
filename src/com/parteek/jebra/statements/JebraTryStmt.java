package com.parteek.jebra.statements;

import java.util.ArrayList;

import japa.parser.ast.stmt.BlockStmt;
import japa.parser.ast.stmt.CatchClause;
import japa.parser.ast.stmt.Statement;
import japa.parser.ast.stmt.TryStmt;
import util.JebraStatementFactory;

public class JebraTryStmt extends JebraStmts {

	private int lineStart;
	private int lineEnd;
	private String catchExpectionType;
	private String catchExpectionObject;
	
	private TryStmt tryOrignalStmt;	
	private BlockStmt tryBody;
	private BlockStmt finallyBody;
	
	private ArrayList<CatchClause> catchBody;	
	private ArrayList<JebraStmts> tryStmts;	
	private ArrayList<ArrayList<JebraStmts>> catchStmts;	
	private ArrayList<JebraStmts> finallyStmts;	
	
	
	public JebraTryStmt() {
		setCatchBody(new ArrayList<CatchClause>());
		setTryStmts(new ArrayList<JebraStmts>());
		setCatchStmts(new ArrayList<ArrayList<JebraStmts>>());
		setFinallyStmts(new ArrayList<JebraStmts>());
	}
	
	public void initObject(TryStmt tryStmt) {
		this.setTryOrignalStmt(tryStmt);
		this.setTryBody(tryStmt.getTryBlock());
		this.setFinallyBody(tryStmt.getFinallyBlock());
		this.setIndex(tryStmt.getBeginLine());
		this.setStmt(tryStmt.toString());
	//	jebraTryStmt.setIfElseIndex(ifElse);
		for (CatchClause clause : tryStmt.getCatchs()) {
			if (clause != null) {
				this.addCatchBody(clause);
			}
		}

		this.setLineStart(tryStmt.getBeginLine());
		this.setLineEnd(tryStmt.getEndLine());
		
		
		// start generating try block
		if (this.getTryBody() != null && this.getTryBody() instanceof BlockStmt) {
			BlockStmt blockStmt = (BlockStmt) this.getTryBody();
			if(blockStmt!=null && blockStmt.getStmts() !=null) {
				for (Statement statement2 : blockStmt.getStmts()) {
					JebraStatementFactory.getInstance().genrateStatements(statement2, this.getTryStmts());
				}
			}
		}

		ArrayList<JebraStmts> tryStmtsInuldedInCatch = new ArrayList<JebraStmts>();
		for (JebraStmts jebraStmts : this.getTryStmts()) {
			if(jebraStmts != null &&  !(jebraStmts instanceof JebraTryStmt)) {
				jebraStmts.setThrowExcpetion(true);
				tryStmtsInuldedInCatch.add(jebraStmts);
			}
		}
		// start generating catch block
		if (this.getCatchBody() != null) {
			for (CatchClause catchClause : this.getCatchBody()) {
				if (catchClause != null && catchClause.getCatchBlock() != null
						&& catchClause.getCatchBlock() instanceof BlockStmt) {
					
					BlockStmt blockStmt = (BlockStmt) catchClause.getCatchBlock();
					if(blockStmt!=null && blockStmt.getStmts() !=null) {
						ArrayList<JebraStmts> newCatchBlock = new ArrayList<JebraStmts>();
						newCatchBlock.addAll(tryStmtsInuldedInCatch);
						for (Statement statement2 : blockStmt.getStmts()) {
							JebraStatementFactory.getInstance().genrateStatements(statement2, newCatchBlock);	
						}
						catchStmts.add(newCatchBlock);
					}
				}
			}
		}

		// start generating try block
		if (this.getFinallyBody() != null && this.getFinallyBody() instanceof BlockStmt) {
			BlockStmt blockStmt = (BlockStmt) this.getFinallyBody();
			if(blockStmt!=null && blockStmt.getStmts() !=null) {
				for (Statement statement2 : blockStmt.getStmts()) {
					if(statement2 instanceof TryStmt) {
						TryStmt stmt = (TryStmt) statement2;
						BlockStmt tryblockStmt = (BlockStmt) stmt.getTryBlock();
						if(tryblockStmt!=null && tryblockStmt.getStmts() !=null) {
							for (Statement trystmt : tryblockStmt.getStmts()) {
								JebraStatementFactory.getInstance().genrateStatements(trystmt, this.getFinallyStmts());
							}
						}
					}
					JebraStatementFactory.getInstance().genrateStatements(statement2, this.getFinallyStmts());
				}
			}
		}
	}
	public int getLineStart() {
		return lineStart;
	}
	public void setLineStart(int lineStart) {
		this.lineStart = lineStart;
	}
	public int getLineEnd() {
		return lineEnd;
	}
	public void setLineEnd(int lineEnd) {
		this.lineEnd = lineEnd;
	}
	public String getCatchExpectionType() {
		return catchExpectionType;
	}
	public void setCatchExpectionType(String catchExpectionType) {
		this.catchExpectionType = catchExpectionType;
	}
	public String getCatchExpectionObject() {
		return catchExpectionObject;
	}
	public void setCatchExpectionObject(String catchExpectionObject) {
		this.catchExpectionObject = catchExpectionObject;
	}
	
	public BlockStmt getTryBody() {
		return tryBody;
	}
	
	public void setTryBody(BlockStmt tryBody) {
		this.tryBody = tryBody;
	}
	public ArrayList<CatchClause> getCatchBody() {
		return catchBody;
	}
	public void setCatchBody(ArrayList<CatchClause> catchBody) {
		this.catchBody = catchBody;
	}
	public void addCatchBody(CatchClause catchObj) {
		this.catchBody.add(catchObj);
	}
	
	public BlockStmt getFinallyBody() {
		return finallyBody;
	}
	public void setFinallyBody(BlockStmt finallyBody) {
		this.finallyBody = finallyBody;
	}

	public TryStmt getTryOrignalStmt() {
		return tryOrignalStmt;
	}

	public void setTryOrignalStmt(TryStmt tryOrignalStmt) {
		this.tryOrignalStmt = tryOrignalStmt;
	}

	public ArrayList<JebraStmts> getTryStmts() {
		return tryStmts;
	}

	public void setTryStmts(ArrayList<JebraStmts> tryStmts) {
		this.tryStmts = tryStmts;
	}

	public ArrayList<ArrayList<JebraStmts>> getCatchStmts() {
		return catchStmts;
	}

	public void setCatchStmts(ArrayList<ArrayList<JebraStmts>> catchStmts) {
		this.catchStmts = catchStmts;
	}
	
	public ArrayList<JebraStmts> getFinallyStmts() {
		return finallyStmts;
	}

	public void setFinallyStmts(ArrayList<JebraStmts> finallyStmts) {
		this.finallyStmts = finallyStmts;
	}

	
}
