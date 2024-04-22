package Serveur;

import Enseignant.Enseignant;
import Enseignant.EnseignantDAO;
import Etudiant.Etudiant;
import Etudiant.EtudiantDAO;
import db_config.Config;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;

public class Authentification extends Thread {
    PrintWriter out = null;
    BufferedReader in = null;
    private Socket client;

    public Authentification(Socket client) {
        this.client = client;
    }

    @Override
    public void run() {
        try {
            out = new PrintWriter(client.getOutputStream());
            in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            while (true) {
                String email = in.readLine();
                String password = in.readLine();
                String role = in.readLine();
                boolean isAuthenticated = authenticate(email, password, role);
                out.println(isAuthenticated ? "SUCCESS" : "FAILURE");
                out.flush();
            }
        } catch (SocketException e) {

            System.err.println("Client connection reset: " + e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erreur lors de l'authentification", "Erreur", JOptionPane.ERROR_MESSAGE);
        }

    }


    private boolean authenticate(String email, String password, String role) {
        if (role.equals("enseignant")) {
            int code = Integer.parseInt(password);
            EnseignantDAO dataEnseignant = new EnseignantDAO(Config.URL, Config.USERNAME, Config.PASSWORD);
            Enseignant enseignantRech = dataEnseignant.Recherche(code);
            if (enseignantRech != null) {

                return true;
            } else {
                return false;
            }
        } else if (role.equals("etudiant")) {
            EtudiantDAO dataEtudiant = new EtudiantDAO(Config.URL, Config.USERNAME, Config.PASSWORD);
            Etudiant etudiantRech = dataEtudiant.Recherche(password.toCharArray(),email);
            if (etudiantRech != null) {

                return true;
            } else {
                return false;
            }
        }
      return false;
    }
}
