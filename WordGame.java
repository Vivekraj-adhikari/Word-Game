import java.util.Scanner;
import java.util.Random;
import java.io.File;

class WordGame{
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        File file = new File("words.txt");

        System.out.println("Enter a word: ");
        String word = pickWord(file);

        System.out.println(searchWord(file, word));

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


    //Search for the given word in the file
    public static String searchWord(File file, String target){
        try{
            Scanner sc = new Scanner(file);

            while(sc.hasNextLine()){
                String temp = sc.nextLine();

                if(temp.equals(target)){
                    return temp;
                }
            }

            sc.close();
        }
        catch(Exception e){
            System.out.println("An error occurred while reading the file.");
        }

        return "Word not found";
    }
}