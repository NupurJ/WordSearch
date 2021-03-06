package wordsearch;
import java.io.*;
import java.util.*;

public class Play
{
    public int score = 0;//stores score of the user

    void input(String words[])throws IOException
    {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        Scanner sc = new Scanner(System.in);

        int len = words.length;
        int lencopy = len;

        String input = "";
        String foundw[] = new String[len];
        outer: 
        for(int a = 0; a < lencopy; a++)
        {
            input = sc.next();
            for(int k = 0; k < input.length(); k++)//check if each character in the input is an alphabet
            {
                if(Character.isLetter(input.charAt(k)) == false)//if input contins non-alphabetical character - ask user if she wants to exit
                {
                    while(true)
                    {
                        System.out.println("Do you want to exit? Type y for yes and n for no.");
                        char exit = sc.next().charAt(0);
                        switch(exit)
                        {
                            case 'y':
                                System.out.println("Okay. Your score is: " + score + "\n");
                                System.out.println("Words you didn't find are:");
                                for(int i = 0; i < len; i++)
                                {
                                    System.out.println(words[i]);
                                }
                                break outer;
                                
                            case 'n':
                                System.out.println("Okay. Please enter next word:");
                                a--;
                                continue outer;
                                
                            default:
                                System.out.println("Invalid input, please try again.");
                                continue;
                        }
                    }
                }
            }

            boolean found = false;

            for(int j = 0; j < lencopy - len; j++)
            {
                if(input.trim().equalsIgnoreCase(foundw[j]))
                {
                    System.out.println("You have already entered that word!");
                    a--;
                    continue outer;
                }
            }
                
            for(int i = 0; i < len; i++)//checks if word is in list
            {
                if(input.trim().equalsIgnoreCase(words[i]))
                {                    
                    score += 10;
                    foundw[a] = input;
                    for(int j = i + 1; j < len ; j++)//if found - removes word from list
                        words[j - 1] = words[j];
                    len--;
                    if(len == 0)
                    {
                        System.out.println("You have found all the words. Good job! Your score is " + score);
                        break outer;
                    }
                    System.out.println("Correct!\tScore: " + score + "\tNumber of words to go: " + len);
                    found = true;
                    break;
                }
            }

            if(found == false)
            {
                score -= 2;
                a--;
                System.out.println("Oops, that isn't right. Try again!\t Score = " + score);
            }
        }
    }
}
