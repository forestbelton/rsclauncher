package com.rsclauncher.menu;

import com.rsclauncher.api.Client;

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
        new PrintStaticVariable(classLoader),
        new PrintSceneVariables(client, classLoader),
        new PrintClientData((Client) client),
        new SetClientVariable(client),
        new PrintClientVariables(client, classLoader),
        new DumpZVars(classLoader),
        new TestAction((Client) client)
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
