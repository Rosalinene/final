/*
* @author Huong Pham
 * Course: CSC 112-301 Fund of computing II
 * Assignment: Guessing game
 * Description: Write a program that plays the guessing game and allows the computer to “learn” how to play.
Set up the Class structure as follows:
Have one class for the Tree Node – one node on the tree.
Have one class for the Binary Tree operations on Tree Node.  Include your Learn and Play functions in this class.
Have the driver class that contains the main function to represent the game.  Include a menu that allows the Player to pick from an Animal, Vegetable, or Mineral.  Have a separate tree structure for each option.  Include an separate menu to allow the Player to start a new game or continue an existing game.
Include the ability to save a current game so that it can be continued at a later time.
Document at least 6 possible answers for each category (animal, vegetable, and mineral) that the computer can try to guess.
Include the documentation in your submission so that the program can be properly tested.
*/
package treenode;
import java.io.Serializable;
/**
 *
 * @author huong
 * @param <T>
 */
public class TreeNode <T> implements Serializable
{
    private T data;
    private TreeNode<T> left;
    private TreeNode<T> right;

    /**
     *
     */
    public TreeNode()
    {
        this(null); // call next constructor
    } //End default constructor

    /**
     *
     * @param dataPortion
     */
    public TreeNode(T dataPortion)
    {
        this(dataPortion, null, null); // call next constructor
    } //End constructor

    /**
     *
     * @param dataPortion
     * @param leftChild
     * @param rightChild
     */
    public TreeNode(T dataPortion, TreeNode<T> leftChild,
                      TreeNode<T> rightChild)
    {
        data = dataPortion;
        left = leftChild;
        right = rightChild;
    } //End constructor

    /**
     *
     * @return
     */
    public T getData()
    {
        return data;
    } //End getData

    /**
     *
     * @param newData
     */
    public void setData(T newData)
    {
        data = newData;
    } //End setData

    /**
     *
     * @return
     */
    public TreeNode<T> getLeftChild()
    {
        return left;
    } //End getLeftChild

    /**
     *
     * @return
     */
    public TreeNode<T> getRightChild()
    {
        return right;
    } //End getRightChild

    /**
     *
     * @param leftChild
     */
    public void setLeftChild(TreeNode<T> leftChild)
    {
        left = (TreeNode<T>)leftChild;
    } //End setLeftChild

    /**
     *
     * @param rightChild
     */
    public void setRightChild(TreeNode<T> rightChild)
    {
        right = (TreeNode<T>)rightChild;
    } //End setRightChild

    /**
     *
     * @return
     */
    public boolean hasLeftChild()
    {
        return left != null;
    } //End hasLeftChild

    /**
     *
     * @return
     */
    public boolean hasRightChild()
    {
        return right != null;
    } //End hasRightChild

    /**
     *
     * @return
     */
    public boolean isLeaf()
    {
        return (left == null) && (right == null);
    } //End isLeaf

    /**
     *
     * @return
     */
    public TreeNode<T> copy()
    {
        TreeNode<T> newRoot = new TreeNode<T>(data);

        if (left != null)
        {
            newRoot.left = (TreeNode<T>)left.copy();
        }//End if
        if (right != null)
        {
            newRoot.right = (TreeNode<T>)right.copy();
        }//End if
        return newRoot;
    } //End copy

    /**
     *
     * @return
     */
    public int getHeight()
    {
        return getHeight(this); 
    } //End getHeight

    private int getHeight(TreeNode<T> node)
    {
        int height = 0;

        if (node != null)
        {
            height = 1 + Math.max(getHeight(node.left), getHeight(node.right));
        }//End if
        return height;
    } //End getHeight

    /**
     *
     * @return
     */
    public int getNumberOfNodes()
    {
        int leftNumber = 0;
        int rightNumber = 0;

        if (left != null)
        {
            leftNumber = left.getNumberOfNodes();
        }//End if
        if (right != null)
        {
            rightNumber = right.getNumberOfNodes();
        }//End if
        return 1 + leftNumber + rightNumber;
    } //End getNumberOfNodes

    /**
     *
     * @return
     */
    public String toString() 
    {
        String msg = getData().toString() + "{";
        if (left != null) 
        {
            msg += "left: " + left.toString() + ",";
        }//End if
        if (right != null) 
        {
            msg += "right: " + right.toString() + ",";
        }//End if 
        msg += "}";

        return  msg;
    }//End toString
} //End BinaryNode
