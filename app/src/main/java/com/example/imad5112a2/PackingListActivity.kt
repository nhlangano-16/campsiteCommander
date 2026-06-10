package com.example.imad5112a2

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class PackingListActivity : AppCompatActivity() {

  private lateinit var btnDisplayList: Button
  private lateinit var btnReturn: Button
  private lateinit var txtResults: TextView

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_packing_list)

    btnDisplayList = findViewById(R.id.btnDisplayList)
    btnReturn = findViewById(R.id.btnReturn)
    txtResults = findViewById(R.id.txtResults)

    btnDisplayList.setOnClickListener {
      txtResults.text = displayPackingList()
    }

    btnReturn.setOnClickListener {
      finish() // Acts as "Back to Base", closing this screen to show Main
    }
  }

  private fun displayPackingList(): String {

    if (PackingData.count == 0) {
      return "No gear has been added yet."
    }

    var output = ""

    for (i in 0 until PackingData.count) {
      output += "Item: ${PackingData.itemArray[i]}\n" +
      "Category: ${PackingData.categoryArray[i]}\n" +
      "Quantity: ${PackingData.quantityArray[i]}\n" +
      "Notes: ${PackingData.commentsArray[i]}\n\n"
    }

    return output
  }
}
