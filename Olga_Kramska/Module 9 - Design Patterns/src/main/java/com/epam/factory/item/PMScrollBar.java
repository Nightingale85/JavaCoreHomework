package com.epam.factory.item;

/**
 * Created by Olga Kramska on 24-Apr-16.
 */
public class PMScrollBar implements ScrollBar {
    @Override
    public void draw() {
        System.out.println("Drawing scroll bar in a Presentation Manager stile");
    }
}
