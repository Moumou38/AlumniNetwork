package imac.alumninetwork;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;


public class AlumniActivity extends ActionBarActivity {

    static public String NAME_ID = "name_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_alumni);

        if(savedInstanceState == null) {

            String id = getIntent().getStringExtra(AlumniFragment.NAME_ID);

            Bundle bundle=new Bundle();
            NAME_ID = id;
            bundle.putString(AlumniFragment.NAME_ID, id);
            Fragment fragment = new AlumniFragment();
            fragment.setArguments(bundle);

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_alumni, fragment)
                    .commit();
        }

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
