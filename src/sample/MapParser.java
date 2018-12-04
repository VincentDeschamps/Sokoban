package sample;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class MapParser {

    public static ArrayList<char[]> parseMap(String link) throws FileNotFoundException{
        System.out.println("Function called");

        File f = new File(link);

        ArrayList<char[]> res = new ArrayList<char[]>();
        Scanner sc = new Scanner(f);

        try (BufferedReader reader = new BufferedReader(new FileReader(new File(link)))) {
            String line;
            while ((line = reader.readLine()) != null) {
                    char[] lineArray = line.toCharArray();
                    if(lineArray[0]!='T') {
                        res.add(lineArray);
                    }else{
                        break;
                    }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return res;
    }

    public static void readMap(ArrayList<char[]> map){
        String res = "";
        for(char[] c : map){
            for(int i=0; i<c.length; i++){
                res+=c[i];
            }
            res+="\n";
        }
        System.out.println(res);
    }

}
