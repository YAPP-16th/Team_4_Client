package kr.yapp.teamplay.data

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import kr.yapp.teamplay.domain.entity.ConstValue

object SharedPreferenceManager {
    private lateinit var pref : SharedPreferences
    fun init(context : Context) {
        pref = context.getSharedPreferences(ConstValue.CONST_PREFERENCE_NAME, MODE_PRIVATE)
    }

    fun setPref(key : String, value : String) {
        pref.edit()
            .putString(key, value)
            .commit()
    }

    fun getStringPref(key : String) : String {
        return pref.getString(key, "") ?: ""
    }

    fun setPref(key : String, value : Int) {
        pref.edit()
            .putInt(key, value)
            .commit()
    }

    fun getIntPref(key : String) : Int {
        return pref.getInt(key, 0)
    }

    fun setPref(key : String, value : Boolean) {
        pref.edit()
            .putBoolean(key, value)
            .commit()
    }

    fun getBooleanPref(key : String) : Boolean {
        return pref.getBoolean(key, false)
    }

    fun setPref(key : String, value : Long) {
        pref.edit()
            .putLong(key, value)
            .commit()
    }

    fun getLongPref(key : String) : Long {
        return pref.getLong(key, 0)
    }
}
