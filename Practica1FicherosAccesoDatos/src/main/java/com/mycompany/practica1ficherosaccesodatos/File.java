/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.practica1ficherosaccesodatos;

/**
 *
 * @author josem
 */
public class File {

    private static int nextId = 1; // necesito que se gestione solo cada vez que añada un fichero. 
    // Esto o con uuid que genera un id unico según he buscado..
     private int id;
    private String name;
    private String extension;
    private double size;
    private String author;
    
    
    
    
    

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
    
   

    public File(String name, String extension, double size, String author) {
        this.id = nextId++;
        this.name = name;
        this.extension = extension;
        this.size = size;
        this.author = author;
    }

    public static int getNextId() {
        return nextId;
    }

    
    // ESTE TAMPOCO PUEDE SER PÚBLICO
//    public static void setNextId(int nextId) {
//        File.nextId = nextId;
//    }

    public int getId() {
        return id;
    }

    /* NO QUIERO QUE ESTE METODO SEA PÚBLICO
    public void setId(int id) {
        this.id = id;
    }
*/
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "File{" + "id=" + id + ", name=" + name + ", extension=" + extension + ", size=" + size + '}';
    }

    

}
