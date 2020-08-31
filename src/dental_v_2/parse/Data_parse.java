package dental_v_2.parse;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Data_parse {
    public String data(int year, int mount, int day){
        return year + "-" + mount + "-" + day;
    }
    public String data_nuw(){
        Date data = new Date();
        SimpleDateFormat year_format = new SimpleDateFormat("yyyy");
        SimpleDateFormat mount_format = new SimpleDateFormat("MM");
        SimpleDateFormat day_format = new SimpleDateFormat("dd");
        int year = Integer.parseInt(year_format.format(data));
        int mount = Integer.parseInt(mount_format.format(data));
        int day = Integer.parseInt(day_format.format(data));
        return year + "-" + mount + "-" + day;
    }
}
