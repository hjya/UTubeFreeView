import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Calendar;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.border.TitledBorder;
import java.awt.Dimension;
import javax.swing.JLabel;

public class test  extends JFrame{

	private String FIFO = "\\\\.\\pipe\\tmp\\mpv-socket";
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					test frame = new test();
					//frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public test() {
		setBackground(Color.BLACK);
		String osname =  System.getProperty("os.name");

		System.out.println(osname);
		
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(1600);
		System.out.println(cal.get(Calendar.SECOND));
		
		/*************/
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("New label");
		contentPane.add(lblNewLabel, BorderLayout.NORTH);
		//this.setVisible(true);
		/*************

		   

		      BufferedReader in = null;
		      try{
		    	  while(true) {
		         System.out.println("JAVA SIDE!!");
		         in = new BufferedReader(new FileReader(FIFO));
		         while(in.ready()){
		             System.out.println(in.readLine());
		         }
		         //done, however you can choose to cycle over this line
		         //in this thread or launch another to check for new input
		         in.close();
		         try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    	  }
		      }catch(IOException ex){
		         ex.printStackTrace();
		         System.exit(-1);
		      }
/************/		      
		   }
		

}
