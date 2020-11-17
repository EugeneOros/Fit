package pl.edu.agh.oros.fit.fragments

import android.app.AlertDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.GridLayout
import android.widget.RadioButton
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.add_dialog.*
import kotlinx.android.synthetic.main.add_dialog.view.*
import kotlinx.android.synthetic.main.fragment_people.*
import kotlinx.android.synthetic.main.fragment_people.view.*
import pl.edu.agh.oros.fit.PeopleAdapter
import pl.edu.agh.oros.fit.Person
import pl.edu.agh.oros.fit.R
import pl.edu.agh.oros.fit.SkillLevel
import java.util.*
import kotlin.collections.ArrayList
import android.widget.Adapter as Adapter

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [PeopleFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PeopleFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var peopleRef: DatabaseReference
    private lateinit var addAlertDialog:AlertDialog
    private lateinit var personList: ArrayList<Person>
    private lateinit var selectedRadioButton: RadioButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true);

        val firebase = FirebaseDatabase.getInstance()
        peopleRef = firebase.getReference("People")



//        recyclerView_people.layoutManager = GridLayoutManager(activity, 1)


        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_people, container, false)
        view.recyclerView_people.layoutManager = GridLayoutManager(activity, 1)
        peopleRef.addValueEventListener(object: ValueEventListener{
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                personList = ArrayList()
                for(i in snapshot.children){
                    val person = i.getValue(Person::class.java)
                    personList.add(person!!)
                }
                setupRecyclerViewAdapter(personList)
            }

        })

        // Inflate the layout for this fragment
        return view
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        menu.setGroupVisible(R.id.people_tool_group, true)
        menu.setGroupVisible(R.id.teams_tool_group, false)
        menu.setGroupVisible(R.id.tournament_tool_group, false)
        menu.setGroupVisible(R.id.settings_tool_group, false)
        super.onPrepareOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.add_item -> {
                val addDialogView = LayoutInflater.from(activity).inflate(R.layout.add_dialog, null)
                val addBilder = AlertDialog.Builder(activity)
                        .setView(addDialogView)
                addAlertDialog = addBilder.show()
                addAlertDialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

                addDialogView.add_cancel_button.setOnClickListener{
                    addAlertDialog.dismiss()
                }
                addDialogView.add_submit_button.setOnClickListener{
                    val name = addAlertDialog.add_nameET.editText?.text.toString()
                    var skillLevel: String = "BEGINNER"
                    selectedRadioButton = addAlertDialog.findViewById(addAlertDialog.radioGroup_skillLevel.checkedRadioButtonId)
                    when(selectedRadioButton.id){
                        R.id.radioButton_beginner -> skillLevel = "BEGINNER"
                        R.id.radioButton_intermediate -> skillLevel = "INTERMEDIATE"
                        R.id.radioButton_proficient -> skillLevel = "SkillLevel.PROFICIENT"
                        R.id.radioButton_advanced -> skillLevel = "ADVANCED"
                        R.id.radioButton_expert -> skillLevel = "EXPERT"
                    }
                    val peopleFirebaseInput = Person(name, skillLevel, true)
                    peopleRef.child("${Date().time}").setValue(peopleFirebaseInput)
                    addAlertDialog.dismiss()
                }
            }
            R.id.search_item -> {
                Toast.makeText(activity, "Search clicked", Toast.LENGTH_SHORT).show()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment PeopleFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            PeopleFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    private fun setupRecyclerViewAdapter(personList: ArrayList<Person>){
        recyclerView_people. adapter = PeopleAdapter(personList)
    }
}