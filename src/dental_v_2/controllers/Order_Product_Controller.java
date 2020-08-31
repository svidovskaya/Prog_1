package dental_v_2.controllers;


import dental_v_2.db.DB_Order_Prosm;
import dental_v_2.db.DB_Order_Prosm_Update;

public class Order_Product_Controller {
    DB_Order_Prosm database = new DB_Order_Prosm();
    DB_Order_Prosm_Update update = new DB_Order_Prosm_Update();
//    DB_File file = new DB_File();

    public String[][] history_action(int id_order){

        return database.gen_zhachenia_hist(id_order, database.count_sales(id_order));
    }

    public int count_history(int id_order){

        return database.count_sales(id_order);
    }
    public void update_h(String[][] mass, int id_order){
        update.update_h(mass, id_order);
    }
    public void create_n(String[][] mass, int id_order, int id_user, String vid_opl){
        update.create_n(mass, id_order, id_user, vid_opl);
    }
    public void del_h(int id_del){
        update.del_h(id_del);
    }
    public int poisk_summa(int id_order){
        return database.poisk_summa(id_order);
    }

//    public String[][] poisk_danni(){
//
//        return file.poisk_danni();
//    }
}
