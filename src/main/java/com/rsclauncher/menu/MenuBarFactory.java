package com.rsclauncher.menu;

import java.util.Arrays;
import java.util.List;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MenuBarFactory {
  public static JMenuBar newMenuBar(Object client, ClassLoader classLoader) {
    final JMenuBar menuBar = new JMenuBar();
    final JMenu debugMenu = new JMenu("Debug");

    final List<MenuItem> menuItems = Arrays.asList(
        new PrintSceneVariables(client, classLoader),
        new PrintStaticVariable(classLoader)
    );

    menuItems.forEach(menuItem -> {
      final JMenuItem jmenuItem = new JMenuItem(menuItem.title());
      jmenuItem.addActionListener(menuItem);

      debugMenu.add(jmenuItem);
    });

    menuBar.add(debugMenu);
    return menuBar;
  }
}
