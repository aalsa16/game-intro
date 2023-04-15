import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class App extends JFrame implements ActionListener {
    private JButton instructionsButton, playButton, quitButton;
    private JFrame instructionsFrame, playFrame;
    private JButton backButtonIx, backButtonPlay, submitButton;
    private JTextField input;
    private int numberToGuess;

    public App() {
        setTitle("Game Introduction");
        setLayout(new GridLayout(3, 1));

        setSize(1200, 700);
        setLocationRelativeTo(null);

        instructionsButton = new JButton("Instructions");
        instructionsButton.addActionListener(this);
        add(instructionsButton);

        playButton = new JButton("Play");
        playButton.addActionListener(this);
        add(playButton);

        quitButton = new JButton("Quit");
        quitButton.addActionListener(this);
        add(quitButton);

        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == instructionsButton) {
            instructionsFrame = new JFrame("Instructions");
            instructionsFrame.add(new JLabel("Instructions: Guess a random number from 1-100, keep trying until you get it."));
            backButtonIx = new JButton("Back");

            backButtonIx.addActionListener(this);

            instructionsFrame.setLayout(new GridLayout(2, 1));
            instructionsFrame.add(backButtonIx);

            instructionsFrame.setSize(600, 300);
            instructionsFrame.setLocationRelativeTo(null);

            instructionsFrame.setVisible(true);
        } else if (e.getSource() == playButton) {
            Random rand = new Random();
            numberToGuess = rand.nextInt(100);

            playFrame = new JFrame("Guessing game");
            JLabel enterGuess = new JLabel("Enter guess:");
            input = new JTextField(10);
            submitButton = new JButton("Submit");
            submitButton.addActionListener(this);

            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
            panel.add(enterGuess);
            panel.add(input);
            panel.add(submitButton);
            playFrame.add(panel);

            playFrame.pack();
            playFrame.setLocationRelativeTo(null);
            playFrame.setVisible(true);
            playFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        } else if (e.getSource() == quitButton) {
            dispose();

            if (instructionsFrame != null) {
                instructionsFrame.dispose();
            }

            if (playFrame != null) {
                playFrame.dispose();
            }

            System.exit(0);
        } else {
            if (e.getSource() == backButtonIx) {
                instructionsFrame.dispose();
            } else if (e.getSource() == backButtonPlay) {
                playFrame.dispose();
            } else if (e.getSource() == submitButton) {
                try {                    
                    int guess = Integer.parseInt(input.getText());

                    if (guess > numberToGuess) {
                        JOptionPane.showMessageDialog(playFrame, "Your guess is higher.", "Message", JOptionPane.PLAIN_MESSAGE);
                    } else if (guess < numberToGuess) {
                        JOptionPane.showMessageDialog(playFrame, "Your guess is lower.", "Message", JOptionPane.PLAIN_MESSAGE);
                    } else if (guess == numberToGuess) {
                        JOptionPane.showMessageDialog(playFrame, "You got it!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(playFrame, "An error has occurred", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    public static void main(String[] args) {
        new App();
    }
}
