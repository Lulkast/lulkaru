package com.javarush.task.task28.task2810.model;

import com.javarush.task.task28.task2810.vo.Vacancy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MoikrugStrategy implements Strategy {
    private static final String URL_FORMAT = "https://moikrug.ru/vacancies?q=java+%s&page=%d";

    String ADDITIONAL_VALUE;
    int PAGE_VALUE = 0;

    @Override
    public List<Vacancy> getVacancies(String searchString) {
        ArrayList<Vacancy> vacancies = new ArrayList<>();
        int count = 0;
        while (count>-1) {
            Document html = null;
            html = getDocument(searchString, count);
            ArrayList<Element> tagList = html.body().getElementsByClass("job");
            if (tagList.size() > 0) {
                count++;
            } else {
                count = -1;
            }
            //            class Vacancy {
//                private String title,
//                        salary,
//                        city,
//                        companyName,
//                        siteName,
//                        url;
            for (Element element : tagList) {
                Vacancy vacancy = new Vacancy();

                vacancy.setSiteName("https://moikrug.ru/");

                Elements el;

                el = element.getElementsByClass("title").first().getElementsByTag("a");
                vacancy.setTitle(el.first().text());
                vacancy.setUrl(vacancy.getSiteName() + el.attr("href").substring(1));

                el = element.getElementsByClass("salary");
                vacancy.setSalary(el.size() != 0 ? el.first().getElementsByTag("div").first().text() : "");

                el = element.getElementsByClass("location");
                vacancy.setCity(el.size() != 0 ? el.first().getElementsByTag("a").first().text() : "");

                el = element.getElementsByClass("company_name");
                vacancy.setCompanyName(el != null ? el.first().text() : "");

                vacancies.add(vacancy);
            }
        }
            return vacancies;

    }

    protected Document getDocument(String searchString, int page_value) {
        String myURL = String.format(URL_FORMAT, searchString, page_value);

        Document doc = null;
        try {
            doc = Jsoup.connect(myURL).
                    userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_5) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/11.1.1 Safari/605.1.15").
                    referrer("").
                    get();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return doc;
    }
}
