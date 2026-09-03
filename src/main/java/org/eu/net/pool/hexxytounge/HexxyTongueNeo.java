package org.eu.net.pool.hexxytounge;

import net.neoforged.fml.common.Mod;
import net.neoforged.fml.loading.FMLEnvironment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Mod(HexxyTongueNeo.MOD_ID)
public final class HexxyTongueNeo {
    public static final String MOD_ID = "hexxytounge";
    private static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public HexxyTongueNeo() {
        LOGGER.info("Tongued Hexxy NeoForge compatibility marker loaded; upstream commit fc8007f fused the chat implementation into Hexic.");
        if (FMLEnvironment.dist.isClient()) {
            try {
                Class.forName("org.eu.net.pool.hexxytounge.client.HexxyTongueClientProbe")
                        .getMethod("register")
                        .invoke(null);
            } catch (ReflectiveOperationException exception) {
                LOGGER.error("Failed to register Tongued Hexxy client probe hooks", exception);
            }
        }
    }
}
