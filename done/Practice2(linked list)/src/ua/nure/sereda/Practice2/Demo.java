package ua.nure.sereda.Practice2;

import java.util.Iterator;

public class Demo {
    public static void main(String[] args) {

        System.out.println("==== Part1");
        // test add
        MyList con = new MyListImpl();
        con.add("A");
        con.add("B");
        con.add(1);
        con.add(7);
        System.out.println(con); // [A, B, 1, 7]

        // test clear
        con.clear();
        System.out.println(con); // []
        con.add("A");
        con.add("B");
        con.add(7);
        System.out.println(con); // [A, B, 7]

// test remove
        con.remove("B");
        con.remove("C");
        System.out.println(con); // [A, 7]

// test size
        System.out.println(con.size()); // 2

// test contains
        System.out.println(con.contains("B")); // false
        System.out.println(con.contains(7)); // true

// test toArray
        for (Object el : con.toArray()) {
            System.out.print(el + " "); // A 7
        }
        System.out.println();

// test containsAll
        MyList anotherList = new MyListImpl();
        anotherList.add("A");
        anotherList.add(9);
        System.out.println(con.containsAll(anotherList)); // false
        con.add(9);
        System.out.println(con.containsAll(anotherList)); // true

// ================================

        System.out.println("==== Part2");

        MyList con2 = new MyListImpl();
        con2.add("A");
        con2.add(7);
        con2.add(9);
        for (Object el : con) {
            System.out.print(el + " "); // A 7 9
        }
        System.out.println();

        Iterator<Object> it = con.iterator();
        while (it.hasNext()) {
            System.out.print(it.next() + " "); // A 7 9
        }
        System.out.println();

        it = con.iterator();
        it.next();
        it.next();
        it.remove();
        System.out.println(con); // [A, 9]
        System.out.println(it.hasNext()); // true
        System.out.println(it.next()); // 9
        System.out.println(it.hasNext()); // false

// ================================

        System.out.println("==== Part3");

        MyList con3 = new MyListImpl();
        con3.add("A");
        con3.add("B");
        con3.add(7);
        ListIterator li = con3.listIterator();
        System.out.println(con3); // [A, B, 7]

        while (li.hasNext()) {
            System.out.print(li.next() + " "); // A B 7
        }
        System.out.println();

        li = con3.listIterator();
        li.next();
        li.next();
        li.set("C");
        System.out.println(con3); // [A, C, 7]

        while (li.hasPrevious()) {
            System.out.print(li.previous() + " "); // C A
        }
        System.out.println();

        li.set(1);
        System.out.println(con3); // [1, C, 7]
        con.add("A");
    }
}

