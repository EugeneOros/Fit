package pl.edu.agh.oros.fit

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Switch
import android.widget.TextView
import androidx.appcompat.widget.SwitchCompat
import androidx.recyclerview.widget.RecyclerView

class PeopleAdapter(private val peopleList: ArrayList<Person>): RecyclerView.Adapter<PeopleViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PeopleViewHolder {
        val layoutInflater: LayoutInflater = LayoutInflater.from(parent.context)
        val view:View = layoutInflater.inflate(R.layout.person, parent, false)
        return PeopleViewHolder(view)
    }

    override fun getItemCount(): Int {
        return peopleList.size
    }

    override fun onBindViewHolder(holder: PeopleViewHolder, position: Int) {
        holder.nameTextView.text = peopleList[holder.adapterPosition].name
        holder.levelTextView.text = peopleList[holder.adapterPosition].level.toString()
        holder.stateSwitch.isChecked = peopleList[holder.adapterPosition].state
    }
}

class PeopleViewHolder(val view: View): RecyclerView.ViewHolder(view){
    val nameTextView: TextView = view.findViewById(R.id.person_name)
    val levelTextView: TextView = view.findViewById(R.id.person_level)
    val stateSwitch: SwitchCompat = view.findViewById(R.id.person_state)
}