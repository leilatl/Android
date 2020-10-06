package com.example.calculator3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn0.setOnClickListener { appendOnClick(true, "0") }
        btn1.setOnClickListener { appendOnClick(true, "1") }
        btn2.setOnClickListener { appendOnClick(true, "2") }
        btn3.setOnClickListener { appendOnClick(true, "3") }
        btn4.setOnClickListener { appendOnClick(true, "4") }
        btn5.setOnClickListener { appendOnClick(true, "5") }
        btn6.setOnClickListener { appendOnClick(true, "6") }
        btn7.setOnClickListener { appendOnClick(true, "7") }
        btn8.setOnClickListener { appendOnClick(true, "8") }
        btn9.setOnClickListener { appendOnClick(true, "9") }
        btnAC.setOnClickListener { clear() }
        btnDel.setOnClickListener { clear() }
        btnDiv.setOnClickListener { appendOnClick(false, "/") }
        btnMin.setOnClickListener { appendOnClick(false, "-") }
        btnPlus.setOnClickListener { appendOnClick(false, "+") }
        btnMult.setOnClickListener { appendOnClick(false, "*") }
        btnDot.setOnClickListener { appendOnClick(true, ".") }
        btnEquals.setOnClickListener { calculate() }

        if(savedInstanceState != null){
            var res = savedInstanceState.getString("result")
            var cal = savedInstanceState.getString("calculation")
            result.setText(res)
            calculation.setText(cal)
        }
    }
    fun appendOnClick(clear: Boolean, string: String){

        calculation.append(result.text)
        calculation.append(string)
        result.setText("")


    }
    fun clear(){
        calculation.setText("")
        result.setText("")
    }
    fun calculate(){
        try {

            val input = ExpressionBuilder(calculation.text.toString()).build()
            val output = input.evaluate()
            val longOutput = output.toLong()
            if(output == longOutput.toDouble()){
                result.setText(longOutput.toString())
            }else{
                result.setText(output.toString())
            }

        }catch (e: Exception){
            Toast.makeText(this@MainActivity,e.message,Toast.LENGTH_LONG ).show()

        }
    }
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("result", result.toString())
        outState.putString("calculation", calculation.toString())

    }

}
