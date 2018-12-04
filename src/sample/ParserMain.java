package sample;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class ParserMain {

    public static void main (String[] args){
        String fic = "novoban01.xsb";
        try {
            Map map = MapParser.parseMap(fic);
            System.out.println("File found");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}


