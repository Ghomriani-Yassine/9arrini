package authentification;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Connecter extends JFrame {
    JLabel ltitle,lmail,lmdp;
    JTextField tfmail;
    JPasswordField passf;
    JPanel ptitle,pmail,pmdp,pform,pbtn;
    JButton btn;
    ArrayList<Etudiant> data=new ArrayList<Etudiant>();

    public Connecter(){
        setTitle("Se Connecter ");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setBackground(new Color(0x2979FF));
        setSize(600,700);

        setLocationRelativeTo(null);

        ptitle=new JPanel(new FlowLayout(FlowLayout.CENTER,0,50));
        ltitle=new JLabel("Se Connecter");

        ltitle.setFont(new Font("Inter",Font.BOLD,48));


        ltitle.setHorizontalAlignment(JLabel.CENTER);
        ptitle.add(ltitle);
        ptitle.setOpaque(false);
        lmail=new JLabel("E-mail:");
        lmail.setFont(new Font("Inter",Font.BOLD,20));
        lmail.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                lmail.setForeground(Color.WHITE);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                lmail.setForeground(Color.BLACK);
            }
        });
        tfmail=new JTextField(20);
        tfmail.setText("Saisir Votre Email:");
        tfmail.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (tfmail.getText().equals("Saisir Votre Email:")) {
                    tfmail.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
               if (tfmail.getText().equals(""))
               {
                   tfmail.setText("Saisir Votre Email:");
               }

            }
        });
        pmail=new JPanel(new FlowLayout(FlowLayout.CENTER,50,20));
        pmail.add(lmail);
        pmail.add(tfmail);
        pmail.setOpaque(false);
        lmdp=new JLabel("Mot de passe:");
        lmdp.setFont(new Font("Inter",Font.BOLD,15));
        lmdp.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                lmdp.setForeground(Color.WHITE);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                lmdp.setForeground(Color.BLACK);
            }
        });
        passf=new JPasswordField(20);
        passf.setText("Saisir Votre mot de passe:");
        passf.setEchoChar((char) 0);
        passf.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (Arrays.equals(passf.getPassword(), "Saisir Votre mot de passe:".toCharArray())) {
                    passf.setText("");
                    passf.setEchoChar('*'); // Or whatever your echo character is
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (passf.getPassword().length == 0) {
                    passf.setText("Saisir Votre mot de passe:");
                    passf.setEchoChar((char) 0);
                }
            }
        });
        pmdp=new JPanel(new FlowLayout(FlowLayout.CENTER,5,20));
        pmdp.add(lmdp);
        pmdp.add(passf);
        pmdp.setOpaque(false);
        pform=new JPanel(new FlowLayout(FlowLayout.CENTER,0,10));
        btn =new JButton("Valider");
        btn.setBackground(Color.white);
        btn.setForeground(new Color(0x2979FF));
        btn.setFont(new Font("Judson",Font.BOLD,20));
        pbtn=new JPanel(new FlowLayout(FlowLayout.CENTER,50,100));
        pbtn.add(btn);
        pbtn.setOpaque(false);
        add(ptitle,BorderLayout.NORTH);
        pform.add(pmail);
        pform.add(pmdp);
        pform.setOpaque(false);


        add(pform,BorderLayout.CENTER);
        add(pbtn,BorderLayout.SOUTH);
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!(exist(passf.getPassword()))) {
                    JOptionPane.showMessageDialog(null, "mot de passe  inexistant", "Erreur", JOptionPane.ERROR_MESSAGE);

                } else if (tfmail.getText().isEmpty() || tfmail.getText().equals("Saisir Votre Email:") || passf.getPassword().equals("") || passf.getPassword().equals("Saisir Votre mot de passe:")) {
                    JOptionPane.showMessageDialog(null, "Veuillez remplir tous les champs");


                } else if (!tfmail.getText().contains("@")) {
                    JOptionPane.showMessageDialog(null, "Email Invalide", "Erreur", JOptionPane.ERROR_MESSAGE);

                } else {

                    System.out.println(passf.getPassword());

                }
            }
            public boolean exist(char[] password)
            {
                for (Etudiant etudiant:data)
                {
                    if (Arrays.equals(etudiant.getPassword(), password))
                    {

                        return true;
                    }
                }
                return false;
            }
        });

        setVisible(true);
    }
}
