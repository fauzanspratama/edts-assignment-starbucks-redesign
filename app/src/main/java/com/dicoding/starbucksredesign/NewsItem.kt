package com.dicoding.starbucksredesign

data class NewsItem(
    val category: String,  // e.g., "Coffee and Product"
    val title: String,     // e.g., "Starbucks Reserve Hot Honey Ginger Spritz"
    val date: String,      // e.g., "January 8, 2025"
    val readTime: String,   // e.g., "2 min read"
    val imageResId: Int  // Resource ID for the news image
)
