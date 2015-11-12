package edu.wpi.total_joint_replacement;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link GeneralInfoFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link GeneralInfoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GeneralInfoFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment GeneralInfoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static GeneralInfoFragment newInstance(String param1, String param2) {
        GeneralInfoFragment fragment = new GeneralInfoFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public GeneralInfoFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_general_info, container, false);


        Spinner ethnicitySpinner = (Spinner) view.findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> ethAdapter = ArrayAdapter.createFromResource(view.getContext(),
                R.array.ethnicities, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        ethAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        ethnicitySpinner.setAdapter(ethAdapter);

        Spinner languageSpinner = (Spinner) view.findViewById(R.id.spinner2);
        ArrayAdapter<CharSequence> langAdapter = ArrayAdapter.createFromResource(view.getContext(),
                R.array.languages, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        langAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        languageSpinner.setAdapter(langAdapter);

        Spinner genderSpinner = (Spinner) view.findViewById(R.id.genderSpinner);
        ArrayAdapter<CharSequence> genderAdapter = ArrayAdapter.createFromResource(view.getContext(),
                R.array.gender, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        genderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        genderSpinner.setAdapter(genderAdapter);

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
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
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

}
