/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkgExceptions;

/**
 *
 * @author emma
 */
public class ErrorObjeto extends Exception {
    public ErrorObjeto(String s){
        super("No pueden crearse mas de 3 instancias de la clase: " + s);
    }
}
