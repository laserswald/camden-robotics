/*
 * PolarVector.java
 * 
 * Copyright 2011 Ben Davenport-Ray <ben@ben-desktop>
 * 
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA 02110-1301, USA.
 * 
 * 
 */


public class PolarVector implements CoordinateVector{
	private double magnitude;
	private double angle;
	
	public PolarVector(){
	        this.magnitude = 0;
        	this.angle = 0;
	}
	
	public PolarVector(double magnitude, double angle){
		this.magnitude = magnitude;
		this.angle = angle;
	}

	public PolarVector(CartesianVector v){
		this.magnitude = Math.sqrt(MathUtils.pow(v.getXCoordinate(),2)+MathUtils.pow(v.getYCoordinate(),2));
        	this.angle = MathUtils.atan(v.getYCoordinate()/v.getXCoordinate());
	}
	
	public double getMagnitude(){
		return this.magnitude;
	}
	
	public double getAngle(){
		return this.angle;
	}
	
	public double getDistance(){
		return getMagnitude();
	}
	
	public double getHeading(){
		return getAngle();
	}
	
	public void add(CoordinateVector v){
		this.magnitude += v.getDistance;
		this.angle += v.getHeading;
	}
	
	public double dotProduct(CoordinateVector v){
		return (this.magnitude * v.getDistance() * Math.cos(this.angle - v.getHeading));
	}
}

