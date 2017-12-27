import java.util.ArrayList;
import java.util.Iterator;

public class RedBlackTree<Key extends Comparable<Key>> {
	static RedBlackTree.Node<String> nil = new Node<String>(null);
	static RedBlackTree.Node<String> root;
	static final int RED = 0;
	static final int BLACK = 1;
	ArrayList<String> values = new ArrayList<>();
	ArrayList<Integer> colors = new ArrayList<>();

	public static class Node<Key extends Comparable<Key>> { //changed to static

		Key key;
		Node<String> parent;
		Node<String> leftChild;
		Node<String> rightChild;
		boolean isRed;
		int color;

		public Node(Key data){
			this.key = data;
			parent = nil;
			leftChild = nil;
			rightChild = nil;
			color = RED;
		}


		public int compareTo(Node<Key> n){ 	//this < that  <0
			return key.compareTo(n.key);  	//this > that  >0
		}

		public boolean isLeaf(){
			if (this.equals(root) && this.leftChild == null && this.rightChild == null) return true;
			if (this.equals(root)) return false;
			if (this.leftChild == null && this.rightChild == null){
				return true;
			}
			return false;
		}


	}

	public void insert(String key){
		Node current = this.root;
		Node parent = null;
		int comp = 0;
		while (current!=null){
			parent = current;
			comp = compare(key, (String) current.key);
			if (comp > 0){
				current = current.rightChild;
			}
			else if (comp < 0){
				current = current.leftChild;
			}
			else return;
		}
		Node n = new Node(key);
		n.parent = parent;
		if (parent == null){
			this.root = n;
		}
		else if (comp > 0){
			parent.rightChild = n;
		}
		else {
			parent.leftChild = n;
		}
		fixTree(n);
		values.add((String)n.key);
		colors.add(n.color);
		System.out.println("Node: " + n.key + " " + n.color );
		System.out.println("Parent: " + n.parent.key + " " + n.parent.color);
		System.out.println(root.key);
		System.out.println(root.color);
	}

	private int compare(String x, String y){
		if (x.compareTo(y) < 0){
			return -1;
		}
		if (x.compareTo(y) > 0){
			return 1;
		}
		else
			return 0;
	}

	public void fixTree(Node n){
		while (n.parent.color == RED && n.parent.parent != null) {
			if (n.parent == n.parent.parent.leftChild) {
				Node uncle = n.parent.parent.rightChild;
				if (uncle!= null && uncle.color == RED) {
					n.parent.color = BLACK;
					uncle.color = BLACK;
					uncle.parent.color = RED;
					n = uncle.parent;
				} else if (n == n.parent.rightChild) {
						n = n.parent;
						rotateLeft(n);
					}
					n.parent.color = BLACK;
					n.parent.parent.color = RED;
					rotateRight(n.parent.parent);
				}
			 else {
				Node uncle = n.parent.parent.leftChild;
				if (uncle!= null && uncle.color == RED) {
					n.parent.color = BLACK;
					uncle.color = BLACK;
					uncle.parent.color = RED;
					n = uncle.parent;
				} else if (n == n.parent.leftChild) {
						n = n.parent;
						rotateRight(n);
					}
					n.parent.color = BLACK;
					n.parent.parent.color = RED;
					rotateLeft(n.parent.parent);
				}

		}

		this.root.color = BLACK;
	}


	public void rotateRight(Node n) {
		Node child = n.leftChild;
		if (n == null || child == null){
			return;
		}
		n.leftChild = child.rightChild;
		if (child.rightChild != null) {
			child.rightChild.parent = n;
		}
		child.parent = n.parent;
		if (n.parent != null) {
			if (n == n.parent.rightChild) {
				n.parent.rightChild = child;
			} else {
				n.parent.leftChild = child;
			}
		} else {
			this.root = child;
		}
		child.rightChild = n;
		n.parent = child;
	}

	public void rotateLeft(Node n) {
		Node child = n.rightChild;
		if (n == null || child == null) {
			return;
		}
		n.rightChild = child.leftChild;
		if (child.leftChild != null) {
			child.leftChild.parent = n;
		}
		child.parent = n.parent;
		if (n.parent != null) {
			if (n == n.parent.leftChild) {
				n.parent.leftChild = child;
			} else {
				n.parent.rightChild = child;
			}
		} else {
			this.root = child;
		}
		child.leftChild = n;
		n.parent = child;
	}

	public boolean isLeaf(RedBlackTree.Node<String> n){
		if (n.equals(root) && n.leftChild == null && n.rightChild == null) return true;
		if (n.equals(root)) return false;
		if (n.leftChild == null && n.rightChild == null){
			return true;
		}
		return false;
	}

	public interface Visitor<Key extends Comparable<Key>> {
		/**
		 This method is called at each node.
		 @param n the visited node
		 */
		void visit(Node<Key> n);
	}

	public void visit(Node<Key> n){
		System.out.println(n.key);
	}

	public void printTree(){  //preorder: visit, go left, go right
		RedBlackTree.Node<String> currentNode = root;
		printTree(currentNode);
	}

	private void printTree(RedBlackTree.Node<String> node){
		System.out.print(node.key);
		if (node.isLeaf()){
			return;
		}
		printTree(node.leftChild);
		printTree(node.rightChild);
	}


	public RedBlackTree.Node lookUp(String key){
		if (isEmpty(root)){
			return null;
		}
		RedBlackTree.Node current = root;
		while (current != null) {
			if (key.equals(current.key)) {
				return current;
			} else if (current.key.compareTo(key) < 0) {
				current = current.leftChild;
			} else {
				current = current.rightChild;
			}
		}
		return null;
	}


	public RedBlackTree.Node<String> getSibling(RedBlackTree.Node<String> n){
		if (n.compareTo(root) == 0){
			return null;
		}
		if (n.compareTo(n.parent.leftChild) == 0){
			return n.parent.rightChild;
		}
		else if (n.compareTo(n.parent.rightChild) == 0){
			return n.parent.leftChild;
		}
		return null;
	}

	private boolean sameParents(RedBlackTree.Node<String> root, RedBlackTree.Node<String> x,
								RedBlackTree.Node<String> y){
		if (root == null){
			return false;
		}
		return ((root.leftChild == x && root.rightChild == y) ||
				root.leftChild == y && root.rightChild == x ||
				sameParents(root.leftChild,x,y) ||
				sameParents(root.rightChild,x,y));

	}


	public RedBlackTree.Node<String> getAunt(RedBlackTree.Node<String> n){
		return getSibling(n.parent);
	}

	public RedBlackTree.Node<String> getGrandparent(RedBlackTree.Node<String> n){
		if (n.compareTo(root) != 0) {
			return n.parent.parent;
		}
		return null;
	}


	public boolean isEmpty(RedBlackTree.Node<String> n){
		if (n.key == null){
			return true;
		}
		return false;
	}

	public String printValues(){
		Iterator<String> it = values.iterator();
		String result = root.key;
		while (it.hasNext()) {
			result += it.next();
		}
		return result;
	}
	public String printColors(){
		Iterator<Integer> it = colors.iterator();
		String result = String.valueOf(root.color);
		while (it.hasNext()) {
			result += it.next();
		}
		return result;
	}
	public boolean isLeftChild(RedBlackTree.Node<String> parent, RedBlackTree.Node<String> child)
	{
		if (child.compareTo(parent) < 0 ) {//child is less than parent
			return true;
		}
		return false;
	}

	public boolean isRightChild(RedBlackTree.Node<String> parent, RedBlackTree.Node<String> child){
		if (child.compareTo(parent) > 0 ) {//child is greater than parent
			return true;
		}
		return false;
	}

	public void preOrderVisit(Visitor<String> v) {
		preOrderVisit(root, v);
	}


	private static void preOrderVisit(RedBlackTree.Node<String> n, Visitor<String> v) {
		if (n == null) {
			return;
		}
		v.visit(n);
		preOrderVisit(n.leftChild, v);
		preOrderVisit(n.rightChild, v);
	}
}

