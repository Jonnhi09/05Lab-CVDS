/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.guess;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author jonnhi
 */
@SessionScoped
//@ApplicationScoped
@ManagedBean(name = "guessBean")
public class Guess {

    private String message;
    private boolean isPlaying;
    private int num, tries, prize;

    public Guess() {
        restart();
    }

    public void guess(int x) throws Exception {
        if (isPlaying) {
            this.tries++;
            try {
                if (x == this.num) {
                    this.message = "Ganó";
                    this.isPlaying = false;
                } else {
                    int discount = prize - 5 * tries;
                    if (discount >= 0) {
                        this.prize -= 5 * tries;
                    } else {
                        this.message = "Perdió";
                        this.isPlaying = false;
                    }
                }
            } catch (Exception e) {
                throw new Exception("Se deben ingresar números, y se ingresó: " + x, e);
            }
        }
    }

    public void restart() {
        this.tries = 0;
        this.num = (new java.util.Random()).nextInt(100) + 1;
        this.prize = 100;
        this.isPlaying = true;
        this.message = "En juego";
    }

    public int getNum() {
        return num;
    }

    public int getTries() {
        return tries;
    }

    public int getPrize() {
        return prize;
    }

    public String getMessage() {
        return message;
    }

}
