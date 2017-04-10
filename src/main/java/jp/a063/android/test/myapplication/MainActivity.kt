package jp.a063.android.test.myapplication

import android.app.Activity
import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import jp.a063.android.test.myapplication.R.layout.activity_main
import java.net.URL
import java.nio.charset.Charset
import java.util.regex.Pattern

class MainActivity : Activity(),View.OnClickListener{
    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val button:Button=findViewById(R.id.button)as Button
        button.setOnClickListener(this)
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

    override fun onClick(v: View?){
        Toast.makeText(this,"Tapped",Toast.LENGTH_SHORT).show()
    }
}
