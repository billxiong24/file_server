import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class FileServerClient {


    public FileServerClient() {}

    /**
     * Makes request to server and saves response to file.
     * @param url url to make GET request to
     * @param filename name of file to save to
     */
    public void makeRequest(String url, String filename) throws IOException {
        URL urlObj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) urlObj.openConnection();
        con.setRequestMethod("GET");
        InputStream inputStream = con.getInputStream();
        FileOutputStream outputStream = new FileOutputStream(filename);

        int bytesRead;
        byte[] buffer = new byte[8192];
        while ((bytesRead = inputStream.read(buffer)) > 0) {
            outputStream.write(buffer, 0, bytesRead);
        }

        outputStream.close();
        inputStream.close();
        con.disconnect();
    }

    public static void main(String args[]) throws Exception {
        FileServerClient client = new FileServerClient();
        //instead of sunrise.jpg, use your own file
        String url = "http://localhost:3000/file?file_name=birdmountain.jpg";
        client.makeRequest(url, "test.jpg");
    }
}
