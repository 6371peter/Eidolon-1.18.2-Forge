package elucent.eidolon;

import elucent.eidolon.spell.SpellReloadListener;
import net.minecraftforge.event.AddReloadListenerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class ListenerEvents {
    @SubscribeEvent
    public static void registerListeners(AddReloadListenerEvent event) {
        SpellReloadListener.registry(event);
    }
}
