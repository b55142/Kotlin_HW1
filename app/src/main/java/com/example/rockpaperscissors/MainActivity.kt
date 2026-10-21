
package com.example.rockpaperscissors

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(
            findViewById(R.id.main)
        ) { v, insets ->
            val bars = insets.getInsets(
                WindowInsetsCompat.Type.systemBars()
            )
            v.setPadding(
                bars.left, bars.top, bars.right, bars.bottom
            )
            insets
        }

        // 取得 XML 畫面元件
        val edName = findViewById<EditText>(R.id.ed_name)
        val tvText = findViewById<TextView>(R.id.tv_text)
        val tvName = findViewById<TextView>(R.id.tv_name)
        val tvWinner = findViewById<TextView>(R.id.tv_winner)
        val tvMmora = findViewById<TextView>(R.id.tv_mmora)
        val tvCmora = findViewById<TextView>(R.id.tv_cmora)

        val btnScissor = findViewById<RadioButton>(R.id.btn_scissor)
        val btnStone = findViewById<RadioButton>(R.id.btn_stone)
        val btnPaper = findViewById<RadioButton>(R.id.btn_paper)
        val btnMora = findViewById<Button>(R.id.btn_mora)

        // 剪刀 = 0、石頭 = 1、布 = 2
        val choices = arrayOf("剪刀", "石頭", "布")

        // 設定猜拳按鈕的監聽事件
        btnMora.setOnClickListener {

            // 讀取玩家姓名
            val playerName = edName.text.toString().trim()

            if (playerName.isEmpty()) {
                tvText.text = "請輸入玩家姓名"
                return@setOnClickListener
            }

            // 判斷玩家選擇
            val player = when {
                btnScissor.isChecked -> 0
                btnStone.isChecked -> 1
                btnPaper.isChecked -> 2
                else -> {
                    tvText.text = "請選擇剪刀、石頭或布"
                    return@setOnClickListener
                }
            }

            // 電腦隨機出拳
            val computer = Random.nextInt(3)

            // 顯示雙方出拳
            tvName.text = playerName
            tvMmora.text = choices[player]
            tvCmora.text = choices[computer]

            // 判斷勝負
            if (player == computer) {
                tvWinner.text = "平手"
                tvText.text = "平手！再來一次！"

            } else if (
                (player == 0 && computer == 2) ||
                (player == 1 && computer == 0) ||
                (player == 2 && computer == 1)
            ) {
                tvWinner.text = playerName
                tvText.text = "$playerName 獲勝！"

            } else {
                tvWinner.text = "電腦"
                tvText.text = "電腦獲勝！"
            }
        }
    }
}
