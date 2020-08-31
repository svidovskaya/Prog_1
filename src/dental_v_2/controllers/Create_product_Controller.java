package dental_v_2.controllers;


import dental_v_2.db.DB_Create_Product;
import dental_v_2.db.DB_Create_Product_Combo;

import java.sql.SQLException;

public class Create_product_Controller {
    DB_Create_Product_Combo db = new DB_Create_Product_Combo();
    DB_Create_Product prod = new DB_Create_Product();
    public String [] combo_manuf() throws SQLException, ClassNotFoundException{
        return db.combo_manuf();
    }


    public void create_product(String manuf, String name, float price, float price_stom, String shtrih, String code, String articul) throws SQLException, ClassNotFoundException{
        int id_manuf = db.poisk_manuf(manuf);

        prod.save_prod(id_manuf, name, price, price_stom, shtrih, code, articul);
    }

}
