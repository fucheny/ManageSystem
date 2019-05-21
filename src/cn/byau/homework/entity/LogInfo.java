/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cn.byau.homework.entity;

/**
 *
 * @author Administrator
 */
public class LogInfo {
    private int id;
    private String userId;
    private String username;
    private String logintime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public LogInfo(String userId, String username, String logintime) {
        this.userId = userId;
        this.username = username;
        this.logintime = logintime;
    }

  

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the logintime
     */
    public String getLogintime() {
        return logintime;
    }

    /**
     * @param logintime the logintime to set
     */
    public void setLogintime(String logintime) {
        this.logintime = logintime;
    }
    
    
}
