/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ASUS
 */

public class Route {

    private String code;
    private String origin;
    private String destination;
    private double distance;
    private int time;

    public Route(String code, String origin, String destination, double distance, int time) {
        this.code = code;
        this.origin = origin;
        this.destination = destination;
        this.distance = distance;
        this.time = time;
    }

    public String getCode() { return code; }
    public String getOrigin() { return origin; }
    public String getDestination() { return destination; }
    public double getDistance() { return distance; }
    public int getTime() { return time; }

    @Override
    public String toString() {
        return origin + " -> " + destination;
    }
}
