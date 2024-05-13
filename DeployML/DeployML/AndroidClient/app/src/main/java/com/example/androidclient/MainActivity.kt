package com.example.andrioidclient

import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Email
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    lateinit var  retrofit : Retrofit
    lateinit var apiService : ApiService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val baseUrl = "https://4a8a-119-194-23-161.ngrok-free.app"

        retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        apiService = retrofit.create(ApiService::class.java)

        val PostBtn = findViewById<Button>(R.id.post_btn)
        val GetBtn = findViewById<Button>(R.id.get_btn)
        val PasswordEdit = findViewById<EditText>(R.id.password_edit)
        val EmailEdit = findViewById<EditText>(R.id.email_edit)
        val NameEdit = findViewById<EditText>(R.id.name_edit)
        val IntroduceEdit = findViewById<EditText>(R.id.introduce_edit)
        val GetText = findViewById<TextView>(R.id.get_text)

        PostBtn.setOnClickListener{
            val email = EmailEdit.text.toString()
            val password = PasswordEdit.text.toString()
            val name = NameEdit.text.toString()
            val introduce = IntroduceEdit.text.toString()
            apiService.requestPOST(email,password,name,introduce).enqueue(object :retrofit2.Callback<JSONObject>{
                override fun onResponse(call: Call<JSONObject>, response: Response<JSONObject>) {
                    Toast.makeText(applicationContext,"POST 성공", Toast.LENGTH_LONG).show()
                }

                override fun onFailure(call: Call<JSONObject>, t: Throwable) {
                    t.message?.let { it1 -> Log.d("TAG", it1) }
                }
            })
        }

        GetBtn.setOnClickListener{
            apiService.requestGET().enqueue(object :retrofit2.Callback<JSONObject>{
                override fun onResponse(call: Call<JSONObject>, response: Response<JSONObject>) {
                    GetText.text = response.body()!!.toString()
                }

                override fun onFailure(call: Call<JSONObject>, t: Throwable) {
                    t.message?.let { it1 -> Log.d("TAG", it1) }
                }


            })
        }

    }
}