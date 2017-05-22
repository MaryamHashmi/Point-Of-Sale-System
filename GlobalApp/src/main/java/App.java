/**
 * Created by mika on 20/05/2017.
 */

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import sun.tools.java.Scanner;

import java.io.IOException;
import java.util.HashMap;

public class App extends Application {


    @Override
    public void start(Stage stage) throws IOException {
        extract_data ed = new extract_data();



        HashMap<Integer, Integer> shop_sales = ed.shop_sales();
        HashMap<Integer, Integer> year_sales = ed.year_sales();
        BarChart<String, Number> shop = Chart.getBarChart("Shop Sales", "Cafe", "Sales in $$", shop_sales);
        BarChart<String, Number> year = Chart.getBarChart("Yearly Sales", "Year", "Sales in $$", year_sales);
        FlowPane root = new FlowPane();
        root.getChildren().addAll(shop, year);

        Scene scene = new Scene(root, 1000, 600);

        stage.setTitle("Point of Sale Charts");
        stage.setScene(scene);
        stage.show();
    }



}
