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
public class Libro extends Referencia {
    public static final String EtqLibro="libro";
    public static final String EtqFormato="formato";
    public static final String EtqEditorial="editorial";
    public static final String EtqIsbn="isbn";
    
    public static enum tipoFormato { ELECTRONICO, PAPEL };
    private String editorial;
    private String isbn;
    private tipoFormato formato;

    public Libro(String editorial, String isbn, tipoFormato formato, String autores, String titulo, int ano) {
        super(autores, titulo, ano);
        this.editorial = editorial;
        this.isbn = isbn;
        this.formato = formato;
    }
    public Libro(Element e)throws ParsingException{
        super(e);
        Element eltoeditorial=e.getFirstChildElement(EtqEditorial);
        Element eltoisbn=e.getFirstChildElement(EtqIsbn);
        Element eltoformato=e.getFirstChildElement(EtqFormato);
        if(eltoeditorial==null){
            throw new ParsingException("Falta la editorial en el elemento libro");
        }
        if(eltoisbn==null){
            throw new ParsingException("Falta el isbn en el elemento libro");
        }
        if(eltoformato==null){
            throw new ParsingException("Falta el formato en el elemento libro");
        }
        this.editorial=eltoeditorial.getValue().trim();
        this.isbn=eltoisbn.getValue().trim();
        this.formato=tipoFormato.valueOf(eltoformato.getValue().trim());    
    }

    public String getEditorial() {
        return editorial;
    }

    public String getIsbn() {
        return isbn;
    }

    public tipoFormato getFormato() {
        return formato;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setFormato(tipoFormato formato) {
        this.formato = formato;
    }

    @Override
    public String toString() {
        StringBuilder toret = new StringBuilder ();
        
        toret.append("Referencia Libro.\n");
        toret.append(super.toString());
        toret.append("\tEditorial: ");
        toret.append(getEditorial());
        toret.append("\tISBN: ");
        toret.append(getIsbn());
        toret.append("\tFormato: ");
        toret.append(getFormato());
        
        return toret.toString();                
    }
    public Element toDOM(){
        Element raiz=super.toDOM();
        raiz.setLocalName(EtqLibro);
        
        Element eltoeditorial=new Element(EtqEditorial);
        Element eltoisbn=new Element(EtqIsbn);
        Element eltoformato=new Element(EtqFormato);
        
        eltoeditorial.appendChild(editorial);
        eltoisbn.appendChild(isbn);
        eltoformato.appendChild(formato.toString());
        raiz.appendChild(eltoeditorial);
        raiz.appendChild(eltoisbn);
        raiz.appendChild(eltoformato);
        return raiz;  
        
    }
    
    
    
    
    
}
