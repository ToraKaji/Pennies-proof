package deepdive.cnm.edu.penniesv10.view.activities.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;

import deepdive.cnm.edu.penniesv10.R;
import deepdive.cnm.edu.penniesv10.model.entities.User;
import deepdive.cnm.edu.penniesv10.view.activities.MainActivity;

public class PennyPopper extends Fragment {

  User user;

  private int gridRows;
  private int gridColumns;

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    Bundle bundle = getArguments();
    gridColumns = bundle.getInt("cols", 1);
    gridRows = bundle.getInt("rows", 1);
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    user = ((MainActivity) getActivity()).getUser();
    GridLayout grid = (GridLayout) inflater
        .inflate(R.layout.fragment_penny_popper, container, false);

    grid.setRowCount(gridRows); grid.setColumnCount(gridColumns);

    for(int i = 0;i < gridColumns * gridRows;i++){

    }

    return grid;
  }

  public void checkForWin() {
  }
}