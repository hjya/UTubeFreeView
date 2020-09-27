import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;

public class DrawPanel extends JPanel{
	Image img;
	String title = "";
	String url = "";
	String embedurl = "";
	JButton btnImage;
	JLabel lbltitle;
	/**
	 * @wbp.parser.constructor
	 */
	public DrawPanel(UTubeFreeJFrame frame)
	{
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				
				
			}
			public void mouseClicked(MouseEvent e) {

				
			}
		});
		setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		this.setPreferredSize(new Dimension(200, 200));
		setLayout(new BorderLayout(0, 0));
		
		btnImage = new JButton("");
		btnImage.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				
				/*********
				String imsiurl = "";
				try {
					imsiurl = "https://www.youtube.com/embed/"+url.split("\\=")[1];
					frame.freeview.driver.get(imsiurl);
				}catch(Exception ex)
				{
					frame.freeview.driver.get(url);
				}
				/*************/
				//frame.download.playMPV(url);
				
				frame.freeview.driver.get(url);
				frame.freeview.ispass = true;
				/*********
				try {
					frame.freeview.driver.get(url);
					WebDriverWait wait = new WebDriverWait(frame.freeview.driver, 3);
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@aria-label='Àç»ý']")));
					
					frame.freeview.driver.findElement(By.xpath("//button[@aria-label='Àç»ý']")).click();			
				}catch(Exception ex)
				{
					ex.printStackTrace();
				}
				try {
					WebDriverWait wait = new WebDriverWait(frame.freeview.driver, 3);
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.className( "ytp-error-content" )));
					if( frame.freeview.driver.findElement(By.className( "ytp-error-content" )).isEnabled() )
					{
						//String imsi = url.split("embed/")[1];
						//frame.freeview.driver.get( "https://www.youtube.com/watch?v="+imsi+"?youtubevew=yes" );
						frame.download.playMPV(url);
					}
				}catch(Exception ex)
				{
					ex.printStackTrace();
				}
				/***************/
			}
		});
		add(btnImage, BorderLayout.CENTER);
		
		lbltitle = new JLabel("");
		lbltitle.setBackground(new Color(255, 255, 204));
		lbltitle.setPreferredSize(new Dimension(20, 30));
		lbltitle.setFont(new Font("³ª´®°íµñ", Font.BOLD, 12));
		lbltitle.setHorizontalAlignment(SwingConstants.CENTER);
		add(lbltitle, BorderLayout.SOUTH);
	}

	public void setImage(Image img)
	{
		this.img = img;

		Image newimg = img.getScaledInstance(btnImage.getWidth(), btnImage.getHeight(), java.awt.Image.SCALE_SMOOTH);
		this.btnImage.setIcon(new ImageIcon(newimg));
	}
	
	public void setTitle(String text)
	{
		this.title = text;
		this.lbltitle.setText(text);
	}
	public void setURL(String url)
	{
		this.url = url;
	}
	public void setEmbedURL(String embedurl)
	{
		this.embedurl = embedurl;
	}
	public String getURL()
	{
		return this.url;
		/*********
		if( !embedurl.equals("") ) {
			return this.embedurl;
		}else {
			return this.url;
		}
		/*********/
	}
	/*********
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
        
    }
    /**************/
}
