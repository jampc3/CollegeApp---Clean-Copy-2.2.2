package example.collegeapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.persistence.DataQueryBuilder;

import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

public class ProfileFragment extends Fragment {

    private TextView pFirstName, pLastName;
    private EditText pedFirstName, pedLastName;
    private Profile person;
    private Button pButton;
    private String newFirstName;

    //private DatePicker pDatepicker;

    public void onStart(){
        super.onStart();

        SharedPreferences sharedPreferences = getActivity().getPreferences(Context.MODE_PRIVATE);
        String email = sharedPreferences.getString(ApplicantActivity.EMAIL_PREF, null);
        if (person.getEmail() == null) {
            person.setEmail(email);
        }

        String whereClause = "email = '" + email + "'";
        DataQueryBuilder query = DataQueryBuilder.create();
        query.setWhereClause(whereClause);
        Backendless.Data.of(Profile.class).find(query, new AsyncCallback<List<Profile>>() {
            @Override
            public void handleResponse(List<Profile> profile) {
                if (!profile.isEmpty()) {
                    SharedPreferences sharedPreferences = getActivity().getPreferences(Context.MODE_PRIVATE);
                    String email = sharedPreferences.getString(ApplicantActivity.EMAIL_PREF, null);
                    person = profile.get(0);
                    pFirstName.setText(person.getFirstName());
                    pLastName.setText(person.getLastName());
                }
            }
            @Override
            public void handleFault(BackendlessFault fault) {
                Log.e("Profile Failure", "Failed to find profile: " + fault.getMessage());
            }
        });
    }

    public void onPause(){
        super.onPause();

        SharedPreferences sharedPreferences = getActivity().getPreferences(Context.MODE_PRIVATE);
        String email = sharedPreferences.getString(ApplicantActivity.EMAIL_PREF, null);
        if (person.getEmail() == null) {
            person.setEmail(email);
        }

        String whereClause = "email = '" + email + "'";
        DataQueryBuilder query = DataQueryBuilder.create();
        query.setWhereClause(whereClause);
        Backendless.Data.of(Profile.class).find(query, new AsyncCallback<List<Profile>>() {
            @Override
            public void handleResponse(List<Profile> profile) {
                if (!profile.isEmpty()) {
                    String profileId = profile.get(0).getObjectId();
                    Log.d("ObjectID: ", "Object ID: " + profileId);
                    person.setObjectId(profileId);
                }
            }
            @Override
            public void handleFault(BackendlessFault fault) {
                Log.e("Profile Failure", "Failed to find profile: " + fault.getMessage());
            }
        });

        Backendless.Data.of(Profile.class).save(person, new AsyncCallback<Profile>() {
            @Override
            public void handleResponse(Profile response) {
                Log.i("Saving", "Saved profile to Backendless");
            }
            public void handleFault(BackendlessFault fault) {
                Log.i("Not Saving", "Failed to save profile!" + fault.getMessage());
            }
        });
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup view, Bundle bundle) {
        super.onCreateView(inflater, view, bundle);
        View rootView = inflater.inflate(R.layout.profile_fragment, view, false);
        person = new Profile();
        pFirstName = (TextView) rootView.findViewById(R.id.txtMyFirstName);
        pLastName = (TextView) rootView.findViewById(R.id.txtMyLastName);
        //pDatepicker = rootView.findViewById(R.id.datePicker);
        pedFirstName = (EditText) rootView.findViewById(R.id.MyFirstName);
        pedLastName = (EditText) rootView.findViewById(R.id.MyLastName);
        //String date = pDatepicker.getDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd");
        pFirstName.setText(person.getFirstName());
        pLastName.setText(person.getLastName());

        pButton = (Button) rootView.findViewById(R.id.MyNameSubmit);
        pButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                person.setFirstName(pedFirstName.getText().toString());
                pFirstName.setText(person.getFirstName());
                person.setLastName(pedLastName.getText().toString());
                pLastName.setText(person.getLastName());
            }
        });
        return rootView;
    }
}
