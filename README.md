
# Klivvr Internship Android Task

## Project Overview

This Android project demonstrates an application that renders a list of countries and provides an optimized search functionality. Instead of a simple search, binary search is utilized to determine the upper and lower bounds for more efficient range-based searches.

## Features

- **Country List Display:** A list of countries is displayed using a `RecyclerView`.
- **Optimized Search:** Binary search is used to efficiently determine the upper and lower bounds of the search range within the sorted list of countries.
- **Smooth UI/UX:** The app offers a user-friendly interface for displaying and searching countries.

## Technology Stack

- **Programming Language:** Kotlin
- **Android SDK:** API Level 21+
- **UI Components:** RecyclerView, SearchView

## Binary Search for Range Determination

In this project, binary search is leveraged to find the upper and lower bounds for a search range, making the search operation more efficient than linear search. This technique is especially useful when you need to find all occurrences of countries that match certain criteria within a sorted list.

### How It Works

1. **Pre-sorted List:** The list of countries is pre-sorted alphabetically.
2. **Lower Bound:** Binary search is used to find the first occurrence of the target or the closest match.
3. **Upper Bound:** Similarly, binary search is used to find the last occurrence of the target or the closest match.
4. **Range Extraction:** The determined bounds are then used to extract all relevant countries within the specified range.
