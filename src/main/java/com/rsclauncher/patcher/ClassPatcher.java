package com.rsclauncher.patcher;

import org.objectweb.asm.tree.ClassNode;

public abstract class ClassPatcher {

  public abstract ClassNode patch(ClassNode classNode);

}
