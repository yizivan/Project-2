package MouseMaze;
import MouseMaze.Mouse;
import MouseMaze.MazeCell;
import java.util.ArrayList;
/**
 * @author Yiyang Shi
 */
class MouseTrap extends MazeCell
{
    private int RowPosition, ColumnPosition;
    private ArrayList<Mouse> GraveYard = new ArrayList<Mouse>();
    /**
     * constructor for MouseTrap class which set up the initial position for MouseTrap;
     * @precondition: int type variable RowPosition, int type variable ColumnPosition;
     * @postcondition: RowPosition has changed to -1;
     *                 ColumnPosition has changed to -1;
     */
    public MouseTrap()
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
    public MouseTrap(int rowposition, int columnposition)
    {
        RowPosition = rowposition;
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
     * This method return the position of this MouseTrap;
     * @precondition: int type variable RowPosition;
     *                int type variable ColumnPosition;
     * @postcondition: noun;
     * @return
     * RowPosition and ColumnPosition as a String type;
     */
    public String toString()
    {
        return (RowPosition + " " + ColumnPosition);
    }
    /**
     * This method add currentmouse to the GraveYard ArrayList;
     * @precondition: Mouse type ArrayList GraveYard;
     * @postcondition: GraveYard has added one more element which is currentmouse;
     * @param currentmouse
     * GraveYard ArrayList add currentmouse in the list;
     */
    public void trapAmouse(Mouse currentmouse)
    {
        GraveYard.add(currentmouse);
    }
    /**
     * This mouse return the GraveYard Arraylist;
     * @precondition: Mouse type ArrayList GraveYard;
     * @postcondition: noun;
     * @return
     * GraveYard;
     */
    public ArrayList<Mouse> GetGrave()
    {
        return GraveYard;
    }
}