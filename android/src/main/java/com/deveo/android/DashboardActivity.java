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
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.deveo.android.accounts.AccountAuthenticator;
import com.deveo.android.api.ApiManager;
import com.deveo.android.api.DeveoService;
import com.deveo.android.api.MetadataResults;
import com.deveo.android.core.Project;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.novoda.imageloader.core.loader.Loader;
import com.novoda.imageloader.core.model.ImageTagFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class DashboardActivity extends ListActivity {

    static final String TAG = DashboardActivity.class.getSimpleName();

    static final int REQUEST_AUTHENTICATE_ACCOUNT = 1337;

    private DeveoService service;

    private Loader imageLoader;

    private ImageTagFactory imageTagFactory;

    private Context context;

    private SimpleAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        context = this;
        service = ApiManager.getService();
        imageLoader = DeveoApplication.getImageManager().getLoader();
        imageTagFactory = ImageTagFactory.newInstance(this, R.drawable.bg_img_loading);

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
                            List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();

                            for (Project project : metadataResults.getResults()) {
                                Map<String, Object> projectEntry = new HashMap<String, Object>();
                                projectEntry.put("image", project.getAvatar().get("medium"));
                                projectEntry.put("project", project);
                                data.add(projectEntry);
                            }

                            String[] from = new String[]{"image", "project"};
                            int[] to = new int[]{R.id.image_list_item_avatar, R.id.image_list_item_label};
                            adapter = new SimpleAdapter(context, data, R.layout.image_list_item, from, to);
                            adapter.setViewBinder(getViewBinder());
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

    private SimpleAdapter.ViewBinder getViewBinder() {
        return new SimpleAdapter.ViewBinder() {

            @Override
            public boolean setViewValue(View view, Object data, String textRepresentation) {
                switch (view.getId()) {
                    case R.id.image_list_item_avatar:
                        view.setTag(imageTagFactory.build(getAvatarUrl(data.toString()), context));
                        imageLoader.load((ImageView) view);
                        break;

                    case R.id.image_list_item_label:
                        ((TextView) view).setText(((Project) data).getName());
                        break;
                }
                return true;
            }

        };
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        // There's no way to check whether or not the Map should have generic parameters.
        @SuppressWarnings("unchecked")
        HashMap<String, Object> projectEntry = (HashMap<String, Object>) adapter.getItem(position);

        Project project = (Project) projectEntry.get("project");
        String json = gson.toJson(project);

        Toast.makeText(context, json, Toast.LENGTH_LONG).show();
    }

    private String getAvatarUrl(String original) {
        StringBuilder builder = new StringBuilder();
        builder.append("%s%s?");
        builder.append(String.format("plugin_key=%s&", "f048f1f3a611631a228c7f7c57037744"));
        builder.append(String.format("company_key=%s&", "e29b5239082e73223228b1cd7254e9b8"));
        builder.append(String.format("account_key=%s", "05a261503c6afa4f257b032074737396"));
        return String.format(builder.toString(), ApiManager.API_URL, original);
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
