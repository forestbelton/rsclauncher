package com.rsclauncher.patcher;

import org.objectweb.asm.tree.*;

import java.io.InputStream;

import static org.objectweb.asm.Opcodes.*;

public class ClientStreamPatcher extends ClassPatcher {

  @Override
  public ClassNode patch(ClassNode classNode) {
    for (int i = 0; i < classNode.methods.size(); i++) {
      MethodNode methodNode = classNode.methods.get(i);

      if (methodNode.name.equals("run")) {
        MethodInsnNode writeNode = null;
        for (int j = 0; j < methodNode.instructions.size(); j++) {
          AbstractInsnNode insnNode = methodNode.instructions.get(j);
          if (insnNode instanceof MethodInsnNode) {
            MethodInsnNode methodInsnNode = (MethodInsnNode) insnNode;
            if (methodInsnNode.name.equals("write")) {
              writeNode = methodInsnNode;
            }
          }
        }

        InsnList newWriteInstructions = new InsnList();
        newWriteInstructions.add(new VarInsnNode(ALOAD, 0));
        newWriteInstructions.add(new FieldInsnNode(GETFIELD, "mb", "M", "[B"));
        newWriteInstructions.add(new VarInsnNode(ILOAD, 2));
        newWriteInstructions.add(new VarInsnNode(ILOAD, 1));
        newWriteInstructions.add(new MethodInsnNode(INVOKESTATIC, "com/rsclauncher/internal/BufferUtils", "printWriteBuffer", "([BII)V", false));
        methodNode.maxStack += 4;
        methodNode.maxLocals += 1;
        methodNode.instructions.insert(writeNode, newWriteInstructions);
      } else if (methodNode.name.equals("a") && methodNode.desc.equals("(II[BI)V")) {
        MethodInsnNode readNode = null;
        for (int j = 0; j < methodNode.instructions.size(); j++) {
          AbstractInsnNode insnNode = methodNode.instructions.get(j);
          if (insnNode instanceof MethodInsnNode) {
            MethodInsnNode methodInsnNode = (MethodInsnNode) insnNode;
            if (methodInsnNode.name.equals("read")) {
              readNode = methodInsnNode;
            }
          }
        }

        InsnList newReadInstructions = new InsnList();
        newReadInstructions.add(new VarInsnNode(ALOAD, 3));
        newReadInstructions.add(new VarInsnNode(ILOAD, 2));
        newReadInstructions.add(new VarInsnNode(ILOAD, 1));
        newReadInstructions.add(new MethodInsnNode(INVOKESTATIC, "com/rsclauncher/internal/BufferUtils", "printReadBuffer", "([BII)V", false));
        methodNode.maxStack += 3;
        methodNode.maxLocals += 1;
        methodNode.instructions.insert(readNode, newReadInstructions);
      }
    }

    return classNode;
  }

}
