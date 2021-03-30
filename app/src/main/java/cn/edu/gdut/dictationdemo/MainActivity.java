package cn.edu.gdut.dictationdemo;

import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

public class MainActivity extends AppCompatActivity {

    NavController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        controller = Navigation.findNavController(this, R.id.fragment);
        NavigationUI.setupActionBarWithNavController(this, controller);
    }

    @Override
    public boolean onSupportNavigateUp() {
        if (controller.getCurrentDestination().getId() == R.id.contentFragment) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(getString(R.string.quit_dialog_name));
            builder.setPositiveButton(R.string.dialog_positive_msg, (dialog, which) -> controller.navigateUp());
            builder.setNegativeButton(R.string.dialog_negeative_msg, (dialog, which) -> { });
            AlertDialog dialog = builder.create();
            dialog.show();
        } else  if (controller.getCurrentDestination().getId() == R.id.homeFragment) {
            finish();
        }else {
            controller.navigate(R.id.homeFragment);
        }

        return super.onSupportNavigateUp();
//        return super.onSupportNavigateUp();
    }

    @Override
    public void onBackPressed() {
        onSupportNavigateUp();
    }
}