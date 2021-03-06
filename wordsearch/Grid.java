package wordsearch;
import java.io.*;

public class Grid //generates the grid
{
    int grlen;//length of side of grid
    char grid[][];//stores grid
    static int h_or_v = 0;

    Grid(String words[])//constructor to initialize words[] and grid[][]
    {
        words = this.sort(words);//to sort words in descending order of their length

        grlen = words[0].length() + 2; //grid height and width will be two more than the length of the longest word
        int sum = 0;//stores sum of lengths of all words in words[]
        for(int i = 0; i < words.length; i++)
        {
            sum += words[i].length();
        }
        
        while(grlen * grlen < sum)//if number of cells in grid < number of letter to be fit - increase length of rid, thus increasing number of spaces in grid
            grlen++;
            
        grid = new char[grlen][grlen];

        for(int i = 0; i < grlen; i++)//set every element of grid to '*'
        {
            for(int j = 0; j < grlen; j++)
                grid[i][j] = '*';
        }
    }

    char[][] array(String words[])throws IOException//generates an array which contains the grid to be printed
    {
        int longest = 0;//index of longest word
        String cur_long = words[longest];//longest word
        for (int k = 0; k < words.length; k++)//insert words - loops continues till all the words have been inserted
        {
            int common_indices[][] = this.common(cur_long);//stores information about common letters between current longest word and words already in the grid
                                                          //cur_long[][0] contains index of letter in word
                                                         //cur_long[][1] contains index row of letter in grid
                                                        //cur_long[][2] contains index of column of letter in grid
            int common_letters = common_indices.length;//number of matches (number of sets of common letters)

            if(common_letters == 0)//if there are no common letters
            {
                this.insert(cur_long);
            }
            else//if there are common letters
            {
                double overlap_separate = Math.random();//stores a random number to decide whether words will be overlapped
                if(overlap_separate > 0.4D)//overlap in the grid 60% of the time
                {
                    //Word aligns?
                    boolean aligns = false;
                    int a = 0;
                    while(common_letters > 0)//check for each match - does the current word align?
                    {
                        int random = (int)(Math.random() * (common_letters - 1));//random row                      
                        aligns = true;

                        for(int i = 0; i < cur_long.length(); i++)//does the word align if placed vertically?
                        {
                            a = common_indices[random][1];
                            int b = common_indices[random][0] - i;
                            int x = a - b;

                            if(x >= grlen || x < 0 || random >= common_indices.length)//to prevent Array OutOfBounds exception from being thrown
                            {
                                aligns = false;
                                break;
                            }
                            if(grid[x][common_indices[random][2]] != '*' && grid[x][common_indices[random][2]] != cur_long.charAt(i))//if there is already a letter at that spot which does not match the correspponding letter on the word
                            {
                                aligns = false;
                                break;                              
                            }
                        }
                        if(aligns == true)//if aligned vertically - insert vertically
                        {                            
                            for(int i = 0; i < cur_long.length(); i++)
                            {                                
                                int b = common_indices[random][0] - i;
                                int x = a - b;
                                
                                if(random >= common_indices.length)
                                    continue;
                                    
                                if(x >= 0)
                                    grid[x][common_indices[random][2]] = cur_long.charAt(i);
                            }
                            break;
                        }
                        else if(aligns == false)//if word does not align vertically, check for horizontal alignment
                        {
                            aligns = true;
                            for(int i = 0; i < cur_long.length(); i++)//does the word align if placed horizontally?
                            {
                                int x = common_indices[random][2] - (common_indices[random][0] - i);                                
                                if(x >= grlen || x < 0 || random >= common_indices.length)//to prevent Array OutOfBounds exception from being thrown
                                {
                                    aligns = false;
                                    break;
                                }
                                if(grid[common_indices[random][1]][x] != '*' && grid[common_indices[random][1]][x] != cur_long.charAt(i))//if there is already a letter at that spot which does not match the correspponding letter on the word
                                {
                                    aligns = false;
                                    break;                                    
                                }
                            }
                            if(aligns == true)//if aligned horizontally - insert horizontally
                            {
                                for(int i = 0; i < cur_long.length(); i++)
                                {
                                    int x = common_indices[random][2] - (common_indices[random][0] - i);
                                    
                                    if(random >= common_indices.length)
                                        continue;
                                        
                                    if(x >= 0)
                                        grid[common_indices[random][1]][x] = cur_long.charAt(i);
                                }
                                break;
                            }
                        }

                        if(aligns == false)//if word does not align for current match - delete the row pertaining to that match
                        {
                            for(int i = random + 1; i < common_letters; i++)
                            {
                                for(int j = 0; j < 3; j++)
                                {
                                    common_indices[i - 1][j] = common_indices[i][j];
                                }
                            }
                        }

                        common_letters--;
                    }

                    if(aligns == false)//if word still does not align after checking for each match - insert separately, without overlapping
                        grid = this.insert(cur_long);
                }
                else//keep as separate words, without overlapping
                {
                    grid = this.insert(cur_long);
                }
            }

            longest++;//index of current longest unused word
            if(longest < words.length)
                cur_long = words[longest];//current longest word;
            else
                break;
        }

        return grid;
    }

    int[][] common(String cur_long)//checks if the word has any letters in common with the ones already in the grid, and returns the index of the common letters
    {
        int indices[][] = new int[grlen * grlen][3];
        for(int i = 0; i < grlen * grlen; i++)//initializes all elements of indices[][] to -1
        {
            indices[i][0] = -1;
            indices[i][1] = -1;
            indices[i][2] = -1;
        }

        int n = 0;//number of matches found
        for(int i = 0; i < grlen; i++)
        {
            for(int j = 0; j < grlen; j++)
            {
                for(int k = 0; k < cur_long.length(); k++)
                {
                    if(grid[i][j] == cur_long.charAt(k))//if match found -   
                                                       //indices[][0] contains index of letter in word
                                                      //indices[][1] contains index row of letter in grid
                                                     //indices[][2] contains index of column of letter in grid
                    {
                        indices[n][0] = k;
                        indices[n][1] = i;
                        indices[n][2] = j;
                        n++;
                    }
                }
            }
        }

        int final_common[][] = new int[n][3];//stores only indices and without '-1's, which are unnecessary
        for(int i = 0; i < n; i++)
        {
            final_common[i][0] = indices[i][0];
            final_common[i][1] = indices[i][1];
            final_common[i][2] = indices[i][2];
        }

        return final_common;
    }

    char[][] insert(String cur_long)throws IOException//inserts words in grid
    {
        try
        {
            double random1 = (float)Math.random() * (grlen + 1);
            int longinsertrow = (int)(random1);
            double random2 = (float)Math.random() * (grlen + 1);
            int longinsertcolumn = (int)(random2);

            char hvlong = this.h_v();
            boolean insertable = true;

            if (hvlong == 'h')//to insert word horizontally
            {
                while(longinsertrow > grlen - 1)//if row index is greater than length if grid, i.e., if the row does not exist in grid
                {
                    random1 = Math.random() * (grlen + 1);
                    longinsertrow = (int)(random1);
                }

                while(grlen - longinsertcolumn < cur_long.length())//if word will not fit if placed with its first letter in any cell of that column in grid
                {
                    random2 = Math.random() * (grlen + 1);
                    longinsertcolumn = (int)(random2);
                }
                
                for (int i = 0; i < cur_long.length(); i++)
                {
                    if(grid[longinsertrow][i + longinsertcolumn] != '*')//if the spot is not empty, i.e., it already contains another letter - break loop and look for another spot
                    {
                        insertable = false;
                        break;
                    }
                }

                if(insertable == true)//inserts word only if other words are not being overlapped or overwritten
                {
                    for (int i = 0; i < cur_long.length(); i++)
                    {
                        grid[longinsertrow][i + longinsertcolumn] = cur_long.charAt(i);
                    }
                }
                else//else calls the Insert function again
                {
                    this.insert(cur_long);
                } 
            }
            else//to insert word vertically
            {
                while(longinsertcolumn > grlen - 1)//if column index is greater than length if grid, i.e., if the column does not exist in grid
                {
                    random1 = Math.random() * (grlen + 1);
                    longinsertcolumn = (int)(random1);
                }

                while(grlen - longinsertrow < cur_long.length())//if word will not fit if placed with its first letter in any cell of that row in grid
                {
                    random2 = Math.random() * (grlen + 1);
                    longinsertrow = (int)(random2);
                }
                
                for (int i = 0; i < cur_long.length(); i++)//if the spot is not empty, i.e., it already contains another letter - break loop and look for another spot
                {
                    if(grid[i + longinsertrow][longinsertcolumn] != '*')
                    {
                        insertable = false;
                        break;
                    }
                }
                
                if(insertable == true)//inserts word only if other words are not being overlapped or overwritten
                {
                    for (int i = 0; i < cur_long.length(); i++)
                    {
                        grid[i + longinsertrow][longinsertcolumn] = cur_long.charAt(i);
                    }
                }
                else//else calls the Insert function again
                {
                    this.insert(cur_long);
                } 
            }
        }
        catch(StackOverflowError e)
        {
        }
        return grid;
    }

    char h_v()//decides whether to insert word horizontally or vertically
    {
        h_or_v++;
        
        if (h_or_v % 2 == 0)
            return 'h';
        else
            return 'v';
    }

    String[] sort(String words[])//Sorts words in descending order of their length (using bubble sort)
    {
        for(int i = 0; i < words.length; i++)
        {
            int longest = i;
            for(int j = i + 1; j < words.length; j++)
            {
                if(words[longest].length() < words[j].length())
                {
                    longest = j;
                }
            }
            if(longest != i)
            {
                String temp = words[i];
                words[i] = words[longest];
                words[longest] = temp;
            }
        }
        return words;
    }
}