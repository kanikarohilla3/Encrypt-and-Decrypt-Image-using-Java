import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class ImageOperation2 {


	public static void operate(int key) {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.showOpenDialog(null);   //will open file chooser for us, Null will help to open file chooser at center
		File file = fileChooser.getSelectedFile();	//will store our file in file 
		//file fileInputStream

		try {

			FileInputStream fis = new FileInputStream(file);
			byte []data = new byte[fis.available()];		//will convert our file into byte and stored that byte in an array that we called
			fis.read(data);
			int i=0;
			for(byte b: data) {
				System.out.println(b);
				data[i] = (byte)(b^key);	//XOR will change the original byte value to another value and this will encrypt our image, in case of decryt we will again XOR b with key which will return original Value
				i++;
			}
			FileOutputStream fos = new FileOutputStream(file);
			fos.write(data);		//this will write changed data in our image 
			fos.close();
			fis.close();
			JOptionPane.showMessageDialog(null, "Success!!!!!!");

		}catch(Exception e) {
			e.printStackTrace();     //should search from internet
		}

	}


	public static void main(String args[]) {

		System.out.println("this is testing");

		JFrame f = new JFrame();
		
		JLabel label2 = new JLabel();  	//JLabel will print our text as it is on our GUI
		label2.setText("This project is developed by Anurag");
		//label2.setBounds(null);
		ImageIcon image = new ImageIcon("C://Users//ANURAG//Desktop/encryption.png");		// will show Instructions image on my GUI
		JLabel i = new JLabel(image);
		
		f.setTitle("Image Encryption/Decryption");
		f.setSize(700,350);
		f.setLocationRelativeTo(null);   //will bring our external GUI to center of screen	
		f.setResizable(false);		//will not allow user to resize the application by dragging from corner

		//to show warning message while closing the GUI
			f.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			f.addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent e) {
					int choose = JOptionPane.showConfirmDialog(null,
							"Do you really want to exit the application?",
							"Confirm Close", JOptionPane.YES_NO_OPTION,
							JOptionPane.INFORMATION_MESSAGE);
					if (choose == JOptionPane.YES_OPTION) {
						e.getWindow().dispose();
					}
				}
			});

		//		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //will close our window directly if we click on close button, we can also add any warning message before closing

		Font font = new Font("Times new Roman", Font.BOLD, 25);  //Setting font type and size

		//creating button for GUI
		JButton button = new JButton();
		button.setText("Select Image");
		button.setFont(font);


		//Creating text Filed
		JTextField textField = new JTextField(10);
		textField.setFont(font);

		button.addActionListener(e->{		//will give functionality of clicking to our button, here we used e->{} because we are not making class of action listener we are using lembda functions
			String text = textField.getText();		// will store the text we will write in out text field
			if(text.length() == 0) {
				JOptionPane.showMessageDialog(null, "Enter Valid Encryption Key");
			}else {
				int temp = Integer.parseInt(text); 		// parseInt function will convert our string into int
				operate(temp);
			}

		});

		f.setLayout(new FlowLayout());		//will allow my window to drag it anywhere on the screen
		
		f.add(label2);
		f.add(button);
		f.add(textField);
		f.add(i);
		f.setVisible(true);	
	}

}
