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
package finalassignment;

import treenode.*;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;
import java.io.*;

/**
 *FinalAssignment
 * @author huong
 */

public class FinalAssignment 
{
    private static String answer = "";
    private static BinaryTree<String> tree = new BinaryTree<String>();
    private static Scanner scan = new Scanner(System.in);
    
    /**
     *main
     * @param args
     */
    public static void main(String args[]) 
    {
        String filename = "tree.ser";

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
        }//End else 

        System.out.println("Do you want to continue from your last checkpoint (y or n)?");
        String load = scan.next();
        scan.nextLine(); 
        BinaryTree<String> tree;
        while (true)
        {
        if (load.toLowerCase().startsWith("y")) 
        {
            try 
            {
                tree = loadBinaryTree(filename);
                System.out.println("Custom tree loaded successfully.");
                break;
            }//End try
            catch (FileNotFoundException e) 
            {
                System.out.println("There is no start checkpoint.");
                tree = createDefaultTree(mode);
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

        while (true) 
        {
            System.out.println("Think of a random animal, I will attempt to guess it.");

            TreeNode<String> currentNode = tree.getRootNode();

            while (!currentNode.isLeaf()) 
            {
                System.out.println(currentNode.getData());
                answer = scan.nextLine();

                //Left if correct, right if incorrect
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
            //When the answer is n on a leaf Node
            else    
            {
                //Ask for the answer and save it
                System.out.println("I don't know. What is the correct answer?");
                String newItem = scan.nextLine();
                String oldItem = currentNode.getData();

                //Now ask for a question based on the answer
                System.out.println("Enter a question for which the answer is Yes for " + newItem + " and No for " + oldItem);
                String newQuestion = scan.nextLine();

                //Ensure that the question will have a question mark at the end
                newQuestion = newQuestion.trim();
                if (!newQuestion.endsWith("?")) newQuestion += "?";
                {
                //Save the updated node structure
                currentNode.setData(newQuestion);
                currentNode.setLeftChild(new TreeNode<String>(newItem));
                currentNode.setRightChild(new TreeNode<String>(oldItem));
                }
            }//End else
    
            System.out.println("Would you like to play again? (y or n)");
            String action = scan.nextLine();
            
            while (!action.toLowerCase().startsWith("y") && !action.toLowerCase().startsWith("n")) 
            {
                System.out.println("Invalid input. Please enter 'y' or 'n'.");
                action = scan.nextLine();
            }

            if (!action.toLowerCase().startsWith("y")) 
            {
                System.out.println("Do you want to save the current checkpoint? (y or n)");
                String save = scan.nextLine();
                while (!save.toLowerCase().startsWith("y") && !save.toLowerCase().startsWith("n")) 
                {
                    System.out.println("Invalid input. Please enter 'y' or 'n'.");
                    save = scan.nextLine();
                }
                if (save.toLowerCase().startsWith("y")) 
                {
                    try 
                    {
                        saveBinaryTree(tree, filename);
                        System.out.println("Current checkpoint saved successfully.");                    
                    } 
                    catch (Exception e) 
                    {
                        e.printStackTrace();
                    }
                }
                break;
            }
        }//End while
    }//End main

    /**
     *BinaryTree
     * @param mode
     * @return
     */
    public static BinaryTree<String> createDefaultTree(int mode) 
    {
        BinaryTree<String> tree = new BinaryTree<String>();
        if (mode == 1) 
        {
            TreeNode<String> question1 = new TreeNode<String>("Is it a mammal?");
            TreeNode<String> question2 = new TreeNode<String>("Does it bite?");
            TreeNode<String> question3 = new TreeNode<String>("Does it make a 'meow' sound?");
            TreeNode<String> question4 = new TreeNode<String>("Can it fly?");
            TreeNode<String> question5 = new TreeNode<String>("Does it live underwater?");
            TreeNode<String> question6 = new TreeNode<String>("Is it a large mammal?");
            TreeNode<String> ans1 = new TreeNode<String>("Cat");
            TreeNode<String> ans2 = new TreeNode<String>("Dog");
            TreeNode<String> ans3 = new TreeNode<String>("Bird");
            TreeNode<String> ans4 = new TreeNode<String>("Fish");
            TreeNode<String> ans5 = new TreeNode<String>("rabbit");
            TreeNode<String> ans6 = new TreeNode<String>("bear");
            
            tree.setRoot(question1);
            question1.setLeftChild(question2);
            question1.setRightChild(ans3);
            question2.setLeftChild(question3);
            question2.setRightChild(ans2);
            question3.setLeftChild(ans1);
            question3.setRightChild(ans3);
            question4.setLeftChild(ans4);
            question4.setRightChild(ans3);
            question5.setLeftChild(ans4);
            question5.setRightChild(ans5);
            question6.setLeftChild(ans5);
            question6.setRightChild(ans6);
        }//End if
        else if (mode == 2) 
        {
            TreeNode<String> question1 = new TreeNode<String>("Is it a vegetable?");
            TreeNode<String> question2 = new TreeNode<String>("Is it a type of grain?");
            TreeNode<String> question3 = new TreeNode<String>("Is it commonly used in salads?");
            TreeNode<String> question4 = new TreeNode<String>("Is it a leafy green?");
            TreeNode<String> question5 = new TreeNode<String>("Is it a root vegetable?");
            TreeNode<String> question6 = new TreeNode<String>("Is it a type of fungus?");
            TreeNode<String> ans1 = new TreeNode<String>("Carrot");
            TreeNode<String> ans2 = new TreeNode<String>("Corn");
            TreeNode<String> ans3 = new TreeNode<String>("Lettuce");
            TreeNode<String> ans4 = new TreeNode<String>("Cilantro");
            TreeNode<String> ans5 = new TreeNode<String>("Pepper");
            TreeNode<String> ans6 = new TreeNode<String>("Mushroom");
            
            tree.setRoot(question1);
            question1.setLeftChild(question2);
            question1.setRightChild(ans3);
            question2.setLeftChild(ans2);
            question2.setRightChild(ans1);
            question3.setLeftChild(ans3);
            question3.setRightChild(ans5);
            question4.setLeftChild(ans3);
            question4.setRightChild(ans6);
            question5.setLeftChild(ans1);
            question5.setRightChild(ans4);
            question6.setLeftChild(ans6);
            question6.setRightChild(ans4);
        }//End else if
        else if (mode == 3) 
        {
            TreeNode<String> question1 = new TreeNode<String>("Is it a mineral or gemstone?");
            TreeNode<String> question2 = new TreeNode<String>("Is it a type of metal?");
            TreeNode<String> question3 = new TreeNode<String>("Is it known for its brilliance?");
            TreeNode<String> question4 = new TreeNode<String>("Is it commonly used in jewelry?");
            TreeNode<String> question5 = new TreeNode<String>("Is it a type of crystal?");
            TreeNode<String> question6 = new TreeNode<String>("Is it a precious metal?");
            TreeNode<String> ans1 = new TreeNode<String>("Quartz");
            TreeNode<String> ans2 = new TreeNode<String>("Gold");
            TreeNode<String> ans3 = new TreeNode<String>("Diamond");
            TreeNode<String> ans4 = new TreeNode<String>("Apatite");
            TreeNode<String> ans5 = new TreeNode<String>("Gypsum");
            TreeNode<String> ans6 = new TreeNode<String>("Silver");
            
            tree.setRoot(question1);
            question1.setLeftChild(question2);
            question1.setRightChild(ans3);
            question2.setLeftChild(ans2);
            question2.setRightChild(ans1);
            question3.setLeftChild(ans3);
            question3.setRightChild(ans6);
            question4.setLeftChild(ans3);
            question4.setRightChild(ans4);
            question5.setLeftChild(ans1);
            question5.setRightChild(ans4);
            question6.setLeftChild(ans6);
            question6.setRightChild(ans2);
        }//End else if 
        return tree;
    }//End BinaryTree

    /**
     *saveBinaryTree
     * @param tree
     * @param filename
     * @throws Exception
     */
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
}//End FinalAssignment 