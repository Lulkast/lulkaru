package com.javarush.task.task28.task2810;

import com.javarush.task.task28.task2810.model.Model;
import com.javarush.task.task28.task2810.model.Provider;
import com.javarush.task.task28.task2810.vo.Vacancy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Controller {

private Model model;

    public Controller(Model model) {
        if (model == null) throw new IllegalArgumentException();
        this.model = model;
    }
   public void onCitySelect(String cityName){
        model.selectCity(cityName);
   }
// private Provider[] providers;

//    public Controller(Provider ... providers) {
//        if (providers.length == 0) throw new IllegalArgumentException();
//        this.providers = providers;
//    }
//
//    @Override
//    public String toString() {
//        return "Controller{" +
//                "providers=" + Arrays.toString(providers) +
//                '}';
//    }

//    public void scan() {
//        List<Vacancy> vacancies = new ArrayList<>();
//        Arrays.stream(providers).map(provider -> provider.getJavaVacancies("")).forEach(vacancies1 -> vacancies.addAll(vacancies1));
//        System.out.println(vacancies.size());
//    }
}
