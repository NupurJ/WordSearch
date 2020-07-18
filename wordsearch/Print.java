package wordsearch;
public class Print
{
    void grid(char grid[][])
    {
        for(int i = 0; i < grid.length; i++)//fill the remaining part of the grid
        {
            for(int j = 0; j < grid.length; j++)
            {
                if(grid[i][j] == '*')//if a letter is not present in this spot yet - insert a random letter
                    grid[i][j] = this.ranLetGen();
            }
        }
        
        for(int i = 0; i < grid.length; i++)//print grid
        {
            for(int j = 0; j < grid.length; j++)
                System.out.print(grid[i][j] + "  ");
            System.out.println();
        }
    }
    
    char ranLetGen()//Random Letter Generator
    {
        return ((char)(((Math.random() * 1039) % 26) + 65));
    }
}