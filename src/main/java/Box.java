public class Box {

    final static int SIZE = 11;

    static char[][] box=new char[SIZE][SIZE];

    static Box boxInstance=new Box();

    private Box(){clean();}

    private DisjSet[] rowDS=new DisjSet[SIZE];
    private DisjSet[] colDS=new DisjSet[SIZE];
    private DisjSet[] mdiDS=new DisjSet[2*SIZE-1];
    private DisjSet[] sdiDS=new DisjSet[2*SIZE-1];

    public static Box getBox(){
        return boxInstance;
    }

    public void clean(){

        for (int i=0;i<SIZE;i++){
            for (int j=0;j<SIZE;j++){
                box[i][j]='-';
            }
        }

        for (int i=0;i<SIZE;i++){
            rowDS[i]=new DisjSet(SIZE);
            colDS[i]=new DisjSet(SIZE);
        }
        for (int i=0;i<SIZE*2-1;i++){
            mdiDS[i]=new DisjSet(SIZE);
            sdiDS[i]=new DisjSet(SIZE);
        }

    }

    public boolean set(int x,int y, char symbol)throws IllegalInput{
        if (x<0 || x>=SIZE || y<0 || y>=SIZE || box[x][y]!='-') throw new IllegalInput();
        box[x][y]=symbol;
        boolean isOver = joinAndCheck(x,y,symbol);
        print();
        //rowDS[0].print();
        //colDS[0].print();
        //mdiDS[10].print();
        //sdiDS[10].print();
        return isOver;
    }

    private boolean joinAndCheck(int x,int y,char symbol){
        if (x-1>=0 && box[x-1][y]==symbol) colDS[y].combine(x,x-1);
        if (x+1<SIZE && box[x+1][y]==symbol) colDS[y].combine(x,x+1);
        if (colDS[y].sizeOf(x)>=5) return true;

        if (y-1>=0 && box[x][y-1]==symbol) rowDS[x].combine(y,y-1);
        if (y+1<SIZE && box[x][y+1]==symbol) rowDS[x].combine(y,y+1);
        if (rowDS[x].sizeOf(y)>=5) return true;

        if (x-1>=0 && y+1<SIZE && box[x-1][y+1]==symbol) mdiDS[x+y].combine((x-y+SIZE-1)/2,(x-y+SIZE-1-2)/2);
        if (x+1<SIZE && y-1>=0 && box[x+1][y-1]==symbol) mdiDS[x+y].combine((x-y+SIZE-1)/2,(x-y+SIZE-1+2)/2);
        if (mdiDS[x+y].sizeOf((x-y+SIZE-1)/2)>=5) return true;

        if (x-1>=0 && y-1>=0 && box[x-1][y-1]==symbol) sdiDS[x-y+SIZE-1].combine((x+y)/2,(x+y-2)/2);
        if (x+1<SIZE && y+1<SIZE && box[x+1][y+1]==symbol) sdiDS[x-y+SIZE-1].combine((x+y)/2,(x+y+2)/2);
        if (sdiDS[x-y+SIZE-1].sizeOf((x+y)/2)>=5) return true;

        return false;
    }

    public void print(){
        for (int i=SIZE-1;i>=0;i--){
            System.out.printf("%2d ",i);
            for (int j=0;j<SIZE;j++){
                System.out.print(" "+box[j][i]+" ");
            }
            System.out.println();
        }
        System.out.print("   ");
        for (int i=0;i<SIZE;i++){
            System.out.printf("%2d ",i);
        }
        System.out.println();
    }



}
