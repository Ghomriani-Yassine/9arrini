package Admin;

import Cours.AjoutCours;
import Cours.GestionFormation;
import Cours.GestionFormation;
import Enseignant.AjoutEnseignant;
import Enseignant.GestionEnseignant;
import Etudiant.GestionEtudiant;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Dashboard extends JFrame {
    JMenuBar menuBar;
    JDesktopPane desktopPane;
    JMenu enseignant,etudiant,formation;
   JMenuItem listeEtudiant,ajoutEnseignant,listeEnseignant,ListFormation,ajoutFormation;
    JLabel limage;
    JPanel pimage;

    ImageIcon img;
    JLabel bvn;

    Dashboard()
    {
        setTitle("Dashboard");
        this.setSize(1000, 800);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setResizable(true);
        menuBar=new JMenuBar();


        enseignant=new JMenu("Enseignants");
        etudiant=new JMenu("Etudiants");
        formation=new JMenu("Formation");
        listeEtudiant=new JMenuItem("liste des Etudiants");




        menuBar.add(enseignant);
        menuBar.add(etudiant);
        menuBar.add(formation);
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

        enseignant.add(ajoutEnseignant);
        enseignant.add(listeEnseignant);

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
        ListFormation=new JMenuItem("Liste des Formations");

    formation.add(ListFormation);
    ListFormation.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            GestionFormation formation=new GestionFormation();
            desktopPane.add(formation);
        }
    });

        ajoutFormation=new JMenuItem("Ajout de Formation");
        formation.add(ajoutFormation);
        ajoutFormation.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AjoutCours addCours=new AjoutCours();
                desktopPane.add(addCours);
            }
        });

        limage=new JLabel();
        img=new ImageIcon("src/images/admin.jpeg");
        Image image=img.getImage().getScaledInstance(80,65,Image.SCALE_SMOOTH);
        ImageIcon newImg =new ImageIcon(image);
        limage.setIcon(newImg);


        bvn=new JLabel("Bienvenue ");
        bvn.setFont(new Font("Arial",Font.BOLD,25));
        JPanel pbvn = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 10));
        pbvn.add(bvn);

       pbvn.add(limage);
    add(pbvn,BorderLayout.NORTH);
        this.setVisible(true);

    }

    public static void main(String[] args) {
        Dashboard d=new Dashboard();
    }
}
