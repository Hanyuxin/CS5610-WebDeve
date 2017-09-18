package Assignment1;

import java.util.Scanner;

public class ReverseNumber {
    public int reverse(int n){
        if(n==Integer.MIN_VALUE) throw new IllegalArgumentException("Overflow");
        if(n < 0) return -reverse(Math.abs(n));
        int res = 0;
        while(n > 0){
            res = res * 10 + n % 10;
            n = n / 10;
        }
        if(res<0) throw new IllegalArgumentException("Overflow");
        return res;
    }

    public void reverseNumber(){
        Scanner scanner = new Scanner(System.in);
        if(!scanner.hasNext()) throw new IllegalArgumentException();
        System.out.println("Please input the number you want to reverse:");
        String s = scanner.next();
        for(int i = 1;i < s.length(); i++)
            if(s.charAt(i)-'0'<0 || s.charAt(i)-'0'>9)
                throw new IllegalArgumentException();
        System.out.println("Result:" + reverse(Integer.parseInt(s)));
    }

}
