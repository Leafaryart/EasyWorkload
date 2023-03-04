# How SQLite Works for this Project
Link to SQLite Documentation: https://www.sqlitetutorial.net/

Use VSCode for now. Its helpful for the project.
## Notes on Installing SQLite and JDBC
### Installing SQLite
1. Follow this video: https://www.youtube.com/watch?v=wXEZZ2JT3-k&ab_channel=ProgrammingKnowledge
2. The folder 'essentials' has all the stuff you need to install SQLite

### Installing SQLite JDBC Driver
1. The driver should be installed in the project now. Check if its part of the libraries being referenced by the project. ('Reference Libraries' in VSCode)
2. If its not referenced, the .jar version of the driver is in the 'essentials' folder.

### Setting Up
1. Use VSCode for now. VSCode will make things easier for you.
2. Install the extension 'SQLite' by alexcvzz. This extension will help you run SQL scripts inside of VSCode.
3. Open Command Palette (shortcut: Ctrl + Shift + P) and type 'SQLite' to see all the commands available. Play around a little with the commmands to get used to it.
4. Open the database app_storage.db with the open command of SQLite
4. Write an SQL script. You can write about anything. Use this link as your reference: https://www.sqlitetutorial.net

## Notes on SQLite Limitations
1. There is an *extremely* limited number of data attributes the database can store. It can only store integers, floating points (SQLite stores it as a 'REAL' data attribute), strings, NULL, and BLOB.
2. To store date and time values, you'll need to somehow convert them into strings.
3. To store boolean, use = for false and 1 for true.

