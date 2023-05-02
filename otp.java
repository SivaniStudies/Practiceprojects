import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import javax.swing.*;

public class otp extends JFrame implements ActionListener {
    
    private JTextField otpField, verifyOtpField;
    private JButton generateButton, verifyButton;
    private JLabel otpLabel, verifyOtpLabel;
    
    public otp() {
        setTitle("OTP Generator");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        // Create OTP label and text field
        otpLabel = new JLabel("OTP: ");
        otpLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        otpField = new JTextField(4);
        otpField.setEditable(false);
        otpField.setFont(new Font("Arial", Font.PLAIN, 18));
        otpField.setBackground(new Color(255, 228, 225)); // Set background color to ffe4e1
        
        // Create verify OTP label and text field
        verifyOtpLabel = new JLabel("Verify OTP: ");
        verifyOtpLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        verifyOtpField = new JTextField(4);
        verifyOtpField.setFont(new Font("Arial", Font.PLAIN, 18));
        verifyOtpField.setBackground(new Color(255, 228, 225)); // Set background color to ffe4e1
        
        // Create generate button
        generateButton = new JButton("Generate OTP");
        generateButton.addActionListener(this);
        generateButton.setFont(new Font("Arial", Font.PLAIN, 18));
        generateButton.setBackground(new Color(255, 198, 197)); // Set button color to offc6c85
        generateButton.setForeground(Color.BLACK); // Set button text color to black
        
        // Create verify button
        verifyButton = new JButton("Verify OTP");
        verifyButton.addActionListener(this);
        verifyButton.setFont(new Font("Arial", Font.PLAIN, 18));
        verifyButton.setBackground(new Color(255, 198, 197)); // Set button color to offc6c85
        verifyButton.setForeground(Color.BLACK); // Set button text color to black
        
        // Create panel and add components
        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.setBackground(new Color(255, 228, 225)); // Set background color to ffe4e1
        panel.add(otpLabel);
        panel.add(otpField);
        panel.add(verifyOtpLabel);
        panel.add(verifyOtpField);
        panel.add(generateButton);
        panel.add(verifyButton);
        
        setContentPane(panel);
        pack();
        setLocationRelativeTo(null);
    }
    
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == generateButton) {
            generateOTP();
        } else if (e.getSource() == verifyButton) {
            verifyOTP();
        }
    }
    
    private void generateOTP() {
        int length = 4; // Change OTP length to 4 digits
        String numbers = "0123456789";
        Random rndm_method = new Random();
        char[] otp = new char[length];
        for (int i = 0; i < length; i++) {
            otp[i] = numbers.charAt(rndm_method.nextInt(numbers.length()));
        }
        otpField.setText(new String(otp));
    }
    
    private void verifyOTP() {
        String otp = otpField.getText();
        String enteredOtp = verifyOtpField.getText();
        if (otp.equals(enteredOtp)) {
            JOptionPane.showMessageDialog(this, "Verification Successful");
        } else {
            JOptionPane.showMessageDialog(this, "Verification Failed");
        }
    }

    public static void main(String[] args) {
        otp otpGenerator = new otp();
        otpGenerator.setVisible(true);
    }
}