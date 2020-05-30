# shoesyourself
Mobile E-commerce made with Android.


The user can create an account, login, browse trough the items, have a cart and purchase.




The server (made with Node.js) offers a REST API that communicate with JSON (made with Express.js) and allows persistence in a MongoDb database. 


I worked in the files and functions that allows the app to make REST calls to the external server.


Here is the path to the android source code:  
_shoesyourself\android\app\src\main\java\com\example\shoesyourself_


All the files in the folders _entities_, _helpers_, _managers_ and _services_ was made by me.


The modifications made in the app will be made in the internal database (SQLite) and then synchronize with the external server.


We had a 1 week deadline for this project.


# How to install 
1. You need to have [Node.js]( https://nodejs.org/en/) otherwise install the LTS version.
2. Clone or download the repository.
3. On the server path : _shoesyourself/server_ do _npm ci_ et once all the dependencies are installed do _npm start_
4. Open the project with [Android Studio](https://developer.android.com/studio).
5. In the seciton _/helpers/DataBaseHelper_, change URL_SERVER with your ipv4 adress like this: _http://yourIpAdress:8080_ (do not forget to put :8080)
6. Connect your phone (Android) 
7. Press _f6_ in Android Studio.
8. Open the app on your phone. 
