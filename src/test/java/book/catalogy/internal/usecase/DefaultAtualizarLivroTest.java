package book.catalogy.internal.usecase;

import book.catalogy.internal.entity.Livro;
import book.catalogy.internal.repository.LivroRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;

import java.util.Optional;

import static org.mockito.Mockito.*;

@SpringBootTest
@ContextConfiguration
class DefaultAtualizarLivroTest {
    @Mock
    private LivroRepository livroRepository;
    @Autowired
    @InjectMocks
    private DefaultAtualizarLivro defaultAtualizarLivro;
    private final Livro livro = new Livro();

    @BeforeEach
    void init() {
        MockitoAnnotations.initMocks(this);
        livro.setId(1L);
        livro.setTitulo("Senhor dos Aneis");
        livro.setAutor("Gabriel M.");
    }
    @Test
    @DisplayName(value = "Validar se o livro é atualizado com sucesso")
    void atualizarLivroComSucesso() {
        Livro livroAtualizado = new Livro(
                livro.getId(),
                livro.getTitulo(),
                "Gabriel M",
                "Gabriel SA",
                null
                );
        when(this.livroRepository.findById(1L)).thenReturn(Optional.of(livro));
        when(this.livroRepository.save(livroAtualizado)).thenReturn(livroAtualizado);

        ResponseEntity<Livro> resposta = this.defaultAtualizarLivro.execute(1L, livroAtualizado);

        verify(livroRepository, times(1)).findById(any());
        verify(livroRepository, times(1)).save(any());

        Assertions.assertEquals(resposta, ResponseEntity.status(HttpStatus.OK).body(livroAtualizado));
    }

    @Test
    @DisplayName(value = "Validar se o livro não é atualizado e o codigo Http retornado")
    void falhaAoAtualizarLivro() {
        Livro livroAtualizado = new Livro(
                livro.getId(),
                livro.getTitulo(),
                "Gabriel M",
                "Gabriel SA",
                null
        );
        when(this.livroRepository.findById(2L)).thenReturn(Optional.empty());
        when(this.livroRepository.save(livroAtualizado)).thenReturn(livroAtualizado);

        ResponseEntity<Livro> resposta = this.defaultAtualizarLivro.execute(2L, livroAtualizado);

        verify(livroRepository, times(1)).findById(any());
        verify(livroRepository, times(0)).save(any());

        Assertions.assertEquals(resposta, ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
}