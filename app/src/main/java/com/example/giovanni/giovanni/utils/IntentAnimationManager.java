package com.example.giovanni.giovanni.utils;

import android.app.Activity;
import android.content.Context;

import com.example.giovanni.giovanni.R;

public class IntentAnimationManager {

    public static void handleIntentAnimation(Context context, String animationType) {

        Activity activity = (Activity) context;
        switch (animationType) {
            case "left to right":
                activity.overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);
                break;
            case "right to left":
                activity.overridePendingTransition(R.anim.out_left_to_right, R.anim.out_right_to_left);
                break;
            case "bottom to up":
                activity.overridePendingTransition(R.anim.bottom_to_up, R.anim.up_to_bottom);
                break;
            case "up to bottom":
                activity.overridePendingTransition(R.anim.out_up_to_bottom, R.anim.out_bottom_to_up);
                break;
            case "fade in to fade out":
                activity.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                break;
            case "rotate out to rotate in":
                activity.overridePendingTransition(R.anim.rotatein_out, R.anim.rotateout_in);
                break;
            default:
                break;
        }
    }
}