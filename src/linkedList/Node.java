package linkedList;

public class Node {
	
	int data;
	Node next;
	
	//constructors
	public Node(int newData) {
		data = newData;
		next = null;
	}
	
	//getters and setters
	public int getData() {
		return data;
	}
	public Node getNext() {
		return next;
	}
	public void setData(int newData) {
		data = newData;
	}
	public void setNext(Node nextNode) {
		next = nextNode;
	}
}
