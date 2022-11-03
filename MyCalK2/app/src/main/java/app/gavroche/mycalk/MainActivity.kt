package app.gavroche.mycalk

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.google.android.material.button.MaterialButton
import com.fathzer.soft.javaluator.DoubleEvaluator

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onClick(v: View?){
        var button = v as MaterialButton
        var resultTv = findViewById<TextView>(R.id.result_tv)
        var solutionTv = findViewById<TextView>(R.id.solution_tv)

        var old = solutionTv.text.toString()

        var buttonc = button.text.toString()

        if (buttonc == "AC"){
            solutionTv.text = ""
            resultTv.text ="0"
            return
        }

        try{
            if (old == "Err" || old == "Infinity"){
                old = ""
            }
        }
        catch (e: Exception){}

        if (buttonc == "DEL"){
            try{
                var new = old.substring(0, old.length - 1)
                solutionTv.text = new
                return
            }
            catch (e: Exception) {
                return
            }
        }

        if (buttonc == "="){
            solutionTv.text = evaluate(old)
            resultTv.text = ""
            return
        }

        var new = old + buttonc
        var res = evaluate(new)

        if (new.startsWith("0")) {
            new = new.substring(1, new.length)
        }


        solutionTv.text = new

        if (res != "Err"){
            resultTv.text = res
        }

    }

    private fun evaluate(data: String): String {

        return try {
            if (data == "Infinity"){
                "Infinity"
            } else{
                var evaluator = DoubleEvaluator()
                var res = evaluator.evaluate(data).toString()
                if (res.endsWith(".0")){
                    res = res.substring(0, res.length - 2)
                }
                res
            }
        } catch (e: Exception){
            "Err"
        }
    }
}