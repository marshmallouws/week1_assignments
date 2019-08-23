/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author Annika
 */
public class Animals {
    private String name;
    private int birthYear;
    private String sound;
    
    public Animals(String name, int birthYear, String sound) {
        this.name = name;
        this.birthYear = birthYear;
        this.sound = sound;
    }
    
    public Animals() {     
    }
}
