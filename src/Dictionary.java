/**
 * @description - This class represents an English to *Language* Dictionary
 *
 * @author - Mark Santiago
 */
public class Dictionary {
    private TrieNode root; //Start of the tree
    private int depth;
    private final String alphabetOrder = "abcdefghijklmnopqrstuvwxyz"; //for indexing :)


    /** Constructor Sets the root of the trie to an empty string.
     */
    public Dictionary(){
        root = new TrieNode();
        root.definitionOfWord = "";
        root.isWord = false;
        depth = 0;
    }

    /** Method to add a word to the existing tree along with its translation
     * @para word w to be added to the tree with its definiton or translation. In this case it will be a transaltion
     */
    public void addWord(String w,String definition){
        //Pass in the word we are looking for
        addWordHelper(w, definition, root);
    }

    public void addWordHelper (String w, String definition, TrieNode thisNode) {
        //TODO take each letter of the word and separate them, each letter going to a new node

        //Get charAt start of word
        char currentLetter = w.charAt(0);
        //Split the string
        String remainingString = w.substring(1, w.length());

        //gets the index position of that letter in the alphabet cuz I can't be bothered to write a for loop
        int letterIndex = alphabetOrder.indexOf(currentLetter);

        //Adding the last letter of the word
        if (remainingString.equals("")) {
            boolean lastLetterExists = false;

            if (thisNode.alphabet[letterIndex] != null)
            {
                lastLetterExists = true;
                thisNode.alphabet[letterIndex].definitionOfWord = definition;
                thisNode.alphabet[letterIndex].isWord = true;
                return;
            }

            if (!lastLetterExists)
            {
                if (thisNode.alphabet[letterIndex] == null)
                {
                    thisNode.alphabet[letterIndex] = new TrieNode();
                    thisNode.alphabet[letterIndex].letter = currentLetter;
                    thisNode.alphabet[letterIndex].isWord = true;
                    thisNode.alphabet[letterIndex].definitionOfWord = definition;
                    return;
                }
            }
        }

        //Checks if currentLetter is inside this TrieNode
        if (thisNode.alphabet[letterIndex] != null)
        {
            addWordHelper(remainingString, definition, thisNode.alphabet[letterIndex]);
            return;
        }

        //If the character we are looking for does not exist inside this trieNodes list then we add it in
        if (remainingString.length() > 0) {
            if (thisNode.alphabet[letterIndex] == null)
            { //if this index is empty then we need to instantiate it
                thisNode.alphabet[letterIndex] = new TrieNode();
                thisNode.alphabet[letterIndex].letter = currentLetter;
                addWordHelper(remainingString, definition, thisNode.alphabet[letterIndex]);
            }
        }
    }

    /** Method to return the definition of the word if the word is found, otherwise returns null
     * @para word s is the word we are searching for
     * @return string s which is the word to be searched, null otherwise
     */

    //Work on this last
    public String wordSearch(String s) {
        return wordSearchHelper(s, root);
    }

    public String wordSearchHelper(String s, TrieNode thisNode)
    {
        char currentLetter = s.charAt(0);
        String remainingString = s.substring(1, s.length());
        int letterIndex = alphabetOrder.indexOf(currentLetter);

        if (!Character.isLetter(currentLetter)) return "Word not found";

        if (remainingString.equals(""))
        {
            if (thisNode.alphabet[letterIndex] != null
                    && thisNode.alphabet[letterIndex].isWord)
            {
                return thisNode.alphabet[letterIndex].definitionOfWord;
            }
        }
        else
        {
            if (thisNode.alphabet[letterIndex] != null)
            {
                return wordSearchHelper(remainingString, thisNode.alphabet[letterIndex]);
            }
        }
        return "Word not found";
    }

    /** Method to print all the words with a given prefix and their definitions.
     * @para word s is the prefix we are searching for
     */

    public void depthFirstPrefixPrint(String s){
        //TODO will be used for finding all words of a given prefix
        depthFirstPrefixPrintHelper(s, root);
    }

    public void depthFirstPrefixPrintHelper(String word, TrieNode thisNode)
    {
        char currentLetter = ' ';
        String remainingString = word;
        int letterIndex = 0;
        if (word.length() > 0)
        {
            currentLetter = word.charAt(0);
            remainingString = word.substring(1, word.length());
            letterIndex = alphabetOrder.indexOf(currentLetter);
        }

        if (word.length() == 0)
        {
            if (thisNode.isWord)
            {
                System.out.println(thisNode.definitionOfWord);
            }

            for (int i = 0; i < 26; i++)
            {
                if (thisNode.alphabet[i] != null)
                {
                    depthFirstPrefixPrintHelper(remainingString, thisNode.alphabet[i]);
                }
            }
        }
        else
        {
            if (thisNode.alphabet[letterIndex] != null)
            {
                depthFirstPrefixPrintHelper(remainingString, thisNode.alphabet[letterIndex]);
            }
            else
            {
                System.out.println("Prefix not found");
            }
        }
    }



    /** Class to represent a node with 26 potential children
     *   Uses an array to store the references to children <- reference to all letters stored in the node
     *   @author RM
     */

    public class TrieNode {

        //used to store translations or definitions
        private String definitionOfWord=null;
        //26 potential children of this node
        private TrieNode[] alphabet;
        //flag for if this node is the end node of a word
        private boolean isWord;
        // [a-z] what letter is this node
        private char letter;

        public TrieNode(){
            alphabet = new TrieNode[26]; //26 references for 26 letters [a-z]
        }
    }
}
