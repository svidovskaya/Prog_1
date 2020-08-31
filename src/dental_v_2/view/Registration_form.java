package dental_v_2.view;


import dental_v_2.controllers.User_Combo_Controller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Registration_form {
    public JButton e_entry = new JButton("Создать");
    public JButton e_back = new JButton("Назад");

    String mass_pos[];
    User_Combo_Controller controller = new User_Combo_Controller();

    public JPanel newJPanel(){
        try {
            show_positions();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(0, 200, 1300, 600);

        e_entry.setBounds(700, 325, 150, 50);
        e_entry.setVisible(true);
        panel.add(e_entry);

        e_back.setBounds(500, 325, 150, 50);
        e_back.setVisible(true);
        panel.add(e_back);

        JTextField text_surname = new JTextField("Фамилия*");
        text_surname.setBounds(515, 45, 300, 30);
        panel.add(text_surname);
        JTextField text_name = new JTextField("Имя*");
        text_name.setBounds(515, 80 , 300, 30);
        panel.add(text_name);
        JTextField text_middle = new JTextField("Отчество*");
        text_middle.setBounds(515, 115, 300, 30);
        panel.add(text_middle);
        JTextField text_phone = new JTextField("Номер телефона*");
        text_phone.setBounds(515, 150, 300, 30);
        panel.add(text_phone);
        JTextField text_mail = new JTextField("e-mail");
        text_mail.setBounds(515, 195, 300, 30);
        panel.add(text_mail);
        JTextField text_login = new JTextField("Логин*");
        text_login.setBounds(515, 235, 300, 30);
        panel.add(text_login);
        JTextField text_pass = new JTextField("Пароль*");
        text_pass.setBounds(515, 280, 300, 30);
        panel.add(text_pass);



        JComboBox combo_positions = new JComboBox(mass_pos);
        combo_positions.setBounds(625, 0, 100, 30);
        panel.add(combo_positions);

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

        text_mail.addMouseListener(new MouseAdapter(){
            public void mouseClicked (MouseEvent event){
                text_mail.setText(null);

            }
        });
        text_login.addMouseListener(new MouseAdapter(){
            public void mouseClicked (MouseEvent event){
                text_login.setText(null);

            }
        });
        text_pass.addMouseListener(new MouseAdapter(){
            public void mouseClicked (MouseEvent event){
                text_pass.setText(null);

            }
        });



        e_entry.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = text_name.getText();
                String surname = text_surname.getText();
                String middle = text_middle.getText();
            //    int phone = Integer.parseInt(text_phone.getText());
                String ph = text_phone.getText();
                String mail = text_mail.getText();
                String login = text_login.getText();
                String pass = text_pass.getText();

                String pos = (String)combo_positions.getSelectedItem();
if (name.length()<3 || name.length()>45){
    JOptionPane op = new JOptionPane();
    op.showMessageDialog(null, "Имя должно содержать от 3 до 45 символов!");
}
if (surname.length()<3 || surname.length()>45){
                    JOptionPane op = new JOptionPane();
                    op.showMessageDialog(null, "Фамилия должна содержать от 3 до 45 символов!");
}
if (middle.length()<3 || middle.length()>45){
                    JOptionPane op = new JOptionPane();
                    op.showMessageDialog(null, "Отчество должно содержать от 3 до 45 символов!");
}if ( ph.length()!=11){
                    JOptionPane op = new JOptionPane();
                    op.showMessageDialog(null, "Номер телефона должен содержать 11 символов!");
                }if (login.length()<3 || login.length()>45){
                    JOptionPane op = new JOptionPane();
                    op.showMessageDialog(null, "Логин должен содержать от 3 до 45 символов!");
                }if (pass.length()<3 || pass.length()>45){
                    JOptionPane op = new JOptionPane();
                    op.showMessageDialog(null, "Пароль должен содержать от 3 до 45 символов!");
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

                        create_user(surname, name, middle, ph, mail, pos, login, pass);
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
    public void show_positions()throws SQLException, ClassNotFoundException{
        mass_pos = controller.combo_positions();
    }
    public void create_user ( String surname, String name, String middle, String ph, String mail, String pos, String login, String pass) throws SQLException, ClassNotFoundException{
        controller.create_user(surname, name, middle, ph, mail, pos, login, pass);
    }

}
