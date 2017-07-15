package ua.nure.sereda.Practice1;

public class Demo {
    public static void main(String[] args) {
        //Part1
        System.out.println("==Task1==");
        Part1.main(args);

        //Part2
        System.out.println("==Task2==");
        if(args.length > 1) {
            Part2.main(args);
        }

        //Part3
        System.out.println("==Task3==");
        if(args.length > 1) {
            Part3.main(args);
        }

        //Part4
        System.out.println("==Task4==");
        if(args.length> 0 ) {
            Part4.main(args);
        }

        //Part5
        System.out.println("==Task5==");
            Part5.main(args);
    }
}
