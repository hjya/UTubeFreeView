import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import java.awt.event.WindowFocusListener;
import java.util.Calendar;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseWheelListener;
import java.awt.event.MouseWheelEvent;

public class MPVFrame {

	public JFrame frame;
	UTubeFreeJFrame mframe;
	String mtime;
	boolean isVisible = false;
	double playtime;
	double totaltime;
	int y = -65536;
	int x = -65536;
	JPanel contentPane;
	JLabel lblpic;
	//UTubeDownload download;
	/**
	 * Launch the application.
	 *
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					test2 window = new test2();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	/**********/
	/**
	 * Create the application.
	 */
	public MPVFrame(UTubeDownload download) {
		//this.download = download;
		initialize();
	}
	public MPVFrame(UTubeFreeJFrame mframe) {
		this.mframe = mframe;
		initialize();
		
		
		new Thread(new Runnable() {
			public void run()
			{
				while(true) {
					try {
							try {
								
								
								Calendar cal = Calendar.getInstance();
								playtime =  Double.parseDouble(mframe.download.getmpvProperty("playback-time"));
								double remaintime =  Double.parseDouble(mframe.download.getmpvProperty("time-remaining"));
								totaltime = playtime + remaintime;
								cal.setTimeInMillis( (long)playtime*1000 );
								mtime = cal.get(Calendar.HOUR-1) + ":" + cal.get(Calendar.MINUTE) + ":" +cal.get(Calendar.SECOND);
								cal.setTimeInMillis( (long)totaltime*1000 );
								mtime = mtime + " / " +cal.get(Calendar.HOUR-1) + ":" + cal.get(Calendar.MINUTE) + ":" +cal.get(Calendar.SECOND);
								double per = Double.parseDouble( mframe.download.getmpvProperty("percent-pos") );
								mtime = mtime + " ("+ (int)per +"%)";
								mtime = mtime + " Volume " + String.valueOf( (int)(Double.parseDouble( mframe.download.getmpvProperty("volume"))) );
								//mtime = mtime + "     " + (long)playtime*100 + "    " + (long)totaltime*100;
								
								if( remaintime < 1) {
									if( !mframe.chkboxLOOP.isSelected() ) {
										mframe.freeview.driver.get( ((DrawPanel)mframe.drawPanelarray[0]).getURL() );
									}
								}
								
								
							}catch(Exception ex)
							{
								//ex.printStackTrace();
							}
							
							Thread.sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}).start();
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("MPV");
		contentPane = new JPanel();
		frame.add(contentPane);
		frame.getContentPane().addMouseWheelListener(new MouseWheelListener() {
			public void mouseWheelMoved(MouseWheelEvent e) {
				
				
				double vol = Double.parseDouble( mframe.download.getmpvProperty("volume"));
				//System.out.println(vol);
				if( e.getWheelRotation() == 1) {
					vol--;
					if( vol < 0 )
					{
						vol = 0;
					}
					 mframe.download.setmpvProperty("volume", String.valueOf(vol));
				}else {
					vol++;
					if( vol > 130 ) {
						vol = 130;
					}
					 mframe.download.setmpvProperty("volume", String.valueOf(vol));
				}
				mframe.setProperty("volume", String.valueOf(vol));
				mframe.download.mpv.mpv_set_property_string( mframe.download.handle,"osd-level", "3");
				mframe.download.mpv.mpv_set_property_string( mframe.download.handle,"osd-msg3", mtime);
				mframe.download.mpv.mpv_set_property_string( mframe.download.handle,"gpu-sw", "yes");
				
				
			}
		});
		frame.getContentPane().addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				
				
				
			}
			public void mouseDragged(MouseEvent e)
			{
								
				/************/
				if( x != -65536 && Math.abs(y - e.getY()) < 100 )
				{
					if( x < e.getX() )
					{
						double res = playtime+((totaltime/100)+1);
						mframe.download.setmpvProperty("playback-time",String.valueOf(res));
					}else {
						double res = playtime+(-(totaltime/100)-1);
						mframe.download.setmpvProperty("playback-time",String.valueOf(res));
					}
					
				}
				/************/
				y = e.getY();
				x = e.getX();
				
			}
		});
		frame.getContentPane().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				
				
				new Thread(new Runnable() {
					public void run()
					{
						isVisible = true;
						while(isVisible) {
						/************/
							mframe.download.mpv.mpv_set_property_string( mframe.download.handle,"osd-level", "3");
							mframe.download.mpv.mpv_set_property_string( mframe.download.handle,"osd-msg3", mtime);
							/*********
							try {
								Thread.sleep(3000);
							} catch (InterruptedException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							/*********/
							//mframe.download.mpv.mpv_set_property_string( mframe.download.handle,"osd-level", "0");		
						/************/
							try {
								Thread.sleep(100);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}
				}).start();
				
				//download.mpv.mpv_command(download.handle, new String[] {"title", "true"});

				//download.mpv.mpv_set_property_string(download.handle, "player-operation-mode","pseudo-gui");
					
				/**************
				download.mpv.mpv_set_property_string(download.handle,"osd-level", "3");
				
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				download.mpv.mpv_set_property_string(download.handle,"osd-level", "0");
				/**************/
			}
			@Override
			public void mouseExited(MouseEvent e) {
				isVisible = false;
				mframe.download.mpv.mpv_set_property_string( mframe.download.handle,"osd-level", "0");
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				
				if( e.getButton() == 1 && e.getClickCount() == 2 ) {
					
					if( e.getX() < frame.getWidth()/2 ) {
						
						double res = playtime+(-(totaltime/100)-10);

						
						mframe.download.setmpvProperty("playback-time",String.valueOf(res));
						
					}else if( e.getX() > frame.getWidth()/2 ) {
						
						double res = playtime+(totaltime/100+10);
						mframe.download.setmpvProperty("playback-time",String.valueOf(res));

					}
					
					//System.out.println(e.toString());
					//download.setProperty("pause","yes");
					/*********
					if( download.getProperty("pause").equals("yes") ) {
						download.setProperty("pause", "no");
					}else {
						download.setProperty("pause", "yes");
					}
					/*********/

				}
				if( e.getButton() == 3 )
				{
					if( mframe.download.getmpvProperty("pause").equals("yes") ) {
						mframe.download.setmpvProperty("pause", "no");
					}else {
						mframe.download.setmpvProperty("pause", "yes");
					}
				}
			}
			@Override
			public void mouseReleased(MouseEvent e)
			{
				//System.out.println(e.toString());
			}
			
			
		});
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				//download.mpv.mpv_free((download.hwnd.getPointer()));
				mframe.setWinBound();
				if( mframe.download.getmpvProperty("pause").equals("yes") ) {
				}else {
					mframe.download.setmpvProperty("pause", "yes");
				}
			}
		});
		frame.addWindowFocusListener(new WindowFocusListener() {
			public void windowGainedFocus(WindowEvent e) {
				mframe.setWinBound();
			}
			
			public void windowLostFocus(WindowEvent e) {
				mframe.setWinBound();
			}
		});
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		frame.setIconImage(new ImageIcon("./youtube_logo5.png").getImage());
		frame.setVisible(true);
	}
	
	public void show(String title)
	{
		if( frame.isVisible() ) 
		{
			frame.setTitle(title);
		}else {
			frame.setVisible(true);
			frame.setTitle(title);
		}
	}
}
