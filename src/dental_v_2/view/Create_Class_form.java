package dental_v_2.view;


import dental_v_2.controllers.Create_Class_Controller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

public class Create_Class_form {
    JButton b_back = new JButton("Назад");
    String mass_class[];
    Create_Class_Controller controller = new Create_Class_Controller();

    public JPanel newJPanel(){


        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(0, 200, 1300, 600);

        JButton b_create = new JButton("Создать");
        b_create.setBounds(700, 300, 150, 50);
        b_create.setVisible(true);
        panel.add(b_create);
        b_back.setBounds(480, 300, 150, 50);
        panel.add(b_back);


        JTextField text_name = new JTextField("Название*");
        text_name.setBounds(515, 45, 300, 30);
        panel.add(text_name);
        JTextField text_country = new JTextField("Коммент");
        text_country.setBounds(515, 80 , 300, 30);
        panel.add(text_country);





        text_name.addMouseListener(new MouseAdapter(){
            public void mouseClicked (MouseEvent event){
                text_name.setText(null);

            }
        });

        text_country.addMouseListener(new MouseAdapter(){
            public void mouseClicked (MouseEvent event){
                text_country.setText(null);

            }
        });

        b_create.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = text_name.getText();
                String country = text_country.getText();
                if (country.equals("Коммент")){
                    country = "";
                } else {
                    country = text_country.getText();
                }

                try {
                    create_manuf(name, country);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
            }
        });

        return panel;
    }


    public void create_manuf ( String name, String country) throws SQLException, ClassNotFoundException{
        controller.create_class(name, country);
    }
}
