/*
 * NerdShooter is a pseudo libray project for future Xemplar 2D Side Scroller Games.
 * Copyright (C) 2015  Rohan Loomis
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
package com.xemplar.games.android.nerdshooter.model;

import com.badlogic.gdx.*;
import com.badlogic.gdx.files.*;
import com.badlogic.gdx.math.*;
import com.xemplar.games.android.nerdshooter.items.*;
import java.io.*;
import java.security.*;
import com.xemplar.games.android.nerdshooter.blocks.*;
import com.xemplar.games.android.nerdshooter.entities.*;
import com.xemplar.games.android.nerdshooter.screens.*;
import com.badlogic.gdx.utils.*;

public class Level {
	public Vector2 jaxonStart;
	private int width;
	private int height;
	
	private Block[] blocks;
	private Block[] extras;
	
	private Array<Entity> entities;

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public void setEntites(Array<Entity> entities){
		this.entities = entities;
	}
	
	public Array<Entity> getEntities(){
		return entities;
	}
	
	public Block[] getBlocks() {
		return blocks;
	}

	public Block[] getExtras(){
		return extras;
	}
	
	public void setBlocks(Block[] blocks) {
		this.blocks = blocks;
	}
	
	public void setExtras(Block[] extras){
		this.extras = extras;
	}
	
	public Level(int levelNum){
		jaxonStart = loadLevel(levelNum);
	}
	
	public Block get(int i) {
		return blocks[i];
	}
	
	private Vector2 loadLevel(int num){
		Vector2 value = new Vector2(1, 1);
		
		width = 50;
		height = 7;
        
		if(num == -1){
            File file = new File(Gdx.files.getExternalStoragePath(), "levelExp.txt");
                
            FileHandle handle = new FileHandle(file);
            
            value = loadFile(handle);
		} else {
            String fileName = num + "";
            if(num < 100){
                fileName = "0" + num;
            }
            if(num < 10){
                fileName = "00" + num;
            }
            
			FileHandle handle = Gdx.files.internal("levels/level" + fileName + ".nsl");
			value = loadFile(handle);
		}
		
        value.add(0.25F, 0);
        
		return value;
	}
	
    public Vector2 loadFile(FileHandle handle){
        Vector2 value = new Vector2(1, 1);
        
        String lines = handle.readString(); 

        String[] rows = lines.split("\n");

        width = rows[0].split(",").length;
        height = rows.length;

        setupLevel(width, height);

        String[][] ids = new String[height][];

        for(int row = 0; row < height; row++){
            String[] line = rows[row].split(",");
            ids[row] = new String[width];
            for(int col = 0; col < width; col++){
                ids[row][col] = line[col];

                if(ids[row][col].equals("s")){
                    value = new Vector2(col, (row - height) * -1);
                } else if(ids[row][col].equals("00") || ids[row][col].equals("0")){
                    continue;
                } else {
                    addBlock(col, (row - (height - 1)) * -1, ids[row][col]);
                }
            }
        }
        
        return value;
    }
    
	public String parseExtras(String input){
		String returnValue = "00";
		
		if(input.contains("T1")){
			returnValue = "tochLit";
		} else if(input.contains("T0")){
			returnValue = "torch";
		} else if(input.contains("WI")){
			returnValue = "window";
		}
		
		return returnValue;
	}
	
	public void setupLevel(int width, int height){
		entities = new Array<Entity>();
        
		//entities.add(new MoveablePlatform(new Vector2(2, 12), "grass", 2, 1, MoveablePlatform.MovementType.SINE));
        
		blocks = new Block[width * height];
		extras = new Block[width * height];
		
		for (int index = 0; index < blocks.length; index++) {
			blocks[index] = null;
			extras[index] = null;
		}
	}
	
	private void addExtra(int x, int y, String id){
		extras[x + y * width] = new Block(new Vector2(x, y), id);
	}
	
	private void addBlock(int x, int y, String id){
        String output = "grass";
        Block block = new Block(new Vector2(x, y), "grass");
        
        if(id.equals("gm")){
            output = "grassMid";
            block = new Block(new Vector2(x, y), output);
        } else if(id.equals("gr")){
            output = "grassRight";
            block = new Block(new Vector2(x, y), output);
        } else if(id.equals("gl")){
            output = "grassLeft";
            block = new Block(new Vector2(x, y), output);
        } else if(id.equals("gc")){
            output = "grassCenter";
            block = new Block(new Vector2(x, y), output);
        } else if(id.equals("gs")){
            output = "grass";
            block = new Block(new Vector2(x, y), output);
        } else if(id.equals("gcl")){
            output = "grassCliftLeft";
            block = new Block(new Vector2(x, y), output);
        } else if(id.equals("gcla")){
            output = "grassCliftLeftAlt";
            block = new Block(new Vector2(x, y), output);
        } else if(id.equals("gcr")){
            output = "grassCliftRight";
            block = new Block(new Vector2(x, y), output);
        } else if(id.equals("gcra")){
            output = "grassCliftRightAlt";
            block = new Block(new Vector2(x, y), output);
        } else if(id.equals("gss")){
            output = "grassHalf";
            block = new SlabBlock(new Vector2(x, y), output, Block.WIDTH, Block.HEIGHT / 2F, true);
        } else if(id.equals("gsl")){
            output = "grassHalfLeft";
            block = new SlabBlock(new Vector2(x, y), output, Block.WIDTH, Block.HEIGHT / 2F, true);
        } else if(id.equals("gsm")){
            output = "grassHalfMid";
            block = new SlabBlock(new Vector2(x, y), output, Block.WIDTH, Block.HEIGHT / 2F, true);
        } else if(id.equals("gsr")){
            output = "grassHalfRight";
            block = new SlabBlock(new Vector2(x, y), output, Block.WIDTH, Block.HEIGHT / 2F, true);
        }else if(id.equals("rm")){
            output = "stoneMid";
            block = new Block(new Vector2(x, y), output);
        } else if(id.equals("rr")){
            output = "stoneRight";
            block = new Block(new Vector2(x, y), output);
        } else if(id.equals("rl")){
            output = "stoneLeft";
            block = new Block(new Vector2(x, y), output);
        } else if(id.equals("rc")){
            output = "stoneCenter";
            block = new Block(new Vector2(x, y), output);
        } else if(id.equals("rs")){
            output = "stone";
            block = new Block(new Vector2(x, y), output);
        } else if(id.equals("rcl")){
            output = "stoneCliftLeft";
            block = new Block(new Vector2(x, y), output);
        } else if(id.equals("rcla")){
            output = "stoneCliftLeftAlt";
            block = new Block(new Vector2(x, y), output);
        } else if(id.equals("rcr")){
            output = "stoneCliftRight";
            block = new Block(new Vector2(x, y), output);
        } else if(id.equals("rcra")){
            output = "stoneCliftRightAlt";
            block = new Block(new Vector2(x, y), output);
        } else if(id.equals("rss")){
            output = "stoneHalf";
            block = new SlabBlock(new Vector2(x, y), output, Block.WIDTH, Block.HEIGHT / 2F, true);
        } else if(id.equals("rsl")){
            output = "stoneHalfLeft";
            block = new SlabBlock(new Vector2(x, y), output, Block.WIDTH, Block.HEIGHT / 2F, true);
        } else if(id.equals("rsm")){
            output = "stoneHalfMid";
            block = new SlabBlock(new Vector2(x, y), output, Block.WIDTH, Block.HEIGHT / 2F, true);
        } else if(id.equals("rsr")){
            output = "stoneHalfRight";
            block = new SlabBlock(new Vector2(x, y), output, Block.WIDTH, Block.HEIGHT / 2F, true);
        }else if(id.equals("cm")){
            output = "castleMid";
            block = new Block(new Vector2(x, y), output);
        } else if(id.equals("cr")){
            output = "castleRight";
            block = new Block(new Vector2(x, y), output);
        } else if(id.equals("cl")){
            output = "castleLeft";
            block = new Block(new Vector2(x, y), output);
        } else if(id.equals("cc")){
            output = "castleCenter";
            block = new Block(new Vector2(x, y), output);
        } else if(id.equals("cs")){
            output = "castle";
            block = new Block(new Vector2(x, y), output);
        } else if(id.equals("ccl")){
            output = "castleCliftLeft";
            block = new Block(new Vector2(x, y), output);
        } else if(id.equals("ccla")){
            output = "castleCliftLeftAlt";
            block = new Block(new Vector2(x, y), output);
        } else if(id.equals("ccr")){
            output = "castleCliftRight";
            block = new Block(new Vector2(x, y), output);
        } else if(id.equals("ccra")){
            output = "castleCliftRightAlt";
            block = new Block(new Vector2(x, y), output);
        } else if(id.equals("css")){
            output = "castleHalf";
            block = new SlabBlock(new Vector2(x, y), output, Block.WIDTH, Block.HEIGHT / 2F, true);
        } else if(id.equals("csl")){
            output = "castleHalfLeft";
            block = new SlabBlock(new Vector2(x, y), output, Block.WIDTH, Block.HEIGHT / 2F, true);
        } else if(id.equals("csm")){
            output = "castleHalfMid";
            block = new SlabBlock(new Vector2(x, y), output, Block.WIDTH, Block.HEIGHT / 2F, true);
        } else if(id.equals("csr")){
            output = "castleHalfRight";
            block = new SlabBlock(new Vector2(x, y), output, Block.WIDTH, Block.HEIGHT / 2F, true);
        }else if(id.equals("sm")){
            output = "snowMid";
            block = new Block(new Vector2(x, y), output);
        } else if(id.equals("sr")){
            output = "snowRight";
            block = new Block(new Vector2(x, y), output);
        } else if(id.equals("sl")){
            output = "snowLeft";
            block = new Block(new Vector2(x, y), output);
        } else if(id.equals("sc")){
            output = "snowCenter";
            block = new Block(new Vector2(x, y), output);
        } else if(id.equals("ss")){
            output = "snow";
            block = new Block(new Vector2(x, y), output);
        } else if(id.equals("scl")){
            output = "snowCliftLeft";
            block = new Block(new Vector2(x, y), output);
        } else if(id.equals("scla")){
            output = "snowCliftLeftAlt";
            block = new Block(new Vector2(x, y), output);
        } else if(id.equals("scr")){
            output = "snowCliftRight";
            block = new Block(new Vector2(x, y), output);
        } else if(id.equals("scra")){
            output = "snowCliftRightAlt";
            block = new Block(new Vector2(x, y), output);
        } else if(id.equals("sss")){
            output = "snowHalf";
            block = new SlabBlock(new Vector2(x, y), output, Block.WIDTH, Block.HEIGHT / 2F, true);
        } else if(id.equals("ssl")){
            output = "snowHalfLeft";
            block = new SlabBlock(new Vector2(x, y), output, Block.WIDTH, Block.HEIGHT / 2F, true);
        } else if(id.equals("ssm")){
            output = "snowHalfMid";
            block = new SlabBlock(new Vector2(x, y), output, Block.WIDTH, Block.HEIGHT / 2F, true);
        } else if(id.equals("ssr")){
            output = "snowHalfRight";
            block = new SlabBlock(new Vector2(x, y), output, Block.WIDTH, Block.HEIGHT / 2F, true);
        }else if(id.equals("dm")){
            output = "dirtMid";
            block = new Block(new Vector2(x, y), output);
        } else if(id.equals("dr")){
            output = "dirtRight";
            block = new Block(new Vector2(x, y), output);
        } else if(id.equals("dl")){
            output = "dirtLeft";
            block = new Block(new Vector2(x, y), output);
        } else if(id.equals("dc")){
            output = "dirtCenter";
            block = new Block(new Vector2(x, y), output);
        } else if(id.equals("ds")){
            output = "dirt";
            block = new Block(new Vector2(x, y), output);
        } else if(id.equals("dcl")){
            output = "dirtCliftLeft";
            block = new Block(new Vector2(x, y), output);
        } else if(id.equals("dcla")){
            output = "dirtCliftLeftAlt";
            block = new Block(new Vector2(x, y), output);
        } else if(id.equals("dcr")){
            output = "dirtCliftRight";
            block = new Block(new Vector2(x, y), output);
        } else if(id.equals("dcra")){
            output = "dirtCliftRightAlt";
            block = new Block(new Vector2(x, y), output);
        } else if(id.equals("dss")){
            output = "dirtHalf";
            block = new SlabBlock(new Vector2(x, y), output, Block.WIDTH, Block.HEIGHT / 2F, true);
        } else if(id.equals("dsl")){
            output = "dirtHalfLeft";
            block = new SlabBlock(new Vector2(x, y), output, Block.WIDTH, Block.HEIGHT / 2F, true);
        } else if(id.equals("dsm")){
            output = "dirtHalfMid";
            block = new SlabBlock(new Vector2(x, y), output, Block.WIDTH, Block.HEIGHT / 2F, true);
        } else if(id.equals("dsr")){
            output = "dirtHalfRight";
            block = new SlabBlock(new Vector2(x, y), output, Block.WIDTH, Block.HEIGHT / 2F, true);
        }else if(id.equals("nm")){
            output = "sandMid";
            block = new Block(new Vector2(x, y), output);
        } else if(id.equals("nr")){
            output = "sandRight";
            block = new Block(new Vector2(x, y), output);
        } else if(id.equals("nl")){
            output = "sandLeft";
            block = new Block(new Vector2(x, y), output);
        } else if(id.equals("nc")){
            output = "sandCenter";
            block = new Block(new Vector2(x, y), output);
        } else if(id.equals("ns")){
            output = "sand";
            block = new Block(new Vector2(x, y), output);
        } else if(id.equals("ncl")){
            output = "sandCliftLeft";
            block = new Block(new Vector2(x, y), output);
        } else if(id.equals("ncla")){
            output = "sandCliftLeftAlt";
            block = new Block(new Vector2(x, y), output);
        } else if(id.equals("ncr")){
            output = "sandCliftRight";
            block = new Block(new Vector2(x, y), output);
        } else if(id.equals("ncra")){
            output = "sandCliftRightAlt";
            block = new Block(new Vector2(x, y), output);
        } else if(id.equals("nss")){
            output = "sandHalf";
            block = new SlabBlock(new Vector2(x, y), output, Block.WIDTH, Block.HEIGHT / 2F, true);
        } else if(id.equals("nsl")){
            output = "sandHalfLeft";
            block = new SlabBlock(new Vector2(x, y), output, Block.WIDTH, Block.HEIGHT / 2F, true);
        } else if(id.equals("nsm")){
            output = "sandHalfMid";
            block = new SlabBlock(new Vector2(x, y), output, Block.WIDTH, Block.HEIGHT / 2F, true);
        } else if(id.equals("nsr")){
            output = "sandHalfRight";
            block = new SlabBlock(new Vector2(x, y), output, Block.WIDTH, Block.HEIGHT / 2F, true);
        } else if(id.equals("lav")){
            output = "lava";
            block = new DeathBlock(new Vector2(x, y), output);
        } else if(id.equals("lvt")){
            output = "lavaTop";
            block = new SlabDeathBlock(new Vector2(x, y), output, Block.WIDTH, Block.HEIGHT / 2F, false);
        } else if(id.equals("ltm")){
            output = "lavaTop_mid";
            block = new SlabDeathBlock(new Vector2(x, y), output, Block.WIDTH, Block.HEIGHT / 2F, false);
        } else if(id.equals("wat")){
            output = "water";
            block = new Block(new Vector2(x, y), output);
        } else if(id.equals("wtt")){
            output = "waterTop";
            block = new Block(new Vector2(x, y), output);
        } else if(id.equals("wtm")){
            output = "waterTop_mid";
            block = new Block(new Vector2(x, y), output);
        } else if(id.equals("kb")){
            output = "keyBlue";
            block = new ItemBlock(new Vector2(x, y), 0.5F, Item.BLUE_KEY.clone());
        } else if(id.equals("kr")){
            output = "keyRed";
            block = new ItemBlock(new Vector2(x, y), 0.5F, Item.RED_KEY.clone());
        } else if(id.equals("kg")){
            output = "keyGreen";
            block = new ItemBlock(new Vector2(x, y), 0.5F, Item.GREEN_KEY.clone());
        } else if(id.equals("ky")){
            output = "keyYellow";
            block = new ItemBlock(new Vector2(x, y), 0.5F, Item.YELLOW_KEY.clone());
        } else if(id.equals("lb")){
            output = "lock_blue";
            block = new LockBlock(new Vector2(x, y), output, Item.BLUE_KEY);
        } else if(id.equals("lr")){
            output = "lock_red";
            block = new LockBlock(new Vector2(x, y), output, Item.RED_KEY);
        } else if(id.equals("lg")){
            output = "lock_green";
            block = new LockBlock(new Vector2(x, y), output, Item.GREEN_KEY);
        } else if(id.equals("ly")){
            output = "lock_yellow";
            block = new LockBlock(new Vector2(x, y), output, Item.YELLOW_KEY);
        } else if(id.equals("ext")){
            output = "exit";
            block = new ExitBlock(new Vector2(x, y), output, ExitBlock.EXIT_NORMAL);
        }
        
		blocks[x + y * width] = block;
	}
    
    private enum Type{
        Block,
        Lock,
        Item;
    }
}
