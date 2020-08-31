package dental_v_2.controllers;


import dental_v_2.db.DB_Combo_Orders;
import dental_v_2.db.DB_Create_Order;
import dental_v_2.parse.Text_parse;

import java.sql.SQLException;

public class Create_Orders_Controller {

    DB_Combo_Orders cb = new DB_Combo_Orders();
    Text_parse text = new Text_parse();
    DB_Create_Order order = new DB_Create_Order();
   // DB_Prod_in_Stock ps = new DB_Prod_in_Stock();

    public String [] combo_platforms() throws SQLException, ClassNotFoundException{
        return cb.combo_platforms();
    }
    public String [] combo_class() throws SQLException, ClassNotFoundException{
        return cb.combo_class();
    }
    public String [] combo_clients(String clas) throws SQLException, ClassNotFoundException {
        return cb.combo_clients(clas);
    }
    public String [] combo_deliv() throws SQLException, ClassNotFoundException {
        return cb.combo_deliv();
    }
    public String [] combo_prod(String poisk) throws SQLException, ClassNotFoundException {
        return cb.combo_prod(poisk);
    }
    public String [] combo_prod_1(String poisk) throws SQLException, ClassNotFoundException {
        return cb.combo_prod_1(poisk);
    }

    public int create_order(String client, String delivery, String platform, String fio, int user_id) throws SQLException, ClassNotFoundException{
        String[] ck = text.name_surname(client);
        int id_client = cb.poisk_client(ck[0],ck[1]);
        int id_delivery = cb.poisk_delivery(delivery);
        int id_platform = cb.poisk_platforms(platform);


        return order.save_action(id_client, id_delivery, id_platform , fio, user_id);
    }
    public void app_product(String product, int count, int id_order, String clas) throws SQLException, ClassNotFoundException{

        int id_class = cb.poisk_clas(clas);
        String[] p_info = cb.poisk_prod(product);

        String pr = "";
        if (id_class == 1){
            pr = p_info[1];
        } else if (id_class == 2){
            pr = p_info[2];
        }

        order.save_prod(id_order, Integer.parseInt(p_info[0]), pr,  count);


    }
    public String[] poisk_pr(String product) throws SQLException, ClassNotFoundException{

        String[] p_info = cb.poisk_prod(product);


        return p_info;
    }
//    public void app_stock_product(String product, int count) throws SQLException, ClassNotFoundException{
//        int id_prod = cb.poisk_prod(product);
//        ps.save_prod_minus(id_prod, count);
//    }
    public int proverka(String product) throws SQLException, ClassNotFoundException{

        return order.poisk_colva(product);
    }
    public String poisk_s(int id_order) throws SQLException, ClassNotFoundException{

        return order.poisk_s(id_order);
    }

}
