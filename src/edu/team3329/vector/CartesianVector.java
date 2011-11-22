package edu.team3329.vector;

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
    	return Math.sqrt(Math.pow(this.X, 2)+Math.pow(this.Y, 2));
    }
    
    public double getDirection(){
    	return Math.atan(this.y/this.x);
    }
    
    public void add(CoordinateVector v){
	CartesianVector cv = CartesianVector(v);
	this.X += cv.getXCoordinate();
	this.Y += cv.getYCoordinate();   	
    }
    public double dotProduct(CoordinateVector v){
    	CartesianVector cv = CartesianVector(v);
    	return (this.X * cv.getXCoordinate()) + (this.Y * cv.getYCoordinate())
    }
}
