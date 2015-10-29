package com.example.czf.heightcalculatoractivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Button calculatorButton;
    private EditText weightEditText;
    private CheckBox manCheckBox,womanCheckBox;
    private TextView resultTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
        calculatorButton = (Button) findViewById(R.id.calculator);
        weightEditText = (EditText) findViewById(R.id.weight);
        manCheckBox = (CheckBox) findViewById(R.id.man);
        womanCheckBox = (CheckBox) findViewById(R.id.woman);
        resultTextView = (TextView) findViewById(R.id.result);

    }
    protected void OnStart() {
        super.onStart();
        registerEvent();
    }

    private void registerEvent(){




        calculatorButton.setOnClickListener(new View.OnClickListener() {


            public void onClick(View view) {
                if (!weightEditText.getText().toString().trim().equals("")) {
                    if (manCheckBox.isChecked() || womanCheckBox.isChecked()) {
                        Double weight = Double.parseDouble(weightEditText.getText().toString());
                        StringBuffer sb = new StringBuffer();
                        sb.append("---------评估结果--------\n");
                        if (manCheckBox.isChecked()) {
                            sb.append("男性的标准身高：");
                            double result = evaluateHeight(weight, "男");
                            sb.append((int) result + "(厘米)");

                        }
                        if (womanCheckBox.isChecked()) {
                            sb.append("女性的标准身高：");
                            double result = evaluateHeight(weight, "女");
                            sb.append((int) result + "(厘米)");
                        }
                        resultTextView.setText(sb.toString());
                    } else {
                        showMessage("请选择性别！");


                    }
                } else {
                    showMessage("请输入体重！");
                }
            }
            });
        }
            private double evaluateHeight(Double weight, String sex) {
                double height;
                if (sex == "男") {
                    height = 170 - (62 - weight) / 0.6;
                } else {
                    height = 158 - (52 - weight) / 0.6;
                }
                return height;
            }

            private void showMessage(String message) {
                AlertDialog alert = new AlertDialog.Builder(this).create();

                alert.setTitle("系统信息");
                alert.setMessage(message);
                alert.setButton("确定", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int whichButton) {

                    }
                });
                alert.show();
            }
        }



