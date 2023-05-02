import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import javax.swing.*;

public class otp extends JFrame implements ActionListener {
    
    private JTextField otpField;
    private JTextField verifyField; // new field to input verification code
    private JButton generateButton;
    private JButton verifyButton; // new button to verify OTP
    
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
        
        verifyField = new JTextField(4); // new verification field with 4-digit limit
        verifyButton = new JButton("Verify OTP");
        verifyButton.addActionListener(this);
        
        JPanel otpPanel = new JPanel(new BorderLayout());
        otpPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        otpPanel.add(otpField, BorderLayout.CENTER);
        otpPanel.add(generateButton, BorderLayout.SOUTH);
        
        JPanel verifyPanel = new JPanel(new BorderLayout());
        verifyPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        verifyPanel.add(verifyField, BorderLayout.CENTER);
        verifyPanel.add(verifyButton, BorderLayout.SOUTH);
        
        JPanel mainPanel = new JPanel(new GridLayout(2,1));
        mainPanel.add(otpPanel);
        mainPanel.add(verifyPanel);
        
        setContentPane(mainPanel);
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
        int length = 4; // update to 4 digits
        String numbers = "0123456789";
        Random rndm_method = new Random();
        char[] otp = new char[length];
        for (int i = 0; i < length; i++) {
            otp[i] = numbers.charAt(rndm_method.nextInt(numbers.length()));
        }
        otpField.setText(new String(otp));
        verifyField.setText(""); // clear verification field when new OTP is generated
    }
    
    private void verifyOTP() {
        String generatedOTP = otpField.getText();
        String enteredOTP = verifyField.getText();
        if (generatedOTP.equals(enteredOTP)) {
            JOptionPane.showMessageDialog(this, "OTP Verified!"); // display message if OTP is correct
        } else {
            JOptionPane.showMessageDialog(this, "Incorrect OTP, please try again."); // display message if OTP is incorrect
        }
        verifyField.setText(""); // clear verification field after verifying OTP
    }
    
    public static void main(String[] args) {
        otp otpGenerator = new otp();
        otpGenerator.setVisible(true);
    }
}
