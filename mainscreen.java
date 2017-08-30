    /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Random;
import java.util.Vector;
import javax.microedition.io.Connector;
import javax.microedition.io.file.FileConnection;
import net.rim.blackberry.api.invoke.Invoke;
import net.rim.blackberry.api.invoke.MessageArguments;
import net.rim.blackberry.api.mail.Message;
import net.rim.blackberry.api.mail.MessagingException;
import net.rim.device.api.io.IOUtilities;
import net.rim.device.api.system.Characters;
import net.rim.device.api.system.DeviceInfo;
import net.rim.device.api.system.Display;
import net.rim.device.api.system.PersistentObject;
import net.rim.device.api.system.PersistentStore;
import net.rim.device.api.ui.Color;
import net.rim.device.api.ui.Font;
import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.MenuItem;
import net.rim.device.api.ui.Ui;
import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.component.Dialog;
import net.rim.device.api.ui.component.LabelField;
import net.rim.device.api.ui.component.ListField;
import net.rim.device.api.ui.component.ListFieldCallback;
import net.rim.device.api.ui.component.Menu;
import net.rim.device.api.ui.component.RichTextField;
import net.rim.device.api.ui.container.MainScreen;
import net.rim.device.api.ui.container.VerticalFieldManager;

/**
 *
 * @author Tiandi Zhang <tiandi.zhang@richina.com>
 */
 class mainscreen extends MainScreen {

   public MyListField myList = new MyListField();
   static int MAX_STORE = 102;
   static String pincode22 = (String.valueOf(DeviceInfo.getDeviceId()/17+1919248)).substring(2, 4);
   static String pincode11 = (String.valueOf(DeviceInfo.getDeviceId()/17)+4456311).substring(0, 2);
   static String pincode77 = String.valueOf(DeviceInfo.getDeviceId()/7+23055043).substring(4,6);
   static String pincode12 =  (String.valueOf(DeviceInfo.getDeviceId()/17)+4456311).substring(2, 4);
   static String pincode32 = (String.valueOf(DeviceInfo.getDeviceId()/17+8317909)).substring(2, 4);
   static String pincode33 = (String.valueOf(DeviceInfo.getDeviceId()/17+8317909)).substring(4, 6);
   static String pincode13 =  (String.valueOf(DeviceInfo.getDeviceId()/17)+4456311).substring(4, 6);
   static String pincode14 =  (String.valueOf(DeviceInfo.getDeviceId()/17)+4456311).substring(6,7);
   static String pincode24 = (String.valueOf(DeviceInfo.getDeviceId()/17+1919248)).substring(6, 7);
   static String pincode21 = (String.valueOf(DeviceInfo.getDeviceId()/17+1919248)).substring(0, 2);
   static String pincode23 = (String.valueOf(DeviceInfo.getDeviceId()/17+1919248)).substring(4, 6);
   static String pincode31 = (String.valueOf(DeviceInfo.getDeviceId()/17+8317909)).substring(0, 2);
   static String pincode55 = String.valueOf(DeviceInfo.getDeviceId()/7+23055043).substring(0,2);
   static String pincode34 = (String.valueOf(DeviceInfo.getDeviceId()/17+8317909)).substring(6, 7);
   static String pincode66 = String.valueOf(DeviceInfo.getDeviceId()/7+23055043).substring(2,4);
   static String pincode88 = String.valueOf(DeviceInfo.getDeviceId()/7+23055043).substring(6,8);
    String s1 = "Call Extra(p)";
    String s2 = "Led Extra(l)";
    String s3 = "Charging Extra(c)";
    String s4 = "Wireless Extra(w)";
    String s5 = "Clock Extra(t)";
    String s6 = "Preview Extra(a)";
    String s7 = "Firewall Extra(f)";
    String s8 = "Other Extra(o)";
    String s9 = "BB Extra is expired.You need buy a key from feelberry.taobao.com.";
    String s12 = "Recommand BBextra for you!";
    String s13 = "BBextra is a good conversation utilities for blackberry.\n" +
                 "Here I recommand you to have a look at http://www.feelberry.com/2010/05/28/bbextra/\n";
    String s16 = "File export to SDcard/BBextra/bbextra.ini.";
    String s18 = "INI file format error! line =";
    String s19 = "File is Imported! Please restart your device.";

    // need to set up manually.
    String s10 = "关于";
    String s11 = "分享软件";
    String s14 = "帮助";
    String s15 = "导出配置";
    String s17 = "*导入配置";

    public mainscreen(){
        super();
        //#ifdef SC
//#      s1 = "通话增强(p)";
//#      s2 = "彩灯增强(l)";
//#      s3 = "充电增强(c)";
//#      s4 = "无线增强(w)";
//#      s5 = "时钟增强(t)";
//#      s6 = "预览增强(a)";
//#      s7 = "防火墙增强(f)";
//#      s8 = "其他增强(o)";
//#      s9 = "BBextra试用已经过期，您可以到感触黑莓淘宝店feelberry.taobao.com购买注册码。";
//#      s10 = "关于";
//#      s11 = "分享软件";
//#      s12 = "我向你推荐一款叫BBextra的黑莓原创软件!";
//#      s13 = "BBextra是一款集多种系统辅助功能与一身的强大软件.\n" +
//#                   "你可以访问感触黑莓的网站了解更多此软件http://www.feelberry.com/2010/05/28/bbextra/\n";
//#      s14 = "帮助";
//#      s15 = "导出";
//#      s16 = "配置文件导出到SDcard/BBextra/bbextra.ini.";
//#      s17 = "*导入";
//#      s18 = "配置文件格式错误，行数=";
//#      s19 = "配置文件导入成功，请重启设备.";
       //#endif
        LabelField title = new LabelField("BBextra",LabelField.ELLIPSIS|LabelField.USE_ALL_WIDTH);
           setTitle(title);
           PersistentObject storesn;
        storesn = PersistentStore.getPersistentObject (0xa2f31503d67bce1dL);
               synchronized (storesn) {
                   if(storesn.getContents() == null) {
                    storesn.setContents(String.valueOf(10080));
                    storesn.commit();
                    globel.canrun = true;
                   }
                   else if(Integer.parseInt((String) storesn.getContents())<0)
                       globel.canrun = false;
                   else
                       globel.canrun = true;
               }
//           System.out.println("****BBextra Log****:Detect display menu="+globel.canrun);
           if(globel.canrun == true) {

            ListCallback myCallback = new ListCallback();
            myList.setCallback(myCallback);
            String fieldOne = s1;
            String fieldTwo = s2;
            String fieldThree = s3;
            String fieldFour = s4;
            String fieldFive = s5;
            String fieldSix = s6;
            String fieldSeven = s7;
            String fieldEight = s8;
            myList.insert(0);
            myCallback.insert(fieldOne, 0);
            myList.insert(1);
            myCallback.insert(fieldTwo, 1);
            myList.insert(2);
            myCallback.insert(fieldThree, 2);
            myList.insert(3);
            myCallback.insert(fieldFour, 3);
            myList.insert(4);
            myCallback.insert(fieldFive, 4);
            myList.insert(5);
            myCallback.insert(fieldSix, 5);
            myList.insert(6);
            myCallback.insert(fieldSeven, 6);
            myList.insert(7);
            myCallback.insert(fieldEight, 7);
            add(myList);
           }
            else
               add(new LabelField(s9));
    }
       public boolean keyChar(char key, int status, int time) {

            if (key == Characters.LATIN_CAPITAL_LETTER_P || key == Characters.LATIN_SMALL_LETTER_P)
                myList.setSelectedIndex(0);
            if (key == Characters.LATIN_CAPITAL_LETTER_C || key == Characters.LATIN_SMALL_LETTER_C)
                myList.setSelectedIndex(2);
            if (key == Characters.LATIN_CAPITAL_LETTER_L || key == Characters.LATIN_SMALL_LETTER_L)
                myList.setSelectedIndex(1);
            if (key == Characters.LATIN_CAPITAL_LETTER_W || key == Characters.LATIN_SMALL_LETTER_W)
                myList.setSelectedIndex(3);
            if (key == Characters.LATIN_CAPITAL_LETTER_T || key == Characters.LATIN_SMALL_LETTER_T)
                myList.setSelectedIndex(4);
            if (key == Characters.LATIN_CAPITAL_LETTER_O || key == Characters.LATIN_SMALL_LETTER_O)
                myList.setSelectedIndex(7);
            if (key == Characters.LATIN_CAPITAL_LETTER_F || key == Characters.LATIN_SMALL_LETTER_F)
                myList.setSelectedIndex(6);
            if (key == Characters.LATIN_CAPITAL_LETTER_A || key == Characters.LATIN_SMALL_LETTER_A)
                myList.setSelectedIndex(5);
            if (key == Characters.ENTER) {
                if (myList.getSelectedIndex() == 0)
                        UiApplication.getUiApplication().pushScreen(new ascreen());
                else if (myList.getSelectedIndex() == 1)
                        UiApplication.getUiApplication().pushScreen(new ascreen2());
                else if (myList.getSelectedIndex() == 2)
                        UiApplication.getUiApplication().pushScreen(new ascreen3());
                else if (myList.getSelectedIndex() == 3)
                        UiApplication.getUiApplication().pushScreen(new ascreen4());
                else if (myList.getSelectedIndex() == 4)
                        UiApplication.getUiApplication().pushScreen(new ascreen5());
                else if (myList.getSelectedIndex() == 5)
                        UiApplication.getUiApplication().pushScreen(new ascreen8());
                else if (myList.getSelectedIndex() == 6)
                        UiApplication.getUiApplication().pushScreen(new ascreen7());
                else if (myList.getSelectedIndex() == 7)
                        UiApplication.getUiApplication().pushScreen(new ascreen6());
                return true;
            }
            return false;
        }
        public boolean keyDown(int keycode, int time) {
            return false;
        }

        public boolean keyUp(int keycode, int time) {
            return false;
        }

        public boolean keyRepeat(int keycode, int time) {
            return false;
        }

        public boolean keyStatus(int keycode, int time) {
            return false;
        }

     private static class ListCallback implements ListFieldCallback {
            private Vector listElements = new Vector();
            public void drawListRow(ListField list, Graphics g, int index, int y, int w) {
               String text = (String)listElements.elementAt(index);
               g.drawText(text, 0, y, 0, w);
            }
            public Object get(ListField list, int index) {
                return listElements.elementAt(index);
            }
            public int indexOfList(ListField list, String p, int s) {
                return listElements.indexOf(p, s);
            }
            public int getPreferredWidth(ListField list) {
                return Display.getWidth();
            }
            public void insert(String toInsert, int index) {
                listElements.addElement(toInsert);
            }
            public void rase() {
                listElements.removeAllElements();
            }

        }
     private MenuItem viewItem = new MenuItem(s10, 100, 10) {
            public void run() {
        UiApplication.getUiApplication().pushScreen(new AboutScreen());
          }
        };
     private MenuItem viewItem3 = new MenuItem(s11, 99, 10) {
            public void run() {
         Message m = new Message();
         m.setSubject(s12);
         String msg = s13;
            try {
                m.setContent(msg);
            } catch (MessagingException ex) {
                ex.printStackTrace();
            }
                 MessageArguments m1 = new MessageArguments(m);
                 Invoke.invokeApplication(Invoke.APP_TYPE_MESSAGES,m1);
          }
        };
     private MenuItem viewItem2 = new MenuItem(s14, 98, 10) {
            public void run() {
               UiApplication.getUiApplication().pushScreen(new helpScreen());
          }
        };
     private MenuItem viewItem4 = new MenuItem(s15, 96, 10) {
     public void run() {
         try {
            FileConnection fc = (FileConnection) Connector.open("file:///SDCard/BBextra/", Connector.READ_WRITE);
            if (!fc.exists()) {
                fc.mkdir();
            }
         } catch (Exception e) {
                Dialog.alert(e.toString());
         }
            try {
                FileConnection fc = (FileConnection) (FileConnection)Connector.open("file:///SDCard/BBextra/bbextra.ini");
                if (fc.exists())
                    fc.delete();
                fc.create();

                OutputStream os = fc.openOutputStream();
                os.write(getdate().getBytes());
                os.close();
                fc.close();
                Dialog.alert(s16);
            } catch (IOException ex) {
                Dialog.alert(ex.getMessage());
            }
          }
        };
         private MenuItem viewItem5 = new MenuItem(s17, 97, 10) {
            public void run() {
                String text;
            try {
                FileConnection fc = (FileConnection) (FileConnection)Connector.open("file:///SDCard/BBextra/bbextra.ini");
                if (!fc.exists()) {
                    Dialog.alert("SDcard/bbextra.ini can not be found!");
                } else {
                    InputStream is = fc.openInputStream();
                    byte[] data = IOUtilities.streamToBytes(is);
                    text = new String(data);
                    if (readcount(text) != MAX_STORE) {
                        Dialog.alert(s18+readcount(text));
                        is.close();
                        fc.close();
                    }
                    else
                    {
                    doimport(text);
                    is.close();
                    fc.close();
                    Dialog.alert(s19);
                    }
            }
            } catch (IOException ex) {
                Dialog.alert(ex.getMessage());
            }
            }
        };
private void doimport(String text) {
    System.out.println("****BBextra Log****:doimport");
     int i = 0,j = 0;
     String[] a =new String[MAX_STORE] ;
     String tmp,tmp2,tmp3;
     tmp= text;
     tmp2=text;
     i = text.indexOf("]");
     while (i!= -1) {
//         System.out.println("****BBextra Log****:i="+i);
         tmp = tmp2.substring(0,i);
//         System.out.println("****BBextra Log****:tmp="+tmp);
         tmp2 = tmp2.substring(i+1);
//         System.out.println("****BBextra Log****:tmp2="+tmp2);
         tmp3 = tmp.substring(tmp.indexOf("[")+1);
//         System.out.println("****BBextra Log****:tmp3="+tmp3);
         a[j] = tmp3;
//         System.out.println("****BBextra Log****:a="+a[j]);
         i = tmp2.indexOf("]");
         j++;
     }

     PersistentObject store;
     ////////////////////
     //  call extra
     //  ////////////////
     store = PersistentStore.getPersistentObject (0x76e829a94adadcc6L);
     i = 0;
     if(!a[i].equalsIgnoreCase("null")){
     store.setContents(a[i]);
     store.commit();
     }
     store = PersistentStore.getPersistentObject (0x4f8080c03ba1cc10L);
     i ++;
     if(!a[i].equalsIgnoreCase("null")){
     store.setContents(a[i]);
     store.commit();
     }
     store = PersistentStore.getPersistentObject (0x2d8da4ace5b17e2fL);
     i ++;
     if(!a[i].equalsIgnoreCase("null")){
     store.setContents(a[i]);
     store.commit();
     }
     store = PersistentStore.getPersistentObject (0x9e5719378f00c08L);
     i ++;
     if(!a[i].equalsIgnoreCase("null")){
     store.setContents(a[i]);
     store.commit();
     }
     store = PersistentStore.getPersistentObject (0x348d7d96ab789c93L);
     i ++;
     if(!a[i].equalsIgnoreCase("null")){
     store.setContents(a[i]);
     store.commit();
     }
     store = PersistentStore.getPersistentObject (0x2e7547ff0ac3340aL);
     i ++;
     if(!a[i].equalsIgnoreCase("null")){
     store.setContents(a[i]);
     store.commit();
     }
     store = PersistentStore.getPersistentObject (0xd13f2a862fcbd1c7L);
     i ++;
     if(!a[i].equalsIgnoreCase("null")){
     store.setContents(a[i]);
     store.commit();
     }
     store = PersistentStore.getPersistentObject (0x7f948c198f1bfafaL);
     i ++;
     if(!a[i].equalsIgnoreCase("null")){
     store.setContents(a[i]);
     store.commit();
     }
     store = PersistentStore.getPersistentObject (0x520e17018653f10eL);
     i ++;
     if(!a[i].equalsIgnoreCase("null")){
     store.setContents(a[i]);
     store.commit();
     }
     store = PersistentStore.getPersistentObject (0xf61384d91cd700acL); //10
     i ++;
     if(!a[i].equalsIgnoreCase("null")){
     store.setContents(a[i]);
     store.commit();
     }
     ////////////////
     //  led extra  //
     ////////////////
     store = PersistentStore.getPersistentObject (0x1c8c5fd3c1daa52aL);
     i ++;
     if(!a[i].equalsIgnoreCase("null")){
     store.setContents(a[i]);
     store.commit();
     }
     store = PersistentStore.getPersistentObject (0xff823c5ac48a9154L);
     i ++;
     if(!a[i].equalsIgnoreCase("null")){
     store.setContents(a[i]);
     store.commit();
     }
     store = PersistentStore.getPersistentObject (0x8a839593ae4ec8ecL);
     i ++;
     if(!a[i].equalsIgnoreCase("null")){
     store.setContents(a[i]);
     store.commit();
     }
     store = PersistentStore.getPersistentObject (0x7433d0f77a5cf730L);
     i ++;
     if(!a[i].equalsIgnoreCase("null")){
     store.setContents(a[i]);
     store.commit();
     }
     store = PersistentStore.getPersistentObject (0xc5da3270842802c1L);
     i ++;
     if(!a[i].equalsIgnoreCase("null")){
     store.setContents(a[i]);
     store.commit();
     }
     store = PersistentStore.getPersistentObject (0xd1513f6e5fb17e24L); //16
     i ++;
     if(!a[i].equalsIgnoreCase("null")){
     store.setContents(a[i]);
     store.commit();
     }
     store = PersistentStore.getPersistentObject (0x98b2e4273ded5d69L);
     i ++;
     if(!a[i].equalsIgnoreCase("null")){
     store.setContents(a[i]);
     store.commit();
     }
     store = PersistentStore.getPersistentObject (0x7b866b9679171a30L);
     i ++;
     if(!a[i].equalsIgnoreCase("null")){
     store.setContents(a[i]);
     store.commit();
     }
     store = PersistentStore.getPersistentObject (0x3de87c7fdc41155L);
     i ++;
     if(!a[i].equalsIgnoreCase("null")){
     store.setContents(a[i]);
     store.commit();
     }
     store = PersistentStore.getPersistentObject (0xd6aa329edde0ac8aL);
     i ++;
     if(!a[i].equalsIgnoreCase("null")){
     store.setContents(a[i]);
     store.commit();
     }
     store = PersistentStore.getPersistentObject (0x6f7258f00ca7b656L);
     i ++;
     if(!a[i].equalsIgnoreCase("null")){
     store.setContents(a[i]);
     store.commit();
     }
     store = PersistentStore.getPersistentObject (0xcd12018b3dcee492L); //22
     i ++;
     if(!a[i].equalsIgnoreCase("null")){
     store.setContents(a[i]);
     store.commit();
     }
     /////////////////
     //   charging extra //
     /////////////////
     store = PersistentStore.getPersistentObject (0x42f7439cfea21673L);
     i ++;
     if(!a[i].equalsIgnoreCase("null")){
     store.setContents(a[i]);
     store.commit();
     }
     store = PersistentStore.getPersistentObject (0x94d16aab37867123L);
     i ++;
     if(!a[i].equalsIgnoreCase("null")){
     store.setContents(a[i]);
     store.commit();
     }
     store = PersistentStore.getPersistentObject (0x75a21d7e57dbebbL);
     i ++;
     if(!a[i].equalsIgnoreCase("null")){
     store.setContents(a[i]);
     store.commit();
     }
     store = PersistentStore.getPersistentObject (0xbbf87d7fc545afe9L);
     i ++;
     if(!a[i].equalsIgnoreCase("null")){
     store.setContents(a[i]);
     store.commit();
     }
     store = PersistentStore.getPersistentObject (0x9baf7e102a05663fL);
     i ++;
     if(!a[i].equalsIgnoreCase("null")){
     store.setContents(a[i]);
     store.commit();
     }
     store = PersistentStore.getPersistentObject (0xa991639c1eea79aeL); //28
     i ++;
     if(!a[i].equalsIgnoreCase("null")){
     store.setContents(a[i]);
     store.commit();
     }
      /////////////////
     //   wireless extra //
     /////////////////
     store = PersistentStore.getPersistentObject (0x8d115948c9c2c87aL);
     i ++;
     if(!a[i].equalsIgnoreCase("null")){
     store.setContents(a[i]);
     store.commit();
     }
     store = PersistentStore.getPersistentObject (0x608a39b43884080eL);
     i ++;
     if(!a[i].equalsIgnoreCase("null")){
     store.setContents(a[i]);
     store.commit();
     }
     store = PersistentStore.getPersistentObject (0xf1d06a4809a33cdcL);
     i ++;
     if(!a[i].equalsIgnoreCase("null")){
     store.setContents(a[i]);
     store.commit();
     }
     store = PersistentStore.getPersistentObject (0xe62b62a5dd6bbea6L);
     i ++;
     if(!a[i].equalsIgnoreCase("null")){
     store.setContents(a[i]);
     store.commit();
     }
     store = PersistentStore.getPersistentObject (0xfdb812bfc68f72e3L);
     i ++;
     if(!a[i].equalsIgnoreCase("null")){
     store.setContents(a[i]);
     store.commit();
     }
     store = PersistentStore.getPersistentObject (0xed24038a3dfee82bL); //34
     i ++;
     if(!a[i].equalsIgnoreCase("null")){
     store.setContents(a[i]);
     store.commit();
     }
      /////////////////
     //  clock extra //
     /////////////////
     text +="\n\n****Clock Extra:\n";
     store = PersistentStore.getPersistentObject (0xa7cb7eb2e499c592L);
     i ++;
     if(!a[i].equalsIgnoreCase("null")){
     store.setContents(a[i]);
     store.commit();
     }
     store = PersistentStore.getPersistentObject (0xc9217e122938cfaeL);
     i ++;
     if(!a[i].equalsIgnoreCase("null")){
     store.setContents(a[i]);
     store.commit();
     }
     store = PersistentStore.getPersistentObject (0x4edda4e19d069233L);
     i ++;
     if(!a[i].equalsIgnoreCase("null")){
     store.setContents(a[i]);
     store.commit();
     }
     store = PersistentStore.getPersistentObject (0xbf39f850a23b9573L);
     i ++;
     if(!a[i].equalsIgnoreCase("null")){
     store.setContents(a[i]);
     store.commit();
     }
     store = PersistentStore.getPersistentObject (0x743427ce25a6b74cL);
     i ++;
     if(!a[i].equalsIgnoreCase("null")){
     store.setContents(a[i]);
     store.commit();
     }
     store = PersistentStore.getPersistentObject (0xf79c2d6bd19e97d5L); //40
     i ++;
     if(!a[i].equalsIgnoreCase("null")){
     store.setContents(a[i]);
     store.commit();
     }
     store = PersistentStore.getPersistentObject (0x7847782406658f55L);
     i ++;
     if(!a[i].equalsIgnoreCase("null")){
     store.setContents(a[i]);
     store.commit();
     }
     store = PersistentStore.getPersistentObject (0x20c5f6d1336be7b6L);
     i ++;
     if(!a[i].equalsIgnoreCase("null")){
     store.setContents(a[i]);
     store.commit();
     }
     store = PersistentStore.getPersistentObject (0xa85576eaeaf1ef20L);
     i ++;
     if(!a[i].equalsIgnoreCase("null")){
     store.setContents(a[i]);
     store.commit();
     }
     store = PersistentStore.getPersistentObject (0x8d49121cb7684695L);
     i ++;
     if(!a[i].equalsIgnoreCase("null")){
     store.setContents(a[i]);
     store.commit();
     }
     store = PersistentStore.getPersistentObject (0xc39425bf786480dfL);
     i ++;
     if(!a[i].equalsIgnoreCase("null")){
     store.setContents(a[i]);
     store.commit();
     }
     store = PersistentStore.getPersistentObject (0x26dd5c21153ea8b7L); //46
     i ++;
     if(!a[i].equalsIgnoreCase("null")){
     store.setContents(a[i]);
     store.commit();
     }
     store = PersistentStore.getPersistentObject (0xb4e00b28758a7525L);
     i ++;
     if(!a[i].equalsIgnoreCase("null")){
     store.setContents(a[i]);
     store.commit();
     }
     store = PersistentStore.getPersistentObject (0x882a5b70fe4f00L);
     i ++;
     if(!a[i].equalsIgnoreCase("null")){
     store.setContents(a[i]);
     store.commit();
     }
     store = PersistentStore.getPersistentObject (0xd60bae6df03f5a34L);
     i ++;
     if(!a[i].equalsIgnoreCase("null")){
     store.setContents(a[i]);
     store.commit();
     }
     store = PersistentStore.getPersistentObject (0x9e9a51a9c53906f7L);
     i ++;
     if(!a[i].equalsIgnoreCase("null")){
     store.setContents(a[i]);
     store.commit();
     }
     store = PersistentStore.getPersistentObject (0x182b063c5c35aa1fL);
     i ++;
     if(!a[i].equalsIgnoreCase("null")){
     store.setContents(a[i]);
     store.commit();
     }
     store = PersistentStore.getPersistentObject (0xa1c8b5fbbca891f5L);
     i ++;
     if(!a[i].equalsIgnoreCase("null")){
     store.setContents(a[i]);
     store.commit();
     }
     store = PersistentStore.getPersistentObject (0x641488d388cfa1ecL);
     i ++;
     if(!a[i].equalsIgnoreCase("null")){
     store.setContents(a[i]);
     store.commit();
     }
     store = PersistentStore.getPersistentObject (0xaec09de4d1ec3f4eL);
     i ++;
     if(!a[i].equalsIgnoreCase("null")){
     store.setContents(a[i]);
     store.commit();
     }
     store = PersistentStore.getPersistentObject (0x23cc435034469c9fL);
     i ++;
     if(!a[i].equalsIgnoreCase("null")){
     store.setContents(a[i]);
     store.commit();
     }
     store = PersistentStore.getPersistentObject (0x2692d94e6114eac5L);//56
     i ++;
     if(!a[i].equalsIgnoreCase("null")){
     store.setContents(a[i]);
     store.commit();
     }
     store = PersistentStore.getPersistentObject (0xef2a91f63529defbL);
     i ++;
     if(!a[i].equalsIgnoreCase("null")){
     store.setContents(a[i]);
     store.commit();
     }
     store = PersistentStore.getPersistentObject (0xefb2f40e7ca8157fL);
     i ++;
     if(!a[i].equalsIgnoreCase("null")){
     store.setContents(a[i]);
     store.commit();
     }
     store = PersistentStore.getPersistentObject (0x4e30030f1ea96be1L);
     i ++;
     if(!a[i].equalsIgnoreCase("null")){
     store.setContents(a[i]);
     store.commit();
     }
     store = PersistentStore.getPersistentObject (0xff5d29eae5143093L);
     i ++;
     if(!a[i].equalsIgnoreCase("null")){
     store.setContents(a[i]);
     store.commit();
     }
     store = PersistentStore.getPersistentObject (0xce1472c295472e87L);
     i ++;
     if(!a[i].equalsIgnoreCase("null")){
     store.setContents(a[i]);
     store.commit();
     }
     store = PersistentStore.getPersistentObject (0xe952454c385f2d60L);
     i ++;
     if(!a[i].equalsIgnoreCase("null")){
     store.setContents(a[i]);
     store.commit();
     }
     store = PersistentStore.getPersistentObject (0x73fe9c32146ab6e4L);
     i ++;
     if(!a[i].equalsIgnoreCase("null")){
     store.setContents(a[i]);
     store.commit();
     }
     store = PersistentStore.getPersistentObject (0xf281b213404da0daL);
     i ++;
     if(!a[i].equalsIgnoreCase("null")){
     store.setContents(a[i]);
     store.commit();
     }
     store = PersistentStore.getPersistentObject (0x8e8bd8b20e6da5eL);
     i ++;
     if(!a[i].equalsIgnoreCase("null")){
     store.setContents(a[i]);
     store.commit();
     }
     store = PersistentStore.getPersistentObject (0x1c8f91eb76a13d0bL);//66
     i ++;
     if(!a[i].equalsIgnoreCase("null")){
     store.setContents(a[i]);
     store.commit();
     }
     store = PersistentStore.getPersistentObject (0x4592bdf7f3feaaa0L);
     i ++;
     if(!a[i].equalsIgnoreCase("null")){
     store.setContents(a[i]);
     store.commit();
     }
     store = PersistentStore.getPersistentObject (0x5559ad9e5ba41f57L);
     i ++;
     if(!a[i].equalsIgnoreCase("null")){
     store.setContents(a[i]);
     store.commit();
     }
     store = PersistentStore.getPersistentObject (0x836c7b22b1180985L);
     i ++;
     if(!a[i].equalsIgnoreCase("null")){
     store.setContents(a[i]);
     store.commit();
     }
     store = PersistentStore.getPersistentObject (0x6b9b78bc6bf657daL);
     i ++;
     if(!a[i].equalsIgnoreCase("null")){
     store.setContents(a[i]);
     store.commit();
     }
     store = PersistentStore.getPersistentObject (0xf4b763d73d7464f6L);
     i ++;
     if(!a[i].equalsIgnoreCase("null")){
     store.setContents(a[i]);
     store.commit();
     }
     store = PersistentStore.getPersistentObject (0xe63a9bdc93bfe01aL);
     i ++;
     if(!a[i].equalsIgnoreCase("null")){
     store.setContents(a[i]);
     store.commit();
     }
     store = PersistentStore.getPersistentObject (0x7897a365635c5a6aL);
     i ++;
     if(!a[i].equalsIgnoreCase("null")){
     store.setContents(a[i]);
     store.commit();
     }
     store = PersistentStore.getPersistentObject (0x1e712e2d2fbc8a56L);
     i ++;
     if(!a[i].equalsIgnoreCase("null")){
     store.setContents(a[i]);
     store.commit();
     }
     store = PersistentStore.getPersistentObject (0xb7bf8946dda72749L); //75
     i ++;
     if(!a[i].equalsIgnoreCase("null")){
     store.setContents(a[i]);
     store.commit();
     }
     /////////////////
     // firewall extra //
     /////////////////

     store = PersistentStore.getPersistentObject (0x1d0e86de3c5a9540L);
     i ++;
     if(!a[i].equalsIgnoreCase("null")){
     store.setContents(a[i]);
     store.commit();
     }
     store = PersistentStore.getPersistentObject (0x860e202733f13780L);
     i ++;
     if(!a[i].equalsIgnoreCase("null")){
     store.setContents(a[i]);
     store.commit();
     }
     store = PersistentStore.getPersistentObject (0xbe60c7c34967bf0aL);
     i ++;
     if(!a[i].equalsIgnoreCase("null")){
     store.setContents(a[i]);
     store.commit();
     }
     store = PersistentStore.getPersistentObject (0x55a9ef284cd02d7bL);
     i ++;
     if(!a[i].equalsIgnoreCase("null")){
     store.setContents(a[i]);
     store.commit();
     }
     store = PersistentStore.getPersistentObject (0x4dd59dd49bb680c7L);//80
     i ++;
     if(!a[i].equalsIgnoreCase("null")){
     store.setContents(a[i]);
     store.commit();
     }
     /////////////////
     // preview extra //
     /////////////////

     store = PersistentStore.getPersistentObject (0x3945fecf2601a023L);
     i ++;
     if(!a[i].equalsIgnoreCase("null")){
     store.setContents(a[i]);
     store.commit();
     }
     store = PersistentStore.getPersistentObject (0xa6970fbc46bf0f24L);
     i ++;
     if(!a[i].equalsIgnoreCase("null")){
     store.setContents(a[i]);
     store.commit();
     }
     store = PersistentStore.getPersistentObject (0xf4224d9ff8b6f205L);
     i ++;
     if(!a[i].equalsIgnoreCase("null")){
     store.setContents(a[i]);
     store.commit();
     }
     store = PersistentStore.getPersistentObject (0x6e352242e2bbdb68L);
     i ++;
     if(!a[i].equalsIgnoreCase("null")){
     store.setContents(a[i]);
     store.commit();
     }
     store = PersistentStore.getPersistentObject (0x5d91f892c5fdb156L);//85
     i ++;
     if(!a[i].equalsIgnoreCase("null")){
     store.setContents(a[i]);
     store.commit();
     }
     /////////////////
     // other extra //
     /////////////////
     text +="\n\n****other Extra:\n";
     store = PersistentStore.getPersistentObject (0x80e0d842a0e9fe05L);
     i ++;
     if(!a[i].equalsIgnoreCase("null")){
     store.setContents(a[i]);
     store.commit();
     }
     store = PersistentStore.getPersistentObject (0xdd4cdf439a9307d9L);
     i ++;
     if(!a[i].equalsIgnoreCase("null")){
     store.setContents(a[i]);
     store.commit();
     }
     store = PersistentStore.getPersistentObject (0xc71f14aefa7f191L);//88
     i ++;
     if(!a[i].equalsIgnoreCase("null")){
     store.setContents(a[i]);
     store.commit();
     }
     store = PersistentStore.getPersistentObject (0x3359d1d21ab299e2L);
     i ++;
     if(!a[i].equalsIgnoreCase("null")){
     store.setContents(a[i]);
     store.commit();
     }
     store = PersistentStore.getPersistentObject (0x82b714e0f44c53dfL);
     i ++;
     if(!a[i].equalsIgnoreCase("null")){
     store.setContents(a[i]);
     store.commit();
     }
     store = PersistentStore.getPersistentObject (0x6b2ace76c96a002cL);//90
     i ++;
     if(!a[i].equalsIgnoreCase("null")){
     store.setContents(a[i]);
     store.commit();
     }
     store = PersistentStore.getPersistentObject (0x7219d09205062076L);
     i ++;
     if(!a[i].equalsIgnoreCase("null")){
     store.setContents(a[i]);
     store.commit();
     }
     store = PersistentStore.getPersistentObject (0x7379a1ecc0c572bbL);
     i ++;
     if(!a[i].equalsIgnoreCase("null")){
     store.setContents(a[i]);
     store.commit();
     }
     store = PersistentStore.getPersistentObject (0x7219d09205062076L);
     i ++;
     if(!a[i].equalsIgnoreCase("null")){
     store.setContents(a[i]);
     store.commit();
     }
     store = PersistentStore.getPersistentObject (0x68fcca44ebc80651L);
     i ++;
     if(!a[i].equalsIgnoreCase("null")){
     store.setContents(a[i]);
     store.commit();
     }
     store = PersistentStore.getPersistentObject (0xe37875548f344a35L); //96
     i ++;
     if(!a[i].equalsIgnoreCase("null")){
     store.setContents(a[i]);
     store.commit();
     }
     store = PersistentStore.getPersistentObject (0xa03e229fa21bebfcL);
     i ++;
     if(!a[i].equalsIgnoreCase("null")){
     store.setContents(a[i]);
     store.commit();
     }
     store = PersistentStore.getPersistentObject (0xfecef8c0f80e7823L);
     i ++;
     if(!a[i].equalsIgnoreCase("null")){
     store.setContents(a[i]);
     store.commit();
     }
     store = PersistentStore.getPersistentObject (0x685d17f90a66b371L);
     i ++;
     if(!a[i].equalsIgnoreCase("null")){
     store.setContents(a[i]);
     store.commit();
     }
     store = PersistentStore.getPersistentObject (0x760c2b022b02f81fL);
     i ++;
     if(!a[i].equalsIgnoreCase("null")){
     store.setContents(a[i]);
     store.commit();
     }
     store = PersistentStore.getPersistentObject (0xc860c22da74baa63L);
     i ++;
     if(!a[i].equalsIgnoreCase("null")){
     store.setContents(a[i]);
     store.commit();
     }
     store = PersistentStore.getPersistentObject (0x565c4a4f2d148a95L);//102
     i ++;
     if(!a[i].equalsIgnoreCase("null")){
     store.setContents(a[i]);
     store.commit();
     }
}

protected void makeMenu(Menu menu, int instance) {
            menu.add(viewItem);
            menu.add(viewItem3);
            menu.add(viewItem4);
            menu.add(viewItem5);
            menu.add(viewItem2);
}

private int readcount(String text){
     int i = 0,j = 0;
     String tmp;
     tmp= text;
     i = text.indexOf("]");
     while (i!= -1) {
         j++;
         tmp = tmp.substring(i+1);
         i = tmp.indexOf("]");
     }
     return j;
 }
private String getdate(){
     PersistentObject store;
     ////////////////////
     //  call extra
     //  ////////////////
     String des,value;
     String text = "BBextra Configuration:\nVer:"+globel.ver+"\n\n****Call Extra:\n";
     store = PersistentStore.getPersistentObject (0x76e829a94adadcc6L);
     des = "Call Connected When Outgoing:";
     value = "["+(String) store.getContents()+"]";
     text += "\n"+des + value;
     store = PersistentStore.getPersistentObject (0x4f8080c03ba1cc10L);
     des = "Disconnected By Opposite:";
     value = "["+(String) store.getContents()+"]";
     text += "\n"+des + value;
     store = PersistentStore.getPersistentObject (0x2d8da4ace5b17e2fL);
     des = "Add Contact Notify After Disconnect:";
     value = "["+(String) store.getContents()+"]";
     text += "\n"+des + value;
     store = PersistentStore.getPersistentObject (0x9e5719378f00c08L);
     des = "Alert When Connected After Seconds:";
     value = "["+(String) store.getContents()+"]";
     text += "\n"+des + value;
     store = PersistentStore.getPersistentObject (0x348d7d96ab789c93L);
     des = "Alert When Connected After Seconds(Vibrate):";
     value = "["+(String) store.getContents()+"]";
     text += "\n"+des + value;
     store = PersistentStore.getPersistentObject (0x2e7547ff0ac3340aL);
     des = "IP Number :";
     value = "["+(String) store.getContents()+"]";
     text += "\n"+des + value;
     store = PersistentStore.getPersistentObject (0xd13f2a862fcbd1c7L);
     des = "Missed Call Every 5 Mins:";
     value = "["+(String) store.getContents()+"]";
     text += "\n"+des + value;
     store = PersistentStore.getPersistentObject (0x7f948c198f1bfafaL);
     des = "Vibrate With Ring Tone:";
     value = "["+(String) store.getContents()+"]";
     text += "\n"+des + value;
     store = PersistentStore.getPersistentObject (0x520e17018653f10eL);
     des = "BackLight Off When Calling:";
     value = "["+(String) store.getContents()+"]";
     text += "\n"+des + value;
     store = PersistentStore.getPersistentObject (0xf61384d91cd700acL); //10
     des = "Missed Call Music:";
     value = "["+(String) store.getContents()+"]";
     text += "\n"+des + value;
     /////////////////
     //   LED extra //
     /////////////////
     text +="\n\n****Led Extra:\n";
     store = PersistentStore.getPersistentObject (0x1c8c5fd3c1daa52aL);
     des = "Missed Call:";
     value = "["+(String) store.getContents()+"]";
     text += "\n"+des + value;
     store = PersistentStore.getPersistentObject (0xff823c5ac48a9154L);
     des = "New Incoming SMS:";
     value = "["+(String) store.getContents()+"]";
     text += "\n"+des + value;
     store = PersistentStore.getPersistentObject (0x8a839593ae4ec8ecL);
     des = "New Incoming Email:";
     value = "["+(String) store.getContents()+"]";
     text += "\n"+des + value;
     store = PersistentStore.getPersistentObject (0x7433d0f77a5cf730L);
     des = "Blinking On:";
     value = "["+(String) store.getContents()+"]";
     text += "\n"+des + value;
     store = PersistentStore.getPersistentObject (0xc5da3270842802c1L);
     des = "Blinking Off:";
     value = "["+(String) store.getContents()+"]";
     text += "\n"+des + value;
     store = PersistentStore.getPersistentObject (0xd1513f6e5fb17e24L); //16
     des = "Disco Led When Imcoming Call:";
     value = "["+(String) store.getContents()+"]";
     text += "\n"+des + value;
     store = PersistentStore.getPersistentObject (0x98b2e4273ded5d69L);
     des = "Person From A:";
     value = "["+(String) store.getContents()+"]";
     text += "\n"+des + value;
     store = PersistentStore.getPersistentObject (0x7b866b9679171a30L);
     des = "Action A:";
     value = "["+(String) store.getContents()+"]";
     text += "\n"+des + value;
     store = PersistentStore.getPersistentObject (0x3de87c7fdc41155L);
     des = "Person From B:";
     value = "["+(String) store.getContents()+"]";
     text += "\n"+des + value;
     store = PersistentStore.getPersistentObject (0xd6aa329edde0ac8aL);
     des = "Action B:";
     value = "["+(String) store.getContents()+"]";
     text += "\n"+des + value;
     store = PersistentStore.getPersistentObject (0x6f7258f00ca7b656L);
     des = "Person From C:";
     value = "["+(String) store.getContents()+"]";
     text += "\n"+des + value;
     store = PersistentStore.getPersistentObject (0xcd12018b3dcee492L); //22
     des = "Action C:";
     value = "["+(String) store.getContents()+"]";
     text += "\n"+des + value;

      /////////////////
     //   charging extra //
     /////////////////
     text +="\n\n****Charging Extra:\n";
     store = PersistentStore.getPersistentObject (0x42f7439cfea21673L);
     des = "Display Image:";
     value = "["+(String) store.getContents()+"]";
     text += "\n"+des + value;
     store = PersistentStore.getPersistentObject (0x94d16aab37867123L);
     des = "Finish Charging Notify:";
     value = "["+(String) store.getContents()+"]";
     text += "\n"+des + value;
     store = PersistentStore.getPersistentObject (0x75a21d7e57dbebbL);
     des = "Image Path:";
     value = "["+(String) store.getContents()+"]";
     text += "\n"+des + value;
     store = PersistentStore.getPersistentObject (0xbbf87d7fc545afe9L);
     des = "Sound Path:";
     value = "["+(String) store.getContents()+"]";
     text += "\n"+des + value;
     store = PersistentStore.getPersistentObject (0x9baf7e102a05663fL);
     des = "From Time";
     value = "["+(String) store.getContents()+"]";
     text += "\n"+des + value;
     store = PersistentStore.getPersistentObject (0xa991639c1eea79aeL); //28
     des = "To Time:";
     value = "["+(String) store.getContents()+"]";
     text += "\n"+des + value;
      /////////////////
     //   wireless extra //
     /////////////////
     text +="\n\n****Wireless Extra:\n";
     store = PersistentStore.getPersistentObject (0x8d115948c9c2c87aL);
     des = "Wi-Fi Auto:";
     value = "["+(String) store.getContents()+"]";
     text += "\n"+des + value;
     store = PersistentStore.getPersistentObject (0x608a39b43884080eL);
     des = "Wi-Fi Turn On At:";
     value = "["+(String) store.getContents()+"]";
     text += "\n"+des + value;
     store = PersistentStore.getPersistentObject (0xf1d06a4809a33cdcL);
     des = "Wi-Fi Turn Off At:";
     value = "["+(String) store.getContents()+"]";
     text += "\n"+des + value;
     store = PersistentStore.getPersistentObject (0xe62b62a5dd6bbea6L);
     des = "Radio Auto:";
     value = "["+(String) store.getContents()+"]";
     text += "\n"+des + value;
     store = PersistentStore.getPersistentObject (0xfdb812bfc68f72e3L);
     des = "Radio Turn On At:";
     value = "["+(String) store.getContents()+"]";
     text += "\n"+des + value;
     store = PersistentStore.getPersistentObject (0xed24038a3dfee82bL); //34
     des = "Radio Turn Off At:";
     value = "["+(String) store.getContents()+"]";
     text += "\n"+des + value;
      /////////////////
     //  clock extra //
     /////////////////
     text +="\n\n****Clock Extra:\n";
     store = PersistentStore.getPersistentObject (0xa7cb7eb2e499c592L);
     des = "Auto Lock By Clock Updated:";
     value = "["+(String) store.getContents()+"]";
     text += "\n"+des + value;
     store = PersistentStore.getPersistentObject (0xc9217e122938cfaeL);
     des = "Only When Home Screen:";
     value = "["+(String) store.getContents()+"]";
     text += "\n"+des + value;
     store = PersistentStore.getPersistentObject (0x4edda4e19d069233L);
     des = "Disco LED When Alarm:";
     value = "["+(String) store.getContents()+"]";
     text += "\n"+des + value;
     store = PersistentStore.getPersistentObject (0xbf39f850a23b9573L);
     des = "Baby Mode:";
     value = "["+(String) store.getContents()+"]";
     text += "\n"+des + value;
     store = PersistentStore.getPersistentObject (0x743427ce25a6b74cL);
     des = "Not Lock When USB Connected:";
     value = "["+(String) store.getContents()+"]";
     text += "\n"+des + value;
     store = PersistentStore.getPersistentObject (0xf79c2d6bd19e97d5L); //40
     des = "Lock After Minutes:";
     value = "["+(String) store.getContents()+"]";
     text += "\n"+des + value;
     store = PersistentStore.getPersistentObject (0x7847782406658f55L);
     des = "Clock Alarm One:";
     value = "["+(String) store.getContents()+"]";
     text += "\n"+des + value;
     store = PersistentStore.getPersistentObject (0x20c5f6d1336be7b6L);
     des = "Clock Alarm One(Alarm Time):";
     value = "["+(String) store.getContents()+"]";
     text += "\n"+des + value;
     store = PersistentStore.getPersistentObject (0xa85576eaeaf1ef20L);
     des = "Clock Alarm One(Mon):";
     value = "["+(String) store.getContents()+"]";
     text += "\n"+des + value;
     store = PersistentStore.getPersistentObject (0x8d49121cb7684695L);
     des = "Clock Alarm One(Tus)";
     value = "["+(String) store.getContents()+"]";
     text += "\n"+des + value;
     store = PersistentStore.getPersistentObject (0xc39425bf786480dfL);
     des = "Clock Alarm One(Wed):";
     value = "["+(String) store.getContents()+"]";
     text += "\n"+des + value;
     store = PersistentStore.getPersistentObject (0x26dd5c21153ea8b7L); //46
     des = "Clock Alarm One(Thu):";
     value = "["+(String) store.getContents()+"]";
     text += "\n"+des + value;
     store = PersistentStore.getPersistentObject (0xb4e00b28758a7525L);
     des = "Clock Alarm One(Fri):";
     value = "["+(String) store.getContents()+"]";
     text += "\n"+des + value;
     store = PersistentStore.getPersistentObject (0x882a5b70fe4f00L);
     des = "Clock Alarm One(Sat):";
     value = "["+(String) store.getContents()+"]";
     text += "\n"+des + value;
     store = PersistentStore.getPersistentObject (0xd60bae6df03f5a34L);
     des = "Clock Alarm One(Sun):";
     value = "["+(String) store.getContents()+"]";
     text += "\n"+des + value;
     store = PersistentStore.getPersistentObject (0x9e9a51a9c53906f7L);
     des = "Clock Alarm Two:";
     value = "["+(String) store.getContents()+"]";
     text += "\n"+des + value;
     store = PersistentStore.getPersistentObject (0x182b063c5c35aa1fL);
     des = "Clock Alarm Two(Alarm Time):";
     value = "["+(String) store.getContents()+"]";
     text += "\n"+des + value;
     store = PersistentStore.getPersistentObject (0xa1c8b5fbbca891f5L);
     des = "Clock Alarm Two(Mon):";
     value = "["+(String) store.getContents()+"]";
     text += "\n"+des + value;
     store = PersistentStore.getPersistentObject (0x641488d388cfa1ecL);
     des = "Clock Alarm Two(Tus):";
     value = "["+(String) store.getContents()+"]";
     text += "\n"+des + value;
     store = PersistentStore.getPersistentObject (0xaec09de4d1ec3f4eL);
     des = "Clock Alarm Two(Wed):";
     value = "["+(String) store.getContents()+"]";
     text += "\n"+des + value;
     store = PersistentStore.getPersistentObject (0x23cc435034469c9fL);
     des = "Clock Alarm Two(Thu):";
     value = "["+(String) store.getContents()+"]";
     text += "\n"+des + value;
     store = PersistentStore.getPersistentObject (0x2692d94e6114eac5L);//56
     des = "Clock Alarm Two(Fri):";
     value = "["+(String) store.getContents()+"]";
     text += "\n"+des + value;
     store = PersistentStore.getPersistentObject (0xef2a91f63529defbL);
     des = "Clock Alarm Two(Sat):";
     value = "["+(String) store.getContents()+"]";
     text += "\n"+des + value;
     store = PersistentStore.getPersistentObject (0xefb2f40e7ca8157fL);
     des = "Clock Alarm Two(Sun):";
     value = "["+(String) store.getContents()+"]";
     text += "\n"+des + value;
     store = PersistentStore.getPersistentObject (0x4e30030f1ea96be1L);
     des = "Clock Alarm Three:";
     value = "["+(String) store.getContents()+"]";
     text += "\n"+des + value;
     store = PersistentStore.getPersistentObject (0xff5d29eae5143093L);
     des = "Clock Alarm Three(Alarm Time):";
     value = "["+(String) store.getContents()+"]";
     text += "\n"+des + value;
     store = PersistentStore.getPersistentObject (0xce1472c295472e87L);
     des = "Clock Alarm Two(Mon):";
     value = "["+(String) store.getContents()+"]";
     text += "\n"+des + value;
     store = PersistentStore.getPersistentObject (0xe952454c385f2d60L);
     des = "Clock Alarm Two(Tus):";
     value = "["+(String) store.getContents()+"]";
     text += "\n"+des + value;
     store = PersistentStore.getPersistentObject (0x73fe9c32146ab6e4L);
     des = "Clock Alarm Two(Wed):";
     value = "["+(String) store.getContents()+"]";
     text += "\n"+des + value;
     store = PersistentStore.getPersistentObject (0xf281b213404da0daL);
     des = "Clock Alarm Two(Tus):";
     value = "["+(String) store.getContents()+"]";
     text += "\n"+des + value;
     store = PersistentStore.getPersistentObject (0x8e8bd8b20e6da5eL);
     des = "Clock Alarm Two(Fri):";
     value = "["+(String) store.getContents()+"]";
     text += "\n"+des + value;
     store = PersistentStore.getPersistentObject (0x1c8f91eb76a13d0bL);//66
     des = "Clock Alarm Two(Sat):";
     value = "["+(String) store.getContents()+"]";
     text += "\n"+des + value;
     store = PersistentStore.getPersistentObject (0x4592bdf7f3feaaa0L);
     des = "Clock Alarm Two(Sun):";
     value = "["+(String) store.getContents()+"]";
     text += "\n"+des + value;
     store = PersistentStore.getPersistentObject (0x5559ad9e5ba41f57L);
     des = "Alarm One Music:";
     value = "["+(String) store.getContents()+"]";
     text += "\n"+des + value;
     store = PersistentStore.getPersistentObject (0x836c7b22b1180985L);
     des = "Alarm Two Music:";
     value = "["+(String) store.getContents()+"]";
     text += "\n"+des + value;
     store = PersistentStore.getPersistentObject (0x6b9b78bc6bf657daL);
     des = "Alarm Three Music:";
     value = "["+(String) store.getContents()+"]";
     text += "\n"+des + value;
     store = PersistentStore.getPersistentObject (0xf4b763d73d7464f6L);
     des = "Play Sound When Auto Lock:";
     value = "["+(String) store.getContents()+"]";
     text += "\n"+des + value;
     store = PersistentStore.getPersistentObject (0xe63a9bdc93bfe01aL);
     des = "Auto Lock Music:";
     value = "["+(String) store.getContents()+"]";
     text += "\n"+des + value;
     store = PersistentStore.getPersistentObject (0x7897a365635c5a6aL);
     des = "Hourly Alarm :";
     value = "["+(String) store.getContents()+"]";
     text += "\n"+des + value;
     store = PersistentStore.getPersistentObject (0x1e712e2d2fbc8a56L);
     des = "Always On Top:";
     value = "["+(String) store.getContents()+"]";
     text += "\n"+des + value;
     store = PersistentStore.getPersistentObject (0xb7bf8946dda72749L); //75
     des = "Snooze:";
     value = "["+(String) store.getContents()+"]";
     text += "\n"+des + value;
      /////////////////
     // firewall extra //
     /////////////////
     text +="\n\n****Firewall Extra:\n";
     store = PersistentStore.getPersistentObject (0x1d0e86de3c5a9540L);
     des = "Phone Firewall :";
     value = "["+(String) store.getContents()+"]";
     text += "\n"+des + value;
     store = PersistentStore.getPersistentObject (0x860e202733f13780L);
     des = "Do Action :";
     value = "["+(String) store.getContents()+"]";
     text += "\n"+des + value;
     store = PersistentStore.getPersistentObject (0xbe60c7c34967bf0aL);
     des = "Block When Calling:";
     value = "["+(String) store.getContents()+"]";
     text += "\n"+des + value;
     store = PersistentStore.getPersistentObject (0x55a9ef284cd02d7bL);
     des = "Black List:";
     value = "["+(String) store.getContents()+"]";
     text += "\n"+des + value;
     store = PersistentStore.getPersistentObject (0x4dd59dd49bb680c7L);//80
     des = "White List:";
     value = "["+(String) store.getContents()+"]";
     text += "\n"+des + value;
     /////////////////
     // preview extra //
     /////////////////
     text +="\n\n****Preview Extra:\n";
     store = PersistentStore.getPersistentObject (0x3945fecf2601a023L);
     des = "SMS Preview :";
     value = "["+(String) store.getContents()+"]";
     text += "\n"+des + value;
     store = PersistentStore.getPersistentObject (0xa6970fbc46bf0f24L);
     des = "Preview tips :";
     value = "["+(String) store.getContents()+"]";
     text += "\n"+des + value;
     store = PersistentStore.getPersistentObject (0xf4224d9ff8b6f205L);
     des = "Email Preview :";
     value = "["+(String) store.getContents()+"]";
     text += "\n"+des + value;
     store = PersistentStore.getPersistentObject (0x6e352242e2bbdb68L);
     des = "Preview Font Size :";
     value = "["+(String) store.getContents()+"]";
     text += "\n"+des + value;
     store = PersistentStore.getPersistentObject (0x5d91f892c5fdb156L);//85
     des = "Show Contact Photo :";
     value = "["+(String) store.getContents()+"]";
     text += "\n"+des + value;
     /////////////////
     // other extra //
     /////////////////
     text +="\n\n****other Extra:\n";
     store = PersistentStore.getPersistentObject (0x80e0d842a0e9fe05L);
     des = "SMS Signature :";
     value = "["+(String) store.getContents()+"]";
     text += "\n"+des + value;
     store = PersistentStore.getPersistentObject (0xdd4cdf439a9307d9L);
     des = "Show Battery :";
     value = "["+(String) store.getContents()+"]";
     text += "\n"+des + value;
     store = PersistentStore.getPersistentObject (0xc71f14aefa7f191L);//88
     des = "A Time :";
     value = "["+(String) store.getContents()+"]";
     text += "\n"+des + value;
     store = PersistentStore.getPersistentObject (0x3359d1d21ab299e2L);
     des = "A Switch :";
     value = "["+(String) store.getContents()+"]";
     text += "\n"+des + value;
     store = PersistentStore.getPersistentObject (0x82b714e0f44c53dfL);
     des = "B Time :";
     value = "["+(String) store.getContents()+"]";
     text += "\n"+des + value;
     store = PersistentStore.getPersistentObject (0x6b2ace76c96a002cL);
     des = "B Switch :";
     value = "["+(String) store.getContents()+"]";
     text += "\n"+des + value;
     store = PersistentStore.getPersistentObject (0x7219d09205062076L);
     des = "C Time :";
     value = "["+(String) store.getContents()+"]";
     text += "\n"+des + value;
     store = PersistentStore.getPersistentObject (0x7379a1ecc0c572bbL);
     des = "C Switch :";
     value = "["+(String) store.getContents()+"]";
     text += "\n"+des + value;
     store = PersistentStore.getPersistentObject (0x1b289b6e0a027daaL);
     des = "D Time :";
     value = "["+(String) store.getContents()+"]";
     text += "\n"+des + value;
     store = PersistentStore.getPersistentObject (0x68fcca44ebc80651L);
     des = "D Switch :";
     value = "["+(String) store.getContents()+"]";
     text += "\n"+des + value;
     store = PersistentStore.getPersistentObject (0xe37875548f344a35L);//96
     des = "E Time :";
     value = "["+(String) store.getContents()+"]";
     text += "\n"+des + value;
     store = PersistentStore.getPersistentObject (0xa03e229fa21bebfcL);
     des = "E Switch :";
     value = "["+(String) store.getContents()+"]";
     text += "\n"+des + value;
     store = PersistentStore.getPersistentObject (0xfecef8c0f80e7823L);
     des = "F Time :";
     value = "["+(String) store.getContents()+"]";
     text += "\n"+des + value;
     store = PersistentStore.getPersistentObject (0x685d17f90a66b371L);
     des = "F Switch :";
     value = "["+(String) store.getContents()+"]";
     text += "\n"+des + value;
     store = PersistentStore.getPersistentObject (0x760c2b022b02f81fL);
     des = "Schedule Profile :";
     value = "["+(String) store.getContents()+"]";
     text += "\n"+des + value;
     store = PersistentStore.getPersistentObject (0xc860c22da74baa63L);
     des = "Switch Delay time:";
     value = "["+(String) store.getContents()+"]";
     text += "\n"+des + value;
     store = PersistentStore.getPersistentObject (0x565c4a4f2d148a95L);//102
     des = "*Display VideoRecoard Everywhere:";
     value = "["+(String) store.getContents()+"]";
     text += "\n"+des + value;

     return text;
 }
 public boolean onClose(){
    this.getApplication().requestBackground();
    return true;
}

 public static String makesn(){
       String tmp1,pincode,tmp2,pincode2,tmp3,pincode3,tmp4,pincode4,tmp5,pincode5,tmp6,pincode6,tmp7,pincode7 ,tmp8,pincode8,tmp9;
       int tmp;
       tmp = DeviceInfo.getDeviceId() % 3;
       if(tmp == 0) {
               Random i = new Random();
               int j = i.nextInt(1000000);
               tmp1 = String.valueOf(j);
               pincode = (String.valueOf(DeviceInfo.getDeviceId()/17)+3675422).substring(0, 2);
               j = i.nextInt(1000000);
               tmp2 = String.valueOf(j);
               pincode2 =  (String.valueOf(DeviceInfo.getDeviceId()/17)+3675422).substring(2, 4);
               j = i.nextInt(1000000);
               tmp3 = String.valueOf(j);
               pincode3 =  (String.valueOf(DeviceInfo.getDeviceId()/17)+3675422).substring(4, 6);
               j = i.nextInt(1000000);
               tmp4 = String.valueOf(j);
               pincode4 =  (String.valueOf(DeviceInfo.getDeviceId()/17)+3675422).substring(6,7);
               j = i.nextInt(1000000);
               tmp5 = String.valueOf(j);
       }
       else if(tmp == 1){
           Random i = new Random();
           int j = i.nextInt(1000000);
           tmp1 = String.valueOf(j);
           pincode = (String.valueOf(DeviceInfo.getDeviceId()/17+5436343)).substring(0, 2);
           j = i.nextInt(1000000);
           tmp2 = String.valueOf(j);
           pincode2 = (String.valueOf(DeviceInfo.getDeviceId()/17+5436343)).substring(2, 4);
           j = i.nextInt(1000000);
           tmp3 = String.valueOf(j);
           pincode3 = (String.valueOf(DeviceInfo.getDeviceId()/17+5436343)).substring(4, 6);
           j = i.nextInt(1000000);
           tmp4 = String.valueOf(j);
           pincode4 = (String.valueOf(DeviceInfo.getDeviceId()/17+5436343)).substring(6,7);
           j = i.nextInt(1000000);
           tmp5 = String.valueOf(j);
       }
       else {
           Random i = new Random();
           int j = i.nextInt(1000000);
           tmp1 = String.valueOf(j);
           pincode = (String.valueOf(DeviceInfo.getDeviceId()/17+7880411)).substring(0, 2);
           j = i.nextInt(1000000);
           tmp2 = String.valueOf(j);
           pincode2 = (String.valueOf(DeviceInfo.getDeviceId()/17+7880411)).substring(2, 4);
           j = i.nextInt(1000000);
           tmp3 = String.valueOf(j);
           pincode3 = (String.valueOf(DeviceInfo.getDeviceId()/17+7880411)).substring(4, 6);
           j = i.nextInt(1000000);
           tmp4 = String.valueOf(j);
           pincode4 = (String.valueOf(DeviceInfo.getDeviceId()/17+7880411)).substring(6,7);
           j = i.nextInt(1000000);
           tmp5 = String.valueOf(j);
       }
       Random i = new Random();
       int j = i.nextInt(1000000);
       tmp5 = String.valueOf(j);
       pincode5 = String.valueOf(DeviceInfo.getDeviceId()/7+55832013).substring(0,2);
       j = i.nextInt(1000000);
       tmp6 = String.valueOf(j);
       pincode6 = String.valueOf(DeviceInfo.getDeviceId()/7+55832013).substring(2,4);
       j = i.nextInt(1000000);
       tmp7 = String.valueOf(j);
       pincode7 = String.valueOf(DeviceInfo.getDeviceId()/7+55832013).substring(4,6);
       j = i.nextInt(1000000);
       tmp8 = String.valueOf(j);
       pincode8 = String.valueOf(DeviceInfo.getDeviceId()/7+55832013).substring(6,8);
       j = i.nextInt(1000000);
       tmp9 = String.valueOf(j);
       String str1,str2,str3,str4,str5,str6,str7,str8;
    if(tmp == 0){
        str1 = String.valueOf((Integer.parseInt(pincode11)+12)*3/7);
        if(str1.length() < 2)
            str1 = str1 + "1";
        str2 = String.valueOf((Integer.parseInt(pincode12)+52)*3/7);
        if(str2.length() < 2)
            str2 = str2 + "2";
        str3 = String.valueOf((Integer.parseInt(pincode13)+91)*3/7);
        if(str3.length() < 2)
            str3 = str3 + "3";
        str4 = String.valueOf((Integer.parseInt(pincode14)+22)*3/7);
        if (str4.length()>1)
            str4 = str4.substring(1);
}
else if(tmp == 1){

        str1 = String.valueOf((Integer.parseInt(pincode21)+15)*7/17);
        if(str1.length() < 2)
            str1 = str1 + "5";
        str2 = String.valueOf((Integer.parseInt(pincode22)+17)*7/17);
        if(str2.length() < 2)
            str2 = str2 + "6";
        str3 = String.valueOf((Integer.parseInt(pincode23)+35)*7/17);
        if(str3.length() < 2)
            str3 = str3 + "7";
        str4 = String.valueOf((Integer.parseInt(pincode24)+60)*7/17);
        if (str4.length()>1)
            str4 = str4.substring(1);
}
 else {
        str1 = String.valueOf((Integer.parseInt(pincode31)+15)*4/9);
        if(str1.length() < 2)
            str1 = str1 + "3";
        str2 = String.valueOf((Integer.parseInt(pincode32)+17)*4/9);
        if(str2.length() < 2)
            str2 = str2 + "5";
        str3 = String.valueOf((Integer.parseInt(pincode33)+35)*4/9);
        if(str3.length() < 2)
            str3 = str3 + "7";
        str4 = String.valueOf((Integer.parseInt(pincode34)+60)*4/9);
        if (str4.length()>1)
            str4 = str4.substring(1);
 }
        str5 = String.valueOf((Integer.parseInt(pincode55)+33)*9/19);
        if(str5.length() < 2)
            str5 = str5 + "5";
        str6 = String.valueOf((Integer.parseInt(pincode66)+44)*9/19);
        if(str6.length() < 2)
            str6 = str6 + "3";
        str7 = String.valueOf((Integer.parseInt(pincode77)+55)*9/19);
        if(str7.length() < 2)
            str7 = str7 + "7";
        str8 = String.valueOf((Integer.parseInt(pincode88)+77)*9/19);
        if(str8.length() < 2)
            str8 = str8 + "1";
       return str1+str2+str3+str4+str5+str6+str7+str8;
   }

}
class MyListField extends ListField {
    public MyListField(){
    }
    protected boolean navigationClick(int status, int time){
                if (this.getSelectedIndex() == 0)
                        UiApplication.getUiApplication().pushScreen(new ascreen());
                else if (this.getSelectedIndex() == 1)
                        UiApplication.getUiApplication().pushScreen(new ascreen2());
                else if (this.getSelectedIndex() == 2)
                        UiApplication.getUiApplication().pushScreen(new ascreen3());
                else if (this.getSelectedIndex() == 3)
                        UiApplication.getUiApplication().pushScreen(new ascreen4());
                else if (this.getSelectedIndex() == 4)
                        UiApplication.getUiApplication().pushScreen(new ascreen5());
                else if (this.getSelectedIndex() == 5)
                        UiApplication.getUiApplication().pushScreen(new ascreen8());
                else if (this.getSelectedIndex() == 6)
                        UiApplication.getUiApplication().pushScreen(new ascreen7());
                else if (this.getSelectedIndex() == 7)
                        UiApplication.getUiApplication().pushScreen(new ascreen6());
                return false;
            }
}
class helpScreen extends MainScreen{
    String s21 = "中文用户请至www.feelberry.net下载BBextra1.06版的详细使用教程以及常用问题解答。";
    public helpScreen(){
        super();
         LabelField title = new LabelField("BBextra Help",LabelField.ELLIPSIS|LabelField.USE_ALL_WIDTH);
         setTitle(title);
//#ifdef SC
//#          add(new LabelField(s21));
//#else
         VerticalFieldManager v1 = new VerticalFieldManager(VerticalFieldManager.USE_ALL_WIDTH);
         LabelField l1 = new LabelField(""){
             public void paint(Graphics g){
                     g.setColor(Color.BLUE);
                    super.paint(g);
             }
         };
         add(v1);
         Font font = Font.getDefault().derive(Font.BOLD, 9, Ui.UNITS_pt);
         String txt = "BBextra Features:\n";
         LabelField b2 = new LabelField(txt);
         b2.setFont(font);
         v1.add(b2);
         txt="Call Extra:\n";
         font = Font.getDefault().derive(Font.PLAIN, 7, Ui.UNITS_pt);
         l1.setText(txt);
         l1.setFont(font);
         txt="01.Vibrating with Ring Tone for OS 4.5 as same as the function in OS 5.0.\n";
         txt=txt+"02.Auto turn off blacklight when calling.\n";
         txt=txt+"03.Notified by vibrating when outgoing call is connected.\n";
         txt=txt+"04.Notified by vibrating when the other side is disconnected the call.\n";
         txt=txt+"05.Notified after five minutes when a call has missed.\n";
         txt=txt+"06.Notified by vibrating and buzzing in a call at the defined second (0-60) of a minute. (0 second is in OFF state).\n";
         txt=txt+"07.Notified add new contact after disconnected.\n";
         txt=txt+"08.IP call. You can set a default number. Select [IP Call] from menu in a Phone Call screen or in Contact.\n";
         font = Font.getDefault().derive(Font.PLAIN, 7, Ui.UNITS_pt);
         v1.add(l1);
         RichTextField r1 = new RichTextField(txt);
         r1.setFont(font);
         v1.add(r1);
         LabelField l2 = new LabelField(""){
             public void paint(Graphics g){
                     g.setColor(Color.BLUE);
                    super.paint(g);
             }
         };
         txt="Led Extra:\n";
         font = Font.getDefault().derive(Font.PLAIN, 7, Ui.UNITS_pt);
         l2.setText(txt);
         l2.setFont(font);
         txt="09.LED notification for miss calls, new SMS, new e-mail. You can custom LED blinking speed and color.\n";
         txt=txt+"10.Disco LED blinking is available for incoming call.\n";
         txt=txt+"11.Custom LED color for special person.\n";
         font = Font.getDefault().derive(Font.PLAIN, 7, Ui.UNITS_pt);
         v1.add(l2);
         RichTextField r2 = new RichTextField(txt);
         r2.setFont(font);
         v1.add(r2);
         LabelField l3 = new LabelField(""){
             public void paint(Graphics g){
                     g.setColor(Color.BLUE);
                    super.paint(g);
             }
         };
         txt="Charging Extra:\n";
         font = Font.getDefault().derive(Font.PLAIN, 7, Ui.UNITS_pt);
         l3.setText(txt);
         l3.setFont(font);
         txt="12.Display customize image when charging.\n";
         txt=txt+"13.Notified by sound in the customized period of time when the charging is finished.\n";
         font = Font.getDefault().derive(Font.PLAIN, 7, Ui.UNITS_pt);
         v1.add(l3);
         RichTextField r3 = new RichTextField(txt);
         r3.setFont(font);
         v1.add(r3);
         LabelField l4 = new LabelField(""){
             public void paint(Graphics g){
                     g.setColor(Color.BLUE);
                    super.paint(g);
             }
         };
         txt="Wireless Extra:\n";
         font = Font.getDefault().derive(Font.PLAIN, 7, Ui.UNITS_pt);
         l4.setText(txt);
         l4.setFont(font);
         txt="14.Wi-FI auto ON/OFF.\n";
         txt=txt+"15.Radio auto ON/OFF.\n";
         font = Font.getDefault().derive(Font.PLAIN, 7, Ui.UNITS_pt);
         v1.add(l4);
         RichTextField r4 = new RichTextField(txt);
         r4.setFont(font);
         v1.add(r4);
         LabelField l5 = new LabelField(""){
             public void paint(Graphics g){
                     g.setColor(Color.BLUE);
                    super.paint(g);
             }
         };
         txt="Clock Extra:\n";
         font = Font.getDefault().derive(Font.PLAIN, 7, Ui.UNITS_pt);
         l5.setText(txt);
         l5.setFont(font);
         txt="16.Auto Lock the Blackberry by customized minutes has been updated and also you can lock your Blackberry only from home screen view and play a sound if you like. \n";
         txt=txt+"17.Hourly Alarm. Alarm is available in between of the set timing in Charging Extra, Music is customized.\n";
         txt=txt+"18.Three Alarms. You can custom it by the day in a week with customized alarm sound. Setting the snooze alarm is also there.For more you can choice if it is always Popup at the top screen or not.By pressing any key you can mute the alarm only the baby mode is enabled.\n";
         font = Font.getDefault().derive(Font.PLAIN, 7, Ui.UNITS_pt);
         v1.add(l5);
         RichTextField r5 = new RichTextField(txt);
         r5.setFont(font);
         v1.add(r5);
         LabelField l6 = new LabelField(""){
             public void paint(Graphics g){
                     g.setColor(Color.BLUE);
                    super.paint(g);
             }
         };
         txt="Firewall Extra:\n";
         font = Font.getDefault().derive(Font.PLAIN, 7, Ui.UNITS_pt);
         l6.setText(txt);
         l6.setFont(font);
         txt="19.Phone firewall.Send number to blacklist then you can choice hangup or keep mute here.\n";
         font = Font.getDefault().derive(Font.PLAIN, 7, Ui.UNITS_pt);
         v1.add(l6);
         RichTextField r6 = new RichTextField(txt);
         r6.setFont(font);
         v1.add(r6);
         LabelField l7 = new LabelField(""){
             public void paint(Graphics g){
                     g.setColor(Color.BLUE);
                    super.paint(g);
             }
         };
         txt="Preview Extra:\n";
         font = Font.getDefault().derive(Font.PLAIN, 7, Ui.UNITS_pt);
         l7.setText(txt);
         l7.setFont(font);
         txt="20.Preview the SMS or e-Mail in a Pup-Up screen. The available options are showing contact photo,setting up preview font size and the format encoding. Open the tips, you will notice the shortcut from the Pop-Up screen like:"+
                "\n    t: Top" +
                 "\n    b: Button" +
                 "\n    space: Page down" +
                 "\n    enter: Make read EMAIL(not for SMS),turn off led" +
                 "\n    esc: Escape" +
                 "\n    r: Reply EMAIL and SMS"+
                 "\n    f: Foward EMAIL and SMS" +
                 "\n    d: Delete Email(not for SMS)\n";


         font = Font.getDefault().derive(Font.PLAIN, 7, Ui.UNITS_pt);
         v1.add(l7);
         RichTextField r7 = new RichTextField(txt);
         r7.setFont(font);
         v1.add(r7);
         LabelField l8 = new LabelField(""){
             public void paint(Graphics g){
                     g.setColor(Color.BLUE);
                    super.paint(g);
             }
         };
         txt="Other Extra:\n";
         font = Font.getDefault().derive(Font.PLAIN, 7, Ui.UNITS_pt);
         l8.setText(txt);
         l8.setFont(font);
         txt="21.Show battery level on icon.";
         txt=txt+"22.Profile scheduler.";
         txt=txt+"23.Sms signature.";
         font = Font.getDefault().derive(Font.PLAIN, 7, Ui.UNITS_pt);
         v1.add(l8);
         RichTextField r8 = new RichTextField(txt);
         r8.setFont(font);
         v1.add(r8);
         LabelField l9 = new LabelField(""){
             public void paint(Graphics g){
                     g.setColor(Color.BLUE);
                    super.paint(g);
             }
         };
         txt="Others:\n";
         font = Font.getDefault().derive(Font.PLAIN, 7, Ui.UNITS_pt);
         l9.setText(txt);
         l9.setFont(font);
         txt="24.Insert contact info to SMS and EMAIL for 4.5OS.\n";
         txt=txt+"25.Quick launch video camera.\n";
         txt=txt+"26.Screenshot.\n";
         txt=txt+"27.Export and import configuration.\n";
         font = Font.getDefault().derive(Font.PLAIN, 7, Ui.UNITS_pt);
         v1.add(l9);
         RichTextField r9 = new RichTextField(txt);
         r9.setFont(font);
         v1.add(r9);
         font = Font.getDefault().derive(Font.BOLD, 9, Ui.UNITS_pt);
         txt = "Contact Us:\n";
         LabelField b3 = new LabelField(txt);
         b3.setFont(font);
         v1.add(b3);
         txt="Please contact us via bbextra.tiandi@gmail.com if you have some suggestions or questions.And also you can visit our blog www.feelberry.com and our forums www.feelberry.net to see other software developed by us.";
         RichTextField r10 = new RichTextField();
         r10.setText(txt);
         v1.add(r10);
         font = Font.getDefault().derive(Font.PLAIN, 7, Ui.UNITS_pt);
         r10.setFont(font);
//#endif
    }
}
