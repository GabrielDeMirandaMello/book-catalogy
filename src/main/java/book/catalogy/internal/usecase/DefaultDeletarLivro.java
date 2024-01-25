package book.catalogy.internal.usecase;

import book.catalogy.api.usecase.DeletarLivro;
import book.catalogy.internal.entity.Livro;
import book.catalogy.internal.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DefaultDeletarLivro implements DeletarLivro {
    @Autowired
    private LivroRepository livroRepository;

    @Override
    public ResponseEntity<?> execute(Long id) {
        Optional<Livro> livroById = this.livroRepository.findById(id);
        return livroById.map(livro -> {
            this.livroRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body("Livro deletado com sucesso !");
        }).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("Livro com id:"+ id +" não existe !"));
    }
}
