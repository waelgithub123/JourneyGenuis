# JourneyGenius

## Overview
Planning a trip can be overwhelming with numerous details to track. **JourneyGenius** is a desktop application designed to simplify travel planning by providing an intuitive interface to manage itineraries, budgets, accommodations, transportation, and more. Our goal is to take the stress out of travel planning so users can focus on enjoying their trips.

## Features

### 1. **Budget Tracking**
- Users can input their maximum travel budget.
- Every expense (e.g., flights, activities, visas) will be deducted from the budget.
- Expenses will be displayed like a receipt.
- If the total budget falls below a certain threshold, the number will turn red as a warning.

### 2. **Destinations**
- Displays travel information including departure location, arrival location, and corresponding dates.
- Data will be retrieved from the **Google Flights API** or **Amadeus API** as a fallback.

### 3. **Activities (Optional)**
- Users can input activity details such as name, description, and cost.
- Activities will be stored in a database.
- Clicking an activity icon will reveal more details.

### 4. **Accommodations**
- Users can specify accommodation types (e.g., Resort, Airbnb, Hotel, Hostel).
- Accommodation details will be stored in an SQL database.
- Display includes the name and potentially an image of the accommodation.

### 5. **Transportation**
- Users can select their mode of transportation (e.g., Uber, walking, renting a car).
- The total cost of transportation will be recorded in the database.
- Different transportation options will be displayed for clarity.

### 6. **Weather Forecast**
- Retrieves weather data from the **Weather.com API** based on the user’s travel dates and destination.
- Displays weather conditions for the specified time period.

### 7. **Travel Visa Information**
- Retrieves visa requirements from a government visa API.
- Informs the user about necessary travel visas for their selected destination.

## Technologies Used
- **Backend:** SQL database for storing user input and travel details
- **Platform:** Desktop application

## Data Sources
- **User Input:** Budget, activities, accommodations, transportation details.
- **APIs:** Flights, weather, visa requirements.

## Use Cases
- Individuals who want to plan trips with detailed itineraries.
- Users who need an easy way to track travel expenses.
- Travelers looking for real-time weather updates and visa requirements.

## Installation & Setup
Install the executable file

## Contact
For questions or suggestions, please reach out via GitHub: [JourneyGenius Repository](https://github.com/waelgithub123/JourneyGenuis).

