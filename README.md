## Basic Node.js file server with Java client
Run ```npm install``` to install dependencies.  
Start server with ```node app```  
### Routes
```GET / ``` home page, where you can upload files.  
```POST /upload ``` upload file to server.  
```GET /file?file_name=FILE_NAME``` fetch file by name of ```FILE_NAME```.   

### Java client
Compile with ```javac FileServerClient.java```  
Run with ```java FileServerClient.java```  


