package sample;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class ParserMain {

    public static void main (String[] args){
        String fic = "/home/etud/o2163473/Bureau/map1.xsb";
        ArrayList<ArrayList<Character>> map;
        try {
            map = MapParser.parseMap(fic);
            System.out.println(map);
            System.out.println("File found");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}


