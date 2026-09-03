package org.eu.net.pool.hexxytounge.client;

import net.minecraft.client.Minecraft;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.common.NeoForge;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class HexxyTongueClientProbe {
    private static final Logger LOGGER = LoggerFactory.getLogger("hexxytounge");
    private static int startupTicks;

    private HexxyTongueClientProbe() {
    }

    public static void register() {
        NeoForge.EVENT_BUS.addListener(HexxyTongueClientProbe::onClientTick);
        if (Boolean.getBoolean("hexxytounge.probe.validateClientHooks")) {
        LOGGER.info("[HEXXYTOUNGE-PROBE] client_hooks=PASS events=ClientTickEvent.Post implementation=fused_into_hexic");
        }
    }

    private static void onClientTick(ClientTickEvent.Post event) {
        if (!Boolean.getBoolean("hexxytounge.probe.exitAfterClientStartup")) {
            return;
        }
        startupTicks++;
        if (startupTicks == 120) {
            Minecraft client = Minecraft.getInstance();
            String screen = client.screen == null ? "null" : client.screen.getClass().getName();
            LOGGER.info("[HEXXYTOUNGE-PROBE] client_startup_exit=PASS ticks={} screen={} implementation=fused_into_hexic", startupTicks, screen);
            client.stop();
        }
    }
}
