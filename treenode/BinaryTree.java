package treenode;

import java.io.Serializable;
/**
 *
 * @author huong
 */
public class BinaryTree<T> implements Serializable
{
    private TreeNode<T> root;

    public BinaryTree()
    {
        root = new TreeNode<T>();
    } // End default constructor

    public BinaryTree(T rootData)
    {
        root = new TreeNode<T>(rootData);
    } // End constructor

    public BinaryTree(T rootData, BinaryTree<T> leftTree,
                      BinaryTree<T> rightTree)
    {
        privateSetTree(rootData, leftTree, rightTree);
    } // End constructor

    public void setTree(T rootData)
    {
        root = new TreeNode<>(rootData);
    } // End setTree

    public void setRoot(TreeNode<T> node) 
    {
        root = node;
    }//End setRoot

    private void privateSetTree(T rootData, BinaryTree<T> leftTree,
                                BinaryTree<T> rightTree)
    {
        root = new TreeNode<>(rootData);

        if ((leftTree != null) && !leftTree.isEmpty())
        {
            root.setLeftChild(leftTree.root);
        }//End if

        if ((rightTree != null) && !rightTree.isEmpty())
        {
            if (rightTree != leftTree)
            {
                root.setRightChild(rightTree.root);
            }//End if
            else
            {
                root.setRightChild(rightTree.root.copy());
            }//End else
        } // End if

        if ((leftTree != null) && (leftTree != this))
        {
            leftTree.clear();
        }//End if

        if ((rightTree != null) && (rightTree != this))
        {
            rightTree.clear();
        }//End if
    } // End privateSetTree


    private TreeNode<T> copyNodes() // not essential
    {
        return (TreeNode<T>)root.copy();
    } // End copyNodes

    public T getRootData()
    {
        T rootData = null;

        if (root != null)
        {
            rootData = root.getData();
        }//End if
        return rootData;
    } // End getRootData

    public boolean isEmpty()
    {
        return root == null;
    } // End isEmpty

    public void clear()
    {
        root = null;
    } // End clear

    protected void setRootData(T rootData)
    {
        root.setData(rootData);
    } // End setRootData

    protected void setRootNode(TreeNode<T> rootNode)
    {
        root = rootNode;
    } // End setRootNode

    public TreeNode<T> getRootNode()
    {
        return root;
    } // End getRootNode

    public int getHeight()
    {
        return root.getHeight();
    } // End getHeight

    public int getNumberOfNodes()
    {
        return root.getNumberOfNodes();
    } // End getNumberOfNodes

    public void inorderTraverse()
    {
        inorderTraverse(root);
    } // End inorderTraverse

    private void inorderTraverse(TreeNode<T> node)
    {
        if (node != null)
        {
            inorderTraverse(node.getLeftChild());
            System.out.println(node.getData());
            inorderTraverse(node.getRightChild());
        } // End if
    } // End inorderTraverse
    public String toString() 
    {
        return root.toString();
    }//End toString
} // End BinaryNode
