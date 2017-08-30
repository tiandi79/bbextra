/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package test;

import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FieldChangeListener;
import net.rim.device.api.ui.Ui;
import net.rim.device.api.ui.component.ButtonField;
import net.rim.device.api.ui.component.LabelField;
import net.rim.device.api.ui.container.PopupScreen;
import net.rim.device.api.ui.container.VerticalFieldManager;

/**
 *
 * @author Tiandi Zhang <tiandi.zhang@richina.com>
 */
class saveto extends PopupScreen {
    int non;
    String s1 = "Screenshot file save to SDCard/BBextra/BBescreen";

    public saveto(int i) {
        super(new VerticalFieldManager());
            //#ifdef SC
//#      s1 = "截屏图片保存到SDCard/BBextra/BBescreen";
          //#endif
        non = i;
//        DialogFieldManager dfm = (DialogFieldManager)getDelegate();
        add(new LabelField(s1+non+".png"));
        ButtonField b1 = new ButtonField("OK",ButtonField.CONSUME_CLICK|ButtonField.FIELD_HCENTER);
        add(b1);
        b1.setChangeListener(new FieldChangeListener() {

            public void fieldChanged(Field field, int context) {
                closescreen();
            }
        });
    }

    private void closescreen() {
        Ui.getUiEngine().popScreen(this);
    }
protected boolean keyDown(int keycode, int time) {
        Ui.getUiEngine().popScreen(this);
        return false;
   }
protected boolean navigationClick(int status, int time){

        Ui.getUiEngine().popScreen(this);
        return false;
    }
}
