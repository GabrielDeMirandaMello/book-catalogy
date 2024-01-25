package book.catalogy.internal.usecase;

import book.catalogy.api.usecase.AdicionarLivro;
import book.catalogy.internal.entity.Livro;
import book.catalogy.internal.repository.LivroRepository;
import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class DefaultAdicionarLivro implements AdicionarLivro {

    @Autowired
    private LivroRepository livroRepository;

    @Override
    public ResponseEntity<?> execute(Livro livro) {
        try{
            if (existeLivro(livro.getTitulo())) {
                return ResponseEntity.status(HttpStatus.CREATED).body(this.livroRepository.save(livro));
            } else {
                return ResponseEntity.status(HttpStatus.CONFLICT).body(
                        String.format("Titulo: %s, Já existe !",
                                livro.getTitulo()));
            }
        } catch (ConstraintViolationException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }

    }

    private Boolean existeLivro(String titulo){
        return this.livroRepository.findByTituloIgnoreCase(titulo).isEmpty();
    }

}
