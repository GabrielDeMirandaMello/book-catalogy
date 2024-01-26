package book.catalogy.internal.usecase;

import book.catalogy.internal.entity.Livro;
import book.catalogy.internal.repository.LivroRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.ValidationException;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;

import java.util.Optional;

import static org.mockito.Mockito.*;

@SpringBootTest
@ContextConfiguration
class DefaultAdicionarLivroTest {
    @Mock
    private LivroRepository livroRepository;
    @Autowired
    @InjectMocks
    private DefaultAdicionarLivro defaultAdicionarLivro;
    private final Livro livro = new Livro();

    @BeforeEach
    void init() {
        MockitoAnnotations.initMocks(this);
        livro.setId(1L);
        livro.setTitulo("Senhor dos Aneis");
        livro.setAutor("Gabriel M.");
    }
    @Test
    @DisplayName(value = "Adicionando o livro com sucesso e validando o codigo http")
    void executeEmCasoDeSucesso() {
        when(livroRepository.findByTituloIgnoreCase("Senhor dos Aneis")).thenReturn(Optional.empty());
        when(livroRepository.save(livro)).thenReturn(livro);

        ResponseEntity<?> resposta = this.defaultAdicionarLivro.execute(livro);

        verify(livroRepository, times(1)).save(livro);
        verify(livroRepository, times(1)).findByTituloIgnoreCase("Senhor dos Aneis");

        Assertions.assertEquals(HttpStatusCode.valueOf(201), resposta.getStatusCode());
        Assertions.assertEquals(livro, resposta.getBody());
    }
    @Test
    @DisplayName(value = "Adicionando o livro com um titulo já existente e validando o codigo http")
    void executeEmCasoDeFalha() {
        when(this.livroRepository.findByTituloIgnoreCase("Senhor dos Aneis")).thenReturn(Optional.of(livro));
        when(this.livroRepository.save(livro)).thenReturn(livro);

        ResponseEntity<?> resposta = this.defaultAdicionarLivro.execute(livro);

        verify(this.livroRepository, times(0)).save(livro);
        verify(this.livroRepository, times(1)).findByTituloIgnoreCase("Senhor dos Aneis");

        Assertions.assertEquals(HttpStatusCode.valueOf(409), resposta.getStatusCode());
        Assertions.assertEquals("Titulo: Senhor dos Aneis, Já existe !", resposta.getBody());
    }
}