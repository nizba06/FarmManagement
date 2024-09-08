**Farm Management API**
This project provides an API to manage farm reports, including the ability to view reports for farms by season. The application includes preloaded data, but APIs for adding new data have also been created.

**Features**
Get Farm Report: Retrieve a farm's report for a specific season in a human-readable text format.
Data Preloaded: The database is preloaded with sample data for demonstration purposes.
APIs to Add Data: While the API includes preloaded data, endpoints to add new farms and reports are available as well.

**Preloaded Data**
The application comes with preloaded data for demonstration. You can run the application and retrieve reports for the following preloaded farm and season:

Farm: MyFarm
Season: Spring 2024

**Running the Application**
Step 1: Clone the Repository
git clone https://github.com/nizba06/FarmManagement/tree/main/src/main/java/com/farmmanagement.git

Step 2: Build the Application
mvn clean install

Step 3: Run the Application
mvn spring-boot:run

**How to Get Farm Report**
Get Farm Report by Season
To retrieve a farm report for a specific farm and season, you can use the following endpoint:

URL: http://localhost:8080/api/reports/farm/{farmName}/season/{season}
Method: GET

curl http://localhost:8080/api/reports/farm/MyFarm/season/Spring%202024
