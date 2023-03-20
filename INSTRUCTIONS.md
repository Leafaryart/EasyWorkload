# How SQLite Works for this Project
Link to SQLite Documentation: https://www.sqlitetutorial.net/

## How to Get Database Working
### Install SQLite on Computer
1. Follow this video: https://www.youtube.com/watch?v=wXEZZ2JT3-k&ab_channel=ProgrammingKnowledge
2. The folder 'essentials' has all the sqlite file you need to download

### Adding SQLite JDBC Driver to NetBeans
1. On NetBeans, right click on the 'Dependencies' folder on the Project Explorer. Click Add dependency
2. Set groupID to anyvalue, artifact ID to 'sqlite-jdbc', and version to '3.14.0.0'.
3. Manually install the driver into the project. The driver is a .jar file under the essentials folder. Install that.
4. Wait for NetBeans to finish installing.

### app_storage.md
app_storage.md is where all the data about the app is stored. To connect app_storage to the program, you'll need to get the absolute path of the app_storage and give it to the program.

In my computer, the absolute path goes something like: "C:\\Users\\Ray Rafael Abenido\\Desktop\\Rafael\\College\\Ateneo\\Third Year - Second Semester\\CSCI 42 O\\project\\app_storage.db"

The class EasyWorkload in package main should have the absolute path set already. Simply replace the absolute path when working with your project.

## Other Notes on SQLite (useful reading)
### VS Code and the sqlite extension
1. The sqlite extension by alexcvzz is a very useful tool. I use it to add tables, delete them, add in random sample data, and to also check if the app is returning the correct results.
2. Download it on VS Code and you will find it useful. Really useful.

## Notes on SQLite Limitations
1. There is an *extremely* limited number of data attributes the database can store. It can only store integers, floating points (SQLite stores it as a 'REAL' data attribute), strings, NULL, and BLOB.
2. To store date and time values, you'll need to somehow convert them into strings.
3. To store boolean, use = for false and 1 for true.

