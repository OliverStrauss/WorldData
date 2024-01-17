package WorldData;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class country {
    private int rank;
    private String name;
    private String continent;

    private int [] pops;

    private double popPercent;

    public country(int rank, String name, String continent , int [] pops,double popPercent){
        this.rank = rank;
        this.name = name;
        this.continent = continent;
        this.pops = pops;
        this.popPercent = popPercent;
    }


    public String getName() {
        return name;
    }

    public String getContinent() {
        return continent;
    }

    public int[] getPops() {
        return pops;
    }

    public double getPopPercent(){
        return popPercent;
    }

    public static long getAvg(int[] pops) {
        if (pops.length == 0) {
            // Handle the case where the array is empty
            return 0; // You can choose another default value or throw an exception
        }

        long sum = 0;
        int count = 0; // Count of positive values
        for (int pop : pops) {

            if (pop > 0) {
                sum += pop;
                count++;
            }

        }



        return (long) sum / count;
    }

}
