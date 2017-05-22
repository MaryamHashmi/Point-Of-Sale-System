import org.lightcouch.CouchDbClient;

import java.util.HashMap;
import java.util.List;

/**
 * Created by mika on 22/05/2017.
 */
public class extract_data {

    public HashMap year_sales(){
        CouchDbClient dbClient = new CouchDbClient();

        List<MeraObj> list = dbClient.view("sales_design/sale")
                .includeDocs(true)
                .query(MeraObj.class);
        HashMap<Integer, Integer> year_sales = new HashMap<Integer, Integer> ();
        int year;
        for(MeraObj item: list){
            year=item.getYear();
            if(!year_sales.containsKey(year))
            {
                year_sales.put(year, item.getAmount());
            }
            else
            {
                year_sales.put(year, year_sales.get(year)+item.getAmount());
            }
        }
        return year_sales;
    }
    public HashMap shop_sales(){
//        try {
//            System.out.println(list.get(0).getShop());
//        }catch(Exception e){
//            e.printStackTrace();
//        }
        HashMap<Integer, Integer> shop_sales = new HashMap<Integer, Integer>();
        CouchDbClient dbClient = new CouchDbClient();

        List<MeraObj> list = dbClient.view("sales_design/sale")
                .includeDocs(true)
                .query(MeraObj.class);
        HashMap<Integer, Integer> year_sales = new HashMap<Integer, Integer> ();
        int shop;
        for (MeraObj item : list) {
            shop = item.getShop();
            if (!shop_sales.containsKey(shop)) {
                shop_sales.put(shop, item.getAmount());
            } else {
                shop_sales.put(shop, shop_sales.get(shop) + item.getAmount());
            }
        }
        return shop_sales;
    }
}




