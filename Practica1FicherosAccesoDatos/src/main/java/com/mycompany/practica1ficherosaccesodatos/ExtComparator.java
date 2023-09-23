/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.practica1ficherosaccesodatos;

import java.util.Comparator;

/**
 *
 * @author Jose
 */
public class ExtComparator implements Comparator<File>{
    public int compare(File f1, File f2){
        return f1.getExtension().compareTo(f2.getExtension());
    }
}
