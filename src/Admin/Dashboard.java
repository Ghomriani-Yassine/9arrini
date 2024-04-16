package Admin;

import Enseignant.AjoutEnseignant;
import Enseignant.GestionEnseignant;
import Enseignant.RechEnseignant;
import Etudiant.GestionEtudiant;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Dashboard extends JFrame {
    JMenuBar menuBar;
    JDesktopPane desktopPane;
    JMenu enseignant,etudiant,cours;
   JMenuItem listeEtudiant,ajoutEnseignant,listeEnseignant,RechercherEnseignant;
    JLabel limage;
    JPanel pimage;

    ImageIcon img;

    Dashboard()
    {
        setTitle("Dashboard");
        this.setSize(1000, 800);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setResizable(true);
        menuBar=new JMenuBar();


        enseignant=new JMenu("Enseignants");
        etudiant=new JMenu("Etudiants");
        cours=new JMenu("Cours");
        listeEtudiant=new JMenuItem("liste des Etudiants");




        menuBar.add(enseignant);
        menuBar.add(etudiant);
        menuBar.add(cours);
        etudiant.add(listeEtudiant);
        desktopPane=new JDesktopPane();
        add(desktopPane,BorderLayout.CENTER);
        setJMenuBar(menuBar);

        listeEtudiant.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GestionEtudiant gestionEtudiant=new GestionEtudiant();
                desktopPane.add(gestionEtudiant);
            }
        });
        ajoutEnseignant=new JMenuItem("Ajouter Enseignant");
        listeEnseignant=new JMenuItem("Liste des Enseigants");
        RechercherEnseignant=new JMenuItem("Rechercher d'un enseignant");
        enseignant.add(ajoutEnseignant);
        enseignant.add(listeEnseignant);
        enseignant.add(RechercherEnseignant);
        ajoutEnseignant.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AjoutEnseignant ajout=new AjoutEnseignant();
                desktopPane.add(ajout);
            }
        });
listeEnseignant.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        GestionEnseignant gestionEnseignant=new GestionEnseignant();
        desktopPane.add(gestionEnseignant);
    }
});

        RechercherEnseignant.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RechEnseignant rechEnseignant=new RechEnseignant();
                desktopPane.add(rechEnseignant);
            }
        });




        this.setVisible(true);

    }

    public static void main(String[] args) {
        Dashboard d=new Dashboard();
    }
}
