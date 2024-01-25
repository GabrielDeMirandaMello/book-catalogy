package book.catalogy.internal.usecase;

import book.catalogy.api.usecase.AtualizarLivro;
import book.catalogy.internal.entity.Livro;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class DefaultAtualizarLivro implements AtualizarLivro {
    @Override
    public ResponseEntity<Livro> execute(Long id) {
        return null;
    }
}
