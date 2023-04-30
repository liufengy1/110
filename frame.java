import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class frame {
   

  public static void createAndShowGUI() {
       
        JFrame frame = new JFrame("Similarity program");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        
        JPanel panel = new JPanel();
        frame.getContentPane().add(panel, BorderLayout.CENTER);
        panel.setLayout(new BorderLayout());

        
        JTextArea textArea = new JTextArea();
        panel.add(new JScrollPane(textArea), BorderLayout.CENTER);

        JPanel inputPanel = new JPanel();
        frame.getContentPane().add(inputPanel, BorderLayout.SOUTH);
        inputPanel.setLayout(new FlowLayout());

        JTextField textField = new JTextField(20);
        inputPanel.add(textField);

        JButton continueButton = new JButton("Continue");
        inputPanel.add(continueButton);

        
        frame.setVisible(true);
    }
}
