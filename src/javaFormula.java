import java.lang.Math;
import java.io.*;


public class Frisbee {
//The x position of the frisbee.
private static double x;
//The y position of the frisbee.
private static double y;

//The x velocity of the frisbee.
private static double vx;
//The y velocity of the frisbee.
private static double vy;

//The acceleration of gravity (m/s^2).
private static final double g = -9.81;
//The mass of a standard frisbee in kilograms.
private static final double m = 0.175;
//The density of air in kg/m^3.
private static final double RHO = 1.23;
//The area of a standard frisbee.
private static final double AREA = 0.0568;
//The lift coefficient at alpha = 0.
private static final double CL0 = 0.1;
//The lift coefficient dependent on alpha.
private static final double CLA = 1.4;
//The drag coefficent at alpha = 0.
private static final double CD0 = 0.08;
//The drag coefficient dependent on alpha.
private static final double CDA = 2.72;
private static final double ALPHA0 = -4;


/**
* A method that uses Euler’s method to simulate the flight of a frisbee in
* two dimensions, distance and height (x and y, respectively).
*
*/
public static void simulate(double y0, double vx0, double vy0,
double alpha, double deltaT)
{

//Calculation of the lift coefficient using the relationship given
//by S. A. Hummel.
double cl = CL0 + CLA*alpha*Math.PI/180;
//Calculation of the drag coefficient (for Prantl’s relationship)
//using the relationship given by S. A. Hummel.
double cd = CD0 + CDA*Math.pow((alpha-ALPHA0)*Math.PI/180,2);
//Initial position x = 0.
x = 0;
//Initial position y = y0.
y = y0;
//Initial x velocity vx = vx0.
vx = vx0;
//Initial y velocity vy = vy0.
vy = vy0;

try{
    //A loop index to monitor the simulation steps.
    int k = 0;
    //A while loop that performs iterations until the y position
    //reaches zero (i.e. the frisbee hits the ground).
    while(y>0){
        //The change in velocity in the y direction obtained setting the
        //net force equal to the sum of the gravitational force and the
        //lift force and solving for delta v.
        double deltavy = (RHO*Math.pow(vx,2)*AREA*cl/2/m+g)*deltaT;
        
        //The change in velocity in the x direction, obtained by
        //solving the force equation for delta v. (The only force
        //present is the drag force).
        double deltavx = -RHO*Math.pow(vx,2)*AREA*cd*deltaT;
        //The new positions and velocities are calculated using
        //simple introductory mechanics.
        vx = vx + deltavx;
        vy = vy + deltavy;
        x = x + vx*deltaT;
        y = y + vy*deltaT;

        //Only the output from every tenth iteration will be sent
        //to the spreadsheet so as to decrease the number of data points.
        if(k%10 == 0){
            pw.print(x + "," + y + "," + vx);
            pw.println();
            pw.flush();
        }
        k++;
    }
    }
    catch(Exception e){
    System.out.println("Error, file frisbee.csv is in use.");}
    }
}