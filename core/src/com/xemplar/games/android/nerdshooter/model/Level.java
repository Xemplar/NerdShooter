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
package com.xemplar.games.android.nerdshooter.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ArrayMap;
import com.xemplar.games.android.nerdshooter.blocks.*;
import com.xemplar.games.android.nerdshooter.entities.Entity;
import com.xemplar.games.android.nerdshooter.entities.ai.AbsoluteLinearAI;
import com.xemplar.games.android.nerdshooter.entities.ai.AbstractAI;
import com.xemplar.games.android.nerdshooter.entities.mobs.BlockMob;
import com.xemplar.games.android.nerdshooter.entities.mobs.EnemyMob;
import com.xemplar.games.android.nerdshooter.extras.Extra;
import com.xemplar.games.android.nerdshooter.extras.TorchExtra;

import java.io.File;

import static com.xemplar.games.android.nerdshooter.blocks.Block.*;
import static com.xemplar.games.android.nerdshooter.extras.Extra.*;

public class Level {
    public ArrayMap<Integer, Array<Swatch>> swatches = new ArrayMap<Integer, Array<Swatch>>();
    public Vector2 jaxonStart;
    private int width;
    private int height;
    
    private Block[] blocks;

    private Array<Extra> extras;
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
    public Array<Extra> getExtras(){
        return extras;
    }
    public void setBlocks(Block[] blocks) {
        this.blocks = blocks;
    }
    public void setExtras(Array<Extra> extras){
        this.extras = extras;
    }
    
    public Level(String pack, int levelNum){
        jaxonStart = loadLevel(pack, levelNum);
    }
    public Block get(int i) {
        return blocks[i];
    }
    
    private Vector2 loadLevel(String pack, int num){
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
            
            FileHandle handle = Gdx.files.local("levels/"+ pack + "/" + fileName + ".nsl");
            value = loadFile(handle);
        }
        
        value.add(0.25F, 0);
        
        return value;
    }
    
    public Vector2 loadFile(FileHandle handle){
        Vector2 value = new Vector2(1, 1);
        
        String lines = new String(handle.readBytes());

        String[] data = lines.split("\n");
        Array<String> list = new Array<String>();
        Array<String> extras = new Array<String>();
        for(int i = 0; i < data.length; i++){
            if(data[i].startsWith("$")) {
                extras.add(data[i].substring(1));
                data[i] = null;
            } else {
                list.add(data[i]);
            }
        }
        String[] rows = list.toArray(String.class);

        width = rows[0].split(",").length;
        height = rows.length;

        setupLevel(width, height);

        String[][] ids = new String[height][];

        for(int read = 0; read < height; read++){
            int row = read;
            String[] line = rows[read].split(",");
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
        for(String s : extras){
            String[] line = s.split(",");
            for(int col = 0; col < line.length; col++){
                addExtra(line[col]);
            }
        }
        setupSwatches();
        return value;
    }
    
    public void setupLevel(int width, int height){
        entities = new Array<Entity>();
        extras = new Array<Extra>();
        
        blocks = new Block[width * height];
        
        for (int index = 0; index < blocks.length; index++) {
            blocks[index] = null;
        }
    }

    private void addExtra(String id){
        Extra extra = parseExtraID(id);

        if(extra == null) return;

        extras.add(extra);
    }
    
    private void addBlock(int x, int y, String id){
        Vector2 pos = new Vector2(x, y);
        Block block = parseID(id, x, y);
        
        if(block == null) {
            return;
        } else if(block instanceof Uncloneable) {
            blocks[x + y * width] = block;
        } else {
            blocks[x + y * width] = block.clone(pos);
        }
    }

    private Extra parseExtraID(String id) {
        id = id.toLowerCase();
        id = id.trim();

        Extra b = getExtraEntity(id);
        if(b != null){
            return b;
        } else {
            return null;
        }
    }

    public Extra getExtraEntity(String id){
        if(id.charAt(0) == 'x'){
            String parse = id.substring(1);
            String[] options = parse.split("#");
            int name = Integer.parseInt(options[0]);
            System.out.println("YEETE: " + name);
            switch(name){
                case 0:{

                }
                case 1:{
                    int x = Integer.parseInt(options[1]);
                    int y = Integer.parseInt(options[2]);
                    int s = Integer.parseInt(options[3]);

                    System.out.println("Y Choord: " + y);

                    TorchExtra torch = new TorchExtra(new Vector2(x, y), s);
                    Array<Swatch> swatchArray = new Array<Swatch>();
                    for(int i = 4; i < options.length; i+=2){
                        boolean isExtra = options[i].startsWith("x");
                        int sx = Integer.parseInt(options[i].substring(isExtra ? 1 : 0));
                        int sy = height - (Integer.parseInt(options[i + 1].substring(isExtra ? 1 : 0)) + 1);

                        swatchArray.add(new Swatch(torch, sx, sy, isExtra));
                    }
                    swatches.put(x + y * width, swatchArray);

                    return torch;
                }
                case 2:{
                    int x = Integer.parseInt(options[1]);
                    int y = Integer.parseInt(options[2]);

                    return window.clone(new Vector2(x, y));
                }
                case 3: {
                    int x = Integer.parseInt(options[1]);
                    int y = Integer.parseInt(options[2]);

                    int h = Integer.parseInt(options[3]);

                    spawnEntity(new EnemyMob(new Vector2(x, y), 0.3F, 0.5F, h));
                    return null;
                }
            }
        }

        return null;
    }

    private void setupSwatches(){
        for(int i = 0; i < swatches.size; i++){
            int key = swatches.getKeyAt(i);
            Array<Swatch> swatch = swatches.get(key);
            Block b = swatch.get(0).getParent();

            if(b instanceof TorchExtra){
                TorchExtra t = (TorchExtra)b;
                for(int j = 0; j < swatch.size; j++){
                    t.addSwitchable(swatch.get(j).getSwatch());
                }
            } else if(b instanceof LockedDoor){
                LockedDoor d = (LockedDoor)b;
                for(int j = 0; j < swatch.size; j++){
                    d.addSwitchable(swatch.get(j).getSwatch());
                }
            }
        }
    }

    private Block parseID(String id, int x, int y){
        id = id.toLowerCase();
        id = id.trim();

        if(id.equals("gm"))
            return grass_mid;
        if(id.equals("gr"))
            return grass_right;
        if(id.equals("gl"))
            return grass_left;
        if(id.equals("gc"))
            return grass_center;
        if(id.equals("gs"))
            return grass;
        if(id.equals("gcl"))
            return grass_cliff_left;
        if(id.equals("gcla"))
            return grass_cliff_left_a;
        if(id.equals("gcr"))
            return grass_cliff_right;
        if(id.equals("gcra"))
            return grass_cliff_right_a;
        if(id.equals("gss"))
            return grass_half;
        if(id.equals("gsl"))
            return grass_half_left;
        if(id.equals("gsm"))
            return grass_half_mid;
        if(id.equals("gsr"))
            return grass_half_right;

        if(id.equals("rm"))
            return stone_mid;
        if(id.equals("rr"))
            return stone_right;
        if(id.equals("rl"))
            return stone_left;
        if(id.equals("rc"))
            return stone_center;
        if(id.equals("rs"))
            return stone;
        if(id.equals("rcl"))
            return stone_cliff_left;
        if(id.equals("rcla"))
            return stone_cliff_left_a;
        if(id.equals("rcr"))
            return stone_cliff_right;
        if(id.equals("rcra"))
            return stone_cliff_right_a;
        if(id.equals("rss"))
            return stone_half;
        if(id.equals("rsl"))
            return stone_half_left;
        if(id.equals("rsm"))
            return stone_half_mid;
        if(id.equals("rsr"))
            return stone_half_right;

        if(id.equals("cm"))
            return castle_mid;
        if(id.equals("cr"))
            return castle_right;
        if(id.equals("cl"))
            return castle_left;
        if(id.equals("cc"))
            return castle_center;
        if(id.equals("cs"))
            return castle;
        if(id.equals("ccl"))
            return castle_cliff_left;
        if(id.equals("ccla"))
            return castle_cliff_left_a;
        if(id.equals("ccr"))
            return castle_cliff_right;
        if(id.equals("ccra"))
            return castle_cliff_right_a;
        if(id.equals("css"))
            return castle_half;
        if(id.equals("csl"))
            return castle_half_left;
        if(id.equals("csm"))
            return castle_half_mid;
        if(id.equals("csr"))
            return castle_half_right;

        if(id.equals("sm"))
            return snow_mid;
        if(id.equals("sr"))
            return snow_right;
        if(id.equals("sl"))
            return snow_left;
        if(id.equals("sc"))
            return snow_center;
        if(id.equals("ss"))
            return snow;
        if(id.equals("scl"))
            return snow_cliff_left;
        if(id.equals("scla"))
            return snow_cliff_left_a;
        if(id.equals("scr"))
            return snow_cliff_right;
        if(id.equals("scra"))
            return snow_cliff_right_a;
        if(id.equals("sss"))
            return snow_half;
        if(id.equals("ssl"))
            return snow_half_left;
        if(id.equals("ssm"))
            return snow_half_mid;
        if(id.equals("ssr"))
            return snow_half_right;

        if(id.equals("dm"))
            return dirt_mid;
        if(id.equals("dr"))
            return dirt_right;
        if(id.equals("dl"))
            return dirt_left;
        if(id.equals("dc"))
            return dirt_center;
        if(id.equals("ds"))
            return dirt_mid;
        if(id.equals("dcl"))
            return dirt_cliff_left;
        if(id.equals("dcla"))
            return dirt_cliff_left_a;
        if(id.equals("dcr"))
            return dirt_cliff_right;
        if(id.equals("dcra"))
            return dirt_cliff_right_a;
        if(id.equals("dss"))
            return dirt_half;
        if(id.equals("dsl"))
            return dirt_half_left;
        if(id.equals("dsm"))
            return dirt_half_mid;
        if(id.equals("dsr"))
            return dirt_half_right;

        if(id.equals("nm"))
            return sand_mid;
        if(id.equals("nr"))
            return sand_right;
        if(id.equals("nl"))
            return sand_left;
        if(id.equals("nc"))
            return sand_center;
        if(id.equals("ns"))
            return sand;
        if(id.equals("ncl"))
            return sand_cliff_left;
        if(id.equals("ncla"))
            return sand_cliff_left_a;
        if(id.equals("ncr"))
            return sand_cliff_right;
        if(id.equals("ncra"))
            return sand_cliff_right_a;
        if(id.equals("nss"))
            return sand_half;
        if(id.equals("nsl"))
            return sand_half_left;
        if(id.equals("nsm"))
            return sand_half_mid;
        if(id.equals("nsr"))
            return sand_half_right;

        if(id.equals("lav"))
            return misc_lava;
        if(id.equals("lvt"))
            return misc_lava_top;
        if(id.equals("ltm"))
            return misc_lava_mid;
        if(id.equals("wat"))
            return misc_water;
        if(id.equals("wtt"))
            return misc_water_top;
        if(id.equals("wtm"))
            return misc_water_mid;

        if(id.equals("kb"))
            return key_blue;
        if(id.equals("kr"))
            return key_red;
        if(id.equals("kg"))
            return key_green;
        if(id.equals("ky"))
            return key_yellow;
        if(id.equals("lb"))
            return lock_blue;
        if(id.equals("lr"))
            return lock_red;
        if(id.equals("lg"))
            return lock_green;
        if(id.equals("ly"))
            return lock_yellow;

        if(id.equals("ext"))
            return exit;
        if(id.equals("ch"))
            return checkPoint;

        if(id.equals("spu"))
            return spike_steel_up;
        if(id.equals("spd"))
            return spike_steel_down;
        if(id.equals("spl"))
            return spike_steel_left;
        if(id.equals("spr"))
            return spike_steel_right;
        if(id.equals("sspu"))
            return spike_stone_up;
        if(id.equals("sspd"))
            return spike_stone_down;
        if(id.equals("sspl"))
            return spike_stone_left;
        if(id.equals("sspr"))
            return spike_stone_right;
        if(id.equals("nspu"))
            return spike_sand_up;
        if(id.equals("nspd"))
            return spike_sand_down;
        if(id.equals("nspl"))
            return spike_sand_left;
        if(id.equals("nspr"))
            return spike_sand_right;

        if(id.equals("swi"))
            return switchBlock;

        if(id.equals("gun"))
            return launcher;
        else{
            Block b = getEntity(id, x, y);
            if(b != null && b instanceof Entity){
                spawnEntity((Entity)b);
            } else if(b != null){
                return b;
            } else {
                return grass;
            }
        }
        
        return null;
    }
    
    public Block getEntity(String id, int x, int y){
        Entity b = null;
        if(id.charAt(0) == 'e'){
            String parse = id.substring(1);
            String[] options = parse.split("#");
            int name = Integer.parseInt(options[0]);
            
            switch(name){
            case 0:{

            }
            case 1:{
                int qnt = Integer.parseInt(options[1]);
                return ammo.clone(empty, qnt);
            }
            case 2:{
                int health = Integer.parseInt(options[1]);
                return bricks.clone(new Vector2(x, y), health);
            }
            case 3:{
                Block slave = parseID(options[1], x, y);
                AbstractAI.Direction dir = options[2].equals("0") ? AbstractAI.Direction.HORIZONTAL:AbstractAI.Direction.VERTICAL;
                float amount = Float.parseFloat(options[3]);
                float speed = Float.parseFloat(options[4]);

                float dx = dir.equals(AbstractAI.Direction.HORIZONTAL) ? amount : 0;
                float dy = dir.equals(AbstractAI.Direction.VERTICAL) ? amount : 0;

                return new BlockMob(new Vector2(x, y), slave, new AbsoluteLinearAI(new Vector2(dx, dy), speed, dir));
            }
            case 4:{
                float dx = Float.parseFloat(options[1]);
                float dy = height - Float.parseFloat(options[2]) - 1;

                return new DoorBlock(new Vector2(x, y), "door_open", new Vector2(dx, dy));
            }
            case 5:{
                int lx = Integer.parseInt(options[1]);
                int ly = Integer.parseInt(options[2]);
                int s = Integer.parseInt(options[3]);

                LockedDoor door = new LockedDoor(new Vector2(x, y), "door_locked", "door_open", new Vector2(lx, ly), s);
                Array<Swatch> swatchArray = new Array<Swatch>();
                for(int i = 4; i < options.length; i+=2){
                    boolean isExtra = options[i].startsWith("x");
                    int sx = Integer.parseInt(options[i].substring(isExtra ? 1 : 0));
                    int sy = height - Integer.parseInt(options[i + 1].substring(isExtra ? 1 : 0)) - 1;

                    swatchArray.add(new Swatch(door, sx, sy, isExtra));
                }
                swatches.put(x + y * width, swatchArray);
                return door;
            }
            }
        }
        
        return b;
    }
    
    public void spawnEntity(Entity e){
        entities.add(e);
    }
    public void despawnEntity(Entity e){
        entities.removeValue(e, false);
    }

    private final class Swatch{
        private boolean isExtra;
        private Block parent;
        private int x, y;

        public Swatch(Block parent, int x, int y, boolean isExtra){
            this.x = x;
            this.y = y;

            this.parent = parent;
            this.isExtra = isExtra;
        }

        public Block getParent(){
            return parent;
        }

        public Switchable getSwatch(){
            if(isExtra){
                for(int i = 0; i < extras.size; i++){
                    Vector2 curr = extras.get(i).getPosition();
                    if(curr.x == this.x && curr.y == this.y){
                        return (Switchable) extras.get(i);
                    }
                }
            } else {
                return (Switchable) blocks[this.x + this.y * width];
            }
            return null;
        }
    }
}
