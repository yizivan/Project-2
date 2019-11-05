package MouseMaze;

/**
 * @author Yiyang Shi
 */
class MazeCell
{
    private final char CellType;
    /**
     * constructor for MazeCell class which set up MazeCell type as ' ';
     * @precondition : final char type avaible CellType;
     * @postcondition: CellType has change to ' '
     */
    public MazeCell()
    {
        CellType = ' ';
    }
    /**
     * constructor for MazeCell class which set up MazeCell type as celltype
     * @precondition: final char type avaible CellType;
     * @postcondition: CellTyoe has change to celltype;
     * @param celltype
     */
    public MazeCell(char celltype)
    {
        CellType = celltype;
    }
    /**
     * return the Celltype value;
     * @precondition: final char type availble CellType;
     * @postcondition: noun;
     * @return
     */
    public char getCellType()
    {
        return CellType;
    }
}