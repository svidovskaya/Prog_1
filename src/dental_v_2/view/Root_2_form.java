package dental_v_2.view;


import dental_v_2.controllers.Entry_Controller;
import dental_v_2.controllers.File_Controller;
import dental_v_2.mail.mail_properties;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;

public class Root_2_form extends JFrame{


    public Entry_form entry_form = new Entry_form();
    public Registration_form registration_form = new Registration_form();

    public Menu_form menu_form = new Menu_form();
    public Create_Client_form create_client_form = new Create_Client_form();
    public Create_product_form create_product_form = new Create_product_form();
    public Create_Manuf_form create_manuf_form = new Create_Manuf_form();
    public Create_Class_form create_class_form = new Create_Class_form();
    public Add_prod_in_stock_form add_prod_in_stock_form = new Add_prod_in_stock_form();
    public All_clients_form all_clients_form = new All_clients_form();
    public All_products_form all_products_form = new All_products_form();
    public Order_Create_form order_create_form = new Order_Create_form();
    public Order_product_form order_product_form = new Order_product_form();

    public File_Controller file_controller = new File_Controller();
    public History_Nakladnaya_form history_nakladnaya_form = new History_Nakladnaya_form();
    public History_Order_form history_order_form = new History_Order_form();






    public JPanel menu;
    public JPanel entry;

    public JPanel registation;
    public JPanel create_client;
    public JPanel create_product;
    public JPanel create_manuf;
    public JPanel create_class;
    public JPanel add_prod_in_st;
    public JPanel all_clients;
    public JPanel all_products;
    public JPanel create_order;
    public JPanel order_product;
    public JPanel history_nakl;
    public  JPanel history_orders;





    Entry_Controller controller = new Entry_Controller();
    public int id_user =0;
    public int id_pos =0;
    String text_fio ="";
    int pr = 0;
    String[] mail_danni;



    public Root_2_form(){

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

        registation = registration_form.newJPanel();


        menu = menu_form.newJPanel();
        create_client = create_client_form.newJPanel();
        create_product = create_product_form.newJPanel();
        create_manuf = create_manuf_form.newJPanel();
        create_class = create_class_form.newJPanel();
        add_prod_in_st = add_prod_in_stock_form.newJPanel();
        all_clients = all_clients_form.all_product_JPanel();
        all_products = all_products_form.all_product_JPanel();
        create_order = order_create_form.orderPanel();
        order_product = order_product_form.o_p_JPanel();
        history_nakl = history_nakladnaya_form.history_JPanel();
        history_orders = history_order_form.history_JPanel();





        panel.add(entry);
        panel.repaint();




//        entry_form.e_registration.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                panel.remove(entry);
//                panel.add(registation);
//                panel.repaint();
//            }
//        });

        registration_form.e_back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.remove(registation);
                panel.add(menu);
                panel.repaint();
            }
        });

        entry_form.e_entry.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            String login = entry_form.text_login.getText();
            String pass = entry_form.text_passw.getText();

                try {
                    entry_pr(login, pass);
                    System.out.println(pr);
if (pr==1){
    reg();
    panel.repaint();
}
if (pr == 0){
    entry_form.e_registration.setVisible(false);
    panel.repaint();
}


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
                        menu_form.b_create_user.setVisible(false);
                        menu_form.b_create_class.setVisible(false);
                        menu_form.b_create_manuf.setVisible(false);
                        menu_form.b_create_file.setVisible(false);

                        panel.repaint();
                        break;


                        default:

                            panel.remove(menu);
                            panel.add(entry);
                            panel.repaint();
//                            JOptionPane op = new JOptionPane();
//                            op.showMessageDialog(null, "Нет совпадений!");

                }


            }
        });

        menu_form.b_create_user.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.remove(menu);
                panel.add(registation);
                panel.repaint();
            }
        });
        create_client_form.b_back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.remove(create_client);
                panel.add(menu);
                panel.repaint();
            }
        });
        menu_form.b_create_client.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.remove(menu);
                panel.add(create_client);
                panel.repaint();
            }
        });
        menu_form.b_create_product.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.remove(menu);
                create_product_form.combo_manuf.setVisible(false);
create_product.repaint();
                try {

                    create_product_form.show_combo();
                    create_product_form.combo_manuf = new JComboBox(create_product_form.mass_manuf);


                    create_product_form.combo_manuf.setBounds(340, 10, 100, 30);
                    create_product_form.combo_manuf.setVisible(true);

                    create_product.add(create_product_form.combo_manuf);



                    create_product.repaint();

                    panel.add(create_product);

                    panel.repaint();

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            });

        create_product_form.b_back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.remove(create_product);
                panel.add(menu);
                panel.repaint();
            }
        });
        menu_form.b_create_manuf.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.remove(menu);
                panel.add(create_manuf);
                panel.repaint();
            }
        });
        create_manuf_form.b_back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.remove(create_manuf);
                panel.add(menu);
                panel.repaint();
            }
        });
        menu_form.b_create_class.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.remove(menu);
                panel.add(create_class);
                panel.repaint();
            }
        });
        create_class_form.b_back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.remove(create_class);
                panel.add(menu);
                panel.repaint();
            }
        });
        add_prod_in_stock_form.b_back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.remove(add_prod_in_st);
                panel.add(menu);
                panel.repaint();
            }
        });
        menu_form.b_add_prod_in_st.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.remove(menu);

                panel.add(add_prod_in_st);
                panel.repaint();
              //  add_prod_in_stock_form.user_id = id_user;
            }
        });
        menu_form.b_all_clients.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.remove(menu);

                all_clients.remove(all_clients_form.scrollPane);
                try {
                    all_clients_form.show();
                    all_clients_form.table_action = new  JTable(all_clients_form.mas_action,all_clients_form.head);
                    all_clients_form.table_action.setBounds(0, 100, 1300, 250);
                    all_clients.add(all_clients_form.table_action);
                    all_clients_form.scrollPane = new JScrollPane(all_clients_form.table_action);
                    all_clients_form.scrollPane.setBounds(150, 100, 1000, 300);
                    all_clients.add(all_clients_form.scrollPane);
                    all_clients.repaint();

                } catch (SQLException ex) {
                    ex.printStackTrace();
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
                panel.add(all_clients);
                panel.repaint();

                //  add_prod_in_stock_form.user_id = id_user;
            }
        });
        all_clients_form.b_back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.remove(all_clients);
                panel.add(menu);
                panel.repaint();
            }
        });
        menu_form.b_show_prod.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.remove(menu);

                all_products.remove(all_products_form.scrollPane);
                try {
                    all_products_form.show();
                    all_products_form.table_action = new  JTable(all_products_form.mas_action,all_products_form.head);
                    all_products_form.table_action.setBounds(0, 100, 1300, 250);
                    all_products.add(all_products_form.table_action);
                    all_products_form.scrollPane = new JScrollPane(all_products_form.table_action);
                    all_products_form.scrollPane.setBounds(150, 100, 1000, 300);
                    all_products.add(all_products_form.scrollPane);
                    all_products.repaint();

                } catch (SQLException ex) {
                    ex.printStackTrace();
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
                panel.add(all_products);
                panel.repaint();

                //  add_prod_in_stock_form.user_id = id_user;
            }
        });
        all_products_form.b_back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.remove(all_products);
                panel.add(menu);
                panel.repaint();
            }
        });
        menu_form.b_create_order.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.remove(menu);

                order_create_form.fio = text_fio;
                order_create_form.user_id = id_user;

                order_create_form.b_next.setVisible(true);
                order_create_form.l_summa.setVisible(false);
                order_create_form.b_add_tttt.setVisible(false);
                order_create_form.t_search_1.setVisible(false);
                order_create_form.l_pr.setVisible(false);
                order_create_form.l_pr_s.setVisible(false);
                order_create_form.t_search.setVisible(false);
                order_create_form.img_s.setVisible(false);
                order_create_form.b_add_t.setVisible(false);
                order_create_form.c_prod.setVisible(false);
                order_create_form.l_summa.setVisible(false);
                order_create_form.c_kolvo.setVisible(false);
                panel.add(create_order);
                panel.repaint();
            }
        });
        order_create_form.b_back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.remove(create_order);



                panel.add(menu);
                order_create_form.l_pr_s.setVisible(false);
                order_create_form.l_pr.setVisible(false);
                panel.repaint();
            }
        });
        order_create_form.b_add_tttt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.remove(create_order);
                order_product.remove(order_product_form.scrollPane);
                order_product.remove(order_create_form.l_summa);
                try {
                    order_product_form.id_user = id_user;
                    order_product_form.id_order = order_create_form.id_order;
                    order_product_form.show(order_product_form.id_order);
                    order_product_form.table_action = new  JTable(order_product_form.mas_action,order_product_form.head);
                    order_product_form.table_action.setBounds(0, 100, 1300, 250);
                    order_product.add(order_product_form.table_action);
                    order_product_form.scrollPane = new JScrollPane(order_product_form.table_action);
                    order_product_form.scrollPane.setBounds(150, 100, 1000, 300);
                    order_product.add(order_product_form.scrollPane);
                    order_product_form.sum = order_product_form.poisk_summa(order_product_form.id_order);
                    order_product_form.l_sum = new JLabel("Сумма заказа: " + order_product_form.sum + " грн");
                    order_product_form.l_sum.setBounds(10, 30, 300, 40);
                    order_product.add(order_product_form.l_sum);

                    order_product.repaint();

                } catch (SQLException ex) {
                    ex.printStackTrace();
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
                panel.add(order_product);
                panel.repaint();
            }
        });
        order_product_form.b_back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.remove(order_product);
                panel.add(create_order);
                order_product.remove(order_product_form.l_sum);
                order_product.repaint();
                try {

                    create_order.remove(order_create_form.l_summa);
                   order_create_form.poisk_summa(order_product_form.id_order);
                    create_order.add(order_create_form.l_summa = new JTextField(order_create_form.summa + " грн"));
                    order_create_form.l_summa.setBounds(650, 380, 100, 50);
                    create_order.add(order_create_form.l_summa);
                    create_order.repaint();

                } catch (SQLException ex) {
                    ex.printStackTrace();
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
                panel.repaint();
            }
        });
//        order_product_form.b_update.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                order_product.remove(order_create_form.l_summa);
//                try {
//                    order_product_form.id_user = id_user;
//                    order_product_form.id_order = order_create_form.id_order;
//                    order_product_form.show(order_product_form.id_order);
//                    order_product_form.table_action = new  JTable(order_product_form.mas_action,order_product_form.head);
//                    order_product_form.table_action.setBounds(0, 100, 1300, 250);
//                    order_product.add(order_product_form.table_action);
//                    order_product_form.scrollPane = new JScrollPane(order_product_form.table_action);
//                    order_product_form.scrollPane.setBounds(150, 100, 1000, 300);
//                    order_product.add(order_product_form.scrollPane);
//                    order_product_form.sum = order_product_form.poisk_summa(order_product_form.id_order);
//                    order_product_form.l_sum = new JLabel("Сумма заказа: " + order_product_form.sum + " грн");
//                    order_product_form.l_sum.setBounds(10, 30, 300, 40);
//                    order_product.add(order_product_form.l_sum);
//
//                    order_product.repaint();
//
//                } catch (SQLException ex) {
//                    ex.printStackTrace();
//                } catch (ClassNotFoundException ex) {
//                    ex.printStackTrace();
//                }
//                panel.add(order_product);
//                panel.repaint();
//            }
//        });
//        order_product_form.b_del.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//
//                panel.repaint();
//            }
//        });
        menu_form.b_create_file.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //poisk_danni();
                try {
                    poisk_danni_1();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }


                //  panel.print(menu.getGraphics());
            }
        });


        menu_form.b_history_nakl.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.remove(menu);
                history_nakl.remove(history_nakladnaya_form.scrollPane);
                try {
                    history_nakladnaya_form.show("");
                    history_nakladnaya_form.table_action = new  JTable(history_nakladnaya_form.mas_action, history_nakladnaya_form.head);
                    history_nakladnaya_form.table_action.setBounds(0, 100, 1300, 250);
                    history_nakl.add(history_nakladnaya_form.table_action);
                    history_nakladnaya_form.scrollPane = new JScrollPane(history_nakladnaya_form.table_action);
                    history_nakladnaya_form.scrollPane.setBounds(150, 100, 1000, 300);
                    history_nakl.add(history_nakladnaya_form.scrollPane);
                    history_nakl.repaint();

                } catch (SQLException ex) {
                    ex.printStackTrace();
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
                panel.add(history_nakl);

                panel.repaint();
            }
        });
        history_nakladnaya_form.b_back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.remove(history_nakl);
                panel.add(menu);
                panel.repaint();
            }
        });
        menu_form.b_history_order.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.remove(menu);
                history_orders.remove(history_order_form.scrollPane);
                try {
                    history_order_form.id_user=id_user;
                    history_order_form.show("");
                    history_order_form.table_action = new  JTable(history_order_form.mas_action, history_order_form.head);
                    history_order_form.table_action.setBounds(0, 100, 1300, 250);
                    history_orders.add(history_order_form.table_action);
                    history_order_form.scrollPane = new JScrollPane(history_order_form.table_action);
                    history_order_form.scrollPane.setBounds(150, 100, 1000, 300);
                    history_orders.add(history_order_form.scrollPane);
                    history_orders.repaint();

                } catch (SQLException ex) {
                    ex.printStackTrace();
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
                panel.add(history_orders);

                panel.repaint();
            }
        });
        history_order_form.b_back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.remove(history_orders);
                panel.add(menu);
                panel.repaint();
            }
        });

        entry_form.e_registration.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String login = entry_form.text_login.getText();
                try {
                    poisk_mail(login);

                    String p = mail_danni[0];
                    String m = mail_danni[1];
                    String n = mail_danni[2];
                    mail_properties.sendMail(m, n, p, 1, "");
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });


history_order_form.b_open.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        int id_order = history_order_form.poisk_id_order();
        order_create_form.id_order = history_order_form.poisk_id_order();
        panel.remove(history_orders);
      //  System.out.println(id_order);
        order_product.remove(order_product_form.scrollPane);
                order_product.remove(order_create_form.l_summa);
                try {
                    order_product_form.id_user = id_user;

                    order_product_form.id_order = history_order_form.id_order;
                    order_product_form.show(order_product_form.id_order);
                    System.out.println(order_product_form.id_order + "mclkdsmclds");
                    order_product_form.table_action = new  JTable(order_product_form.mas_action,order_product_form.head);
                    order_product_form.table_action.setBounds(0, 100, 1300, 250);
                    order_product.add(order_product_form.table_action);
                    order_product_form.scrollPane = new JScrollPane(order_product_form.table_action);
                    order_product_form.scrollPane.setBounds(150, 100, 1000, 300);
                    order_product.add(order_product_form.scrollPane);
                    order_product_form.sum = order_product_form.poisk_summa(order_product_form.id_order);
                    order_product_form.l_sum = new JLabel("Сумма заказа: " + order_product_form.sum + " грн");
                    order_product_form.l_sum.setBounds(10, 30, 300, 40);
                    order_product.add(order_product_form.l_sum);

                    order_product.repaint();


                } catch (SQLException ex) {
                    ex.printStackTrace();
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
        panel.add(order_product);
        panel.repaint();
    }

});


        setContentPane(panel);
    }

    public int entry_user (String login, String pass) throws SQLException, ClassNotFoundException{


        id_user = controller.entry_user(login, pass);
        return id_user;

    }
    public int poisk_position (int id_user) throws SQLException, ClassNotFoundException{


        id_pos = controller.poisk_position(id_user);
        return id_pos;
    }
    public void poisk_fio (int id_user) throws SQLException, ClassNotFoundException{


        text_fio = controller.poisk_fio(id_user);

    }
    public String[][] poisk_danni(){
        return file_controller.poisk_danni();
    }

    public String[][] poisk_danni_1() throws IOException {
        return file_controller.poisk_danni_1();
    }

    public void reg(){
        entry_form.e_registration.setVisible(true);

    }
    public int entry_pr (String login, String pass) throws SQLException, ClassNotFoundException{

        pr = controller.entry_pr(login, pass);
        return pr;

    }

    public String[] poisk_mail(String login) throws SQLException, ClassNotFoundException{

        mail_danni = controller.poisk_mail(login);
        return mail_danni;

    }



}
