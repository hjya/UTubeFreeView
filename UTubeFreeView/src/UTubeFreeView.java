import java.awt.Image;
import java.awt.Toolkit;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UTubeFreeView{

	public WebDriver driver;
	private WebElement webElement;
	WebDriverWait wait;
	
	public final String WEB_DRIVER_ID = "webdriver.chrome.driver";
	public final String WEB_DRIVER_PATH = ".\\libs\\chrome\\chromedriver.exe";
	
	private String driver_url = "";
	private String img = "";
	UTubeFreeJFrame jframe;

	
	public String og_title = "";
	private String og_image = "";
	private String og_url = "";
	private String og_embedurl = "";
	
	private String og_next_title = "";
	private String og_next_image = "";
	private String og_next_url = "";
	
	public String driver_url_next = "";
	public String title_next = "";
	
	public boolean ispass = false;
	
	public UTubeFreeView(UTubeFreeJFrame jframe, String url)
	{
		this.jframe = jframe;
		
		taskkill();
		selleniumInit();
		
		driver.get(url);
		
		new Thread(new Runnable() {
			public void run()
			{
				while(true) {
					try {
						
						//System.out.println(driver.getWindowHandles().size());
						if( driver.getWindowHandles().size() > 1 )
						{
							System.out.println(driver.getCurrentUrl());
							
							ArrayList<String> openTabs = new ArrayList<String> (driver.getWindowHandles());
							//String handle = ;
							driver.switchTo().window(openTabs.get(1));
							String imurl = driver.getCurrentUrl();
							driver.close();   
							driver.switchTo().window(openTabs.get(0));
				            //driver.navigate().to(imurl);
				            
						}

						//if( !currentUrl.equals(driver_url) )
						//System.out.println("ogtitle = " + ogtitle + " :: imsititle = " + imsititle);
						/************
						if(currentUrl.contains("embed")) {

							try {
								wait = new WebDriverWait(driver, 1);
								wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("ytp-time-current")));
								String cutime = driver.findElement(By.className("ytp-time-current")).getText();
								String dutime = driver.findElement(By.className("ytp-time-duration")).getText();
								if( cutime != null && dutime != null ) {
									if( !cutime.equals("") && !dutime.equals("")) {
										if( cutime.equals(dutime)) {
											driver.get(jframe.drawPanelarray[0].getURL());
										}
									}
								}
							}catch(Exception ex)
							{
								//ex.printStackTrace();
							}
							
						}
						/************/

						
						
						if( (!og_title.equals(driver.getTitle()) && !og_url.equals(driver.getCurrentUrl())) || ( ispass ) )// && 
						{
							ispass = false;
							String mimage = "";
							try {
								
								try {
									
									System.out.println("########################################");
									System.out.println(og_title + " :: " + driver.getTitle());
									System.out.println(og_url + " :: " + driver.getCurrentUrl());
									System.out.println("########################################");
									
									og_title = driver.getTitle();
									og_url = driver.getCurrentUrl();

									System.out.println("########################################");
									System.out.println(og_title + " :: " + driver.getTitle());
									System.out.println(og_url + " :: " + driver.getCurrentUrl());
									System.out.println("########################################");									
									
									/************/
									new Thread(new Runnable() {
										public void run()
										{
											if( og_url.contains("watch?v=") ) {
												jframe.download.playMPV(og_url,og_title);
											}
										}
									}).start();
									/************/
									
									//jframe.download.playMPV(og_url,og_title);
									
									try {
										wait = new WebDriverWait(driver, 1);
										wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ytd-player")));
										//driver.findElement(By.id("ytd-player")).click();
										
										try {
											wait = new WebDriverWait(driver, 1);
											wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button[class='ytp-mute-button ytp-button']")));
											String mute = driver.findElement(By.cssSelector("button[class='ytp-mute-button ytp-button']")).getAttribute("aria-label");
											System.out.println("mute ===== " + mute);
											if( mute.contains("해제") ) {
												
											}else {
												driver.findElement(By.cssSelector("button[class='ytp-mute-button ytp-button']")).click();
											}
										}catch(Exception ex)
										{
											
										}
										try {
											wait = new WebDriverWait(driver, 1);
											wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("paper-toggle-button[id='toggle']")));
											String autoplay = driver.findElement(By.cssSelector("paper-toggle-button[id='toggle']")).getAttribute("aria-pressed");
											System.out.println("autoplay ===== " + autoplay);
											if( autoplay.contains("true") ) {
												driver.findElement(By.cssSelector("div[id='toggleButton']")).click();
												//driver.findElement(By.cssSelector("div[id='toggleButton']")).sendKeys(Keys.ENTER);
											}else {
												
											}
										}catch(Exception ex)
										{
											
										}
									}catch(Exception ex)
									{
										
									}
									
									try {
										mimage = driver.findElement(By.cssSelector("link[itemprop='thumbnailUrl']")).getAttribute("href");
										if(mimage != null && !mimage.equals(""))
											jframe.drawPanel.setImage(ImageIO.read(new URL(mimage)));
									}catch(Exception ex) {
										
									}
									
									/*************/
									driver.navigate().refresh();
																		
									wait = new WebDriverWait(driver, 1);
									wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ytd-player")));
									//driver.findElement(By.id("ytd-player")).click();
									/*************/
									
									try {
										wait = new WebDriverWait(driver, 1);
										wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button[class='ytp-mute-button ytp-button']")));
										String mute = driver.findElement(By.cssSelector("button[class='ytp-mute-button ytp-button']")).getAttribute("aria-label");
										System.out.println("mute ===== " + mute);
										if( mute.contains("해제") ) {
											
										}else {
											driver.findElement(By.cssSelector("button[class='ytp-mute-button ytp-button']")).click();
										}
									}catch(Exception ex)
									{
										
									}
									try {
										wait = new WebDriverWait(driver, 1);
										wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("paper-toggle-button[id='toggle']")));
										String autoplay = driver.findElement(By.cssSelector("paper-toggle-button[id='toggle']")).getAttribute("aria-pressed");
										System.out.println("autoplay ===== " + autoplay);
										if( autoplay.contains("true") ) {
											driver.findElement(By.cssSelector("div[id='toggleButton']")).click();
											//driver.findElement(By.cssSelector("div[id='toggleButton']")).sendKeys(Keys.ENTER);
										}else {
											
										}
									}catch(Exception ex)
									{
										
									}
									
								}catch(Exception ex)
								{
									ex.printStackTrace();
								}
								/*************
								WebDriverWait wait = new WebDriverWait(driver, 3);
								wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//link[@itemprop='thumbnailUrl']")));
								og_image = driver.findElement(By.xpath("//link[@itemprop='thumbnailUrl']")).getAttribute("href");
								String embedurl = driver.findElement(By.xpath("//link[@itemprop='embedurl']")).getAttribute("href");
								System.out.println("og_image == " + og_image + " :: embedurl = " + embedurl);
								/*************/
								
								String viewcnt = "";// [조회수] "+driver.findElement(By.cssSelector("meta[itemprop='interactionCount']")).getAttribute("content")+"] ";
								String published = "";// [" + driver.findElement(By.cssSelector("meta[itemprop='datePublished']")).getAttribute("content").trim()+"] ";
								
								try {
									 viewcnt = " [조회수] "+driver.findElement(By.cssSelector("meta[itemprop='interactionCount']")).getAttribute("content")+"] ";
								}catch(Exception ex)
								{
									ex.printStackTrace();
								}
								try {
									published = " [" + driver.findElement(By.cssSelector("meta[itemprop='datePublished']")).getAttribute("content").trim()+"] ";
								}catch(Exception ex)
								{
									ex.printStackTrace();
								}
								
								String mtitle = "";//
								try{
									mtitle = driver.findElement(By.cssSelector("meta[itemprop='name']")).getAttribute("content");
								}catch(Exception ex)
								{
									ex.printStackTrace();
								}
								System.out.println(mtitle + " ::  " + viewcnt + published );
								
								jframe.drawPanel.setTitle(mtitle);
								jframe.lbltitle.setText(mtitle);
								jframe.lbltitle_view.setText(viewcnt + published );
								jframe.repaint();
								
								String murl = "";
								
								try {
									murl = driver.findElement(By.cssSelector("link[itemprop='embedUrl']")).getAttribute("href");
									String oriurl = driver.findElement(By.cssSelector("link[itemprop='url']")).getAttribute("href");
									jframe.drawPanel.setURL(oriurl);
								}catch(Exception ex)
								{
									murl = driver.getCurrentUrl();
									jframe.drawPanel.setURL(murl);
								}
								try {
									mimage = driver.findElement(By.cssSelector("link[itemprop='thumbnailUrl']")).getAttribute("href");
									if(mimage != null && !mimage.equals(""))
										jframe.drawPanel.setImage(ImageIO.read(new URL(mimage)));
								}catch(Exception ex) {
									
								}
								
								jframe.ptitle = mtitle;
								jframe.purl = driver.getCurrentUrl();
								jframe.pimg = mimage;
								
								//new Thread(new Runnable() {
								//	public void run()
								//	{
										
										wait = new WebDriverWait(driver, 3);
										wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("dismissable")));
										List<WebElement> eles = driver.findElements(By.id("dismissable"));
										int cnt = 0; 
										for( WebElement ele : eles )
										{
											try {
												System.out.println(cnt + " ******************************************************* ");
												System.out.println(ele.findElement(By.id("video-title")).getAttribute("title"));
												System.out.println(ele.findElement(By.id("thumbnail")).getAttribute("href"));
												System.out.println(ele.findElement(By.id("img")).getAttribute("src").split("\\?")[0]);
												System.out.println(" ******************************************************* ");
												
												String title = ele.findElement(By.id("video-title")).getAttribute("title");
												title = title + " [" + ele.findElement(By.id("overlays")).getText().trim()+"]";
												String imsistr = ele.findElement(By.id("metadata-line")).getText().trim();
												imsistr = imsistr.replaceAll("\r\n", "");
												title = title + " [" + imsistr +"]";
														
												jframe.drawPanelarray[cnt].setTitle(title);
												jframe.drawPanelarray[cnt].setURL(ele.findElement(By.id("thumbnail")).getAttribute("href"));
												
												String imgimsi = ele.findElement(By.id("img")).getAttribute("src").split("\\?")[0];
												//jframe.drawPanelarray[cnt].setImage(new ImageIcon(imgimsi).getImage());
												if(imgimsi != null && !imgimsi.equals(""))
													jframe.drawPanelarray[cnt].setImage( ImageIO.read(new URL(imgimsi )));
												jframe.drawPanelarray[cnt].repaint();
												
												jframe.ptitle1 = title;
												jframe.purl1 = ele.findElement(By.id("thumbnail")).getAttribute("href");
												jframe.pimg1 = ele.findElement(By.id("img")).getAttribute("src").split("\\?")[0];
												
											}catch(Exception ex)
											{
												ex.printStackTrace();
											}
											cnt++;
											if(cnt >= jframe.drawPanelarray.length) {
												break;
											}
											
										}
										
								//	}
								//}).start();
								
								
								
								jframe.drawPanel.repaint();
								jframe.repaint();
								
								
								/***********
								driver.get(og_url);
								wait = new WebDriverWait(driver, 3);
								wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@aria-label='재생']")));
								driver.findElement(By.xpath("//button[@aria-label='재생']")).click();
								
								try {
									WebDriverWait wait = new WebDriverWait(driver, 3);
									wait.until(ExpectedConditions.visibilityOfElementLocated(By.className( "ytp-error-content" )));
									if( driver.findElement(By.className( "ytp-error-content" )).isEnabled() )
									{
										jframe.download.playMPV(driver.getCurrentUrl());
										//String imsi = og_url.split("embed/")[1];
										//driver.get( "https://www.youtube.com/watch?v="+imsi+"?youtubevew=yes" );
										//jframe.download.playMPV(driver.getCurrentUrl());
									}
								}catch(Exception ex)
								{
									ex.printStackTrace();
								}
								/****************/
								//System.out.println("embedUrl == " + 
								//System.out.println("embedUrl == " + 
								
								//((DrawPanel)jframe.panel_center2_center).setImage( ImageIO.read(new URL(og_image)) );
								//((DrawPanel)jframe.panel_center2_center).repaint();
								
								/***********
								
								
								//driver.navigate().refresh();
								//driver.findElement(By.id("ytd-player")).click();
								System.out.println("imsititle = " + imsititle);
								ogtitle = driver.getTitle();
								//driver.findElement(By.xpath("//meta[@property='og:title']");
								//img = driver.findElement(By.xpath("//meta[@property='og.image']")).getAttribute("content");
								try {
									img = driver.findElements(By.id("img")).get(2).getAttribute("src").toString().split("\\?")[0];
									System.out.println(img);
								}catch(Exception ex)
								{
									ex.printStackTrace();
								}
								try {
									currentUrl = currentUrl.split("\\&")[0];
									System.out.println(currentUrl);
								}catch(Exception ex)
								{
									ex.printStackTrace();
								}
								/*************
								String imsiurl = "https://www.youtube.com/embed/"+currentUrl.split("\\=")[1];
								driver.get(imsiurl);
								System.out.println(currentUrl + " :: ");
								
								/*************/
								
								//jframe.textarea_search.setText(og_url);
								//jframe.textarea_title.setText(og_title);
								
								
								
							}catch(Exception ex)
							{
								ex.printStackTrace();
							}
							//do nothing;;;;;;;
						}
						/*************
						else if( false ){
							driver_url = driver.getCurrentUrl();
							if( driver_url.contains("watch?v="))
							{
								WebElement ele = null;
								String imgurl = "";
								try {
									
									List<WebElement> eles = driver.findElements(By.id("content"));//"));
									
									Thread.sleep(1000);
									
									for( int m=0;m<eles.size();m++)
									{
										try {
											String href = eles.get(m).findElement(By.id("video-title-link")).getAttribute("href");
											
											driver_url_next = eles.get(m+1).findElement(By.id("video-title-link")).getAttribute("href");
											title_next = eles.get(m+1).findElement(By.id("video-title-link")).getAttribute("title");
											
											if( driver_url.contains(href)) {
												og_title = eles.get(m).findElement(By.id("video-title-link")).getAttribute("title");
												imgurl = eles.get(m).findElement(By.id("img")).getAttribute("src").toString().split("\\?")[0];
												break;
											}
											
										}catch(Exception ex)
										{
											
											ex.printStackTrace();
	
											
										}
									}
									
								}catch(Exception ex)
								{
									ex.printStackTrace();
								}
								extenalPlayerRun(driver_url, og_title, imgurl);
							}else if( driver_url.contains("https://tv.naver.com/v/") )
							{
								og_title = driver_url;
								//imgurl = "";
								extenalPlayerRun(driver_url, og_title, "");
							}else if( driver_url.contains("https://tv.kakao.com/channel/") )
							{
								og_title = driver_url;
								//imgurl = "";
								extenalPlayerRun(driver_url, og_title, "");
							}
							System.out.println("driver_url = " + driver_url);
							System.out.println("driver_url_next = " +  driver_url_next + " :: title_next = " + title_next);
						}
						/*************/
						try {
							Thread.sleep(100);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}catch(Exception ex)
					{
						ex.printStackTrace();
						System.exit(1);
					}
				}
			}
		}).start();
		
	}
	
	/**********
	void extenalPlayerRun(String url, String title, String imgsrc)
	{
		try {
			//Runtime.getRuntime().exec("C:\\Program Files\\SMPlayer\\smplayer.exe "+ url);
			System.out.println(title);
			System.out.println(imgsrc);
			this.jframe.setTitle(title);
			if( url != null && !url.equals("")) {
				//this.jframe.vlcPanel.vlcPlay(url,title);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**********/
	
	void taskkill()
	{
		Process process = null;
		try {
			process = Runtime.getRuntime().exec("tasklist.exe");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Scanner scanner = new Scanner(new InputStreamReader(process.getInputStream()));
	    while (scanner.hasNext()) {
	        String proc = scanner.nextLine();
	        if( proc.contains("chromedriver.exe") )
	        {
	        	try {
					Runtime.getRuntime().exec("taskkill /F /IM chromedriver.exe");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        }
	    }
	}
	
	private void selleniumInit()
	{
	
		System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH);
	    /************/
	    ChromeOptions options = new ChromeOptions();
	    //options.addArguments("--headless");
	    //options.addArguments("--no-sandbox");
	    options.addArguments("window-size=100,100");

	    options.setCapability("ignoreProtectedModeSettings", true);
	    options.addArguments("--disable-popup-blocking");
	    driver = new ChromeDriver(options);
	
	}
	
	
}
