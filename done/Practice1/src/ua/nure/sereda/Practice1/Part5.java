package ua.nure.sereda.Practice1;

public class Part5 {

        public static int chars2digits(String num) {
            int res = 0;
            for(int i = 0; i < num.length(); i++){
                int lttr=num.charAt(i) - 64;
                res += lttr * (int)Math.pow(26,num.length() - (i+1));
            }
            return res;
        }

        public static String digits2chars(int numb) {
            int num = numb - 1;
            StringBuilder res = new StringBuilder();
            while (num >= 0) {
                int numLttr = (num%26) + 65;
                res.append((char) numLttr);
                num = (num / 26) - 1;
            }
            return  res.reverse().toString();

        }

        public static String nxtChar (String num) {
            int nxtNum = chars2digits(num) + 1;
            return digits2chars(nxtNum);
        }

        public static void main(String[] args){
            String [] lttrsTest = {"A", "B", "Z", "AA", "AZ", "BA", "ZZ", "AAA"};
            int lttrNum;
            for (String lttrs : lttrsTest) {
                lttrNum = Part5.chars2digits(lttrs);
                System.out.print(lttrs + " ==> " + lttrNum);
                System.out.println(" ==> " + Part5.digits2chars(lttrNum));
            }
        }
}

