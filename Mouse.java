package MouseMaze;
/**
 * @author Yiyang Shi
 */
class Mouse
{
    private int AliveState;
    private static int[][] steps = {{-1,0,1},{-1,0,1}};
    /**
     * constructor for Mouse class which set up the int type variable AliveState as 1;
     * @precondition: int type variable AliveState;
     * @postcondition: AliveState has change to 1;
     */
    public Mouse()
    {
        AliveState = 1;
    }
    /**
     * This method will randomly return a int type array value which contain two elements inside as x position and y position for the moving step;
     * @precondition: noun;
     * @postcondition: noun;
     * @return
     * the int type array step which has two elements x and y coordinate that consider as moving direction for the mouse;
     */
    public int[] move()
    {
        int[] step = {-2,-2};
        int moveRow,moveColumn;
        moveRow = steps[0][(int)(Math.random()*3)];
        moveColumn = steps[1][(int)(Math.random()*3)];
        step[0] = moveRow;
        step[1] = moveColumn;
        return step;
    }
    /**
     * This method return the AliveState for this Mouse;
     * @precondition: int type variable AliveState;
     * @postcondition: noun;
     * @return
     * AliveState
     */
    public int getAliveState()
    {
        return AliveState;
    }
    /**
     * This method will try to kill this mouse randomly which may be dead or almost dead;
     * if the Mouse is dead, change the AliveState to -1;
     * if the Mouse is almost dead, change the AliveState to 0;
     * @precondition: int type variable AliveState;
     * @postcondition: AliveState has change to 0 or -1;
     * declare int type variable DeadPercent between 0 and 9;
     * if this mouse's AliveState is equal 1 or 0
     *      if DeadPercent equals to 5
     *          change AliveState to 0;
     *      else
     *          change AliveState to -1;
     */
    public void kill()
    {
        int DeadPercent = (int)(Math.random() * 10);
        if (AliveState == 1 || AliveState == 0)
        {
            if (DeadPercent == 5)
            {
                AliveState = 0;
            }
            else
                AliveState = -1;
        }
    }
    /**
     * This method try to revive this mouse if the mouse is almost dead;
     * if revive succefully, return true, otherwise return false;
     * @precondition: int type variable AliveState;
     * @postcondition: AliveState may change to 1;
     * @return
     * if the mouse is alive that its AliveState equals to 1;
     *      change its AliveState to -1;
     *      return false;
     * if the mouse is almost dead that its AliveState equals to 0;
     *      it has 50 percents to be revived;
     *      if revive succefully, change AliveState to 1 and return true;
     *          else change AliveState to -1 and return false;
     * if this Mouse is dead already, return false;
     */
    public Boolean revive()
    {
        if (AliveState == 1)
        {
            AliveState = -1;
            return false;
        }
        if (AliveState == 0)
        {
            int RevivePercent = (int)(Math.random()*2);
            if (RevivePercent == 1)
            {
                AliveState = 1;
                return true;
            }
            else
            {
                AliveState = -1;
                return false;
            }
        }
        if (AliveState == -1)
        {
            System.out.println("This mouse is currently dead.");
            return false;
        }
        return false;
    }

}