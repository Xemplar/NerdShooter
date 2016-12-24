/*
 * NerdShooter is a pseudo library project for future Xemplar 2D Side Scroller Games.
 * Copyright (C) 2016  Rohan Loomis
 *
 * This file is part of NerdShooter
 *
 * NerdShooter is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, version 3 of the License, or
 * any later version.
 *
 * NerdShooter is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 */
package com.xemplar.games.android.nerdshooter.view;

public final class RendererModifier {
    public static final int MOD_FADEOUT = 0x0001;
    public static final int MOD_FADEIN = 0x0002;

    private ModFinishListener listener;
    private WorldRenderer renderer;
    private int modification;
    private float alpha = 1.0F;

    RendererModifier(WorldRenderer renderer){
        this.renderer = renderer;
    }

    public void applyMod(float delta){
        renderer.spriteBatch.setColor(1F, 1F, 1F, alpha);
        if(modification == MOD_FADEOUT){
            renderer.world.getJaxon().setLocked(true);
            renderer.world.getJaxon().getVelocity().set(0, 0);
            alpha *= 0.80F;
            if(alpha <= 0.01F) {
                modification = 0;
                alpha = 0.01F;
                if(listener != null) {
                    listener.onFinish();
                    listener = null;
                }
            }
        } else if(modification == MOD_FADEIN){
            alpha *= 1.20F;
            if(alpha >= 1F) {
                modification = 0;
                alpha = 1F;
                renderer.world.getJaxon().setLocked(false);
                if(listener != null) {
                    listener.onFinish();
                    listener = null;
                }
            }
        }
    }

    public void setModification(int mod, ModFinishListener listener){
        this.modification = mod;
        this.listener = listener;
    }
}
