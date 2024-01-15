import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * @description - Main class (prefix search & word search)
 * @author - Mark Santiago
 * */
public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        //Get the definitions from the txt file
        Dictionary dictionary = new Dictionary();
        try
        {
            Scanner readFile = new Scanner(new File("words.txt"));
            while (readFile.hasNextLine())
            {
                //Insert the values into the tree
                //dictionary.addWord(readFile.nextLine(), readFile.nextLine());
                dictionary.addWord(readFile.nextLine(), readFile.nextLine());
                //System.out.println(readFile.nextLine());
            }
        }
        catch (FileNotFoundException e) //If for some reason word.txt doesn't exist
        {
            e.printStackTrace();
            throw new FileNotFoundException("--File doesn't exist OR wrong path specified!");
        }
        Scanner in = new Scanner(System.in);
        //dictionary.addWord("cat", "cat:chat");
        //dictionary.addWord("car", "car:voiture");
        //dictionary.addWord("cart", "cart:chariot");


        do
        {
            String nextWord = in.next();
            if (nextWord.equals("quit")) break;
            //System.out.println(dictionary.wordSearch(nextWord));
            dictionary.depthFirstPrefixPrint(nextWord);
        }
        while (in.hasNext());
    }
}