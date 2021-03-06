import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class run {
    /*
    Creates instances of the various classes that are used
     */
    private static PairMatrix PM = new PairMatrix();
    private static RPNCalc RPNC = new RPNCalc();
    private static PrimeNumbers PRIME = new PrimeNumbers();
    private static BinaryTree BT = new BinaryTree();
    private static HighestInt HIGH = new HighestInt();
    private static FibSequence FIB = new FibSequence();
    private static Expansion EX = new Expansion();
    private static DuplicateNumbers DN = new DuplicateNumbers();
    private static BabMethod BM = new BabMethod();
    private static Scanner SCAN = new Scanner(System.in);

    public static void main(String[] args) {
        boolean exit = false; //boolean for making it so it loops forever until the exit clause is met
        while (!exit) {
            //outputs the instructions to the user
            System.out.println("Please input a number from 1-9 to select the question you want to run");
            System.out.println("Enter -1 to quit");
            try {//it is surrounded by a try catch block in case the user enters something that is not a number.
                int selected = SCAN.nextInt(); //gets the input from the user
                switch (selected) {//The switch statement takes the user input and runs the selected question or exits the loop
                    case -1://This is the exit case
                        System.out.println("Quitting");
                        exit = true;
                        break;
                    case 1://This is for running question 1
                        PM.start();
                        break;
                    case 2://This is for running question 2
                        /*
                        This method first prompts the user to input the RPN expression, it then calls the right method
                        to first set the RPN expression. The equation is then validated and the result is then outputted
                        to the user.
                         */
                        System.out.println("Enter the RPN expression to be checked separate each element by a space.");
                        String expression = readString();
                        if(expression != null) {
                            RPNC.SetInput(expression);
                            if (RPNC.validateInput()) {
                                System.out.println("The Equation is valid");
                            } else {
                                System.out.println("The Equation is invalid");
                            }
                        }
                        break;
                    case 3://This is for running question 3
                        /*
                        The method first prompts the user to see if they want to check if a number is PRIME,
                        or run the Sieve of Eratosthenes. If the user want to check if a number is PRIME the user is
                        prompted to input the number that they wish to check. If the user inputs a invalid input they
                        are informed and the user will be returned back to the main menu.
                         */
                        System.out.println("Enter 1 to check if a number is PRIME or 2 if you want to run the sieve of Eratosthenes");
                        try {
                            selected = SCAN.nextInt(); //stores the users input
                            switch (selected) {
                                case 1:
                                    System.out.println("Please input a number to check");
                                    selected = SCAN.nextInt(); //selected is reused for the number to save on memory
                                    System.out.println(PRIME.isPrime(selected));
                                    break;
                                case 2:
                                    PRIME.sieve();
                                    break;
                                default:
                                    System.out.print("Invalid input");
                                    break;
                            }
                        } catch (Exception e) {
                            System.out.println("Invalid input");
                            resetScanner();
                        }
                        break;
                    case 4:
                        /*
                        This method calls the method that starts the creation of a binary tree.
                        */
                        BT.start();
                        break;
                    case 5:
                        /*
                        The user is prompted to enter the number that they wish to approximate the square root of,
                        they are then prompted to see the number of iterations they want to run to increase the accuracy.
                        The result is then outputted back to the user. If the user inputs an invalid input they will be
                        informed and the user will be returned back to the main menu.
                         */
                        try {
                            System.out.println("Please enter the number you want the approx sqrt of:");
                            int number = SCAN.nextInt();
                            System.out.println("Please enter the number of iterations you want to run:");
                            int iterations = SCAN.nextInt();
                            System.out.println(BM.approximate(number, iterations));
                        } catch (Exception e) {
                            System.out.println("Invalid Input");
                            resetScanner();
                        }
                        break;
                    case 6:
                        /*
                        This option first calls the method to populate the list then calls the method to find the duplicate
                        numbers and outputs them to the user.
                         */
                        long start = System.currentTimeMillis();
                        DN.findDuplicatesHash(DN.popList());
                        long end  = System.currentTimeMillis();
                        System.out.println("Time taken: " + (end - start));
                        break;
                    case 7:
                        /*
                        This option first call the method the populate the list then calls the method to recursively find
                        the highest number in the list then highest number is then outputted to the user.
                         */
                        System.out.println(HIGH.largestInt(HIGH.populateList()));
                        break;
                    case 8:
                        /*
                        This first prompts the user to enter the angle value of COS in radians then it prompts the user
                        to enter the degree of accuracy that the user wants to calculate the cosExpansion to. The cosExpansion
                        is then calculated and the result is outputted to the user to 4 decimal places. If the user enters
                        an invalid input then once again the user will be returned to the main menu.
                         */
                        try {
                            System.out.println("Please enter the value of the angle X in radians");
                            double x = SCAN.nextDouble();
                            System.out.println("Please enter the degree you want to calculate the Expansion to");
                            int y = SCAN.nextInt();
                            System.out.println("Enter 1 to expand sin or 2 to expand cos");
                            selected = SCAN.nextInt();
                            switch (selected) {
                                case 1:
                                    System.out.printf("%.4f\n", EX.sinExpansion(x, y));
                                    break;
                                case 2:
                                    System.out.printf("%.4f\n", EX.cosExpansion(x, y));
                                    break;
                                default:
                                    System.out.println("Invalid input");
                            }
                        } catch (Exception e) {
                            System.out.println("An error has occurred");
                            resetScanner();
                        }
                        break;
                    case 9:
                        /*
                        This method prompts the user to enter the degree to which they want to calculate the fibonacci
                        sequence to, it also warns the user that after 103 degrees the long that stores the value returned
                        will suffer an overflow error. The returned result is then displayed to the user after the user
                        was prompted. If the user inputs an invalid input the user is again returned to the main menu.
                         */
                        System.out.println("Enter the degree which you want to calculate the fibonacci sequence to ");
                        try {
                            int n = SCAN.nextInt();
                            System.out.println(FIB.fibonacci(n).toString());
                        } catch (Exception e) {
                            System.out.println("A error occurred");
                            resetScanner();
                        }
                        break;
                    default:
                        /*
                        This method informs the user that they inputted an invalid input if the input does not match
                        any of the above cases.
                         */
                        System.out.println("Invalid input");
                        break;
                }
            } catch (Exception e) {
                System.out.println("Invalid input");
                resetScanner();
            }
        }
    }

    private static String readString() {
        /*
        This method is for getting a string inputted from the user. In the case of an error it will return null
         */
        BufferedReader br;
        try {
            br = new BufferedReader(new InputStreamReader(System.in));
            return br.readLine();
        } catch (Exception e) {
            System.out.println("An error occurred during the input of a string");
            return null;
        }
    }

    private static void resetScanner() {
        /*
        this method is called if there was an invalid input to the scanner, it resets any error.
         */
        SCAN = null;
        SCAN = new Scanner(System.in);
    }
}