package net.lemeow.aimod.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import net.lemeow.aimod.AIMod;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class InfusionTableScreen extends HandledScreen<InfusionTableScreenHandler> {
    private static final Identifier TEXTURE =
            new Identifier(AIMod.MOD_ID, "textures/gui/infusion_table.png");
    public static final int DELTA= 8;



    public InfusionTableScreen(InfusionTableScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
        super.backgroundHeight = 232;
    }

    protected void init(){
        super.init();
        titleX = (backgroundWidth-textRenderer.getWidth(title))/2;
        titleY = 4;
        playerInventoryTitleY = 141;
    }

    // need to fix
    @Override
    protected void drawBackground(MatrixStack matrices, float delta, int mouseX, int mouseY) {

        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 0.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int x = (width-backgroundWidth)/2;
        int y = (height-backgroundHeight)/2;
        drawTexture(matrices, x, y, 0, 0, backgroundWidth, backgroundHeight);


        // handles the arrows
        if(handler.isCrafting()){

            // pointing right
            drawTexture(matrices, x+52, y + 61+DELTA, 177, 0, handler.getScaledProgress(), 16);
            // pointing down
            drawTexture(matrices, x+80, y+32+DELTA, 177, 37, 16, handler.getScaledProgress());

            // pointing up
            //idea is you draw from bottom up and increment the height as well as from where you're drawing
            // so that you inch up and don't go top down
            drawTexture(matrices, x+80,y+91 + 21 - handler.getScaledProgress(), 177,
                    84- handler.getScaledProgress(), 16, handler.getScaledProgress() );

            //pointing left
            // same idea
            drawTexture(matrices, x+103 + 21 - handler.getScaledProgress(), y + 61 +DELTA,
                    199- handler.getScaledProgress(), 18, handler.getScaledProgress(), 16);

        }



    }

    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta){
        renderBackground(matrices);
        super.render(matrices, mouseX, mouseY, delta);
        drawMouseoverTooltip(matrices, mouseX, mouseY);
    }
}
