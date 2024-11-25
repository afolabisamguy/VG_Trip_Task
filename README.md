# Android Developer Task  

This project was created as part of the Android Developer Interview Test. It implements a basic trip management app with a UI based on the provided Figma design and integrates with a mock API for creating and viewing trips.  

## Features  
- Responsive UI closely aligned with the Figma design.  
- Functionality to create and view trips via API calls.  
- Error handling and user feedback for API operations.  

## Setup Instructions  

### Prerequisites  
- Android Studio (latest version recommended).  
- Java 11 or later.  
- An Android device or emulator for testing.  

### Steps to Run the Project  
1. Clone this repository to your local machine:  
   ```bash  
   git clone https://github.com/afolabisamguy/VG_Trip_Task.git  
   cd VG_Trip_Task
   ```  
2. Open the project in Android Studio.  
3. Sync the Gradle files.  
4. Build and run the application on an emulator or connected device.  

### API Endpoints  
This project uses the mock API provided by Beeceptor for CRUD operations:  
- **Create a Trip**: `[POST] https://triptask.free.beeceptor.com/trips`  
- **View Trips**: `[GET] https://triptask.free.beeceptor.com/trips`  

*Note: Since Beeceptor does not persist data, trip data will reset after API calls are made.*  

---

## Known Limitations  
- The Figma design provided was incomplete and lacked assets. As a result, some improvisation was necessary to achieve the UI.  
- The mock API does not support data persistence, limiting real-time CRUD functionalities.  

---

## Future Improvements  
- Enhance UI with additional features and refinements.  
- Implement a persistent backend API for robust CRUD operations.  
- Add unit and integration tests to ensure app reliability.  

---

## APK  
The APK can be downloaded from the link below:  
[https://afolabisamguy.github.io/my_app_download/](#)  

---

## Libraries Used  
- **Ktor**: For API integration.  
- **ViewModel and LiveData**: For managing UI-related data.  
- **Material Design Components**: For styling and UI responsiveness.  

---

## Feedback  
Feel free to raise issues or provide feedback through GitHub or contact me directly at [Your Email Address].  

---

### Author  
Samuel Afolabi
afolabisamguy@gmail.com

--- 
