package dental_v_2.db;

import dental_v_2.mail.mail_properties;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;

public class DB_File_Nakladnaya_Mail extends DBProcessor {

    public void poisk_danni(int id) {
int r_c =0;
int id_order = id;
String m = "";
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             Statement statement = connection.createStatement()) {
            String sql_code_1 = "Select on_nakladnaya_id from ord_nak where on_order_id = '"+id_order+"' ";

            ResultSet resSet1 = statement.executeQuery(sql_code_1);
            int i = 0;

            while (resSet1.next()) {
                r_c = resSet1.getInt("on_nakladnaya_id");
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             Statement statement = connection.createStatement()) {
            String sql_code_1 = "SELECT cl_email from clients inner join orders on clients.client_id = orders.o_client_id where order_id = '"+id_order+"' ";

            ResultSet resSet1 = statement.executeQuery(sql_code_1);
            int i = 0;

            while (resSet1.next()) {
                m = resSet1.getString("cl_email");
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

       String name = "C:/" +r_c+".txt";


        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             Statement statement = connection.createStatement()) {


            String sql_code2 = "Select n_summa as ssss from nakladnaya where nakladnaya_id = '"+r_c+"'";
            String su = "";
            ResultSet resSet2 = statement.executeQuery(sql_code2);

            while (resSet2.next()){
                su = resSet2.getString("ssss");
            }
            String sql = "Select count(i_p_name) as ss from info_nakl inner join nakladnaya on i_nakladnaya = nakladnaya.nakladnaya_id\n" +
                    "inner join ord_nak on ord_nak.on_nakladnaya_id = nakladnaya_id\n" +
                    "inner join orders on ord_nak.on_order_id = orders.order_id\n" +
                    "inner join clients on clients.client_id = orders.o_client_id\n" +
                    "where nakladnaya_id = '"+r_c+"'";


            ResultSet res = statement.executeQuery(sql);

            int r = 0;
            while (res.next()){
                r = res.getInt("ss");
            }

            int i=0;

            File file = new File(name);
            FileWriter fileWriter = new FileWriter(file);

            String sql_co = "Select info_nakl.i_nakladnaya, cl_surname, cl_name, i_p_name, i_price, n_summa, n_data from info_nakl inner join nakladnaya on i_nakladnaya = nakladnaya.nakladnaya_id\n" +
                    "inner join ord_nak on ord_nak.on_nakladnaya_id = nakladnaya_id\n" +
                    "inner join orders on ord_nak.on_order_id = orders.order_id\n" +
                    "inner join clients on clients.client_id = orders.o_client_id\n" +
                    "where nakladnaya_id = '"+r_c+"'" +
                    "limit 1";
            ResultSet rse = statement.executeQuery(sql_co);
            String fio = "";
            String da = "";
            while (rse.next()) {
                fio += rse.getString("cl_surname");
                fio += rse.getString("cl_name");
                da = rse.getString("n_data");
            }
            String sql_code = "Select info_nakl.i_nakladnaya, i_amount, cl_surname, cl_name, i_p_name, i_price, n_summa, n_data from info_nakl inner join nakladnaya on i_nakladnaya = nakladnaya.nakladnaya_id\n" +
                    "inner join ord_nak on ord_nak.on_nakladnaya_id = nakladnaya_id\n" +
                    "inner join orders on ord_nak.on_order_id = orders.order_id\n" +
                    "inner join clients on clients.client_id = orders.o_client_id\n" +
                    "where nakladnaya_id = '"+r_c+"'";
            ResultSet resSet = statement.executeQuery(sql_code);
//            int zn_n = 0;
//            Float s = Float.valueOf(0);


            fileWriter.write("Постачальник:       ФОП ``Медуха Ірина Володимирівна`` "+ "\n");
            fileWriter.write("                    П/р 26003055318617, у банку Приват Банк, МФО 302689"+ "\n");
            fileWriter.write("                    юр. адреса: Вінниця, Хмельницьке шосе, будинок №49, \n                    кв.59, тел.: 067 398 43 75"+ "\n");
            fileWriter.write("                    ІПН 2713113102, № свід. 741456,"+ "\n");
            fileWriter.write("                    Не є платником податку на прибуток за загальною \n                    системою оподаткування відповідно до Податкового"+ "\n");
            fileWriter.write("                    кодексу України"+ "\n");

            fileWriter.write("\n");
            fileWriter.write("Покупатель:         " + fio + "\n");
            fileWriter.write("\n");

            fileWriter.write("Договір:            №" + r_c + " від " +  da + "\n");
            fileWriter.write("\n");


            fileWriter.flush();
            String[][] result_h = new String[r][7];
            int k = 0;
            fileWriter.write("_________________________________________________________________________ \n \n");
            while (resSet.next()) {



                result_h[i][0] = String.valueOf(resSet.getInt("i_nakladnaya"));
                result_h[i][1] = resSet.getString("cl_surname");
                result_h[i][2] = resSet.getString("cl_name");
                result_h[i][3] = resSet.getString("i_p_name");
                result_h[i][4] = resSet.getString("i_price");
                result_h[i][5] = resSet.getString("n_summa");
                result_h[i][6] = resSet.getString("i_amount");
                k+=1;

                float a = Float.parseFloat(result_h[i][4]);
                float b = Integer.parseInt(result_h[i][6]);
                float c = a*b;

                fileWriter.write(k + " "+(result_h[i][3]) + " " + (result_h[i][6]) +" шт. " + (result_h[i][4]) + " " + c + " грн \n");

                fileWriter.flush();


                i += 1;



            }
            fileWriter.write("_________________________________________________________________________ \n");

            fileWriter.write("\n" +"Сумма: " + su + " грн");
            fileWriter.write("\n");
            fileWriter.write("\n");
            fileWriter.write("Місце складання: м.Вінниця \n");
            fileWriter.write("\n");

            fileWriter.write("_________________________________________________________________________ \n");
            fileWriter.write("\n");

            fileWriter.write("Від постачатьника*                     Отримав(ла) \n");
            fileWriter.write("\n");
            fileWriter.write("\n");
            fileWriter.write("___________________________________     _________________________________\n");

            fileWriter.write("Медуха Ірина Володимирівна                                 \n");
            fileWriter.write("*Відповідальний за здійснення           За довіреністю       №      від   \n");
            fileWriter.write(" господарської операції і \n правильність її оформлення        \n");

            fileWriter.flush();
           // fileWriter.write("Сумма накладной " + s + " грн \n");


            fileWriter.close();


            mail_properties.sendMail(m, "", "", 2, String.valueOf(r_c));

         //   Desktop.getDesktop().edit(file);



        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
