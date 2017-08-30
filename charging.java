/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
//#define SC
package test;

import javax.microedition.media.MediaException;
import javax.microedition.media.Player;
import net.rim.device.api.system.Alert;
import net.rim.device.api.system.KeyListener;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FieldChangeListener;
import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.component.ButtonField;
import net.rim.device.api.ui.container.PopupScreen;
import net.rim.device.api.ui.container.VerticalFieldManager;

/**
 *
 * @author Tiandi Zhang <tiandi.zhang@richina.com>
 */

class charging extends PopupScreen implements KeyListener {
    static private Player pp;
    static boolean eeror;
    public charging(Player p,boolean error){
        super(new VerticalFieldManager(),Field.FOCUSABLE);
        eeror = error;
        pp = p;
        String s1 = "Charging completed.";
    //#ifdef SC
     s1 = "充电完成";
          //#endif
        ButtonField b1 = new ButtonField(s1,ButtonField.CONSUME_CLICK|ButtonField.FIELD_HCENTER);
        b1.setChangeListener(new FieldChangeListener() {
            public void fieldChanged(Field field, int context) {
                charging.this.close();
            }
         });
         add(b1);
    }
 public void close(){
        UiApplication.getUiApplication().popScreen(this);
        Alert.stopVibrate();
        try {
            if (charging.eeror == false){
                //System.out.println("****BBextra Log****:Stop Charging music");
                charging.pp.stop();
            }
        } catch (MediaException ex) {
           charging.eeror = true;
         //do sth when can not find music file
          }
    }
public boolean keyChar(char key, int status, int time) {
         return super.keyChar(key, status, time);
   }
   /** Implementation of KeyListener.keyDown */
   public boolean keyDown(int keycode, int time) {
       return false;
   }
   /** Implementation of KeyListener.keyRepeat */
   public boolean keyRepeat(int keycode, int time) {
       return false;
   }
   /** Implementation of KeyListener.keyStatus */
   public boolean keyStatus(int keycode, int time) {
       return false;
   }
   /** Implementation of KeyListener.keyUp */
   public boolean keyUp(int keycode, int time) {
       return false;
   }
}