package dental_v_2.controllers;


import dental_v_2.db.DB_Combo_Client;
import dental_v_2.db.DB_Create_Client;

import java.sql.SQLException;

public class Client_Combo_Controller {

    DB_Combo_Client cb = new DB_Combo_Client();
    DB_Create_Client cl = new DB_Create_Client();

    public String [] combo_class() throws SQLException, ClassNotFoundException{
        return cb.combo_class();
    }
    public void create_client(String surname, String name, String middle, String phone, String mail, String comment, String clas) throws SQLException, ClassNotFoundException{
        int id_clas = cb.poisk_class(clas);
        cl.save_client(surname, name, middle, phone, mail, comment, id_clas);
    }
    public int proverka_client(String surname, String name, String middle, String phone, String mail, String comment, String clas) throws SQLException, ClassNotFoundException{
       int c = cl.proverka(surname, name, middle, phone, mail, comment);
     return c;
    }
}
