package sample;
import java.io.File;
import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MapParser {

    public static ArrayList<ArrayList<Character>> parseMap(String link) throws FileNotFoundException{
        System.out.println("Function called");

        File f = new File(link);

        ArrayList<ArrayList<Character>> res = new ArrayList<ArrayList<Character>>();
        Scanner sc = new Scanner(f);

        while (sc.hasNextLine()) {
            ArrayList<Character> subL = new ArrayList<Character>();
            res.add(subL);
            String a = sc.nextLine();
            for (int i=0; i<a.length(); i++){
                Character c = (Character)a.charAt(i);
                subL.add(c);
            }
        }
        return res;
    }
}
