package guru.springframework.image;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;

@Slf4j
@Service
public class ImageConverterImpl implements ImageConverter {

    @Override
    public Byte[] convertFromUrl(String url, String imageFormat) {

        BufferedImage bImage = null;
        try {
            final URL input = new URL(url);
            bImage = ImageIO.read(input);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ImageIO.write(bImage, imageFormat, bos );
            byte[] data = bos.toByteArray();
            Byte[] bytes = new Byte[data.length];
            Arrays.setAll(bytes, n -> data[n]);
            return bytes;
        } catch (IOException e) {
            log.debug("Could not process file");
            return null;
        }

    }
}
