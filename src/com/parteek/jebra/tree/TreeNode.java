package com.parteek.jebra.tree;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import com.parteek.jebra.statements.JebraStmts;

public class TreeNode<T> implements Iterable<TreeNode<T>> {

	public T data;
	public TreeNode<T> parent;
	public List<TreeNode<T>> children;
	public List<JebraStmts> myList;

	public boolean isRoot() {
		return parent == null;
	}

	public List<JebraStmts> getMyList() {
		return myList;
	}

	public void setMyList(List<JebraStmts> myList) {
		this.myList = myList;
	}

	public boolean isLeaf() {
		return children.size() == 0;
	}

	private List<TreeNode<T>> elementsIndex;

	public TreeNode(T data) {
		this.data = data;
		this.children = new LinkedList<TreeNode<T>>();
		this.elementsIndex = new LinkedList<TreeNode<T>>();
		this.myList = new ArrayList<JebraStmts>();
		this.elementsIndex.add(this);
	}

	public void addToMyList(JebraStmts stmt) {
		this.myList.add(stmt);
	}
	
	public TreeNode<T> addChild(T child) {
		TreeNode<T> childNode = new TreeNode<T>(child);
		childNode.parent = this;
		this.children.add(childNode);
		this.registerChildForSearch(childNode);
		return childNode;
	}

	public int getLevel() {
		if (this.isRoot())
			return 0;
		else
			return parent.getLevel() + 1;
	}

	public T getData() {
		return this.data;
	}
	
	private void registerChildForSearch(TreeNode<T> node) {
		elementsIndex.add(node);
		if (parent != null)
			parent.registerChildForSearch(node);
	}

	public TreeNode<T> findTreeNode(T search) {
		for (TreeNode<T> element : this.elementsIndex) {
			T elData = element.data;
			if (search.equals(elData))
				return element;
		}

		return null;
	}

	public List<TreeNode<T>> getAllElements(){
		
		return this.elementsIndex;
	}
	
	@Override
	public String toString() {
		return data != null ? data.toString() : "[data null]";
	}

	@Override
	public Iterator<TreeNode<T>> iterator() {
		TreeNodeIter<T> iter = new TreeNodeIter<T>(this);
		return iter;
	}

}
