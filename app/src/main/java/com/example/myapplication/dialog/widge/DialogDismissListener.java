package com.example.myapplication.dialog.widge;

import android.content.DialogInterface;

import java.lang.ref.WeakReference;

public class DialogDismissListener implements DialogInterface.OnDismissListener {
    private WeakReference<LeakDialogFragment> leakDialogFragmentWeakReference;

    public DialogDismissListener(LeakDialogFragment leakDialogFragment) {
        this.leakDialogFragmentWeakReference = new WeakReference<>(leakDialogFragment);
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        LeakDialogFragment leakDialogFragment = leakDialogFragmentWeakReference.get();
        if(leakDialogFragment!=null){
            leakDialogFragment.onDismiss(dialog);
        }
    }
}
