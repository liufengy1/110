import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class myframe extends JFrame{


  JTextField textField = new JTextField(20);
  JButton continueButton = new JButton("Continue");
  JPanel textAreaPanel = new JPanel();


  public myframe(String title) {
       
        super(title);
       

        
        JPanel panel = new JPanel();
        this.getContentPane().add(panel, BorderLayout.CENTER);
        panel.setLayout(new BorderLayout());

        JPanel textAreaPanel = new JPanel();
        textAreaPanel.setLayout(new BoxLayout(textAreaPanel, BoxLayout.X_AXIS));
        textAreaPanel.add(new JScrollPane(BagOfWords.textArea));
        textAreaPanel.add(new JScrollPane(BagOfWords.textArea1));
        textAreaPanel.add(new JScrollPane(BagOfWords.textArea2));
        textAreaPanel.add(new JScrollPane(BagOfWords.textArea3));
        textAreaPanel.add(new JScrollPane(BagOfWords.textArea4));
        panel.add(textAreaPanel, BorderLayout.CENTER);// Add the JTextArea panel to the main panel

        
    

        JPanel inputPanel = new JPanel();
        this.getContentPane().add(inputPanel, BorderLayout.SOUTH);
        inputPanel.setLayout(new FlowLayout());

       
        inputPanel.add(textField);

        
        inputPanel.add(continueButton);
        continueButton.addActionListener(e -> BagOfWords. actionPerformed(this));

        
        this.setVisible(true);
    }
}
