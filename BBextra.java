//#preprocess
package test;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import net.rim.blackberry.api.phone.phonelogs.CallLog;
import java.util.Enumeration;
import java.util.Timer;
import java.util.Vector;
import javax.microedition.io.Connector;
import javax.microedition.io.file.FileConnection;
import javax.microedition.media.Manager;
import javax.microedition.media.MediaException;
import javax.microedition.media.Player;
import net.rim.blackberry.api.homescreen.HomeScreen;
import net.rim.blackberry.api.mail.Message;
import net.rim.blackberry.api.menuitem.ApplicationMenuItemRepository;
import net.rim.blackberry.api.pdap.BlackBerryContact;
import net.rim.blackberry.api.pdap.BlackBerryContactList;
import net.rim.blackberry.api.pdap.BlackBerryPIM;
import net.rim.blackberry.api.phone.*;
import net.rim.blackberry.api.phone.phonelogs.PhoneCallLog;
import net.rim.blackberry.api.phone.phonelogs.PhoneLogListener;
import net.rim.blackberry.api.phone.phonelogs.PhoneLogs;
import net.rim.device.api.applicationcontrol.ApplicationPermissions;
import net.rim.device.api.applicationcontrol.ApplicationPermissionsManager;
import net.rim.device.api.system.Alert;
import net.rim.device.api.system.ApplicationDescriptor;
import net.rim.device.api.system.ApplicationManager;
import net.rim.device.api.system.ApplicationManagerException;
import net.rim.device.api.system.Backlight;
import net.rim.device.api.system.Bitmap;
import net.rim.device.api.system.Characters;
import net.rim.device.api.system.DeviceInfo;
import net.rim.device.api.system.EventInjector;
import net.rim.device.api.system.EventInjector.KeyCodeEvent;
import net.rim.device.api.system.EventLogger;
import net.rim.device.api.system.KeypadListener;
import net.rim.device.api.system.LED;
import net.rim.device.api.system.PersistentObject;
import net.rim.device.api.system.PersistentStore;
import net.rim.device.api.system.Radio;
import net.rim.device.api.system.RadioInfo;
import net.rim.device.api.system.RealtimeClockListener;
import net.rim.device.api.system.RuntimeStore;
import net.rim.device.api.system.SystemListener2;
import net.rim.device.api.ui.*;

/**
 *
 * @author Tiandi Zhang
 */
class globel{
    public static String ver= "1.06.011";
    public static String phoneno = "";
    public static String incoming = "";
    public static boolean canled = false;
    public static boolean emailled = false;
    public static String theFile;
    public static String theMusic;
    public static String theMusic2;
    public static String theMusic3;
    public static boolean canrun = false;
    public static int blinkon = 10;
    public static int blinkoff = 30;
    public static int startmin;
    public static int startsec;
    public static int starthour;
    public static int endmin;
    public static int endsec;
    public static int endhour;
    public static boolean incolorled = false;
    public static Player alertmusic;
    public static boolean alertmusicerror = false;
    public static String indebug;
    public static boolean canvibrate = false;
    public static String lastsms = "";
    public static String lastfrom ="";
    public static Date lasttime = new Date(System.currentTimeMillis());
    public static boolean firstlong = false;
    public static boolean usbmode = false;
    public static boolean forcemenu = false;
}
public class BBextra extends UiApplication {
        private static BlackBerryContactList contactlist;
        private static Vector contacts;
        private static boolean endbytarget;
        private static long ID = "test".hashCode();
        final static Timer time1 = new Timer();
        public static boolean incharing;
        public static boolean canalert = false;
        public static boolean canmissed = false;
        public static boolean inthetime = false;
        public static boolean canled = false;
        static Timer time2 = new Timer();
        static int tmphour1=99,tmpmin1=99,tmphour2=99,tmpmin2=99,tmphour3=99,tmpmin3=99;

        static Bitmap myBitmap = Bitmap.getBitmapResource("extra100.png");
        static Bitmap flashbit = Bitmap.getBitmapResource("flash.png");
        static BBextra theApp;
        static {
        EventLogger.register(0x91332e638d661069L, "BBextra", EventLogger.VIEWER_STRING);

    }
    public static void main(String[] args) throws IOException{

        ////////////////////////
        //     make log       //
        ////////////////////////
        String logMessage = "main... ";
                if (args != null && args.length > 0)
                    logMessage += args[0];

                if (EventLogger.logEvent(0x4c9d3452d87922f2L, logMessage.getBytes(), EventLogger.ALWAYS_LOG)) {
                    //System.out.println("****BBextra Log****:Log Success");
                }

        theApp = new BBextra();
//        RuntimeStore rs = RuntimeStore.getRuntimeStore();
//        rs.put(0x6bde11d91989a4acL, theApp);
        //System.out.println("****BBextra Log****:start bbextra step");
        //////////////////////////////////////
        //          set permission
        /////////////////////////////////////
       ApplicationPermissions apm = ApplicationPermissionsManager.getInstance().getApplicationPermissions();
        if (apm.getPermission(ApplicationPermissions.PERMISSION_BROWSER_FILTER) != ApplicationPermissions.VALUE_ALLOW) {
            ApplicationPermissions ap = new ApplicationPermissions();
            int[] key = apm.getPermissionKeys();
            for(int i=0;i<key.length;i++){
                ap.addPermission(key[i]);
            }

            ApplicationPermissionsManager.getInstance().invokePermissionsRequest(ap);
        }

        //////////////////////////////
        //  init  sn = 60m          //
        //////////////////////////////
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
        //System.out.println("****BBextra Log****:Trail remain"+Integer.parseInt((String) storesn.getContents()));
        //System.out.println("****BBextra Log****:Detect if bbextra can run="+globel.canrun);
        if(globel.canrun == true) {


        ////////////////////////////
        //    sms detect by thread//
        ////////////////////////////

            aThread anew = new aThread();
            anew.start();
            addmenu();
//            GlobalEventListener newg = new GlobalEventListener() {
//
//                public void eventOccurred(long guid, int data0, int data1, Object object0, Object object1) {
//                    if(guid == 0x6bde11d91989a4acL)
//                        Dialog.alert("Screenshot file save to SDCard/BBextra/BBescreen"+data0+".png");
//                }
//            };
//          theApp.addGlobalEventListener(newg);

            PersistentObject store;
        store = PersistentStore.getPersistentObject (0xdd4cdf439a9307d9L);
                    synchronized (store) {
                        if(store.getContents() != null) {
                        if (store.getContents().toString().equalsIgnoreCase("Enable") ){
                           Graphics g ;
                           String tmp = String.valueOf(DeviceInfo.getBatteryLevel());
                           Font font = null;
                           if (DeviceInfo.getDeviceName().startsWith("8") &&! DeviceInfo.getDeviceName().startsWith("89"))
                               font = Font.getDefault().derive(Font.BOLD,9,Ui.UNITS_pt);
                           else
                               font = Font.getDefault().derive(Font.BOLD,5,Ui.UNITS_pt);
                           Bitmap tmpbit = null ;

                           if(DeviceInfo.getBatteryLevel()<20){
                               Bitmap myBitmap2 = Bitmap.getBitmapResource("extra20.png");
                               tmpbit = myBitmap2;
                            }
                           else if(DeviceInfo.getBatteryLevel()<40){
                               Bitmap myBitmap4 = Bitmap.getBitmapResource("extra40.png");
                               tmpbit = myBitmap4;
                            }
                           else if(DeviceInfo.getBatteryLevel()<60){
                               Bitmap myBitmap6 = Bitmap.getBitmapResource("extra60.png");
                               tmpbit = myBitmap6;
                            }
                           else if(DeviceInfo.getBatteryLevel()<80){
                               Bitmap myBitmap8 = Bitmap.getBitmapResource("extra80.png");
                               tmpbit = myBitmap8;
                            }
                           else
                           {
                               Bitmap myBitmap10 = Bitmap.getBitmapResource("extra100.png");
                               tmpbit = myBitmap10;
                           }
                               g = new Graphics(tmpbit);
                               g.setFont(font);
                               g.setColor(Color.WHITE);

                               if(DeviceInfo.getBatteryLevel()<100)
                                    g.drawText(tmp+"%",25, 20);
                               else
                                    g.drawText("Max",25, 20);
                               HomeScreen.updateIcon(tmpbit);
                        }
                    }
                    }

                    //////////////////////////////////
                    //swithes led when missing call //
                    //////////////////////////////////
          //System.out.println("****BBextra Log****:start to phoneloglistener");
            PhoneLogListener loglisten = new PhoneLogListener(){

            public void callLogAdded(CallLog cl) {
                globel.incolorled=false;
                    LED.setState(LED.STATE_OFF);
                 //System.out.println("****BBextra Log****:Calllog added");
                 PhoneLogs logs = PhoneLogs.getInstance();
                 if (logs.numberOfCalls(PhoneLogs.FOLDER_MISSED_CALLS) > 0){
                     PhoneCallLog clogs = (PhoneCallLog) logs.callAt(logs.numberOfCalls(PhoneLogs.FOLDER_MISSED_CALLS)-1, PhoneLogs.FOLDER_MISSED_CALLS);
                 if((clogs.getType() == PhoneCallLog.TYPE_MISSED_CALL_UNOPENED)&&cl.getDate().equals(clogs.getDate())) {
                     //System.out.println("****BBextra Log****:Detect last call is missed call");
                    /////////////////////
                    //    add missed call momo
                    //////////////////////
                    Calendar c2 = Calendar.getInstance();
                c2.setTime(new Date(System.currentTimeMillis()));
                globel.endmin = c2.get(Calendar.MINUTE);
                globel.endsec = c2.get(Calendar.SECOND);
                globel.endhour = c2.get(Calendar.HOUR);
                clogs.setNotes("This missed call started at "+globel.starthour+":"+globel.startmin+":"+globel.startsec+" and ended at "
                        +globel.endhour+":"+globel.endmin+":"+globel.endsec);
                    PersistentObject store2;
                    store2 = PersistentStore.getPersistentObject (0x1c8c5fd3c1daa52aL);
                    synchronized (store2) {
                        if(store2.getContents() != null)
                        if (!store2.getContents().toString().equalsIgnoreCase("Default") ) {
                 try {
                   Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                     if (LED.isPolychromatic()){
                if (store2.getContents().toString().equalsIgnoreCase("Orange"))
                    LED.setColorConfiguration(globel.blinkon*100, globel.blinkoff*100, 0x00FF6100);
                else if (store2.getContents().toString().equalsIgnoreCase("Yellow"))
                    LED.setColorConfiguration(globel.blinkon*100, globel.blinkoff*100, 0x00FFFF00);
                else if (store2.getContents().toString().equalsIgnoreCase("Green"))
                    LED.setColorConfiguration(globel.blinkon*100, globel.blinkoff*100, 0x0000FF00);
                else if (store2.getContents().toString().equalsIgnoreCase("Cyan"))
                    LED.setColorConfiguration(globel.blinkon*100, globel.blinkoff*100, 0x0000FFFF);
                else if (store2.getContents().toString().equalsIgnoreCase("Blue"))
                    LED.setColorConfiguration(globel.blinkon*100, globel.blinkoff*100, 0x000000FF);
                else if (store2.getContents().toString().equalsIgnoreCase("Purple"))
                    LED.setColorConfiguration(globel.blinkon*100, globel.blinkoff*100, 0x00A020F0);
                else
                    LED.setColorConfiguration(globel.blinkon*100, globel.blinkoff*100, 0x00FFFFFF);
                   }
                 }
                }
                     store2 = PersistentStore.getPersistentObject (0xd13f2a862fcbd1c7L);
                    synchronized (store2) {
                        if(store2.getContents() != null)
                        if (store2.getContents().toString().equalsIgnoreCase("Enable")) {
                            BBextra.canmissed = true;
                            //System.out.println("****BBextra Log****:detect miss call need vibrate");
                             globel.alertmusicerror=false;
                             Timer time1 = new Timer();
                             time1.scheduleAtFixedRate(new missedalert(),300000,300000);

                        }
                    }
            }
                     /////////////// to fix led OS bug
                 else{
                     if(LED.isPolychromatic())
                         LED.setState(LED.STATE_OFF);
                 }
                        }
                    }
            public void callLogUpdated(CallLog cl, CallLog oldCl) {
                //System.out.println("****BBextra Log****:Detect call log update");
                   LED.setState(LED.STATE_OFF);
                if(!globel.alertmusicerror & BBextra.canmissed) {
                    if(globel.alertmusic != null) {
                    try {
                        globel.alertmusic.stop();
                    } catch (MediaException ex) {
                        //System.out.println("****BBextra Log****:Can not stop alertmusic");
                    }
                    }
                }
                BBextra.canmissed = false;
            }

            public void callLogRemoved(CallLog cl) {
                //System.out.println("****BBextra Log****:Detect call log remove");
            }

            public void reset() {
                //System.out.println("****BBextra Log****:Detect call log reset");
            }

            };
                   PhoneLogs.addListener(loglisten);

            AbstractPhoneListener Listena = new AbstractPhoneListener() {
            public void callInitiated(int callid) {
                //System.out.println("****BBextra Log****:Detect call init");
                globel.phoneno = "";
                globel.canvibrate=false;
            }

            public void callWaiting(int callid) {
                //System.out.println("****BBextra Log****:Detect call waiting");
            }

            public void callIncoming(int callId) {
                globel.phoneno = "";
                endbytarget = false;

                //////////////////////
                //  close pscreen   //
                //////////////////////
                int a = 0;
                if(pScreen.tmpscreen !=null)
                    UiApplication.getUiApplication().popScreen(pScreen.tmpscreen);
                //////////////////
                //  missed call momo
                //////////////////
                Calendar c2 = Calendar.getInstance();
                c2.setTime(new Date(System.currentTimeMillis()));
                globel.startmin = c2.get(Calendar.MINUTE);
                globel.startsec = c2.get(Calendar.SECOND);
                globel.starthour = c2.get(Calendar.HOUR);
                PersistentObject store;
                globel.incoming = Phone.getCall(callId).getDisplayPhoneNumber();
                //System.out.println("****BBextra Log****:Detect original call incoming,number="+globel.incoming);
                //System.out.println("****BBextra Log****:Incoming number length="+globel.incoming.length());
                globel.incoming=checkstr(globel.incoming);
                while (a > -1) {
                a = globel.incoming.indexOf(' ');
                    //System.out.println("****BBextra Log****:Space in incoming number position="+a);
                    if (a != -1){
                        globel.incoming = globel.incoming.substring(a+1).trim();
                         //System.out.println("****BBextra Log****:Detect adjust incoming number="+globel.incoming);
                    }
                  }
//                 System.out.println("****BBextra Log****:The final incoming number=["+globel.incoming+"]");

                    store = PersistentStore.getPersistentObject (0x1d0e86de3c5a9540L);
                    synchronized (store) {
                        if(store.getContents() != null)
                        if (store.getContents().toString().equalsIgnoreCase("Enable")){
                            //System.out.println("****BBextra Log****:Start firewall function");
                            if (isblock(globel.incoming)) {
                            store = PersistentStore.getPersistentObject (0x860e202733f13780L);
                            synchronized (store) {
                                if(store.getContents() != null)
                                if (store.getContents().toString().equalsIgnoreCase("Mute")){
                                    UiApplication.getApplication().invokeLater(new Runnable()
                            {
                            public void run()
                            {
                                            EventInjector.KeyCodeEvent ev = new EventInjector.KeyCodeEvent(
                                                    EventInjector.KeyCodeEvent.KEY_DOWN, (char) Keypad.KEY_VOLUME_DOWN,KeypadListener.STATUS_NOT_FROM_KEYPAD);
                                            EventInjector.invokeEvent(ev);
                                            //System.out.println("****BBextra Log****:mute calling");
                            }
                                    });
                                }
                                else {
                                    EventInjector.invokeEvent(new EventInjector.KeyCodeEvent(
                                             EventInjector.KeyCodeEvent.KEY_DOWN,(char) Keypad.KEY_END,KeypadListener.STATUS_NOT_FROM_KEYPAD));
                                }
                            }
                            }
                        }
                    }



            /////////////////////
            //   vibirate with tone
            /////////////////////
            store = PersistentStore.getPersistentObject (0x7f948c198f1bfafaL);
                    synchronized (store) {
                        if(store.getContents() != null)
                        if(store.getContents().toString().equalsIgnoreCase("Enable")){
                            Timer time1 = new Timer();
                            globel.canvibrate=true;
                            time1.scheduleAtFixedRate(new Vibration(),500,4000);
                        }
                    }
                ////////////////////////////
                // led disco when imcoming call //
                ////////////////////////////
            store = PersistentStore.getPersistentObject (0xd1513f6e5fb17e24L);
                    synchronized (store) {
                        if(store.getContents() != null)
                        if(store.getContents().toString().equalsIgnoreCase("Enable")) {
                            Timer time1 = new Timer();
                            globel.incolorled=true;
                            time1.scheduleAtFixedRate(new TimerLED(),500,300);
                        }
                    }
            }
            public void callAnswered(int callId) {
              //  System.out.println("****BBextra Log****:Detect call answer");
            }
            public void callConnected(int callId) {
                int a = 0;
                lightoff();
                globel.canvibrate=false;
                globel.incolorled=false;
                LED.setState(LED.STATE_OFF);
                Alert.stopVibrate();
                PersistentObject store;
                store = PersistentStore.getPersistentObject (0x9e5719378f00c08L);
                    synchronized (store) {
                        if(store.getContents() !=null ){
                        if (Integer.parseInt(store.getContents().toString()) != 0) {
                                 BBextra.canalert = true;
                                 time2.scheduleAtFixedRate(new alertmin(),1000,1000);
                        }
                        }
                    }
                globel.phoneno = Phone.getActiveCall().getDisplayPhoneNumber();
                //System.out.println("****BBextra Log****:Call connect to ["+globel.phoneno+"]==================");
                globel.phoneno=checkstr(globel.phoneno);
                while (a > -1) {
                a = globel.phoneno.indexOf(' ');
                    //System.out.println("****BBextra Log****:Space in connecting number position="+a);
                    if (a != -1){
                        globel.phoneno = globel.phoneno.substring(a+1);
                        if(globel.phoneno.charAt(0) == ' ')
                           globel.phoneno = globel.phoneno.substring(1);
                        //System.out.println("****BBextra Log****:Ajust connecting number ="+globel.phoneno);
                    }
                  }
                //System.out.println("****BBextra Log****:Final connecting number ="+globel.phoneno);
                endbytarget = true;

                if(Phone.getActiveCall().isOutgoing()) {
                    /////////////////////////////////////////////////////
                    // switch Vibrate after callConnected when outgoing//
                    /////////////////////////////////////////////////////
                    store = PersistentStore.getPersistentObject (0x76e829a94adadcc6L);
                    synchronized (store) {
                                if (store.getContents() == null)
                                    Alert.startVibrate(500);
                                else if (store.getContents().toString().equalsIgnoreCase("Short"))
                                    Alert.startVibrate(100);
                                else if (store.getContents().toString().equalsIgnoreCase("Middle"))
                                    Alert.startVibrate(500);
                                else if (store.getContents().toString().equalsIgnoreCase("Long"))
                                    Alert.startVibrate(1000);
                    }
                }

            }
            public void callConferenceCallEstablished(int callId) {
            }

            public void conferenceCallDisconnected(int callId) {
            }

            public void callDisconnected(int callId) {
                LED.setState(LED.STATE_OFF);
                Alert.stopVibrate();
                globel.canvibrate=false;
                globel.endmin = 0;
                globel.endsec = 0;
                //System.out.println("****BBextra Log****:Detect call disconnect");
                BBextra.canalert = false;
                PersistentObject store;
                //System.out.println("****BBextra Log****:Disconnect by target="+endbytarget);
                if (endbytarget) {
                    ////////////////////////////////////////////////////
                   // switch Vibrate after disconnect by opposite side//
                    ////////////////////////////////////////////////////
                    store = PersistentStore.getPersistentObject (0x4f8080c03ba1cc10L);
                    synchronized (store) {
                        if (store.getContents() == null)
                                    Alert.startVibrate(500);
                                else if (store.getContents().toString().equalsIgnoreCase("Short"))
                                    Alert.startVibrate(100);
                                else if (store.getContents().toString().equalsIgnoreCase("Middle"))
                                    Alert.startVibrate(500);
                                else if (store.getContents().toString().equalsIgnoreCase("Long"))
                                    Alert.startVibrate(1000);
                    }
                }
                    /////////////////////////////////////////////
                    // switch add contact if does not exsited. //
                    /////////////////////////////////////////////
                    store = PersistentStore.getPersistentObject (0x2d8da4ace5b17e2fL);
                    synchronized (store) {
                        if(store.getContents() != null)
                    if (store.getContents().toString().equalsIgnoreCase("Enable")) {
                        //System.out.println("****BBextra Log****:Start add contact function");
                        if (isexist(globel.phoneno)){
               //   if (globel.phoneno.indexOf(')') > -1)   think of search type later
                            //System.out.println("****BBextra Log****:Detect["+globel.phoneno+"] already exist, do nothing");
                        return ;
                        }
                        else
                        {
                            //System.out.println("****BBextra Log****:Detect ["+globel.phoneno+"]==================not exist, popup windows");
                            UiApplication.getUiApplication().pushGlobalScreen(new pScreen(), 3,GLOBAL_MODAL);
                        return;
                        }
                    }

                }

            }

            public void callDirectConnectConnected(int callId) {
                //System.out.println("****BBextra Log****:Detect call direct connect");
            }

            public void callDirectConnectDisconnected(int callId) {
                //System.out.println("****BBextra Log****:Detect call direct disconnect");
            }

            public void callEndedByUser(int callId) {
                    endbytarget = false;
                //System.out.println("****BBextra Log****:Detect end by user="+endbytarget);
            }

            public void callFailed(int callId, int reason) {
                //System.out.println("****BBextra Log****:Detect call failed");
            }

            public void callResumed(int callId) {
            }

            public void callHeld(int callId) {
            }

            public void callAdded(int callId) {
            }

            public void callRemoved(int callId) {
            }
            boolean isblock(String number) {
                String e1,tmp,tmp2;
                String[] ab = new String[100] ;
                String[] cd = new String[100] ;
                int i=0,j=0;
                PersistentObject store2;
                // let us check whitelist first. if yes return false ,so do not need check the following
                store2 = PersistentStore.getPersistentObject (0x4dd59dd49bb680c7L);
                synchronized (store2) {
                    if (store2.getContents() != null)
                        e1 = (String) store2.getContents();
                    else
                        e1= "";
                }
                tmp= e1;
                tmp2 =e1;
                i = tmp.indexOf("\n");
                while (i!= -1){
                    tmp = tmp2.substring(0, i);
                    tmp2 = tmp2.substring(i+1);
                    ab[j] =  tmp;
                    j++;
                    i=tmp2.indexOf("\n");
                }
                if (!tmp2.equalsIgnoreCase(""))
                    ab[j] = tmp2;
                for (i=0;i<ab.length;i++){
                    if(ab[i] == null )
                        break;
                    if(ab[i].equalsIgnoreCase(""))
                        break;
                    if(ab[i].equalsIgnoreCase(number)){
                        return false;
                    }
                }
                ab = null;

                //then read block mothed
                i = 0;
                j = 0;
                store2 = PersistentStore.getPersistentObject (0xbe60c7c34967bf0aL);
                synchronized (store2) {
                    if (store2.getContents()!=null && store2.getContents().toString().equalsIgnoreCase("Not In Whitelist"))
                        return true;
                    else {
                         store2 = PersistentStore.getPersistentObject (0x55a9ef284cd02d7bL);
                         synchronized (store2) {
                              if (store2.getContents() != null)
                                e1 = (String) store2.getContents();
                              else
                                e1= "";
                         }
                         tmp= e1;
                         tmp2 =e1;
                         i = tmp.indexOf("\n");
                         while (i!= -1){
                            tmp = tmp2.substring(0, i);
                            tmp2 = tmp2.substring(i+1);
                            cd[j] =  tmp;
                            j++;
                            i=tmp2.indexOf("\n");
                        }
                        if (!tmp2.equalsIgnoreCase(""))
                        cd[j] = tmp2;
//                        System.out.println("****BBextra Log****:cd["+j+"]="+cd[j]);
                        for (i=0;i<cd.length;i++){
                            if(cd[i] == null )
                                break;
                            if(cd[i].equalsIgnoreCase(""))
                                break;
                            if(cd[i].equalsIgnoreCase(number)){
                        //System.out.println("****BBextra Log****:Incoming number is in blacklist or block all selected");
                                cd = null;
                                return true;
                            }
                        }
                        cd =  null;
                        store2 = PersistentStore.getPersistentObject (0xbe60c7c34967bf0aL);
                        synchronized (store2) {
                            if (store2.getContents()!=null && store2.getContents().toString().equalsIgnoreCase("In Blacklist"))
                                return false;
                            else if (isexist(number))
                                return false;
                            else
                                return true;
                        }
                    }
                }
            }
           boolean isexist(String number) {
              if(number.equalsIgnoreCase(""))
                      return true;
            try {
                contactlist = (BlackBerryContactList) BlackBerryPIM.getInstance().openPIMList(BlackBerryPIM.CONTACT_LIST, BlackBerryPIM.READ_ONLY);
                Enumeration allContacts = contactlist.items();
                contacts = enumToVector(allContacts);

            for (int i=0;i<contacts.size();i++) {
                BlackBerryContact item = (BlackBerryContact)contacts.elementAt(i);

                for (int j=0;j<item.countValues(BlackBerryContact.TEL);j++){
                    try{
                        System.out.println("****BBextra Log****:Number is "+number+" and compare with "+item.getString(BlackBerryContact.TEL,j));
                            if (item.getString(BlackBerryContact.TEL,j).endsWith(number)
                                    || number.endsWith(item.getString(BlackBerryContact.TEL,j)))

                                return true;
                    }
                    catch (java.lang.Throwable ex) {
                        //System.out.println("****BBextra Log****:Detect contact field null ,error="+ex.toString());
                        }
                }
           }

              return false;
            } catch (javax.microedition.pim.PIMException ex) {
                //System.out.println("****BBextra Log****:Detect Pim error="+ex.toString());
               return true;
            }
        }
             //Convert the list of contacts from an Enumeration to a Vector
    private Vector enumToVector(Enumeration contactEnum)  {
    Vector v = new Vector();
    if (contactEnum == null)
        return v;
    while (contactEnum.hasMoreElements())    {
        v.addElement(contactEnum.nextElement());
    }
    return v;
}
        };
       Phone.addPhoneListener(Listena);

       /////////////////
       // listener2   //
       /////////////////
       checksystemlistener();

       //System.out.println("****BBextra Log****:start to realtimeclocklistener");
       RealtimeClockListener timelistener = new RealtimeClockListener(){
            private String song;
            private Player p;
            private int fromh,toh,fromm,tom,hour,min;
            private boolean timealarm = false;
            private String[] ab = new String[15] ;
            public void clockUpdated() {
//              System.out.println("****BBextra Log****:clockupdate unlock key status?"+ApplicationManager.getApplicationManager().isSystemLocked());
              ApplicationManager manager = ApplicationManager.getApplicationManager();
              ApplicationDescriptor[] appDes = manager.getVisibleApplications();
//                System.out.println("****BBextra Log****:first app ="+appDes[0].getName());
//                System.out.println("****BBextra Log****:first app module ="+appDes[0].getModuleName());
//                System.out.println("****BBextra Log****:first app bundle ="+appDes[0].getNameResourceBundle());
                //System.out.println("****BBextra Log****:Detect clock update");
                PersistentObject store4;
                store4 = PersistentStore.getPersistentObject (0xa2f31503d67bce1dL);
                synchronized (store4) {
                    int remain;
                    if(store4.getContents()== null)
                        remain = 10080;
                    else
                        remain = Integer.parseInt((String) store4.getContents());
                    //System.out.println("****BBextra Log****:Trail remain time="+remain);
                    if (remain < 90000) {
                    remain --;
                    store4.setContents(String.valueOf(remain));
                    store4.commit();
                        //System.out.println("****BBextra Log****:Trail remain time-1="+remain);
                    if(remain < 0 )
                        System.exit(0);
                    }
                }
              //////////////////////////////
              //  time available          //
              //////////////////////////////

                Calendar c1 = Calendar.getInstance();
                // get hour & min of from time
                store4 = PersistentStore.getPersistentObject (0x9baf7e102a05663fL);
                synchronized (store4) {
                     if (store4.getContents() != null) {
                        Date fromdate = new Date(Long.parseLong(store4.getContents().toString()));
                        c1.setTime(fromdate);
                        fromh = c1.get(Calendar.HOUR_OF_DAY);
                        fromm = c1.get(Calendar.MINUTE);
                     }
                     else{
                        fromh = 0;
                        fromm = 0;
                     }
                }
                // get hour & min of to time;
                store4 = PersistentStore.getPersistentObject (0xa991639c1eea79aeL);
                synchronized (store4) {
                     if (store4.getContents() != null) {
                        Date fromdate = new Date(Long.parseLong(store4.getContents().toString()));
                        c1.setTime(fromdate);
                        toh = c1.get(Calendar.HOUR_OF_DAY);
                        tom = c1.get(Calendar.MINUTE);
                     }
                     else{
                        toh = 0;
                        tom = 0;
                     }
                }
                // get currect time
                c1.setTime(new Date(System.currentTimeMillis()));
                hour = c1.get(Calendar.HOUR_OF_DAY);
                min = c1.get(Calendar.MINUTE);
                    try {
                        checkschedule();
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }

                if (((toh*60+tom)>(hour*60+min))&((hour*60+min)>(fromh*60+fromm))
                 |(((toh*60+tom)<(hour*60+min))&((hour*60+min)>(fromh*60+fromm))&((fromh*60+fromm)>(toh*60+tom)))
                 |((fromh*60+fromm)>(toh*60+tom))&((toh*60+tom)>(hour*60+min))&((hour*60+min)<(fromh*60+fromm)))

                   BBextra.inthetime = true;
                   //System.out.println("****BBextra Log****:inthetime="+BBextra.inthetime);
                int nowhour,nowmin,idletime = 0,nowday;
                store4 = PersistentStore.getPersistentObject (0xa7cb7eb2e499c592L);

                synchronized (store4) {
                    if(store4.getContents() != null)
                     if (store4.getContents().toString().equalsIgnoreCase("Enable"))
                     {
                         store4 = PersistentStore.getPersistentObject (0x743427ce25a6b74cL);
                         if(store4.getContents() != null)
                         if(!store4.getContents().toString().equalsIgnoreCase("Enable")
                                 ||(store4.getContents().toString().equalsIgnoreCase("Enable")&& !globel.usbmode)) {
                         store4 = PersistentStore.getPersistentObject (0xf79c2d6bd19e97d5L);
                        synchronized (store4) {
                            int lockmin;
                            if(store4.getContents() != null)
                                lockmin = Integer.parseInt(store4.getContents().toString());
                            else
                                lockmin = 1;
                            if ((int) DeviceInfo.getIdleTime()> 58)
                                idletime = idletime +1;
                            else
                                idletime = 0;
                            //System.out.println("****BBextra Log****:idletime before list="+idletime);
                            store4 = PersistentStore.getPersistentObject (0xb4d4b7c025832afeL);
                          synchronized (store4) {
                              if(store4.getContents() != null) {
                              String tmp = store4.getContents().toString();
                                   int i=0,j=0;
                             String tmp2 =tmp;
            i = tmp.indexOf("\n");
            while (i!= -1){
               tmp = tmp2.substring(0, i);
               tmp2 = tmp2.substring(i+1);
                ab[j] =  tmp;
                j++;
                i=tmp2.indexOf("\n");
            }
             if (!tmp2.equalsIgnoreCase(""))
                 ab[j] = tmp2;
             else
                 j--;
                 if(j>14) j = 14;
               for(i=0;i<j+1;i++) {
                   //System.out.println("****BBextra Log****:Detect unlock program list="+ab[i]);
                      if(ab[i].equalsIgnoreCase(appDes[0].getName())){
                          //System.out.println("****BBextra Log****:Detect foreground"+appDes[0].getName()+"is in the list");
                          idletime = 0;
                          break;
                      }
               }
                              }
                          }
                            //System.out.println("****BBextra Log****:idletime after list="+idletime);

                            if(idletime >= lockmin & !islock()){
                                //System.out.println("****BBextra Log****:run lock program");

                                // only lock when home screen
                                store4 = PersistentStore.getPersistentObject (0xc9217e122938cfaeL);
                                synchronized (store4) {
                      if (store4.getContents()!=null && store4.getContents().toString().equalsIgnoreCase("Enable")){
                                if(appDes[0].getName().equalsIgnoreCase("Home Screen")) {
                                     ApplicationManager.getApplicationManager().lockSystem(true);
//                 EventInjector.invokeEvent(new EventInjector.KeyCodeEvent(KeyCodeEvent.KEY_DOWN,(char)4099,0));
                                         // hold A to lock

            EventInjector.invokeEvent(new EventInjector.KeyCodeEvent(KeyCodeEvent.KEY_DOWN,
                    Characters.LATIN_CAPITAL_LETTER_A,0));
            EventInjector.invokeEvent(new EventInjector.KeyCodeEvent(KeyCodeEvent.KEY_REPEAT,
                    Characters.LATIN_CAPITAL_LETTER_A,0));                                    try {
                                        Thread.sleep(2000);
                                    } catch (InterruptedException ex) {
                                          }
            EventInjector.invokeEvent(new EventInjector.KeyCodeEvent(KeyCodeEvent.KEY_UP,
                    Characters.LATIN_CAPITAL_LETTER_A,0));
                                // hold A to lock
                                     //System.out.println("****BBextra Log****:lock when home screen");

                                store4 = PersistentStore.getPersistentObject (0xf4b763d73d7464f6L);
                  synchronized (store4) {
                      //System.out.println("****BBextra Log****:play sound enable");
                     if (store4.getContents()!=null && store4.getContents().toString().equalsIgnoreCase("Enable") ){
                         store4 = PersistentStore.getPersistentObject (0xe63a9bdc93bfe01aL);
                  synchronized (store4) {
                      if (store4.getContents() != null){
                          //System.out.println("****BBextra Log****:check the sound");
                        song = (String) store4.getContents();
                        try {
                            p = Manager.createPlayer("file:///"+song);
                            p.start();
                         } catch (MediaException pe) {
                             //System.out.println("****BBextra Log****:Can not play this alert-ring!");
                         } catch (IOException ioe) {
                             //System.out.println("****BBextra Log****:Can not find alert-ring file!");
                         }
                     }
                  }
                     }
                  }
                                }
//                                else
                                    //System.out.println("****BBextra Log****:Detect enable home funtion but not home screen");
                      }
                      else {
                        ApplicationManager.getApplicationManager().lockSystem(true);
                              try {
                                  ApplicationManager.getApplicationManager().launch("net_rim_bb_ribbon_app");
                               } catch (ApplicationManagerException ex) {
                                   ex.printStackTrace();
                               }

                                        // hold A to lock
                                    try {
                                        Thread.sleep(500);
                                    } catch (InterruptedException ex) {
                                          }
            EventInjector.invokeEvent(new EventInjector.KeyCodeEvent(KeyCodeEvent.KEY_DOWN,
                    Characters.LATIN_CAPITAL_LETTER_A,0));
            EventInjector.invokeEvent(new EventInjector.KeyCodeEvent(KeyCodeEvent.KEY_REPEAT,
                    Characters.LATIN_CAPITAL_LETTER_A,0));
                                //System.out.println("****BBextra Log****:keyrepeat");
                                    try {
                                        Thread.sleep(2000);
                                    } catch (InterruptedException ex) {
                                          }
            EventInjector.invokeEvent(new EventInjector.KeyCodeEvent(KeyCodeEvent.KEY_UP,
                    Characters.LATIN_CAPITAL_LETTER_A,0));
                                // hold A to lock

           store4 = PersistentStore.getPersistentObject (0xf4b763d73d7464f6L);

                  synchronized (store4) {
                      //System.out.println("****BBextra Log****:play sound enable");
                     if (store4.getContents()!=null && store4.getContents().toString().equalsIgnoreCase("Enable")){
                         store4 = PersistentStore.getPersistentObject (0xe63a9bdc93bfe01aL);
                  synchronized (store4) {
                      if (store4.getContents() != null){
                          //System.out.println("****BBextra Log****:check the sound");
                        song = (String) store4.getContents();
                        try {
                            p = Manager.createPlayer("file:///"+song);
                            p.start();
                         } catch (MediaException pe) {
                             //System.out.println("****BBextra Log****:Can not play this alert-ring!");
                         } catch (IOException ioe) {
                             //System.out.println("****BBextra Log****:Can not find alert-ring file!");
                         }
                     }
                  }
                     }
                  }
                      }
                                }
//                                //////////////////////
//                                //   standby mode  //
//                                /////////////////////
//                                store4 = PersistentStore.getPersistentObject (0x41bc68ef0f06beaaL);
//                                synchronized (store4) {
//                      if (store4.getContents() == "Enable"){
//                             try {
//                                        Thread.sleep(5000);
//                                    } catch (InterruptedException ex) {
//                                          }
//                          char mute = (char) Keypad.key(Keypad.KEY_SPEAKERPHONE);
//                 EventInjector.invokeEvent(new EventInjector.KeyCodeEvent(EventInjector.KeyCodeEvent.KEY_DOWN, mute, 0));
//                           System.out.println("****BBextra Log****: down mute");
//            EventInjector.invokeEvent(new EventInjector.KeyCodeEvent(EventInjector.KeyCodeEvent.KEY_REPEAT, mute, 0));
//                                    try {
//                                        Thread.sleep(5000);
//                                    } catch (InterruptedException ex) {
//                                          }
//            EventInjector.invokeEvent(new EventInjector.KeyCodeEvent(EventInjector.KeyCodeEvent.KEY_UP, mute, 0));
//                          System.out.println("****BBextra Log****:up mute");
//                      }
//                      }
//

                            }
                        }
                         }
                     }
                }

                c1.setTime(new Date(System.currentTimeMillis()));
                nowhour = c1.get(Calendar.HOUR_OF_DAY);
                nowmin = c1.get(Calendar.MINUTE);
                nowday = c1.get(Calendar.DAY_OF_WEEK);
                //System.out.println("****BBextra Log****:Today is "+nowday);
                /////////////////////
                //    Hourly Alarm //
                /////////////////////
                //System.out.println("****BBextra Log****:Now minutes is "+nowmin);
                if(nowmin == 0) {
                store4 = PersistentStore.getPersistentObject (0x7897a365635c5a6aL);
                synchronized (store4) {
                     if (store4.getContents() != null & BBextra.inthetime){
                        song = (String) store4.getContents();
                        try {
                            p = Manager.createPlayer("file:///"+song);
                            p.start();
                         } catch (MediaException pe) {
                         } catch (IOException ioe) {
                         }
                     }
                }
                }
                ////////////////////
                //  clock 1       //
                ////////////////////
                timealarm = false;
                store4 = PersistentStore.getPersistentObject (0x7847782406658f55L);
                synchronized (store4) {
                     if (store4.getContents()!=null && store4.getContents().toString().equalsIgnoreCase("Enable") )
                     {
                         //System.out.println("****BBextra Log****:Detect clock 1 enable");
                      if (nowday == 1) {
                            store4 = PersistentStore.getPersistentObject (0xd60bae6df03f5a34L);
                            synchronized (store4) {
                            if (store4.getContents()!=null && store4.getContents().toString().equalsIgnoreCase("true"))
                                timealarm = true;
                            }
                      }
                      else if (nowday == 2) {
                            store4 = PersistentStore.getPersistentObject (0xa85576eaeaf1ef20L);
                            synchronized (store4) {
                            if (store4.getContents()!=null && store4.getContents().toString().equalsIgnoreCase("true"))
                                timealarm = true;
                            }
                      }
                      else if (nowday == 3) {
                            store4 = PersistentStore.getPersistentObject (0x8d49121cb7684695L);
                            synchronized (store4) {
                            if (store4.getContents()!=null && store4.getContents().toString().equalsIgnoreCase("true"))
                            {
                                timealarm = true;
                            }
                            }
                      }
                      else if (nowday == 4) {
                            store4 = PersistentStore.getPersistentObject (0xc39425bf786480dfL);
                            synchronized (store4) {
                            if (store4.getContents()!=null && store4.getContents().toString().equalsIgnoreCase("true"))
                                timealarm = true;
                            }
                      }
                      else if (nowday == 5) {
                            store4 = PersistentStore.getPersistentObject (0x26dd5c21153ea8b7L);
                            synchronized (store4) {
                            if (store4.getContents()!=null && store4.getContents().toString().equalsIgnoreCase("true"))
                                timealarm = true;
                            }
                      }
                      else if (nowday == 6) {
                            store4 = PersistentStore.getPersistentObject (0xb4e00b28758a7525L);
                            synchronized (store4) {
                            if (store4.getContents()!=null && store4.getContents().toString().equalsIgnoreCase("true"))
                                timealarm = true;
                            }
                      }
                      else if (nowday == 7) {
                            store4 = PersistentStore.getPersistentObject (0x882a5b70fe4f00L);
                            synchronized (store4) {
                            if (store4.getContents()!=null && store4.getContents().toString().equalsIgnoreCase("true"))
                                timealarm = true;
                            }
                      }
                if(timealarm) {
                    //System.out.println("****BBextra Log****:clock can run in the time");
                store4 = PersistentStore.getPersistentObject (0x20c5f6d1336be7b6L);
                synchronized (store4) {
                     if (store4.getContents() != null) {
                        Date fromdate = new Date(Long.parseLong(store4.getContents().toString()));
                        c1.setTime(fromdate);
                        hour = c1.get(Calendar.HOUR_OF_DAY);
                        min = c1.get(Calendar.MINUTE);
                     }
                     else{
                        hour = 0;
                        min = 0;
                     }
                }
                if (((nowhour == hour) & (nowmin == min))|(nowhour == tmphour1)&(nowmin == tmpmin1)){
                    //System.out.println("****BBextra Log****:start to play clock1");
               store4 = PersistentStore.getPersistentObject (0x5559ad9e5ba41f57L);
                  synchronized (store4) {
                     if (store4.getContents() != null) {
                        song = (String) store4.getContents();
                         //System.out.println("****BBextra Log****:Clock 1 song path="+song);
                            boolean error = false;
                        try {
                            p = Manager.createPlayer("file:///"+song);
                            p.start();
                         } catch (MediaException pe) {
                             error = true;
                         } catch (IOException ioe) {
                             error = true;
                         }
                ////////////////////////////
                // led disco when alert //
                ////////////////////////////
                store4 = PersistentStore.getPersistentObject (0x4edda4e19d069233L);
                    synchronized (store4) {
                        if(store4.getContents()!=null && store4.getContents().toString().equalsIgnoreCase("Enable")) {
                            Timer time1 = new Timer();
                            globel.incolorled=true;
                            time1.scheduleAtFixedRate(new TimerLED(),500,300);
                        }
                    }
                         store4 = PersistentStore.getPersistentObject (0x1e712e2d2fbc8a56L);
                         synchronized (store4) {
                                if (store4.getContents()!=null && store4.getContents().toString().equalsIgnoreCase("Enable")){
                                                    try {
                                                        autounlock();
                                                    } catch (InterruptedException ex) {
                                                        ex.printStackTrace();
                                                    }
                            UiApplication.getUiApplication().pushGlobalScreen(new snoozscreen(1,p,error),3,GLOBAL_QUEUE);
                                }
                                else
                                     UiApplication.getUiApplication().pushScreen(new snoozscreen(1,p,error));
                         }
                     }
                  }
                }
                }
                     }
                }
                ////////////////////
                //  clock 2      //
                ////////////////////
                timealarm = false;
                store4 = PersistentStore.getPersistentObject (0x9e9a51a9c53906f7L);
                synchronized (store4) {
                     if (store4.getContents()!=null && store4.getContents().toString().equalsIgnoreCase("Enable"))
                     {
                      if (nowday == 1) {
                            store4 = PersistentStore.getPersistentObject (0xefb2f40e7ca8157fL);
                            synchronized (store4) {
                            if (store4.getContents()!=null && store4.getContents().toString().equalsIgnoreCase("true"))
                                timealarm = true;
                            }
                      }
                      else if (nowday == 2) {
                            store4 = PersistentStore.getPersistentObject (0xa1c8b5fbbca891f5L);
                            synchronized (store4) {
                            if (store4.getContents()!=null && store4.getContents().toString().equalsIgnoreCase("true"))
                                timealarm = true;
                            }
                      }
                      else if (nowday == 3) {
                            store4 = PersistentStore.getPersistentObject (0x641488d388cfa1ecL);
                            synchronized (store4) {
                            if (store4.getContents()!=null && store4.getContents().toString().equalsIgnoreCase("true"))
                                timealarm = true;
                            }
                      }
                      else if (nowday == 4) {
                            store4 = PersistentStore.getPersistentObject (0xaec09de4d1ec3f4eL);
                            synchronized (store4) {
                            if (store4.getContents()!=null && store4.getContents().toString().equalsIgnoreCase("true"))
                                timealarm = true;
                            }
                      }
                      else if (nowday == 5) {
                            store4 = PersistentStore.getPersistentObject (0x23cc435034469c9fL);
                            synchronized (store4) {
                            if (store4.getContents()!=null && store4.getContents().toString().equalsIgnoreCase("true"))
                                timealarm = true;
                            }
                      }
                      else if (nowday == 6) {
                            store4 = PersistentStore.getPersistentObject (0x2692d94e6114eac5L);
                            synchronized (store4) {
                            if (store4.getContents()!=null && store4.getContents().toString().equalsIgnoreCase("true"))
                                timealarm = true;
                            }
                      }
                      else if (nowday == 7) {
                            store4 = PersistentStore.getPersistentObject (0xef2a91f63529defbL);
                            synchronized (store4) {
                            if (store4.getContents()!=null && store4.getContents().toString().equalsIgnoreCase("true"))
                                timealarm = true;
                            }
                      }
                        if(timealarm) {
                            store4 = PersistentStore.getPersistentObject (0x182b063c5c35aa1fL);
                            synchronized (store4) {
                     if (store4.getContents() != null) {
                        Date fromdate = new Date(Long.parseLong(store4.getContents().toString()));
                        c1.setTime(fromdate);
                        hour = c1.get(Calendar.HOUR_OF_DAY);
                        min = c1.get(Calendar.MINUTE);
                     }
                     else{
                        hour = 0;
                        min = 0;
                     }
                }
                    if (((nowhour == hour) & (nowmin == min))|(nowhour == tmphour2)&(nowmin == tmpmin2)){
                        store4 = PersistentStore.getPersistentObject (0x836c7b22b1180985L);
                        synchronized (store4) {
                            if (store4.getContents() != null) {
                                song = (String) store4.getContents();
                                      boolean error = false;
                                 try {
                                        p = Manager.createPlayer("file:///"+song);
                                         p.start();
                                        } catch (MediaException pe) {
                                            error = true;
                                         } catch (IOException ioe) {
                                             error = true;
                                     }
                 ////////////////////////////
                // led disco when alert //
                ////////////////////////////
                store4 = PersistentStore.getPersistentObject (0x4edda4e19d069233L);
                    synchronized (store4) {
                        if(store4.getContents()!=null && store4.getContents().toString().equalsIgnoreCase("Enable")) {
                            Timer time1 = new Timer();
                            globel.incolorled=true;
                            time1.scheduleAtFixedRate(new TimerLED(),500,300);
                        }
                    }
           store4 = PersistentStore.getPersistentObject (0x1e712e2d2fbc8a56L);
                         synchronized (store4) {
                                if (store4.getContents()!=null && store4.getContents().toString().equalsIgnoreCase("Enable")){
                                    try {
                                                        autounlock();
                                                    } catch (InterruptedException ex) {
                                                        ex.printStackTrace();
                                                    }
                            UiApplication.getUiApplication().pushGlobalScreen(new snoozscreen(2,p,error),3,GLOBAL_QUEUE);
                                }
                                else
                                     UiApplication.getUiApplication().pushScreen(new snoozscreen(2,p,error));
                         }

                     }
                  }
                }
                    }
                     }
                }
                ////////////////////
                //  clock 3      //
                ////////////////////
                timealarm = false;
                store4 = PersistentStore.getPersistentObject (0x4e30030f1ea96be1L);
                synchronized (store4) {
                     if (store4.getContents()!=null && store4.getContents().toString().equalsIgnoreCase("Enable"))
                     {
                            if (nowday == 1) {
                                store4 = PersistentStore.getPersistentObject (0x4592bdf7f3feaaa0L);
                                synchronized (store4) {
                                    if(store4.getContents()!=null && store4.getContents().toString().equalsIgnoreCase("true"))
                                        timealarm = true;
                                 }
                           }
                            else if (nowday == 2) {
                                store4 = PersistentStore.getPersistentObject (0xce1472c295472e87L);
                                synchronized (store4) {
                                      if(store4.getContents()!=null && store4.getContents().toString().equalsIgnoreCase("true"))
                                        timealarm = true;
                                }
                            }
                            else if (nowday == 3) {
                            store4 = PersistentStore.getPersistentObject (0xe952454c385f2d60L);
                            synchronized (store4) {
                              if(store4.getContents()!=null && store4.getContents().toString().equalsIgnoreCase("true"))
                                timealarm = true;
                            }
                      }
                      else if (nowday == 4) {
                            store4 = PersistentStore.getPersistentObject (0x73fe9c32146ab6e4L);
                            synchronized (store4) {
                             if(store4.getContents()!=null && store4.getContents().toString().equalsIgnoreCase("true"))
                                timealarm = true;
                            }
                      }
                      else if (nowday == 5) {
                            store4 = PersistentStore.getPersistentObject (0xf281b213404da0daL);
                            synchronized (store4) {
                              if(store4.getContents()!=null && store4.getContents().toString().equalsIgnoreCase("true"))
                                timealarm = true;
                            }
                      }
                      else if (nowday == 6) {
                            store4 = PersistentStore.getPersistentObject (0x8e8bd8b20e6da5eL);
                            synchronized (store4) {
                              if(store4.getContents()!=null && store4.getContents().toString().equalsIgnoreCase("true"))
                                timealarm = true;
                            }
                      }
                      else if (nowday == 7) {
                            store4 = PersistentStore.getPersistentObject (0x1c8f91eb76a13d0bL);
                            synchronized (store4) {
                              if(store4.getContents()!=null && store4.getContents().toString().equalsIgnoreCase("true"))
                                timealarm = true;
                            }
                      }
                        if(timealarm) {
                            store4 = PersistentStore.getPersistentObject (0xff5d29eae5143093L);
                            synchronized (store4) {
                     if (store4.getContents() != null) {
                        Date fromdate = new Date(Long.parseLong(store4.getContents().toString()));
                        c1.setTime(fromdate);
                        hour = c1.get(Calendar.HOUR_OF_DAY);
                        min = c1.get(Calendar.MINUTE);
                     }
                     else{
                        hour = 0;
                        min = 0;
                     }
                }
                   if (((nowhour == hour) & (nowmin == min))|(nowhour == tmphour3)&(nowmin == tmpmin3)){
                        store4 = PersistentStore.getPersistentObject (0x6b9b78bc6bf657daL);
                        synchronized (store4) {
                            if (store4.getContents() != null) {
                                song = (String) store4.getContents();
                                  boolean error = false;
                                 try {
                                        p = Manager.createPlayer("file:///"+song);
                                         p.start();
                                        } catch (MediaException pe) {
                                            error = true;
                                         } catch (IOException ioe) {
                                             error = true;
                                      }
                ////////////////////////////
                // led disco when alert //
                ////////////////////////////
                store4 = PersistentStore.getPersistentObject (0x4edda4e19d069233L);
                    synchronized (store4) {
                        if(store4.getContents()!=null && store4.getContents().toString().equalsIgnoreCase("Enable")){
                            Timer time1 = new Timer();
                            globel.incolorled=true;
                            time1.scheduleAtFixedRate(new TimerLED(),500,300);
                        }
                    }
           store4 = PersistentStore.getPersistentObject (0x1e712e2d2fbc8a56L);
                         synchronized (store4) {
                                if (store4.getContents()!=null && store4.getContents().toString().equalsIgnoreCase("Enable")){
                                    try {
                                                        autounlock();
                                                    } catch (InterruptedException ex) {
                                                        ex.printStackTrace();
                                                    }
                            UiApplication.getUiApplication().pushGlobalScreen(new snoozscreen(3,p,error),3,GLOBAL_QUEUE);
                                }
                                else
                                     UiApplication.getUiApplication().pushScreen(new snoozscreen(3,p,error));
                         }
                   }
                  }
                }
                    }
                     }
                }
                //////////////////
                // switch wifi  //
                //////////////////
                store4 = PersistentStore.getPersistentObject (0x8d115948c9c2c87aL);
                synchronized (store4) {
                     if (store4.getContents()!=null && store4.getContents().toString().equalsIgnoreCase("Enable"))
                     {
                       // get hour & min of from time to turn on wifi
                store4 = PersistentStore.getPersistentObject (0x608a39b43884080eL);
                synchronized (store4) {
                     if (store4.getContents() != null) {
                        Date fromdate = new Date(Long.parseLong(store4.getContents().toString()));
                        c1.setTime(fromdate);
                        hour = c1.get(Calendar.HOUR_OF_DAY);
                        min = c1.get(Calendar.MINUTE);
                     }
                     else{
                        hour = 0;
                        min = 0;
                     }
                }

                if ((nowhour == hour) & (nowmin == min))
                    Radio.activateWAFs(RadioInfo.NETWORK_802_11);
               store4 = PersistentStore.getPersistentObject (0xf1d06a4809a33cdcL);
                synchronized (store4) {
                     if (store4.getContents() != null) {
                        Date fromdate = new Date(Long.parseLong(store4.getContents().toString()));
                        c1.setTime(fromdate);
                        hour = c1.get(Calendar.HOUR_OF_DAY);
                        min = c1.get(Calendar.MINUTE);
                     }
                     else{
                        hour = 0;
                        min = 0;
                     }
                }
                if ((nowhour == hour) & (nowmin == min))
                    Radio.deactivateWAFs(RadioInfo.NETWORK_802_11);
                     }
                }
               ////////////////////////////
               // swithes turn radio off //
               ////////////////////////////
                store4 = PersistentStore.getPersistentObject (0xe62b62a5dd6bbea6L);
                     synchronized (store4) {
                     if (store4.getContents()!=null && store4.getContents().toString().equalsIgnoreCase("Enable"))
                     {
                        // turn on
                     store4 = PersistentStore.getPersistentObject (0xfdb812bfc68f72e3L);
                     synchronized (store4) {
                     if (store4.getContents() != null) {
                        Date fromdate = new Date(Long.parseLong(store4.getContents().toString()));
                        c1.setTime(fromdate);
                        hour = c1.get(Calendar.HOUR_OF_DAY);
                        min = c1.get(Calendar.MINUTE);
                     }
                     else{
                        hour = 0;
                        min = 0;
                     }
                }
                if ((nowhour == hour) & (nowmin == min)){
                    Radio.activateWAFs(RadioInfo.WAF_3GPP);
                    Radio.activateWAFs(RadioInfo.WAF_CDMA);
                }
                     //turn off
                     store4 = PersistentStore.getPersistentObject (0xed24038a3dfee82bL);
                     synchronized (store4) {
                     if (store4.getContents() != null) {
                        Date fromdate = new Date(Long.parseLong(store4.getContents().toString()));
                        c1.setTime(fromdate);
                        hour = c1.get(Calendar.HOUR_OF_DAY);
                        min = c1.get(Calendar.MINUTE);
                     }
                     else{
                        hour = 0;
                        min = 0;
                     }
                }
                if ((nowhour == hour) & (nowmin == min)){
                    Radio.deactivateWAFs(RadioInfo.WAF_3GPP);
                    Radio.deactivateWAFs(RadioInfo.WAF_CDMA);
                }
                     }
                }

            }

       };
       theApp.addRealtimeClockListener(timelistener);

        }

        theApp.enterEventDispatcher();
    }
public static boolean islock() {
   if(ApplicationManager.getApplicationManager().isSystemLocked())
          return true;
   else {
         ApplicationManager manager = ApplicationManager.getApplicationManager();
         ApplicationDescriptor[] appDes = manager.getVisibleApplications();
         if(appDes[0].getName().equalsIgnoreCase("secruity"))
                  return true;
         return false;
         }
}
public static void checksystemlistener(){
     //System.out.println("****BBextra Log****:start to systemlistener");
       SystemListener2 systemlisten = new SystemListener2() {
           Player p;
            private int fromh,toh,fromm,tom,hour,min;
            public void powerOff() {
            }

            public void powerUp() {
            }

            public void batteryLow() {
            }

            public void batteryGood() {
            }

            public void batteryStatusChange(int status) {
                    Graphics g = null;
                    /////////////////////////////////////////////////
                    // display level on icon. //
                    /////////////////////////////////////////////////
                    PersistentObject store;
                    store = PersistentStore.getPersistentObject (0xdd4cdf439a9307d9L);
                    synchronized (store) {
                        if (store.getContents()!=null && store.getContents().toString().equalsIgnoreCase("Enable")){
                           String tmp = String.valueOf(DeviceInfo.getBatteryLevel());
                           Font font = null;
                           if (DeviceInfo.getDeviceName().startsWith("8") &&! DeviceInfo.getDeviceName().startsWith("89"))
                               font = Font.getDefault().derive(Font.BOLD,9,Ui.UNITS_pt);
                           else
                               font = Font.getDefault().derive(Font.BOLD,5,Ui.UNITS_pt);
                           Bitmap tmpbit = null ;

                           if(DeviceInfo.getBatteryLevel()<20){
                               Bitmap myBitmap2 = Bitmap.getBitmapResource("extra20.png");
                               tmpbit = myBitmap2;
                            }
                           else if(DeviceInfo.getBatteryLevel()<40){
                               Bitmap myBitmap4 = Bitmap.getBitmapResource("extra40.png");
                               tmpbit = myBitmap4;
                            }
                           else if(DeviceInfo.getBatteryLevel()<60){
                               Bitmap myBitmap6 = Bitmap.getBitmapResource("extra60.png");
                               tmpbit = myBitmap6;
                            }
                           else if(DeviceInfo.getBatteryLevel()<80){
                               Bitmap myBitmap8 = Bitmap.getBitmapResource("extra80.png");
                               tmpbit = myBitmap8;
                            }
                           else
                           {
                               Bitmap myBitmap10 = Bitmap.getBitmapResource("extra100.png");
                               tmpbit = myBitmap10;
                           }
                               g = new Graphics(tmpbit);
                               g.setFont(font);
                               g.setColor(Color.WHITE);
                               if(DeviceInfo.getBatteryLevel()<100) {
                                    g.drawText(tmp+"%",25, 20);
                               }
                               else
                                    g.drawText("Max",25, 20);
                               HomeScreen.updateIcon(tmpbit);
                     }
                    }


                if (status >=1073741824) {
                    status = status - 1073741824;
                    //System.out.println("****BBextra Log****:Battery is too cold");
                }
                if (status >=536870912) {
                    status = status - 536870912;
                    //System.out.println("****BBextra Log****:Battery is too hot");
                }
                if (status >=268435456) {
                    status = status - 268435456;
                    //System.out.println("****BBextra Log****:Battery is too low");
                }
                if (status >=8388608) {
                    status = status - 8388608;
                    //System.out.println("****BBextra Log****:No battery");
                }
                if (status >=4194304) {
                    status = status - 4194304;
                    //System.out.println("****BBextra Log****:Battery is reversed");
                }
                if (status >=32768) {
                    status = status - 32768;
                    //System.out.println("****BBextra Log****:Battery is too low to turn on");
                }
                if (status >=16384) {
                    status = status - 16384;
                    //System.out.println("****BBextra Log****:Battery is too low to radio on");
                }
                if (status >=8192) {
                    status = status - 8192;
                    //System.out.println("****BBextra Log****:Battery is too low for camera flash usage");
                }
                if (status >=4096) {
                    status = status - 4096;
                    //System.out.println("****BBextra Log****:Battery is too low to open wifi");
                }
                if (status >=16) {
                    status = status - 16;
                    //System.out.println("****BBextra Log****:Battery is AC contect");
                }
                if (status >=8) {
                    status = status - 8;
                    //System.out.println("****BBextra Log****:Battery is in low rate charing");
                }
                if (status >=4) {
                    String song;
                if (BBextra.inthetime == true){
                    PersistentObject store4;
                store4 = PersistentStore.getPersistentObject (0xbbf87d7fc545afe9L);
                song = (String) store4.getContents();
                    //System.out.println("****BBextra Log****:Charging finish song path="+song);
                store4 = PersistentStore.getPersistentObject (0x94d16aab37867123L);
                if (DeviceInfo.getBatteryLevel()==100) {
                    //System.out.println("****BBextra Log****:Battery is 100%");
                synchronized (store4) {
                        if (store4.getContents()!=null && store4.getContents().toString().equalsIgnoreCase("Enable")){
                            //System.out.println("****BBextra Log****:Try to play music");
                            boolean error = false;
                        try {
                            p = Manager.createPlayer("file:///"+song);
                            p.start();
                         } catch (MediaException pe) {
                             //System.out.println("****BBextra Log****:Can not play charging song");
                             error = true;
                         } catch (IOException ioe) {
                             //System.out.println("****BBextra Log****:Can not find charging song");
                             error = true;
                         }
                        Alert.startVibrate(12000);
                        UiApplication.getUiApplication().pushGlobalScreen(new charging(p,error),3,GLOBAL_QUEUE);

                        }

                    }
                }
                }
                    status = status - 4;
                    //System.out.println("****BBextra Log****:Batter is using external power");
                }
                if (status >=2) {
                    status = status - 2;
                }
                if (status >=1) {
                    //System.out.println("****BBextra Log****:incharging ="+incharing);
                    store = PersistentStore.getPersistentObject (0xdd4cdf439a9307d9L);
                    synchronized (store) {
                        if (store.getContents()!=null && store.getContents().toString().equalsIgnoreCase("Enable")){
                            String tmp = String.valueOf(DeviceInfo.getBatteryLevel());
                           Font font = null;
                           if (DeviceInfo.getDeviceName().startsWith("8") &&! DeviceInfo.getDeviceName().startsWith("89"))
                               font = Font.getDefault().derive(Font.BOLD,9,Ui.UNITS_pt);
                           else
                               font = Font.getDefault().derive(Font.BOLD,5,Ui.UNITS_pt);
                           Bitmap tmpbit = null ;

                           if(DeviceInfo.getBatteryLevel()<20){
                               Bitmap myBitmap2 = Bitmap.getBitmapResource("extra20.png");
                               tmpbit = myBitmap2;
                            }
                           else if(DeviceInfo.getBatteryLevel()<40){
                               Bitmap myBitmap4 = Bitmap.getBitmapResource("extra40.png");
                               tmpbit = myBitmap4;
                            }
                           else if(DeviceInfo.getBatteryLevel()<60){
                               Bitmap myBitmap6 = Bitmap.getBitmapResource("extra60.png");
                               tmpbit = myBitmap6;
                            }
                           else if(DeviceInfo.getBatteryLevel()<80){
                               Bitmap myBitmap8 = Bitmap.getBitmapResource("extra80.png");
                               tmpbit = myBitmap8;
                            }
                           else
                           {
                               Bitmap myBitmap10 = Bitmap.getBitmapResource("extra100.png");
                               tmpbit = myBitmap10;
                           }
                               g = new Graphics(tmpbit);
                               g.setFont(font);
                               g.setColor(Color.WHITE);
                               g.drawBitmap(25, 18, 30, 30, flashbit, 0, 0);
                               if(DeviceInfo.getBatteryLevel()<100)
                                    g.drawText(tmp+"%",25, 20);
                               else
                                    g.drawText("Max",25, 20);
                               HomeScreen.updateIcon(tmpbit);
                        }
                    }
                    if(!incharing) {
                    /////////////////////////////////////////////////
                    // swithes turn on dispay image when charging. //
                    /////////////////////////////////////////////////
                    store = PersistentStore.getPersistentObject (0x42f7439cfea21673L);
                    synchronized (store) {
                        if (store.getContents()!=null && store.getContents().toString().equalsIgnoreCase("Enable")){
                    incharing = true;
                            //System.out.println("****BBextra Log****:Battery is in charging");
                    UiApplication.getUiApplication().pushGlobalScreen(new jpgScreen(),2,GLOBAL_SHOW_LOWER);

                        }
                     }
                    }

                }
                if (status <=0 & incharing) {
                    incharing = false;
                    //System.out.println("****BBextra Log****:Try to close charing picture");
                    UiApplication.getUiApplication().popScreen(jpgScreen.tmpScreen);
                }
            }

            public void powerOffRequested(int reason) {
            }

            public void cradleMismatch(boolean mismatch) {
            }

            public void fastReset() {
            }

            public void backlightStateChange(boolean on) {
            }

            public void usbConnectionStateChange(int state) {
                if(state == USB_STATE_CABLE_CONNECTED)
                    globel.usbmode = true;
                if(state == USB_STATE_CABLE_DISCONNECTED)
                    globel.usbmode = false;
            }
        };
       theApp.addSystemListener(systemlisten);
}
public static void NewMailArrived(Message email)
{
    String msg,title,sender,tmp = null;
    PersistentObject store3,store4;
    store3 = PersistentStore.getPersistentObject (0x8a839593ae4ec8ecL);
    store4 = PersistentStore.getPersistentObject (0xf4224d9ff8b6f205L);
    final Date time = new Date(System.currentTimeMillis());
    try {
         tmp = email.getFrom().toString();
         if(tmp.substring(0,7).equalsIgnoreCase("mailto:"))
         tmp = tmp.substring(7);
         }
    catch (Throwable ex) {
          //System.out.println("****BBextra Log****:Email address error"+ex.toString());
         tmp ="error address";
    }
    sender = tmp ;
    msg = email.getBodyText();
    title= email.getSubject();
    mytext sms = null;
    synchronized (store3) {
        if (!checksender(sender)
                && store3.getContents()!=null
                && !store3.getContents().toString().equalsIgnoreCase("Default") ){
            Timer time4 = new Timer();
            globel.emailled = true;
            time4.scheduleAtFixedRate(new TimerMainEvent(),1000,300);
        }
    }
    if (store4.getContents()!=null && store4.getContents().toString().equalsIgnoreCase("Enable")) {
          //System.out.println("****BBextra Log****:start to push alertscreen");
          Ui.getUiEngine().pushGlobalScreen(new alertScreen(msg,sender,time,title,email,sms), 1, 3);
    }

 }

    private static void addmenu() {
        //System.out.println("****BBextra Log****:start to register menu");
        //send phone number to blacklist
        PersistentObject store;
        ApplicationMenuItemRepository repository = ApplicationMenuItemRepository.getInstance();
        repository.addMenuItem(ApplicationMenuItemRepository.MENUITEM_ADDRESSCARD_VIEW,new custommenu());
        repository.addMenuItem(ApplicationMenuItemRepository.MENUITEM_PHONE,new custommenu2());
        repository.addMenuItem(ApplicationMenuItemRepository.MENUITEM_ADDRESSCARD_VIEW,new custommenu4());
        repository.addMenuItem(ApplicationMenuItemRepository.MENUITEM_PHONE,new custommenu5());
        repository.addMenuItem(ApplicationMenuItemRepository.MENUITEM_SMS_EDIT,new custommenu6());
        repository.addMenuItem(ApplicationMenuItemRepository.MENUITEM_SMS_VIEW,new custommenu6());
        repository.addMenuItem(ApplicationMenuItemRepository.MENUITEM_EMAIL_EDIT,new custommenu6());


        store = PersistentStore.getPersistentObject(0x80e0d842a0e9fe05L);
        synchronized (store) {
            if (store.getContents() != null && !store.getContents().toString().equalsIgnoreCase("")) {
                    RuntimeStore rs = RuntimeStore.getRuntimeStore();
                    if(rs.get(0x3f24e678ca662c01L) == null) {
                        custommenu3 menu3 = new custommenu3();
                        repository.addMenuItem(ApplicationMenuItemRepository.MENUITEM_SMS_EDIT,menu3);
                        rs.put(0x3f24e678ca662c01L, menu3);
                }
            }
        }
        store = PersistentStore.getPersistentObject(0x565c4a4f2d148a95L);
        if(store.getContents()!= null) {
        if(store.getContents().toString().equalsIgnoreCase("Enable") )
            repository.addMenuItem(ApplicationMenuItemRepository.MENUITEM_SYSTEM,new flashlight());
        }
        boolean hassdcard = false;
        try {
            FileConnection fc = (FileConnection) Connector.open("file:///SDCard/BBextra/", Connector.READ_WRITE);
            if(fc.exists()){
                hassdcard = true;
                System.out.println("****BBextra Log****:folder exsit="+hassdcard);
            }
            else if (!fc.exists()) {
                fc.mkdir();
                hassdcard = true;
                System.out.println("****BBextra Log****:create folder="+hassdcard);
            }
         } catch (Exception e) {
                hassdcard = false;
                System.out.println("****BBextra Log****:throw error");
         }
        if(hassdcard) {
            System.out.println("****BBextra Log****:final hascard="+hassdcard);
            repository.addMenuItem(ApplicationMenuItemRepository.MENUITEM_SYSTEM,new screenshotmenu());
        }
    }

    public static void autounlock() throws InterruptedException {
         try {
              ApplicationManager.getApplicationManager().launch("net_rim_bb_ribbon_app");
         } catch (ApplicationManagerException ex) {
              ex.printStackTrace();
         }
        if(DeviceInfo.getSoftwareVersion().startsWith("4."))
            ApplicationManager.getApplicationManager().lockSystem(true);
        else {
           EventInjector.invokeEvent(new EventInjector.KeyCodeEvent(KeyCodeEvent.KEY_DOWN,(char)Keypad.KEY_ESCAPE,0));
           EventInjector.invokeEvent(new EventInjector.KeyCodeEvent(KeyCodeEvent.KEY_DOWN,(char)Keypad.KEY_ESCAPE,0));
           EventInjector.invokeEvent(new EventInjector.KeyCodeEvent(KeyCodeEvent.KEY_DOWN,(char)Keypad.KEY_ESCAPE,0));
           EventInjector.invokeEvent(new EventInjector.KeyCodeEvent(KeyCodeEvent.KEY_DOWN,(char)Keypad.KEY_ESCAPE,0));
           
 // hold A to lock

            EventInjector.invokeEvent(new EventInjector.KeyCodeEvent(KeyCodeEvent.KEY_DOWN,
                    Characters.LATIN_CAPITAL_LETTER_A,0));
            EventInjector.invokeEvent(new EventInjector.KeyCodeEvent(KeyCodeEvent.KEY_REPEAT,
                    Characters.LATIN_CAPITAL_LETTER_A,0));
            //System.out.println("****BBextra Log****:keyrepeat");
            try {
                 Thread.sleep(2000);
                 }
            catch (InterruptedException ex)
                 {
                 }
            EventInjector.invokeEvent(new EventInjector.KeyCodeEvent(KeyCodeEvent.KEY_UP,
                    Characters.LATIN_CAPITAL_LETTER_A,0));
                                // hold A to lock
        }
        Thread.sleep(200);
        if(DeviceInfo.getSoftwareVersion().startsWith("5.")) {
        EventInjector.invokeEvent(new EventInjector.KeyCodeEvent(KeyCodeEvent.KEY_DOWN,(char)4099,0));
        EventInjector.invokeEvent(new EventInjector.KeyCodeEvent(KeyCodeEvent.KEY_UP,(char)4099,0));
        EventInjector.invokeEvent(new EventInjector.KeyCodeEvent(KeyCodeEvent.KEY_DOWN,(char)273,0));
        EventInjector.invokeEvent(new EventInjector.KeyCodeEvent(KeyCodeEvent.KEY_UP,(char)273,0));
        }
        else {
        EventInjector.invokeEvent(new EventInjector.KeyCodeEvent(KeyCodeEvent.KEY_DOWN,Characters.LATIN_CAPITAL_LETTER_A,0));
        EventInjector.invokeEvent(new EventInjector.KeyCodeEvent(KeyCodeEvent.KEY_DOWN,(char)Keypad.KEY_SEND,0));
        }
    }

    public static boolean checksender(String sender) {
        PersistentObject store;
        String from,a = null;
        int i;
        try {
           Thread.sleep(1000);
        } catch (InterruptedException ex) {
           //System.out.println("****BBextra Log****:SMS led error="+ex.toString());
        }
        store = PersistentStore.getPersistentObject (0x98b2e4273ded5d69L);
        if(store.getContents()!=null && !store.getContents().toString().equalsIgnoreCase("")) {
            from = store.getContents().toString();
            store = PersistentStore.getPersistentObject (0x7b866b9679171a30L);
            if(store.getContents() == null)
                a = "FFFFFF";
            else
                a = store.getContents().toString();
            i = from.indexOf(",");
            while (i != -1){
                if(sender.endsWith(from.substring(0,i))){
                    LED.setColorConfiguration(200, 200, Integer.parseInt(a,16));
                    return true;
                }
                from = from.substring(i+1);
                i = from.indexOf(",");
            }
            if(i<0 && sender.endsWith(from)) {
                LED.setColorConfiguration(200, 200, Integer.parseInt(a,16));
                return true;
            }
        }
        store = PersistentStore.getPersistentObject (0x3de87c7fdc41155L);
        if(store.getContents()!=null && !store.getContents().toString().equalsIgnoreCase("")) {
            from = store.getContents().toString();
            store = PersistentStore.getPersistentObject (0xd6aa329edde0ac8aL);
            if(store.getContents() == null)
                a = "FFFFFF";
            else
                a = store.getContents().toString();
            i = from.indexOf(",");
            while (i != -1){
                if(sender.endsWith(from.substring(0,i))){
                    LED.setColorConfiguration(200, 200, Integer.parseInt(a,16));
                    return true;
                }
                from = from.substring(i+1);
                i = from.indexOf(",");
            }
            if(i<0 && sender.endsWith(from)) {
                LED.setColorConfiguration(200, 200, Integer.parseInt(a,16));
                return true;
            }
        }
        store = PersistentStore.getPersistentObject (0x6f7258f00ca7b656L);
        if(store.getContents()!=null && !store.getContents().toString().equalsIgnoreCase("")) {
            from = store.getContents().toString();
            store = PersistentStore.getPersistentObject (0xcd12018b3dcee492L);
            if(store.getContents() == null)
                a = "FFFFFF";
            else
                a = store.getContents().toString();
            i = from.indexOf(",");
            while (i != -1){
                if(sender.endsWith(from.substring(0,i))){
                    LED.setColorConfiguration(200, 200, Integer.parseInt(a,16));
                    return true;
                }
                from = from.substring(i+1);
                i = from.indexOf(",");
            }
            if(i<0 && sender.endsWith(from)) {
                LED.setColorConfiguration(200, 200, Integer.parseInt(a,16));
                return true;
            }
        }
        return false;
    }

        public BBextra(){
//            for bbextra not sc
      FolderListenerApp.waitForSingleton().setFolderListenerGUI(this);
           pushScreen(new mainscreen());
       }
                //////////////////////////
                //   switch schedule     // net_rim_bb_profiles_app
                ///////////////////////////
public static void checkschedule() throws InterruptedException{
    PersistentObject store;
    int shour,smin,nowour,nowmin;
    Calendar c1 = Calendar.getInstance();
    c1.setTime(new Date(System.currentTimeMillis()));
    nowour = c1.get(Calendar.HOUR_OF_DAY);
    nowmin = c1.get(Calendar.MINUTE);
    store = PersistentStore.getPersistentObject (0x760c2b022b02f81fL);
    synchronized (store) {
        if (store.getContents()!=null && store.getContents().toString().equalsIgnoreCase("Enable")){
            store = PersistentStore.getPersistentObject (0xc71f14aefa7f191L);
            synchronized (store) {
                if (store.getContents() != null) {
                    Date fromdate = new Date(Long.parseLong(store.getContents().toString()));
                    c1.setTime(fromdate);
                    shour = c1.get(Calendar.HOUR_OF_DAY);
                    smin = c1.get(Calendar.MINUTE);
                }
                else{
                    shour = 99;
                    smin = 99;
                }
            }
            if(shour == nowour && smin == nowmin) {
                store = PersistentStore.getPersistentObject (0x3359d1d21ab299e2L);
                if (store.getContents() != null) {
                    String abc = (String) store.getContents();
                        startswitch(abc);
                }
            }
            store = PersistentStore.getPersistentObject (0x82b714e0f44c53dfL);
            synchronized (store) {
                if (store.getContents() != null) {
                    Date fromdate = new Date(Long.parseLong(store.getContents().toString()));
                    c1.setTime(fromdate);
                    shour = c1.get(Calendar.HOUR_OF_DAY);
                    smin = c1.get(Calendar.MINUTE);
                }
                else{
                    shour = 99;
                    smin = 99;
                }
            }
            if(shour == nowour && smin == nowmin) {
                store = PersistentStore.getPersistentObject (0x6b2ace76c96a002cL);
                if (store.getContents() != null) {
                    String abc = (String) store.getContents();
                        startswitch(abc);
                }
            }
            store = PersistentStore.getPersistentObject (0x7219d09205062076L);
            synchronized (store) {
                if (store.getContents() != null) {
                    Date fromdate = new Date(Long.parseLong(store.getContents().toString()));
                    c1.setTime(fromdate);
                    shour = c1.get(Calendar.HOUR_OF_DAY);
                    smin = c1.get(Calendar.MINUTE);
                }
                else{
                    shour = 99;
                    smin = 99;
                }
            }
            if(shour == nowour && smin == nowmin) {
                store = PersistentStore.getPersistentObject (0x7379a1ecc0c572bbL);
                if (store.getContents() != null) {
                    String abc = (String) store.getContents();
                        startswitch(abc);
                }
            }
            store = PersistentStore.getPersistentObject (0x1b289b6e0a027daaL);
            synchronized (store) {
                if (store.getContents() != null) {
                    Date fromdate = new Date(Long.parseLong(store.getContents().toString()));
                    c1.setTime(fromdate);
                    shour = c1.get(Calendar.HOUR_OF_DAY);
                    smin = c1.get(Calendar.MINUTE);
                }
                else{
                    shour = 99;
                    smin = 99;
                }
            }
            if(shour == nowour && smin == nowmin) {
                store = PersistentStore.getPersistentObject (0x68fcca44ebc80651L);
                if (store.getContents() != null) {
                    String abc = (String) store.getContents();
                        startswitch(abc);
                }
            }
            store = PersistentStore.getPersistentObject (0xe37875548f344a35L);
            synchronized (store) {
                if (store.getContents() != null) {
                    Date fromdate = new Date(Long.parseLong(store.getContents().toString()));
                    c1.setTime(fromdate);
                    shour = c1.get(Calendar.HOUR_OF_DAY);
                    smin = c1.get(Calendar.MINUTE);
                }
                else{
                    shour = 99;
                    smin = 99;
                }
            }
            if(shour == nowour && smin == nowmin) {
                store = PersistentStore.getPersistentObject (0xa03e229fa21bebfcL);
                if (store.getContents() != null) {
                    String abc = (String) store.getContents();
                        startswitch(abc);
                }
            }
            store = PersistentStore.getPersistentObject (0xfecef8c0f80e7823L);
            synchronized (store) {
                if (store.getContents() != null) {
                    Date fromdate = new Date(Long.parseLong(store.getContents().toString()));
                    c1.setTime(fromdate);
                    shour = c1.get(Calendar.HOUR_OF_DAY);
                    smin = c1.get(Calendar.MINUTE);
                }
                else{
                    shour = 99;
                    smin = 99;
                }
            }
            if(shour == nowour && smin == nowmin) {
                store = PersistentStore.getPersistentObject (0x685d17f90a66b371L);
                if (store.getContents() != null) {
                    String abc = (String) store.getContents();
                        startswitch(abc);
                }
            }
        }
    }
}
//
static void startswitch(String str) throws InterruptedException{
    //268632064
    if(!str.equalsIgnoreCase("Disable")) {
       autounlock();
       int min;
       PersistentObject store;
       store = PersistentStore.getPersistentObject (0xc860c22da74baa63L);
       min = Integer.parseInt(store.getContents().toString());
       if(str.equalsIgnoreCase("Loud")){
        try {
            ApplicationManager.getApplicationManager().launch("net_rim_bb_profiles_app");
         }
        catch (ApplicationManagerException ex)  {
         }
        Thread.sleep(min);
        // go top
        EventInjector.invokeEvent(new EventInjector.NavigationEvent(EventInjector.NavigationEvent.NAVIGATION_MOVEMENT, 0, -6, KeypadListener.STATUS_TRACKWHEEL));
        Thread.sleep(min);
        if(! DeviceInfo.getSoftwareVersion().startsWith("4."))
            EventInjector.invokeEvent(new EventInjector.NavigationEvent(EventInjector.NavigationEvent.NAVIGATION_MOVEMENT, 0, 1, KeypadListener.STATUS_TRACKWHEEL));
        Thread.sleep(min);
        EventInjector.invokeEvent(new EventInjector.KeyCodeEvent(KeyCodeEvent.KEY_DOWN,Characters.ENTER,0));
   }
    else if(str.equalsIgnoreCase("Normal")){
        try {
            ApplicationManager.getApplicationManager().launch("net_rim_bb_profiles_app");
         }
        catch (ApplicationManagerException ex)  {
         }
        Thread.sleep(min);
        // go top
        EventInjector.invokeEvent(new EventInjector.NavigationEvent(EventInjector.NavigationEvent.NAVIGATION_MOVEMENT, 0, -6, KeypadListener.STATUS_TRACKWHEEL));
        Thread.sleep(min);
        if( DeviceInfo.getSoftwareVersion().startsWith("4."))
            EventInjector.invokeEvent(new EventInjector.NavigationEvent(EventInjector.NavigationEvent.NAVIGATION_MOVEMENT, 0, 3, KeypadListener.STATUS_TRACKWHEEL));
        Thread.sleep(min);
        EventInjector.invokeEvent(new EventInjector.KeyCodeEvent(KeyCodeEvent.KEY_DOWN,Characters.ENTER,0));
   }
    else if(str.startsWith("Vibrate")){
        try {
            ApplicationManager.getApplicationManager().launch("net_rim_bb_profiles_app");
         }
        catch (ApplicationManagerException ex)  {
         }
        Thread.sleep(min);
        // go top
        EventInjector.invokeEvent(new EventInjector.NavigationEvent(EventInjector.NavigationEvent.NAVIGATION_MOVEMENT, 0, -6, KeypadListener.STATUS_TRACKWHEEL));
        Thread.sleep(min);
        if( DeviceInfo.getSoftwareVersion().startsWith("4."))
            EventInjector.invokeEvent(new EventInjector.NavigationEvent(EventInjector.NavigationEvent.NAVIGATION_MOVEMENT, 0, 1, KeypadListener.STATUS_TRACKWHEEL));
        else
            EventInjector.invokeEvent(new EventInjector.NavigationEvent(EventInjector.NavigationEvent.NAVIGATION_MOVEMENT, 0, 3, KeypadListener.STATUS_TRACKWHEEL));
        Thread.sleep(min);
        EventInjector.invokeEvent(new EventInjector.KeyCodeEvent(KeyCodeEvent.KEY_DOWN,Characters.ENTER,0));
   }
    else if(str.startsWith("Phone")){
        try {
            ApplicationManager.getApplicationManager().launch("net_rim_bb_profiles_app");
         }
        catch (ApplicationManagerException ex)  {
         }
        Thread.sleep(min);
        // go top
        EventInjector.invokeEvent(new EventInjector.NavigationEvent(EventInjector.NavigationEvent.NAVIGATION_MOVEMENT, 0, -6, KeypadListener.STATUS_TRACKWHEEL));
        Thread.sleep(min);
        if( DeviceInfo.getSoftwareVersion().startsWith("4."))
            EventInjector.invokeEvent(new EventInjector.NavigationEvent(EventInjector.NavigationEvent.NAVIGATION_MOVEMENT, 0,4, KeypadListener.STATUS_TRACKWHEEL));
        else
            EventInjector.invokeEvent(new EventInjector.NavigationEvent(EventInjector.NavigationEvent.NAVIGATION_MOVEMENT, 0,5, KeypadListener.STATUS_TRACKWHEEL));
        Thread.sleep(min);
        EventInjector.invokeEvent(new EventInjector.KeyCodeEvent(KeyCodeEvent.KEY_DOWN,Characters.ENTER,0));
   }
    else if(str.startsWith("Medium")){
        try {
            ApplicationManager.getApplicationManager().launch("net_rim_bb_profiles_app");
         }
        catch (ApplicationManagerException ex)  {
         }
        Thread.sleep(min);
        // go top
        EventInjector.invokeEvent(new EventInjector.NavigationEvent(EventInjector.NavigationEvent.NAVIGATION_MOVEMENT, 0, -6, KeypadListener.STATUS_TRACKWHEEL));
        Thread.sleep(min);
        EventInjector.invokeEvent(new EventInjector.NavigationEvent(EventInjector.NavigationEvent.NAVIGATION_MOVEMENT, 0, 2, KeypadListener.STATUS_TRACKWHEEL));
        Thread.sleep(min);
        EventInjector.invokeEvent(new EventInjector.KeyCodeEvent(KeyCodeEvent.KEY_DOWN,Characters.ENTER,0));
   }
    else if(str.endsWith("Off")){
        try {
            ApplicationManager.getApplicationManager().launch("net_rim_bb_profiles_app");
         }
        catch (ApplicationManagerException ex)  {
         }
        Thread.sleep(min);
        // go top
        EventInjector.invokeEvent(new EventInjector.NavigationEvent(EventInjector.NavigationEvent.NAVIGATION_MOVEMENT, 0, -6, KeypadListener.STATUS_TRACKWHEEL));
        Thread.sleep(min);
        if( DeviceInfo.getSoftwareVersion().startsWith("4."))
            EventInjector.invokeEvent(new EventInjector.NavigationEvent(EventInjector.NavigationEvent.NAVIGATION_MOVEMENT, 0, 5, KeypadListener.STATUS_TRACKWHEEL));
        else
            EventInjector.invokeEvent(new EventInjector.NavigationEvent(EventInjector.NavigationEvent.NAVIGATION_MOVEMENT, 0, 6, KeypadListener.STATUS_TRACKWHEEL));
        Thread.sleep(min);
        EventInjector.invokeEvent(new EventInjector.KeyCodeEvent(KeyCodeEvent.KEY_DOWN,Characters.ENTER,0));
   }
    else if(str.equalsIgnoreCase("Quiet") ||str.equalsIgnoreCase("Silent")  ){
        try {
            ApplicationManager.getApplicationManager().launch("net_rim_bb_profiles_app");
         }
        catch (ApplicationManagerException ex)  {
         }
        Thread.sleep(min);
        // go top
        EventInjector.invokeEvent(new EventInjector.NavigationEvent(EventInjector.NavigationEvent.NAVIGATION_MOVEMENT, 0, -6, KeypadListener.STATUS_TRACKWHEEL));
        Thread.sleep(min);
        if( DeviceInfo.getSoftwareVersion().startsWith("4."))
            EventInjector.invokeEvent(new EventInjector.NavigationEvent(EventInjector.NavigationEvent.NAVIGATION_MOVEMENT, 0, 2, KeypadListener.STATUS_TRACKWHEEL));
        else
            EventInjector.invokeEvent(new EventInjector.NavigationEvent(EventInjector.NavigationEvent.NAVIGATION_MOVEMENT, 0, 4, KeypadListener.STATUS_TRACKWHEEL));
        Thread.sleep(min);
        EventInjector.invokeEvent(new EventInjector.KeyCodeEvent(KeyCodeEvent.KEY_DOWN,Characters.ENTER,0));
   }
    }
}
       static String checkstr(String str) {
           for(int i=0;i<str.length();i++)
           {
               if(str.charAt(i)!= '1'
                       &&str.charAt(i)!= '2'
                       &&str.charAt(i)!= '3'
                       &&str.charAt(i)!= '4'
                       &&str.charAt(i)!= '5'
                       &&str.charAt(i)!= '6'
                       &&str.charAt(i)!= '7'
                       &&str.charAt(i)!= '8'
                       &&str.charAt(i)!= '9'
                       &&str.charAt(i)!= '0')
               {
                   str=str.replace(str.charAt(i), ' ');
                   //System.out.println("****BBextra Log****:Detect unrecognize char to space ="+str);
               }
//               else
                   //System.out.println("****BBextra Log****:Detect no unrecognize char="+str);
           }
    return str;
}
       ////////////////////////////
       //light off when calling  //
       ////////////////////////////
public static void lightoff() {
    PersistentObject store;
    store = PersistentStore.getPersistentObject (0x520e17018653f10eL);
    if(store.getContents()!=null && store.getContents().toString().equalsIgnoreCase("Enable"))
        Backlight.enable(false);
}
}
