function initializeCoreMod() {
	print("Init CreativeCore coremods ...")
    return {
        'clientnetwork': {
            'target': {
                'type': 'METHOD',
				'class': 'net.minecraft.client.network.play.ClientPlayNetHandler',
				'methodName': 'func_195515_i',
				'methodDesc': '()Lcom/mojang/brigadier/CommandDispatcher;'
            },
            'transformer': function(method) {
				var Opcodes = Java.type('org.objectweb.asm.Opcodes');
				var asmapi = Java.type('net.minecraftforge.coremod.api.ASMAPI');
				var InsnNode = Java.type('org.objectweb.asm.tree.InsnNode');
				var FieldInsnNode = Java.type('org.objectweb.asm.tree.FieldInsnNode');
				var VarInsnNode = Java.type('org.objectweb.asm.tree.VarInsnNode');

				method.instructions.clear();
				method.instructions.add(new VarInsnNode(Opcodes.ALOAD, 0));
				method.instructions.add(new FieldInsnNode(Opcodes.GETFIELD, "net/minecraft/client/network/play/ClientPlayNetHandler", asmapi.mapField("field_195517_n"), "Lcom/mojang/brigadier/CommandDispatcher;"));
				method.instructions.add(asmapi.buildMethodCall("team/creative/creativecore/client/command/ClientCommandRegistry", "getDispatcher", "(Lcom/mojang/brigadier/CommandDispatcher;)Lcom/mojang/brigadier/CommandDispatcher;", asmapi.MethodType.STATIC));
				method.instructions.add(new InsnNode(Opcodes.ARETURN));

                return method;
            }
		}
    }
}
