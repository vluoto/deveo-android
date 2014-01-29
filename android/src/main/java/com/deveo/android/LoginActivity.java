/*
 * Copyright 2014 Deveo Ltd
 *
 * Licensed under the Apache License, Version 2.0 (the “License”);
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an “AS IS” BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.deveo.android;

import android.accounts.Account;
import android.accounts.AccountAuthenticatorActivity;
import android.accounts.AccountManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.deveo.android.accounts.AccountAuthenticator;
import com.deveo.android.api.DeveoClient;
import com.deveo.android.core.Session;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class LoginActivity extends AccountAuthenticatorActivity {

    private static final String TAG = LoginActivity.class.getSimpleName();

    public static final String PARAM_AUTHTOKEN_TYPE = "authtokenType";

    public static final String PARAM_COMPANY_ID = "companyId";

    public static final String PARAM_COMPANY_KEY = "companyKey";

    public static final String PARAM_ACCOUNT_KEY = "accountKey";

    private DeveoClient client;

    private AccountManager accountManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        client = DeveoApplication.getClient();

        accountManager = AccountManager.get(this);
        Account[] accounts = accountManager.getAccountsByType(AccountAuthenticator.ACCOUNT_TYPE);
        if (accounts.length > 0) {
            Account account = accounts[0];

            String company = accountManager.getUserData(account, PARAM_COMPANY_ID);
            String password = accountManager.getPassword(account);

            authenticate(company, account.name, password);
        }
    }

    public void loginClickHandler(View view) {
        EditText editTextCompany = (EditText) findViewById(R.id.edit_text_company_main);
        EditText editTextLogin = (EditText) findViewById(R.id.edit_text_login_main);
        EditText editTextPassword = (EditText) findViewById(R.id.edit_text_password_main);

        final String company = editTextCompany.getText().toString();
        final String login = editTextLogin.getText().toString();
        final String password = editTextPassword.getText().toString();

        authenticate(company, login, password);
    }

    protected void authenticate(final String company, final String login, final String password) {
        client.authenticate(company, login, password, new Callback<Session>() {
            @Override
            public void success(Session session, Response response) {
                Account account = new Account(login, AccountAuthenticator.ACCOUNT_TYPE);

                Bundle bundle = new Bundle();
                bundle.putString(PARAM_COMPANY_ID, session.getCompanyId());

                accountManager.addAccountExplicitly(account, password, bundle);

                finishLogin(session.getAccountId(), session.getCompanyKey(), session.getAccountKey());
            }

            @Override
            public void failure(RetrofitError retrofitError) {
                Log.e(TAG, retrofitError.getResponse().getReason());
            }
        });
    }

    protected void finishLogin(String accountName, String companyKey, String accountKey) {
        Intent intent = new Intent();
        intent.putExtra(AccountManager.KEY_ACCOUNT_NAME, accountName);
        intent.putExtra(AccountManager.KEY_ACCOUNT_TYPE, AccountAuthenticator.ACCOUNT_TYPE);
        intent.putExtra(AccountManager.KEY_AUTHTOKEN, AccountAuthenticator.ACCOUNT_TYPE);
        intent.putExtra(PARAM_ACCOUNT_KEY, accountKey);
        intent.putExtra(PARAM_COMPANY_KEY, companyKey);

        setAccountAuthenticatorResult(intent.getExtras());
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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
