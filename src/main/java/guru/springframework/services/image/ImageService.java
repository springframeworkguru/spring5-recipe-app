package guru.springframework.services.image;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService {
    void saveImageFile(String id, MultipartFile multipartFile);
}
