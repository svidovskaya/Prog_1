package dental_v_2.controllers;


import dental_v_2.db.DB_History_Orders;
import dental_v_2.db.DB_History_Orders_Update;

public class History_Order_Controler {
    DB_History_Orders database = new DB_History_Orders();
    DB_History_Orders_Update hist = new DB_History_Orders_Update();

    public String[][] history_action(String poisk){

        return database.gen_zhachenia_hist(database.count_sales(poisk), poisk);
    }

    public int count_history(String poisk){

        return database.count_sales(poisk);
    }

    public void show_information(int id){
        database.show_inform(id);
    }
  /*  public void show_delivery(int id){
        database.show_delivery(id);
    }
*/
//    public void update_h(String[][] mass){
//        hist.update_h(mass);
//    }
    public void del_h(int id_del){
        hist.del_h(id_del);
    }
}
