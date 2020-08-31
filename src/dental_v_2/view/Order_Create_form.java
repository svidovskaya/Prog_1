package dental_v_2.view;


import dental_v_2.controllers.Create_Orders_Controller;

import javax.swing.*;
import java.awt.event.*;
import java.sql.SQLException;

public class Order_Create_form {


    Create_Orders_Controller controller = new Create_Orders_Controller();
    Order_product_form order_product_form = new Order_product_form();

    int id_order = 0;
    int chislo_proverka = 0;
    int user_id = 0;
    String fio = "";

    public JButton b_next = new JButton("Создать");
    public JButton b_back = new JButton("Назад");
    public JButton b_client = new JButton("Создать клиента");
    public JButton b_order =  new JButton("+++");
    JTextField t_search_1 = new JTextField("Поиск товара по коду...");
    JButton b_add_tttt= new JButton("Просмотр");
    ImageIcon image_2 = new ImageIcon("C:\\Users\\я\\Desktop\\Курсовая\\Prog_1\\src\\prog_4\\img\\info.png");
    JButton img_s = new JButton(image_2);
    JButton b_add_t= new JButton("Добавить в заказ");






    public String[] mass_clas;
    public String[] mass_clients;
    public String[] mass_deliv;
    public String[] mass_platforms;
    public String[] mass_prod = {""};
    public JTextField c_kolvo = new JTextField("1");
    public JPanel order_product;



    String clas = "";
    String poisk = "";
    String poisk1 = "";
    String[] pr = new String[3];
    String pr_s="";
    JTextField l_pr= new JTextField(pr[1]);
    JTextField l_pr_s= new JTextField(pr[2]);
    JTextField t_search = new JTextField("Поиск товара....");


    String summa = "0";
    JTextField l_summa = new JTextField(summa + " грн");
    JComboBox c_prod = new JComboBox();


    public JPanel orderPanel(){
        try {
            show_combo();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            show_2("");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(0, 120, 1300, 700);


        b_next.setBounds(650, 380, 150, 50);
        b_next.setVisible(true);
        panel.add(b_next);
        b_back.setBounds(450, 380, 150, 50);
        panel.add(b_back);
        b_client.setBounds(850, 380, 150, 50);
        b_client.setVisible(false);
        panel.add(b_client);

        b_order.setBounds(850, 380, 150, 50);
        b_order.setVisible(false);
        panel.add(b_order);


        JLabel l_platforms = new JLabel("Площадка:");
        l_platforms.setBounds(370, 0, 100, 20);
        panel.add(l_platforms);
        JLabel l_clas = new JLabel("Класс клиента:");
        l_clas.setBounds(490, 0, 100, 20);
        panel.add(l_clas);
        JLabel l_fio = new JLabel("ФИО клиента:");
        l_fio.setBounds(610, 0, 100, 20);
        panel.add(l_fio);
        JLabel l_deliv = new JLabel("Доставка:");
        l_deliv.setBounds(830, 0, 100, 20);
        panel.add(l_deliv);
        l_summa.setBounds(650, 380, 100, 50);
        panel.add(l_summa);

order_product = order_product_form.o_p_JPanel();

        JComboBox c_platforms = new JComboBox(mass_platforms);
        c_platforms.setBounds(370, 20, 100, 30);
        JComboBox c_class = new JComboBox(mass_clas);
        c_class.setBounds(490, 20, 100, 30);
        JComboBox c_clients = new JComboBox(mass_clients);
        c_clients.setBounds(610, 20, 200, 30);
        JComboBox c_delivery = new JComboBox(mass_deliv);
        c_delivery.setBounds(830, 20, 100, 30);

        t_search.setBounds(15, 70, 190, 30);
        panel.add(t_search);
        t_search.setVisible(false);
        t_search_1.setBounds(15, 35, 190, 30);
        t_search_1.setVisible(false);
        panel.add(t_search_1);




        img_s.setBounds(220, 70, 30, 30);
        panel.add(img_s);
        img_s.setVisible(false);

        c_prod = new JComboBox(mass_prod);
        c_prod.setBounds(370, 70, 510, 30);
        panel.add(c_prod);
        c_prod.setVisible(false);


        c_kolvo.setBounds(900, 70, 100, 30);
        panel.add(c_kolvo);
        c_kolvo.setVisible(false);

        b_add_t.setBounds(1020, 70, 150, 30);
        panel.add(b_add_t);
        b_add_t.setVisible(false);

        b_add_tttt.setBounds(1020, 35, 150, 30);
        b_add_tttt.setVisible(false);
        panel.add(b_add_tttt);




        panel.add(c_platforms);
        panel.add(c_class);
        panel.add(c_clients);
        panel.add(c_delivery);

        c_class.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               clas = (String) c_class.getSelectedItem();
                try {
                    show(clas);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                }

                int qw = c_clients.getItemCount();
                for(int i = 0; qw > i; i++){
                    c_clients.removeItemAt(0);
                }
                for (int i = 0; i < mass_clients.length; i++){
                    c_clients.addItem(mass_clients[i]);
                }
            }
        });

        c_prod.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String product = (String) c_prod.getSelectedItem();
                try {
                    poisk_pr(product);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
                panel.remove(l_pr);
                panel.remove(l_pr_s);
                l_pr= new JTextField(pr[1]);
                l_pr.setBounds(260, 70, 40, 30);
                panel.add(l_pr);
                l_pr_s = new JTextField(pr[2]);
                l_pr_s.setBounds(310, 70,40 , 30);
                panel.add(l_pr_s);
                try {
                    poisk_summa(id_order);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
                panel.remove(l_summa);

                panel.repaint();

                System.out.println(summa + "cndskncl");
                JTextField l_summa = new JTextField(summa + " грн");
                l_summa.setBounds(650, 380, 100, 50);

                panel.add(l_summa);
                panel.repaint();

            }
        });

        t_search.addMouseListener(new MouseAdapter(){
            public void mouseClicked (MouseEvent event){
                t_search.setText(null);

            }
        });

        t_search_1.addMouseListener(new MouseAdapter(){
            public void mouseClicked (MouseEvent event){
                t_search_1.setText(null);

            }
        });

        t_search_1.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent evt) {
                BarcodeKeyPressed(evt);
                poisk1 = t_search_1.getText();

                try {
                    show3(poisk1);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
                int qw = c_prod.getItemCount();
                for(int i = 0; qw > i; i++){
                    c_prod.removeItemAt(0);
                }
                for (int i = 0; i < mass_prod.length; i++){
                    c_prod.addItem(mass_prod[i]);
                    //      System.out.println(mass_prod[i]);
                }

                poisk1 = "";


            }

        });
        img_s.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                poisk = t_search.getText();
                try {
                    show_2(poisk);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
                int qw = c_prod.getItemCount();
                for(int i = 0; qw > i; i++){
                    c_prod.removeItemAt(0);
                }
                for (int i = 0; i < mass_prod.length; i++){
                    c_prod.addItem(mass_prod[i]);
                    System.out.println(mass_prod[i]);
                }


            }
        });
        b_next.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                t_search.setVisible(true);
                t_search_1.setVisible(true);
                img_s.setVisible(true);
                c_prod.setVisible(true);
                c_kolvo.setVisible(true);
                b_add_t.setVisible(true);
                b_add_tttt.setVisible(true);
                l_summa.setVisible(true);


                String platforms = (String) c_platforms.getSelectedItem();
                String client = (String) c_clients.getSelectedItem();
                String deliv = (String) c_delivery.getSelectedItem();


                try {
                    create_o(client,deliv, platforms, fio, user_id);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                }

                b_next.setVisible(false);
            }
        });
        b_add_tttt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                order_product_form.id_order = id_order;

            }
        });

        b_add_t.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String product = (String) c_prod.getSelectedItem();
               // System.out.println(product);
                int amount = Integer.parseInt(c_kolvo.getText());
              //  panel.remove(l_summa);
               // panel.repaint();

                try {
                    proverka(product);

                    //System.out.println(chislo_proverka);
//                    if (amount>0 && amount<=chislo_proverka){
//                        app(product, amount);
//                   //     app_stock(product, amount);
//                    } else {
//                        JOptionPane op = new JOptionPane();
//                        op.showMessageDialog(null, "Кол-во превышает остатки! На складе '"+chislo_proverka+"' шт!");
//                    }
                    clas = (String) c_class.getSelectedItem();
                    app(product,amount, clas);


                } catch (SQLException ex) {
                    ex.printStackTrace();
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                }

                try {
                    poisk_summa(id_order);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
                panel.remove(l_summa);

                panel.repaint();

                System.out.println(summa + "cndskncl");
                JTextField l_summa = new JTextField(summa + " грн");
                l_summa.setBounds(650, 380, 100, 50);

                panel.add(l_summa);
                panel.repaint();



            }
        });





        return panel;
    }
    public void show_combo()throws SQLException, ClassNotFoundException{

        mass_platforms = controller.combo_platforms();


        mass_clas = controller.combo_class();


        mass_clients = controller.combo_clients(clas);


        mass_deliv = controller.combo_deliv();


    }
    public void show(String clas) throws SQLException, ClassNotFoundException{

        mass_clients = controller.combo_clients(clas);

    }
    public void show_2(String poisk) throws SQLException, ClassNotFoundException{

        mass_prod = controller.combo_prod(poisk);

    }
    public void poisk_pr (String product) throws SQLException, ClassNotFoundException{
        pr = controller.poisk_pr(product);
    }

    public void create_o (String client, String delivery, String platform, String fio, int user_id) throws SQLException, ClassNotFoundException{
        id_order = controller.create_order(client,delivery, platform, fio, user_id);
    }

    public void app (String product, int count, String clas) throws SQLException, ClassNotFoundException{
        controller.app_product(product, count, id_order, clas);
     //   summa = controller.app_product(product,count, id_order,clas);

    }

    public void poisk_summa (int id_order) throws SQLException, ClassNotFoundException{
       summa = controller.poisk_s(id_order);


    }


//    public void app_stock (String product, int count) throws SQLException, ClassNotFoundException{
//        controller.app_stock_product(product, count);
//    }
    public void proverka (String product) throws SQLException, ClassNotFoundException{

        chislo_proverka = controller.proverka(product);
    }

    public void show3(String poisk1) throws SQLException, ClassNotFoundException{
        mass_prod = controller.combo_prod_1(poisk1);
    }

//    public void generat(String manuf, String vid_t, String vid_a, String ploshadka, String clas, String price_q) throws SQLException, ClassNotFoundException{
//        res = controller.generat(manuf, vid_t, ploshadka, price_q);
//    }
public void BarcodeKeyPressed(KeyEvent evt){
    if(evt.getKeyCode()== KeyEvent.VK_ENTER){

    }
}

}
