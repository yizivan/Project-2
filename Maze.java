package MouseMaze;
import MouseMaze.MazeCell;
import MouseMaze.Mouse;
import MouseMaze.MouseHole;
import MouseMaze.MouseTrap;
import java.util.ArrayList;
import java.util.Scanner;
/**
 * @author Yiyang Shi
 */
class Maze
{
    private static Scanner keyboard = new Scanner(System.in);
    private static int Row, Column, MouseHoleNum, MouseTrapNum;
    private static MazeCell[][] MazeCell = new MazeCell[1][1];
    private static ArrayList<Mouse> myMouseList = new ArrayList<Mouse>();
    private static ArrayList<int[]> MouseStep = new ArrayList<int[]>();
    private static ArrayList<MouseTrap> MouseTraps = new ArrayList<MouseTrap>();
    private static ArrayList<MouseHole> MouseHoles = new ArrayList<MouseHole>();
    private static ArrayList<Mouse> Net = new ArrayList<Mouse>();
    private static ArrayList<Mouse> EscapeMouse = new ArrayList<Mouse>();
    private static long DeadMiceInTrap = 0;
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
     * request users to enter an available int type number;
     * return a right and available int type number which is between zero and ten percent of cell amount minus eight.
     * @precondition: Scanner type variable keyboard;
     *                int type variable Row; int type variable Column;
     * @postcondition: noun;
     * @return
     * delcare a String type variable str; declare a boolean type variable bo; declare an int type variable enterint;
     * set up the initial bo to false;
     * do the following steps
     *      use str to receive a string value;
     *      try to translate str to int type value which save as enterint;
     *          if str is available to be translated to int type or enterint got the number between range;
     *              bo will change to true;
     *          else print out "wrong enter type, please enter an int type number which is positive and not greater than 10% of mazecells minus 8." and bo will change to false;
     * while the bo's value is false;
     * return enterint which is an int number greater than zero;
     */
    public static int ReturnHole()
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
                if (enterint < 0 || (enterint > 0.1* (Row * Column - 8)))
                    enterint = Integer.valueOf("asd");
                bo = true;
            }
            catch(Throwable e)
            {
                System.out.println("wrong enter type, please enter an Int type number which is positive and not greater than 10% of mazecells minus 8.");
                bo = false;
            }
        }
        while (!bo);
        return (enterint);
    }
    /**
     * request users to enter an available int type number;
     * return a right and available int type number which is between zero and twenty-five percent of cell amount minus eight.
     * @precondition: Scanner type variable keyboard;
     *                int type variable Row; int type variable Column;
     * @postcondition: noun;
     * @return
     * delcare a String type variable str; declare a boolean type variable bo; declare an int type variable enterint;
     * set up the initial bo to false;
     * do the following steps
     *      use str to receive a string value;
     *      try to translate str to int type value which save as enterint;
     *          if str is available to be translated to int type or enterint got the number between range;
     *              bo will change to true;
     *          else print out "wrong enter type, please enter an int type number which is positive and not greater than 25% of mazecells minus 8." and bo will change to false;
     * while the bo's value is false;
     * return enterint which is an int number greater than zero;
     */
    public static int ReturnTrap()
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
                if (enterint < 0 || (enterint > 0.25* (Row * Column - 8)))
                    enterint = Integer.valueOf("asd");
                bo = true;
            }
            catch(Throwable e)
            {
                System.out.println("wrong enter type, please enter an Int type number which is positive and not greater than 25% of mazecells minus 8.");
                bo = false;
            }
        }
        while (!bo);
        return (enterint);
    }
    /**
     * send a coordinate in then checking is this coordinate in the first four cells of the maze or the lazt four cells of the maze;
     * if the coordinate is not in those eight particular cells, return true;
     * if the coordinate is in those eight particular cells, return false;
     * @precondition: int type variable Row;
     *                int type variable Column;
     * @postcondition: noun;
     * @param row
     * @param column
     * @return
     * if row equal zero or row equal one 
     *      if column equal 0 or column equal one
     *          return false;
     * if row equal that Row minus one or row equal that Row minus two
     *      if column equal that Column minus one or column equal that Column minus two
     *          return false;
     * row and column in other place return true;
     */
    public static Boolean EnterExit(int row, int column)
    {
        if (row == 0 || row == 1)
        {
            if (column == 0 || column == 1)
                return false;
        }
        if (row == (Row-1) || row == (Row - 2))
        {
            if (column == (Column - 1) || column == (Column - 2))
                return false;
        }
        return true;
    }
    /**
     * This method set up MouseHoles in undefine MazeCells randomly except eight cells in the maze which can be checked in EnterExit(int, int) method;
     * The amount of MouseHoles based on what users enter MouseHoleNum; Save MouseHoles' coordinates in an arraylist MouseHoles;
     * @precondition: int type variable Row;
     *                int type variable Column;
     *                int type variable MouseHoleNum;
     *                method EnterExit(int; int) exist;
     *                arraylist MouseHoles declared;
     * @postcondition: MouseHoleNum of MazeCells' CellType has changed to letter 'H';
     *                 ArrayList MouseHoles has added new objects in the list;
     * declare count equal zero as int type variable; declare i and j as int type variables;
     * open a while loop
     *      if count equals to MouseHoleNum then break the while loop;
     *      variable i set as a randomly int number between 0 and (Row - 1);
     *      variabel j set as a randomly int number between 0 and (Column - 1);
     *      if MazeCell in position (i,j) is null and availble from method EnterExit(i,j)
     *          set up the MazeCell's CellType in this postion as letter 'H';
     *          add this coordinate in to the ArrayList MouseHoles;
     *          count's value plus one;
     */
    public static void SetinHole()
    {
        int count = 0, i, j;
        while (true)
        {
            if (count == MouseHoleNum)
                break;
            i = (int)(Math.random() * (Row - 1));
            j = (int)(Math.random() * (Column - 1));
            if (MazeCell[i][j] == null && EnterExit(i, j))
            {
                MazeCell[i][j] = new MazeCell('H');
                MouseHole newHole = new MouseHole(i, j);
                MouseHoles.add(newHole);
                count ++;
            }
            
        }
    }
    /**
     * This method set up MouseTraps in undefine MazeCells randomly except eight cells in the maze which can be checked in EnterExit(int, int) method;
     * The amount of MouseTraps based on what users enter MouseTrapNum; Save MouseTraps' coordinates in an arraylist MouseTraps;
     * @precondition: int type variable Row;
     *                int type variable Column;
     *                int type variable MouseTrapNum;
     *                method EnterExit(int; int) exist;
     *                arraylist MouseTraps declared;
     * @postcondition: MouseTrapNum of MazeCells' CellType has changed to letter 'T';
     *                 ArrayList MouseTraps has added new objects in the list;
     * declare count equal zero as int type variable; declare i and j as int type variables;
     * open a while loop
     *      if count equals to MouseTrapNum then break the while loop;
     *      variable i set as a randomly int number between 0 and (Row - 1);
     *      variabel j set as a randomly int number between 0 and (Column - 1);
     *      if MazeCell in position (i,j) is null and availble from method EnterExit(i,j)
     *          set up the MazeCell's CellType in this postion as letter 'T';
     *          add this coordinate in to the ArrayList MouseTraps;
     *          count's value plus one;
     */
    public static void SetinTrap()
    {
        int count = 0, i, j;
        while (true)
        {
            if (count == MouseTrapNum)
                break;
            i = (int)(Math.random() * (Row - 1));
            j = (int)(Math.random() * (Column - 1));
            if (MazeCell[i][j] == null && EnterExit(i, j))
            {
                MazeCell[i][j] = new MazeCell('T');
                MouseTrap newTrap = new MouseTrap(i,j);
                MouseTraps.add(newTrap);
                count ++;
            }
            
        }
    }
    /**
     * This method is veryfying a coordinate which is inside the maze or not.
     * if the coordinate which is enter as position is out of the maze return true, if not return false;
     * @precondition: int variable Row;
     *                int variable Column;
     * @postcondition: noun;
     * @param position
     * @return
     * send a int type arrays position which has two current value in the method;
     * if x-coordinate is smaller than 0 or greatter than Row or y coordinate is smaller than 0 or greatter than Column 
     *      return true;
     * returen false;
     */
    public Boolean OverFenze(int[] position)
    {
        if (position[0] < 0 || position[1] < 0 || position[0] >= Row || position[1] >= Column)
            return true;
        return false;
    }
    /**
     * This is the constructor for Maze class;
     * it helps users to build a random maze based on user's size, the amount of mouseholes, and the amount of mousetraps;
     * @precondition: MouseTrap type ArrayList MouseTraps;
     *                Mouse type ArrayList myMouseList;
     *                int array type of ArrayList MouseStep;
     *                MouseHole type ArrayList MouseHoles;
     *                Mouse type ArrayList Net;
     *                Mouse type ArrayList EscapeMouse;
     *                int variable Row;
     *                int variable Column;
     *                int variable MouseHoleNum;
     *                int variable MouseTrapNum;
     *                int vairable DeadMiceInTrap;
     *                MazeCell type double arrays MazeCell;
     *                method ReturnInt();
     *                method ReturnHole();
     *                method ReturnTrap();
     *                method SetinHole();
     *                method SetinTrap();
     * @postcondition: MouseTraps has been reseted and added some values;
     *                 MouseHoles has been reseted and added some values;
     *                 myMouseList has been reset to null;
     *                 MouseStep has been reset to null;
     *                 Net has been reset to null;
     *                 EscapeMouse has been reset to null;
     *                 Row has changed its value;
     *                 Column has changed its value;
     *                 MouseHoleNum has changed its value;
     *                 MouseTrapNum has change its value;
     *                 MazeCell has reset and setted up all the Cells' value;
     * reset MouseTraps, myMouseList, MouseStep, MouseHoles, Net, EscapeMouse, DeadMiceInTrap;
     * request users to enter the size of maze he likes which is Row and Column's values;
     * enter Row and Column by using ReturnInt() method to get avariable values;
     * reset the MazeCell's size based on Row and Column;
     * request users to enter the MouseHoleNum which use ReturnHole() method to get an avariable value;
     * request users to enter the MouseTrapNum which use ReturnTrap() method to get an avariable value;
     * using SetinHole() method to randomly set up the MouseHoles in the MazeCells;
     * using SetinTrap() method to randomly set up the MouseTraps in the MazeCells;
     * go through all the MazeCells if the MazeCells' types are null;
     *      set up the MazeCells as 'C';
     * tell users the maze has been created;
     */
    public Maze()
    {
        MouseTraps = new ArrayList<MouseTrap>();
        myMouseList = new ArrayList<Mouse>();
        MouseStep = new ArrayList<int[]>();
        MouseHoles = new ArrayList<MouseHole>();
        Net = new ArrayList<Mouse>();
        EscapeMouse = new ArrayList<Mouse>();
        DeadMiceInTrap = 0;
        System.out.println("Please enter how big of the maze you want to create?");
        System.out.println("Please enter the row of maze.");
        Row = ReturnInt();
        System.out.println("Row of maze has been saved.");
        System.out.println("Please enter the column");
        Column = ReturnInt();
        System.out.println("Column of maze has been saved.");
        MazeCell = new MazeCell [Row][Column];
        System.out.println("Please how many mousehole you want to create in the maze which should be smaller than 10% of mazecells.");
        MouseHoleNum = ReturnHole();
        System.out.println("Mousehole numbers has been saved.");
        System.out.println("Please how many mousetrap you want to create in the maze which should be smaller than 25% of mazecells.");
        MouseTrapNum = ReturnTrap();
        System.out.println("Mousetrap numbers has been saved.");
        SetinHole();
        SetinTrap();
        for (int i = 0; i < Row; i++)
        {
            for (int j = 0; j < Column; j++)
            {
                if (MazeCell[i][j] == null)
                {
                    MazeCell[i][j] = new MazeCell('C');
                }
            }
        }
        System.out.println("Your maze has been created and saved!");
        
    }
    /**
     * toString method which print out whole maze map;
     * @precondition: int type variable Row;
     *                int type variable Column;
     *                MazeCell type double arrays MazeCell;
     * @postcondition: noun;
     * visit all the MazeCell's elements then print out all the CellTypes of those elements;
     * every row will change the line;
     */
    public String toString()
    {
        for (int i = 0; i < Row; i++)
        {
            for (int j = 0; j < Column; j++)
            {
                System.out.print(MazeCell[i][j].getCellType() + " ");
            }
            if (i < Row - 1) System.out.println();
        }
        return "";
    }
    /**
     * This method will kill the mouse, if currentMouse's AliveState is not (-1) which means currentMouse is not currently dead, return false;
     * if currentMouse is currently dead, return true;
     * @precomdition: Mouse class has kill() method;
     *                Mouse class has getAliveState() method;
     * @postcondition: noun;
     * @param currentMouse
     * @return
     * send a currentMouse in this method;
     * using kill() method which from Mouse class to kill currentMosue;
     * if after killing, currentMouse is dead return true; otherwise return false;
     */
    public Boolean KillingMouse(Mouse currentMouse)
    {
        currentMouse.kill();
        if (currentMouse.getAliveState() == -1)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    /**
     * set currentMouse in the enter of the maze to start the tour;
     * @precondition: int array type of ArrayList MouseStep;
     *                Mouse type ArrayList myMouseList;
     * @postcondition: myMouseList has add currentMouse in the ArrayList;
     *                 MouseStep ArrayList has been resetted and add the (0,0) point in the begining;
     * @param currentMouse
     * sent currentMouse in this method;
     * declare an int type array startPoint which has two elements which are zero and zero;
     * reset the MouseStep values as null;
     * myMouseList add currentMouse in the list;
     * MouseStep add startPoint in the list;
     */
    public void startMouse(Mouse currentMouse)
    {
        int[] startPoint = {0,0};
        MouseStep = new ArrayList<int[]>();
        myMouseList.add(currentMouse);
        MouseStep.add(startPoint);
    }
    /**
     * move currentMouse in the maze until it dead, escape, or exit;
     * @precondition: int array type of ArrayList MouseStep;
     *                Mouse type ArrayList Net;
     *                MazeCell type double arrays MazeCell;
     *                OverFenze(int[]) method;
     *                KillingMouse(Mouse) method;
     *                MouseHole type ArrayList MouseHoles;
     *                MouseTrap type ArrayList MouseTraps;
     *                Mouse type ArrayList EscapeMouse;
     *                int variable DeadMiceInTrap;
     * @postcondition: MouseStep has added some elements;
     *                 Net will add currentMouse if the mouse get out of the fenze;
     *                 currentMouse has change its AliveState;
     *                 EscapeMouse will add currentMouse if the mouse get in the hole and disapeaar;
     * @param currentMouse
     * @return
     * 2 if mouse escapes
	 * 1 if mouse successfully exits the maze.
	 * 0 if mouse moves to a new cell but not the exit
	 * -1 if mouse dies
     */
    public int moveMouse(Mouse currentMouse)
    {
        int[] newPosition = new int[2];
        while(true)
        {
            int [] currentMove = currentMouse.move();
            newPosition[0] = MouseStep.get(MouseStep.size() - 1)[0] + currentMove[0];
            newPosition[1] = MouseStep.get(MouseStep.size() - 1)[1] + currentMove[1];
            //System.out.println(newPosition[0] + " " + newPosition[1]);
            MouseStep.add(newPosition);
            if (OverFenze(newPosition))
            {
                if (KillingMouse(currentMouse))
                {
                    Net.add(currentMouse);
                    return -1;
                }
                else
                {
                    Net.add(currentMouse);
                    return -1;
                } 
            }
            if (MazeCell[newPosition[0]][newPosition[1]].getCellType() == 'C')
            {
                if ((newPosition[0] == (Row - 1)) && (newPosition[1] == (Column -1)))
                {
                    EscapeMouse.add(currentMouse);
                    return 1;
                } 
                return 0;
            }
            if (MazeCell[newPosition[0]][newPosition[1]].getCellType() == 'H')
            {
                int choice = (int)(Math.random() * 2);
                if (choice == 0)
                {
                    int randomHole = (int)(Math.random() * MouseHoles.size());
                    newPosition[0] = MouseHoles.get(randomHole).GetRowPosition();
                    newPosition[1] = MouseHoles.get(randomHole).GetColumnPosition();
                    MouseStep.add(newPosition);
                    return 0;
                }
                if (choice == 1)
                {
                    for (int i = 0; i < MouseHoles.size(); i++)
                    {
                        if (MouseHoles.get(i).GetRowPosition() == newPosition[0] && MouseHoles.get(i).GetColumnPosition() == newPosition[1])
                        {
                            MouseHoles.get(i).escapeAmouse(currentMouse);
                            break;
                        }
                    }
                    return 2;
                }
            }
            if (MazeCell[newPosition[0]][newPosition[1]].getCellType() == 'T')
            {
                do
                {
                    currentMouse.kill();
                }
                while (currentMouse.getAliveState() != -1);
                for (int i = 0; i < MouseTraps.size(); i++)
                {
                    if (MouseTraps.get(i).GetRowPosition() == newPosition[0] && MouseTraps.get(i).GetColumnPosition() == newPosition[1])
                    {
                        MouseTraps.get(i).trapAmouse(currentMouse);
                        DeadMiceInTrap++;
                        break;
                    }
                }
                return -1;

            }
        }
    }
    /**
     * print out the maze map;
     * @precondition: int type variable Row;
     *                int type variable Column;
     *                MazeCell type double arrays MazeCell;
     * @postcondition: noun;
     * visit all the MazeCell's elements then print out all the CellTypes of those elements;
     * every row will change the line;
     */
    public void printMaze()
    {
        for (int i = 0; i < Row; i++)
        {
            for (int j = 0; j < Column; j++)
            {
                System.out.print(MazeCell[i][j].getCellType() + " ");
            }
            if (i < Row - 1) System.out.println();
        }
    }
    /**
     * display the information after a mouse exit the maze succefully;
     * @precondition: int array type of ArrayList MouseStep;
     *                MazeCell type double arrays MazeCell;
     *                Mouse type ArrayList Net;
     *                int type variable DeadMouseInNet;
     *                MouseHole type ArrayList MouseHoles;
     *                MouseTrap type ArrayList MouseTraps;
     *                Mouse type ArrayList EscapeMouse;
     * @postcondition: noun;
     * display the mouse's steps which exit the maze successfully;
     * display the maze map;
     * display how many mice we try to restart before one of them succeed;
     * display how many mice dead before one of them succeed;
     * display every traps kill how many mice;
     * display every holes escape how many mice;
     * display how many almost dead mice in the net;
     */
    public void displayStatistics()
    {
        int counter = 0;
        System.out.println("This mouse's steps as shown under: ");
        for (int i = 0; i < MouseStep.size(); i++)
        {
            System.out.print("(" + MouseStep.get(i)[0] + ", " + MouseStep.get(i)[1] + ")");
            counter ++;
            if (counter != (MouseStep.size())) System.out.print(" -> ");
            if (counter % 5 == 0 || counter == (MouseStep.size())) System.out.println();
        }
        for (int i = 0; i < Row; i++)
        {
            for (int j = 0; j < Column; j++)
            {
                System.out.print(MazeCell[i][j].getCellType() + " ");
            }
            if (i < Row - 1) System.out.println();
        }
        System.out.println();
        int DeadMouseInNet = 0;
        for (int i = 0; i < Net.size(); i++)
        {
            if (Net.get(i).getAliveState() == -1)
                DeadMouseInNet ++;
        }
        System.out.println("The total numbers of mouse restart is: " + (myMouseList.size()));
        System.out.println("There are total number of mouse dead: " + (DeadMouseInNet + DeadMiceInTrap));
        for (int i = 0; i < MouseTraps.size(); i++)
        {
            System.out.println("The Trap locates in (" + MouseTraps.get(i).GetRowPosition() + ", " + MouseTraps.get(i).GetColumnPosition() + ") has killed " + MouseTraps.get(i).GetGrave().size());
        }
        for (int i = 0; i < MouseHoles.size(); i++)
        {
            System.out.println("The Hole locates in ("+ MouseHoles.get(i).GetRowPosition() + ", " + MouseHoles.get(i).GetColumnPosition() + ") has escaped " + MouseHoles.get(i).GetEscape().size());
        }
        int AlmostDeadMouseNumber = 0;
        for (int i = 0; i < Net.size(); i++)
        {
            if (Net.get(i).getAliveState() == 0)
                AlmostDeadMouseNumber ++;
        }
        System.out.println("There are " + AlmostDeadMouseNumber + " mice almost dead in the net.");
    }
}