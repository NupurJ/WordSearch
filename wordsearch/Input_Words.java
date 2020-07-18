package wordsearch;
import java.io.*;
import java.util.*;

public class Input_Words
{
    String[] ask()throws IOException
    {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        Input_Words ob = new Input_Words();

        System.out.print("Enter number of words: ");
        boolean isInt = false;
        String s = "";
        int n = 0;
        while(isInt == false)
        {
            s = in.readLine();
            try
            {
                Integer.parseInt(s);
            }
            catch(NumberFormatException e)//if input is not a number
            {
                System.out.print("That's an invalid choice. But that's okay, please try again: ");
                continue;
            }

            n = Integer.parseInt(s);
            if(n < 15)//to make sure the user enters 15 - 30 words
            {
                System.out.print("You need to enter at least 15 words. Please try again: ");
            }
            else if(n > 30)
            {
                System.out.print("You cannot enter more than 30 words. Please try again: ");          
            }
            else//if input meets all requirements
                isInt = true;
        }

        System.out.println("Enter words one by one. You can enter any digit or special character to exit.");

        String words[] = new String[n];//to store words input by the user
        int keep = 0;//number of words - this might be different from 'n' in case the user decides to exit and save the list before entering 'n' words
        for(int i = 0; i < n; i++)
        {
            System.out.print((i + 1) + ". ");//prints serial number

            String w = in.readLine();//accepts next word
            int e = ob.errorCheck(w, i, words);//checks if word has a whitespace, digit or special character

            if(e == 0)//if the input words has only letters, ie, no errors
                words[i] = w.toUpperCase();
            else if(e == 1 || e == 4)//(if word contains a whitespace OR if user enters a digit/special character but does not wish to exit) OR if user enters a previously entered word again
            {
                i--;
                continue;
            }
            else if(e == 2)//if user wishes to exit and save list
            {
                break;
            }                
            else if(e == 3)//if the user wishes to exit list (without saving)
            {
                String d[] = {"s"};
                return d;
            }
            keep++;
        }
        String words_final[] = new String[keep];//stores only as many words as the user has enterd - this may differ from length of words[] if user decides to exit list and save it before entering 'n' words

        for(int i = 0; i < keep; i++)//store words in words_final[]
            words_final[i] = words[i];

        return words_final;
    }

    String theme()throws IOException
    {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        Input_Words ob = new Input_Words();

        System.out.print("\nEnter the theme of your list: ");
        String theme = in.readLine();
        return theme;
    }

    int errorCheck(String word, int cur_word, String words[])
    {
        Scanner sc = new Scanner(System.in);

        for(int i = 0; i < cur_word; i++)//checks if the word input last has already been input before in the current list - repetition of words is not allowed
        {
            if(word.equalsIgnoreCase(words[i]))
            {
                System.out.println("It seems like you've already entered that word. Try another one!");
                return 4;
            }
        }

        for(int i = 0; i < word.length(); i++)
        {
            if(Character.isWhitespace(word.charAt(i)) == true)//in case word contains a whitespace - this is not allowed
            {
                System.out.println("Please make sure your word does not contain a whitespace.");
                return 1;
            }
            else if(Character.isLetter(word.charAt(i)) == false)//in case word contains a digit/special character - this indicates the user wants to exit
            {
                while(true)
                {  
                    System.out.println("Do you wish to exit? Enter y for yes and n for no.");
                    char c = sc.next().charAt(0);
                    switch(c)
                    {
                        case 'y'://if user wishes to exit
                        System.out.print("Okay. ");
                        if(cur_word >= 15)//if user has entered at least 15 words already - she has the option of saving her list with the current number of words
                        {
                            while(true)
                            {
                                System.out.println("Do you wish to save your list? Enter y for yes and n for no.");
                                char ch = sc.next().charAt(0);
                                switch(ch)
                                {
                                    case 'y'://in case user wants to save the list
                                        System.out.println("Okay. Your list has been saved.");
                                        return 2;

                                    case 'n'://in case user does not wish to save the list
                                        System.out.println("Okay. Your list has been deleted.");
                                        return 3;

                                    default:
                                        System.out.println("That is an invalid choice. Try again, please.");
                                        continue;
                                }
                            }
                        }
                        else//if 15 words have not been input yet - list may not be saved
                        {
                            return 3;
                        }

                        case 'n'://if user does not wish to exit
                            System.out.println("Okay. Please enter the next word: ");
                            return 1;

                        default:
                            System.out.println("That is an invalid choice. Try again, please.");
                            continue;
                    }
                }
            }
        }
        return 0;//if everything is normal - return 0
    }

}
