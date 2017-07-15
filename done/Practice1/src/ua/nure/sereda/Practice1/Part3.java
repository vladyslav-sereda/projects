package ua.nure.sereda.Practice1;

public class Part3 {
    public static void main(String[] args)   {
        int x = Integer.parseInt(args[0]);
        int y = Integer.parseInt(args[1]);
        while(x != y){
            if(x > y){
                x -= y;
            } else{
                y -= x;
            }
        }
        System.out.println(x);
    }
}
