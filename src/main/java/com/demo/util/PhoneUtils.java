package com.demo.util;

import me.ihxq.projects.pna.PhoneNumberInfo;
import me.ihxq.projects.pna.PhoneNumberLookup;

public class PhoneUtils {
    private final static PhoneNumberLookup PHONE_NUMBER_LOOKUP = new PhoneNumberLookup();

    public static void main(String[] args) {
        System.out.println(find("18754717037"));
    }

    public static PhoneNumberInfo find(String number) {
        PhoneNumberInfo found = PHONE_NUMBER_LOOKUP.lookup(number).orElseThrow(RuntimeException::new);
        return found;
    }

}
