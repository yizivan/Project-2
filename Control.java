package Control;
import MouseMaze.Maze;
import MouseMaze.MazeCell;
import MouseMaze.Mouse;
import MouseMaze.MouseHole;
import MouseMaze.MouseTrap;
import java.util.Scanner;
/**
 * @author Yiyang Shi
 */
class Control
{
    private static Scanner keyboard = new Scanner(System.in);
    private static Maze myMaze;
     /**
     * request users to enter an available int type number;
     * return a right and available int type number which is positive.
     * @precondition: Scanner type variable keyboard;
     * @postcondition: noun;
     * @return
     * delcare a String type variable str; declare a boolean type variable bo; declare an int type variable enterint;
     * set up the initial bo to false;
     * do the following steps
     *      use str to receive a string value;
     *      try to translate str to int type value which save as enterint;
     *          if str is available to be translated to int type or enterint got the number greater then zero
     *              bo will change to true;
     *          else print out "wrong enter type, please enter an int type number which is positive" and bo will change to false;
     * while the bo's value is false;
     * return enterint which is an int number greater than zero;
     */
    public static int ReturnInt()
    {
        String str;
        Boolean bo;
        int enterint = 0;
        bo = false;
        do
        {
            try
            {
                str = keyboard.nextLine();
                enterint = Integer.valueOf(str);
                if (enterint <= 0)
                    enterint = Integer.valueOf("asd");
                bo = true;
            }
            catch(Throwable e)
            {
                System.out.println("wrong enter type, please enter an Int type number which is positive.");
                bo = false;
            }
        }
        while (!bo);
        return (enterint);
    }
    /**
     * ask users to enter the Row and Column for the maze;
     * if users is satisfy with the maze we created then using this maze to test, otherwise, rebuild a new maze;
     * keep sending mice to the entrence of maze which is (0,0) util one of the mouse ge out from the maze from the last cell of the maze which is (Row-1, Column-1);
     * then print out the dates;
     * keep repeating until the exit mice reach the amount users settle;
     * ask users if he wants to test another test.
     * @param args
     */
    public static void main(String[] args)
    {
        while (true)
        {
            while (true)
            {
                System.out.println("Let's start creating a maze!");
                myMaze = new Maze();
                System.out.println("This is your maze we have created: ");
                System.out.println(myMaze);
                System.out.println("Do you satisfied with this maze? If you satisfied with this please enter YES, If not please enter anything else.");
                if (keyboard.nextLine().toUpperCase().equals("YES"))        
                {
                    System.out.println("OK, it seems you like this maze, let's get start with this maze.");
                    break;
                }
                else
                {
                    System.out.println("It seems you don't like this maze, let's get another maze.");
                }
            }
            System.out.println("How many mice you want them to get out of the maze from exit?(please enter an positive int number)");
            int TotalExit = ReturnInt();
            int counter = 0;
            while (true)
            {
                Mouse myMouse = new Mouse();
                myMaze.startMouse(myMouse);
                while (true)
                {
                    int LiveState = myMaze.moveMouse(myMouse);
                    if (LiveState == -1)
                    {
                        break;
                    }
                    else
                    if (LiveState == 2)
                    {
                        break;
                    }
                    else
                    if (LiveState == 1)
                    {
                        myMaze.displayStatistics();
                        counter++;
                        break;
                    }
                    else continue;
                }
                if (counter == TotalExit)
                {
                    break;
                }
            }
            System.out.println("Do you want to get another maze to test again? If you want, please enter yes, otherwise enter anything else.");
            if (!keyboard.nextLine().toUpperCase().equals("YES"))
            {
                System.out.println("Thank you for using this maze system. See you next time.");
                break;
            }
        }
    }
}