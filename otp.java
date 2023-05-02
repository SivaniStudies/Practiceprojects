import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import javax.swing.*;

public class otp extends JFrame implements ActionListener {
    
    private JTextField otpField;
    private JButton generateButton;
    
    public otp() {
        setTitle("OTP Generator");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        otpField = new JTextField(20);
        otpField.setEditable(false);
        otpField.setHorizontalAlignment(JTextField.CENTER);
        otpField.setFont(new Font("Arial", Font.PLAIN, 24));
        
        generateButton = new JButton("Generate OTP");
        generateButton.addActionListener(this);
        generateButton.setFont(new Font("Arial", Font.PLAIN, 18));
        
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.add(otpField, BorderLayout.CENTER);
        panel.add(generateButton, BorderLayout.SOUTH);
        
        setContentPane(panel);
        pack();
        setLocationRelativeTo(null);
    }
    
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == generateButton) {
            generateOTP();
        }
    }
    
    private void generateOTP() {
        int length = 6;
        String numbers = "0123456789";
        Random rndm_method = new Random();
        char[] otp = new char[length];
        for (int i = 0; i < length; i++) {
            otp[i] = numbers.charAt(rndm_method.nextInt(numbers.length()));
        }
        otpField.setText(new String(otp));
    }
    
    public static void main(String[] args) {
        otp otpGenerator = new otp();
        otpGenerator.setVisible(true);
    }
}
