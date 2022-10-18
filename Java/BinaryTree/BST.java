package BinaryTree;

import java.util.*;
/**
 * This program creates an Iterable Binary Search Tree (BST)
 * @author mae93
 * @since July 23 2017
 */
public class BST implements Iterable<Integer>{
	protected TreeNode root;
	
	/**
	 * This method checks whether an element exists in the BST
	 * @param element the element we need to check
	 * @return true if the element exists and false otherwise
	 */
	public boolean contains(int element) {
	    return contains(root, element);
	}
	
	/**
	 * This method checks whether an element exists in the BST starting with a TreeNode and using recursion
	 * @param node the TreeNode thats elements and the elements of its surrounding TreeNodes we need to check
	 * @param element the element we need to check
	 * @return true if the element exists and false otherwise
	 */
	private boolean contains(TreeNode node, int element) {
	    if (node == null)
	        return false;
	    else if (node.value == element)
	        return true;
	    else if (node.value > element)
	        return contains(node.left, element);
	    else
	        return contains(node.right, element);
	}
	
	/**
	 * This method adds an element to the BST
	 * @param element the element to be added
	 */
	public void add(int element){
		if(root == null)
			root = new TreeNode(element);
		else
			add(root, element);
	}
	
	/**
	 * This method adds an element to the BST in a sorted manner starting with a TreeNode and using recursion
	 * @param node the TreeNode we need to check (whether it's greater than or less than) in order to know where to add the the new TreeNode
	 * @param element the value of the new TreeNode
	 */
	public void add(TreeNode node, int element){
		if(node.value > element) {
			if(node.left == null)
				node.left = new TreeNode(element, null, null, node);
			else
				add(node.left, element);
		}
		else if(node.value < element){
			if(node.right == null)
				node.right = new TreeNode(element, null, null, node);
			else
				add(node.right, element);
		}
	}
	
	/**
	 * This method counts the number of leaves in the BST
	 * @return the number of leaves
	 */
	public int leaves(){
		return leaves(root, 0);
	}
	
	/**
	 * This method counts the number of leaves in the BST starting with a TreeNode and using recursion
	 * @param node the TreeNode we need to start counting from
	 * @param count the number of leaves so far
	 * @return the total number of leaves
	 */
	public int leaves(TreeNode node, int count){
		if(node == null)
            return 0;
		if(node.left == null && node.right == null)
            return count + 1;
        else {
            int leftLeaves = leaves(node.left, count);
            int totalLeaves = leaves(node.right, leftLeaves);
            return totalLeaves;
        }
	}
	
	/**
	 * This program check the elements between the given value and the root of the BST
	 * @param value the given value
	 * @return a List of theses elements
	 */
	public List<Integer> getPath(int value){
		if(contains(value))
			return getPath(root, value, new ArrayList<Integer>());
		else
			throw new NoSuchElementException();
	}
	
	/**
	 * This program check the elements between the given value and the root of the BST starting from a TreeNode and using recursion
	 * @param node the TreeNode we start from
	 * @param value the given value
	 * @param path the List we need to add values to
	 * @return a List of these elements
	 */
	public List<Integer> getPath(TreeNode node, int value, List<Integer> path){
		if(node.value >= value){
			if(node.left != null)
				getPath(node.left, value, path);
			path.add(node.value);
		}
		else if(node.value <= value){
			if(node.right != null)
				getPath(node.right, value, path);
			path.add(node.value);
		}
		return path;
	}
	
	/**
	 * This method counts the number of elements of BST between 2 given numbers
	 * @param leftValue the smaller of the 2 given numbers
	 * @param rightValue the larger of the 2 given numbers
	 * @return the number of elements between the 2 numbers
	 */
	public int rangeCount(int leftValue, int rightValue){
		return rangeCount(root, leftValue, rightValue, 0);
	}
	
	/**
	 * This method counts the number of elements of BST between 2 given numbers starting from a TreeNode and using recursion
	 * @param node the TreeNode to start from
	 * @param leftValue the smaller of the 2 given numbers
	 * @param rightValue the larger of the 2 given numbers
	 * @param count the number of elements so far
	 * @return the number of elements between the 2 numbers
	 */
	public int rangeCount(TreeNode node, int leftValue, int rightValue, int count){
		if(node != null && node.value < rightValue && node.value > leftValue){
			count = 1 + 
					rangeCount(node.left, leftValue, rightValue, count) + 
					rangeCount(node.right, leftValue, rightValue, count);
		}
		else if(node != null && node.value < rightValue)
			rangeCount(node.right, leftValue, rightValue, count);
		else if(node != null && node.value > leftValue)
			rangeCount(node.left, leftValue, rightValue, count);
		return count;
	}
	
	/**
	 * The method reverses the order of the BST from increasing to decreasing order
	 */
	public void toReverse(){
		toReverse(root);
	}
	
	/**
	 * The method reverses the order of the BST from increasing to decreasing order starting from a TreeNode and using recursion
	 * @param node the TreeNode to start from
	 */
	public void toReverse(TreeNode node){
		TreeNode temp = node.left;
		node.left = node.right;
		node.right = temp;
		if(node.left != null)
			toReverse(node.left);
		if(node.right != null)
			toReverse(node.right);
	}
	
	/**
	 * This method returns an iterator for the BST
	 */
	@Override
	public Iterator<Integer> iterator() {
		return this.toArrayList(root, new ArrayList<>()).iterator();
	}
	
	/**
	 * This method converts the BST to a sorted ArrayList starting from a TreeNode and using recursion
	 * @param node the TreeNode to start from and thats element we need to add to the ArrayList
	 * @param elements the ArrayList of elements
	 * @return the sorted ArrayList of the BST
	 */
	public ArrayList<Integer> toArrayList(TreeNode node, ArrayList<Integer> elements){
		if(node != null){
			elements.add(node.value);
			toArrayList(node.left, elements);
			toArrayList(node.right, elements);
		}
		return elements;
	}
	
	/**
	 * This method converts an ArrayList to a BST
	 * @param elements the ArrayList to be converted
	 * @return a BST derived from the ArrayList
	 */
	public static BST arrayListToBST(ArrayList<Integer> elements){
		BST tree = new BST();
		for(Integer i: elements){
			tree.add(i);
		}
		return tree;
	}
	
	/**
	 * This method converts a BST to a sorted ArrayList
	 * @param tree the BST to be converted
	 * @return a sorted ArrayList derived from the BST
	 */
	public static ArrayList<Integer> bstToSortedArrayList (BST tree){
		return bstToSortedArrayList(tree.root, new ArrayList<>());
	}
	
	/**
	 * This method converts a BST to a sorted ArrayList starting from a TreeNode and using recursion
	 * @param node the TreeNode to start from and thats value we need to add to the ArrayList
	 * @param elements the ArrayList to be derived from the TreeNodes that make up the BST
	 * @return the sorted ArrayList elements 
	 */
	public static ArrayList<Integer> bstToSortedArrayList(TreeNode node, ArrayList<Integer> elements){
		if(node != null){
			bstToSortedArrayList(node.left, elements);
			elements.add(node.value);
			bstToSortedArrayList(node.right, elements);
		}
		return elements;
	}
	
	/**
	 * This method utilizes the above 2 methods to convert an ArrayList to a SortedArrayList by first converting it to a BST then to a SortedArrayList
	 * @param elements the (not sorted) ArrayList to be converted
	 * @return a sorted ArrayList of the above ArrayList
	 */
	public static ArrayList<Integer> arrayListToSortedArrayList(ArrayList<Integer> elements){
		return bstToSortedArrayList(arrayListToBST(elements));
	}
	
	public static void main(String[] args){
		BST tree = new BST();
		tree.add(10);
		tree.add(5);
		tree.add(50);
		tree.add(51);
		tree.add(30);
		
		for(Integer element: tree) {
			System.out.print(element + " ");
		}
		// pre-oder: 10 5 50 30 51
	}
	
}
