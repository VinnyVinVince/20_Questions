import java.util.*;
import java.io.*;

/*
 *  Vincent Do
 *  Data Structures
 *  Project 6
 *  A QuestionTree contains and allows for run-throughs of possible question & answer choices in a game of questions.
 *  Branches are hereby referred to as 'questions' and leaves as 'answers'.
 */
public class QuestionTree {

    // The first node in the tree structure.
    private QuestionNode overallRoot;

    // Access to console input.
    private Scanner console;

    // Creates a tree with an answer node containing "computer" as its text.
    public QuestionTree() {

        overallRoot = new QuestionNode("computer");
        console = new Scanner(System.in);

    }

    /*
     *  Pre:  Scanner is reading a legally formatted text file.
     *
     *  Post: Tree is filled with nodes corresponding to the questions and answers contained within the read file.
     */
    public void read(Scanner input) {

        overallRoot = insert(input);

    }

    // Private pair method to read(). Uses x = change(x) algorithm.
    private QuestionNode insert(Scanner input) {

        String nodeType = input.nextLine();
        String phrase = input.nextLine();

        if (nodeType.equals("Q:")) {

            return new QuestionNode(phrase, insert(input), insert(input));

        } else {

            return new QuestionNode(phrase);

        }

    }

    /*
     *  Pre:  Input PrintStream allows writing to text file.
     *
     *  Post: Tree will be printed to text file in legal format.
     */
    public void write(PrintStream output) {

        write(overallRoot, output);

    }

    // Private pair method. Uses preorder traversal algorithm.
    private void write(QuestionNode root, PrintStream output) {

        if (root != null) {

            if (root.isAnswer()) {

                output.println("A:");

            } else {

                output.println("Q:");

            }

            output.println(root.text());

            write(root.yes(), output);
            write(root.no(), output);

        }

    }

    /*
     *  Post: Plays a game of questions. If a failed guess is made, prompts user for a new question & answer,
     *        then modifies the tree accordingly.
     */
    public void askQuestions() {

        boolean ongoing = true;
        QuestionNode parent = null;
        QuestionNode current = overallRoot;

        while (ongoing) {

            if (current.isAnswer()) {

                if (yesTo("Would your object happen to be " + current.text() + "?")) {

                    System.out.println("Great, I got it right!");

                } else {

                    System.out.print("What is the name of your object? ");

                    String newAnswer = console.nextLine().toLowerCase();

                    System.out.println("Please give me a yes/no question that");
                    System.out.println("distinguishes between your object");
                    System.out.print("and mine--> ");

                    String newQuestion = console.nextLine();

                    QuestionNode yes;
                    QuestionNode no;

                    boolean direction = yesTo("And what is the answer for your object?");

                    if (direction) {

                        yes = new QuestionNode(newAnswer);
                        no = current;

                    } else {

                        yes = current;
                        no = new QuestionNode(newAnswer);

                    }

                    current = new QuestionNode(newQuestion, yes, no);

                    if (parent == null) {

                        overallRoot = current;

                    } else {

                        if (direction) {

                            parent.setYes(current);

                        } else {

                            parent.setNo(current);

                        }

                    }

                }

                ongoing = false;

            } else {

                parent = current;

                if (yesTo(current.text())) {

                    current = current.yes();

                } else {

                    current = current.no();

                }

            }

        }

    }

    /*
     *  Post: Asks inputted question, only accepts "y" or "n" answers, otherwise it prompts again.
     *        Returns true if answer was "y", false if "n".
     */
    public boolean yesTo(String prompt) {

        System.out.print(prompt + " (y/n)? ");
        String response = console.nextLine().trim().toLowerCase();

        while (!response.equals("y") && !response.equals("n")) {

            System.out.println("Please answer y or n.");
            System.out.print(prompt + " (y/n)? ");
            response = console.nextLine().trim().toLowerCase();

        }

        return response.equals("y");

    }

}