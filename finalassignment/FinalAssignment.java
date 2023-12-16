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
    /**
     *main
     * @param args
     */
    static BinaryTree<String> tree;
    public static void main(String args[]) 
    {
        tree = new BinaryTree<String>();
        startMenu();
        System.out.println(tree);
        tree.playGame();
        tree.endingMenu();
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
            System.out.println("Think of a random animal, I will attempt to guess it.");
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
            question1.setRightChild(question3);
            question2.setLeftChild(ans2);
            question2.setRightChild(question4);
            question3.setLeftChild(ans1);
            question3.setRightChild(question5);
            question4.setLeftChild(ans3);
            question4.setRightChild(ans4);
            question5.setLeftChild(ans4);
            question5.setRightChild(question6);
            question6.setLeftChild(ans5);
            question6.setRightChild(ans6);
        }//End if
        else if (mode == 2) 
        {
            System.out.println("Think of a random vegetable, I will attempt to guess it.");
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
            question1.setRightChild(question3);
            question2.setLeftChild(ans2);
            question2.setRightChild(question4);
            question3.setLeftChild(ans1);
            question3.setRightChild(question5);
            question4.setLeftChild(ans3);
            question4.setRightChild(ans4);
            question5.setLeftChild(ans4);
            question5.setRightChild(question6);
            question6.setLeftChild(ans5);
            question6.setRightChild(ans6);
        }//End else if
        else if (mode == 3) 
        {
            System.out.println("Think of a random mineral, I will attempt to guess it.");
            TreeNode<String> question1 = new TreeNode<String>("Is it a gemstone?");
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
            question1.setRightChild(question3);
            question2.setLeftChild(ans2);
            question2.setRightChild(question4);
            question3.setLeftChild(ans1);
            question3.setRightChild(question5);
            question4.setLeftChild(ans3);
            question4.setRightChild(ans4);
            question5.setLeftChild(ans4);
            question5.setRightChild(question6);
            question6.setLeftChild(ans5);
            question6.setRightChild(ans6);
        }//End else if 
        return tree;
    }//End BinaryTree
    public static void startMenu()
    {
        String filename = "tree.ser";
        String answer = "";
        Scanner scan = new Scanner(System.in);
        
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
                BinaryTree.loadBinaryTree(filename);
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
            System.out.println(tree);
            break;
        } //End else if
        else 
        {
            System.out.println("Invalid input. Please enter 'y' or 'n'.");
            load = scan.next();  // ask the user again
            scan.nextLine();
        }//End else
        }
    }       
}//End FinalAssignment 