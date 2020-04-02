import java.util.Scanner;

public class WuZi {
    public static void main(String[] args){
        Box box=Box.getBox();

        int x,y;
        Scanner scanner=new Scanner(System.in);
        boolean isO=true;
        char symbol='O';

        box.print();
        System.out.println("O先走！");
        while (true){

            System.out.print("请输入坐标：");
            x=scanner.nextInt();
            y=scanner.nextInt();

            try {
                if(box.set(x,y,symbol)) {
                    System.out.print(symbol+" wins!");
                    break;
                }
            }catch (IllegalInput e){
                System.out.println("输入错误，请重新输入！");
                continue;
            }

            isO=isO?false:true;
            symbol=isO?'O':'X';
            if (isO) System.out.println("轮到O");
            else System.out.println("轮到X：");
        }
    }
}
