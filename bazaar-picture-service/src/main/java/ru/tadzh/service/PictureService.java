package ru.tadzh.service;

import ru.tadzh.persist.entity.Picture;

import java.util.List;
import java.util.Optional;

public interface PictureService {

    Optional<String> getPictureContentTypeById(long id);

    Optional<byte[]> getPictureDataById(long id);

    String createPicture(byte[] picture);

    Optional<Picture> findById(Long id);

    List<Picture> findByProductId(Long id);
}
