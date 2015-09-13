package imac.alumninetwork;

import android.app.Activity;
import android.app.ActionBar;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;

/**
 * Created by tony on 16/12/14.
 */

public class CompanyListActivity extends ActionBarActivity {

    public static String FIELD_ID = "field_id";
    public static String COMPANY_ID = "company_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_list);

        if(savedInstanceState == null) {
            String id = getIntent().getStringExtra(CompanyListFragment.FIELD_ID);

            Bundle bundle=new Bundle();
            FIELD_ID = id;
            bundle.putString(CompanyListFragment.FIELD_ID, id);

            Fragment fragment = new CompanyListFragment();
            fragment.setArguments(bundle);

            setTitle(getString(R.string.title_activity_company_list,id));

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_company_list, fragment)
                    .commit();
        }

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void onSelectedCompany (String id){
        Intent intent=new Intent(this, AlumnusListActivity.class);
        intent.putExtra(AlumnusListFragment.COMPANY_ID, id);
        intent.putExtra(AlumnusListFragment.FIELD_ID, FIELD_ID);

        startActivity(intent);
    }

}
