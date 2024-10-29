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

    public static String pickWord(File file){
        Random random = new Random();
        try{
            Scanner sc = new Scanner(file);
            boolean wordPicked = false;

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