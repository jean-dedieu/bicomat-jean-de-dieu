package com.bicomat.console;

import com.bicomat.model.Client;
import com.bicomat.service.ClientService;
import com.bicomat.service.ClientServiceImpl;
import com.bicomat.repository.ClientRepository;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BicomatSwingApp {
    private final ClientService clientService;

    public BicomatSwingApp() {
        // Initialize the service layer
        clientService = new ClientServiceImpl(new ClientRepository());
    }

    public void launch() {
        // Create the main frame
        JFrame frame = new JFrame("Bicomat - Gestion des Clients et Comptes");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create the panel
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2, 10, 10)); // Grid layout with 5 rows and 2 columns

        // Add form elements
        JLabel nameLabel = new JLabel("Nom du Client:");
        JTextField nameField = new JTextField();

        JLabel emailLabel = new JLabel("Email du Client:");
        JTextField emailField = new JTextField();

        JLabel passwordLabel = new JLabel("Mot de Passe:");
        JPasswordField passwordField = new JPasswordField();

        JButton createClientButton = new JButton("Créer Client");
        JTextArea resultArea = new JTextArea(5, 20);
        resultArea.setEditable(false);

        // Add components to the panel
        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(emailLabel);
        panel.add(emailField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(createClientButton);

        // Add panel and result area to the frame
        frame.add(panel, BorderLayout.CENTER);
        frame.add(new JScrollPane(resultArea), BorderLayout.SOUTH);

        // Add event listener to the button
        createClientButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String email = emailField.getText();
                String password = new String(passwordField.getPassword());

                if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Veuillez remplir tous les champs.", "Erreur", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Create a new client
                try {
                    Client client = clientService.creerClient(name, email, password);
                    resultArea.append("Client créé: " + client + "\n");
                    nameField.setText("");
                    emailField.setText("");
                    passwordField.setText("");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frame, "Erreur lors de la création du client: " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Make the frame visible
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new BicomatSwingApp().launch());
    }
}
