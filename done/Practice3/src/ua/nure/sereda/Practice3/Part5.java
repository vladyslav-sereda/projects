package ua.nure.sereda.Practice3;

public class Part5 {
    private final static String[] ROMAN = {"", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
    private final static int[] ARABIC = {0, 100, 90, 50, 40, 10, 9, 5, 4, 1};

    public static void main(String[] args) {
        String arrow = " ==> ";
        System.out.println(45 + arrow + decimal2Roman(45));
        System.out.println(24 + arrow + decimal2Roman(24));
        System.out.println(63 + arrow + decimal2Roman(63));
        System.out.println(98 + arrow + decimal2Roman(98));
        System.out.println("XLV   ==>" + roman2Decimal("XLV"));
        System.out.println("XXIV  ==>" + roman2Decimal("XXIV"));
        System.out.println("LXIII ==>" + roman2Decimal("LXIII"));
        System.out.println("XCVIII==>" + roman2Decimal("XCVIII"));

    }

    public static String decimal2Roman(int numb) {
        StringBuilder res = new StringBuilder();
        int num = numb;
        for (int i = 1; num > 0; i++) {
            for (; num >= ARABIC[i]; num -= ARABIC[i]) {
                res.append(ROMAN[i]);
            }
        }
        return res.toString();
    }

    public static int roman2Decimal(String s) {
        int res = 0;
        boolean firstIter = true;
        for (int length = s.length() - 1; length >= 0; length--) {
            for (int i = ROMAN.length - 1; i >= 0; i--) {
                if (!firstIter && String.valueOf(s.charAt(length)).equals(ROMAN[i]) && s.charAt(length) == s.charAt(length+1)) {
                    res += ARABIC[i];
                    break;
                } else if (String.valueOf(s.charAt(length)).equals(ROMAN[i]) && ARABIC[i] < res) {
                    res -= ARABIC[i];
                    break;
                } else if (String.valueOf(s.charAt(length)).equals(ROMAN[i])) {
                    res += ARABIC[i];
                    firstIter = false;
                    break;
                }
            }
        }
        return res;
    }
}
