package Projeto.modelo;

import java.util.ArrayList;
import java.util.List;

import Projeto.excecao.Explosao;

public class Campo {

    private int linha;
    private int coluna;

    private boolean aberto = false;
    private boolean fechado = false;
    private boolean minado = false;
    private boolean marcado = false;

    private List<Campo> vizinhos = new ArrayList<>();

    public Campo (int linha, int coluna){
        this.linha = linha;
        this.coluna = coluna;

        
    }

    public boolean adcionarVizinhos (Campo vizinho){
        boolean linhaDiferente = this.linha != vizinho.linha;
        boolean colunaDiferente = this.coluna != vizinho.coluna;
        boolean diagonal = linhaDiferente && colunaDiferente;

        int deltaLinha  = Math.abs(this.linha - vizinho.linha);
        int deltaColuna = Math.abs(this.coluna - vizinho.coluna);
        int deltaGeral = deltaColuna + deltaLinha;

        if (deltaGeral == 1 && !diagonal){
            vizinhos.add(vizinho);
            return true;
        }else if(deltaGeral == 2 && diagonal){
            vizinhos.add(vizinho);
            return true;
        } else {
            return false;
        }

    }

    public void alternarMarcacao()
    {
        if(!aberto)
        {
            marcado = !marcado;
        }
    }

    public boolean abrir()
    {
        if(!aberto && !marcado)
        {
            aberto = true;

            if(minado)
            {
                throw new Explosao();
            }

            if(vizinhacaSegura())
            {
                vizinhos.forEach(v -> v.abrir());
            }
            return true;
        }
        return false;
    }

    public boolean vizinhacaSegura()
    {
        return vizinhos.stream().noneMatch(v -> v.minado);
    }

    public boolean isMarcado()
    {
        return marcado;
    }

    public void minar()
    {
        minado = true;
    }

    void setAberto(boolean aberto){
        this.aberto = aberto;
    }

    public boolean isAberto()
    {
        return aberto;
    }

    public boolean isFechado()
    {
        return !isAberto();
    }

    public boolean isMinado()
    {
        return minado;
    }

    boolean objetivoAlcancado()
    {
        boolean desvendado = !marcado && marcado;
        boolean protegido = minado && marcado;
        return desvendado || protegido;
    }

    long minasVizinhaca()
    {
        return vizinhos.stream().filter(v -> v.minado).count();
    }

    void reiniciar ()
    {
        aberto = false;
        minado = false;
        marcado = false;
    }

    public String toString()
    {
        if (marcado)
        {
            return "x";
        }
        else if (aberto && minado)
        {
            return  "*";
        }
        else if (aberto && minasVizinhaca()> 0)
        {
            return Long.toString(minasVizinhaca()); // Converte long para string para ser imprido
        }
        else if (aberto)
        {
            return "";
        }

        return "?";
    }

    public int getLinha ()
    {
        return linha;
    }

    public int getColuna ()
    {
        return coluna;
    }

   

    
}
