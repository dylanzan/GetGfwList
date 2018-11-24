package me.codeseeker.getgfwlist;

import java.io.IOException;

public class DoMainTest {
    public static void main(String[] args) throws IOException {
        GetFileUntil getFileUntil=new GetFileUntil();
        getFileUntil.getGfwFile("/media/data/IdeaProjects/GetGfwList/src/me/codeseeker/getgfwlist/pac.txt");
    }
}
