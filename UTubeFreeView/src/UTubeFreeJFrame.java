import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.WindowStateListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.border.TitledBorder;

import com.sun.jna.Pointer;

import java.awt.Dimension;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JScrollPane;
import java.awt.FlowLayout;
import java.awt.event.WindowAdapter;
import java.awt.SystemColor;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Cursor;
import javax.swing.JTextField;
import java.awt.Panel;
import javax.swing.JCheckBox;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.beans.VetoableChangeListener;
import java.beans.PropertyChangeEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class UTubeFreeJFrame extends JFrame {

	private JPanel contentPane;
	public UTubeDownload download;
	String properties = "./prop.txt";
	Properties props;
	public UTubeFreeView freeview;
	public DrawPanel drawPanel;
	private JScrollPane scrollPane;
	public DrawPanel[] drawPanelarray = new DrawPanel[2];
	private JButton btnyoutube;
	private JTextField textField;
	private JLabel lblNewLabel;
	public JLabel lbltitle;
	public JLabel lbltitle_view;
	private Panel panel_2;
	private JPanel panel_3;
	public ProgressPanel panel_4;
	private JButton btndownload;
	private JButton btnmp3;
	public JLabel lbldownload;
	private JPanel panel_5;
	private JButton btnsearch;
	private JPanel panel_6;
	private JButton btnmp3download;
	private JButton btnvideodownload;
	public MPVFrame test;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;

	public String ptitle;
	public String purl;
	public String pimg;
	
	public String ptitle1;
	public String purl1;
	public String pimg1;
	public JCheckBox chkboxLOOP;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UTubeFreeJFrame frame = new UTubeFreeJFrame();
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void setWinBound()
	{
		try {
		
		setProperty("windowx",String.valueOf( this.getX() ));
		setProperty("windowy",String.valueOf( this.getY()));
		setProperty("windowwidth",String.valueOf( this.getWidth()));
		setProperty("windowheight",String.valueOf( this.getHeight()));
		
		setProperty("mpvwindowx",String.valueOf( test.frame.getX()));
		setProperty("mpvwindowy",String.valueOf( test.frame.getY()));
		setProperty("mpvwindowwidth",String.valueOf( test.frame.getWidth()));
		setProperty("mpvwindowheight",String.valueOf( test.frame.getHeight()));
		
		setProperty("driverx",String.valueOf(freeview.driver.manage().window().getPosition().getX()));
		setProperty("drivery",String.valueOf(freeview.driver.manage().window().getPosition().getY()));
		setProperty("driverwidth",String.valueOf(freeview.driver.manage().window().getSize().getWidth() ));
		setProperty("driverheight",String.valueOf(freeview.driver.manage().window().getSize().getHeight() ));
		
		try {
			if( ptitle != null && purl != null && pimg != null && ptitle1 != null  && purl1 != null && pimg1 != null) {
				setProperty("ptitle", ptitle);
				setProperty("purl",purl);
				setProperty("pimg",pimg);
				
				setProperty("ptitle1", ptitle1);
				setProperty("purl1",purl1);
				setProperty("pimg2",pimg1);
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
		
	}
	/**
	 * Create the frame.
	 */
	public UTubeFreeJFrame() {
		
		setResizable(false);
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				
				setWinBound();
				freeview.driver.quit();
			
			}
		});
		addWindowFocusListener(new WindowFocusListener() {
			public void windowGainedFocus(WindowEvent e) {
				setWinBound();	
			}
			
			public void windowLostFocus(WindowEvent e) {

				setWinBound();	
				
				
			}
		});
		addWindowStateListener(new WindowStateListener() {
			public void windowStateChanged(WindowEvent e) {
				System.out.println(e);
				
			}
		});
		props = new Properties();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		try {
			setBounds( Integer.parseInt((String) getProperty("windowx")) 
							,Integer.parseInt((String) getProperty("windowy"))
							, Integer.parseInt((String) getProperty("windowwidth"))
							, Integer.parseInt((String) getProperty("windowheight")));
		}catch(Exception ex)
		{
			this.setBounds(100,100,450,620);
		}
		//this.setBounds(100,100,600,620);
		/**********/
		System.out.println(getClass().getName() + " :::::::: " + props.getProperty("purl"));
		if( props.getProperty("purl") != null )
		{
			freeview = new UTubeFreeView(this, props.getProperty("purl"));
		}else{
			freeview = new UTubeFreeView(this, "https://www.youtube.com");
		}
		try {
			org.openqa.selenium.Dimension dim = new org.openqa.selenium.Dimension(Integer.parseInt(getProperty("driverwidth")),Integer.parseInt(getProperty("driverheight")));
			freeview.driver.manage().window().setSize(dim);
			org.openqa.selenium.Point pot = new org.openqa.selenium.Point(Integer.parseInt(getProperty("driverx")),Integer.parseInt(getProperty("drivery")));
			freeview.driver.manage().window().setPosition(pot);
		}catch(Exception ex)
		{
			freeview.driver.manage().window().setSize(new org.openqa.selenium.Dimension(1600,800));
		}
		/**********/
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		
		
		//MyPanel panel = new MyPanel();
		//contentPane.add(panel, BorderLayout.CENTER);
		
		JPanel panel_search = new JPanel();
		panel_search.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPane.add(panel_search, BorderLayout.NORTH);
		panel_search.setLayout(new BorderLayout(0, 0));
		
		textField = new JTextField();
		textField.setFont(new Font("³ª´®¹Ù¸¥°íµñ", Font.PLAIN, 12));
		panel_search.add(textField, BorderLayout.CENTER);
		textField.setColumns(10);
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		try {
			lblNewLabel.setIcon( new ImageIcon("./youtube_logo3.png"));
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
		lblNewLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				
				freeview.driver.get("https://www.youtube.com");
			}
		});
		lblNewLabel.setPreferredSize(new Dimension(100, 0));
		lblNewLabel.setBackground(Color.DARK_GRAY);
		panel_search.add(lblNewLabel, BorderLayout.WEST);
		
		panel_5 = new JPanel();
		panel_search.add(panel_5, BorderLayout.EAST);
		panel_5.setLayout(new BorderLayout(0, 0));
		
		btnsearch = new JButton("\uAC80\uC0C9");
		btnsearch.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				
				if( textField.getText().contains("youtube.com") ) {
					freeview.driver.get(textField.getText());
				}else {
					freeview.driver.get("https://www.youtube.com/results?search_query="+textField.getText());
					textField.setText("https://www.youtube.com/results?search_query="+textField.getText());
				}
				
			}
		});
		
		panel_6 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_6.getLayout();
		flowLayout.setVgap(0);
		panel_6.setAlignmentY(0.0f);
		panel_6.setAlignmentX(Component.LEFT_ALIGNMENT);
		panel_5.add(panel_6, BorderLayout.CENTER);

		btnsearch.setForeground(Color.DARK_GRAY);
		btnsearch.setBackground(Color.ORANGE);
		panel_5.add(btnsearch, BorderLayout.WEST);
		
		JPanel panel_center = new JPanel();
		contentPane.add(panel_center, BorderLayout.CENTER);
		panel_center.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_youtube = new JPanel();
		panel_youtube.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_youtube.setBackground(new Color(100, 149, 237));
		panel_youtube.setPreferredSize(new Dimension(10, 320));
		
		panel_youtube.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		Panel panel_1 = new Panel();
		panel_1.setPreferredSize(new Dimension(400, 100));
		panel_youtube.add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		lbltitle = new JLabel("");
		lbltitle.setPreferredSize(new Dimension(400, 25));
		lbltitle.setFont(new Font("³ª´®¹Ù¸¥°íµñ", Font.BOLD, 14));
		lbltitle.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		lbltitle.setAlignmentX(0.5f);
		panel_1.add(lbltitle, BorderLayout.NORTH);
		
		lbltitle_view = new JLabel("");
		lbltitle_view.setPreferredSize(new Dimension(400, 25));
		lbltitle_view.setHorizontalAlignment(SwingConstants.CENTER);
		lbltitle_view.setFont(new Font("³ª´®¹Ù¸¥°íµñ", Font.BOLD, 14));
		lbltitle_view.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		lbltitle_view.setAlignmentX(0.5f);
		panel_1.add(lbltitle_view, BorderLayout.CENTER);
		
		panel_2 = new Panel();
		panel_2.setPreferredSize(new Dimension(10, 50));
		panel_1.add(panel_2, BorderLayout.SOUTH);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		panel_3 = new JPanel();
		panel_3.setBackground(new Color(135, 206, 250));
		panel_2.add(panel_3, BorderLayout.CENTER);
		
		btndownload = new JButton("\uB3D9\uC601\uC0C1 \uB2E4\uC6B4\uB85C\uB4DC");
		btndownload.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				
				download.setDownload(lbltitle.getText(), freeview.driver.getCurrentUrl());
			}
		});
		btndownload.setPreferredSize(new Dimension(150, 25));
		btndownload.setFont(new Font("³ª´®¹Ù¸¥°íµñ", Font.BOLD, 12));
		panel_3.add(btndownload);
		
		btnmp3 = new JButton("MP3 \uB2E4\uC6B4\uB85C\uB4DC");
		btnmp3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				download.setMP3Download(lbltitle.getText(), freeview.driver.getCurrentUrl());
			}
		});
		btnmp3.setPreferredSize(new Dimension(150, 25));
		btnmp3.setFont(new Font("³ª´®¹Ù¸¥°íµñ", Font.BOLD, 12));
		panel_3.add(btnmp3);
		
		panel_4 = new ProgressPanel();
		panel_2.add(panel_4, BorderLayout.SOUTH);
		panel_4.setLayout(new BorderLayout(0, 0));
		
		lbldownload = new JLabel("");
		lbldownload.setHorizontalAlignment(SwingConstants.CENTER);
		lbldownload.setBackground(new Color(65, 105, 225));
		panel_4.add(lbldownload, BorderLayout.CENTER);
		panel_4.repaint();
		panel_center.add(panel_youtube, BorderLayout.NORTH);
		
		chkboxLOOP = new JCheckBox("LOOP");
		chkboxLOOP.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				System.out.println(e);
				if( e.getStateChange() == e.SELECTED ) {
					download.mpv.mpv_set_property_string( download.handle,"loop", "yes");
				}else {
					download.mpv.mpv_set_property_string( download.handle,"loop", "no");
				}
			}
		});

		panel_youtube.add(chkboxLOOP);
		
		drawPanel = new DrawPanel(this);
		/**********
		if( props.getProperty("ptitle") != null ) {
			drawPanel.setTitle(props.getProperty("ptitle"));
		}
		if( props.getProperty("pimg") != null ) {
			try {
				drawPanel.setImage( ImageIO.read(new URL(props.getProperty("pimg"))));
			} catch (MalformedURLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if( props.getProperty("purl") != null ) {
			drawPanel.setURL(props.getProperty("purl"));
		}
		/**********/
		panel_youtube.add(drawPanel);
		
		lblNewLabel_2 = new JLabel("");
		try {
			lblNewLabel_2.setIcon( new ImageIcon("./next3.png"));
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
		lblNewLabel_2.setPreferredSize(new Dimension(100, 100));
		panel_youtube.add(lblNewLabel_2);
		
		JPanel panel_center2 = new JPanel();
		panel_center.add(panel_center2, BorderLayout.CENTER);
		panel_center2.setLayout(new BorderLayout(0, 0));
		
		scrollPane = new JScrollPane();
		panel_center2.add(scrollPane, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 204, 102));
		scrollPane.setViewportView(panel);
		
		
		lblNewLabel_1 = new JLabel("");
		try {
			lblNewLabel_1.setIcon( new ImageIcon("./next2.png"));
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		lblNewLabel_1.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblNewLabel_1.setPreferredSize(new Dimension(100, 100));
		//panel.add(lblNewLabel_1);
		
		for( int i = 0 ; i < drawPanelarray.length ; i++)
		{
			drawPanelarray[i] = new DrawPanel(this);
			
			/*********
			if( props.getProperty("ptitle1") != null ) {
				drawPanelarray[i].setTitle(props.getProperty("ptitle1"));
			}
			if( props.getProperty("pimg1") != null ) {
				try {
					drawPanelarray[i].setImage( ImageIO.read(new URL(props.getProperty("pimg1"))));
				} catch (MalformedURLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			if( props.getProperty("purl1") != null ) {
				drawPanelarray[i].setURL(props.getProperty("purl1"));
			}
			/*********/
			
			panel.add(drawPanelarray[i]);
		}
		//panel.setLayout(new BorderLayout(0, 0));
		
		this.setIconImage(new ImageIcon("./mainicon.png").getImage());
		//vlcPanel = new UTubeFreeVlcPanel(this);
		//JPanel panel_imsi = new JPanel();
		//panel.add(vlcPanel, BorderLayout.CENTER);
				
		this.setTitle("UTube Viewer V1.0");
		this.setVisible(true);
		test = new MPVFrame(this);
		try {
			test.frame.setBounds( Integer.parseInt((String) getProperty("mpvwindowx")) 
							,Integer.parseInt((String) getProperty("mpvwindowy"))
							, Integer.parseInt((String) getProperty("mpvwindowwidth"))
							, Integer.parseInt((String) getProperty("mpvwindowheight")));
		}catch(Exception ex)
		{
			test.frame.setBounds(this.getX()+this.getWidth(),this.getY(),600,620);
		}
		download = new UTubeDownload(this);
		//test.frame.setVisible(false);
		
	}

	
	public void setProperty(String key, String value)
	{
		try {
			String pattern = "yyyy-MM-dd HH:mm:ss";
		    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		    
			props.setProperty(key, value);
			Calendar cal = Calendar.getInstance();
			try {
				props.store(new FileOutputStream(properties),"last update " + simpleDateFormat.format(cal.getTime()));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
	
	public String getProperty(String key)
	{
		try {
			props.load(new FileInputStream( properties ));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return props.getProperty(key);
	}
	
}



