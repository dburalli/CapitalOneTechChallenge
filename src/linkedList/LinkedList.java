package linkedList;

public class LinkedList {
	Node head;
	int count;
	
	public LinkedList(Node newHead) {
		head = newHead;
		count = 1;
	}
	
	//add
	public void addNode(int data) {
		Node temp = new Node(data);
		Node current = head;
		while(current.getNext() != null) {
			current = current.getNext();
		}
		current.setNext(temp);
		count++;
	}
	
	//get
	public int get(int index) {
		int i;
		
		if( index <= 0) {
			return -1;
		}
		
		Node current = head;
		for(i=1;i<index;i++) {
			current = current.getNext();
		}

		return current.getData();
	}
	
	//size
	public int getSize() {
		return count;
	}
	
	//isEmpty
	public boolean isEmpty(Node node) {
		return head == null;
	}
	
	//remove
	public void removeNode() {
		Node current = head;
		
		while(current.getNext().getNext() != null) {
			current = current.getNext();
		}
		current.setNext(null);
		count--;
	}
	
}
