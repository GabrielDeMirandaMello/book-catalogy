package book.catalogy.internal.usecase;

import book.catalogy.internal.entity.Livro;
import book.catalogy.internal.repository.LivroRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@ContextConfiguration
class DefaultBuscarLivroPorTermoTest {
    @Mock
    private LivroRepository livroRepository;
    @Autowired
    @InjectMocks
    private DefaultBuscarLivroPorTermo defaultBuscarLivroPorTermo;
    private final Livro livro = new Livro();
    private final List<Livro> listaExibicao = new ArrayList<>();

    @BeforeEach
    void init() {
        MockitoAnnotations.initMocks(this);
        livro.setId(1L);
        livro.setTitulo("Senhor dos Aneis");
        livro.setAutor("Gabriel M.");
        listaExibicao.add(livro);
    }
    @Test
    @DisplayName(value = "Buscando termo 'Senhor' e validando a resposta e se o status Http é 200")
    void buscarLivroPorTermoComSucesso() {
        when(this.livroRepository.findAllByTituloContainsIgnoreCase("Senhor"))
                .thenReturn(Optional.of(listaExibicao));
        when(this.livroRepository.findAllByAutorContainsIgnoreCase("Senhor"))
                .thenReturn(Optional.empty());
        when(this.livroRepository.findAllByEditoraContainsIgnoreCase("Senhor"))
                .thenReturn(Optional.empty());

        ResponseEntity<List<Livro>> resposta = this.defaultBuscarLivroPorTermo.execute("Senhor");
        verify(livroRepository, times(1)).findAllByTituloContainsIgnoreCase("Senhor");
        verify(livroRepository, times(1)).findAllByAutorContainsIgnoreCase("Senhor");
        verify(livroRepository, times(1)).findAllByEditoraContainsIgnoreCase("Senhor");

        assertEquals(HttpStatusCode.valueOf(200), resposta.getStatusCode());
        assertEquals(listaExibicao, resposta.getBody());
    }

    @Test
    @DisplayName(value = "Buscando termo 'Senhor' e validando se o status Http é 404")
    void naoAcharNenhumLivroPeloTermo() {
        when(this.livroRepository.findAllByTituloContainsIgnoreCase("Senhor"))
                .thenReturn(Optional.empty());
        when(this.livroRepository.findAllByAutorContainsIgnoreCase("Senhor"))
                .thenReturn(Optional.empty());
        when(this.livroRepository.findAllByEditoraContainsIgnoreCase("Senhor"))
                .thenReturn(Optional.empty());

        ResponseEntity<List<Livro>> resposta = this.defaultBuscarLivroPorTermo.execute("Senhor");

        verify(livroRepository, times(1)).findAllByTituloContainsIgnoreCase("Senhor");
        verify(livroRepository, times(1)).findAllByAutorContainsIgnoreCase("Senhor");
        verify(livroRepository, times(1)).findAllByEditoraContainsIgnoreCase("Senhor");

        assertEquals(HttpStatusCode.valueOf(404), resposta.getStatusCode());
    }
}