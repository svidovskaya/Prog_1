package dental_v_2.db;



import javax.swing.*;
import java.sql.*;

public class DB_Entry extends DBProcessor {
    public int entry_pr (String login, String pass) {
        int k = 0;


        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             Statement statement = connection.createStatement()) {
            String sql_code = "select user_id from users where u_login = '" + login + "' ";

            ResultSet resSet = statement.executeQuery(sql_code);
            int c = 0;
            int v=0;

            while (resSet.next()) {
                c = resSet.getInt("user_id");
            }

            if (c != 0){
                String sql_code1 = "select user_id from users where u_login = '" + login + "' && u_password = '"+pass+"' ";
                ResultSet resSet1 = statement.executeQuery(sql_code1);

                while (resSet1.next()) {
                    v = resSet1.getInt("user_id");
                }
                if (v ==0){
                   k=1;

                }
            }




        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane op = new JOptionPane();
            op.showMessageDialog(null, "Ошибка!");
        }

        return k;
    }
    public int entry_user (String login, String pass) {



        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             Statement statement = connection.createStatement()) {
            String sql_code = "select user_id from users where u_login = '" + login + "' ";

            ResultSet resSet = statement.executeQuery(sql_code);
            int c = 0;
            int v = 0;
            int k = 0;
            while (resSet.next()) {
                c = resSet.getInt("user_id");
            }
          /*  if (c==0){
                JOptionPane op = new JOptionPane();
                op.showMessageDialog(null, "Нет такого пользователя!");
            }*/
            if (c != 0){
                String sql_code1 = "select user_id from users where u_login = '" + login + "' && u_password = '"+pass+"' ";
                ResultSet resSet1 = statement.executeQuery(sql_code1);

                while (resSet1.next()) {
                    v = resSet1.getInt("user_id");
                }
                if (v ==0){
                    JOptionPane op = new JOptionPane();
                    op.showMessageDialog(null, "Неверный пароль");




                }
            } else { JOptionPane op = new JOptionPane();
                op.showMessageDialog(null, "Такого пользователя нет!");}

            return v;



        } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane op = new JOptionPane();
        op.showMessageDialog(null, "Ошибка!");
    }
        int k = 0;
        return k;
    }

    public int poisk_position (int id_user) {


        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             Statement statement = connection.createStatement()) {
            String sql_code = "select u_pos_id from users where user_id = '" + id_user + "' ";
            ResultSet resSet = statement.executeQuery(sql_code);
            int c = 0;
            while (resSet.next()) {
                c = resSet.getInt("u_pos_id");
            }
          /*  if (c==0){
                JOptionPane op = new JOptionPane();
                op.showMessageDialog(null, "Нет такого пользователя!");
            }*/

            return c;



        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane op = new JOptionPane();
            op.showMessageDialog(null, "Ошибка!");
        }
        int k = 0;
        return k;
    }
    public String poisk_fio(int id_user){

        String c="";
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             Statement statement = connection.createStatement()) {
            String sql_code = "select i_surname, i_name, i_middlename from informations where i_user_is = '"+id_user+"'";
            ResultSet resSet = statement.executeQuery(sql_code);

            while (resSet.next()) {
                c += resSet.getString("i_surname");
                c+= " ";
                c += resSet.getString("i_name");
                c+= " ";
                c += resSet.getString("i_middlename");

            }


        } catch (SQLException e) {
            e.printStackTrace();
        }


        return c ;
    }

    public String[] poisk_mail(String login){

        String[] c = {"", "", ""};
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             Statement statement = connection.createStatement()) {
            String sql_code = "select u_password, i_email, i_name from informations inner join users on user_id = i_user_is where u_login = '"+login+"'";
            ResultSet resSet = statement.executeQuery(sql_code);

            while (resSet.next()) {
                c[0] = resSet.getString("u_password");
                c[1] = resSet.getString("i_email");
                c[2] = resSet.getString("i_name");
            }






        } catch (SQLException e) {
            e.printStackTrace();
        }


        return c ;
    }




}
