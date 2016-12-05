package com.skreaper.game.entity;

public class MenuOption {
    private int orderNumber;
    private String mainText;
    private Integer activity;
    private int icon;
    private Class optionClass;

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getMainText() {
        return mainText;
    }

    public void setMainText(String mainText) {
        this.mainText = mainText;
    }

    public Integer getActivity() {
        return activity;
    }

    public void setActivity(Integer activity) {
        this.activity = activity;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public Class getOptionClass() {
        return optionClass;
    }

    public void setOptionClass(Class optionClass) {
        this.optionClass = optionClass;
    }
}
