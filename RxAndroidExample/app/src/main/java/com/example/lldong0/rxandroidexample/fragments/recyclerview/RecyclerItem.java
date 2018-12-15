package com.example.lldong0.rxandroidexample.fragments.recyclerview;

import android.graphics.drawable.Drawable;

/**
 * recyclerView 에 표시할 데이터 셋 클래스
 */
//@Data
//@AllArgsConstructor(staticName = "of")
class RecyclerItem {
    private Drawable image;
    private String title;

    public RecyclerItem(Drawable image, String title) {
        this.image = image;
        this.title = title;
    }

    public Drawable getImage() {
        return image;
    }

    public void setImage(Drawable image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
