package imac.alumninetwork;

import android.support.v4.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by tony on 16/12/14.
 */
public class FieldListFragment extends Fragment{

    private FieldListActivity mActivity;

    private Button mAudiovisualButton;
    private Button mProgrammingButton;
    private Button mWebButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_field_list, container, false);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.field_list, menu);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);

        mActivity = (FieldListActivity)getActivity();

        View parent = getView();
        mAudiovisualButton = (Button) parent.findViewById(R.id.audiovisual_button);
        mProgrammingButton = (Button) parent.findViewById(R.id.programming_button);
        mWebButton = (Button) parent.findViewById(R.id.web_button);
        mAudiovisualButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mActivity.onFieldSelected("Audiovisual");
            }
        });

        mProgrammingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mActivity.onFieldSelected("Programming");
            }
        });

        mWebButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mActivity.onFieldSelected("Web");
            }
        });
    }
}
