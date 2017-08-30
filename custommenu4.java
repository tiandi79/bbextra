/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package test;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.TimerTask;
import javax.microedition.io.Connector;
import javax.microedition.io.file.FileConnection;
import javax.microedition.media.MediaException;
import javax.microedition.media.Player;
import javax.microedition.pim.PIMException;
import javax.microedition.pim.PIMItem;
import javax.wireless.messaging.TextMessage;
import net.rim.blackberry.api.invoke.AddressBookArguments;
import net.rim.blackberry.api.invoke.Invoke;
import net.rim.blackberry.api.invoke.PhoneArguments;
import net.rim.blackberry.api.menuitem.ApplicationMenuItem;
import net.rim.blackberry.api.pdap.BlackBerryContact;
import net.rim.blackberry.api.pdap.BlackBerryContactList;
import net.rim.blackberry.api.pdap.BlackBerryPIM;
import net.rim.blackberry.api.phone.phonelogs.PhoneCallLog;
import net.rim.device.api.system.Alert;
import net.rim.device.api.system.ApplicationManager;
import net.rim.device.api.system.ApplicationManagerException;
import net.rim.device.api.system.Bitmap;
import net.rim.device.api.system.Characters;
import net.rim.device.api.system.Clipboard;
import net.rim.device.api.system.Display;
import net.rim.device.api.system.EncodedImage;
import net.rim.device.api.system.EventInjector;
import net.rim.device.api.system.KeyListener;
import net.rim.device.api.system.KeypadListener;
import net.rim.device.api.system.LED;
import net.rim.device.api.system.PNGEncodedImage;
import net.rim.device.api.system.PersistentObject;
import net.rim.device.api.system.PersistentStore;
import net.rim.device.api.ui.DrawStyle;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FieldChangeListener;
import net.rim.device.api.ui.Keypad;
import net.rim.device.api.ui.Screen;
import net.rim.device.api.ui.Ui;
import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.component.BitmapField;
import net.rim.device.api.ui.component.ButtonField;
import net.rim.device.api.ui.component.Dialog;
import net.rim.device.api.ui.component.LabelField;
import net.rim.device.api.ui.component.Menu;
import net.rim.device.api.ui.component.TextField;
import net.rim.device.api.ui.container.FlowFieldManager;
import net.rim.device.api.ui.container.PopupScreen;
import net.rim.device.api.ui.container.VerticalFieldManager;

/**
 *
 * @author Tiandi Zhang <tiandi.zhang@richina.com>
 */
class custommenu4 extends ApplicationMenuItem{
    custommenu4(){
        super(5000);
    }

    public Object run(Object context) {
        boolean candial = true;
        int i;
        PersistentObject store = PersistentStore.getPersistentObject(0x2e7547ff0ac3340aL);
        Field focus = UiApplication.getUiApplication().getActiveScreen().getLeafFieldWithFocus();
        if(focus != null) {
            //System.out.println("****BBextra Log****:start to check ip number");
            String tmp =focus.getLeafFieldWithFocus().toString();
                if(tmp.indexOf("+")> -1){
                    tmp = tmp.substring(1);
                    tmp = "00"+tmp;
                }
            //System.out.println("****BBextra Log****:tmp="+tmp);
            for(i=0;i<tmp.length();i++) {
                if(tmp.charAt(i)!= '1'
                       &&tmp.charAt(i)!= '2'
                       &&tmp.charAt(i)!= '3'
                       &&tmp.charAt(i)!= '4'
                       &&tmp.charAt(i)!= '5'
                       &&tmp.charAt(i)!= '6'
                       &&tmp.charAt(i)!= '7'
                       &&tmp.charAt(i)!= '8'
                       &&tmp.charAt(i)!= '9'
                       &&tmp.charAt(i)!= '0')
                    candial = false;
            }
             //System.out.println("****BBextra Log****:candial="+candial);
                     if (candial) {
                            synchronized (store) {
                            if (store.getContents() == null) {
                                String s1 = "Please check your IP number.";
    //#ifdef SC
//#      s1 = "请检查预设的IP号码";
          //#endif
                              Dialog.alert(s1);
                              return null;
                            }
                            if(store.getContents().toString().equalsIgnoreCase("")) {
                                String s1 = "Please check your IP number.";
     //#ifdef SC
//#      s1 = "请检查预设的IP号码";
          //#endif
                             Dialog.alert(s1);
                              return null;
                            }
                                 String ip = store.getContents().toString();
                                 ip = ip + tmp;
                                 PhoneArguments call = new PhoneArguments (PhoneArguments.ARG_CALL,ip);
                        Invoke.invokeApplication(Invoke.APP_TYPE_PHONE, call);
            }
        }
             else {
                     String s1 ="Please select phone number.";
     //#ifdef SC
//#      s1 = "请先选中电话号码";
          //#endif
                     Dialog.alert(s1);
                     return null;
             }
        }
        return null;
    }
    public String toString() {
        String s1 ="IP call";
     //#ifdef SC
//#      s1 = "IP拨号";
          //#endif
        return s1;
    }
}
class custommenu extends ApplicationMenuItem{
     custommenu(){
        super(5);
    }

    public Object run(Object context) {
    String s1 = " added to blacklist!";
    //#ifdef SC
//#      s1 = "已添加到黑名单";
          //#endif

        Field focus = UiApplication.getUiApplication().getActiveScreen().getLeafFieldWithFocus();
        if(focus != null) {
            PersistentObject store;
            String e1;
            store = PersistentStore.getPersistentObject (0x55a9ef284cd02d7bL);
            synchronized (store) {
                 if (store != null)
                      e1 = (String) store.getContents();
                 else
                      e1= "";
                    }
            if(e1.equalsIgnoreCase(""))
                store.setContents(focus.getLeafFieldWithFocus().toString());
            else
                store.setContents(e1+"\n"+focus.getLeafFieldWithFocus().toString());
            store.commit();
            Dialog.alert(focus.getLeafFieldWithFocus().toString()+s1);
   }
        return null;
    }

    public String toString() {
        String s2 = "Add to blacklist";
                    //#ifdef SC
//#      s2 = "添加至黑名单";
          //#endif
        return s2;
    }
}
class screenshotmenu extends ApplicationMenuItem{
    screenshotmenu(){
        super(6);
    }
    public Object run(Object context)
    {
         synchronized(BBextra.getEventLock()){
             BBextra.getApplication().invokeLater(new Runnable() {
                 public void run() {
                    //Write out the data to the file.
        DataOutputStream out = null;
        try {
            int width = Display.getWidth();
            int height = Display.getHeight();
            Bitmap bm = new Bitmap(width, height);
            Display.screenshot(bm);
            PNGEncodedImage png = PNGEncodedImage.encode(bm);
        try {
            FileConnection fc = (FileConnection) Connector.open("file:///SDCard/BBextra/", Connector.READ_WRITE);
            if (!fc.exists()) {
                fc.mkdir();
            }
         } catch (Exception e) {
                Dialog.alert(e.toString());
         }
            int i = 1;
            FileConnection fc = (FileConnection) Connector.open("file:///SDCard/BBextra/BBescreen"+i+".png");
            while (fc.exists()) {
                i++;
                fc = (FileConnection) Connector.open("file:///SDCard/BBextra/BBescreen"+i+".png");
            }
            fc.create();
            out = fc.openDataOutputStream();
            out.write(png.getData());
//Close the Connections.
            out.close();
            fc.close();
            Ui.getUiEngine().pushGlobalScreen(new saveto(i),5,0);
//            ApplicationManager manager = ApplicationManager.getApplicationManager();
//            manager.postGlobalEvent(0x6bde11d91989a4acL, i, 0, null, null);
//            Dialog.alert("Screenshot file save to SDCard/BBextra/BBescreen"+i+".png");

        } catch (IOException ex) {
            Dialog.alert(ex.getMessage());
        } finally {
            try {
                if(out !=null)
                    out.close();
            } catch (IOException ex) {
                 Dialog.alert(ex.getMessage());
            }
        }
      }
                        });

                    }
        return null;
    }

    public String toString() {
       String s2 = "ScreenShot";
          //#ifdef SC
//#      s2 = "截屏";
          //#endif
        return s2;
    }
}
class flashlight extends ApplicationMenuItem{
    flashlight(){
        super(5);
    }
    public Object run(Object context){
        try {
            ApplicationManager.getApplicationManager().launch("net_rim_bb_videorecorder");
         }
        catch (ApplicationManagerException ex)  {
         }
        try {
            Thread.sleep(5000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        EventInjector.invokeEvent(new EventInjector.KeyCodeEvent(
                                             EventInjector.KeyCodeEvent.KEY_UP,(char) Keypad.KEY_SPACE,KeypadListener.STATUS_NOT_FROM_KEYPAD));
        return null;
    }
    public String toString() {
        String s2 = "Launch VideoRecoard";
          //#ifdef SC
//#      s2 = "手电筒";
          //#endif
        return s2;
    }
}
class custommenu2 extends ApplicationMenuItem{
     custommenu2(){
        super(5);
    }

    public Object run(Object context) {
        //  below by BT.Seven
        String number;
            String s1 = " added to blacklist!";
    //#ifdef SC
//#      s1 = "已添加到黑名单";
          //#endif
        if (context instanceof PhoneCallLog){
                               PhoneCallLog pcl = (PhoneCallLog)context;
                              number = pcl.getParticipant().getNumber();
                     }else{
                         number = (String)context;
                         if(number == null)
                             number = UiApplication.getUiApplication().getActiveScreen().getLeafFieldWithFocus().toString();
                         if(number.indexOf("+")> -1){
                                number = number.substring(1);
                                number = "00"+number;
                         }
                      }
        //  above by BT.Seven
            PersistentObject store;
            String e1;
            store = PersistentStore.getPersistentObject (0x55a9ef284cd02d7bL);
            synchronized (store) {
                 if (store != null)
                      e1 = (String) store.getContents();
                 else
                      e1= "";
                    }
            if(e1.equalsIgnoreCase(""))
                store.setContents(number);
            else
                store.setContents(e1+"\n"+number);
            store.commit();
        Dialog.alert(number+s1);
        return null;
    }

    public String toString() {
        String s2 = "Add to blacklist";
        //#ifdef SC
//#      s2 = "添加至黑名单";
          //#endif
        return s2;
    }
}

class custommenu3 extends ApplicationMenuItem{
     String str ="sms content";
//     String[] num = new String [50];
     String signature = "  Sent via Blackberry";
     boolean flag = false;
     custommenu3(){
        super(20);
    }

    public Object run(Object context) {
        Field f;
        Screen s = null;
        Menu menu1 = null;

        PersistentObject store = PersistentStore.getPersistentObject(0x80e0d842a0e9fe05L);
        synchronized (store) {
            if (store.getContents() != null) {
                signature = "  "+store.getContents().toString();
            }
        }
        s = UiApplication.getUiApplication().getActiveScreen();

        flag = false;
        for(int i=0;i< s.getFieldCount();i++)
        {
            f = s.getField(i);
            printFields(f);
            if(flag)
                break;
        }
        menu1 = s.getMenu(0);
        for(int j=0;j<menu1.getSize();j++)
        {

            if(menu1.getItem(j).toString().equalsIgnoreCase("Send"))
                menu1.getItem(j).run();
        }

//        num = null;
        return null;
    }
   private void printFields (Field field)
         {
             try
             {
                         if (field instanceof TextField) {
                             TextField textField = (TextField) field;
                             if((textField.getClass().getName().endsWith("SMSEditField") ||
                                      textField.getClass ().getName ().endsWith(".SMSUiReplyField")) && !flag)
                             {
//                                 textField.insert(signature);
                                 Clipboard cp1 = Clipboard.getClipboard();
                                 cp1.put(null);
                                 cp1.put(signature);
                                 textField.paste(cp1);
                                 cp1.put(null);
                                 flag = true;
                             }
                         }
                         if(field.getClass().getName().endsWith("SMSUiConversationManager")){
                             //System.out.println("****BBextra Log****:Detect 5.0 UI");
                             Field focus = UiApplication.getUiApplication().getActiveScreen().getLeafFieldWithFocus();
                             System.out.println("****BBextra Log****:5.0 focus="+focus.getClass().getName());
                             printFields (focus);
                         }
                         else if (field instanceof net.rim.device.api.ui.Manager)
                         {
                             System.out.println("****BBextra Log****:detect manager="+field.getClass().getName());
                             net.rim.device.api.ui.Manager mgrField = (net.rim.device.api.ui.Manager) field;
                                for (int i = 0; i < mgrField.getFieldCount(); i++) {
                                        Field subFields = mgrField.getField(i);
                                        printFields (subFields);
                        }
                    }
                }
                catch (Exception e)
                {
                    //System.out.println("****BBextra Log****:Information error="+e.toString());
                }
        }
    public String toString() {
            String s1 = "Send With Signature";
    //#ifdef SC
//#      s1 = "附带短信签名发送";
          //#endif
        return s1;
    }
}



class custommenu5 extends ApplicationMenuItem{
     custommenu5(){
        super(5000);
    }

    public Object run(Object context) {
        //  below by BT.Seven
        String number;
        PersistentObject store = PersistentStore.getPersistentObject(0x2e7547ff0ac3340aL);
        if (context instanceof PhoneCallLog){
                               PhoneCallLog pcl = (PhoneCallLog)context;
                              number = pcl.getParticipant().getNumber();
                                //System.out.println("****BBextra Log****:Context can be change to Phonecalllog");
        }else{
             //System.out.println("****BBextra Log****:Context can not be change to Phonecalllog");
                           number = (String)context;
                                if(number == null)
                                    number = UiApplication.getUiApplication().getActiveScreen().getLeafFieldWithFocus().toString();
                                    if(number.indexOf("+")> -1){
                    number = number.substring(1);
                    number = "00"+number;
                     }
        }
       //  above by BT.Seven
                            synchronized (store) {
                            if (store.getContents() != null) {
                                if(store.getContents().toString().equalsIgnoreCase("")) {
                        String s1 = "Please check your IP number.";
    //#ifdef SC
//#      s1 = "请检查预设的IP号码";
          //#endif
                              Dialog.alert(s1);
                              return null;
                            }
                                 String ip = store.getContents().toString();
                                 if( !number.startsWith(ip))
                                     ip = ip + number;
                                 else
                                     ip = number;
                                 PhoneArguments call = new PhoneArguments (PhoneArguments.ARG_CALL,ip);
                        Invoke.invokeApplication(Invoke.APP_TYPE_PHONE, call);
            }
        }
        return null;
    }

    public String toString() {
    String s1 = "IP call";
    //#ifdef SC
//#      s1 = "IP拨号";
          //#endif
        return s1;
    }
}
class custommenu6 extends ApplicationMenuItem{
    static int flag = 0;
     custommenu6(){
        super(5000);
    }

    public Object run(Object context) {
        String tel[] = {"","","","","","","",""};
        String email[] = {"","",""};

        BlackBerryContactList list = null;
        try {
            list = (BlackBerryContactList) BlackBerryPIM.getInstance().openPIMList(BlackBerryPIM.CONTACT_LIST, BlackBerryPIM.READ_ONLY);
        } catch (PIMException ex) {
            ex.printStackTrace();
        }

        PIMItem pimItem = list.choose ();
        if(pimItem != null) {
        if(pimItem.countValues(BlackBerryContact.TEL)>0) {
            for(int i=0;i<pimItem.countValues(BlackBerryContact.TEL);i++)
                tel[i] = pimItem.getString(BlackBerryContact.TEL, i);
        }
        if(pimItem.countValues(BlackBerryContact.EMAIL)>0) {
            for(int i=0;i<pimItem.countValues(BlackBerryContact.EMAIL);i++)
                email[i] = pimItem.getString(BlackBerryContact.EMAIL, i);
        }
        if(pimItem.countValues(BlackBerryContact.TEL)>0 | pimItem.countValues(BlackBerryContact.EMAIL)>0)
            UiApplication.getUiApplication().pushScreen(new inscreen(tel,email));
        }
        return null;
    }

    public String toString() {
           String s1 = "Insert contact info";
    //#ifdef SC
//#      s1 = "插入联系人信息";
          //#endif
        return s1;
    }
}
class inscreen extends PopupScreen implements KeyListener{
    private String number[] = {"","","","","","","",""};
    private String email[] = {"","",""};
    private ButtonField b1,b2,b3,b4,b5,b6,b7,b8,b9,b10,b11;
    public static String infomation = null;
   public inscreen(String[] a,String[] b)
   {
       super(new FlowFieldManager(),Field.FOCUSABLE);

       number = a;
       email = b;
           String s1 = "Insert info:";
    //#ifdef SC
//#     s1 = "插入信息";
          //#endif
       add(new LabelField(s1));
       if (!number[0].equalsIgnoreCase("")) {
           b1 = new ButtonField(number[0],ButtonField.CONSUME_CLICK);
           add(b1);
           b1.setChangeListener(new FieldChangeListener() {
                public void fieldChanged(Field field, int context) {
                    infomation = number[0];
                    inscreen.this.close();
                }
            });
       }
       if (!number[1].equalsIgnoreCase("")) {
           b2 = new ButtonField(number[1],ButtonField.CONSUME_CLICK|ButtonField.FIELD_HCENTER);
           add(b2);
           b2.setChangeListener(new FieldChangeListener() {
                public void fieldChanged(Field field, int context) {
                    infomation = number[1];
                    inscreen.this.close();
                }
            });
       }
       if (!number[2].equalsIgnoreCase("")) {
           b3 = new ButtonField(number[2],ButtonField.CONSUME_CLICK|ButtonField.FIELD_HCENTER);
           add(b3);
           b3.setChangeListener(new FieldChangeListener() {
                public void fieldChanged(Field field, int context) {
                    infomation = number[2];
                    inscreen.this.close();
                }
            });
       }
       if (!number[3].equalsIgnoreCase("")) {
           b4 = new ButtonField(number[3],ButtonField.CONSUME_CLICK|ButtonField.FIELD_HCENTER);
           add(b4);
           b4.setChangeListener(new FieldChangeListener() {
                public void fieldChanged(Field field, int context) {
                    infomation = number[3];
                    inscreen.this.close();
                }
            });
       }
       if (!number[4].equalsIgnoreCase("")) {
           b5 = new ButtonField(number[4],ButtonField.CONSUME_CLICK|ButtonField.FIELD_HCENTER);
           add(b5);
           b5.setChangeListener(new FieldChangeListener() {
                public void fieldChanged(Field field, int context) {
                    infomation = number[4];
                    inscreen.this.close();
                }
            });
       }
       if (!number[5].equalsIgnoreCase("")) {
           b6 = new ButtonField(number[5],ButtonField.CONSUME_CLICK|ButtonField.FIELD_HCENTER);
           add(b6);
           b6.setChangeListener(new FieldChangeListener() {
                public void fieldChanged(Field field, int context) {
                    infomation = number[5];
                    inscreen.this.close();
                }
            });
       }
       if (!number[6].equalsIgnoreCase("")) {
           b7 = new ButtonField(number[6],ButtonField.CONSUME_CLICK|ButtonField.FIELD_HCENTER);
           add(b7);
           b7.setChangeListener(new FieldChangeListener() {
                public void fieldChanged(Field field, int context) {
                    infomation = number[6];
                    inscreen.this.close();
                }
            });
       }
       if (!number[7].equalsIgnoreCase("")) {
           b8 = new ButtonField(number[7],ButtonField.CONSUME_CLICK|ButtonField.FIELD_HCENTER);
           add(b8);
           b8.setChangeListener(new FieldChangeListener() {
                public void fieldChanged(Field field, int context) {
                    infomation = number[7];
                    inscreen.this.close();
                }
            });
       }
       if (!email[0].equalsIgnoreCase("")) {
           b9 = new ButtonField(email[0],ButtonField.CONSUME_CLICK|ButtonField.FIELD_HCENTER);
           add(b9);
           b9.setChangeListener(new FieldChangeListener() {
                public void fieldChanged(Field field, int context) {
                    infomation = email[0];
                    inscreen.this.close();
                }
            });
       }
       if (!email[1].equalsIgnoreCase("")) {
           b10 = new ButtonField(email[1],ButtonField.CONSUME_CLICK|ButtonField.FIELD_HCENTER);
           add(b10);
           b10.setChangeListener(new FieldChangeListener() {
                public void fieldChanged(Field field, int context) {
                    infomation = email[1];
                    inscreen.this.close();
                }
            });
       }
       if (!email[2].equalsIgnoreCase("")) {
           b11 = new ButtonField(email[2],ButtonField.CONSUME_CLICK|ButtonField.FIELD_HCENTER);
           add(b11);
           b11.setChangeListener(new FieldChangeListener() {
                public void fieldChanged(Field field, int context) {
                    infomation = email[2];
                    inscreen.this.close();
                }
            });
       }
   }
   public void close() {
       UiApplication.getUiApplication().popScreen(this);
       //System.out.println("****BBextra Log****:close info screen");
       Screen screen = UiApplication.getUiApplication().getActiveScreen();
       for (int i = 0; i < screen.getFieldCount(); i++)
             {
                 Field field = screen.getField(i);
                 //System.out.println("****BBextra Log****:getfieldname ="+field.toString());
                 printFields (field, inscreen.infomation);
             }
   }
   // by pangwa
   private void printFields (Field field, String str)
         {
             //System.out.println("****BBextra Log****:now field name="+field.getClass().getName());
             try
             {
                         if (field instanceof TextField) {
                             //System.out.println("****BBextra Log****:field can instanc to textfield");
                             TextField textField = (TextField) field;
                             //System.out.println("****BBextra Log****:Infomation="+str);
                             //System.out.println("****BBextra Log****:Textname ="+textField.getClass().getName());
                                 if (textField.getClass ().getName ().endsWith(".SMSEditField") ||
                                   textField.getClass ().getName ().endsWith(".SMSUiReplyField"))
                                 {
                                    textField.insert (str);
                                }
                                 else if(textField.getClass().getName().endsWith("ActiveAutoTextEditField")){
                                     if(custommenu6.flag == 1){
                                         textField.insert(str);
                                         custommenu6.flag = 0;
                                     }
                                     else
                                         custommenu6.flag = 1;
                                 }
                         }
                         if(field.getClass().getName().endsWith("SMSUiConversationManager")){
                             //System.out.println("****BBextra Log****:Detect 5.0 UI");
                             Field focus = UiApplication.getUiApplication().getActiveScreen().getLeafFieldWithFocus();
                             //System.out.println("****BBextra Log****:5.0 focus="+focus.getClass().getName());
                             printFields (focus,str);
                         }
                         else if (field instanceof net.rim.device.api.ui.Manager)
                         {
                             //System.out.println("****BBextra Log****:field can instanof manager");
                             net.rim.device.api.ui.Manager mgrField = (net.rim.device.api.ui.Manager) field;
                             //System.out.println("****BBextra Log****:field in manager="+mgrField.getFieldCount());
                                for (int i = 0; i < mgrField.getFieldCount(); i++) {
                                        Field subFields = mgrField.getField(i);
                                        printFields (subFields, str);
                        }
                    }
//                         else
                            //System.out.println("****BBextra Log****:field can not instanof anyfield.");
                }
                catch (Exception e)
                {
                    //System.out.println("****BBextra Log****:Information error="+e.toString());
                }
        }
   public boolean keyChar(char key, int status, int time) {
         if (key == Characters.ESCAPE){
             infomation = null;
             UiApplication.getUiApplication().popScreen(this);
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
final class pScreen extends PopupScreen implements KeyListener {
    static Screen tmpscreen = null;
    String s1 = "Add number to address book?";
    String s2 = "Add as new contact";
    String s3 = "Update existed contact";
    String s4 = "Cancel";
   public pScreen()
   {
       super(new VerticalFieldManager(),Field.FOCUSABLE);
  //#ifdef SC
//#       s1 = "添加号码到通讯录？";
//#       s2 = "添加新联系人";
//#       s3 = "更新已存在联系人";
//#       s4 = "取消";
          //#endif
       tmpscreen = pScreen.this;
       LabelField question = new LabelField(s1);
       add(question);
       ButtonField myButton = new ButtonField(s2,ButtonField.CONSUME_CLICK | DrawStyle.HCENTER|ButtonField.FIELD_HCENTER);
       add(myButton);
       ButtonField myButton2 = new ButtonField(s3,ButtonField.CONSUME_CLICK | DrawStyle.HCENTER|ButtonField.FIELD_HCENTER);
       add(myButton2);
       ButtonField myButton3 = new ButtonField(s4,ButtonField.CONSUME_CLICK | DrawStyle.HCENTER|ButtonField.FIELD_HCENTER);
       add(myButton3);

       class addlistener implements FieldChangeListener {
            public void fieldChanged(Field field, int context) {
                pScreen.this.close();
                BlackBerryContactList contacts = null;
                try {
                    contacts = (BlackBerryContactList) BlackBerryPIM.getInstance().openPIMList(BlackBerryPIM.CONTACT_LIST, BlackBerryPIM.READ_WRITE);
                }catch (javax.microedition.pim.PIMException ex) {
                    Dialog.alert(ex.getMessage());
                return;
                 }
                 BlackBerryContact contact = (BlackBerryContact) contacts.createContact();
                    if (contacts.isSupportedField(BlackBerryContact.TEL))
                        contact.addString(BlackBerryContact.TEL, BlackBerryContact.ATTR_MOBILE, globel.phoneno);
                    Invoke.invokeApplication(
                    Invoke.APP_TYPE_ADDRESSBOOK,
                    new AddressBookArguments(AddressBookArguments.ARG_NEW,contact)
                );
                globel.phoneno = null;
                tmpscreen = null;
               }

         }
       class addlistener2 implements FieldChangeListener {
            public void fieldChanged(Field field, int context) {
                pScreen.this.close();

                BlackBerryContactList contacts = null;
                try {
                    contacts = (BlackBerryContactList) BlackBerryPIM.getInstance().openPIMList(BlackBerryPIM.CONTACT_LIST, BlackBerryPIM.READ_WRITE);
                }catch (javax.microedition.pim.PIMException ex) {
                    Dialog.alert(ex.getMessage());
                return;
                 }
                 BlackBerryContact contact = (BlackBerryContact) contacts.createContact();
                 if (contacts.isSupportedField(BlackBerryContact.TEL))
                        contact.addString(BlackBerryContact.TEL, BlackBerryContact.ATTR_MOBILE, globel.phoneno);
                    Invoke.invokeApplication(Invoke.APP_TYPE_ADDRESSBOOK,null);
                    Clipboard cl = Clipboard.getClipboard();
                    cl.put(null);
                    cl.put(globel.phoneno);
                  globel.phoneno = null;
                  tmpscreen = null;
               }

         }
       class addlistener3 implements FieldChangeListener {

            public void fieldChanged(Field field, int context) {
                pScreen.this.close();
                globel.phoneno = null;
                tmpscreen = null;
            }

       }
         myButton.setChangeListener(new addlistener());
         myButton2.setChangeListener(new addlistener2());
         myButton3.setChangeListener(new addlistener3());
   }

   public void close() {
       UiApplication.getUiApplication().popScreen(this);
   }


   public boolean keyChar(char key, int status, int time) {
       //intercept the ESC key - exit the app on its receipt
       if (key == Characters.ESCAPE)
           close();
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




final class jpgScreen extends PopupScreen implements KeyListener{
     static Screen tmpScreen = null ;
//     private Bitmap myBitmap = Bitmap.getBitmapResource("charging.png");
     private Bitmap myBitmap = null ;
    public jpgScreen()
    {
        super(new VerticalFieldManager(),Field.USE_ALL_HEIGHT | Field.USE_ALL_WIDTH);
        PersistentObject store;
        store = PersistentStore.getPersistentObject (0x75a21d7e57dbebbL);
        synchronized (store) {
            if (store.getContents() == null) {
                 globel.theFile = null;
            }
            else {
                  globel.theFile = (String) store.getContents();
            }
        }
        if (globel.theFile != null) {
        try {
            //System.out.println("****BBextra Log****:Charging jpg="+globel.theFile);
           // FileConnection fconn = (FileConnection) Connector.open( "file:///SDCard/test.jpg");
            FileConnection fconn = (FileConnection) Connector.open("file:///"+globel.theFile);
            if (fconn.exists()) {
                //System.out.println("****BBextra Log****:Detect jpg file");
                InputStream input = fconn.openInputStream();
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                int SIZE = 100000;
                byte[] buffer = new byte[SIZE];
                while (true)
       {
         int bytesRead = input.read( buffer, 0, SIZE );
         if (bytesRead == -1) break;
         byteArrayOutputStream.write( buffer, 0, bytesRead );
       }
        byte[] data = byteArrayOutputStream.toByteArray();
       byteArrayOutputStream.flush();
       byteArrayOutputStream.close();
       EncodedImage image = EncodedImage.createEncodedImage(data,0,data.length);
       Bitmap b = image.getBitmap();
       BitmapField picture = new BitmapField(b);
       add(picture);
                //System.out.println("****BBextra Log****:Data length" + data.length);
            }
//            else
                //System.out.println("****BBextra Log****:Can not find jpg file");
        } catch (IOException ex) {
            //System.out.println("****BBextra Log****:Jpg function error");
            UiApplication.getUiApplication().popScreen(this);
            ex.printStackTrace();
        }
        }
        else {
            //System.out.println("****BBextra Log****:Use Blank");
            BitmapField bitmapField = new BitmapField(myBitmap,BitmapField.FIELD_HCENTER);
            add(bitmapField);
        }
        tmpScreen = jpgScreen.this;
    }

    protected void applyTheme(){

    }
 public void sublayout(int w, int h)
    {
        setExtent(Display.getWidth(), Display.getHeight());
        setPosition(0, 0);
        layoutDelegate(Display.getWidth(), Display.getHeight());

    }

    public boolean keyChar(char key, int status, int time) {
         if (key == Characters.ESCAPE){
             BBextra.incharing = false;
             UiApplication.getUiApplication().popScreen(this);
         }
        return false;
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



class snoozscreen extends PopupScreen implements KeyListener{
    static private Player pp;
    static boolean eeror;
    public snoozscreen(final int flag,Player p,boolean error){
        super(new VerticalFieldManager(),Field.FOCUSABLE);
        final int minutes;
        ButtonField b2;
        pp = p;
        eeror = error;
        PersistentObject store= PersistentStore.getPersistentObject (0xb7bf8946dda72749L);
        String s1= "Alarm";
        //#ifdef SC
//#      s1 = "闹铃";
          //#endif
        add(new LabelField(s1+flag+" :",LabelField.FIELD_HCENTER));
        String s2= "Stop alarm";
        //#ifdef SC
//#      s2 = "停止闹铃";
          //#endif
        ButtonField b1= new ButtonField(s2,ButtonField.CONSUME_CLICK|ButtonField.FIELD_HCENTER);
        add(b1);
        b1.setChangeListener(new FieldChangeListener() {
            public void fieldChanged(Field field, int context) {
                snoozscreen.this.close();
                if(flag == 1) {
                BBextra.tmphour1 =99;
                BBextra.tmpmin1 =99;
                }
                else if(flag == 2) {
                BBextra.tmphour2 =99;
                BBextra.tmpmin2 =99;
                }
                if(flag == 3) {
                BBextra.tmphour3 =99;
                BBextra.tmpmin3 =99;
                }
            }
        });
        synchronized (store) {
            if (store.getContents()!=null && !store.getContents().toString().equalsIgnoreCase("0")){
                minutes = Integer.parseInt((String)store.getContents());
         String s3= "Snooze ";
         String s4 =" mins";
        //#ifdef SC
//#          s3 = "贪睡";
//#          s4 = "分";
         //#endif
                b2 = new ButtonField(s3+(String)store.getContents()+s4,ButtonField.CONSUME_CLICK|ButtonField.FIELD_HCENTER);
                add(b2);
                b2.setChangeListener(new FieldChangeListener() {
                    public void fieldChanged(Field field, int context) {
                        snoozscreen.this.close();
                        Calendar c1 = Calendar.getInstance();
                        c1.setTime(new Date(System.currentTimeMillis()));
                        if(flag==1){
                            BBextra.tmphour1 = c1.get(Calendar.HOUR_OF_DAY);
                            BBextra.tmpmin1 = c1.get(Calendar.MINUTE)+ minutes;
                            if(BBextra.tmpmin1 > 60) {
                                BBextra.tmpmin1 = BBextra.tmpmin1-60;
                                BBextra.tmphour1 ++;
                                if(BBextra.tmphour1 > 23) BBextra.tmphour1=0;
                            }
                         }
                        else if(flag==2){
                            BBextra.tmphour2 = c1.get(Calendar.HOUR_OF_DAY);
                            BBextra.tmpmin2 = c1.get(Calendar.MINUTE)+ minutes;
                            if(BBextra.tmpmin2 > 60) {
                                BBextra.tmpmin2 = BBextra.tmpmin2-60;
                                BBextra.tmphour2 ++;
                                if(BBextra.tmphour2 > 23) BBextra.tmphour2=0;
                            }
                         }
                        else if(flag==3){
                            BBextra.tmphour3 = c1.get(Calendar.HOUR_OF_DAY);
                            BBextra.tmpmin3 = c1.get(Calendar.MINUTE)+ minutes;
                            if(BBextra.tmpmin3 > 60) {
                                BBextra.tmpmin3 = BBextra.tmpmin3-60;
                                BBextra.tmphour3 ++;
                                if(BBextra.tmphour3 > 23) BBextra.tmphour3=0;
                            }
                         }
                      }
                });
            }
            }
        }
   public void close(){
        UiApplication.getUiApplication().popScreen(this);
                                    try {
                                if (snoozscreen.eeror == false){
                                    //System.out.println("****BBextra Log****:Stop alarm music");
                                    globel.incolorled=false;
                                    LED.setState(LED.STATE_OFF);
                                 snoozscreen.pp.stop();
                                }
                            } catch (MediaException ex) {
                                snoozscreen.eeror = true;
                               //do sth when can not find music file
                            }
    }
   public boolean keyChar(char key, int status, int time) {
       PersistentObject store= PersistentStore.getPersistentObject (0xbf39f850a23b9573L);
       synchronized (store) {
       if(store.getContents()!=null && store.getContents().toString().equalsIgnoreCase("Enable")) {
            try {
                if (snoozscreen.eeror == false){
                    //System.out.println("****BBextra Log****:Stop alarm music");
                    globel.incolorled=false;
                    LED.setState(LED.STATE_OFF);
                    snoozscreen.pp.stop();
                    }
                 } catch (MediaException ex) {
                     snoozscreen.eeror = true;
                     //do sth when can not find music file
                 }
       }
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

class mytext implements TextMessage{
private String phone;
private String text;

    public String getPayloadText() {
        return text;
    }
    public void setPayloadText(String data) {
       text = data;
    }
    public String getAddress() {
        return phone;
    }
    public Date getTimestamp() {
        return null;
    }
    public void setAddress(String addr) {
        phone = addr;
    }
}

class TimerLED extends TimerTask {
    public TimerLED() {
    }


    public void run() {
        //System.out.println("****BBextra Log****:globel.incolorled of timerled="+globel.incolorled);
        if(globel.incolorled) {
            if(LED.isPolychromatic()){
                String tmp2 = "";
                int tmpa2 = 0;
                                Random rndColor2 = new Random();
                                tmpa2 = rndColor2.nextInt(9);
                                switch (tmpa2) {
                                    case 1: tmp2 = "FF0000";break;
                                    case 2: tmp2 = "FF6100";break;
                                    case 3: tmp2 = "FFFF00";break;
                                    case 4: tmp2 = "00FF00";break;
                                    case 5: tmp2 = "FFFF00";break;
                                    case 6: tmp2 = "0000FF";break;
                                    case 7: tmp2 = "A020F0";break;
                                    case 8: tmp2 = "FFFFFF";break;
                                    default: tmp2 = "FFFFFF";break;
                                }
                                //System.out.println("****BBextra Log****:set led to colorful");
                                   LED.setColorConfiguration(150, 150, Integer.parseInt(tmp2,16));
            }
        }
        else{
            this.cancel();
        }
    }
}
class Vibration extends TimerTask {
    public Vibration() {
    }


    public void run() {
        if(globel.canvibrate)
        {
                Alert.startVibrate(2000);
         }
        else
            this.cancel();
    }
}
