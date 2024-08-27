package com.example.learning_andriod.repository.fake

import com.example.learning_andriod.R.drawable.almacen_
import com.example.learning_andriod.R.drawable.carretilla_
import com.example.learning_andriod.R.drawable.carretilla_bulk_
import com.example.learning_andriod.R.drawable.cosecha_
import com.example.learning_andriod.dao.FakeMenuDao
import com.example.learning_andriod.domain.MenuItem

class FakeMenuRepository : FakeMenuDao{
    override fun getAllMenuItem(): List<MenuItem> {
        val fakeMenuItemList: MutableList<MenuItem> = mutableListOf()

        val menuItem1 = MenuItem(id = 1, title = "Almacen", imageResUrl = almacen_)
        val menuItem2 = MenuItem(id = 2, title = "Carretilla", imageResUrl = carretilla_)
        val menuItem3 = MenuItem(id = 3, title = "Carretilla Bulk", imageResUrl = carretilla_bulk_)
        val menuItem4 = MenuItem(id = 4, title = "Cosecha", imageResUrl = cosecha_)
        val menuItem5 = MenuItem(id = 5, title = "Almacen", imageResUrl = almacen_)
        val menuItem6 = MenuItem(id = 6, title = "Carretilla", imageResUrl = carretilla_)
        val menuItem7 = MenuItem(id = 7, title = "Carretilla Bulk", imageResUrl = carretilla_bulk_)
        val menuItem8 = MenuItem(id = 8, title = "Cosecha", imageResUrl = cosecha_)
        val menuItem9 = MenuItem(id = 9, title = "Almacen", imageResUrl = almacen_)
        val menuItem10 = MenuItem(id = 10, title = "Carretilla", imageResUrl = carretilla_)
        val menuItem11 = MenuItem(id = 11, title = "Carretilla Bulk", imageResUrl = carretilla_bulk_)
        val menuItem12 = MenuItem(id = 12, title = "Cosecha", imageResUrl = cosecha_)

        fakeMenuItemList.add(menuItem1)
        fakeMenuItemList.add(menuItem2)
        fakeMenuItemList.add(menuItem3)
        fakeMenuItemList.add(menuItem4)
        fakeMenuItemList.add(menuItem5)
        fakeMenuItemList.add(menuItem6)
        fakeMenuItemList.add(menuItem7)
        fakeMenuItemList.add(menuItem8)
        fakeMenuItemList.add(menuItem9)
        fakeMenuItemList.add(menuItem10)
        fakeMenuItemList.add(menuItem11)
        fakeMenuItemList.add(menuItem12)

        return fakeMenuItemList
    }

}