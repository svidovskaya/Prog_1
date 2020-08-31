package dental_v_1.view;

import javax.swing.*;
import java.sql.SQLException;

public class Poisk_ip_form {

    JTextField text_ip = new JTextField("localhost");
    JButton but_ip = new JButton("Ok");
    public String zn_ip = "";
    public String URL = "";
    Entry_form entry_form = new Entry_form();

    public JPanel poisk_ip_panel(){
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(0, 200, 1300, 600);


        text_ip.setBounds(15, 70, 200, 30);
        panel.add(text_ip);


        but_ip.setBounds(235, 70, 50, 30);
        panel.add(but_ip);
//
//        but_ip.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
////                zn_ip = text_ip.getText();
////                //  System.out.println(zn_ip);
////                try {
////                    poisk_ip(zn_ip);
////                    System.out.println(URL + "нажатие");
////                    entry_form.URL = URL;
////
////
////
////                } catch (SQLException ex) {
////                    ex.printStackTrace();
////                } catch (ClassNotFoundException ex) {
////                    ex.printStackTrace();
////                }
////                text_ip.setVisible(false);
////                but_ip.setVisible(false);
////                panel.remove(panel);
////                panel.add(entry);
////                panel.repaint();
////                DBProcessor db = new DBProcessor();
////
////                db.zn_ip_d = zn_ip;
////                System.out.println(db.zn_ip_d);
////
////
////                root_2_form.setVisible(true);
//
//            }
//        });




        return panel;
    }

    public void poisk_ip (String zn_ip) throws SQLException, ClassNotFoundException{

        URL = "jdbc:mysql://";
        URL += zn_ip;
        URL += "/dental?useSSL=false";





    }
}
