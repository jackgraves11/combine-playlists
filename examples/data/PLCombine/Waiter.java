package data.PLCombine;

import org.apache.hc.core5.http.io.HttpServerRequestHandler;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.awt.*;
import java.io.IOException;
import java.net.*;

public class Waiter {

  public Waiter() throws MalformedURLException, URISyntaxException {
  }

  public void test() throws URISyntaxException {
    try {
      URL u = new URL("http://google.com");
      HttpURLConnection hr = (HttpURLConnection) u.openConnection();
      System.out.println(hr.getResponseCode());
      System.out.println(hr.getURL());
    } catch (MalformedURLException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }

  }

  public void main() throws URISyntaxException, InterruptedException {
    test();
    /**
    WebDriver driver = new SafariDriver();
    driver.get("http://google.com");
    String currentURL = driver.getCurrentUrl();
    System.out.println(currentURL);
     */
  }
}
