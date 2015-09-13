package imac.alumninetwork;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import imac.alumninetwork.data.Alumni;
import imac.alumninetwork.data.AlumnusContent;
import imac.alumninetwork.data.CompaniesContent;
import imac.alumninetwork.data.Company;

/**
 * Created by tony on 16/12/14.
 */
public class AlumnusListFragment  extends ListFragment{

    public static String COMPANY_ID = "company_id";
    public static String FIELD_ID = "field_id";

    private TextView mCompanyName;
    private TextView mCompanyDescription;
    private TextView mCompanyWebsite;

    private AlumnusListActivity mActivity;
    private AlumnusListTask mTask;

    private View mAlumnusListLoader;
    private View mAlumnusListEmpty;

    private int mAnimationDuration;

    private Company company;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_alumnus_list, container, false);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAnimationDuration = getResources().getInteger(
                android.R.integer.config_longAnimTime);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.alumnus_list, menu);
    }

    public void setListSingleSelectionMode (boolean selection){
        getListView().setChoiceMode(selection ? ListView.CHOICE_MODE_SINGLE : ListView.CHOICE_MODE_SINGLE);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);

        mActivity = (AlumnusListActivity)getActivity();

        View parent = getView();

        mAlumnusListLoader = parent.findViewById(R.id.alumnus_list_loader);
        mAlumnusListEmpty = parent.findViewById(R.id.alumnus_list_empty);

        launchTask();

        mCompanyName = (TextView) parent.findViewById(R.id.company_name);
        mCompanyDescription = (TextView) parent.findViewById(R.id.company_description);
        mCompanyWebsite = (TextView) parent.findViewById(R.id.company_website);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {

        Alumni selectedAlumni = (Alumni)getListView().getItemAtPosition(position);
        mActivity.onSelectedAlumni(selectedAlumni.getName());
    }

    private void launchTask (){

        Anim.show(mActivity, mAlumnusListLoader);
        Anim.hide(mActivity, mAlumnusListEmpty);
        Anim.hide(mActivity, getListView());

        if(mTask!=null){
            mTask.cancel(true);
        }
        mTask = new AlumnusListTask();
        mTask.execute();
    }

    private void updateView (List<Alumni> alumnus){

        mCompanyName.setText(company.getName());
        mCompanyDescription.setText("Descritption : \n" + company.getDescription());
        mCompanyWebsite.setText("Website : " + company.getWebsite());

        boolean isEmpty = alumnus==null || alumnus.isEmpty();

        Anim.hide(mActivity, mAlumnusListLoader);

        if(isEmpty){
            setListAdapter(null);

            Anim.show(mActivity, mAlumnusListEmpty);
            Anim.hide(mActivity, getListView());
        }
        else {
            setListAdapter(new ArrayAdapter<Alumni>(mActivity, R.layout.white_text_view, alumnus));

            Anim.hide(mActivity, mAlumnusListEmpty);
            Anim.show(mActivity, getListView());

        }

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

    public class AlumnusListTask extends AsyncTask<Void, Void, List<Alumni>> {

        private boolean error;

        @Override
        protected List<Alumni> doInBackground(Void... voids) {
            try {
                mActivity = (AlumnusListActivity)getActivity();

                company = new CompaniesContent().findCompany(mActivity.COMPANY_ID);

                return new AlumnusContent().findAlumnusByCompanyAndField(mActivity.COMPANY_ID, mActivity.FIELD_ID);
            } catch (Exception e){
                e.printStackTrace();
                error=true;
            }
            return null;
        }

        @Override
        protected void onPostExecute(List<Alumni> alumnus) {

            if(error){
                showErrorDialog();
            }

            updateView(alumnus);
        }
    }
}