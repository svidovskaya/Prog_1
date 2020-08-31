package dental_v_2.controllers;

import dental_v_2.db.DB_File_Nakladnaya;
import dental_v_2.db.DB_File_Nakladnaya_Mail;

public class File_Nakladnaya_Controller {

  //  DB_File file = new DB_File();
    DB_File_Nakladnaya file = new DB_File_Nakladnaya();
    DB_File_Nakladnaya_Mail file_m = new DB_File_Nakladnaya_Mail();

    public void file_db(int id){

        file.poisk_danni(id);
    }
    public void file_db_mail(int id){

        file_m.poisk_danni(id);
    }
}
