package com.example.simplenoteapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


public class SimpleNote extends AppCompatActivity {

    private static final String TAG = "main_activity";
    int LAUNCH_SECOND_ACTIVITY = 2;
    FloatingActionButton mFloatingActionButton;
    float dx, dy;

    RecyclerView recyclerView_View;
    RecyclerView.Adapter recyclerView_Adapter;
    RecyclerView.LayoutManager recyclerView_LayoutManager;
    ArrayList<Notes> loadNotesList;

    //sql instantiation to handle db
    SqlHelper dbHelper = new SqlHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //set recyclerview
        setRecyclerView_View();
        //swipe delete
        ItemTouchHelper.SimpleCallback simpleItemTouchCallback =
                new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
                    @Override
                    public boolean onMove(RecyclerView recyclerView,
                                          RecyclerView.ViewHolder viewHolder,
                                          RecyclerView.ViewHolder target) {
                        return false;
                    }

                    @Override
                    public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
                        //Remove swiped item from list and notify the RecyclerView
                        int position = viewHolder.getAdapterPosition();
                        Notes delete_file = loadNotesList.get(position);
                        int delete = delete_file.getSlno();
//                        dbHelper.deleteNotes(delete);
//                        recyclerView_View.removeViewAt(position);
//                        recyclerView_Adapter.notifyItemRemoved(position);
//                        recyclerView_Adapter.notifyItemRangeChanged(position,loadNotesList.size());
                    }

                    Paint paint;

                    public Paint getPaintObject() {

                        if (paint == null)
                            paint = new Paint();
                        return paint;
                    }

                    public static final float ALPHA_FULL = 1.0f;

                    public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
                        if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {

                            View itemView = viewHolder.itemView;
                            Paint p = getPaintObject();
                            if (dX > 0) {
                                p.setARGB(255, 255, 0, 0);
                                c.drawRect((float) itemView.getLeft(), (float) itemView.getTop(), dX,
                                        (float) itemView.getBottom(), p);
                            }
                            final float alpha = ALPHA_FULL - Math.abs(dX) / (float) viewHolder.itemView.getWidth();
                            viewHolder.itemView.setAlpha(alpha);
                            viewHolder.itemView.setTranslationX(dX);

                        } else {
                            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
                        }
                    }
                };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView_View);

        mFloatingActionButton = (FloatingActionButton) findViewById(R.id.fab);
        mFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addItem();
            }
        });
    }
    public  void setRecyclerView_View(){
        //list loading
        if (dbHelper.listNotes() != null) {
            loadNotesList = dbHelper.listNotes();
        }
        //set Recyclerview
        recyclerView_View = findViewById(R.id.recycle_view);
        recyclerView_View.setHasFixedSize(true);
        recyclerView_LayoutManager = new LinearLayoutManager(this);
        ((LinearLayoutManager) recyclerView_LayoutManager).setReverseLayout(true);
        recyclerView_Adapter = new Radapter(SimpleNote.this, loadNotesList);
        recyclerView_View.setLayoutManager(recyclerView_LayoutManager);
        recyclerView_View.setAdapter(recyclerView_Adapter);
    }
    public void addItem() {
        Intent create_intent = new Intent(SimpleNote.this, InputSection.class);
        startActivityForResult(create_intent, LAUNCH_SECOND_ACTIVITY);
    }

    @Override
    protected void onResume() {
        super.onResume();
        setRecyclerView_View();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        recyclerView_Adapter.notifyDataSetChanged();
        if (requestCode == LAUNCH_SECOND_ACTIVITY) {
            if (resultCode == Activity.RESULT_OK) {
                Log.d(TAG, "onActivityResult: getbacked");
                setRecyclerView_View();
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                Log.d(TAG, "onActivityResult: " + "not getbacked");
            }
        }
    }
}


