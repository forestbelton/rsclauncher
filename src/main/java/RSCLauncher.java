import java.applet.Applet;
import java.applet.AppletContext;
import java.applet.AppletStub;
import java.awt.Color;
import java.awt.Dimension;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import javax.swing.JFrame;

public class RSCLauncher {

  public static class RSCFrame extends JFrame implements AppletStub {

    private static final int world = 2;
    private static final HashMap<String, String> gameParameters = new HashMap<>();

    static {
      gameParameters.put("java_arguments", "-Xmx96m -Dsun.java2d.noddraw=true");
      gameParameters.put("nodeid", "5004");
      gameParameters.put("modewhere", "0");
      gameParameters.put("modewhat", "0");
      gameParameters.put("servertype", "1");
      gameParameters.put("advertsuppressed", "1");
      gameParameters.put("js", "1");
      gameParameters.put("affid", "0");
    }

    public RSCFrame(String name) {
      super(name);
    }

    @Override
    public URL getDocumentBase() {
      return getCodeBase();
    }

    @Override
    public URL getCodeBase() {
      try {
        return new URL("http://classic" + world + ".runescape.com/");
      } catch (MalformedURLException ex) {
        return null;
      }
    }

    @Override
    public String getParameter(String name) {
      return gameParameters.get(name);
    }

    @Override
    public AppletContext getAppletContext() {
      return null;
    }

    @Override
    public void appletResize(int width, int height) {
    }
  }

  private static String decryptString(String s, char[] key) {
    final char[] out = s.toCharArray();

    for (int i = 0; i < out.length; ++i) {
      out[i] = (char)(out[i] ^ key[i % key.length]);
    }

    return new String(out);
  }

  public static class AppletThread extends Thread {

    public final Applet applet;

    public AppletThread(Applet applet) {
      this.applet = applet;
    }

    @Override
    public void run() {
      while (true) {
        try {
          Thread.sleep(100);
        } catch (InterruptedException ex) {}
      }
    }
  }

  public static void main(String[] args) throws Exception {
    final RSCFrame frame = new RSCFrame("RSCLauncher");

    final Class<?> clientClass = ClassLoader.getSystemClassLoader().loadClass("client");
    final Applet clientApplet = Applet.class.cast(clientClass.newInstance());

    clientApplet.setStub(frame);

    frame.setContentPane(clientApplet);
    frame.getContentPane().setBackground(Color.BLACK);
    frame.getContentPane().setPreferredSize(new Dimension(512, 346));

    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.pack();
    frame.setVisible(true);

    clientApplet.init();
    clientApplet.start();

    new AppletThread(clientApplet).start();
  }
}
