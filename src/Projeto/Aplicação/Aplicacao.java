package Projeto.Aplicação;

import java.util.Scanner;

import Projeto.modelo.Tabuleiro;
import Projeto.visao.TabuleiroConsole;

public class Aplicacao {
    public static void main (String[] args)
{
    Tabuleiro tabuleiro = new Tabuleiro(9, 9, 10);

    new TabuleiroConsole(tabuleiro);



    

}
}
