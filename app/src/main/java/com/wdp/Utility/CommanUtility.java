package com.wdp.Utility;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.inputmethod.InputMethodManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.filestack.Client;
import com.filestack.Config;
import com.filestack.FileLink;
import com.filestack.StorageOptions;
import com.filestack.transforms.tasks.ResizeTask;
import com.wdp.turnstr.R;


import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommanUtility {

    public static void replaceFragment(Fragment newFragment, Context context) {
        FragmentManager fragmentManager = ((AppCompatActivity) context).getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container_body, newFragment);
        fragmentTransaction.commit();
    }

    public static void addFragment(Fragment newFragment, Context context) {
        FragmentManager fragmentManager = ((AppCompatActivity) context).getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.container_body, newFragment);
        fragmentTransaction.commit();
    }

    public static void replaceFragmentContainer(Fragment newFragment, Context context) {
        FragmentManager fragmentManager = ((AppCompatActivity) context).getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container_cube, newFragment);
        fragmentTransaction.commit();
    }

    public static String getDateTime() {
        DateFormat dateFormat = new SimpleDateFormat("dd MMM, yyyy");
        Date date = new Date();
        return dateFormat.format(date);
    }

    public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        if (activity.getCurrentFocus() != null) {
            inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
        }
    }

    public static boolean isEmailValid(String email) {
        String regExpn = "^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@" + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\." + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|" + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$";
        CharSequence inputStr = email;
        Pattern pattern = Pattern.compile(regExpn, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);
        if (matcher.matches())
            return true;
        else
            return false;
    }

    public static String getAdaptiveUrl(FileLink fileLink, int dimen, String fileType) {
        ResizeTask resizeTask = new ResizeTask.Builder()
                .align("center")
                .width(dimen)
                .height(dimen)
                .build();
        if (fileType.equals("video")){
            Log.d("jresponse","--->" + fileLink.imageTagsAsync());
            return fileLink.imageTransform().url();
        }
        else {
            return fileLink.imageTransform().url();
            //return fileLink.imageTransform().addTask(resizeTask).url();
        }

    }
    public static FileLink Upload(String url,String fileType) throws IOException {
        Config config = new Config("Aq7v7YUpjTdiQq9eVXzV3z");
        Client client = new Client(config);
        File file = new File(url);
        StorageOptions options = new StorageOptions.Builder()
                .mimeType(fileType)
                .filename(file.getName())
                .path(url)
                .build();
        FileLink fileLink = client.upload(url,true);
        if (fileLink != null) {
            String urlRes = getAdaptiveUrl(fileLink, 300,fileType);
            Log.e("Tag", "urlRes.........................urlRes...."+urlRes);
            Log.e("Tag", "urlRes.........................urlRes...."+fileLink.getHandle());
        }

        return fileLink;
    }
}
