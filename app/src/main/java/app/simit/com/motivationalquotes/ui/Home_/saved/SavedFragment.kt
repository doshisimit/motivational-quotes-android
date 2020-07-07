package app.simit.com.motivationalquotes.ui.Home_.saved

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import app.simit.com.motivationalquotes.R

class SavedFragment : Fragment() {
    private var savedViewModel: SavedViewModel? = null
    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?, savedInstanceState: Bundle?): View? {
        savedViewModel = ViewModelProviders.of(this).get(SavedViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_saved, container, false)
        val textView = root.findViewById<TextView>(R.id.text_dashboard)
        savedViewModel!!.text.observe(viewLifecycleOwner, Observer { s -> textView.text = s })
        return root
    }
}