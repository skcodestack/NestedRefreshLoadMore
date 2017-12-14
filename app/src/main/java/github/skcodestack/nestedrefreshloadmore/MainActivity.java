package github.skcodestack.nestedrefreshloadmore;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import github.skcodestack.nestedrefreshloadmore.nested.NestedWithRefreshActivity;
import github.skcodestack.nestedrefreshloadmore.no_nested.NoNestedWithRefreshActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void noNestedClick(View view){
        startActivity(new Intent(view.getContext(), NoNestedWithRefreshActivity.class));
    }



    public void nestedClick(View view){
        startActivity(new Intent(view.getContext(), NestedWithRefreshActivity.class));
    }
}
