package dental_v_1.controllers;


import dental_v_1.db.DB_Combo_User;
import dental_v_1.db.DB_Create_User;

import java.sql.SQLException;

public class User_Combo_Controller {
    DB_Combo_User cb = new DB_Combo_User();
    DB_Create_User cr = new DB_Create_User();
   public String URL = "";


    public String [] combo_positions() throws SQLException, ClassNotFoundException{
        System.out.println(URL+ "controller");
        cb.URL = URL;
        return cb.combo_position();
    }
    public void create_user(String surname, String name, String middle, int phone, String mail, String pos, String login, String pass) throws SQLException, ClassNotFoundException{
        cr.URL = URL;
        int id_position = cb.poisk_pos(pos);
        cr.save_user(surname, name, middle, phone, mail, id_position, login, pass);
    }
}
