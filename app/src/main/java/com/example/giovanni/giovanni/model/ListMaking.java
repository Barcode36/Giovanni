package com.example.giovanni.giovanni.model;

import java.util.ArrayList;
import java.util.List;

import static com.example.giovanni.giovanni.model.Body.HEADER_TYPE;
import static com.example.giovanni.giovanni.model.Body.ITEM_TYPE;

public class ListMaking {

    public static List<Body> getData() {
        List<Body> list = new ArrayList<>();

        list.add(new Body(HEADER_TYPE, "Title 1"));
        list.add(new Body(ITEM_TYPE, "Row 1", "Description 1"));
        list.add(new Body(ITEM_TYPE, "Row 2", "Description 2"));
        list.add(new Body(HEADER_TYPE, "Title 2"));
        list.add(new Body(ITEM_TYPE, "Row 3", "Description 3"));
        list.add(new Body(ITEM_TYPE, "Row 4", "Description 4"));
        list.add(new Body(ITEM_TYPE, "Row 5", "Description 5"));
        list.add(new Body(HEADER_TYPE, "Title 3"));
        list.add(new Body(ITEM_TYPE, "Row 6", "Description 6"));
        list.add(new Body(ITEM_TYPE, "Row 7", "Description 7"));
        list.add(new Body(HEADER_TYPE, "Title 4"));
        list.add(new Body(ITEM_TYPE, "Row 8", "Description 8"));
        list.add(new Body(ITEM_TYPE, "Row 9", "Description 9"));
        list.add(new Body(ITEM_TYPE, "Row 10", "Description 10"));
        list.add(new Body(HEADER_TYPE, "Title 5"));
        list.add(new Body(ITEM_TYPE, "Row 11", "Description 11"));
        list.add(new Body(ITEM_TYPE, "Row 12", "Description 12"));
        list.add(new Body(HEADER_TYPE, "Title 6"));
        list.add(new Body(ITEM_TYPE, "Row 13", "Description 13"));
        list.add(new Body(ITEM_TYPE, "Row 14", "Description 14"));
        list.add(new Body(ITEM_TYPE, "Row 15", "Description 15"));
        list.add(new Body(HEADER_TYPE, "Title 7"));
        list.add(new Body(ITEM_TYPE, "Row 16", "Description 16"));
        list.add(new Body(ITEM_TYPE, "Row 17", "Description 17"));
        list.add(new Body(HEADER_TYPE, "Title 8"));
        list.add(new Body(ITEM_TYPE, "Row 18", "Description 18"));
        list.add(new Body(ITEM_TYPE, "Row 19", "Description 19"));
        list.add(new Body(ITEM_TYPE, "Row 20", "Description 20"));

        return list;
    }
}