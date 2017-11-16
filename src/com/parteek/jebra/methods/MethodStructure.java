package com.parteek.jebra.methods;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.swing.JOptionPane;

import com.parteek.jebra.GenrateTestCase;
import com.parteek.jebra.JebraNode;
import com.parteek.jebra.statements.JebraConditonalStmt;
import com.parteek.jebra.statements.JebraIfElseStmts;
import com.parteek.jebra.statements.JebraLoopStmts;
import com.parteek.jebra.statements.JebraStmts;
import com.parteek.jebra.statements.JebraTryStmt;
import com.parteek.jebra.tree.TreeNode;

import japa.parser.ast.body.ConstructorDeclaration;
import japa.parser.ast.stmt.Statement;
import util.Constants;
import util.JebraStatementFactory;
import util.Utils;

public class MethodStructure extends MethodParent {

	private ConstructorDeclaration myConstDeclaration;
	private List<TestMethodStructure> allTestMethods;

	// private ArrayList<String> testMethodCreationType; //means if_else_if breaks
	// in if else if array
	public MethodStructure() {
		setAllTestMethods(new ArrayList<TestMethodStructure>());
	}

	// if you change here you need to make changes in testMethodStruture also
	// (CalulateJebraStamentsForTestMethods() in this method)
	public void readMethodBodyStatments() {
		// int ifElse=0;
		if (getBodyStmts() != null && getBodyStmts().size() > 0) {
			for (Statement statement : getBodyStmts()) {
				statement.getClass().getName().substring(statement.getClass().getName().lastIndexOf('.') + 1);
				if (statement != null) {
					JebraStatementFactory.getInstance().genrateStatements(statement, jebraStmts);
					// genrateStatements(statement, ifElse , false);

					// else if(statement instanceof ForStmt) {
					// createForLoopStmts(statement,ifElse , false);
					// }
					// else if(statement instanceof WhileStmt) {
					// createWhileLoopStmts(statement,ifElse , false);
					// }
					// else if (statement instanceof IfStmt) {
					// IfStmt obj = (IfStmt) statement;
					// createIfStmt(obj,"if",ifElse,null, false);
					// ifElse++;
					// }
				}

			}
		}

	}

	public void initailiseMethodBody(List<Statement> bodyStmts) throws CloneNotSupportedException {
		setBodyStmts(bodyStmts);
		readMethodBodyStatments();
		// setAllTestMethods(getMethodCounts());getPossibleTestMethods
		setAllTestMethods(getPossibleTestMethods());
		// getPossibleTestMethods();
		// genratTreeStructureForTestCases();
		for (TestMethodStructure structure : allTestMethods) {
			GenrateTestCase.getInstance().GenrateTest(structure);
		}

		System.out.println();
		// if(arrayList!=null)
		// for (MethodStructure methodStructure : arrayList) {
		// System.out.println("chekc: "+methodStructure.getMethodDeclationStmt());
		// }

	}

	public ArrayList<TestMethodStructure> getPossibleTestMethods() throws CloneNotSupportedException {
		ArrayList<TestMethodStructure> testMethodList = new ArrayList<TestMethodStructure>();
		List<List<JebraStmts>> methodsStatements = getPossibleTestMethodsStatements();
		/// -------------------- generate testMethods
		int counter = 0;
		for (List<JebraStmts> arrayList : methodsStatements) { // methodsStatements contains methods data
			String val = String.valueOf(counter++);
			Set<Integer> lineInMethod = new HashSet<Integer>();// need to find this
			lineInMethod.add(0);
			TestMethodStructure basicTestCase = new TestMethodStructure();
			basicTestCase.initialise(this, val, arrayList, this.getMyClass(), lineInMethod);
			testMethodList.add(basicTestCase);
		}
		return testMethodList;
	}

	/*
	 * 
	 * 1  11  111  112  1111  1112  1113  11111  11112
	 * 1  11  111  1111 11111 11112 1112  1113 	112
	 * */
	private TreeNode<Integer> getNodeFromTree(List<TreeNode<Integer>> allElements, Integer nodeId) {

		for (TreeNode<Integer> treeNode : allElements) {
			if (treeNode != null) {
				if (treeNode.getData().equals(nodeId)) {
					return treeNode;
				}
			}
		}
		return null;
	}
	
	public boolean IsNodeChildPresent(Integer nodeId , List<Integer> nodeIds) {
		for (Integer integer : nodeIds) {
			if(integer.equals(nodeId)) {
				return true;
			}
		}
		return false;
	}
	
	public void getNextChild(Integer nodeId , List<Integer> nodeIds,List<Integer> groupNodes , List<List<Integer>> groupOfNodeIds) {

		{
			groupNodes.add(nodeId);

			Integer childNode = (nodeId * 10) + 1;
			boolean checkFlag = IsNodeChildPresent(childNode, nodeIds);
			while (checkFlag) {
				groupNodes.add(childNode);
				childNode = (childNode * 10) + 1;
				checkFlag = IsNodeChildPresent(childNode, nodeIds);
				if (!checkFlag) {
					//groupOfNodeIds.add(groupNodes);
				}
			}
			if(!checkFlag && Utils.isNotEmpty(groupNodes)) {
				groupOfNodeIds.add(groupNodes);
			}
		}
		// check for siblings
		for (int i = groupNodes.size() - 1 ; i >= 0; i--) {

			boolean checkFlagForSibling = false;
			Integer childNode = groupNodes.get(i);
			Integer siblingNode = childNode + 1;
			checkFlagForSibling = IsNodeChildPresent(siblingNode, nodeIds);
			if (checkFlagForSibling) {
				List<Integer> newGroupNodes = new ArrayList<Integer>(groupNodes);
				newGroupNodes.remove(i);
				getNextChild(siblingNode, nodeIds, newGroupNodes ,groupOfNodeIds);
				System.out.println("");
			}
		}
	}
	
	public List<List<JebraStmts>> getPossibleTestMethodsStatements() throws CloneNotSupportedException {
		if (isContructor())
			return null;
		TreeNode<Integer> root = genratTreeStructureForTestCases();

		List<List<JebraStmts>> methodsStatements = new ArrayList<List<JebraStmts>>();
		List<TreeNode<Integer>> allElements = root.getAllElements();
		Integer lastIndex = -1;
		List<TreeNode<Integer>> sortedElements = new ArrayList<TreeNode<Integer>>();
		List<Integer> nodeIds = new ArrayList<Integer>();
		List<Integer> sortedNodeIds = new ArrayList<Integer>();
		List<List<Integer>> groupOfNodeIds = new ArrayList<List<Integer>>();
		
		for (TreeNode<Integer> treeNode : allElements) {
			if (treeNode != null) {
				Integer currIndexData = treeNode.getData();
				nodeIds.add(currIndexData);
			}
		}
		List<Integer> groupIds = new ArrayList<Integer>();
		getNextChild(nodeIds.get(0), nodeIds ,groupIds , groupOfNodeIds);
		for (List<Integer> list : groupOfNodeIds) {
			if(Utils.isNotEmpty(list)) {
				for (Integer integer : list) {
					if(integer != null && !sortedNodeIds.contains(integer)) {
						sortedNodeIds.add(integer);
					}
				}
			}
		}
		
		for (Integer nodeId : sortedNodeIds) {
			if(nodeId != null) {
				TreeNode<Integer> node = getNodeFromTree(allElements, nodeId);
				if(node != null) {
					sortedElements.add(node);
				}
			}
		}
		
		if (Utils.isNotEmpty(sortedElements)) {
			Map<Integer, List<JebraStmts>> jebraStmtsGroup = new LinkedHashMap<Integer, List<JebraStmts>>();

			for (TreeNode<Integer> treeNode : sortedElements) {
				if (treeNode != null) {
					Integer currIndexData = treeNode.getData();
					boolean checkFlag = false;
					if (lastIndex.equals(-1)) {
						checkFlag = true;
					} else if (currIndexData.equals(lastIndex)) {
						checkFlag = true;
					} else if (currIndexData > lastIndex) {
						Integer parentIndex = currIndexData / 10;
						if (lastIndex.equals(parentIndex)) { // 111 contains as a child 1111 and 1112
							checkFlag = true;
						} else {
							// currIndexData 112 lastindex 1112 parentindex 11
							List<JebraStmts> methodConatinedStmts = new ArrayList<JebraStmts>();
							Collection<List<JebraStmts>> allValues = jebraStmtsGroup.values();
							for (List<JebraStmts> list : allValues) {
								methodConatinedStmts.addAll(list);
							}
							methodsStatements.add(methodConatinedStmts);

							// removing all the siblings of this index
							Set<Integer> allKeys = jebraStmtsGroup.keySet();
							Iterator<Integer> itr = allKeys.iterator();
							while (itr.hasNext()) {
								Integer key = itr.next();
								if (key > parentIndex) {
									itr.remove();
								}
							}
							checkFlag = true;
						}
					} else if (currIndexData < lastIndex) {
						Integer parentIndex = currIndexData / 10;
						if (lastIndex.equals(parentIndex)) { // 111 contains as a child 1111 and 1112
							checkFlag = true;
						} else {
							// currIndexData 112 lastindex 1112 parentindex 11
							List<JebraStmts> methodConatinedStmts = new ArrayList<JebraStmts>();
							Collection<List<JebraStmts>> allValues = jebraStmtsGroup.values();
							for (List<JebraStmts> list : allValues) {
								methodConatinedStmts.addAll(list);
							}
							methodsStatements.add(methodConatinedStmts);

							// removing all the siblings of this index
							Set<Integer> allKeys = jebraStmtsGroup.keySet();
							Iterator<Integer> itr = allKeys.iterator();
							while (itr.hasNext()) {
								Integer key = itr.next();
								if (key > parentIndex) {
									itr.remove();
								}
							}
							checkFlag = true;
						}
					}
					if (checkFlag) {
						List<JebraStmts> valueStatements = new ArrayList<JebraStmts>();
						if (jebraStmtsGroup.containsKey(currIndexData)) {
							List<JebraStmts> previousStms = jebraStmtsGroup.get(currIndexData);
							valueStatements.addAll(previousStms);
						}
						valueStatements.addAll(treeNode.getMyList());
						jebraStmtsGroup.put(currIndexData, valueStatements);
					}
					lastIndex = currIndexData;
				}
			}
			// last method to add
			List<JebraStmts> methodConatinedStmts = new ArrayList<JebraStmts>();
			Collection<List<JebraStmts>> allValues = jebraStmtsGroup.values();
			for (List<JebraStmts> list : allValues) {
				methodConatinedStmts.addAll(list);
			}
			methodsStatements.add(methodConatinedStmts);
		}
		return methodsStatements;
	}

	public TreeNode<Integer> genratTreeStructureForTestCases() throws CloneNotSupportedException {
		int index = 1;
		TreeNode<Integer> root = new TreeNode<Integer>(index);
		ArrayList<JebraNode> jebraNodeList = getPossibleCombinationOfJebrStatements();
		for (JebraNode jebraNode : jebraNodeList) {
			if (jebraNode != null) {
				if (jebraNode.getIndex().equals(index)) {
					root.addToMyList(jebraNode.getJebraStmts());
				} else {

					TreeNode<Integer> foundNode = root.findTreeNode(jebraNode.getIndex());
					if (foundNode != null) {
						foundNode.addToMyList(jebraNode.getJebraStmts());
					} else {
						int parentIndex = jebraNode.getIndex() / 10;
						TreeNode<Integer> foundParentNode = root.findTreeNode(parentIndex);
						if (foundParentNode != null) {
							TreeNode<Integer> node = foundParentNode.addChild(jebraNode.getIndex());
							if (node != null) {
								node.addToMyList(jebraNode.getJebraStmts());
							}
						}
					}
				}
			}
		}

		return root;
	}

	public ArrayList<JebraNode> getPossibleCombinationOfJebrStatements() throws CloneNotSupportedException {
		if (isContructor())
			return null;
		int index = 1;
		ArrayList<JebraNode> jebraNodeList = new ArrayList<JebraNode>();
		if (this.jebraStmts != null && this.jebraStmts.size() > 0) {
			for (int i = 0; i < this.jebraStmts.size(); i++) {
				JebraStmts orgStmts = this.jebraStmts.get(i);
				if (orgStmts != null) {

					if (orgStmts instanceof JebraIfElseStmts) {
						readMapFor_IfElse(index, orgStmts, jebraNodeList);
					} else if (orgStmts instanceof JebraLoopStmts) {
						readMapFor_Loop(index, orgStmts, jebraNodeList);
					} else if (orgStmts instanceof JebraTryStmt) {
						readMapFor_TryCatch(index, orgStmts, jebraNodeList);
					} else if (orgStmts instanceof JebraConditonalStmt) {
						readMapFor_ConditionalStmt(index, orgStmts, jebraNodeList);
					} else {
						JebraNode jebraNode = new JebraNode(index, orgStmts);
						jebraNodeList.add(jebraNode);
					}
				}
			}
		}

		return jebraNodeList;

	}

	private void readMapCheckpoint(int index, JebraStmts stmts, ArrayList<JebraNode> jebraNodeList) throws CloneNotSupportedException {
		if (stmts != null) {
			if (stmts instanceof JebraIfElseStmts) {
				readMapFor_IfElse(index, stmts, jebraNodeList);
			} else if (stmts instanceof JebraLoopStmts) {
				readMapFor_Loop(index, stmts, jebraNodeList);
			} else if (stmts instanceof JebraTryStmt) {
				readMapFor_TryCatch(index, stmts, jebraNodeList);
			} else if (stmts instanceof JebraConditonalStmt) {
				readMapFor_ConditionalStmt(index, stmts, jebraNodeList);
			} else {
				JebraNode jebraNode = new JebraNode(index, stmts);
				jebraNodeList.add(jebraNode);
			}
		}
	}

	private void readMapFor_IfElse(int index, JebraStmts orgStmts, ArrayList<JebraNode> jebraNodeList) throws CloneNotSupportedException {
		JebraIfElseStmts ifElseStmts = (JebraIfElseStmts) orgStmts;
		if (ifElseStmts != null) {
			index = 10 * index;
			if (Utils.isNotEmpty(ifElseStmts.getThenStmts())) {
				index++; // INCRESE THE INDEX (MEANS CHANGE THE CHILD INDEX IN MAP)
				for (int i = 0; i < ifElseStmts.getThenStmts().size(); i++) {
					JebraStmts stmts = ifElseStmts.getThenStmts().get(i);
					if(stmts != null) {
						JebraStmts  clonedObject = (JebraStmts) stmts.clone();
						if(i == 0 && ifElseStmts.getConditionalStmt() != null ) { // condtional statement
							clonedObject.setConditionType(Constants.PASS);
							clonedObject.setReturnValue(stmts.getReturnObject());
						}
						readMapCheckpoint(index, clonedObject, jebraNodeList);
					}
				}
			}

			if (Utils.isNotEmpty(ifElseStmts.getElseIfStmts())) {
				index++; // INCRESE THE INDEX (MEANS CHANGE THE CHILD INDEX IN MAP)
				for (int i = 0; i < ifElseStmts.getElseIfStmts().size(); i++) {
					JebraStmts stmts = ifElseStmts.getElseIfStmts().get(i);
					if(stmts != null) {
						JebraStmts  clonedObject = (JebraStmts) stmts.clone();
						if(i == 0 && ifElseStmts.getConditionalStmt() != null) { // condtional statement
							clonedObject.setConditionType(Constants.FAIL);
							clonedObject.setReturnValue(stmts.getReturnObject() + Utils.getRandomNumber());
						}
						if(i == 1 && ifElseStmts.getElseIfConditionalStmt() != null) { // condtional statement
							clonedObject.setConditionType(Constants.PASS);
							clonedObject.setReturnValue(stmts.getReturnObject());
						}
						readMapCheckpoint(index, clonedObject, jebraNodeList);
					}
				}
			}

			if (Utils.isNotEmpty(ifElseStmts.getElseStmts())) {
				index++; // INCRESE THE INDEX (MEANS CHANGE THE CHILD INDEX IN MAP)
				for (int i = 0; i < ifElseStmts.getElseStmts().size(); i++) {
					JebraStmts stmts = ifElseStmts.getElseStmts().get(i);
					if(stmts != null) {
						JebraStmts  clonedObject = (JebraStmts) stmts.clone();
						if(i == 0 && ifElseStmts.getConditionalStmt() != null) { // condtional statement
							clonedObject.setConditionType(Constants.FAIL);
							clonedObject.setReturnValue(stmts.getReturnObject() + Utils.getRandomNumber());
						}
						if(i == 1 && ifElseStmts.getElseIfConditionalStmt() != null) { // condtional statement
							clonedObject.setConditionType(Constants.FAIL);
							clonedObject.setReturnValue(stmts.getReturnObject() + Utils.getRandomNumber());
						}
						readMapCheckpoint(index, clonedObject, jebraNodeList);
					}
				}
			}
		}
	}

	private void readMapFor_TryCatch(int index, JebraStmts orgStmts, ArrayList<JebraNode> jebraNodeList) throws CloneNotSupportedException {
		JebraTryStmt jebraTryStmt = (JebraTryStmt) orgStmts;
		if (jebraTryStmt != null) {
			int orignalIndex = index;
			index = 10 * index;
			if (Utils.isNotEmpty(jebraTryStmt.getTryStmts())) {
				index++;
				ArrayList<JebraStmts> jebraStmts = jebraTryStmt.getTryStmts();
				for (JebraStmts stmts : jebraStmts) {
					readMapCheckpoint(index, stmts, jebraNodeList);
				}
			}

			if (Utils.isNotEmpty(jebraTryStmt.getCatchStmts())) {
				for (ArrayList<JebraStmts> catchStmts : jebraTryStmt.getCatchStmts()) {
					index++;
					if (Utils.isNotEmpty(catchStmts)) {
						for (JebraStmts stmts : catchStmts) {
							readMapCheckpoint(index, stmts, jebraNodeList);
						}
					}
				}
			}
			if (Utils.isNotEmpty(jebraTryStmt.getFinallyStmts())) {
				// for finally we need to use special index noted as - minus (-1)
				ArrayList<JebraStmts> jebraStmts = jebraTryStmt.getFinallyStmts();
				for (JebraStmts stmts : jebraStmts) {
					readMapCheckpoint(orignalIndex, stmts, jebraNodeList);
				}
			}
		}
	}

	private void readMapFor_Loop(int index, JebraStmts orgStmts, ArrayList<JebraNode> jebraNodeList) throws CloneNotSupportedException {
		JebraLoopStmts jebraLoopStmts = (JebraLoopStmts) orgStmts;
		if (jebraLoopStmts != null && Utils.isNotEmpty(jebraLoopStmts.getContainedStmts())) {
			ArrayList<JebraStmts> containedStmts = jebraLoopStmts.getContainedStmts();
			for (JebraStmts stmts : containedStmts) {
				readMapCheckpoint(index, stmts, jebraNodeList);
			}
		}
	}

	private void readMapFor_ConditionalStmt(int index, JebraStmts orgStmts, ArrayList<JebraNode> jebraNodeList) throws CloneNotSupportedException {
		JebraConditonalStmt conditonalStmt = (JebraConditonalStmt) orgStmts;
		if (conditonalStmt != null) {
			index = 10 * index;
			
			if (Utils.isNotEmpty(conditonalStmt.getThenStatement())) {
				index++;
				ArrayList<JebraStmts> containedStmts = conditonalStmt.getThenStatement();
				for (int i = 0; i < containedStmts.size(); i++) {
					JebraStmts stmts = containedStmts.get(i);
					if(stmts != null) {
						JebraStmts  clonedObject = (JebraStmts) stmts.clone();
						if( i == 0) {
							clonedObject.setConditionType(Constants.PASS);
							
						}
						else if( i == 1 ) {
							System.out.println();
//							clonedObject.setReturnObject(conditonalStmt.getMainReturnObjectName());
//							clonedObject.setReturnType(conditonalStmt.getMainReturnType());
						}
						else {
							JOptionPane.showMessageDialog(null, "Need to handle this Future development (contact Dev MethodStruture.java L-490)");
						}
						readMapCheckpoint(index, clonedObject, jebraNodeList);
					}
				}
			}
			
			if (Utils.isNotEmpty(conditonalStmt.getElseStmt())) {
				index++;
				ArrayList<JebraStmts> containedStmts = conditonalStmt.getElseStmt();
				for (int i = 0; i < containedStmts.size(); i++) {
					JebraStmts stmts = containedStmts.get(i);
					if(stmts != null) {
						JebraStmts  clonedObject = (JebraStmts) stmts.clone();
						if( i == 0) {
							clonedObject.setConditionType(Constants.FAIL);
							System.out.println();
						}
						readMapCheckpoint(index, clonedObject, jebraNodeList);
					}
				}
			}
			
		}
	}
	// public ArrayList<TestMethodStructure> getPossibleTestMethods1(){
	// if(isContructor())
	// return null;
	// ArrayList<TestMethodStructure> testMethodList = new
	// ArrayList<TestMethodStructure>();
	// ArrayList<JebraStmts> jebraStmtsGroup = new ArrayList<JebraStmts>();
	// ArrayList<ArrayList<JebraStmts>> methodsStatements = new
	// ArrayList<ArrayList<JebraStmts>>();
	// if(this.jebraStmts!=null && this.jebraStmts.size()>0){
	// for (int i = 0; i < this.jebraStmts.size(); i++) {
	// JebraStmts orgStmts = this.jebraStmts.get(i);
	// if(orgStmts!=null) {
	// if(orgStmts instanceof JebraIfElseStmts){
	// JebraIfElseStmts stmts = (JebraIfElseStmts) orgStmts;
	// if(stmts != null) {
	// ArrayList<ArrayList<JebraStmts>> arrayList =
	// genrateTestMethodForIfElseStmt(stmts);
	// if (Utils.isNotEmpty(arrayList)) {
	// for (ArrayList<JebraStmts> arrayList2 : arrayList) {
	// ArrayList<JebraStmts> newMethod = new ArrayList<JebraStmts>();
	// newMethod.addAll(jebraStmtsGroup);
	// newMethod.addAll(arrayList2);
	// methodsStatements.add(newMethod);
	// }
	// }
	// if(!checkElseStmtContainsAnyIfStmts(stmts)) {
	// ArrayList<JebraStmts> newMethod = new ArrayList<JebraStmts>();
	// newMethod.addAll(jebraStmtsGroup);
	// newMethod.addAll(stmts.getElseStmts());
	// methodsStatements.add(newMethod);
	// }
	// }
	// }
	// else if(orgStmts instanceof JebraLoopStmts) {
	// JebraLoopStmts stmts = (JebraLoopStmts) orgStmts;
	// if(stmts != null) {
	// ArrayList<JebraStmts> allJebraStmtList =
	// genrateTestMethodForeachLoopStmt(stmts);
	// if (Utils.isNotEmpty(allJebraStmtList)) {
	// for (JebraStmts jebraStmts : allJebraStmtList) {
	// if (jebraStmts != null)
	// if (methodsStatements.size() > 0) {
	// for (ArrayList<JebraStmts> arrayList : methodsStatements) {
	// arrayList.add(jebraStmts);
	// }
	// } else {
	// jebraStmtsGroup.add(jebraStmts);
	// }
	// }
	// }
	// }
	//// lineInMethod.add(stmts.getLineNumber().get(0));
	//// lineInMethod.add(stmts.getLineNumber().get(stmts.getLineNumber().size()-1));
	//// bodyStmts.add(orgStmts.getStmt());
	//// jebraStmtsGroup.add(orgStmts);
	// }
	// else if(orgStmts instanceof JebraTryStmt) {
	// JebraTryStmt stmts = (JebraTryStmt) orgStmts;
	// if(stmts != null) {
	// ArrayList<ArrayList<JebraStmts>> arrayList =
	// genrateTestMethodForTryCatchStmt(stmts);
	// for (ArrayList<JebraStmts> arrayList2 : arrayList) {
	// ArrayList<JebraStmts> newMethod = new ArrayList<JebraStmts>();
	// newMethod.addAll(jebraStmtsGroup);
	// newMethod.addAll(arrayList2);
	// methodsStatements.add(newMethod);
	// }
	// }
	// }
	// else {
	// if(methodsStatements.size() > 0) {
	// for (ArrayList<JebraStmts> arrayList : methodsStatements) {
	// arrayList.add(orgStmts);
	// }
	// }else {
	// jebraStmtsGroup.add(orgStmts);
	// }
	// }
	// }
	// }
	// }
	//
	//
	// ///-------------------- generate testMethods
	// int counter = 0;
	// for (ArrayList<JebraStmts> arrayList : methodsStatements) { //
	// methodsStatements contains methods data
	// String val = String.valueOf(counter++);
	// Set<Integer> lineInMethod = new HashSet<Integer>() ;// need to find this
	// lineInMethod.add(0);
	// TestMethodStructure basicTestCase = new TestMethodStructure();
	// basicTestCase.initialise(this, val, arrayList, this.getMyClass(),
	// lineInMethod);
	// testMethodList.add(basicTestCase);
	// }
	// return testMethodList;
	// }

	// we need this because we are not able to get the else part during fetching
	private boolean checkElseStmtContainsAnyIfStmts(JebraIfElseStmts stmts) {

		if (stmts != null && Utils.isNotEmpty(stmts.getElseStmts())) {
			for (JebraStmts jebraStmts : stmts.getElseStmts()) {
				if (jebraStmts instanceof JebraIfElseStmts) {
					return true;
				}
			}
		}
		return false;

	}

	/********************************************************************************************************************************************
	 * If else related methods
	 ********************************************************************************************************************************************
	 */
	private ArrayList<JebraStmts> genrateTestMethodForeachLoopStmt(JebraLoopStmts stmts) {
		if (stmts != null) {
			System.out.println("");
		}
		return null;
	}

	/**
	 * ********************************************************************************************************************************************
	 * try catch related methods
	 * *******************************************************************************************************************************************
	 */
	private ArrayList<ArrayList<JebraStmts>> genrateTestMethodForTryCatchStmt(JebraTryStmt stmts) {
		ArrayList<ArrayList<JebraStmts>> finalJebraStmtsGroup = new ArrayList<ArrayList<JebraStmts>>();
		ArrayList<ArrayList<JebraStmts>> jebraStmtsGroup = new ArrayList<ArrayList<JebraStmts>>();
		ArrayList<ArrayList<JebraStmts>> jebraFinallyStmtsGroup = new ArrayList<ArrayList<JebraStmts>>();
		genrateTestMethodWithTryStmt(stmts, null, jebraStmtsGroup);
		genrateTestMethodWithCatchStmt(stmts, null, jebraStmtsGroup);
		genrateTestMethodWithFinallyStmt(stmts, null, jebraFinallyStmtsGroup);

		if (Utils.isNotEmpty(jebraFinallyStmtsGroup)) {
			for (ArrayList<JebraStmts> arrayList : jebraFinallyStmtsGroup) {
				for (ArrayList<JebraStmts> mainArrayList : jebraStmtsGroup) {
					ArrayList<JebraStmts> tempJebraStmtsGroup = new ArrayList<JebraStmts>();
					tempJebraStmtsGroup.addAll(arrayList);
					tempJebraStmtsGroup.addAll(mainArrayList);
					finalJebraStmtsGroup.add(tempJebraStmtsGroup);
				}
			}
			return finalJebraStmtsGroup;
		} else {

			return jebraStmtsGroup;
		}
	}

	private void genrateTestMethodWithTryStmt(JebraTryStmt stmts, ArrayList<JebraStmts> jebraStmtsList,
			ArrayList<ArrayList<JebraStmts>> jebraStmtsGroup) {
		boolean flag = false;
		ArrayList<JebraStmts> jebraStmtsSubGroup = new ArrayList<JebraStmts>();
		if (jebraStmtsList != null)
			jebraStmtsSubGroup.addAll(jebraStmtsList);
		if (stmts != null && stmts.getTryStmts() != null && Utils.isNotEmpty(stmts.getTryStmts())) {
			for (JebraStmts jebraStmts : stmts.getTryStmts()) {
				if (jebraStmts != null && !(jebraStmts instanceof JebraTryStmt)) {
					jebraStmtsSubGroup.add(jebraStmts);
				}
			}
			for (JebraStmts jebraStmts : stmts.getTryStmts()) {
				if (jebraStmts != null && jebraStmts instanceof JebraTryStmt) {
					JebraTryStmt tryStmt = (JebraTryStmt) jebraStmts;
					genrateTestMethodWithTryStmt(tryStmt, jebraStmtsSubGroup, jebraStmtsGroup);
					flag = genrateTestMethodWithCatchStmt(tryStmt, jebraStmtsSubGroup, jebraStmtsGroup);
				}
			}
			if (!flag) {
				jebraStmtsGroup.add(jebraStmtsSubGroup);

				if (Utils.isNotEmpty(jebraStmtsList))
					if (stmts != null && stmts.getFinallyStmts() != null && Utils.isNotEmpty(stmts.getFinallyStmts())) {
						for (JebraStmts finalJebraStmts : stmts.getFinallyStmts()) {
							if (finalJebraStmts != null && !(finalJebraStmts instanceof JebraTryStmt)) {
								jebraStmtsSubGroup.add(finalJebraStmts);
							}
						}
						for (JebraStmts jebraStmts : stmts.getFinallyStmts()) {
							if (jebraStmts != null && jebraStmts instanceof JebraTryStmt) {
								JebraTryStmt tryStmt = (JebraTryStmt) jebraStmts;
								genrateTestMethodWithTryStmt(tryStmt, jebraStmtsSubGroup, jebraStmtsGroup);
								genrateTestMethodWithCatchStmt(tryStmt, jebraStmtsSubGroup, jebraStmtsGroup);
							}
						}
					}
			}
		}

	}

	private boolean genrateTestMethodWithCatchStmt(JebraTryStmt stmts, ArrayList<JebraStmts> jebraStmtsList,
			ArrayList<ArrayList<JebraStmts>> jebraStmtsGroup) {
		boolean flag = false;

		if (stmts != null && stmts.getCatchStmts() != null && Utils.isNotEmpty(stmts.getCatchStmts())) {

			for (ArrayList<JebraStmts> jebraStmtsArray : stmts.getCatchStmts()) {
				ArrayList<JebraStmts> jebraStmtsSubGroup = new ArrayList<JebraStmts>();
				if (jebraStmtsList != null)
					jebraStmtsSubGroup.addAll(jebraStmtsList);
				boolean lastCallflag = false;
				for (JebraStmts jebraStmts : jebraStmtsArray) {
					if (jebraStmts != null && jebraStmts instanceof JebraTryStmt) {
						lastCallflag = true;
						JebraTryStmt thenStmts = (JebraTryStmt) jebraStmts;
						genrateTestMethodWithTryStmt(thenStmts, jebraStmtsSubGroup, jebraStmtsGroup);
						genrateTestMethodWithCatchStmt(thenStmts, jebraStmtsSubGroup, jebraStmtsGroup);
					} else {
						jebraStmtsSubGroup.add(jebraStmts);
					}
					flag = true;
				}
				if (jebraStmtsList != null || !lastCallflag)
					jebraStmtsGroup.add(jebraStmtsSubGroup);

				if (Utils.isNotEmpty(jebraStmtsList))
					if (stmts != null && stmts.getFinallyStmts() != null && Utils.isNotEmpty(stmts.getFinallyStmts())) {
						for (JebraStmts jebraStmts : stmts.getFinallyStmts()) {
							if (jebraStmts != null && jebraStmts instanceof JebraTryStmt) {
								JebraTryStmt tryStmt = (JebraTryStmt) jebraStmts;
								genrateTestMethodWithTryStmt(tryStmt, jebraStmtsSubGroup, jebraStmtsGroup);
								genrateTestMethodWithCatchStmt(tryStmt, jebraStmtsSubGroup, jebraStmtsGroup);
							} else {
								jebraStmtsSubGroup.add(jebraStmts);
							}
						}
					}
			}
		}
		return flag;
	}

	private void genrateTestMethodWithFinallyStmt(JebraTryStmt stmts, ArrayList<JebraStmts> jebraStmtsList,
			ArrayList<ArrayList<JebraStmts>> jebraStmtsGroup) {
		boolean flag = false;
		ArrayList<JebraStmts> jebraStmtsSubGroup = new ArrayList<JebraStmts>();
		if (jebraStmtsList != null)
			jebraStmtsSubGroup.addAll(jebraStmtsList);
		if (stmts != null && stmts.getFinallyStmts() != null && Utils.isNotEmpty(stmts.getFinallyStmts())) {
			for (JebraStmts jebraStmts : stmts.getFinallyStmts()) {
				if (jebraStmts != null && jebraStmts instanceof JebraTryStmt) {
					JebraTryStmt thenStmts = (JebraTryStmt) jebraStmts;
					genrateTestMethodWithTryStmt(thenStmts, jebraStmtsSubGroup, jebraStmtsGroup);
					flag = genrateTestMethodWithCatchStmt(thenStmts, jebraStmtsSubGroup, jebraStmtsGroup);
				} else {
					jebraStmtsSubGroup.add(jebraStmts);
				}
			}
			if (!flag)
				jebraStmtsGroup.add(jebraStmtsSubGroup);
		}
	}

	/********************************************************************************************************************************************
	 * If else related methods
	 ********************************************************************************************************************************************
	 */
	private ArrayList<ArrayList<JebraStmts>> genrateTestMethodForIfElseStmt(JebraIfElseStmts stmts) {
		ArrayList<ArrayList<JebraStmts>> jebraStmtsGroup = new ArrayList<ArrayList<JebraStmts>>();
		genrateTestMethodWithThenStmt(stmts, null, jebraStmtsGroup);
		genrateTestMethodWithElseIfStmt(stmts, null, jebraStmtsGroup);
		genrateTestMethodWithElseStmt(stmts, null, jebraStmtsGroup);
		// int index = 0;
		// for (ArrayList<JebraStmts> arrayList : jebraStmtsGroup) {
		// System.out.println("===========================================================
		// : "+index++);
		// for (JebraStmts jebraStmts : arrayList) {
		// System.out.println(jebraStmts.getStmt());
		// }
		// }
		return jebraStmtsGroup;
	}

	private void genrateTestMethodWithThenStmt(JebraIfElseStmts stmts, ArrayList<JebraStmts> jebraStmtsList,
			ArrayList<ArrayList<JebraStmts>> jebraStmtsGroup) {
		boolean flag = false;
		ArrayList<JebraStmts> jebraStmtsSubGroup = new ArrayList<JebraStmts>();
		if (jebraStmtsList != null)
			jebraStmtsSubGroup.addAll(jebraStmtsList);
		if (stmts != null && stmts.getThenStmts() != null && Utils.isNotEmpty(stmts.getThenStmts())) {
			for (JebraStmts jebraStmts : stmts.getThenStmts()) {
				if (jebraStmts != null && jebraStmts instanceof JebraIfElseStmts) {
					JebraIfElseStmts thenStmts = (JebraIfElseStmts) jebraStmts;
					genrateTestMethodWithThenStmt(thenStmts, jebraStmtsSubGroup, jebraStmtsGroup);
					genrateTestMethodWithElseIfStmt(thenStmts, jebraStmtsSubGroup, jebraStmtsGroup);
					flag = genrateTestMethodWithElseStmt(thenStmts, jebraStmtsSubGroup, jebraStmtsGroup);
				} else {
					jebraStmtsSubGroup.add(jebraStmts);
				}
			}
			if (!flag)
				jebraStmtsGroup.add(jebraStmtsSubGroup);
		}
	}

	private void genrateTestMethodWithElseIfStmt(JebraIfElseStmts stmts, ArrayList<JebraStmts> jebraStmtsList,
			ArrayList<ArrayList<JebraStmts>> jebraStmtsGroup) {
		boolean flag = false;
		ArrayList<JebraStmts> jebraStmtsSubGroup = new ArrayList<JebraStmts>();
		if (jebraStmtsList != null)
			jebraStmtsSubGroup.addAll(jebraStmtsList);
		if (stmts != null && stmts.getElseIfStmts() != null && Utils.isNotEmpty(stmts.getElseIfStmts())) {
			for (JebraStmts jebraStmts : stmts.getElseIfStmts()) {
				if (jebraStmts != null && jebraStmts instanceof JebraIfElseStmts) {
					JebraIfElseStmts thenStmts = (JebraIfElseStmts) jebraStmts;
					genrateTestMethodWithThenStmt(thenStmts, jebraStmtsSubGroup, jebraStmtsGroup);
					genrateTestMethodWithElseIfStmt(thenStmts, jebraStmtsSubGroup, jebraStmtsGroup);
					flag = genrateTestMethodWithElseStmt(thenStmts, jebraStmtsSubGroup, jebraStmtsGroup);
				} else {
					jebraStmtsSubGroup.add(jebraStmts);
				}
			}
			if (!flag)
				jebraStmtsGroup.add(jebraStmtsSubGroup);
		}
	}

	private boolean genrateTestMethodWithElseStmt(JebraIfElseStmts stmts, ArrayList<JebraStmts> jebraStmtsList,
			ArrayList<ArrayList<JebraStmts>> jebraStmtsGroup) {
		boolean flag = false;
		ArrayList<JebraStmts> jebraStmtsSubGroup = new ArrayList<JebraStmts>();
		if (jebraStmtsList != null)
			jebraStmtsSubGroup.addAll(jebraStmtsList);
		if (stmts != null && stmts.getElseStmts() != null && Utils.isNotEmpty(stmts.getElseStmts())) {
			for (JebraStmts jebraStmts : stmts.getElseStmts()) {
				if (jebraStmts != null && jebraStmts instanceof JebraIfElseStmts) {
					JebraIfElseStmts thenStmts = (JebraIfElseStmts) jebraStmts;
					genrateTestMethodWithThenStmt(thenStmts, jebraStmtsSubGroup, jebraStmtsGroup);
					genrateTestMethodWithElseIfStmt(thenStmts, jebraStmtsSubGroup, jebraStmtsGroup);
					flag = genrateTestMethodWithElseStmt(thenStmts, jebraStmtsSubGroup, jebraStmtsGroup);
				} else {
					jebraStmtsSubGroup.add(jebraStmts);
				}
				flag = true;
			}
			if (jebraStmtsList != null)
				jebraStmtsGroup.add(jebraStmtsSubGroup);
		}
		return flag;
	}

	// public ArrayList<TestMethodStructure> getMethodCounts(){
	// if(isContructor())
	// return null;
	// ArrayList<TestMethodStructure> structuresList = new
	// ArrayList<TestMethodStructure>();
	// ArrayList<ArrayList<JebraStmts>> JebraStmtsMap = new
	// ArrayList<ArrayList<JebraStmts>>();
	// ArrayList<JebraStmts> JebraStmtsGrp =null;
	// ArrayList<JebraStmts> JebraTryStmtsGrp =null;
	// Map<Integer, ArrayList<JebraStmts>> dummyJebraTryStmtsGrp = new
	// HashMap<Integer, ArrayList<JebraStmts>>();
	// int index6=-1;
	// Integer tryIndex =-1;
	// Set<Integer> lineInMethod = new TreeSet<Integer>();
	// ArrayList<Integer> ignoredLineNumbers = new ArrayList<Integer>();
	// ArrayList<String> bodyStmts = new ArrayList<String>();
	// ArrayList<JebraStmts> jebraStmtsGroup = new ArrayList<JebraStmts>();
	// if(this.jebraStmts!=null && this.jebraStmts.size()>0){
	// for (int i = 0; i < this.jebraStmts.size(); i++) {
	// JebraStmts orgStmts = this.jebraStmts.get(i);
	//
	// if(orgStmts!=null) {
	// if(orgStmts instanceof JebraIfStmts && ((JebraIfStmts)
	// orgStmts).isIfStmts()){
	// JebraIfStmts stmts = (JebraIfStmts) orgStmts;
	// if(stmts.getIfStmtType()=="if"){
	// JebraStmtsGrp = new ArrayList<JebraStmts>();
	// index6 = stmts.getIfElseIndex();
	// JebraStmtsMap.add(JebraStmtsGrp);
	// JebraStmtsGrp.add(stmts);
	// ignoredLineNumbers.addAll(stmts.getLineNumber());
	// }
	// else if(index6 == stmts.getIfElseIndex()){
	// JebraStmtsGrp.add(stmts);
	// ignoredLineNumbers.addAll(stmts.getLineNumber());
	// }
	// }
	// else if(orgStmts instanceof JebraTryStmt ) {
	// JebraTryStmt tryStmt = (JebraTryStmt) orgStmts;
	// JebraTryStmtsGrp = new ArrayList<JebraStmts>();
	// dummyJebraTryStmtsGrp.put(tryStmt.getIfElseIndex(), JebraTryStmtsGrp);
	// }
	// else if(Constants.TRY.equals(orgStmts.getTryCatchType())) {
	// ArrayList<JebraStmts> arrayList =
	// dummyJebraTryStmtsGrp.get(orgStmts.getIfElseIndex());
	// if(arrayList != null) {
	// arrayList.add(orgStmts);
	// dummyJebraTryStmtsGrp.put(orgStmts.getIfElseIndex(),arrayList);
	// }
	// }
	// else if(Constants.CATCH.equals(orgStmts.getTryCatchType())) {
	// JebraTryStmtsGrp = new ArrayList<JebraStmts>();
	// ArrayList<JebraStmts> arrayList =
	// dummyJebraTryStmtsGrp.get(orgStmts.getIfElseIndex());
	// if(arrayList != null) {
	// arrayList.add(orgStmts);
	// dummyJebraTryStmtsGrp.put(orgStmts.getIfElseIndex(),arrayList);
	// }
	// else {
	// ArrayList<JebraStmts> arrayList1 = new ArrayList<JebraStmts>();
	// arrayList1.add(orgStmts);
	// dummyJebraTryStmtsGrp.put(orgStmts.getIfElseIndex(),arrayList);
	// }
	// }
	// else if(Constants.FINALLY.equals(orgStmts.getTryCatchType())) {
	// if(tryIndex.equals(orgStmts.getIfElseIndex())) {
	// JebraTryStmtsGrp.add(orgStmts);
	// }
	// }
	// else if(orgStmts instanceof JebraLoopStmts) {
	// JebraLoopStmts stmts = (JebraLoopStmts) orgStmts;
	// lineInMethod.add(stmts.getLineNumber().get(0));
	// lineInMethod.add(stmts.getLineNumber().get(stmts.getLineNumber().size()-1));
	// bodyStmts.add(orgStmts.getStmt());
	// jebraStmtsGroup.add(orgStmts);
	// }
	// else{
	// if(!ignoredLineNumbers.contains(orgStmts.getIndex())){
	// lineInMethod.add(orgStmts.getIndex());
	// }
	// // parteek need to check here why it is using getIfElseIndex it is not a if
	// stmts
	// //if(orgStmts.getIfElseIndex()<0){
	// bodyStmts.add(orgStmts.getStmt());
	// jebraStmtsGroup.add(orgStmts);
	// //}
	// }
	// }
	// }
	//
	// if(JebraStmtsMap.size() <= 0) {
	// TestMethodStructure basicTestCase = new TestMethodStructure();
	// basicTestCase.initialise(this,null, jebraStmtsGroup, null, getMyClass(),
	// lineInMethod, bodyStmts);
	// structuresList.add(basicTestCase);
	// }
	// else {
	// getMethodNames(structuresList,JebraStmtsMap, JebraStmtsMap.get(0)
	// ,0,"",lineInMethod,bodyStmts,jebraStmtsGroup);
	// }

	// }
	// if(jebraStmts!=null){
	// String testcaseIfName="";
	//
	// for (int i = 0; i < jebraStmts.size(); i++) {
	// JebraStmts stmts = jebraStmts.get(i);
	// if(stmts!=null && stmts.isIfStmts()){
	// int index=stmts.getIfElseIndex();
	// boolean flag =false;
	//
	// MethodStructure testCase = new MethodStructure();
	// String methodName = "Test"+this.getName();
	// String arguments = "_";
	// arguments += stmts.getLeft()!="boolean" ? stmts.getLeft():"";
	// arguments += "_";
	// arguments += stmts.getRight()!="boolean" ? stmts.getRight():"" ;
	//
	// return structuresList;
	// }

	// private void GroupStatements(ArrayList<JebraStmts> allStatement) {
	// ArrayList<JebraStmts> arrayList = new ArrayList<JebraStmts>();
	// for (int i = 0; i < allStatement.size(); i++) {
	// JebraStmts jebraStmts= allStatement.get(0);
	// if(jebraStmts!=null && jebraStmts instanceof JebraIfStmts) {
	//// if(((JebraIfStmts) jebraStmts).isIfStmts()) {
	//
	// }
	// else {
	// arrayList.add(jebraStmts);
	// }
	// }
	// }
	// }
	@SuppressWarnings("unused")
	private String checkTestMethodName(String name, int index) {

		// for (int j = 0; j < getJebraStmts().size(); j++) {
		// JebraStmts obj = getJebraStmts().get(j);
		// if(obj!=null && ((JebraIfStmts) obj).isIfStmts()){
		// JebraIfStmts proxyObj = (JebraIfStmts)obj;
		// int myIndex = proxyObj.getIfElseIndex();
		// String type = proxyObj.getIfStmtType();
		// if(index < myIndex){
		// String arguments = "_";
		// arguments += proxyObj.getLeft()!="boolean" ? proxyObj.getLeft():"";
		// arguments += "_";
		// arguments += proxyObj.getRight()!="boolean" ? proxyObj.getRight():"" ;
		//
		//
		// if(proxyObj.getIfStmtType()=="else"){
		// name += "_"+proxyObj.getIfStmtType();
		// }else{
		// name += "_"+proxyObj.getIfStmtType()+arguments;
		// }
		// //System.out.println(name + " : "+myIndex);
		// index++;
		// checkTestMethodName(name,index);
		// }
		// else{
		// continue;
		// }
		// }
		// }
		return name;

	}

	public void getMethodNames(ArrayList<TestMethodStructure> structuresList, ArrayList<ArrayList<JebraStmts>> mainList,
			ArrayList<JebraStmts> obj, int index, String value, Set<Integer> lineInMethod, ArrayList<String> bodyStmts,
			ArrayList<JebraStmts> jebraStmtsGroup) {
		// Set<Integer> newlineInMethod =null;
		if (obj == null) {
			return;
		}
		// for (int i = 0; i < obj.size(); i++) {
		// String val = value;
		// if(index+1 == mainList.size()){
		// JebraStmts jebraStmts = obj.get(i);
		// val = value+"_" +((JebraIfStmts) obj.get(i)).getIfStmtType();
		// TestMethodStructure basicTestCase = new TestMethodStructure();
		// basicTestCase.initialise(this,val, jebraStmtsGroup, jebraStmts, getMyClass(),
		// lineInMethod, bodyStmts);
		// structuresList.add(basicTestCase);
		// }
		// else if(index+1 < mainList.size()){
		// val = value+"_" +((JebraIfStmts) obj.get(i)).getIfStmtType();
		// for (int j = 0; j < i; j++) {
		// JebraStmts stmt = obj.get(j);
		// lineInMethod.removeAll(stmt.getLineNumber());
		// bodyStmts.removeAll(stmt.getBodyStmts());
		// jebraStmtsGroup.remove(stmt);
		// }
		// lineInMethod.addAll(obj.get(i).getLineNumber());
		// bodyStmts.addAll(obj.get(i).getBodyStmts());
		// jebraStmtsGroup.add(obj.get(i));
		// //newlineInMethod.addAll(obj.get(i).getLineNumber());
		// getMethodNames(structuresList,mainList,mainList.get(index+1),index+1,val,lineInMethod,bodyStmts,jebraStmtsGroup);
		// }
		// }
	}

	public List<TestMethodStructure> getAllTestMethods() {
		return allTestMethods;
	}

	public void setAllTestMethods(List<TestMethodStructure> allTestMethods) {
		this.allTestMethods = allTestMethods;
	}

	public void addAllTestMethods(TestMethodStructure testMethod) {
		this.allTestMethods.add(testMethod);
	}

	public void setMyConstDeclaration(ConstructorDeclaration myConstDeclaration) {
		this.myConstDeclaration = myConstDeclaration;
	}

	public ConstructorDeclaration getMyConstDeclaration() {
		return myConstDeclaration;
	}

	public boolean isContructor() {
		if (myConstDeclaration != null) {
			return true;
		}
		return false;
	}

	public boolean isMethod() {
		if (getMyMethod() != null) {
			return true;
		}
		return false;
	}

	public boolean isTestCase() {
		if (myConstDeclaration != null && getMyMethod() != null) {
			return true;
		}
		return false;
	}
	
	public String toString() {
        return getName();
    }
}