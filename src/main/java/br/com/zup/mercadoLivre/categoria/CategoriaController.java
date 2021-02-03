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
        Categoria categoria = request.toModel();
        categoriaRepository.save(categoria);
        CategoriaResponse response = new CategoriaResponse(categoria.getId(),categoria.getNome(), categoria.getIdCategoriaMae());
        return ResponseEntity.ok().body(response);
    }

}
