package me.codeseeker.getgfwlist;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;

public class GetFileUntil {
    //gfwUrl
    private final String GFWLISTURL = "https://raw.githubusercontent.com/gfwlist/gfwlist/master/gfwlist.txt";

    /*
    * get gfwList file
    * */
    public void getGfwFile(String filePath) throws IOException {
        URL url=null;
        HttpURLConnection httpURLConnection=null;
        InputStream inputStream =null;
        InputStreamReader inputStreamReader =null;
        BufferedReader bufferedReader =null;
        BufferedWriter bufferedWriter =null;
        try {
            url = new URL(GFWLISTURL);
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.connect();

            //read getRes
            inputStream = httpURLConnection.getInputStream();
            inputStreamReader = new InputStreamReader(inputStream);
            bufferedReader = new BufferedReader(inputStreamReader);

            //write res to file
            bufferedWriter = new BufferedWriter(new FileWriter(checkFile(filePath),true));

            String res = "";
            while ((res=bufferedReader.readLine())!=null) {
                byte[] base64DecodeBytes = Base64.getDecoder().decode(res);
                String getDecodeRes = new String(base64DecodeBytes);
                bufferedWriter.write(new String(getDecodeRes.getBytes(),"UTF-8"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            httpURLConnection.disconnect();
            bufferedWriter.close();
            inputStream.close();
            inputStreamReader.close();
            bufferedReader.close();
            System.out.println("Write Done!");
        }
    }

    /*
    * if file is exists,delete file;
    * file not exists,create file
    * */
    public String checkFile(String filePath) throws IOException {
        File file =null;
        try {
            file=new File(filePath);
            if (file.exists()) {
                file.delete();
            } else {
                file.createNewFile();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return filePath;
    }


}
