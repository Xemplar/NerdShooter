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
package com.xemplar.games.android.nerdshooter.net;

import java.util.HashMap;
import java.util.Map;

public class AccountHandle {
    private String user, pass;
    private String key;


    public AccountHandle(String username, String password){
        this.user = username;
        this.pass = password;
    }

    public Map<String, String> getLoginCreds(){
        Map<String, String> creds = new HashMap<String, String>();
        creds.put("user", NetworkHandle.encrypt(user, NetworkHandle.ENC_KEY));
        creds.put("pass", NetworkHandle.encrypt(pass, NetworkHandle.ENC_KEY));

        return creds;
    }
}
