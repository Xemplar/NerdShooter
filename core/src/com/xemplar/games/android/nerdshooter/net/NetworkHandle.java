/*
 * NerdShooter is a pseudo library project for future Xemplar 2D Side Scroller Games.
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
package com.xemplar.games.android.nerdshooter.net;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.Net.*;
import com.badlogic.gdx.net.HttpParametersUtils;
import org.json.*;

import java.util.Base64;
import java.util.Map;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class NetworkHandle {
    public static final String website = "http://localhost/nerdshooter/REST/";
    public static final String ENC_KEY = "QuGl4xrJelfSg7gk";
    private final AccountHandle account;

    public NetworkHandle(String user, String pass){
        this.account = new AccountHandle(user, pass);
    }

    public void getKey(final NetworkListener listener){
        sendRequest("ns_getkey", account.getLoginCreds(), listener);
    }

    public void sendRequest(String method, Map<String, String> params, final NetworkListener listener){
        HttpRequest httpPost = new HttpRequest(HttpMethods.POST);
        httpPost.setUrl(website + method + ".php");
        httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded");
        httpPost.setContent(HttpParametersUtils.convertHttpParameters(params));

        Gdx.net.sendHttpRequest (httpPost, new HttpResponseListener() {
            public void handleHttpResponse(Net.HttpResponse httpResponse) {
                String dat = decrypt(new String(httpResponse.getResult()), ENC_KEY);
                listener.finished(new JSONObject(dat));
            }

            public void failed(Throwable t) {
                listener.failed(t);
            }

            public void cancelled(){

            }
        });
    }

    public static String encrypt(String input, String key){
        byte[] crypted = null;
        try{
            SecretKeySpec skey = new SecretKeySpec(key.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, skey);
            crypted = cipher.doFinal(input.getBytes());
        }catch(Exception e){
            System.out.println(e.toString());
        }
        return new String(Base64.getEncoder().encodeToString(crypted));
    }

    public static String decrypt(String input, String key){
        System.out.println(input);
        byte[] output = null;
        try{
            SecretKeySpec skey = new SecretKeySpec(key.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, skey);
            output = cipher.doFinal(Base64.getDecoder().decode(input.replace("\\", "")));
        } catch(Exception e){
            System.out.println(e.toString());
        }
        return new String(output);
    }
}
