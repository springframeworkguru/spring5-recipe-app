package guru.springframework.image;

import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;

@Service
public class ImageConverterImpl implements ImageConverter {

    @Override
    public byte[] convertFromUrl(String url, String imageFormat) {

        BufferedImage bImage = null;
        try {
            bImage = ImageIO.read(new URL(url));
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ImageIO.write(bImage, imageFormat, bos );
            byte[] data = bos.toByteArray();
            return data;
        } catch (IOException e) {
            System.out.println("Could not process file");
            return null;
        }

    }
}
