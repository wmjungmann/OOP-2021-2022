package ie.tudublin;

import processing.core.PApplet;

public class Arrays extends PApplet{


    // Allocaiton of a new float array
    float[] rainfall = { 45, 37, 55, 27, 38, 50, 79, 48, 104, 31, 100, 58 };

    String[] months = {"JAN", "FEB", "MAR", "APR", "MAY", "JUN", "JUL", "AUG", "SEP", "OCT", "NOV", "DEC"};

    // Different ways of allocating a new array
    float[] a1 = new float[100];
    float[] a2; // Results in null pointer exception

    int leastIndex = 0;
    int mostIndex = 0;

    public void settings()
    {
        size(500, 500);
    }

    public void setup()
    {   
        // Ways to iterate over arrays

        // 1. For loop
        for(int i = 0; i < rainfall.length; i ++)
        {
            println(rainfall[i] + "\t" + months[i]);
        }


        // 2. Enhanced for loop - less functionality as more code needed to display months aswell
        // int j = 0;
        for(float r:rainfall)
        {
            println(r);
            // println(r + "\t" + months[ij);
            // j ++;
        }


        // Iterating backwards through an array
        for(int i = rainfall.length - 1; i >= 0; i --)
        {
            println(rainfall[i] + "\t" + months[i]);
        }


        // Finding the month with the lowest, highest, total and average level of rainfall
        float totalRain = 0; 
        
        

        for(int i = 0; i < rainfall.length; i++)
        {
            // Find least amount of rainfall
            if( rainfall[i] < rainfall[leastIndex])
            {
                leastIndex = i;
            }

            // Find most amount of rainfall
            if( rainfall[i] > rainfall[mostIndex])
            {
                mostIndex = i;
            }

            totalRain += rainfall[i];
        }


        println("The month with the least rain is " + months[leastIndex] + ": " + rainfall[leastIndex]);
        println("The month with the least rain is " + months[mostIndex] + ": " + rainfall[mostIndex]);
        println("Total rainfall is: " + totalRain);
        println("Average rainfall is: " + (totalRain / 12));

    }

    public void draw()
    {
        
        background(0);
        colorMode(HSB);

        float barWidth = width / rainfall.length;

        for(int i = 0; i < rainfall.length; i ++)
        {
            // Find position of bars
            float x = map(i, 0, rainfall.length, 0, width);

            // Change the colour of the bars
            int c=(int)map(i, 0, rainfall.length, 0, 255);
            fill(c, 255, 255);
            float h = map(rainfall[i], 0, rainfall[mostIndex], 0, -height);
            // Draw the bars
            rect( x, height, barWidth, h);
            
            // Print te names of the months
            fill(255);
            textAlign(CENTER, CENTER);
            text(months[i], x + (barWidth / 2), height - 50);
        }
    }

}
