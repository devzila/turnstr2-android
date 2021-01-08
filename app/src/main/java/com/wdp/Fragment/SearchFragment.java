package com.wdp.Fragment;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.wdp.Adapter.SearchAdapter;
import com.wdp.ApiServices.MeStoryApiService;
import com.wdp.ApiServices.SearchApiService;
import com.wdp.Interface.ApiConstants;
import com.wdp.Interface.ApiResponseListner;
import com.wdp.Interface.SuperCastClass;
import com.wdp.Modal.LoginResDataModal;
import com.wdp.Modal.SearchDataModal;
import com.wdp.Utility.CommonSharedPreference;
import com.wdp.Utility.NetworkCheck;
import com.wdp.turnstr.R;

import java.util.ArrayList;

public class SearchFragment extends Fragment implements ApiResponseListner {
    ArrayList<SearchDataModal.DataBean.UsersBean> dataList = new ArrayList<>();
    RecyclerView recyclerView;
    SearchAdapter searchAdapter;
    EditText edt_search;
    CommonSharedPreference commonSharedPreference = new CommonSharedPreference();
    String url;
    int page=1;
    String str="a";
    SearchDataModal searchDataModal;
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_search, container, false);
        initView(root);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        searchAdapter = new SearchAdapter(getActivity(), dataList);
        recyclerView.setAdapter(searchAdapter);


        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0) {
                    if (!recyclerView.canScrollVertically(1)) {
                        if (searchDataModal != null) {
                            if (Integer.parseInt(searchDataModal.getData().getPagination().getCurrent_page()) < Integer.parseInt(searchDataModal.getData().getPagination().getTotal_pages())) {
                                loadMore();
                            }

                        }
                    }

                }
            }
        });

        edt_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String message = charSequence.toString();
                if (message.trim().length() == 0){
                    str = "a";
                    page =1;
                    connectSearch(ApiConstants.Search  +str,page,false);
                }
                else if (message.trim().length() >1){
                    str = message;
                    url ="";
                    page =1;
                    url = ApiConstants.Search  +str;
                    connectSearch(url,page,false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        url = ApiConstants.Search + str;
        connectSearch(url,page,true);
        return root;
    }
    private void loadMore() {
        page++;
        connectSearch(url,page,true);
    }
    private void initView(View view) {
        recyclerView = view.findViewById(R.id.recyclerView);
        edt_search = view.findViewById(R.id.edt_search);
    }

    private void connectSearch(String url,int page,boolean flag) {
        LoginResDataModal loginResDataModal = commonSharedPreference.getSubscriberLoginSharedPref(getActivity());
        if (NetworkCheck.isConnected(getActivity())) {
            SearchApiService meStoryApiService = new SearchApiService(getActivity());
            meStoryApiService.Connect(url,loginResDataModal.getData().getToken(),String.valueOf(page),flag,this);

        } else {
            Toast.makeText(getActivity(), getResources().getString(R.string.server_data_not_found), Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void onSuccess(String Tag, SuperCastClass superCastClass) {
        if (Tag.equalsIgnoreCase(ApiConstants.search_view_TAG)){
            SearchDataModal searchDataModal = (SearchDataModal) superCastClass;
            if (page ==1){
                 dataList.clear();
                 dataList.addAll(searchDataModal.getData().getUsers());
                //searchAdapter.notifyDataSetChanged();
            }
            else {
                dataList.addAll(searchDataModal.getData().getUsers());
               // searchAdapter.notifyDataSetChanged();
            }

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
            recyclerView.setLayoutManager(linearLayoutManager);
            searchAdapter = new SearchAdapter(getActivity(), dataList);
            recyclerView.setAdapter(searchAdapter);

        }

    }

    @Override
    public void onFailure(String message) {
        Toast.makeText(getActivity(),message,Toast.LENGTH_LONG).show();

    }

    @Override
    public void onException(String message) {
        Toast.makeText(getActivity(),message,Toast.LENGTH_LONG).show();
    }
}