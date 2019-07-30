package example.collegeapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class GuardianFragment extends Fragment {

    private TextView mLastname, mFirstname, mOccupation;
    private EditText editLastname, editFirstname, editOccupation;
    private Guardian member;
    private Button oSubmit;

    public View onCreateView(LayoutInflater inflater, ViewGroup view, Bundle bundle){
        super.onCreateView(inflater, view, bundle);
        View rootView = inflater.inflate(R.layout.fragment_guardian, view, false);
        member = new Guardian();
        mFirstname = (TextView) rootView.findViewById(R.id.txtGuardFirstName);
        mLastname = (TextView) rootView.findViewById(R.id.txtGuardLastName);
        mOccupation = (TextView) rootView.findViewById(R.id.txtGuardJob);
        editFirstname = (EditText) rootView.findViewById(R.id.editGuardFirstName);
        editLastname = (EditText) rootView.findViewById(R.id.editGuardLastName);
        editOccupation = (EditText) rootView.findViewById(R.id.editGuardJob);
        mFirstname.setText(member.getFirstName());
        mLastname.setText(member.getLastName());
        mOccupation.setText(member.getOccupation());
        oSubmit = (Button) rootView.findViewById(R.id.guardSubmitButton);
        oSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                member.setFirstName(editFirstname.getText().toString());
                mFirstname.setText(member.getFirstName());
                member.setLastName(editLastname.getText().toString());
                mLastname.setText(member.getLastName());
                member.setOccupation(editOccupation.getText().toString());
                mOccupation.setText(member.getOccupation());
            }
        });
        return rootView;
    }

}
