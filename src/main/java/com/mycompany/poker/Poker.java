/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.poker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

public class Poker {

    class Card {
        private String palo;
        private String color;
        private String valor;

        public Card(String palo, String color, String valor) {
            this.palo = palo;
            this.color = color;
            this.valor = valor;
        }

        public String getPalo() {
            return palo;
        }

        public String getColor() {
            return color;
        }

        public String getValor() {
            return valor;
        }

        @Override
        public String toString() {
            return palo + "," + color + "," + valor;
        }
    }

    class Deck {
        private ArrayList<Card> deck;

        public Deck() {
            deck = new ArrayList<>();
            String[] palos = {"Treboles", "Corazones", "Picas", "Diamantes"};
            String[] colores = {"Negro", "Rojo"};
            String[] valores = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "A", "J", "Q", "K"};

            // Inicializar el deck con las 52 cartas
            for (String palo : palos) {
                String color = (palo.equals("Treboles") || palo.equals("Picas")) ? colores[0] : colores[1];
                for (String valor : valores) {
                    deck.add(new Card(palo, color, valor));
                }
            }
        }

        public void shuffle() {
            Collections.shuffle(deck);
            System.out.println("Se bajareo el maso de cartas.");
        }

        public Card dealCard() {
            if (!deck.isEmpty()) {
                return deck.remove(0);
            } else {
                System.out.println("No quedan cartas en el maso.");
                return null;
            }
        }

        public int getRemainingCards() {
            return deck.size();
        }
    }

    public static void main(String[] args) {
        Poker poker = new Poker();
        Deck deck = poker.new Deck();
        Scanner scanner = new Scanner(System.in);

        // Mezclar el deck
        deck.shuffle();

        // Pedir número de jugadores
        System.out.print("Ingresa el total de jugadores: ");
        int numJugadores = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer de entrada

        // Pedir nombres de los jugadores
        String[] jugadores = new String[numJugadores];
        for (int i = 0; i < numJugadores; i++) {
            System.out.print("Ingresa el nombre del jugador " + (i + 1) + ": ");
            jugadores[i] = scanner.nextLine();
        }

        // Comprobar si hay suficientes cartas para todos los jugadores
        if (deck.getRemainingCards() < numJugadores * 5) {
            System.out.println("No hay suficientes cartas en el deck para repartir manos a todos los jugadores.");
            return;
        }

        // Repartir 5 cartas a cada jugador
        for (int i = 0; i < numJugadores; i++) {
            System.out.println("\nCartas de " + jugadores[i] + ":");
            for (int j = 0; j < 5; j++) {
                Card card = deck.dealCard();
                System.out.println(card.toString());
            }
        }

        // Mostrar cuántas cartas quedan en el deck
        System.out.println("\nQuedan " + deck.getRemainingCards() + " cartas en el deck.");
    }
}

