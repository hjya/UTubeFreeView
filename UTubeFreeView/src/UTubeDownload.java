import java.awt.Color;
import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.JPanel;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.ptr.LongByReference;

public class UTubeDownload {
	
	MPV mpv;
	long handle;
	int error;
	UTubeFreeJFrame frame;
	String msg = "";
	public WinDef.HWND hwnd;
	public LongByReference longByReference;
	boolean isfull = false;
	/***********
	public static void main(String args[])
	{
		new UTubeDownload();
	}
	/***********/
	public UTubeDownload()
	{
		//new test2(this);
		init();
		
		String murl = "https://tv.naver.com/v/15910051";
		/*****************/
		if((error = mpv.mpv_command(handle, new String[] {"loadfile", murl})) != 0) {
		      throw new IllegalStateException("Playback failed with error: " + error);
		}
		
		//
		/************
		mpv.mpv_set_option_string(handle, "show-progress", "yes");
		
		mpv.mpv_set_option_string(handle, "term-osd","force");
		mpv.mpv_set_property_string(handle, "term-osd","force");
		mpv.mpv_set_option_string(handle, "term-osd-bar","yes");
		mpv.mpv_set_option_string(handle, "term-osd-bar","yes");
		mpv.mpv_set_option_string(handle, "osd-bar", "yes");
		mpv.mpv_set_option_string(handle, "osc", "yes");
		mpv.mpv_set_option_string(handle, "osd-on-seek", "msg-bar");
		mpv.mpv_set_option_string(handle, "progress", "yes");
		mpv.mpv_set_option_string(handle, "osd-align-x", "0");
		mpv.mpv_set_option_string(handle, "osd-bar-align-y", "1");
		mpv.mpv_set_option_string(handle, "osd-bar-w", "100");
		mpv.mpv_set_option_string(handle, "osd-bar-w", "75");
		mpv.mpv_command(handle, new String[] {"show-progress","yes"});
		mpv.mpv_command(handle, new String[] {"osc","yes"});
		/*****************/
		//mpv.mpv_set_property_string(handle, "seekable","yes");
		//mpv.mpv_set_property_string(handle, "loop","yes");
		//mpv.mpv_set_property_string(handle, "save-position-on-quit", "yes");
		//mpv.mpv_set_property_string(handle,"terminal","yes");

		mpv.mpv_set_property_string(handle, "geometry","720x480+500+100");
		mpv.mpv_set_property_string(handle, "keepaspect-window","no");


		new Thread(new Runnable() {
			public void run()
			{
				while(true) {
					try {
						
							/***********
							System.out.println(mpv.mpv_get_property_string(handle,"geometry"));
							System.out.println(mpv.mpv_get_property_string(handle,"current-window-scale"));
							System.out.println(mpv.mpv_get_property_string(handle,"window-scale"));
							System.out.println(mpv.mpv_get_property_string(handle,"dwidth"));
							System.out.println(mpv.mpv_get_property_string(handle,"dheight"));
							System.out.println(mpv.mpv_get_property_string(handle,"volume"));
							System.out.println(mpv.mpv_get_property_string(handle,"playtime-remaining"));
							System.out.println(mpv.mpv_get_property_string(handle,"time-remaining"));
							/***********/
							//System.out.println( mpv.mpv_wait_event(handle,1000).data. );
							//System.out.println();
							
					
							Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}).start();

	}
	
	public void getprop()
	{
		System.out.println( getmpvProperty ("ao-volume"));
		System.out.println( getmpvProperty ("current-window-scale"));
		System.out.println( getmpvProperty ("window-scale"));
		System.out.println( getmpvProperty ("dwidth"));
		System.out.println( getmpvProperty ("dheight"));		
	}
	
	public UTubeDownload(UTubeFreeJFrame frame)
	{
		this.frame = frame;
		init();
	    /**************/
		

	    
	}
	
	private void init()
	{
		/**************/
		mpv = MPV.INSTANCE;
	    handle = mpv.mpv_create();
	    // Get the native window id by looking up a window by title:
	    /***********/
	    hwnd = User32.INSTANCE.FindWindow(null, "MPV");

	    // Tell MPV on which window video should be displayed:
	    longByReference =
	        new LongByReference(Pointer.nativeValue(hwnd.getPointer()));
	    mpv.mpv_set_option(handle, "wid", 4, longByReference.getPointer());
	    /***********/
	    if((error = mpv.mpv_initialize(handle)) != 0) {
	      throw new IllegalStateException("Initialization failed with error: " + error);
	    }		
	    
		mpv.mpv_set_property_string(handle, "osd-font-size","30");
		mpv.mpv_set_property_string(handle, "osd-duration", "3");
		//mpv.mpv_set_property_string(handle, "osd-level","3");
		mpv.mpv_set_property_string(handle, "volume", frame.getProperty("volume"));
		mpv.mpv_set_property_string(handle, "osc","yes");
	}
	
	//public Pointer getProperty(String name) {
	//	return mpv.mpv_get_property_string(handle, "current-vo");
	//}
	
	public String getmpvProperty(String name) {
		return mpv.mpv_get_property_string(handle, name);
	}
	public void setmpvProperty(String name, String data)
	{
		mpv.mpv_set_property_string(handle, name, data);
	}
	public void playMPV(String murl, String title)
	{
		frame.test.show(title);
		if( frame.download.getmpvProperty("pause").equals("yes") ) {
			frame.download.setmpvProperty("pause", "no");
		}
		setmpvProperty("volume",frame.getProperty("volume"));
		
		/*****************/
		if((error = mpv.mpv_command(handle, new String[] {"loadfile", murl})) != 0) {
		      throw new IllegalStateException("Playback failed with error: " + error);
		}
		/*****************
		new Thread(new Runnable() {
			
			public void run()
			{	//" 
				String cmd =".\\mpv.com " + murl + " --volume=50 --autofit="+ (frame.freeview.driver.manage().window().getSize().getWidth()-200) + " --geometry="+
						(frame.freeview.driver.manage().window().getPosition().getX()+100)+":"+(frame.freeview.driver.manage().window().getPosition().getY()+100); 
		    	System.out.println(cmd);
		    	//new Thread(new Runnable(){

					//@Override
					//public void run() {
						// TODO Auto-generated method stub
				Runtime rt = Runtime.getRuntime( );
		        Process p = null;						
				try {
					p =  rt.exec(cmd);
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					
					
					BufferedReader buff = new BufferedReader(new InputStreamReader(p.getErrorStream()));
					
					int flag = 1;
					
		        	String msg = "";
		        	try {
						while((msg = buff.readLine()) != null) 
						{
							//System.out.print(msg);
							try {
								
								//[KAV: 00:03:51 / 00:03:51 (99%) A-V:  0.000 Cache: 0.0s/0KB
								//[KAV: 00:03:51 / 00:03:51 (99%) A-V: -0.000 Cache: 0.0s
								
								String imsi = msg.split("Cache:")[1].trim();
								String fimsi = imsi.split("s")[0].trim();
								//System.out.println(" :: " + imsi + " :: " + fimsi + " :: ");
								if( fimsi.equals("0.0")) {
									flag = 0;
								}else {
									flag = 1;
								}
							}catch(Exception ex)
							{
								
							}
						}
						
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				    
				    
					buff.close();
					if( flag == 0) {
						frame.freeview.driver.get( ((DrawPanel)frame.drawPanelarray[0]).getURL() );
					}
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}).start();
		/*****************/
		
	}
	public void setDownload(String title, String murl)
	{
		isfull = false;
		/***********
		if((error = mpv.mpv_command(handle, new String[] {"loadfile", murl})) != 0) {
		      throw new IllegalStateException("Playback failed with error: " + error);
		}	
		mpv.mpv_set_option_string(handle, "stream-record", "./"+title+".mp4");
		/**************/
		new Thread(new Runnable(){
			public void run() {
		        try
		        {
		        	
		        	frame.lbldownload.setBackground(Color.WHITE);
		        	
		        	String cmd =".\\youtube-dl.exe " + murl;// + " -f mp4"; 
		        	System.out.println(cmd);
		        	//new Thread(new Runnable(){
		
						//@Override
						//public void run() {
							// TODO Auto-generated method stub
					Runtime rt = Runtime.getRuntime( );
			        Process p = null;						
		    		try {
						p =  rt.exec(cmd);
						
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		    		
		    		try {
		
		    			/**********/
		    			BufferedReader buff = new BufferedReader(new InputStreamReader(p.getInputStream()));
		
			        	
			        	try {
							while((msg = buff.readLine()) != null) 
							{
								System.out.println(msg);
								//[download] 100.0% of 6.12MiB at 48.33MiB/s ETA 00:00
								try {
									new Thread(new Runnable() {
										public void run()
										{
											String persent = (msg.split("of")[0]).split("\\]")[1].trim();
											persent = persent.replaceAll("%", "").trim();
											//persent = persent.split("\\.")[0];
											double dbl = Double.parseDouble(persent);
											persent = String.valueOf( (int)dbl );
											System.out.println(" =================== " + Integer.parseInt(persent) );

											if ( !isfull ) {
												frame.panel_4.setWidth( Integer.valueOf( persent) );
												frame.lbldownload.setText(dbl + "%");
											}
											//System.out.println(Integer.valueOf(persent) );
											frame.panel_4.repaint();	
											if( dbl == 100) {
												isfull = true;
											}	
										}
									}).start();
									
								}catch(Exception ex)
								{
									
								}
							}
							
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
		
		    		    /*************/
		    		    
						buff.close();
						frame.lbldownload.setBackground(new Color(100, 149, 237));
						frame.lbldownload.setText("");
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			        		
		        }catch(Exception e2)
		        {
		        	System.out.println(e2);
		        }
			}
		}).start();
		
	}
	public void setMP3Download(String title, String murl)
	{
		isfull = false;
		/**************
		if((error = mpv.mpv_command(handle, new String[] {"loadfile", murl})) != 0) {
		      throw new IllegalStateException("Playback failed with error: " + error);
		}
		mpv.mpv_set_option_string(handle, "stream-record", "./"+title+".mp3");
		
		/**************/
		new Thread(new Runnable(){
			public void run() {
			
		        try
		        {
		        	frame.lbldownload.setBackground(Color.WHITE);
		        	
		        	String cmd =".\\youtube-dl.exe " + murl + " -x --audio-format mp3"; 
		        	System.out.println(cmd);
		        	//new Thread(new Runnable(){
		
						//@Override
						//public void run() {
							// TODO Auto-generated method stub
					Runtime rt = Runtime.getRuntime( );
			        Process p = null;						
		    		try {
						p =  rt.exec(cmd);
						
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		    		
		    		try {
		
		    			/**********/
		    			
		    			BufferedReader buff = new BufferedReader(new InputStreamReader(p.getInputStream()));
			        	try {
			        		
							while((msg = buff.readLine()) != null) 
							{
								System.out.println(msg);
								try {
									new Thread(new Runnable() {
										public void run()
										{
											String persent = (msg.split("of")[0]).split("\\]")[1].trim();
											persent = persent.replaceAll("%", "").trim();
											//persent = persent.split("\\.")[0];
											double dbl = Double.parseDouble(persent);
											persent = String.valueOf( (int)dbl );
											if ( !isfull ) {
												frame.panel_4.setWidth( Integer.valueOf( persent) );
												frame.lbldownload.setText(dbl + "%");
											}
											//System.out.println(Integer.valueOf(persent) );
											frame.panel_4.repaint();	
											if( dbl == 100) {
												isfull = true;
											}
										}
									}).start();
									
								}catch(Exception ex)
								{
									
								}
							}
							
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
		
		    		    /*************/
		    		    
						buff.close();
						frame.lbldownload.setBackground(new Color(100, 149, 237));
						frame.lbldownload.setText("");
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			        		
		        }catch(Exception e2)
		        {
		        	System.out.println(e2);
		        }
    	
			}
		}).start();
	}

}


