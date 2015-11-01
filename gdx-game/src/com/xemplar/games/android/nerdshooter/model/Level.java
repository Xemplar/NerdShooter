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
import static com.xemplar.games.android.nerdshooter.blocks.Block.castle;
import static com.xemplar.games.android.nerdshooter.blocks.Block.castle_center;
import static com.xemplar.games.android.nerdshooter.blocks.Block.castle_clift_left;
import static com.xemplar.games.android.nerdshooter.blocks.Block.castle_clift_left_a;
import static com.xemplar.games.android.nerdshooter.blocks.Block.castle_clift_right;
import static com.xemplar.games.android.nerdshooter.blocks.Block.castle_clift_right_a;
import static com.xemplar.games.android.nerdshooter.blocks.Block.castle_half;
import static com.xemplar.games.android.nerdshooter.blocks.Block.castle_half_left;
import static com.xemplar.games.android.nerdshooter.blocks.Block.castle_half_mid;
import static com.xemplar.games.android.nerdshooter.blocks.Block.castle_half_right;
import static com.xemplar.games.android.nerdshooter.blocks.Block.castle_left;
import static com.xemplar.games.android.nerdshooter.blocks.Block.castle_mid;
import static com.xemplar.games.android.nerdshooter.blocks.Block.castle_right;
import static com.xemplar.games.android.nerdshooter.blocks.Block.dirt_center;
import static com.xemplar.games.android.nerdshooter.blocks.Block.dirt_clift_left;
import static com.xemplar.games.android.nerdshooter.blocks.Block.dirt_clift_left_a;
import static com.xemplar.games.android.nerdshooter.blocks.Block.dirt_clift_right;
import static com.xemplar.games.android.nerdshooter.blocks.Block.dirt_clift_right_a;
import static com.xemplar.games.android.nerdshooter.blocks.Block.dirt_half;
import static com.xemplar.games.android.nerdshooter.blocks.Block.dirt_half_left;
import static com.xemplar.games.android.nerdshooter.blocks.Block.dirt_half_mid;
import static com.xemplar.games.android.nerdshooter.blocks.Block.dirt_half_right;
import static com.xemplar.games.android.nerdshooter.blocks.Block.dirt_left;
import static com.xemplar.games.android.nerdshooter.blocks.Block.dirt_mid;
import static com.xemplar.games.android.nerdshooter.blocks.Block.dirt_right;
import static com.xemplar.games.android.nerdshooter.blocks.Block.exit;
import static com.xemplar.games.android.nerdshooter.blocks.Block.grass;
import static com.xemplar.games.android.nerdshooter.blocks.Block.grass_center;
import static com.xemplar.games.android.nerdshooter.blocks.Block.grass_clift_left;
import static com.xemplar.games.android.nerdshooter.blocks.Block.grass_clift_left_a;
import static com.xemplar.games.android.nerdshooter.blocks.Block.grass_clift_right;
import static com.xemplar.games.android.nerdshooter.blocks.Block.grass_clift_right_a;
import static com.xemplar.games.android.nerdshooter.blocks.Block.grass_half;
import static com.xemplar.games.android.nerdshooter.blocks.Block.grass_half_left;
import static com.xemplar.games.android.nerdshooter.blocks.Block.grass_half_mid;
import static com.xemplar.games.android.nerdshooter.blocks.Block.grass_half_right;
import static com.xemplar.games.android.nerdshooter.blocks.Block.grass_left;
import static com.xemplar.games.android.nerdshooter.blocks.Block.grass_mid;
import static com.xemplar.games.android.nerdshooter.blocks.Block.grass_right;
import static com.xemplar.games.android.nerdshooter.blocks.Block.key_blue;
import static com.xemplar.games.android.nerdshooter.blocks.Block.key_green;
import static com.xemplar.games.android.nerdshooter.blocks.Block.key_red;
import static com.xemplar.games.android.nerdshooter.blocks.Block.key_yellow;
import static com.xemplar.games.android.nerdshooter.blocks.Block.lock_blue;
import static com.xemplar.games.android.nerdshooter.blocks.Block.lock_green;
import static com.xemplar.games.android.nerdshooter.blocks.Block.lock_red;
import static com.xemplar.games.android.nerdshooter.blocks.Block.lock_yellow;
import static com.xemplar.games.android.nerdshooter.blocks.Block.misc_lava;
import static com.xemplar.games.android.nerdshooter.blocks.Block.misc_lava_mid;
import static com.xemplar.games.android.nerdshooter.blocks.Block.misc_lava_top;
import static com.xemplar.games.android.nerdshooter.blocks.Block.misc_water;
import static com.xemplar.games.android.nerdshooter.blocks.Block.misc_water_mid;
import static com.xemplar.games.android.nerdshooter.blocks.Block.misc_water_top;
import static com.xemplar.games.android.nerdshooter.blocks.Block.sand;
import static com.xemplar.games.android.nerdshooter.blocks.Block.sand_center;
import static com.xemplar.games.android.nerdshooter.blocks.Block.sand_clift_left;
import static com.xemplar.games.android.nerdshooter.blocks.Block.sand_clift_left_a;
import static com.xemplar.games.android.nerdshooter.blocks.Block.sand_clift_right;
import static com.xemplar.games.android.nerdshooter.blocks.Block.sand_clift_right_a;
import static com.xemplar.games.android.nerdshooter.blocks.Block.sand_half;
import static com.xemplar.games.android.nerdshooter.blocks.Block.sand_half_left;
import static com.xemplar.games.android.nerdshooter.blocks.Block.sand_half_mid;
import static com.xemplar.games.android.nerdshooter.blocks.Block.sand_half_right;
import static com.xemplar.games.android.nerdshooter.blocks.Block.sand_left;
import static com.xemplar.games.android.nerdshooter.blocks.Block.sand_mid;
import static com.xemplar.games.android.nerdshooter.blocks.Block.sand_right;
import static com.xemplar.games.android.nerdshooter.blocks.Block.snow;
import static com.xemplar.games.android.nerdshooter.blocks.Block.snow_center;
import static com.xemplar.games.android.nerdshooter.blocks.Block.snow_clift_left;
import static com.xemplar.games.android.nerdshooter.blocks.Block.snow_clift_left_a;
import static com.xemplar.games.android.nerdshooter.blocks.Block.snow_clift_right;
import static com.xemplar.games.android.nerdshooter.blocks.Block.snow_clift_right_a;
import static com.xemplar.games.android.nerdshooter.blocks.Block.snow_half;
import static com.xemplar.games.android.nerdshooter.blocks.Block.snow_half_left;
import static com.xemplar.games.android.nerdshooter.blocks.Block.snow_half_mid;
import static com.xemplar.games.android.nerdshooter.blocks.Block.snow_half_right;
import static com.xemplar.games.android.nerdshooter.blocks.Block.snow_left;
import static com.xemplar.games.android.nerdshooter.blocks.Block.snow_mid;
import static com.xemplar.games.android.nerdshooter.blocks.Block.snow_right;
import static com.xemplar.games.android.nerdshooter.blocks.Block.stone;
import static com.xemplar.games.android.nerdshooter.blocks.Block.stone_center;
import static com.xemplar.games.android.nerdshooter.blocks.Block.stone_clift_left;
import static com.xemplar.games.android.nerdshooter.blocks.Block.stone_clift_left_a;
import static com.xemplar.games.android.nerdshooter.blocks.Block.stone_clift_right;
import static com.xemplar.games.android.nerdshooter.blocks.Block.stone_clift_right_a;
import static com.xemplar.games.android.nerdshooter.blocks.Block.stone_half;
import static com.xemplar.games.android.nerdshooter.blocks.Block.stone_half_left;
import static com.xemplar.games.android.nerdshooter.blocks.Block.stone_half_mid;
import static com.xemplar.games.android.nerdshooter.blocks.Block.stone_half_right;
import static com.xemplar.games.android.nerdshooter.blocks.Block.stone_left;
import static com.xemplar.games.android.nerdshooter.blocks.Block.stone_mid;
import static com.xemplar.games.android.nerdshooter.blocks.Block.stone_right;

import java.io.File;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.xemplar.games.android.nerdshooter.blocks.Block;
import com.xemplar.games.android.nerdshooter.entities.Entity;

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
	
	@SuppressWarnings("unused")
	private void addExtra(int x, int y, String id){
		//extras[x + y * width] = new Block(new Vector2(x, y), id);
	}
	
	private void addBlock(int x, int y, String id){
		Vector2 pos = new Vector2(x, y);
		Block block = parseID(id).clone(pos);
		
		blocks[x + y * width] = block;
	}
	
	private Block parseID(String id){
        id = id.toLowerCase();
        id = id.trim();
        
		switch(id){
			case "gm" :
        		return grass_mid;
        	case "gr" :
        		return grass_right;
        	case "gl" :
        		return grass_left;
        	case "gc" :
        		return grass_center;
        	case "gs" :
        		return grass;
        	case "gcl" :
        		return grass_clift_left;
        	case "gcla" :
        		return grass_clift_left_a;
        	case "gcr" :
        		return grass_clift_right;
        	case "gcra" :
        		return grass_clift_right_a;
        	case "gss" :
        		return grass_half;
        	case "gsl" :
        		return grass_half_left;
        	case "gsm" :
        		return grass_half_mid;
        	case "gsr" :
        		return grass_half_right;
        	case "rm" :
        		return stone_mid;
        	case "rr" :
        		return stone_right;
        	case "rl" :
        		return stone_left;
        	case "rc" :
        		return stone_center;
        	case "rs" :
        		return stone;
        	case "rcl" :
        		return stone_clift_left;
        	case "rcla" :
        		return stone_clift_left_a;
        	case "rcr" :
        		return stone_clift_right;
        	case "rcra" :
        		return stone_clift_right_a;
        	case "rss" :
        		return stone_half;
        	case "rsl" :
        		return stone_half_left;
        	case "rsm" :
        		return stone_half_mid;
        	case "rsr" :
        		return stone_half_right;
        	case "cm" :
        		return castle_mid;
        	case "cr" :
        		return castle_right;
        	case "cl" :
        		return castle_left;
        	case "cc" :
        		return castle_center;
        	case "cs" :
        		return castle;
        	case "ccl" :
        		return castle_clift_left;
        	case "ccla" :
        		return castle_clift_left_a;
        	case "ccr" :
        		return castle_clift_right;
        	case "ccra" :
        		return castle_clift_right_a;
        	case "css" :
        		return castle_half;
        	case "csl" :
        		return castle_half_left;
        	case "csm" :
        		return castle_half_mid;
        	case "csr" :
        		return castle_half_right;
        	case "sm" :
        		return snow_mid;
        	case "sr" :
        		return snow_right;
        	case "sl" :
        		return snow_left;
        	case "sc" :
        		return snow_center;
        	case "ss" :
        		return snow;
        	case "scl" :
        		return snow_clift_left;
        	case "scla" :
        		return snow_clift_left_a;
        	case "scr" :
        		return snow_clift_right;
        	case "scra" :
        		return snow_clift_right_a;
        	case "sss" :
        		return snow_half;
        	case "ssl" :
        		return snow_half_left;
        	case "ssm" :
        		return snow_half_mid;
        	case "ssr" :
        		return snow_half_right;
        	case "dm" :
            	return dirt_mid;
        	case "dr" :
        		return dirt_right;
        	case "dl" :
        		return dirt_left;
        	case "dc" :
        		return dirt_center;
        	case "ds" :
        		return dirt_mid;
        	case "dcl" :
        		return dirt_clift_left;
        	case "dcla" :
        		return dirt_clift_left_a;
        	case "dcr" :
        		return dirt_clift_right;
        	case "dcra" :
        		return dirt_clift_right_a;
        	case "dss" :
        		return dirt_half;
        	case "dsl" :
        		return dirt_half_left;
        	case "dsm" :
        		return dirt_half_mid;
        	case "dsr" :
        		return dirt_half_right;
        	case "nm" :
        		return sand_mid;
        	case "nr" :
        		return sand_right;
        	case "nl" :
        		return sand_left;
        	case "nc" :
        		return sand_center;
        	case "ns" :
        		return sand;
        	case "ncl" :
        		return sand_clift_left;
        	case "ncla" :
        		return sand_clift_left_a;
        	case "ncr" :
        		return sand_clift_right;
        	case "ncra" :
        		return sand_clift_right_a;
        	case "nss" :
        		return sand_half;
        	case "nsl" :
        		return sand_half_left;
        	case "nsm" :
        		return sand_half_mid;
        	case "nsr" :
        		return sand_half_right;
        	case "lav" :
            	return misc_lava;
        	case "lvt" :
        		return misc_lava_top;
        	case "ltm" :
        		return misc_lava_mid;
        	case "wat" :
        		return misc_water;
        	case "wtt" :
        		return misc_water_top;
        	case "wtm" :
        		return misc_water_mid;
        	case "kb" :
            	return key_blue;
        	case "kr" :
        		return key_red;
        	case "kg" :
        		return key_green;
        	case "ky" :
        		return key_yellow;
        	case "lb" :
        		return lock_blue;
        	case "lr" :
        		return lock_red;
        	case "lg" :
        		return lock_green;
        	case "ly" :
        		return lock_yellow;
        	case "ext" :
        		return exit;
        	default:
        	return grass;
        }
	}
}
