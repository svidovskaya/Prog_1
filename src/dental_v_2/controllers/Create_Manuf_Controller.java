package dental_v_2.controllers;


import dental_v_2.db.DB_Create_Manuf;

import java.sql.SQLException;

public class Create_Manuf_Controller {

    DB_Create_Manuf mn = new DB_Create_Manuf();
int p = 0;
    public void create_manuf(String name, String country) throws SQLException, ClassNotFoundException{
        mn.save_manuf(name, country);
    }
    public int proverka(String name) throws SQLException, ClassNotFoundException{
      p =  mn.proverka_manuf(name);
      return p;
    }
}
