package br.com.zup.mercadoLivre.upload;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Set;
import java.util.stream.Collectors;

@Primary
@Component
public class UploaderFake implements Uploader {

    @Override
    public Set<String> upload(Set<MultipartFile> imagens) {
        return imagens.stream().map(imagem ->
            ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/images/")
                    .path(imagem.getOriginalFilename().trim()).toUriString()
        ).collect(Collectors.toSet());
    }
}
