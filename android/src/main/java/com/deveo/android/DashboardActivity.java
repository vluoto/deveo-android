package com.deveo.android;

import android.accounts.AccountManager;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.deveo.android.accounts.AccountAuthenticator;
import com.deveo.android.api.ApiManager;
import com.deveo.android.api.DeveoService;
import com.deveo.android.api.MetadataResults;
import com.deveo.android.core.Project;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class DashboardActivity extends ListActivity {

    static final String TAG = DashboardActivity.class.getSimpleName();

    static final int REQUEST_AUTHENTICATE_ACCOUNT = 1337;

    private DeveoService service;

    private Context context;

    private ArrayAdapter<Project> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        service = ApiManager.getService();
        context = this;

        Intent intent = new Intent(this, LoginActivity.class);
        intent.putExtra(LoginActivity.PARAM_AUTHTOKEN_TYPE, AccountAuthenticator.ACCOUNT_TYPE);

        startActivityForResult(intent, REQUEST_AUTHENTICATE_ACCOUNT);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_AUTHENTICATE_ACCOUNT:
                if (data != null && data.getExtras() != null) {
                    Bundle extras = data.getExtras();

                    String login = extras.getString(AccountManager.KEY_ACCOUNT_NAME);
                    String authz = getAuthorizationHeader(extras);

                    service.getUserProjects(authz, login, new Callback<MetadataResults<Project>>() {
                        @Override
                        public void success(MetadataResults<Project> metadataResults, Response response) {
                            adapter = new ArrayAdapter<Project>(context, android.R.layout.simple_list_item_1, metadataResults.getResults());
                            getListView().setAdapter(adapter);
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
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Project project = (Project) l.getAdapter().getItem(position);
        String json = gson.toJson(project);

        Toast.makeText(context, json, Toast.LENGTH_LONG).show();
    }

    private String getAuthorizationHeader(Bundle bundle) {
        StringBuilder builder = new StringBuilder();
        builder.append("deveo ");
        builder.append(String.format("plugin_key='%s'", "3c94d47d6257ca0d3bc54a9b6a91aa64"));
        builder.append(String.format("company_key='%s'", bundle.getString(LoginActivity.PARAM_COMPANY_KEY)));
        builder.append(String.format("account_key='%s'", bundle.getString(LoginActivity.PARAM_ACCOUNT_KEY)));
        return builder.toString();
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

}
