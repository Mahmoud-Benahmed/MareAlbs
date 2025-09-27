package com.marealbs.app;

import android.os.Bundle;
import android.widget.Toast;
import com.getcapacitor.BridgeActivity;
import java.util.ArrayList;

public class MainActivity extends BridgeActivity {
    private DataBaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        dbHelper = new DataBaseHelper(this);

        setContentView(R.layout.activity_main);

        insertSampleData();
    }

    private void insertSampleData() {
        boolean porteInserted = dbHelper.insertPorte("Porte1", "2023-10-25", 100);
        Toast.makeText(this, "Porte inserted: " + porteInserted, Toast.LENGTH_SHORT).show();


        boolean employeurInserted = dbHelper.insertEmployeur(12345678, "Doe", "John", 5, 1000);
        Toast.makeText(this, "Employeur inserted: " + employeurInserted, Toast.LENGTH_SHORT).show();


        ArrayList<Integer> bassins = new ArrayList<>();
        bassins.add(1);
        bassins.add(2);
        boolean moretInserted = dbHelper.insertMoret(true, "Destination1", bassins);
        Toast.makeText(this, "Moret inserted: " + moretInserted, Toast.LENGTH_SHORT).show();


        boolean bassinInserted = dbHelper.insertBassin(1, false);
        Toast.makeText(this, "Bassin inserted: " + bassinInserted, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroy() {

        dbHelper.close();
        super.onDestroy();
    }
}