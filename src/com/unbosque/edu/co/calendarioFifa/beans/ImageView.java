package com.unbosque.edu.co.calendarioFifa.beans;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
 
/**
 * The Class ImageView.
 */
@ManagedBean
@ViewScoped
public class ImageView {
     
    /** The images. */
    private List<String> images;
     
    /**
     * Inits the.
     */
    @PostConstruct
    public void init() {
        images = new ArrayList<String>();
//        for (int i = 1; i <= 4; i++) {
//            images.add("noti" + i + ".jpg");
//        }
        images.add("pdf.png");
        images.add("goleador.png");
    }
 
    /**
     * Gets the images.
     *
     * @return the images
     */
    public List<String> getImages() {
        return images;
    }
}