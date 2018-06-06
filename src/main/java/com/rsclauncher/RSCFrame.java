package com.rsclauncher;

import java.applet.AppletContext;
import java.applet.AppletStub;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import javax.swing.JFrame;

public class RSCFrame extends JFrame implements AppletStub {

  private static final int GAME_WORLD = 2;
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
      return new URL("http://classic" + GAME_WORLD + ".runescape.com/");
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
