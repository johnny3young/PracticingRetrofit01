package com.young.practicingretrofit01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.util.Log
import android.widget.ImageView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        DownloadImageTask(findViewById(R.id.imageViewToShowTop))
            .execute("https://picsum.photos/200/250")

        Picasso.get().load("https://picsum.photos/200/250").into(imageViewToShowBottom)


    }

    private inner class DownloadImageTask(internal var bmImage: ImageView) :

        AsyncTask<String, Void, Bitmap>() {

        override fun doInBackground(vararg urls: String): Bitmap? {
            val urldisplay = urls[0]
            var myPicture: Bitmap? = null
            try {
                val `in` = java.net.URL(urldisplay).openStream()
                myPicture = BitmapFactory.decodeStream(`in`)
            } catch (e: Exception) {
                Log.e("Error", e.message)
                e.printStackTrace()
            }

            return myPicture
        }

        override fun onPostExecute(result: Bitmap) {
            bmImage.setImageBitmap(result)
        }
    }



}
