package com.javarush.task.task28.task2810;

import com.javarush.task.task28.task2810.model.HHStrategy;
import com.javarush.task.task28.task2810.model.Model;
import com.javarush.task.task28.task2810.model.MoikrugStrategy;
import com.javarush.task.task28.task2810.model.Provider;
import com.javarush.task.task28.task2810.view.HtmlView;
import com.javarush.task.task28.task2810.view.View;


public class Aggregator {
    public static void main(String[] args) {
        HtmlView view = new HtmlView();   // view
//        HHStrategy hhStrategy = new HHStrategy();//
//        Provider providerHH = new Provider(hhStrategy);
        MoikrugStrategy moikrugStrategy = new MoikrugStrategy();
        Provider providerMoikrug = new Provider(moikrugStrategy); //
        HHStrategy hhStrategy = new HHStrategy();
        Provider provider = new Provider(hhStrategy);
//
        Model model = new Model(view, provider, providerMoikrug);
        Controller controller = new Controller(model);
        view.setController(controller);
//       // controller.onCitySelect("Odessa");
        view.userCitySelectEmulationMethod();
//
    }
}
