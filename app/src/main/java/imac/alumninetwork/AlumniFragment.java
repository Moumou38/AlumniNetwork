package imac.alumninetwork;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import imac.alumninetwork.data.Alumni;
import imac.alumninetwork.data.AlumnusContent;

/**
 * Created by tony on 16/12/14.
 */
public class AlumniFragment extends Fragment{

    private View mAlumniLoader;
    private View mAlumniContent;

    private AlumniTask mTask;

    private Alumni mAlumni;

    private Context mContext ;

    static public String NAME_ID = "name_id";

    private TextView mName;
    private TextView mPromo;
    private TextView mCompany;
    private TextView mField;
    private TextView mPhoneNumber;
    private TextView mEmail;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_alumni, container, false);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.alumni, menu);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);

        mContext = getActivity();

        View parent = getView();

        mAlumniLoader = parent.findViewById(R.id.alumni_loader);
        mAlumniContent = parent.findViewById(R.id.alumni_content);

        mName = (TextView) parent.findViewById(R.id.alumni_name);
        mPromo = (TextView) parent.findViewById(R.id.alumni_promo);
        mCompany = (TextView) parent.findViewById(R.id.alumni_company);
        mField = (TextView) parent.findViewById(R.id.alumni_field);
        mPhoneNumber = (TextView) parent.findViewById(R.id.alumni_phonenumber);
        mEmail = (TextView) parent.findViewById(R.id.alumni_email);

        launchTask();
    }

    private void launchTask (){

        Anim.hide(mContext, mAlumniContent);
        Anim.show(mContext, mAlumniLoader);

        if(mTask!=null){
            mTask.cancel(true);
        }
        mTask = new AlumniTask();
        mTask.execute(((AlumniActivity)mContext).NAME_ID);
    }

    private void updateView (Alumni alumni){
        if(mAlumni != null){
            mAlumni = alumni;
            mName.setText(mAlumni.getName());
            mPromo.setText("Promo : Imac " + mAlumni.getPromo());
            mCompany.setText("Company : " + mAlumni.getCompany());
            mField.setText("Field : " + mAlumni.getField());
            mPhoneNumber.setText("Phone number : " + mAlumni.getPhoneNumber());
            mEmail.setText("Contact : " + mAlumni.getEmail());
        }
        Anim.hide(mContext, mAlumniLoader);
        Anim.show(mContext, mAlumniContent);
    }

    private void showErrorDialog (){
        DialogFragment fragment = new ErrorDialogFragment();
        fragment.setTargetFragment(this, 0);
        fragment.show(getFragmentManager(), "error");
    }

    @Override
    public void onDestroy() {
        if(mTask!=null){
            mTask.cancel(true);
        }
        super.onDestroy();
    }

    public class AlumniTask extends AsyncTask<String, Void, Alumni> {

        private boolean error;

        @Override
        protected Alumni doInBackground(String... ids) {
            try {
                return new AlumnusContent().findAlumni(ids[0]);
            } catch (Exception e){
                e.printStackTrace();
                error=true;
            }
            return null;
        }

        @Override
        protected void onPostExecute(Alumni alumni) {
            if(error){
                showErrorDialog();
            }

            mAlumni = alumni;
            updateView(alumni);
        }
    }
}