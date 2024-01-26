package book.catalogy.internal.usecase;

import book.catalogy.internal.entity.Livro;
import book.catalogy.internal.repository.LivroRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@ContextConfiguration
class DefaultBuscarLivroPorIdTest {
    @Mock
    private LivroRepository livroRepository;
    @Autowired
    @InjectMocks
    private DefaultBuscarLivroPorId defaultBuscarLivroPorId;
    private final Livro livro = new Livro();

    @BeforeEach
    void init(){
        MockitoAnnotations.initMocks(this);
        livro.setId(1L);
        livro.setTitulo("Senhor dos Aneis");
        livro.setAutor("Gabriel M.");
    }

    @Test
    @DisplayName(value = "Validando se está retornando o livro e o codigo corretamente")
    void buscarLivroComSucesso() {
        when(this.livroRepository.findById(1L)).thenReturn(Optional.of(this.livro));
        ResponseEntity<Livro> resposta = defaultBuscarLivroPorId.execute(this.livro.getId());

        verify(livroRepository, times(1)).findById(any());
        Assertions.assertEquals(ResponseEntity.status(HttpStatus.OK).body(this.livro), resposta);
    }

    @Test
    @DisplayName(value = "Validando se está retornando o codigo de erro correto")
    void falhaAoBuscarLivroPorId() {
        when(this.livroRepository.findById(1L)).thenReturn(Optional.empty());
        ResponseEntity<Livro> resposta = defaultBuscarLivroPorId.execute(this.livro.getId());

        verify(livroRepository, times(1)).findById(any());
        Assertions.assertEquals(ResponseEntity.status(HttpStatus.NOT_FOUND).build(), resposta);
    }
}