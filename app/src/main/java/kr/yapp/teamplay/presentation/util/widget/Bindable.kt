package kr.yapp.teamplay.presentation.util.widget

interface Bindable<in ITEM : BindableData> {
    fun bindTo(item: ITEM)
}

