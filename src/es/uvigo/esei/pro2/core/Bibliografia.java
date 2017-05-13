/*  Definición de la clase Bibliografia
 *  Una bibliografia esta compuesta de referencias bibliograficas
*/

package es.uvigo.esei.pro2.core;

import java.util.ArrayList;
import java.util.*;
import java.lang.*;
import java.io.*;
import nu.xom.*;
/**
 *
 * @author nrufino
 */
public class Bibliografia {
    public static final String EtqBibliografia="bibliografia";
    public static final String EtqLibro="libro";
    public static final String EtqDocumentoWeb="documentoweb";
    public static final String EtqArticuloRevista="articulorevista";
    public static class BibliografiaException extends Exception {
        
        public BibliografiaException(String msg){
            super(msg);
        }
    }
    
    
    
    public static class PosicionInexistenteBibliografiaException extends BibliografiaException{

        public PosicionInexistenteBibliografiaException(String msg) {
            super(msg);
        }
        
    }
    
    private ArrayList<Referencia> referencias;
    

    /** Nueva Coleccion con un num. max. de referencias.
     * @param maxReferencias el num. max. de referencias, como entero.
     */
    public Bibliografia()
    {
        
        referencias = new ArrayList<>();
    }
    public Bibliografia(String nf)throws ParsingException,IOException{
        this();
        Builder parser=new Builder();
        Document doc=parser.build(new File(nf));
        Elements tipos=doc.getRootElement().getChildElements();
        
        for(int i=0;i<tipos.size();i++){
            
            
             if(tipos.get(i).getLocalName()==EtqLibro){
                 this.inserta(new Libro(tipos.get(i)));
             }
            
            if(tipos.get(i).getLocalName()==EtqDocumentoWeb){
                this.inserta(new DocumentoWeb(tipos.get(i)));
            }
            if(tipos.get(i).getLocalName()==EtqArticuloRevista){
                this.inserta(new ArticuloRevista(tipos.get(i)));
            }
            
        }
        
        
    }
public int getNumReferencias(){
    return referencias.size();
}
    /**
     * Devuelve la referencia en pos
     * @param pos el lugar de la referencia en el vector de referencias
     * @return el objeto Referencia correspondiente.
     */
    public Referencia get(int pos) throws PosicionInexistenteBibliografiaException
    {
        if ( pos >= getNumReferencias() ) {
            throw new PosicionInexistenteBibliografiaException ("get(): sobrepasa la pos: " 
                        + ( pos + 1 )  );       
        }

        return referencias.get(pos);
    }


    /** Inserta una nueva referencia
     * @param r el nuevo objeto Referencia
     */
    public void inserta(Referencia r) 
    {

        referencias.add(r);
        
    }

    /** Elimina una referencia
     * @param pos el lugar de la referencia en el vector de referencias
     */
    public void elimina(int pos)throws PosicionInexistenteBibliografiaException
    {
        if ( pos >= getNumReferencias() ){
            throw new PosicionInexistenteBibliografiaException("elimina(): sobrepasa el número de referencias : "
                                + getNumReferencias());           
        }
        referencias.remove(pos);
        
    }
    
    /** Devuelve el contenido de todas las Referenciass
     * @return el String con el contenido
     */
    public String toString(){
        StringBuilder toret;
        final int numReferencias = getNumReferencias();

        toret = new StringBuilder();
        if ( numReferencias > 0 ) {
            for (int i = 0; i < numReferencias; i++) {
                toret.append (( i + 1 ) + ". " );
                toret.append(referencias.get(i).toString() + "\n");
            }
        } else {
            toret.append("No hay referencias." );
        }
        
        return toret.toString();
    }            
    
    public String listarPorTipo(char t) throws PosicionInexistenteBibliografiaException
    {
        final int numReferencias = getNumReferencias();
        StringBuilder toret = new StringBuilder();
        Referencia r;
        
        if ( numReferencias > 0 ) {
            for (int i = 0; i < numReferencias; i++) {
                r = get(i);
                switch (t){
                    case 'L' :  if(r instanceof Libro){
                                    toret.append(r.toString() + "\n");
                                }
                                break;
                    case 'A' :  if(r instanceof ArticuloRevista){
                                    toret.append(r.toString() + "\n");
                                }
                                break;
                    case 'D' :  if(r instanceof DocumentoWeb){
                                    toret.append(r.toString() + "\n");
                                }
                            break;
                }
            }
        }
        else {    
            toret.append("No hay referencias." );
        }
        
        if (toret.length() == 0){
            toret.append("No hay referencias de ese tipo." );
        }
        
        return toret.toString();
    }
    public Element toDOM(){
        Element toret=new Element(EtqBibliografia);
        for(int i=0;i<getNumReferencias();i++){
            toret.appendChild(referencias.get(i).toDOM());
        }
        return toret;
    }
    public void toXML(String nf) throws IOException{
        FileOutputStream f=new FileOutputStream(nf);
        Serializer serial=new Serializer(f);
        Document doc=new Document(this.toDOM());
        serial.write(doc);
    }
}
