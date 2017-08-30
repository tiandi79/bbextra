/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
//#define SC
package test;

import java.util.Date;
import java.util.Enumeration;
import java.util.Vector;
import javax.microedition.pim.Contact;
import net.rim.blackberry.api.invoke.Invoke;
import net.rim.blackberry.api.invoke.MessageArguments;
import net.rim.blackberry.api.mail.Folder;
import net.rim.blackberry.api.mail.Message;
import net.rim.blackberry.api.mail.Message.Flag;
import net.rim.blackberry.api.mail.MessagingException;
import net.rim.blackberry.api.pdap.BlackBerryContact;
import net.rim.blackberry.api.pdap.BlackBerryContactList;
import net.rim.blackberry.api.pdap.BlackBerryPIM;
import net.rim.device.api.io.Base64InputStream;
import net.rim.device.api.system.Bitmap;
import net.rim.device.api.system.Characters;
import net.rim.device.api.system.Display;
import net.rim.device.api.system.EncodedImage;
import net.rim.device.api.system.EventInjector;
import net.rim.device.api.system.KeyListener;
import net.rim.device.api.system.KeypadListener;
import net.rim.device.api.system.LED;
import net.rim.device.api.system.PersistentObject;
import net.rim.device.api.system.PersistentStore;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.Font;
import net.rim.device.api.ui.Manager;
import net.rim.device.api.ui.Screen;
//#ifdef TOUCH
//# //import net.rim.device.api.ui.TouchEvent;
//#endif
import net.rim.device.api.ui.Ui;
import net.rim.device.api.ui.component.BitmapField;
import net.rim.device.api.ui.component.LabelField;
import net.rim.device.api.ui.component.RichTextField;
import net.rim.device.api.ui.component.SeparatorField;
import net.rim.device.api.ui.container.HorizontalFieldManager;
import net.rim.device.api.ui.container.PopupScreen;
import net.rim.device.api.ui.container.VerticalFieldManager;

/**
 *
 * @author Tiandi Zhang <tiandi.zhang@richina.com>
 */
class alertScreen extends PopupScreen implements KeyListener{
    BlackBerryContactList contactlist;
    Vector contacts;
    Bitmap mypic;
    Message myemail;
    mytext mysms;
    String realaddress,realtitle,realcontext;
    BitmapField bf,enterf,replyf,forwardf,openf,deletef;
    //change phone number to address name
    String[] cname = null;
    private Font font;
    boolean flag=false;
    private final PersistentObject store;
    private final PersistentObject store2;
    private final PersistentObject store3;
//#ifdef TOUCH
//#     private Bitmap delete = Bitmap.getBitmapResource("delete.png");
//#     private Bitmap reply = Bitmap.getBitmapResource("reply.png");
//#     private Bitmap forward = Bitmap.getBitmapResource("forward.png");
//#     private Bitmap enter = Bitmap.getBitmapResource("enter.png");
//#     private Bitmap open = Bitmap.getBitmapResource("open.png");
//#endif
    String s1 = " From :";
    public alertScreen(String msg,String address,Date time,String title,Message email,mytext sms) {
        super(new VerticalFieldManager(Manager.VERTICAL_SCROLL|Manager.VERTICAL_SCROLLBAR|Manager.USE_ALL_WIDTH));
//#ifdef SC
    s1 = "来自";
//#endif
//        System.out.println("****BBextra Log****:Start preview screen");
        myemail = email;
        realtitle = title;
        mysms =sms;
        realcontext = msg;
/*        
//#ifdef TOUCH
//#
//#        LabelField bl1= new LabelField(" ");
//#         // for touch
//#         FlowFieldManager bm1= new FlowFieldManager(Field.FOCUSABLE){
//#             protected boolean touchEvent(TouchEvent event) {
//#         switch(event.getEvent()) {
//#           case TouchEvent.DOWN:
//#             return true;
//#           case TouchEvent.MOVE:
//#             return true;
//#           case TouchEvent.UP:
//#             return true;
//#           case TouchEvent.CLICK:
//#               System.out.println("****BBextra Log****:touch click");
//#             int index = getFieldAtLocation(event.getX(1), event.getY(1));
//#               System.out.println("****BBextra Log****:x="+event.getX(1)+",y="+event.getY(1));
//#             // Ignore click events outside any fields
//#             System.out.println("****BBextra Log****:index ="+index);
//#             if (index == -1)
//#                 return true;
//# 
//#             Field field = getField(index);
//#             if (field == enterf) {
//#                 reademail();
//#             } else if (field == replyf) {
//#                replyemail();
//#             } else if (field == forwardf) {
//#                 forwardemail();
//#             } else if (field == openf) {
//#                 openemail();
//#             } else if (field == deletef) {
//#                 deleteemail();
//#             }
//#             return true;
//#       }
//#       return false;
//#   }
//#         };
//# 
//#         enterf = new BitmapField(enter,BitmapField.FOCUSABLE);
//#         bm1.add(enterf);
//#         bm1.add(bl1);
//#         LabelField bl2= new LabelField(" ");
//#         replyf = new BitmapField(reply,BitmapField.FOCUSABLE);
//#         bm1.add(replyf);
//#         bm1.add(bl2);
//#         LabelField bl3= new LabelField(" ");
//#         forwardf = new BitmapField(forward,BitmapField.FOCUSABLE);
//#         bm1.add(forwardf);
//#         bm1.add(bl3);
//#         LabelField bl4= new LabelField(" ");
//#         openf = new BitmapField(open,BitmapField.FOCUSABLE);
//#         bm1.add(openf);
//#         bm1.add(bl4);
//#         if(myemail != null) {
//#         deletef = new BitmapField(delete,BitmapField.FOCUSABLE);
//#         bm1.add(deletef);
//#         }
//#         add(bm1);
//#         // above for touch
//#endif
*/
//        System.out.println("****BBextra Log****:Msg="+msg);
//        System.out.println("****BBextra Log****:From="+address);
//        System.out.println("****BBextra Log****:Title="+title);
        LabelField timefield = new LabelField(time.toString(),LabelField.FIELD_VCENTER);
        font = Font.getDefault().derive(Font.ITALIC, 7, Ui.UNITS_pt);
        timefield.setFont(font);
        if(address.indexOf("+")==0)
            address=address.substring(1);
        if(address.length()>12 & address.substring(0,2).equalsIgnoreCase("86"))
            address = address.substring(address.length()-11);
        LabelField addressfield = new LabelField(s1+address);
        realaddress = address;
        font = Font.getDefault().derive(Font.PLAIN, 7, Ui.UNITS_pt);
        addressfield.setFont(font);
        RichTextField msgfield = new RichTextField("\n"+msg,Field.FOCUSABLE|Field.READONLY );
        store = PersistentStore.getPersistentObject(0x6e352242e2bbdb68L);
        synchronized (store) {
            if (store.getContents() == null) {
                 font = Font.getDefault().derive(Font.PLAIN, 9, Ui.UNITS_pt);
            }
            else if (store.getContents().toString().equalsIgnoreCase("Small")){
                 font = Font.getDefault().derive(Font.PLAIN, 6 , Ui.UNITS_pt);
            }
            else if (store.getContents().toString().equalsIgnoreCase("Middle")){
                 font = Font.getDefault().derive(Font.PLAIN, 9 , Ui.UNITS_pt);
            }
            else if (store.getContents().toString().equalsIgnoreCase("Large")){
                 font = Font.getDefault().derive(Font.PLAIN, 14, Ui.UNITS_pt);
            }
        }
        msgfield.setFont(font);
        add(timefield);
        add(new SeparatorField());
        HorizontalFieldManager hfm = new HorizontalFieldManager();

        store3= PersistentStore.getPersistentObject(0x5d91f892c5fdb156L);
        int k=getpic(address);
        synchronized (store3) {
            if (store3.getContents().toString().equalsIgnoreCase("Enable") ){
//            System.out.println("****BBextra Log****:Enable show head photo");
        if(k>-1) {
//            System.out.println("****BBextra Log****:Found this address in address book("+k+")");
        try {
            contactlist = (BlackBerryContactList) BlackBerryPIM.getInstance().openPIMList(BlackBerryPIM.CONTACT_LIST, BlackBerryPIM.READ_ONLY);
                 if (contactlist.isSupportedField(Contact.PHOTO)) {
                 Enumeration allContacts = contactlist.items();
                 contacts = enumToVector(allContacts);
                 BlackBerryContact item = (BlackBerryContact)contacts.elementAt(k);
                 if(item.countValues(BlackBerryContact.PHOTO)>0) {
                 byte[] pic = item.getBinary(BlackBerryContact.PHOTO, 0);
                 byte[] pic2 = Base64InputStream.decode(pic, 0, pic.length);
                 EncodedImage e = EncodedImage.createEncodedImage(pic2, 0, pic2.length);
//                 System.out.println("****BBextra Log****:Detect head photo");
                 bf = new BitmapField(e.getBitmap());
                 flag = true;
                 }
                 }
        } catch (java.lang.Throwable ex) {
//                   System.out.println("****BBextra Log****:Can not find head photo.error="+ex.toString());
                   flag = false;
            }
//            System.out.println("****BBextra Log****:Preview step1");
            if (flag)
                hfm.add(bf);
        }
            }
        }
        if(cname!= null) {
        if(cname[BlackBerryContact.NAME_FAMILY]==null)
            cname[BlackBerryContact.NAME_FAMILY]="";
        if(cname[BlackBerryContact.NAME_GIVEN]==null)
            cname[BlackBerryContact.NAME_GIVEN]="";
        addressfield.setText(cname[BlackBerryContact.NAME_FAMILY]+" "+ cname[BlackBerryContact.NAME_GIVEN]);
        }
        VerticalFieldManager vfm = new VerticalFieldManager();
        vfm.add(addressfield);
//        System.out.println("****BBextra Log****:Preview step2");
        if(title != null) {
            LabelField titlefield = new LabelField(" "+title);
            font = Font.getDefault().derive(Font.BOLD, 9 , Ui.UNITS_pt);
            titlefield.setFont(font);
            vfm.add(titlefield);
            //System.out.println("****BBextra Log****:Preview step3");
        }
        //System.out.println("****BBextra Log****:Preview step4");
        hfm.add(vfm);
        add(hfm);
        add(msgfield);
        //System.out.println("****BBextra Log****:Preview step5");
        store2= PersistentStore.getPersistentObject (0xa6970fbc46bf0f24L);
        synchronized (store2) {
            if (store2.getContents().toString().equalsIgnoreCase("Enable")){
                LabelField prompt;
                if( this.myemail == null )
                    prompt= new LabelField("OK[Enter] Open[O] Cancel[ESC] Reply[R] Foward[F]",LabelField.FIELD_LEFT);
                else
                   prompt = new LabelField("OK[Enter] Open[O] Cancel[ESC] Reply[R] Foward[F] Delete[D]",LabelField.FIELD_LEFT);
                font = Font.getDefault().derive(Font.BOLD, 6, Ui.UNITS_pt);
                prompt.setFont(font);
                add(new SeparatorField());
                add(prompt);
            }
        }
        //System.out.println("****BBextra Log****:Preview step6");
        add(new LabelField("\n\n"));
}
      public void sublayout(int w, int h)
    {
        setExtent(Display.getWidth()-55, Display.getHeight()-55);
        setPosition(10, 10);
        layoutDelegate(Display.getWidth()-55, Display.getHeight()-55);

    }

 public boolean navigationClick(int status, int time){
     LED.setState(LED.STATE_OFF);
     Field focus = getLeafFieldWithFocus();
     if(focus.equals(replyf))
         replyemail();
     else if(focus.equals(forwardf))
         forwardemail();
     else if(focus.equals(openf))
         openemail();
     else if(focus.equals(deletef))
         deleteemail();
     else {
             if( this.myemail != null ){
                 synchronized(BBextra.getEventLock()){
                     BBextra.getApplication().invokeLater(new Runnable() {
                            public void run() {
                 myemail.setFlag(Flag.OPENED, true);
                            }
                     });
                 }
             }
        Ui.getUiEngine().popScreen(this);
     }
     return false;
 }
 public boolean keyChar(char key, int status, int time) {

         if (key == Characters.ESCAPE){
             Ui.getUiEngine().popScreen(this);
         }
         else if (key == Characters.SPACE){
             alertScreen.super.scroll(Screen.DOWNWARD);
         }
         else if (key == Characters.LATIN_CAPITAL_LETTER_B || key == Characters.LATIN_SMALL_LETTER_B){
             alertScreen.super.scroll(Screen.BOTTOMMOST);
         }
         else if (key == Characters.LATIN_CAPITAL_LETTER_T || key == Characters.LATIN_SMALL_LETTER_T){
             alertScreen.super.scroll(Screen.TOPMOST);
         }
         else if(key == Characters.ENTER){
             reademail();
         }
         else if(key == Characters.LATIN_CAPITAL_LETTER_D || key == Characters.LATIN_SMALL_LETTER_D){
             deleteemail();
         }
         else if(key == Characters.LATIN_CAPITAL_LETTER_R || key == Characters.LATIN_SMALL_LETTER_R){
            replyemail();
         }
         else if(key == Characters.LATIN_CAPITAL_LETTER_F || key == Characters.LATIN_SMALL_LETTER_F){
            forwardemail();
         }
         else if(key == Characters.LATIN_CAPITAL_LETTER_O || key == Characters.LATIN_SMALL_LETTER_O){
            openemail();
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
public void reademail(){
    LED.setState(LED.STATE_OFF);
    BBextra.canled = false;
    if( this.myemail != null )
       synchronized(BBextra.getEventLock()){
       BBextra.getApplication().invokeLater(new Runnable() {
       public void run() {
           myemail.setFlag(Flag.OPENED, true);
           }
       });
    }
    Ui.getUiEngine().popScreen(this);
}
public void deleteemail(){
    LED.setState(LED.STATE_OFF);
             if( this.myemail != null ){
                 synchronized(BBextra.getEventLock()){
                     BBextra.getApplication().invokeLater(new Runnable() {
                            public void run() {
                 myemail.setFlag(Flag.OPENED, true);
                 Folder folder = myemail.getFolder();
                 folder.deleteMessage(myemail);
                            }
                     });
                 }

             }
             Ui.getUiEngine().popScreen(this);
}

public void replyemail(){
                    LED.setState(LED.STATE_OFF);
             Ui.getUiEngine().popScreen(this);

             BBextra.getApplication().requestForeground();
             if(mysms !=null) {
                    mysms.setAddress(realaddress);
                    mysms.setPayloadText("");
                    MessageArguments m1 = new MessageArguments(mysms);
                    Invoke.invokeApplication(Invoke.APP_TYPE_MESSAGES,m1);
             }
             else if(myemail !=null) {
                synchronized(BBextra.getEventLock()){
                     BBextra.getApplication().invokeLater(new Runnable() {
                            public void run() {
                 myemail.setFlag(Flag.OPENED, true);
                 Message m = new Message();

                    m = myemail.forward();
                            try {
                                m.addRecipient(Message.RecipientType.TO, myemail.getFrom());
                            } catch (MessagingException ex) {
                                //System.out.println("****BBextra Log****:wrong reply address");
                            }
                    m.setSubject("Re:"+myemail.getSubject());
                 MessageArguments m1 = new MessageArguments(m);
                 Invoke.invokeApplication(Invoke.APP_TYPE_MESSAGES,m1);
                            }
                     });
                 }
             }
             if(!BBextra.getApplication().isForeground())
            BBextra.getApplication().requestBackground();
   }
public void forwardemail(){
                 LED.setState(LED.STATE_OFF);
             Ui.getUiEngine().popScreen(this);

             BBextra.getApplication().requestForeground();
             if(mysms !=null) {
                    mysms.setAddress("");
                    mysms.setPayloadText(mysms.getPayloadText());
                    MessageArguments m1 = new MessageArguments(mysms);
                    Invoke.invokeApplication(Invoke.APP_TYPE_MESSAGES,m1);
             }
             else if(myemail !=null) {
                synchronized(BBextra.getEventLock()){
                     BBextra.getApplication().invokeLater(new Runnable() {
                            public void run() {
                 myemail.setFlag(Flag.OPENED, true);
                 Message m = new Message();

                    m = myemail.forward();
                 MessageArguments m1 = new MessageArguments(m);
                 Invoke.invokeApplication(Invoke.APP_TYPE_MESSAGES,m1);
                            }
                     });
                 }
             }
            BBextra.getApplication().requestBackground();
}
public void openemail(){
               //System.out.println("****BBextra Log****:Detect press O");
             LED.setState(LED.STATE_OFF);
             Ui.getUiEngine().popScreen(this);
                Invoke.invokeApplication(Invoke.APP_TYPE_MESSAGES , null);
                 if( this.myemail != null ) {
        BBextra.getApplication().invokeLater(new Runnable() {
                            public void run() {

                EventInjector.KeyCodeEvent ev = new EventInjector.KeyCodeEvent(
                     EventInjector.KeyCodeEvent.KEY_DOWN, Characters.LATIN_SMALL_LETTER_U,KeypadListener.STATUS_NOT_FROM_KEYPAD);
                 //System.out.println("****BBextra Log****:press alt+u");
                EventInjector.invokeEvent(ev);
                EventInjector.KeyCodeEvent ev2 = new EventInjector.KeyCodeEvent(
                     EventInjector.KeyCodeEvent.KEY_UP, Characters.LATIN_SMALL_LETTER_U,KeypadListener.STATUS_NOT_FROM_KEYPAD);
                 //System.out.println("****BBextra Log****:release alt+u");
                EventInjector.invokeEvent(ev2);
             }
        });
                 }
             else {
        BBextra.getApplication().invokeLater(new Runnable() {
                            public void run() {

                EventInjector.KeyCodeEvent ev = new EventInjector.KeyCodeEvent(
                     EventInjector.KeyCodeEvent.KEY_DOWN, Characters.LATIN_SMALL_LETTER_U,KeypadListener.STATUS_NOT_FROM_KEYPAD);
                 //System.out.println("****BBextra Log****:press alt+u");
                EventInjector.invokeEvent(ev);
                EventInjector.KeyCodeEvent ev2 = new EventInjector.KeyCodeEvent(
                     EventInjector.KeyCodeEvent.KEY_UP, Characters.LATIN_SMALL_LETTER_U,KeypadListener.STATUS_NOT_FROM_KEYPAD);
                 //System.out.println("****BBextra Log****:release alt+u");
                EventInjector.invokeEvent(ev2);
             }
        });
             }
}
   private int getpic(String number) {
        if(number.equalsIgnoreCase(""))
           return -1;
        try {
         contactlist = (BlackBerryContactList) BlackBerryPIM.getInstance().openPIMList(BlackBerryPIM.CONTACT_LIST, BlackBerryPIM.READ_ONLY);
                Enumeration allContacts = contactlist.items();
                contacts = enumToVector(allContacts);
            if(number.indexOf("@") < 0) {
            for (int i=0;i<contacts.size();i++) {
                //System.out.println("****BBextra Log****:Check the next contact via SMS");
                BlackBerryContact item = (BlackBerryContact)contacts.elementAt(i);

                for (int j=0;j<item.countValues(BlackBerryContact.TEL);j++){
                    try{
                            if (item.getString(BlackBerryContact.TEL,j).endsWith(number)
                                    || number.endsWith(item.getString(BlackBerryContact.TEL,j))){
                                alertScreen.this.cname = item.getStringArray(BlackBerryContact.NAME, 0);
                                //System.out.println("****BBextra Log****:Contact match="+i);
                                return i;
                            }
                    }
                    catch (java.lang.Throwable ex) {
//                       System.out.println("****BBextra Log****:Contact sms match error="+ex.toString());
                        }
                }
           }
            }
            else {
                    for (int i=0;i<contacts.size();i++) {
                //System.out.println("****BBextra Log****:Check the next contact via EMAIL");
                BlackBerryContact item = (BlackBerryContact)contacts.elementAt(i);

                for (int j=0;j<item.countValues(BlackBerryContact.EMAIL);j++){
                     try{
                            if (item.getString(BlackBerryContact.EMAIL,j).equalsIgnoreCase(number)){
                                //System.out.println("****BBextra Log****:Contact match="+i);
                                return i;
                            }
                    }
                    catch (java.lang.Throwable ex) {
                        //System.out.println("****BBextra Log****:Contact email match error="+ex.toString());
                        }
                }
           }
            }
              //System.out.println("****BBextra Log****:Can not find matching contact");
              return -1;
            } catch (javax.microedition.pim.PIMException ex) {
               //System.out.println("****BBextra Log****:Contact list error="+ex.toString());
               return -1;
            }
   }
    private Vector enumToVector(Enumeration contactEnum)  {
    Vector v = new Vector();
    if (contactEnum == null)
        return v;
    while (contactEnum.hasMoreElements())    {
        v.addElement(contactEnum.nextElement());
    }
    return v;
}
}

