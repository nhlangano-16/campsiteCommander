package com.example.imad5112a2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import android.util.Log

class MainActivity : AppCompatActivity() {

  private lateinit var edtItem: EditText
  private lateinit var edtCategory: EditText
  private lateinit var edtQuantity: EditText
  private lateinit var edtComments: EditText
  private lateinit var txtTotalItems: TextView

  private lateinit var btnAdd: Button
  private lateinit var btnView: Button
  private lateinit var btnExit: Button

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    edtItem = findViewById(R.id.edtItem)
    edtCategory = findViewById(R.id.edtCategory)
    edtQuantity = findViewById(R.id.edtQuantity)
    edtComments = findViewById(R.id.edtComments)
    txtTotalItems = findViewById(R.id.txtTotalItems)

    btnAdd = findViewById(R.id.btnAdd)
    btnView = findViewById(R.id.btnView)
    btnExit = findViewById(R.id.btnExit)

    // Calculate initial total from the sample data
    updateTotalItems()

    btnAdd.setOnClickListener {
      addPackingItem()
    }

    btnView.setOnClickListener {
      val intent = Intent(this, PackingListActivity::class.java)
      startActivity(intent)
    }

    btnExit.setOnClickListener {
      finishAffinity()
    }
  }

  // Ensure the total updates if we return from the list screen
  override fun onResume() {
    super.onResume()
    updateTotalItems()
  }

  private fun addPackingItem() {

    val item = edtItem.text.toString().trim()
    val category = edtCategory.text.toString().trim()
    val quantityText = edtQuantity.text.toString().trim()
    val comments = edtComments.text.toString().trim()

    if (item.isEmpty() || category.isEmpty() || quantityText.isEmpty() || comments.isEmpty()) {
      Toast.makeText(this, "Please complete all fields", Toast.LENGTH_SHORT).show()
      return
    }

    val quantity = quantityText.toIntOrNull()

    if (quantity == null || quantity <= 0) {
      Toast.makeText(this, "Quantity must be greater than 0", Toast.LENGTH_SHORT).show()
      return
    }

    if (PackingData.count >= PackingData.MAX_ITEMS) {
      Log.d("PackingApp","User tried adding to a full list")
      Toast.makeText(this, "Packing list is full", Toast.LENGTH_SHORT).show()
      return
    }

    PackingData.itemArray[PackingData.count] = item
    PackingData.categoryArray[PackingData.count] = category
    PackingData.quantityArray[PackingData.count] = quantity
    PackingData.commentsArray[PackingData.count] = comments

    PackingData.count++
    Log.d("PackingApp", "Successfully added gear: $item. Unique items: ${PackingData.count}")
    Toast.makeText(this, "Gear Added Successfully", Toast.LENGTH_SHORT).show()

    clearFields()
    updateTotalItems() // Update the loop calculation
  }

  private fun clearFields() {
    edtItem.text.clear()
    edtCategory.text.clear()
    edtQuantity.text.clear()
    edtComments.text.clear()
  }

  /**
   * Requirement: Calculates Total Items Packed using a loop
   */
    private fun updateTotalItems() {
      var totalQuantity = 0
      for (i in 0 until PackingData.count) {
        totalQuantity += PackingData.quantityArray[i]
      }
      txtTotalItems.text = "Total Items Packed: $totalQuantity"
    }
  }
