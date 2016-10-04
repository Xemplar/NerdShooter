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

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.Net.HttpMethods;
import com.badlogic.gdx.Net.HttpRequest;
import com.badlogic.gdx.Net.HttpResponseListener;
import com.badlogic.gdx.net.HttpParametersUtils;
import com.badlogic.gdx.utils.Base64Coder;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.HashMap;
import java.util.Map;

public class NetworkHandle {
    public static final String website = "http://www.xemplar.ml/nerdshooter/REST/";
    public static final String ENC_KEY = "QuGl4xrJelfSg7gk";
    private final AccountHandle account;

    public NetworkHandle(String user, String pass){
        this.account = new AccountHandle(user, pass);
    }

    public NetworkHandle(){
        this.account = new AccountHandle("anon", "anon");
    }

    public void getKey(final NetworkListener listener){
        sendRequest("ns_getkey", account.getLoginCreds(), listener);
    }

    public void getPackList(final NetworkListener listener){
        sendRequest("ns_levellist", account.getLoginCreds(), listener);
    }

    public void getPack(int id, final NetworkListener listener){
        Map<String, String> params = new HashMap<String, String>();
        params.putAll(account.getLoginCreds());
        params.put("id", encrypt(id + "", ENC_KEY));

        sendRequest("ns_getpack", params, listener);
    }

    protected void sendRequest(String method, Map<String, String> params, final NetworkListener listener){
        HttpRequest httpPost = new HttpRequest(HttpMethods.POST);
        httpPost.setUrl(website + method + ".php");
        httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded");
        httpPost.setContent(HttpParametersUtils.convertHttpParameters(params));

        Gdx.net.sendHttpRequest (httpPost, new HttpResponseListener() {
            public void handleHttpResponse(Net.HttpResponse httpResponse) {
                String responce = new String(httpResponse.getResult());
                String dat = decrypt(responce, ENC_KEY);
                listener.finished(dat);
                listener.length(dat.getBytes().length);
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
        return new String(Base64Coder.encode(crypted));
    }

    public static String decrypt(String input, String key){
        System.out.println(input);
        byte[] output = null;
        try{
            SecretKeySpec skey = new SecretKeySpec(key.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, skey);
            output = cipher.doFinal(Base64Coder.decode(input.replace("\\", "")));
        } catch(Exception e){
            System.out.println(e.toString());
        }
        return new String(output);
    }
}
