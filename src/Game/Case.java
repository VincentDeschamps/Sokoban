package Game;

public class Case {
    public static final String img = "";

    public GameObject content = null;
    private int x, y;

    public Case(int x, int y){
        this.x = x;
        this.y =y;
    }

    public Case(int x, int y, GameObject go){
        this(x,y);
        content = go;
    }

    public boolean isFree(){
        return content == null;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public boolean addContent(GameObject g){
        if (content != null){
            return false;
        }
        content = g;
        return true;
    }


    public void removeContent() {
        this.content = null;
    }
}
