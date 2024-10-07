package com.cp.dtos;

public enum FormFieldTypes {

    BUTTON, CHECKBOX, COLOR, DATE, EMAIL, FILE, HIDDEN, IMAGE, MONTH, NUMBER, PASSWORD, RADIO, RANGE, RESET, SEARCH,
    SUBMIT, TEL, TEXT, TIME, URL, WEEK;

    @Override
    public String toString() {
        return name().toLowerCase();
    }
}
