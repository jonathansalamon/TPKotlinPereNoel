package cours.intechinfo.com.perenoeltp

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.launch

class MainActivity : AppCompatActivity() {

    private lateinit var link: Link

    val emailRegex by lazy {
        // Compute regex
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)




        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

        val richCountries = mutableListOf(
                Country("France",15_000_000),
                Country("Allemagne",10_000_000),
                Country("Japon",19_000_000)
        )
        val poorCountries = mutableListOf(
                Country("Afrique du Sud",21_000_000),
                Country("Bresil",60_000_000),
                Country("Congo",3_000_000)
        )

        link = when {
            richCountries[0].name == "France" -> Link(this, "France Portal",
                    "http://www.google.fr")
            else -> Link(this, "default", "http://www.google.com")
        }

        richCountries.forEach { it.giftsCount = (it.childCount * 0.8).toInt() }

        val totalGiftForRich = richCountries.sumBy { it.giftsCount }
        val totalGifts = totalGiftForRich + poorCountries.sumBy { it.giftsCount }
        val optimizedPath = mutableListOf<Country>().apply {
            addAll(richCountries)
            addAll(poorCountries)
            sortByDescending { it.giftsCount }
        }

        text_result.text = optimizedPath.joinToString { it.name }

        launch(UI) {
            text_result.text = async { link.getResult() }.await().toString()
        }

        text_result.setOnClickListener { View.OnClickListener{ textView -> textView!!.alpha = 0f } }
        text_result.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })
    }

    fun getLink(country: Country): Link {
        return when {
            country.name == "France" -> Link(this, "France Portal",
                    "http://www.google.fr")
            else -> Link(this, "default", "http://www.google.com")
        }
    }

    fun login(username: String?, password: String?) {
        username ?: return
        password ?: return
        // Login user
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
