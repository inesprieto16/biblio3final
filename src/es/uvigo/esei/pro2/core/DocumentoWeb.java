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
public class DocumentoWeb extends Referencia{
    public static final String EtqDocumentoWeb="documentoweb";
    public static final String EtqUrl="url";
    public static final String EtqFechaConsulta="fechaconsulta";
    private String url;
    private Fecha fechaConsulta;

    public DocumentoWeb(String url, Fecha fechaConsulta, 
                        String autores, String titulo, int ano) {
        super(autores, titulo, ano);
        this.url = url;
        this.fechaConsulta = fechaConsulta;
    }
    public DocumentoWeb(Element e)throws ParsingException{
        super(e);
        Element eltourl=e.getFirstChildElement(EtqUrl);
        Element eltofechaconsulta=e.getFirstChildElement(EtqFechaConsulta);
        
        if(eltourl==null){
            throw new ParsingException("Faltan el url en el elemento documento web");
        }
        if(eltofechaconsulta==null){
            throw new ParsingException("Falta la fecha de consulta en el elemento documento web");
        }
        this.url=eltourl.getValue().trim();
        this.fechaConsulta=new Fecha(eltofechaconsulta);
        
    }

    public String getUrl() {
        return url;
    }

    public Fecha getFechaConsulta() {
        return fechaConsulta;
    }
    
    public void setUrl(String url) {
        this.url = url;
    }

    public void setFechaConsulta(Fecha fechaConsulta) {
        this.fechaConsulta = fechaConsulta;
    }

    @Override
    public String toString() {
        StringBuilder toret = new StringBuilder ();
        
        toret.append("Referencia Documento Web.\n");
        toret.append(super.toString());
        toret.append("\tUrl: ");
        toret.append(getUrl());
        toret.append("\tFecha Consulta: ");
        toret.append(getFechaConsulta().toString());
        
        return toret.toString();
    }
    
    public Element toDOM(){
        Element raiz=super.toDOM();
        raiz.setLocalName(EtqDocumentoWeb);
        Element eltourl=new Element(EtqUrl);
        Element eltofechaconsulta=new Element(EtqFechaConsulta);
       
        
        eltourl.appendChild(url);
        eltofechaconsulta.appendChild(fechaConsulta.toString());
        raiz.appendChild(eltourl);
        raiz.appendChild(eltofechaconsulta);
        
        return raiz;
        
        
        
    }
    
}
