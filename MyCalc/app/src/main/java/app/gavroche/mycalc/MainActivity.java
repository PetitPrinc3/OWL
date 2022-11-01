package app.gavroche.mycalc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView resultTv, solutionTv;
    MaterialButton buttonC, buttonBrackOpen, buttonBrackClose;
    MaterialButton buttonDiv, buttonMul, buttonPlu, buttonMin, buttonEqu, buttonCom, buttonBac;
    MaterialButton button0, button1, button2, button3, button4, button5, button6, button7, button8, button9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultTv = findViewById(R.id.result_tv);
        solutionTv = findViewById(R.id.solution_tv);

        assignId(buttonC, R.id.button_ac);
        assignId(buttonBrackOpen, R.id.button_open_bracket);
        assignId(buttonBrackClose, R.id.button_close_bracket);
        assignId(buttonDiv, R.id.button_divide);
        assignId(buttonMul, R.id.button_multiply);
        assignId(buttonPlu, R.id.button_plus);
        assignId(buttonMin, R.id.button_minus);
        assignId(buttonEqu, R.id.button_equal);
        assignId(buttonCom, R.id.button_coma);
        assignId(buttonBac, R.id.button_back);
        assignId(button0, R.id.button_0);
        assignId(button1, R.id.button_1);
        assignId(button2, R.id.button_2);
        assignId(button3, R.id.button_3);
        assignId(button4, R.id.button_4);
        assignId(button5, R.id.button_5);
        assignId(button6, R.id.button_6);
        assignId(button7, R.id.button_7);
        assignId(button8, R.id.button_8);
        assignId(button9, R.id.button_9);

    }

    void assignId(MaterialButton btn, int id){
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        MaterialButton button = (MaterialButton) view;
        String buttonText = button.getText().toString();
        String calc = solutionTv.getText().toString();

        if (calc.startsWith("0")){
            calc = calc.substring(1, calc.length());
        }

        if (buttonText.equals("C")) {
            solutionTv.setText("");
            resultTv.setText("0");
            return;
        }

        if (buttonText.equals("=")){
            solutionTv.setText(getRes(solutionTv.getText().toString()));
            resultTv.setText("");
            return;
        }

        if (buttonText.equals("DEL")){
            calc = calc.substring(0, calc.length() - 1);
            solutionTv.setText(calc);
            solutionTv.setText(calc);

            String result = getRes(calc);

            if (!result.equals("ERR")){
                resultTv.setText(result);
            }

            return;
        }



        calc = calc + buttonText;

        solutionTv.setText(calc);

        String result = getRes(calc);

        if (!result.equals("ERR")){
            resultTv.setText(result);
        }

    }

    String getRes(String data){
        try {
            Context context  = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();
            String result =  context.evaluateString(scriptable,data,"Javascript",1,null).toString();
            if (result.endsWith(".0")){
                result = result.replace(".0", "");
            }
            return result;
        }
        catch (Exception e){
            return "ERR";
        }
    }
}