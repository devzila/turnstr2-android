package com.adroidtech.turnstr2;

import android.content.Context;
import android.content.Intent;
import android.os.Process;

import com.adroidtech.turnstr2.Activity.HomePageActivity;
import com.adroidtech.turnstr2.Activity.LoginActivity;
import com.adroidtech.turnstr2.Utils.PreferenceKeys;
import com.adroidtech.turnstr2.Utils.SharedPreference;

import java.io.PrintWriter;
import java.io.StringWriter;

public class MyExceptionHandler implements Thread.UncaughtExceptionHandler {
    private final Context myContext;
    private final Class<?> myActivityClass;

    public MyExceptionHandler(Context context, Class<?> c) {
        myContext = context;
        myActivityClass = c;
    }

    public MyExceptionHandler(Context context) {
        myContext = context;
        myActivityClass = LoginActivity.class;
    }

    public void uncaughtException(Thread thread, Throwable exception) {
        StringWriter stackTrace = new StringWriter();
        exception.printStackTrace(new PrintWriter(stackTrace));
        System.err.println(stackTrace);// You can use LogCat too
        Intent intent;
        try {
            intent = new Intent(myContext, LoginActivity.class);

        } catch (Exception e) {
            e.printStackTrace();
            intent = new Intent(myContext, myActivityClass);
        }
//        "Sorry, we ran into an application error and can't complete your request"
//        "Sorry, An error occurred while completing your request."
//        Utils.showAlertDialog2(myContext, "Error !", "Sorry, we ran into an application error and can't complete your request", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                dialog.dismiss();
//            }
//        });
//        Intent intent = new Intent(myContext, myActivityClass);
        String s = stackTrace.toString();
        //you can use this String to know what caused the exception and in which Activity
        intent.putExtra("uncaughtException", "Exception is: " + stackTrace.toString());
        intent.putExtra("stacktrace", s);
        myContext.startActivity(intent);
        //for restarting the Activity
        Process.killProcess(Process.myPid());
        System.exit(0);
    }
}