package sample;
import Game.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class MapParser {

    public static Map parseMap(String link) throws FileNotFoundException{
        System.out.println("Function called");

        File f = new File(link);

        Scanner sc = new Scanner(f);
        ArrayList<ArrayList<Case>> res = new ArrayList<ArrayList<Case>>();
        Joueur j = null;

        try (BufferedReader reader = new BufferedReader(new FileReader(new File(link)))) {
            String line;
            int y = 0;
            while ((line = reader.readLine()) != null) {
                    char[] lineArray = line.toCharArray();
                    ArrayList<Case> curLine = new ArrayList<Case>();
                    if(lineArray[0]!='T') {
                        for (int i = 0; i < lineArray.length; i++){
                            switch (lineArray[i]){
                                case GameChars.WALL:
                                    curLine.add(new Case(i, y, new Mur(i, y)));
                                    break;
                                case GameChars.CAISSECASE:
                                    curLine.add(new Case (i, y, new Caisse(i,y)));
                                    break;
                                case GameChars.EMPTYARRIVED:
                                    curLine.add(new CaseArrive(i,y));
                                    break;
                                case GameChars.PLAYERCASE:
                                    j = new Joueur(i,y);
                                    curLine.add(new Case(i, y, j));
                                    break;
                                case GameChars.PLAYERARRIVEDCASE:
                                    j = new Joueur(i,y);
                                    curLine.add(new CaseArrive(i,y,j));

                                    break;
                                case GameChars.EMPTYCASE:
                                    curLine.add(new Case(i,y));
                                    break;
                                case GameChars.CAISSEARRIVED:
                                    curLine.add(new CaseArrive(i, y, new Caisse(i,y)));
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

        return new Map(res, j);
    }

    public static void readMap(Map map){
        String res = "";
        for(ArrayList<Case> line : map.map){
            for (Case c : line){
                if (c instanceof CaseArrive){
                    if (c.isFree()){
                        res += GameChars.EMPTYARRIVED;
                    } else if (c.content instanceof Joueur){
                        res += GameChars.PLAYERARRIVEDCASE;
                    } else {
                        res += GameChars.CAISSEARRIVED;
                    }
                } else if (c.content == null){
                    res += GameChars.EMPTYCASE;
                } else if (c.content instanceof Caisse){
                    res += GameChars.CAISSECASE;
                } else if (c.content instanceof Mur){
                    res += GameChars.WALL;
                } else {
                    res += GameChars.PLAYERCASE;
                }
            }
            res+="\n";
        }
        System.out.println(res);
    }

}
