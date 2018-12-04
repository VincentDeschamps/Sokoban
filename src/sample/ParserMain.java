package sample;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class ParserMain {

    public static void main (String[] args){
        String fic = "novoban01.xsb";
        ArrayList<char[]> map;
        try {
            map = MapParser.parseMap(fic);
            System.out.println("File found");
            MapParser.readMap(map);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}


