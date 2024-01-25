package book.catalogy.internal.usecase;

import book.catalogy.api.usecase.BuscarQuantidadeLivro;
import book.catalogy.internal.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class DefaultBuscarQuantidadeLivro implements BuscarQuantidadeLivro {
    @Autowired
    private LivroRepository livroRepository;
    @Override
    public ResponseEntity<Long> execute() {
        return ResponseEntity.status(HttpStatus.OK).body(this.livroRepository.count());
    }
}
