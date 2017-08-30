/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package test;

import java.io.IOException;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import javax.microedition.io.Connector;
import javax.microedition.io.DatagramConnection;
import javax.microedition.media.Manager;
import javax.microedition.media.MediaException;
import net.rim.blackberry.api.mail.Message;
import net.rim.device.api.io.DatagramBase;
import net.rim.device.api.io.SmsAddress;
import net.rim.device.api.system.Alert;
import net.rim.device.api.system.ApplicationDescriptor;
import net.rim.device.api.system.ApplicationManager;
import net.rim.device.api.system.LED;
import net.rim.device.api.system.PersistentObject;
import net.rim.device.api.system.PersistentStore;
import net.rim.device.api.system.SMSParameters;
import net.rim.device.api.ui.Ui;

/**
 *
 * @author Tiandi Zhang <tiandi.zhang@richina.com>
 */
class TimerMainEvent extends TimerTask {
    public TimerMainEvent() {
    }

    public void run() {
        if (globel.emailled){
             final PersistentObject store3;
                store3 = PersistentStore.getPersistentObject (0x8a839593ae4ec8ecL);
               synchronized (store3) {
            if (store3.getContents()!=null && !store3.getContents().toString().equalsIgnoreCase("Default")){
                if (LED.isPolychromatic()){
               if (store3.getContents().toString().equalsIgnoreCase("Orange") )
                    LED.setColorConfiguration(globel.blinkon*100, globel.blinkoff*100, 0x00FF6100);
                else if (store3.getContents().toString().equalsIgnoreCase("Yellow") )
                    LED.setColorConfiguration(globel.blinkon*100, globel.blinkoff*100, 0x00FFFF00);
                else if (store3.getContents().toString().equalsIgnoreCase("Green") )
                    LED.setColorConfiguration(globel.blinkon*100, globel.blinkoff*100, 0x0000FF00);
                else if (store3.getContents().toString().equalsIgnoreCase("Cyan") )
                    LED.setColorConfiguration(globel.blinkon*100, globel.blinkoff*100, 0x0000FFFF);
                else if (store3.getContents().toString().equalsIgnoreCase("Blue") )
                    LED.setColorConfiguration(globel.blinkon*100, globel.blinkoff*100, 0x000000FF);
                else if (store3.getContents().toString().equalsIgnoreCase("Purple") )
                    LED.setColorConfiguration(globel.blinkon*100, globel.blinkoff*100, 0x00A020F0);
                else
                    LED.setColorConfiguration(globel.blinkon*100, globel.blinkoff*100, 0x00FFFFFF);
               globel.emailled = false;
               this.cancel();
                    //System.out.println("****BBextra Log****:Email led set is ok");
                }
            }
               }
        }
    }
}
class aThread extends Thread {
        private boolean _stop = false;
        private DatagramConnection _dc;
        final PersistentObject store = PersistentStore.getPersistentObject (0xff823c5ac48a9154L);
        final PersistentObject store2 = PersistentStore.getPersistentObject (0x3945fecf2601a023L);

        String msg = "";
        int type = 134;   //if not use UCS2 ,please use 70
        private Message email= null;
        private mytext sms = new mytext();
        boolean longsms = false;
        public synchronized void stop() {

               _stop = true;
               try {
                       _dc.close(); // Close the connection so the thread returns.
                       return;
               } catch (IOException e) {
                       System.err.println(e.toString());
               }
}
            public void run() {
       try {
           //System.out.println("****BBextra Log****:Start to detect sms");
               _dc = (DatagramConnection)Connector.open("sms://0");
               for(;;) {
                       if ( _stop ) {
                              return;
                       }
//                       Datagram d = _dc.newDatagram(_dc.getMaximumLength());
                       DatagramBase d = (DatagramBase) _dc.newDatagram(_dc.getMaximumLength());
                       _dc.receive(d);
//                       System.out.println("****BBextra Log****:d.length="+ d.getLength());
                       if(d.getLength() == type)
                           longsms = true;
                       else
                       {
                           longsms = false;
                       }
//                       System.out.println("****BBextra Log****:if this is longsms="+longsms);
                       final String address = new String(d.getAddress());
                       sms.setAddress(address);
                       if(address.substring(0, 2).equalsIgnoreCase("//"))
                            sms.setAddress(address.substring(2));
                       SmsAddress saddress = (SmsAddress) d.getAddressBase();
//                       SMSPacketHeader smsheader =  saddress.getHeader();
                       SMSParameters smsp = saddress.getHeader();
                       if(!saddress.getHeader().isFromSIMCard()) {
                       if(smsp.getMessageCoding() >0)
                            msg = new String(d.getData(),"UTF-16BE");
                       else
                           msg = new String(d.getData());
                       sms.setPayloadText(msg);
                       final Date time = new Date(System.currentTimeMillis());
                       final String title = null;
                       // d.recieve only can do twice.
                       if(longsms && !globel.firstlong) {
                           globel.lastsms = globel.lastsms + msg;
                           globel.lastfrom = sms.getAddress();
                           globel.lasttime = time;
                           globel.firstlong = true;

                       }
                       else
                       {
                           if(globel.firstlong && globel.lastfrom.equalsIgnoreCase(sms.getAddress()))
                           {
//                           System.out.println("****BBextra Log****:continue sms with same info");
                           msg = globel.lastsms + msg;
//                           System.out.println("****BBextra Log****:longsms2, lastsms="+globel.lastfrom);
//                           System.out.println("****BBextra Log****:longsms2, so msg ="+ msg);
                           globel.lastsms =  "";
                           globel.firstlong = false;
                           }
                       synchronized (store2) {

                        if (store2.getContents()!=null && store2.getContents().toString().equalsIgnoreCase("Enable") ) {
                try{
                    synchronized(BBextra.getEventLock()){
                        BBextra.getApplication().invokeLater(new Runnable() {
                            public void run() {
                                     Ui.getUiEngine().pushGlobalScreen(new alertScreen(msg,sms.getAddress(),time,title,email,sms),1,3);
                            }
                        });

                    }
                }
                catch (Throwable s)
                {
                    //System.out.println("****BBextra Log****:Preview screen error="+s.toString());
                }
                        }
                       }
                       }

        /////////////////////////////
        // switch LED when new sms //
        /////////////////////////////

                     synchronized (store) {
                        if (!BBextra.checksender(sms.getAddress())
                                && store.getContents()!= null
                                && !store.getContents().toString().equalsIgnoreCase("Default")
                                ) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        //System.out.println("****BBextra Log****:SMS led error="+ex.toString());
                    }
                if (LED.isPolychromatic()){
                if (store.getContents().toString().equalsIgnoreCase("Orange"))
                    LED.setColorConfiguration(globel.blinkon*100, globel.blinkoff*100, 0x00FF6100);
                else if (store.getContents().toString().equalsIgnoreCase("Yellow"))
                    LED.setColorConfiguration(globel.blinkon*100, globel.blinkoff*100, 0x00FFFF00);
                else if (store.getContents().toString().equalsIgnoreCase("Green"))
                    LED.setColorConfiguration(globel.blinkon*100, globel.blinkoff*100, 0x0000FF00);
                else if (store.getContents().toString().equalsIgnoreCase("Cyan"))
                    LED.setColorConfiguration(globel.blinkon*100, globel.blinkoff*100, 0x0000FFFF);
                else if (store.getContents().toString().equalsIgnoreCase("Blue"))
                    LED.setColorConfiguration(globel.blinkon*100, globel.blinkoff*100, 0x000000FF);
                else if (store.getContents().toString().equalsIgnoreCase("Purple"))
                    LED.setColorConfiguration(globel.blinkon*100, globel.blinkoff*100, 0x00A020F0);
                else
                    LED.setColorConfiguration(globel.blinkon*100, globel.blinkoff*100, 0x00FFFFFF);
                    //System.out.println("****BBextra Log****:Set up SMS led");
                    Timer time3 = new Timer();
                    //System.out.println("****BBextra Log****:create time3");
                    BBextra.canled = true;
                    time3.scheduleAtFixedRate(new offled(),1000,500);
                }
            }
        }
                      }
                       else
                       {
                           // if from sims card set led off
                            LED.setState(LED.STATE_OFF);
                       }
               }
          }
       catch (IOException e) {
                   //System.out.println("****BBextra Log****:SMS error="+e.toString());
          }
     }

}
class offled extends TimerTask{
    public void run() {
        if(BBextra.canled) {
            //System.out.println("****BBextra Log****:start offled function");
            ApplicationManager manager = ApplicationManager.getApplicationManager();
            ApplicationDescriptor descriptors[] = manager.getVisibleApplications();
            //System.out.println("****BBextra Log****:the first program is "+descriptors[0].getName());
            if (descriptors[0].getName().equalsIgnoreCase("Messages")) {
                 if(LED.isPolychromatic())
                            LED.setState(LED.STATE_OFF);
                        BBextra.canled = false;
            }
        }
        else
            this.cancel();
    }
}
class missedalert extends TimerTask {
    String song;

    final PersistentObject store = PersistentStore.getPersistentObject (0xf61384d91cd700acL);
    public void run() {
        if(BBextra.canmissed) {
            //System.out.println("****BBextra Log****:Miss call vibrate");
            Alert.startVibrate(2000);

            synchronized (store) {
                      if (store.getContents() != null){
                        song = (String) store.getContents();
                        if(!song.equalsIgnoreCase("none")){
                        try {
                            if(globel.alertmusic != null)
                                globel.alertmusic.stop();
                            globel.alertmusic = Manager.createPlayer("file:///"+song);
                            globel.alertmusic.start();
                         } catch (MediaException pe) {
                             //System.out.println("****BBextra Log****:Can not play this alert-ring!");
                             globel.alertmusicerror= true;
                         } catch (IOException ioe) {
                             //System.out.println("****BBextra Log****:Can not find alert-ring file!");
                             globel.alertmusicerror=true;
                         }
                        }
                      }
            }
        }
        else
        {
            //System.out.println("****BBextra Log****:Miss call vibrate cancel");
            this.cancel();
        }
    }
}
class alertmin extends TimerTask {
    int i = 0;
    public alertmin() {
    }

    public void run() {

        if (BBextra.canalert){
            PersistentObject store;
               store = PersistentStore.getPersistentObject (0x9e5719378f00c08L);
               int time ;
               if(store.getContents()!= null)
                    time = Integer.parseInt(store.getContents().toString());
               else
                    time =0;
                    if ( time > 0) {
                        i++;
                        if(i==60) i = 0;
                        if(i == time) {
                            //System.out.println("****BBextra Log****:Start alert ="+i);
                            store = PersistentStore.getPersistentObject(0x348d7d96ab789c93L);
                            synchronized (store) {
                                if (store.getContents() == null)
                                    Alert.startVibrate(500);
                                else if (store.getContents().toString().equalsIgnoreCase("Short") )
                                    Alert.startVibrate(100);
                                else if (store.getContents().toString().equalsIgnoreCase("Middle") )
                                    Alert.startVibrate(500);
                                else if (store.getContents().toString().equalsIgnoreCase("Long") )
                                    Alert.startVibrate(1000);
                            }
                            short[] successAudio ={500,400,300,200,100};
                            if (Alert.isAudioSupported())
                                Alert.startAudio(successAudio, 50);
                            else if (Alert.isBuzzerSupported())
                                Alert.startBuzzer(successAudio, 50);
                        }
                    }
    }
        else {
            i=0;
            this.cancel();
        }
}
}
