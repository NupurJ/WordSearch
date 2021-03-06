package wordsearch;
import java.io.*;
import java.util.*;

public class Main
{
    String name;//stores name of current user
    static int inp = 0;//stores number of custom lists created
    Input_Words input[] = new Input_Words[10];//array of objects
    String creator[] = new String[20];//to store the name of the creator of each custom list
    Scores sc = new Scores();
    

    public static void main(String args[])throws IOException
    {
        InputStreamReader read = new InputStreamReader(System.in);
        BufferedReader in = new BufferedReader(read);
        Main p = new Main();
        
        System.out.println("\t\t\t\t\t\tWORD SEARCH");      
        Information info = new Information();
        p.name();
        while(true)
        {
            int choice_1 = p.introduction();
            p.choice(choice_1, info);
            p.pause();
        }
    }

    void pause()
    {
        for(long i = 0; i < 400000000L; i++)
        {
        }
    }

    void name()throws IOException//to accept and store the name of the user
    {
        InputStreamReader read = new InputStreamReader(System.in);
        BufferedReader in = new BufferedReader(read);

        System.out.print("Enter your name: ");
        name = in.readLine();
        System.out.println("Hi, " + name + "!");
        pause();
    }

    int introduction()throws IOException//first screen viewed by the user
    {
        InputStreamReader read = new InputStreamReader(System.in);
        BufferedReader in = new BufferedReader(read);
        Scanner sc = new Scanner(System.in);

        System.out.println("\n\nEnter 1 if you want to create a custom list.");
        pause();
        System.out.println("Enter 2 if you want to play.");
        pause();
        System.out.println("Enter 3 if you want to view the high scores.");
        pause();
        System.out.println("Enter 4 to change the user.");
        pause();
        System.out.println("Enter 5 to exit.");
        String ch = "";

        boolean isInt = false;
        int choice = 0;
        while(isInt == false)
        {
            ch = in.readLine();

            try
            {
                Integer.parseInt(ch);
            }
            catch(NumberFormatException e)//if ch does not contain a numeric value
            {
                System.out.print("That's an invalid choice, " + name + ". But that's okay, please try again: ");
                continue;
            }
            
            choice = Integer.parseInt(ch);//stores value 'ch' input by user in 'choice' only is it is a numeric value
            if(choice > 5 || choice <= 0)//if choice does not fit the range 1-5
            {
                System.out.print("That's an invalid choice, " + name + ". But that's okay, please try again: ");
            }
            else//if value entered by user fits all the requirements
                isInt = true;
        }
        return choice;
    }

    void choice(int choice, Information info)throws IOException
    {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        switch(choice)
        {
            case 1://create custom list
                if(inp > 10)//if 10 custom lists have already been created
                {
                    System.out.println("Sorry, custom list limit has been reached.");
                    break;
                }
                
                input[inp] = new Input_Words();
                outer:
                while(true)
                {
                    info.themes[inp + 10] = input[inp].theme();
                    for(int i = 0; i < inp + 10; i++)//to check if theme input by user does not match a pre-existing theme
                    {
                        if(info.themes[inp + 10].equals(info.themes[i]) == true)
                        {
                            System.out.println("Oops, looks like that theme has already been taken. Please choose another name for your theme.");
                            continue outer;
                        }
                    }
                    break;
                }
            
                String w[] = input[inp].ask();//to accept words from user
                if(w.length == 1)//if the user wishes to delete the list
                    break;

                info.len[inp + 10] = w.length;//stores number of words in list
                for(int i = 0;  i <  w.length; i++)//stores each word in list
                    info.words[i][inp + 10] = w[i];
                info.creator[inp + 10] = name;//stores name of creator of list
                inp++;//stores value of number of lists created
                System.out.println("\nList created!");
                break;

            case 2://play
                //display list of themes to choose from
                System.out.printf("%3s %-25s %-20s %n", "", "THEME", "CREATOR");//headings
                System.out.println("-----------------------------------------");
                for(int i = 0; i < inp + 10; i++)
                {
                    String sr = Integer.toString(i + 1).concat(".");//print theme number
                    System.out.printf("%3s %-20s %15s %n", sr, info.themes[i], info.creator[i]);//print each theme and its creators name
                }
                this.pause();
                
                //accept user's choice
                System.out.print("\nEnter the serial number corresponding to the theme of your choice: ");
                boolean isInt = false;
                String s = "";
                int sno = 0;
                while(isInt == false)
                {
                    s = in.readLine();
                    try
                    {
                        Integer.parseInt(s);
                    }
                    catch(NumberFormatException e)//if input is not a number
                    {
                        System.out.print("That's an invalid choice, " + name + ". But that's okay, please try again: ");
                        continue;
                    }
                    
                    sno = Integer.parseInt(s);
                    if(sno > inp + 10 || sno <= 0)//if sr. no. corresponding to input is not present in list
                    {
                        System.out.print("That's an invalid choice, " + name + ". But that's okay, please try again: ");
                    }
                    else//if input meets all requirements
                        isInt = true;
                }
                
                System.out.print("Enter 1 for Easy, 2 for Medium and 3 for Hard: ");
                String l = in.readLine();
                while(l.equals("1") == false && l.equals("2") == false && l.equals("3") == false)//while user input does not match the requirements
                {
                    System.out.print("That's an invalid choice, " + name + ". But that's okay, please try again: ");
                    l = in.readLine();
                }
                int level = Integer.parseInt(l);
                int num = level * 5;//stores number of words to be found
                                     //easy - 5 words to be found
                                    //medium - 10 words to be found
                                   //hard - 15 words to be found

                String finalw[] = info.chooseWords(level * 5, info.words, info.len[sno - 1], sno - 1);//to randomly choose 'num' number of words from the stored list according to the user's choice of theme
                
                Grid g = new Grid(finalw);
                char grid[][] = g.array(finalw);//to generate grid with the random words stored in finalw[]
                System.out.println();
                System.out.println();

                Print pr = new Print();
                System.out.println("\t\tTHEME: " + info.themes[sno - 1]);
                System.out.println("  Level: " + ((level == 1)?"Easy":(level == 2)?"Medium":"Hard"));
                System.out.println("  Words to be found: " + num);
                System.out.println("  Enter any non-alphabetical character to exit. Your score will be saved as it is.\n");
                pr.grid(grid);//to print generated grid

                System.out.println("\nEnter the words you find:");
                Play pl = new Play();
                pl.input(finalw);//to take input of words from user
                sc.high(level, pl.score, name);//to check if user's score is among the top 3 scores in that level and store the high score
                break;

            case 3://scores
                sc.display();//displays scores
                break;

            case 4://switch user
                this.name();
                break;

            case 5://exit
                System.exit(0);
        }
    }
}