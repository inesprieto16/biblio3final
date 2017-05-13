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
public class ArticuloRevista extends Referencia {
    public static final String EtqArticuloRevista="articulorevista";
    public static final String EtqTituloRevista="titulorevista";
    public static final String EtqDoi="doi";
    public static final String EtqVolumen="volumen";
    public static final String EtqNumero="numero";
    public static final String EtqPaginaInicio="paginainicio";
    public static final String EtqPaginaFin="paginafin";
    
    private String tituloRevista;
    private String doi;
    private int volumen;
    private int numero;
    private int paginaInicio;
    private int paginaFin;

    public ArticuloRevista( String tituloRevista, String doi, int volumen, 
                            int numero, int paginaInicio, int paginaFin, 
                            String autores, String titulo, int ano) {
        super(autores, titulo, ano);
        this.tituloRevista = tituloRevista;
        this.doi = doi;
        this.volumen = volumen;
        this.numero = numero;
        this.paginaInicio = paginaInicio;
        this.paginaFin = paginaFin;
    }
    public ArticuloRevista(Element e) throws ParsingException{
        super(e);
        Element eltotitulorevista=e.getFirstChildElement(EtqTituloRevista);
        Element eltodoi=e.getFirstChildElement(EtqDoi);
        Element eltovolumen=e.getFirstChildElement(EtqVolumen);
        Element eltonumero=e.getFirstChildElement(EtqNumero);
        Element eltopaginainicio=e.getFirstChildElement(EtqPaginaInicio);
        Element eltopaginafin=e.getFirstChildElement(EtqPaginaFin);
        
        if(eltotitulorevista==null){
            throw new ParsingException("Falta el titulo en el elemento articulo revista");
        }
        if(eltodoi==null){
            throw new ParsingException("Falta el doi en el elemento articulo revista");
        }
        if(eltovolumen==null){
            throw new ParsingException("Falta el volumen en el elemento articulo revista");
        }
        if(eltonumero==null){
            throw new ParsingException("Falta el numero en el elemento articulo revista");
        }
        if(eltopaginainicio==null){
            throw new ParsingException("Falta la pagina de inicio en el elemento articulo revista");
        }
        if(eltopaginafin==null){
            throw new ParsingException("Falta la pagina final en el elemento articulo revista");
        }
        this.tituloRevista=eltotitulorevista.getValue().trim();
        this.doi=eltodoi.getValue().trim();
        try{
            this.volumen=Integer.parseInt(eltovolumen.getValue().trim());
            this.numero=Integer.parseInt(eltonumero.getValue().trim());
            this.paginaFin=Integer.parseInt(eltopaginafin.getValue().trim());
            this.paginaInicio=Integer.parseInt(eltopaginainicio.getValue().trim());
        }
        catch(NumberFormatException ex){
            throw new ParsingException("Valor incorrecto");
        }
          
    }

    public String getTituloRevista() {
        return tituloRevista;
    }

    public String getDoi() {
        return doi;
    }

    public int getVolumen() {
        return volumen;
    }

    public int getNumero() {
        return numero;
    }

    public int getPaginaInicio() {
        return paginaInicio;
    }

    public int getPaginaFin() {
        return paginaFin;
    }

    public void setTituloRevista(String tituloRevista) {
        this.tituloRevista = tituloRevista;
    }

    public void setDoi(String doi) {
        this.doi = doi;
    }

    public void setVolumen(int volumen) {
        this.volumen = volumen;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public void setPaginaInicio(int paginaInicio) {
        this.paginaInicio = paginaInicio;
    }

    public void setPaginaFin(int paginaFin) {
        this.paginaFin = paginaFin;
    }

    @Override
    public String toString() {
        StringBuilder toret = new StringBuilder();
        
        toret.append("Referencia Articulo Revista.\n");
        toret.append(super.toString());
        toret.append("\tTitulo revista: ");
        toret.append(getTituloRevista());
        toret.append("\tVolumen: ");
        toret.append(getVolumen());
        toret.append("\tNumero: ");
        toret.append(getNumero());
        toret.append("\tPaginas: ");
        toret.append(getPaginaInicio() + " - " + getPaginaFin());
        toret.append("\tDOI: ");
        toret.append(getDoi());
        
        return toret.toString();
    }
    public Element toDOM(){
        Element raiz=super.toDOM();
        raiz.setLocalName(EtqArticuloRevista);
        Element eltotitulorevista=new Element(EtqTituloRevista);
        Element eltodoi=new Element(EtqDoi);
        Element eltovolumen=new Element(EtqVolumen);
        Element eltonumero=new Element(EtqNumero);
        Element eltopaginainicio=new Element(EtqPaginaInicio);
        Element eltopaginafin=new Element(EtqPaginaFin);
        
        eltotitulorevista.appendChild(tituloRevista);
        eltodoi.appendChild(doi);
        eltovolumen.appendChild(Integer.toString(volumen));
        eltonumero.appendChild(Integer.toString(numero));
        eltopaginainicio.appendChild(Integer.toString(paginaInicio));
        eltopaginafin.appendChild(Integer.toString(paginaFin));
        raiz.appendChild(eltotitulorevista);
        raiz.appendChild(eltodoi);
        raiz.appendChild(eltovolumen);
        raiz.appendChild(eltonumero);
        raiz.appendChild(eltopaginainicio);
        raiz.appendChild(eltopaginafin);
        return raiz;
        
        
        
    }
    
    
}
