package dental_v_2.controllers;

import dental_v_2.db.DB_File;
import dental_v_2.db.DB_File_1;

import java.io.IOException;

public class File_Controller {

    DB_File file = new DB_File();
    DB_File_1 file_1 = new DB_File_1();


    public String[][] poisk_danni(){

        return file.poisk_danni();
    }
    public String[][] poisk_danni_1() throws IOException {

        return file_1.poisk_danni();
    }

}
