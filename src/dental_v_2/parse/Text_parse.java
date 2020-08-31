package dental_v_2.parse;

import java.sql.SQLException;

public class Text_parse {
    public String [] name_surname(String n) throws SQLException, ClassNotFoundException{

            String [] result = new String[2];
            int start = 0;
            int stop = n.indexOf(" ");
            result[0] = n.substring(start,stop);
            start = stop + 1;
            result[1] = n.substring(start);


            return result;

    }
}
