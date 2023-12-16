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
public class BinaryTree<T> implements Serializable
{
    private TreeNode<T> root;

    /**
     *
     */
    public BinaryTree()
    {
        root = new TreeNode<T>();
    } // End default constructor

    /**
     *
     * @param rootData
     */
    public BinaryTree(T rootData)
    {
        root = new TreeNode<T>(rootData);
    } // End constructor

    /**
     *
     * @param rootData
     * @param leftTree
     * @param rightTree
     */
    public BinaryTree(T rootData, BinaryTree<T> leftTree,
                      BinaryTree<T> rightTree)
    {
        privateSetTree(rootData, leftTree, rightTree);
    } // End constructor

    /**
     *
     * @param rootData
     */
    public void setTree(T rootData)
    {
        root = new TreeNode<>(rootData);
    } // End setTree

    /**
     *
     * @param node
     */
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

    /**
     *
     * @return
     */
    public T getRootData()
    {
        T rootData = null;

        if (root != null)
        {
            rootData = root.getData();
        }//End if
        return rootData;
    } // End getRootData

    /**
     *
     * @return
     */
    public boolean isEmpty()
    {
        return root == null;
    } // End isEmpty

    /**
     *
     */
    public void clear()
    {
        root = null;
    } // End clear

    /**
     *
     * @param rootData
     */
    protected void setRootData(T rootData)
    {
        root.setData(rootData);
    } // End setRootData

    /**
     *
     * @param rootNode
     */
    protected void setRootNode(TreeNode<T> rootNode)
    {
        root = rootNode;
    } // End setRootNode

    /**
     *
     * @return
     */
    public TreeNode<T> getRootNode()
    {
        return root;
    } // End getRootNode

    /**
     *
     * @return
     */
    public int getHeight()
    {
        return root.getHeight();
    } // End getHeight

    /**
     *
     * @return
     */
    public int getNumberOfNodes()
    {
        return root.getNumberOfNodes();
    } // End getNumberOfNodes

    /**
     *
     */
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

    /**
     *
     * @return
     */
    public String toString() 
    {
        return root.toString();
    }//End toString
} // End BinaryNode
