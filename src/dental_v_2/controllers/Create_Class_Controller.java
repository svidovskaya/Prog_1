package dental_v_2.controllers;


import dental_v_2.db.DB_Create_Class;

import java.sql.SQLException;

public class Create_Class_Controller {

    DB_Create_Class cla = new DB_Create_Class();

    public void create_class(String name, String info) throws SQLException, ClassNotFoundException{
        cla.save_class(name, info);
    }
}
