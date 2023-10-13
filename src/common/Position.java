package common;

import java.util.Objects;

/** 
 * @author Marlon Dominguez
 * @author Ernie Oscar
 */


/**Position class
 * -Responsible for the location of each and every object on the chessboard. 
 * -Overridden equals() method to distinguish object instances.
 * -toString representation allows for convenient text processing. 
 */
public class Position 
{
    private final File file;
    private final Integer rank;

    public Position(File file, Integer rank)
    {
        this.file = file;
        this.rank = rank;
    }

    public File getFile()
    {
        return file;
    }

    public Integer getRank()
    {
        return rank;
    }

    @Override
    public boolean equals(Object o)
    {
        if(this == o)
        {
            return true;
        }
        if(!(o instanceof Position))
        {
            return false;
        }
        Position position = (Position) o;
        return file == position.file && Objects.equals(rank, position.rank);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(file, rank);
    }

    @Override
    public String toString()
    {
        return "Location{" +
        "file=" + file +
        ", rank=" + rank +
        '}';
    }
    
    public int compareTo(Position obj){
    	
    	if(this.rank.equals(obj.getRank()) && this.file.equals(obj.getFile())) {
    		
    		return 1;
    	}
    	return 0;
    	
    	
    	
    	
    }
}
