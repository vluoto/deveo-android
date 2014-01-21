package com.deveo.android;

import android.accounts.AccountManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.deveo.android.accounts.AccountAuthenticator;
import com.deveo.android.api.ApiManager;
import com.deveo.android.api.DeveoService;
import com.deveo.android.api.MetadataResults;
import com.deveo.android.core.Project;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class DashboardActivity extends ActionBarActivity {

    static final String TAG = DashboardActivity.class.getSimpleName();

    static final int REQUEST_AUTHENTICATE_ACCOUNT = 1337;

    private DeveoService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        service = ApiManager.getService();

        Intent intent = new Intent(this, LoginActivity.class);
        intent.putExtra(LoginActivity.PARAM_AUTHTOKEN_TYPE, AccountAuthenticator.ACCOUNT_TYPE);

        startActivityForResult(intent, REQUEST_AUTHENTICATE_ACCOUNT);

        setContentView(R.layout.activity_dashboard);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_AUTHENTICATE_ACCOUNT:
                if (data != null && data.getExtras() != null) {
                    Bundle extras = data.getExtras();

                    String login = extras.getString(AccountManager.KEY_ACCOUNT_NAME);
                    String companyKey = extras.getString(LoginActivity.PARAM_COMPANY_KEY);
                    String accountKey = extras.getString(LoginActivity.PARAM_ACCOUNT_KEY);

                    String authorization = String.format("deveo plugin_key='3c94d47d6257ca0d3bc54a9b6a91aa64',company_key='%s',account_key='%s'", companyKey, accountKey);

                    service.getUserProjects(authorization, login, new Callback<MetadataResults<Project>>() {
                        @Override
                        public void success(MetadataResults<Project> metadataResults, Response response) {
                            Log.i(TAG, "SUCCESS");
                            Log.i(TAG, String.valueOf(metadataResults.getResults().size()));
                        }

                        @Override
                        public void failure(RetrofitError retrofitError) {
                            Log.i(TAG, "FAILURE");
                            Log.i(TAG, retrofitError.getMessage());
                        }
                    });
                }
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.dashboard, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_dashboard, container, false);
            return rootView;
        }
    }

}
