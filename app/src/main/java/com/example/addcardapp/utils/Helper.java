package com.example.addcardapp.utils;

import android.text.TextUtils;

import androidx.annotation.FloatRange;
import androidx.annotation.NonNull;

import org.jetbrains.annotations.NotNull;

import java.util.Locale;

public class Helper {
    @NotNull
    public static String DontTrimCurrencyDecimalAndAttachCountrySymbol(@NonNull String amount) {
        String strippedAmount = stripAwayCommas(amount);

        double rawAmount = Double.parseDouble(strippedAmount);
        String formattedNumber = formatNumberByLocale(rawAmount);

        return String.valueOf(TextUtils.concat("$", formattedNumber));
    }

    static String formatNumberByLocale(@FloatRange(from = 0.0) double value) {
        return String.format( Locale.getDefault(),"%,.2f", value );
    }

    @NonNull
    public static String stripAwayCommas(@NonNull String unformattedValue) {
        return unformattedValue.replaceAll(",", "");
    }
}
