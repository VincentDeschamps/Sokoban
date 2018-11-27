package sample;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class ParserMain {

    public static void main (String[] args){
        String fic = "/home/etud/o2163473/Bureau/map1.xsb";
        ArrayList<String> map;
        try {
            map = MapParser.parseMap(fic);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}


