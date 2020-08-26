package com.example.myapplication.dialog.widge;

import android.content.DialogInterface;

import java.lang.ref.WeakReference;

public class DialogCancelListener implements DialogInterface.OnCancelListener {
    private WeakReference<LeakDialogFragment> leakDialogFragmentWeakReference;

    public DialogCancelListener(LeakDialogFragment leakDialogFragment) {
        this.leakDialogFragmentWeakReference = new WeakReference<>(leakDialogFragment);
    }

    @Override
    public void onCancel(DialogInterface dialog) {
        LeakDialogFragment leakDialogFragment = leakDialogFragmentWeakReference.get();
        if(leakDialogFragment!=null){
            leakDialogFragment.onCancel(dialog);
        }
    }
}
