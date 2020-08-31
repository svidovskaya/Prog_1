package dental_v_2.view;


import dental_v_2.controllers.Prod_in_Stock_Combo_Controller;

import javax.swing.*;
import java.awt.event.*;
import java.sql.SQLException;


public class Add_prod_in_stock_form {


    public JButton b_back = new JButton("Назад");
    public JButton b_add = new JButton("Добавить");
    JTextField t_search = new JTextField("Поиск товара....");
    JTextField t_search_1 = new JTextField("Поиск товара по коду...");

    Prod_in_Stock_Combo_Controller controller = new Prod_in_Stock_Combo_Controller();




    public String[] mass_sklad;
    public String[] mass_prod = {""};
//    public Integer[] mass_kolvo = {
//            1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,
//            50,51,52,53,54,55,56,57,58,59,60,61,62,63,64,65,66,67,68,69,70,71,72,73,74,75,76,77,78,79,80,81,82,83,84,85,86,87,88,89,90,91,92,93,94,95,96,97,98,99,100
//    };

    String poisk = "";
    String poisk1 = "";
    int user_id=0;



    public JPanel newJPanel(){

        try {
            show_2("");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(0, 200, 1300, 600);


        b_back.setBounds(450, 300, 150, 50);
        panel.add(b_back);
        b_add.setBounds(700, 300, 150, 50);
        panel.add(b_add);


        t_search.setBounds(15, 70, 200, 30);
        panel.add(t_search);
        t_search_1.setBounds(15, 35, 200, 30);
        panel.add(t_search_1);

//        ImageIcon image_2 = new ImageIcon("C:\\Users\\я\\Desktop\\Курсовая\\Prog_1\\src\\prog_4\\img\\info.png");
//        JButton img_s = new JButton(image_2);
//        img_s.setBounds(220, 70, 30, 30);
//        panel.add(img_s);
//
//        JButton img_s_1 = new JButton(image_2);
//        img_s_1.setBounds(220, 35, 30, 30);
//        panel.add(img_s_1);


        JLabel l_1 = new JLabel("Название товара: ");
        l_1.setBounds(350, 40, 510,  30);
        panel.add(l_1);

        JComboBox c_prod = new JComboBox(mass_prod);
        c_prod.setBounds(350, 70, 510, 30);
        panel.add(c_prod);

        JLabel l_2 = new JLabel("Количество: ");
        l_2.setBounds(880, 40,100,  30);
        panel.add(l_2);

        JTextField c_kolvo = new JTextField("1");
        c_kolvo.setBounds(880, 70, 100, 30);
        panel.add(c_kolvo);



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

        t_search.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent evt) {
                BarcodeKeyPressed(evt);
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
        t_search_1.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent evt) {
                BarcodeKeyPressed(evt);
                poisk1 = t_search_1.getText();

                try {
                    show(poisk1);
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
   /*
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
*/
        b_add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String product = (String) c_prod.getSelectedItem();
                int amount = Integer.parseInt(c_kolvo.getText());


                try {
                    app(product, amount);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                }

            }
        });




        return panel;
    }



    public void show_2(String poisk) throws SQLException, ClassNotFoundException{
        mass_prod = controller.combo_prod(poisk);
    }
    public void show(String poisk1) throws SQLException, ClassNotFoundException{
        mass_prod = controller.combo_prod_1(poisk1);
    }

    public void app (String product, int count) throws SQLException, ClassNotFoundException{
        controller.app_product(product, count);
    }
    public void BarcodeKeyPressed(KeyEvent evt){
        if(evt.getKeyCode()== KeyEvent.VK_ENTER){

        }
    }

}
