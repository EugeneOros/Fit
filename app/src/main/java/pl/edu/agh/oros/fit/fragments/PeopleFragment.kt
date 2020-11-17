package pl.edu.agh.oros.fit.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.GridLayout
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.add_dialog.*
import kotlinx.android.synthetic.main.add_dialog.view.*
import kotlinx.android.synthetic.main.fragment_people.*
import pl.edu.agh.oros.fit.Person
import pl.edu.agh.oros.fit.R
import java.util.*

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true);

        val firebase = FirebaseDatabase.getInstance()
        peopleRef = firebase.getReference("People")


//        recyclerView_people.layoutManager = GridLayoutManager()


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
                        .setTitle("Add Person")
                addAlertDialog = addBilder.show()

                addDialogView.add_cancel_button.setOnClickListener{
                    addAlertDialog.dismiss()
                }
                addDialogView.add_submit_button.setOnClickListener{
                    val name = addAlertDialog.add_nameET.text.toString()
                    val level = addAlertDialog.add_levelET.text.toString()
                    val peopleFirebaseInput = Person(name, level, true)
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
}