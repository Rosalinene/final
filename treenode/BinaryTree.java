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

import static finalassignment.FinalAssignment.createDefaultTree;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Scanner;
/**
 *
 * @author huong
 * @param <T>
 */
public class BinaryTree<T> implements Serializable
{
    private TreeNode<T> root;
    private static String filename = "tree.ser"; 
    private static String answer = "";
    private static BinaryTree<String> tree = new BinaryTree<String>();
    private static Scanner scan = new Scanner(System.in);

    /**
     *default constructor
     */
    public BinaryTree()
    {
        root = new TreeNode<T>();
    } // End default constructor

    /**
     *constructor
     * @param rootData
     */
    public BinaryTree(T rootData)
    {
        root = new TreeNode<T>(rootData);
    } // End constructor

    /**
     *constructor
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
     *setTree
     * @param rootData
     */
    public void setTree(T rootData)
    {
        root = new TreeNode<>(rootData);
    } // End setTree

    /**
     *setRoot
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
     *getRootData
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
     *isEmpty
     * @return
     */
    public boolean isEmpty()
    {
        return root == null;
    } // End isEmpty

    /**
     *clear
     */
    public void clear()
    {
        root = null;
    } // End clear

    /**
     *setRootData
     * @param rootData
     */
    protected void setRootData(T rootData)
    {
        root.setData(rootData);
    } // End setRootData

    /**
     *setRootNode
     * @param rootNode
     */
    protected void setRootNode(TreeNode<T> rootNode)
    {
        root = rootNode;
    } // End setRootNode

    /**
     *getRootNode
     * @return
     */
    public TreeNode<T> getRootNode()
    {
        return root;
    } // End getRootNode

    /**
     *getHeight
     * @return
     */
    public int getHeight()
    {
        return root.getHeight();
    } // End getHeight

    /**
     *getNumberOfNodes
     * @return
     */
    public int getNumberOfNodes()
    {
        return root.getNumberOfNodes();
    } // End getNumberOfNodes

    /**
     *inorderTraverse
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
     *toString
     * @return
     */
    public String toString() 
    {
        return root.toString();
    }//End toString
    
    private static void learn(TreeNode<String> currentNode, String newItem, String oldItem, String newQuestion) 
    {
        currentNode.setData(newQuestion);
        currentNode.setLeftChild(new TreeNode<>(newItem));
        currentNode.setRightChild(new TreeNode<>(oldItem));
    }
    
    public static void startMenu()
    {
        System.out.println("Welcome to the Guessing Game!");
        System.out.println("Please choose a mode:\n 1. Animals\n 2. Vegetables\n 3. Mineral\n 4. Exit");
        int mode = scan.nextInt();
        
        if (mode == 1) 
        {
            filename = "animals.cer";
        }//End if 
        else if (mode == 2) 
        {
            filename = "vegetables.cer";
        }//End else if 
        else if (mode == 3) 
        {
            filename = "mineral.cer";
        }//End else if 
        else if (mode ==4)
        {
            System.out.println("Goodbye!");
            return;
        }
        else
        {
            System.out.println("Invalid choice. Please enter a number between 1 and 4.");
            //return;
        }//End else 
        System.out.println("Do you want to continue from your last checkpoint (y or n)?");
        String load = scan.next();
        scan.nextLine(); 
        
        while (true)
        {
        if (load.toLowerCase().startsWith("y")) 
        {
            try 
            {
                loadBinaryTree(filename);
                System.out.println("Custom tree loaded successfully.");
                break;
            }//End try
            catch (FileNotFoundException e) 
            {
                System.out.println("There is no start checkpoint.");
                createDefaultTree(mode);
                break;
            }//End catch       
            catch (Exception e) 
            {
                e.printStackTrace();
                return;
            }//End catch
        }//End if
        else if ("n".equalsIgnoreCase(load)) 
        {
            tree = createDefaultTree(mode);
            break;
        } //End else if
        else 
        {
            System.out.println("Invalid input. Please enter 'y' or 'n'.");
            load = scan.next();  // ask the user again
            scan.nextLine();
        }//End else
    }
    public static void playGame() 
    {
        TreeNode<String> currentNode = tree.getRootNode();

        while (!currentNode.isLeaf()) 
        {
            System.out.println(currentNode.getData());
            answer = scan.nextLine();

            if (answer.toLowerCase().startsWith("y")) 
            {
                currentNode = currentNode.getLeftChild();
            } //End if
            else 
            {
                currentNode = currentNode.getRightChild();
            }//End else
        }//End while

        System.out.println("Is it a(n) " + currentNode.getData() + "?");
        answer = scan.nextLine();

        if (answer.toLowerCase().startsWith("y")) 
        {
            System.out.println("\nI guessed it! What you would like to do now? Choose a number...");
        }//End if
        else    
        {
            //Ask for the answer and save it
            System.out.println("I give up. What is the correct answer?");
            String newItem = scan.nextLine();
            String oldItem = currentNode.getData();

            //Now ask for a question based on the answer
            System.out.println("Enter a question for which the answer is Yes for " + newItem + " and No for " + oldItem);
            String newQuestion = scan.nextLine();

            //Ensure that the question will have a question mark at the end
            newQuestion = newQuestion.trim();
            if (!newQuestion.endsWith("?")) newQuestion += "?";
            {
                learn(currentNode, newItem, oldItem, newQuestion);
            }//End if
        }//End else

        /*System.out.println("Would you like to play again? (y or n)");
        String action = scan.nextLine();

        while (!action.toLowerCase().startsWith("y") && !action.toLowerCase().startsWith("n")) 
        {
            System.out.println("Invalid input. Please enter 'y' or 'n'.");
            action = scan.nextLine();
        }//End while

        if (!action.toLowerCase().startsWith("y")) 
        {
            System.out.println("Do you want to save the current checkpoint? (y or n)");
            String save = scan.nextLine();
            while (!save.toLowerCase().startsWith("y") && !save.toLowerCase().startsWith("n")) 
            {
                System.out.println("Invalid input. Please enter 'y' or 'n'.");
                save = scan.nextLine();
            }//End while
            if (save.toLowerCase().startsWith("y")) 
            {
                try 
                {
                    saveBinaryTree(tree, filename);
                    System.out.println("Current checkpoint saved successfully.");                    
                }//End try
                catch (Exception e) 
                {
                    e.printStackTrace();
                }//End catch
            }//End if
        }*/
    }//End while
    
    public static void endingMenu()
    {
        System.out.println("Would you like to play again? (y or n)");
        String action = scan.nextLine();

        while (!action.toLowerCase().startsWith("y") && !action.toLowerCase().startsWith("n")) 
        {
            System.out.println("Invalid input. Please enter 'y' or 'n'.");
            action = scan.nextLine();
        }//End while

        if (!action.toLowerCase().startsWith("y")) 
        {
            System.out.println("Do you want to save the current checkpoint? (y or n)");
            String save = scan.nextLine();
            while (!save.toLowerCase().startsWith("y") && !save.toLowerCase().startsWith("n")) 
            {
                System.out.println("Invalid input. Please enter 'y' or 'n'.");
                save = scan.nextLine();
            }//End while
            if (save.toLowerCase().startsWith("y")) 
            {
                try 
                {
                    saveBinaryTree(tree, filename);
                    System.out.println("Current checkpoint saved successfully.");                    
                }//End try
                catch (Exception e) 
                {
                    e.printStackTrace();
                }//End catch
            }//End if
        }//End if
    }//End endingMenu()
    
    public static void saveBinaryTree(BinaryTree<String> tree, String filename) throws Exception 
    {
        FileOutputStream fos = new FileOutputStream(filename);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(tree);
        oos.close();
        fos.close();
        System.out.println("The tree has been successfully saved\n");
    }//End saveBinaryTree

    /**
     *loadBinaryTree
     * @param filename
     * @return
     * @throws Exception
     */
    public static BinaryTree<String> loadBinaryTree(String filename) throws Exception
    {
        FileInputStream fis = new FileInputStream(filename);
        ObjectInputStream ois = new ObjectInputStream(fis);
        BinaryTree<String> tree = (BinaryTree<String>) ois.readObject();
        ois.close();
        fis.close();
        System.out.println("The tree has been successfully loaded\n");
        return tree;  
    }//End BinaryTree
} // End BinaryNode

    