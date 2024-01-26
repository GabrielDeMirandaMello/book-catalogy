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
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
@ContextConfiguration
class DefaultDeletarLivroTest {
    @Mock
    private LivroRepository livroRepository;
    @Autowired
    @InjectMocks
    private DefaultDeletarLivro defaultDeletarLivro;
    private final Livro livro = new Livro();

    @BeforeEach
    void init() {
        MockitoAnnotations.initMocks(this);
        livro.setId(1L);
        livro.setTitulo("Senhor dos Aneis");
        livro.setAutor("Gabriel M.");
    }

    @Test
    @DisplayName(value = "Deletar livro por id e validar o retorno do código Http")
    void execute() {
        when(this.livroRepository.findById(1L)).thenReturn(Optional.of(livro));
        ResponseEntity<?> resposta = this.defaultDeletarLivro.execute(1L);

        verify(this.livroRepository, times(1)).deleteById(any());
        Assertions.assertEquals(HttpStatusCode.valueOf(200), resposta.getStatusCode());
        Assertions.assertEquals("Livro deletado com sucesso !", resposta.getBody());
    }

    @Test
    @DisplayName(value = "Não deletar livro por id e validar o retorno do código Http")
    void executeFail() {
        when(this.livroRepository.findById(1L)).thenReturn(Optional.empty());
        ResponseEntity<?> resposta = this.defaultDeletarLivro.execute(1L);

        verify(this.livroRepository, times(0)).deleteById(any());
        Assertions.assertEquals(HttpStatusCode.valueOf(404), resposta.getStatusCode());
        Assertions.assertEquals("Livro com id:1 não existe !", resposta.getBody());
    }
}