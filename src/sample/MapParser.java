package sample;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class MapParser {

    public static Map parseMap(String link) throws FileNotFoundException{
        System.out.println("Function called");

        File f = new File(link);

        Scanner sc = new Scanner(f);
        ArrayList<ArrayList<GameObject>> res = new ArrayList<ArrayList<GameObject>>();

        try (BufferedReader reader = new BufferedReader(new FileReader(new File(link)))) {
            String line;
            int y = 0;
            while ((line = reader.readLine()) != null) {
                    char[] lineArray = line.toCharArray();
                    ArrayList<GameObject> curLine = new ArrayList<GameObject>();
                    if(lineArray[0]!='T') {
                        for (int i = 0; i < lineArray.length; i++){
                            switch (lineArray[i]){
                                case '#':
                                    curLine.add(new Mur(i, y));
                                    break;
                                case '$':
                                    curLine.add(new Caisse(i,y));
                                    break;
                                case '.':
                                    curLine.add(null);
                                    break;
                                case '@':
                                    curLine.add(null);
                                    break;
                                case '+':
                                    curLine.add(null);
                                    break;
                                case ' ':
                                    curLine.add(null);
                            }
                        }
                        res.add(curLine);
                        y++;
                    }else{
                        break;
                    }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new Map(res);
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
