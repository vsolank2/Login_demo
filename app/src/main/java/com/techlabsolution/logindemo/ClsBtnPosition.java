package com.techlabsolution.logindemo;

public class ClsBtnPosition {
    String btnNo;
    int colorPosition;

    public ClsBtnPosition(String btnNo, int colorPosition) {
        this.btnNo = btnNo;
        this.colorPosition = colorPosition;
    }

    public String getBtnNo() {
        return btnNo;
    }

    public void setBtnNo(String btnNo) {
        this.btnNo = btnNo;
    }

    public int getColorPosition() {
        return colorPosition;
    }

    public void setColorPosition(int colorPosition) {
        this.colorPosition = colorPosition;
    }
}
