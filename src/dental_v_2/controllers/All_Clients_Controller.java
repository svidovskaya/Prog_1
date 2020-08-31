package dental_v_2.controllers;


import dental_v_2.db.DB_All_Clients;
import dental_v_2.db.DB_All_Clients_Update;

public class All_Clients_Controller {
    DB_All_Clients database = new DB_All_Clients();
    DB_All_Clients_Update update = new DB_All_Clients_Update();

    public String[][] history_action(){

        return database.gen_zhachenia_hist(database.count_sales());
    }

    public int count_history(){

        return database.count_sales();
    }
    public void update_h(String[][] mass){
        update.update_h(mass);
    }
    public void del_h(int id_del){
        update.del_h(id_del);
    }



}
