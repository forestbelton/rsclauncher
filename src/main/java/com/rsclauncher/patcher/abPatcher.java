package com.rsclauncher.patcher;

import org.objectweb.asm.tree.*;

import static org.objectweb.asm.Opcodes.*;

public class abPatcher extends ClassPatcher {

  @Override
  public ClassNode patch(ClassNode classNode) {
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
