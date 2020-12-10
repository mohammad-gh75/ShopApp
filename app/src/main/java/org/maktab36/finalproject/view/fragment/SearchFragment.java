package org.maktab36.finalproject.view.fragment;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.maktab36.finalproject.R;
import org.maktab36.finalproject.adapters.ListSearchProductAdapter;
import org.maktab36.finalproject.databinding.FragmentSearchBinding;
import org.maktab36.finalproject.viewmodel.SearchViewModel;


public class SearchFragment extends Fragment {
    public static final String ARG_QUERY="searchQuery";
    private String mQuery;
    private SearchViewModel mSearchViewModel;
    private FragmentSearchBinding mSearchBinding;
    private ListSearchProductAdapter mSearchProductAdapter;


    public SearchFragment() {
        // Required empty public constructor
    }

    public static SearchFragment newInstance(String query) {
        SearchFragment fragment = new SearchFragment();
        Bundle args = new Bundle();
        args.putString(ARG_QUERY, query);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mQuery=getArguments().getString(ARG_QUERY);
        }
        mSearchViewModel=new ViewModelProvider(this).get(SearchViewModel.class);
        mSearchViewModel.getSearchProductsLiveData().observe(this,products -> setAdapter());
        mSearchViewModel.getSelectedProductLiveData().observe(this,product ->{
            ProductFragment fragment = ProductFragment.newInstance(product.getId());
            requireActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();
        });
        mSearchViewModel.fetchSearchProductsLiveData(mQuery);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mSearchBinding= DataBindingUtil
                .inflate(inflater,R.layout.fragment_search,container,false);

        mSearchBinding.listSearchProduct.setLayoutManager(new LinearLayoutManager(requireContext()));


        return mSearchBinding.getRoot();
    }

    private void setAdapter(){
        if(mSearchProductAdapter==null){
            mSearchProductAdapter=new ListSearchProductAdapter(mSearchViewModel);
            mSearchBinding.listSearchProduct.setAdapter(mSearchProductAdapter);
        }else{
            mSearchProductAdapter.notifyDataSetChanged();
        }
    }
}