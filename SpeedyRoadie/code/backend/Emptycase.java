/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend;

/**
 * Layoutable représentant l'absence de tout autre chose chose. Une case vide.
 * @author Corentin Dachy
 */
public class Emptycase implements Layoutable{

    @Override
    public String getType() {
        return "emptycase";
    }

    @Override
    public String toString() {
        return " ";
    }
    
    @Override
    public char toChar(){
        return ' ';
    }
    
    
}
