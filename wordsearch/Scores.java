package wordsearch;
public class Scores
{
    public static int scores[][] = new int[3][3];
    public static String names[][] = new String[3][3];
    
    Scores()//constructor to initialise high scores
    {
        for(int i = 0; i < 3; i++)
        {
            for(int j = 0; j < 3; j++)
            {
                scores[i][j] = 0;
                names[i][j] = "";
            }
        }
    }
    
    void high(int level, int score, String name)//checks if the score attained by user is a high score
    {
        for(int i = 0; i < 3; i++)
        {
            if(names[i][level - 1].equals("") == true|| scores[i][level - 1] < score)//if n high score has been made in that level yet OR if score attained by current player is higher than that attained by a previous player
            {
                insert(level - 1, score, name, i);
                break;
            }
        }
    }
    
    void insert(int level, int score, String name, int pos)//inserts score and name of player in the appropriate position
    {
        for(int i = 2; i > pos; i--)//shifts previous high scores to make space for current high score
        {
            scores[i][level] = scores[i - 1][level];
            names[i][level] = names[i - 1][level];
        }
        scores[pos][level] = score;//inserts current high score
        names[pos][level] = name;//inserts name of player
    }
    
    void display()//displays high score table
    {
        System.out.printf("%16s %4s %14s %26s %6s %13s %25s %4s %19s\n", "", "EASY", "", "", "MEDIUM", "", "", "HARD", "");
        System.out.printf("%3s %-15s %15s %10s %3s %-15s %15s %10s %3s %-15s %15s\n", "", "NAME", "SCORE", "", "", "NAME", "SCORE", "", "", "NAME", "SCORE");
        for(int i = 0; i < 3; i++)
        {
            for(int j = 0; j < 3; j++)
            {
                if(names[i][j].equals("") == false)
                    System.out.printf("%3s %-15s %15d %10s ", Integer.toString(i + 1).concat("."), names[i][j], scores[i][j], "");
                else
                    System.out.printf("%3s %-15s %15s %10s", Integer.toString(i + 1).concat("."), "--", "--", "");
                
            }
            System.out.println();
        }
        System.out.println();
    }
}