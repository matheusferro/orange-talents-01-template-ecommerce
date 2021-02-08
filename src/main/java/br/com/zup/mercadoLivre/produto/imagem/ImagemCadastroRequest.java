package br.com.zup.mercadoLivre.produto.imagem;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

public class ImagemCadastroRequest {

    @Size(min = 1)
    @NotNull
    private Set<MultipartFile> imagens = new HashSet<>();

    public Set<MultipartFile> getImagens() {
        return imagens;
    }

    public void setImagens(Set<MultipartFile> imagens) {
        this.imagens = imagens;
    }
}
