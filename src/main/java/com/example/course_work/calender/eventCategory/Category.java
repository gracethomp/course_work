package com.example.course_work.calender.eventCategory;
import java.util.ArrayList;

public class Category {

    public String categoryName;

    public Category() {
       super();
    }

    public String getCategoryName () {
        return this.categoryName;
    }
    public void setCategoryName(String name1) {
        this.categoryName = name1;
    }
    private final ArrayList<Category> categoryList = new ArrayList<>();
    public Category(String categoryName) {
        this.categoryName = categoryName;
    }
    public void choose(Category curCategory) {
        showAll();
        if(!categoryList.contains(curCategory)) categoryList.add(curCategory);
        //return  categoryName;
    }
    public void add(Category category1){
        categoryList.add(category1);
    }
    public void edit(Category Category1){
        this.categoryName = Category1.getCategoryName();
    }
    public String printName() {
        return this.categoryName;
    }
    public void showAll(){
        for(Category e : categoryList){
            this.printName();
        }
       //return categoryList;
    }
    public void delete(int index){
        categoryList.remove(index);
    }
}
