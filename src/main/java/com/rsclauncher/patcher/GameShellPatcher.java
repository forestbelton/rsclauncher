package com.rsclauncher.patcher;


import org.objectweb.asm.tree.*;

import static org.objectweb.asm.Opcodes.*;

public class GameShellPatcher extends ClassPatcher {

  @Override
  public ClassNode patch(ClassNode classNode) {
    for (int i = 0; i < classNode.methods.size(); i++) {
      MethodNode methodNode = classNode.methods.get(i);

//      if (methodNode.name.equals("mousePressed")) {
//        InsnList newInstructions = new InsnList();
//        newInstructions.add(new FieldInsnNode(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;"));
//        newInstructions.add(new VarInsnNode(ALOAD, 1));
//        newInstructions.add(new MethodInsnNode(INVOKEVIRTUAL, "java/awt/event/MouseEvent", "getX", "()I", false));
//        newInstructions.add(new MethodInsnNode(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(I)V", false));
//        newInstructions.add(new FieldInsnNode(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;"));
//        newInstructions.add(new VarInsnNode(ALOAD, 1));
//        newInstructions.add(new MethodInsnNode(INVOKEVIRTUAL, "java/awt/event/MouseEvent", "getY", "()I", false));
//        newInstructions.add(new MethodInsnNode(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(I)V", false));
//
//        methodNode.instructions.insert(newInstructions);
//      }
    }

    return classNode;
  }

}
