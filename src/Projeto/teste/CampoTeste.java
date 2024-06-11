package Projeto.teste;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

import Projeto.excecao.Explosao;
import Projeto.modelo.Campo;


public class CampoTeste  {
    
    
    void iniciarCampo()
    {
        campo = new Campo(3, 3);
    }

        private Campo campo = new Campo(3, 3);


    @Test
    public void testeVizinho(){
        Campo vizinho = new Campo(3, 2);

        boolean resultado = campo.adcionarVizinhos(vizinho);
        assertTrue(resultado);;
    }

    @Test
    public void testeVizinhoDiagonal(){
        Campo vizinho = new Campo(2, 2);

        boolean resultado = campo.adcionarVizinhos(vizinho);
        assertTrue(resultado);
    }

    @Test
    public void testeNaoVizinho(){
        Campo vizinho = new Campo(1, 1);

        boolean resultado = campo.adcionarVizinhos(vizinho);
        assertFalse(resultado);
    }

    @Test

    public void testeAlternarMarcacao()
    {
        campo.alternarMarcacao();
        assertTrue(campo.isMarcado());
    }

    @Test
    public void testeAlternarDuasMarcacao()
    {
        campo.alternarMarcacao();
        campo.alternarMarcacao();
        assertFalse(campo.isMarcado());
    }

    @Test

    public void AbrirNaoMarcadoMinado()
    {
        campo.minar();
        assertThrows(Explosao.class, () -> {campo.abrir();
        });
    }

    @Test
    public void AbrirNaoMinadoMarcado()
    {   
        campo.alternarMarcacao();
        assertFalse(campo.abrir());
    }

    @Test
    public void AbrirNaoMinadoNaoMarcado()
    {
        assertTrue(campo.abrir());
    }

    @Test
    public void AbrirMinadoMarcado()
    {
        campo.minar();
        campo.alternarMarcacao();
        assertFalse(campo.abrir());
    }


    @Test
    public void testeAbrirComVizinhos1()
    {
        Campo campo11 = new Campo(1, 1);
        Campo campo22 = new Campo(2, 2);
        campo22.adcionarVizinhos(campo11);

        campo.adcionarVizinhos(campo22);
        campo.abrir();

        assertTrue(campo11.isAberto() && campo22.isAberto());
    }

    @Test
    public void testeAbrirComVizinhos2()
    {
        Campo campo11 = new Campo(1, 1);
        Campo campo12 = new Campo(2, 2);
        campo12.minar();

        Campo campo22 = new Campo(2, 2);
        campo22.adcionarVizinhos(campo11);
        campo22.adcionarVizinhos(campo12);

        campo.adcionarVizinhos(campo22);
        campo.abrir();

        assertFalse(campo11.isAberto() && campo22.isFechado());
    }

    }
