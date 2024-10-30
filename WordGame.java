import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.io.File;

class WordGame{
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        File file = new File("words.txt");

        String word = pickWord(file); // Picking a random 7 lettered word from the file

        int score = 0; // Initial Score

        ArrayList<String> inputString = new ArrayList<>(10); // List to store words inputed by the user.

        shuffleCharacter(word); // Shuffling the words of the picked word

        displayLetters(word); //Displaying letters of the shuffled word

        String option; // To store the option check the input given by the user
        do{
            option = scan.nextLine();

            switch (option) {
                case "mix":
                    shuffleCharacter(word);
                    displayLetters(word);
                    displayScore(score);
                    break;
                
                case "ls":
                    displayInputStrings(inputString);
                    displayScore(score);
                    break;

                default:
                    score = updateScore(score, option, file);
                    displayScore(score);
                    storeInputStrings(option, inputString);
                    break;
            }

        }while(option.compareTo("bye") != 0);

        scan.close();
    }

    //Picks a random word with 7 distinct letters
    public static String pickWord(File file){
        Random random = new Random();
        try{
            int currentLine = 1;
            int startLine = random.nextInt(8520);
            Scanner sc = new Scanner(file);

            while(currentLine < startLine && sc.hasNextLine()){
                sc.nextLine();
                currentLine++;
            }

            while(sc.hasNextLine()){
                String temp = sc.nextLine();

                if(temp.length() == 7 && areCharactersUnique(temp)){
                    return temp;
                }
            }
            
            sc.close();
        }
        catch(Exception e){
            System.out.println("An error occurred while reading the file.");
        }
        return "Not found";
    }


    //Checks if all the characters of the word are unique
    public static boolean areCharactersUnique(String s){
        boolean[] seenChars = new boolean[256];

        for (char c : s.toCharArray()) {
            if (seenChars[c]) {
                return false;
            }
            seenChars[c] = true;
        }

        return true;
    }

    //Shuffle characters of the word and return the character array
    public static char[] shuffleCharacter(String s){
        ArrayList<Character> chars = new ArrayList<>();
        char[] characterArray = new char[s.length()];
        for (char c : s.toCharArray()) {
            chars.add(c);
        }

        Collections.shuffle(chars);

        int i = 0;
        for (char c : chars) {
            characterArray[i] = c;
            i++;
        }

        return characterArray;
    }

    //Print shuffled characters in the terminal in right format
    public static void displayLetters(String word){
        char[] chars = shuffleCharacter(word);
        for(int i = 0; i < chars.length; i++){
            System.out.format("%7s", chars[i]);
        }
        System.out.println();
    }

    //Store input words in a list
    public static ArrayList<String> storeInputStrings(String s, ArrayList<String> inputStrings){
        inputStrings.add(s);
        return inputStrings;
    }

    //Display all the strings that was inputted by the user
    public static void displayInputStrings(ArrayList<String> inputStrings){
        for(String s: inputStrings){
            System.out.println("   " + s);
        }
    }

    //Update score based on input
    public static int updateScore(int score, String s, File file){
        int newScore = score;
        if(searchWord(file, s)){
            if(s.length() < 4){
                newScore = score;
            }

            else if(s.length() == 4){
                newScore = score + 1;
            }

            else{
                newScore = score + s.length();
            }
        }

        return newScore;
    }

    public static void displayScore(int score){
        System.out.println("Score: " + score);
    }

    //Search for the given word in the file
    public static boolean searchWord(File file, String target){
        try{
            Scanner sc = new Scanner(file);

            while(sc.hasNextLine()){
                String temp = sc.nextLine();

                if(temp.equals(target)){
                    return true;
                }
            }

            sc.close();
        }
        catch(Exception e){
            System.out.println("An error occurred while reading the file.");
        }

        return false;
    }
}