package com.rsclauncher.patcher;

import org.objectweb.asm.tree.*;

import static org.objectweb.asm.Opcodes.ALOAD;
import static org.objectweb.asm.Opcodes.ILOAD;
import static org.objectweb.asm.Opcodes.INVOKESTATIC;

public class daPatcher extends ClassPatcher {

  @Override
  public ClassNode patch(ClassNode classNode) {
    for (int i = 0; i < classNode.methods.size(); i++) {
      MethodNode methodNode = classNode.methods.get(i);

      if (methodNode.name.equals("a") && methodNode.desc.equals("(ILjava/lang/String;ILjava/lang/String;)Ljava/net/Socket;")) {
        InsnList newInstructions = new InsnList();
        newInstructions.add(new VarInsnNode(ILOAD, 1));
        newInstructions.add(new VarInsnNode(ALOAD, 2));
        newInstructions.add(new VarInsnNode(ILOAD, 3));
        newInstructions.add(new VarInsnNode(ALOAD, 4));
        newInstructions.add(new MethodInsnNode(INVOKESTATIC, "com/rsclauncher/internal/ResourceUtils", "print2", "(ILjava/lang/String;ILjava/lang/String;)V", false));
        methodNode.instructions.insert(newInstructions);
      }
    }

    return classNode;
  }

}
