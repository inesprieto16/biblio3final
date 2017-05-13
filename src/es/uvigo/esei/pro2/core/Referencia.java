// Definicion de la clase Referencia

package es.uvigo.esei.pro2.core;
import nu.xom.*;
/**
 *
 * @author nrufino
 */
abstract public class Referencia {
    public static final String EtqReferencia="referencia";
    public static final String EtqAutores="autores";
    public static final String EtqTitulo="titulo";
    public static final String EtqAño="año";
    private String autores; 
    private String titulo;  
    private int ano;

    /** Crea una nueva referencia, con sus autores, título y año
     * @param autores los nombres de los autores de la referencia
     * @param titulo el título de la referencia
     * @param ano el ano de la referencia
     */
    public Referencia(String autores, String titulo, int ano)
    {
        this.setAutores( autores );
        this.setTitulo( titulo );
        this.setAno(ano);
    }
    public Referencia(Element e) throws ParsingException{
        Element eltoautores=e.getFirstChildElement(EtqAutores);
        Element eltotitulo=e.getFirstChildElement(EtqTitulo);
        Element eltoaño=e.getFirstChildElement(EtqAño);
        if(eltoautores==null){
            throw new ParsingException("Faltan los autores en el elemento referencia");
        }
        if(eltotitulo==null){
            throw new ParsingException("Falta el titulo en el elemento referencia");
        }
        if(eltoaño==null){
            throw new ParsingException("Falta el año en el elemento referencia");
        }
        this.autores=eltoautores.getValue().trim();
        this.titulo=eltotitulo.getValue().trim();
        try{
            this.ano=Integer.parseInt(eltoaño.getValue().trim());
        }
        catch(NumberFormatException ex){
            throw new ParsingException("Valor incorrecto para el año");
        }
        
    }

    /** Devuelve el titulo de la referencia
     * @return el titulo de la referencia, como String.
     */
    public String getTitulo()
    {
        return titulo;
    }

    /** Cambia el titulo de la referencia
     * @param t el titulo de la referencia
     */
    public void setTitulo(String t)
    {
        titulo = t.trim();
    }

    /** Devuelve los autores de la referencia
     *  @return El valor como cadena
     **/
    public String getAutores() {
        return autores;
    }

    /** Cambia los autores
     * @param autores El nuevo valor, como cadena
     */
    public void setAutores(String autores) {
        this.autores = autores.trim();
    }

    /** Devuelve el año de la referencia
     *  @return El valor como entero
     **/
    public int getAno() {
        return ano;
    }

    /** Cambia el año
     * @param ano El nuevo valor, como entero
     */
    public void setAno(int ano) {
        this.ano = ano;
    }
  
    public String toString()
    {
        StringBuilder toret = new StringBuilder();
        
        toret.append(getAutores() + " ; " );
        toret.append(getTitulo() + " ; " );
        toret.append(getAno() + " ; " ); 

        return toret.toString();
    }
    public Element toDOM(){
        Element raiz=new Element(EtqReferencia);
        Element eltoautores=new Element(EtqAutores);
        Element eltotitulo=new Element(EtqTitulo);
        Element eltoaño=new Element(EtqAño);
        
        eltoautores.appendChild(autores);
        eltotitulo.appendChild(titulo);
        eltoaño.appendChild(Integer.toString(ano));
        raiz.appendChild(eltoautores);
        raiz.appendChild(eltotitulo);
        raiz.appendChild(eltoaño);
        return raiz;
        
        
        
    }
}

