package Enseignant;

import db_config.Config;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class RechEnseignant extends JInternalFrame {
    JLabel lcode,ltitle,lnom,lprenom,lmail,lmatiere;
    JTextField tfcode,tfnom,tfprenom,tfmail,tfmatiere;
    JPanel pcode,ptitle,pnom,pprenom,pmail,pmatiere,pform;
    JButton rech;


    public RechEnseignant()
    {
        setTitle("Recherche d'un Enseignant ");
        setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
        getContentPane().setBackground(new Color(0x2979FF));

        setSize(700,700);
        this.setClosable(true);
        this.setResizable(true);
        this.setMaximizable(true);
        this.setIconifiable(true);
        ptitle=new JPanel(new FlowLayout(FlowLayout.CENTER,0,50));
        ltitle=new JLabel("Recherche d'un Enseignant ");

        ltitle.setFont(new Font("Inter",Font.BOLD,48));


        ltitle.setHorizontalAlignment(JLabel.CENTER);

        ptitle.add(ltitle);


        ptitle.setOpaque(false);
        lcode=new JLabel("Code :");
        lcode.setFont(new Font("Inter",Font.BOLD,20));
        tfcode=new JTextField(20);
        tfcode.setText("Code Prof:");
        tfcode.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (tfcode.getText().equals("Code Prof:")) {
                    tfcode.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (tfcode.getText().equals(""))
                {
                    tfcode.setText("Code Prof:");
                }

            }
        });
        pcode=new JPanel(new FlowLayout(FlowLayout.CENTER,50,40));
        pcode.setOpaque(false);
        pcode.add(lcode);
        pcode.add(tfcode);
        pcode.setOpaque(false);
        rech=new JButton("Rechercher");
        pcode.add(rech);
        lnom=new JLabel("Nom");
        lnom.setFont(new Font("Inter",Font.BOLD,20));
        tfnom=new JTextField(20);
        tfnom.setEnabled(false);

        pnom=new JPanel(new FlowLayout(FlowLayout.CENTER,50,10));
        pnom.setOpaque(false);
        lprenom=new JLabel("Prenom");
        lprenom.setFont(new Font("Inter",Font.BOLD,20));
        tfprenom =new JTextField(20);
        tfprenom.setEnabled(false);

        pprenom=new JPanel(new FlowLayout(FlowLayout.CENTER,50,10));
        pprenom.setOpaque(false);
        lmail=new JLabel("Email");
        lmail.setFont(new Font("Inter",Font.BOLD,20));
        tfmail=new JTextField(20);
        tfmail.setEnabled(false);
        pmail=new JPanel(new FlowLayout(FlowLayout.CENTER,50,10));
        pmail.setOpaque(false);
        lmatiere=new JLabel("Matiere");
        lmatiere.setFont(new Font("Inter",Font.BOLD,20));
        tfmatiere=new JTextField(20);
        tfmatiere.setEnabled(false);
        pmatiere=new JPanel(new FlowLayout(FlowLayout.CENTER,50,10));
        pmatiere.setOpaque(false);
        pform=new JPanel(new FlowLayout(FlowLayout.CENTER,0,10));
        pnom.add(lnom);
        pnom.add(tfnom);

        pprenom.add(lprenom);
        pprenom.add(tfprenom);

        pmail.add(lmail);
        pmail.add(tfmail);

        pmatiere.add(lmatiere);
        pmatiere.add(tfmatiere);
        pform.add(pcode);
        pform.add(pnom);
        pform.add(pprenom);
        pform.add(pmail);
        pform.add(pmatiere);
        pcode.setBackground(new Color(0x2979FF));
        pnom.setBackground(new Color(0x2979FF));
        pprenom.setBackground(new Color(0x2979FF));
        pmail.setBackground(new Color(0x2979FF));
        pmatiere.setBackground(new Color(0x2979FF));
        pform.setBackground(new Color(0x2979FF));
        add(ptitle,BorderLayout.NORTH);
        add(pform,BorderLayout.CENTER);
        rech.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (tfcode.getText().isEmpty() || tfcode.getText().equals("Code:")) {
                    JOptionPane.showMessageDialog(null, "Veuillez saisir un code de professeur");
                } else {
                    int code = Integer.parseInt(tfcode.getText());

                    // Perform the search for the teacher by code
                   EnseignantDAO enseignantDAO = new EnseignantDAO(Config.URL, Config.USERNAME, Config.PASSWORD);
                    Enseignant enseignant = enseignantDAO.Recherche(code);


                    // Check if the teacher was found
                    if (enseignant != null) {
                        // Display the details in JTextFields
                        tfnom.setEnabled(true);
                        tfnom.setText(enseignant.getNom());
                        tfprenom.setEnabled(true);

                        tfprenom.setText(enseignant.getPrenom());
                        tfmail.setEnabled(true);

                        tfmail.setText(enseignant.getEmail());
                        tfmatiere.setEnabled(true);

                        tfmatiere.setText(enseignant.getMatiere());
                    } else {
                        JOptionPane.showMessageDialog(null, "Aucun enseignant trouvé avec ce code");
                    }
                }
            }
            });



        setVisible(true);

    }
}
