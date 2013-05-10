import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class Main
{
	public static long TOTAL_ITERATIONS = 200 * 1024 * 1024;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Parent Final:    " + Parent.makeKeyFromFinal());
		System.out.println("Parent Variable: " + Parent.makeKeyFromVariable());
		System.out.println("Parent Method:   " + Parent.makeKeyFromMethod());
		System.out.println("Child Final:     " + Child.makeKeyFromFinal());
		System.out.println("Child Variable:  " + Child.makeKeyFromVariable());
		System.out.println("Child Method:    " + Child.makeKeyFromMethod());
		//(new LongThreadTest()).test(1024*1024, 8*1024);
		//(new DoubleCalculation()).test(200*1024*1024, 1024*1024);
		//(new IntegerCalculation()).test(200*1024*1024, 1024*1024);

		JFrame frame = new JFrame();
		JPanel panel = new JPanel();
		JButton button = new JButton("Cool");
		
		panel.setLayout(new BorderLayout());
		panel.add(button, BorderLayout.CENTER);
//		panel.add(button, new GridBagConstraints(gridx, gridy, gridwidth, gridheight, weightx, weighty, anchor, fill, insets, ipadx, ipady));
		
		frame.add(panel);
		frame.doLayout();
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.setVisible(true);
	}
}
