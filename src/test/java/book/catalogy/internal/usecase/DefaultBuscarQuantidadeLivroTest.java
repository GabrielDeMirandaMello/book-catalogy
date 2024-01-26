package book.catalogy.internal.usecase;

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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
@ContextConfiguration
class DefaultBuscarQuantidadeLivroTest {
    @Mock
    private LivroRepository livroRepository;
    @Autowired
    @InjectMocks
    private DefaultBuscarQuantidadeLivro defaultBuscarQuantidadeLivro;
    @BeforeEach
    void init() {
        MockitoAnnotations.initMocks(this);
    }
    @Test
    @DisplayName(value = "Validar se esta retornando a quantidade de livros e o codigo http correto")
    void buscarQuantidadeDeLivroComSucesso() {
        when(this.livroRepository.count()).thenReturn(1L);

        ResponseEntity<Long> resposta = this.defaultBuscarQuantidadeLivro.execute();

        verify(livroRepository, times(1)).count();
        assertEquals(HttpStatusCode.valueOf(200), resposta.getStatusCode());
        assertEquals(1L, resposta.getBody());
    }
}