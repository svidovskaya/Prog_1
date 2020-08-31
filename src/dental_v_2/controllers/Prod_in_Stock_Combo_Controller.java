package dental_v_2.controllers;


import dental_v_2.db.DB_Combo_Prod_in_Stock;
import dental_v_2.db.DB_Prod_in_Stock;

import java.sql.SQLException;

public class Prod_in_Stock_Combo_Controller {
    DB_Combo_Prod_in_Stock cb = new DB_Combo_Prod_in_Stock();

    DB_Prod_in_Stock db = new DB_Prod_in_Stock();


    public String [] combo_prod(String poisk) throws SQLException, ClassNotFoundException {
        return cb.combo_prod(poisk);
    }
    public String [] combo_prod_1(String poisk) throws SQLException, ClassNotFoundException {
        return cb.combo_prod_1(poisk);
    }

    public void app_product(String product, int count) throws SQLException, ClassNotFoundException{
        int id_prod = cb.poisk_prod(product);

        db.save_prod(id_prod, count);
    }

}
