package Assignment1;

import java.util.Scanner;

public class ReverseNumber {
    public int reverse(int n){
        int res = 0;
        while(n > 0){
            res = res * 10 + n % 10;
            n = n / 10;
        }
        return res;
    }

    public void reverseNumber(){
        Scanner scanner = new Scanner(System.in);
        if(!scanner.hasNext()) throw new IllegalArgumentException();
        System.out.println("Please input the number you want to reverse:");
        String i = scanner.next();
        for(char c: i.toCharArray())
            if(c-'0'<0 || c-'0'>9)
                throw new IllegalArgumentException();
        System.out.println("Result:" + reverse(Integer.parseInt(i)));
    }

}
