package org.example;

import org.example.views.LoginSignupFrame;
import org.example.views.WindowPanel;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new WindowPanel().setVisible(true);
            }
        });
    }
}