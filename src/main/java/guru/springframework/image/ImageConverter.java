package guru.springframework.image;

public interface ImageConverter {

    public byte[] convertFromUrl(String url, String imageFormat);

}
