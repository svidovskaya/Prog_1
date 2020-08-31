package dental_v_2.view;


import dental_v_2.controllers.Client_Combo_Controller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Create_Client_form {

    JButton b_back = new JButton("Назад");
    String mass_class[];
    Client_Combo_Controller controller = new Client_Combo_Controller();

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
        b_back.setBounds(480, 300, 150, 50);
        panel.add(b_back);


        JTextField text_surname = new JTextField("Фамилия*");
        text_surname.setBounds(515, 45, 300, 30);
        panel.add(text_surname);
        JTextField text_name = new JTextField("Имя*");
        text_name.setBounds(515, 80 , 300, 30);
        panel.add(text_name);
        JTextField text_middle = new JTextField("Отчество");
        text_middle.setBounds(515, 115, 300, 30);
        panel.add(text_middle);
        JTextField text_phone = new JTextField("Номер телефона*");
        text_phone.setBounds(515, 150, 300, 30);
        panel.add(text_phone);
        JTextField text_mail = new JTextField("e-mail");
        text_mail.setBounds(515, 195, 300, 30);
        panel.add(text_mail);
        JTextField text_comment = new JTextField("Комментарий");
        text_comment.setBounds(515, 235, 300, 30);
        panel.add(text_comment);


        JComboBox combo_class = new JComboBox(mass_class);
        combo_class.setBounds(625, 0, 100, 30);
        panel.add(combo_class);


        text_name.addMouseListener(new MouseAdapter(){
            public void mouseClicked (MouseEvent event){
                text_name.setText(null);

            }
        });

        text_surname.addMouseListener(new MouseAdapter(){
            public void mouseClicked (MouseEvent event){
                text_surname.setText(null);

            }
        });
        text_middle.addMouseListener(new MouseAdapter(){
            public void mouseClicked (MouseEvent event){
                text_middle.setText(null);

            }
        });
        text_phone.addMouseListener(new MouseAdapter(){
            public void mouseClicked (MouseEvent event){
                text_phone.setText(null);

            }
        });
        text_mail.addMouseListener(new MouseAdapter(){
            public void mouseClicked (MouseEvent event){
                text_mail.setText(null);

            }
        });
        text_comment.addMouseListener(new MouseAdapter(){
            public void mouseClicked (MouseEvent event){
                text_comment.setText(null);

            }
        });

        b_create.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = text_name.getText();
                String surname = text_surname.getText();
                String middle = text_middle.getText();
                String phone = text_phone.getText();
                String mail = text_mail.getText();
                String comment = text_comment.getText();

                String clas = (String)combo_class.getSelectedItem();

                if (name.length()<3 || name.length()>45){
                    JOptionPane op = new JOptionPane();
                    op.showMessageDialog(null, "Имя должно содержать от 3 до 45 символов!");
                }
                if (surname.length()<3 || surname.length()>45){
                    JOptionPane op = new JOptionPane();
                    op.showMessageDialog(null, "Фамилия должна содержать от 3 до 45 символов!");
                }
              if ( phone.length()!=11){
                    JOptionPane op = new JOptionPane();
                    op.showMessageDialog(null, "Номер телефона должен содержать 11 символов!");
                }
                Pattern pattern = Pattern.compile("^((\\w|[-+])+(\\.[\\w-]+)*@[\\w-]+((\\.[\\d\\p{Alpha}]+)*(\\.\\p{Alpha}{2,})*)*)$");
                Matcher matcher = pattern.matcher(mail);

                int a=0;

                while (matcher.find()){
                    a=1;
                }
                if(a==0){
                    JOptionPane op = new JOptionPane();
                    op.showMessageDialog(null, "Введите верный email");
                } else {
                    try {
                        int c = proverka(surname, name, middle, phone, mail, comment, clas);
                        if (c == 0) {
                            try {
                                create_cl(surname, name, middle, phone, mail, comment, clas);
                            } catch (SQLException ex) {
                                ex.printStackTrace();
                            } catch (ClassNotFoundException ex) {
                                ex.printStackTrace();
                            }
                        } else {
                            JOptionPane op = new JOptionPane();
                            op.showMessageDialog(null, "Пользователь с таким номером телефона существует!");
                        }
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    } catch (ClassNotFoundException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });

        return panel;
    }

    public void show_combo()throws SQLException, ClassNotFoundException{
        mass_class = controller.combo_class();
    }
    public void create_cl ( String surname, String name, String middle, String phone, String mail, String comment, String clas) throws SQLException, ClassNotFoundException{
        controller.create_client(surname, name, middle, phone, mail, comment, clas);
    }
    public int proverka (String surname, String name, String middle, String phone, String mail, String comment, String clas) throws SQLException, ClassNotFoundException{
     int  c = controller.proverka_client(surname, name, middle, phone, mail, comment, clas);
        return c;
    }
}
