package com.rsclauncher.patcher;

import org.objectweb.asm.tree.ClassNode;

// TODO: Can possibly compose chains of ClassPatchers
public abstract class ClassPatcher {

  public abstract ClassNode patch(ClassNode classNode);

}
