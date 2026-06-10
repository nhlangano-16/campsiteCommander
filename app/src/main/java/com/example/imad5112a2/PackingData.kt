package com.example.imad5112a2
object PackingData {

    // Maximum number of packing items
    const val MAX_ITEMS = 20

    // Parallel arrays
    val itemArray = arrayOfNulls<String>(MAX_ITEMS)
    val categoryArray = arrayOfNulls<String>(MAX_ITEMS)
    val commentsArray = arrayOfNulls<String>(MAX_ITEMS)
    val quantityArray = IntArray(MAX_ITEMS)

    // Keeps track of number of items stored
    var count = 0

    // Initialise the arrays with sample data as required
    init {
        itemArray[0] = "Tent"
        categoryArray[0] = "Shelter"
        quantityArray[0] = 1
        commentsArray[0] = "4-person waterproof"

        itemArray[1] = "Marshmallows"
        categoryArray[1] = "Food"
        quantityArray[1] = 3
        commentsArray[1] = "For S'mores (Mega size)"

        itemArray[2] = "Flashlight"
        categoryArray[2] = "Safety"
        quantityArray[2] = 2
        commentsArray[2] = "Check batteries (AA)"

        count = 3 // Starting with 3 items pre-loaded
    }
}
