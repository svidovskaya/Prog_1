package dental_v_2.controllers;


import dental_v_2.db.DB_Entry;

import java.sql.SQLException;

public class Entry_Controller {
    DB_Entry de = new DB_Entry();
    int id_user = 0;
    public int entry_pr(String login, String pass) throws SQLException, ClassNotFoundException{
        int pt = de.entry_pr(login, pass);
        return pt;
    }
    public int entry_user(String login, String pass) throws SQLException, ClassNotFoundException{
        id_user = de.entry_user(login, pass);
        return id_user;
    }
    public int poisk_position(int id_user) throws SQLException, ClassNotFoundException{

        int id_pos = de.poisk_position(id_user);
        return id_pos;
    }
    public String poisk_fio(int id_user) throws SQLException, ClassNotFoundException{
        de.poisk_fio(id_user);
        return  de.poisk_fio(id_user);
    }
    public String[] poisk_mail(String login) throws SQLException, ClassNotFoundException{

        return  de.poisk_mail(login);
    }

}
