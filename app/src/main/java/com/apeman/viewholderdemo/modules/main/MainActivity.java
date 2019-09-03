package com.apeman.viewholderdemo.modules.main;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.apeman.viewholderdemo.base.AutoWindJsonAdapter;
import com.apeman.viewholderdemo.R;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rlv;
    private AutoWindJsonAdapter adapter = new MainAdapter("一级From MainActivity");
    private Gson gson = new Gson();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rlv = findViewById(R.id.rlv);
        rlv.setLayoutManager(new LinearLayoutManager(this));
        rlv.setAdapter(adapter);
        adapter.setData(getData());
    }

    //TODO: 测试数据
    private List<JSONObject> getData() {
        List<JSONObject> res = new LinkedList<>();
        for (Object genTestObj : genTestObjs()) {
            JSONObject j = toJSON(gson.toJson(genTestObj));
            if (j != null) {
                res.add(j);
            }
        }
        return res;
    }

    private JSONObject toJSON(String jsonStr) {
        try {
            return new JSONObject(jsonStr);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    private List<Object> genTestObjs() {
        List<Object> data = new LinkedList<>();
        data.add(new ListBean(getListBean()));
        data.add(new ArticleBean("测试文章"));
        data.add(new ImageBean("https://a.zdmimg.com/201908/23/5d5f8b5c37fae876.jpg_a640.jpg"));
        data.add(new Userbean("大萝卜",
                "https://a.zdmimg.com/201908/23/5d5f955dd3c638170.jpg_h640.jpg"));
        data.add(new ArticleBean("测试文章"));
        data.add(new ImageBean("https://a.zdmimg.com/201908/23/5d5f9a6f70bf06385.jpg_h640.jpg"));
        data.add(new ArticleBean("天气预报文章"));
        data.add(new ArticleBean("测试文章"));
        data.add(new ImageBean("https://a.zdmimg.com/201908/23/5d5f9a6f70bf06385.jpg_h640.jpg"));
        data.add(new ArticleBean("天气预报文章"));
        return data;
    }

    private List<Object> getListBean() {
        List<Object> data = new LinkedList<>();
        data.add(new SubBean("https://a.zdmimg.com/201908/23/5d5f8b5c37fae876.jpg_a640.jpg"));
        data.add(new SubBean("https://a.zdmimg.com/201908/23/5d5f8b5c37fae876.jpg_a640.jpg"));
        data.add(new SubBean("https://a.zdmimg.com/201908/23/5d5f8b5c37fae876.jpg_a640.jpg"));
        return data;
    }

    //TODO: 用于测试
    static class ArticleBean {
        int cellType = 1;
        String title;

        public ArticleBean(String title) {
            this.title = title;
        }
    }

    //TODO: 用于测试
    static class ImageBean {
        int cellType = 2;
        String url;

        public ImageBean(String url) {
            this.url = url;
        }
    }

    //TODO: 用于测试
    static class Userbean {
        int cellType = 3;
        String name;
        String url;

        public Userbean(String name, String url) {
            this.name = name;
            this.url = url;
        }
    }

    //TODO: 用于测试
    static class ListBean {
        int cellType = 4;
        List<Object> list;

        public ListBean(List<Object> list) {
            this.list = list;
        }
    }

    static class SubBean {
        int cellType = 5;
        String url;

        public SubBean(String url) {
            this.cellType = cellType;
            this.url = url;
        }
    }

}
