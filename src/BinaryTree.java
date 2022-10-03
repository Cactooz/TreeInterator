import java.util.Iterator;
import java.util.Random;
import java.util.Stack;

import static java.lang.System.out;

public class BinaryTree implements Iterable<Integer> {
	private class Node {
		private int key;
		private int value;
		private Node left, right;
		
		/// <summary>
		/// Constructor for node, without <see cref="Node.left">left</see> and <see cref="Node.right">right</see> references.
		/// </summary>
		/// <param name="key">The key of the node.</param>
		/// <param name="value">The value of the node.</param>
		public Node(int key, int value) {
			this.key = key;
			this.value = value;
		}
		
		/// <summary>
		/// Get the <see cref="Node.key">key</see> of the node.
		/// </summary>
		/// <returns>The key as integer.</returns>
		public int GetKey() {
			return key;
		}
		/// <summary>
		/// Set the <see cref="Node.key">key</see> value.
		/// </summary>
		/// <param name="value">The int value the key should be.</param>
		public void SetKey(int value) {
			key = value;
		}
		/// <summary>
		/// Get the <see cref="Node.value">value</see> of the node.
		/// </summary>
		/// <returns>The value as integer.</returns>
		public int GetValue() {
			return value;
		}
		/// <summary>
		/// Set the <see cref="Node.value">value</see> of the node.
		/// </summary>
		/// <param name="value">The int value the value should be.</param>
		public void SetValue(int value) {
			this.value = value;
		}
		/// <summary>
		/// Get the <see cref="Node.left">left</see> of the node.
		/// </summary>
		/// <returns>The left node reference.</returns>
		public Node GetLeft() {
			return left;
		}
		/// <summary>
		/// Set the <see cref="Node.left">left</see> reference.
		/// </summary>
		/// <param name="node">The reference to the left node.</param>
		public void SetLeft(Node node) {
			left = node;
		}
		/// <summary>
		/// Get the <see cref="Node.right">right</see> of the node.
		/// </summary>
		/// <returns>The right node reference.</returns>
		public Node GetRight() {
			return right;
		}
		/// <summary>
		/// Set the <see cref="Node.right">right</see> reference.
		/// </summary>
		/// <param name="node">The reference to the right node.</param>
		public void SetRight(Node node) {
			right = node;
		}
		/// <summary>
		/// Print the <see cref="Node.value">values</see> of all elements in the tree, recursive.
		/// </summary>
		public void Print() {
			if(left != null)
				left.Print();
			out.println("Key:\t" + key + "\tValue: " + value);
			if(right != null)
				right.Print();
		}
		
		public void PrintString() {
			if(left != null)
				left.PrintString();
			out.println(" " + key);
			if(right != null)
				right.PrintString();
		}
	}
	
	//The root of the binary tree
	private Node root;
	private int treeSize = 0;
	
	public BinaryTree() {
		root = null;
	}
	
	public BinaryTree(int size) {
		Random random = new Random();
		root = new Node(random.nextInt(size * 2 - size / 4, size * 2 + size / 4), 0);
		treeSize++;
		
		while(treeSize < size)
			Add(random.nextInt(size*4) + 1, random.nextInt(size) + 1);
	}
	
	/// <summary>
	/// Add a <see cref="Node"/> with <see cref="Node.key">key</see> and <see cref="Node.value">value</see> to the existing tree.
	/// </summary>
	/// <param name="key">The key of the node.</param>
	/// <param name="value">The value of the node.</param>
	public void Add(int key, int value) {
		//Add a node if the tree is empty
		if(root == null) {
			root = new Node(key, value);
			treeSize++;
			return;
		}
		
		//Set a pointer to the start of the tree
		Node pointer = root;
		
		while(true) {
			//If a Node with the key already exists, update its value
			if(key == pointer.GetKey()) {
				pointer.SetValue(value);
				break;
			}
			
			if(key < pointer.GetKey()) {
				//Stop the loop if the left reference does not exist
				if(pointer.GetLeft() == null)
					break;
				
				//Move down the left of the tree
				pointer = pointer.GetLeft();
			}
			else {
				//Stop the loop if the right reference does not exist
				if(pointer.GetRight() == null)
					break;
				
				//Move down the right of the tree
				pointer = pointer.GetRight();
			}
			
		}
		
		//Set the value right or left.
		if(key < pointer.GetKey()) {
			pointer.SetLeft(new Node(key, value));
			treeSize++;
		}
		else if(key > pointer.GetKey()) {
			pointer.SetRight(new Node(key, value));
			treeSize++;
		}
	}
	
	/// <summary>
	/// Lookup a specific <see cref="Node.key"/>.
	/// </summary>
	/// <param name="key">The key to find.</param>
	/// <returns>The <see cref="Node.value"/> if found, otherwise <c>null</c>.</returns>
	public Integer Lookup(int key) {
		Node pointer = root;
		
		while(pointer != null) {
			//Return the value if found
			if(pointer.GetKey() == key)
				return pointer.GetValue();
			
			//Go left if key is smaller otherwise right
			if(key < pointer.GetKey())
				pointer = pointer.GetLeft();
			else
				pointer = pointer.GetRight();
		}
		
		//Return null if not found
		return null;
	}
	
	/// <summary>
	/// Print the whole tree from left to right.
	/// </summary>
	public void Print(String type) {
		if(root == null)
			out.println("Empty tree");
		else if(type.equalsIgnoreCase("full")) {
			root.Print();
			out.println();
		}
		else {
			out.println("{");
			root.PrintString();
			out.println(" }");
		}
	}
	
	public Iterator<Integer> iterator() {
		return new TreeIterator();
	}
	
	public class TreeIterator implements Iterator<Integer> {
		private Stack<Node> stack;
		
		public TreeIterator() {
			stack = new Stack<Node>();
			
			traverseLeft(root);
		}
		
		private void traverseLeft(Node next) {
			if(next != null) {
				stack.push(next);
				traverseLeft(next.GetLeft());
			}
		}
		
		@Override
		public boolean hasNext(){
			//Return true if there is another element in the stack
			return !stack.isEmpty();
		}
		
		@Override
		public Integer next() {
			Node node = stack.pop();
			if(node.GetRight() != null)
				traverseLeft(node.GetRight());
			
			return node.GetValue();
		}
		
		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}
	}
}
