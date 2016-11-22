package ru.kpfu.itis.group.kadyrov.utils;

import freemarker.template.Template;
import freemarker.template.TemplateException;
import ru.kpfu.itis.group.kadyrov.singleton.ConfigSingleton;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Map;

/**
 * Created by Амир on 12.10.2016.
 */
public class Helper {
    public static void render(HttpServletRequest request, HttpServletResponse response, String ftl, Map root) {
        Template tmpl = null;
        try {
            tmpl = ConfigSingleton.getConfig(
                    request.getServletContext()
            ).getTemplate(ftl);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            tmpl.process(root, response.getWriter());
        } catch (TemplateException | IOException e) {
            e.printStackTrace();
        }
    }
}