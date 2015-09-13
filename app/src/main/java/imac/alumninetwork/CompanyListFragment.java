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

import java.util.List;

import imac.alumninetwork.data.CompaniesContent;
import imac.alumninetwork.data.Company;

/**
 * Created by tony on 16/12/14.
 */
public class CompanyListFragment extends ListFragment {

    public static String FIELD_ID = "field_id";

    private CompanyListActivity mActivity;
    private CompanyListTask mTask;

    private View mCompanyListLoader;
    private View mCompanyListEmpty;

    private int mAnimationDuration;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_company_list, container, false);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAnimationDuration = getResources().getInteger(
                android.R.integer.config_longAnimTime);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.company_list, menu);
    }

    public void setListSingleSelectionMode (boolean selection){
        getListView().setChoiceMode(selection ? ListView.CHOICE_MODE_SINGLE : ListView.CHOICE_MODE_SINGLE);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);

        mActivity = (CompanyListActivity)getActivity();

        View parent = getView();

        mCompanyListLoader = parent.findViewById(R.id.company_list_loader);
        mCompanyListEmpty = parent.findViewById(R.id.company_list_empty);

        launchTask();
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {

        Company selectedCompany = (Company)getListView().getItemAtPosition(position);
        mActivity.onSelectedCompany(selectedCompany.getName());
    }

    private void launchTask (){

        Anim.show(mActivity, mCompanyListLoader);
        Anim.hide(mActivity, mCompanyListEmpty);
        Anim.hide(mActivity, getListView());

        if(mTask!=null){
            mTask.cancel(true);
        }
        mTask = new CompanyListTask();
        mTask.execute();
    }

    private void updateView (List<Company> companies){

        boolean isEmpty = companies==null || companies.isEmpty();

        Anim.hide(mActivity, mCompanyListLoader);

        if(isEmpty){
            setListAdapter(null);

            Anim.show(mActivity, mCompanyListEmpty);
            Anim.hide(mActivity, getListView());
        }
        else {
            setListAdapter(new ArrayAdapter<Company>(mActivity, R.layout.white_text_view, companies));

            Anim.hide(mActivity, mCompanyListEmpty);
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

    public class CompanyListTask extends AsyncTask<Void, Void, List<Company>> {

        private boolean error;

        @Override
        protected List<Company> doInBackground(Void... voids) {
            try {

                mActivity = (CompanyListActivity)getActivity();
                return new CompaniesContent().findCompaniesByField(mActivity.FIELD_ID);
            } catch (Exception e){
                e.printStackTrace();
                error=true;
            }
            return null;
        }

        @Override
        protected void onPostExecute(List<Company> companies) {
            if(error){
                showErrorDialog();
            }

            updateView(companies);
        }
    }
}