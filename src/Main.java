import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;




public class Main {
    public static void main(String[] args) throws IOException {
        try {
            URL url = new URL("https://www.google.ru/");
            String URL_img = "";

            try {
                LineNumberReader reader = new LineNumberReader(new InputStreamReader(url.openStream()));
                String HTMLCode = reader.readLine();

                while (HTMLCode != null) {
                    Pattern img = Pattern.compile("<img (.*?)>");
                    Matcher findImg = img.matcher(HTMLCode);
                    if (findImg.find()) {
                        String tempImg = findImg.group(1);
                        Pattern src = Pattern.compile("src=\"(.*?)\"");
                        Matcher findSrc = src.matcher(tempImg);
                        if(findSrc.find())
                        {
                           URL_img = url.toString() + findSrc.group(1);
                           URL url1 = new URL(URL_img);
                           Image image = ImageIO.read(url1);
                           System.out.println("URL: " + URL_img + ",  разрешение: " + image.getWidth(null) +" x "+ image.getHeight(null) +",  размер: " + url1.openConnection().getContentLength() + " байт");
                        }
                    }
                    HTMLCode = reader.readLine();
                }
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (MalformedURLException ex) {
            ex.printStackTrace();
        }
    }
}



