package ua.nure.sereda.Practice6.part2;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;


public class Part2 {

    private static final int N = 1000;
    private static final int K = 31;
    private static List<Integer> arrList = new ArrayList<>();
    private static List<Integer> linkList = new LinkedList<>();
    private static Iterator<Integer> arrIter;
    private static Iterator<Integer> linkIter;

    public static void main(String[] args) {
        Part2.InitList();

        long time = System.currentTimeMillis();
        deleteK(arrList, arrIter);
        System.out.println("Array List ==> " + (System.currentTimeMillis() - time) + " ms");
        time = System.currentTimeMillis();
        deleteK(linkList,linkIter);
        System.out.println("Linked List ==> " + (System.currentTimeMillis() - time) + " ms");
    }

    private static void deleteK(List list, Iterator it) {
        int i = 0;
        while(list.size() > 1){
            i++;
            if (!it.hasNext()) {
                it = list.iterator();
            } else {
                it.next();
                if (i % K == 0) {
                    it.remove();
                    System.out.println(list);
                }
            }
        }
    }


    private static void InitList() {
        for (int i = 0; i < N; i++) {
            arrList.add(i);
            linkList.add(i);
        }
        arrIter = arrList.iterator();
        linkIter = linkList.iterator();
    }
}
