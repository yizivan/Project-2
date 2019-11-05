package MouseMaze;
import MouseMaze.Mouse;
import MouseMaze.MazeCell;
import java.util.ArrayList;
/**
 * @author Yiyang Shi
 */
class MouseHole extends MazeCell
{
    private int RowPosition, ColumnPosition;
    private ArrayList<Mouse> EscapeMouse = new ArrayList<Mouse>();
    /**
     * constructor for MouseHole class which set up the initial position for MouseHole;
     * @precondition: int type variable RowPosition, int type variable ColumnPosition;
     * @postcondition: RowPosition has changed to -1;
     *                 ColumnPosition has changed to -1;
     */
    public MouseHole()
    {
        RowPosition = -1;
        ColumnPosition = -1;
    }
    /**
     * This constructor set up the RowPosition by sending in rowpostion;
     * set up the ColumnPosition by sending in columnposition;
     * @precondition: int type variable RowPosition;
     *                int type variable ColumnPosition;
     * @postcondition: RowPostition has change to rowpostion;
     *                 Columnposition has change to columnposition;
     * @param rowpostion
     * @param columnposition
     */
    public MouseHole(int rowpostion, int columnposition)
    {
        RowPosition = rowpostion;
        ColumnPosition = columnposition;
    }
    /**
     * @precondition: int type variable RowPosition;
     * @postcondition: noun;
     * @return
     * Rowposition;
     */
    public int GetRowPosition()
    {
        return RowPosition;
    }
    /**
     * @precondition: int type variable ColumnPosition;
     * @postcondition: noun;
     * @return
     * ColumnPosition;
     */
    public int GetColumnPosition()
    {
        return ColumnPosition;
    }
    /**
     * This method return the position of this MouseHole;
     * @precondition: int type variable RowPosition;
     *                int type variable ColumnPosition;
     * @postcondition: noun;
     * @return
     * RowPosition and ColumnPosition as a String type;
     */
    public String toString()
    {
        return(RowPosition + " " + ColumnPosition);
    }
    /**
     * This method add currentmouse to the EscapeMouse ArrayList;
     * @precondition: Mouse type ArrayList EscapeMouse;
     * @postcondition: EscapeMouse has added one more element which is currentmouse;
     * @param currentmouse
     * EscapeMouse ArrayList add currentmouse in the list;
     */
    public void escapeAmouse(Mouse currentmouse)
    {   
        EscapeMouse.add(currentmouse);
    }
    /**
     * This mouse return the EscapeMouse Arraylist;
     * @precondition: Mouse type ArrayList EscapeMouse;
     * @postcondition: noun;
     * @return
     * EscapeMouse;
     */
    public ArrayList<Mouse> GetEscape()
    {
        return EscapeMouse;
    }
}