package com.rsclauncher.patcher;

import org.objectweb.asm.tree.ClassNode;

public class ASMUtils {

  public static ClassNode copyClassNode(ClassNode originalClassNode) {
    ClassNode newClassNode = new ClassNode();
    originalClassNode.accept(newClassNode);

    return newClassNode;
  }

}
