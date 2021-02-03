package br.com.zup.mercadoLivre.categoria;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class CategoriaController {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @PostMapping("/categoria")
    public ResponseEntity<?> cadastrar(@RequestBody @Valid CategoriaCadastroRequest request ){
        Categoria categoria = request.toModel(categoriaRepository);
        categoriaRepository.save(categoria);
        return ResponseEntity.ok().body(categoria);
    }

}
