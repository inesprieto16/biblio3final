/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package es.uvigo.esei.pro2.core;
import nu.xom.*;
/**
 *
 * @author Nani
 */
public class Fecha {
    public static final String EtqDia="dia";
    public static final String EtqMes="mes";
    public static final String EtqAño="año";
    private int dia;
    private int mes;
    private int anho;

    public Fecha(int dia, int mes, int anho) {
        this.dia = dia;
        this.mes = mes;
        this.anho = anho;
    }
    public Fecha(Element e)throws ParsingException{
       Element eltodia=e.getFirstChildElement(EtqDia);
       Element eltomes=e.getFirstChildElement(EtqMes);
       Element eltoaño=e.getFirstChildElement(EtqAño);
       if(eltodia==null){
           throw new ParsingException("Falta el dia en el elemento fecha");
       }
       if(eltomes==null){
           throw new ParsingException("Falta el mes en el elemento fecha");
       }
       if(eltoaño==null){
           throw new ParsingException("Falta el año en el elemento fecha");
       }
       try{
           this.dia=Integer.parseInt(eltodia.getValue().trim());
           this.mes=Integer.parseInt(eltomes.getValue().trim());
           this.anho=Integer.parseInt(eltoaño.getValue().trim());
       }
       catch(NumberFormatException ex){
           throw new ParsingException("Valor incorrecto");
       }
    }

    public int getDia() {
        return dia;
    }

    public int getMes() {
        return mes;
    }

    public int getAnho() {
        return anho;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public void setAnho(int anho) {
        this.anho = anho;
    }
    
    @Override
    public String toString() {
        StringBuilder toret = new StringBuilder();
        
        toret.append (getDia());
        toret.append ("/");
        toret.append (getMes());
        toret.append ("/");
        toret.append (getAnho());
        
        return toret.toString();
    }
    
    
}
