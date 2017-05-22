
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javafx.scene.Scene;
import javafx.scene.chart.*;

/**
 * Created by mika on 21/05/2017.
 */

public abstract class Chart{

    public static BarChart<String, Number> getBarChart(String title, String x, String y, HashMap map){
        System.out.println(map);
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        final BarChart<String,Number> barChart =
                new BarChart<String,Number>(xAxis,yAxis);
        barChart.setTitle(title);
        xAxis.setLabel(x);
        yAxis.setLabel(y);
        XYChart.Series series = new XYChart.Series();

        Iterator<Map.Entry<Integer, Integer>> entries = map.entrySet().iterator();
        while(entries.hasNext()) {
            Map.Entry entry = (Map.Entry)entries.next();
            series.getData().add(new XYChart.Data(entry.getKey().toString(), entry.getValue()));
        }

        barChart.getData().add(series);
        return barChart;
    }






    }





