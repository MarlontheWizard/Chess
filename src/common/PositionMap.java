package common;
/** 
 * @author Marlon Dominguez
 * @author Ernie Oscar
 */
public class PositionMap 
{
    private static final File[] files = File.values();
    public static Position build(Position current, Integer fileOffset, Integer rankOffset)
    {
        Integer currentFile = current.getFile().ordinal();
        if(currentFile + fileOffset >= files.length || currentFile + fileOffset < 0)
        {
            return new Position(files[currentFile], current.getRank() + rankOffset);
        }
        return new Position(files[currentFile + fileOffset], current.getRank() + rankOffset);
    }
}
