package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Mazo {
    private List<Carta> cartas;

    public Mazo() {
        this.cartas = new ArrayList<Carta>();
    }
    /**Este metodo crea el mazo desde un foreach pasando primero
     * cada carta en la lista de cartas del mazo(Palo)
     * agrega la carta con los enumerables de la clase Palo

     */
    public void crearMazoCompleto(){
        //genera el mazo
        for(Palo paloCarta : Palo.values()){
            for(Valor valorCarta : Valor.values()){
                //agregar cartas al mazo
                this.cartas.add(new Carta(paloCarta, valorCarta));
            }
        }
    }
    /**Este metodo mezcla el mazo

     */
    public void mezclarMazo(){
        Collections.shuffle(cartas);
    }
    /**Este metodo muestra la cadena del mazo
     * osea, una impresion de todas las cartas
     * del mazo

     */
    @Override
    public String toString(){
        String listaDeCartas="";
        for(Carta aCarta :this.cartas){
            listaDeCartas+="\n"+ aCarta.toString();
        }
        return listaDeCartas;
    }
    public void borrarCarta(int indice){
        this.cartas.remove(indice);
    }
    public Carta getCarta(int indice){
        return this.cartas.get(indice);
    }
    public void agregarCarta(Carta addCarta){
        this.cartas.add(addCarta);
    }
    /**Este metodo extrae del mazo una carta.
     * Crea un orden para mover una carta de un
     * mazo a otro
     * @param origen
     */

    public void mostrar(Mazo origen){//cual es su origen
        this.cartas.add(origen.getCarta(0));
        origen.borrarCarta(0);
    }
    public int cantidadEnMazo(){
        return this.cartas.size();
    }
    /**Este metodo retorna el valor
     * total de la mano que le repartio
     * el crupier
     */
    public int valorDeCartas(){
        int totalValor=0;
        int ases=0;
        for(Carta aCarta :this.cartas){
            switch(aCarta.getValor()){
                case DOS: totalValor+=2;break;
                case TRES: totalValor+=3;break;
                case CUATRO: totalValor+=4;break;
                case CINCO: totalValor+=5;break;
                case SEIS: totalValor+=6;break;
                case SIETE: totalValor+=7;break;
                case OCHO: totalValor+=8;break;
                case NUEVE: totalValor+=9;break;
                case DIEZ: totalValor+=10;break;
                case J: totalValor+=10;break;
                case Q: totalValor+=10;break;
                case K: totalValor+=10;break;
                case AS: ases +=1;break;
            }
        }
        for(int i=0;i<ases;i++){//verifica que el valor total de las cartas repartidas
            if(totalValor>10){//si es mayor a 10 puntos
                totalValor+=1;//el valor del as es de 1
            }else{
                totalValor+=11;//sino se toma como 11
            }
        }
        return totalValor;
    }

    /**Este metodo va a devolver todas las cartas al mazo para reiniciar otra partida
     * @param moverA
     */
    public void moverTodoAlMazo(Mazo moverA){
        int elTamañoDelMazo=this.cartas.size();
        //colocar las cartas para moverlas al mazo
        for(int i=0;i<elTamañoDelMazo;i++){
            moverA.agregarCarta(this.getCarta(i));
        }
        for(int i=0;i<elTamañoDelMazo;i++){
            this.borrarCarta(0);
        }
    }
}
