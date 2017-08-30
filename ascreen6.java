
package test;

import net.rim.blackberry.api.homescreen.HomeScreen;
import net.rim.blackberry.api.menuitem.ApplicationMenuItemRepository;
import net.rim.device.api.system.Bitmap;
import net.rim.device.api.system.DeviceInfo;
import net.rim.device.api.system.PersistentObject;
import net.rim.device.api.system.PersistentStore;
import net.rim.device.api.system.RuntimeStore;
import net.rim.device.api.ui.Color;
import net.rim.device.api.ui.Font;
import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.MenuItem;
import net.rim.device.api.ui.Ui;
import net.rim.device.api.ui.component.CheckboxField;
import net.rim.device.api.ui.component.DateField;
import net.rim.device.api.ui.component.EditField;
import net.rim.device.api.ui.component.LabelField;
import net.rim.device.api.ui.component.Menu;
import net.rim.device.api.ui.component.ObjectChoiceField;
import net.rim.device.api.ui.component.SeparatorField;
import net.rim.device.api.ui.container.HorizontalFieldManager;
import net.rim.device.api.ui.container.MainScreen;
/**
 *
 * @author Tiandi Zhang <tiandi.zhang@richina.com>
 */
class ascreen6 extends MainScreen{
    ObjectChoiceField cfield,cfield1,cfield2,cfield3,cfield4,cfield5,cfield6,cfield7,cfield8;
    EditField smssignature,cfield9;
    DateField time1,time2,time3,time4,time5,time6;
    CheckboxField force;
    String s1 = "Other Extra";
    String s2 = "Device Model :";
    String s3 = "Device PIN :";
    String s4 = "Software Version :";
    String s5 = "*Launch VideoRecoard Everywhere:";
    String s6 = "Show Level On Icon:";
    String s7 = "Schedule Profile :";
    String s8 = "  Switch Time A:";
    String s9 = "   Switch To:";
    String s10 = "  Switch Time B:";
    String s11 = "  Switch Time C:";
    String s12 = "  Switch Time D:";
    String s13 = "  Switch Time E:";
    String s14 = "  Switch Time F:";
    String s15 = "SMS Signature :";
    String s16 = "Delay Time :";
    String s17 = "save";
    public ascreen6() {
        super();
        //#ifdef SC
//#       s1 = "其他增强";
//#       s2 = "设备型号:";
//#       s3 = "PIN码:";
//#       s4 = "软件版本:";
//#       s5 = "*手电筒:";
//#       s6 = "显示电量百分比:";
//#       s7 = "情景模式切换:";
//#       s8 = "  切换时间A:";
//#       s9 = "   切换到:";
//#       s10 = "  切换时间B:";
//#       s11 = "  切换时间C:";
//#       s12 = "  切换时间D:";
//#       s13 = "  切换时间E:";
//#       s14 = "  切换时间F:";
//#       s15 = "短信签名:";
//#       s16 = "切换延迟毫秒:";
//#       s17 = "保存";
          //#endif
        PersistentObject store;
        String a[]={"Disable","Enable"};
        String b[];
        String d[]={"Disable","Normal","Loud","Medium","Vibrate Only","Silent","Phone Call Only","All Alerts Off"};
        String c[]={"Disable","Normal","Loud","Vibrate","Quiet","Phone Only","Off"};

        if(DeviceInfo.getSoftwareVersion().startsWith("5"))
            b=d;
        else
            b=c;
        LabelField title = new LabelField(s1,LabelField.ELLIPSIS|LabelField.USE_ALL_WIDTH);
        setTitle(title);
        LabelField l1 = new LabelField(s2);
        EditField e1 = new EditField("",DeviceInfo.getDeviceName(),5,EditField.READONLY);
        MyHorizontalFieldManager m1 = new MyHorizontalFieldManager(l1,e1);
        add(m1);
        l1 = new LabelField(s3);
        e1 = new EditField("",String.valueOf(Integer.toHexString(DeviceInfo.getDeviceId())).toUpperCase(),10,EditField.READONLY);
        m1 = new MyHorizontalFieldManager(l1,e1);
        add(m1);
        l1 = new LabelField(s4);
        e1 = new EditField("",DeviceInfo.getSoftwareVersion()+"",35,EditField.READONLY);
        m1 = new MyHorizontalFieldManager(l1,e1);
        add(m1);
        add(new SeparatorField());
        cfield6 = new ObjectChoiceField(s5, a, 0);
        store = PersistentStore.getPersistentObject (0x565c4a4f2d148a95L);
        synchronized (store) {
            if (store.getContents() == null) {
                 store.setContents(cfield6.getChoice(cfield6.getSelectedIndex()));
                 store.commit();
            }
            else {
                cfield6.setSelectedIndex(store.getContents());
            }
        }
        add(cfield6);
        cfield = new ObjectChoiceField(s6, a, 0);
        store = PersistentStore.getPersistentObject (0xdd4cdf439a9307d9L);
        synchronized (store) {
            if (store.getContents() == null) {
                 store.setContents(cfield.getChoice(cfield.getSelectedIndex()));
                 store.commit();
            }
            else {
                cfield.setSelectedIndex(store.getContents());
            }
        }
        add(cfield);

        add(new SeparatorField());
        cfield5 = new ObjectChoiceField(s7, a, 0);
        store = PersistentStore.getPersistentObject (0x760c2b022b02f81fL);
        synchronized (store) {
            if (store.getContents() == null) {
                 store.setContents(cfield5.getChoice(cfield5.getSelectedIndex()));
                 store.commit();
            }
            else {
                cfield5.setSelectedIndex(store.getContents());
            }
        }
        add(cfield5);
        cfield9 = new EditField(s16,"500",4,EditField.FILTER_NUMERIC);
        store = PersistentStore.getPersistentObject (0xc860c22da74baa63L);
        synchronized (store) {
            if (store.getContents() == null) {
                 store.setContents(cfield9.getText());
                 store.commit();
            }
            else {
                cfield9.setText((String) store.getContents());
            }
        }
        add(cfield9);
        time1 = new DateField(s8,Long.MIN_VALUE,DateField.TIME);
        store = PersistentStore.getPersistentObject (0xc71f14aefa7f191L);
        synchronized (store) {
            if (store.getContents() != null) {
                long fromclock = Long.parseLong(store.getContents().toString());
                time1.setDate(fromclock);
            }
        }
        add(time1);
        cfield1 = new ObjectChoiceField(s9, b, 0);
        store = PersistentStore.getPersistentObject (0x3359d1d21ab299e2L);
        synchronized (store) {
            if (store.getContents() == null) {
                 store.setContents(cfield1.getChoice(cfield1.getSelectedIndex()));
                 store.commit();
            }
            else {
                cfield1.setSelectedIndex(store.getContents());
            }
        }
        add(cfield1);
        time2 = new DateField(s10,Long.MIN_VALUE,DateField.TIME);
        store = PersistentStore.getPersistentObject (0x82b714e0f44c53dfL);
        synchronized (store) {
            if (store.getContents() != null) {
                long fromclock = Long.parseLong(store.getContents().toString());
                time2.setDate(fromclock);
            }
        }
        add(time2);
        cfield2 = new ObjectChoiceField(s9, b, 0);
        store = PersistentStore.getPersistentObject (0x6b2ace76c96a002cL);
        synchronized (store) {
            if (store.getContents() == null) {
                 store.setContents(cfield2.getChoice(cfield2.getSelectedIndex()));
                 store.commit();
            }
            else {
                cfield2.setSelectedIndex(store.getContents());
            }
        }
        add(cfield2);
        time3 = new DateField(s11,Long.MIN_VALUE,DateField.TIME);
        store = PersistentStore.getPersistentObject (0x7219d09205062076L);
        synchronized (store) {
            if (store.getContents() != null) {
                long fromclock = Long.parseLong(store.getContents().toString());
                time3.setDate(fromclock);
            }
        }
        add(time3);
        cfield3 = new ObjectChoiceField(s9, b, 0);
        store = PersistentStore.getPersistentObject (0x7379a1ecc0c572bbL);
        synchronized (store) {
            if (store.getContents() == null) {
                 store.setContents(cfield3.getChoice(cfield3.getSelectedIndex()));
                 store.commit();
            }
            else {
                cfield3.setSelectedIndex(store.getContents());
            }
        }
        add(cfield3);
        time4 = new DateField(s12,Long.MIN_VALUE,DateField.TIME);
        store = PersistentStore.getPersistentObject (0x1b289b6e0a027daaL);
        synchronized (store) {
            if (store.getContents() != null) {
                long fromclock = Long.parseLong(store.getContents().toString());
                time4.setDate(fromclock);
            }
        }
        add(time4);
        cfield4 = new ObjectChoiceField(s9, b, 0);
        store = PersistentStore.getPersistentObject (0x68fcca44ebc80651L);
        synchronized (store) {
            if (store.getContents() == null) {
                 store.setContents(cfield4.getChoice(cfield4.getSelectedIndex()));
                 store.commit();
            }
            else {
                cfield4.setSelectedIndex(store.getContents());
            }
        }
        add(cfield4);
        time5 = new DateField(s13,Long.MIN_VALUE,DateField.TIME);
        store = PersistentStore.getPersistentObject (0xe37875548f344a35L);
        synchronized (store) {
            if (store.getContents() != null) {
                long fromclock = Long.parseLong(store.getContents().toString());
                time5.setDate(fromclock);
            }
        }
        add(time5);
        cfield7 = new ObjectChoiceField(s9, b, 0);
        store = PersistentStore.getPersistentObject (0xa03e229fa21bebfcL);
        synchronized (store) {
            if (store.getContents() == null) {
                 store.setContents(cfield7.getChoice(cfield7.getSelectedIndex()));
                 store.commit();
            }
            else {
                cfield7.setSelectedIndex(store.getContents());
            }
        }
        add(cfield7);
        time6 = new DateField(s14,Long.MIN_VALUE,DateField.TIME);
        store = PersistentStore.getPersistentObject (0xfecef8c0f80e7823L);
        synchronized (store) {
            if (store.getContents() != null) {
                long fromclock = Long.parseLong(store.getContents().toString());
                time6.setDate(fromclock);
            }
        }
        add(time6);
        cfield8 = new ObjectChoiceField(s9, b, 0);
        store = PersistentStore.getPersistentObject (0x685d17f90a66b371L);
        synchronized (store) {
            if (store.getContents() == null) {
                 store.setContents(cfield8.getChoice(cfield8.getSelectedIndex()));
                 store.commit();
            }
            else {
                cfield8.setSelectedIndex(store.getContents());
            }
        }
        add(cfield8);

        add(new SeparatorField());
        smssignature = new EditField(s15,"",20,EditField.FOCUSABLE);
        store = PersistentStore.getPersistentObject(0x80e0d842a0e9fe05L);
        synchronized (store) {
            if (store.getContents() != null) {
                smssignature.setText(store.getContents().toString());
            }
        }
        add(smssignature);
    }
         private MenuItem viewItem = new MenuItem(s17, 100, 10) {
            public void run() {
            ascreen6.this.save();
            ascreen6.this.close();
          }
        };
        protected void makeMenu(Menu menu, int instance) {
            menu.add(viewItem);
       }
     public void save() {
        PersistentObject store;
        store = PersistentStore.getPersistentObject (0xc860c22da74baa63L);
        store.setContents(cfield9.getText());
        store.commit();
        ApplicationMenuItemRepository repository = ApplicationMenuItemRepository.getInstance();
        store = PersistentStore.getPersistentObject (0x80e0d842a0e9fe05L);
        store.setContents(smssignature.getText());
        store.commit();
        RuntimeStore rs = RuntimeStore.getRuntimeStore();
        custommenu3 menu3 = new custommenu3();
        if(smssignature.getText()!= null) {
            if(!smssignature.getText().equalsIgnoreCase("") && rs.get(0x3f24e678ca662c01L)== null) {
                repository.addMenuItem(ApplicationMenuItemRepository.MENUITEM_SMS_EDIT,menu3);

                if(rs.get(0x3f24e678ca662c01L)== null)
                    rs.put(0x3f24e678ca662c01L,menu3);
                }
            else if(smssignature.getText().equalsIgnoreCase("") )
            {
                if(rs.get(0x3f24e678ca662c01L)!= null) {
                	menu3 = (custommenu3) rs.get(0x3f24e678ca662c01L);
                    repository.removeMenuItem(ApplicationMenuItemRepository.MENUITEM_SMS_EDIT,menu3);
                    rs.remove(0x3f24e678ca662c01L);
                }
            }
        }
        else
            {
                custommenu3 menu4 = (custommenu3) rs.get(0x3f24e678ca662c01L);
                if(menu4 != null) {
                repository.removeMenuItem(ApplicationMenuItemRepository.MENUITEM_SMS_EDIT,menu4);
                }
        }


        store = PersistentStore.getPersistentObject (0xdd4cdf439a9307d9L);
        store.setContents(cfield.getChoice(cfield.getSelectedIndex()));
        store.commit();
        if (store.getContents().toString().equalsIgnoreCase("Enable")) {
                           String tmp = String.valueOf(DeviceInfo.getBatteryLevel());
                           Font font = null;
                           if (DeviceInfo.getDeviceName().startsWith("8") &&! DeviceInfo.getDeviceName().startsWith("89"))
                               font = Font.getDefault().derive(Font.BOLD,9,Ui.UNITS_pt);
                           else
                               font = Font.getDefault().derive(Font.BOLD,5,Ui.UNITS_pt);
                           Bitmap tmpbit = null ;

                           if(DeviceInfo.getBatteryLevel()<20){
                               tmpbit = Bitmap.getBitmapResource("extra20.png");
                            }
                           else if(DeviceInfo.getBatteryLevel()<40){
                               tmpbit = Bitmap.getBitmapResource("extra40.png");
                            }
                           else if(DeviceInfo.getBatteryLevel()<60){
                               tmpbit = Bitmap.getBitmapResource("extra60.png");
                            }
                           else if(DeviceInfo.getBatteryLevel()<80){
                               tmpbit = Bitmap.getBitmapResource("extra80.png");
                            }
                           else
                               tmpbit = Bitmap.getBitmapResource("extra100.png");

                               Graphics g = new Graphics(tmpbit);
                               g.setFont(font);
                               g.setColor(Color.WHITE);
                               if(DeviceInfo.getBatteryLevel()<100)
                                    g.drawText(tmp+"%",25, 20);
                               else
                                    g.drawText("Max",25, 20);
                               HomeScreen.updateIcon(tmpbit);
                     }
        else
        {
            HomeScreen.updateIcon(BBextra.myBitmap);
        }
        store = PersistentStore.getPersistentObject (0x3359d1d21ab299e2L);
        store.setContents(cfield1.getChoice(cfield1.getSelectedIndex()));
        store.commit();
        store = PersistentStore.getPersistentObject (0x6b2ace76c96a002cL);
        store.setContents(cfield2.getChoice(cfield2.getSelectedIndex()));
        store.commit();
        store = PersistentStore.getPersistentObject (0x7379a1ecc0c572bbL);
        store.setContents(cfield3.getChoice(cfield3.getSelectedIndex()));
        store.commit();
        store = PersistentStore.getPersistentObject (0x68fcca44ebc80651L);
        store.setContents(cfield4.getChoice(cfield4.getSelectedIndex()));
        store.commit();
        store = PersistentStore.getPersistentObject (0x760c2b022b02f81fL);
        store.setContents(cfield5.getChoice(cfield5.getSelectedIndex()));
        store.commit();
        store = PersistentStore.getPersistentObject (0x565c4a4f2d148a95L);
        store.setContents(cfield6.getChoice(cfield6.getSelectedIndex()));
        store.commit();
        store = PersistentStore.getPersistentObject (0xa03e229fa21bebfcL);
        store.setContents(cfield7.getChoice(cfield7.getSelectedIndex()));
        store.commit();
        store = PersistentStore.getPersistentObject (0x685d17f90a66b371L);
        store.setContents(cfield8.getChoice(cfield8.getSelectedIndex()));
        store.commit();
        store = PersistentStore.getPersistentObject (0xc71f14aefa7f191L);
        store.setContents(String.valueOf(time1.getDate()));
        store.commit();
        store = PersistentStore.getPersistentObject (0x82b714e0f44c53dfL);
        store.setContents(String.valueOf(time2.getDate()));
        store.commit();
        store = PersistentStore.getPersistentObject (0x7219d09205062076L);
        store.setContents(String.valueOf(time3.getDate()));
        store.commit();
        store = PersistentStore.getPersistentObject (0x1b289b6e0a027daaL);
        store.setContents(String.valueOf(time4.getDate()));
        store.commit();
        store = PersistentStore.getPersistentObject (0xe37875548f344a35L);
        store.setContents(String.valueOf(time5.getDate()));
        store.commit();
        store = PersistentStore.getPersistentObject (0xfecef8c0f80e7823L);
        store.setContents(String.valueOf(time6.getDate()));
        store.commit();
    }
}
class MyHorizontalFieldManager extends HorizontalFieldManager
{
    private EditField myedit;
    private LabelField mylabel;
    public MyHorizontalFieldManager(LabelField l1, EditField e1) {
        super();
        mylabel = l1;
        myedit = e1;
        LabelField leftlabel = mylabel;
        add(leftlabel);
        EditField rightedit = myedit;
        add(rightedit);
    }
    protected void sublayout( int maxWidth, int maxHeight )
    {
        super.sublayout( maxWidth, maxHeight );
        int width = getWidth();
        EditField rightedit = getRightEdit();
        if (rightedit != null ) {
            int x = width - Font.getDefault().getAdvance(rightedit.getText()) ;
            int y = 0;
            setPositionChild( rightedit, x, y );
        }
    }
//    private LabelField getLeftLabel()
//    {
//        return mylabel;
//    }
//    private void setLeftLabel( LabelField leftlabel )
//    {
//        this.mylabel = leftlabel;
//    }
    private EditField getRightEdit()
    {
        return myedit;
    }
//    private void setRightEdit( EditField rightedit)
//    {
//        this.myedit = rightedit;
//    }
//
}


