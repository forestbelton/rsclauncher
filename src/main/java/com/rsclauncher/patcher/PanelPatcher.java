package com.rsclauncher.patcher;

import org.objectweb.asm.tree.*;

import static org.objectweb.asm.Opcodes.*;

public class PanelPatcher extends ClassPatcher {

  private static final String O_CLASS_NAME = "ab";
  private static final String O_CONTROL_CLICKED = "m";

  @Override
  public ClassNode patch(ClassNode classNode) {
    classNode.interfaces.add("com/rsclauncher/api/Panel");

    MethodNode setControlClicked = new MethodNode(ACC_PUBLIC, "setControlClicked", "(I)V", null, null);
    setControlClicked.instructions.add(new VarInsnNode(ALOAD, 0));
    setControlClicked.instructions.add(new FieldInsnNode(GETFIELD, O_CLASS_NAME, O_CONTROL_CLICKED, "[Z"));
    setControlClicked.instructions.add(new VarInsnNode(ILOAD, 1));
    setControlClicked.instructions.add(new InsnNode(ICONST_1));
    setControlClicked.instructions.add(new InsnNode(BASTORE));
    setControlClicked.instructions.add(new InsnNode(RETURN));
    setControlClicked.maxStack = 3;
    setControlClicked.maxLocals = 2;
    classNode.methods.add(setControlClicked);

    for (int i = 0; i < classNode.methods.size(); i++) {
      MethodNode methodNode = classNode.methods.get(i);

//      if (methodNode.name.equals("a") && methodNode.desc.equals("(IIZIILjava/lang/String;)I")) {
//        InsnList newInstructions = new InsnList();
//        newInstructions.add(new FieldInsnNode(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;"));
//        newInstructions.add(new VarInsnNode(ALOAD, 6));
//        newInstructions.add(new MethodInsnNode(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false));
//
//        newInstructions.add(new FieldInsnNode(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;"));
//        newInstructions.add(new VarInsnNode(ILOAD, 1));
//        newInstructions.add(new MethodInsnNode(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(I)V", false));
//
//        newInstructions.add(new FieldInsnNode(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;"));
//        newInstructions.add(new VarInsnNode(ILOAD, 2));
//        newInstructions.add(new MethodInsnNode(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(I)V", false));
//
//        newInstructions.add(new FieldInsnNode(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;"));
//        newInstructions.add(new VarInsnNode(ILOAD, 4));
//        newInstructions.add(new MethodInsnNode(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(I)V", false));
//
//        newInstructions.add(new FieldInsnNode(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;"));
//        newInstructions.add(new VarInsnNode(ILOAD, 5));
//        newInstructions.add(new MethodInsnNode(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(I)V", false));
//
//        methodNode.instructions.insert(newInstructions);
////      } else if (methodNode.name.equals("a") && methodNode.desc.equals("(I)V")) {
////        InsnList newInstructions = new InsnList();
////        newInstructions.add(new FieldInsnNode(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;"));
////        newInstructions.add(new VarInsnNode(ILOAD, 1));
////        newInstructions.add(new MethodInsnNode(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(I)V", false));
////
////        methodNode.instructions.insert(newInstructions);
//      }
    }

    return classNode;
  }

}
