package book.catalogy.internal.usecase;

import book.catalogy.api.usecase.AtualizarLivro;
import book.catalogy.internal.entity.Livro;
import book.catalogy.internal.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DefaultAtualizarLivro implements AtualizarLivro {
    @Autowired
    private LivroRepository livroRepository;

    @Override
    public ResponseEntity<Livro> execute(Long id, Livro livroAtualizado) {
        Optional<Livro> livroById = this.livroRepository.findById(id);
        if (livroById.isPresent()){
            Livro livroAlterado = this.livroRepository.save(livroAtualizado);
            return ResponseEntity.status(HttpStatus.OK).body(livroAlterado);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
