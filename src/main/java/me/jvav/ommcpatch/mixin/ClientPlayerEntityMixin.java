package me.jvav.ommcpatch.mixin;

import com.plusls.ommc.api.command.ClientCommandInternals;
import net.minecraft.client.network.ClientPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ClientPlayerEntity.class)
public class ClientPlayerEntityMixin {
    @Inject(
        method = "sendCommand(Ljava/lang/String;)Z",
        at = @At("HEAD"),
        cancellable = true
    )
    private void onSendCommand(String command, CallbackInfoReturnable<Boolean> cir) {
        command = "/" + command;
        if (ClientCommandInternals.executeCommand(command)) {
            cir.setReturnValue(true);
        }
    }
}
