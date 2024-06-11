package Projeto.modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Tabuleiro {
    private int linhas;
    private int colunas;
    private int minas;

    private final List<Campo> campos = new ArrayList<>();
    
    public Tabuleiro (int linhas, int colunas, int minas)
    {
        this.linhas = linhas;
        this.colunas = colunas;
        this.minas = minas;

        gerarCampos();
        associarVizinhos();
        sortearMinas();
    }

    public void abrir(int linha, int coluna)
    {
       try {
        campos.parallelStream()
        .filter(c-> c.getLinha() == linha && c.getColuna() == coluna)
        .findFirst()
        .ifPresent(c -> c.abrir());
       } catch (Exception e) {
        campos.forEach(c -> c.setAberto(true));
        throw e;
       }
    }

    public void alternarMarcacao(int linha, int coluna)
    {
        campos.parallelStream()
        .filter(c-> c.getLinha() == linha && c.getColuna() == coluna)
        .findFirst()
        .ifPresent(c-> c.alternarMarcacao());
    }



    private void gerarCampos()
    {
        for (int linha = 0; linha< linhas; linha++)
        {
            for(int coluna = 0; coluna< colunas; coluna++)
            {
                campos.add (new Campo (linha, coluna));
            }
        }
    }
    private void associarVizinhos()
    {
        for(Campo c1:campos) // Percorre o campo linha 1 - coluna 1.
        {
            for(Campo c2 : campos) // Nesse for são feitos vários laços até terminar o primeiro for do c1.
                c1.adcionarVizinhos(c2); // Operação que adiciona o vizinho com base no campo que foi inserido. Ambos c1 e c2 percorrdio o os campos
            }
        }

    private void sortearMinas ()
    {
        long minasArmadas = 0;
        Predicate <Campo> minado = c -> c.isMinado();

        do {
            int aleatorio = (int) (Math.random() * campos.size()); // Nessa linha está gerando um valor para aleatório menor que o tamanho do campo. Começa com o índice 0 até o último.
            minasArmadas = campos.stream().filter(minado).count(); // Passa um stream filtrando (filter), minado e depois calcula quantas minas existem.
            campos.get(aleatorio).minar();
        } while (minasArmadas< minas);
    }

    public boolean objetivoAlcancado ()
    {
        return campos.stream().allMatch(c-> c.objetivoAlcancado());
    }

    public void reiniciar ()
    {
        campos.stream().forEach(c-> c.reiniciar());
        sortearMinas();
    }

    public String toString()
    {
        StringBuilder sb = new StringBuilder(); //Método para concatenação em massa
        sb.append("  ");
        for(int c = 0;c < colunas; c++){
            sb.append(" ");
            sb.append(c);
            sb.append(" ");
        }   
        sb.append("\n");


        int i = 0; // Contador da lista campos
        for (int l = 0; l< linhas; l++) // Laço que vai pecorrer o tabuleiro
        {   sb.append(l);
            sb.append(" ");
            for(int c = 0; c< colunas; c++)// Laço que vai pecorrer o tabuleiro
            {
                sb.append(" ");
                sb.append(campos.get(i));
                sb.append(" ");
                i++;
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
