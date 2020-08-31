package dental_v_2.view;


import dental_v_2.controllers.Create_product_Controller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

public class Create_product_form {

    Create_product_Controller controller = new Create_product_Controller();
    JButton b_back = new JButton("Назад");
    public String mass_manuf[];
    public JComboBox combo_manuf = new JComboBox();


    public JPanel newJPanel(){

        try {
            show_combo();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(0, 200, 1300, 600);

        JButton b_create = new JButton("Создать");
        b_create.setBounds(700, 300, 150, 50);
        b_create.setVisible(true);
        panel.add(b_create);
        b_back.setBounds(450, 300, 150, 50);
        panel.add(b_back);


        JTextField text_name = new JTextField("Название товара*");
        text_name.setBounds(450, 10, 300, 30);
        panel.add(text_name);

        JTextField text_price = new JTextField("Цена*");

        text_price.setBounds(760, 10, 100, 30);
        panel.add(text_price);


        JTextField text_price_stom = new JTextField("Цена стоматолога*");
        text_price_stom.setBounds(870, 10, 150, 30);
        panel.add(text_price_stom);

        combo_manuf = new JComboBox(mass_manuf);
        combo_manuf.setBounds(340, 10, 100, 30);

        panel.add(combo_manuf);
        JTextField text_shtrih = new JTextField("Штрих-код");
        text_shtrih.setBounds(230, 10, 100, 30);
        panel.add(text_shtrih);
        JTextField text_code = new JTextField("Kод");
        text_code.setBounds(120, 10, 100, 30);
        panel.add(text_code);
        JTextField text_articul = new JTextField("Артикул");
        text_articul.setBounds(10, 10, 100, 30);
        panel.add(text_articul);


        text_name.addMouseListener(new MouseAdapter(){
            public void mouseClicked (MouseEvent event){
                text_name.setText(null);

            }
        });
        text_price.addMouseListener(new MouseAdapter(){
            public void mouseClicked (MouseEvent event){
                text_price.setText(null);

            }
        });
        text_price_stom.addMouseListener(new MouseAdapter(){
            public void mouseClicked (MouseEvent event){
                text_price_stom.setText(null);

            }
        });
        text_shtrih.addMouseListener(new MouseAdapter(){
            public void mouseClicked (MouseEvent event){
                text_shtrih.setText(null);

            }
        });
        text_code.addMouseListener(new MouseAdapter(){
            public void mouseClicked (MouseEvent event){
                text_code.setText(null);

            }
        });
        text_articul.addMouseListener(new MouseAdapter(){
            public void mouseClicked (MouseEvent event){
                text_articul.setText(null);

            }
        });


        b_create.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int a = 0;
                String name = text_name.getText();
                String manuf = (String) combo_manuf.getSelectedItem();
                float price = 0;
                if (text_price.getText().equals("Цена*")){
                    JOptionPane op = new JOptionPane();
                    op.showMessageDialog(null, "Введите корректную цену");
                    a = 1;
                } else {
                price = Float.parseFloat(text_price.getText());
                a = 0;}

                float price_stom = 0;
                if (text_price_stom.getText().equals("Цена стоматолога*")){
                    JOptionPane op = new JOptionPane();
                    op.showMessageDialog(null, "Введите корректную цену стоматолога");
                    a=1;
                } else { price_stom = Float.parseFloat(text_price_stom.getText()); a=0;}
                String shtrih = text_shtrih.getText();
                if (shtrih.equals("Штрих-код")){
                    shtrih = "";
                } else {shtrih = text_shtrih.getText();}
                String code = text_code.getText();
                if (code.equals("Kод")){
                    code = "";
                } else {code = text_code.getText();}
                String articul = text_articul.getText();
                if (articul.equals("Артикул")){
                    articul = "";
                } else {articul = text_articul.getText();}



                try {
                    if (a==0){
                        create_p(manuf, name, price, price_stom, shtrih, code, articul);

                    }
                } catch (SQLException ex) {

                    ex.printStackTrace();
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
            }
        });

        return panel;
    }

    public void show_combo()throws SQLException, ClassNotFoundException{
        mass_manuf = controller.combo_manuf();


    }
    public void create_p (String manuf, String name, float price, float price_stom, String shtrih, String code, String articul) throws SQLException, ClassNotFoundException{
        controller.create_product(manuf, name, price, price_stom, shtrih, code, articul);
    }


}
