package imac.alumninetwork;

import android.app.Activity;
import android.app.ActionBar;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;

/**
 * Created by tony on 16/12/14.
 */

public class AlumnusListActivity extends ActionBarActivity {

    public static String COMPANY_ID = "company_id";
    public static String FIELD_ID = "field_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_alumnus_list);

        if(savedInstanceState == null) {

            String companyId = getIntent().getStringExtra(AlumnusListFragment.COMPANY_ID);
            String fieldId = getIntent().getStringExtra(AlumnusListFragment.FIELD_ID);

            Bundle bundle=new Bundle();
            COMPANY_ID = companyId;
            FIELD_ID = fieldId;
            bundle.putString(AlumnusListFragment.FIELD_ID, fieldId);
            bundle.putString(AlumnusListFragment.COMPANY_ID, companyId);
            Fragment fragment = new AlumnusListFragment();
            fragment.setArguments(bundle);

            setTitle(getString(R.string.title_activity_alumnus_list,companyId));

            // Load fragment in the Activity
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_alumnus_list, fragment)
                    .commit();
        }

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void onSelectedAlumni (String id){

        Intent intent=new Intent(this, AlumniActivity.class);
        intent.putExtra(AlumniFragment.NAME_ID, id);

        startActivity(intent);
    }
}
