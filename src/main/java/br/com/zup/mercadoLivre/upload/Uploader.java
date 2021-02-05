package br.com.zup.mercadoLivre.upload;

import org.springframework.web.multipart.MultipartFile;

import java.util.Set;

public interface Uploader {
    Set<String> upload(Set<MultipartFile> files);
}
