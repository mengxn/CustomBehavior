package me.codego.custombehavior.autohide;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import me.codego.custombehavior.R;

public class TestAutoHideActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private MyAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_auto_hide);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new MyAdapter(this);
        mAdapter.setDataList(getDataList());
        mRecyclerView.setAdapter(mAdapter);
    }

    public List<String> getDataList() {
        List<String> dataList = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            dataList.add("item " + i);
        }
        return dataList;
    }
}
