package com.unbosque.edu.co.calendarioFifa.beans;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
 
@ManagedBean
public class ImageView {
     
    private List<String> images;
     
    @PostConstruct
    public void init() {
        images = new ArrayList<String>();
        for (int i = 1; i <= 4; i++) {
            images.add("noti" + i + ".jpg");
        }
    }
 
    public List<String> getImages() {
        return images;
    }
}