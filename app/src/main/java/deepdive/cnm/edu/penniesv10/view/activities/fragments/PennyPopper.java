package deepdive.cnm.edu.penniesv10.view.activities.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.GridLayout.Spec;
import android.widget.Toast;
import de.hdodenhof.circleimageview.CircleImageView;
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
    user = ((MainActivity) getActivity()).getUser();
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {

    GridLayout grid = (GridLayout) inflater
        .inflate(R.layout.fragment_penny_popper, container, false);

    grid.setRowCount(gridRows);
    grid.setColumnCount(gridColumns);

    grid.removeAllViews();

    CircleImageView[] pennies = new CircleImageView[gridRows*gridColumns];

    for(int i = 0,col = 0,row = 0;i<pennies.length;i++,col++){

      if(col==gridColumns){
        col = 0;
        row++;
      }

      pennies[i] = new CircleImageView(getContext());
      pennies[i].setImageResource(R.drawable.ic_penny);

      Spec rowSpan = GridLayout.spec(row, 1);
      Spec colSpan = GridLayout.spec(col, 1);

      GridLayout.LayoutParams gridParam = new GridLayout.LayoutParams(
          rowSpan, colSpan);
      gridParam.setMargins(10,10,10,10);
      gridParam.setGravity(Gravity.CENTER);

      grid.addView(pennies[i], gridParam);

    }
    Toast.makeText(getContext(), String.valueOf(pennies.length), Toast.LENGTH_SHORT).show();

    return grid;
  }

  public void checkForWin() {
  }
}