package teccart.herrera.calculatorr

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import kotlin.collections.ArrayList

class BandeArrayAdapter(private val context: Context,
                        private val dataSource: ArrayList<Bande>) : BaseAdapter(){
    private val inflater:LayoutInflater
    = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

//1
override fun getCount(): Int {
    return dataSource.size
}

//2
override fun getItem(position: Int): Any {
    return dataSource[position]
}

//3
override fun getItemId(position: Int): Long {
    return position.toLong()
}

//4
override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
    // Get view for row item
    val rowView = inflater.inflate(R.layout.item_color , parent, false)
    val ivColor = rowView.findViewById(R.id.ivColor) as ImageView
    val resitance = getItem(position) as Bande
    resitance.getIdCouleur()
    ivColor.setBackgroundColor(resitance.getCodeCouleur());


    return rowView
}

}
