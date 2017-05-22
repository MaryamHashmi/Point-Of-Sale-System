import org.lightcouch.CouchDbClient;

/**
 * Created by mika on 22/05/2017.
 */
public class insert_data {

    public void populate(){
        CouchDbClient dbClient = new CouchDbClient();

        MeraObj s1 = new MeraObj();
        s1.setShop(2);
        s1.setItem("coffee");
        s1.setYear(2014);
        s1.setMonth(10);
        s1.setDay(4);
        s1.setAmount(3);
        dbClient.save(s1);
//
//        MeraObj s2 = new MeraObj();
//        s1.setShop(1);
//        s1.setItem("cheese cake");
//        s1.setYear(2014);
//        s1.setMonth(3);
//        s1.setDay(11);
//        s1.setAmount(12);
//        dbClient.save(s2);
//
//        MeraObj s3 = new MeraObj();
//        s1.setShop(2);
//        s1.setItem("coffee");
//        s1.setYear(2015);
//        s1.setMonth(3);
//        s1.setDay(11);
//        s1.setAmount(3);
//        dbClient.save(s3);
//
//        MeraObj s4 = new MeraObj();
//        s1.setShop(3);
//        s1.setItem("iced tea");
//        s1.setYear(2016);
//        s1.setMonth(3);
//        s1.setDay(11);
//        s1.setAmount(2);
//        dbClient.save(s4);

//        dbClient.close();
    }

}
