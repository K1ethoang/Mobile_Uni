package com.hoanggiakiet.broadcastreceiver_ex2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class SmsReceiver extends BroadcastReceiver {
    SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy", Locale.getDefault());
    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle =intent.getExtras();
        Object[] arrMessages = (Object[]) bundle.get("pdus");
        String phone, time, content;
        Date date;
        byte[] bytes;

        if(arrMessages != null)
        {
            for(Object messageItem : arrMessages)
            {
                bytes = (byte[]) messageItem;
                SmsMessage message = SmsMessage.createFromPdu(bytes, SmsMessage.FORMAT_3GPP);
                phone = message.getDisplayOriginatingAddress();
                date = new Date(message.getTimestampMillis());
                time = dateFormat.format(date);
                content = message.getMessageBody();
                Toast.makeText(context, "Phone: " + phone + "\nTime: " + time, Toast.LENGTH_LONG).show();
            }
        }
    }
}
