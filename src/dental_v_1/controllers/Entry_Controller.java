package dental_v_1.controllers;


import dental_v_1.db.DB_Entry;

import java.sql.SQLException;

public class Entry_Controller {
    DB_Entry de = new DB_Entry();
    public String URL = "";


    public int entry_user(String login, String pass) throws SQLException, ClassNotFoundException{

        de.URL=URL;
        de.entry_user(login, pass);
        int id_user = de.entry_user(login, pass);
        return id_user;
    }
    public int poisk_position(int id_user) throws SQLException, ClassNotFoundException{
        de.poisk_position(id_user);
        int id_pos = de.poisk_position(id_user);
        return id_pos;
    }
    public String poisk_fio(int id_user) throws SQLException, ClassNotFoundException{
        de.poisk_fio(id_user);
        return  de.poisk_fio(id_user);
    }

}
