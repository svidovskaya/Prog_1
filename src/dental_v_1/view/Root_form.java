package dental_v_1.view;

import dental_v_1.controllers.Entry_Controller;
import dental_v_1.controllers.User_Combo_Controller;
import dental_v_1.db.DBProcessor;
import dental_v_1.db.DB_Entry;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;


public class Root_form extends JFrame{

    public Entry_form entry_form = new Entry_form();
    public DBProcessor dbProcessor = new DBProcessor();
    public DB_Entry db_entry = new DB_Entry();
    public Poisk_ip_form poisk_ip_form = new Poisk_ip_form();
    public Registration_form registration_form = new Registration_form();

    public Menu_form menu_form = new Menu_form();
    public JPanel poisk_ip_panel;
    public JPanel entry;
    public JPanel menu;
    public JPanel registation;

    String zn_ip = "";
    public int id_user =0;
    public int id_pos =0;

    public  String URL = "";
    String text_fio ="";
    Entry_Controller entry_controller = new Entry_Controller();
    User_Combo_Controller user_combo_controller = new User_Combo_Controller();



    public Root_form(){


        super("DentalGid");
        this.setBounds(40, 0, 1300, 700);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);

        ImageIcon image_1 = new ImageIcon("C:\\logo.png");

        JLabel img_dg = new JLabel(image_1);
        img_dg.setBounds(500, 10, 300, 100);
        panel.add(img_dg);

        entry = entry_form.newJPanel();
        poisk_ip_panel = poisk_ip_form.poisk_ip_panel();

        registation = registration_form.newJPanel();


        menu = menu_form.newJPanel();

        panel.add(poisk_ip_panel);

        poisk_ip_form.but_ip.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                zn_ip = poisk_ip_form.text_ip.getText();
              //  System.out.println(zn_ip);
                try {
                    poisk_ip(zn_ip);
   //                 System.out.println(URL + "нажатие");
                    entry_form.URL = URL;

                } catch (SQLException ex) {
                    ex.printStackTrace();
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
//                text_ip.setVisible(false);
//                but_ip.setVisible(false);
                panel.remove(poisk_ip_panel);
                panel.add(entry);
                panel.repaint();


            }
        });
        entry_form.e_registration.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                panel.remove(entry);
                panel.add(registation);
                panel.repaint();
            }
        });

        registration_form.e_back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.remove(registation);
                panel.add(entry);
                panel.repaint();
            }
        });

        entry_form.e_entry.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String login = entry_form.text_login.getText();
                String pass = entry_form.text_passw.getText();

                try {
                    entry_controller.URL = URL;
                    entry_user(login, pass);
                    poisk_position(id_user);
                    poisk_fio(id_user);

                    JLabel text_label = new JLabel(text_fio);
                    text_label.setBounds(10,10, 400, 30);
                    panel.add(text_label);
                    panel.repaint();



                } catch (SQLException ex) {
                    ex.printStackTrace();
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                }

//                System.out.println(id_user + "user1");
//                System.out.println(id_pos + "pos1");
//                System.out.println(text_fio + "t");

                panel.remove(entry);
                panel.add(menu);
                panel.repaint();

                switch (id_pos){
                    case 1:

                        panel.repaint();
                        break;
                    case 2:
                        menu_form.b_create_product.setVisible(false);
                        menu_form.b_add_prod_in_st.setVisible(false);
                        panel.repaint();
                        break;
                    case 3:
                        menu_form.b_create_product.setVisible(false);
                        menu_form.b_add_prod_in_st.setVisible(false);
                        menu_form.b_create_client.setVisible(false);
                        menu_form.b_create_order.setVisible(false);
                        menu_form.b_generation.setVisible(false);
                        panel.repaint();
                        break;
                    case 4:
                        menu_form.b_create_client.setVisible(false);
                        menu_form.b_create_order.setVisible(false);
                        menu_form.b_generation.setVisible(false);
                        menu_form.b_history_order.setVisible(false);
                        menu_form.b_history.setVisible(false);
                        panel.repaint();
                        break;
                    case 5:
                        menu_form.b_add_prod_in_st.setVisible(false);
                        panel.repaint();
                        break;
                    default:
                        panel.remove(menu);
                        panel.add(entry);
                        panel.repaint();
                        JOptionPane op = new JOptionPane();
                        op.showMessageDialog(null, "Нет совпадений!");

                }


            }
        });








        panel.repaint();

        setContentPane(panel);
    }

    public void poisk_ip (String zn_ip) throws SQLException, ClassNotFoundException{

        URL = "jdbc:mysql://";
        URL += zn_ip;
        URL += "/dental?useSSL=false";





    }

    public int entry_user (String login, String pass) throws SQLException, ClassNotFoundException{
        entry_controller.entry_user(login, pass);

        id_user = entry_controller.entry_user(login, pass);
        return id_user;

    }
    public int poisk_position (int id_user) throws SQLException, ClassNotFoundException{
        entry_controller.poisk_position(id_user);

        id_pos = entry_controller.poisk_position(id_user);
        return id_pos;
    }
    public void poisk_fio (int id_user) throws SQLException, ClassNotFoundException{
        entry_controller.poisk_fio(id_user);

        text_fio = entry_controller.poisk_fio(id_user);

    }
}
