package com.k1.trakttv.fragment;

import android.annotation.TargetApi;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.k1.trakttv.R;
import com.k1.trakttv.model.DeviceCode;

/**
 * Created by K1 on 8/10/16.
 */
public class VerificationDialogFragment extends DialogFragment {

    public static final String FRAGMENT_NAME = VerificationDialogFragment.class.getName();
    private static final String TAG = VerificationDialogFragment.class.getSimpleName();
    private static final String URL = "url";
    private static final String DEVICE_CODE = "deviceCode";
    private static final String USE_CODE = "useCode";

    /**
     * Notice: 8/10/16 add Parcels to pass whole fields of {@link DeviceCode} into bundle
     *
     * @param deviceCode
     * @return
     */
    public static VerificationDialogFragment newInstance(DeviceCode deviceCode) {
        Bundle args = new Bundle();
        VerificationDialogFragment fragment = new VerificationDialogFragment();
        args.putString(URL, deviceCode.getVerificationUrl());
        args.putString(DEVICE_CODE, deviceCode.getDeviceCode());
        args.putString(USE_CODE, deviceCode.getUserCode());
        fragment.setArguments(args);
        return fragment;
    }

    // 1
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.d(TAG, "onAttach() called with: " + "context = [" + context + "]");
    }


    @Override
    public void onCancel(DialogInterface dialog) {
        super.onCancel(dialog);
        Log.d(TAG, "onCancel() called with: " + "dialog = [" + dialog + "]");
    }

    // 3
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Log.d(TAG, "onCreateDialog() called with: " + "savedInstanceState = [" + savedInstanceState + "]");
        return super.onCreateDialog(savedInstanceState);
    }

    // 2
    @TargetApi(Build.VERSION_CODES.N)
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreate() called with: " + "savedInstanceState = [" + savedInstanceState + "]");
        super.onCreate(savedInstanceState);

        setStyle(DialogFragment.STYLE_NO_FRAME, android.R.style.Theme_Material_Light_DialogWhenLarge_DarkActionBar);
    }

    // 4
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView() called with: " + "inflater = [" + inflater + "], container = [" + container + "], savedInstanceState = [" + savedInstanceState + "]");
        final WebView root = (WebView) inflater.inflate(R.layout.fragment_verification, container, false);
        final String activeUrl = getArguments().getString(URL);
        final WebSettings settings = root.getSettings();
//        settings.setJavaScriptEnabled(true);


        root.loadUrl(activeUrl);

        return root;
    }
}
