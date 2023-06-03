package elucent.eidolon.compat.classicbar;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.vertex.PoseStack;
import elucent.eidolon.ClientConfig;
import elucent.eidolon.Eidolon;
import elucent.eidolon.capability.ISoul;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.client.gui.ForgeIngameGui;
import net.minecraftforge.fml.ModList;
import tfar.classicbar.config.ClassicBarsConfig;
import tfar.classicbar.impl.BarOverlayImpl;
import tfar.classicbar.util.Color;
import tfar.classicbar.util.ColorUtils;
import tfar.classicbar.util.ModUtils;

public class EtherealClassicBars extends BarOverlayImpl {

    public static final ResourceLocation ICONS =new ResourceLocation(Eidolon.MODID, "textures/gui/icons.png");

    public EtherealClassicBars() {
        super("ethereal");
    }

    @Override
    public boolean shouldRender(Player player) {
        return ModList.get().isLoaded("classicbar") && player.getCapability(ISoul.INSTANCE).resolve().get().hasEtherealHealth() && ClientConfig.CLASSIC_ETHEREAL_BAR.get();
    }

    @Override
    public void renderBar(ForgeIngameGui gui, PoseStack stack, Player player, int screenWidth, int screenHeight, int vOffset) {
        double etherealHealth = 0, etherealMax = 0;

        ISoul cap = player.getCapability(ISoul.INSTANCE).resolve().get();
        etherealHealth = cap.getEtherealHealth();
        etherealMax = cap.getMaxEtherealHealth();

        int xStart = screenWidth / 2 + getHOffset();
        int yStart = screenHeight - vOffset;
        GlStateManager._enableBlend();

        double maxHealth = player.getMaxHealth();
        int barWidth = (int) getBarWidth(player);

        if (rightHandSide()) {
            xStart += ModUtils.WIDTH - barWidth;
        }

        double eWidth = Math.ceil(79 * etherealHealth / etherealMax);
        double dWidth = Math.ceil(81 * etherealMax / maxHealth);
        double d = dWidth - 2;
        double fWidth = Math.ceil(d * etherealHealth / etherealMax);

        Color.reset();
        if (etherealMax >= maxHealth) {
            ModUtils.drawTexturedModalRect(stack, xStart, yStart, 0, 0, 81, 9);
            ColorUtils.hex2Color("#9966ff").color2Gl();
            ModUtils.drawTexturedModalRect(stack, xStart + 1, yStart + 1, 1, 10, eWidth, 7);
        } else {
            ModUtils.drawTexturedModalRect(stack, xStart, yStart, 0, 0, dWidth - 1, 9);
            ModUtils.drawTexturedModalRect(stack, xStart + dWidth - 1, yStart, 79, 0, 2, 9);
            ColorUtils.hex2Color("#9966ff").color2Gl();
            if (etherealHealth >= etherealMax) ModUtils.drawTexturedModalRect(stack, xStart + 1, yStart + 1, 1, 10, d, 7);
            if (fWidth == 0) ModUtils.drawTexturedModalRect(stack, xStart + 1, yStart + 1, 1, 10, 0, 7);
            else ModUtils.drawTexturedModalRect(stack, xStart + 1, yStart + 1, 1, 10, fWidth - 1, 7);
        }
    }

    @Override
    public boolean shouldRenderText() { return ClassicBarsConfig.showHealthNumbers.get(); }

    @Override
    public double getBarWidth(Player player) {
        ISoul cap = player.getCapability(ISoul.INSTANCE).resolve().get();
        double etherealMax = cap.getMaxEtherealHealth();
        double maxHealth = player.getMaxHealth();
        return Math.ceil(ModUtils.WIDTH * Math.min(maxHealth, etherealMax) / maxHealth);
    }

    @Override
    public void renderText(PoseStack poseStack, Player player, int width, int height, int vOffset) {
        ISoul cap = player.getCapability(ISoul.INSTANCE).resolve().get();
        double etherealHealth = cap.getEtherealHealth();
        int xStart = width / 2 + getIconOffset();
        int yStart = height - vOffset;
        int color = Integer.decode("#9966ff");
        textHelper(poseStack, xStart, yStart, etherealHealth, color);
    }

    @Override
    public void renderIcon(PoseStack poseStack, Player player, int width, int height, int vOffset) {
        int xStart = width / 2 + getIconOffset();
        int yStart = height - vOffset;

        ModUtils.drawTexturedModalRect(poseStack, xStart, yStart, 0, 18, 9, 9);
        ModUtils.drawTexturedModalRect(poseStack, xStart, yStart, 0, 9, 9, 9);
    }

    @Override
    public ResourceLocation getIconRL() {
        return ICONS;
    }
}
