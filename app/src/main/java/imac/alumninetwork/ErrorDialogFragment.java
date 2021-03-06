package imac.alumninetwork;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;

/**
 * Created by tony on 21/12/14.
 */
public class ErrorDialogFragment extends DialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new AlertDialog.Builder(getActivity())
                .setTitle(R.string.error)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setMessage(R.string.network_error)
                .setPositiveButton(android.R.string.ok,null)
                .create();
    }
}