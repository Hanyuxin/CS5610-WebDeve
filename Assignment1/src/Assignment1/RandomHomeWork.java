package Assignment1;

import java.util.Random;

public class RandomHomeWork {
    public static void main(String[] args){
        RandomHomeWork randomHomeWork = new RandomHomeWork();
        System.out.println(randomHomeWork.getCourse());
    }

    public String getCourse(){
        String[] courses = new String[4];
        courses[0] = "Algorithm";
        courses[1] = "Computer Network";
        courses[2] = "Data Structure";
        courses[3] = "Discrete mathematics";
        Random rand = new Random();
        return courses[rand.nextInt(4)];
    }
}
