package com.fitforever.ailment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fitforever.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Sarvex Jatasra on 9/16/2015.
 */
public class AilmentFragment extends Fragment {

  @Bind(R.id.recycler_view) RecyclerView recyclerView;
  private AilmentAdapter adapter;
  private List<Ailment> ailments;

  private static final String ARG_PARAM1 = "param1";
  private static final String ARG_PARAM2 = "param2";

  private String mParam1;
  private String mParam2;

  private OnFragmentInteractionListener listener;

  public static AilmentFragment newInstance(String param1, String param2) {
    AilmentFragment fragment = new AilmentFragment();
    Bundle args = new Bundle();
    args.putString(ARG_PARAM1, param1);
    args.putString(ARG_PARAM2, param2);
    fragment.setArguments(args);
    return fragment;
  }

  /**
   * Mandatory empty constructor for the fragment manager to instantiate the
   * fragment (e.g. upon screen orientation changes).
   */
  public AilmentFragment() {
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    // TODO dummy data
    ailments = new ArrayList<>(10);
    ailments.add(new Ailment(R.drawable.ic_menu, "Ailment 1", "Ailment One Description"));
    ailments.add(new Ailment(R.drawable.ic_menu, "Ailment 2", "Ailment Two Description"));
    ailments.add(new Ailment(R.drawable.ic_menu, "Ailment 3", "Ailment Three Description"));
    ailments.add(new Ailment(R.drawable.ic_menu, "Ailment 4", "Ailment Four Description"));
    ailments.add(new Ailment(R.drawable.ic_menu, "Ailment 5", "Ailment Five Description"));
    ailments.add(new Ailment(R.drawable.ic_menu, "Ailment 6", "Ailment Six Description"));
    ailments.add(new Ailment(R.drawable.ic_menu, "Ailment 7", "Ailment Seven Description"));
    ailments.add(new Ailment(R.drawable.ic_menu, "Ailment 8", "Ailment Eight Description"));
    ailments.add(new Ailment(R.drawable.ic_menu, "Ailment 9", "Ailment Nine Description"));

    if (getArguments() != null) {
      mParam1 = getArguments().getString(ARG_PARAM1);
      mParam2 = getArguments().getString(ARG_PARAM2);
    }
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_ailment, container, false);
    ButterKnife.bind(this, view);
    recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    recyclerView.setItemAnimator(new DefaultItemAnimator());
    recyclerView.setHasFixedSize(true);
    recyclerView.setAdapter(new AilmentAdapter(ailments));

    return view;
  }

  @Override
  public void onAttach(Context context) {
    super.onAttach(context);
    try {
      listener = (OnFragmentInteractionListener) context;
    } catch (ClassCastException e) {
      throw new ClassCastException(context.toString() + " must implement OnFragmentInteractionListener");
    }
  }

  @Override
  public void onDetach() {
    super.onDetach();
    listener = null;
  }


  /**
   * This interface must be implemented by activities that contain this
   * fragment to allow an interaction in this fragment to be communicated
   * to the activity and potentially other fragments contained in that
   * activity.
   * <p/>
   * See the Android Training lesson <a href=
   * "http://developer.android.com/training/basics/fragments/communicating.html"
   * >Communicating with Other Fragments</a> for more information.
   */
  public interface OnFragmentInteractionListener {
    public void onFragmentInteraction(String id);
  }

}
