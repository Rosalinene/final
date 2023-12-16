package treenode;
import java.io.Serializable;
/**
 *
 * @author huong
 */
public class TreeNode <T> implements Serializable
{
    private T data;
    private TreeNode<T> left;
    private TreeNode<T> right;

    public TreeNode()
    {
        this(null); // call next constructor
    } //End default constructor

    public TreeNode(T dataPortion)
    {
        this(dataPortion, null, null); // call next constructor
    } //End constructor

    public TreeNode(T dataPortion, TreeNode<T> leftChild,
                      TreeNode<T> rightChild)
    {
        data = dataPortion;
        left = leftChild;
        right = rightChild;
    } //End constructor

    public T getData()
    {
        return data;
    } //End getData

    public void setData(T newData)
    {
        data = newData;
    } //End setData

    public TreeNode<T> getLeftChild()
    {
        return left;
    } //End getLeftChild

    public TreeNode<T> getRightChild()
    {
        return right;
    } //End getRightChild

    public void setLeftChild(TreeNode<T> leftChild)
    {
        left = (TreeNode<T>)leftChild;
    } //End setLeftChild

    public void setRightChild(TreeNode<T> rightChild)
    {
        right = (TreeNode<T>)rightChild;
    } //End setRightChild

    public boolean hasLeftChild()
    {
        return left != null;
    } //End hasLeftChild

    public boolean hasRightChild()
    {
        return right != null;
    } //End hasRightChild

    public boolean isLeaf()
    {
        return (left == null) && (right == null);
    } //End isLeaf

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
