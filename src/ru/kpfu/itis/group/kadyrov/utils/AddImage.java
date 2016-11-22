package ru.kpfu.itis.group.kadyrov.utils;

import javax.servlet.http.Part;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Амир on 22.11.2016.
 */
public class AddImage {
    public static void add(Part filePart, String fileName) throws IOException {
        File file = new File("C:/Users/Амир/IdeaProjects/GameProjectV3.1/web/images/" + fileName);
        file.createNewFile();
        FileOutputStream out = new FileOutputStream(file);
        InputStream fileContent = filePart.getInputStream();

        int read = 0;
        final byte[] bytes = new byte[1024];

        while ((read = fileContent.read(bytes)) != -1) {
            out.write(bytes, 0, read);
        }

        out.close();
        fileContent.close();
    }
}
