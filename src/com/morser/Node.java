package com.morser;

public class Node <T> {
	private Node <T> left;
	private Node <T> right;
	private T data;
	
	Node(){

	}

	public Node(T data){
		this.data = data;
	}
	public Node<T> getLeft(){
		return left;
	}

	public Node<T> getRight(){
		return right;
	}

	public T getData(){
		return data;
	}

	public void setLeft(Node<T> left){
		this.left = left;
	}

	public void setRight(Node<T> right){
		this.right = right;
	}

	public void setData(T data){
		this.data = data;

	}

}
