/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.emojimaker;

/**
 *
 * @author robes
 */
public class RectangleDimensions {
    private double x;
    private double y;
    private double width;
    private double high;

    public RectangleDimensions(double x, double y, double width, double high) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.high = high;
    }
    
    public RectangleDimensions(){
        
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHigh() {
        return high;
    }

    public void setHigh(double high) {
        this.high = high;
    }
    
    
}
