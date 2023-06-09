package org.example;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        //bienvenida
        System.out.println("Bienvenido al Black Jack");
        //creacion del mazo de cartas
        Mazo mazoJuego =new Mazo();
        mazoJuego.crearMazoCompleto();
        mazoJuego.mezclarMazo();
        //crear el mazo para el jugador
        Mazo mazoJugador =new Mazo();

        //crear mazo para el dealer

        Mazo mazoCrupier =new Mazo();

        double dineroEnPropiedad=100.00;

        Scanner entradaPorTeclado=new Scanner(System.in);

        //Juego loop
        while (dineroEnPropiedad>0){
            //sigue el juego
            //toma las apuestas del juego
            System.out.println("Tienes $"+dineroEnPropiedad+", cuanto quieres apostar???");
            double apuesta=entradaPorTeclado.nextDouble();
            if(apuesta>dineroEnPropiedad){
                System.out.println("No puedes apostar mas de lo que tienes, retirese señor!");
                break;
            }
            boolean finPartida=false;
            //comienza el dealer y le da 2 cartas al jugador
            mazoJugador.mostrar(mazoJuego);
            mazoJugador.mostrar(mazoJuego);
            //el dealer toma 2 cartas
            mazoCrupier.mostrar(mazoJuego);
            mazoCrupier.mostrar(mazoJuego);
            while(true){
                System.out.println("Tu mano: ");
                System.out.println(mazoJugador);
                System.out.println("Tu mano vale "+ mazoJugador.valorDeCartas());//puntaje de la mano del jugador
                System.out.println("La mano del crupier vale "+ mazoCrupier.getCarta(0).toString()+", la otra carta no se muestra");
                //le preguntamos si se planta o sigue pidiendo cartas
                System.out.println("Pides una carta mas(1)? o te plantas(2)?");
                int respuesta=entradaPorTeclado.nextInt();
                if(respuesta==1){
                    mazoJugador.mostrar(mazoJuego);//el dealer saca una carta para el jugador
                    System.out.println("Tu mano es: "+ mazoJugador.getCarta(mazoJugador.cantidadEnMazo()-1).toString());
                    if(mazoJugador.valorDeCartas()>21){
                        System.out.println("Perdiste. Tu mano actual es de: "+ mazoJugador.valorDeCartas());
                        dineroEnPropiedad-=apuesta;
                        finPartida=true;
                    }
                }
                if(respuesta==2){
                    break;
                }

            }
            //revelar la carta oculta del dealer
            System.out.println("La carta del dealer es: "+ mazoCrupier);
            //ver si el crupier tiene mas puntos que el jugador
            if(mazoCrupier.valorDeCartas()> mazoJugador.valorDeCartas()&& !finPartida){
                System.out.println("El dealer gana!");
                dineroEnPropiedad-=apuesta;
                finPartida=true;
            }
            //averiguar si el dealer va a sacar mas cartas.
            while ((mazoCrupier.valorDeCartas()<17)&& !finPartida){
                mazoCrupier.mostrar(mazoJuego);
                System.out.println("El dealer saco: "+ mazoCrupier.getCarta(mazoCrupier.cantidadEnMazo()-1).toString());
            }
            //Mostrar el total de puntos del dealer
            System.out.println("La mano del dealer tiene un puntaje de: "+ mazoCrupier.valorDeCartas());
            //determinar si el dealer perdio
            if((mazoCrupier.valorDeCartas()>21)&& !finPartida){
                System.out.println("El dealer perdio!! Has ganado la mano!!");
                dineroEnPropiedad+=apuesta;
                finPartida=true;

            }
            //determinar si hay empate(push)
            if(mazoJugador.valorDeCartas()== mazoCrupier.valorDeCartas()&& !finPartida){
                System.out.println("Empate!!!");
                finPartida=true;

            }
            if((mazoJugador.valorDeCartas()> mazoCrupier.valorDeCartas())&& !finPartida){
                System.out.println("Tu mano es la ganadora!");
                dineroEnPropiedad+=apuesta;

            }else if(!finPartida){
                System.out.println("Perdiste la mano!");
                dineroEnPropiedad-=apuesta;

            }
            mazoJugador.moverTodoAlMazo(mazoJuego);
            mazoCrupier.moverTodoAlMazo(mazoJuego);
            System.out.println("Termino la mano");
        }

        System.out.println("Perdiste, te quedaste sin dinero");

    }
}