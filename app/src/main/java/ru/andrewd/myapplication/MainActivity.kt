package ru.andrewd.myapplication

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import ru.andrewd.myapplication.R
import kotlin.math.roundToLong

class MainActivity : AppCompatActivity() {
    private var itemName: EditText? = null
    private var itemCost: EditText? = null
    private var itemCount: EditText? = null
    private var itemX: EditText? = null
    private var itemY: EditText? = null
    private var itemZ: EditText? = null
    private var itemDoubleSide: CheckBox? = null
    private var itemK: EditText? = null
    private var itemResultText: EditText? = null
    private var btnCopy: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        itemName = findViewById(R.id.item_name)
        itemCost = findViewById(R.id.item_cost)
        itemCount = findViewById(R.id.item_count)
        itemX = findViewById(R.id.item_x)
        itemY = findViewById(R.id.item_y)
        itemZ = findViewById(R.id.item_z)
        itemDoubleSide = findViewById(R.id.item_double_side)
        itemK = findViewById(R.id.item_k)
        itemResultText = findViewById(R.id.item_result_text)
        btnCopy = findViewById(R.id.btn_copy)

        itemName?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(
                s: CharSequence, start: Int,
                count: Int, after: Int
            ) {
            }

            override fun onTextChanged(
                s: CharSequence, start: Int,
                before: Int, count: Int
            ) {
                buildResult()
            }

            override fun afterTextChanged(p0: Editable?) {
            }
        })
        itemCost?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(
                s: CharSequence, start: Int,
                count: Int, after: Int
            ) {
            }

            override fun onTextChanged(
                s: CharSequence, start: Int,
                before: Int, count: Int
            ) {
                buildResult()
            }

            override fun afterTextChanged(p0: Editable?) {
            }
        })
        itemCount?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(
                s: CharSequence, start: Int,
                count: Int, after: Int
            ) {
            }

            override fun onTextChanged(
                s: CharSequence, start: Int,
                before: Int, count: Int
            ) {
                buildResult()
            }

            override fun afterTextChanged(p0: Editable?) {
            }
        })
        itemX?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(
                s: CharSequence, start: Int,
                count: Int, after: Int
            ) {
            }

            override fun onTextChanged(
                s: CharSequence, start: Int,
                before: Int, count: Int
            ) {
                buildResult()
            }

            override fun afterTextChanged(p0: Editable?) {
            }
        })
        itemY?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(
                s: CharSequence, start: Int,
                count: Int, after: Int
            ) {
            }

            override fun onTextChanged(
                s: CharSequence, start: Int,
                before: Int, count: Int
            ) {
                buildResult()
            }

            override fun afterTextChanged(p0: Editable?) {
            }
        })
        itemZ?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(
                s: CharSequence, start: Int,
                count: Int, after: Int
            ) {
            }

            override fun onTextChanged(
                s: CharSequence, start: Int,
                before: Int, count: Int
            ) {
                buildResult()
            }

            override fun afterTextChanged(p0: Editable?) {
            }
        })
        itemDoubleSide?.setOnClickListener {
            buildResult()
        }
        itemK?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(
                s: CharSequence, start: Int,
                count: Int, after: Int
            ) {
            }

            override fun onTextChanged(
                s: CharSequence, start: Int,
                before: Int, count: Int
            ) {
                buildResult()
            }

            override fun afterTextChanged(p0: Editable?) {
            }
        })
        btnCopy?.setOnClickListener {
            copy2clipboard()
        }
    }

    fun buildResult() {
        val name = itemName?.text.toString()
        val x = str2Double(str = itemX?.text.toString())
        val y = str2Double(str = itemY?.text.toString())
        val z = str2Double(str = itemZ?.text.toString())
        val cost = str2Double(str = itemCost?.text.toString())
        val count = str2Double(str = itemCount?.text.toString())
        val ds = if (itemDoubleSide?.isChecked == true) 2 else 1
        val k = str2Double(str = itemK?.text.toString())
        val totalAmount: Double = if (z == 0.0) {  //  2D
            count * cost * x * y * ds * k
        } else {  // 3D
            count * cost * (x * y + x * z + y * z) * 2 * ds * k
        }
        val resultText = "$name ($x x $y x $z) - $count шт. = ${totalAmount.roundToLong()} руб."
        itemResultText?.setText(resultText)
    }

    private fun copy2clipboard() {
        val resultText = itemResultText?.text.toString()
        val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clip: ClipData = ClipData.newPlainText("SurfaceCalc", resultText)
        clipboard.setPrimaryClip(clip)
    }
}

private fun str2Double(str: String): Double {
    return try {
        java.lang.Double.valueOf(str)
    } catch(ex: Exception) {
        0.0
    }
}
