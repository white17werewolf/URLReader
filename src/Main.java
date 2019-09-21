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
           /* Scanner in = new Scanner(System.in);
            System.out.print("Введите ссылку: ");
            String link = in.nextLine();
            in.close();*/

            URL url = new URL(/*"https://nat-geo.ru/"*/"https://www.google.ru/");
            String URL_img = "";
            float sizeIMG = 0;

            try {
                LineNumberReader reader = new LineNumberReader(new InputStreamReader(url.openStream()));
                String HTMLCode = reader.readLine(); //запись кода страницы в переменную

                while (HTMLCode != null) {          //прогон всего кода элемента
                    Pattern img = Pattern.compile("<img (.*?)>"); //поиск картинок
                    Matcher findImg = img.matcher(HTMLCode);
                    if (findImg.find()) {                   //если обнаружено изображение
                        String tempImg = findImg.group(1);
                        Pattern src = Pattern.compile("src=\"(.*?)\""); //извлечение адреса изображения
                        Matcher findSrc = src.matcher(tempImg);
                        if(findSrc.find())
                        {
                           URL_img = url.toString() + findSrc.group(1);
                           URL url1 = new URL(URL_img);
                           Image image = ImageIO.read(url1);
                           System.out.println("URL изображения: " + URL_img + ",  разрешение изображения " + image.getWidth(null) +" X "+ image.getHeight(null) +",  размер изображения:" + url1.openConnection().getContentLength() + "байт");
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



