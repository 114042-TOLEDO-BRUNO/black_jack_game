package org.example;

public class Carta {
    private Palo palo;
    private Valor valor;



    public Carta(Palo palo, Valor valor) {
        this.palo = palo;
        this.valor = valor;
    }
    public Valor getValor(){
        return this.valor;
    }
    @Override
    public String toString() {
        return this.valor.toString()+" de "+this.palo.toString();
    }
}
