package book.catalogy.internal.usecase;

import book.catalogy.api.usecase.BuscarLivroPorId;
import book.catalogy.internal.entity.Livro;
import book.catalogy.internal.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DefaultBuscarLivroPorId implements BuscarLivroPorId {
    @Autowired
    private LivroRepository livroRepository;

    @Override
    public ResponseEntity<Livro> execute(Long id) {
        Optional<Livro> livroById = this.livroRepository.findById(id);
        return livroById.map(livro -> ResponseEntity.status(HttpStatus.OK).body(livro))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
}
