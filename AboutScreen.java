/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import net.rim.device.api.system.Bitmap;
import net.rim.device.api.system.Characters;
import net.rim.device.api.system.DeviceInfo;
import net.rim.device.api.system.KeyListener;
import net.rim.device.api.system.PersistentObject;
import net.rim.device.api.system.PersistentStore;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FieldChangeListener;
import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.component.BitmapField;
import net.rim.device.api.ui.component.ButtonField;
import net.rim.device.api.ui.component.Dialog;
import net.rim.device.api.ui.component.EditField;
import net.rim.device.api.ui.component.LabelField;
import net.rim.device.api.ui.component.RichTextField;
import net.rim.device.api.ui.component.SeparatorField;
import net.rim.device.api.ui.container.MainScreen;
import net.rim.device.api.ui.container.PopupScreen;
import net.rim.device.api.ui.container.VerticalFieldManager;
/**
 *
 * @author Tiandi Zhang <tiandi.zhang@richina.com>
 */

class AboutScreen extends MainScreen implements KeyListener{
    private Bitmap myBitmap = Bitmap.getBitmapResource("extra100.png");
    public static ButtonField regbutton;
    String s1 = "About BBextra";
    String s2 = "BBextra " + globel.ver +"\nCopy Right 2010 Tiandi\nTwitter:richina00\nSina:tiandi79\nwww.feelberry.com\n";
    String s3 = "Register";
    String s4 = "Expired";
    String s5 = "Registerd";
    String s6 = "Please input SN code(Press ENTER to confirm): \n";
    String s7 = "Register successed!\nPlease restart your device.";
    String s8 = "Invalid code!";
    public AboutScreen() {
        super();
     //#ifdef SC
//#          s1 = "关于BBextra";
//#          s2 = "BBExtra " + globel.ver +"\nCopy Right 2010 Tiandi\n推特:richina00\n新浪微博:tiandi79\nwww.feelberry.com\n";
//#          s3 = "注册";
//#          s4 = "已过期";
//#          s5 = "已注册";
//#          s6 = "请输入注册码，按回车确认。";
//#          s7 = "注册成功，请重启设备。";
//#          s8 = "无效的注册码!";
          //#endif

        final PersistentObject store;

        LabelField title = new LabelField(s1,LabelField.ELLIPSIS|LabelField.USE_ALL_WIDTH|LabelField.VCENTER);
        setTitle(title);
        BitmapField bitmapField = new BitmapField(myBitmap,BitmapField.FIELD_HCENTER);
        add(bitmapField);
        SeparatorField line1 = new SeparatorField();
        add(line1);
        add(new RichTextField(s2,RichTextField.TEXT_ALIGN_HCENTER));
        store = PersistentStore.getPersistentObject (0xa2f31503d67bce1dL);
        // clear register info
//        store.setContents(null);
//        store.commit();
        //
        synchronized (store) {
            if (store.getContents() == null) {
                 regbutton = new ButtonField(s3,ButtonField.CONSUME_CLICK|ButtonField.FIELD_HCENTER);
            }
            else if (Integer.parseInt((String) store.getContents()) < 0) {
                 regbutton = new ButtonField(s4,ButtonField.CONSUME_CLICK|ButtonField.FIELD_HCENTER);
            }
            else if (Integer.parseInt((String) store.getContents()) < 10081) {
                 regbutton = new ButtonField(s3+Integer.parseInt((String) store.getContents()),ButtonField.CONSUME_CLICK|ButtonField.FIELD_HCENTER);
            }
            else{
                 regbutton = new ButtonField(s5,ButtonField.CONSUME_CLICK|ButtonField.FIELD_HCENTER);
                 regbutton.setEditable(false);
            }
        }
        add(regbutton);
        regbutton.setChangeListener(new FieldChangeListener() {

            public void fieldChanged(Field field, int context) {
                UiApplication.getUiApplication().pushScreen(new snscreen());
            }
        });

    }
    public boolean keyChar(char key, int status, int time) {
        return false;
    }
       /** Implementation of KeyListener.keyDown */
   public boolean keyDown(int keycode, int time) {
       return false;
   }
   /** Implementation of KeyListener.keyRepeat */
   public boolean keyRepeat(int keycode, int time) {
         // clear register info
        PersistentObject store = PersistentStore.getPersistentObject (0xa2f31503d67bce1dL);
        if(store.getContents()!= null) {
        if (keycode == 524288 & Integer.parseInt((String) store.getContents()) > 90000 ) {
        store.setContents(null);
        store.commit();
        Dialog.alert("Reset registeration.Please register again.");
        }
        }
       return true;
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
class snscreen extends PopupScreen implements KeyListener{
    EditField question;
    String s8 = "Invalid code!";
    String s6 = "Please input SN code(Press ENTER to confirm): \n";
    String s5 = "Registerd";
    String s7 = "Register successed!\nPlease restart your device.";
    public snscreen(){
       super(new VerticalFieldManager(),Field.FOCUSABLE);
     //#ifdef SC
//#         s6 = "请输入注册码，按回车确认。";
//#         s8 = "无效的注册码!";
//#         s5 = "已注册";
//#         s7 = "注册成功，请重启设备。";
    //#endif
       question = new EditField(s6,"",15,EditField.FIELD_HCENTER);
       add(question);
    }

public boolean keyChar(char key, int status, int time) {
       //intercept the ESC key - exit the app on its receipt
       if (key == Characters.ESCAPE)
           close();
       else if(key == Characters.ENTER){
           if (question.getText().length() != 15) {
               Dialog.alert(s8);
               close();
               return true;
           }
           String sn,str1,str2,str3,str4,str5,str6,str7,str8;
           str1 = question.getText().substring(0, 2);
           str2 = question.getText().substring(2, 4);
           str3 = question.getText().substring(4, 6);
           str4 = question.getText().substring(6,7);
           str5 = question.getText().substring(7,9);
           str6 = question.getText().substring(9,11);
           str7 = question.getText().substring(11,13);
           str8 = question.getText().substring(13,15);
           int tmp = DeviceInfo.getDeviceId() % 3;
           if(tmp == 0){
                str1 = String.valueOf((Integer.parseInt(str1)+12)*3/7);
                if(str1.length() < 2)
                     str1 = str1 + "1";
                str2 = String.valueOf((Integer.parseInt(str2)+52)*3/7);
                if(str2.length() < 2)
                str2 = str2 + "2";
                str3 = String.valueOf((Integer.parseInt(str3)+91)*3/7);
                if(str3.length() < 2)
                    str3 = str3 + "3";
                str4 = String.valueOf((Integer.parseInt(str4)+22)*3/7);
                if (str4.length()>1)
                    str4 = str4.substring(1);
           }
           else if(tmp == 1) {
                str1 = String.valueOf((Integer.parseInt(str1)+15)*7/17);
                if(str1.length() < 2)
                    str1 = str1 + "5";
                str2 = String.valueOf((Integer.parseInt(str2)+17)*7/17);
                if(str2.length() < 2)
                    str2 = str2 + "6";
                str3 = String.valueOf((Integer.parseInt(str3)+35)*7/17);
                if(str3.length() < 2)
                    str3 = str3 + "7";
                str4 = String.valueOf((Integer.parseInt(str4)+60)*7/17);
                if (str4.length()>1)
                    str4 = str4.substring(1);
           }
           else{
                str1 = String.valueOf((Integer.parseInt(str1)+15)*4/9);
                if(str1.length() < 2)
                    str1 = str1 + "3";
                str2 = String.valueOf((Integer.parseInt(str2)+17)*4/9);
                if(str2.length() < 2)
                    str2 = str2 + "5";
                str3 = String.valueOf((Integer.parseInt(str3)+35)*4/9);
                if(str3.length() < 2)
                    str3 = str3 + "7";
                str4 = String.valueOf((Integer.parseInt(str4)+60)*4/9);
                if (str4.length()>1)
                    str4 = str4.substring(1);
           }
        str5 = String.valueOf((Integer.parseInt(str5)+33)*9/19);
        if(str5.length() < 2)
            str5 = str5 + "5";
        str6 = String.valueOf((Integer.parseInt(str6)+44)*9/19);
        if(str6.length() < 2)
            str6 = str6 + "3";
        str7 = String.valueOf((Integer.parseInt(str7)+55)*9/19);
        if(str7.length() < 2)
            str7 = str7 + "7";
        str8 = String.valueOf((Integer.parseInt(str8)+77)*9/19);
        if(str8.length() < 2)
            str8 = str8 + "1";

           sn = mainscreen.makesn();
           if((str1+str2+str3+str4+str5+str6+str7+str8).equalsIgnoreCase(sn)){
               PersistentObject store;
               store = PersistentStore.getPersistentObject (0xa2f31503d67bce1dL);
               synchronized (store) {
                    store.setContents(String.valueOf(99999));
                    store.commit();
               }
               AboutScreen.regbutton.setEditable(false);
               AboutScreen.regbutton.setLabel(s5);

               Dialog.alert(s7);
               globel.canrun = true;
           }
           else
               Dialog.alert(s8);
           close();
       }
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
