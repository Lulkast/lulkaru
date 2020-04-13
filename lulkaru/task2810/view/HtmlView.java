package com.javarush.task.task28.task2810.view;

import com.javarush.task.task28.task2810.Controller;
import com.javarush.task.task28.task2810.vo.Vacancy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class HtmlView implements View {
    private final String filePath = "./4.JavaCollections/src/" + this.getClass().getPackage().getName().replace('.', '/') + "/vacancies.html";
    Controller controller;

    @Override
    public void update(List<Vacancy> vacancies) {
        try {
            String newBody = getUpdatedFileContent(vacancies);
            updateFile(newBody);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //   System.out.println(vacancies.size());
    }

    @Override
    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void userCitySelectEmulationMethod() {
        controller.onCitySelect("Odessa");
    }

    private String getUpdatedFileContent(List<Vacancy> vacancies) {
        Document document = null;
        try {
            document = getDocument();
            Element templateVacancyByElement = document
                    .getElementsByClass("template").first();
            Element patternVacancyByElement = templateVacancyByElement.clone();
            patternVacancyByElement
                    .removeClass("template")
                    .removeAttr("style");
            document
                    .getElementsByAttributeValueEnding("class", "vacancy")
                    .remove();

            for (Vacancy vacancy : vacancies) {
                Element currentElement = patternVacancyByElement.clone();

                currentElement
                        .getElementsByClass("city").first()
                        .text(vacancy.getCity());
                currentElement
                        .getElementsByClass("companyName").first()
                        .text(vacancy.getCompanyName());
                currentElement
                        .getElementsByClass("salary").first()
                        .text(vacancy.getSalary());
                //currentElement.getElementsByAttribute("siteName").first().text(vacancy.getSiteName());

                Element link = currentElement.getElementsByTag("a").first();
                link.text(vacancy.getTitle());
                link.attr("href", vacancy.getUrl());

                templateVacancyByElement.before(currentElement.outerHtml());
            }

        } catch (Exception exp) {
            exp.printStackTrace();
            return "Some exception occurred";
        }
        return document.html();
    }

    private void updateFile(String string) {
        try (FileOutputStream fileOutputStream = new FileOutputStream(filePath)) {
            fileOutputStream.write(string.getBytes());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    protected Document getDocument() throws IOException{
        File in = new File(filePath);
        return Jsoup.parse(in, "UTF-8");
    }
}
