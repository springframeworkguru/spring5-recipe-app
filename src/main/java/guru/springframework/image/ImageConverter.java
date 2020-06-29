package guru.springframework.image;

public interface ImageConverter {

    public Byte[] convertFromUrl(String url, String imageFormat);

}
