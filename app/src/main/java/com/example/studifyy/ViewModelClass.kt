package com.example.studifyy

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.room.Room

class ViewModelClass(application: Application):AndroidViewModel(application) {
    private val dao:SavedDAOClass=SavedDataBase.getDatabase(application).savedDaoClass()
    val roomLiveData:LiveData<List<SavedData>> =dao.getAllData()
}