package com.LukeTheDuke9801.progressivetechnicalities.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.world.ClientWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.IRenderHandler;

public class ModRenderHandler implements IRenderHandler {
    @Override
    @OnlyIn(Dist.CLIENT)
    public void render(int ticks, float partialTicks, ClientWorld world, Minecraft mc){
        int x = 100;
        int y = 200;
        int color = 0xFFFFFF;
        mc.fontRenderer.drawStringWithShadow("hello world", x, y, color);
    }
}