package com.muybaby.food.utils;

import java.util.Base64;

public class Recipe {
    public static String content;

    public static void main(String[] args) {
        Recipe recipe = new Recipe();
        recipe.content="IyMg5b+F5aSH5Y6f5paZ5ZKM5bel5YW3CgotIOiPoOiPnAotIOm4oeibiwotIOmjn+eUqOayuQotIOmjn+eUqOebkAoKIyMg6K6h566XCgrmjInnhacgMSDkurrnmoTku73ph4/vvJoKCi0g6I+g6I+cIDM1MGcKLSDpuKHom4sgMiDkuKoKLSDpo5/nlKjmsrkgMTVtbAotIOmjn+eUqOebkCA1ZwoKIyMg5pON5L2cCgotIOiPoOiPnOWOu+ague+8jOa0l+WHgO+8jOaUvuWcqOevruWtkOmHjO+8jOeEr+awtAotIOWwhum4oeibi+aJk+WFpeeil+S4re+8jOaQheWMgAotIOeDremUhe+8jOWKoOWFpSAxMG1sIOayuQotIOayueeDreWQju+8jOWAkuWFpem4oeibi+a2su+8jOS4reeBq+e/u+eCkiAxNSDnp5LvvIzlhYjnhY7miJDom4vppbzvvIznhLblkI7lho3nlKjplIXpk7LliIfmiJDlsI/lnZcKLSDlhbPngavvvIzlsIbpuKHom4vlnZcg55ub5Yiw55uY5a2Q5Lit77yM5LiN6KaB5rSX6ZSFCi0g6YeN5paw5byA54Gr77yM5YCS5YWlIDVtbCDmsrnvvIzmsrnng63lkI7vvIzmlL7lhaXoj6Doj5zvvIzlpKfngasg57+754KSIDE1IOenkuWQju+8jOWAkuWFpem4oeibi+Wdl++8jOe/u+eCkuWdh+WMgAotIOWKoOWFpSA1ZyDnm5DjgIExMDBtbCDppa7nlKjmsLTvvIzlpKfngasg57+754KSIDEwIOenkgotIOWFs+eBq++8jOebm+ebmA==";
        recipe.setMarkdownContent(content);
        System.out.println(recipe.content);
    }
    public void setMarkdownContent(String content) {
        this.content = new String(Base64.getDecoder().decode(content));
    }
}