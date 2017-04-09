package jp.a063.android.test.myapplication

import android.app.Activity
import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import java.net.URL
import java.nio.charset.Charset
import java.util.regex.Pattern

class MainActivity : Activity() {
    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    override fun onResume(){
        super.onResume()

        object :AsyncTask<Unit,Unit,String>(){
            override protected fun doInBackground(vararg params: Unit?): String? {
                val html=URL("https://google.com").readText(Charset.defaultCharset())
                var matcher=Pattern.compile("<title>.*?</title>")?.matcher(html)
                return if (matcher!=null&&matcher.find())matcher.group() else null
            }

            override protected fun onPostExecute(result: String?) {
                Toast.makeText(getApplication(),result?:"null",Toast.LENGTH_LONG).show()
            }
        }.execute()
    }
}
