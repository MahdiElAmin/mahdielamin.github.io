package BinaryTree;
/**
 * This program creates the class TreeNode which represents a leaf of a Binary Tree
 * @author mae93
 * @since July 23 2017
 */
public class TreeNode {
	protected int value;
	protected TreeNode left;
	protected TreeNode right;
	protected TreeNode parent;
	
	/**
	 * TreeNode constructor
	 * @param value the value the TreeNode holds
	 */
	public TreeNode(int value){
		this.value = value;
		this.left = null;
		this.right = null;
		this.parent = null;
	}
	
	/**
	 * TreeNode constructor
	 * @param value the value the TreeNode holds
	 * @param left the TreeNode to the left of this TreeNode
	 * @param right the TreeNode to the right of this TreeNode
	 * @param parent the TreeNode on top (parent) of this TreeNode
	 */
	public TreeNode(int value, TreeNode left, TreeNode right, TreeNode parent){
		this.value = value;
		this.left = left;
		this.right = right;
		this.parent = parent;
	}
	
}
