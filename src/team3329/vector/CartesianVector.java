package team3329.vector;

import com.sun.squawk.util.*;

public class CartesianVector implements CoordinateVector{

    private double X;
    private double Y;

    public CartesianVector(){
    	this.X = 0;
    	this.Y = 0;
    }
    public CartesianVector(double x, double y){
    	this.X = x;
    	this.Y = y;
    }
    public CartesianVector(CoordinateVector v){
    	this.X = v.getDistance()*Math.cos(v.getDirection());
        this.Y = v.getDistance()*Math.sin(v.getDirection());
    }

    public double getXCoordinate(){
    	return this.X;
    }

    public double getYCoordinate(){
    	return this.Y;
    }

    public double getDistance(){
    	return Math.sqrt(MathUtils.pow(this.X, 2)+MathUtils.pow(this.Y, 2));
    }

    //returns angle in radians
    public double getDirection(){
    	return MathUtils.atan(this.Y/this.X);
    }

    public void setDistance(double d)
    {
        //get temp current angle
        double a = MathUtils.atan2(this.X, this.Y);

        //set the x and y keeping the same angle and changing only mag
        this.X = d * Math.cos(a);
        this.Y = d * Math.sin(a);
    }

    public void setDirection(double d)
    {
        //create a temperary magnitude
        double m = Math.sqrt(MathUtils.pow(this.X, 2) + MathUtils.pow(this.Y, 2));

        //set direction by keeping the same magnitude
        this.X = m * Math.cos(d);
        this.Y = m * Math.sin(d);
    }

    public void add(CoordinateVector v){
	CartesianVector cv = new CartesianVector(v);
	
        this.X += cv.getXCoordinate();
	this.Y += cv.getYCoordinate();
    }
    
    public double dotProduct(CoordinateVector v){
    	CartesianVector cv = new CartesianVector(v);
    	return (this.X * cv.getXCoordinate()) + (this.Y * cv.getYCoordinate());
    }
}