package com.example.amauryesparza.androidchat.addcontact.ui;


import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.amauryesparza.androidchat.R;
import com.example.amauryesparza.androidchat.addcontact.AddContactPresenter;
import com.example.amauryesparza.androidchat.addcontact.AddContactPresenterImpl;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 **/
public class AddContactFragment extends DialogFragment implements AddContactView, DialogInterface.OnShowListener {

    @BindView(R.id.editContactEmail)
    EditText editEmail;
    @BindView(R.id.progressContactListBar)
    ProgressBar progressBar;

    private AddContactPresenter presenter;

    public AddContactFragment(){
        presenter = new AddContactPresenterImpl(this);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = getActivity().getLayoutInflater().inflate(R.layout.fragment_add_contact, null);
        ButterKnife.bind(this, view);
        ButterKnife.setDebug(true);

        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(getActivity());
        alertBuilder.setView(view);
        alertBuilder.setTitle(R.string.addcontact_dialog_title);
        alertBuilder.setPositiveButton(R.string.dialog_button_add_contact, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                presenter.addContact(editEmail.getText().toString());
            }
        });
        alertBuilder.setNegativeButton(R.string.dialog_button_cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        AlertDialog dialog = alertBuilder.create();
        dialog.setOnShowListener(this);
        return dialog;
    }

    @Override
    public void showInput() {
            editEmail.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideInput() {
        editEmail.setVisibility(View.GONE);
    }

    @Override
    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void contactAddedSuccessfuly() {
        //Return to the Activity
        Toast.makeText(getActivity(), R.string.addcontact_message_contactadded, Toast.LENGTH_LONG).show();
    }

    @Override
    public void contactAddedError() {
        editEmail.setText("");
        editEmail.setError(getResources().getString(R.string.addcontact_error_message));
    }

    @Override
    public void onShow(DialogInterface dialog) {
        presenter.onShow();
    }

    @Override
    public void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }
}
